
package com.qa.challenge.tasks;

import com.qa.challenge.ui.PurchasePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenPurchaseForm implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(PurchasePage.PLACE_ORDER),

            WaitUntil.the(
                PurchasePage.NAME,
                isVisible()
            ).forNoMoreThan(10).seconds()
        );
    }

    public static OpenPurchaseForm form() {
        return instrumented(OpenPurchaseForm.class);
    }
}
