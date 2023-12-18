package dev.mvc.sell;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.sell.SellProc")
public class SellProc implements SellProcInter {
  @Autowired
  private SellDAOInter sellDAO;
  
  public SellProc() {
    System.out.println("-> SellProc created.");
  }
  
  @Override
  public int create(SellVO sellVO) {
    int cnt = this.sellDAO.create(sellVO);
    return cnt;
  }

  @Override
  public ArrayList<SellVO> list_all_managerno(int managerno) {
    ArrayList<SellVO> list = this.sellDAO.list_all_managerno(managerno);
    return list;
  }

  @Override
  public SellVO read(int sellno) {
    SellVO sellVO = this.sellDAO.read(sellno);
    return sellVO;
  }
  
  @Override
  public int update(SellVO sellVO) {
    int cnt = this.sellDAO.update(sellVO);
    return cnt;
  }

  @Override
  public int delete(int sellno) {
    int cnt = this.sellDAO.delete(sellno);
    return cnt;
  }
  
  @Override
  public ArrayList<Sell_JoinVO> list_all_names(int managerno) {
    ArrayList<Sell_JoinVO> list = this.sellDAO.list_all_names(managerno);
    return list;
  }
}
