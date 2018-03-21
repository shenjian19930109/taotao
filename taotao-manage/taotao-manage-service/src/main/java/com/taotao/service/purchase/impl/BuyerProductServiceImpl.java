package com.taotao.service.purchase.impl;

import com.google.common.collect.Lists;
import com.taotao.mapper.BuyerProductMapper;
import com.taotao.po.BuyerProductPO;
import com.taotao.service.purchase.BuyerProductService;
import com.taotao.utils.OrikaMapperUtil;
import com.taotao.vo.BuyerProductVO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by apple on 18/3/17.
 */
@Service("buyerProductService")
public class BuyerProductServiceImpl implements BuyerProductService {

    @Autowired
    private BuyerProductMapper buyerProductMapper;
    @Override
    public List<BuyerProductVO> listAllBuyerProductsByUsername(String username) {

        List<BuyerProductPO> buyerProductPOs = buyerProductMapper.listAllBuyerProductsByUsername(username);

        // 转化类型 TODO
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(BuyerProductPO.class, BuyerProductVO.class)
                .byDefault()
                .register();

        for (BuyerProductPO buyerProductPO:
                buyerProductPOs) {
            BuyerProductVO buyerProductVO = mapperFactory.getMapperFacade().map(buyerProductPO, BuyerProductVO.class);
//            buyerProductVO.get

        }

        return null;
    }

    /**
     * 列出买家商品页面的信息
     * @return 列出买家商品列表
     * */
    @Override
    public List<BuyerProductVO> listAllBuyerProducts() {
        List<BuyerProductPO> buyerProductPOs = buyerProductMapper.listAllBuyerProducts();
        // 转化类型 TODO
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(BuyerProductPO.class, BuyerProductVO.class)
                .byDefault()
                .register();
        List<BuyerProductVO> buyerProductVOs = Lists.newArrayList();
        for (BuyerProductPO buyerProductPO:
                buyerProductPOs) {
            BuyerProductVO buyerProductVO = mapperFactory.getMapperFacade().map(buyerProductPO, BuyerProductVO.class);
            String sellStatus = buyerProductVO.isSold() ? "已购买" : "未购买";
            buyerProductVO.setSellStatus(sellStatus);
            buyerProductVOs.add(buyerProductVO);
        }
        return buyerProductVOs;
    }

    /**
     * 根据产品id查询商品
     * @param id 商品id
     * @return 买家商品详情VO
     * */
    @Override
    public BuyerProductVO showBuyerOneProductByProductId(long id) {
        BuyerProductPO buyerProductPO = buyerProductMapper.showBuyerOneProductByProductId(id);
        // 转化
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(BuyerProductPO.class, BuyerProductVO.class)
                .byDefault()
                .register();
        BuyerProductVO buyerProductVO = mapperFactory.getMapperFacade().map(buyerProductPO, BuyerProductVO.class);
        String sellStatus = buyerProductVO.isSold() ? "已购买" : "未购买";
        buyerProductVO.setSellStatus(sellStatus);
        return buyerProductVO;
    }
}
