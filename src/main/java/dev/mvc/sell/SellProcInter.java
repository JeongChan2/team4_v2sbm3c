package dev.mvc.sell;

import java.util.ArrayList;
import java.util.HashMap;

public interface SellProcInter {
  /**
   * 판매내역 정보 생성
   * @param SellVO
   * @return 추가한 레코드 갯수
   */
  public int create(SellVO sellVO);
  
  /**
   * 매니저별 전체 목록
   * @return
   */
  public ArrayList<SellVO> list_all_managerno(int managerno);
  
  /**
   * 조회
   * @param sellno
   * @return
   */
  public SellVO read(int sellno);
  
  /**
   * 수정
   * @param SellVO
   * @return
   */
  public int update(SellVO sellVO);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete(int sellno);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete_resno(int resno);
  
  /**
   * 매니저별 전체 목록, 식당이름 같이 가져옴
   * @return
   */
  public ArrayList<Sell_JoinVO> list_all_names(int managerno);
  
  /**
   * 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 매니저별 검색 목록 + 페이징
   * @param Sell_JoinVO
   * @return
   */
  public ArrayList<Sell_JoinVO> list_by_managerno_search_paging(Sell_JoinVO sell_JoinVO);
  
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
