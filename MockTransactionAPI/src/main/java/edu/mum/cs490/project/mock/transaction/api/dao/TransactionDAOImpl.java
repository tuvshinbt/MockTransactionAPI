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
public class TransactionDAOImpl implements TransactionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account getAccount(Account account) {
        TypedQuery<Account> query = em.createNamedQuery("Account.find.by.fields", Account.class);
        query.setParameter("cardNo", account.getCardNo());
        query.setParameter("name", account.getName());
        query.setParameter("zipCode", account.getZipCode());
        query.setParameter("CCV", account.getCCV());
        query.setParameter("expirationDate", account.getExpirationDate());
        return TransactionDAOImpl.<Account>getSingleResultOrNull(query.getResultList());
    }

    @Override
    public Transaction getLastActiveTransaction(String cardNo) {
        TypedQuery<Transaction> query = em.createNamedQuery("Transaction.find.last.active", Transaction.class);
        query.setParameter("cardNo", cardNo);
        return TransactionDAOImpl.<Transaction>getSingleResultOrNull(query.getResultList());
    }

    private static <T> T getSingleResultOrNull(List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public <T> T save(T t) {
        em.persist(t);
        return t;
    }

}
