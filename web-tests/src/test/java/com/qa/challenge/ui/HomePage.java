
package com.qa.challenge.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {

    public static final Target PRODUCT_BY_NAME = Target
            .the("product named {0}")
            .locatedBy(
                "//a[contains(@class,'hrefch') and normalize-space()='{0}']"
            );

    public static final Target HOME_LINK = Target
            .the("Home navigation link")
            .located(By.cssSelector("a.nav-link[onclick='byCat()']"));
}
