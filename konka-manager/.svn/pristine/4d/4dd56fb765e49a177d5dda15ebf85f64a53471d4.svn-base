package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtAttendanceDayDao;
import com.ebiz.mmt.domain.YwtAttendanceDay;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtAttendanceDayDaoSqlMapImpl extends EntityDaoSqlMapImpl<YwtAttendanceDay> implements YwtAttendanceDayDao {

	@Override
	public Long selectYwtAttendanceDayLBCount(YwtAttendanceDay v) {
		
		 return (Long) super.getSqlMapClientTemplate().queryForObject("selectYwtAttendanceDayLBCount", v);
	}

	@Override
	public List<YwtAttendanceDay> selectYwtAttendanceDayLBPaginatedList(
			YwtAttendanceDay v) {
		return super.getSqlMapClientTemplate().queryForList("selectYwtAttendanceDayLBPaginatedList", v);
	}

}
