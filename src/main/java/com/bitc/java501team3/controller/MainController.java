package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.FavListDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ApiDbDTO;
import com.bitc.java501team3.dto.hotPlaceDTO.ItemDTO;
import com.bitc.java501team3.service.MainService;
import com.bitc.java501team3.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @Autowired
    private ReviewService reviewService;

    @Value("${fullstack501.hotplace.service.url}")
    private String hotPlaceServiceUrl;

    @Value("${fullstack501.hotplace.service.key}")
    private String hotPlaceServicekey;

    //    홈 뷰
    @RequestMapping("/home")
    public String home() throws Exception {
        return "main/home";
    }

    // 카드형식으로 데이터 가져오기
    @ResponseBody
    @GetMapping("/home.do")
    public List<ApiDbDTO> getCard() throws Exception {

        List<ApiDbDTO> cardList = mainService.getCard();

        return cardList;
    }

    // 기본 목록 가져오기
    @ResponseBody
    @PostMapping("/main/hotPlaceJson")
    public List<ItemDTO> gethotPlaceJson() throws Exception {
        String optkey = "?serviceKey=";
        String opt1 = "&pageNo=";
        String opt2 = "&numOfRows=";
        String opt3 = "&resultType=json";

        List<ItemDTO> hotPlaceList = mainService.gethotPlaceJson(hotPlaceServiceUrl + optkey + hotPlaceServicekey + opt1 + "1" + opt2 + "300" + opt3);

        return hotPlaceList;
    }

    @RequestMapping("/main/detail")
    public ModelAndView detail(@RequestParam int idx) throws Exception {
        ModelAndView mv = new ModelAndView("/main/detail");

        mv.addObject("idx",idx);

        return mv;
    }

    //    즐겨찾기 추가
    @ResponseBody
    @RequestMapping("/mainAddFav")
    public String mainAddFav(@RequestParam("favListStoreIdx") int favListStoreIdx, HttpSession session) throws Exception {
        String loggedInUserId = (String) session.getAttribute("userId");
        try {
            mainService.insertFav(favListStoreIdx,loggedInUserId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // 또는 로그 기록
            return "error";
        }
    }

    //    즐겨찾기 삭제
    @ResponseBody
    @RequestMapping("/mainDeleteFav")
    public String mainDeleteFav(@RequestParam("favListStoreIdx") int favListStoreIdx, HttpSession session) throws Exception {
        String loggedInUserId = (String) session.getAttribute("userId");
        try {
            mainService.deltetFav(favListStoreIdx,loggedInUserId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // 또는 로그 기록
            return "error";
        }
    }

    //    즐겨찾기 목록 불러오기
    @ResponseBody
    @RequestMapping("/mainViewFavList")
    public ResponseEntity<List<FavListDTO>> viewFavList(HttpSession session) throws Exception {
        String loggedInUserId = (String) session.getAttribute("userId");
        List<FavListDTO> favList = mainService.selectFavList(loggedInUserId);
        return new ResponseEntity<>(favList, HttpStatus.OK);
    }

    //    home에서 식당이름 검색하기 (url : ajax url과 동일하게)
    @RequestMapping("/main/search.do")
    public ModelAndView searchBoardList(@RequestParam("keyword") String keyword) throws Exception{

        ModelAndView mv = new ModelAndView("main/searchInfo");

        List<ApiDbDTO> searchCardList = new ArrayList<>(mainService.searchGetCard(keyword));

        mv.addObject("searchCardList", searchCardList);
        mv.addObject("keyword", keyword);

        return mv;
    }
}


