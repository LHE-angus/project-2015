package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JsSellsDao;
import com.ebiz.mmt.domain.JsSells;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
@Service
public class JsSellsDaoSqlMapImpl extends EntityDaoSqlMapImpl<JsSells> implements JsSellsDao {

	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-16
	 */
	@SuppressWarnings("unchecked")
	public List<JsSells> selectJsSellsListForML(JsSells t) {
		return super.getSqlMapClientTemplate().queryForList("selectJsSellsListForML", t);
	}
}
