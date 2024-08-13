package com.desafio.caju.services;

import com.desafio.caju.dtos.TransactionDTO;
import com.desafio.caju.exceptions.TransactionException;
import com.desafio.caju.models.Account;
import com.desafio.caju.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class TransactionServiceIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        InMemoryMerchantMCCMapping merchantMCCMapping = new InMemoryMerchantMCCMapping();
        transactionService = new TransactionService(accountRepository, merchantMCCMapping);
    }

    @Test
    void testAuthorizeTransaction_InsufficientBalance_ShouldThrowException() {
        Account account = new Account("123", new BigDecimal("50.00"), new BigDecimal("50.00"), new BigDecimal("100.00"));
        accountRepository.save(account);

        TransactionDTO transactionDTO = new TransactionDTO("123", new BigDecimal("150.00"), "5411", "PADARIA DO ZE               SAO PAULO BR");

        assertThrows(TransactionException.class, () -> {
            transactionService.authorizeTransaction(transactionDTO);
        });
    }

    @Test
    void testAuthorizeTransaction_UnknownMCC_ShouldUseCashBalance() throws TransactionException {
        Account account = new Account("123", new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("300.00"));
        accountRepository.save(account);

        TransactionDTO transactionDTO = new TransactionDTO("123", new BigDecimal("100.00"), "9999", "DESCONHECIDO");
        String result = transactionService.authorizeTransaction(transactionDTO);

        assertEquals("00", result);
        Account updatedAccount = accountRepository.findByAccountId("123").orElseThrow();
        assertEquals(new BigDecimal("200.00"), updatedAccount.getCashBalance());
    }

    @Test
    void testAuthorizeTransaction_WithSufficientBalance_ShouldReturn00() throws TransactionException {
        Account account = new Account("123", new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("300.00"));
        accountRepository.save(account);

        TransactionDTO transactionDTO = new TransactionDTO("123", new BigDecimal("100.00"), "5411", "PADARIA DO ZE               SAO PAULO BR");
        String result = transactionService.authorizeTransaction(transactionDTO);

        assertEquals("00", result);
        Account updatedAccount = accountRepository.findByAccountId("123").orElseThrow();
        assertEquals(new BigDecimal("100.00"), updatedAccount.getFoodBalance());
    }

    @Test
    void testAuthorizeTransaction_WithMerchantMCCOverride_ShouldReturn00() throws TransactionException {
        InMemoryMerchantMCCMapping merchantMCCMapping = new InMemoryMerchantMCCMapping();
        merchantMCCMapping.addMapping("UBER EATS SAO PAULO BR", "5812");
        transactionService = new TransactionService(accountRepository, merchantMCCMapping);

        Account account = new Account("123", new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("300.00"));
        accountRepository.save(account);

        TransactionDTO transactionDTO = new TransactionDTO("123", new BigDecimal("100.00"), "5411", "UBER EATS                   SAO PAULO BR");
        String result = transactionService.authorizeTransaction(transactionDTO);

        assertEquals("00", result);
        Account updatedAccount = accountRepository.findByAccountId("123").orElseThrow();
        assertEquals(new BigDecimal("50.00"), updatedAccount.getMealBalance());
    }
}
