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

    public static final String INVALID_JSON_MESSAGE = "Formato de mensagem inválido. Verifique o JSON enviado.";
    public static final String INVALID_DATA_MESSAGE = "Dados inválidos. Verifique os campos e tente novamente.";
    public static final String GENERAL_ERROR_MESSAGE = "Ocorreu um erro inesperado. Tente novamente mais tarde.";
    public static final String INSUFFICIENT_FUNDS = "Saldo insuficiente para completar a transação.";
    public static final String ACCOUNT_NOT_FOUND = "Conta não encontrada.";
}

