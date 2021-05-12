package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.PageSupport;
import cn.smbms.uitl.Constants;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/pwdmodify.do")
    public String pwdmodify() {
        return "provider/pwdmodify";
    }

    @RequestMapping("/modify.do")
    public String modify(Integer id, Model model) throws Exception {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/usermodify";
    }

    @RequestMapping("/user.do")
    public String user(User user) throws Exception {
        User user1 = userService.getUserById(user.getId());
        user1.setUserName(user.getUserName());
        user1.setGender(user.getGender());
        user1.setBirthday(user.getBirthday());
        user1.setPhone(user.getPhone());
        user1.setAddress(user.getAddress());
        user1.setUserRole(user.getUserRole());
        userService.modify(user1);
        return "redirect:list.do";
    }

    @RequestMapping("/useradd.do")
    public String useradd() {
        return "user/useradd";
    }

    @RequestMapping("/getUserById.do")
    public String getUserById(Integer id, Model model) throws Exception {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/userview";
    }

    @RequestMapping("/deleteUserById.do")
    @ResponseBody
    public String deleteUserById(Integer id) throws Exception {
        System.out.println(id);
        boolean user = userService.deleteUserById(id);
        if (user) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/userCode_add")
    @ResponseBody
    public String userCode_add(String userCode) throws Exception {
        User user = userService.selectUserCodeExist(userCode);
        if (user != null) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/add.do")
    public Object add(User user, HttpSession session,
                      @RequestParam(value = "idPicPath_1",required = false) MultipartFile file,
                      HttpServletRequest request) throws Exception {

        String realPath = request.getServletContext().getRealPath("/images/");
        file.transferTo(new File(realPath + File.separator + file.getOriginalFilename()));



        user.setIdPicPath("images/" + file.getOriginalFilename());
        User attribute = (User) session.getAttribute(Constants.USER_SESSION);
        user.setCreationDate(new Date());
        user.setCreatedBy(attribute.getId());

        userService.add(user);
        return "redirect:list.do";


    }

    @RequestMapping("/userpwdmodify.do")
    public String pwdModify(String password2, HttpSession session) throws Exception {
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        boolean updatePwd = userService.updatePwd(user.getId(), password2);
        User login = userService.login(user.getUserCode(), password2);
        session.setAttribute(Constants.USER_SESSION, login);
        if (updatePwd) {
            return "redirect:../frame.do";
        } else {
            return "provider/user_pwdmodify";
        }
    }

    @RequestMapping("/checkPwd")
    @ResponseBody
    public String checkPwd(String password, HttpSession session) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        if (session.getAttribute(Constants.USER_SESSION) == null) {
            map.put("login", "false");
            map.put("result", "false");
        } else {
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            String userPassword = user.getUserPassword();
            if (userPassword.equals(password)) {
                map.put("result", "true");
            } else {
                map.put("result", "false");
            }
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/list.do")
    public String list(String queryUserName,
                       @RequestParam(defaultValue = "0") Integer queryUserRole,
                       @RequestParam(defaultValue = "0") Integer currentPageNo,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       Model model) throws Exception {
        int currentPageNo2 = 0;
        if (currentPageNo > 1) {
            currentPageNo2 = (currentPageNo - 1) * pageSize;
        }
        int count = userService.getUserCount(queryUserName, queryUserRole);
        PageSupport pageSupport = new PageSupport(currentPageNo, pageSize, count);
        List<User> userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo2, pageSize);
        List<Role> roleList = roleService.getRoleList();
        model.addAttribute("queryUserName", queryUserName);
        model.addAttribute("queryUserRole", queryUserRole);

        model.addAttribute("pageSupport", pageSupport);
        model.addAttribute("userList", userList);
        model.addAttribute("roleList", roleList);
        return "user/userlist";
    }
}
