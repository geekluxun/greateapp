package com.geekluxun.greateapp.spring.springmvc.view.excelview;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: luxun
 * @Create: 2018-12-02 11:24
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/excel")
public class ExcelViewController {

    @GetMapping("/download")
    public ModelAndView download(Model model) {
        User user = new User();
        user.setFirstName("lu");
        user.setLastName("xun");
        List<User> users = new ArrayList<>();
        users.add(user);
        model.addAttribute("users", users);
        Map map = new HashMap(10);
        map.put("users", users);
        ExcelView view = new ExcelView();
        ModelAndView modelAndView = new ModelAndView(view, map);

        return modelAndView;
    }

}
