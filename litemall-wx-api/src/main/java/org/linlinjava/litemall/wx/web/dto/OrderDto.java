package org.linlinjava.litemall.wx.web.dto;

import org.fengxiaodong.db.bean.Address;
import org.fengxiaodong.db.bean.Good;
import org.linlinjava.litemall.wx.util.JsonUtils;

import java.util.List;

public class OrderDto {


    private Integer id;
    private List<Good> goodList;

    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }
}
