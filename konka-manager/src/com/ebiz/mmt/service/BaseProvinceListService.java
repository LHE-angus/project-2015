package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BaseProvinceList;


public interface BaseProvinceListService {

	int modifyBaseProvinceList(BaseProvinceList t);

	BaseProvinceList getBaseProvinceList(BaseProvinceList t);


	List<BaseProvinceList> getBaseProvinceListList(BaseProvinceList t);


	Long getBaseProvinceListCount(BaseProvinceList t);


	List<BaseProvinceList> getBaseProvinceListPaginatedList(BaseProvinceList t);

	/**
	 * @author Xing,XiuDong
	 */

	List<BaseProvinceList> getBaseProvinceListParentList(BaseProvinceList t);


	/**
	 * @author Chen,Lilin
	 */

	public List<BaseProvinceList> getBaseProvinceListChildrenList(BaseProvinceList t);

	
	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 */

	List<BaseProvinceList> getBaseProvinceListAllParPindexByPindex(BaseProvinceList t);
}