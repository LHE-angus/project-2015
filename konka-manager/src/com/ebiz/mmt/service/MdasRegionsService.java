package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MdasRegions;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-25 10:35:27
 */
public interface MdasRegionsService {

	Long createMdasRegions(MdasRegions t);

	int modifyMdasRegions(MdasRegions t);

	int removeMdasRegions(MdasRegions t);

	MdasRegions getMdasRegions(MdasRegions t);

	List<MdasRegions> getMdasRegionsList(MdasRegions t);

	Long getMdasRegionsCount(MdasRegions t);

	List<MdasRegions> getMdasRegionsPaginatedList(MdasRegions t);

}