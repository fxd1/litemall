package org.linlinjava.litemall.wx.service;

import org.fengxiaodong.db.bean.Trip;
import org.fengxiaodong.db.bean.TripExample;
import org.fengxiaodong.db.dao.TripMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TripService {

    @Resource
    private TripMapper tripMapper;


    public List<Trip> findAll(){

        return tripMapper.selectByExample(new TripExample().orderBy(Trip.Column.endTime.desc()));
    }

    public void updateById(Trip trip){
        tripMapper.updateByPrimaryKey(trip);
    }


    public Trip find(int tripId){
        return tripMapper.selectByPrimaryKey(tripId);
    }
}
