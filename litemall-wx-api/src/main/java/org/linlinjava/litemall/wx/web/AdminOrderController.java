package org.linlinjava.litemall.wx.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.fengxiaodong.db.bean.Good;
import org.fengxiaodong.db.bean.Trip;
import org.fengxiaodong.db.bean.User;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.wx.service.AddressService;
import org.linlinjava.litemall.wx.service.ClassService;
import org.linlinjava.litemall.wx.service.GoodService;
import org.linlinjava.litemall.wx.service.OrderService;
import org.linlinjava.litemall.wx.service.TripService;
import org.linlinjava.litemall.wx.service.UserService;
import org.linlinjava.litemall.wx.web.dto.AdminUserUnDeliveryListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 发货管理
 */
@RestController
@RequestMapping("/admin/delivery/")
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

    @Resource
    private UserService userService;


    /**
     * 一个行程的 时间段内的 按照 用户维度 去查看,  概览 每一个用户总共 未发货多少件
     * @param tripId 行程id
     * @return
     */
    @GetMapping("list")
    public Object orderList(Integer tripId){
        Trip trip = tripService.find(tripId);

        Multimap<Integer, Good> unSendByTrip = goodService.findUnSendByTrip(trip);
        Set<Integer> userIds = unSendByTrip.keySet();
        List<User> userList = userService.findByIds(Lists.newArrayList(userIds));

        Map<Integer, AdminUserUnDeliveryListDto> data = Maps.newHashMap();

        for (int userId: userIds){
            Collection<Good> goods = unSendByTrip.get(userId);
            AdminUserUnDeliveryListDto adminUserUnDeliveryListDto = new AdminUserUnDeliveryListDto();
            adminUserUnDeliveryListDto
        }
        return ResponseUtil.ok()

    }


    @GetMapping("unsend/detail")
    public Object detail(Integer userId){

    }
    @GetMapping("good")
    public Object goodList(){

    }


    public Object fixPrice(){

    }
}
