package dev.mvc.withdrawalLog;

import java.util.ArrayList;

import dev.mvc.customer.CustomerVO;
import dev.mvc.login.LoginVO;

public interface WithdrawalLogDAOInter {
  
  /**
   * 탈퇴 로그
   * @param customerno
   * @return
   */
  public int loginsert(int customerno);
  
  /**
   * 로그 내역
   * @return
   */
  public ArrayList<JoinLogVO> log_list();
}
