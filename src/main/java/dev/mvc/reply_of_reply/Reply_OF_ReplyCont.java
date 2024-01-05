package dev.mvc.reply_of_reply;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.reply.ReplyProcInter;
import dev.mvc.reply.ReplyVO;
import dev.mvc.rescontents.RescontentsVO;


@Controller
public class Reply_OF_ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply_of_reply.Reply_OF_ReplyProc")
  private Reply_OF_ReplyProcInter reply_of_replyProc;
  
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc")
  private ReplyProcInter replyProc;
  
  /**
   * 음식점 목록(식당)
   * http://localhost:9093/sell/select_menu_fetch.do?resno=3
   * @return
   */
  @RequestMapping(value="/reply_of_reply/reply_fetch.do", method = RequestMethod.GET)
  @ResponseBody
  public String reply_fetch(int replyno) {
    
    Reply_OF_ReplyVO reply_of_replyVO = new Reply_OF_ReplyVO();
    
    reply_of_replyVO.setReplyno(replyno);

    ArrayList<Reply_OF_ReplyVO> list = this.reply_of_replyProc.read_paging(reply_of_replyVO);
    
    JSONArray array = new JSONArray();
    JSONObject json = null;
    
    for (Reply_OF_ReplyVO replyVO : list) {
      json = new JSONObject();
      json.put("replyofreplyno", replyVO.getReplyofreplyno());
      json.put("replycontents", replyVO.getReplycontents());
      json.put("rdate", replyVO.getRdate());
      json.put("customerno", replyVO.getCustomerno());
      json.put("cname", replyVO.getCname());
      json.put("replyno", replyVO.getReplyno());
      array.put(json);
    }
    System.out.println(array.toString());
    return array.toString();
  }
  
  /**
   * 등록
   * 
   * @return
   */
  @RequestMapping(value="/reply_of_reply/reply.do", method=RequestMethod.POST )
  public ModelAndView reply(Reply_OF_ReplyVO replyVO, int resno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.reply_of_replyProc.create(replyVO);
    int cnt2 = this.replyProc.re_plus(replyVO.getReplyno());
    
    if (cnt == 1) {
        mav.addObject("code", "reply_success");
        // resProc.increaseCnt(rescontentsVO.getResno()); // 글수 증가
    } else {
        mav.addObject("code", "reply_fail");
    }
    mav.addObject("resno", resno); // redirect parameter 적용
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    //mav.setViewName("/rescontents/read"); // /WEB-INF/views/contents/map.jsp
    mav.addObject("url", "/rescontents/msg"); // msg.jsp, redirect parameter 적용
    mav.setViewName("redirect:/rescontents/msg.do"); // Post -> Get - param...     
    return mav;
  }
  
  /**
   * 삭제
   * 
   * @return
   */
  @RequestMapping(value="/reply_of_reply/reply_of_reply_delete.do", method=RequestMethod.GET )
  public ModelAndView reply_of_reply_delete(int replyofreplyno, int resno) {
    ModelAndView mav = new ModelAndView();
    
    Reply_OF_ReplyVO replyVO = this.reply_of_replyProc.read(replyofreplyno);
    int cnt2 = this.replyProc.re_minus(replyVO.getReplyno());
    int cnt = this.reply_of_replyProc.delete_by_reply_of_replyno(replyofreplyno);
    
    
    
    if (cnt == 1) {
        mav.addObject("code", "reply_delete_success");
    } else {
        mav.addObject("code", "reply_delete_fail");
    }
    mav.addObject("resno", resno); // redirect parameter 적용
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    //mav.setViewName("/rescontents/read"); // /WEB-INF/views/contents/map.jsp
    mav.addObject("url", "/rescontents/msg"); // msg.jsp, redirect parameter 적용
    mav.setViewName("redirect:/rescontents/msg.do"); // Post -> Get - param...     
    return mav;
  }
}
