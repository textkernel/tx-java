// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;
import com.textkernel.tx.models.api.account.GetAccountInfoResponse;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests extends TestBase
{
    @Test
    public void testGetAccount() throws Exception
    {
        AtomicReference<GetAccountInfoResponse> accountInfo = new AtomicReference<>();
        
        assertDoesNotThrow(() -> {
            accountInfo.set(Client.getAccountInfo());
        });

        assertNotNull(accountInfo.get().Info.CustomerDetails.AccountId);
        assertTrue(accountInfo.get().Info.CustomerDetails.AccountId.length() > 0);
        assertNotEquals(0, accountInfo.get().Info.CustomerDetails.CreditsRemaining);
        assertNotEquals(0, accountInfo.get().Info.CustomerDetails.CreditsUsed);
        assertNotNull(accountInfo.get().Info.CustomerDetails.ExpirationDate);
        assertTrue(accountInfo.get().Info.CustomerDetails.ExpirationDate.length() > 0);
        assertNotNull(accountInfo.get().Info.CustomerDetails.IPAddress);
        assertTrue(accountInfo.get().Info.CustomerDetails.IPAddress.length() > 0);
        assertTrue(accountInfo.get().Info.CustomerDetails.MaximumConcurrentRequests > 0);
        assertNotNull(accountInfo.get().Info.CustomerDetails.Name);
        assertTrue(accountInfo.get().Info.CustomerDetails.Name.length() > 0);
        assertNotNull(accountInfo.get().Info.CustomerDetails.Region);
        assertTrue(accountInfo.get().Info.CustomerDetails.Region.length() > 0);
        assertNotNull(accountInfo.get().Info.CustomerDetails.Region);
    }
}
