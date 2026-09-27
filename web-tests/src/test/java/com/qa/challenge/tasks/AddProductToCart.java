
package com.qa.challenge.tasks;

import com.qa.challenge.ui.ProductDetailPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
            WaitUntil.the(
                ProductDetailPage.ADD_TO_CART,
                isVisible()
            ).forNoMoreThan(15).seconds(),

            Click.on(ProductDetailPage.ADD_TO_CART)
        );

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent())
                .accept();
    }

    public static AddProductToCart item() {
        return instrumented(AddProductToCart.class);
    }
}
