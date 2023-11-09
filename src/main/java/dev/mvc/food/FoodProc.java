package dev.mvc.food;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.food.FoodProc")
public class FoodProc implements FoodProcInter {
  @Autowired
  private FoodDAOInter foodDAO;
  
  public FoodProc() {
    System.out.println("-> FoodProc created.");
  }
  
  @Override
  public int create(FoodVO foodVO) {
    int cnt = this.foodDAO.create(foodVO);
    return cnt;
  }

  @Override
  public ArrayList<FoodVO> list_all() {
    ArrayList<FoodVO> list = this.foodDAO.list_all();
    return list;
  }

  @Override
  public FoodVO read(int foodno) {
    FoodVO foodVO = this.foodDAO.read(foodno);
    return foodVO;
  }
  
  @Override
  public int update(FoodVO foodVO) {
    int cnt = this.foodDAO.update(foodVO);
    return cnt;
  }

  @Override
  public int delete(int foodno) {
    int cnt = this.foodDAO.delete(foodno);
    return cnt;
  }

}
