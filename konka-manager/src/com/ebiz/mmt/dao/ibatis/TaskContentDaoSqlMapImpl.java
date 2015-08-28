package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.TaskContentDao;
import com.ebiz.mmt.domain.TaskContent;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * 
 * @author zhou
 * 
 */
@Service
public class TaskContentDaoSqlMapImpl extends EntityDaoSqlMapImpl<TaskContent> implements TaskContentDao {

	@Override
	public Long selectTaskContentYwyReportCount(TaskContent tc) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectTaskYwyReportContentCount",tc);
	}

	@Override
	public List<TaskContent> selectTaskContentYwyReportPaginatedList(
			TaskContent tc) {
		
		return super.getSqlMapClientTemplate().queryForList("selectTaskContentYwyReportPaginatedList", tc);
	}

	@Override
	public Long selectTaskContentCustReportCount(TaskContent tc) {
		
		return null;
	}

	@Override
	public List<TaskContent> selectTaskContentCustReportPaginatedList(
			TaskContent tc) {
		
		return null;
	}

	@Override
	public Long selectTaskReportByCustCount(TaskContent tc) {
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectTaskReportByCustCount",tc);
	}

	@Override
	public List<Map<String, String>> selectTaskReportByCustPaginatedList(TaskContent tc) {
		return super.getSqlMapClientTemplate().queryForList("selectTaskReportByCustPaginatedList", tc);
	}

}
