package org.linlinjava.litemall.wx.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.fengxiaodong.db.bean.Good;
import org.fengxiaodong.db.bean.GoodExample;
import org.fengxiaodong.db.bean.Trip;
import org.fengxiaodong.db.dao.GoodMapper;
import org.fengxiaodong.db.enums.StatusEnum;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodService {

    @Resource
    private GoodMapper goodMapper;


    public List<Good> findListByOrderId(int orderId){

        return goodMapper.selectByExample(new GoodExample().createCriteria()
        .andOrderIdEqualTo(orderId).example().orderBy(Good.Column.updateTime.desc()));
    }


    private Multimap<Integer, Good> findByTrip(Trip trip, List<Byte> status){

        List<Good> goodList = goodMapper.selectByExample(new GoodExample()
                .createCriteria()
                .andCreateTimeBetween(trip.getStartTime(), trip.getEndTime())
                .andStatusIn(status)
                .example());

        ArrayListMultimap<Integer, Good> map = ArrayListMultimap.create();

        for (Good good: goodList){
            map.put(good.getUserId(), good);
        }
        return map;
    }

    public Multimap<Integer, Good> findSendByTrip(Trip trip){
        ArrayList<Byte> bytes = Lists.newArrayList(StatusEnum.SEND_PAY.getCode());
        return findByTrip(trip, bytes);
    }

    public Multimap<Integer, Good> findUnSendByTrip(Trip trip){
        ArrayList<Byte> bytes = Lists.newArrayList(StatusEnum.PAY_NO_SEND.getCode(),
                StatusEnum.SUBMIT.getCode());
        return findByTrip(trip, bytes);
    }

}
