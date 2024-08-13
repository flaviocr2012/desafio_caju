package com.desafio.caju.exceptions;

import static com.desafio.caju.constants.TransactionConstants.GENERAL_ERROR_CODE;
import static com.desafio.caju.constants.TransactionConstants.GENERAL_ERROR_MESSAGE;

public class GeneralTransactionException extends TransactionException {
    public GeneralTransactionException() {
        super(GENERAL_ERROR_CODE, GENERAL_ERROR_MESSAGE);
    }
}

