package dev.mvc.res;

import java.util.ArrayList;

public interface ResProcInter {
  /**
   * 등록
   * @param resVO
   * @return 등록한 레코드 개수
   */
  public int create(ResVO resVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<ResVO> list_all();
  
  /**
   * 조회
   * @param resno
   * @return
   */
  public ResVO read(int resno);
  
  /**
   * 수정
   * @param ResVO
   * @return
   */
  public int update(ResVO resVO);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete(int resno);
  
  /**
   * 우선 순위 높임, 10등->1등   
   * @param resno
   * @return 수정된 레코드 갯수
   */
  public int update_seqno_forward(int resno);

  /**
   * 우선 순위 낮춤, 1등->10등   
   * @param resno
   * @return 수정된 레코드 갯수
   */
  public int update_seqno_backward(int resno);
  
  /**
   * 카테고리 공개 설정    
   * @param cateno
   * @return 수정된 레코드 갯수
   */
  public int update_visible_y(int resno);
  
  /**
   * 카테고리 비공개 설정  
   * @param cateno
   * @return 수정된 레코드 갯수
   */
  public int update_visible_n(int resno);

  /**
   * 비회원/회원 SELECTE LIST
   * @return
   */
  public ArrayList<ResVO> list_all_y();
}
