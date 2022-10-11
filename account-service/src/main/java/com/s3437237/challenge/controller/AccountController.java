package com.s3437237.challenge.controller;

import com.s3437237.challenge.dao.AccountDAO;
import com.s3437237.challenge.model.Account;
import com.s3437237.challenge.repository.AccountRepository;
import com.s3437237.challenge.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    /**
     * Get all the accounts
     *
     * @return ResponseEntity
     */
    @GetMapping("/accounts/account")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    /**
     * Create new account
     *
     * @param dao
     * @return ResponseEntity
     */
    @PostMapping("/account")
    public ResponseEntity<Account> addAccount(@RequestBody AccountDAO dao) {
        return accountService.createNewAccount(dao);
    }


    /**
     * Delete account by accId
     *
     * @param account
     * @return ResponseEntity
     */
    @RequestMapping(value = "/accounts/account", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteAccount(@RequestBody Account account) {
        try {
            //check if teacher exist in database
            Account acn = account;

            if (acn != null) {
                accountRepository.delete(account);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the account by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/accounts/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") long id) {
        try {
            //check if person exist in database
            Account accountObj = getAccount(id);

            if (accountObj != null) {
                return new ResponseEntity<>(accountObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Update account record by using it's id
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/accounts/account", method = RequestMethod.PUT)
    public ResponseEntity<Account> updatePerson(@RequestBody Account account) {

        Account accountObj = account;

        if (accountObj != null) {
            accountObj.setAccName(account.getAccName());
            accountObj.setAccountType(account.getAccountType());
            accountObj.setBalance(account.getBalance());
            accountObj.setAccNumber(account.getAccNumber());
            return new ResponseEntity<>(accountRepository.save(accountObj), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Method to get the account record by id
     *
     * @param id
     * @return Account
     */
    private Account getAccount(long id) {
        Optional<Account> accountObj = accountRepository.findById(id);

        if (accountObj.isPresent()) {
            return accountObj.get();
        }
        return null;
    }
}
