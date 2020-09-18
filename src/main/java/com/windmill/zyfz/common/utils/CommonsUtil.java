package com.windmill.zyfz.common.utils;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CommonsUtil {
	/**
	 * @Description: 获取UUID
	 * @param 
	 * @throws
	 */	
	public static String getUUID(){
		String s = UUID.randomUUID().toString().replace("-", "");
		return s;
	}
	
	/**
	 * @Description: 获取当前时间
	 * @param 
	 * @return Timestamp
	 * @throws
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public static Timestamp getCurrentTime(){
		return new Timestamp(System.currentTimeMillis());
	}

	
}
