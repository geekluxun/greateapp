package com.geekluxun.greateapp.spring.springmvc;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @ApiOperation("会话中保存pet属性")
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
    @ApiOperation("会话中获取pet属性2")
    @PostMapping("/pets/get2")
    public String handle2(HttpSession session, @SessionAttribute Pet pet) {
        System.out.println("sessionI:" + session.getId());
        if (sessionId.equals(session.getId())){
            System.out.println("是同一会话:" + pet);
        }
        return null;
    }

    /**
     * RequestAttribute使用场景在Filter中传递数据 在本示例中
     * 它的值来自populateModel中setAttribute的设置的值
     * @param session
     * @param pet
     * @return
     */
    @PostMapping("/pets/get3")
    @ApiOperation("会话中获取pet属性3")
    public String handle3(HttpSession session, @RequestAttribute Pet pet, @RequestParam String color) {
        System.out.println("sessionI:" + session.getId());
        System.out.println("来自reqeust设置的attribute的pet:" + pet);
        System.out.println("来自请求URL中的请求参数color:" + color);
        if (sessionId.equals(session.getId())){
            System.out.println("是同一会话:" + pet);
        }
        return null;
    }

    
    @PostMapping("/pets/get4")
    @ApiOperation("会话中获取pet属性4")
    public String handle4(HttpSession session, @ModelAttribute Pet pet) {
        System.out.println("sessionI:" + session.getId());
        if (sessionId.equals(session.getId())){
            System.out.println("是同一会话:" + pet);
        }
        return null;
    }

    /**
     * 这个是方法级别的ModelAttribute,在所有ReqeustMap之前调用
     * @param model
     */
    @ModelAttribute
    public void populateModel(Model model, HttpServletRequest request) {
        model.addAttribute(new Pet("cat", "blue11",10));
        model.addAttribute("pet", new Pet("cat", "red123",99));
        request.setAttribute("pet", new Pet("dog!!!", "white", 12));
    }

    /**
     * 这个方法中的pet
     * @param pet
     */
    @PostMapping("/pets/get5")
    @ApiOperation("获取pet属性5")
    public void handle5(@ModelAttribute Pet pet){
        System.out.println("======获取的Model======" );
    }
    

}