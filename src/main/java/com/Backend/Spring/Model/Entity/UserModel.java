package com.Backend.Spring.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="[Users]" , schema = "dbo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //bildiğin sql tablo querysi gibi
    @Column(nullable = false ,unique = true , length = 50)
    private String username;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @Column(nullable = false,length = 255)
    private String password_hash;

    @Column(nullable = false , length = 20)
    private String status="ACTIVE";

    @Column(nullable = false , length = 20)
    private String role;

    @CreationTimestamp
    @Column
    private LocalDateTime created_at;
}
