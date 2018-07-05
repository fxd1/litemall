package org.linlinjava.litemall.wx.service;

import org.fengxiaodong.db.bean.Address;
import org.fengxiaodong.db.bean.AddressExample;
import org.fengxiaodong.db.dao.AddressMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    public List<Address> findAll(int userId){

        return addressMapper.selectByExample(
                new AddressExample()
                .createCriteria()
                .andUserIdEqualTo(userId)
                .andPriorityGreaterThan((byte) -1) // -1 为已经弃用的，值越大，优先级越高
                .example()
        );
    }
}
