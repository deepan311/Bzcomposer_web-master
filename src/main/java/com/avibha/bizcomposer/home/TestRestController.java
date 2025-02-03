package com.avibha.bizcomposer.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/TestRestController" })
public class TestRestController {

    @GetMapping("/testButton")
    public String testButton(HttpServletRequest request) {
        System.out.println("--------------testButton-------------");
        return "testButton-AA";
    }

    @GetMapping({ "/testButton2" })
    @ResponseStatus(HttpStatus.OK)
    public String testButton2(HttpServletRequest request) {
        System.out.println("--------------testButton2-------------");
        return "testButton-BB";
    }

}
