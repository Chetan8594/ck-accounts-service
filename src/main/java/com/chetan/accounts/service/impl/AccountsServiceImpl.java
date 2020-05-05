package com.chetan.accounts.service.impl;

import com.chetan.accounts.domain.Account;
import com.chetan.accounts.repository.AccountsRepository;
import com.chetan.accounts.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public Flux<Account> getAllAccounts() {
        return accountsRepository.findAll();
    }

    @Override
    public Mono<Account> getAccount(Integer id) {
        return accountsRepository.findById(id);
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountsRepository.save(account);
    }

    @Override
    public Mono<Account> updateAccount(Integer id, Account account) {
        return accountsRepository.findById(id).flatMap(currentAccount -> {
            currentAccount.setAvailableBalance(account.getAvailableBalance());
            return accountsRepository.save(currentAccount);
        });
    }

    @Override
    public Mono<Void> deleteAccount(Integer id) {
        return accountsRepository.deleteById(id);
    }

}
