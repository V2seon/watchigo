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
public class MainController {

    private LoginService loginService;

    @GetMapping("/")
    public String main(Model m, HttpServletRequest request){
        return "login.html";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "userlogin")
    public HashMap<String, String> userlogin(@RequestParam(required = false, defaultValue = "", value = "userid") String userid,
                                             @RequestParam(required = false, defaultValue = "", value = "userpw") String userpw,
                                             HttpServletRequest request){
        HttpSession session = request.getSession();
        HashMap<String, String> msg = new HashMap<String, String>();
//        try {
//            userpw = encrypt(userpw);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        int loginResult = loginService.loginAdmin(userid, userpw);
        if(loginResult == 1){
            //로그인성공
            msg.put("loginResult", "1");
            session.setAttribute("userid", userid);
        }else{
            //로그인실패
            msg.put("loginResult", "0");
        }
        return msg;
    }



}
