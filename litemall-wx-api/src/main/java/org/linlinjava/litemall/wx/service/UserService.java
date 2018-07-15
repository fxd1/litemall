package org.linlinjava.litemall.wx.service;

import org.fengxiaodong.db.bean.User;
import org.fengxiaodong.db.bean.UserExample;
import org.fengxiaodong.db.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {



    @Resource
    private UserMapper userMapper;

    public List<User> findByIds(List<Integer> userIds){
        return userMapper.selectByExample(new UserExample().createCriteria()
        .andIdIn(userIds)
        .example());
    }



}
