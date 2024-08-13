package com.desafio.caju.exceptions;

import static com.desafio.caju.constants.TransactionConstants.INSUFFICIENT_FUNDS;
import static com.desafio.caju.constants.TransactionConstants.INSUFFICIENT_FUNDS_CODE;

public class InsufficientFundsException extends TransactionException {
    public InsufficientFundsException() {
        super(INSUFFICIENT_FUNDS_CODE, INSUFFICIENT_FUNDS);
    }
}

