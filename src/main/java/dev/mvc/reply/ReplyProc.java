package dev.mvc.reply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {
    @Autowired
    private ReplyDAOInter replyDAO;
    
    public ReplyProc() {
        System.out.println("-> ReplyProc created.");
    }

    @Override
    public int create(ReplyVO replyVO) {
        int cnt = this.replyDAO.create(replyVO);
        return cnt;
    }

    @Override
    public ArrayList<ReplyVO> read(int rescontentsno) {
        ArrayList<ReplyVO> list = this.replyDAO.read(rescontentsno);
        return list;
    }

    @Override
    public int update(ReplyVO replyVO) {
        int cnt = this.replyDAO.update(replyVO);
        return cnt;
    }

    @Override
    public int delete_by_rescontentsno(int rescontentsno) {
        int cnt = this.replyDAO.delete_by_rescontentsno(rescontentsno);
        return cnt;
    }

    @Override
    public int delete_by_replyno(int replyno) {
        int cnt = this.replyDAO.delete_by_replyno(replyno);
        return cnt;
    }

    @Override
    public int delete_by_customerno(int customerno) {
        int cnt = this.replyDAO.delete_by_customerno(customerno);
        return cnt;
    }
    
}
