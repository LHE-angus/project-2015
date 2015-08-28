package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.FighterInfo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-10-25 11:16:14
 */
public interface FighterInfoService {

	Long createFighterInfo(FighterInfo t);

	int modifyFighterInfo(FighterInfo t);

	int removeFighterInfo(FighterInfo t);

	FighterInfo getFighterInfo(FighterInfo t);

	List<FighterInfo> getFighterInfoList(FighterInfo t);

	Long getFighterInfoCount(FighterInfo t);

	List<FighterInfo> getFighterInfoPaginatedList(FighterInfo t);

}