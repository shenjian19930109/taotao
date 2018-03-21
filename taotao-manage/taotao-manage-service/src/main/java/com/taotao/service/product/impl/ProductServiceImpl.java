package com.taotao.service.product.impl;

import com.google.common.collect.Lists;
import com.taotao.mapper.ProductMapper;
import com.taotao.po.Product;
import com.taotao.po.ProductExample;
import com.taotao.service.product.ProductService;
import com.taotao.utils.OrikaMapperUtil;
import com.taotao.vo.ProductVO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by apple on 18/3/14.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    /**
     * 列出所有的商品
     * @return 返回商品VO的集合,提供前端展示
     * */
    @Override
    public List<ProductVO> listAllProducts() {

        // 从数据库获取所有数据
        List<Product> products = productMapper.listAll();

        // 将结果转化为前端可以使用的VO(List<Product> ---> List<ProductVO>)

        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(Product.class, ProductVO.class)
                .byDefault()
                .register();

        List<ProductVO> productVOs = Lists.newArrayList();

        for (Product product:
                products) {
            ProductVO productVO = mapperFactory.getMapperFacade().map(product, ProductVO.class);
            productVOs.add(productVO);
        }
        return productVOs;
    }

    @Override
    public ProductVO selectProductById(long id) {

        Product product = productMapper.selectByPrimaryKey(id);

        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(Product.class, ProductVO.class)
                .byDefault()
                .register();

        ProductVO productVO = mapperFactory.getMapperFacade().map(product, ProductVO.class);

        return productVO;
    }




}
