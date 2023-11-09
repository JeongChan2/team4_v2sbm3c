package dev.mvc.take;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.take.TakeProc")
public class TakeProc implements TakeProcInter {
  @Autowired
  private TakeDAOInter takeDAO;
  
  public TakeProc() {
    System.out.println("-> TakeProc created.");
  }
  
  @Override
  public int check(int customerno) {
    int cnt = this.takeDAO.check(customerno);
    return cnt;
  }
  
  @Override
  public int create(int customerno) {
    int cnt = this.takeDAO.create(customerno);
    return cnt;
  }

  @Override
  public TakeVO read(int customerno) {
    TakeVO takeVO = this.takeDAO.read(customerno);
    return takeVO;
  }

  @Override
  public int update(TakeVO takeVO) {
    int cnt = this.takeDAO.update(takeVO);
    return cnt;
  }

  
}
