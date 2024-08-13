package com.desafio.caju.constants;

public final class TransactionConstants {

    private TransactionConstants() {

    }

    public static final String SUCCESS_CODE = "00";
    public static final String INSUFFICIENT_FUNDS_CODE = "51";
    public static final String GENERAL_ERROR_CODE = "07";

    public static final String FOOD_MCC_1 = "5411";
    public static final String FOOD_MCC_2 = "5412";
    public static final String MEAL_MCC_1 = "5811";
    public static final String MEAL_MCC_2 = "5812";

    public static final String INVALID_JSON_MESSAGE = "Invalid message format. Please check the submitted JSON.";
    public static final String INVALID_DATA_MESSAGE = "Invalid data. Please check the fields and try again.";
    public static final String GENERAL_ERROR_MESSAGE = "An unexpected error occurred. Please try again later.";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds to complete the transaction.";
    public static final String ACCOUNT_NOT_FOUND = "Account not found.";
    public static final String UNKNOWN_BALANCE_TYPE = "Unknown balance type";
}

