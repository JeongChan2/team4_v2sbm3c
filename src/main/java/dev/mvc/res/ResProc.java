package dev.mvc.res;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("dev.mvc.res.ResProc")
public class ResProc implements ResProcInter {
  @Autowired
  private ResDAOInter resDAO;

  @Override
  public int create(ResVO resVO) {
    int cnt = this.resDAO.create(resVO);
    
    return cnt;
  }

  @Override
  public ArrayList<ResVO> list_all() {
    ArrayList<ResVO> list = this.resDAO.list_all();
    
    return list;
  }

  @Override
  public ResVO read(int resno) {
    ResVO resVO = this.resDAO.read(resno);
    
    return resVO;
  }

  @Override
  public int update(ResVO resVO) {
    int cnt = this.resDAO.update(resVO);

    return cnt;
  }

  @Override
  public int delete(int resno) {
    int cnt = this.resDAO.delete(resno);

    return cnt;
  }

  @Override
  public int update_seqno_forward(int resno) {
    int cnt = this.resDAO.update_seqno_forward(resno);
    
    return cnt;
  }

  @Override
  public int update_seqno_backward(int resno) {
    int cnt = this.resDAO.update_seqno_backward(resno);
    
    return cnt;
  }

  @Override
  public int update_visible_y(int resno) {
    int cnt = this.resDAO.update_visible_y(resno);

    return cnt;
  }

  @Override
  public int update_visible_n(int resno) {
    int cnt = this.resDAO.update_visible_n(resno);

    return cnt;
  }

  @Override
  public ArrayList<ResVO> list_all_y() {
    ArrayList<ResVO> list = this.resDAO.list_all_y();

    return list;
  }
}
