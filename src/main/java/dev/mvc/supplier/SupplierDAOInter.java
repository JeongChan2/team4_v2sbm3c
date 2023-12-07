package dev.mvc.supplier;

import java.util.ArrayList;

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
}
