package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.ReviewDTO;
import com.bitc.java501team3.dto.ReviewFileDTO;
import com.bitc.java501team3.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;



    //리뷰입력
    @PostMapping("/review/write")
    public String writeReview(ReviewDTO review, MultipartHttpServletRequest multiPart, HttpSession session) throws Exception{
        String loggedInUserId = (String) session.getAttribute("userId");
        review.setReviewUserId(loggedInUserId);
        reviewService.writeReview(review, multiPart);

        return "redirect:/main/detail?idx=" + review.getReviewStoreIdx();
    }

    @ResponseBody
    @GetMapping("/review/list")
    public List<ReviewDTO> selectReview(@RequestParam int reviewStoreIdx) throws Exception{
        List<ReviewDTO> reviewLists = reviewService.selectReviewList(reviewStoreIdx);

        return reviewLists;
    }
}
