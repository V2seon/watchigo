package com.example.watchigo.controller;

import com.example.watchigo.common.Pagination;
import com.example.watchigo.common.SessionCheck;
import com.example.watchigo.dto.ExhibitDto;
import com.example.watchigo.entity.*;
import com.example.watchigo.repository.*;
import com.example.watchigo.service.ExhibitService;
import com.example.watchigo.service.ServiceZoneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@AllArgsConstructor
@RequestMapping
public class Exhibitcontroller {

    private UserRepository userRepository;
    private ServiceZoneService serviceZoneService;
    private ServiceZoneRepository serviceZoneRepository;
    private RentangleRepository rentangleRepository;
    private CircleRepository circleRepository;
    private PolygonRepository polygonRepository;
    private ExhibitRepository exhibitRepository;
    private ExhibitService exhibitService;


    @GetMapping("/exhibit")
    public String main(Model model, HttpServletRequest request, Pageable pageable,
                       @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
            HttpSession session = request.getSession();
            Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

            Page<ExhibitEntity> memberEntities = exhibitService.selectALLTable0(s1.get().getAseq(), pageable);
            Page<ServiceZoneEntity> memberEntities1 = serviceZoneService.selectALLTable0(s1.get().getAseq(), pageable);

            pageable = PageRequest.of(page, 5, Sort.by("pk").descending());
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

            pageable = PageRequest.of(page, 5, Sort.by("pk").descending());
            Pagination pagination1 = new Pagination(memberEntities1.getTotalPages(), page);

            model.addAttribute("thisPage1", pagination1.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
            model.addAttribute("isNextSection1", pagination1.isNextSection()); //다음버튼 유무 확인하기 위함
            model.addAttribute("isPrevSection1", pagination1.isPrevSection()); //이전버튼 유무 확인하기 위함
            model.addAttribute("firstBtnIndex1", pagination1.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
            model.addAttribute("lastBtnIndex1", pagination1.getLastBtnIndex()); //섹션 변경 위함
            model.addAttribute("totalPage1", pagination1.getTotalPages()); //끝 버튼 위함
            //서비스 엔티티 추가후 주석 풀고 사용
//            Page<GradeType1DataEntity> pageList = Gradetype1DataService.selectALLTable2(selectKey, titleText, pageable);

            model.addAttribute("userlist1", memberEntities1); //페이지 객체 리스트
//            return "gradetypedatalist0 :: #example3";

            return  "Exhibit.html";
        }else{
            returnValue = "login.html";
        }
        return returnValue;
    }

    @RequestMapping(value = "/exhibit_search", method = RequestMethod.POST)
    public String servicezone_search(Model model, HttpServletRequest request,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        Pageable pageable = PageRequest.of(page, 5,Sort.by("pk").descending());
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

        model.addAttribute("userlist1", pageList); //페이지 객체 리스트

        return "Exhibit :: #see";
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/searchzoneview1")
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

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/saveexhibit")
    public Object saveexhibit(Model model, HttpServletRequest request,
                              @RequestParam(required = false, defaultValue = "", value = "zonepk") Long zonepk,
                              @RequestParam(required = false, defaultValue = "", value = "printtype") int printtype,
                              @RequestParam(required = false, defaultValue = "", value = "exhibitname") String exhibitname,
                              @RequestParam(required = false, defaultValue = "", value = "exhibitex") String exhibitex,
                              @RequestParam(required = false, defaultValue = "", value = "extype") String extype,
                              @RequestParam(required = false, defaultValue = "", value = "inv1") String inv1,
                              @RequestParam(required = false, defaultValue = "", value = "inv2") String inv2,
                              @RequestParam(required = false, defaultValue = "", value = "inv3") String inv3,
                              @RequestParam(required = false, defaultValue = "", value = "ini1") String ini1,
                              @RequestParam(required = false, defaultValue = "", value = "ini2") String ini2,
                              @RequestParam(required = false, defaultValue = "", value = "ini3") String ini3,
                              @RequestParam(required = false, defaultValue = "", value = "ini4") String ini4,
                              @RequestParam(required = false, defaultValue = "", value = "ini5") String ini5,
                              @RequestParam(required = false, defaultValue = "", value = "ini6") String ini6,
                              @RequestParam(required = false, defaultValue = "", value = "expoint") String expoint){

        HttpSession session = request.getSession();

        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));
        Optional<ServiceZoneEntity> s2 = serviceZoneRepository.findById(zonepk);

        System.out.println(s2.get().getZonename());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);

        String [] filedata = {inv1, inv2, ini1, ini2, ini3, ini4, ini5, ini6};

        // 파일이름 재선언
        for(int i = 0; i<filedata.length; i++){
            String id = UUID.randomUUID().toString().replace("-","");
            if(filedata[i].length()>1){
                String [] token = filedata[i].split("\\.");
                filedata[i] = id +"."+ token[1];
            }
        }


        ExhibitDto exhibitDto = new ExhibitDto(null,zonepk,s1.get().getAseq(),extype,s2.get().getZonename(),exhibitname,
                exhibitex,expoint,filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]
                ,"0","0",printtype,str);
        exhibitService.save(exhibitDto);

        return "redirect:";
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/exhibitview")
    public Object view(Model model, HttpServletRequest request){

        HashMap<String, String> msg = new HashMap<String, String>();

        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        List<ExhibitEntity> sss = exhibitRepository.findByuserseq(s1.get().getAseq());

        for(int i=0; i<sss.size(); i++){
            msg.put("renspoint"+i,sss.get(i).getPoint());
        }

        return msg;
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/searchexhibit")
    public Object searchexhibit(Model model, HttpServletRequest request, Pageable pageable,
                             @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){
        Optional<ExhibitEntity> s1 = exhibitRepository.findById(seq);

        model.addAttribute("s1",s1);

        HashMap<String, String> msg = new HashMap<String, String>();

        msg.put("center",s1.get().getPoint());

        return msg;
    }



}