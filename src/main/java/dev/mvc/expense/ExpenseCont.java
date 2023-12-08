package dev.mvc.expense;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.manager.ManagerProcInter;
import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;
import dev.mvc.supplier.SupplierProcInter;
import dev.mvc.supplier.SupplierVO;

@Controller
public class ExpenseCont {
  @Autowired
  @Qualifier("dev.mvc.expense.ExpenseProc")
  private ExpenseProcInter expenseProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
  
  @Autowired
  @Qualifier("dev.mvc.res.ResProc")
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.supplier.SupplierProc")
  private SupplierProcInter supplierProc;
  
  /**
   * 등록 폼
   * @return
   */
   @RequestMapping(value="/expense/create.do", method=RequestMethod.POST )
   public ModelAndView create(ExpenseVO expenseVO) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/expense/msg");
     
     int cnt = this.expenseProc.create(expenseVO);
     
     if(cnt == 1) {
       mav.addObject("code","create_success"); // 키, 값
       mav.addObject("expense_name",expenseVO.getName()); // 이름 jsp로 전송
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
   @RequestMapping(value="/expense/list_all_managerno.do",method = RequestMethod.GET)
   public ModelAndView list_all(HttpSession session) {
     ModelAndView mav = new ModelAndView();
     
     int managerno=0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/expense/list_all_managerno");// /WEB-INF/views/expense/list_all_managerno.jsp
       
       managerno = (int)session.getAttribute("managerno");
       mav.addObject("managerno", managerno);
       
       ArrayList<SupplierVO> supplier_list = this.supplierProc.list_all_managerno(managerno);
       mav.addObject("supplier_list", supplier_list);
       
       ArrayList<ResVO> res_list = this.resProc.list_all_managerno(managerno);
       mav.addObject("res_list", res_list);
       
       ArrayList<Expense_JoinVO> list = this.expenseProc.list_all_names(managerno);
       mav.addObject("list", list);
     }
     else {
       mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
     }
     return mav;
   }
   
   /**
    * 수정
    * http://localhost:9093/expense/update.do?expenseno=2
    * @return
    */
   @RequestMapping(value="/expense/update.do",method = RequestMethod.GET)
   public ModelAndView update(HttpSession session,int expenseno) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
     ModelAndView mav = new ModelAndView();
     
     int managerno = 0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/expense/list_all_update");// /WEB-INF/views/res/update.jsp
       
       ExpenseVO expenseVO = this.expenseProc.read(expenseno);
       mav.addObject("expenseVO",expenseVO);
       
       managerno = (int)session.getAttribute("managerno");
       mav.addObject("managerno", managerno);
       
       ArrayList<ResVO> res_list = this.resProc.list_all_managerno(managerno);
       mav.addObject("res_list", res_list);
       
       ArrayList<SupplierVO> supplier_list = this.supplierProc.list_all_managerno(managerno);
       mav.addObject("supplier_list", supplier_list);
       
       ArrayList<Expense_JoinVO> list = this.expenseProc.list_all_names(managerno);
       mav.addObject("list", list);
     }
     else {
       mav.setViewName("/manager/login_need");
     }
     
     return mav;
   }
   
   // FORM 데이터 처리 http://localhost:9093/expense/update.do
   @RequestMapping(value="/expense/update.do",method = RequestMethod.POST)
   public ModelAndView update(ExpenseVO expenseVO) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
         
     int cnt = this.expenseProc.update(expenseVO); // 수정 처리
     System.out.println("-> cnt:"+cnt);
     
     if(cnt == 1) {
       mav.setViewName("redirect:/expense/list_all_managerno.do"); 
     }
     else {
       mav.addObject("code","update_fail");     
       mav.setViewName("/expense/msg");  // /WEB-INF/views/res/msg.jsp
     }
     
     mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
     return mav;
   }
   
 //FORM 데이터 처리 http://localhost:9093/expense/delete.do
   @RequestMapping(value="/expense/delete.do",method = RequestMethod.GET)
   public ModelAndView delete(HttpSession session, int expenseno) { // 자동으로 foodDAO 객체가 생성되고 Form의 값이 할당됨
     ModelAndView mav = new ModelAndView();
     
     int managerno=0;
     if(this.managerProc.isManager(session)) {
       mav.setViewName("/expense/list_all_delete");// /WEB-INF/views/res/update.jsp
       
       managerno = (int)session.getAttribute("managerno");
       
       ExpenseVO expenseVO = this.expenseProc.read(expenseno);
       mav.addObject("expenseVO",expenseVO);
       
       ArrayList<Expense_JoinVO> list = this.expenseProc.list_all_names(managerno);
       mav.addObject("list", list);
       
     }
     else {
       mav.setViewName("/manager/login_need");
     }
     
     return mav;
   }
   
// FORM 데이터 처리 http://localhost:9093/expense/delete.do
  @RequestMapping(value="/expense/delete.do",method = RequestMethod.POST)
  public ModelAndView delete(int expenseno) { // 자동으로 FoodDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.expenseProc.delete(expenseno); // 수정 처리
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/expense/list_all_managerno.do"); 
    }
    else {
      mav.addObject("code","delete_fail");     
      mav.setViewName("/expense/msg");  // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
}
