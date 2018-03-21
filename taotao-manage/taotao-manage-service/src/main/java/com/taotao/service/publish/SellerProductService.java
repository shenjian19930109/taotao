package com.taotao.service.publish;

import com.taotao.vo.SellerProductVO;

import java.util.List;

/**
 * Created by apple on 18/3/15.
 */
public interface SellerProductService {


    long addPublishIntoDB(long userId, String username, long productId);

    List<SellerProductVO> listAllSellerProducts(String username);

    List<SellerProductVO> listAllUnSoldProducts(String username);

    List<SellerProductVO> listAllSoldProducts(String username);


    SellerProductVO selectSellerOneProductByProductId(long id);

    void deleteSellerProductByProductId(long id);
}
