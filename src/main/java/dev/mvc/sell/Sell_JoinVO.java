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
  
  /** 관리자번호 FK */
  private int managerno;
  /** 음식번호 */
  private int foodno;
  /** 식당번호 FK */
  private int resno;
  
  //페이징 관련
  // -----------------------------------------------------------------------------------
  /** 검색어 */
  private String word = "";
  /** 시작 rownum */
  private int start_num;    
  /** 종료 rownum */
  private int end_num;    
  /** 현재 페이지 */
  private int now_page = 1;
  
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
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public int getStart_num() {
    return start_num;
  }
  public void setStart_num(int start_num) {
    this.start_num = start_num;
  }
  public int getEnd_num() {
    return end_num;
  }
  public void setEnd_num(int end_num) {
    this.end_num = end_num;
  }
  public int getNow_page() {
    return now_page;
  }
  public void setNow_page(int now_page) {
    this.now_page = now_page;
  }
  
}
