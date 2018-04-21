/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.bean;

import edu.mum.cs490.project.mock.transaction.api.dao.TransactionDAO;
import edu.mum.cs490.project.mock.transaction.api.entity.Account;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author tuvshuu
 */
@Component
public class InitBean implements ApplicationRunner {

    @Value("${name}")
    private String author;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("<<< MOCK TRANSACTION API has been successfully started >>>");
        System.out.printf("<<< Author - %s >>>\n", author);
        insertData();
    }

    @Autowired
    TransactionDAO transactionDAO;

    public void insertData() {

        Account account = new Account();
        account.setCardNo("01234567890123456");
        account.setCCV("CCV");
        account.setAmount(10000.0);
        account.setExpirationDate("04/2018");
        account.setName("TEST");
        account.setZipCode("52557");
        account.setCreatedAt(new Date());
        transactionDAO.<Account>save(account
        );

    }

}
