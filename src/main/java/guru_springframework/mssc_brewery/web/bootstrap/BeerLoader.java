package guru_springframework.mssc_brewery.web.bootstrap;

import guru_springframework.mssc_brewery.web.domain.Beer;
import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;
import guru_springframework.mssc_brewery.web.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBearRepo();
    }

    private void loadBearRepo() {

        if(beerRepository.count() == 0) {
            beerRepository.save(
                    Beer.builder()
                    .beerName("KFG")
                    .beerStyle(BeerStyleEnum.CHILLED)
                    .price(new BigDecimal("150.00"))
                    .quantityToBrew(200)
                    .minOnHand(20)
                            .upc(12345656L)
                    .build()
            );

            beerRepository.save(
                    Beer.builder()
                            .beerName("KFG_Extra")
                            .beerStyle(BeerStyleEnum.HOT)
                            .price(new BigDecimal("175.00"))
                            .quantityToBrew(100)
                            .minOnHand(10)
                            .upc(2345678L)
                            .build()
            );
            beerRepository.save(
                    Beer.builder()
                            .beerName("BOOMBOOM")
                            .beerStyle(BeerStyleEnum.BREEZER)
                            .price(new BigDecimal("195.00"))
                            .quantityToBrew(100)
                            .minOnHand(10)
                            .upc(876543l)
                            .build()
            );
        }
        System.out.println("Loaded beers: "+beerRepository.count());
    }
}
