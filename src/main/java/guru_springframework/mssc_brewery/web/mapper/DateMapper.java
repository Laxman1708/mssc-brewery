package guru_springframework.mssc_brewery.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime toOffsetDate(Timestamp ts) {
        if(ts != null){
            return ts.toInstant().atOffset(ZoneOffset.UTC);
        }
        else {
            return null;
        }
    }

    public Timestamp toTimeStampDate(OffsetDateTime od) {
        if(od != null){
            return Timestamp.from(od.toInstant());
        }
        else {
            return null;
        }
    }
}
