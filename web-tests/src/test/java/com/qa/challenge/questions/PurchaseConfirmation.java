
package com.qa.challenge.questions;

import com.qa.challenge.ui.PurchasePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PurchaseConfirmation implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return PurchasePage.CONFIRMATION
                .resolveFor(actor)
                .getText();
    }

    public static PurchaseConfirmation message() {
        return new PurchaseConfirmation();
    }
}
