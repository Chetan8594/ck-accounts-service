package com.chetan.accounts.controller;

import com.chetan.accounts.domain.Account;
import com.chetan.accounts.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AccountsController {


    @Autowired
    private AccountsService accountsService;

    @PostMapping("/v1/accounts")
    public Mono<Account> createAccount(@RequestBody Account account){
        return accountsService.createAccount(account);
    }

    @GetMapping("/v1/user/{userId}/accounts/{accountNumber}")
    public Mono<ResponseEntity<Account>> getUserAccount(@PathVariable String userId, @PathVariable Integer accountNumber){
        return accountsService.getUserAccount(userId , accountNumber).map(account ->
                new ResponseEntity<>(account,HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/v1/user/{userId}/accounts")
    public Flux<Account> getAllUserAccounts(@PathVariable String userId){
        return accountsService.getAllUserAccounts(userId);
    }

    @PutMapping("/v1/user/{userId}/accounts/{accountNumber}")
    public Mono<Account> updateAccount(@PathVariable String userId, @PathVariable Integer accountNumber,@RequestBody Account account){
        return accountsService.updateAccount(userId, accountNumber, account);
    }

    @DeleteMapping("/v1/user/{userId}/accounts/{accountNumber}")
    public Mono<Void> deleteAccount(@PathVariable String userId, @PathVariable Integer accountNumber){
        return accountsService.deleteAccount(userId, accountNumber);
    }

}
