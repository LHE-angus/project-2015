package com.ebiz.mmt.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.BaseProvinceListFour;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-03 17:04:13
 */
public interface BaseProvinceListFourService {

	Long createBaseProvinceListFour(BaseProvinceListFour t);

	int modifyBaseProvinceListFour(BaseProvinceListFour t);

	int removeBaseProvinceListFour(BaseProvinceListFour t);

	BaseProvinceListFour getBaseProvinceListFour(BaseProvinceListFour t);

	BaseProvinceListFour getBaseProvinceListFourParent(BaseProvinceListFour t);

	List<BaseProvinceListFour> getBaseProvinceListFourList(BaseProvinceListFour t);

	Long getBaseProvinceListFourCount(BaseProvinceListFour t);

	List<BaseProvinceListFour> getBaseProvinceListFourPaginatedList(BaseProvinceListFour t);

	/**
	 * @author Chen,LiLin
	 * @date 2011-05-16
	 */
	List<BaseProvinceListFour> getBaseProvinceListFourParentList(BaseProvinceListFour t);

	/**
	 * @author Cheng,Bing
	 * @date 2011-11-7
	 */
	List<BaseProvinceListFour> getBaseProvinceListFourSonTreeList(BaseProvinceListFour t);

	List<BaseProvinceListFour> getBaseProvinceListFourForProvinceInList(BaseProvinceListFour t);

	List<BaseProvinceListFour> getBaseProvinceListFourForProvinceNotInList(BaseProvinceListFour t);

	List<BaseProvinceListFour> getBaseProvinceListFourForProvinceList(BaseProvinceListFour t);

	List<BaseProvinceListFour> getBaseProvinceListFourForCityList(BaseProvinceListFour t);

	/**
	 * @author Pan,Gang
	 * @date 2013-09-16 根据省市县取pindex
	 */
	public List<BaseProvinceListFour> getBaseProvinceListFourByCityNameList(BaseProvinceListFour t)
	        throws DataAccessException;
}