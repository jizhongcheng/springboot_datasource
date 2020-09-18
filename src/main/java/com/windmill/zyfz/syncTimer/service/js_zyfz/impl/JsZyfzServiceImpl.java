package com.windmill.zyfz.syncTimer.service.js_zyfz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.windmill.common.service.BaseService;
import com.windmill.zyfz.api.entity.js_zyfz.JsZyfz;
import com.windmill.zyfz.api.mapper.js_zyfz.JsZyfzMapper;
import com.windmill.zyfz.common.dbconfig.DBTypeEnum;
import com.windmill.zyfz.common.dbconfig.DataSourceSwitch;
import com.windmill.zyfz.syncTimer.service.js_zyfz.IJsZyfzService;;

@Service
public class JsZyfzServiceImpl extends BaseService<JsZyfzMapper, JsZyfz> implements IJsZyfzService {

	@Autowired
	private JsZyfzMapper jsZyfzMapper;

    

}
