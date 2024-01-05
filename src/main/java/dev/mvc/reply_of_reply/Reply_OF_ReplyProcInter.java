package dev.mvc.reply_of_reply;

import java.util.ArrayList;

public interface Reply_OF_ReplyProcInter {

    /**
     * 등록, 추상 메소드
     * @param Reply_OF_ReplyVO
     * @return
     */
    public int create(Reply_OF_ReplyVO replyVO);
    /**
     * 조회
     * @param 
     * @return
     */
    public Reply_OF_ReplyVO read(int replyofreplyno);
    
    /**
     * 검색된 레코드 갯수
     * @param map
     * @return
     */
    public int search_count(int replyno);
    
    /**
     * 페이징 조회
     * @param 
     * @return
     */
    public ArrayList<Reply_OF_ReplyVO> read_paging(Reply_OF_ReplyVO replyVO);
    
    /**
     * 수정
     * @param Reply_OF_ReplyVO
     * @return
     */
    public int update(Reply_OF_ReplyVO replyVO);
    /**
     * 댓글 삭제 시 자식 삭제용
     * @param replyVO
     * @return
     */
    public int delete_by_replyno(int replyno);
    /**
     * 댓글 번호로 삭제
     * @param replyVO
     * @return
     */
    public int delete_by_reply_of_replyno(int replyofreplyno);
    /**
     * 회원 삭제 시 자식 삭제용
     * @param customerno
     * @return
     */
    public int delete_by_customerno(int customerno);
    
    /** 
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param rescontentsno          컨텐츠번호 
     * @param now_page      현재 페이지
     * @param word 검색어
     * @param list_file 목록 파일명 
     * @return 페이징 생성 문자열
     */ 
    public String pagingBox(int replyno, int rescontentsno, int resno, int now_page, int reply_now_page, String list_file);

}
