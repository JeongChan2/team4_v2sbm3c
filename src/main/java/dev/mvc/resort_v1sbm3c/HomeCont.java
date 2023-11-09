package dev.mvc.resort_v1sbm3c;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;
import dev.mvc.rescontents.RescontentsProcInter;
import dev.mvc.rescontents.RescontentsVO;


@Controller
public class HomeCont {
  @Autowired
  @Qualifier("dev.mvc.res.ResProc")
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.rescontents.RescontentsProc") // @Component("dev.mvc.rescontents.RescontentsProc")
  private RescontentsProcInter rescontentsProc;
  
  
  public HomeCont() {
    System.out.println("-> HomeCont created");
  }
   
  // http://localhost:9091
  @RequestMapping(value= {"","/","/index.do","/index.resort"},method=RequestMethod.GET)
  public ModelAndView home() {
    System.out.println("->home()");
    
    ModelAndView mav = new ModelAndView();
    //mav.setViewName("/index");// WEB-INF/views/index.jsp
    // spring.mvc.view.prefix=/WEB-INF/views/
    // spring.mvc.view.suffix=.jsp
    mav.setViewName("/rescontents/list_all_gallery"); // /WEB-INF/views/rescontents/list_all_gallery.jsp
    
    ArrayList<RescontentsVO> list = this.rescontentsProc.list_all();
    mav.addObject("list", list);
    
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
