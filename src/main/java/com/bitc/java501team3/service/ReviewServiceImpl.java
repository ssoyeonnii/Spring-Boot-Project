    package com.bitc.java501team3.service;

    import com.bitc.java501team3.common.FileUtils;
    import com.bitc.java501team3.dto.ReviewDTO;
    import com.bitc.java501team3.dto.ReviewFileDTO;
    import com.bitc.java501team3.mapper.ReviewMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.util.CollectionUtils;
    import org.springframework.web.multipart.MultipartHttpServletRequest;

    import java.util.List;

    @Service
    public class ReviewServiceImpl implements ReviewService {
        @Autowired
        private ReviewMapper reviewMapper;

        @Autowired
        private FileUtils fileUtils;


        @Override
        public void writeReview(ReviewDTO review, MultipartHttpServletRequest multiPart) throws Exception {
            reviewMapper.insertReview(review);

            List<ReviewFileDTO> reFileList = fileUtils.parseFileInfoReview(review.getCmIdx() , review.getReviewUserId(), multiPart);

            if(!CollectionUtils.isEmpty(reFileList)) { //안비었으면
                //CollectionUtils : 원래는 java class에서 제공하는 collection 타입의 클래스이나 스프링 프레임워크에서 제공하는 컬렉션 타입의 객체를 활용할 수 있는 클래스
                //utils : 도구모음 utility
                reviewMapper.insertReviewFileList(reFileList);
                //만들어진 파일정보를 db에 저장
            }

        }

        @Override
        public List<ReviewDTO> selectReviewList(int reviewStoreIdx) throws Exception {
            List<ReviewDTO> reviewList = reviewMapper.selectReviewList(reviewStoreIdx);
            for (ReviewDTO review : reviewList) {
                List<ReviewFileDTO> imagePaths = getReviewImages(review.getReviewIdx());
                review.setReviewImagePaths(imagePaths);
            }

            return reviewList;
        }

        @Override
        public List<ReviewFileDTO> getReviewImages(int fileReviewIdx) throws Exception {
            List<ReviewFileDTO> reviewFiles = reviewMapper.selectReviewFileInfo(fileReviewIdx);
            return reviewFiles;
        }

}
