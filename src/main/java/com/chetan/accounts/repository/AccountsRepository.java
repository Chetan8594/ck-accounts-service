package com.chetan.accounts.repository;

import com.chetan.accounts.domain.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AccountsRepository extends ReactiveMongoRepository<Account,Integer> {
}
