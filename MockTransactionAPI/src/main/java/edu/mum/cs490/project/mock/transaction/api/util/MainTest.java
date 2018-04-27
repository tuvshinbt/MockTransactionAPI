/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs490.project.mock.transaction.api.model.DepositRequest;
import java.io.IOException;

/**
 *
 * @author tuvshuu
 */
public class MainTest {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        //For testing
        DepositRequest tr = new DepositRequest();
        tr.setAmount(1.1);
        tr.setCVV("CVV");
        tr.setDstCardNo("0123456789012345");
        tr.setExpirationDate("04/2018");
        tr.setNameOnCard("TEST");
        tr.setTxnId("00011");
        tr.setZipCode("52557");
//        tr.setDstCardNo("0123456789012399");

        try {
            //Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(tr);
            System.out.println(jsonInString);

            //Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tr);
            System.out.println(jsonInString);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
