package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.ModPopedom;

/**
 * @author Jin,QingHua
 */
public interface ModPopedomService {

	Long createModPopedom(ModPopedom t);

	int modifyModPopedom(ModPopedom t);

	int removeModPopedom(ModPopedom t);

	ModPopedom getModPopedom(ModPopedom t);

	List<ModPopedom> getModPopedomList(ModPopedom t);

	Long getModPopedomCount(ModPopedom t);

	List<ModPopedom> getModPopedomPaginatedList(ModPopedom t);

}
