package com.taotao.service.publish.impl;

import com.google.common.collect.Lists;
import com.taotao.mapper.ProductMapper;
import com.taotao.mapper.SellerProductMapper;
import com.taotao.po.SellerProduct;
import com.taotao.po.SellerProductExample;
import com.taotao.po.SellerProductPO;
import com.taotao.service.publish.SellerProductService;
import com.taotao.utils.OrikaMapperUtil;
import com.taotao.vo.SellerProductVO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by apple on 18/3/15.
 */
@Service("sellerProductService")
public class SellerProductServiceImpl implements SellerProductService {

    @Autowired
    private SellerProductMapper sellerProductMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 向tb_seller_product表中插入卖家的发布记录
     * @param userId
     * @param username
     * @param productId
     * @return 插入记录的id
     * */
    @Override
    public long addPublishIntoDB(long userId, String username, long productId) {

        SellerProduct sellerProduct = new SellerProduct();
        sellerProduct.setUserId(userId);
        sellerProduct.setUsername(username);
        sellerProduct.setProductId(productId);

        int id = sellerProductMapper.insertSelective(sellerProduct);

        return id;
    }

    /**
     * 列出所有该卖家发布的商品
     * @param username
     * @return 该买家发布过的商品集合
     * */
    @Override
    public List<SellerProductVO> listAllSellerProducts(String username) {

        // 通过用户名查询该卖家发布的所有商品
        List<SellerProductPO> sellerProductPOs = sellerProductMapper.listAllSellerProductsByUsername(username);

        // 将数据库查询结果集合映射为List<SellerProductVO>
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(SellerProductVO.class, SellerProductPO.class)
                .byDefault()
                .register();

        List<SellerProductVO> sellerProductVOs = Lists.newArrayList();

        for (SellerProductPO sellerProductPO:
                sellerProductPOs) {
            SellerProductVO sellerProductVO = mapperFactory.getMapperFacade().map(sellerProductPO, SellerProductVO.class);
            String sellStatus = sellerProductVO.isSold() ? "已售出" : "未售出";
            sellerProductVO.setSellStatus(sellStatus);
            sellerProductVOs.add(sellerProductVO);
        }
        return sellerProductVOs;
    }

    /**
     * 列出所有卖家发布的未售出的商品
     * @param username
     * @return 所有该卖家发布的未售出商品
     * */
    @Override
    public List<SellerProductVO> listAllUnSoldProducts(String username) {

        // 通过用户名查询该卖家发布的所有未售出的商品
        List<SellerProductPO> sellerProductPOs = sellerProductMapper.listAllUnSoldProductsByUsername(username);

        // 将数据库查询结果集合映射为List<SellerProductVO>
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(SellerProductVO.class, SellerProductPO.class)
                .byDefault()
                .register();

        List<SellerProductVO> sellerProductVOs = Lists.newArrayList();

        for (SellerProductPO sellerProductPO:
                sellerProductPOs) {
            SellerProductVO sellerProductVO = mapperFactory.getMapperFacade().map(sellerProductPO, SellerProductVO.class);
            String sellStatus = sellerProductVO.isSold() ? "已售出" : "未售出";
            sellerProductVO.setSellStatus(sellStatus);
            sellerProductVOs.add(sellerProductVO);
        }
        return sellerProductVOs;
    }

    /**
     * 列出所有卖家发布的已售出的商品
     * @param username
     * @return 所有该卖家发布的已售出商品
     * */
    @Override
    public List<SellerProductVO> listAllSoldProducts(String username) {

        // 通过用户名查询该卖家发布的所有已售出的商品
        List<SellerProductPO> sellerProductPOs = sellerProductMapper.listAllSoldProductsByUsername(username);

        // 将数据库查询结果集合映射为List<SellerProductVO>
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(SellerProductVO.class, SellerProductPO.class)
                .byDefault()
                .register();

        List<SellerProductVO> sellerProductVOs = Lists.newArrayList();

        for (SellerProductPO sellerProductPO:
                sellerProductPOs) {
            SellerProductVO sellerProductVO = mapperFactory.getMapperFacade().map(sellerProductPO, SellerProductVO.class);
            String sellStatus = sellerProductVO.isSold() ? "已售出" : "未售出";
            sellerProductVO.setSellStatus(sellStatus);
            sellerProductVOs.add(sellerProductVO);
        }
        return sellerProductVOs;
    }

    /**
     * 按照商品id查询卖家售卖的商品详情
     * @param id 商品id
     * @return 返回卖家售卖的商品详情
     * */
    @Override
    public SellerProductVO selectSellerOneProductByProductId(long id) {

        // 查询卖家售卖的商品详情,根据商品id
        SellerProductPO sellerProductPO = sellerProductMapper.selectSellerOneProductByProductId(id);

        // 转换
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(SellerProductVO.class, SellerProductPO.class)
                .byDefault()
                .register();

        SellerProductVO sellerProductVO = mapperFactory.getMapperFacade().map(sellerProductPO, SellerProductVO.class);

        return sellerProductVO;
    }

    /**
     * 删除卖家未出售商品
     * @param id 商品id
     * @return 返回卖家登录成功的首页面
     * */
    @Override
    public void deleteSellerProductByProductId(long id) {
        // 删除卖家商品表中的发布信息
        SellerProductExample example = new SellerProductExample();
        example.createCriteria().andProductIdEqualTo(id);
        sellerProductMapper.deleteByExample(example);
        // 删除商品表中的商品
        productMapper.deleteByPrimaryKey(id);
    }
}
