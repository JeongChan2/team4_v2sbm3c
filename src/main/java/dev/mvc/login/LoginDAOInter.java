package dev.mvc.login;

import java.util.HashMap;

public interface LoginDAOInter {
  
  /**
   * 로그인 내역 저장
   * @param map
   * @return 
   */
  public int login_cookie_proc(HashMap<String, Object> map);
}
