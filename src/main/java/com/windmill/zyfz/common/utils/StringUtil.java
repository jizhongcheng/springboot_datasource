package com.windmill.zyfz.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	/**
	 * 将参数变量转换为查询条件
	 * @param filterMap
	 * @return
	 */
	public static Map<String, Object> collectRequestParameters(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("isValid", "T");
		for (Map.Entry<String, String[]> ent : parameterMap.entrySet()) {
			String[] values =removeBlankString(ent.getValue());
			if (values == null)
				continue;
			if (values.length == 1) {
				map.put(ent.getKey(), values[0]);
			} else {
				map.put(ent.getKey(), values);
			}
		}
		return map;
	}

	public static String[] removeBlankString(String[] list) {
		if ((list == null) || (list.length < 1)) {
			return null;
		}
		int notNullItemPos = -1;
		int size = 0;
		for (int i = 0; i < list.length; i++) {
			if (StringUtils.isNoneBlank(new CharSequence[] { list[i] })) {
				size++;
				notNullItemPos = i;
			}
		}
		if (notNullItemPos < 0) {
			return null;
		}
		String[] ta = new String[size];
		size = 0;
		for (int i = 0; i < list.length; i++) {
			if (StringUtils.isNoneBlank(new CharSequence[] { list[i] })) {
				ta[size] = list[i];
				size++;
			}
		}
		return ta;
	}
}
