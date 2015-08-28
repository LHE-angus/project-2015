package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdDailyDistDetail;

/**
 * @author Ren,zhong
 * @version 2012-03-22 12:33
 */
public interface KonkaXxZmdDailyDistDetailService {

	Long createKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t);

	int modifyKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t);

	int removeKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t);

	KonkaXxZmdDailyDistDetail getKonkaXxZmdDailyDistDetail(KonkaXxZmdDailyDistDetail t);

	List<KonkaXxZmdDailyDistDetail> getKonkaXxZmdDailyDistDetailList(KonkaXxZmdDailyDistDetail t);

	Long getKonkaXxZmdDailyDistDetailCount(KonkaXxZmdDailyDistDetail t);

	List<KonkaXxZmdDailyDistDetail> getKonkaXxZmdDailyDistDetailPaginatedList(KonkaXxZmdDailyDistDetail t);

}