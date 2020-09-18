package com.windmill.zyfz.syncTimer.service.wlxy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windmill.common.service.BaseService;
import com.windmill.zyfz.api.entity.zyfz.ZyfzYjxx;
import com.windmill.zyfz.api.mapper.wlxy.WlxyYjxxMapper;
import com.windmill.zyfz.api.mapper.zyfz.ZyfzYjxxMapper;
import com.windmill.zyfz.common.dbconfig.DBTypeEnum;
import com.windmill.zyfz.common.dbconfig.DataSourceSwitch;
import com.windmill.zyfz.syncTimer.service.wlxy.IWlxyAchiService;

@Service
public class WlxyAchiServiceImpl   extends BaseService<ZyfzYjxxMapper, ZyfzYjxx>  implements IWlxyAchiService{

	@Autowired
	private WlxyYjxxMapper yjxxMapper;

	/**
	 * 查询
	 *
	 * @param id id
	 * @param age age
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 */
	@DataSourceSwitch(DBTypeEnum.db1)
	@Override
	public List<Map<String,Object>> queryUser(String id,int age){
		List<Map<String,Object>> list = yjxxMapper.queryUser(id,age);
		return list;
	}
    
}
