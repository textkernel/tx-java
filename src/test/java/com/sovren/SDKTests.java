// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren;

import com.sovren.exceptions.SovrenException;
import com.sovren.models.api.matching.request.FilterCriteria;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SDKTests extends TestBase {
    
    @Test
    public void test404Message(){
        DataCenter fakeDC = new DataCenter("https://rest.resumeparsing.com/v9/fake");
        SovrenClient client = new SovrenClient("1234", "1234", fakeDC);
        
        try {
            client.getAccountInfo();
        }
        catch (SovrenException e){
            assertEquals(404, e.HttpStatusCode);
            assertEquals("404 - Not Found", e.getMessage());
        }
    }
    
    @Test
    public void test500Message(){
        DataCenter fakeDC = new DataCenter("https://thisisnotarealurlatall-akmeaoiaefoij.com/");
        SovrenClient client = new SovrenClient("1234", "1234", fakeDC);
        
        try {
            client.getAccountInfo();
        }
        catch (SovrenException e){
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
        SovrenClient client = new SovrenClient("1234", "1234", fakeDC);
        client.ShowFullRequestBodyInExceptions = true;
        
        try {
            ArrayList<String> index = new ArrayList<>();
            index.add("testIndex");
            client.search(index, new FilterCriteria(), null, null);
        }
        catch (SovrenException e){
            String expectedRequest = "{\"IndexIdsToSearchInto\":[\"testIndex\"],\"FilterCriteria\":{\"UserDefinedTagsMustAllExist\":false,\"HasPatents\":false,\"HasSecurityCredentials\":false,\"IsAuthor\":false,\"IsPublicSpeaker\":false,\"IsMilitary\":false,\"EmployersMustAllBeCurrentEmployer\":false,\"SkillsMustAllExist\":false,\"IsTopStudent\":false,\"IsCurrentStudent\":false,\"IsRecentGraduate\":false,\"LanguagesKnownMustAllExist\":false}}";
            assertEquals(expectedRequest, e.RequestBody);
        }
    }
}
