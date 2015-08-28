package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSpMdSail;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-10 17:06:35
 */
public interface KonkaSpMdSailService {

	Long createKonkaSpMdSail(KonkaSpMdSail t);

	int modifyKonkaSpMdSail(KonkaSpMdSail t);

	int removeKonkaSpMdSail(KonkaSpMdSail t);

	KonkaSpMdSail getKonkaSpMdSail(KonkaSpMdSail t);

	List<KonkaSpMdSail> getKonkaSpMdSailList(KonkaSpMdSail t);

	Long getKonkaSpMdSailCount(KonkaSpMdSail t);

	List<KonkaSpMdSail> getKonkaSpMdSailPaginatedList(KonkaSpMdSail t);

	/**
	 * 查询指定机型的金额和数量的合计
	 * 
	 * @param KonkaSpMdSail
	 * @return KonkaSpMdSail的map
	 */
	KonkaSpMdSail getKonkaSpMdSailForSum(KonkaSpMdSail t);

}