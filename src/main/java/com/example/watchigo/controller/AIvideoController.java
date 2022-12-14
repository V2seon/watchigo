package com.example.watchigo.controller;

import com.example.watchigo.common.Pagination;
import com.example.watchigo.common.SessionCheck;
import com.example.watchigo.entity.ServiceZoneEntity;
import com.example.watchigo.entity.UserEntity;
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
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping
public class AIvideoController {

    @GetMapping("/aivideo")
    public String main(Model m, HttpServletRequest request, Model model){
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
}
