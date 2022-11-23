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


    @GetMapping("/servicezone")
    public String main(Model model, HttpServletRequest request, Pageable pageable,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();
            Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

            Page<ServiceZoneEntity> memberEntities = serviceZoneService.selectALLTable0(s1.get().getAseq(), pageable);

            pageable = PageRequest.of(page, 15,Sort.by("pk").descending());
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

//            return "gradetypedatalist0 :: #example3";

            return  "Servicezone.html";
        }else{
            returnValue = "login.html";
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

        Pageable pageable = PageRequest.of(page, 15,Sort.by("pk").descending());
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

        return "Servicezone :: #intable";
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

        String state = "";
        if(a == 0){
            state = "등록됨";
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
        System.out.println("타입값: " +  type);
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

        // 사용자 일련번호 가져오기
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        // 서비스존 저장
        if(type == 0){ // 사각형
            String ren[] = serviceszone.split("&");
            String sw[] = ren[0].split(",");
            String ne[] = ren[1].split(",");

            Float y =  (parseFloat(sw[0]) + parseFloat(ne[0])) /2;
            Float x =  (parseFloat(sw[1]) + parseFloat(ne[1])) /2;

            System.out.println(parseFloat(sw[0]));
            System.out.println(parseFloat(sw[1]));

            String abc = y+","+x;

            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(null, s1.get().getAseq(),zonename,abc,state,address,address1,zoneex,type,str,"0",
                    filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]);
            serviceZoneService.save(serviceZoneDto);

        }else if(type == 1){ //원
            String redate[] = serviceszone.split("&");
            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(null, s1.get().getAseq(),zonename,redate[0],state,address,address1,zoneex,type,str,"0",
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

            ServiceZoneDto serviceZoneDto = new ServiceZoneDto(null, s1.get().getAseq(),zonename,abc,state,address,address1,zoneex,type,str,"0",
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
        
        session.setAttribute("dir","/home/apache/htdocs/WatchigoAdmin/"+session.getAttribute("userid"));
        return "redirect:";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/view")
    public Object view(Model model, HttpServletRequest request){

        HashMap<String, String> msg = new HashMap<String, String>();

        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        List<ServiceZoneEntity> sss = serviceZoneServeyRepository.findByseq(s1.get().getAseq());

        for(int i=0; i<sss.size(); i++){
            msg.put("renspoint"+i,sss.get(i).getZonecenter());
        }

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

        ArrayList<String> filename = new ArrayList<String>();

        filename.add(s1.get().getVedio1());
        filename.add(s1.get().getVedio2());
        filename.add(s1.get().getImg1());
        filename.add(s1.get().getImg2());
        filename.add(s1.get().getImg3());
        filename.add(s1.get().getImg4());
        filename.add(s1.get().getImg5());
        filename.add(s1.get().getImg6());

        filename.removeAll(Arrays.asList("", null));


        // 파일 저장
        for(MultipartFile multipartFile : file){
            int a = 0;
            File savefile = new File(savePath, filename.get(a));
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

        msg.put("center",s1.get().getZonecenter());

        msg.put("zonename",s1.get().getZonename());
        msg.put("ex",s1.get().getEx());
        msg.put("pk", String.valueOf(pk));

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

    @PostMapping("/deletezone")
    public String delete(@RequestParam(required = false, defaultValue = "", value = "pk")Long pk){
        System.out.println("인덱스값");
        System.out.println(pk);
        Optional<ServiceZoneEntity> s1 = serviceZoneRepository.findById(pk);
        if(s1.get().getType() == 0){
            serviceZoneRepository.deleteById(pk);
            rentangleRepository.deleteById(pk);
        }else if(s1.get().getType() == 1){
            serviceZoneRepository.deleteById(pk);
            circleRepository.deleteById(pk);
        }else if(s1.get().getType() == 2){
            serviceZoneRepository.deleteById(pk);
            polygonRepository.deleteByApk(pk);
        }
        return "test1 :: Success";
    }





}
