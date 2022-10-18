package com.playnetz.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Long id;
    @Size(max = 25, message = "value should not be more than 25 in length")
    private String firstName;
    @Size(max = 25, message = "value should not be more than 25 in length")
    private String lastName;
    private String email;
    @ElementCollection(targetClass = RoleUser.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "userIdRole",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private Set<RoleUser> roleName;
    private String address;
//    @Pattern(regexp = "^([0]{1})[0-9]{10}$", message = "please enter a valid nigeria number")
    private String phoneNumber;
    private String password;
    private LocalDateTime createdAt;
    @Transient
    private String token;


}
