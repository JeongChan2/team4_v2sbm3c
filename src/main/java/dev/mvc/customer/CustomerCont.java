package dev.mvc.customer;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.manager.ManagerProcInter;

@Controller
public class CustomerCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // @Component("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;

  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc")
  private CustomerProcInter customerProc = null;
  
  
  public CustomerCont(){
    System.out.println("-> CustomerCont created.");
  }
  
  // http://localhost:9092/customer/checkID.do?id=user1@gmail.com
  /**
  * ID 중복 체크, JSON 출력
  * @return {"cnt":0}, {"cnt":1}
  */
  @ResponseBody
  @RequestMapping(value="/customer/checkID.do", method=RequestMethod.GET ,
                              produces = "text/plain;charset=UTF-8" )
  public String checkID(String id) {
    System.out.println("->id:"+id);
    try {
      Thread.sleep(3000); // 3 초 지연
    }catch(Exception e) {
      
    }
    
    int cnt = this.customerProc.checkID(id);
   
    JSONObject json = new JSONObject();
    json.put("cnt", cnt); // 키와 값 = HashMap
   
    return json.toString(); // {"cnt":1} 
  }

  // http://localhost:9092/customer/create.do
  /**
  * 등록 폼
  * @return
  */
  @RequestMapping(value="/customer/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/customer/create"); // /WEB-INF/views/customer/create.jsp
   
    return mav; // forward
  }

  /**
   * 등록 처리
   * @param customerVO
   * @return
   */
  @RequestMapping(value="/customer/create.do", method=RequestMethod.POST)
  public ModelAndView create(CustomerVO customerVO){
    ModelAndView mav = new ModelAndView();
    
    // 중복 ID 검사
    int checkID_cnt = this.customerProc.checkID(customerVO.getId());
    if (checkID_cnt == 0) {
      // System.out.println("id: " + customerVO.getId());
      
      customerVO.setGrade(15); // 기본 회원 가입 등록 15 지정
      
      int cnt= customerProc.create(customerVO); // SQL insert
      
      if (cnt == 1) { // insert 레코드 개수, 회원 가입 성공
        mav.addObject("code", "create_success");
        mav.addObject("cname", customerVO.getCname());  // 홍길동님(user4) 회원 가입을 축하합니다.
        mav.addObject("id", customerVO.getId());
      } else { // 회원 가입 실패
        mav.addObject("code", "create_fail");
        // mav.addObject("cnt", 0);  // 추가된 레코드 없음.  
      }
      
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.addObject("code", "duplicate_fail"); // 이미 사용중인 id임으로 가입 실패
      mav.addObject("cnt", 0);                       // 추가된 레코드 없음.      
    }

    mav.addObject("url", "/customer/msg");  // /customer/msg -> /customer/msg.jsp
    
    mav.setViewName("redirect:/customer/msg.do"); // POST -> GET -> /customer/msg.jsp
    
    return mav;
  }
  
  /**
   * 새로고침 방지, EL에서 param으로 접근, POST -> GET -> /customer/msg.jsp
   * @return
   */
  @RequestMapping(value="/customer/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  /**
  * 목록 출력 가능
  * @param session
  * @return
  */
  @RequestMapping(value="/customer/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session) == true) {
      ArrayList<CustomerVO> list = this.customerProc.list();
      mav.addObject("list", list);

      mav.setViewName("/customer/list"); // /webapp/WEB-INF/views/customer/list.jsp

    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/admin/login_need.jsp
    }
        
    return mav;
  } 
  
  /**
   * localhost:9092/customer/read_info.do?customerno=1
   * 회원 조회
   * 관리자, 회원 본인만 가능
   * @param customerno
   * @return
   */
  @RequestMapping(value="/customer/read_info.do", method=RequestMethod.GET)
  public ModelAndView read_info(HttpSession session, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    
    int customerno = 0;
    if (this.customerProc.isCustomer(session) || this.managerProc.isManager(session)) { 
      // 로그인한 경우

      if (this.customerProc.isCustomer(session)) { // 회원으로 로그인
        // session을 사용하여 현재 로그인한 사용자의 customerno 값만 읽음으로 다른 사용자의
        // 정보를 조회할 수 없음
        customerno = (int)session.getAttribute("customerno");
        
      } else if (this.managerProc.isManager(session)) { // 관리자로 로그인
        // 관리자는 모든 회원의 정보를 조회할 수 있어야 함으로 parameter로 회원번호를 이용하여 조회
        customerno = Integer.parseInt(request.getParameter("customerno"));
        
      }

      CustomerVO customerVO = this.customerProc.read(customerno);
      mav.addObject("customerVO", customerVO);
      mav.setViewName("/customer/read_info"); // /customer/read.jsp
      
    } else {
      // 로그인을 하지 않은 경우
      mav.setViewName("/customer/login_need"); // /webapp/WEB-INF/views/customer/login_need.jsp
    }
    
    return mav; // forward
  }
  
  /**
   * localhost:9092/customer/read.do?customerno=1
   * 회원 조회
   * 관리자, 회원 본인만 가능
   * @param customerno
   * @return
   */
  @RequestMapping(value="/customer/read.do", method=RequestMethod.GET)
  public ModelAndView read(HttpSession session, HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    
    int customerno = 0;
    if (this.customerProc.isCustomer(session) || this.managerProc.isManager(session)) { 
      // 로그인한 경우

      if (this.customerProc.isCustomer(session)) { // 회원으로 로그인
        // session을 사용하여 현재 로그인한 사용자의 customerno 값만 읽음으로 다른 사용자의
        // 정보를 조회할 수 없음
        customerno = (int)session.getAttribute("customerno");
        
      } else if (this.managerProc.isManager(session)) { // 관리자로 로그인
        // 관리자는 모든 회원의 정보를 조회할 수 있어야 함으로 parameter로 회원번호를 이용하여 조회
        customerno = Integer.parseInt(request.getParameter("customerno"));
        
      }

      CustomerVO customerVO = this.customerProc.read(customerno);
      mav.addObject("customerVO", customerVO);
      mav.setViewName("/customer/read"); // /customer/read.jsp
      
    } else {
      // 로그인을 하지 않은 경우
      mav.setViewName("/customer/login_need"); // /webapp/WEB-INF/views/customer/login_need.jsp
    }
    
    return mav; // forward
  }
 
  /**
   * 회원 정보 수정 처리
   * @param customerVO
   * @return
   */
  @RequestMapping(value="/customer/update.do", method=RequestMethod.POST)
  public ModelAndView update(CustomerVO customerVO){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + customerVO.getId());
    
    int cnt= this.customerProc.update(customerVO);
    
    if (cnt == 1) {
      mav.addObject("code", "update_success");
      mav.addObject("cname", customerVO.getCname());  // 홍길동님(user4) 회원 정보를 변경했습니다.
      mav.addObject("id", customerVO.getId());
      mav.addObject("age", customerVO.getAge());
      mav.addObject("gender", customerVO.getGender());
      mav.addObject("height", customerVO.getHeight());
      mav.addObject("weight", customerVO.getWeight());
    } else {
      mav.addObject("code", "update_fail");
    }

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("url", "/customer/msg");  // /customer/msg -> /customer/msg.jsp
    
    mav.setViewName("redirect:/customer/msg.do");
    
    return mav;
  }
  
  /**
   * 회원 삭제
   * @param customerno
   * @return
   */
  @RequestMapping(value="/customer/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int customerno){
    ModelAndView mav = new ModelAndView();
    
    CustomerVO customerVO = this.customerProc.read(customerno); // 삭제할 레코드를 사용자에게 출력하기위해 읽음.
    mav.addObject("customerVO", customerVO);
    mav.setViewName("/customer/delete"); // /customer/delete.jsp
    
    return mav; // forward
  }
 
  /**
   * 회원 삭제 처리
   * @param customerVO
   * @return
   */
  @RequestMapping(value="/customer/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int customerno){
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("id: " + customerVO.getId());
    // 삭제된 정보를 msg.jsp에 출력하기 위해, 삭제전에 회원 정보를 읽음.
    CustomerVO customerVO = this.customerProc.read(customerno); 
    
    int cnt= this.customerProc.delete(customerno);

    if (cnt == 1) {
      mav.addObject("code", "delete_success");
      mav.addObject("cname", customerVO.getCname());  // 홍길동님(user4) 회원 정보를 삭제했습니다.
      mav.addObject("id", customerVO.getId());
    } else {
      mav.addObject("code", "delete_fail");
    }

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("url", "/customer/msg");  // /customer/msg -> /customer/msg.jsp
    
    mav.setViewName("redirect:/customer/msg.do");
    
    return mav;
  }
  
//  /**
//  * 로그인 폼
//  * @return
//  */
//  // http://localhost:9092/customer/login.do 
//  @RequestMapping(value = "/customer/login.do", 
//                            method = RequestMethod.GET)
//  public ModelAndView login() {
//     ModelAndView mav = new ModelAndView();
//    
//     mav.setViewName("/customer/login_form");
//     return mav;
//  }
//  
//  /**
//  * 로그인 처리
//  * @return
//  */
//  // http://localhost:9092/customer/login.do 
//  @RequestMapping(value = "/customer/login.do", 
//                            method = RequestMethod.POST)
//  public ModelAndView login_proc(HttpSession session,
//                                                  String id, 
//                                                  String passwd) {
//     ModelAndView mav = new ModelAndView();
//     HashMap<String, Object> map = new HashMap<String, Object>();
//     map.put("id", id);
//     map.put("passwd", passwd);
//     
//     int count = this.customerProc.login(map); // id, passwd 일치 여부 확인
//     if (count == 1) { // 로그인 성공
//       // System.out.println(id + " 로그인 성공");
//       CustomerVO customerVO = customerProc.readById(id); // 로그인한 회원의 정보 조회
//       session.setAttribute("customerno", customerVO.getCustomerno());
//       session.setAttribute("id", id);
//       session.setAttribute("cname", customerVO.getCname());
//       session.setAttribute("grade", customerVO.getGrade());
//       
//       mav.setViewName("redirect:/index.do"); // 시작 페이지로 이동  
//     } else {
//       mav.addObject("url", "/customer/login_fail_msg"); // login_fail_msg.jsp, redirect parameter 적용
//      
//       mav.setViewName("redirect:/customer/msg.do"); // 새로고침 방지
//     }
//         
//     return mav;
//  }
  
  /**
   * 로그아웃 처리
   * @param session
   * @return
   */
  @RequestMapping(value="/customer/logout.do", 
                             method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session){
    ModelAndView mav = new ModelAndView();
    session.invalidate(); // 모든 session 변수 삭제
    
    mav.setViewName("redirect:/index.do"); 
    
    return mav;
  }
  
  /**
   * 로그인 폼
   * @return
   */
  // http://localhost:9092/customer/login.do 
  @RequestMapping(value = "/customer/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
  
    String ck_id = ""; // id 저장
    String ck_id_save = ""; // id 저장 여부를 체크
    String ck_passwd = ""; // passwd 저장
    String ck_passwd_save = ""; // passwd 저장 여부를 체크
  
    if (cookies != null) { // 쿠키가 존재한다면
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
      
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();  // Y, N
        }else if (cookie.getName().equals("ck_passwd")){
          ck_passwd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_passwd_save")){
          ck_passwd_save = cookie.getValue();  // Y, N
        }
      }
    }
  
    //    <input type='text' class="form-control" name='id' id='id' 
    //            value='${ck_id }' required="required" 
    //            style='width: 30%;' placeholder="아이디" autofocus="autofocus">
    mav.addObject("ck_id", ck_id);
  
    //    <input type='checkbox' name='id_save' value='Y' 
    //            ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 저장
    mav.addObject("ck_id_save", ck_id_save);
  
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);
  
    mav.setViewName("/customer/login_form_ck"); // /customer/login_form_ck.jsp
    return mav;
  }
   
  /**
  * Cookie 기반 로그인 처리
  * @param request Cookie를 읽기위해 필요
  * @param response Cookie를 쓰기위해 필요
  * @param session 로그인 정보를 메모리에 기록
  * @param id  회원 아이디
  * @param passwd 회원 패스워드
  * @param id_save 회원 아이디 Cookie에 저장 여부
  * @param passwd_save 패스워드 Cookie에 저장 여부
  * @return
  */
  // http://localhost:9092/customer/login.do 
  @RequestMapping(value = "/customer/login.do", 
                            method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session,
                            String id,
                            String passwd,
                            @RequestParam(value="id_save", defaultValue="") String id_save,
                            @RequestParam(value="passwd_save", defaultValue="") String passwd_save) {
    ModelAndView mav = new ModelAndView();
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);
   
    int cnt = customerProc.login(map);
    if (cnt == 1) { // 로그인 성공
      // System.out.println(id + " 로그인 성공");
      CustomerVO customerVO = customerProc.readById(id);
      session.setAttribute("customerno", customerVO.getCustomerno()); // 서버의 메모리에 기록
      session.setAttribute("id", id);
      session.setAttribute("cname", customerVO.getCname());
      session.setAttribute("grade", customerVO.getGrade());
   
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
        ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
        response.addCookie(ck_id); // id 저장
      } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setPath("/");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id); // id 저장
      }
      
      // id를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setPath("/");
      ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------
  
      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_passwd = new Cookie("ck_passwd", passwd);
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setPath("/");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // passwd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setPath("/");
      ck_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
   
      mav.setViewName("redirect:/index.do");  
    } else {
      mav.addObject("url", "/customer/login_fail_msg");
      mav.setViewName("redirect:/customer/msg.do"); 
    }
       
    return mav;
  }
  
  /**
   * 패스워드를 변경합니다.
   * @param customerno
   * @return
   */
  @RequestMapping(value="/customer/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/customer/passwd_update"); // passwd_update.jsp
    
    return mav;
  }
  
  /**
   * 패스워드 검사 
   * 로그인 실행 -> http://localhost:9092/customer/passwd_check.do?current_passwd=1234
   * @param session
   * @param current_passwd 현재 패스워드
   * @return 1: 일치, 0: 불일치
   */
  @ResponseBody
  @RequestMapping(value="/customer/passwd_check.do", method=RequestMethod.GET)
  public String passwd_check(HttpSession session, String current_passwd) {
    try {
      Thread.sleep(2000);
    }catch(Exception e) {
      e.printStackTrace();
    }
    
    int customerno = (int)session.getAttribute("customerno");
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("customerno", customerno);
    map.put("passwd", current_passwd);
    int cnt = this.customerProc.passwd_check(map);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    
    return json.toString();
  }
  
  /**
   * 패스워드 변경 처리
   * @param customerno 회원 번호
   * @param current_passwd 현재 패스워드
   * @param new_passwd 새로운 패스워드
   * @return
   */
  @RequestMapping(value="/customer/passwd_update.do", method=RequestMethod.POST)
  public ModelAndView passwd_update(HttpSession session, String current_passwd, String new_passwd){
    ModelAndView mav = new ModelAndView();
    
    int customerno = (int)session.getAttribute("customerno"); // 현재 로그인한 회원의 정보만 패스워드 변경 가능
    //int customerno = 3; // 테스트
    
    CustomerVO customerVO = this.customerProc.read(customerno); // 패스워드를 변경하려는 회원 정보를 읽음
    mav.addObject("cname", customerVO.getCname());  
    mav.addObject("id", customerVO.getId());
    
    // 현재 패스워드 검사용 데이터
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("customerno", customerno);
    map.put("passwd", current_passwd);
    
    int cnt = customerProc.passwd_check(map); // 현재 패스워드 검사
    int update_cnt = 0; // 변경된 패스워드 수
    
    if (cnt == 1) { // 현재 패스워드가 일치하는 경우
      map.put("passwd", new_passwd); // 새로운 패스워드를 저장
      update_cnt = this.customerProc.passwd_update(map); // 패스워드 변경 처리
      
      if (update_cnt == 1) {
        mav.addObject("code", "passwd_update_success"); // 패스워드 변경 성공
      } else {
        cnt = 0;  // 패스워드는 일치했으나 변경하지는 못함.
        mav.addObject("code", "passwd_update_fail");       // 패스워드 변경 실패
      }
      
      mav.addObject("update_cnt", update_cnt);  // 변경된 패스워드의 갯수    
    } else {
      mav.addObject("code", "passwd_fail"); // 패스워드가 일치하지 않는 경우
    }

    mav.addObject("cnt", cnt); // 패스워드 일치 여부
    mav.addObject("url", "/customer/msg");  // /customer/msg -> /customer/msg.jsp
    
    mav.setViewName("redirect:/customer/msg.do");
    
    return mav;
  }
}
