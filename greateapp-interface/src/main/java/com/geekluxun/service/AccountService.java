package com.geekluxun.service;

import com.geekluxun.dto.Account;

import java.util.List;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-10 13:26
 * @Description:
 * @Other:
 */
public interface AccountService {
    void insertAccount(Account account);

    List<Account> getAccounts(String name);
}

