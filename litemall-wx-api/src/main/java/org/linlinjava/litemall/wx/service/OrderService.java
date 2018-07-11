package org.linlinjava.litemall.wx.service;

import com.google.common.collect.Lists;
import org.fengxiaodong.db.bean.Address;
import org.fengxiaodong.db.bean.Good;
import org.fengxiaodong.db.bean.Order;
import org.fengxiaodong.db.bean.OrderExample;
import org.fengxiaodong.db.dao.AddressMapper;
import org.fengxiaodong.db.dao.ClassMapper;
import org.fengxiaodong.db.dao.GoodMapper;
import org.fengxiaodong.db.dao.OrderMapper;
import org.fengxiaodong.db.dao.UserMapper;
import org.fengxiaodong.db.enums.PromotionType;
import org.fengxiaodong.db.enums.StatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private GoodMapper goodMapper;
    @Resource
    private ClassMapper classMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private OrderMapper orderMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void submitOrder(Order order, List<Good> goodList, Address address) {


        for (Good good : goodList) {
            if (good.getId() > 0) {
                goodMapper.updateByPrimaryKey(good);
            } else {
                goodMapper.insertSelective(good);
            }
        }

        // 存在 更新，否则 插入
        if (address.getId() > 0) {
            addressMapper.updateByPrimaryKeySelective(address);
        } else {
            int i = addressMapper.insertSelective(address);
            address.setId(i);
        }

        order.setAddressId(address.getId());

        orderMapper.updateByExampleSelective(order, new OrderExample()
                .createCriteria().andIdEqualTo(order.getId())
                .example(), Order.Column.addressId);

    }

    public Order createOrder(int userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setPromotionType((byte) PromotionType.NONE.getCode());
        order.setStatus((byte) StatusEnum.SUBMIT.getCode());
        orderMapper.insert(order);
        return order;
    }


    public List<Order> findAll() {
        return orderMapper.selectByExample(
                new OrderExample()
                        .createCriteria()
                        .andStatusNotIn(Lists.newArrayList(StatusEnum.DELETE.getCode(), StatusEnum.CACEL.getCode()))
                        .example()
                        .orderBy(Order.Column.updateTime.desc())
        );
    }


    /**
     * 逻辑删除订单
     */
    public void deleteById(List<Integer> orderId) {

        List<Order> orderList = orderMapper.selectByExample(new OrderExample()
                .createCriteria().andIdIn(orderId).example());
        for (Order order : orderList) {
            order.setStatus(StatusEnum.DELETE.getCode());
            orderMapper.updateByExampleSelective(order, new OrderExample()
                            .createCriteria().andIdEqualTo(order.getId()).example(),
                    Order.Column.status);
        }

    }

}
