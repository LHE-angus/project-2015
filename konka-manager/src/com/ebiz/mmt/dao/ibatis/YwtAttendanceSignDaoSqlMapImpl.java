package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.YwtAttendanceSignDao;
import com.ebiz.mmt.domain.YwtAttendanceSign;
import com.ebiz.mmt.domain.YwtAttendanceSignTime;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtAttendanceSignDaoSqlMapImpl extends EntityDaoSqlMapImpl<YwtAttendanceSign> implements YwtAttendanceSignDao {

	@Override
	public Long selectYwtAttendanceSignLBCount(YwtAttendanceSign v) {
		 return (Long) super.getSqlMapClientTemplate().queryForObject("selectYwtAttendanceSignLBCount", v);
	}

	@Override
	public List<Map<String, Object>> selectYwtAttendanceSignLBPaginatedList(
			YwtAttendanceSign v) {
		return super.getSqlMapClientTemplate().queryForList("selectYwtAttendanceSignLBPaginatedList", v);
	}

	@Override
	public Long selectYwtAttendanceSignMobileCount(YwtAttendanceSign v) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectYwtAttendanceSignMobileCount", v);
	}

	@Override
	public List<Map<String, Object>> selectYwtAttendanceSignMobilePaginatedList(
			YwtAttendanceSign v) {
		return super.getSqlMapClientTemplate().queryForList("selectYwtAttendanceSignMobilePaginatedList", v);
	}

	@Override
	public List<Map<String, Object>> selectMonthYwtAttendanceSignList(
			YwtAttendanceSign v) {
		return super.getSqlMapClientTemplate().queryForList("selectMonthYwtAttendanceSignList", v);
	}

	@Override
	public List<Map<String, Object>> selectDaySignList(YwtAttendanceSign v) {
		
		return super.getSqlMapClientTemplate().queryForList("selectDaySignList", v);
	}
	
	
	@Override
	public List<YwtAttendanceSignTime> selectAllYwtAttendanceSignTimeSignTimeList(YwtAttendanceSign v) {
		
		return super.getSqlMapClientTemplate().queryForList("selectAllYwtAttendanceSignTimeSignTimeList", v);
	}

}
