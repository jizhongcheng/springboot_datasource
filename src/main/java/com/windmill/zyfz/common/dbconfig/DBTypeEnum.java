package com.windmill.zyfz.common.dbconfig;

/**
 * @Author DGD
 * @date 2018/2/7.
 */
public enum DBTypeEnum {
//    db1("zyfz"), db2("wlxy"), db3("js_zyfz");
    db1("db1"), db2("db2"), db3("db3");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
