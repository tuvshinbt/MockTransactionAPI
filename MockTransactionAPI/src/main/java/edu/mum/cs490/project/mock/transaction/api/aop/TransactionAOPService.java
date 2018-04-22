/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.aop;

import edu.mum.cs490.project.mock.transaction.api.util.AES;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tuvshuu
 */
@Aspect
@Component
public class TransactionAOPService {

    private final Logger logger = LogManager.getLogger(TransactionAOPService.class);

    @Autowired
    private AES aes;

    @Around("execution(* edu.mum.cs490.project.mock.transaction.api.service.*.*(..))&& args(transactionRequestStr)")
    public Object aopAroundExam(ProceedingJoinPoint pjp, String transactionRequestStr) throws Throwable {
        logger.info("# AOP BEFORE (5) #  is called on " + pjp.getSignature().toShortString() + " " + transactionRequestStr);
        String decrytedData = aes.decrypt(transactionRequestStr);
        logger.info("decrypting data - " + (decrytedData != null ? decrytedData.substring(0, 10) : decrytedData));
        Object retVal = pjp.proceed(new Object[]{decrytedData});
        logger.info("# AOP AFTER (5) #  is called on " + pjp.getSignature().toShortString() + " returnValue - " + (retVal != null ? retVal.toString() : null));
        retVal = aes.encrypt(retVal != null ? retVal.toString() : "null");
        logger.info("encrypted result - " + retVal);
        return retVal;
    }
}
