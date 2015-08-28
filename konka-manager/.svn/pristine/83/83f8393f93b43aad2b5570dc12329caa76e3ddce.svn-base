package com.ebiz.mmt.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.domain.KhjxQueryResult;
import com.ebiz.mmt.service.ChannelDataImportReportService;

@Service
public class ChannelDataImportReportServiceImpl extends SimpleJdbcDaoSupport implements ChannelDataImportReportService {

	@Override
	public List<KhjxQueryResult> getKhjxDataFromChannelDataImport(String sql, Object[] args) {

		final List<KhjxQueryResult> list = new ArrayList<KhjxQueryResult>();

		super.getJdbcTemplate().query(sql, args, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {

				KhjxQueryResult obj = new KhjxQueryResult();
				// 一行有多少列
				int count = rs.getMetaData().getColumnCount();
				for (int i = 0; i < count; i++) {
					if ("DEPT_NAME".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setFgs_name(rs.getString(i + 1));
					}
					if ("L4_DEPT_NAME".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setJyb_name(rs.getString(i + 1));
					}
					if ("L5_DEPT_NAME".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setBsc_name(rs.getString(i + 1));
					}
					if ("COLUMN_1".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setColumn_1(rs.getString(i + 1));
					}
					if ("COLUMN_5".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setColumn_5(rs.getString(i + 1));
					}
					if ("CUSTOMER_TYPE".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setCustomerType(rs.getString(i + 1));
					}
					if ("CUSTOMER_TYPE_NAME".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setCustomerTypeName(rs.getString(i + 1));
					}
					if ("YWY_USER_NAME".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setUserName(rs.getString(i + 1));
					}
					if ("COLUMN_11".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setColumn_11(rs.getString(i + 1));
					}
					if ("c_all_amount".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setC_all_amount(rs.getString(i + 1));
					}
					if ("c_all_money".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setC_all_money(rs.getString(i + 1));
					}
					if ("c_all_k007".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setC_all_k007(rs.getString(i + 1));
					}
					if ("c_all_rb00".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setC_all_rb00(rs.getString(i + 1));
					}
					if ("p_all_amount".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setP_all_amount(rs.getString(i + 1));
					}
					if ("p_all_money".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setP_all_money(rs.getString(i + 1));
					}
					if ("p_amount".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setP_amount(rs.getString(i + 1));
					}
					if ("p_money".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						obj.setP_money(rs.getString(i + 1));
					}
				}
				list.add(obj);
			}
		});
		return list;
	}

	@Override
	public int getKhjxDataFromChannelDataImportCount(String sql, Object[] args) {
		final List<Integer> list = new ArrayList<Integer>();
		super.getJdbcTemplate().query(sql, args, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// 一行有多少列
				int count = rs.getMetaData().getColumnCount();
				for (int i = 0; i < count; i++) {
					if ("howmany".equalsIgnoreCase(rs.getMetaData().getColumnName(i + 1))) {
						list.add(Integer.valueOf(rs.getString(i + 1)));
					}
				}
			}
		});

		return list == null ? 0 : list.get(0);

	}

}
