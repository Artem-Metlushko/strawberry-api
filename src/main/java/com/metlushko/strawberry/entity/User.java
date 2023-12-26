package com.metlushko.strawberry.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    public User(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Id
//    @GeneratedValue(strategy = IDENTITY)
    @GeneratedValue(strategy = SEQUENCE,generator = "seq_generator_user")
    @SequenceGenerator(name="seq_generator_user", sequenceName="users_id_seq", allocationSize=1)
    private Long id;

    private String name;

    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;


}
