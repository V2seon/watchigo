package com.example.watchigo.controller;

import com.example.watchigo.common.SessionCheck;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.http.HttpHeaders;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static java.sql.DriverManager.println;

@Controller
@AllArgsConstructor
@RequestMapping("/aivideo")
public class AIvideoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName()); // log찍기용

    @GetMapping("/list")
    public String main(HttpServletRequest request, Model model){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0", "/aivideo");
            return  "AIvideo.html";
        }else{
            returnValue = "/AdminSite/HomePage.html";
        }
        return returnValue;

    }

    @GetMapping("/write")
    public String ailistenter(Model model, HttpServletRequest request) {
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0", "/aivideo");
            return  "AIvideoInsert.html";
        }else{
            returnValue = "/AdminSite/HomePage.html";
        }
        return returnValue;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/video_split")
    public String AIvideoSplit (HttpServletRequest request, Model model){
        String video_name = request.getParameter("aiinv"); // 받아온 videoname

        String url = "http://192.168.219.102:3000/watchigo/" + video_name; // flask get방식 통신 url *****************
        String get_ai_data = ""; // 가져온 데이터 {"키1":"값1","키2":"값2"} 넣어놓을 공간

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            if(conn != null){
                conn.setConnectTimeout(1000*60*10); // 10분
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if(resCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;

                    // DB저장(alv) 기능 작성하기 *****************

                   while(true){
                        line = reader.readLine();
                        if(line==null){
                            break;
                        }else {
                            get_ai_data += line;
                        }
                        println(line);
                    }
                    log.info("get_ai_data => " + get_ai_data);
                    reader.close();
                } else{
                    conn.disconnect();
                    return "null";
                }
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return get_ai_data;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/video_download")
    public String AIvideoDownload (MultipartHttpServletRequest request){
        MultipartFile video = request.getFile("aiinvideo");
        String video_name = video.getOriginalFilename();

        UUID uuid = UUID.randomUUID();
        String[] nameArr = video_name.split("[.]");
        String download_name = uuid.toString() + "." + nameArr[(nameArr.length-1)];
        String path = "D:/LeeYJ/images/videos/"+download_name; // 저장경로 ********************

//        log.info("video_name =====> {}", video_name);
//        log.info("download_name =====> {}", download_name);
//        log.info("video_path =====> {}", path);

        File fullPath = new File("/file/videos/"+download_name); // 불러올경로(확인용) ********************

        try {
            video.transferTo(new File(path));
            return download_name; // 저장명 반환
        } catch (IOException e) {
            e.printStackTrace();
            return "null";
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/ai_labeling")
    public String AIlabeling (HttpServletRequest request,
                              @RequestParam(required = false, defaultValue = "", value = "img_name")String img_name,
                              @RequestParam(required = false, defaultValue = "", value = "label_name")String label_name,
                              @RequestParam(required = false, defaultValue = "", value = "img_count")String img_count,
                              @RequestParam(required = false, defaultValue = "", value = "main_count")String main_count,
                              @RequestParam(required = false, defaultValue = "", value = "main_label")String main_label,
                              @RequestParam(required = false, defaultValue = "", value = "width")String width,
                              @RequestParam(required = false, defaultValue = "", value = "height")String height){
        String data = "?img_name="+img_name+"&label_name="+label_name+"&img_count="+img_count
                +"&main_count="+main_count+"&main_label="+main_label+"&width="+width+"&height="+height;
        String url = "http://192.168.219.102:3000/ai_labeling"+data; // flask get방식 통신 url *****************

//        log.info("data : "+data);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            if(conn != null){
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if(resCode==201){
                    log.info("flask OK");

                    // DB 변환/추가(alv/ald) 기능 작성하기 *****************

                }else {
                    log.info("flask No");
                    conn.disconnect();
                    return "null";
                }
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:";
    }



}
