package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.CommentDTO;
import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //댓글등록하기
    @RequestMapping(value = "/board/commentWrite.do", method = RequestMethod.POST)
    public String insertComment(CommentDTO comment, HttpSession session) throws Exception{
        String loggedInUserId = (String) session.getAttribute("userId");
        comment.setCmUserId(loggedInUserId);

        commentService.insertComment(comment);

        return "redirect:/board/boardDetail.do?boardIdx=" + comment.getCmBoardIdx();
    }

    //댓글삭제하기
    @RequestMapping("/board/commentDelete.do")
    public String boardDelete(@RequestParam("cmIdx") int cmIdx, @RequestParam("cmBoardIdx") int cmBoardIdx,@RequestParam("cmUserId") String cmUserId, HttpSession session)throws Exception {
        String loggedInUserId = (String) session.getAttribute("userId");
        String userAdCheck = (String) session.getAttribute("userAdCheck");

        if ("admin".equals(userAdCheck) || (loggedInUserId != null && loggedInUserId.equals(cmUserId))) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("cmBoardIdx", cmBoardIdx);
            params.put("cmIdx", cmIdx);

            commentService.deleteComment(params);

            return "redirect:/board/boardDetail.do?boardIdx=" + cmBoardIdx;
        } else {
            // 수정 권한이 없는 경우 처리 (예: 에러 페이지 또는 경고 메시지 반환)
            return "main/errorpage";
        }
    }
}


