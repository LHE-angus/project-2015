package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcUserFavotrites;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcUserFavotritesService {

	Long createEcUserFavotrites(EcUserFavotrites t);

	int modifyEcUserFavotrites(EcUserFavotrites t);

	int removeEcUserFavotrites(EcUserFavotrites t);

	EcUserFavotrites getEcUserFavotrites(EcUserFavotrites t);

	List<EcUserFavotrites> getEcUserFavotritesList(EcUserFavotrites t);

	Long getEcUserFavotritesCount(EcUserFavotrites t);

	List<EcUserFavotrites> getEcUserFavotritesPaginatedList(EcUserFavotrites t);

}