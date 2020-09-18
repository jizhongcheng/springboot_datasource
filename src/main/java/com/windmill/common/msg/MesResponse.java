package com.windmill.common.msg;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 后端返回rest数据的包装类
 *
 * @author HJY
 */
public class MesResponse {
    // 状态码
    @Setter
    @Getter
    private int code;

    // 响应数据
    @Getter
    private Map<String, Object> data;

    // 响应信息
    @Getter
    private Map<String, Object> mes;

    public MesResponse() {
        this.data = new HashMap<String, Object>();
        this.mes = new HashMap<String, Object>();
    }

    public static MesResponse success() {
        MesResponse mesreq = new MesResponse();
//        mesreq.setCode(20000);
        return mesreq;
    }

    public static MesResponse failed() {
        MesResponse mesreq = new MesResponse();
//        mesreq.setCode(50000);
        return mesreq;
    }

    public MesResponse successMes(Object obj) {
        this.mes("success", obj);
        return this;
    }

    public MesResponse errorMes(Object obj) {
        this.mes("error", obj);
        return this;
    }

    public MesResponse treeData(Object obj) {
        this.data("children", obj);
        return this;
    }

    public MesResponse data(String key, Object obj) {
        this.data.put(key, obj);
        return this;
    }

    public MesResponse mes(String key, Object obj) {
        this.mes.put(key, obj);
        return this;
    }
}
