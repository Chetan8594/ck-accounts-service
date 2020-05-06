package com.chetan.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account  {

    @Id
    private Integer accountNumber;
    private String userId;
    private String accountType;
    private String accountTypeDescription;
    private String accountNickname;
    private String isExternalAccount;
    private String accountStatus;
    private String accountOpenDate;
    private String accountCloseDate;
    private String baseCurrencyValue;
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private String paymentDueDate;
    private BigDecimal totalMinimumPaymentDue;
    private String nextClosingDate;
    private BigDecimal interestPercentage;
    private BigDecimal cashBackPercentage;
    private BigDecimal availableCashBackBalance;
}
