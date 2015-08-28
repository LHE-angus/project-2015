package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdAuditUserHisDao;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-19 11:53:08
 */
@Service
public class KonkaXxZmdAuditUserHisDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdAuditUserHis> implements
		KonkaXxZmdAuditUserHisDao {
	
	/**
	 * @author Hu,hao
	 * @version 2013
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdAuditUserHis> selectKonkaXxZmdAuditUserHisForZmdList(KonkaXxZmdAuditUserHis t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdAuditUserHisForZmdList", t);
	}
	/**
	 * @author lideyu
	 * @version 2014
	 */
	@SuppressWarnings("unchecked")
	public KonkaXxZmdAuditUserHis selectKonkaXxZmdAuditUserHisMAX(KonkaXxZmdAuditUserHis t)
	{
		return(KonkaXxZmdAuditUserHis)super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdAuditUserHisMax", t);
	}
}
