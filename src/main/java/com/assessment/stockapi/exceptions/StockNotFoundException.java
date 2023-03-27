package com.assessment.stockapi.exceptions;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String s) {
        super(s);
    }
}
