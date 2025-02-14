package guru_springframework.mssc_brewery.web.client;

import guru_springframework.mssc_brewery.web.model.BeerDto;
import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    public BeerDto getValidBeer() {
        return BeerDto.builder()
                .beerName("edokatilera for testing")
                .upc(123456L)
                .price(new BigDecimal("123.43"))
                .build();
    }
    @Test
    void getBeerByIdTest() {

        BeerDto beerDto = client.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeerTest() {
        BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("KF").build();
        URI url = client.saveNewBeer(getValidBeer());
        assertNotNull(url.toString());
        System.out.println("Location for saved beer is: "+url.toString());
    }

    @Test
    void updateBeerTest() {
        BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).beerName("KF").build();
        client.updateBeer(beerDto.getId(), getValidBeer());
    }

    @Test
    void deleteBeer() {
        client.deleteBeer(UUID.randomUUID());
    }
}