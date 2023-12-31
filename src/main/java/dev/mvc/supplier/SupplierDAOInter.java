package dev.mvc.supplier;

import java.util.ArrayList;
import java.util.HashMap;

import dev.mvc.rescontents.RescontentsVO;

public interface SupplierDAOInter {
  
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
}
