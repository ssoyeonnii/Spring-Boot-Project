package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.CommentDTO;
import com.bitc.java501team3.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    //댓글 목록
    @Override
    public List<CommentDTO> commentList(int cmBoardIdx) throws Exception {
        return commentMapper.commentList(cmBoardIdx);
    }

    //댓글작성
    @Override
    public void insertComment(CommentDTO comment) throws Exception {
        commentMapper.insertComment(comment);

    }

    @Override
    public void deleteComment(HashMap params) throws Exception {
        commentMapper.deleteComment(params);
    }


}
