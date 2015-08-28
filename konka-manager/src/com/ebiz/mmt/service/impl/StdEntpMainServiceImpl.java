package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EntpShopDao;
import com.ebiz.mmt.dao.ShopPdDao;
import com.ebiz.mmt.dao.StdEntpMainDao;
import com.ebiz.mmt.dao.StdEntpUserDao;
import com.ebiz.mmt.dao.UserInfoDao;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.ShopPd;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.service.StdEntpMainService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
@Service
public class StdEntpMainServiceImpl implements StdEntpMainService {

	@Resource
	private StdEntpMainDao stdEntpMainDao;

	@Resource
	private EntpShopDao entpShopDao;

	@Resource
	private StdEntpUserDao stdEntpUserDao;

	@Resource
	private UserInfoDao userInfoDao;

	@Resource
	private ShopPdDao shopPdDao;

	public Long createStdEntpMain(StdEntpMain t) {
		return this.stdEntpMainDao.insertEntity(t);
	}

	public StdEntpMain getStdEntpMain(StdEntpMain t) {
		return this.stdEntpMainDao.selectEntity(t);
	}

	public Long getStdEntpMainCount(StdEntpMain t) {
		return this.stdEntpMainDao.selectEntityCount(t);
	}

	public List<StdEntpMain> getStdEntpMainList(StdEntpMain t) {
		return this.stdEntpMainDao.selectEntityList(t);
	}

	public int modifyStdEntpMain(StdEntpMain t) {
		return this.stdEntpMainDao.updateEntity(t);
	}

	public int removeStdEntpMain(StdEntpMain t) {
		return this.stdEntpMainDao.deleteEntity(t);
	}

	public List<StdEntpMain> getStdEntpMainPaginatedList(StdEntpMain t) {
		return this.stdEntpMainDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	public List<StdEntpMain> getStdEntpMainListForStatistics(StdEntpMain t) {
		return this.stdEntpMainDao.selectStdEntpMainListForStatistics(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	public Long getStdEntpMainPublishCountForStatistics(StdEntpMain t) {
		return this.stdEntpMainDao.selectStdEntpMainPublishCountForStatistics(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-27
	 */
	public Long getStdEntpMainListForStatisticsCount(StdEntpMain t) {
		return this.stdEntpMainDao.selectStdEntpMainListForStatisticsCount(t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2010-08-08
	 */
	@SuppressWarnings("unchecked")
	public int modifyStdEntpMainForBindStdEntpMain(StdEntpMain t) {
		List<StdEntpMain> stdEntpMainList = (List<StdEntpMain>) t.getMap().get("stdEntpMainList");
		List<EntpShop> shopList = (List<EntpShop>) t.getMap().get("shopList");

		if (null != stdEntpMainList && stdEntpMainList.size() > 0) {
			for (StdEntpMain entity : stdEntpMainList) {
				List<StdEntpUser> stdEntpUserList = (List<StdEntpUser>) entity.getMap().get("stdEntpUserList");
				if (null != stdEntpUserList && stdEntpUserList.size() > 0) {
					for (StdEntpUser stdEntpUser : stdEntpUserList) {
						this.stdEntpUserDao.updateEntity(stdEntpUser);
					}
					this.stdEntpMainDao.updateEntity(entity);
				}
			}
		}

		if (null != shopList && shopList.size() > 0) {
			for (EntpShop entpShop : shopList) {
				StdEntpMain sem = new StdEntpMain();
				sem.setShop_id(entpShop.getShop_id());
				Long semCount = this.stdEntpMainDao.selectEntityCount(sem);

				if (!(semCount > 0)) {
					entpShop.setState(1);
					this.entpShopDao.updateEntity(entpShop);
					UserInfo ui = new UserInfo();
					ui.setShop_id(entpShop.getShop_id());

					List<UserInfo> uiList = this.userInfoDao.selectEntityList(ui);
					if (null != uiList && uiList.size() > 0) {
						for (UserInfo userInfo : uiList) {
							userInfo.setUser_state(1);
							this.userInfoDao.updateEntity(userInfo);
						}
					}
					ShopPd shopPd = new ShopPd();
					shopPd.setShop_id(entpShop.getShop_id());
					List<ShopPd> shopPdList = this.shopPdDao.selectEntityList(shopPd);
					if (null != uiList && uiList.size() > 0) {
						for (ShopPd pd : shopPdList) {
							pd.setState(1);
							this.shopPdDao.updateEntity(pd);
						}
					}
				}
			}
		}

		return 1;
	}

	/**
	 * @author Chen,LiLin
	 * @version 2010-08-08
	 */
	@SuppressWarnings( { "unchecked" })
	public int removeAllEntpForMmst(StdEntpMain t) {
		if (null == t.getShop_id()) {
			List<StdEntpUser> stdEntpUserList = (List<StdEntpUser>) t.getMap().get("stdEntpUserList");

			if (null != stdEntpUserList && stdEntpUserList.size() > 0) {
				this.stdEntpUserDao.deleteStdEntpUserForMmst(stdEntpUserList.get(0));
			}
			return this.stdEntpMainDao.deleteStdEntpMainAndUserForMmst(t);
		}

		List<StdEntpMain> stdEntpMainList = (List<StdEntpMain>) t.getMap().get("stdEntpMainList");
		if (null != stdEntpMainList && stdEntpMainList.size() > 0) {
			for (StdEntpMain stdEntpMain : stdEntpMainList) {
				List<StdEntpUser> stdEntpUserList = (List<StdEntpUser>) stdEntpMain.getMap().get("stdEntpUserList");

				if (null != stdEntpUserList && stdEntpUserList.size() > 0) {
					this.stdEntpUserDao.deleteStdEntpUserForMmst(stdEntpUserList.get(0));
				}
				this.stdEntpMainDao.deleteStdEntpMainAndUserForMmst(stdEntpMain);
			}
		}

		UserInfo userInfo = (UserInfo) t.getMap().get("userInfo");
		EntpShop entpShop = (EntpShop) t.getMap().get("entpShop");

		if (null != userInfo) {
			this.userInfoDao.deleteEntity(userInfo);
		}
		if (null != entpShop) {
			this.entpShopDao.deleteEntity(entpShop);
		}

		return 1;
	}

	/**
	 * @author Zhang,DaPeng
	 * @version 2010-09-21
	 */
	// public Long queryShopIdByEntpId(StdEntpMain mai) {
	// return stdEntpMainDao.queryShopIdByEntpId(mai);
	// }

	/**
	 * @author Du,HongGang
	 * @version 2010-12-08
	 */
	public List<StdEntpMain> getStdEntpMainListForPay(StdEntpMain t) throws DataAccessException {
		return this.stdEntpMainDao.selectStdEntpMainListForPay(t);
	}

	/**
	 * @author Li,Ka
	 * @version 2012-3-16
	 * @desc  有密钥登录时查询登录网点企业信息，需要合并买卖提用户和官网用户的所有数据
	 */
	public List<StdEntpMain> getStdEntpMainOrEntpShopList(StdEntpMain t) throws DataAccessException {
		return this.stdEntpMainDao.selectStdEntpMainOrEntpShopList(t);
	}
}
