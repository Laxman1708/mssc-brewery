package guru_springframework.mssc_brewery;

import com.cat.digital.common.utils.DateTimeUtils;
import com.cat.digital.common.utils.DateTimeUtils2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.OffsetDateTime;
import java.util.Date;

@SpringBootApplication
public class MsscBreweryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBreweryApplication.class, args);

//		String current = "2024-02-20T15:30:45.123+05:30";
//		String current = "2025-02-20T11:04:45";
		String dateStr = "2025-12-31T23:59:5.999Z";
		System.out.println("Existing: "+DateTimeUtils.validateDateTimeFormat(dateStr));

		System.out.println("Modified: "+ DateTimeUtils2.validateDateTimeFormat(dateStr));

		String dateStr2 = null;
		System.out.println("Existing: "+DateTimeUtils.validateDateTimeFormat(dateStr2));

		System.out.println("Modified: "+ DateTimeUtils2.validateDateTimeFormat(dateStr2));

		String dateStr3 = "2022-06-15T08:45:00.000-07:00";
		System.out.println("Existing: "+DateTimeUtils.validateDateTimeFormat(dateStr3));

		System.out.println("Modified: "+ DateTimeUtils2.validateDateTimeFormat(dateStr3));
	}


}
