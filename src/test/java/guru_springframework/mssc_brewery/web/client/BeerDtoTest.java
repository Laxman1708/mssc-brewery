package guru_springframework.mssc_brewery.web.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru_springframework.mssc_brewery.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("kebab")
//@ActiveProfiles("snake") // gives snake style for attribute names. Add required propery in application-snake.properties and make that as active here.
@JsonTest
public class BeerDtoTest extends BaseTest{

    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void getJsonTest() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(getDto());
        System.out.println(json);
    }

    @Test
    public void getDeserializedObj() throws JsonProcessingException {
        BeerDto beerDto = objectMapper.readValue("{\"id\":null,\"version\":null,\"dateWhenCreated\":\"2025-02-17T21:45:05.9530037+05:30\",\"lastModifiedDate\":\"2025-02-17T21:45:05.9530037+05:30\",\"nameOfTheBeer\":\"KFG\",\"beerStyle\":\"BREEZER\",\"upc\":23876,\"price\":123,\"quantiryOnHand\":10,\"localDate\":20250224}", BeerDto.class);
        System.out.println(beerDto);
    }
}
