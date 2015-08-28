package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Jin,QingHua
 */
public interface BaseBrandInfoDao extends EntityDao<BaseBrandInfo> {

	/**
	 * @author Jiang,JiaYong
	 */
	List<BaseBrandInfo> selectBaseBrandInfoForEntpShopList(BaseBrandInfo t) throws DataAccessException;

	/**
	 * @author Li,Yuan
	 */
	List<BaseBrandInfo> selectBaseBrandInfoByPdType(BaseBrandInfo t);

	/**
	 * @author Chen,LiLin
	 * @version 2011-1-24
	 */
	public Long selectBaseBrandInfoForjoinEntpProdCount(BaseBrandInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-10-25
	 */
	public List<BaseBrandInfo> selectBaseBrandInfoForjoinEntpProdList(BaseBrandInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-10-26
	 */
	public BaseBrandInfo selectBaseBrandInfoForjoinEntpProd(BaseBrandInfo t);

	/**
	 * @author Du,HongGang
	 * @version 2010-11-16
	 */
	public List<BaseBrandInfo> selectBaseBrandInfoForPopList(BaseBrandInfo t);
}
