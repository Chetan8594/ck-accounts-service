package com.chetan.accounts.service;

import com.chetan.accounts.domain.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountsService {

    Mono<Account> createAccount(Account account);

    Mono<Account> updateAccount(String userId, Integer accountNumber,Account account);

    Flux<Account> getAllUserAccounts(String userId);

    Mono<Account> getUserAccount(String userId, Integer accountNumber);

    Mono<Void> deleteAccount(String userId, Integer accountNumber);
}
