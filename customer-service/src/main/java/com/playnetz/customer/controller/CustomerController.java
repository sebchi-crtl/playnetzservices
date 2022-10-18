package com.playnetz.customer.controller;

import com.playnetz.customer.config.UserPrincipal;
import com.playnetz.customer.exception.CustomerEmailNotValidException;
import com.playnetz.customer.model.RoleUser;
import com.playnetz.customer.model.User;
import com.playnetz.customer.service.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    private final PasswordEncoder passwordEncoder;


    @DeleteMapping("{customerId}")//api/customer/{courseId}
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId)
    {
        try{
            customerService.deleteCourse(customerId);
        }
        catch (Exception rse){
            throw new CustomerEmailNotValidException("user with this id = " + customerId + " not found", rse);
        }
        return new ResponseEntity<>("deletion successful with id = " + customerId,HttpStatus.OK);
    }

    @GetMapping
    public List<User> viewAllCustomer(){

        List<User> allUsers = customerService.findAllCustomers();

        log.info("view all users registered {}", allUsers);

        if(!allUsers.isEmpty()){
            return allUsers;
        }else{
            throw new CustomerEmailNotValidException("no user stored in database");
        }
    }

    @PutMapping("{planId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user)
    {
        User updateUser = customerService.findById(userId)
                .orElseThrow(() -> new CustomerEmailNotValidException("User not exist with id: " + userId));

        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setAddress(user.getAddress());
        updateUser.setPhoneNumber(user.getPhoneNumber());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        customerService.updateUser(updateUser);

        return ResponseEntity.ok(updateUser);
    }


    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable RoleUser role)
    {
        customerService.changeRole(role, userPrincipal.getUsername());


        return ResponseEntity.ok(true);
    }


    @GetMapping("{customerId}")
    public ResponseEntity<User> getUserById(@PathVariable Long customerId)
    {
        log.info("view user id registered {}", customerId);
        System.out.println("user Id = " + customerId + " not valid");
        ResponseEntity ok = ResponseEntity.ok(customerService.findById(customerId)
                .orElseThrow(() -> new CustomerEmailNotValidException("Customer with " + customerId + " as an id does not exists")));
        return ok;


    }


//    private static final List<Customer> CUSTOMER = Arrays.asList(
//        new Customer(
//                1,
//                "Amina",
//                "Farida",
//                "rina@playnetz.com",
//                List.of(RoleUser.USER),
//                "+2349044294541",
//                "06, buhari elena kano",
//                "vxvxxxuxjbdjjcjcbcvxvxvx",
//                LocalDateTime.now()
//        ),
//        new Customer(
//                2,
//                "James",
//                "Smith",
//                "jath@playnetz.com",
//                List.of(RoleUser.ADMIN),
//                "+2348187654336",
//                "14, erickson london",
//                "xxcxcchgjfycyhxcxcxc",
//                LocalDateTime.now()
//        ),
//        new Customer(
//            3,
//            "Jones",
//            "Nwobi",
//            "onbi@playnetz.com",
//            List.of(RoleUser.PRODUCER),
//            "+2348058654584",
//            "92, ossify midline abuja",
//            "weejrsnxbgufoiu",
//            LocalDateTime.now()
//        ),
//        new Customer(
//                4,
//                "Okafor",
//                "Nwokedi",
//                "Okadi@playnetz.com",
//                List.of(RoleUser.ADMIN),
//                "+2347043943564",
//                "107, lemondry elena lagos",
//                "gjejrsnxbejcefgn",
//                LocalDateTime.now()
//        )
//    );


//    @GetMapping(value = "fake_user/{id}")
//    public Customer findOne(@PathVariable Integer id) {
//        return CUSTOMER.stream()
//                .filter(customer -> id.equals(customer.getId()))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
//    }
}
