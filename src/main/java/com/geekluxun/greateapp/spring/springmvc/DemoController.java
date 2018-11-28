package com.geekluxun.greateapp.spring.springmvc;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright,2018-2019,xinxindai Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-26 9:35
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/demo")
@SessionAttributes("pet")
public class DemoController {

    /**
     * path上通过正则匹配 ":"前是变量名
     *
     * @param name
     * @param version
     * @param ext
     */
    @GetMapping("/{name:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{ext:\\.[a-z]+}")
    public void handle(@PathVariable String name, @PathVariable String version, @PathVariable String ext) {
        System.out.println("正则匹配路径:name:" + name + " version:" + version + " ext:" + ext);

    }


    /**
     * 路径匹配中使用/*
     *
     * @param request
     * @return
     */
    @GetMapping("/*.jsp")
    public Object handle2(HttpServletRequest request) {
        System.out.println("======匹配一个路径以.jsp结尾的请求url:======" + request.getRequestURL());
        return new Object();
    }

    /**
     * 路径匹配中使用/**
     * ? matches one character
     * * matches zero or more characters
     * ** matches zero or more directories in a path
     * {spring:[a-z]+} matches the regexp [a-z]+ as a path variable named "spring"
     *
     * @param request
     * @return
     */
    @GetMapping("/**/*.jsp")
    public Object handle3(HttpServletRequest request) {
        System.out.println("======匹配以任何多个路径以.jsp结尾的请求url:======" + request.getRequestURL());
        return new Object();
    }

    /**
     * 默认springMvc匹配/person /persion.* 例如 /person.pdf
     *
     * @param request
     * @return
     */
    @GetMapping("/person")
    public Object handle4(HttpServletRequest request) {
        System.out.println("======匹配person请求url:======" + request.getRequestURL());
        return new Object();
    }

    /**
     * consumes表示只接收client的content-type是application/json的body,另外增加了response的mmie类型限制
     *
     * @param pet
     * @return
     */
    @PostMapping(path = "/pets", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "测试pet")
    public Pet addPet(@RequestBody Pet pet) {
        System.out.println("======接收pet请求=====" + pet);
        return pet;
    }

    /**
     * 这里增加了请求参数myParam=11和请求头myHeader=22的匹配，如果匹配不上，此方法不会处理请求
     *
     * @param petId
     * @return
     */
    @GetMapping(path = "/pets/{petId}", params = "myParam=11", headers = "myHeader=22")
    @ResponseBody
    @ApiOperation(value = "测试pet读")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "myHeader", defaultValue = "11", paramType = "header", dataType = "string"),
                    @ApiImplicitParam(name = "myParam", defaultValue = "22", paramType = "query", dataType = "string")
            }
    )
    public Object findPet(@PathVariable String petId,
                          @RequestParam(value = "myParam", required = false) String myParam, // request 表示"可选项"
                          @RequestHeader(value = "myHeader") String myHeader) {
        System.out.println("======接收pet请求=====" + "petId:" + petId + "myParam:" + myParam + "myHeader:" + myHeader);
        Pet pet = new Pet();
        return pet;
    }


    /**
     * 矩阵变量测试未生效!!!
     *
     * @param ownerId
     * @param q
     */
    @GetMapping("/owners/{ownerId}")
    @ApiOperation(value = "测试MatrixVariable")
    public void findPet(
            @PathVariable(name = "ownerId") String ownerId,
            @MatrixVariable(value = "q", pathVar = "ownerId", required = false) String q) {
        System.out.println("======矩阵变量测试!!!======");
    }

    /**
     * 请求头注解示例
     *
     * @param encoding
     * @param accept
     * @param request
     */
    @GetMapping("/header")
    @ApiOperation(value = "head头测试")
    public void handle5(
            @RequestHeader("Accept-Encoding") String[] encoding,
            @RequestHeader("Accept") String[] accept,
            HttpServletRequest request) {
        System.out.println("header:" + request.getHeader("Accept-Encoding"));
    }


    /**
     * cookie头请求示例
     *
     * @param sessionId
     * @param dd
     */
    @GetMapping("/cookie")
    @ApiOperation(value = "cookie测试")
    public void handle6(@CookieValue("JSESSIONID") String sessionId, @CookieValue("dd") String dd) {
        System.out.println("sessionId:" + sessionId + " dd:" + dd);
    }

    /**
     * ResponseEntity的特点是类似ResponseEntity，但可以带status和header
     *
     * @return
     */
    @GetMapping("/entity")
    @ApiOperation(value = "ResponseEntity测试")
    public ResponseEntity<Object> handle7() {
        Pet pet = new Pet();
        pet.setAge(22);
        pet.setName("dog");
        pet.setColor("black");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("h1", "value1");

        ResponseEntity<Object> entity = new ResponseEntity(pet, httpHeaders, HttpStatus.OK);

        return entity;
    }

   
    @GetMapping("/jsonview")
    @ResponseBody
    @JsonView(User2.WithPasswordView.class)
    @ApiOperation(value = "@Jsonview测试")
    public User2 getUser() {
        return new User2("luxun", "7!jd#h23");
    }
}

