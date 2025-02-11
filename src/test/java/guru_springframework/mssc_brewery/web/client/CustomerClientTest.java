package guru_springframework.mssc_brewery.web.client;

import guru_springframework.mssc_brewery.web.model.BeerDto;
import guru_springframework.mssc_brewery.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getCustomerByIdTest() {
        CustomerDto customerDto = breweryClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomerTest() {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("Lucky").build();
        URI uri = breweryClient.saveNewCustomer(customerDto);
        assertNotNull(uri.toString());
        System.out.println("Location for saved beer is: "+uri.toString());
    }

    @Test
    void updateCustomerTest() {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("Lucky").build();
        breweryClient.updateCustomer(customerDto.getId(), customerDto);
    }

    @Test
    void deleteCustomerTest() {
        breweryClient.deleteCustomer(UUID.randomUUID());
    }
}