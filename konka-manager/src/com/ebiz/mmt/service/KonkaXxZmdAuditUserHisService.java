package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-19 11:53:08
 */
public interface KonkaXxZmdAuditUserHisService {

	Long createKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t);

	int modifyKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t);

	int removeKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t);

	KonkaXxZmdAuditUserHis getKonkaXxZmdAuditUserHis(KonkaXxZmdAuditUserHis t);
	
	/**
	 * @author lideyu
	 * @version 2014
	 */
	KonkaXxZmdAuditUserHis getKonkaXxZmdAuditUserHisMAX(KonkaXxZmdAuditUserHis t);

	List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisList(KonkaXxZmdAuditUserHis t);

	Long getKonkaXxZmdAuditUserHisCount(KonkaXxZmdAuditUserHis t);

	List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisPaginatedList(KonkaXxZmdAuditUserHis t);
	
	/**
	 * @author Hu,hao
	 * @version 2013
	 */
	List<KonkaXxZmdAuditUserHis> getKonkaXxZmdAuditUserHisForZmdList(KonkaXxZmdAuditUserHis t);
	

}