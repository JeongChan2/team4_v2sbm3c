package dev.mvc.manager;

public interface ManagerDAOInter {
  /**
   * 로그인
   * @param ManagerVO
   * @return
   */
  public int login(ManagerVO managerVO);
  
  /**
   * 관리자 정보
   * @param String
   * @return
   */
  public ManagerVO read_by_id(String id);
  
  /**
   * 조회
   * @param managerno
   * @return
   */
  public  ManagerVO read(int managerno);
}