package com.playnetz.customer.controller;

import com.playnetz.customer.model.CustomerRegistrationRequest;
import com.playnetz.customer.model.User;
import com.playnetz.customer.service.AuthenticationService;
import com.playnetz.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("sign-up")
    public User registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest){
        log.info("new user registration {}", customerRequest);
        User user = customerService.registerCustomer(customerRequest);
        return user;
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        log.info("login user {}", user);
//        User user = customerService.loginCustomer(customerRequest);
//        ResponseEntity ok = ResponseEntity.ok(customer);
//
//        return user;
        return new ResponseEntity<>(authenticationService.signInReturnJWT(user), HttpStatus.OK);

    }
}
