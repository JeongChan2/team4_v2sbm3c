package dev.mvc.expense;

import java.util.ArrayList;

import dev.mvc.food.FoodVO;

public interface ExpenseDAOInter {
  /**
   * 지출내역 정보 생성
   * @param ExpenseVO
   * @return 추가한 레코드 갯수
   */
  public int create(ExpenseVO expenseVO);
  
  /**
   * 매니저별 전체 목록
   * @return
   */
  public ArrayList<ExpenseVO> list_all_managerno(int managerno);
  
  /**
   * 조회
   * @param expenseno
   * @return
   */
  public ExpenseVO read(int expenseno);
  
  /**
   * 수정
   * @param ExpenseVO
   * @return
   */
  public int update(ExpenseVO expenseVO);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete(int expenseno);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete_resno(int resno);
  
  /**
   * 매니저별 전체 목록, 식당이름과 공급체이름까지 같이 가져옴
   * @return
   */
  public ArrayList<Expense_JoinVO> list_all_names(int managerno);
}
