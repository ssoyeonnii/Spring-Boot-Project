package com.bitc.java501team3.service;

import com.bitc.java501team3.dto.ReviewDTO;
import com.bitc.java501team3.dto.ReviewFileDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface ReviewService {
    void writeReview(ReviewDTO review, MultipartHttpServletRequest multiPart) throws Exception;

    List<ReviewDTO> selectReviewList(int reviewStoreIdx)throws Exception;

    List<ReviewFileDTO> getReviewImages(int reviewIdx) throws  Exception;
}
