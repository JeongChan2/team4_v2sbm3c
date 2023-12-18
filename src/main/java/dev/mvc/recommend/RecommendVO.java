package dev.mvc.recommend;

public class RecommendVO {
//    CREATE TABLE RECOMMEND(
//            RECOMMENDNO                           NUMBER(8)         NOT NULL         PRIMARY KEY,
//            CUSTOMERNO                              NUMBER(10)         NULL ,
//            TYPENUM                                NUMBER(10)         NULL ,
//            SEQ                                   NUMBER(2)         DEFAULT 1         NOT NULL,
//            RDATE                                 DATE         NOT NULL,
//      FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO),
//      FOREIGN KEY (TYPENUM) REFERENCES FOODTYPE (TYPENUM)
    /** 추천 번호 */
    private int recommendno;
    /** 회원 번호 */
    private int customerno;
    /** 음식 분류 번호 */
    private int typenum;
    /** 추천 우선순위 */
    private int seq;
    /** 날짜 */
    private String rdate;
    
    public int getRecommendno() {
        return recommendno;
    }
    public void setRecommendno(int recommendno) {
        this.recommendno = recommendno;
    }
    public int getCustomerno() {
        return customerno;
    }
    public void setCustomerno(int customerno) {
        this.customerno = customerno;
    }
    public int getTypenum() {
        return typenum;
    }
    public void setTypenum(int typenum) {
        this.typenum = typenum;
    }
    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }
    public String getRdate() {
        return rdate;
    }
    public void setRdate(String rdate) {
        this.rdate = rdate;
    }
    
}
