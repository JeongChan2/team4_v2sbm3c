package dev.mvc.take;

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
import dev.mvc.requirement.RequirementProcInter;
import dev.mvc.requirement.RequirementVO;

@Controller
public class TakeCont {
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
  
  // http://localhost:9092/take/check.do?customerno=3
  /**
  * 영양 정보 유/무 확인
  * @return {"cnt":0}, {"cnt":1}
  */
  @ResponseBody
  @RequestMapping(value="/take/check.do", method=RequestMethod.GET,
                                produces = "text/plain;charset=UTF-8")
  public String check(int customerno) {
    System.out.println("->영양 정보 유/무 확인(check.do):"+ customerno);
    try {
      Thread.sleep(1000); // 1 초 지연
    }catch(Exception e) {
      
    }
    int cnt = this.takeProc.check(customerno);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt); // 키와 값 = HashMap
    
    
    return json.toString(); // {"cnt":1} 
  }
  
  /**
   * 회원 정보 수정 처리
   * @param RequirementVO
   * @return
   */
  @RequestMapping(value="/take/update.do", method=RequestMethod.POST)
  public ModelAndView update(TakeVO takeVO){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + requirementVO.getId());
    
    int cnt= this.takeProc.update(takeVO);
    
    mav.setViewName("redirect:/requirement/read.do");

    return mav;
  }
   
}
