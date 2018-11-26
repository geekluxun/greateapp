package com.geekluxun.greateapp.spring.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
//@SessionAttributes(names = "pet")
public class EditPetForm {


    @PostMapping("/pets/save")
    public ModelAndView handle(@RequestBody Pet pet, HttpSession session) {
        System.out.println("sessionI:" + session.getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pet", pet);
        session.setAttribute("pet", pet);
        return null;
    }

    @PostMapping("/pets/me")
    public String handle2(HttpSession session, @ModelAttribute Pet pet) {
        System.out.println("sessionI:" + session.getId());
        return null;
    }

}