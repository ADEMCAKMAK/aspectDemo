package com.example.aspectDemo.controller;


import com.example.aspectDemo.service.DoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoController {

    private final DoService doService;

    public DoController(DoService doService) {
        this.doService = doService;
    }

    @RequestMapping(value = "/do/{str}")
    public void doSomething(@PathVariable String str){
        this.doService.doSomething(str);
    }
}
