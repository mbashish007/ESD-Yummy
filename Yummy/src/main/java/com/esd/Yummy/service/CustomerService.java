package com.esd.Yummy.service;

import com.esd.Yummy.dto.CustomerRequest;
import com.esd.Yummy.dto.CustomerResponse;
import com.esd.Yummy.dto.LoginRequest;
import com.esd.Yummy.entity.Customer;
import com.esd.Yummy.exception.CustomerNotFoundException;
import com.esd.Yummy.exception.LoginFailedException;
import com.esd.Yummy.mapper.CustomerMapper;
import com.esd.Yummy.repo.CustomerRepo;
import com.esd.Yummy.utility.EncryptionUtility;
import com.esd.Yummy.utility.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final EncryptionUtility encryptionUtility;
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final JWTUtil jwtUtil;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionUtility.encode(customer.getPassword()));
        repo.save(customer);
        return "Created";
    }

    public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {
        return repo.findByEmail(email).orElseThrow(() -> new CustomerNotFoundException(
                format("Failed to find Customer:: No customer found with the provided email:: %s", email)
        ));
    }

    public CustomerResponse getCustomer(String email) throws CustomerNotFoundException {
        return mapper.toCustomerResponse(getCustomerByEmail(email));
    }

    public String loginCustomer(LoginRequest request) throws CustomerNotFoundException {
        Customer customer = getCustomerByEmail(request.email());
        if(!encryptionUtility.validates(request.password(), customer.getPassword())) {
            throw new LoginFailedException("Wrong Password or Email");
        }

        return jwtUtil.generateToken(request.email());
    }

    public void deleteCustomer(String email) {
        Customer customer = getCustomerByEmail(email);
        repo.delete(customer);
    }

    public void updateCustomer(String email, CustomerRequest request) {
        Customer customer = getCustomerByEmail(email);
        Customer updatedCustomer = Customer.builder().id(customer.getId())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
       repo.save(updatedCustomer);

    }
}
