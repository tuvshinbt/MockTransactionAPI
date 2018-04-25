/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.util.impl;

/**
 *
 * @author tuvshuu
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs490.project.mock.transaction.api.model.TransactionRequest;
import edu.mum.cs490.project.mock.transaction.api.util.AES;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class AESImpl implements AES {

    private SecretKeySpec secretKey;
    private byte[] key;

    private String secretKeyWord;

    public AESImpl() {
    }

    public AESImpl(String secretKeyWord) {
        this.secretKeyWord = secretKeyWord;
    }

    private final Logger logger = LogManager.getLogger(AESImpl.class);

    public void setKey() {
        MessageDigest sha = null;
        try {
            key = secretKeyWord.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error(e);
        }
    }

    @Override
    public String encrypt(String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("Error while encrypting: " + e.toString());
        }
        return null;
    }

    @Override
    public String decrypt(String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        AESImpl aes = new AESImpl();
        aes.secretKeyWord = "b23das12s";
        try {
            String json = "{\"txnId\":\"00011\",\"srcCardNo\":\"0123456789012345\",\"expirationDate\":\"04/2018\",\"nameOnCard\":\"TEST\",\"zipCode\":\"52557\",\"amount\":1.1,\"ccv\":\"CCV\"}";
            System.out.println(json + "\n");
            String encJson = aes.encrypt(json);
            System.out.println(encJson);
            String decJson = aes.decrypt(encJson);
            System.out.println(decJson);

            ObjectMapper mapper = new ObjectMapper();
            TransactionRequest transactionRequest = mapper.readValue(decJson, TransactionRequest.class);
            System.out.println(transactionRequest.getSrcCardNo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}