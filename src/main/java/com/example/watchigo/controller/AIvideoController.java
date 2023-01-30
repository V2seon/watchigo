package com.example.watchigo.controller;

import com.example.watchigo.common.Pagination;
import com.example.watchigo.common.SessionCheck;
import com.example.watchigo.dto.AivideoALDDto;
import com.example.watchigo.dto.AivideoALVDto;
import com.example.watchigo.entity.AivideoALDEntity;
import com.example.watchigo.entity.AivideoALVEntity;
import com.example.watchigo.entity.UserEntity;
import com.example.watchigo.repository.AivideoALDRepository;
import com.example.watchigo.repository.AivideoALVRepository;
import com.example.watchigo.repository.UserRepository;
import com.example.watchigo.service.AivideoService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.net.ProtocolException;
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
    public String main(HttpServletRequest request, Model model, Pageable pageable,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        String returnValue = "";
        if (new SessionCheck().loginSessionCheck(request)) {
            HttpSession session = request.getSession();
            Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));
            pageable = PageRequest.of(page, 6);
            Page<AivideoALVEntity> aivideoALVEntities = aivideoService.selectALVList(s1.get().getAseq(), pageable);
            Pagination pagination = new Pagination(aivideoALVEntities.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함
            model.addAttribute("s1",s1);
            model.addAttribute("userlist", aivideoALVEntities); //페이지 객체 리스트

            model.addAttribute("nowurl0", "/aivideo");
            return "AIvideo.html";
        } else {
            returnValue = "/AdminSite/HomePage.html";
        }
        return returnValue;
    }

    @GetMapping("/write")
    public String ailistenter(Model model, HttpServletRequest request) {
        String returnValue = "";
        if (new SessionCheck().loginSessionCheck(request)) {
            model.addAttribute("nowurl0", "/aivideo");
            return "AIvideoInsert.html";
        } else {
            returnValue = "/AdminSite/HomePage.html";
        }
        return returnValue;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/video_download")
    public String AIvideoDownload(MultipartHttpServletRequest request) {
        MultipartFile video = request.getFile("aiinvideo");
        String video_name = video.getOriginalFilename();

        UUID uuid = UUID.randomUUID();
        String[] nameArr = video_name.split("[.]");
        String download_name = uuid.toString() + "." + nameArr[(nameArr.length - 1)];
        String path = "D:/LeeYJ/images/videos/" + download_name; // 저장경로 ********************
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
    public String AIvideoSplit(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long alv_seq;
        if(request.getParameter("alvseq").equals("")){ // 받아온 alvseq
            alv_seq = null;
        }else {
            alv_seq = Long.valueOf(request.getParameter("alvseq"));
        }
        String video_name, aiclass, ainame;
        video_name = request.getParameter("aiinv"); // 받아온 videoname
        aiclass = request.getParameter("aiclass"); // 받아온 aiclass
        ainame = request.getParameter("ainame"); // 받아온 ainame
        log.info(String.valueOf(alv_seq));

        String url = "http://192.168.10.44:3000/watchigo/" + video_name; // flask get방식 통신 url *****************
        String get_ai_data = ""; // 가져온 데이터 {"키1":"값1","키2":"값2"} 넣어놓을 공간

        if(alv_seq==null){
            // DB저장(alv)
            // 날짜-시간
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
            Date date = new Date();
            String str = sdf.format(date);
            Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid")); // 사용자 일련번호

            AivideoALVDto aivideoALVDto = new AivideoALVDto(null, s1.get().getAseq(), aiclass, ainame, video_name, 5, str);
            aivideoService.alvSave(aivideoALVDto);
            log.info("test1");
        }else if(alv_seq!=null){
            aivideoALVRepository.updateState(alv_seq,5);
            log.info("test2");
        }
        Long alvseq = aivideoALVRepository.findALVseq(video_name); // 저장 alvseq번호
        log.info("test3 // "+alvseq);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            if (conn != null) {
                conn.setConnectTimeout(1000 * 60 * 10); // 10분
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                log.info("test!!!! ==> " + resCode);
                if (resCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;

                    while (true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            get_ai_data += line;
                        }
                        println(line);
                    }
                    // 성공 state = 0
                    aivideoALVRepository.updateState(alvseq, 0);
                    log.info("get_ai_data => " + get_ai_data);
                    reader.close();
                } else {
                    conn.disconnect();
                    // 실패 state = 4
                    aivideoALVRepository.updateState(alvseq, 4);
                    String imgname = video_name.split(".")[0];
                    File D_imgs_url = new File("D:/LeeYJ/images/img/" + imgname); // *****************
                    File D_show_url = new File("D:/LeeYJ/images/img/showImgs/" + imgname + ".jpg"); // *****************
                    if(D_imgs_url.exists()){
                        FileUtils.deleteDirectory(D_imgs_url);
                    }
                    if(D_show_url.exists()){
                        FileUtils.deleteDirectory(D_show_url);
                    }
                    return "null";
                }
                conn.disconnect();
            }
        } catch (MalformedURLException e) {
            // 실패 state = 4
            aivideoALVRepository.updateState(alvseq, 4);
            String imgname = video_name.split(".")[0];
            File D_imgs_url = new File("D:/LeeYJ/images/img/" + imgname); // *****************
            File D_show_url = new File("D:/LeeYJ/images/img/showImgs/" + imgname + ".jpg"); // *****************
            try {
                if(D_imgs_url.exists()){
                    FileUtils.deleteDirectory(D_imgs_url);
                }
                if(D_show_url.exists()){
                    FileUtils.deleteDirectory(D_show_url);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e) {
            // 실패 state = 4
            aivideoALVRepository.updateState(alvseq, 4);
            String imgname = video_name.split(".")[0];
            File D_imgs_url = new File("D:/LeeYJ/images/img/" + imgname); // *****************
            File D_show_url = new File("D:/LeeYJ/images/img/showImgs/" + imgname + ".jpg"); // *****************
            try {
                if(D_imgs_url.exists()){
                    FileUtils.deleteDirectory(D_imgs_url);
                }
                if(D_show_url.exists()){
                    FileUtils.deleteDirectory(D_show_url);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 데이터 분할 및 저장
        String[] datas = get_ai_data.split("/");
        String imgname="";
        int imgcount=0;
        String maincount="";
        int width=0;
        int height=0;
        for(int i=0;i<datas.length;i++){
            if(datas[i].split(":")[0].equals("img_name")){
                imgname = datas[i].split(":")[1]; // 받아온 imgname
            }else if(datas[i].split(":")[0].equals("img_count")){
                imgcount = Integer.parseInt(datas[i].split(":")[1]); // 받아온 imgcount
            }else if(datas[i].split(":")[0].equals("main_count")){
                maincount = datas[i].split(":")[1]; // 받아온 maincount
            }else if(datas[i].split(":")[0].equals("width")){
                width = Integer.parseInt(datas[i].split(":")[1]); // 받아온 width
            }else if(datas[i].split(":")[0].equals("height")){
                height = Integer.parseInt(datas[i].split(":")[1]); // 받아온 height
            }
        }
        log.info("getDatas ==> " + imgname + "/" + imgcount + "/" + maincount + "/" + width + "/" + height);

        // DB저장(ald)
        // 날짜-시간
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        // seq 가져오기
        Long getAlvseq = aivideoALVRepository.findALVseq(video_name);

        AivideoALDDto aivideoALDDto = new AivideoALDDto(null, getAlvseq, imgname, imgcount, maincount, width, height, null, null, str);
        aivideoService.aldSave(aivideoALDDto);

        return get_ai_data;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/update_alv")
    public String UpdateALV(HttpServletRequest request, Model model) {
        Long alvseq = Long.valueOf(request.getParameter("alvseq")); // 받아온 alvseq
        int state = Integer.parseInt(request.getParameter("state")); // 받아온 state
        String alvclass = request.getParameter("aiclass"); // 받아온 aiclass
        String alvname = request.getParameter("ainame"); // 받아온 ainame
        String boxXY = request.getParameter("boxXY"); // 받아온 boxXY
        String ailagelname = request.getParameter("ailagelname"); // 받아온 ailagelname
        String aivideo_name = request.getParameter("aivideo_name"); // 받아온 aivideo_name

        if(state==4){
            // DB수정(alv)
            aivideoALVRepository.updateDatas(alvseq,alvclass,alvname);
            if(aivideo_name!=""&&aivideo_name!=null){
                aivideoALVRepository.updatevideo(alvseq,aivideo_name);
            }
        }else if(state==0){
            // DB수정(alv)
            aivideoALVRepository.updateDatas(alvseq,alvclass,alvname);
            // DB수정(ald)
            aivideoALDRepository.updateDatas(alvseq,boxXY,ailagelname);
        }
        return "null";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/ai_labeling")
    public String AIlabeling(HttpServletRequest request,
                             @RequestParam(required = false, defaultValue = "", value = "img_name") String img_name,
                             @RequestParam(required = false, defaultValue = "", value = "label_name") String label_name,
                             @RequestParam(required = false, defaultValue = "", value = "img_count") String img_count,
                             @RequestParam(required = false, defaultValue = "", value = "main_count") String main_count,
                             @RequestParam(required = false, defaultValue = "", value = "main_label") String main_label,
                             @RequestParam(required = false, defaultValue = "", value = "width") String width,
                             @RequestParam(required = false, defaultValue = "", value = "height") String height) {
        String data = "?img_name=" + img_name + "&label_name=" + label_name + "&img_count=" + img_count
                + "&main_count=" + main_count + "&main_label=" + main_label + "&width=" + width + "&height=" + height;
        String url = "http://192.168.10.44:3000/ai_labeling" + data; // flask get방식 통신 url *****************
        log.info("labeling Data ==> " + data);

        // DB 변환/추가(alv/ald)
        String videoname = img_name + "%";
        Long alvseq = aivideoALVRepository.findALVseq(videoname);
        aivideoALVRepository.updateState(alvseq, 1);
        aivideoALDRepository.updateALDData(alvseq, label_name, main_label);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            if (conn != null) {
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    log.info("flask OK");
                    conn.disconnect();
                    log.info("resCode!!!!! ==> " + resCode);
                    aivideoALVRepository.updateState(alvseq, 2);
                } else {
                    log.info("flask No");
                    conn.disconnect();
                    // DB(alv/ald) 수정/삭제
                    aivideoALVRepository.updateState(alvseq, 4);
                    aivideoALDRepository.deleteALDData(alvseq);
                    // 이미지 폴더 및 내용 전부 삭제
                    File D_url = new File("D:/LeeYJ/images/videos/" + img_name); // *****************
                    File D_show_url = new File("D:/LeeYJ/images/img/showImgs/" + img_name + ".jpg"); // *****************
                    FileUtils.deleteDirectory(D_url);
                    FileUtils.deleteDirectory(D_show_url);
                    return "null";
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        // ai학습 연결
//        String ai_url = "http://000.000.000.000:0000/ai학습/" + img_name; // flask get방식 통신 url *****************
//        // 필요한 정보 : 저장명(==폴더명)
//        try {
//            HttpURLConnection conn = (HttpURLConnection) new URL(ai_url).openConnection();
//            if (conn != null) {
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                int resCode = conn.getResponseCode();
//                if (resCode == HttpURLConnection.HTTP_OK) {
//                    //ai학습 성공
//                    conn.disconnect();
//                    aivideoALVRepository.updateState(alvseq, 3);
//                } else {
//                    //ai학습 실패
//                    conn.disconnect();
//                    aivideoALVRepository.updateState(alvseq, 4);
//                    aivideoALDRepository.deleteALDData(alvseq);
//                    // 이미지 폴더 및 내용 전부 삭제
//                    File D_url = new File("D:/LeeYJ/images/videos/" + img_name); // *****************
//                    File D_show_url = new File("D:/LeeYJ/images/img/showImgs/" + img_name + ".jpg"); // *****************
//                    FileUtils.deleteDirectory(D_url);
//                    FileUtils.deleteDirectory(D_show_url);
//                    return "null";
//                }
//            }
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "redirect:";
    }

    // list페이지 기능작업
    // 전부 삭제
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/aideletes")
    public String aidelete(HttpServletRequest request, Model model) {
        Long alvseq = Long.valueOf(request.getParameter("alvseq")); // 받아온 alvseq
        int state = Integer.parseInt(request.getParameter("state")); // 받아온 state
        String videoname = aivideoALVRepository.findALVvideo(alvseq);
        log.info("alvseq => "+alvseq+" / state => "+state+" / videoname => "+videoname);

        // DB삭제(alv, ald)
        if(state == 4){
            aivideoALVRepository.deleteALVData(alvseq);
            File D_video_url = new File("D:/LeeYJ/images/videos/" + videoname); // *****************
            if(D_video_url.delete()){
                log.info("sucess");
            }else {
                log.info("fail");
            }
        }else if(state == 0||state==3){
            aivideoALVRepository.deleteALVData(alvseq);
            aivideoALDRepository.deleteALDData(alvseq);
            String imgname = videoname.split("\\.")[0];
            File D_video_url = new File("D:/LeeYJ/images/videos/" + videoname); // *****************
            File D_imgs_url = new File("D:/LeeYJ/images/img/" + imgname); // *****************
            File D_show_url = new File("D:/LeeYJ/images/img/showImgs/" + imgname + "1.jpg"); // *****************
            if(D_video_url.exists()){
                D_video_url.delete();
            }
            if(D_imgs_url.exists()){
                File[] D_imgs_url_list = D_imgs_url.listFiles();
                for (int i = 0; i<D_imgs_url_list.length;i++){
                    D_imgs_url_list[i].delete();
                }
                D_imgs_url.delete();
            }
            if(D_show_url.exists()){
                D_show_url.delete();
            }
        }
        return "::redirect";
    }

    // 상세페이지 이동
    @GetMapping("/detail_page")
    public String DetailPage(Model model, HttpServletRequest request) {
        Long alvseq = Long.valueOf(request.getParameter("alvseq")); // 받아온 alvseq
        int state = Integer.parseInt(request.getParameter("state")); // 받아온 state
        String changeCheck = request.getParameter("changeCheck"); // 받아온 state
        log.info(changeCheck);

        if(state==4){
            AivideoALVEntity alvdata = aivideoALVRepository.findALVdata(alvseq);
            model.addAttribute("alvseq",alvseq);
            model.addAttribute("alvstate",state);
            model.addAttribute("alvclass",alvdata.getAlvclass());
            model.addAttribute("alvname",alvdata.getAlvname());
            model.addAttribute("alvvideo",alvdata.getAlvvideo());
            model.addAttribute("imgname","");
            model.addAttribute("imgcount","");
            model.addAttribute("maincount","");
            model.addAttribute("width","");
            model.addAttribute("height","");
            model.addAttribute("mainbox","");
            model.addAttribute("labelname","");
            model.addAttribute("mainnum1","");
            model.addAttribute("mainnum2","");
            model.addAttribute("mainnum3","");
            model.addAttribute("mainnum4","");
            model.addAttribute("mainnum5","");
            model.addAttribute("mainbox1","");
            model.addAttribute("mainbox2","");
            model.addAttribute("mainbox3","");
            model.addAttribute("mainbox4","");
            model.addAttribute("mainbox5","");
            model.addAttribute("changeCheck",changeCheck);

        }else if(state==0||state==1||state==2||state==3){
            AivideoALVEntity alvdata = aivideoALVRepository.findALVdata(alvseq);
            AivideoALDEntity alddata = aivideoALDRepository.findALDdata(alvseq);
            model.addAttribute("alvseq",alvseq);
            model.addAttribute("alvstate",state);
            model.addAttribute("alvclass",alvdata.getAlvclass());
            model.addAttribute("alvname",alvdata.getAlvname());
            model.addAttribute("alvvideo","");

            String[] datas = alddata.getAldmaincnt().split(",");
            model.addAttribute("imgname",alddata.getAldimgname());
            model.addAttribute("imgcount",alddata.getAldimgcnt());
            model.addAttribute("maincount",alddata.getAldmaincnt());
            model.addAttribute("width",alddata.getAldwidth());
            model.addAttribute("height",alddata.getAldheight());
            model.addAttribute("mainbox",alddata.getAldmainbox());
            model.addAttribute("labelname",alddata.getAldlabelname());
            model.addAttribute("mainnum1",datas[0]);
            model.addAttribute("mainnum2",datas[1]);
            model.addAttribute("mainnum3",datas[2]);
            model.addAttribute("mainnum4",datas[3]);
            model.addAttribute("mainnum5",datas[4]);
            log.info(alddata.getAldmainbox());
            if(alddata.getAldmainbox()==null||alddata.getAldmainbox()==""||alddata.getAldmainbox()=="null"){
                model.addAttribute("mainbox1","");
                model.addAttribute("mainbox2","");
                model.addAttribute("mainbox3","");
                model.addAttribute("mainbox4","");
                model.addAttribute("mainbox5","");
            } else {
                String[] datas2 = alddata.getAldmainbox().split("/");
                log.info("test! ==> "+datas2[0]+"/"+datas2[1]+"/"+datas2[2]+"/"+datas2[3]+"/"+datas2[4]);
                model.addAttribute("mainbox1",datas2[0]);
                model.addAttribute("mainbox2",datas2[1]);
                model.addAttribute("mainbox3",datas2[2]);
                model.addAttribute("mainbox4",datas2[3]);
                model.addAttribute("mainbox5",datas2[4]);
            }
            model.addAttribute("changeCheck",changeCheck);
        }
        return "AIvideoDetail.html";
    }

    // 동영상만 삭제
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/Vdeletes")
    public String AiVdelete(HttpServletRequest request, Model model) {
        Long alvseq = Long.valueOf(request.getParameter("alvseq")); // 받아온 alvseq
        int state = Integer.parseInt(request.getParameter("state")); // 받아온 state
        String videoname = aivideoALVRepository.findALVvideo(alvseq);
        File D_video_url = new File("D:/LeeYJ/images/videos/" + videoname); // *****************
        if(D_video_url.exists()){
            D_video_url.delete();
        }
        return "Vdeletes";
    }

    // 동영상 데이터 가져오기
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getVname")
    public String AiVget(HttpServletRequest request, Model model) {
        Long alvseq = Long.valueOf(request.getParameter("alvseq")); // 받아온 alvseq
        String videoname = aivideoALVRepository.findALVvideo(alvseq);
        File D_video_url = new File("D:/LeeYJ/images/videos/" + videoname); // *****************
        if(D_video_url.exists()){
            return videoname;
        }
        return "null";
    }

    // alv state 확인
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/stateCheck")
    public String AiStateCheck(HttpServletRequest request, Model model) {
        Long alvseq = Long.valueOf(request.getParameter("alvseq")); // 받아온 alvseq
        String state = aivideoALVRepository.findALVstate(alvseq);
        return state;
    }
}

