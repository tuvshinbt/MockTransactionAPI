/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.dao;

import edu.mum.cs490.project.mock.transaction.api.entity.Account;
import edu.mum.cs490.project.mock.transaction.api.entity.Transaction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tuvshuu
 */
@Repository
@Transactional
public class TransactionDAOImpl {

    @PersistenceContext
    private EntityManager em;

    public Account getAccount(Account account) {
        TypedQuery<Account> query = em.createNamedQuery("Account.find.by.fields", Account.class);
        query.setParameter("cardNo", account.getCardNo());
        query.setParameter("name", account.getName());
        query.setParameter("zipCode", account.getZipCode());
        query.setParameter("CCV", account.getCCV());
        query.setParameter("expirationDate", account.getExpirationDate());
        return TransactionDAOImpl.<Account>getSingleResultOrNull(query.getResultList());
    }

    public Transaction getLastActiveTransaction(String srcCardNo) {
        TypedQuery<Transaction> query = em.createNamedQuery("Transaction.find.last.active", Transaction.class);
        query.setParameter("srcCardNo", srcCardNo);
        return TransactionDAOImpl.<Transaction>getSingleResultOrNull(query.getResultList());
    }

    private static <T> T getSingleResultOrNull(List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public <T> T save(T t) {
        em.persist(t);
        return t;
    }

    public Account getAccountCardNo(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
