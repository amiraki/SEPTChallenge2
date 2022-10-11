package com.s3437237.challenge.controller;

import com.s3437237.challenge.dao.AccountDAO;
import com.s3437237.challenge.model.Account;
import com.s3437237.challenge.repository.AccountRepository;
import com.s3437237.challenge.service.AccountService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountControllerTest {

    private AccountController accountControllerUnderTest;

    @Before("")
    public void setUp() {
        accountControllerUnderTest = new AccountController();
        accountControllerUnderTest.accountRepository = mock(AccountRepository.class);
        accountControllerUnderTest.accountService = mock(AccountService.class);
    }

    @Test
    public void testGetAllAccounts() {
        // Setup
        final ResponseEntity<List<Account>> expectedResult = new ResponseEntity<>(
                List.of(new Account(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType")), HttpStatus.OK);

        // Configure AccountService.getAllAccounts(...).
        final ResponseEntity<List<Account>> listResponseEntity = new ResponseEntity<>(
                List.of(new Account(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType")), HttpStatus.OK);
        when(accountControllerUnderTest.accountService.getAllAccounts()).thenReturn(listResponseEntity);

        // Run the test
        final ResponseEntity<List<Account>> result = accountControllerUnderTest.getAllAccounts();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAllAccounts_AccountServiceReturnsNoItems() {
        // Setup
        // Configure AccountService.getAllAccounts(...).
        final ResponseEntity<List<Account>> listResponseEntity = ResponseEntity.ok(Collections.emptyList());
        when(accountControllerUnderTest.accountService.getAllAccounts()).thenReturn(listResponseEntity);

        // Run the test
        final ResponseEntity<List<Account>> result = accountControllerUnderTest.getAllAccounts();

        // Verify the results
        assertEquals(ResponseEntity.ok(Collections.emptyList()), result);
    }

    @Test
    public void testAddAccount() {
        // Setup
        final AccountDAO dao = new AccountDAO(0L, "accNumber", "accName", "balance",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType");
        final ResponseEntity<Account> expectedResult = new ResponseEntity<>(
                new Account(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"), HttpStatus.OK);

        // Configure AccountService.createNewAccount(...).
        final ResponseEntity<Account> accountResponseEntity = new ResponseEntity<>(
                new Account(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"), HttpStatus.OK);
        when(accountControllerUnderTest.accountService.createNewAccount(
                new AccountDAO(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"))).thenReturn(
                accountResponseEntity);

        // Run the test
        final ResponseEntity<Account> result = accountControllerUnderTest.addAccount(dao);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteAccount() {
        // Setup
        final Account account = new Account(0L, "accNumber", "accName", "balance",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType");
        final ResponseEntity<HttpStatus> expectedResult = new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);

        // Run the test
        final ResponseEntity<HttpStatus> result = accountControllerUnderTest.deleteAccount(account);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(accountControllerUnderTest.accountRepository).delete(new Account(0L, "accNumber", "accName", "balance",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"));
    }

    @Test
    public void testGetAccountById() {
        // Setup
        final ResponseEntity<Account> expectedResult = new ResponseEntity<>(
                new Account(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"), HttpStatus.OK);

        // Configure AccountRepository.findById(...).
        final Optional<Account> account = Optional.of(new Account(0L, "accNumber", "accName", "balance",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"));
        when(accountControllerUnderTest.accountRepository.findById(0L)).thenReturn(account);

        // Run the test
        final ResponseEntity<Account> result = accountControllerUnderTest.getAccountById(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAccountById_AccountRepositoryReturnsAbsent() {
        // Setup
        final ResponseEntity<Account> expectedResult = new ResponseEntity<>(
                new Account(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"), HttpStatus.OK);
        when(accountControllerUnderTest.accountRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<Account> result = accountControllerUnderTest.getAccountById(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdatePerson() {
        // Setup
        final Account account = new Account(0L, "accNumber", "accName", "balance",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType");
        final ResponseEntity<Account> expectedResult = new ResponseEntity<>(
                new Account(0L, "accNumber", "accName", "balance",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"), HttpStatus.OK);

        // Configure AccountRepository.save(...).
        final Account account1 = new Account(0L, "accNumber", "accName", "balance",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType");
        when(accountControllerUnderTest.accountRepository.save(new Account(0L, "accNumber", "accName", "balance",
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), "accountType"))).thenReturn(account1);

        // Run the test
        final ResponseEntity<Account> result = accountControllerUnderTest.updatePerson(account);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
