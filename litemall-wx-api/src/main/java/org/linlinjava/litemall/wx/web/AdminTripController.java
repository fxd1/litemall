package org.linlinjava.litemall.wx.web;

import com.google.common.collect.Maps;
import org.fengxiaodong.db.bean.Trip;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.wx.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 行程管理
 */
@RestController
@RequestMapping("/admin/trip/")
public class AdminTripController {


    @Resource
    private TripService tripService;


    @GetMapping("list")
    public Object list(){
        List<Trip> all = tripService.findAll();
        Map<String, Object> map = Maps.newHashMap();
        map.put("tripList", all);
        return ResponseUtil.ok(map);
    }


    @GetMapping("update")
    public Object update(Trip trip){
        tripService.updateById(trip);
        return ResponseUtil.ok();
    }



}
