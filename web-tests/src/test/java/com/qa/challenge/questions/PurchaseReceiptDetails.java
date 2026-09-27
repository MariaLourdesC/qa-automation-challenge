package com.qa.challenge.questions;

import com.qa.challenge.models.PurchaseReceipt;
import com.qa.challenge.ui.PurchasePage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseReceiptDetails implements Question<PurchaseReceipt> {

    @Override
    public PurchaseReceipt answeredBy(Actor actor) {

        String details = PurchasePage.PURCHASE_DETAILS
                .resolveFor(actor)
                .getText();

        return new PurchaseReceipt(
                extract(details, "Id:\\s*(\\d+)"),
                Integer.parseInt(
                        extract(details, "Amount:\\s*(\\d+)\\s*USD")
                ),
                extract(details, "Card Number:\\s*([^\\r\\n]+)"),
                extract(details, "Name:\\s*([^\\r\\n]+)")
        );
    }

    private String extract(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);

        if (!matcher.find()) {
            throw new AssertionError(
                    "Expected purchase detail not found: " + regex
            );
        }

        return matcher.group(1).trim();
    }

    public static PurchaseReceiptDetails displayed() {
        return new PurchaseReceiptDetails();
    }
}
