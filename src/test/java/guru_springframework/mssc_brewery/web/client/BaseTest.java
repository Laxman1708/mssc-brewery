package guru_springframework.mssc_brewery.web.client;

import guru_springframework.mssc_brewery.web.model.BeerDto;
import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class BaseTest {

    BeerDto getDto() {
        return BeerDto.builder()
                .beerName("KFG")
                .beerStyle(BeerStyleEnum.CHILLED)
                .price(new BigDecimal("123"))
                .quantiryOnHand(10)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .upc(23876L)
                .localDate(LocalDate.now())
                .build();
    }
}
