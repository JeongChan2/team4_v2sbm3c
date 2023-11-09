package dev.mvc.res;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.manager.ManagerProcInter;
import dev.mvc.rescontents.Rescontents;
import dev.mvc.rescontents.RescontentsProcInter;
import dev.mvc.rescontents.RescontentsVO;
import dev.mvc.tool.Tool;

@Controller
public class ResCont {
  @Autowired  // ResDAOInter interface 객체를 만들어 자동으로 할당해라.
  @Qualifier("dev.mvc.res.ResProc")  // 이 인터페이스에 누가 할당되야 하느냐 Autowired로만 안될 때가 있다.
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.rescontents.RescontentsProc") // @Component("dev.mvc.rescontents.RescontentsProc")
  private RescontentsProcInter rescontentsProc;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")   // "dev.mvc.admin.AdminProc"라고 명명된 클래스 (Qualifier->이름을 찾아와주는 역할)
  private ManagerProcInter managerProc; 
  
  public ResCont() {
    System.out.println("-> ResCont created");
  }
  
////FORM 출력, http://localhost:9092/res/create.do
// @RequestMapping(value="/res/create.do", method = RequestMethod.GET)
// @ResponseBody // 단순 문자열로 출력, jsp 파일명 조합이 발생하지 않음.
// public String create() {
//   return "GET 방식 FORM 출력";
// }
 
  // FORM 출력  http://localhost:9092/res/create.do
  @RequestMapping(value="/res/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/res/create"); // /WEB-INF/views/res/create.jsp
    
    return mav;
  }
  
  // FORM 데이터 처리 http://localhost:9092/res/create.do
  @RequestMapping(value="/res/create.do",method = RequestMethod.POST)
  public ModelAndView create(ResVO resVO) { // 자동으로 ResDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView(); 
    mav.setViewName("/res/msg"); // /WEB-INF/views/res/msg.jsp
    
    int cnt = this.resProc.create(resVO);
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.addObject("code","create_success"); // 키, 값
      mav.addObject("resname",resVO.getResname()); // 카테고리 이름 jsp로 전송
    }
    else {
      mav.addObject("code","create_fail");      
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
  
  
  /**
   * 전체목록
   * // FORM 데이터 처리 http://localhost:9092/res/create.do
   * @return
   */
  @RequestMapping(value="/res/list_all.do",method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if(this.managerProc.isManager(session)) {
      mav.setViewName("/res/list_all");// /WEB-INF/views/res/list_all.jsp
      
      ArrayList<ResVO> list = this.resProc.list_all();
      mav.addObject("list",list);
    }
    else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
    }
    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9092/res/read.do?resno=2
   * @return
   */
  @RequestMapping(value="/res/read.do",method = RequestMethod.GET)
  public ModelAndView read(int resno) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/res/read");// /WEB-INF/views/res/read.jsp
    
    ResVO resVO = this.resProc.read(resno);
    mav.addObject("resVO",resVO);
    
    return mav;
  }
  
  /**
   * 수정
   * http://localhost:9092/res/update.do?resno=2
   * @return
   */
  @RequestMapping(value="/res/update.do",method = RequestMethod.GET)
  public ModelAndView update(HttpSession session,int resno) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
    ModelAndView mav = new ModelAndView();
    
    if(this.managerProc.isManager(session)) {
      mav.setViewName("/res/list_all_update");// /WEB-INF/views/res/update.jsp
      
      ResVO resVO = this.resProc.read(resno);
      mav.addObject("resVO",resVO);
      
      ArrayList<ResVO> list = this.resProc.list_all();
      mav.addObject("list",list);
    }
    else {
      mav.setViewName("/manager/login_need");
    }
    
    return mav;
  }
  
  // FORM 데이터 처리 http://localhost:9092/res/update.do
  @RequestMapping(value="/res/update.do",method = RequestMethod.POST)
  public ModelAndView update(ResVO resVO) { // 자동으로 ResDAO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();
        
    int cnt = this.resProc.update(resVO); // 수정 처리
    System.out.println("-> cnt:"+cnt);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/res/list_all.do"); 
    }
    else {
      mav.addObject("code","update_fail");     
      mav.setViewName("/res/msg");  // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt",cnt); // request.setAttribute("cnt",cnt); 와 같은 기능을 해준다.
    return mav;
  }
  
  /**
   * 삭제 처리, http://localhost:9092/res/delete.do?resno=1
   * @return
   */
  @RequestMapping(value="/res/delete.do",method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session,int resno) {  // int resno = (int)request.getParameter("resno"); 이걸 spring이 자동으로 해준다.
    ModelAndView mav = new ModelAndView();
    
    if(this.managerProc.isManager(session)) {
      mav.setViewName("/res/list_all_delete");// /WEB-INF/views/res/delete.jsp
      
      ResVO resVO = this.resProc.read(resno);
      mav.addObject("resVO",resVO);
      
      ArrayList<ResVO> list = this.resProc.list_all();
      mav.addObject("list",list);
      
      // 특정 카테고리에 속한 레코드 갯수를 리턴
      int count_by_resno = this.rescontentsProc.count_by_resno(resno);
      mav.addObject("count_by_resno", count_by_resno);
    }
    else {
      mav.setViewName("/manager/login_need");
    }
    
    return mav;
  }
  
  /**
   * 삭제 처리, http://localhost:9092/res/delete.do
   * @param resno 삭제할 레코드 번호
   * @return
   */
  // FORM 데이터 처리 http://localhost:9092/res/update.do
  @RequestMapping(value="/res/delete.do",method = RequestMethod.POST)
  public ModelAndView delete_proc(HttpSession session, int resno) { // 자동으로 ResVO 객체가 생성되고 Form의 값이 할당됨
    ModelAndView mav = new ModelAndView();

    if (this.managerProc.isManager(session) == true) {
      ArrayList<RescontentsVO> list = this.rescontentsProc.list_by_resno(resno); // 자식 레코드 목록 읽기
      
      for(RescontentsVO rescontentsVO : list) { // 자식 레코드 관련 파일 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 시작
        // -------------------------------------------------------------------
        String file1saved = rescontentsVO.getFile1saved();
        String thumb1 = rescontentsVO.getThumb1();
        
        String uploadDir = Rescontents.getUploadDir();
        Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
        Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
        // -------------------------------------------------------------------
        // 파일 삭제 종료
        // -------------------------------------------------------------------
      }
      
      this.rescontentsProc.delete_by_resno(resno); // 자식 레코드 삭제     
            
      int cnt = this.resProc.delete(resno); // 카테고리 삭제
      
      if (cnt == 1) {
        mav.setViewName("redirect:/res/list_all.do");       // 자동 주소 이동, Spring 재호출
        
      } else {
        mav.addObject("code", "delete_fail");
        mav.setViewName("/res/msg"); // /WEB-INF/views/res/msg.jsp
      }
      
      mav.addObject("cnt", cnt);
      
    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
    
    return mav;
  }
  
  
  /**
   * 우선 순위 높임, 10등->1등, http://localhost:9092/res/update_seqno_forward.do?resno=1
   * @param resno 수정할 레코드 pk번호
   * @return
   */
  @RequestMapping(value="/res/update_seqno_forward.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_forward(int resno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.resProc.update_seqno_forward(resno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/res/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/res/msg"); // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 우선 순위 낮춤, 1등->10등, http://localhost:9092/res/update_seqno_backward.do?resno=1
   * @param resno 수정할 레코드 pk 번호
   * @return
   */
  @RequestMapping(value="/res/update_seqno_backward.do", method = RequestMethod.GET)
  public ModelAndView update_seqno_backward(int resno) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.resProc.update_seqno_backward(resno);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/res/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/res/msg"); // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  /**
   * 카테고리 공개 설정, http://localhost:9092/res/update_visible_y.do?resno=1
   * @param resno 수정할 레코드 PK 번호
   * @return
   */
  @RequestMapping(value="/res/update_visible_y.do", method = RequestMethod.GET)
  public ModelAndView update_visible_y(int resno) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.resProc.update_visible_y(resno); // 수정 처리
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/res/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/res/msg"); // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
  
  
  /**
   * 카테고리 비공개 설정, http://localhost:9092/res/update_visible_n.do?resno=1
   * @param resno 수정할 레코드 PK 번호
   * @return
   */
  @RequestMapping(value="/res/update_visible_n.do", method = RequestMethod.GET)
  public ModelAndView update_visible_n(int resno) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.resProc.update_visible_n(resno); // 수정 처리
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/res/list_all.do"); 
      
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/res/msg"); // /WEB-INF/views/res/msg.jsp
    }
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
    
    return mav;
  }
}
