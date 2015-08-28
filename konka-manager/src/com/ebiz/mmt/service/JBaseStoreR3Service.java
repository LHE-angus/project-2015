package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.JBaseStoreR3;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-13 14:54:36
 */
public interface JBaseStoreR3Service {

	Long createJBaseStoreR3(JBaseStoreR3 t);

	int modifyJBaseStoreR3(JBaseStoreR3 t);

	int removeJBaseStoreR3(JBaseStoreR3 t);

	JBaseStoreR3 getJBaseStoreR3(JBaseStoreR3 t);

	List<JBaseStoreR3> getJBaseStoreR3List(JBaseStoreR3 t);

	Long getJBaseStoreR3Count(JBaseStoreR3 t);

	List<JBaseStoreR3> getJBaseStoreR3PaginatedList(JBaseStoreR3 t);

	HashMap getSDFStore(JBaseStoreR3 t);
}