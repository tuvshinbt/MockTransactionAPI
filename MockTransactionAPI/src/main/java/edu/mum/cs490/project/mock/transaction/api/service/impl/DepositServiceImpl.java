/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs490.project.mock.transaction.api.dao.AccountDAO;
import edu.mum.cs490.project.mock.transaction.api.dao.TransactionDAO;
import edu.mum.cs490.project.mock.transaction.api.entity.Account;
import edu.mum.cs490.project.mock.transaction.api.entity.Transaction;
import edu.mum.cs490.project.mock.transaction.api.model.DepositRequest;
import edu.mum.cs490.project.mock.transaction.api.service.DepositService;
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
public class DepositServiceImpl implements DepositService {

    public DepositServiceImpl() {
        System.out.println("\nDepositServiceImpl has been initialized.\n");
    }

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    private final ObjectMapper mapper = new ObjectMapper();

    private final Logger logger = LogManager.getLogger(DepositServiceImpl.class);

    @Override
    public String doDeposit(String depositRequestStr) {
        DepositRequest dr;
        try {
            dr = mapper.readValue(depositRequestStr, DepositRequest.class);
        } catch (IOException | NullPointerException ex) {
            logger.error("", ex);
            return "Invalid request";
        }
        Account account = new Account(dr.getDstCardNo(), dr.getExpirationDate(), dr.getNameOnCard().toUpperCase(), dr.getCCV(), dr.getZipCode());

        Integer resultCode = 0;
        double availableAmount = 0.0;

        // To get an account from db
        Account dstAccount = accountDAO.findByCardNoAndNameAndZipCodeAndCCVAndExpirationDate(account.getCardNo(), account.getName(), account.getZipCode(), account.getCCV(), account.getExpirationDate());

        if (dstAccount == null) {
            // Not found account
            resultCode = 2;
        } else {
            // FOUND amount
            resultCode = 1;
        }
        if (resultCode == 1) {

            // Deduction from source account
            availableAmount = dstAccount.getAmount() + dr.getAmount();
            dstAccount.setAmount(availableAmount);
            accountDAO.save(dstAccount);
        }
        Transaction newTransaction = new Transaction(
                null,
                dr.getDstCardNo(),
                dr.getAmount(),
                availableAmount,
                0.0,
                resultCode,
                dr.getTxnId());
        newTransaction.setPayCash(true);
        transactionDAO.save(newTransaction);

        logger.info("A deposit has been inserted . Result of deposit - " + (resultCode == 1) + "(" + resultCode + ")");
        return resultCode.toString();
    }
}