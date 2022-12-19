package com.example.watchigo.controller;

import com.example.watchigo.common.Pagination;
import com.example.watchigo.entity.CustomerServiceEntity;
import com.example.watchigo.entity.ExhibitEntity;
import com.example.watchigo.entity.ServiceZoneEntity;
import com.example.watchigo.entity.UserEntity;
import com.example.watchigo.repository.CustomerServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping
public class AdminSiteController {

    private CustomerServiceRepository customerServiceRepository;

    @GetMapping("/")
    public String main(Model m, HttpServletRequest request){
        return "AdminSite/Homepage.html";
    }

    @GetMapping("/Intro")
    public String Intro(Model m, HttpServletRequest request){
        return "AdminSite/Intro.html";
    }

    @GetMapping("/AR_Factory")
    public String AR_Factory(Model m, HttpServletRequest request){
        return "AdminSite/AR_Factory.html";
    }

    @GetMapping("/WATCHIGO")
    public String WATCHIGO(Model m, HttpServletRequest request){
        return "AdminSite/WATCHIGO_.html";
    }

    @GetMapping("/Process")
    public String Process(Model m, HttpServletRequest request){
        return "AdminSite/Process.html";
    }

    @GetMapping("/Ask")
    public String Ask(Model m, HttpServletRequest request){
        return "AdminSite/Ask.html";
    }

    @GetMapping("/IP")
    public String IP(Model m, HttpServletRequest request){
        return "AdminSite/IP.html";
    }

    @GetMapping("/Plan")
    public String Plan(Model m, HttpServletRequest request){
        return "AdminSite/Plan.html";
    }

    @GetMapping("/Partners")
    public String Partners(Model m, HttpServletRequest request){
        return "AdminSite/Partners.html";
    }

    @GetMapping("/Payment")
    public String Payment(Model m, HttpServletRequest request){
        return "AdminSite/Payment.html";
    }

    @GetMapping("/Register")
    public String Register(Model m, HttpServletRequest request){
        return "AdminSite/Register.html";
    }

    @GetMapping("/Post")
    public String Post(Model m, HttpServletRequest request){
        return "/AdminSite/Post.html";
    }

    @GetMapping("/upload")
    public String upload(Model m, HttpServletRequest request){
        return "/AdminSite/upload.html";
    }

    @GetMapping("/userup")
    public String userup(Model m, HttpServletRequest request){
        return "/AdminSite/userup.html";
    }




    @GetMapping("/Customer_Service")
    public String Customer_Service(Model model, HttpServletRequest request, Pageable pageable,
                                   @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        pageable = PageRequest.of(page, 5);

        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("공지",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);


        return "AdminSite/Customer_Service.html";
    }

    @RequestMapping(value = "/Customer_Service_search", method = RequestMethod.POST)
    public String servicezone_search(Model model, HttpServletRequest request, Pageable pageable,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){

        pageable = PageRequest.of(page, 5);
        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("공지",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);

        return "AdminSite/Customer_Service.html :: #cubox";
    }

    @GetMapping("/Press_Release")
    public String Press_Release(Model model, HttpServletRequest request, Pageable pageable,
                                @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        pageable = PageRequest.of(page, 5);

        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("언론보도",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);

        return "AdminSite/Press_Release.html";
    }

    @RequestMapping(value = "/Press_Release_search", method = RequestMethod.POST)
    public String Press_Release_search(Model model, HttpServletRequest request, Pageable pageable,
                             @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                             @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                             @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){

        pageable = PageRequest.of(page, 5);
        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("언론보도",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);

        return "AdminSite/Press_Release.html :: #cubox";
    }

    @GetMapping("/QA")
    public String QA(Model model, HttpServletRequest request, Pageable pageable,
                     @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        pageable = PageRequest.of(page, 5);

        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("Q&A",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);

        return "AdminSite/Q&A.html";
    }

    @RequestMapping(value = "/QA_search", method = RequestMethod.POST)
    public String QA_search(Model model, HttpServletRequest request, Pageable pageable,
                                     @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                                     @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                                     @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){

        pageable = PageRequest.of(page, 5);
        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("Q&A",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);

        return "AdminSite/Q&A.html :: #cubox";
    }

    @GetMapping("/FAQ")
    public String FAQ(Model model, HttpServletRequest request, Pageable pageable,
                      @RequestParam(required = false, defaultValue = "0", value = "page") int page){

        pageable = PageRequest.of(page, 5);

        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("FAQ",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);

        return "AdminSite/FAQ.html";
    }

    @RequestMapping(value = "/FAQ_search", method = RequestMethod.POST)
    public String FAQ_search(Model model, HttpServletRequest request, Pageable pageable,
                            @RequestParam(required = false ,defaultValue = "0" , value="page") int page,
                            @RequestParam(required = false ,defaultValue = "" , value="selectKey") String selectKey,
                            @RequestParam(required = false ,defaultValue = "" , value="titleText") String titleText){

        pageable = PageRequest.of(page, 5);
        Page<CustomerServiceEntity> customerServiceEntities = customerServiceRepository.findAtype("FAQ",pageable);
        Pagination pagination = new Pagination(customerServiceEntities.getTotalPages(), page);
        model.addAttribute("thisPage", pagination.getPage()); //현재 몇 페이지에 있는지 확인하기 위함
        model.addAttribute("isNextSection", pagination.isNextSection()); //다음버튼 유무 확인하기 위함
        model.addAttribute("isPrevSection", pagination.isPrevSection()); //이전버튼 유무 확인하기 위함
        model.addAttribute("firstBtnIndex", pagination.getFirstBtnIndex()); //버튼 페이징 - 첫시작 인덱스
        model.addAttribute("lastBtnIndex", pagination.getLastBtnIndex()); //섹션 변경 위함
        model.addAttribute("totalPage", pagination.getTotalPages()); //끝 버튼 위함

        model.addAttribute("list", customerServiceEntities);

        return "AdminSite/FAQ.html :: #cubox";
    }


}
