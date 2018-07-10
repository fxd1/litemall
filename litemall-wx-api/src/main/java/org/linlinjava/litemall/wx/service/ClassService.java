package org.linlinjava.litemall.wx.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassService {


    @Resource
    private ClassMapper classMapper;


    public List<Class> findAll(){
        ClassExample classExample = new ClassExample();
        ClassExample.Criteria criteria = classExample.createCriteria();
        return classMapper.selectByExample(classExample);
    }


}
