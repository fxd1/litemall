package org.linlinjava.litemall.wx.web.dto;

import org.fengxiaodong.db.bean.Address;
import org.fengxiaodong.db.bean.Good;
import org.linlinjava.litemall.wx.util.JsonUtils;

import java.util.List;

public class OrderDto {


    private List<Good> goodList;

    private List<Address> addressList;

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }
}
