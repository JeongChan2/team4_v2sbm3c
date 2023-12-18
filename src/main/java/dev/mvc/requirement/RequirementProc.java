package dev.mvc.requirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.requirement.RequirementProc")
public class RequirementProc implements RequirementProcInter {
  @Autowired
  private RequirementDAOInter requirementDAO;
  
  public RequirementProc() {
    System.out.println("-> RequirementProc created.");
  }
  
  @Override
  public int check(int customerno) {
    int cnt = this.requirementDAO.check(customerno);
    return cnt;
  }
  
  @Override
  public int create(RequirementVO requirementVO) {
    int cnt = this.requirementDAO.create(requirementVO);
    return cnt;
  }

  @Override
  public RequirementVO read(int customerno) {
    RequirementVO requirementVO = this.requirementDAO.read(customerno);
    return requirementVO;
  }

  @Override
  public int update(RequirementVO requirementVO) {
    int cnt = this.requirementDAO.update(requirementVO);
    return cnt;
  }

  
}
