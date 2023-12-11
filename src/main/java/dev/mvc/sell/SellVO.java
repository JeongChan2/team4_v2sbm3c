package dev.mvc.sell;

public class SellVO {
//  CREATE TABLE SELL(
//      SELLNO                        NUMBER(10)    NOT NULL    PRIMARY KEY,
//      NAME                              VARCHAR2(50)  NOT NULL,
//      CNT                               NUMBER(5)   DEFAULT 0   NOT NULL,
//      PRICE                             NUMBER(10)    NOT NULL,
//      RDATE                             DATE        NOT NULL,
//      MANAGERNO                       NUMBER(10)      NOT NULL,
//      FOODNO                            NUMBER(10)    NOT NULL,
//      RESNO                             NUMBER(10)    NOT NULL,
//      FOREIGN KEY (resno) REFERENCES restaurant (resno),
//      FOREIGN KEY (managerno) REFERENCES manager (managerno),
//      FOREIGN KEY (FOODNO) REFERENCES food (FOODNO)
//  );
  
  /** 번호 */
  private int sellno;
  /** 이름 */
  private String name;
  /** 개수 */
  private int cnt;
  /** 판매금액 */
  private int price;
  /** 지출날짜 */
  private String rdate;
  /** 관리자번호 FK */
  private int managerno;
  /** 음식번호 */
  private int foodno;
  /** 식당번호 FK */
  private int resno;
  
  public int getSellno() {
    return sellno;
  }
  
  public void setSellno(int sellno) {
    this.sellno = sellno;
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
  public int getManagerno() {
    return managerno;
  }
  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }
  public int getFoodno() {
    return foodno;
  }
  public void setFoodno(int foodno) {
    this.foodno = foodno;
  }
  public int getResno() {
    return resno;
  }
  public void setResno(int resno) {
    this.resno = resno;
  }
  
}
