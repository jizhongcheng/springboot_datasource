package com.windmill.zyfz.api.mapper.zyfz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.windmill.zyfz.api.entity.zyfz.ZyfzYjxx;

import tk.mybatis.mapper.common.Mapper;

public interface ZyfzYjxxMapper extends Mapper<ZyfzYjxx> {

	List<Map<String,Object>> queryUser(String id,int age);

	void insertObject(Map<String, String> map);
}
