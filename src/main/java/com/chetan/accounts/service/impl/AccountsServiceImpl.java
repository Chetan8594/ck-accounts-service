package com.chetan.accounts.service.impl;

import org.apache.commons.io.IOUtils;
import com.chetan.accounts.domain.Account;
import com.chetan.accounts.model.AccountsList;
import com.chetan.accounts.repository.AccountRepository;
import com.chetan.accounts.service.AccountsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> getAccount(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Mono<Account> updateAccount(Integer id, Account account) {
        return accountRepository.findById(id).flatMap(currentAccount -> {
            currentAccount.setAvailableBalance(account.getAvailableBalance());
            return accountRepository.save(currentAccount);
        });
    }

    @Override
    public Mono<Void> deleteAccount(Integer id) {
        return accountRepository.deleteById(id);
    }

}
