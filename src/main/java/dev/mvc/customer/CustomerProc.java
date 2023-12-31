package dev.mvc.customer;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.customer.CustomerProc")
public class CustomerProc implements CustomerProcInter {
  @Autowired
  private CustomerDAOInter customerDAO;
  
  public CustomerProc(){
    // System.out.println("-> CustomerProc created.");
  }

  @Override
  public int checkID(String id) {
    int cnt = this.customerDAO.checkID(id);
    return cnt;
  }

  @Override
  public int create(CustomerVO customerVO) {
    int cnt = this.customerDAO.create(customerVO);
    return cnt;
  }
 
  @Override
  public ArrayList<CustomerVO> list() {
    ArrayList<CustomerVO> list = this.customerDAO.list();
    return list;
  }
  
  @Override
  public CustomerVO read(int customerno) {
    CustomerVO customerVO = this.customerDAO.read(customerno);
    return customerVO;
  }

  @Override
  public CustomerVO readById(String id) {
    CustomerVO customerVO = this.customerDAO.readById(id);
    return customerVO;
  }

@Override
  public boolean isCustomer(HttpSession session){
    boolean sw = false; // 로그인하지 않은 것으로 초기화
    int grade = 99;
    
    // System.out.println("-> grade: " + session.getAttribute("grade"));
    if (session != null) {
      String id = (String)session.getAttribute("id");
      if (session.getAttribute("grade") != null) {
        grade = (int)session.getAttribute("grade");
      }
      
      if (id != null && grade <= 20){ // 관리자 + 회원
        sw = true;  // 로그인 한 경우
      }
    }
    
    return sw;
  }

  @Override
  public boolean isCustomerAdmin(HttpSession session){
    boolean sw = false; // 로그인하지 않은 것으로 초기화
    int grade = 99;
    
    // System.out.println("-> grade: " + session.getAttribute("grade"));
    if (session != null) {
      String id = (String)session.getAttribute("id");
      if (session.getAttribute("grade") != null) {
        grade = (int)session.getAttribute("grade");
      }
      
      if (id != null && grade <= 10){ // 관리자 
        sw = true;  // 로그인 한 경우
      }
    }
    
    return sw;
  }
  
  @Override
  public int update(CustomerVO customerVO) {
    int cnt = this.customerDAO.update(customerVO);
    return cnt;
  }
  
  @Override
  public int delete(int customerno) {
    int cnt = this.customerDAO.delete(customerno);
    return cnt;
  }
  
  @Override
  public int passwd_check(HashMap<String, Object> map) {
    int cnt = this.customerDAO.passwd_check(map);
    return cnt;
  }

  @Override
  public int passwd_update(HashMap<String, Object> map) {
    int cnt = this.customerDAO.passwd_update(map);
    return cnt;
  }
  
  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.customerDAO.login(map);
    return cnt;
  }

  @Override
  public int user_withdrawal(int customerno) {
    int cnt = this.customerDAO.user_withdrawal(customerno);
    return cnt;
  }

  @Override
  public String find_id(HashMap<String, Object> map) {
    String id = this.customerDAO.find_id(map);
    return id;
  }

  @Override
  public int find_pw(HashMap<String, Object> map) {
    int cnt = this.customerDAO.find_pw(map);
    return cnt;
  }

  @Override
  public int pw_reset(HashMap<String, Object> map) {
    int cnt = this.customerDAO.pw_reset(map);
    return cnt;
  }
}
