package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobilePaymentChis;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-06 17:18:42
 */
public interface KonkaMobilePaymentChisService {

	Long createKonkaMobilePaymentChis(KonkaMobilePaymentChis t);

	int modifyKonkaMobilePaymentChis(KonkaMobilePaymentChis t);

	int removeKonkaMobilePaymentChis(KonkaMobilePaymentChis t);

	KonkaMobilePaymentChis getKonkaMobilePaymentChis(KonkaMobilePaymentChis t);

	List<KonkaMobilePaymentChis> getKonkaMobilePaymentChisList(KonkaMobilePaymentChis t);

	Long getKonkaMobilePaymentChisCount(KonkaMobilePaymentChis t);

	List<KonkaMobilePaymentChis> getKonkaMobilePaymentChisPaginatedList(KonkaMobilePaymentChis t);

}