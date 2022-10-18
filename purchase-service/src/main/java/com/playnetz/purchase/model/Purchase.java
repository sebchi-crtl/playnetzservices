package com.playnetz.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Purchase {
    @Id
    @SequenceGenerator(
            name = "purchase_id_sequence",
            sequenceName = "purchase_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_id_sequence"
    )
    private Long id;
    private Long userId;
    private Long planId;
    private String title;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "order_status",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "status_id"))
    @ElementCollection(targetClass = StatusName.class)
    @CollectionTable(name = "purchase_status",
            joinColumns = @JoinColumn(name = "purchase_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Set<StatusName> status;
    private LocalDateTime purchaseCreated;



}
