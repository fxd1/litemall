package org.linlinjava.litemall.admin.dao;

import org.fengxiaodong.db.domain.LitemallGoods;
import org.fengxiaodong.db.domain.LitemallGoodsAttribute;
import org.fengxiaodong.db.domain.LitemallGoodsSpecification;

public class GoodsAllinone {
    LitemallGoods goods;
    LitemallGoodsSpecification[] specifications;
    LitemallGoodsAttribute[] attributes;
    // 这里采用 Product 再转换到 LitemallProduct
    Product[] products;

    public LitemallGoods getGoods() {
        return goods;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void setGoods(LitemallGoods goods) {
        this.goods = goods;
    }

    public LitemallGoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(LitemallGoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public LitemallGoodsAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(LitemallGoodsAttribute[] attributes) {
        this.attributes = attributes;
    }

}
