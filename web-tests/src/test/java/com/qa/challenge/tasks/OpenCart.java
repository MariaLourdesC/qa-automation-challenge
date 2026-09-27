
package com.qa.challenge.tasks;

import com.qa.challenge.ui.CartPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenCart implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(CartPage.CART_LINK)
        );
    }

    public static OpenCart page() {
        return instrumented(OpenCart.class);
    }
}

