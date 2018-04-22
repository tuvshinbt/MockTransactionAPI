/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs490.project.mock.transaction.api.model.TransactionRequest;
import edu.mum.cs490.project.mock.transaction.api.service.TransactionService;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final Logger logger = LogManager.getLogger(TransactionRestController.class);

    public TransactionRestController() {
        System.out.println("\n TransactionRestController has been initialized.\n");
    }

    @GetMapping("/")
    String home() {
        return "Welcome MOCK TRANSACTION API";
    }

    @PostMapping(value = "/mock/transaction/api")
    public ResponseEntity<String> doTransaction(@RequestBody String transactionRequestStr) {
        System.out.printf("### TransactionRestController %s() has been called ###\n", "doTransaction");
        System.out.println("TR - " + transactionRequestStr);
        return new ResponseEntity(transactionService.doTransaction(transactionRequestStr), HttpStatus.OK);
    }
}
