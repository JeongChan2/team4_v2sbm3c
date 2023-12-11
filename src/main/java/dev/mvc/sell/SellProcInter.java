package dev.mvc.sell;

import java.util.ArrayList;

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
   * 매니저별 전체 목록, 식당이름 같이 가져옴
   * @return
   */
  public ArrayList<Sell_JoinVO> list_all_names(int managerno);
}
