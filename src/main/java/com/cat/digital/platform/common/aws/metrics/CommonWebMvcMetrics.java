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

import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommonWebMvcMetrics {

    private final List<String> enabledTomcatMetrics = List.of("tomcat.threads.current");

    /**
     * Custom ObservationFilter bean to add custom tags (uri, method, aggregatedStatus) to metrics.
     */
    @Bean
    public ObservationFilter commonWebMvcObservationFilter() {
        return new CommonWebMvcObservationFilter();
    }

    /**
     * Custom meter filter to deny all tomcat metrics (there are a lot of them)
     * and only allow the ones from the enabled list.
     */
    @Bean
    public MeterFilter denyTomcatMetricsExceptEnabled() {
        return MeterFilter.deny(meterId -> {
            String name = meterId.getName();
            return name.startsWith("tomcat") && !enabledTomcatMetrics.contains(name);
        });
    }
}