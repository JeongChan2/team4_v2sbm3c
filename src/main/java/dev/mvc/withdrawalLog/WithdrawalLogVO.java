package dev.mvc.withdrawalLog;

public class WithdrawalLogVO {
//  CREATE TABLE WithdrawalLog(
//      logno                    NUMBER(10) NOT NULL PRIMARY KEY,
//      customerno                NUMBER(10) NOT NULL,
//      logdate                  DATE NOT NULL,
//      FOREIGN KEY (customerno) REFERENCES n_customer (customerno)
//    );
  /** 회원 번호 */
  private int logno;
  /** 회원 번호 */
  private int customerno;
  /** 가입일 */
  private String logdate = "";
  
  
  public int getLogno() {
    return logno;
  }
  public void setLogno(int logno) {
    this.logno = logno;
  }
  public int getCustomerno() {
    return customerno;
  }
  public void setCustomerno(int customerno) {
    this.customerno = customerno;
  }
  public String getLogdate() {
    return logdate;
  }
  public void setLogdate(String logdate) {
    this.logdate = logdate;
  }
}
