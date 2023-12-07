package dev.mvc.supplier;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.supplier.SupplierProc")
public class SupplierProc implements SupplierProcInter {
  @Autowired
  private SupplierDAOInter supplierDAO;
  
  public SupplierProc() {
    System.out.println("-> SupplierProc created.");
  }
  
  @Override
  public int create(SupplierVO supplierVO) {
    int cnt = this.supplierDAO.create(supplierVO);
    return cnt;
  }
  
  @Override
  public ArrayList<SupplierVO> list_all_managerno(int managerno){
    ArrayList<SupplierVO> list = this.supplierDAO.list_all_managerno(managerno);
    return list;
  }
  
  @Override
  public SupplierVO read(int supplierno) {
    SupplierVO supplierVO = this.supplierDAO.read(supplierno);
    return supplierVO;
  }
  
  @Override
  public int delete(int supplierno) {
    int cnt = this.supplierDAO.delete(supplierno);
    return cnt;
  }
}
