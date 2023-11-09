package dev.mvc.rescontents;

import org.springframework.web.multipart.MultipartFile;

//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

/*
    rescontentsno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
    managerno                              NUMBER(10)     NOT NULL ,
    resno                                NUMBER(10)         NOT NULL ,
    title                                 VARCHAR2(300)         NOT NULL,
    rescontent                               CLOB                  NOT NULL,
    recom                                 NUMBER(7)         DEFAULT 0         NOT NULL,
    cnt                                   NUMBER(7)         DEFAULT 0         NOT NULL,
    replycnt                              NUMBER(7)         DEFAULT 0         NOT NULL,
    passwd                                VARCHAR2(15)         NOT NULL,
    word                                  VARCHAR2(300)         NULL ,
    rdate                                 DATE               NOT NULL,
    file1                                   VARCHAR(100)          NULL,
    file1saved                            VARCHAR(100)          NULL,
    thumb1                              VARCHAR(100)          NULL,
    size1                                 NUMBER(10)      DEFAULT 0 NULL,  
    price                                 NUMBER(10)      DEFAULT 0 NULL,  
    dc                                    NUMBER(10)      DEFAULT 0 NULL,  
    saleprice                            NUMBER(10)      DEFAULT 0 NULL,  
    point                                 NUMBER(10)      DEFAULT 0 NULL,  
    salecnt                               NUMBER(10)      DEFAULT 0 NULL,  
    map                                  VARCHAR2(1000)            NULL,
    youtube                             VARCHAR2(1000)            NULL,   
    foodno                              NUMBER(10)                NULL,
*/

//@Getter @Setter @ToString
public class RescontentsVO {
    /** 컨텐츠 번호 */
    private int rescontentsno;
    /** 관리자 번호 */
    private int managerno;
    /** 카테고리 번호 */
    private int resno;
    /** 제목 */
    private String title = "";
    /** 내용 */
    private String rescontent = "";
    /** 추천수 */
    private int recom;
    /** 조회수 */
    private int cnt = 0;
    /** 댓글수 */
    private int replycnt = 0;
    /** 패스워드 */
    private String passwd = "";
    /** 검색어 */
    private String word = "";
    /** 등록 날짜 */
    private String rdate = "";
    /** 지도 */
    private String map;
    /** Youtube */
    private String youtube;
    /** 음식 이름 */
    private int foodno;

    // 파일 업로드 관련
    // -----------------------------------------------------------------------------------
    /**
    이미지 파일
    <input type='file' class="form-control" name='file1MF' id='file1MF' 
               value='' placeholder="파일 선택">
    */
    private MultipartFile file1MF;
    /** 메인 이미지 크기 단위, 파일 크기 */
    private String size1_label = "";
    /** 메인 이미지 */
    private String file1 = "";
    /** 실제 저장된 메인 이미지 */
    private String file1saved = "";
    /** 메인 이미지 preview */
    private String thumb1 = "";
    /** 메인 이미지 크기 */
    private long size1;

    // 쇼핑몰 상품 관련
    // -----------------------------------------------------------------------------------
    /** 정가 */
    private int price;
    /** 할인률 */
    private int dc;
    /** 판매가 */
    private int saleprice;
    /** 포인트 */
    private int point;
    /** 재고 수량 */
    private int salecnt;
    
    // 페이징 관련
    // -----------------------------------------------------------------------------------
    /** 시작 rownum */
    private int start_num;    
    /** 종료 rownum */
    private int end_num;    
    /** 현재 페이지 */
    private int now_page = 1;

    public int getRescontentsno() {
      return rescontentsno;
    }

    public void setRescontentsno(int rescontentsno) {
      this.rescontentsno = rescontentsno;
    }

    public int getManagerno() {
      return managerno;
    }

    public void setManagerno(int managerno) {
      this.managerno = managerno;
    }

    public int getResno() {
      return resno;
    }

    public void setResno(int resno) {
      this.resno = resno;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getRescontent() {
      return rescontent;
    }

    public void setRescontent(String rescontent) {
      this.rescontent = rescontent;
    }

    public int getRecom() {
      return recom;
    }

    public void setRecom(int recom) {
      this.recom = recom;
    }

    public int getCnt() {
      return cnt;
    }

    public void setCnt(int cnt) {
      this.cnt = cnt;
    }

    public int getReplycnt() {
      return replycnt;
    }

    public void setReplycnt(int replycnt) {
      this.replycnt = replycnt;
    }

    public String getPasswd() {
      return passwd;
    }

    public void setPasswd(String passwd) {
      this.passwd = passwd;
    }

    public String getWord() {
      return word;
    }

    public void setWord(String word) {
      this.word = word;
    }

    public String getRdate() {
      return rdate;
    }

    public void setRdate(String rdate) {
      this.rdate = rdate;
    }

    public String getFile1() {
      return file1;
    }

    public void setFile1(String file1) {
      this.file1 = file1;
    }

    public String getFile1saved() {
      return file1saved;
    }

    public void setFile1saved(String file1saved) {
      this.file1saved = file1saved;
    }

    public String getThumb1() {
      return thumb1;
    }

    public void setThumb1(String thumb1) {
      this.thumb1 = thumb1;
    }

    public long getSize1() {
      return size1;
    }

    public void setSize1(long size1) {
      this.size1 = size1;
    }

    public int getPrice() {
      return price;
    }

    public void setPrice(int price) {
      this.price = price;
    }

    public int getDc() {
      return dc;
    }

    public void setDc(int dc) {
      this.dc = dc;
    }

    public int getSaleprice() {
      return saleprice;
    }

    public void setSaleprice(int saleprice) {
      this.saleprice = saleprice;
    }

    public int getPoint() {
      return point;
    }

    public void setPoint(int point) {
      this.point = point;
    }

    public int getSalecnt() {
      return salecnt;
    }

    public void setSalecnt(int salecnt) {
      this.salecnt = salecnt;
    }

    public String getMap() {
      return map;
    }

    public void setMap(String map) {
      this.map = map;
    }

    public String getYoutube() {
      return youtube;
    }

    public void setYoutube(String youtube) {
      this.youtube = youtube;
    }

    public MultipartFile getFile1MF() {
      return file1MF;
    }

    public void setFile1MF(MultipartFile file1mf) {
      file1MF = file1mf;
    }

    public String getSize1_label() {
      return size1_label;
    }

    public void setSize1_label(String size1_label) {
      this.size1_label = size1_label;
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

    public int getNow_page() {
      return now_page;
    }

    public void setNow_page(int now_page) {
      this.now_page = now_page;
    }

    public int getFoodno() {
      return foodno;
    }

    public void setFoodno(int foodno) {
      this.foodno = foodno;
    }

    @Override
    public String toString() {
      return "RescontentsVO [rescontentsno=" + rescontentsno + ", managerno=" + managerno + ", resno=" + resno
          + ", title=" + title + ", rescontent=" + rescontent + ", recom=" + recom + ", cnt=" + cnt + ", replycnt="
          + replycnt + ", passwd=" + passwd + ", word=" + word + ", rdate=" + rdate + ", map=" + map + ", youtube="
          + youtube + ", foodno=" + foodno + ", file1MF=" + file1MF + ", size1_label=" + size1_label + ", file1="
          + file1 + ", file1saved=" + file1saved + ", thumb1=" + thumb1 + ", size1=" + size1 + ", price=" + price
          + ", dc=" + dc + ", saleprice=" + saleprice + ", point=" + point + ", salecnt=" + salecnt + ", start_num="
          + start_num + ", end_num=" + end_num + ", now_page=" + now_page + "]";
    }
    

    
}


