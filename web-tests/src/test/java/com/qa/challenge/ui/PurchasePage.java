
package com.qa.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PurchasePage {

    public static final Target PLACE_ORDER = Target
            .the("Place Order button")
            .located(By.cssSelector("button[data-target='#orderModal']"));

    public static final Target NAME = Target
            .the("customer name")
            .located(By.id("name"));

    public static final Target COUNTRY = Target
            .the("country")
            .located(By.id("country"));

    public static final Target CITY = Target
            .the("city")
            .located(By.id("city"));

    public static final Target CARD = Target
            .the("credit card")
            .located(By.id("card"));

    public static final Target MONTH = Target
            .the("expiration month")
            .located(By.id("month"));

    public static final Target YEAR = Target
            .the("expiration year")
            .located(By.id("year"));

    public static final Target PURCHASE = Target
            .the("Purchase button")
            .located(By.cssSelector("#orderModal .btn-primary"));

    public static final Target CONFIRMATION = Target
            .the("purchase confirmation")
            .located(By.cssSelector(".sweet-alert"));

    public static final Target CONFIRMATION_OK = Target
            .the("confirmation OK button")
            .located(By.cssSelector(".sweet-alert .confirm"));

        public static final Target CONFIRMATION_TITLE = Target
        .the("purchase confirmation title")
        .located(By.cssSelector(".sweet-alert h2"));

        public static final Target PURCHASE_DETAILS = Target
        .the("purchase transaction details")
        .located(By.cssSelector(".sweet-alert p.lead"));

}
