package com.s3437237.challenge.repository;

import com.s3437237.challenge.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccTypeRepository extends JpaRepository<AccountType, Long> {

    boolean existsByAccountType(String accountType);
}