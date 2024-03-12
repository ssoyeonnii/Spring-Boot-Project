package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.CommentDTO;
import com.bitc.java501team3.dto.FreeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommentMapper {

    //댓글목록
    public List<CommentDTO> commentList(int cmBoardIdx) throws Exception;

    //댓글작성
    public void insertComment(CommentDTO comment) throws Exception;

    //댓글수정
    public void deleteComment(HashMap params) throws Exception;
}
