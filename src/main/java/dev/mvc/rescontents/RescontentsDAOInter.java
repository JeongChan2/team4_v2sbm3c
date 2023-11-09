package dev.mvc.rescontents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface RescontentsDAOInter {
  /**
   * 등록, 추상 메소드
   * @param contentsVO
   * @return
   */
  public int create(RescontentsVO rescontentsVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<RescontentsVO> list_all();

  /**
   * 전체 목록
   * @return
   */
  public ArrayList<JoinVO> food_list_all();
  
  /**
   * 식당 카테고리별 등록한 글 목록
   * @param rescontentsno
   * @return
   */
  public ArrayList<RescontentsVO> list_by_resno(int rescontentsno);
  
  /**
   * 조회
   * @param rescontentno
   * @return
   */
  public RescontentsVO read(int rescontentsno);
  
  /**
   * map 등록, 수정, 삭제
   * @param map
   * @return 수정된 레코드 갯수
   */
  public int map(HashMap<String, Object> map);
  
  /**
   * youtube 등록, 수정, 삭제
   * @param youtube
   * @return 수정된 레코드 갯수
   */
  public int youtube(HashMap<String, Object> map);
  
  /**
   * 카테고리별 검색 목록
   * @param map
   * @return
   */
  public ArrayList<RescontentsVO> list_by_resno_search(HashMap<String, Object> map);
  
  /**
   * 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 카테고리별 검색 목록 + 페이징
   * @param rescontentsVO
   * @return
   */
  public ArrayList<RescontentsVO> list_by_resno_search_paging(RescontentsVO rescontentsVO);
  
  /**
   * 패스워드 검사
   * @param hashMap
   * @return
   */
  public int password_check(HashMap<String, Object> hashMap);
  
  /**
   * 글 정보 수정
   * @param rescontentsVO
   * @return 처리된 레코드 갯수
   */
  public int update_text(RescontentsVO rescontentsVO);
  
  /**
   * 파일 정보 수정
   * @param rescontentsVO
   * @return 처리된 레코드 갯수
   */
  public int update_file(RescontentsVO rescontentsVO);
  
  /**
   * 삭제
   * @param rescontentsno
   * @return 삭제된 레코드 갯수
   */
  public int delete(int rescontentsno);

  /**
   * FK resno 값이 같은 레코드 갯수 산출
   * @param resno
   * @return
   */
  public int count_by_resno(int resno);
  
  /**
   * 특정 카테고리에 속한 모든 레코드 삭제
   * @param resno
   * @return 삭제된 레코드 갯수
   */
  public int delete_by_resno(int resno);
}
