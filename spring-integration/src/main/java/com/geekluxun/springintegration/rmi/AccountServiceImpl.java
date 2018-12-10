package com.geekluxun.springintegration.rmi;

import com.geekluxun.dto.Account;
import com.geekluxun.service.AccountService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-10 13:27
 * @Description:
 * @Other:
 */
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public void insertAccount(Account account) {
        log.info("======insertAccount=====");  
    }

    @Override
    public List<Account> getAccounts(String name) {
        log.info("======getAccounts=====");
        return null;
    }
}
