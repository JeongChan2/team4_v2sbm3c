package dev.mvc.reservation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.reservation.ReservationProc")
public class ReservationProc implements ReservationProcInter {
  @Autowired
  private ReservationDAOInter reservationDAO;
  
  public ReservationProc() {
    System.out.println("-> ReservationProc created.");
  }
  
  @Override
  public int create(ReservationVO reservationVO) {
    int cnt = this.reservationDAO.create(reservationVO);
    return cnt;
  }
  
  @Override
  public int non_mem_create(ReservationVO reservationVO) {
    int cnt = this.reservationDAO.non_mem_create(reservationVO);
    return cnt;
  }

  @Override
  public ArrayList<ReservationVO> list_all_managerno(int managerno) {
    ArrayList<ReservationVO> list = this.reservationDAO.list_all_managerno(managerno);
    return list;
  }
  
  @Override
  public ArrayList<ReservationVO> list_all_customerno(int customerno) {
    ArrayList<ReservationVO> list = this.reservationDAO.list_all_customerno(customerno);
    return list;
  }

  @Override
  public ReservationVO read(int reserv_no) {
    ReservationVO reservationVO = this.reservationDAO.read(reserv_no);
    return reservationVO;
  }
  
  @Override
  public int update(ReservationVO reservationVO) {
    int cnt = this.reservationDAO.update(reservationVO);
    return cnt;
  }

  @Override
  public int delete(int reserv_no) {
    int cnt = this.reservationDAO.delete(reserv_no);
    return cnt;
  }

    @Override
    public int delete_resno(int resno) {
        int cnt = this.reservationDAO.delete_resno(resno);
        return cnt;
    }
  
}
