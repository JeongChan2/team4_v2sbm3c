package dev.mvc.res;

//CREATE TABLE RESTAURANT(
//    RESNO                             NUMBER(10)     NOT NULL    PRIMARY KEY,
//    RESNAME                           VARCHAR2(30)     NOT NULL,
//    RESADDRESS                        VARCHAR2(50)     NOT NULL,
//    RESPHONE                          VARCHAR2(20)     NOT NULL,
//    RESTIME                           VARCHAR2(20)     NOT NULL,
//    RESSTAR                           NUMBER(10)     NOT NULL
//    SEQNO                             NUMBER(5)   DEFAULT 1   NOT NULL
//    VISIBLE                           CHAR(1)         DEFAULT 'N' NOT NULL
//);

public class ResVO {
  private int resno;
  private String resname;
  private String resaddress;
  private String resphone;
  private String restime;
  private int  resstar;
  private int seqno;
  private String visible;
  
  
  public int getResno() {
    return resno;
  }
  public void setResno(int resno) {
    this.resno = resno;
  }
  public String getResname() {
    return resname;
  }
  public void setResname(String resname) {
    this.resname = resname;
  }
  public String getResaddress() {
    return resaddress;
  }
  public void setResaddress(String resaddress) {
    this.resaddress = resaddress;
  }
  public String getResphone() {
    return resphone;
  }
  public void setResphone(String resphone) {
    this.resphone = resphone;
  }
  public String getRestime() {
    return restime;
  }
  public void setRestime(String restime) {
    this.restime = restime;
  }
  public int getResstar() {
    return resstar;
  }
  public void setResstar(int resstar) {
    this.resstar = resstar;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getVisible() {
    return visible;
  }
  public void setVisible(String visible) {
    this.visible = visible;
  }
  
  @Override
  public String toString() {
    return "ResVO [resno=" + resno + ", resname=" + resname + ", resaddress=" + resaddress + ", resphone=" + resphone
        + ", restime=" + restime + ", resstar=" + resstar + ", seqno=" + seqno + ", visible=" + visible + "]";
  }
  
  
}
