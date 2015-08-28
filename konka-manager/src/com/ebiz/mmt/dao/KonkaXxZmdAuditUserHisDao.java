package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-19 11:53:08
 */
public interface KonkaXxZmdAuditUserHisDao extends EntityDao<KonkaXxZmdAuditUserHis> {

	/**
	 * @author Hu,hao
	 * @version 2013
	 */
	List<KonkaXxZmdAuditUserHis> selectKonkaXxZmdAuditUserHisForZmdList(KonkaXxZmdAuditUserHis t);
	/**
	 * @author lideyu
	 * @version 2014
	 */
	KonkaXxZmdAuditUserHis selectKonkaXxZmdAuditUserHisMAX(KonkaXxZmdAuditUserHis t);
}
