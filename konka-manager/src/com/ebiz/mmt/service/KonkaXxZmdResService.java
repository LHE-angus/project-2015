package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdRes;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-21 16:06:04
 */
public interface KonkaXxZmdResService {

	Long createKonkaXxZmdRes(KonkaXxZmdRes t);

	int modifyKonkaXxZmdRes(KonkaXxZmdRes t);

	int removeKonkaXxZmdRes(KonkaXxZmdRes t);

	KonkaXxZmdRes getKonkaXxZmdRes(KonkaXxZmdRes t);

	List<KonkaXxZmdRes> getKonkaXxZmdResList(KonkaXxZmdRes t);

	Long getKonkaXxZmdResCount(KonkaXxZmdRes t);

	List<KonkaXxZmdRes> getKonkaXxZmdResPaginatedList(KonkaXxZmdRes t);

}