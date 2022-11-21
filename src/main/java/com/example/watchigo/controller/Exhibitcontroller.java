package com.example.watchigo.controller;

import com.example.watchigo.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@AllArgsConstructor
@RequestMapping
public class Exhibitcontroller {

    @GetMapping("/exhibit")
    public String main(Model m, HttpServletRequest request){
        return "Exhibit.html";
    }

}
