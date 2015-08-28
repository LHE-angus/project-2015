package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeShopLeaderRecDao;
import com.ebiz.mmt.domain.PeShopLeaderRec;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeShopLeaderRecDaoSqlMapImpl extends EntityDaoSqlMapImpl<PeShopLeaderRec> implements PeShopLeaderRecDao {

	/**
	 * @author Li,Yuan
	 * @version 2011-05-23
	 */
	@SuppressWarnings("unchecked")
	public List<PeShopLeaderRec> selectPeShopLeaderRecWithNameList(PeShopLeaderRec t) {
		return this.getSqlMapClientTemplate().queryForList("selectPeShopLeaderRecWithNameList", t);
	}

}
