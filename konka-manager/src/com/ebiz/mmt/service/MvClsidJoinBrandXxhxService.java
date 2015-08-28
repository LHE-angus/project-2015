package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.MvClsidJoinBrandXxhx;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-13 11:37:04
 */
public interface MvClsidJoinBrandXxhxService {

	Long createMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t);

	int modifyMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t);

	int removeMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t);

	MvClsidJoinBrandXxhx getMvClsidJoinBrandXxhx(MvClsidJoinBrandXxhx t);

	List<MvClsidJoinBrandXxhx> getMvClsidJoinBrandXxhxList(MvClsidJoinBrandXxhx t);

	Long getMvClsidJoinBrandXxhxCount(MvClsidJoinBrandXxhx t);

	List<MvClsidJoinBrandXxhx> getMvClsidJoinBrandXxhxPaginatedList(MvClsidJoinBrandXxhx t);

	/**
	 * @author Wu,ShangLong
	 * @version 2011.5.13
	 */
	List<MvClsidJoinBrandXxhx> getBrandsByClsidsXxhxList(MvClsidJoinBrandXxhx t);

}