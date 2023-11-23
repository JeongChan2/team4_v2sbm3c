package dev.mvc.login;

import java.util.HashMap;

import org.springframework.stereotype.Component;


@Component("dev.mvc.login.LoginProc")
public class LoginProc implements LoginProcInter {
  private LoginDAOInter loginDAO;

  @Override
  public int login_cookie_proc(HashMap<String, Object> map) {
    int cnt = this.loginDAO.login_cookie_proc(map);
    return cnt;
  }


}
