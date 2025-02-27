/*
 *  Copyright (c) 2019 Caterpillar Inc. All Rights Reserved.
 *
 *  This work contains Caterpillar Inc.'s unpublished
 *  proprietary information which may constitute a trade secret
 *  and/or be confidential. This work may be used only for the
 *  purposes for which it was provided, and may not be copied
 *  or disclosed to others. Copyright notice is precautionary
 *  only, and does not imply publication.
 */

package com.cat.digital.common.utils;

import lombok.extern.slf4j.Slf4j;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Util class consists of convenient conversion methods.
 */
@Slf4j
public final class DateTimeUtils {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    /**
     * Returns true only if the requested time matches the default format {@value DATE_TIME_FORMAT}.
     *
     * @param time String representation of time that is validated.
     * @return returns true only if the requested time matches the default format {@value DATE_TIME_FORMAT}.
     */
    public static boolean validateDateTimeFormat(String time) {
        if (time == null) {
            return false;
        }
        try {
            OffsetDateTime.parse(time, DATE_TIME_FORMATTER);
            System.out.println("parsed time with existing: "+time);
        } catch (Exception e) {
            log.error("Unable to parse the date format {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

}