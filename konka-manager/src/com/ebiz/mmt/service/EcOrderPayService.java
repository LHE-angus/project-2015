package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcOrderPay;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-11 16:03:36
 */
public interface EcOrderPayService {

	Long createEcOrderPay(EcOrderPay t);

	int modifyEcOrderPay(EcOrderPay t);

	int removeEcOrderPay(EcOrderPay t);

	EcOrderPay getEcOrderPay(EcOrderPay t);

	List<EcOrderPay> getEcOrderPayList(EcOrderPay t);

	Long getEcOrderPayCount(EcOrderPay t);

	List<EcOrderPay> getEcOrderPayPaginatedList(EcOrderPay t);

}