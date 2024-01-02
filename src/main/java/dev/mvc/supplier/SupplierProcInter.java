package dev.mvc.supplier;

import java.util.ArrayList;
import java.util.HashMap;

public interface SupplierProcInter {

  /**
   * 공급 업체 정보 생성
   * @param SupplierVO
   * @return 추가한 레코드 갯수
   */
  public int create(SupplierVO supplierVO);
  
  /**
   * 관리자별 전체 목록
   * @param managerno
   * @return
   */
  public ArrayList<SupplierVO> list_all_managerno(int managerno);
  
  /**
   * 조회
   * @param supplierno
   * @return
   */
  public SupplierVO read(int supplierno);
  
  /**
   * 삭제
   * @param 삭제할 레코드 PK 번호
   * @return 삭제한 레코드 갯수
   */
  public int delete(int supplierno);
  
  /**
   * 수정
   * @param SupplierVO
   * @return
   */
  public int update(SupplierVO supplierVO);
  
  /**
   * 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 매니저별 검색 목록 + 페이징
   * @param SupplierVO
   * @return
   */
  public ArrayList<SupplierVO> list_by_managerno_search_paging(SupplierVO supplierVO);
  
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
