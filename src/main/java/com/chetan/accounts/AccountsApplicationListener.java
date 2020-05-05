package com.chetan.accounts;


import com.chetan.accounts.domain.Account;
import com.chetan.accounts.model.AccountsList;
import com.chetan.accounts.repository.AccountsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@Order(0)
@Slf4j
public class AccountsApplicationListener implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    protected ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createAccounts();
    }

    public void createAccounts() {
        accountsRepository.deleteAll()
                .thenMany(Flux.fromIterable(accounts())
                        .flatMap(accountsRepository::save)
                        .doOnNext(account -> {
                            log.info("Account added in MongoDB: {}",account);
                        })).blockLast();
    }

    private List<Account> accounts(){
        try {
            String jsonString = IOUtils.toString(this.getClass().getResourceAsStream("/accounts.json"), "UTF-8");
            return objectMapper.readValue(jsonString, AccountsList.class).getAccounts();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}