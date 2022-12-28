package com.example.watchigo.controller;


import com.example.watchigo.common.Pagination;
import com.example.watchigo.common.SessionCheck;
import com.example.watchigo.dto.CircleDto;
import com.example.watchigo.dto.PolygonDto;
import com.example.watchigo.dto.RectangleDto;
import com.example.watchigo.dto.ServiceZoneDto;
import com.example.watchigo.entity.*;
import com.example.watchigo.repository.*;
import com.example.watchigo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;

import java.util.*;

import static java.lang.Float.parseFloat;

@Controller
@AllArgsConstructor
@RequestMapping
public class ServiceZoneController {

    private FileService fileService;
    private ServiceZoneService serviceZoneService;
    private ServiceZoneRepository serviceZoneRepository;
    private ServiceZoneServeyRepository serviceZoneServeyRepository;
    private RentangleService rentangleService;
    private CircleService circleService;
    private PolygonService polygonServicey;
    private PolygonRepository polygonRepository;
    private RentangleRepository rentangleRepository;
    private CircleRepository circleRepository;
    private UserRepository userRepository;
    private ExhibitRepository exhibitRepository;


    @GetMapping("/servicezone")
    public String main(Model model, HttpServletRequest request, Pageable pageable,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();
            Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));
            pageable = PageRequest.of(page, 100);

            Page<ServiceZoneEntity> memberEntities = serviceZoneService.selectALLTable0(s1.get().getAseq(), pageable);

            Pagination pagination = new Pagination(memberEntities.getTotalPages(), page);

            model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함
            model.addAttribute("s1",s1);
            //서비스 엔티티 추가후 주석 풀고 사용
//            Page<GradeType1DataEntity> pageList = Gradetype1DataService.selectALLTable2(selectKey, titleText, pageable);

            model.addAttribute("userlist", memberEntities); //페이지 객체 리스트

            model.addAttribute("nowurl0", "/servicezone");

//            return "gradetypedatalist0 :: #example3";

            return  "ServicezoneMain.html";
        }else{
            returnValue = "AdminSite/Homepage.html";
        }
        return returnValue;
    }

    @RequestMapping(value = "/servicezone_search", method = RequestMethod.POST)
    public String servicezone_search(Model model, HttpServletRequest request,
                                    @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                    @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                    @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        Pageable pageable = PageRequest.of(page, 100);
        int totalPages = serviceZoneService.selectALLTable(selectKey, titleText,s1.get().getAseq(), pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함


        //서비스 엔티티 추가후 주석 풀고 사용
        Page<ServiceZoneEntity> pageList = serviceZoneService.selectALLTable(selectKey, titleText,s1.get().getAseq(), pageable);

        model.addAttribute("userlist", pageList); //페이지 객체 리스트

        return "ServicezoneMain :: #intable";
    }

    @GetMapping("/newzone")
    public String newzone(Model model,HttpServletRequest request){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0", "/servicezone");
            return "ServicezoneInsert.html";
        }else{
            returnValue = "AdminSite/HomePage.html";
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
                                   @RequestParam(required = false, defaultValue = "", value = "marker1")String marker1,
                                   @RequestParam(required = false, defaultValue = "", value = "a")int a){
        HttpSession session = request.getSession();

        String state = "";
        if(a == 0){
            state = "등록중";
        }else if(a == 1){
            state = "승인대기";
        }else if(a == 2){
            state = "출시";
        }

        int type = 0;
        if(zonetype.equals("RECTANGLE")){
            type = 0;
        }else if(zonetype.equals("CIRCLE")){
            type = 1;
        }else if(zonetype.equals("POLYGON")){
            type = 2;
        }

        String [] filedata = {inv1, inv2, ini1, ini2, ini3, ini4, ini5, ini6};

        // 폴더명 넘기기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        session.setAttribute("sdf",str);

        String text1[] = marker1.split("3020");

        // 사용자 일련번호 가져오기
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        // 서비스존 저장
        if(type == 0){ // 사각형
            String ren[] = serviceszone.split("&");
            String sw[] = ren[0].split(",");
            String ne[] = ren[1].split(",");

            Float y =  (parseFloat(sw[0]) + parseFloat(ne[0])) /2;
            Float x =  (parseFloat(sw[1]) + parseFloat(ne[1])) /2;

            String abc = y+","+x;

            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(null, s1.get().getAseq(),zonename,abc,state,address,address1,zoneex,type,str,text1[1],
                    filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
            serviceZoneService.save(serviceZoneDto);

        }else if(type == 1){ //원
            String redate[] = serviceszone.split("&");
            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(null, s1.get().getAseq(),zonename,redate[0],state,address,address1,zoneex,type,str,text1[1],
                    filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
            serviceZoneService.save(serviceZoneDto);
        }else if(type == 2){ //다각형
            String redate[] = serviceszone.split("&");
            float xnum = 500;
            float xnum1 = 0;
            float ynum = 500;
            float ynum1 = 0;

            for(int i=0; i<redate.length; i++){
                String si [] =  redate[i].split(",");
                if(xnum > Float.parseFloat(si[1])){
                    xnum = Float.parseFloat(si[1]);
                }
                if(xnum1 < Float.parseFloat(si[1])){
                    xnum1 = Float.parseFloat(si[1]);
                }
                if(ynum > Float.parseFloat(si[0])){
                    ynum = Float.parseFloat(si[0]);
                }
                if(ynum1 < Float.parseFloat(si[0])){
                    ynum1 = Float.parseFloat(si[0]);
                }
            }

            Float y = (ynum + ynum1) /2;
            Float x = (xnum + xnum1) /2;

            String abc = y+","+x;

            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(null, s1.get().getAseq(),zonename,abc,state,address,address1,zoneex,type,str,text1[1],
                    filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
            serviceZoneService.save(serviceZoneDto);

        }

        //좌표값 저장
        Optional<ServiceZoneEntity> sss = serviceZoneRepository.findByzonename(zonename);

        // 영역 이름 세션담기
        session.setAttribute("zonename",zonename);

        if(type == 0){ // 사각형
            String redate[] = serviceszone.split("&");
            RectangleDto rectangleDto = new RectangleDto(sss.get().getPk(),sss.get().getSeq(),str,redate[0],redate[1]);
            rentangleService.save(rectangleDto);
        }else if(type == 1){ //원
            String redate[] = serviceszone.split("&");
            CircleDto circleDto = new CircleDto(sss.get().getPk(),sss.get().getSeq(),str,redate[0],redate[1]);
            circleService.save(circleDto);
        }else if(type == 2){ //다각형
            String data[] = serviceszone.split("&");
            for(int i=0; i<data.length; i++){
                PolygonDto polygonDto = new PolygonDto(null,sss.get().getPk(),sss.get().getSeq(), str, (long) i+1, data[i]);
                polygonServicey.save(polygonDto);
            }
        }
        
        session.setAttribute("dir","/home/AdminWatchigo/uploadfiles/servicezone/"+session.getAttribute("userid"));
        return "redirect:";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/view")
    public Object view(Model model, HttpServletRequest request){

        HashMap<String, List> msg = new HashMap<String, List>();

        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        List<ServiceZoneEntity> sss = serviceZoneServeyRepository.findByseq(s1.get().getAseq());

        ArrayList<String> plist = new ArrayList<>();
        ArrayList<String> mlist = new ArrayList<>();
        for(int i=0; i<sss.size(); i++){
            plist.add(sss.get(i).getZonecenter());
            mlist.add(sss.get(i).getMarker());
        }

        msg.put("plist", plist);
        msg.put("mlist", mlist);

        return msg;
    }

    @PostMapping("/upload")
    public String uploadfile(@RequestPart("files") MultipartFile[] file, Model model,HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession();
        String DIR = (String) session.getAttribute("dir");

        Optional<ServiceZoneEntity> s1 = serviceZoneRepository.findByzonename((String) session.getAttribute("zonename"));
//        file.getOriginalFilename()
        // 폴더생성
        // 폴더명 세션으로 받지말고 DB에 입력된 값 가져와서 만들어야됨
        File savePath = new File(DIR, s1.get().getDate());
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
        return "ServicezoneInsert :: Success";
    }

    @GetMapping("/file")
    public StreamingResponseBody img(HttpServletRequest request,@RequestParam("fileName")String fileName) throws Exception {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("userid");
        String date = (String) session.getAttribute("date");
        String DIR = "/home/AdminWatchigo/uploadfiles/servicezone/"+id+"/";
        File file = new File(DIR+fileName);
        final InputStream is = new FileInputStream(file);
        return os -> {
            readAndWrite(is,os);
        };
    }

    private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
        try {
            byte[] data = new byte[2048];
            int read = 0;
            while ((read = is.read(data)) > 0) {
                os.write(data, 0, read);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/searchzone")
    public Object searchzone(Model model, HttpServletRequest request, Pageable pageable,
                             @RequestParam(required = false, defaultValue = "", value = "pk") Long pk){
        Optional<ServiceZoneEntity> s1 = serviceZoneRepository.findById(pk);

        model.addAttribute("s1",s1);

        HashMap<String, String> msg = new HashMap<String, String>();

        msg.put("center",s1.get().getZonecenter());

        return msg;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/searchzoneview")
    public Object searchzoneview(Model model, HttpServletRequest request,
                                 @RequestParam(required = false, defaultValue = "", value = "pk") Long pk){
        Optional<ServiceZoneEntity> s1 = serviceZoneRepository.findById(pk);
        HashMap<String, String> msg = new HashMap<String, String>();
        HttpSession session = request.getSession();

        session.setAttribute("date",s1.get().getDate());

        msg.put("center",s1.get().getZonecenter());
        msg.put("zonename",s1.get().getZonename());
        msg.put("ex",s1.get().getEx());
        msg.put("pk", String.valueOf(pk));
        msg.put("date", s1.get().getDate());
        msg.put("video1",s1.get().getVideo1());
        msg.put("img1",s1.get().getImg1());
        msg.put("marker",s1.get().getMarker());
        msg.put("zonetype",String.valueOf(s1.get().getType()));

        String aass = String.valueOf(s1.get().getType());

        if (s1.get().getType() == 0){
            msg.put("type",String.valueOf(s1.get().getType()));
            Optional<RentangleEntiry> r1 = rentangleRepository.findById(pk);
            msg.put("sp",r1.get().getAspoint());
            msg.put("ep",r1.get().getAepoint());
        }else if(s1.get().getType() == 1){
            msg.put("type",String.valueOf(s1.get().getType()));
            Optional<CircleEntity> c1 = circleRepository.findById(pk);
            msg.put("ce",c1.get().getAcenter());
            msg.put("ra",c1.get().getAradius());
        }else if(s1.get().getType() == 2){
            msg.put("type",String.valueOf(s1.get().getType()));
            List<PolygonEntity> p1 = polygonRepository.findByApk(pk);
            String data = "";
            for(int i =0; i<p1.size(); i++){
                data = data + p1.get(i).getApoint1()+"&" ;
            }
            msg.put("data",data);
        }
        return msg;
    }

    @GetMapping("/editgo")
    public String editgo(Model model,HttpServletRequest request,
                         @RequestParam(required = false, defaultValue = "", value = "pk") Long pk){
        HttpSession session = request.getSession();
        session.setAttribute("pk",pk);
        return "redirect:";
    }

    @GetMapping("/editgo1")
    public String editgo(Model model,HttpServletRequest request){
        String returnValue = "";
        HttpSession session = request.getSession();
        if(new SessionCheck().loginSessionCheck(request)){
            model.addAttribute("nowurl0", "/servicezone");
            Long pk = (Long) session.getAttribute("pk");
            Optional<ServiceZoneEntity> s1 = serviceZoneRepository.findById(pk);
            model.addAttribute("type",s1.get().getType());
            model.addAttribute("address",s1.get().getAddress());
            model.addAttribute("address1",s1.get().getAddress1());
            model.addAttribute("zonename",s1.get().getZonename());
            model.addAttribute("zoneex",s1.get().getEx());
            model.addAttribute("marker",s1.get().getMarker());
            model.addAttribute("date",s1.get().getDate());
            model.addAttribute("vid1",s1.get().getVideo1());
            model.addAttribute("vid2",s1.get().getVideo2());
            model.addAttribute("img1",s1.get().getImg1());
            model.addAttribute("img2",s1.get().getImg2());
            model.addAttribute("img3",s1.get().getImg3());
            model.addAttribute("img4",s1.get().getImg4());
            model.addAttribute("img5",s1.get().getImg5());
            model.addAttribute("img6",s1.get().getImg6());
            model.addAttribute("state",s1.get().getState());
            model.addAttribute("center",s1.get().getZonecenter());
            System.out.println("d역;");
            model.addAttribute("pk",pk);

            if (s1.get().getType() == 0){
                Optional<RentangleEntiry> r1 = rentangleRepository.findById(pk);
                model.addAttribute("data",r1.get().getAspoint()+"&"+r1.get().getAepoint());
            }else if(s1.get().getType() == 1){
                Optional<CircleEntity> c1 = circleRepository.findById(pk);
                model.addAttribute("data",c1.get().getAcenter() + "&" + c1.get().getAradius());
            }else if(s1.get().getType() == 2){
                List<PolygonEntity> p1 = polygonRepository.findByApk(pk);
                String data = "";
                for(int i =0; i<p1.size(); i++){
                    data = data + p1.get(i).getApoint1()+"&" ;
                }
                model.addAttribute("data",data);
            }

            model.addAttribute("s1",s1);
            returnValue = "ServicezoneEdit.html";
        }else{
            returnValue = "AdminSite/HomePage.html";
        }
        return returnValue;
    }

    @PostMapping("/deletezone")
    public String delete(@RequestParam(required = false, defaultValue = "", value = "pk")Long pk){
        Optional<ServiceZoneEntity> s1 = serviceZoneRepository.findById(pk);
        if(s1.get().getType() == 0){
            serviceZoneRepository.deleteById(pk);
            rentangleRepository.deleteById(pk);
            exhibitRepository.deleteBypk(pk);
        }else if(s1.get().getType() == 1){
            serviceZoneRepository.deleteById(pk);
            circleRepository.deleteById(pk);
            exhibitRepository.deleteBypk(pk);
        }else if(s1.get().getType() == 2){
            serviceZoneRepository.deleteById(pk);
            polygonRepository.deleteByApk(pk);
            exhibitRepository.deleteBypk(pk);
        }
        return "ServicezoneMain :: Success";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/editzone")
    public String EditServiceZone (HttpServletRequest request, Model model,
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
                                   @RequestParam(required = false, defaultValue = "", value = "marker1")String marker1,
                                   @RequestParam(required = false, defaultValue = "", value = "a")int a,
                                   @RequestParam(required = false, defaultValue = "", value = "pkzonenum")Long zonepknum){
        HttpSession session = request.getSession();

        String state = "";
        if(a == 0){
            state = "등록중";
        }else if(a == 1){
            state = "승인대기";
        }else if(a == 2){
            state = "출시";
        }

        System.out.println(zonetype);

        int type = 0;
        if(zonetype.equals("RECTANGLE")){
            type = 0;
        }else if(zonetype.equals("CIRCLE")){
            type = 1;
        }else if(zonetype.equals("POLYGON")){
            type = 2;
        }

        Optional<ServiceZoneEntity> ser = serviceZoneRepository.findById(zonepknum);

        String [] filedata = {inv1, inv2, ini1, ini2, ini3, ini4, ini5, ini6};
        for (int i=0; i<filedata.length; i++) {
            System.out.println(filedata[i]);
        }

        String text1[] = marker1.split("3020");

        // 폴더명 넘기기
        session.setAttribute("sdf",ser.get().getDate());

        // 사용자 일련번호 가져오기
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        // 서비스존 저장
        if(type == 0){ // 사각형
            String ren[] = serviceszone.split("&");
            String sw[] = ren[0].split(",");
            String ne[] = ren[1].split(",");

            Float y =  (parseFloat(sw[0]) + parseFloat(ne[0])) /2;
            Float x =  (parseFloat(sw[1]) + parseFloat(ne[1])) /2;

            String abc = y+","+x;

            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(zonepknum, s1.get().getAseq(),zonename,abc,state,address,address1,zoneex,type,ser.get().getDate(),text1[1],
                    filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
            serviceZoneService.save(serviceZoneDto);

        }else if(type == 1){ //원
            String redate[] = serviceszone.split("&");
            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(zonepknum, s1.get().getAseq(),zonename,redate[0],state,address,address1,zoneex,type,ser.get().getDate(),text1[1],
                    filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
            serviceZoneService.save(serviceZoneDto);
        }else if(type == 2){ //다각형
            String redate[] = serviceszone.split("&");
            float xnum = 500;
            float xnum1 = 0;
            float ynum = 500;
            float ynum1 = 0;

            for(int i=0; i<redate.length; i++){
                String si [] =  redate[i].split(",");
                if(xnum > Float.parseFloat(si[1])){
                    xnum = Float.parseFloat(si[1]);
                }
                if(xnum1 < Float.parseFloat(si[1])){
                    xnum1 = Float.parseFloat(si[1]);
                }
                if(ynum > Float.parseFloat(si[0])){
                    ynum = Float.parseFloat(si[0]);
                }
                if(ynum1 < Float.parseFloat(si[0])){
                    ynum1 = Float.parseFloat(si[0]);
                }
            }

            Float y = (ynum + ynum1) /2;
            Float x = (xnum + xnum1) /2;

            String abc = y+","+x;

            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(zonepknum, s1.get().getAseq(),zonename,abc,state,address,address1,zoneex,type,ser.get().getDate(),text1[1],
                    filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
            serviceZoneService.save(serviceZoneDto);

        }

        //좌표값 저장
        Optional<ServiceZoneEntity> sss = serviceZoneRepository.findByzonename(zonename);

        // 영역 이름 세션담기
        session.setAttribute("zonename",zonename);

        if(type == 0){ // 사각형
            String redate[] = serviceszone.split("&");
            RectangleDto rectangleDto = new RectangleDto(sss.get().getPk(),sss.get().getSeq(),ser.get().getDate(),redate[0],redate[1]);
            rentangleService.save(rectangleDto);
        }else if(type == 1){ //원
            String redate[] = serviceszone.split("&");
            CircleDto circleDto = new CircleDto(sss.get().getPk(),sss.get().getSeq(),ser.get().getDate(),redate[0],redate[1]);
            circleService.save(circleDto);
        }else if(type == 2){ //다각형
            String data[] = serviceszone.split("&");
            polygonRepository.deleteByApk(zonepknum);
            for(int i=0; i<data.length; i++){
                PolygonDto polygonDto = new PolygonDto(null,sss.get().getPk(),sss.get().getSeq(), ser.get().getDate(), (long) i+1, data[i]);
                polygonServicey.save(polygonDto);
            }
        }

        session.setAttribute("dir","/home/AdminWatchigo/uploadfiles/servicezone/"+session.getAttribute("userid"));
        return "redirect:";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/findexpoint")
    public Object findexpoint(Model model, HttpServletRequest request, Pageable pageable){

        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        List<ExhibitEntity> ex1 = exhibitRepository.findByuserseq(s1.get().getAseq());

        System.out.println(ex1);
        ArrayList<String> plist = new ArrayList<>();
        ArrayList<String> nlist = new ArrayList<>();

        for(int i=0; i<ex1.size(); i++){
            plist.add(ex1.get(i).getPoint());
            nlist.add(ex1.get(i).getName());
        }

        HashMap<String, List> msg = new HashMap<String, List>();

        msg.put("point", plist);
        msg.put("name", nlist);

        return msg;
    }


}
