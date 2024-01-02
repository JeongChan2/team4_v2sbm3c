package dev.mvc.expense;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.expense.ExpenseProc")
public class ExpenseProc implements ExpenseProcInter {
  @Autowired
  private ExpenseDAOInter expenseDAO;
  
  public ExpenseProc() {
    System.out.println("-> ExpenseProc created.");
  }
  
  @Override
  public int create(ExpenseVO expenseVO) {
    int cnt = this.expenseDAO.create(expenseVO);
    return cnt;
  }

  @Override
  public ArrayList<ExpenseVO> list_all_managerno(int managerno) {
    ArrayList<ExpenseVO> list = this.expenseDAO.list_all_managerno(managerno);
    return list;
  }

  @Override
  public ExpenseVO read(int expenseno) {
    ExpenseVO expenseVO = this.expenseDAO.read(expenseno);
    return expenseVO;
  }
  
  @Override
  public int update(ExpenseVO expenseVO) {
    int cnt = this.expenseDAO.update(expenseVO);
    return cnt;
  }

  @Override
  public int delete(int expenseno) {
    int cnt = this.expenseDAO.delete(expenseno);
    return cnt;
  }
  
  @Override
  public ArrayList<Expense_JoinVO> list_all_names(int managerno) {
    ArrayList<Expense_JoinVO> list = this.expenseDAO.list_all_names(managerno);
    return list;
  }

@Override
public int delete_resno(int resno) {
    int cnt = this.expenseDAO.delete_resno(resno);
    return cnt;
}
}
