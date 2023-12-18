package dev.mvc.recommend;

public interface RecommendProcInter {
    /**
     * customerno로 회원 추천 조회
     * @param customerno
     * @return
     */
    public RecommendVO read(int customerno);
    /**
     * customerno로 회원 추천 데이터 초기화
     * @param customerno
     * @return
     */
    public int delete(int customerno);
}
