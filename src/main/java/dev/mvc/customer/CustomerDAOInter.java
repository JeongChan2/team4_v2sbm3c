package dev.mvc.customer;

import java.util.ArrayList;
import java.util.HashMap;

public interface CustomerDAOInter {
  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkID(String id);
  
  /**
   * 회원 가입
   * @param memberVO
   * @return 추가한 레코드 갯수
   */
  public int create(CustomerVO memberVO);

  /**
   * 회원 전체 목록
   * @return
   */
  public ArrayList<CustomerVO> list();

  /**
   * memberno로 회원 정보 조회
   * @param memberno
   * @return
   */
  public CustomerVO read(int customerno);
  
  /**
   * id로 회원 정보 조회
   * @param id
   * @return
   */
  public CustomerVO readById(String id);
 
  /**
   * 수정 처리
   * @param memberVO
   * @return
   */
  public int update(CustomerVO customerVO);
  
  /**
   * update와 delete는 모두 처리된 레코드 갯수를 리턴한다.
   * 회원 삭제 처리
   * @param memberno
   * @return
   */
  public int delete(int customerno);
  
  /**
   * 현재 패스워드 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int passwd_check(HashMap<String, Object> map);
  
  /**
   * 패스워드 변경
   * @param map
   * @return 변경된 패스워드 갯수
   */
  public int passwd_update(HashMap<String, Object> map);
  
  /**
   * 로그인 처리
   */
  public int login(HashMap<String, Object> map);
  
  /**
   * 회원 탈퇴
   * @param customerno
   * @return
   */
  public int user_withdrawal(int customerno);
  
  /**
   * 아이디 찾기
   * @param map
   * @return
   */
  public String find_id(HashMap<String, Object> map);
  
  /**
   * 비밀번호 초기화
   * @param map
   * @return
   */
  public int find_pw(HashMap<String, Object> map);
  
  /**
   * 비번 초기화
   * @param id
   * @return 
   */
  public int pw_reset(HashMap<String, Object> map);
}
