package com.taotao.web.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by apple on 18/3/9.
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

    @RequestMapping(value = "toReleasePage", method = RequestMethod.GET)
    public String toReleasePage(HttpSession session) {

//        session.getAttribute("username");// 跨controller共享session
        return "release";
    }
}
