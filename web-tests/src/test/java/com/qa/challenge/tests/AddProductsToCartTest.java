
package com.qa.challenge.tests;

import com.qa.challenge.models.PurchaseData;
import com.qa.challenge.models.PurchaseReceipt;
import com.qa.challenge.questions.ProductsInCart;
import com.qa.challenge.questions.PurchaseConfirmation;
import com.qa.challenge.questions.PurchaseReceiptDetails;
import com.qa.challenge.tasks.AddProductToCart;
import com.qa.challenge.tasks.CompletePurchaseForm;
import com.qa.challenge.tasks.ConfirmPurchase;
import com.qa.challenge.tasks.OpenCart;
import com.qa.challenge.tasks.OpenDemoblaze;
import com.qa.challenge.tasks.OpenPurchaseForm;
import com.qa.challenge.tasks.ReturnToCatalog;
import com.qa.challenge.tasks.SelectProduct;
import com.qa.challenge.ui.CartPage;
import com.qa.challenge.ui.PurchasePage;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;

@RunWith(SerenityRunner.class)
public class AddProductsToCartTest {

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void shouldAddTwoProductsToCart() {

        Actor customer = Actor.named("Customer");
        customer.can(BrowseTheWeb.with(browser));

        customer.attemptsTo(
            OpenDemoblaze.homepage(),

            SelectProduct.named("Samsung galaxy s6"),
            AddProductToCart.item(),

            ReturnToCatalog.homepage(),

            SelectProduct.named("Nokia lumia 1520"),
            AddProductToCart.item(),

            OpenCart.page(),

            WaitUntil.the(
                CartPage.PRODUCT_NAMES,
                isVisible()
            ).forNoMoreThan(15).seconds()
        );

        customer.should(
            seeThat(
                "products in cart",
                ProductsInCart.displayed(),
                containsInAnyOrder(
                    "Samsung galaxy s6",
                    "Nokia lumia 1520"
                )
            )
        );
    }

    @Test
    public void shouldCompletePurchase() {

        Actor customer = Actor.named("Customer");
        customer.can(BrowseTheWeb.with(browser));

        PurchaseData buyer = new PurchaseData(
            "QA Test",
            "Peru",
            "Lima",
            "4111111111111111",
            "09",
            "2026"
        );

        customer.attemptsTo(
            OpenDemoblaze.homepage(),

            SelectProduct.named("Samsung galaxy s6"),
            AddProductToCart.item(),

            ReturnToCatalog.homepage(),

            SelectProduct.named("Nokia lumia 1520"),
            AddProductToCart.item(),

            OpenCart.page(),

            WaitUntil.the(
                CartPage.PRODUCT_NAMES,
                isVisible()
            ).forNoMoreThan(15).seconds()
        );

        customer.should(
            seeThat(
                ProductsInCart.displayed(),
                containsInAnyOrder(
                    "Samsung galaxy s6",
                    "Nokia lumia 1520"
                )
            )
        );

        customer.attemptsTo(
            OpenPurchaseForm.form(),
            CompletePurchaseForm.with(buyer),
            ConfirmPurchase.order()
        );

                customer.should(
            seeThat(
                PurchaseConfirmation.message(),
                containsString("Thank you for your purchase!")
            )
        );

        PurchaseReceipt receipt =
                PurchaseReceiptDetails.displayed().answeredBy(customer);

        assertFalse(
                "Purchase ID should not be empty",
                receipt.id().isBlank()
        );

        assertEquals(
                "Incorrect purchase amount",
                1180,
                receipt.amount()
        );

        assertEquals(
                "Incorrect card number",
                buyer.card(),
                receipt.card()
        );

        assertEquals(
                "Incorrect customer name",
                buyer.name(),
                receipt.name()
        );

        customer.attemptsTo(
            Click.on(PurchasePage.CONFIRMATION_OK)
        );

        customer.should(
            seeThat(
                PurchaseConfirmation.message(),
                containsString("Thank you for your purchase!")
            )
        );

        customer.attemptsTo(
            Click.on(PurchasePage.CONFIRMATION_OK)
        );
    }


}
