package dev.mvc.expense;

public class ExpenseVO {
//  CREATE TABLE EXPENSE(
//      EXPENSENO                         NUMBER(10)      NOT NULL    PRIMARY KEY,
//      NAME                              VARCHAR2(50)    NOT NULL,
//      CNT                               NUMBER(5)       DEFAULT 0   NOT NULL,
//      PRICE                             NUMBER(10)      NOT NULL,
//      RDATE                             DATE            NOT NULL,
//      SUPPLIERNO                        NUMBER(10)      NOT NULL,
//      MANAGERNO                         NUMBER(10)      NOT NULL,
//      RESNO                             NUMBER(10)      NOT NULL,
//      FOREIGN KEY (resno) REFERENCES restaurant (resno),
//      FOREIGN KEY (managerno) REFERENCES manager (managerno),
//      FOREIGN KEY (supplierno) REFERENCES supplier (supplierno)
//  );
  
  /** 번호 */
  private int expenseno;
  /** 이름 */
  private String name;
  /** 개수 */
  private int cnt;
  /** 지출금액 */
  private int price;
  /** 지출날짜 */
  private String rdate;
  /** 업체번호 */
  private int supplierno;
  /** 관리자번호 FK */
  private int managerno;
  /** 식당번호 FK */
  private int resno;
  
  public int getExpenseno() {
    return expenseno;
  }
  public void setExpenseno(int expenseno) {
    this.expenseno = expenseno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getSupplierno() {
    return supplierno;
  }
  public void setSupplierno(int supplierno) {
    this.supplierno = supplierno;
  }
  public int getManagerno() {
    return managerno;
  }
  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }
  public int getResno() {
    return resno;
  }
  public void setResno(int resno) {
    this.resno = resno;
  }
  
}
