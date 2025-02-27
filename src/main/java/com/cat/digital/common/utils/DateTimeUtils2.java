package com.cat.digital.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.DateValidator;

/**
 * Util class consists of convenient conversion methods.
 */
@Slf4j
public final class DateTimeUtils2 {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

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
        boolean isValid = DateValidator.getInstance().isValid(time, DATE_TIME_FORMAT);
        if (isValid) {
            System.out.println("Parsed time with modified: " + time);
        } else {
            log.error("Unable to parse the date format: {}", time);
        }
        return isValid;
    }
}
