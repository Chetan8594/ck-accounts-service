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

import static com.chetan.accounts.constants.AccountsConstants.ACCOUNTS_ENDPOINT;

@RestController
@Slf4j
public class AccountsController {


    @Autowired
    private AccountsService accountsService;

    @PostMapping(ACCOUNTS_ENDPOINT)
    public Mono<Account> createAccount(@RequestBody Account account){
        return accountsService.createAccount(account);
    }

    @GetMapping(ACCOUNTS_ENDPOINT+"/{id}")
    public Mono<ResponseEntity<Account>> getAccount(@PathVariable Integer id){
        return accountsService.getAccount(id).map(account ->
                new ResponseEntity<>(account,HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(ACCOUNTS_ENDPOINT)
    public Flux<Account> getAllAccounts(){
        return accountsService.getAllAccounts();
    }

    @PutMapping(ACCOUNTS_ENDPOINT+"/{id}")
    public Mono<Account> updateAccount(@PathVariable Integer id,@RequestBody Account account){
        return accountsService.updateAccount(id, account);
    }

    @DeleteMapping(ACCOUNTS_ENDPOINT+"/{id}")
    public Mono<Void> deleteAccount(@PathVariable Integer id){
        return accountsService.deleteAccount(id);
    }

}
