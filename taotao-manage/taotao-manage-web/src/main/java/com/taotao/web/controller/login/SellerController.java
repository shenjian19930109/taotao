package com.taotao.web.controller.login;

import com.taotao.service.seller.SellerService;
import com.taotao.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Created by apple on 18/3/9.
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

    // 日志工具
    private static final Logger LOG = LoggerFactory.getLogger(SellerController.class);

    @Autowired
    private SellerService sellerService;

    /**
     * 跳转到发布界面
     * @return 跳转到发布界面
     * */
    @RequestMapping(value = "toReleasePage", method = RequestMethod.GET)
    public String toReleasePage() {
        return "release";
    }

    /**
     * 提交发布信息
     * @param productVO 前端卖家填写的信息
     * @param session session,存放信息,用于回显
     * @return 跳转发布成功页面
     * */
    @RequestMapping(value = "publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(ProductVO productVO, HttpSession session) {

        // 校验productVO内容
        checkoutProductVO(productVO);

        // 将productVO保存到数据库中,并返回id
        long id = sellerService.addProductIntoDB(productVO);

        // 获取数据库中的id后,把id存放在session中返回,跳转到发布成功页面 ,为了减少一次查库开销,直接将属性值赋值到session中

        session.setAttribute("productId", id);

        if ("url".equals(productVO.getPic())) {
            productVO.setFile(productVO.getImage());
        }else {
            productVO.setFile(productVO.getAvatar());
        }
        session.setAttribute("productVO", productVO);
        return "release_success";
    }

    private void checkoutProductVO(ProductVO productVO) {
        if(productVO == null || productVO.getTitle().isEmpty() || productVO.getSummary().isEmpty() ||
                (productVO.getAvatar().isEmpty() && productVO.getImage().isEmpty()) || productVO.getDetail().isEmpty() ||
                productVO.getPrice().compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("前端传入的productVO参数错误!!!");
        }
    }


    /**
     * 跳转展示新产品页面
     * @return 跳转展示新产品页面
     * */
    @RequestMapping(value = "showNewProduct", method = RequestMethod.GET)
    public String showNewProduct() {
        return "show_new_product";
    }


    /**
     * 跳转编辑页面
     * @return 跳转编辑页面
     * */
    @RequestMapping(value = "toEditProduct", method = RequestMethod.GET)
    public String editProduct() {
        return "edit_product";
    }

    /**
     * 提交修改信息
     * @return
     * */
    @RequestMapping(value = "editSubmit", method = RequestMethod.POST)
    public String editSubmit(ProductVO productVO, HttpSession session, @RequestParam("productId") long id) {

        // 根据id更新数据库
        int effLine = sellerService.updateProductById(productVO, id);

        if ("url".equals(productVO.getPic())) {
            productVO.setFile(productVO.getImage());
        }else {
            productVO.setFile(productVO.getAvatar());
        }
        session.setAttribute("productVO", productVO);

        return "edit_success";
    }
}
