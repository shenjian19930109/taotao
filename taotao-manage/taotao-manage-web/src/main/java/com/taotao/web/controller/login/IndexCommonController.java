package com.taotao.web.controller.login;

import com.taotao.service.product.ProductService;
import com.taotao.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by apple on 18/3/14.
 */
@Controller
@RequestMapping("/index")
public class IndexCommonController {

    @Autowired
    private ProductService productService;

    /**
     * 未登录时,展示所有商品列表
     * @return 所有商品信息列表
     * */
    @RequestMapping(value = "listAllProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<ProductVO> listAllProducts() {
        List<ProductVO> productVOs = productService.listAllProducts();
        return productVOs;
    }


    /**
     * TODO 修改该方法,入参加上用户名,获得是否是卖家,以及商品信息,用于前端展示时判断
     * 用于无用户登录时的查看单个商品信息.
     * @param id 商品id
     * @param session
     * @return 返回未登录时查看商品页面
     * */
    @RequestMapping(value = "showOneProduct", method = RequestMethod.GET)
    public String showOneProduct(@RequestParam("id") long id, HttpSession session) {
        ProductVO productVO = productService.selectProductById(id);
        session.setAttribute("productVO", productVO);
        return "look_product";
    }

    /**
     * 清空session中信息,并退出登录
     * @param session
     * @return 返回无用户首页
     * */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // 清空session中的用户信息
        session.removeAttribute("username");
        session.removeAttribute("roleType");
        session.removeAttribute("productVO");
        return "login";
    }

}
