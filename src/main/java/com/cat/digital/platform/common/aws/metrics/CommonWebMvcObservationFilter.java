package com.cat.digital.platform.common.aws.metrics;

import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CommonWebMvcObservationFilter implements ObservationFilter {

    @Override
    public Observation.Context map(Observation.Context context) {
        // Extract request and response from the context
        HttpServletRequest request = context.get(HttpServletRequest.class);
        HttpServletResponse response = context.get(HttpServletResponse.class);

        if (request != null && response != null) {
            // Add custom key-values to the observation context
            KeyValues keyValues = KeyValues.of(
                    KeyValue.of("method", request.getMethod()),
                    KeyValue.of("uri", request.getRequestURI()),
                    aggregatedStatus(response)
            );
            context.addLowCardinalityKeyValues(keyValues);
        }

        return context;
    }

    private KeyValue aggregatedStatus(HttpServletResponse response) {
//        int status = response.getStatus();
//        String statusCategory = (status >= 200 && status < 300) ? "SUCCESS" :
//                (status >= 400 && status < 500) ? "CLIENT_ERROR" :
//                        (status >= 500) ? "SERVER_ERROR" : "UNKNOWN";

        AggregatedStatus status = (response != null) ? AggregatedStatus.forStatus(response.getStatus()) : AggregatedStatus.UNKNOWN;
        return KeyValue.of("status", status.toString());
    }
}