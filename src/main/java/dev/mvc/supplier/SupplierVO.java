package dev.mvc.supplier;

public class SupplierVO {
//  CREATE TABLE SUPPLIER(
//      SUPPLIERNO                        NUMBER(10)    NOT NULL   PRIMARY KEY,
//      NAME                              VARCHAR2(50)  NOT NULL,
//      RDATE                             DATE          NOT NULL,
//      MANAGERNO                         NUMBER(10)    NOT NULL,
//      FOREIGN KEY (MANAGERNO) REFERENCES manager (MANAGERNO)
//  );
  /** 번호 */
  private int supplierno;
  /** 공급체 이름 */
  private String name;
  /** 날짜 */
  private String rdate;
  /** 관리자번호 */
  private int managerno;
  
  
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
  
  public int getSupplierno() {
    return supplierno;
  }
  public void setSupplierno(int supplierno) {
    this.supplierno = supplierno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
