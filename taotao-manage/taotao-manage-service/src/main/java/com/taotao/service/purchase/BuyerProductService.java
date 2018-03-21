package com.taotao.service.purchase;

import com.taotao.po.BuyerProductPO;
import com.taotao.vo.BuyerProductVO;

import java.util.List;

/**
 * Created by apple on 18/3/17.
 */
public interface BuyerProductService {

    List<BuyerProductVO> listAllBuyerProductsByUsername(String username);

    List<BuyerProductVO> listAllBuyerProducts();


    BuyerProductVO showBuyerOneProductByProductId(long id);
}
