package dev.mvc.resort_v1sbm3c;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.customer.CustomerProcInter;
import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;
import dev.mvc.rescontents.JoinVO;
import dev.mvc.rescontents.RescontentsProcInter;
import dev.mvc.rescontents.RescontentsVO;
import dev.mvc.tool.Tool;


@Controller
public class HomeCont {
  @Autowired
  @Qualifier("dev.mvc.res.ResProc")
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc") // @Component("dev.mvc.score.ScoreProc")
  private CustomerProcInter customerProc;
  
  @Autowired
  @Qualifier("dev.mvc.rescontents.RescontentsProc") // @Component("dev.mvc.rescontents.RescontentsProc")
  private RescontentsProcInter rescontentsProc;
  
  
  public HomeCont() {
    System.out.println("-> HomeCont created");
  }
   
  // http://localhost:9091
  @RequestMapping(value= {"","/","/index.do","/index.resort"},method=RequestMethod.GET)
  public ModelAndView home(HttpSession session, HttpServletRequest request) {
    System.out.println("->home()");
    
    ModelAndView mav = new ModelAndView();
    //mav.setViewName("/index");// WEB-INF/views/index.jsp
    // spring.mvc.view.prefix=/WEB-INF/views/
    // spring.mvc.view.suffix=.jsp
    mav.setViewName("/rescontents/list_rec"); // /WEB-INF/views/rescontents/list_all_gallery.jsp
    
    int customerno = 0;
    if (this.customerProc.isCustomer(session)) { // 회원으로 로그인
        // session을 사용하여 현재 로그인한 사용자의 customerno 값만 읽음으로 다른 사용자의
        // 정보를 조회할 수 없음
        customerno = (int)session.getAttribute("customerno");
        ArrayList<RescontentsVO> list = this.rescontentsProc.score_list_select(customerno);
        for (RescontentsVO vo : list) {
            String title = vo.getTitle();
            String content = vo.getRescontent();
            
            title = Tool.convertChar(title);  // 특수 문자 처리
            content = Tool.convertChar(content); 
            
            vo.setTitle(title);
            vo.setRescontent(content);  
          }
          mav.addObject("list", list);
        
        
      } else {
          ArrayList<JoinVO> list = this.rescontentsProc.score_list_all();

          // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
          for (JoinVO vo : list) {
            String title = vo.getTitle();
            String content = vo.getRescontent();
            
            title = Tool.convertChar(title);  // 특수 문자 처리
            content = Tool.convertChar(content); 
            
            vo.setTitle(title);
            vo.setRescontent(content);  
          }
          mav.addObject("list", list);
        
      }
    
    return mav;
  }
  
  // http://localhost:9092/menu/top.do
  @RequestMapping(value= {"/menu/top.do"}, method=RequestMethod.GET)
  public ModelAndView top() {
    ModelAndView mav = new ModelAndView();

    ArrayList<ResVO> list_top = this.resProc.list_all_y();
    mav.addObject("list_top", list_top);
    
    mav.setViewName("/menu/top"); // /WEB-INF/views/menu/top.jsp
    
    return mav;
  }
}
