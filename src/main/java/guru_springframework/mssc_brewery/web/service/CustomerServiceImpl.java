package guru_springframework.mssc_brewery.web.service;

import guru_springframework.mssc_brewery.web.model.CustomerDto;
import guru_springframework.mssc_brewery.web.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Vijay").build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        System.out.println("updating customer data dummy....");
    }

    @Override
    public void deleteCustomer(UUID customerId) {

        log.info("customer deleted.....");
    }
}
