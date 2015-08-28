package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.YwtAttendanceSignTimeDao;
import com.ebiz.mmt.domain.YwtAttendanceSignTime;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtAttendanceSignTimeDaoSqlMapImpl extends EntityDaoSqlMapImpl<YwtAttendanceSignTime> implements YwtAttendanceSignTimeDao {

	@Override
	public List<YwtAttendanceSignTime> selectYwtAttendanceSignTimeSignTimeList(
			YwtAttendanceSignTime v) {
		return super.getSqlMapClientTemplate().queryForList("selectYwtAttendanceSignTimeSignTimeList", v);
	}

	@Override
	public List<YwtAttendanceSignTime> selectAllYwtAttendanceSignTimeSignTimeList(
			YwtAttendanceSignTime v) {
		
		return super.getSqlMapClientTemplate().queryForList("selectAllYwtAttendanceSignTimeSignTimeList", v);
	}

}
