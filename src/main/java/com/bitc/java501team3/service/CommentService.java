package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.CommentDTO;

import java.util.HashMap;
import java.util.List;

public interface CommentService {

    //댓글목록
    public List<CommentDTO> commentList(int cmBoardIdx) throws Exception;

    //댓글작성
    public void insertComment(CommentDTO comment) throws Exception;

    //댓글삭제
    public void deleteComment(HashMap params) throws Exception;
}
