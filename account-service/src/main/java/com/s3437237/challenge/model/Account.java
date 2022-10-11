package com.s3437237.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private Long id;
    @Column (name = "acc_number")
    private String accNumber;
    @Column (name = "acc_name")
    private String accName;
    @Column (name = "balance")
    private String balance;
    @Column (name = "date_created")
    private Date createdAt;
    @Column (name = "accountType")
    private String accountType;

}

