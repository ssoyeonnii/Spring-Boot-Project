package com.bitc.java501team3.mapper;


import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.FreeBoardFileDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;


import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    public FreeBoardDTO selectBoardDetail(int boardIdx) throws Exception;

    public void updateHitCount(int boardIdx) throws Exception;

    public void editBoard(FreeBoardDTO board) throws Exception;

    public void insertBoardFileList(List<FreeBoardFileDTO> fileList) throws Exception;

    public void insertBoard(FreeBoardDTO board) throws Exception;

    public List<FreeBoardFileDTO> selectBoardFileInfo(int boardIdx) throws Exception;

    public Page<FreeBoardDTO> selectBoardPageList() throws Exception;

    public void deleteBoard(int boardIdx) throws Exception;

    public void deleteBoardFile(int boardIdx) throws Exception;

    public List<FreeBoardDTO> selectBoardPage(HashMap params) throws Exception;


    public List<FreeBoardDTO> selectBoardList() throws Exception;
}
