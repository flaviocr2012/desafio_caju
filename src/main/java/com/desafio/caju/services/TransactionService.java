package com.desafio.caju.services;

import com.desafio.caju.constants.TransactionConstants;
import com.desafio.caju.dtos.TransactionDTO;
import com.desafio.caju.exceptions.GeneralTransactionException;
import com.desafio.caju.exceptions.InsufficientFundsException;
import com.desafio.caju.exceptions.TransactionException;
import com.desafio.caju.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.desafio.caju.helpers.TransactionHelper.processTransaction;
import static com.desafio.caju.helpers.TransactionHelper.mapMCC;
import static com.desafio.caju.helpers.TransactionHelper.mapMCCToBalanceType;
import static com.desafio.caju.helpers.TransactionHelper.getAccount;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final MerchantMCCMapping merchantMCCMapping;

    @Autowired
    public TransactionService(AccountRepository accountRepository, MerchantMCCMapping merchantMCCMapping) {
        this.accountRepository = accountRepository;
        this.merchantMCCMapping = merchantMCCMapping;

    }

    @Transactional
    public String authorizeTransaction(TransactionDTO transactionDTO) throws TransactionException {
        try {
            var mcc = mapMCC(transactionDTO, merchantMCCMapping);

            var balanceType = mapMCCToBalanceType(mcc);

            var account = getAccount(transactionDTO.getAccountId(), accountRepository);

            processTransaction(account, balanceType, transactionDTO.getTotalAmount());

            accountRepository.save(account);

            return TransactionConstants.SUCCESS_CODE;

        } catch (InsufficientFundsException e) {
            throw new TransactionException(TransactionConstants.INSUFFICIENT_FUNDS_CODE, TransactionConstants.INSUFFICIENT_FUNDS);

        } catch (TransactionException e) {
            throw e;

        } catch (Exception e) {
            throw new GeneralTransactionException();
        }
    }
}



