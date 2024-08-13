package com.desafio.caju.services;

import com.desafio.caju.enums.BalanceType;
import com.desafio.caju.exceptions.TransactionException;
import com.desafio.caju.helpers.TransactionHelper;
import com.desafio.caju.models.Account;
import com.desafio.caju.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class TransactionServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testProcessTransaction_SufficientBalance_ShouldDebitCorrectly() {
        Account account = new Account("123", new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("300.00"));
        TransactionHelper.processTransaction(account, BalanceType.FOOD, new BigDecimal("100.00"));

        assertEquals(new BigDecimal("100.00"), account.getFoodBalance());
    }

    @Test
    void testProcessTransaction_InsufficientBalance_ShouldFallbackToCash() {
        Account account = new Account("123", new BigDecimal("50.00"), new BigDecimal("50.00"), new BigDecimal("300.00"));
        TransactionHelper.processTransaction(account, BalanceType.FOOD, new BigDecimal("100.00"));

        assertEquals(new BigDecimal("200.00"), account.getCashBalance());
        assertEquals(new BigDecimal("50.00"), account.getFoodBalance());
    }

    @Test
    void testMapMCCToBalanceType_ShouldReturnCorrectBalanceType() {
        assertEquals(BalanceType.FOOD, TransactionHelper.mapMCCToBalanceType("5411"));
        assertEquals(BalanceType.MEAL, TransactionHelper.mapMCCToBalanceType("5811"));
        assertEquals(BalanceType.CASH, TransactionHelper.mapMCCToBalanceType("9999"));
    }

    @Test
    void testGetAccount_ShouldThrowExceptionIfAccountNotFound() {
        assertThrows(TransactionException.class, () -> {
            TransactionHelper.getAccount("non-existent-account-id", accountRepository);
        });
    }
}

