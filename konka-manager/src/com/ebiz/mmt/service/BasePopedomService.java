package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BasePopedom;

/**
 * @author Jin,QingHua
 */
public interface BasePopedomService {

	Long createBasePopedom(BasePopedom t);

	int modifyBasePopedom(BasePopedom t);

	int removeBasePopedom(BasePopedom t);

	BasePopedom getBasePopedom(BasePopedom t);

	List<BasePopedom> getBasePopedomList(BasePopedom t);

	Long getBasePopedomCount(BasePopedom t);

	List<BasePopedom> getBasePopedomPaginatedList(BasePopedom t);

}
