package dev.mvc.withdrawalLog;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.withdrawalLog.WithdrawalLogProc")
public class WithdrawalLogProc implements WithdrawalLogProcInter {
  @Autowired
  private WithdrawalLogDAOInter withdrawalLogDAO;
  
  public WithdrawalLogProc(){
    System.out.println("-> WithdrawalLogProc created.");
  }
  @Override
  public int loginsert(int customerno) {
    int cnt = this.withdrawalLogDAO.loginsert(customerno);
    return cnt;
  }
  @Override
  public ArrayList<JoinLogVO> log_list() {
    ArrayList<JoinLogVO> list = this.withdrawalLogDAO.log_list();
    return list;
  }

}
