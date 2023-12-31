package dev.mvc.supplier;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.expense.Expense;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.manager.ManagerVO;


@Controller
public class SupplierCont {
  @Autowired
  @Qualifier("dev.mvc.supplier.SupplierProc")
  private SupplierProcInter supplierProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
  
  /**
   * 등록 폼
   * @return
   */
   @RequestMapping(value="/supplier/create.do", method=RequestMethod.POST )
   public ModelAndView create(SupplierVO supplierVO) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/supplier/msg");
     
     int cnt = this.supplierProc.create(supplierVO);
     
     if(cnt == 1) {
       mav.addObject("code","create_success"); // 키, 값
       mav.addObject("supplier_name",supplierVO.getName()); // 이름 jsp로 전송
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
   @RequestMapping(value="/supplier/list_all_managerno.do",method = RequestMethod.GET)
   public ModelAndView list_all(HttpSession session, SupplierVO supplierVO) {
     ModelAndView mav = new ModelAndView();
     
     int managerno=0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/supplier/list_all_managerno");// /WEB-INF/views/supplier/list_all_managerno.jsp
       
       managerno = (int)session.getAttribute("managerno");
       mav.addObject("managerno", managerno);
       
       supplierVO.setManagerno(managerno);
       
       ArrayList<SupplierVO> list = this.supplierProc.list_by_managerno_search_paging(supplierVO);
       mav.addObject("list", list);
       
       HashMap<String, Object> hashMap = new HashMap<String, Object>();
       hashMap.put("managerno", managerno);
       hashMap.put("word", supplierVO.getWord());
       
       int search_count = this.supplierProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
       mav.addObject("search_count",search_count);
       
       String paging = supplierProc.pagingBox(supplierVO.getManagerno(), supplierVO.getNow_page(), supplierVO.getWord(), "list_all_managerno.do");
       mav.addObject("paging", paging);
       
       int record_per_page = Supplier.RECORD_PER_PAGE;  // 페이지가 바뀌어도 번호 증가 하기위함
       mav.addObject("record_per_page", record_per_page);
     }
     else {
       mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
     }
     return mav;
   }
   
 //FORM 데이터 처리 http://localhost:9093/supplier/delete.do
   @RequestMapping(value="/supplier/delete.do",method = RequestMethod.GET)
   public ModelAndView delete(HttpSession session, int supplierno) { // 자동으로 foodDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
     
     int managerno=0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/supplier/list_all_delete");// /WEB-INF/views/res/update.jsp
       
       managerno = (int)session.getAttribute("managerno");
       
       SupplierVO supplierVO = this.supplierProc.read(supplierno);
       mav.addObject("supplierVO",supplierVO);
       
       ArrayList<SupplierVO> list = this.supplierProc.list_all_managerno(managerno);
       mav.addObject("list",list);
       
     }
     else {
       mav.setViewName("/manager/login_need");
     }
     
     return mav;
   }
   
// FORM 데이터 처리 http://localhost:9093/supplier/delete.do
  @RequestMapping(value="/supplier/delete.do",method = RequestMethod.POST)
  public ModelAndView delete(int supplierno) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.supplierProc.delete(supplierno); // 수정 처리
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/supplier/list_all_managerno.do"); 
    }
    else {
      mav.addObject("code","delete_fail");     
      mav.setViewName("/supplier/msg");  // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
  
  /**
   * 수정
   * http://localhost:9093/supplier/update.do?supplierno=2
   * @return
   */
  @RequestMapping(value="/supplier/update.do",method = RequestMethod.GET)
  public ModelAndView update(HttpSession session,int supplierno) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
    ModelAndView mav = new ModelAndView();
    
    int managerno = 0;
    if(this.managerProc.isManager(session)) {
      mav.setViewName("/supplier/list_all_update");// /WEB-INF/views/res/update.jsp
      
      SupplierVO supplierVO = this.supplierProc.read(supplierno);
      mav.addObject("supplierVO", supplierVO);
      
      managerno = (int)session.getAttribute("managerno");

      ArrayList<SupplierVO> list = this.supplierProc.list_all_managerno(managerno);
      mav.addObject("list",list);
    }
    else {
      mav.setViewName("/manager/login_need");
    }
    
    return mav;
  }
  
  // FORM 데이터 처리 http://localhost:9093/supplier/update.do
  @RequestMapping(value="/supplier/update.do",method = RequestMethod.POST)
  public ModelAndView update(SupplierVO supplierVO) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.supplierProc.update(supplierVO); // 수정 처리
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/supplier/list_all_managerno.do"); 
    }
    else {
      mav.addObject("code","update_fail");     
      mav.setViewName("/supplier/msg");  // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
}
