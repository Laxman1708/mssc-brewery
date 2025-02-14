package guru_springframework.mssc_brewery.web.mapper;

import guru_springframework.mssc_brewery.web.domain.Beer;
import guru_springframework.mssc_brewery.web.domain.Customer;
import guru_springframework.mssc_brewery.web.model.BeerDto;
import guru_springframework.mssc_brewery.web.model.CustomerDto;

@org.mapstruct.Mapper(uses = {DateMapper.class})
public interface Mapper {

    public BeerDto BeerToBeerDto(Beer beer);

    public Beer BeerDtoToBeer(BeerDto beerDto);

    public CustomerDto CustomerToCustomerDto(Customer customer);

    public Customer customerDtoToCustomer(CustomerDto customerDto);
}
