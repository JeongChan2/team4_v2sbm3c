package dev.mvc.food;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.manager.ManagerProcInter;


@Controller
public class FoodCont {
  @Autowired
  @Qualifier("dev.mvc.food.FoodProc") // @Component("dev.mvc.take.TakeProc")
  private FoodProcInter foodProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")   // "dev.mvc.admin.AdminProc"라고 명명된 클래스 (Qualifier->이름을 찾아와주는 역할)
  private ManagerProcInter managerProc; 
  
  /**
  * 등록 폼
  * @return
  */
  @RequestMapping(value="/food/create.do", method=RequestMethod.POST )
  public ModelAndView create(FoodVO foodVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/res/msg");
    
    int cnt = this.foodProc.create(foodVO);
    
    if(cnt == 1) {
      mav.addObject("code","create_success"); // 키, 값
      mav.addObject("foodname",foodVO.getFoodname()); // 카테고리 이름 jsp로 전송
    }
    else {
      mav.addObject("code","create_fail");  
    }
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
  
  /**
   * 전체목록
   * // FORM 데이터 처리 http://localhost:9092/food/list_all.do
   * @return
   */
  @RequestMapping(value="/food/list_all.do",method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if(this.managerProc.isManager(session)) {
      mav.setViewName("/food/list_all");// /WEB-INF/views/res/list_all.jsp
      
      ArrayList<FoodVO> list = this.foodProc.list_all();
      mav.addObject("list",list);
    }
    else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
    }
    return mav;
  }
  
  /**
   * 수정
   * http://localhost:9092/food/update.do?foodno=2
   * @return
   */
  @RequestMapping(value="/food/update.do",method = RequestMethod.GET)
  public ModelAndView update(HttpSession session,int foodno) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
    ModelAndView mav = new ModelAndView();
    
    if(this.managerProc.isManager(session)) {
      mav.setViewName("/food/list_all_update");// /WEB-INF/views/res/update.jsp
      
      FoodVO foodVO = this.foodProc.read(foodno);
      mav.addObject("foodVO",foodVO);
      
      ArrayList<FoodVO> list = this.foodProc.list_all();
      mav.addObject("list",list);
    }
    else {
      mav.setViewName("/manager/login_need");
    }
    
    return mav;
  }
  
  // FORM 데이터 처리 http://localhost:9092/food/update.do
  @RequestMapping(value="/food/update.do",method = RequestMethod.POST)
  public ModelAndView update(FoodVO foodVO) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.foodProc.update(foodVO); // 수정 처리
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/food/list_all.do"); 
    }
    else {
      mav.addObject("code","update_fail");     
      mav.setViewName("/res/msg");  // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
 
  //FORM 데이터 처리 http://localhost:9092/food/delete.do
   @RequestMapping(value="/food/delete.do",method = RequestMethod.GET)
   public ModelAndView delete(HttpSession session, int foodno) { // 자동으로 foodDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
         
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/food/list_all_delete");// /WEB-INF/views/res/update.jsp
       
       FoodVO foodVO = this.foodProc.read(foodno);
       mav.addObject("foodVO",foodVO);
       
       ArrayList<FoodVO> list = this.foodProc.list_all();
       mav.addObject("list",list);
     }
     else {
       mav.setViewName("/manager/login_need");
     }
     
     return mav;
   }
  
  // FORM 데이터 처리 http://localhost:9092/food/update.do
  @RequestMapping(value="/food/delete.do",method = RequestMethod.POST)
  public ModelAndView delete(int foodno) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.foodProc.delete(foodno); // 수정 처리
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/food/list_all.do"); 
    }
    else {
      mav.addObject("code","update_fail");     
      mav.setViewName("/res/msg");  // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
  
  
}
