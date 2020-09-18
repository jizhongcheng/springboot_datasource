package com.windmill.zyfz.syncTimer.service.wlxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface IWlxyAchiService {

	List<Map<String,Object>> queryUser(String id,int age);

}
