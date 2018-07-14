package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BuyerServiceImplTest {

    @Autowired
    private BuyerServiceImpl buyerService;

    private static final String openid = "10086";

    @Test
    public void findOrderOne() {
        OrderDTO orderDTO =  buyerService.findOrderOne(openid,"1234567");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    @Transactional
    public void cancelOrder() {
        OrderDTO orderDTO =  buyerService.cancelOrder(openid,"1234567");
        OrderStatusEnum resultStatusEnum = orderDTO.getOrderStatusEnum();
        Assert.assertEquals(OrderStatusEnum.CANCEL,resultStatusEnum);
    }

}