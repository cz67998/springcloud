package com.wangzhou.order.service.impl;


import com.wangzhou.order.dto.OrderDTO;
import com.wangzhou.order.enums.OrderStatusEnum;
import com.wangzhou.order.enums.PayStatusEnum;
import com.wangzhou.order.dataobject.OrderDetail;
import com.wangzhou.order.dataobject.OrderMaster;
import com.wangzhou.order.enums.ResultEnum;
import com.wangzhou.order.exception.OrderException;
import com.wangzhou.order.repository.OrderDetailRepository;
import com.wangzhou.order.repository.OrderMasterRepository;
import com.wangzhou.order.service.OrderService;
import com.wangzhou.product.DecreaseStockInput;
import com.wangzhou.product.ProductInfoOutput;
import com.wangzhou.product.client.ProductClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wangzhou.order.utils.KeyUtil;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        //查询商品信息（调用商品服务）
        //其实就是从[OrderDetail(detailId=null, orderId=null, productId=1, productName=null, productPrice=null, productQuantity=1, productIcon=null), OrderDetail(detailId=null, orderId=null, productId=1, productName=null, productPrice=null, productQuantity=1, productIcon=null)]
        //取出[1, 1]返回，避免遍历
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        System.out.println(orderDTO.getOrderDetailList());
        System.out.println(productIdList);
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        /**
         * 异步扣库存改造
         * 1.读redis（库存在Redis中保存）
         * 2.收到请求Redis判断是否库存充足，减库存并将新值重新设置进redis   加redis锁，redis没有事务回滚的方法
         * 3.订单服务创建订单写入数据库，并发送消息，订单入库异常，需要手动回滚
         */
        //计算总价
        // BigDecimal支持任意精度，任意长度的浮点数运算
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);//初始化为0
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //总价=单价*数量
                    orderAmout = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);//值为null也会拷贝过去
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        //扣库存
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        System.out.println(orderDTO.getOrderDetailList());
        System.out.println(cartDTOList);
        productClient.decreaseStock(cartDTOList);
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //设置总价
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }


    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        //1.查询订单是否存在  Optional 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        //如果值存在则方法会返回true，否则返回 false。
        if (!orderMaster.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.判断订单状态   如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
        OrderMaster orderMaster1 = orderMaster.get();
        if (OrderStatusEnum.NEW.getCode() != orderMaster1.getOrderStatus()) {
            //如果是新订单，才能设置完结
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3.修改订单状态为完结
        orderMaster1.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster1);
        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAILS_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster1, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
