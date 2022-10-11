package com.s3437237.challenge.dao;
import java.util.Date;

public record AccountDAO(
        Long id,
        String accNumber,
        String accName,
        String balance,
        Date createdAt,
        String accountType
){}


