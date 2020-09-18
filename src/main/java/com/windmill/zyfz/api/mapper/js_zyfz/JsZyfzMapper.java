package com.windmill.zyfz.api.mapper.js_zyfz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.windmill.zyfz.api.entity.js_zyfz.JsZyfz;

import tk.mybatis.mapper.common.Mapper;

public interface JsZyfzMapper extends Mapper<JsZyfz> {

    Long querySqlReturnObjectCounts(Map<String, String> map);

    List<HashMap<String, Object>> queryObjectsAsListBySql(Map<String, String> map);

}
