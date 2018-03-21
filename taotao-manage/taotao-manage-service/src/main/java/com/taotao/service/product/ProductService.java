package com.taotao.service.product;

import com.taotao.vo.ProductVO;

import java.util.List;

/**
 * Created by apple on 18/3/14.
 */
public interface ProductService {

    /**
     * 列出所有的商品
     * @return 返回商品VO的集合,提供前端展示
     * */
    List<ProductVO> listAllProducts();

    ProductVO selectProductById(long id);

}
