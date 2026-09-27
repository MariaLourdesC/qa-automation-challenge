
package com.qa.challenge.tasks;

import com.qa.challenge.ui.PurchasePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConfirmPurchase implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(PurchasePage.PURCHASE),

            WaitUntil.the(
                PurchasePage.CONFIRMATION,
                isVisible()
            ).forNoMoreThan(15).seconds()
        );
    }

    public static ConfirmPurchase order() {
        return instrumented(ConfirmPurchase.class);
    }
}

