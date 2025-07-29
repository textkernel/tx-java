// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;
import com.textkernel.tx.models.api.formatter.FormatResumeRequest;
import com.textkernel.tx.models.api.formatter.FormatResumeResponse;
import com.textkernel.tx.models.api.formatter.OutputDocumentFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FormatterTests extends TestBase {
    @Test
    public void testFormatResume() {
        assertDoesNotThrow(() -> {

            String templateStr = getTestFileAsDocument("template1.docx").getAsBase64();
            byte[] templateBytes = java.util.Base64.getDecoder().decode(templateStr);

            FormatResumeRequest request = new FormatResumeRequest(TestParsedResume, templateBytes, OutputDocumentFormat.DOCX);
            FormatResumeResponse response = Client.formatter().formatResume(request);

            assertNotNull(response.Value.DocumentAsBase64String);
        });
    }
}
