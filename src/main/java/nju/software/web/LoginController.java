package nju.software.web;

import nju.software.domain.User;
import nju.software.service.UserService;
import nju.software.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/index.html")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand) throws ParseException {
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(),loginCommand.getPassword());
        if (!isValidUser){
            return new ModelAndView("login","error","用户名或者密码错误。");
        }else {
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(DateUtil.getCurrentDateForSql());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }
    }
}
