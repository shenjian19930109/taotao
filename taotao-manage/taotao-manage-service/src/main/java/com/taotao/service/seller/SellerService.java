package com.taotao.service.seller;

import com.taotao.vo.ProductVO;

/**
 * Created by apple on 18/3/11.
 */
public interface SellerService {

    /**
     * 向数据库添加产品,并返回id
     * @param productVO 产品VO,前端传递过来的产品bean
     * @return 数据库中的产品id
     * */
    long addProductIntoDB(ProductVO productVO);

    int updateProductById(ProductVO productVO, long id);
}
