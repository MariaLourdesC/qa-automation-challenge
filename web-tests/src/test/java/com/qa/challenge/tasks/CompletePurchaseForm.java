
package com.qa.challenge.tasks;

import com.qa.challenge.models.PurchaseData;
import com.qa.challenge.ui.PurchasePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CompletePurchaseForm implements Task {

    private final PurchaseData data;

    public CompletePurchaseForm(PurchaseData data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Enter.theValue(data.name()).into(PurchasePage.NAME),
            Enter.theValue(data.country()).into(PurchasePage.COUNTRY),
            Enter.theValue(data.city()).into(PurchasePage.CITY),
            Enter.theValue(data.card()).into(PurchasePage.CARD),
            Enter.theValue(data.month()).into(PurchasePage.MONTH),
            Enter.theValue(data.year()).into(PurchasePage.YEAR)
        );
    }

    public static CompletePurchaseForm with(PurchaseData data) {
        return instrumented(CompletePurchaseForm.class, data);
    }
}
