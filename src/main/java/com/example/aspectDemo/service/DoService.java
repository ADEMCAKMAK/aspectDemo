package com.example.aspectDemo.service;


import org.springframework.stereotype.Service;

@Service
public class DoService {

    public void doSomething(String str){

        // note: if you want see throwing advice, comment out this line.
        // throw new RuntimeException("I throw this.");
        System.out.println("\n=====>>> Content of do service "+str);
    }
}
