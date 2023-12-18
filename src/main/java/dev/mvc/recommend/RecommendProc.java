package dev.mvc.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.recommend.RecommendProc")
public class RecommendProc implements RecommendProcInter {
    @Autowired
    private RecommendDAOInter recommendDAO;
    
    public RecommendProc() {
        System.out.println("-> RecommendProc created.");
      }
    
    @Override
    public RecommendVO read(int customerno) {
        RecommendVO recommendVO = this.recommendDAO.read(customerno);
      return recommendVO;
    }
    
    @Override
    public int delete(int customerno) {
        int cnt = this.recommendDAO.delete(customerno);
        return cnt;
    }
}
