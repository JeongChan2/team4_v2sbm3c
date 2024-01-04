package dev.mvc.expense;

import java.util.ArrayList;
import java.util.HashMap;

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
  
  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int cnt = this.expenseDAO.search_count(hashMap);
    return cnt;
  }
  
  @Override
  public ArrayList<Expense_JoinVO> list_by_managerno_search_paging(Expense_JoinVO expense_JoinVO) {
    
    int begin_of_page = (expense_JoinVO.getNow_page() - 1) * Expense.RECORD_PER_PAGE;
    int start_num = begin_of_page + 1;
    int end_num = begin_of_page + Expense.RECORD_PER_PAGE;   
    
    expense_JoinVO.setStart_num(start_num);
    expense_JoinVO.setEnd_num(end_num);
    
    ArrayList<Expense_JoinVO> list = this.expenseDAO.list_by_managerno_search_paging(expense_JoinVO);
    
    
    return list;
  }
  
  @Override
  public String pagingBox(int managerno, int now_page, String word, String list_file){
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("managerno", managerno);
    hashMap.put("word", word);
          
    int search_count = this.expenseDAO.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
    
    // 객체를 만들지 않고 쓰는 메소드 -> static method ( 정적 메소드 ) 라고 한다.
    int total_page = (int)(Math.ceil((double)search_count / Expense.RECORD_PER_PAGE)); // 전체 페이지 수: (double)1/10 = 0.1 -> 1 (Math.ceil)로 올림이 된다.
                                                                                        // 자료가 1개라고 페이지가 0이면 안되기 때문
    // 전체 그룹 수 : (double)1/10 -> 0.1 -> 1그룹, (double)12/10 -> 1.2 -> 2그룹
    int total_grp = (int)(Math.ceil((double)total_page / Expense.PAGE_PER_BLOCK)); // 전체 그룹  수
    // 현재 그룹 번호 : (double)1/10 -> 0.1 -> 1그룹, (double)12/10 -> 1.2 -> 2그룹
    int now_grp = (int)(Math.ceil((double)now_page / Expense.PAGE_PER_BLOCK));  // 현재 그룹 번호
    
    // 1 group: 1, 2, 3 ... 9, 10
    // 2 group: 11, 12 ... 19, 20
    // 3 group: 21, 22 ... 29, 30
    int start_page = ((now_grp - 1) * Expense.PAGE_PER_BLOCK) + 1; // 특정 그룹의 시작  페이지  
    int end_page = (now_grp * Expense.PAGE_PER_BLOCK);               // 특정 그룹의 마지막 페이지   
     
    StringBuffer str = new StringBuffer(); // String class 보다 문자열 추가등의 편집시 속도가 빠름 
     
    // style이 java 파일에 명시되는 경우는 로직에 따라 css가 영향을 많이 받는 경우에 사용하는 방법
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    // 이전 10개 페이지로 이동
    // now_grp: 1 (1 ~ 10 page)
    // now_grp: 2 (11 ~ 20 page)
    // now_grp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
    int _now_page = (now_grp - 1) * Expense.PAGE_PER_BLOCK;  
    if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이 이상임으로 이전 그룹으로 갈수 있는 링크 생성 
      str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"'>이전</A></span>"); 
    } 
 
    // 중앙의 페이지 목록
    for(int i=start_page; i<=end_page; i++){ 
      if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
        break; 
      } 
  
      if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
        str.append("<span class='span_box_1'><A href='"+list_file+"?word="+word+"&now_page="+i+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 페이지 5일경우 -> 현재 1그룹: (1 * 10) + 1 = 2그룹의 시작페이지 11
    // 현재 페이지 15일경우 -> 현재 2그룹: (2 * 10) + 1 = 3그룹의 시작페이지 21
    // 현재 페이지 25일경우 -> 현재 3그룹: (3 * 10) + 1 = 4그룹의 시작페이지 31
    _now_page = (now_grp * Expense.PAGE_PER_BLOCK)+1; //  최대 페이지수 + 1 
    if (now_grp < total_grp){ 
      str.append("<span class='span_box_1'><A href='"+list_file+"?&word="+word+"&now_page="+_now_page+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

}
