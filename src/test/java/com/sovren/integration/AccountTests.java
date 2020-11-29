package com.sovren.integration;

import com.sovren.TestBase;
import com.sovren.models.api.account.GetAccountInfoResponse;
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
        
        assertFalse(accountInfo.get().Info.CustomerDetails.AccountId.isBlank());
        assertNotEquals(0, accountInfo.get().Info.CustomerDetails.CreditsRemaining);
        assertNotEquals(0, accountInfo.get().Info.CustomerDetails.CreditsUsed);
        assertFalse(accountInfo.get().Info.CustomerDetails.ExpirationDate.isBlank());
        assertFalse(accountInfo.get().Info.CustomerDetails.IPAddress.isBlank());
        assertTrue(accountInfo.get().Info.CustomerDetails.MaximumConcurrentRequests > 0);
        assertFalse(accountInfo.get().Info.CustomerDetails.Name.isBlank());
        assertFalse(accountInfo.get().Info.CustomerDetails.Region.isBlank());
        assertNotNull(accountInfo.get().Info.CustomerDetails.Region);
    }
}
