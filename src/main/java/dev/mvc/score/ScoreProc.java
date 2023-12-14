package dev.mvc.score;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.score.ScoreProc")
public class ScoreProc implements ScoreProcInter {
    @Autowired
    private ScoreDAOInter scoreDAO;
    
    public ScoreProc() {
        System.out.println("-> ScoreProc created.");
    }

    @Override
    public int create(ScoreVO scoreVO) {
        int cnt = this.scoreDAO.create(scoreVO);
        return cnt;
    }

    @Override
    public ScoreVO list_by_me(int customerno) {
        ScoreVO scoreVO = this.scoreDAO.list_by_me(customerno);
        return scoreVO;
    }

    @Override
    public Double list_contents(int rescontentsno) {
        Double cnt = this.scoreDAO.list_contents(rescontentsno);
        return cnt;
    }

    @Override
    public int update(ScoreVO scoreVO) {
        int cnt = this.scoreDAO.update(scoreVO);
        return cnt;
    }

    @Override
    public int delete(HashMap<Integer, Integer> map) {
        int cnt = this.scoreDAO.delete(map);
        return cnt;
    }
    
}
