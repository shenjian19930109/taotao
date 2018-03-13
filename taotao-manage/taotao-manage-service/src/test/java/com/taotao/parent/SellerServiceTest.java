package com.taotao.parent;

import com.taotao.service.seller.SellerService;
import com.taotao.service.seller.impl.SellerServiceImpl;
import com.taotao.vo.ProductVO;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

/**
 * Created by apple on 18/3/12.
 */
public class SellerServiceTest {


    @Test
    public void testAddProductIntoDB() {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");

        SellerService sellerService = (SellerService) ac.getBean("sellerService");

        ProductVO productVO = new ProductVO("电脑3", "笔记本电脑3", "url", "http://www.baidu.com", "", "", "macpro笔记本", new BigDecimal(18999));

        long l = sellerService.addProductIntoDB(productVO);

        System.out.println(l);
    }
}
