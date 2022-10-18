package com.playnetz.customer.repository;

import com.playnetz.customer.model.RoleUser;
import com.playnetz.customer.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private CustomerRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateCustomer() {
        User user = new User();
        user.setFirstName("Okafor");
        user.setLastName("Nwokedi");
        user.setEmail("Okadi@playnetz.com");
        user.setRoleName(Collections.singleton(RoleUser.ADMIN));
        user.setPhoneNumber("+2347043943564");
        user.setAddress("107, lemondry elena lagos");
        user.setPassword("gjejrsnxbejcefgn");
        user.setCreatedAt(LocalDateTime.now());

        User saveUser = repo.save(user);

        User existUser = entityManager.find(User.class, saveUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
}