package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobilePayment;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-06 17:18:42
 */
public interface KonkaMobilePaymentService {

	Long createKonkaMobilePayment(KonkaMobilePayment t);

	int modifyKonkaMobilePayment(KonkaMobilePayment t);

	int removeKonkaMobilePayment(KonkaMobilePayment t);

	KonkaMobilePayment getKonkaMobilePayment(KonkaMobilePayment t);

	List<KonkaMobilePayment> getKonkaMobilePaymentList(KonkaMobilePayment t);

	Long getKonkaMobilePaymentCount(KonkaMobilePayment t);

	List<KonkaMobilePayment> getKonkaMobilePaymentPaginatedList(KonkaMobilePayment t);

}