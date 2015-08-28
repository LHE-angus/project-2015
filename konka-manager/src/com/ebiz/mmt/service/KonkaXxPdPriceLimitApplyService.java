package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxPdPriceLimitApply;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:51
 */
public interface KonkaXxPdPriceLimitApplyService {

	Long createKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t);

	int modifyKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t);

	int removeKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t);

	KonkaXxPdPriceLimitApply getKonkaXxPdPriceLimitApply(KonkaXxPdPriceLimitApply t);

	List<KonkaXxPdPriceLimitApply> getKonkaXxPdPriceLimitApplyList(KonkaXxPdPriceLimitApply t);

	Long getKonkaXxPdPriceLimitApplyCount(KonkaXxPdPriceLimitApply t);

	List<KonkaXxPdPriceLimitApply> getKonkaXxPdPriceLimitApplyPaginatedList(KonkaXxPdPriceLimitApply t);

}