package dev.mvc.supplier;

public class SupplierVO {
//  CREATE TABLE SUPPLIER(
//      SUPPLIERNO                        NUMBER(10)     NOT NULL   PRIMARY KEY,
//      NAME                              VARCHAR2(50)     NOT NULL,
//      RDATE                             DATE     NOT NULL,
//      MANAGERNO                         NUMBER(10)     NULL,
//  
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
  
  
}
