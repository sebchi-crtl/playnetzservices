package com.playnetz.purchase;

import com.playnetz.purchase.model.Purchase;
import com.playnetz.purchase.model.Status;
import com.playnetz.purchase.repository.PurchaseRepository;
import com.playnetz.purchase.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.playnetz.purchase.model.StatusName.NOT_PAID;
import static com.playnetz.purchase.model.StatusName.PAID;


@SpringBootApplication
@AllArgsConstructor
@EnableEurekaClient
public class PurchaseApplication implements CommandLineRunner {
    private final StatusRepository ar;
    private final PurchaseRepository pr;
        public static void main(String[] args){
            SpringApplication.run(PurchaseApplication.class, args);

        }

    @Override
    public void run(String... args) throws Exception {
        Status status1 = new Status(1L, PAID);
        ar.save(status1);
        Status status2 = new Status(2L, NOT_PAID);
        ar.save(status2);

        Purchase purch1 = new Purchase(5L,3L,3L,"request", Collections.singleton(PAID), LocalDateTime.now());
        pr.save(purch1);
    }
}
