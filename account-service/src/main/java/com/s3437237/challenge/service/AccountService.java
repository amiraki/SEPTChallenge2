package com.s3437237.challenge.service;

import com.s3437237.challenge.dao.AccountDAO;
import com.s3437237.challenge.exception.ServiceException;
import com.s3437237.challenge.model.Account;
import com.s3437237.challenge.repository.AccTypeRepository;
import com.s3437237.challenge.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccTypeRepository accTypeRepository;

    // get All Accounts
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create New Account
    public ResponseEntity<Account> createNewAccount(AccountDAO dao) {
        Account account = Account.builder()
                .id(dao.id())
                .accountType(dao.accountType())
                .accNumber(dao.accNumber())
                .accName(dao.accName())
                .balance(dao.balance())
                .createdAt(dao.createdAt())
                .build();

        Account accountObj = getAccount(dao.id());

        if (accountObj != null && dao.id() == accountObj.getId()) {
            throw new ServiceException("Account already exists");
        }
        if (!accTypeRepository.existsByAccountType(dao.accountType())){
            throw new ServiceException("Account type is not valid");
        }

        try {
            accountRepository.save(account);
            return new ResponseEntity<>(account, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Account getAccount(long id) {
        Optional<Account> accountObj = accountRepository.findById(id);

        if (accountObj.isPresent()) {
            return accountObj.get();
        }
        return null;
    }
}

