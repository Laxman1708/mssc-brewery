package guru_springframework.mssc_brewery.web.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return LocalDate.parse(p.readValueAs(String.class), DateTimeFormatter.BASIC_ISO_DATE);
    }
}
