package com.playnetz.purchase.service;

import com.playnetz.purchase.model.Purchase;
import com.playnetz.purchase.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public record PurchaseServiceImpl(PurchaseRepository purchaseRepository) implements PurchaseService {

    @Override
    public Purchase savePurchase(Purchase purchase){
        purchase.setPurchaseCreated(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> findAllPurchase() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> findAllPurchaseByUserId(Long user_id) {
        
        return purchaseRepository.findAllPurchaseByUserId(user_id)
                .stream().collect(toList());
    }

    @Override
    public Optional<Purchase> findByPurchaseId(Long purchaseId) {
        return purchaseRepository.findById(purchaseId);
    }
}
