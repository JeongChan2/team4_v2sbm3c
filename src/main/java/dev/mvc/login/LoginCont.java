package dev.mvc.login;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.customer.CustomerProcInter;
import dev.mvc.manager.ManagerProcInter;

@Controller
public class LoginCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // @Component("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;

  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc")
  private CustomerProcInter customerProc;
  
  @Autowired
  @Qualifier("dev.mvc.login.LoginProc")
  private LoginProcInter loginProc;
  
  public LoginCont(){
    System.out.println("-> LoginCont created.");
  }
 
  
}
