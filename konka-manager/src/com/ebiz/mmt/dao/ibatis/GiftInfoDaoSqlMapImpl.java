package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GiftInfoDao;
import com.ebiz.mmt.domain.GiftInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-07 11:39:53
 */
@Service
public class GiftInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<GiftInfo> implements GiftInfoDao {
	
	/**
	 * @author Hu,Hao
	 * @version 2013-11-07
	 */
	@SuppressWarnings("unchecked")
	public List<GiftInfo> selectGiftInfoForNamePaginatedList(GiftInfo t){
		return super.getSqlMapClientTemplate().queryForList("selectGiftInfoForNamePaginatedList", t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2013-11-09
	 */
	@SuppressWarnings("unchecked")
	public List<GiftInfo> selectGiftInfoResultForListWithCate(GiftInfo t){
		return super.getSqlMapClientTemplate().queryForList("selectGiftInfoResultForListWithCate", t);
	}
}
