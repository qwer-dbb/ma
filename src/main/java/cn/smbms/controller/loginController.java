package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.uitl.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public String login(String userCode, String userPassword,
                        HttpSession session,
                        Model model,
                        HttpServletResponse response){


        User login = userService.login(userCode, userPassword);
        if (login != null) {
            session.setAttribute(Constants.USER_SESSION, login);
            return "redirect:frame.do";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "../login";
        }
    }

    @RequestMapping("/frame.do")
    public String frame() {
        return "jsp/frame";
    }

}
