package dev.mvc.reply_of_reply;

//CREATE TABLE REPLY_OF_REPLY(
//    REPLYOFREPLYNO                    NUMBER(10)     NOT NULL,
//    REPLYCONTENTS                     CLOB     NOT NULL,
//    RDATE                             DATE     NOT NULL,
//    CUSTOMERNO                        NUMBER(10)     NULL ,
//    REPLYNO                           NUMBER(10)     NULL,
//    FOREIGN KEY (CUSTOMERNO) REFERENCES N_CUSTOMER (CUSTOMERNO),
//    FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO)
//);

public class Reply_OF_ReplyVO {
    private int replyofreplyno;
    private String replycontents = "";
    private String rdate = "";
    private int customerno;
    private int replyno;

    //회원 관련
    // -----------------------------------------------------------------------------------
    /** 회원 성명 */
    private String cname = "";
    /** 등급 */
    private int grade = 0;
    
    //페이징 관련
    // -----------------------------------------------------------------------------------
    /** 검색어 */
    private String word = "";
    /** 시작 rownum */
    private int start_num;    
    /** 종료 rownum */
    private int end_num;    
    /** 현재 페이지 */
    private int reply_now_page = 1;
    
    
    public int getReplyofreplyno() {
      return replyofreplyno;
    }
    public void setReplyofreplyno(int replyofreplyno) {
      this.replyofreplyno = replyofreplyno;
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
    public int getCustomerno() {
      return customerno;
    }
    public void setCustomerno(int customerno) {
      this.customerno = customerno;
    }
    public int getReplyno() {
      return replyno;
    }
    public void setReplyno(int replyno) {
      this.replyno = replyno;
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
    public String getWord() {
      return word;
    }
    public void setWord(String word) {
      this.word = word;
    }
    public int getStart_num() {
      return start_num;
    }
    public void setStart_num(int start_num) {
      this.start_num = start_num;
    }
    public int getEnd_num() {
      return end_num;
    }
    public void setEnd_num(int end_num) {
      this.end_num = end_num;
    }
    public int getReply_now_page() {
      return reply_now_page;
    }
    public void setReply_now_page(int reply_now_page) {
      this.reply_now_page = reply_now_page;
    }
    
    @Override
    public String toString() {
      return "Reply_OF_ReplyVO [replyofreplyno=" + replyofreplyno + ", replycontents=" + replycontents + ", rdate="
          + rdate + ", customerno=" + customerno + ", replyno=" + replyno + ", cname=" + cname + ", grade=" + grade
          + ", word=" + word + ", start_num=" + start_num + ", end_num=" + end_num + ", reply_now_page="
          + reply_now_page + "]";
    }
    
    
    
    
}
