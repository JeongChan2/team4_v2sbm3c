package dev.mvc.reply;

//CREATE TABLE REPLY(
//        REPLYNO                             NUMBER(10)       NOT NULL        PRIMARY KEY,
//        REPLYCONTENTS                       CLOB         NOT NULL,
//        RDATE                               DATE         NOT NULL,
//        RESCONTENTSNO                       NUMBER(10)       NULL ,
//        CUSTOMERNO                          NUMBER(10)       NULL ,
//  FOREIGN KEY (RESCONTENTSNO) REFERENCES RESCONTENTS (RESCONTENTSNO),
//  FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO)
//);

public class ReplyVO {
    private int replyno;
    private String replycontents = "";
    private String rdate = "";
    private int rescontentsno;
    private int customerno;

    /** 회원 성명 */
    private String cname = "";
    /** 등급 */
    private int grade = 0;
    
    
    public int getReplyno() {
        return replyno;
    }
    public void setReplyno(int replyno) {
        this.replyno = replyno;
    }
    public String getReplycontents() {
        return replycontents;
    }
    public void setReplycontents(String replycontents) {
        this.replycontents = replycontents;
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
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    
}
