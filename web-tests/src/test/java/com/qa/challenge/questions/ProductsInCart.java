
package com.qa.challenge.questions;

import com.qa.challenge.ui.CartPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class ProductsInCart implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
        return CartPage.PRODUCT_NAMES
                .resolveAllFor(actor)
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }

    public static ProductsInCart displayed() {
        return new ProductsInCart();
    }
}
