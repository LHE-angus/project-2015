package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseCardLevel;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
public interface EcBaseCardLevelService {

	Long createEcBaseCardLevel(EcBaseCardLevel t);

	int modifyEcBaseCardLevel(EcBaseCardLevel t);

	int removeEcBaseCardLevel(EcBaseCardLevel t);

	EcBaseCardLevel getEcBaseCardLevel(EcBaseCardLevel t);

	List<EcBaseCardLevel> getEcBaseCardLevelList(EcBaseCardLevel t);

	Long getEcBaseCardLevelCount(EcBaseCardLevel t);

	List<EcBaseCardLevel> getEcBaseCardLevelPaginatedList(EcBaseCardLevel t);

}