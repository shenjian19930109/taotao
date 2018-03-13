package com.taotao.service.seller.impl;

import com.taotao.mapper.ProductMapper;
import com.taotao.po.Product;
import com.taotao.po.ProductExample;
import com.taotao.service.login.impl.LoginServiceImpl;
import com.taotao.service.seller.SellerService;
import com.taotao.utils.OrikaMapperUtil;
import com.taotao.vo.ProductVO;
import ma.glasnost.orika.MapperFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by apple on 18/3/11.
 */
@Service("sellerService")
@Transactional
public class SellerServiceImpl implements SellerService {

    // 日志工具
    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    /**
     * 向数据库添加产品,并返回id
     * @param productVO 产品VO,前端传递过来的产品bean
     * @return 数据库中的产品id
     * */

    @Override
    public long addProductIntoDB(ProductVO productVO) {
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(productVO.getClass(), Product.class)
            .byDefault()
                .register();

        Product product = mapperFactory.getMapperFacade().map(productVO, Product.class);

        product.setCreateTime(new Date());
        productMapper.insertSelective(product);
        return product.getId();
    }

    @Override
    public int updateProductById(ProductVO productVO, long id) {

        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(productVO.getClass(), Product.class)
                .byDefault()
                .register();

        Product product = mapperFactory.getMapperFacade().map(productVO, Product.class);

        product.setId(id);
        int effLine = productMapper.updateByPrimaryKeySelective(product);
        return effLine;
    }




    /*@Override
    public long addProductIntoDB(ProductVO productVO) {

        // 将productVO转化为数据库可识别的productPO
//        Product product = transferProductVOToProduct(productVO);

        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(productVO.getClass(), Product.class)
            .byDefault()
                .register();

        Product product = mapperFactory.getMapperFacade().map(productVO, Product.class);

        // 将productPO添加到数据库中,并返回id
        *//*ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andTitleEqualTo(productVO.getTitle()).andSummaryEqualTo(productVO.getSummary()).andPicEqualTo(productVO.getPic())
        .andImageEqualTo(productVO.getImage()).andFileEqualTo(productVO.getFile()).andAvatarEqualTo(productVO.getAvatar())
        .andDetailEqualTo(productVO.getDetail()).andPriceEqualTo(productVO.getPrice());*//*

        product.setCreateTime(new Date());
        productMapper.insertSelective(product);
        long id = product.getId();
        System.out.println();
        return id;
    }*/

}
