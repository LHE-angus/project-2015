package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaPdModel;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
public interface KonkaPdModelService {

	Long createKonkaPdModel(KonkaPdModel t);

	int modifyKonkaPdModel(KonkaPdModel t);

	int removeKonkaPdModel(KonkaPdModel t);

	KonkaPdModel getKonkaPdModel(KonkaPdModel t);

	List<KonkaPdModel> getKonkaPdModelList(KonkaPdModel t);

	Long getKonkaPdModelCount(KonkaPdModel t);

	List<KonkaPdModel> getKonkaPdModelPaginatedList(KonkaPdModel t);

}