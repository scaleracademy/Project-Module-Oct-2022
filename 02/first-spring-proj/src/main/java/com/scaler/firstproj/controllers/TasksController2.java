package com.scaler.firstproj.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TasksController2 {

    @RequestMapping("/abc")
    public String print() {
        return "Controller2";
    }

    //    two functions running on same route does not work in Spring

//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello from the other side.";
//    }
}
