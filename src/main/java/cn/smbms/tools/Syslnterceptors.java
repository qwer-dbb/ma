package cn.smbms.tools;


import cn.smbms.pojo.User;
import cn.smbms.uitl.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Syslnterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            if(user!=null){
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
