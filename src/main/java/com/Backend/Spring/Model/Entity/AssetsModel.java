package com.Backend.Spring.Model.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "[Assets]" , schema = "[dbo]")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetsModel {

    @Id
    @Column( insertable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false ,length = 100)
    private String asset_name;

    @Column(nullable = false , length = 50)
    private String asset_type;

    @Column(nullable = true)
    private String ip_address;

    @Column(nullable = false)
    private String mac_address;

    @Column(nullable = false)
    private String status;

    @Column(nullable= true)
    private String location;

    @Column(nullable = true)
    private UUID assigned_user_id;

    @Column(nullable = true)
    private LocalDateTime updated_at;

}
