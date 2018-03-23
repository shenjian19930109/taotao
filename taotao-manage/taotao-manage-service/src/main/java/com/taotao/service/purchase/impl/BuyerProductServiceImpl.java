package com.taotao.service.purchase.impl;

import com.google.common.collect.Lists;
import com.taotao.mapper.BuyerProductMapper;
import com.taotao.po.BuyerProduct;
import com.taotao.po.BuyerProductExample;
import com.taotao.po.BuyerProductPO;
import com.taotao.service.purchase.BuyerProductService;
import com.taotao.utils.OrikaMapperUtil;
import com.taotao.vo.BuyerProductVO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by apple on 18/3/17.
 */
@Service("buyerProductService")
public class BuyerProductServiceImpl implements BuyerProductService {

    @Autowired
    private BuyerProductMapper buyerProductMapper;
    /*@Override
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
    }*/

    /**
     * 列出买家商品页面的信息
     * @return 列出买家商品列表
     * */
    @Override
    public List<BuyerProductVO> listAllBuyerProducts() {
        Set<BuyerProductPO> buyerProductPOs = buyerProductMapper.listAllBuyerProducts();
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
        Iterator<BuyerProductPO> it = buyerProductMapper.showBuyerOneProductByProductId(id).iterator();
        BuyerProductPO buyerProductPO = null;
        while (it.hasNext()) {
            buyerProductPO = it.next();
        }
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

    /**
     * 根据用户名列出未购买商品
     * @param username 用户名
     * @return 该用户未购买的商品集合
     * */
    @Override
    public List<BuyerProductVO> listUnBoughtProductsByUsername(String username) {

        // 查询出所有商品
        Set<BuyerProductPO> buyerProductPOs = buyerProductMapper.listAllBuyerProducts();
        
        // 查询出所有该买家已购买过的商品
        BuyerProductExample buyerProductExample = new BuyerProductExample();
        buyerProductExample.createCriteria()
                .andUsernameEqualTo(username);
        List<BuyerProduct> buyerProducts = buyerProductMapper.selectByExample(buyerProductExample);
        // 从buyerProductPOs中剔除掉所有该买家购买过的商品(根据商品id)
        if (buyerProducts.size() > 0) {
            for (BuyerProduct buyerProduct:
                    buyerProducts) {
                Long productId = buyerProduct.getProductId();
                Iterator<BuyerProductPO> it = buyerProductPOs.iterator();
                while (it.hasNext()) {
                    BuyerProductPO buyerProductPO = it.next();
                    if (productId.equals(buyerProductPO.getId())) {
                        it.remove();
                    }
                }
            }
        }
        // PO-->VO
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
     * 列出该买家的账务信息
     * @param username 用户名
     * @return 该买家的账务列表
     * */
    @Override
    public List<BuyerProductVO> listAccount(String username) {

        List<BuyerProductPO> buyerProductPOs = buyerProductMapper.listAccountByUsername(username);

        // PO-->VO
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();
        mapperFactory.classMap(BuyerProductPO.class, BuyerProductVO.class)
                .byDefault()
                .register();
        List<BuyerProductVO> buyerProductVOs = Lists.newArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        for (BuyerProductPO buyerProductPO:
                buyerProductPOs) {
            BuyerProductVO buyerProductVO = mapperFactory.getMapperFacade().map(buyerProductPO, BuyerProductVO.class);
            // 时间格式化
            Date createTime = buyerProductVO.getCreateTime();
            String createTimeStr = sdf.format(createTime);
            buyerProductVO.setCreateTimeStr(createTimeStr);

            buyerProductVOs.add(buyerProductVO);
        }

        return buyerProductVOs;
    }
}
