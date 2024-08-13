package com.desafio.caju.helpers;

import com.desafio.caju.constants.TransactionConstants;
import com.desafio.caju.dtos.TransactionDTO;
import com.desafio.caju.enums.BalanceType;
import com.desafio.caju.exceptions.InsufficientFundsException;
import com.desafio.caju.exceptions.TransactionException;
import com.desafio.caju.models.Account;
import com.desafio.caju.repositories.AccountRepository;
import com.desafio.caju.services.MerchantMCCMapping;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.desafio.caju.constants.TransactionConstants.*;

@Component
public class TransactionHelper {

    public static String mapMCC(TransactionDTO transactionDTO, MerchantMCCMapping merchantMCCMapping) {
        return merchantMCCMapping.getMCCByMerchant(transactionDTO.getMerchant())
                .orElse(transactionDTO.getMcc());
    }

    public static Account getAccount(String accountId, AccountRepository accountRepository) throws TransactionException {
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new TransactionException(GENERAL_ERROR_CODE, ACCOUNT_NOT_FOUND));
    }

    public static void processTransaction(Account account, BalanceType balanceType, BigDecimal amount) throws TransactionException {
        if (hasSufficientBalance(account, balanceType, amount)) {
            debitBalance(account, balanceType, amount);
        } else if (balanceType != BalanceType.CASH && hasSufficientBalance(account, BalanceType.CASH, amount)) {
            debitBalance(account, BalanceType.CASH, amount);
        } else {
            throw new InsufficientFundsException();
        }
    }

    public static BalanceType mapMCCToBalanceType(String mcc) {
        return switch (mcc) {
            case TransactionConstants.FOOD_MCC_1, TransactionConstants.FOOD_MCC_2 -> BalanceType.FOOD;
            case TransactionConstants.MEAL_MCC_1, TransactionConstants.MEAL_MCC_2 -> BalanceType.MEAL;
            default -> BalanceType.CASH;
        };
    }

    private static boolean hasSufficientBalance(Account account, BalanceType balanceType, BigDecimal amount) {
        return switch (balanceType) {
            case FOOD -> account.getFoodBalance().compareTo(amount) >= 0;
            case MEAL -> account.getMealBalance().compareTo(amount) >= 0;
            case CASH -> account.getCashBalance().compareTo(amount) >= 0;
        };
    }

    private static void debitBalance(Account account, BalanceType balanceType, BigDecimal amount) {
        switch (balanceType) {
            case FOOD -> account.setFoodBalance(account.getFoodBalance().subtract(amount));
            case MEAL -> account.setMealBalance(account.getMealBalance().subtract(amount));
            case CASH -> account.setCashBalance(account.getCashBalance().subtract(amount));
            default -> throw new TransactionException(GENERAL_ERROR_CODE, UNKNOWN_BALANCE_TYPE);
        }
    }
}


