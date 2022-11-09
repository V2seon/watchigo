package com.example.watchigo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {

    @GetMapping("/")
    public String main(Model m, HttpServletRequest request){
        return "test1.html";
    }


}
