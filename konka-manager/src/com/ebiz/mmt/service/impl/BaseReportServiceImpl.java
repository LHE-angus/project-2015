package com.ebiz.mmt.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.service.BaseReportService;

/**
 * @author Liu,ZhiXiang
 * @version 2013-6-24
 */
@Service
public class BaseReportServiceImpl extends SimpleJdbcDaoSupport implements BaseReportService {

	@Override
	public List getBaseReportForArray(String sql) {
		
		
		
		
		final List list = new ArrayList();
		super.getJdbcTemplate().query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				int count = rs.getMetaData().getColumnCount();
				String[] str = new String[count];
				for (int i = 0; i < count; i++) {
					str[i] = rs.getString(i + 1);
				}
				list.add(str);
			}
		});

		return list;
	}

	@Override
	public List getBaseReportForMap(String sql) {

		return super.getJdbcTemplate().queryForList(sql);
	}

	@Override
	public List getBaseReportForBindToArray(String sql, Object[] array) {
		final List list = new ArrayList();
		super.getJdbcTemplate().query(sql, array, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				int count = rs.getMetaData().getColumnCount();
				String[] str = new String[count];// 一行数据
				for (int i = 0; i < count; i++) {
					str[i] = rs.getString(i + 1);
				}
				list.add(str);
			}
		});
		return list;
	}

	@Override
	public void createDataForSql(String sql) {

		super.getJdbcTemplate().execute(sql);
	}

}
