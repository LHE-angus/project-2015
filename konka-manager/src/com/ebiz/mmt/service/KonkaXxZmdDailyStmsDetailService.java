package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxZmdDailyStmsDetail;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-21 09:35:55
 */
public interface KonkaXxZmdDailyStmsDetailService {

	Long createKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t);

	int modifyKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t);

	int removeKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t);

	KonkaXxZmdDailyStmsDetail getKonkaXxZmdDailyStmsDetail(KonkaXxZmdDailyStmsDetail t);

	List<KonkaXxZmdDailyStmsDetail> getKonkaXxZmdDailyStmsDetailList(KonkaXxZmdDailyStmsDetail t);

	Long getKonkaXxZmdDailyStmsDetailCount(KonkaXxZmdDailyStmsDetail t);

	List<KonkaXxZmdDailyStmsDetail> getKonkaXxZmdDailyStmsDetailPaginatedList(KonkaXxZmdDailyStmsDetail t);

}