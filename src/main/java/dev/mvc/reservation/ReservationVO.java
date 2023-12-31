package dev.mvc.reservation;

public class ReservationVO {
//  CREATE TABLE RESERVATION(
//      RESERV_NO                         NUMBER(10)         NOT NULL    PRIMARY KEY,
//      RES_NAME                          VARCHAR2(30)       NOT NULL,
//      RESERV_NAME                       VARCHAR2(30)       NOT NULL,
//      RESERV_PHONE                      VARCHAR2(14)       NOT NULL ,
//      CNT                               NUMBER(3)          NOT NULL,
//      RDATE                             DATE               NOT NULL,
//      CUSTOMERNO                        NUMBER(10)         NULL ,
//      MANAGERNO                         NUMBER(10)         NOT NULL ,
//      RESNO                             NUMBER(10)         NOT NULL,
//      FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO),
//      FOREIGN KEY (MANAGERNO) REFERENCES MANAGER (MANAGERNO),
//      FOREIGN KEY (RESNO) REFERENCES RESTAURANT (RESNO)
//  );
  
  /** 번호 */
  private int reserv_no;
  /** 식당 이름 */
  private String res_name;
  /** 예약자 이름 */
  private String reserv_name;
  /** 예약자 핸드폰 번호 */
  private String reserv_phone;
  /** 인원 */
  private int cnt;
  /** 예약날짜 */
  private String rdate;
  /** 고객번호 FK */
  private int customerno;
  /** 관리자번호 FK */
  private int managerno;
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
  
  public int getReserv_no() {
    return reserv_no;
  }
  public void setReserv_no(int reserv_no) {
    this.reserv_no = reserv_no;
  }
  public String getRes_name() {
    return res_name;
  }
  public void setRes_name(String res_name) {
    this.res_name = res_name;
  }
  public String getReserv_name() {
    return reserv_name;
  }
  public void setReserv_name(String reserv_name) {
    this.reserv_name = reserv_name;
  }
  public String getReserv_phone() {
    return reserv_phone;
  }
  public void setReserv_phone(String reserv_phone) {
    this.reserv_phone = reserv_phone;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getCustomerno() {
    return customerno;
  }
  public void setCustomerno(int customerno) {
    this.customerno = customerno;
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
