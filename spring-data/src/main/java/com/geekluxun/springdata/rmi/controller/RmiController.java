package com.geekluxun.springdata.rmi.controller;

import com.geekluxun.dto.Account;
import com.geekluxun.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-10 13:33
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/rmi")
public class RmiController {

    /**
     * 需要另一个项目spring-integration启动，否则启动报错，暂时注掉
     */
    //@Autowired
    AccountService accountService;
    
    @GetMapping("/invoke")
    public Object rmiInvoke(){
        Account account = new Account();
        account.setName("luxun");
        accountService.insertAccount(account);
        
        List<Account> accountList  = accountService.getAccounts("luxun");
        return accountList.toString();        
    }
}
