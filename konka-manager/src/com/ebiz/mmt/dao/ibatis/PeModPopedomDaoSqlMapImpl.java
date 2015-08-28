package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PeModPopedomDao;
import com.ebiz.mmt.domain.PeModPopedom;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-11 15:59:43
 */
@Service
public class PeModPopedomDaoSqlMapImpl extends EntityDaoSqlMapImpl<PeModPopedom> implements PeModPopedomDao {

	/**
	 * @author Ren Zhong
	 * @date 2011-5-18
	 */
	@SuppressWarnings("unchecked")
	public List<PeModPopedom> selectModPopedomResultList(PeModPopedom t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectModPopedomResultList", t);
	}

	/**
	 * @author Li,Yuan
	 * @date 2011-07-05
	 */
	public void deletePeModPopedomInit(PeModPopedom t) throws DataAccessException {
		super.getSqlMapClientTemplate().delete("deletePeModPopedomInit", t);
	}
}
