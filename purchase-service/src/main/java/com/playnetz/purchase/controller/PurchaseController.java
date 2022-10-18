package com.playnetz.purchase.controller;

import com.playnetz.purchase.exception.PurchaseNotFoundException;
import com.playnetz.purchase.model.Purchase;
import com.playnetz.purchase.service.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("api/v1/purchase")
@AllArgsConstructor
public class PurchaseController {


    private final PurchaseService purchaseService;

    @PostMapping 
    public ResponseEntity<?> savePurchase(@RequestBody Purchase purchase)
    {
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.CREATED);
    }

//    @DeleteMapping("{planId}")
//    public ResponseEntity<?> deleteCourse(@PathVariable Long planId)
//    {
//        purchaseService.deletePurchase(planId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<?> getAllPurchase()
    {
        return ResponseEntity.ok(purchaseService.findAllPurchase());
    }

    @GetMapping("{planId}")
    public ResponseEntity<?> getPurchase(@PathVariable Long planId)
    {
        return ResponseEntity.ok(purchaseService.findByPurchaseId(planId));
    }

    @PutMapping("{planId}")
    public ResponseEntity<Purchase> updatePlan(@PathVariable Long purchaseId, @RequestBody Purchase purchase)
    {
        Purchase updatePurchase = purchaseService.findByPurchaseId(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException("Purchase not exist with id: " + purchaseId));
        System.out.println("purchaseId = " + purchaseId + ", purchase = " + purchase);

//        var select = MotionP.musicbox;
//        System.out.println(select);

        updatePurchase.setPurchaseCreated(LocalDateTime.now());
        updatePurchase.setTitle(purchase.getTitle());
        updatePurchase.setPlanId(purchase.getPlanId());
        updatePurchase.setUserId(purchase.getUserId());
        purchaseService.savePurchase(updatePurchase);

        return ResponseEntity.ok(updatePurchase);
    }
}
