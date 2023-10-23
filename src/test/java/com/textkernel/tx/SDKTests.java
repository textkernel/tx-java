// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx;

import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.models.api.matching.request.FilterCriteria;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SDKTests extends TestBase {
    
    @Test
    public void test404Message(){
        DataCenter fakeDC = new DataCenter("https://rest.resumeparsing.com/v9/fake");
        TxClient client = new TxClient("1234", "1234", fakeDC);
        
        try {
            client.getAccountInfo();
        }
        catch (TxException e){
            assertEquals(404, e.HttpStatusCode);
        }
    }
    
    @Test
    public void test500Message(){
        DataCenter fakeDC = new DataCenter("https://thisisnotarealurlatall-akmeaoiaefoij.com/");
        TxClient client = new TxClient("1234", "1234", fakeDC);
        
        try {
            client.getAccountInfo();
        }
        catch (TxException e){
            assertEquals(500, e.HttpStatusCode);
            //this message is very dependent upon test environment, so simply do not check it
        }
    }
    
    @Test
    public void testSelfHostedDataCenter(){
        String url = "https://selfhosted.customer.com";
        DataCenter fakeDC = new DataCenter(url);
        ApiEndpoints endpoints = new ApiEndpoints(fakeDC);
        
        assertEquals(url + "/account", endpoints.account());
    }
    
    @Test
    public void testDebugRequestBody(){
        DataCenter fakeDC = new DataCenter("https://thisisnotarealurlatall-akmeaoiaefoij.com/");
        TxClient client = new TxClient("1234", "1234", fakeDC);
        client.ShowFullRequestBodyInExceptions = true;
        
        try {
            ArrayList<String> index = new ArrayList<>();
            index.add("testIndex");
            client.search(index, new FilterCriteria(), null, null);
        }
        catch (TxException e){
            String expectedRequest = "{\"IndexIdsToSearchInto\":[\"testIndex\"],\"FilterCriteria\":{\"UserDefinedTagsMustAllExist\":false,\"HasPatents\":false,\"HasSecurityCredentials\":false,\"IsAuthor\":false,\"IsPublicSpeaker\":false,\"IsMilitary\":false,\"EmployersMustAllBeCurrentEmployer\":false,\"SkillsMustAllExist\":false,\"IsTopStudent\":false,\"IsCurrentStudent\":false,\"IsRecentGraduate\":false,\"LanguagesKnownMustAllExist\":false}}";
            assertEquals(expectedRequest, e.RequestBody);
        }
    }
}
