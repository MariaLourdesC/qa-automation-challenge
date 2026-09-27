
package com.qa.challenge.tests;

import com.qa.challenge.tasks.OpenDemoblaze;
import com.qa.challenge.questions.PageTitle;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SerenityRunner.class)
public class DemoblazeTest {

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void shouldOpenDemoblaze() {

        Actor customer = Actor.named("Customer");
        customer.can(BrowseTheWeb.with(browser));

        customer.attemptsTo(
            OpenDemoblaze.homepage()
        );

        customer.should(
            seeThat(PageTitle.displayed(),
                containsString("STORE"))
        );
    }
}
