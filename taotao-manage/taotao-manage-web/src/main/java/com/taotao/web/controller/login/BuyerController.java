package com.taotao.web.controller.login;

import com.alibaba.fastjson.JSON;
import com.taotao.service.buyer.BuyerService;
import com.taotao.service.login.LoginService;
import com.taotao.service.purchase.BuyerProductService;
import com.taotao.service.shoppingcar.ShoppingCarService;
import com.taotao.vo.BuyerProductVO;
import com.taotao.vo.ShoppingCarVO;
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
 * Created by apple on 18/3/17.
 */
@Controller
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private BuyerProductService buyerProductService;

    @Autowired
    private ShoppingCarService shoppingCarService;

    /**
     * 列出购买所有商品的目录,包括已购买或未购买的
     * @return 所有商品的列表
     * */
    @RequestMapping(value = "listAllBuyerProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<BuyerProductVO> listAllBuyerProducts(@RequestParam("username") String username) {
        List<BuyerProductVO> buyerProductVOs = buyerProductService.listAllBuyerProducts();
        return buyerProductVOs;
    }

    /**
     * 列出购买所有商品的目录,包括已购买或未购买的
     * @return 所有商品的列表
     * */
    @RequestMapping(value = "listUnBoughtProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<BuyerProductVO> listUnBoughtProducts(@RequestParam("username") String username) {
        List<BuyerProductVO> buyerProductVOs = buyerProductService.listUnBoughtProductsByUsername(username);
        return buyerProductVOs;
    }

    /**
     * 跳转到卖家商品的首页
     * @return 跳转到卖家商品的首页
     * */
    @RequestMapping(value = "toBuyerProductsIndex", method = RequestMethod.GET)
    public String toBuyerProductsIndex() {
        return "buyer_login_success";
    }

    /**
     * 跳转到卖家未购买商品的首页
     * @return 跳转到卖家未购买商品的首页
     * */
    @RequestMapping(value = "toUnBoughtProductsIndex", method = RequestMethod.GET)
    public String toUnBoughtProductsIndex() {
        return "buyer_unbought_product";
    }

    /**
     * 根据产品id展示商品
     * @param id 商品id
     * @param session
     * @return 展示买家商品详情页面
     * */
    @RequestMapping(value = "showBuyerOneProduct", method = RequestMethod.GET)
    public String showBuyerOneProduct(@RequestParam("id") long id, HttpSession session) {
        BuyerProductVO buyerProductVO = buyerProductService.showBuyerOneProductByProductId(id);
        session.setAttribute("buyerProductVO", buyerProductVO);
        return "look_buyer_product";
    }

    /**
     * 将前端传递的产品信息添加到购物车表
     * @param productId 产品id
     * @param purchaseNum 购买数量
     * @param session
     * @return 还是跳转到卖家登录成功页面
     * */
    @RequestMapping(value = "addShoppingCar", method = RequestMethod.GET)
    public String addShoppingCar(@RequestParam("purchaseNum") int purchaseNum,@RequestParam("productId")long productId, HttpSession session) {

        // 从session中获取用户名
        String username = (String) session.getAttribute("username");
        // 根据用户名查询id
        long userId = loginService.getUserIdByUsername(username);

        // 准备数据
        ShoppingCarVO shoppingCarVO = new ShoppingCarVO();
        shoppingCarVO.setProductId(productId);
        shoppingCarVO.setUserId(userId);
        shoppingCarVO.setUsername(username);
        shoppingCarVO.setPurchaseNum(purchaseNum);
        // 添加订单数据 TODO 把数据库设计好,并开发
        long id = shoppingCarService.addProductIntoShoppingCar(shoppingCarVO);

        return "buyer_login_success";
    }

    /**
     * 跳转购物车页面
     * @return 跳转购物车页面
     * */
    @RequestMapping(value = "toShoppingCarPage", method = RequestMethod.GET)
    public String toShoppingCarPage() {
        return "shopping_car";
    }

    /**
     * 列出所有该用户添加到购物车的商品
     * @param username 用户名
     * @return 购物车VO集合
     * */
    @RequestMapping(value = "listAllShoppingCarProducts", method = RequestMethod.POST)
    @ResponseBody
    public List<ShoppingCarVO> listAllShoppingCarProducts(@RequestParam("username") String username) {
        List<ShoppingCarVO> shoppingCarVOs = shoppingCarService.listAllShoppingCarProductsByUsername(username);
        return shoppingCarVOs;
    }

    /**
     * 清空购物车
     * @param scJson 前端页面购物车的json字符串
     * @return 跳转到清空购物车成功页面
     * */
    @RequestMapping(value = "clearShoppingCar", method = RequestMethod.GET)
    public String clearShoppingCar(@RequestParam("sc") String scJson, HttpSession session) {
        // 再把json字符串转成list对象集合
        List <ShoppingCarVO> ShoppingCarVOs = JSON.parseArray(scJson, ShoppingCarVO.class);
        // 将解析的前端集合交由service层处理
        shoppingCarService.clearShoppingCar(ShoppingCarVOs);
        session.removeAttribute("totalPrice");

        return "clean_shopping_car_success";
    }

    /**
     * 跳转账务页面
     * @return 跳转账务页面
     * */
    @RequestMapping(value = "toAccountPage", method = RequestMethod.GET)
    private String toAccountPage() {
        return "account";
    }

    /**
     * 列出该买家的账务信息
     * @param username 用户名
     * @return 该买家的账务列表
     * */
    @RequestMapping(value = "listAccount", method = RequestMethod.POST)
    @ResponseBody
    private List<BuyerProductVO> listAccount(@RequestParam("username") String username, HttpSession session) {
        List<BuyerProductVO> buyerProductVOs = buyerProductService.listAccount(username);

        // 计算该买家的所有商品的总价格
        BigDecimal sum = sumAllProductsPrice(buyerProductVOs);
        session.setAttribute("totalPrice", sum.toString());
        return buyerProductVOs;
    }

    private BigDecimal sumAllProductsPrice(List<BuyerProductVO> buyerProductVOs) {
        BigDecimal sum = new BigDecimal(0);
        for (BuyerProductVO buyerProductVO:
                buyerProductVOs) {
            BigDecimal finalPrice = buyerProductVO.getFinalPrice();
            int soldNum = buyerProductVO.getSoldNum();
            sum = sum.add(finalPrice.multiply(new BigDecimal(soldNum)));
        }
        return sum;
    }


}
