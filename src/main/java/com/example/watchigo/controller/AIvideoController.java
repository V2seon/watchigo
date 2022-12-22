package com.example.watchigo.controller;

import com.example.watchigo.common.Pagination;
import com.example.watchigo.common.SessionCheck;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/aivideo")
public class AIvideoController {

    @GetMapping("/list")
    public String main(HttpServletRequest request, Model model){
//        String returnValue = "";
//        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0", "/aivideo");
//            return  "AIvideo.html";
//        }else{
//            returnValue = "/AdminSite/HomePage.html";
//        }
//        return returnValue;

        return  "AIvideo.html";

    }

    @GetMapping("/write")
    public String ailistenter(Model model, HttpServletRequest request) {
        SessionCheck sessionCheck = new SessionCheck();
        model.addAttribute("nowurl0", "/aivideo");
        return "AIvideoInsert.html";

    }
}
