package dev.mvc.food;

public class FoodVO {
//  CREATE TABLE food (
//      foodno NUMBER(10) NOT NULL, -- 번호, 레코드를 구분하는 컬럼 
//      foodname VARCHAR(20) NOT NULL,            -- 음식 이름
//      f_calories   VARCHAR(10)    NOT NULL,    -- 칼로리 (food) kcal
//      f_carbohydrates VARCHAR(10)    NOT NULL, -- 탄수화물 (food) g(그램)
//      f_protein    VARCHAR(10)    NOT NULL,    -- 단백질  (food) g(그램)
//      f_fat        VARCHAR(10)    NOT NULL,    -- 지방  (food) g(그램)
//      PRIMARY KEY (foodno)
//    );
  /** 번호 */
  private int foodno;
  /** 음식 이름 */
  private String foodname;
  /** 칼로리 */
  private String f_calories;
  /** 탄수화물 */
  private String f_carbohydrates;
  /** 단백질 */
  private String f_protein;
  /** 지방 */
  private String f_fat;
  
  public int getFoodno() {
    return foodno;
  }
  public void setFoodno(int foodno) {
    this.foodno = foodno;
  }
  public String getFoodname() {
    return foodname;
  }
  public void setFoodname(String foodname) {
    this.foodname = foodname;
  }
  public String getF_calories() {
    return f_calories;
  }
  public void setF_calories(String f_calories) {
    this.f_calories = f_calories;
  }
  public String getF_carbohydrates() {
    return f_carbohydrates;
  }
  public void setF_carbohydrates(String f_carbohydrates) {
    this.f_carbohydrates = f_carbohydrates;
  }
  public String getF_protein() {
    return f_protein;
  }
  public void setF_protein(String f_protein) {
    this.f_protein = f_protein;
  }
  public String getF_fat() {
    return f_fat;
  }
  public void setF_fat(String f_fat) {
    this.f_fat = f_fat;
  }
  
}
