package com.bitc.java501team3.dto;

import java.util.List;
import lombok.Data;

@Data
public class ReviewDTO {
    private int reviewIdx;
    private int reviewStoreIdx;
    private String reviewContent;
    private String reviewUserId;
    private String reviewCreateDate;
    private String reviewOriginalFileName;
    private String reviewStoredlFileName;
    private String reviewDeletedYn;
    private int CmIdx;
    private List<ReviewFileDTO> ReviewImagePaths;

}
