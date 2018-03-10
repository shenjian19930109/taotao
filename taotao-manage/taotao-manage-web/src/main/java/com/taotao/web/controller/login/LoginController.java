package com.taotao.web.controller.login;

import com.taotao.bo.UserLoginBO;
import com.taotao.service.login.LoginService;
import com.taotao.web.status.LoginStatusEnum;
import com.taotao.web.status.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by apple on 18/3/1.
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("username")
public class LoginController {

    // 日志工具
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "toLoginPage", method = RequestMethod.GET)
    public String toLoginPage() {
        return "login";
    }

    /**
     * 返回登录结果页
     * @param userLoginBO 通过{@code @ModelAttribute} 注解,自动注入相应属性
     * @return 返回登录结果页.成功,则跳转到首页.失败,则返回登录页.
     * */
    @RequestMapping(value = "toLoginResultPage", method = RequestMethod.GET)
    public String toLoginResultPage(@ModelAttribute("loginForm") UserLoginBO userLoginBO, ModelMap model) {

        LoginStatusEnum loginStatusEnum = loginService.getUserLoginInDB(userLoginBO);

        if (loginStatusEnum.equals(LoginStatusEnum.SUCCESS)) {

            model.addAttribute("username", loginStatusEnum.getUserName());
            if (loginStatusEnum.getRoleType() == RoleType.BUYER){
                return "buyer_login_success";
            }
            return "seller_login_success";
        }else {
            return "login_fail";
        }
    }

}
