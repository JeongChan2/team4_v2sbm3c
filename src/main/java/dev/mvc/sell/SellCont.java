package dev.mvc.sell;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.food.FoodProcInter;
import dev.mvc.food.FoodVO;
import dev.mvc.food.Food_JoinVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;

@Controller
public class SellCont {
  @Autowired
  @Qualifier("dev.mvc.sell.SellProc")
  private SellProcInter sellProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
  
  @Autowired
  @Qualifier("dev.mvc.res.ResProc")
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.food.FoodProc")
  private FoodProcInter foodProc;
  
  /**
   * 음식점 목록(식당)
   * http://localhost:9093/sell/select_menu_fetch.do?resno=3
   * @return
   */
  @RequestMapping(value="/sell/select_menu_fetch.do", method = RequestMethod.GET)
  @ResponseBody
  public String select_menu_fetch(int resno) {
//    System.out.println("-> foodno: " + foodno);
//    try {
//      Thread.sleep(3000); // 3초
//    } catch(Exception e) {
//      e.printStackTrace();
//    }
    
    ArrayList<FoodVO> food_list = this.foodProc.list_all_res(resno);
    
    JSONArray array = new JSONArray();
    JSONObject json = null;
    
    for (FoodVO foodVO : food_list) {
      json = new JSONObject();
      json.put("foodno", foodVO.getFoodno());
      json.put("foodname", foodVO.getFoodname());
      json.put("price", foodVO.getPrice());
      array.put(json);
    }
//    System.out.println(array.toString());
    return array.toString();
  }
  
  /**
   * 등록 폼
   * @return
   */
   @RequestMapping(value="/sell/create.do", method=RequestMethod.POST )
   public ModelAndView create(SellVO sellVO) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/sell/msg");
     
     int cnt = this.sellProc.create(sellVO);
     
     if(cnt == 1) {
       mav.addObject("code","create_success"); // 키, 값
       mav.addObject("sell_name",sellVO.getName()); // 이름 jsp로 전송
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
   @RequestMapping(value="/sell/list_all_managerno.do",method = RequestMethod.GET)
   public ModelAndView list_all(HttpSession session, Sell_JoinVO sell_JoinVO) {
     ModelAndView mav = new ModelAndView();
     
     int managerno=0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/sell/list_all_managerno");// /WEB-INF/views/sell/list_all_managerno.jsp
       
       managerno = (int)session.getAttribute("managerno");
       mav.addObject("managerno", managerno);
       
       ArrayList<ResVO> res_list = this.resProc.list_all_managerno(managerno);
       mav.addObject("res_list", res_list);
       
       ArrayList<Food_JoinVO> food_list = this.foodProc.list_all_resname(managerno);
       mav.addObject("food_list", food_list);
       
       sell_JoinVO.setManagerno(managerno);
       
       ArrayList<Sell_JoinVO> list = this.sellProc.list_by_managerno_search_paging(sell_JoinVO);
       mav.addObject("list", list);
       
       HashMap<String, Object> hashMap = new HashMap<String, Object>();
       hashMap.put("managerno", managerno);
       hashMap.put("word", sell_JoinVO.getWord());
       
       int search_count = this.sellProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
       mav.addObject("search_count",search_count);
       
       String paging = sellProc.pagingBox(sell_JoinVO.getManagerno(), sell_JoinVO.getNow_page(), sell_JoinVO.getWord(), "list_all_managerno.do");
       mav.addObject("paging", paging);
       
       int record_per_page = Sell.RECORD_PER_PAGE;   // 페이지가 바뀌어도 번호 증가 하기위함
       mav.addObject("record_per_page", record_per_page);
     }
     else {
       mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
     }
     return mav;
   }
   
   /**
    * 수정
    * http://localhost:9093/sell/update.do?sellno=2
    * @return
    */
   @RequestMapping(value="/sell/update.do",method = RequestMethod.GET)
   public ModelAndView update(HttpSession session, int sellno) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
     ModelAndView mav = new ModelAndView();
     
     int managerno = 0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/sell/list_all_update");// /WEB-INF/views/res/update.jsp
       
       SellVO sellVO = this.sellProc.read(sellno);
       mav.addObject("sellVO",sellVO);
       
       managerno = (int)session.getAttribute("managerno");
       mav.addObject("managerno", managerno);
       
       ArrayList<ResVO> res_list = this.resProc.list_all_managerno(managerno);
       mav.addObject("res_list", res_list);
       
//       ArrayList<FoodVO> food_list = this.foodProc.list_all_res(managerno);
//       mav.addObject("food_list", food_list);
       
       ArrayList<Sell_JoinVO> list = this.sellProc.list_all_names(managerno);
       mav.addObject("list", list);
     }
     else {
       mav.setViewName("/sell/login_need");
     }
     
     return mav;
   }
   
   // FORM 데이터 처리 http://localhost:9093/sell/update.do
   @RequestMapping(value="/sell/update.do",method = RequestMethod.POST)
   public ModelAndView update(SellVO sellVO) { // 자동으로 SellDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
         
     int cnt = this.sellProc.update(sellVO); // 수정 처리
     System.out.println("-> cnt:"+cnt);
     
     if(cnt == 1) {
       mav.setViewName("redirect:/sell/list_all_managerno.do"); 
     }
     else {
       mav.addObject("code","update_fail");     
       mav.setViewName("/sell/msg");  // /WEB-INF/views/res/msg.jsp
     }
     
     mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
     return mav;
   }
   
 //FORM 데이터 처리 http://localhost:9093/sell/delete.do
   @RequestMapping(value="/sell/delete.do",method = RequestMethod.GET)
   public ModelAndView delete(HttpSession session, int sellno) { // 자동으로 foodDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
     
     int managerno=0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/sell/list_all_delete");// /WEB-INF/views/sell/list_all_delete.jsp
       
       managerno = (int)session.getAttribute("managerno");
       
       SellVO sellVO = this.sellProc.read(sellno);
       mav.addObject("sellVO", sellVO);
       
       ArrayList<Sell_JoinVO> list = this.sellProc.list_all_names(managerno);
       mav.addObject("list", list);
       
     }
     else {
       mav.setViewName("/sell/login_need");
     }
     
     return mav;
   }
   
// FORM 데이터 처리 http://localhost:9093/sell/delete.do
  @RequestMapping(value="/sell/delete.do",method = RequestMethod.POST)
  public ModelAndView delete(int sellno) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.sellProc.delete(sellno); // 수정 처리
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/sell/list_all_managerno.do"); 
    }
    else {
      mav.addObject("code","delete_fail");     
      mav.setViewName("/sell/msg");  // /WEB-INF/views/sell/msg.jsp
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
}
