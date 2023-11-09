package dev.mvc.requirement;

public class RequirementVO {
//  CREATE TABLE n_requirement (
//      requirementno NUMBER(10) NOT NULL     PRIMARY KEY, -- 번호, 레코드를 구분하는 컬럼 
//      customerno NUMBER(10) NOT NULL, -- 회원 번호, 레코드를 구분하는 컬럼 
//      calories   NUMBER(10)    NOT NULL,    -- 칼로리 (n_requirement) kcal
//      carbohydrates NUMBER(10)    NOT NULL, -- 탄수화물 (n_requirement) g(그램)
//      protein    NUMBER(10)    NOT NULL,    -- 단백질  (n_requirement) g(그램)
//      fat        NUMBER(10)    NOT NULL,    -- 지방  (n_requirement) g(그램)
//      FOREIGN KEY (customerno) REFERENCES n_customer (customerno)
//    );
  /** 번호 */
  private int requirementno;
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
  
  
  public int getRequirementno() {
    return requirementno;
  }
  public void setRequirementno(int requirementno) {
    this.requirementno = requirementno;
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
}
