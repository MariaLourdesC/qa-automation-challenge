
package com.qa.challenge.tasks;

import com.qa.challenge.ui.HomePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectProduct implements Task {

    private final String productName;

    public SelectProduct(String productName) {
        this.productName = productName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(
                HomePage.PRODUCT_BY_NAME.of(productName),
                isVisible()
            ).forNoMoreThan(15).seconds(),

            Click.on(HomePage.PRODUCT_BY_NAME.of(productName))
        );
    }

    public static SelectProduct named(String productName) {
        return instrumented(SelectProduct.class, productName);
    }
}
