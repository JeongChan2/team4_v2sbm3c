package dev.mvc.withdrawalLog;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.customer.CustomerProcInter;
import dev.mvc.manager.ManagerProcInter;

@Controller
public class WithdrawalLogCont {
  @Autowired
  @Qualifier("dev.mvc.withdrawalLog.WithdrawalLogProc")
  private WithdrawalLogProc withdrawalLogProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc")
  private CustomerProcInter customerProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // @Component("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;

  
  /**
   * 로그인 내역
   * // FORM 데이터 처리 http://localhost:9093/withdrawalLog/log_list.do
   * @return
   */
  @RequestMapping(value="/withdrawalLog/log_list.do",method = RequestMethod.GET)
  public ModelAndView log_list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if(this.managerProc.isManager(session)) {
      mav.setViewName("/withdrawalLog/log_list");// /WEB-INF/views//withdrawalLog/log_list.jsp
      
      ArrayList<JoinLogVO> list = this.withdrawalLogProc.log_list();
      mav.addObject("list",list);
    }
    else {
      mav.setViewName("/customer/login_need");// /webapp/WEB-INF/views/customer/login_need.jsp
    }
    return mav;
  }
  
}
