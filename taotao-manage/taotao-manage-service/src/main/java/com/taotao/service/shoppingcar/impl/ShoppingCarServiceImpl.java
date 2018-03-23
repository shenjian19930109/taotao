package com.taotao.service.shoppingcar.impl;

import com.google.common.collect.Lists;
import com.taotao.mapper.BuyerProductMapper;
import com.taotao.mapper.ProductMapper;
import com.taotao.mapper.SellerProductMapper;
import com.taotao.mapper.ShoppingCarMapper;
import com.taotao.po.*;
import com.taotao.service.shoppingcar.ShoppingCarService;
import com.taotao.utils.OrikaMapperUtil;
import com.taotao.vo.ShoppingCarVO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 18/3/20.
 */
@Service("shoppingCarService")
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BuyerProductMapper buyerProductMapper;

    @Autowired
    private SellerProductMapper sellerProductMapper;

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    /**
     * 传入购物车VO的信息,添加到数据库中
     * @param shoppingCarVO 购物车VO
     * @return 插入购物车表时返回的id
     * */
    @Override
    public long addProductIntoShoppingCar(ShoppingCarVO shoppingCarVO) {

        // 将没有补全的信息补全:商品价格\标题
        Long productId = shoppingCarVO.getProductId();
        Product product = productMapper.selectByPrimaryKey(productId);

        shoppingCarVO.setTitle(product.getTitle());
        shoppingCarVO.setPrice(product.getPrice());

        //转化VO-->PO
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();

        mapperFactory.classMap(ShoppingCarVO.class, ShoppingCar.class)
                .byDefault()
                .register();

        ShoppingCar map = mapperFactory.getMapperFacade().map(shoppingCarVO, ShoppingCar.class);
        int id = shoppingCarMapper.insertSelective(map);
        return id;
    }

    /**
     * 列出所有该用户添加到购物车的商品
     * @param username 用户名
     * @return 购物车VO集合
     * */
    @Override
    public List<ShoppingCarVO> listAllShoppingCarProductsByUsername(String username) {

        ShoppingCarExample shoppingCarExample = new ShoppingCarExample();

        shoppingCarExample.createCriteria()
                .andUsernameEqualTo(username)
        .andBoughtEqualTo(false);

        List<ShoppingCar> shoppingCars = shoppingCarMapper.selectByExample(shoppingCarExample);

        // PO-->VO
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();

        mapperFactory.classMap(ShoppingCarVO.class, ShoppingCar.class)
                .byDefault()
                .register();

        ArrayList<ShoppingCarVO> shoppingCarVOs = Lists.newArrayList();

        for (ShoppingCar shoppingCar:
                shoppingCars) {
            shoppingCarVOs.add(mapperFactory.getMapperFacade().map(shoppingCar, ShoppingCarVO.class));
        }
        return shoppingCarVOs;
    }


    /**
     * 清空购物车:1,将该客户(买家)的购物车表bought位置1;
     *          2,在买家商品表中添加记录,记录该买家购买了若干商品;
     *          3,在卖家商品表中更新sold和sold_num字段.
     *
     *
     * */
    @Transactional
    @Override
    public void clearShoppingCar(List<ShoppingCarVO> shoppingCarVOs) {
        try {
            // 1,将该客户(买家)的购物车表bought位置1;
            setShoppingCarToBought(shoppingCarVOs);

            // 2,在买家商品表中添加记录,记录该买家购买了若干商品;
            batchShoppingCarIntoDB(shoppingCarVOs);

            // 3,在卖家商品表中更新sold和sold_num字段.
            updateSellerProductTable(shoppingCarVOs);
        }catch (Exception e) {
            throw new RuntimeException("清空购物车失败!!!" + e);
        }
    }

    // 根据userId和productId唯一确定记录,并更新sold和sold_num字段.
    private void updateSellerProductTable(List<ShoppingCarVO> shoppingCarVOs) {

        for (ShoppingCarVO shoppingCarVO:
                shoppingCarVOs) {

            // 先根据userId和productId查询到记录
            SellerProductExample sellerProductExample = new SellerProductExample();
            sellerProductExample.createCriteria()
                    .andProductIdEqualTo(shoppingCarVO.getProductId());
            List<SellerProduct> sellerProducts = sellerProductMapper.selectByExample(sellerProductExample);
            if (sellerProducts.size() > 0) {

                SellerProduct oldSellerProduct = sellerProducts.get(0);
                // 判断sold是否为true,若为true,表示已卖出,则只需要将soldNum = soldNum + shoppingCarVO.getPurchaseNum();
                // 若为false,表示未卖出,则需要先将sold置为true,再将soldNum = shoppingCarVO.getPurchaseNum();
                Boolean sold = oldSellerProduct.getSold();
                SellerProduct newSellerProduct = new SellerProduct();
                if (sold) {
                    newSellerProduct.setSoldNum(oldSellerProduct.getSoldNum() + shoppingCarVO.getPurchaseNum());
                }else {
                    newSellerProduct.setSold(true);
                    newSellerProduct.setSoldNum(shoppingCarVO.getPurchaseNum());
                }

                SellerProductExample newSellerProductExample = new SellerProductExample();
                newSellerProductExample.createCriteria().andIdEqualTo(oldSellerProduct.getId());
                sellerProductMapper.updateByExampleSelective(newSellerProduct, newSellerProductExample);

            }else {
                throw new RuntimeException("清空购物车时,更新卖家商品表时无法获取到userId和productId对应的记录 : " + shoppingCarVO.getUserId() + ", " + shoppingCarVO.getProductId());
            }
        }
    }

    // 将购物车里的商品全部插入到买家商品表中(批量插入)
    private void batchShoppingCarIntoDB(List<ShoppingCarVO> shoppingCarVOs) {

        // VO-->PO
        MapperFactory mapperFactory = OrikaMapperUtil.newDefaultInstance();

        mapperFactory.classMap(ShoppingCarVO.class, BuyerProduct.class)
                .exclude("id")
                .field("purchaseNum", "soldNum")
                .field("price", "finalPrice")
                .byDefault()
                .register();

        List<BuyerProduct> buyerProducts = Lists.newArrayList();
        for (ShoppingCarVO shoppingCarVO:
                shoppingCarVOs) {
            BuyerProduct buyerProduct = mapperFactory.getMapperFacade().map(shoppingCarVO, BuyerProduct.class);
            buyerProduct.setSold(true);
            buyerProducts.add(buyerProduct);
        }

        int effLen = buyerProductMapper.insertIntoShoppingCarBatch(buyerProducts);

//        System.out.println(effLen);

    }

    // 将该用户的购物车表里的数据置为已购买
    private void setShoppingCarToBought(List<ShoppingCarVO> shoppingCarVOs) {

        ArrayList<Long> ids = Lists.newArrayList();
        for (ShoppingCarVO shoppingCarVO:
                shoppingCarVOs) {
            ids.add(shoppingCarVO.getId());
        }
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setBought(true);
        ShoppingCarExample shoppingCarExample = new ShoppingCarExample();
        shoppingCarExample.createCriteria().andIdIn(ids);
        shoppingCarMapper.updateByExampleSelective(shoppingCar, shoppingCarExample);
    }
}
