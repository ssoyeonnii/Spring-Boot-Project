package com.bitc.java501team3.mapper;

import com.bitc.java501team3.dto.ReviewDTO;
import com.bitc.java501team3.dto.ReviewFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public void insertReview(ReviewDTO review) throws Exception;

    public void insertReviewFileList(List<ReviewFileDTO> reFileList) throws Exception;

    public List<ReviewDTO> selectReviewList(int reviewStoreIdx)throws Exception;

    public List<ReviewFileDTO> selectReviewFileInfo(int fileReviewIdx) throws Exception;
}
