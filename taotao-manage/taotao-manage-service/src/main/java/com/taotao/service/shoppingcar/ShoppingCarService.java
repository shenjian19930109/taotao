package com.taotao.service.shoppingcar;

import com.taotao.vo.ShoppingCarVO;

import java.util.List;

/**
 * Created by apple on 18/3/20.
 */
public interface ShoppingCarService {

    long addProductIntoShoppingCar(ShoppingCarVO shoppingCarVO);

    List<ShoppingCarVO> listAllShoppingCarProductsByUsername(String username);

    void clearShoppingCar(List<ShoppingCarVO> shoppingCarVOs);
}
