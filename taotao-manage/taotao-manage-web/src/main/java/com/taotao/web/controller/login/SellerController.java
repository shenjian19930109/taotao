package com.taotao.web.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by apple on 18/3/9.
 */
@Controller
@RequestMapping("/seller")
public class SellerController {


    @RequestMapping(value = "toReleasePage", method = RequestMethod.GET)
    public String toReleasePage() {
        return "release";
    }


}
