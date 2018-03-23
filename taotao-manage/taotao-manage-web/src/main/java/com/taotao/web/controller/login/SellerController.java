package com.taotao.web.controller.login;

import com.sun.xml.internal.bind.v2.TODO;
import com.taotao.service.login.LoginService;
import com.taotao.service.product.ProductService;
import com.taotao.service.publish.SellerProductService;
import com.taotao.service.seller.SellerService;
import com.taotao.vo.ProductVO;
import com.taotao.vo.SellerProductVO;
import com.taotao.vo.UserLoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

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

    @Autowired
    private ProductService productService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SellerProductService sellerProductService;

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
     * TODO 将前端传递的productVO存入数据库时,就将file赋值好
     * */
    @RequestMapping(value = "publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(ProductVO productVO, HttpSession session) {

        // 校验productVO内容
        checkoutProductVO(productVO);

        if ("url".equals(productVO.getPic())) {
            productVO.setFile(productVO.getImage());
        }else {
            productVO.setFile(productVO.getAvatar());
        }

        // 根据session中的卖家用户名,查询到卖家id
        String username = (String) session.getAttribute("username");
        long userId = loginService.getIdByUsername(username);

        // 将productVO保存到数据库中,并返回id
        long productId = sellerService.addProductIntoDB(productVO);

        // 再根据商品的id和卖家的id生成卖家和商品的记录,并插入tb_seller_product表
        long id = sellerProductService.addPublishIntoDB(userId, username, productId);

        // 获取数据库中的id后,把id存放在session中返回,跳转到发布成功页面 ,为了减少一次查库开销,直接将属性值赋值到session中

        session.setAttribute("productId", productId);

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
    public String editProduct(@RequestParam("productId") long productId, HttpSession session) {
        SellerProductVO sellerProductVO = sellerProductService.selectSellerOneProductByProductId(productId);
        session.setAttribute("productVO", sellerProductVO);
        session.setAttribute("productId", productId);
        return "edit_product";
    }

    @RequestMapping(value = "toEditNewProduct", method = RequestMethod.GET)
    public String editNewProduct() {
        return "edit_product";
    }

    /**
     * 提交修改信息
     * @return
     * */
    @RequestMapping(value = "editSubmit", method = RequestMethod.POST)
    public String editSubmit(ProductVO productVO, HttpSession session, @RequestParam("productId") long id) {

        if ("url".equals(productVO.getPic())) {
            productVO.setFile(productVO.getImage());
        }else {
            productVO.setFile(productVO.getAvatar());
        }
        // 根据id更新数据库
        int effLine = sellerService.updateProductById(productVO, id);

        session.setAttribute("productVO", productVO);

        return "edit_success";
    }

    /**
     * 列出所有卖家发布的商品
     * @param username
     * @return 所有该卖家发布的商品
     * */
    @RequestMapping(value = "listAllSellerProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<SellerProductVO> listAllSellerProducts(@RequestParam("username") String username) {
        List<SellerProductVO> sellerProductVOs =  sellerProductService.listAllSellerProducts(username);
        return sellerProductVOs;
    }

    @RequestMapping(value = "toSellerAllProductPage", method = RequestMethod.GET)
    public String toSellerAllProductPage() {
        return "seller_login_success";
    }

    @RequestMapping(value = "toSellerUnSoldProductPage", method = RequestMethod.GET)
    public String toSellerUnSoldProductPage() {
        return "seller_unsold_product";
    }

    /**
     * 列出所有卖家发布的未售出的商品
     * @param username
     * @return 所有该卖家发布的未售出商品
     * */
    @RequestMapping(value = "listAllUnSoldProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<SellerProductVO> listAllUnSoldProducts(@RequestParam("username") String username) {
        List<SellerProductVO> sellerProductVOs = sellerProductService.listAllUnSoldProducts(username);
        return sellerProductVOs;
    }

    /**
     * 列出所有卖家发布的已售出的商品
     * @param username
     * @return 所有该卖家发布的已售出商品
     * */
    @RequestMapping(value = "listAllSoldProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<SellerProductVO> listAllSoldProducts(@RequestParam("username") String username) {
        List<SellerProductVO> sellerProductVOs = sellerProductService.listAllSoldProducts(username);
        return sellerProductVOs;
    }


    /**
     * 展示卖家的一个商品
     * @param id 商品id
     * @param session
     * @return 返回到查看卖家商品页面
     * */
    @RequestMapping(value = "showSellerOneProduct", method = RequestMethod.GET)
    public String showSellerOneProduct(@RequestParam("id") long id, HttpSession session) {
        SellerProductVO sellerProductVO = sellerProductService.selectSellerOneProductByProductId(id);
        session.setAttribute("sellerProductVO", sellerProductVO);
        return "look_seller_product";
    }

    /**
     * 删除卖家未出售商品
     * @param id 商品id
     * @return 返回卖家登录成功的首页面
     * */
    @RequestMapping(value = "deleteSellerProduct", method = RequestMethod.GET)
    public String deleteSellerProduct(@RequestParam("id") long id) {
        // 删除数据库中商品表和卖家商品表的数据
        sellerProductService.deleteSellerProductByProductId(id);
        return "seller_login_success";
    }




    /*@RequestMapping(value = "showOneSoldProduct", method = RequestMethod.GET)
    public String showOneSoldProduct(@RequestParam("id") long id, HttpSession session) {
        ProductVO productVO = productService.selectProductById(id);
        session.setAttribute("productVO", productVO);
        return "look_one_sold_product";
    }

    @RequestMapping(value = "showOneUnSoldProduct", method = RequestMethod.GET)
    public String showOneUnSoldProduct(@RequestParam("id") long id, HttpSession session) {
        ProductVO productVO = productService.selectProductById(id);
        session.setAttribute("productVO", productVO);
        return "look_one_unsold_product";
    }*/


}
