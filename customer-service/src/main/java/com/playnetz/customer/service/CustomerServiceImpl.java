package com.playnetz.customer.service;

import com.playnetz.customer.exception.CustomerAuthenticationConflictException;
import com.playnetz.customer.exception.CustomerEmailNotValidException;
import com.playnetz.customer.model.User;
import com.playnetz.customer.model.CustomerLoginRequest;
import com.playnetz.customer.model.CustomerRegistrationRequest;
import com.playnetz.customer.model.RoleUser;
import com.playnetz.customer.repository.CustomerRepository;
import com.playnetz.customer.response.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final String USER_TAKEN_EMAIL = "email or username is taken by another user";
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationService authenticationService;
    @Override
    @Transactional
    public User registerCustomer(CustomerRegistrationRequest request) {

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .roleName(Collections.singleton(RoleUser.USER))
                .address(request.address())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.customerPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        //checking if customer email is valid
        String email = request.email();
        String firstName = request.firstName();
        String lastName = request.lastName();
        String pass = request.customerPassword();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(email != null){
            Optional<User> customerByEmail = customerRepository.findByEmail(user.getEmail());

            if(customerByEmail.isPresent())
                throw new CustomerAuthenticationConflictException(USER_TAKEN_EMAIL + email);

            if (pattern.matcher(email).matches()) {
                if(pass.contains(firstName) || pass.contains(lastName)){
                    throw new CustomerEmailNotValidException("password can't contain first name and last name");
                }
                customerRepository.saveAndFlush(user);
            }
            else
                throw new CustomerEmailNotValidException("email is not valid");
        }

        //TODO: check if customer is stored in db
        //TODO: CHECK if customer is fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD-SERVICE/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                user.getId()
        );

//        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        //TODO: check if password contains first name or last name
        //TODO: check if address is valid with the country code (thinking of adding)
        return user;
    }


    @Override
    public User loginCustomer(CustomerLoginRequest request) {
        User user = User.builder()
                .email(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        return authenticationService.signInReturnJWT(user);


    }

    @Override
    public List<User> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
//        Customer customer = new Customer();
        Optional<User> customerByEmail = customerRepository.findByEmail(email);
        return customerByEmail;
    }

    @Override
    public Optional<User> findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public void deleteCourse(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public User updateUser(User updateUser) {

        return customerRepository.save(updateUser);
    }

    @Override
    public void changeRole(RoleUser newRole, String username) {
        customerRepository.updateRoleUser(username, newRole);
    }
}
