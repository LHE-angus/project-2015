package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdAccDetails;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:51
 */
public interface KonkaXxZmdAccDetailsService {

	Long createKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t);

	int modifyKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t);

	int removeKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t);

	KonkaXxZmdAccDetails getKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t);

	List<KonkaXxZmdAccDetails> getKonkaXxZmdAccDetailsList(KonkaXxZmdAccDetails t);

	Long getKonkaXxZmdAccDetailsCount(KonkaXxZmdAccDetails t);

	List<KonkaXxZmdAccDetails> getKonkaXxZmdAccDetailsPaginatedList(KonkaXxZmdAccDetails t);

}