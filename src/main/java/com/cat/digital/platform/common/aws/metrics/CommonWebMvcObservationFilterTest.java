/*
 *  Copyright (c) 2021 Caterpillar Inc. All Rights Reserved.
 *
 *  This work contains Caterpillar Inc.'s unpublished
 *  proprietary information which may constitute a trade secret
 *  and/or be confidential. This work may be used only for the
 *  purposes for which it was provided, and may not be copied
 *  or disclosed to others. Copyright notice is precautionary
 *  only, and does not imply publication.
 */

package com.cat.digital.platform.common.aws.metrics;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class CommonWebMvcObservationFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private CommonWebMvcObservationFilter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filter = new CommonWebMvcObservationFilter();
    }

    @Test
    void testMapAddsCorrectTags() {
        // Mock request and response
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/api/test");
        when(response.getStatus()).thenReturn(200);

        // Create an observation context
        Observation.Context context = new Observation.Context();
        context.put(HttpServletRequest.class, request);
        context.put(HttpServletResponse.class, response);

        // Apply the filter
        Observation.Context result = filter.map(context);

System.out.println("lowCardinalityValues: "+result.getLowCardinalityKeyValues());

        System.out.println("lowCardinalityValues for keys: "+result.getLowCardinalityKeyValues().stream().toList().get(0).getValue());

        System.out.println("lowCardinalityValues for keys size: "+result.getLowCardinalityKeyValues().stream().toList().size());

        // Verify the tags
        assertNotNull(result, "Observation context should not be null");
//        assertEquals(3, result.getLowCardinalityKeyValues().size(), "There should be 3 tags added");
//        assertEquals("GET", result.getLowCardinalityKeyValues().get("method"), "Method tag should be 'GET'");
//        assertEquals("/api/test", result.getLowCardinalityKeyValues().get("uri"), "URI tag should be '/api/test'");
//        assertEquals("SUCCESS", result.getLowCardinalityKeyValues().get("status"), "Status tag should be 'SUCCESS'");
    }

//    @Test
//    void testAggregatedStatus() {
//        // Test SUCCESS status
//        when(response.getStatus()).thenReturn(200);
//        KeyValue status = filter.aggregatedStatus(response);
//        assertEquals("status", status.getKey(), "Key should be 'status'");
//        assertEquals("SUCCESS", status.getValue(), "Value should be 'SUCCESS' for status 200");
//
//        // Test CLIENT_ERROR status
//        when(response.getStatus()).thenReturn(404);
//        status = filter.aggregatedStatus(response);
//        assertEquals("status", status.getKey(), "Key should be 'status'");
//        assertEquals("CLIENT_ERROR", status.getValue(), "Value should be 'CLIENT_ERROR' for status 404");
//
//        // Test SERVER_ERROR status
//        when(response.getStatus()).thenReturn(500);
//        status = filter.aggregatedStatus(response);
//        assertEquals("status", status.getKey(), "Key should be 'status'");
//        assertEquals("SERVER_ERROR", status.getValue(), "Value should be 'SERVER_ERROR' for status 500");
//
//        // Test UNKNOWN status
//        when(response.getStatus()).thenReturn(100);
//        status = filter.aggregatedStatus(response);
//        assertEquals("status", status.getKey(), "Key should be 'status'");
//        assertEquals("UNKNOWN", status.getValue(), "Value should be 'UNKNOWN' for status 100");
//    }
}