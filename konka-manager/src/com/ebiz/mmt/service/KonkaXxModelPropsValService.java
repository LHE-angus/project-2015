package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxModelPropsVal;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
public interface KonkaXxModelPropsValService {

	Long createKonkaXxModelPropsVal(KonkaXxModelPropsVal t);

	int modifyKonkaXxModelPropsVal(KonkaXxModelPropsVal t);

	int removeKonkaXxModelPropsVal(KonkaXxModelPropsVal t);

	KonkaXxModelPropsVal getKonkaXxModelPropsVal(KonkaXxModelPropsVal t);

	List<KonkaXxModelPropsVal> getKonkaXxModelPropsValList(KonkaXxModelPropsVal t);

	Long getKonkaXxModelPropsValCount(KonkaXxModelPropsVal t);

	List<KonkaXxModelPropsVal> getKonkaXxModelPropsValPaginatedList(KonkaXxModelPropsVal t);

	void createKonkaXxModelPropsValList(KonkaXxModelPropsVal t);
}