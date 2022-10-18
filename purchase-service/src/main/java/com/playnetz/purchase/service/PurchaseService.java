package com.playnetz.purchase.service;

import com.playnetz.purchase.model.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {

    Purchase savePurchase(Purchase plan);

    List<Purchase> findAllPurchase();

    List<Purchase> findAllPurchaseByUserId(Long user_id);

    Optional<Purchase> findByPurchaseId(Long purchaseId);
}
