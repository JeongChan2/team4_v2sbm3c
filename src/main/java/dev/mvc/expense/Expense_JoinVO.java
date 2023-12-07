package dev.mvc.expense;

public class Expense_JoinVO {
//  SELECT e.expenseno, e.name, e.cnt, e.price, e.rdate, s.name AS supplier_name, r.resname
//  FROM expense e
//  INNER JOIN restaurant r ON e.resno = r.resno
//  INNER JOIN supplier s ON e.supplierno = s.supplierno
//  WHERE e.managerno = #{managerno}
//  ORDER BY expenseno ASC
  
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
  /** 업체이름 */
  private String supplier_name;
  /** 식당이름 */
  private String resname;
  
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
  public String getSupplier_name() {
    return supplier_name;
  }
  public void setSupplier_name(String supplier_name) {
    this.supplier_name = supplier_name;
  }
  public String getResname() {
    return resname;
  }
  public void setResname(String resname) {
    this.resname = resname;
  }
  
}
