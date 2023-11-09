package dev.mvc.requirement;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.customer.CustomerProcInter;
import dev.mvc.customer.CustomerVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;
import dev.mvc.rescontents.JoinVO;
import dev.mvc.rescontents.RescontentsProcInter;
import dev.mvc.rescontents.RescontentsVO;
import dev.mvc.take.TakeProcInter;
import dev.mvc.take.TakeVO;
import dev.mvc.tool.Tool;


@Controller
public class RequirementCont {
  @Autowired
  @Qualifier("dev.mvc.requirement.RequirementProc") // @Component("dev.mvc.requirement.RequirementProc")
  private RequirementProcInter requirementProc;
  
  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc")
  private CustomerProcInter customerProc = null;

  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // @Component("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
  
  @Autowired
  @Qualifier("dev.mvc.take.TakeProc") // @Component("dev.mvc.take.TakeProc")
  private TakeProcInter takeProc;
  
  @Autowired
  @Qualifier("dev.mvc.rescontents.RescontentsProc") // @Component("dev.mvc.rescontents.RescontentsProc")
  private RescontentsProcInter rescontentsProc;

  // http://localhost:9092/requirement/check.do?customerno=3
  /**
  * 영양 정보 유/무 확인
  * @return {"cnt":0}, {"cnt":1}
  */
  @ResponseBody
  @RequestMapping(value="/requirement/check.do", method=RequestMethod.GET,
                                produces = "text/plain;charset=UTF-8")
  public String check(int customerno) {
    System.out.println("->영양 정보 유/무 확인(check.do):"+ customerno);
    try {
      Thread.sleep(1000); // 1 초 지연
    }catch(Exception e) {
      
    }
    int cnt = this.requirementProc.check(customerno);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt); // 키와 값 = HashMap
    
    
    return json.toString(); // {"cnt":1} 
  }
  
   //http://localhost:9092/requirement/create.do
   /**
   * 등록 폼
   * @return
   */
   @RequestMapping(value="/requirement/create.do", method=RequestMethod.GET )
   public ModelAndView create(HttpSession session, HttpServletRequest request) {
     ModelAndView mav = new ModelAndView();
     
     int customerno = 0;
     if (this.customerProc.isCustomer(session) || this.managerProc.isManager(session)) { 
       // 로그인한 경우
  
       if (this.customerProc.isCustomer(session)) { // 회원으로 로그인
         // session을 사용하여 현재 로그인한 사용자의 customerno 값만 읽음으로 다른 사용자의
         // 정보를 조회할 수 없음
         customerno = (int)session.getAttribute("customerno");
         
       } else if (this.managerProc.isManager(session)) { // 관리자로 로그인
         // 관리자는 모든 회원의 정보를 조회할 수 있어야 함으로 parameter로 회원번호를 이용하여 조회
         customerno = Integer.parseInt(request.getParameter("customerno"));
         
       }
  
       CustomerVO customerVO = this.customerProc.read(customerno);
       int cnt_n = this.requirementProc.check(customerno);
       System.out.println("cnt_n->"+cnt_n);
       mav.addObject("cnt_n",cnt_n);
       mav.addObject("customerVO", customerVO);
       mav.setViewName("/requirement/create"); // /WEB-INF/views/requirement/create.jsp
       //mav.setViewName("/customer/read"); // /customer/read.jsp
       
     } else {
       // 로그인을 하지 않은 경우
       mav.setViewName("/customer/login_need"); // /webapp/WEB-INF/views/customer/login_need.jsp
     }
     
     return mav;
   }
   
   /**
    * 등록 처리
    * @param customerVO
    * @return
    */
   @RequestMapping(value="/requirement/create.do", method=RequestMethod.POST)
   public ModelAndView create(RequirementVO requirementVO){
     ModelAndView mav = new ModelAndView(); // 자동으로 requirementVO객체가 생성되고 form의 값이 할당 됨.
     
       
     int cnt= requirementProc.create(requirementVO); // SQL insert
  
     if (cnt == 1) { // insert 레코드 개수, 필요 영양정보 등록 성공
       mav.addObject("code", "create_success");
     } else { // 필요 영양정보 등록 실패
       mav.addObject("code", "create_fail");
       // mav.addObject("cnt", 0);  // 추가된 레코드 없음.  
     }
     
     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
  
     
     mav.addObject("url", "/requirement/msg");
     mav.setViewName("redirect:/requirement/msg.do"); // POST -> GET -> /customer/msg.jsp
     
     return mav;
   }
   
   /**
    * localhost:9092/requirement/read.do?customerno=3
    * 회원 조회
    * 관리자, 회원 본인만 가능
    * @param customerno
    * @return
    */
   @RequestMapping(value="/requirement/read.do", method=RequestMethod.GET)
   public ModelAndView read(HttpSession session, HttpServletRequest request){
     ModelAndView mav = new ModelAndView();
     
     int customerno = 0;
     if (this.customerProc.isCustomer(session) || this.managerProc.isManager(session)) { 
       // 로그인한 경우
  
       if (this.customerProc.isCustomer(session)) { // 회원으로 로그인
         // session을 사용하여 현재 로그인한 사용자의 customerno 값만 읽음으로 다른 사용자의
         // 정보를 조회할 수 없음
         customerno = (int)session.getAttribute("customerno");
         
         
         ArrayList<JoinVO> list = this.rescontentsProc.food_list_all();
         // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
         for (JoinVO joinVO : list) {
           String title = joinVO.getTitle();
           String content = joinVO.getRescontent();
           
           title = Tool.convertChar(title);  // 특수 문자 처리
           content = Tool.convertChar(content); 
           
           joinVO.setTitle(title);
           joinVO.setRescontent(content);  

         }
         mav.addObject("list", list);
         
       } else if (this.managerProc.isManager(session)) { // 관리자로 로그인
         // 관리자는 모든 회원의 정보를 조회할 수 있어야 함으로 parameter로 회원번호를 이용하여 조회
         customerno = Integer.parseInt(request.getParameter("customerno"));
         
       }
  
       if(this.takeProc.check(customerno) == 0) { // 마이페이지로 들어갈 때 오늘의 섭취 영양소가 있는지 확인 => 오늘의 영양소가 없는 경우 생성한다.
         System.out.println("customerno=>"+customerno);
         int cnt = this.takeProc.create(customerno); // 생성 (생성 성공 1리턴, 실패 0리턴)
       }
       
       TakeVO takeVO = this.takeProc.read(customerno);  // 영양소가 있으면 읽어온다.
       mav.addObject("takeVO", takeVO);                 // mav를 통해 값을 전달한다.
       
       RequirementVO requirementVO = this.requirementProc.read(customerno);
       mav.addObject("requirementVO", requirementVO);
       mav.setViewName("/requirement/read"); // /requirement/read.jsp
       
     } else {
       // 로그인을 하지 않은 경우
       mav.setViewName("/customer/login_need"); // /webapp/WEB-INF/views/customer/login_need.jsp
     }
     
     return mav; // forward
   }
   
   /**
    * 회원 정보 수정 처리
    * @param RequirementVO
    * @return
    */
   @RequestMapping(value="/requirement/update.do", method=RequestMethod.POST)
   public ModelAndView update(RequirementVO requirementVO){
     ModelAndView mav = new ModelAndView();
     
     // System.out.println("id: " + requirementVO.getId());
     
     int cnt= this.requirementProc.update(requirementVO);
     
     if (cnt == 1) {
       mav.addObject("code", "update_success");
       mav.addObject("customerno", requirementVO.getCustomerno());  // 홍길동님(user4) 회원 정보를 변경했습니다.
       mav.addObject("calories", requirementVO.getCalories());
       mav.addObject("carbohydrates", requirementVO.getCarbohydrates());
       mav.addObject("protein", requirementVO.getProtein());
       mav.addObject("fat", requirementVO.getFat());
     } else {
       mav.addObject("code", "update_fail");
     }
  
     mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
     mav.addObject("url", "/requirement/msg");  // /customer/msg -> /customer/msg.jsp
     
     mav.setViewName("redirect:/requirement/msg.do");
     
     return mav;
   }
   
   /**
    * 새로고침 방지, EL에서 param으로 접근, POST -> GET -> /customer/msg.jsp
    * @return
    */
   @RequestMapping(value="/requirement/msg.do", method=RequestMethod.GET)
   public ModelAndView msg(String url){
     ModelAndView mav = new ModelAndView();
  
     mav.setViewName(url); // forward
     
     return mav; // forward
   }
}
