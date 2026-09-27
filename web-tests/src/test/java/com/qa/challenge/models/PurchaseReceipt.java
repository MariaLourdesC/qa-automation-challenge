package com.qa.challenge.models;

public record PurchaseReceipt(
        String id,
        int amount,
        String card,
        String name
) {}
