package com.example.watchigo.controller;

import com.example.watchigo.common.SessionCheck;
import com.example.watchigo.dto.AivideoALDDto;
import com.example.watchigo.dto.AivideoALVDto;
import com.example.watchigo.entity.AivideoALVEntity;
import com.example.watchigo.entity.UserEntity;
import com.example.watchigo.repository.AivideoALDRepository;
import com.example.watchigo.repository.AivideoALVRepository;
import com.example.watchigo.repository.UserRepository;
import com.example.watchigo.service.AivideoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static java.sql.DriverManager.println;

@Controller
@AllArgsConstructor
@RequestMapping("/aivideo")
public class AIvideoController {

    private AivideoService aivideoService;
    private UserRepository userRepository;
    private AivideoALVRepository aivideoALVRepository;
    private AivideoALDRepository aivideoALDRepository;
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
    @RequestMapping(method = RequestMethod.POST, value = "/video_download")
    public String AIvideoDownload (MultipartHttpServletRequest request){
        MultipartFile video = request.getFile("aiinvideo");
        String video_name = video.getOriginalFilename();

        UUID uuid = UUID.randomUUID();
        String[] nameArr = video_name.split("[.]");
        String download_name = uuid.toString() + "." + nameArr[(nameArr.length-1)];
        String path = "D:/LeeYJ/images/videos/"+download_name; // 저장경로 ********************
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
    @RequestMapping(method = RequestMethod.GET, value = "/video_split")
    public String AIvideoSplit (HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String video_name = request.getParameter("aiinv"); // 받아온 videoname
        String aiclass = request.getParameter("aiclass"); // 받아온 aiclass
        String ainame = request.getParameter("ainame"); // 받아온 ainame

        log.info("getDatas ==> " + video_name + "/" + aiclass + "/" + ainame);

        String url = "http://192.168.219.102:3000/watchigo/" + video_name; // flask get방식 통신 url *****************
        String get_ai_data = ""; // 가져온 데이터 {"키1":"값1","키2":"값2"} 넣어놓을 공간

        // DB저장(alv)
        // 날짜-시간
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        // 사용자 일련번호 가져오기
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));
        log.info("datascheck ==> "+s1.get().getAseq()+"/"+aiclass+"/"+ainame+"/"+video_name+"/"+str);

        AivideoALVDto aivideoALVDto = new AivideoALVDto(null, s1.get().getAseq(), aiclass, ainame, video_name, 0, str);
        aivideoService.alvSave(aivideoALVDto);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            if(conn != null){
                conn.setConnectTimeout(1000*60*10); // 10분
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                log.info("test!!!! ==> " + resCode);
                if(resCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;

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
                    // 실패 state = 4
                    Long alvseq = aivideoALVRepository.findByAlvseq(video_name);
                    aivideoALVRepository.updateState(alvseq, 4);
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
    @RequestMapping(method = RequestMethod.GET, value = "/save_ald")
    public String SaveALD (HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String imgname = request.getParameter("imgname"); // 받아온 imgname
        int imgcount = Integer.parseInt(request.getParameter("imgcount")); // 받아온 imgcount
        String maincount = request.getParameter("maincount"); // 받아온 maincount
        int width = Integer.parseInt(request.getParameter("width")); // 받아온 width
        int height = Integer.parseInt(request.getParameter("height")); // 받아온 height

        log.info("getDatas ==> " + imgname + "/" + imgcount + "/" + maincount + "/" + width + "/" + height);

        // DB저장(ald)
        // 날짜-시간
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        // video seq 가져오기
        String videoname = imgname+'%';
        Long alvseq = aivideoALVRepository.findByAlvseq(videoname);

        AivideoALDDto aivideoALDDto = new AivideoALDDto(null, alvseq, imgname, imgcount, maincount, width, height, null, null, str);
        aivideoService.aldSave(aivideoALDDto);

        return "null";
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
        log.info("labeling Data ==> " + data);

        // DB 변환/추가(alv/ald)
        String videoname = img_name+"%";
        Long alvseq = aivideoALVRepository.findByAlvseq(videoname);
        aivideoALVRepository.updateState(alvseq, 1);
        aivideoALDRepository.updateALDData(alvseq, label_name, main_label);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            if(conn != null){
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if(resCode==HttpURLConnection.HTTP_OK){
                    log.info("flask OK");
                    log.info("resCode!!!!! ==> " + resCode);
                    aivideoALVRepository.updateState(alvseq, 2);
                }else {
                    log.info("flask No");
                    conn.disconnect();
                    // DB(alv/ald) 수정/삭제
                    aivideoALVRepository.updateState(alvseq, 4);
                    aivideoALDRepository.deleteALDData(alvseq);
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
