package com.chetan.accounts.model;

import com.chetan.accounts.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsList {

    private List<Account> accounts;
}
