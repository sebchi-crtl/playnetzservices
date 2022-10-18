package com.playnetz.customer.service;

import com.playnetz.customer.model.RoleUser;
import com.playnetz.customer.model.User;
import com.playnetz.customer.model.CustomerLoginRequest;
import com.playnetz.customer.model.CustomerRegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService
{

    User registerCustomer(CustomerRegistrationRequest request);

    List<User> findAllCustomers();

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long customerId);

    void deleteCourse(Long customerId);

    User loginCustomer(CustomerLoginRequest customerRequest);

    void changeRole(RoleUser role, String username);
}
