package guru_springframework.mssc_brewery.web.service;

import guru_springframework.mssc_brewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    public CustomerDto getCustomerById(UUID customerId);

    public CustomerDto createCustomer(CustomerDto customerDto);

    public void updateCustomer(UUID customerId, CustomerDto customerDto);

    public void deleteCustomer(UUID customerId);
}
