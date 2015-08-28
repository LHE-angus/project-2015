package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobilePopModel;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-05-04 00:44:48
 */
public interface KonkaMobilePopModelService {

	Long createKonkaMobilePopModel(KonkaMobilePopModel t);

	int modifyKonkaMobilePopModel(KonkaMobilePopModel t);

	int removeKonkaMobilePopModel(KonkaMobilePopModel t);

	KonkaMobilePopModel getKonkaMobilePopModel(KonkaMobilePopModel t);

	List<KonkaMobilePopModel> getKonkaMobilePopModelList(KonkaMobilePopModel t);

	Long getKonkaMobilePopModelCount(KonkaMobilePopModel t);

	List<KonkaMobilePopModel> getKonkaMobilePopModelPaginatedList(KonkaMobilePopModel t);

}