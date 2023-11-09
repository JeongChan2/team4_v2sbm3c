package dev.mvc.take;

public interface TakeDAOInter {
  /**
   * 이미 영양정보가 있는지 확인
   * @param id
   * @return 1이면 이미 있는 것
   */
  public int check(int customerno);
  
  /**
   * 회원에 맞는 영양 기준치 생성
   * @param requirementVO
   * @return 추가한 레코드 갯수
   */
  public int create(int customerno);
  
  /**
   * requirementno로 회원 영양 정보 조회
   * @param requirementno
   * @return
   */
  public TakeVO read(int customerno);
  
  /**
   * 수정 처리
   * @param requirementVO
   * @return
   */
  public int update(TakeVO takeVO);
}
