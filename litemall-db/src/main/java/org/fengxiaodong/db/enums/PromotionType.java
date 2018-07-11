package org.fengxiaodong.db.enums;

public enum PromotionType {

    NONE(0, ""),
    MAN_JIAN(1, "");

    private int code;
    private String desc;

    PromotionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
