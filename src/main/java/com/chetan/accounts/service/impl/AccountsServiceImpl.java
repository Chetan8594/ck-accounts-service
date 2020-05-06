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
    public Flux<Account> getAllUserAccounts(String userId) {
        return accountsRepository.findByUserId(userId);
    }

    @Override
    public Mono<Account> getUserAccount(String userId, Integer accountNumber) {
        return accountsRepository.findByUserIdAndAccountNumber(userId, accountNumber);
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountsRepository.save(account);
    }

    @Override
    public Mono<Account> updateAccount(String userId, Integer accountNumber, Account account) {
        return accountsRepository.findByUserIdAndAccountNumber(userId, accountNumber).flatMap(currentAccount -> {
            currentAccount.setAvailableBalance(account.getAvailableBalance());
            return accountsRepository.save(currentAccount);
        });
    }

    @Override
    public Mono<Void> deleteAccount(String userId, Integer accountNumber) {
        return accountsRepository.deleteByUserIdAndAccountNumber(userId, accountNumber);
    }

}
