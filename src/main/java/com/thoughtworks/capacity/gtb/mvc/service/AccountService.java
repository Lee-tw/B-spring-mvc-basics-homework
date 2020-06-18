package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    private Map<Integer, Account> map = new HashMap<>();

    public AccountService() {
        map.put(1, new Account(1, "test@gmail.com", "testName", "123qqq__"));
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(map.values());
    }

    public void createAccount(Account account) {
        map.put(account.getId(), account);
    }

    public Account getAccountByName(String name) {
        for (Account account: getAccounts()) {
            if (name.equals(account.getName()))
                return account;
        }
        return null;
    }

    public boolean hasAccount(String name) {
        for (Account account: getAccounts()) {
            if (name.equals(account.getName()))
                return true;
        }
        return false;
    }
}
