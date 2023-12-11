package dev.mvc.sell;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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
import dev.mvc.supplier.SupplierProcInter;
import dev.mvc.supplier.SupplierVO;

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
   * FoodList 반환
   * @return
   */
//   @ResponseBody
   @RequestMapping(value="/sell/menu_by_res.do", method=RequestMethod.GET )
   public ArrayList<FoodVO> getFoodList(int resno) {
//     ModelAndView mav = new ModelAndView();
     
     ArrayList<FoodVO> food_list = this.foodProc.list_all_res(resno);
     for(int i=0; i< food_list.size(); i++) {
       System.out.println(food_list.get(i).getFoodname());
     }
//     mav.addObject("food_list", food_list);
     
     return food_list;
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
   public ModelAndView list_all(HttpSession session) {
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
       
       ArrayList<Sell_JoinVO> list = this.sellProc.list_all_names(managerno);
       mav.addObject("list", list);
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
