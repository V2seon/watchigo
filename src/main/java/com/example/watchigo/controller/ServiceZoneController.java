package com.example.watchigo.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
@RequestMapping
public class ServiceZoneController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/savezone")
    public String SaveServiceZone (@RequestParam(required = false, defaultValue = "", value = "address")String address,
                                   @RequestParam(required = false, defaultValue = "", value = "address1")String address1,
                                   @RequestParam(required = false, defaultValue = "", value = "zonename")String zonename,
                                   @RequestParam(required = false, defaultValue = "", value = "zoneex")String zoneex,
                                   @RequestParam(required = false, defaultValue = "", value = "zonetype")String zonetype,
                                   @RequestParam(required = false, defaultValue = "", value = "serviceszone")String serviceszone){
        System.out.println(address);
        System.out.println(address1);
        System.out.println(zonename);
        System.out.println(zoneex);
        System.out.println(zonetype);
        System.out.println(serviceszone);
        return "/";
    }


}
