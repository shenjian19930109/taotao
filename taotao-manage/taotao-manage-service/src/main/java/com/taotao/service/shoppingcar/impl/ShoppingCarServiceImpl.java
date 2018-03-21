package com.taotao.service.shoppingcar.impl;

import com.google.common.collect.Lists;
import com.taotao.mapper.ProductMapper;
import com.taotao.mapper.ShoppingCarMapper;
import com.taotao.po.Product;
import com.taotao.po.ShoppingCar;
import com.taotao.po.ShoppingCarExample;
import com.taotao.service.shoppingcar.ShoppingCarService;
import com.taotao.utils.OrikaMapperUtil;
import com.taotao.vo.ShoppingCarVO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public void clearShoppingCar(List<ShoppingCarVO> shoppingCarVOs) {

    }
}
