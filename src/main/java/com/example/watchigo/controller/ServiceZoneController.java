package com.example.watchigo.controller;


import com.example.watchigo.service.FileService;
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
import java.util.Date;

@Controller
@AllArgsConstructor
@RequestMapping
public class ServiceZoneController {

    private FileService fileService;

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
                                   @RequestParam(required = false, defaultValue = "", value = "ini6")String ini6){
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

        // 폴더명 넘기기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        session.setAttribute("sdf",str);

        session.setAttribute("dir","/home/apache/htdocs/WatchigoAdmin/"+zonename); // 사용자아이디값
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
            File savefile = new File(savePath, multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(savefile);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }



//        fileService.uploadFile(file);
        System.out.println("성공");
        return "test1 :: Success";
    }

//    private final String DIR = "/home/apache/htdocs/WatchigoAdmin/";
//
//    @GetMapping("/file")
//    public StreamingResponseBody img(@RequestParam("fileName")String fileName) throws Exception {
//        File file = new File(DIR+fileName);
//        final InputStream is = new FileInputStream(file);
//        return os -> {
//            readAndWrite(is,os);
//        };
//    }
//
//    private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
//        try {
//            byte[] data = new byte[2048];
//            int read = 0;
//            while ((read = is.read(data)) > 0) {
//                os.write(data, 0, read);
//            }
//            os.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private String getfolder(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", File.separator);
    }


}
