package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PdModelPropsValDao;
import com.ebiz.mmt.domain.PdModelPropsVal;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-21 09:45:12
 */
@Service
public class PdModelPropsValDaoSqlMapImpl extends EntityDaoSqlMapImpl<PdModelPropsVal> implements PdModelPropsValDao {

	/**
	 * @author Chen,LiLin
	 * @date 2011-03-25 11:57:12
	 */
	@SuppressWarnings("unchecked")
	public List<PdModelPropsVal> selectPdModelPropsValWithPropInfoList(PdModelPropsVal t) {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelPropsValWithPropInfoList", t);
	}

	@SuppressWarnings("unchecked")
	public List<String> selectPdModelPropsValGroupByList(PdModelPropsVal t) {
		return super.getSqlMapClientTemplate().queryForList("selectPdModelPropsValGroupByList", t);
	}
}
