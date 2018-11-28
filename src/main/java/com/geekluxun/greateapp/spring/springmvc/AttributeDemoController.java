package com.geekluxun.greateapp.spring.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-26 16:12
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/pet")
public class AttributeDemoController {
    private String sessionId;

    /**
     * 会话中保存pet属性
     * @param pet
     * @param session
     * @param status
     * @return
     */
    @PostMapping("/pets/save")
    public ModelAndView handle(@RequestBody Pet pet, HttpSession session, SessionStatus status) {
        System.out.println("sessionI:" + session.getId());
        sessionId = session.getId();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pet", pet);
        //status.setComplete();
        //session.removeAttribute("pet");
        session.setAttribute("pet", pet);
        System.out.println("保存的pet:" + pet);
        return null;
    }

    /**
     * 如果是同一会话读取出来的pet属性值应该是相同的,其中的一个使用场景就是在Filter中传递数据
     * @param session
     * @param pet
     * @return
     */
    @PostMapping("/pets/get2")
    public String handle2(HttpSession session, @SessionAttribute Pet pet) {
        System.out.println("sessionI:" + session.getId());
        if (sessionId.equals(session.getId())){
            System.out.println("是同一会话:" + pet);
        }
        return null;
    }

    /**
     * RequestAttribute使用场景在Filter中传递数据
     * @param session
     * @param pet
     * @return
     */
    @PostMapping("/pets/get3")
    public String handle3(HttpSession session, @RequestAttribute Pet pet) {
        System.out.println("sessionI:" + session.getId());
        if (sessionId.equals(session.getId())){
            System.out.println("是同一会话:" + pet);
        }
        return null;
    }

    
    @PostMapping("/pets/get4")
    public String handle4(HttpSession session, @ModelAttribute Pet pet) {
        System.out.println("sessionI:" + session.getId());
        if (sessionId.equals(session.getId())){
            System.out.println("是同一会话:" + pet);
        }
        return null;
    }

}