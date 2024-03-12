package com.bitc.java501team3.controller;

import com.bitc.java501team3.dto.FreeBoardDTO;
import com.bitc.java501team3.dto.UserDTO;
import com.bitc.java501team3.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String userInsertProcess(UserDTO user) throws Exception {

        if (userService.isUserIdconfirm(user.getUserId())==1) {
            // 중복된 ID가 존재하면 사용자에게 알림 또는 예외 처리 등을 수행할 수 있습니다.
            // 여기에서는 간단하게 콘솔에 로그를 출력하는 예시를 들었습니다.
            // 중복된 ID에 대한 처리를 추가하거나 사용자에게 알림을 보낼 수 있습니다.

            // 여기서 예시로 바로 로그인 페이지로 리다이렉트합니다.
            return "redirect:/user/login?joinError=true";
        }

        // 중복이 없으면 사용자를 등록
        userService.insertUser(user);

        return "redirect:/user/login?joinMsg=true";
    }
    //    로그인
    @RequestMapping("/login")
    public String loginForm()throws Exception{
        return "user/login";
    }

    @PostMapping(value = "/loginProcess")
    public String loginProcess(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest req) throws Exception {
        int result = userService.isUserInfo(userId, userPw);


        if (result == 1) {
            UserDTO user = userService.getUserInfo(userId);

            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userPw", user.getUserPw());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userAdCheck", user.getUserAdCheck());
            session.setMaxInactiveInterval(60 * 60 * 10); // 세션 유지 시간 설정

            return "redirect:/home";
        }
        else {

            return "redirect:/user/login?loginError=true";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();

        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userPw");

        session.invalidate();

        return "redirect:/home";
    }

    @RequestMapping(value = "/writingList", method = RequestMethod.GET)
    public ModelAndView selectWritingList(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        ModelAndView mv = new ModelAndView("/user/writingList");

        List<FreeBoardDTO> writingList = userService.selectWritingList(userId);

        PageInfo<FreeBoardDTO> boardPageList = new PageInfo<>(userService.selectBoardPageList(pageNum, userId), 3);

        mv.addObject("boardPageList", boardPageList);
        mv.addObject("writingList", writingList);

        return mv;
    }

//    @RequestMapping("/updateUser")
//    public String updateUserView() throws Exception {
//        return "/user/updateUser";
//    }

    @RequestMapping("/userDetail.do")
    public ModelAndView userDetail(@RequestParam String userId) throws Exception {
        ModelAndView mv = new ModelAndView("/user/userDetail");

        UserDTO user = userService.selectUserDetail(userId);
        mv.addObject("user" , user);

        return mv;

    }

    //    회원정보 수정하기
    @RequestMapping("/updateUser.do")
    public String updateUser(UserDTO user, HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();

        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userPw");

        // 서비스에서 사용자 정보 업데이트
        userService.updateUser(user);

        // 세션에 새로운 속성 설정
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("userPw", user.getUserPw());


        return "redirect:/home?updateMsg=true";
    }

    //    회원 탈퇴
    @RequestMapping("/deleteUser.do")
    public String deleteUser(UserDTO user) throws Exception {

        userService.deleteUser(user);

        return "redirect:/user/logout";
    }

}
