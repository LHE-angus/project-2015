package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBcompBinding;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
public interface EcBcompBindingService {

	Long createEcBcompBinding(EcBcompBinding t);

	int modifyEcBcompBinding(EcBcompBinding t);

	int removeEcBcompBinding(EcBcompBinding t);

	EcBcompBinding getEcBcompBinding(EcBcompBinding t);

	List<EcBcompBinding> getEcBcompBindingList(EcBcompBinding t);

	Long getEcBcompBindingCount(EcBcompBinding t);

	List<EcBcompBinding> getEcBcompBindingPaginatedList(EcBcompBinding t);

}