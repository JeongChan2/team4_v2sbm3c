package dev.mvc.reservation;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReservationProcInter {
  /**
   * 회원 예약 정보 생성
   * @param ExpenseVO
   * @return 추가한 레코드 갯수
   */
  public int create(ReservationVO reservationVO);
  
  /**
   * 비회원 예약 정보 생성
   * @param ReservationVO
   * @return 추가한 레코드 갯수
   */
  public int non_mem_create(ReservationVO reservationVO);
  
  /**
   * 매니저별 전체 목록
   * @return
   */
  public ArrayList<ReservationVO> list_all_managerno(int managerno);
  
  /**
   * 고객별 전체 목록
   * @return
   */
  public ArrayList<ReservationVO> list_all_customerno(int customerno);
  
  /**
   * 조회
   * @param reserv_no
   * @return
   */
  public ReservationVO read(int reserv_no);
  
  /**
   * 수정
   * @param ReservationVO
   * @return
   */
  public int update(ReservationVO reservationVO);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete(int reserv_no);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   */
  public int delete_resno(int resno);
  
  /**
   * 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 매니저별 검색 목록 + 페이징
   * @param ReservationVO
   * @return
   */
  public ArrayList<ReservationVO> list_by_managerno_search_paging(ReservationVO reservationVO);
  
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
