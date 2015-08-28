package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BaseBrandInfo;

/**
 * @author Jin,QingHua
 */
public interface BaseBrandInfoService {

	Long createBaseBrandInfo(BaseBrandInfo t);

	int modifyBaseBrandInfo(BaseBrandInfo t);

	int removeBaseBrandInfo(BaseBrandInfo t);

	BaseBrandInfo getBaseBrandInfo(BaseBrandInfo t);

	List<BaseBrandInfo> getBaseBrandInfoList(BaseBrandInfo t);

	Long getBaseBrandInfoCount(BaseBrandInfo t);

	List<BaseBrandInfo> getBaseBrandInfoPaginatedList(BaseBrandInfo t);

	List<BaseBrandInfo> getBaseBrandInfoForEntpShopList(BaseBrandInfo t);

	List<BaseBrandInfo> getBaseBrandInfoByPdType(BaseBrandInfo t);

	/**
	 * @author Chen,LiLin
	 * @version 2011-1-24
	 */
	public Long getBaseBrandInfoForjoinEntpProdCount(BaseBrandInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-10-25
	 */
	public List<BaseBrandInfo> getBaseBrandInfoForjoinEntpProdList(BaseBrandInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public BaseBrandInfo getBaseBrandInfoForjoinEntpProd(BaseBrandInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-11-16
	 */
	public List<BaseBrandInfo> getBaseBrandInfoForPopList(BaseBrandInfo t);
}
