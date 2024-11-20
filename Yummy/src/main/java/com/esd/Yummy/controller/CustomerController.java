package com.esd.Yummy.controller;

import com.esd.Yummy.dto.CustomerRequest;
import com.esd.Yummy.dto.CustomerResponse;
import com.esd.Yummy.dto.LoginRequest;
import com.esd.Yummy.exception.InvalidAuthTokenException;
import com.esd.Yummy.service.CustomerService;
import com.esd.Yummy.utility.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JWTUtil jwtUtil;
    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(customerService.loginCustomer(request));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> getCustomer(@RequestHeader("Authorization") String authHeader) {
        authHeader = authHeader.replace("Bearer ", "");
        if(jwtUtil.validateToken(authHeader, "")) {
            String email = jwtUtil.extractUsername(authHeader);
            return ResponseEntity.ok(customerService.getCustomerByEmail(email).toString());
        }
        throw new InvalidAuthTokenException("Invalid token");
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String authHeader,
                                                 @RequestBody CustomerRequest request) {
        authHeader = authHeader.replace("Bearer ", "");
        if(jwtUtil.validateToken(authHeader, "")) {
            String email = jwtUtil.extractUsername(authHeader);
            customerService.updateCustomer(email, request);
            return ResponseEntity.ok("Customer Updated Successfully");
        }
        throw new InvalidAuthTokenException("Invalid token");

    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String authHeader) {

        authHeader = authHeader.replace("Bearer ", "");
        if(jwtUtil.validateToken(authHeader, "")) {
            String email = jwtUtil.extractUsername(authHeader);
            customerService.deleteCustomer(email);
            return ResponseEntity.ok("Customer Deleted Successfully");
        }
        throw new InvalidAuthTokenException("Invalid token");
    }
}
