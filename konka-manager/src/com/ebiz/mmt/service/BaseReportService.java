package com.ebiz.mmt.service;

import java.util.List;

/**
 * 
 * @author Liu,ZhiXiang
 * @version 2013-6-24
 */
public interface BaseReportService {

	List getBaseReportForArray(String sql);
	
	List getBaseReportForMap(String sql);
	
	public List getBaseReportForBindToArray(String sql,Object[] array);
	
	void createDataForSql(String sql);

}