package dev.mvc.score;

import java.util.HashMap;

public interface ScoreProcInter {
    /**
     * 평점 등록 메소드
     * @param scoreVO
     * @return
     */
    public int create(ScoreVO scoreVO);
    /**
     * 나의 평점
     * @param customerno
     * @return
     */
    public ScoreVO list_by_me(int customerno);
    /**
     * 해당 컨텐츠의 평점(평균)
     * @param rescontentsno
     * @return
     */
    public Double list_contents(int rescontentsno);
    /**
     * 내 평점 수정
     * @param scoreVO
     * @return
     */
    public int update(ScoreVO scoreVO);
    /**
     * 내 평점 삭제
     * @param HashMap<Integer, Integer>
     * @return
     */
    public int delete(HashMap<Integer, Integer> map);
}
