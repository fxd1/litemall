package org.linlinjava.litemall.wx.web;

import org.fengxiaodong.db.bean.Trip;
import org.linlinjava.litemall.wx.service.AddressService;
import org.linlinjava.litemall.wx.service.ClassService;
import org.linlinjava.litemall.wx.service.GoodService;
import org.linlinjava.litemall.wx.service.OrderService;
import org.linlinjava.litemall.wx.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/")
public class AdminOrderController {


    @Resource
    private OrderService orderService;

    @Resource
    private GoodService goodService;
    @Resource
    private ClassService classService;

    @Resource
    private AddressService addressService;

    @Resource
    private TripService tripService;


    /**
     * 一个行程的 时间段内的 按照 用户维度 去查看
     * @param tripId 行程id
     * @return
     */
    @GetMapping("order")
    public Object orderList(Integer tripId){
        Trip trip = tripService.find(tripId);

        return null;
    }
    @GetMapping
    public Object tripList(){

    }
    @GetMapping("good")
    public Object goodList(){

    }


    public Object fixPrice(){

    }
}
