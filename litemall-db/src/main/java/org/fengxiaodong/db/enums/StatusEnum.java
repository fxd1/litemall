package org.fengxiaodong.db.enums;

public enum  StatusEnum {

    SUBMIT(0,""),

    PAY_NO_SEND(1,""),

    SEND_NO_PAY(2,""),

    SEND_PAY(3,""),

    CACEL(4,""),

    DELETE(5,"");


    private byte code;

    private String desc;

    StatusEnum(int code, String desc) {
        this.code = (byte)code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = (byte)code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


