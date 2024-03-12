package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.CommentDTO;
import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.FreeBoardFileDTO;
import com.bitc.java501team3.service.BoardService;
import com.bitc.java501team3.service.CommentService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    //게시판 글 목록
    @RequestMapping(value = "/board/boardList.do" , method = RequestMethod.GET)
    public ModelAndView selectBoardList(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNum) throws Exception{

        ModelAndView mv = new ModelAndView("board/boardList");

        PageInfo<FreeBoardDTO> boardPageList = new PageInfo<>(boardService.selectBoardPageList(pageNum), 3);

        mv.addObject("boardPageList", boardPageList);

        return mv;
    }

    //게시판 글 목록
//    @RequestMapping(value = "/board/boardList.do" , method = RequestMethod.GET)
//    public ModelAndView selectBoardList(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNum ,@RequestParam("keyword") String keyword, @RequestParam("searchType") String searchType) throws Exception{
//
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("searchType", searchType);
//        params.put("keyword", keyword);
//
//        ModelAndView mv = new ModelAndView("board/boardList");
//
//        PageInfo<FreeBoardDTO> boardPageList = new PageInfo<>(boardService.selectBoardPageList(pageNum,params), 3);
//
//        mv.addObject("boardPageList", boardPageList);
//
//        return mv;
//    }

    //게시글 검색
    @RequestMapping("/board/search.do")
    public ModelAndView searchBoardList(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNum, @RequestParam("keyword") String keyword, @RequestParam("searchType") String searchType) throws Exception{

        HashMap<String, Object> params = new HashMap<>();
        params.put("searchType", searchType);
        params.put("keyword", keyword);

        ModelAndView mv = new ModelAndView("board/boardList");

        PageInfo<FreeBoardDTO> boardSearchPageList = new PageInfo<>(boardService.selectBoardPage(pageNum,params), 3);

        mv.addObject("boardSearchPageList" , boardSearchPageList);

        return mv;
    }

    // 글쓰기 view 페이지
    @RequestMapping("/board/boardWrite.do")
    public String boardWrite() throws Exception{
        return "board/boardWrite";
    }

    // 사용자가 입력한 데이터로 글쓰기 페이지로 이동하는 process작성
    // 매개변수를 BoardDto 클래스 타입으로 지정했기 때문에 html의 input 태그 중 name 속성 값을 BoardDto 클래스의 필드명과 동일하게 사용해야 함
    //MultipartHttpServletRequest : 클라이언트에서 전달한 파일 정보를 받아오는 클래스
    //게시판 글 작성 페이지
    @RequestMapping("/board/insertBoard.do")
    public String insertBoard(FreeBoardDTO board, MultipartHttpServletRequest multiPart,HttpSession session) throws Exception{
        String loggedInUserId = (String) session.getAttribute("userId");
        board.setBoardUserId(loggedInUserId);
        boardService.insertBoard(board, multiPart);

        return "redirect:/board/boardList.do";
    }

    //게시판 디테일 페이지
    @RequestMapping("/board/boardDetail.do")
    public ModelAndView boardDetail(@RequestParam int boardIdx) throws Exception{
        ModelAndView mv = new ModelAndView("board/boardDetail");

        FreeBoardDTO board = boardService.selectBoardDetail(boardIdx);
        //        현재 글번호를 기준으로 첨부 파일 목록을 가져옴

        List<FreeBoardFileDTO> boardFileList = boardService.selectBoardFileInfo(boardIdx);

        List<CommentDTO> comment = commentService.commentList(boardIdx);

        mv.addObject("board" , board);
        mv.addObject("comment", comment);
        mv.addObject("boardFileList",boardFileList);

        return mv;
    }

    //게시글 수정 페이지
    @RequestMapping("/board/boardEdit.do")
    public ModelAndView boardEditView(@RequestParam int boardIdx,@RequestParam String boardUserId, HttpSession session) throws Exception {

        String loggedInUserId = (String) session.getAttribute("userId");


        if (loggedInUserId != null && loggedInUserId.equals(boardUserId)) {
            // 글 수정 로직 수행
            ModelAndView mv = new ModelAndView("board/boardEdit");

            FreeBoardDTO board = boardService.selectBoardDetail(boardIdx);

            mv.addObject("board", board);

            return mv;
        } else {
            // 수정 권한이 없는 경우 처리 (예: 에러 페이지 또는 경고 메시지 반환)
            ModelAndView mv = new ModelAndView("main/errorpage");
            return mv;
        }
    }

    //게시글 수정 실행
    @RequestMapping(value = "/board/boardEdit.do", method = RequestMethod.POST)
    public String boardEdit(FreeBoardDTO board, HttpSession session) throws Exception {
        // 세션에서 현재 로그인한 사용자 정보 가져오기
        String loggedInUserId = (String) session.getAttribute("userId");
        board.setBoardUserId(loggedInUserId);

        boardService.boardEdit(board);
        return "redirect:/board/boardDetail.do?boardIdx=" + board.getBoardIdx();

    }

    //게시글 삭제하기
    @RequestMapping("/board/boardDelete.do")
    public String boardDelete(@RequestParam("boardIdx") int boardIdx, @RequestParam("boardUserId") String boardUserId,HttpSession session) throws Exception {
        String loggedInUserId = (String) session.getAttribute("userId");

        // 추가: 세션에 저장된 사용자 권한 확인 (예: "admin"인지 확인)
        String userAdCheck = (String) session.getAttribute("userAdCheck");

        // 추가: 만약 사용자가 관리자인 경우 또는 글 작성자인 경우에만 삭제를 허용
        if ("admin".equals(userAdCheck) || (loggedInUserId != null && loggedInUserId.equals(boardUserId))) {
            // 글 삭제 로직 수행
            boardService.deleteBoard(boardIdx);
            boardService.deleteBoardFile(boardIdx);

            return "redirect:/board/boardList.do";
        } else {
            // 수정 권한이 없는 경우 처리 (예: 에러 페이지 또는 경고 메시지 반환)
            return "main/errorpage";
        }
    }
}
