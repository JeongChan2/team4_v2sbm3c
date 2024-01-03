package dev.mvc.reservation;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.customer.CustomerProcInter;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;
import dev.mvc.sell.Sell;

@Controller
public class ReservationCont {
  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationProc")
  private ReservationProcInter reservationProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
  
  @Autowired
  @Qualifier("dev.mvc.res.ResProc")
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc")
  private CustomerProcInter customerProc;
  
  /**
   * 관리자 예약 등록 폼 ( 전화예약으로 관리자가 직접 예약 입력 )
   * @return
   */
   @RequestMapping(value="/reservation/create.do", method=RequestMethod.POST )
   public ModelAndView create(ReservationVO reservationVO) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/reservation/msg");
     
     String tmp = reservationVO.getRdate();
     tmp = tmp.replaceAll("T", " ");
     reservationVO.setRdate(tmp);
     
     reservationVO.setCustomerno(99999); // 비회원 + 전화예약
     int cnt = this.reservationProc.create(reservationVO);
     
     if(cnt == 1) {
       mav.addObject("code","create_success"); // 키, 값
       mav.addObject("reserv_name", reservationVO.getReserv_name()); // 이름 jsp로 전송
     }
     else {
       mav.addObject("code","create_fail");
     }
     mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
     return mav;
   }
   
   
   /**
    * 전체목록
    * @return
    */
   @RequestMapping(value="/reservation/list_all_managerno.do",method = RequestMethod.GET)
   public ModelAndView list_all(HttpSession session, ReservationVO reservationVO) {
     ModelAndView mav = new ModelAndView();
     
     int managerno = 0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/reservation/list_all_managerno");// /WEB-INF/views/reservation/list_all_managerno.jsp
       
       managerno = (int)session.getAttribute("managerno");
       mav.addObject("managerno", managerno);
       
       ArrayList<ResVO> res_list = this.resProc.list_all_managerno(managerno);
       mav.addObject("res_list", res_list);
       
       reservationVO.setManagerno(managerno);
       
       ArrayList<ReservationVO> list = this.reservationProc.list_by_managerno_search_paging(reservationVO);
       mav.addObject("list", list);
       
       HashMap<String, Object> hashMap = new HashMap<String, Object>();
       hashMap.put("managerno", managerno);
       hashMap.put("word", reservationVO.getWord());
       
       int search_count = this.reservationProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
       mav.addObject("search_count",search_count);
       
       String paging = reservationProc.pagingBox(reservationVO.getManagerno(), reservationVO.getNow_page(), reservationVO.getWord(), "list_all_managerno.do");
       mav.addObject("paging", paging);
       
       int record_per_page = Reservation.RECORD_PER_PAGE;   // 페이지가 바뀌어도 번호 증가 하기위함
       mav.addObject("record_per_page", record_per_page);
       
     }
     else {
       mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
     }
     return mav;
   }
   
   /**
    * 수정
    * http://localhost:9093/reservation/update.do?reserv_no=2
    * @return
    */
   @RequestMapping(value="/reservation/update.do",method = RequestMethod.GET)
   public ModelAndView update(HttpSession session,int reserv_no) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
     ModelAndView mav = new ModelAndView();
     
     int managerno = 0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/reservation/list_all_update");// /WEB-INF/views/reservation/update.jsp
       
       ReservationVO reservationVO = this.reservationProc.read(reserv_no);
       mav.addObject("reservationVO", reservationVO);
       
       managerno = (int)session.getAttribute("managerno");
       mav.addObject("managerno", managerno);
       
       ArrayList<ResVO> res_list = this.resProc.list_all_managerno(managerno);
       mav.addObject("res_list", res_list);
       
       ArrayList<ReservationVO> list = this.reservationProc.list_all_managerno(managerno);
       mav.addObject("list", list);
       
     }
     else {
       mav.setViewName("/manager/login_need");
     }
     
     return mav;
   }
   
   // FORM 데이터 처리 http://localhost:9093/reservation/update.do
   @RequestMapping(value="/reservation/update.do",method = RequestMethod.POST)
   public ModelAndView update(ReservationVO reservationVO) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
     
     String tmp = reservationVO.getRdate();
     tmp = tmp.replaceAll("T", " ");
     reservationVO.setRdate(tmp);
         
     int cnt = this.reservationProc.update(reservationVO); // 수정 처리
     System.out.println("-> cnt:"+ cnt);
     
     if(cnt == 1) {
       mav.setViewName("redirect:/reservation/list_all_managerno.do"); 
     }
     else {
       mav.addObject("code","update_fail");     
       mav.setViewName("/reservation/msg");  // /WEB-INF/views/res/msg.jsp
     }
     
     mav.addObject("cnt", cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
     return mav;
   }
   
 //FORM 데이터 처리 http://localhost:9093/reservation/delete.do
   @RequestMapping(value="/reservation/delete.do",method = RequestMethod.GET)
   public ModelAndView delete(HttpSession session, int reserv_no) { // 자동으로 foodDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
     
     int managerno=0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/reservation/list_all_delete");// /WEB-INF/views/res/update.jsp
       
       managerno = (int)session.getAttribute("managerno");
       
       ReservationVO reservationVO = this.reservationProc.read(reserv_no);
       mav.addObject("reservationVO", reservationVO);
       
       ArrayList<ReservationVO> list = this.reservationProc.list_all_managerno(managerno);
       mav.addObject("list", list);
       
     }
     else {
       mav.setViewName("/manager/login_need");
     }
     
     return mav;
   }
   
// FORM 데이터 처리 http://localhost:9093/reservation/delete.do
  @RequestMapping(value="/reservation/delete.do",method = RequestMethod.POST)
  public ModelAndView delete(int reserv_no) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.reservationProc.delete(reserv_no); // 수정 처리
    System.out.println("-> cnt:" + cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/reservation/list_all_managerno.do"); 
    }
    else {
      mav.addObject("code","delete_fail");     
      mav.setViewName("/reservation/msg");  // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
}
