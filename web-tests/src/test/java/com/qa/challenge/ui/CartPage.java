
package com.qa.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static final Target CART_LINK = Target
            .the("Cart navigation link")
            .located(By.id("cartur"));

    public static final Target PRODUCT_NAMES = Target
            .the("product names in cart")
            .located(By.cssSelector("#tbodyid tr td:nth-child(2)"));
}

