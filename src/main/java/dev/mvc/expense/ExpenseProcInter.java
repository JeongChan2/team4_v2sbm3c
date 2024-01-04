package dev.mvc.expense;

import java.util.ArrayList;
import java.util.HashMap;

public interface ExpenseProcInter {
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
  
  /**
   * 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 매니저별 검색 목록 + 페이징
   * @param Expense_JoinVO
   * @return
   */
  public ArrayList<Expense_JoinVO> list_by_managerno_search_paging(Expense_JoinVO expense_JoinVO);
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param managerno          매니저번호 
   * @param now_page      현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명 
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int managerno, int now_page, String word, String list_file);
}
