package com.example.watchigo.controller;

import com.example.watchigo.common.Pagination;
import com.example.watchigo.common.SessionCheck;
import com.example.watchigo.dto.ExhibitDto;
import com.example.watchigo.entity.*;
import com.example.watchigo.repository.*;
import com.example.watchigo.service.ExhibitService;
import com.example.watchigo.service.ServiceZoneService;
import lombok.AllArgsConstructor;
import org.apache.poi.sl.draw.geom.GuideIf;
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


            pageable = PageRequest.of(page, 100, Sort.by("pk").descending());
            Page<ServiceZoneEntity> memberEntities1 = serviceZoneService.selectALLTable0(s1.get().getAseq(), pageable);
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
            model.addAttribute("nowurl0", "/exhibit");

//            return "gradetypedatalist0 :: #example3";

            return  "ExhibitMain.html";
        }else{
            returnValue = "AdminSite/Homepage.html";
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

        return "ExhibitInsert :: #see";
    }

    @RequestMapping(value = "/exhibit_search1", method = RequestMethod.POST)
    public String servicezone_search1(Model model, HttpServletRequest request,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){
        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        Pageable pageable = PageRequest.of(page, 100);
        int totalPages = exhibitService.selectALLTable(selectKey, titleText,s1.get().getAseq(), pageable).getTotalPages();
        Pagination pagination = new Pagination(totalPages, page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        //서비스 엔티티 추가후 주석 풀고 사용
        Page<ExhibitEntity> pageList = exhibitService.selectALLTable(selectKey, titleText,s1.get().getAseq(), pageable);

        model.addAttribute("userlist", pageList); //페이지 객체 리스트

        return "ExhibitMain :: #viewzonebox1";
    }

    @RequestMapping(value = "/exhibit_search2", method = RequestMethod.POST)
    public String servicezone_search2(Model model, HttpServletRequest request,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText,
                                     @RequestParam(required = false ,defaultValue = "" , value="pk") Long pk){
        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        Pageable pageable = PageRequest.of(page, 100,Sort.by("pk").descending());
        Page<ExhibitEntity> exhibitEntities = exhibitRepository.findAseqApk(s1.get().getAseq(),pk,pageable);
        Pagination pagination = new Pagination(exhibitEntities.getTotalPages(), page);

        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("userlist", exhibitEntities); //페이지 객체 리스트

        return "ExhibitMain :: #bb1";
    }

    @GetMapping("/newexhibit")
    public String newzone(Model model, HttpServletRequest request, Pageable pageable,
                          @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        String returnValue = "";
        if(new SessionCheck().loginSessionCheck(request)){
        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));
        pageable = PageRequest.of(page, 5, Sort.by("pk").descending());
        Page<ServiceZoneEntity> memberEntities1 = serviceZoneService.selectALLTable0(s1.get().getAseq(), pageable);
        Pagination pagination1 = new Pagination(memberEntities1.getTotalPages(), page);

        model.addAttribute("thisPage", pagination1.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination1.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination1.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination1.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination1.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination1.getTotalPages()); //끝 버튼 위함
        //서비스 엔티티 추가후 주석 풀고 사용
//            Page<GradeType1DataEntity> pageList = Gradetype1DataService.selectALLTable2(selectKey, titleText, pageable);

        model.addAttribute("userlist1", memberEntities1); //페이지 객체 리스트
        model.addAttribute("nowurl0", "/exhibit");

        List<ServiceZoneEntity> s2 = serviceZoneRepository.findAll1(s1.get().getAseq());
        model.addAttribute("zonelist", s2); //페이지 객체 리스트
            return "ExhibitInsert.html";
        }else{
            returnValue = "AdminSite/Homepage.html";
        }
        return returnValue;
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
                              @RequestParam(required = false, defaultValue = "", value = "expoint") String expoint,
                              @RequestParam(required = false, defaultValue = "", value = "typename") String typename,
                              @RequestParam(required = false, defaultValue = "", value = "mainicon") String mainicon,
                              @RequestParam(required = false, defaultValue = "", value = "armarker") String armarker){

        HttpSession session = request.getSession();

        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));
        Optional<ServiceZoneEntity> s2 = serviceZoneRepository.findById(zonepk);

        System.out.println(s2.get().getZonename());

        session.setAttribute("name",exhibitname);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);

        String [] filedata = {inv1, inv2, ini1, ini2, ini3, ini4, ini5, ini6};

        String text1[] = mainicon.split("3020");
        String text2[] = armarker.split("3020");

        ExhibitDto exhibitDto = new ExhibitDto(null,zonepk,s1.get().getAseq(),extype,typename,s2.get().getZonename(),exhibitname,
                exhibitex,expoint,filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]
                ,text1[1],text2[1],printtype,str);
        exhibitService.save(exhibitDto);

        session.setAttribute("dir1","/home/AdminWatchigo/uploadfiles/exhibit/"+session.getAttribute("userid"));

        return "redirect:";
    }


    @PostMapping("/upload1")
    public String uploadfile(@RequestPart("files") MultipartFile[] file, Model model, HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession();
        String DIR = (String) session.getAttribute("dir1");

        Optional<ExhibitEntity> s1 = exhibitRepository.findByname((String) session.getAttribute("name"));

        // 폴더생성
        // 폴더명 세션으로 받지말고 DB에 입력된 값 가져와서 만들어야됨
        File savePath = new File(DIR, s1.get().getDate());
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
        return "ExhibitInsert :: Success";
    }

    @GetMapping("/file1")
    public StreamingResponseBody img(HttpServletRequest request, @RequestParam("fileName")String fileName) throws Exception {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("userid");
        String date = (String) session.getAttribute("date");
        String DIR = "/home/AdminWatchigo/uploadfiles/exhibit/"+id+"/";
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
    @RequestMapping(method = RequestMethod.POST, value = "/exhibitview")
    public Object view(Model model, HttpServletRequest request){

        HashMap<String, List> msg = new HashMap<String, List>();

        HttpSession session = request.getSession();
        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));

        List<ExhibitEntity> sss = exhibitRepository.findByuserseq(s1.get().getAseq());

        ArrayList<String> plist = new ArrayList<>();
        ArrayList<String> mlist = new ArrayList<>();

        for(int i=0; i<sss.size(); i++){
            plist.add(sss.get(i).getPoint());
            mlist.add(sss.get(i).getMainicon());
        }

        msg.put("point", plist);
        msg.put("mlist", mlist);

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

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/searchexhibitview")
    public Object searchexhibitview(Model model, HttpServletRequest request,
                                 @RequestParam(required = false, defaultValue = "", value = "seq") Long seq){
        Optional<ExhibitEntity> s1 = exhibitRepository.findById(seq);
        HashMap<String, String> msg = new HashMap<String, String>();
        HttpSession session = request.getSession();

        session.setAttribute("date",s1.get().getDate());

        msg.put("video1",s1.get().getVideo1());
        msg.put("img1",s1.get().getImg1());
        msg.put("date",s1.get().getDate());
        msg.put("zonename",s1.get().getName());
        msg.put("ex",s1.get().getEx());
        msg.put("center",s1.get().getPoint());
        msg.put("type",s1.get().getType());
        msg.put("typename",s1.get().getTypename());
        msg.put("seq",String.valueOf(s1.get().getSeq()));
        msg.put("mainicon",s1.get().getMainicon());
        msg.put("armarker",s1.get().getArmarker());


        return msg;
    }

    @PostMapping("/deleteexhibit")
    public String delete(@RequestParam(required = false, defaultValue = "", value = "pk")Long pk){
        System.out.println("인덱스값");
        System.out.println(pk);

        exhibitRepository.deleteById(pk);

        return "ExhibitMain :: Success";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/editexhibit")
    public Object editexhibit(Model model, HttpServletRequest request,
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
                              @RequestParam(required = false, defaultValue = "", value = "expoint") String expoint,
                              @RequestParam(required = false, defaultValue = "", value = "typename") String typename,
                              @RequestParam(required = false, defaultValue = "", value = "seqnum") Long seqnum,
                              @RequestParam(required = false, defaultValue = "", value = "mainicon") String mainicon,
                              @RequestParam(required = false, defaultValue = "", value = "armarker") String armarker){

        HttpSession session = request.getSession();

        Optional<UserEntity> s1 = userRepository.findByAid((String) session.getAttribute("userid"));
        Optional<ServiceZoneEntity> s2 = serviceZoneRepository.findById(zonepk);

        System.out.println(seqnum);
        System.out.println(s2.get().getZonename());

        session.setAttribute("name",exhibitname);

        Optional<ExhibitEntity> ex11 = exhibitRepository.findById(seqnum);

        String str = ex11.get().getDate();

        String [] filedata = {inv1, inv2, ini1, ini2, ini3, ini4, ini5, ini6};
        String [] dbfiledata = {ex11.get().getVideo1(), ex11.get().getVideo2(), ex11.get().getImg1(), ex11.get().getImg2(),
                ex11.get().getImg3(), ex11.get().getImg4(), ex11.get().getImg5(), ex11.get().getImg6()};

        for (int i=0; i<filedata.length; i++){
            if(filedata[i].equals(null) || filedata[i].equals("")){
                filedata[i] = dbfiledata[i];
            }
        }

        String text1[] = mainicon.split("3020");
        String text2[] = armarker.split("3020");

        ExhibitDto exhibitDto = new ExhibitDto(seqnum,zonepk,s1.get().getAseq(),extype,typename,s2.get().getZonename(),exhibitname,
                exhibitex,expoint,filedata[0],filedata[1],filedata[2],filedata[3],filedata[4],filedata[5],filedata[6],filedata[7]
                ,text1[1] ,text2[1],printtype,str);
        exhibitService.save(exhibitDto);

        session.setAttribute("dir1","/home/AdminWatchigo/uploadfiles/exhibit/"+session.getAttribute("userid"));

        return "redirect:";
    }



}
