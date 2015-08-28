package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseVisitTypeDao;
import com.ebiz.mmt.domain.BaseVisitType;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-23 16:32:16
 */
@Service
public class BaseVisitTypeDaoSqlMapImpl extends EntityDaoSqlMapImpl<BaseVisitType> implements BaseVisitTypeDao {

	@Override
	public List<BaseVisitType> selectBaseVisitTypeByReportTypeList(
			BaseVisitType baseVisitType) {
		return super.getSqlMapClientTemplate().queryForList("selectBaseVisitTypeByReportTypeList", baseVisitType);
	}

}
