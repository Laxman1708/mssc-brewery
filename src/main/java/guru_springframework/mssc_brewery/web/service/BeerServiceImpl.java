package guru_springframework.mssc_brewery.web.service;

import guru_springframework.mssc_brewery.web.model.BeerDto;
//import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;
import guru_springframework.mssc_brewery.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("King Fisher")
                .beerStyle(BeerStyleEnum.BREEZER)
                .build();
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //need to implement update beer method
    }

    @Override
    public void deleteBeer(UUID beerId) {
        log.debug("deleting beer.... using the best features of project lombok....");
    }
}
