package com.windmill.zyfz.syncTimer.contorller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.windmill.zyfz.syncTimer.service.wlxy.IWlxyAchiService;
import com.windmill.zyfz.syncTimer.unit.Calc;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.windmill.zyfz.syncTimer.service.zyfz.IZyfzAchiService;

@CrossOrigin
@Component
@RestController
@RequestMapping(value = "/yjwh")
public class YjwhSyncContorller {

	/**
	 * 日志
	 */
	public static Logger logger = LoggerFactory.getLogger(YjwhSyncContorller.class);

	/**
	 * zyfzService
	 */
	@Autowired
	public IZyfzAchiService zyfzService;
	/**
	 * wlxyService
	 */
	@Autowired
	public IWlxyAchiService wlxyService;


	/**
	 * 查询
	 *
	 * @param
	 * @return
	 */
	@PostMapping(value = "/queryUser")
	public List queryUser(String id,int age) {
		List<Map<String,Object>> list = zyfzService.queryUser(id,age);
		return list;
	}

	/**
	 * 查询
	 *
	 * @param
	 * @return
	 */
	@PostMapping(value = "/queryUser2")
	public List queryUser2(String id,int age) {
		List<Map<String,Object>> list = wlxyService.queryUser(id,age);
		return list;
	}

}


