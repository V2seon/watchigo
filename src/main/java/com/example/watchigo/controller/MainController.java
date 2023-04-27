package com.example.watchigo.controller;

import com.example.watchigo.dto.UserDto;
import com.example.watchigo.entity.UserEntity;
import com.example.watchigo.repository.UserRepository;
import com.example.watchigo.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {

    private LoginService loginService;
    private UserRepository userRepository;

    @GetMapping("/login")
    public String main(Model m, HttpServletRequest request){
        return "/AdminSite/HomePage.html";
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

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/join")
    public String join (HttpServletRequest request, Model model,
                                   @RequestParam(required = false, defaultValue = "", value = "id")String id,
                                   @RequestParam(required = false, defaultValue = "", value = "pw")String pw,
                                   @RequestParam(required = false, defaultValue = "", value = "name")String name,
                                   @RequestParam(required = false, defaultValue = "", value = "date")String date,
                                   @RequestParam(required = false, defaultValue = "", value = "phone")String phone,
                                   @RequestParam(required = false, defaultValue = "", value = "email")String email){

        UserDto userDto = new UserDto(null, id,pw,name,date,phone,email);
        loginService.save(userDto);
        return "::redirect";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "ckid")
    public HashMap<String, String> ckid(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "", value = "id") String userid){
        System.out.println(userid);
        HttpSession session = request.getSession();
        HashMap<String, String> msg = new HashMap<String, String>();

        Optional<UserEntity> optionalAdminEntity = userRepository.findByAid(userid);

        if(!optionalAdminEntity.isPresent()){
            msg.put("ckid", "1");
        }else{
            msg.put("ckid", "0");
        }
        return msg;
    }



}
