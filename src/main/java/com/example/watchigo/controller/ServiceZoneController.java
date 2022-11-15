package com.example.watchigo.controller;


import com.example.watchigo.common.SessionCheck;
import com.example.watchigo.dto.RectangleDto;
import com.example.watchigo.dto.ServiceZoneDto;
import com.example.watchigo.entity.ServiceZoneEntity;
import com.example.watchigo.repository.ServiceZoneRepository;
import com.example.watchigo.service.FileService;
import com.example.watchigo.service.RentangleService;
import com.example.watchigo.service.ServiceZoneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@AllArgsConstructor
@RequestMapping
public class ServiceZoneController {

    private FileService fileService;
    private ServiceZoneService serviceZoneService;
    private ServiceZoneRepository serviceZoneRepository;
    private RentangleService rentangleService;

    @GetMapping("/servicezone")
    public String main(Model m, HttpServletRequest request){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            return  "test1.html";
        }else{
            returnValue = "login.html";
        }
        return returnValue;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/savezone")
    public String SaveServiceZone (HttpServletRequest request, Model model,
                                   @RequestParam(required = false, defaultValue = "", value = "address")String address,
                                   @RequestParam(required = false, defaultValue = "", value = "address1")String address1,
                                   @RequestParam(required = false, defaultValue = "", value = "zonename")String zonename,
                                   @RequestParam(required = false, defaultValue = "", value = "zoneex")String zoneex,
                                   @RequestParam(required = false, defaultValue = "", value = "zonetype")String zonetype,
                                   @RequestParam(required = false, defaultValue = "", value = "serviceszone")String serviceszone,
                                   @RequestParam(required = false, defaultValue = "", value = "inv1")String inv1,
                                   @RequestParam(required = false, defaultValue = "", value = "inv2")String inv2,
                                   @RequestParam(required = false, defaultValue = "", value = "ini1")String ini1,
                                   @RequestParam(required = false, defaultValue = "", value = "ini2")String ini2,
                                   @RequestParam(required = false, defaultValue = "", value = "ini3")String ini3,
                                   @RequestParam(required = false, defaultValue = "", value = "ini4")String ini4,
                                   @RequestParam(required = false, defaultValue = "", value = "ini5")String ini5,
                                   @RequestParam(required = false, defaultValue = "", value = "ini6")String ini6,
                                   @RequestParam(required = false, defaultValue = "", value = "a")int a){
        HttpSession session = request.getSession();
        System.out.println(address);
        System.out.println(address1);
        System.out.println(zonename);
        System.out.println(zoneex);
        System.out.println(zonetype);
        System.out.println(serviceszone);
        System.out.println(inv1);
        System.out.println(inv2);
        System.out.println(ini1);
        System.out.println(ini2);
        System.out.println(ini3);
        System.out.println(ini4);
        System.out.println(ini5);
        System.out.println(ini6);
        int type = 0;
        if(zonetype == "RECTANGLE"){
            type = 0;
        }else if(zonetype == "CIRCLE"){
            type = 1;
        }else if(zonetype == "POLYGON"){
            type = 2;
        }

        String [] filedata = {inv1, inv2, ini1, ini2, ini3, ini4, ini5, ini6};

        // 파일이름 재선언
        for(int i = 0; i<filedata.length; i++){
            String id = UUID.randomUUID().toString().replace("-","");
            if(filedata[i].length()>1){
                String [] token = filedata[i].split("\\.");
                filedata[i] = id +"."+ token[1];
            }
        }

        // 폴더명 넘기기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        session.setAttribute("sdf",str);

        ServiceZoneDto serviceZoneDto = new ServiceZoneDto(null, 0L,zonename,a,address,address1,zoneex,type,str,"0",
                filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
        serviceZoneService.save(serviceZoneDto);

        //좌표값 저장
        Optional<ServiceZoneEntity> sss = serviceZoneRepository.findByzonename(zonename);
        if(type == 0){ // 사각형
            String redate[] = serviceszone.split("&");
            RectangleDto rectangleDto = new RectangleDto(sss.get().getPk(),str,redate[0],redate[1]);
            rentangleService.save(rectangleDto);
        }else if(type == 1){ //원

        }else if(type == 2){ //다각형

        }
        
        session.setAttribute("dir","/home/apache/htdocs/WatchigoAdmin/"+"tjswo0510"); // 사용자아이디값
        return "/";
    }

    @PostMapping("/upload")
    public String uploadfile(@RequestPart("files") MultipartFile[] file, Model model,HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession();
        String DIR = (String) session.getAttribute("dir");

//        file.getOriginalFilename()
        // 폴더생성
        // 폴더명 세션으로 받지말고 DB에 입력된 값 가져와서 만들어야됨
        File savePath = new File(DIR, "123");
//(String) session.getAttribute("sdf")
        if(savePath.exists() == false){
            savePath.mkdirs();
        }

        // 기존파일삭제
        //if(file != null){
        //    File[] folder_list = savePath.listFiles();
        //    for(int j=0; j<folder_list.length; j++){
        //        folder_list[j].delete();
        //    }
        //}

        // 파일 저장
        for(MultipartFile multipartFile : file){
            int a = 0;
            File savefile = new File(savePath, multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(savefile);
                a++;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }



//        fileService.uploadFile(file);
        System.out.println("성공");
        return "test1 :: Success";
    }


}
