package org.linlinjava.litemall.wx.service;

import com.google.common.collect.Lists;
import org.fengxiaodong.db.bean.Address;
import org.fengxiaodong.db.bean.Good;
import org.fengxiaodong.db.bean.GoodExample;
import org.fengxiaodong.db.bean.Order;
import org.fengxiaodong.db.dao.AddressMapper;
import org.fengxiaodong.db.dao.ClassMapper;
import org.fengxiaodong.db.dao.GoodMapper;
import org.fengxiaodong.db.dao.OrderMapper;
import org.fengxiaodong.db.dao.UserMapper;
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
    public void submitOrder(List<Good> goodList, Address address) {


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
            addressMapper.insertSelective(address);
        }


    }

    public int createOrder(int userId){
        Order order = new Order();
        order.setUserId(userId);
        order.setPromotionType((byte)Order.PromotionType.NONE.getCode());
        orderMapper.insert(order);
    }


    public List<Good> findAll() {
        return goodMapper.selectByExample(
                new GoodExample()
                        .createCriteria()
                        .andStatusNotIn(Lists.newArrayList(Good.Status.DELETE.getCode(), Good.Status.CACEL.getCode()))
                        .example()
                        .orderBy(Good.Column.updateTime.desc())
        );
    }


    /**
     * 逻辑删除商品
     *
     * @param goodIds 商品id
     */
    public void deleteById(List<Integer> goodIds) {

        List<Good> goodList = goodMapper.selectByExample(new GoodExample()
                .createCriteria().andIdIn(goodIds).example());
        for (Good good : goodList) {
            good.setStatus(Good.Status.DELETE.getCode());
            goodMapper.updateByExampleSelective(good, new GoodExample()
                    .createCriteria().andIdEqualTo(good.getId()).example());
        }

    }

}
