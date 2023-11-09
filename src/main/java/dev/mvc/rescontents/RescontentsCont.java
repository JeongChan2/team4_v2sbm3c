package dev.mvc.rescontents;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.food.FoodProcInter;
import dev.mvc.food.FoodVO;
import dev.mvc.manager.ManagerProcInter;
import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class RescontentsCont {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc") // @Component("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc;
  
  @Autowired
  @Qualifier("dev.mvc.res.ResProc")  // @Component("dev.mvc.res.ResProc")
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.rescontents.RescontentsProc") // @Component("dev.mvc.rescontents.RescontentsProc")
  private RescontentsProcInter rescontentsProc;
  
  @Autowired
  @Qualifier("dev.mvc.food.FoodProc") // @Component("dev.mvc.food.FoodProc")
  private FoodProcInter foodProc;
  
  public RescontentsCont () {
    System.out.println("-> RescontentsCont created.");
  }
  
  /**
   * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * POST → url → GET → 데이터 전송
   * @return
   */
  @RequestMapping(value="/rescontents/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  // 등록 폼, contents 테이블은 FK로 resno를 사용함.
  // http://localhost:9092/rescontents/create.do  X
  // http://localhost:9092/rescontents/create.do?resno=1  // resno 변수값을 보내는 목적
  // http://localhost:9092/rescontents/create.do?resno=2
  // http://localhost:9092/rescontents/create.do?resno=3
  @RequestMapping(value="/rescontents/create.do", method = RequestMethod.GET)
  public ModelAndView create(int resno) {
//  public ModelAndView create(HttpServletRequest request,  int resno) {
    ModelAndView mav = new ModelAndView();

    ResVO resVO = this.resProc.read(resno); // create.jsp에 카테고리 정보를 출력하기위한 목적
    mav.addObject("resVO", resVO);
    
    ArrayList<FoodVO> list = this.foodProc.list_all();  // 음식 리스트를 받아옴
    mav.addObject("list", list);
    
//    request.setAttribute("resVO", resVO);
    
    mav.setViewName("/rescontents/create"); // /webapp/WEB-INF/views/contents/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9092/rescontents/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/rescontents/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, HttpSession session, RescontentsVO rescontentsVO) {
    ModelAndView mav = new ModelAndView();
    
    System.out.println(rescontentsVO.toString());
    
    if (managerProc.isManager(session)) { // 관리자로 로그인한경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image
      String file1saved = "";   // 저장된 파일명, image
      String thumb1 = "";     // preview image

      String upDir =  Rescontents.getUploadDir(); // 파일을 업로드할 폴더 준비
      System.out.println("-> upDir: " + upDir);
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = rescontentsVO.getFile1MF();
      
      file1 = mf.getOriginalFilename(); // 원본 파일명 산출. 01.jpg
      System.out.println("-> 원본 파일명 산출 file1: " + file1);
      
      long size1 = mf.getSize();  // 파일 크기
      
      if (size1 > 0) { // 파일 크기 체크
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        file1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
          thumb1 = Tool.preview(upDir, file1saved, 200, 150); 
        }
        
      }    
      
      rescontentsVO.setFile1(file1);   // 순수 원본 파일명
      rescontentsVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
      rescontentsVO.setThumb1(thumb1);      // 원본이미지 축소판
      rescontentsVO.setSize1(size1);  // 파일 크기
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
      
      // Call By Reference: 메모리 공유, Hashcode 전달
      int managerno = (int)session.getAttribute("managerno"); // managerno FK
      rescontentsVO.setManagerno(managerno);
      
      System.out.println(rescontentsVO.toString());
      
      int cnt = this.rescontentsProc.create(rescontentsVO); 
      
      // ------------------------------------------------------------------------------
      // PK의 return
      // ------------------------------------------------------------------------------
      // System.out.println("--> rescontentsno: " + contentsVO.getContentsno());
      // mav.addObject("rescontentsno", rescontentsVO.getRescontentsno()); // redirect parameter 적용
      // ------------------------------------------------------------------------------
      
      if (cnt == 1) {
          mav.addObject("code", "create_success");
          // resProc.increaseCnt(rescontentsVO.getResno()); // 글수 증가
      } else {
          mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
      // System.out.println("--> resno: " + rescontentsVO.getResno());
      // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
      mav.addObject("resno", rescontentsVO.getResno()); // redirect parameter 적용
      
      mav.addObject("url", "/rescontents/msg"); // msg.jsp, redirect parameter 적용
      mav.setViewName("redirect:/rescontents/msg.do"); // Post -> Get - param...

    } else {
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      mav.setViewName("redirect:/rescontents/msg.do"); 
    }
    
    return mav; // forward
  }

  /**
   * 전체 목록, 관리자만 사용 가능
   * http://localhost:9092/contents/list_all.do
   * @return
   */
  @RequestMapping(value="/rescontents/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session) == true) {
      mav.setViewName("/rescontents/list_all"); // /WEB-INF/views/contents/list_all.jsp
      
      ArrayList<RescontentsVO> list = this.rescontentsProc.list_all();
      // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
      for (RescontentsVO rescontentsVO : list) {
        String title = rescontentsVO.getTitle();
        String content = rescontentsVO.getRescontent();
        
        title = Tool.convertChar(title);  // 특수 문자 처리
        content = Tool.convertChar(content); 
        
        rescontentsVO.setTitle(title);
        rescontentsVO.setRescontent(content);  

      }
      mav.addObject("list", list);
      
    } else {
      mav.setViewName("/manager/login_need"); // /WEB-INF/views/manager/login_need.jsp
      
    }
    
    return mav;
  }
  
//  // @RequestMapping(value="/rescontents/list_by_resno.do", method = RequestMethod.GET)은
//  // value에 있는 "list_by_resno.do"란 파일은 실제 존재하는 파일이 아니고 그냥 이런 url을 줄 때 이 함수를 호출하게 끔 연결만
//  // 되는 것이다.
//  /**
//   * 특정 카테고리의 검색 목록
//   * http://localhost:9092/rescontents/list_by_resno.do?resno=1
//   * @return
//   */
//  @RequestMapping(value="/contents/list_by_resno.do", method = RequestMethod.GET)
//  public ModelAndView list_by_resno(int resno, String word) {
//    ModelAndView mav = new ModelAndView();
//
//    //검색하지 않는 경우
//    //mav.setViewName("/contents/list_by_resno"); // /WEB-INF/views/contents/list_by_resno.jsp
//
//    //검색하는 경우
//    mav.setViewName("/contents/list_by_resno"); // /WEB-INF/views/contents/list_by_resno_search.jsp
//
//    
//    ResVO resVO = this.resProc.read(resno); // create.jsp에 카테고리 정보를 출력하기위한 목적
//    mav.addObject("resVO", resVO);
//    // request.setAttribute("resVO", resVO);
//    
//    //검색하지 않는 경우
//    //ArrayList<RescontentsVO> list = this.rescontentsProc.list_by_resno(resno);
//    
//    // 검색하는 경우
//    HashMap<String, Object> hashMap = new HashMap<String, Object>();
//    hashMap.put("resno", resno);
//    hashMap.put("word", word);
//    ArrayList<RescontentsVO> list = this.rescontentsProc.list_by_resno_search(hashMap);
//    
//    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
//    for (RescontentsVO rescontentsVO : list) {
//      String title = rescontentsVO.getTitle();
//      String content = rescontentsVO.getRescontent();
//      
//      title = Tool.convertChar(title);  // 특수 문자 처리
//      content = Tool.convertChar(content); 
//      
//      rescontentsVO.setTitle(title);
//      rescontentsVO.setRescontent(content);  
//
//    }
//    
//    mav.addObject("list", list);
//    
//    return mav;
//  }
  
  /**
 * 목록 + 검색 + 페이징 지원
 * 검색하지 않는 경우
 * http://localhost:9092/rescontents/list_by_resno.do?resno=1&word=&now_page=1
 * 검색하는 경우
 * http://localhost:9092/rescontents/list_by_resno.do?resno=1&word=스위스&now_page=1
 * 
 * @param resno
 * @param word
 * @param now_page
 * @return
 */
  @RequestMapping(value = "/rescontents/list_by_resno.do", method = RequestMethod.GET)
  public ModelAndView list_by_resno(RescontentsVO rescontentsVO) { // => list_by_resno_search_paging 페이징이 적용된
    ModelAndView mav = new ModelAndView();
  
    // 검색 목록
    ArrayList<RescontentsVO> list = rescontentsProc.list_by_resno_search_paging(rescontentsVO);
    
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (RescontentsVO vo : list) {
      String title = vo.getTitle();
      String content = vo.getRescontent();
      
      title = Tool.convertChar(title);  // 특수 문자 처리
      content = Tool.convertChar(content); 
      
      vo.setTitle(title);
      vo.setRescontent(content);  
  
    }
    
    mav.addObject("list", list);
  
    ResVO resVO = resProc.read(rescontentsVO.getResno());
    mav.addObject("resVO", resVO);
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("resno", rescontentsVO.getResno());
    hashMap.put("word", rescontentsVO.getWord());
    
    int search_count = this.rescontentsProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
    mav.addObject("search_count",search_count);
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param resno 카테고리번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @param list_file 목록 파일명
     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
     */
    String paging = rescontentsProc.pagingBox(rescontentsVO.getResno(), rescontentsVO.getNow_page(), rescontentsVO.getWord(), "list_by_resno.do");
    mav.addObject("paging", paging);
  
    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/rescontents/list_by_resno");  // /contents/list_by_resno_search_paging.jsp
  
    return mav;
  }
  
  /**
 * 목록 + 검색 + 페이징 지원 + Grid
 * 검색하지 않는 경우
 * http://localhost:9091/rescontents/list_by_resno_grid.do?resno=1&word=&now_page=1
 * 검색하는 경우
 * http://localhost:9091/rescontents/list_by_resno_grid.do?resno=1&word=스위스&now_page=1
 * 
 * @param resno
 * @param word
 * @param now_page
 * @return
 */
  @RequestMapping(value = "/rescontents/list_by_resno_grid.do", method = RequestMethod.GET)
  public ModelAndView list_by_resno_grid(RescontentsVO rescontentsVO) { // => list_by_resno_search_paging 페이징이 적용된
    ModelAndView mav = new ModelAndView();
  
    // 검색 목록
    ArrayList<RescontentsVO> list = rescontentsProc.list_by_resno_search_paging(rescontentsVO);
    
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (RescontentsVO vo : list) {
      String title = vo.getTitle();
      String content = vo.getRescontent();
      
      title = Tool.convertChar(title);  // 특수 문자 처리
      content = Tool.convertChar(content); 
      
      vo.setTitle(title);
      vo.setRescontent(content);  
  
    }
    
    mav.addObject("list", list);
  
    ResVO resVO = resProc.read(rescontentsVO.getResno());
    mav.addObject("resVO", resVO);
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("resno", rescontentsVO.getResno());
    hashMap.put("word", rescontentsVO.getWord());
    
    int search_count = this.rescontentsProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
    mav.addObject("search_count",search_count);
    
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param resno 카테고리번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @param list_file 목록 파일명
     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
     */
    String paging = rescontentsProc.pagingBox(rescontentsVO.getResno(), rescontentsVO.getNow_page(), rescontentsVO.getWord(), "list_by_resno_grid.do");
    mav.addObject("paging", paging);
  
    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/rescontents/list_by_resno_grid");  // /contents/list_by_resno_grid.jsp
  
    return mav;
  }
  
  /**
   * 조회
   * http://localhost:9092/rescontents/read.do?rescontentsno=17
   * @return
   */
  @RequestMapping(value="/rescontents/read.do", method = RequestMethod.GET)
  public ModelAndView read(int rescontentsno) { // int resno = (int)request.getParameter("resno");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/rescontents/read"); // /WEB-INF/views/contents/read.jsp
    
    RescontentsVO rescontentsVO = this.rescontentsProc.read(rescontentsno);
    
    String title = rescontentsVO.getTitle();
    String content = rescontentsVO.getRescontent();
    
    title = Tool.convertChar(title);  // 특수 문자 처리
    content = Tool.convertChar(content); 
    
    rescontentsVO.setTitle(title);
    rescontentsVO.setRescontent(content);  
    
    mav.addObject("rescontentsVO", rescontentsVO);
    
    return mav;
  }

  /**
   * 맵 등록/수정/삭제 폼
   * http://localhost:9092/rescontents/map.do?rescontentsno=1
   * @return
   */
  @RequestMapping(value="/rescontents/map.do", method=RequestMethod.GET )
  public ModelAndView map(int rescontentsno) {
    ModelAndView mav = new ModelAndView();

    RescontentsVO rescontentsVO = this.rescontentsProc.read(rescontentsno); // map 정보 읽어 오기
    mav.addObject("rescontentsVO", rescontentsVO); // request.setAttribute("contentsVO", contentsVO);

    ResVO resVO = this.resProc.read(rescontentsVO.getResno()); // 그룹 정보 읽기
    mav.addObject("resVO", resVO); 

    mav.setViewName("/rescontents/map"); // /WEB-INF/views/contents/map.jsp
        
    return mav;
  }
  
  /**
   * MAP 등록/수정/삭제 처리
   * http://localhost:9092/contents/map.do
   * @param rescontentsVO
   * @return
   */
  @RequestMapping(value="/rescontents/map.do", method = RequestMethod.POST)
  public ModelAndView map_update(int rescontentsno, String map) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("rescontentsno", rescontentsno);
    hashMap.put("map", map);
    
    this.rescontentsProc.map(hashMap);
    
    mav.setViewName("redirect:/rescontents/read.do?rescontentsno=" + rescontentsno); 
    // /webapp/WEB-INF/views/contents/read.jsp
    
    return mav;
  }
  
  /**
   * Youtube 등록/수정/삭제 폼
   * http://localhost:9092/contents/map.do?rescontentsno=1
   * @return
   */
  @RequestMapping(value="/rescontents/youtube.do", method=RequestMethod.GET )
  public ModelAndView youtube(int rescontentsno) {
    ModelAndView mav = new ModelAndView();

    RescontentsVO rescontentsVO = this.rescontentsProc.read(rescontentsno); // map 정보 읽어 오기
    mav.addObject("rescontentsVO", rescontentsVO); // request.setAttribute("contentsVO", contentsVO);

    ResVO resVO = this.resProc.read(rescontentsVO.getResno()); // 그룹 정보 읽기
    mav.addObject("resVO", resVO); 

    mav.setViewName("/rescontents/youtube"); // /WEB-INF/views/contents/youtube.jsp
        
    return mav;
  }
  
  /**
   * Youtube 등록/수정/삭제 처리
   * http://localhost:9092/contents/map.do
   * @param rescontentsno 글 번호
   * @param youtube Youtube url의 소스 코드
   * @return
   */
  @RequestMapping(value="/rescontents/youtube.do", method = RequestMethod.POST)
  public ModelAndView youtube_update(int rescontentsno, String youtube) {
    ModelAndView mav = new ModelAndView();
    
    if (youtube.trim().length() > 0) {  // 삭제 중인지 확인, 삭제가 아니면 youtube 크기 변경
      youtube = Tool.youtubeResize(youtube, 640);  // youtube 영상의 크기를 width 기준 640 px로 변경
    }    
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("rescontentsno", rescontentsno);
    hashMap.put("youtube", youtube);
    
    this.rescontentsProc.youtube(hashMap);
    
    mav.setViewName("redirect:/rescontents/read.do?rescontentsno=" + rescontentsno); 
    // /webapp/WEB-INF/views/contents/read.jsp
    
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9092/rescontents/update_text.do?rescontentsno=1
   * 
   * @return
   */
  @RequestMapping(value = "/rescontents/update_text.do", method = RequestMethod.GET)
  public ModelAndView update_text(HttpSession session, int rescontentsno) {
    ModelAndView mav = new ModelAndView();
    
    if (managerProc.isManager(session)) { // 관리자로 로그인한경우
      RescontentsVO rescontentsVO = this.rescontentsProc.read(rescontentsno);
      mav.addObject("rescontentsVO", rescontentsVO);
      
      ResVO resVO = this.resProc.read(rescontentsVO.getResno());
      mav.addObject("resVO", resVO);
      
      ArrayList<FoodVO> list = this.foodProc.list_all();  // 음식 리스트를 받아옴
      mav.addObject("list", list);
      
      mav.setViewName("/rescontents/update_text"); // /WEB-INF/views/contents/update_text.jsp
      // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
      // mav.addObject("content", content);

    } else {
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/rescontents/msg.do"); 
    }

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9092/rescontents/update_text.do?rescontentsno=1
   * 
   * @return
   */
  @RequestMapping(value = "/rescontents/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(HttpSession session, RescontentsVO rescontentsVO) {
    ModelAndView mav = new ModelAndView();
    
    // System.out.println("-> word: " + contentsVO.getWord());
    
    if (this.managerProc.isManager(session)) { // 관리자 로그인 확인
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("rescontentsno", rescontentsVO.getRescontentsno());
      hashMap.put("passwd", rescontentsVO.getPasswd());
      
      System.out.println(rescontentsVO.toString());
      
      if (this.rescontentsProc.password_check(hashMap) == 1) { // 패스워드 일치
        this.rescontentsProc.update_text(rescontentsVO); // 글수정  
         
        // mav 객체 이용
        mav.addObject("rescontentsno", rescontentsVO.getRescontentsno());
        mav.addObject("resno", rescontentsVO.getResno());
        mav.setViewName("redirect:/rescontents/read.do"); // 페이지 자동 이동
        
      } else { // 패스워드 불일치
        mav.addObject("code", "passwd_fail");
        mav.addObject("cnt", 0);
        mav.addObject("url", "/rescontents/msg"); // msg.jsp, redirect parameter 적용
        mav.setViewName("redirect:/rescontents/msg.do");  // POST -> GET -> JSP 출력
      }
    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/rescontents/msg.do"); 
    }
    
    mav.addObject("now_page", rescontentsVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★
    
    // URL에 파라미터의 전송
    // mav.setViewName("redirect:/contents/read.do?contentsno=" + contentsVO.getContentsno() + "&resno=" + contentsVO.getResno());             
    
    return mav; // forward
  }

  
  /**
   * 파일 수정 폼
   * http://localhost:9092/rescontents/update_file.do?rescontentsno=1
   * 
   * @return
   */
  @RequestMapping(value = "/rescontents/update_file.do", method = RequestMethod.GET)
  public ModelAndView update_file(HttpSession session, int rescontentsno) {
    ModelAndView mav = new ModelAndView();
    
    if (managerProc.isManager(session)) { // 관리자로 로그인한경우
      RescontentsVO rescontentsVO = this.rescontentsProc.read(rescontentsno);
      mav.addObject("rescontentsVO", rescontentsVO);
      
      ResVO resVO = this.resProc.read(rescontentsVO.getResno());
      mav.addObject("resVO", resVO);
      
      mav.setViewName("/rescontents/update_file"); // /WEB-INF/views/contents/update_file.jsp
      
    } else {
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/rescontents/msg.do"); 
    }


    return mav; // forward
  }
 
  /**
   * 파일 수정 처리 http://localhost:9092/rescontents/update_file.do
   * 
   * @return
   */
  @RequestMapping(value = "/rescontents/update_file.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpSession session, RescontentsVO rescontentsVO) {
    ModelAndView mav = new ModelAndView();
    
    if (this.managerProc.isManager(session)) {
      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
      RescontentsVO rescontentsVO_old = rescontentsProc.read(rescontentsVO.getRescontentsno());
      
      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      String file1saved = rescontentsVO_old.getFile1saved();  // 실제 저장된 파일명
      String thumb1 = rescontentsVO_old.getThumb1();       // 실제 저장된 preview 이미지 파일명
      long size1 = 0;
         
      String upDir =  Rescontents.getUploadDir(); // C:/kd/deploy/resort_v2sbm3c/contents/storage/
      
      Tool.deleteFile(upDir, file1saved);  // 실제 저장된 파일삭제
      Tool.deleteFile(upDir, thumb1);     // preview 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------
          
      // -------------------------------------------------------------------
      // 파일 전송 시작
      // -------------------------------------------------------------------
      String file1 = "";          // 원본 파일명 image

      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //           value='' placeholder="파일 선택">
      MultipartFile mf = rescontentsVO.getFile1MF();
          
      file1 = mf.getOriginalFilename(); // 원본 파일명
      size1 = mf.getSize();  // 파일 크기
          
      if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        file1saved = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(file1saved)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
          thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
        }
        
      } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
        file1="";
        file1saved="";
        thumb1="";
        size1=0;
      }
          
      rescontentsVO.setFile1(file1);
      rescontentsVO.setFile1saved(file1saved);
      rescontentsVO.setThumb1(thumb1);
      rescontentsVO.setSize1(size1);
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------
          
      this.rescontentsProc.update_file(rescontentsVO); // Oracle 처리

      mav.addObject("rescontentsno", rescontentsVO.getRescontentsno());
      mav.addObject("resno", rescontentsVO.getResno());
      mav.setViewName("redirect:/rescontents/read.do"); // request -> param으로 접근 전환
                
    } else {
      mav.addObject("url", "/manager/login_need"); // login_need.jsp, redirect parameter 적용
      mav.setViewName("redirect:/rescontents/msg.do"); // GET
    }

    // redirect하게되면 전부 데이터가 삭제됨으로 mav 객체에 다시 저장
    mav.addObject("now_page", rescontentsVO.getNow_page());
    
    return mav; // forward
  }   
  
  /**
   * 파일 삭제 폼
   * http://localhost:9091/rescontents/delete.do?contentsno=1
   * 
   * @return
   */
  @RequestMapping(value = "/rescontents/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session, int rescontentsno) {
    ModelAndView mav = new ModelAndView();
    
    if (managerProc.isManager(session)) { // 관리자로 로그인한경우
      RescontentsVO rescontentsVO = this.rescontentsProc.read(rescontentsno);
      mav.addObject("rescontentsVO", rescontentsVO);
      
      ResVO resVO = this.resProc.read(rescontentsVO.getResno());
      mav.addObject("resVO", resVO);
      
      mav.setViewName("/rescontents/delete"); // /WEB-INF/views/contents/delete.jsp
      
    } else {
      mav.addObject("url", "/manager/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/rescontents/msg.do"); 
    }


    return mav; // forward
  }
  
  /**
   * 삭제 처리 http://localhost:9092/rescontents/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/rescontents/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RescontentsVO rescontentsVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 삭제 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    RescontentsVO rescontentsVO_read = rescontentsProc.read(rescontentsVO.getRescontentsno());
        
    String file1saved = rescontentsVO.getFile1saved();
    String thumb1 = rescontentsVO.getThumb1();
    
    String uploadDir = Rescontents.getUploadDir();
    Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
    Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
    // -------------------------------------------------------------------
    // 파일 삭제 종료
    // -------------------------------------------------------------------
        
    this.rescontentsProc.delete(rescontentsVO.getRescontentsno()); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
    int now_page = rescontentsVO.getNow_page();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("resno", rescontentsVO.getResno());
    hashMap.put("word", rescontentsVO.getWord());
    
    if (rescontentsProc.search_count(hashMap) % Rescontents.RECORD_PER_PAGE == 0) {
      now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
      if (now_page < 1) {
        now_page = 1; // 시작 페이지
      }
    }
    // -------------------------------------------------------------------------------------

    mav.addObject("resno", rescontentsVO.getResno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/rescontents/list_by_resno.do"); 
    
    return mav;
  }   
  
//http://localhost:9091/rescontents/delete_by_resno.do?resno=1
 // 파일 삭제 -> 레코드 삭제
 @RequestMapping(value = "/rescontents/delete_by_resno.do", method = RequestMethod.GET)
 public String delete_by_resno(int resno) {
   ArrayList<RescontentsVO> list = this.rescontentsProc.list_by_resno(resno);
   
   for(RescontentsVO rescontentsVO : list) {
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
   
   int cnt = this.rescontentsProc.delete_by_resno(resno);
   System.out.println("-> count: " + cnt);
   
   return "";
 
 }
 
 /**
  * Gallery 전체 이미지 출력
  * http://localhost:9091/rescontents/list_all_gallery.do
  * @return
  */
 @RequestMapping(value="/rescontents/list_all_gallery.do", method = RequestMethod.GET)
 public ModelAndView list_all_gallery(HttpSession session) {
   ModelAndView mav = new ModelAndView();
   
   if (this.managerProc.isManager(session) == true) {
     mav.setViewName("/rescontents/list_all_gallery"); // /WEB-INF/views/rescontents/list_all_gallery.jsp
     
     ArrayList<RescontentsVO> list = this.rescontentsProc.list_all();
     mav.addObject("list", list);
     
   } else {
     mav.setViewName("/manager/login_need"); // /WEB-INF/views/admin/login_need.jsp
     
   }
   
   return mav;
 }
 
}