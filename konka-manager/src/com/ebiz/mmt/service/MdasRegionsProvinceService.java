package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MdasRegionsProvince;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-08-25 10:35:27
 */
public interface MdasRegionsProvinceService {

	Long createMdasRegionsProvince(MdasRegionsProvince t);

	int modifyMdasRegionsProvince(MdasRegionsProvince t);

	int removeMdasRegionsProvince(MdasRegionsProvince t);

	MdasRegionsProvince getMdasRegionsProvince(MdasRegionsProvince t);

	List<MdasRegionsProvince> getMdasRegionsProvinceList(MdasRegionsProvince t);

	Long getMdasRegionsProvinceCount(MdasRegionsProvince t);

	List<MdasRegionsProvince> getMdasRegionsProvincePaginatedList(MdasRegionsProvince t);

}