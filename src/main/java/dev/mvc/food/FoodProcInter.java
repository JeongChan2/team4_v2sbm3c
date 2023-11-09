package dev.mvc.food;

import java.util.ArrayList;

public interface FoodProcInter {

  /**
   * 음식 정보 생성
   * @param FoodVO
   * @return 추가한 레코드 갯수
   */
  public int create(FoodVO foodVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<FoodVO> list_all();
  
  /**
   * 조회
   * @param foodno
   * @return
   */
  public FoodVO read(int foodno);
  
  /**
   * 수정
   * @param ResVO
   * @return
   */
  public int update(FoodVO foodVO);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete(int foodno);
}
