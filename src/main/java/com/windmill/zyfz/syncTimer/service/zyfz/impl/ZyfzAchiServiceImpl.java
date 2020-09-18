package com.windmill.zyfz.syncTimer.service.zyfz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windmill.common.service.BaseService;

import com.windmill.zyfz.api.entity.zyfz.ZyfzYjxx;
import com.windmill.zyfz.api.mapper.zyfz.ZyfzYjxxMapper;
import com.windmill.zyfz.common.dbconfig.DBTypeEnum;
import com.windmill.zyfz.common.dbconfig.DataSourceSwitch;
import com.windmill.zyfz.syncTimer.service.zyfz.IZyfzAchiService;

@Service
public class ZyfzAchiServiceImpl extends BaseService<ZyfzYjxxMapper, ZyfzYjxx> implements IZyfzAchiService {

	/**
	 * service
	 */
	@Autowired
	private ZyfzYjxxMapper yjxxMapper;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

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

	@DataSourceSwitch(DBTypeEnum.db1)
	public String getUser() {
		StringBuffer res = new StringBuffer();
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ZyfzYjxxMapper mapper = sqlSession.getMapper(ZyfzYjxxMapper.class);

		try {
			sqlSession.flushStatements();
		}catch (Exception e) {
			res.append(e.getMessage());
		}
		return res.toString();
	}


	@DataSourceSwitch(DBTypeEnum.db1)
	@Transactional(rollbackFor = Exception.class)
	public String batchInsert(List<HashMap<String, Object>> tableList, List<HashMap<String, Object>> dataList,
			String tableName) {
		StringBuffer res = new StringBuffer();
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ZyfzYjxxMapper mapper = sqlSession.getMapper(ZyfzYjxxMapper.class);
		for (int i = 0; i < dataList.size(); i++) {
			StringBuffer sql = new StringBuffer();
			sql.append("REPLACE INTO " + tableName + " ( ");
			for (int k = 0; k < tableList.size(); k++) {
				sql.append(tableList.get(k).get("NAME").toString() + ",");
			}
			sql.delete(sql.length() - 1, sql.length());
			sql.append(" ) VALUES ( ");
			for (int k = 0; k < tableList.size(); k++) {
				if(tableList.get(k).get("TYPENAME").toString().equals("BIGINT")) {
					sql.append("" + dataList.get(i).get(tableList.get(k).get("NAME")) + ",");
				}else {
					if(dataList.get(i).get(tableList.get(k).get("NAME")) != null) {
						sql.append("'" + dataList.get(i).get(tableList.get(k).get("NAME")) + "',");
					}else{
						sql.append("" + dataList.get(i).get(tableList.get(k).get("NAME")) + ",");
					}
				}
			}
			sql.delete(sql.length() - 1, sql.length());
			sql.append(" ) ");
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("inSQL", sql.toString());
				mapper.insertObject(map);
				//每 500 条提交一次
				if ((i + 1) % 500 == 0) {
					sqlSession.flushStatements();
				}
			}catch (Exception e) {
				res.append(e.getMessage());
			}
		}
		try {
			sqlSession.flushStatements();
		}catch (Exception e) {
			res.append(e.getMessage());
		}
		return res.toString();
	}


}
