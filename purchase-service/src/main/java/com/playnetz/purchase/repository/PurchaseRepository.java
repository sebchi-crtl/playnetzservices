package com.playnetz.purchase.repository;

import com.playnetz.purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllPurchaseByUserId(Long user_id);
}
