package com.softpager.cms.entities;

import com.softpager.cms.abstracts.CMSUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "user_account")
public class UserAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email(message = "please enter a valid email address")
    @NotEmpty(message = "email is needed")
    @Column(unique = true)
    private String email;


    @NotEmpty(message = "please set a password")
    private String password;

    @OneToOne(mappedBy = "account", cascade = {CascadeType.MERGE})
    @ToString.Exclude
    private CMSUser user;

}
