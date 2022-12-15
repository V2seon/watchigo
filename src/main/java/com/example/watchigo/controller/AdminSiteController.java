package com.example.watchigo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping
public class AdminSiteController {

    @GetMapping("/")
    public String main(Model m, HttpServletRequest request){
        return "AdminSite/Homepage.html";
    }

    @GetMapping("/Customer_Service")
    public String Customer_Service(Model m, HttpServletRequest request){
        return "AdminSite/Customer_Service.html";
    }

    @GetMapping("/Intro")
    public String Intro(Model m, HttpServletRequest request){
        return "AdminSite/Intro.html";
    }

    @GetMapping("/AR_Factory")
    public String AR_Factory(Model m, HttpServletRequest request){
        return "AdminSite/AR_Factory.html";
    }

    @GetMapping("/WATCHIGO")
    public String WATCHIGO(Model m, HttpServletRequest request){
        return "AdminSite/WATCHIGO_.html";
    }

    @GetMapping("/Process")
    public String Process(Model m, HttpServletRequest request){
        return "AdminSite/Process.html";
    }

    @GetMapping("/Ask")
    public String Ask(Model m, HttpServletRequest request){
        return "AdminSite/Ask.html";
    }

    @GetMapping("/IP")
    public String IP(Model m, HttpServletRequest request){
        return "AdminSite/IP.html";
    }

    @GetMapping("/Plan")
    public String Plan(Model m, HttpServletRequest request){
        return "AdminSite/Plan.html";
    }

    @GetMapping("/Partners")
    public String Partners(Model m, HttpServletRequest request){
        return "AdminSite/Partners.html";
    }

    @GetMapping("/Payment")
    public String Payment(Model m, HttpServletRequest request){
        return "AdminSite/Payment.html";
    }

    @GetMapping("/Register")
    public String Register(Model m, HttpServletRequest request){
        return "AdminSite/Register.html";
    }

    @GetMapping("/Press_Release")
    public String Press_Release(Model m, HttpServletRequest request){
        return "AdminSite/Press_Release.html";
    }

    @GetMapping("/QA")
    public String QA(Model m, HttpServletRequest request){
        return "AdminSite/Q&A.html";
    }

    @GetMapping("/FAQ")
    public String FAQ(Model m, HttpServletRequest request){
        return "AdminSite/FAQ.html";
    }

    @GetMapping("/upload")
    public String upload(Model m, HttpServletRequest request){
        return "/AdminSite/upload.html";
    }

    @GetMapping("/userup")
    public String userup(Model m, HttpServletRequest request){
        return "/AdminSite/userup.html";
    }


}
