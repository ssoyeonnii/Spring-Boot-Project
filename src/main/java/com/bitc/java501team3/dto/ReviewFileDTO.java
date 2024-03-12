package com.bitc.java501team3.dto;

import lombok.Data;

@Data
public class ReviewFileDTO {
    private int fileIdx;
    private int fileReviewIdx;
    private String fileReviewOrgfileName;
    private String fileReviewStoredfileName;
    private long fileReviewFilesize;
    private String fileReviewUserid;
    private String fileReviewCreatedate;
    private String fileReviewDeletedYn;

    public ReviewFileDTO() {
        // 기본 생성자
    }
}
