package dev.mvc.sell;

public class Sell_JoinVO {
//  SELECT s.sellno, s.name, s.cnt, s.price, s.rdate, r.resname
//  FROM sell s
//  INNER JOIN restaurant r ON s.resno = r.resno
//  WHERE s.managerno = #{managerno}
//  ORDER BY sellno ASC
  
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
  /** 식당이름 */
  private String resname;
  
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
  public String getResname() {
    return resname;
  }
  public void setResname(String resname) {
    this.resname = resname;
  }
  
}
