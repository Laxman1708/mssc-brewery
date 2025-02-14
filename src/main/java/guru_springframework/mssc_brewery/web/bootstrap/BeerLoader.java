package guru_springframework.mssc_brewery.web.bootstrap;

import guru_springframework.mssc_brewery.web.domain.Beer;
import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;
import guru_springframework.mssc_brewery.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor // this eliminates the creation of constructor
//for final members
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

//    we can remove the below constructore by using @RequiredArgsConstructor annotation of lombok
//    public BeerLoader(BeerRepository beerRepository) {
//        this.beerRepository = beerRepository;
//    }

    @Override
    @SneakyThrows // we can remove Throws Exception with this lombok annotation
    public void run(String... args) {
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
        System.out.println("Loaded beers count: "+beerRepository.count());
    }
}
