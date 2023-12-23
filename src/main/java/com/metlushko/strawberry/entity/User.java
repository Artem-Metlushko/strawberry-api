package com.metlushko.strawberry.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
//    @GeneratedValue(strategy = IDENTITY)
    @GeneratedValue(strategy = SEQUENCE,generator = "seq_generator_user")
    @SequenceGenerator(name="seq_generator_user", sequenceName="users_id_seq", allocationSize=1)
    private Long id;

    private String name;

    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    public User(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
