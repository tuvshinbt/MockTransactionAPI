/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.controller;

import edu.mum.cs490.project.mock.transaction.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tuvshuu
 */
@RestController
public class TransactionRestController {

    @Autowired
//    @Qualifier("transactionServiceImpl")
    private TransactionService transactionService;

    public TransactionRestController() {
        System.out.println("\n TransactionRestController has been initialized.\n");
    }

    @GetMapping("/")
    String home() {
        return "Welcome MOCK TRANSACTION API";
    }

    @PostMapping(value = "/mock/transaction/api")
    public ResponseEntity<String> doTransaction(@RequestBody String data) {
        System.out.printf("### TransactionRestController %s() has been called ###\n", "doTransaction");
        System.out.println("data - " + data);
        String result = transactionService.doTransaction(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
