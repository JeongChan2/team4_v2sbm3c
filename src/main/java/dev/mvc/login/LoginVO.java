package dev.mvc.login;

public class LoginVO {
//  CREATE TABLE login(
//      loginno                    NUMBER(10) NOT NULL PRIMARY KEY,
//      customerno                NUMBER(10) NOT NULL,
//      ip                            VARCHAR2(15) NOT NULL,
//      logindate                  DATE NOT NULL,
//      FOREIGN KEY (customerno) REFERENCES n_customer (customerno)
//    );
//  
  
  /** 로그인 번호 */
  private int loginno;
  /** 회원 번호 */
  private int customerno;
  /** 접속 IP */
  private String ip;
  /** 로그인 날짜 */
  private String logindate;
  
  
  public int getLoginno() {
    return loginno;
  }
  public void setLoginno(int loginno) {
    this.loginno = loginno;
  }
  public int getCustomerno() {
    return customerno;
  }
  public void setCustomerno(int customerno) {
    this.customerno = customerno;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public String getLogindate() {
    return logindate;
  }
  public void setLogindate(String logindate) {
    this.logindate = logindate;
  }
  
  
}
