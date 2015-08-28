package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JsTimesDao;
import com.ebiz.mmt.domain.JsTimes;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
@Service
public class JsTimesDaoSqlMapImpl extends EntityDaoSqlMapImpl<JsTimes> implements JsTimesDao {
	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-10
	 */

	@SuppressWarnings("unchecked")
	public List<JsTimes> selectJsTimesListForCId(JsTimes t) {
		return super.getSqlMapClientTemplate().queryForList("selectJsTimesListForCId");
	}
}
