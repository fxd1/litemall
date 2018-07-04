package org.linlinjava.litemall.wx.service;

import org.fengxiaodong.db.bean.Address;
import org.fengxiaodong.db.bean.Good;
import org.fengxiaodong.db.dao.AddressMapper;
import org.fengxiaodong.db.dao.ClassMapper;
import org.fengxiaodong.db.dao.GoodMapper;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void submitOrder(List<Good> goodList, List<Address> addressList) {


        for (Good good : goodList) {
            if (good.getId() > 0){
                goodMapper.updateByPrimaryKey(good);
            }else {
                goodMapper.insertSelective(good);
            }
        }

        for (Address address: addressList){
            // 存在 更新，否则 插入
            if (address.getId() > 0){
                addressMapper.updateByPrimaryKeySelective(address);
            }else {
                addressMapper.insertSelective(address);
            }

        }




    }
}
