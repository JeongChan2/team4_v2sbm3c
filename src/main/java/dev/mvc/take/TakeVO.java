package dev.mvc.take;

public class TakeVO {
//  CREATE TABLE n_take (
//      takeno NUMBER(10) NOT NULL     PRIMARY KEY, -- 번호, 레코드를 구분하는 컬럼 
//      customerno NUMBER(10) NOT NULL, -- 회원 번호, 레코드를 구분하는 컬럼 
//      calories   VARCHAR(10)    NOT NULL,    -- 칼로리 (n_take) kcal
//      carbohydrates VARCHAR(10)    NOT NULL, -- 탄수화물 (n_take) g(그램)
//      protein    VARCHAR(10)    NOT NULL,    -- 단백질  (n_take) g(그램)
//      fat        VARCHAR(10)    NOT NULL,    -- 지방  (n_take) g(그램)
//      ndate       DATE             NOT NULL, -- 날짜, 하루별로 기록
//      FOREIGN KEY (customerno) REFERENCES n_customer (customerno)
//    );
  /** 번호 */
  private int takeno;
  /** 회원 번호 */
  private int customerno;
  /** 칼로리 */
  private String calories;
  /** 탄수화물 */
  private String carbohydrates;
  /** 단백질 */
  private String protein;
  /** 지방 */
  private String fat;
  /** 날짜 */
  private String ndate;
  
  public int getTakeno() {
    return takeno;
  }
  public void setTakeno(int takeno) {
    this.takeno = takeno;
  }
  public int getCustomerno() {
    return customerno;
  }
  public void setCustomerno(int customerno) {
    this.customerno = customerno;
  }
  public String getCalories() {
    return calories;
  }
  public void setCalories(String calories) {
    this.calories = calories;
  }
  public String getCarbohydrates() {
    return carbohydrates;
  }
  public void setCarbohydrates(String carbohydrates) {
    this.carbohydrates = carbohydrates;
  }
  public String getProtein() {
    return protein;
  }
  public void setProtein(String protein) {
    this.protein = protein;
  }
  public String getFat() {
    return fat;
  }
  public void setFat(String fat) {
    this.fat = fat;
  }
  public String getNdate() {
    return ndate;
  }
  public void setNdate(String ndate) {
    this.ndate = ndate;
  }
  @Override
  public String toString() {
    return "TakeVO [takeno=" + takeno + ", customerno=" + customerno + ", calories=" + calories + ", carbohydrates="
        + carbohydrates + ", protein=" + protein + ", fat=" + fat + ", ndate=" + ndate + "]";
  }
  
}
