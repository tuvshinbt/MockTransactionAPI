/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs490.project.mock.transaction.api.dao.TransactionDAO;
import edu.mum.cs490.project.mock.transaction.api.entity.Account;
import edu.mum.cs490.project.mock.transaction.api.entity.Transaction;
import edu.mum.cs490.project.mock.transaction.api.model.TransactionRequest;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author tuvshuu
 */
@Service
@RequestScope
public class TransactionServiceImpl implements TransactionService {

    public TransactionServiceImpl() {
        System.out.println("\nTransactionServiceImpl has been initialized.\n");
    }

    @Autowired
    private TransactionDAO transactionDAO;

    private final ObjectMapper mapper = new ObjectMapper();

    private final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    @Override
    public String doTransaction(String transactionRequestStr) {
        TransactionRequest tr;
        try {
            tr = mapper.readValue(transactionRequestStr, TransactionRequest.class);
        } catch (IOException | NullPointerException ex) {
            logger.error("", ex);
            return "Invalid request";
        }
        Account trAccount = new Account(tr.getCardNo(), tr.getExpirationDate(), tr.getNameOnCard().toUpperCase(), tr.getCCV(), tr.getZipCode());

        Integer resultCode = 0;
        boolean doTransaction = false;
        double availableAmount = 0.0;
        double usedAmount = 0.0;

        // To get an account from db
        Account account = transactionDAO.getAccount(trAccount);
        if (account == null) {
            // Not found account
            resultCode = 0;
        } else {
            // To get last active transaction from db
            Transaction lastTransaction = transactionDAO.getLastActiveTransaction(account.getCardNo());
            if (lastTransaction == null) {
                // first transaction
                if (account.getAmount() < tr.getAmount()) {

                    // NOT enough amount
                    doTransaction = false;
                    availableAmount = 0.0;
                    resultCode = 2;
                } else {

                    // ENOUGH amound
                    doTransaction = true;
                    availableAmount = account.getAmount();
                    resultCode = 1;
                }
            } else {
                usedAmount = lastTransaction.getUsedAmount();
                availableAmount = lastTransaction.getAvailableAmount();
                if (lastTransaction.getAvailableAmount() < tr.getAmount()) {

                    // NOT enough amount
                    doTransaction = false;
                    resultCode = 2;
                } else {

                    // ENOUGH amound
                    doTransaction = true;
                    resultCode = 1;
                }
            }
        }

        if (doTransaction) {
            availableAmount = availableAmount - tr.getAmount();
            usedAmount = usedAmount + tr.getAmount();
        }
        Transaction transaction = new Transaction(trAccount.getCardNo(),
                tr.getAmount(),
                availableAmount,
                usedAmount,
                doTransaction,
                tr.getTxnId());
        transactionDAO.<Transaction>save(transaction);
        logger.info("A transaction has been inserted . Result of transaction - " + doTransaction);
        return resultCode.toString();
    }
}
