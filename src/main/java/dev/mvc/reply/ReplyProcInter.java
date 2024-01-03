package dev.mvc.reply;

import java.util.ArrayList;

public interface ReplyProcInter {

    /**
     * 등록, 추상 메소드
     * @param replyVO
     * @return
     */
    public int create(ReplyVO replyVO);
    /**
     * 조회
     * @param 
     * @return
     */
    public ArrayList<ReplyVO> read(int rescontentsno);
    /**
     * 수정
     * @param replyVO
     * @return
     */
    public int update(ReplyVO replyVO);
    /**
     * 컨텐츠 삭제 시 자식 삭제용
     * @param replyVO
     * @return
     */
    public int delete_by_rescontentsno(int rescontentsno);
    /**
     * 댓글 번호로 삭제
     * @param replyVO
     * @return
     */
    public int delete_by_replyno(int replyno);
    /**
     * 회원 삭제 시 자식 삭제용
     * @param replyVO
     * @return
     */
    public int delete_by_customerno(int customerno);

}
