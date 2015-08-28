package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPdDao;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
@Service
public class KonkaXxPdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxPd> implements KonkaXxPdDao {

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	public Long selectKonkaXxPdCountForDemo(KonkaXxPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxPdCountForDemo", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxPd> selectKonkaXxPdPaginatedListForDemo(KonkaXxPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxPdPaginatedListForDemo", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-10
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxPd> selectKonkaXxPdToExcelList(KonkaXxPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxPdToExcelList", t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-1-9
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxPd> selectKonkaXxWithUsersPdPaginatedList(KonkaXxPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxWithUsersPdPaginatedList", t);
	}

	/**
	 * @author hu,hao
	 * @version 2012-1-9
	 */
	public Long selectKonkaXxPdWithUserCount(KonkaXxPd t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxPdWithUserCount", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-26
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxPd> selectKonkaXxPdForMdNameList(KonkaXxPd t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxPdForMdNameList", t);
	}
}
