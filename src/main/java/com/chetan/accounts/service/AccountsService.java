package com.chetan.accounts.service;

import com.chetan.accounts.domain.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountsService {

    Mono<Account> createAccount(Account account);

    Mono<Account> updateAccount(Integer id,Account account);

    Flux<Account> getAllAccounts();

    Mono<Account> getAccount(Integer id);

    Mono<Void> deleteAccount(Integer id);
}
