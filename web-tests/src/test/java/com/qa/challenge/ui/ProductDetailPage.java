
package com.qa.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductDetailPage {

    public static final Target ADD_TO_CART = Target
            .the("Add to cart button")
            .located(By.cssSelector("a.btn-success"));
}

