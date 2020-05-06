package com.chetan.accounts.repository;

import com.chetan.accounts.domain.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountsRepository extends ReactiveMongoRepository<Account,Integer> {

     Mono<Account> findByUserIdAndAccountNumber(String userId ,Integer accountNumber);

     Flux<Account> findByUserId(String userId);

     Mono<Void> deleteByUserIdAndAccountNumber(String userId ,Integer accountNumber);

}
