package dev.mvc.score;

//CREATE TABLE SCORE(
//        SCORENO                             NUMBER(10)       NOT NULL        PRIMARY KEY,
//        SCORE                               NUMBER(1)        NOT NULL,
//        RDATE                               DATE         NOT NULL,
//        RESCONTENTSNO                       NUMBER(10)       NULL ,
//        CUSTOMERNO                          NUMBER(10)       NULL ,
//  FOREIGN KEY (RESCONTENTSNO) REFERENCES RESCONTENTS (RESCONTENTSNO),
//  FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO)
//);

public class ScoreVO {

    private int scoreno; //평점 번호
    private Integer score; //평점
    private String rdate; //등록일
    private int rescontentsno; //컨텐츠 번호
    private int customerno;  //회원번호
    public int getScoreno() {
        return scoreno;
    }
    public void setScoreno(int scoreno) {
        this.scoreno = scoreno;
    }
    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    public String getRdate() {
        return rdate;
    }
    public void setRdate(String rdate) {
        this.rdate = rdate;
    }
    public int getRescontentsno() {
        return rescontentsno;
    }
    public void setRescontentsno(int rescontentsno) {
        this.rescontentsno = rescontentsno;
    }
    public int getCustomerno() {
        return customerno;
    }
    public void setCustomerno(int customerno) {
        this.customerno = customerno;
    }
    
}
