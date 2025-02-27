package guru_springframework.mssc_brewery.web.validation;

import com.cat.digital.common.utils.DateTimeUtils;
import com.cat.digital.common.utils.DateTimeUtils2;
import org.apache.commons.validator.routines.DateValidator;

import java.time.format.DateTimeFormatter;

public class DateFormatValidation {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static void main(String[] args) {
        String dateStr = "2023-12-31T23:59:59.999Z";
        System.out.println("Existing: "+DateTimeUtils.validateDateTimeFormat(dateStr));

        System.out.println("Modified: "+ DateTimeUtils2.validateDateTimeFormat(dateStr));
    }
}
