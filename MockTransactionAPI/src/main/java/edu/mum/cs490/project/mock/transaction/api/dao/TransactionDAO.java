/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.dao;

import edu.mum.cs490.project.mock.transaction.api.entity.Account;
import edu.mum.cs490.project.mock.transaction.api.entity.Transaction;

/**
 *
 * @author tuvshuu
 */
public interface TransactionDAO {

    Account getAccount(Account account);

    Transaction getLastActiveTransaction(Account account);

    Integer doTransaction(Transaction transaction);

}
