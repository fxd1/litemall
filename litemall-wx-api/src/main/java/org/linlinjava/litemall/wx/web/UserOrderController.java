package org.linlinjava.litemall.wx.web;

import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fengxiaodong.db.bean.Address;
import org.fengxiaodong.db.bean.Good;
import org.fengxiaodong.db.bean.Order;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.wx.annotation.Json2Bean;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.service.AddressService;
import org.linlinjava.litemall.wx.service.ClassService;
import org.linlinjava.litemall.wx.service.GoodService;
import org.linlinjava.litemall.wx.service.OrderService;
import org.linlinjava.litemall.wx.web.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 订单设计
 *
 */
@RestController
@RequestMapping("/user/order")
public class UserOrderController {
    private final Log logger = LogFactory.getLog(UserOrderController.class);

    @Autowired
    private PlatformTransactionManager txManager;

    @Resource
    private OrderService orderService;

    @Resource
    private GoodService goodService;
    @Resource
    private ClassService classService;

    @Resource
    private AddressService addressService;

    /**
     * 提交订单
     * 1. good 表添加记录
     * 2. 地址表   如果新增的则添加数据 ，  若是存在的则进行刷新数据
     *
     * @param userId 用户ID
     * @return 订单操作结果
     * 成功则 { errno: 0, errmsg: '成功', data: { orderId: xxx } }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("submit")
    public Object submit(@LoginUser Integer userId, @Json2Bean OrderDto orderDto) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (orderDto == null) {
            return ResponseUtil.badArgument();
        }

        //insert
        if (orderDto.getOrder() == null || orderDto.getOrder().getId() == 0) {

            List<Good> goodList = orderDto.getGoodList();


            Address address = orderDto.getAddress();
            if (goodList == null || address == null) {
                return ResponseUtil.badArgument();
            }

            Order order = orderService.createOrder(userId);
            for (Good good : goodList) {
                good.setOrderId(order.getId());
            }

            orderService.submitOrder(order, orderDto.getGoodList(), orderDto.getAddress());
            orderDto.setOrder(order);
            return ResponseUtil.ok(orderDto);
        }

        Order order = orderDto.getOrder();

        //update
        orderService.submitOrder(order, orderDto.getGoodList(), orderDto.getAddress());
        return ResponseUtil.ok(orderDto);
    }


    /**
     * 创建订单
     * 提前准备的  物品分类信息 、 地址信息，用于用户的下单
     *
     * @param userId 用户ID
     * @return 返回
     */
    @GetMapping("preOrder")
    public Object preOrder(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        List<Address> addressList = addressService.findAll(userId);
        List<Class> classList = classService.findAll();
        HashMap<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("address", addressList);
        dataMap.put("class", classList);
        return ResponseUtil.ok(dataMap);

    }

    /**
     * 点击 单个订单，查看订单的详情。
     *
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("detail")
    public Object detailOrder(@LoginUser Integer userId, Integer orderId) {

        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        if (orderId == null) {
            return ResponseUtil.fail();
        }

        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.fail();
        }

        List<Good> goodList = goodService.findListByOrderId(orderId);

        Address address = addressService.findById(order.getAddressId());
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder(order);
        orderDto.setAddress(address);
        orderDto.setGoodList(goodList);

        Map<String, OrderDto> data = Maps.newHashMap();

        data.put("order", orderDto);

        return ResponseUtil.ok(data);
    }


    /**
     * 用户查看我的订单，  按更新时间倒序排列，（除了 已删除、取消两种）
     *
     * @param userId 用户id
     * @return 返回
     */
    @GetMapping("list")
    public Object list(@LoginUser Integer userId) {

        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        List<Order> orderList = orderService.findAll();

        HashMap<String, Object> dataMap = Maps.newHashMap();

        dataMap.put("orderLisr", orderList);

        return ResponseUtil.ok(dataMap);

    }



    @GetMapping("delete")
    public Object delete(@LoginUser Integer userId, List<Integer> orderIds) {

        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (CollectionUtils.isEmpty(orderIds)) {
            return ResponseUtil.fail();
        }
        orderService.deleteById(orderIds);
        return ResponseUtil.ok();
    }



}