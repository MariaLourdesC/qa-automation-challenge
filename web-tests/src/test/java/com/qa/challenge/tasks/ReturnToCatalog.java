
package com.qa.challenge.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ReturnToCatalog implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Open.url("https://www.demoblaze.com/")
        );
    }

    public static ReturnToCatalog homepage() {
        return instrumented(ReturnToCatalog.class);
    }
}

