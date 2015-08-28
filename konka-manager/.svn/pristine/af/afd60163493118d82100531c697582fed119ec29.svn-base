package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdTypeDao;
import com.ebiz.mmt.dao.EntpShopBrandDao;
import com.ebiz.mmt.dao.EntpShopDao;
import com.ebiz.mmt.dao.EntpShopInfoDao;
import com.ebiz.mmt.dao.GeoCnDao;
import com.ebiz.mmt.dao.ShopEmployeeDao;
import com.ebiz.mmt.dao.ShopMemberDao;
import com.ebiz.mmt.dao.ShopMsgDao;
import com.ebiz.mmt.dao.ShopOnsaleInfoDao;
import com.ebiz.mmt.dao.ShopPdDao;
import com.ebiz.mmt.domain.BasePdType;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.EntpShopBrand;
import com.ebiz.mmt.domain.EntpShopInfo;
import com.ebiz.mmt.domain.GeoCn;
import com.ebiz.mmt.domain.ShopEmployee;
import com.ebiz.mmt.domain.ShopMember;
import com.ebiz.mmt.domain.ShopMsg;
import com.ebiz.mmt.domain.ShopOnsaleInfo;
import com.ebiz.mmt.domain.ShopPd;
import com.ebiz.mmt.service.EntpShopService;

/**
 * @author Jin,QingHua
 */
@Service
public class EntpShopServiceImpl implements EntpShopService {

	@Resource
	private EntpShopDao entpShopDao;

	@Resource
	private EntpShopInfoDao entpShopInfoDao;

	@Resource
	private ShopMsgDao shopMsgDao;

	@Resource
	private ShopEmployeeDao shopEmployeeDao;

	@Resource
	private ShopMemberDao shopMemberDao;

	@Resource
	private ShopOnsaleInfoDao shopOnsaleInfoDao;

	@Resource
	private ShopPdDao shopPdDao;

	@Resource
	private EntpShopBrandDao entpShopBrandDao;

//	@Resource
//	private UserInfoDao userInfoDao;

	@Resource
	private BasePdTypeDao basePdTypeDao;

	@Resource
	private GeoCnDao geoCnDao;

	protected void updateEntpShopBrandForMaintEntpShop(EntpShop t) {
		if (null == t.getMain_pd2() || "".equals(t.getMain_pd2()) || 0 == t.getMain_pd2().length()) {
			return;
		}
		// 刪除企业品牌表老数据
		EntpShopBrand esb = new EntpShopBrand();
		esb.getMap().put("delete_by_shop_id", t.getShop_id());
		this.entpShopBrandDao.deleteEntpShopBrandByEntpShop(esb);

		String[] str = t.getMain_pd2().split(",");
		List<EntpShopBrand> entpShopBrandList = new ArrayList<EntpShopBrand>();
		if (null != str && str.length > 0) {
			for (String s : str) {
				EntpShopBrand temp = new EntpShopBrand();
				String[] str1 = s.split(":");
				temp.setShop_id(t.getShop_id());
				temp.setIs_del(Short.valueOf("0"));
				temp.setBrand_id(Long.valueOf(str1[1]));
				temp.setShop_name(t.getShop_name());
				temp.setOrder_value(0);
				temp.setAdd_date(new Date());

				BasePdType bpt = new BasePdType();
				bpt.setPd_type(Long.valueOf(str1[0]));
				bpt = this.basePdTypeDao.selectEntity(bpt);
				temp.setPd_type_sign(bpt.getPd_type_sign());

				entpShopBrandList.add(temp);
			}
		}
		for (EntpShopBrand entpShopBrand : entpShopBrandList) {
			this.entpShopBrandDao.insertEntity(entpShopBrand);
		}
	}

	public Long createEntpShop(EntpShop t) {

		if (null != t.getP_index()) {
			// 按照p_index 更新地理坐标
			GeoCn gc = new GeoCn();
			gc.setIs_del(0);
			gc.setP_index(Long.valueOf(t.getP_index()));
			gc = geoCnDao.selectEntity(gc);
			if (null != gc) {
				t.setG_lat(new BigDecimal(gc.getLat()));
				t.setG_lng(new BigDecimal(gc.getLng()));
			}
		}

		Long shop_id = this.entpShopDao.insertEntity(t);

		// 商铺公告
		if (null != t.getShop_public_notice()) {
			EntpShopInfo shopDesc = new EntpShopInfo();
			shopDesc.setShop_id(shop_id);
			shopDesc.setContent(t.getShop_public_notice());
			shopDesc.setContent_type(new Short("1"));
			this.entpShopInfoDao.insertEntity(shopDesc);
		}

		// 商铺介绍
		if (null != t.getShop_introduction()) {
			EntpShopInfo shopDescAll = new EntpShopInfo();
			shopDescAll.setShop_id(shop_id);
			shopDescAll.setContent(t.getShop_introduction());
			shopDescAll.setContent_type(new Short("2"));
			this.entpShopInfoDao.insertEntity(shopDescAll);
		}

//		if (StringUtils.isNotBlank(t.getMap().get("user_id").toString())
//				&& GenericValidator.isLong(t.getMap().get("user_id").toString())) {
//			// 更新用户商铺ID：shop_id
//			UserInfo ui = new UserInfo();
//			ui.setId((Long) t.getMap().get("user_id"));
//			ui.setShop_id(shop_id);
//			this.userInfoDao.updateEntity(ui);
//		}

		t.setShop_id(shop_id);
		// 更新企业品牌表
		this.updateEntpShopBrandForMaintEntpShop(t);

		return shop_id;
	}

	public EntpShop getEntpShop(EntpShop t) {
		return this.entpShopDao.selectEntity(t);
	}

	public Long getEntpShopCountForMmstAdmin(EntpShop t) {
		return this.entpShopDao.selectEntpShopCountForMmstAdmin(t);
	}

	public Long getEntpShopCount(EntpShop t) {
		return this.entpShopDao.selectEntityCount(t);
	}

	public List<EntpShop> getEntpShopList(EntpShop t) {
		return this.entpShopDao.selectEntityList(t);
	}

	public int modifyEntpShop(EntpShop t) {
		int update_count = this.entpShopDao.updateEntity(t);

		// 商铺公告
		if (null != t.getShop_public_notice()) {
			EntpShopInfo shopDesc = new EntpShopInfo();
			shopDesc.setShop_id(t.getShop_id());
			shopDesc.setContent_type(new Short("1"));
			EntpShopInfo _shopDesc = this.entpShopInfoDao.selectEntity(shopDesc);
			if (null == _shopDesc) {
				shopDesc.setContent(t.getShop_public_notice());
				this.entpShopInfoDao.insertEntity(shopDesc);
			} else {
				shopDesc.setId(_shopDesc.getId());
				shopDesc.setContent(t.getShop_public_notice());
				this.entpShopInfoDao.updateEntity(shopDesc);
			}
		}

		// 商铺介绍
		if (null != t.getShop_introduction()) {
			EntpShopInfo shopDescAll = new EntpShopInfo();
			shopDescAll.setShop_id(t.getShop_id());
			shopDescAll.setContent_type(new Short("2"));
			EntpShopInfo _shopDescAll = this.entpShopInfoDao.selectEntity(shopDescAll);
			if (null == _shopDescAll) {
				shopDescAll.setContent(t.getShop_introduction());
				this.entpShopInfoDao.insertEntity(shopDescAll);
			} else {
				shopDescAll.setId(_shopDescAll.getId());
				shopDescAll.setContent(t.getShop_introduction());
				this.entpShopInfoDao.updateEntity(shopDescAll);
			}
		}

		// 更新企业品牌表
		this.updateEntpShopBrandForMaintEntpShop(t);

		return update_count;
	}

	public int removeEntpShop(EntpShop t) {

		Long shop_id = t.getShop_id();

		// 删除店铺公告和店铺介绍
		EntpShopInfo entpShopInfo = new EntpShopInfo();
		entpShopInfo.setShop_id(shop_id);
		this.entpShopInfoDao.deleteEntity(entpShopInfo);

		// 删除店铺留言
		ShopMsg shopMsg = new ShopMsg();
		shopMsg.setShop_id(shop_id);
		shopMsg.setInfo_state(1);
		this.shopMsgDao.deleteEntity(shopMsg);

		// 解除商铺店员
		ShopEmployee shopEmployee = new ShopEmployee();
		shopEmployee.setShop_id(shop_id);
		shopEmployee.setState(1);
		this.shopEmployeeDao.deleteEntity(shopEmployee);

		// 解除商铺会员
		ShopMember shopMember = new ShopMember();
		shopMember.setShop_id(shop_id);
		shopMember.setState(1);
		this.shopMemberDao.deleteEntity(shopMember);

		// 删除店铺促销信息
		ShopOnsaleInfo shopOnsaleInfo = new ShopOnsaleInfo();
		shopOnsaleInfo.setShop_id(shop_id);
		shopOnsaleInfo.setInfo_state(1);
		this.shopOnsaleInfoDao.deleteEntity(shopOnsaleInfo);

		// 删除商铺产品
		ShopPd shopPd = new ShopPd();
		shopPd.setShop_id(shop_id);
		shopPd.setDel_date(new Date());
		shopPd.setState(1);
		this.shopPdDao.deleteEntity(shopPd);

		// 刪除企业品牌表老数据
		EntpShopBrand esb = new EntpShopBrand();
		// esb.getMap().put("delete_by_entp_id", t.getEntp_id());
		esb.getMap().put("delete_by_shop_id", t.getShop_id());
		this.entpShopBrandDao.deleteEntpShopBrandByEntpShop(esb);

		return this.entpShopDao.deleteEntity(t);
	}

	public List<EntpShop> getEntpShopPaginatedList(EntpShop t) {
		return this.entpShopDao.selectEntityPaginatedList(t);
	}

	public List<EntpShop> getEntpShopPaginatedListForMmstAdmin(EntpShop t) {
		return this.entpShopDao.selectEntpShopPaginatedListForMmstAdmin(t);
	}

	public List<EntpShop> getEntpShopListOrderByMark(EntpShop t) {
		return this.entpShopDao.selectEntpShopListOrderByMark(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-01
	 */
	public Long getEntpShopCountForJDZD(EntpShop t) {
		return this.entpShopDao.selectEntpShopCountForJDZD(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-01
	 */
	public List<EntpShop> getEntpShopPaginatedListForJDZD(EntpShop t) {
		return this.entpShopDao.selectEntpShopPaginatedListForJDZD(t);
	}

	/**
	 * @author Wang,Zhaocai
	 * @version 2010-08-07
	 */
	public Long getEntpShopCountForMMST(EntpShop t) {
		return this.entpShopDao.selectEntpShopCountForMMST(t);
	}

	/**
	 * @author Wang,Zhaocai
	 * @version 2010-08-07
	 */
	public List<EntpShop> getEntpShopPaginatedListForMMST(EntpShop t) {
		return this.entpShopDao.selectEntpShopPaginatedListForMMST(t);
	}

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-03
	 */
	public List<EntpShop> getEntpShopListForMap(EntpShop t) {
		return this.entpShopDao.selectEntpShopListForMap(t);
	}

	/**
	 * @author Xu,XiaoYuan
	 * @version 2010-09-03
	 */
	public List<EntpShop> getEntpShopPaginatedListForMap(EntpShop t) {
		return this.entpShopDao.selectEntpShopPaginatedListForMap(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-04
	 */
	public Long getEntpShopCountForMap(EntpShop t) {
		return this.entpShopDao.selectEntpShopCountForMap(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-07
	 */
	public List<EntpShop> getEntpShopListForXsCountTable(EntpShop t) {
		return this.entpShopDao.selectEntpShopListForXsCountTable(t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2010-09-07
	 */
	public List<EntpShop> getEntpShopGMapAuditStaticsList(EntpShop t) {
		return this.entpShopDao.selectEntpShopGMapAuditStaticsList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public Long getEntpShopCountForMapUpdated(EntpShop t) throws DataAccessException {
		return this.entpShopDao.selectEntpShopCountForMapUpdated(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public List<EntpShop> getEntpShopPaginatedListForMapUpdated(EntpShop t) throws DataAccessException {
		return this.entpShopDao.selectEntpShopPaginatedListForMapUpdated(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-09-20
	 */
	public List<EntpShop> getEntpShopListForMapUpdated(EntpShop t) throws DataAccessException {
		return this.entpShopDao.selectEntpShopListForMapUpdated(t);
	}

	@Override
	public List<EntpShop> getEntShopListWithPdType(EntpShop t) throws DataAccessException {
		return this.entpShopDao.selectEntpShopListWithPdType(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-10-23
	 */
	public List<EntpShop> getEntpShopListForMyInfoOrder(EntpShop t) throws DataAccessException {
		return this.entpShopDao.selectEntpShopListForMyInfoOrder(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-16
	 */
	public Long getEntpShopInFindShopCount(EntpShop t) {
		return this.entpShopDao.selectEntpShopInFindShopCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-16
	 */
	public List<EntpShop> getEntpShopInFindShopPaginatedList(EntpShop t) {
		return this.entpShopDao.selectEntpShopInFindShopPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-27
	 */
	public List<EntpShop> getEntpInfoScores(EntpShop t) {
		return this.entpShopDao.selectEntpInfoScores(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-05-27
	 */
	public List<EntpShop> getEntpShopSellPerMonth(EntpShop t) {
		return this.entpShopDao.selectEntpShopSellPerMonth(t);
	}

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-16
	 */
	public List<EntpShop> getStdEntpMainListForNet(EntpShop t) throws DataAccessException {

		return this.entpShopDao.selectStdEntpMainListForNet(t);
	}

	/**
	 * @author Wu,Yang
	 * @version 2011-09-01
	 */
	public List<EntpShop> getEntpShopForJxcList(EntpShop t) {
		return this.entpShopDao.selectEntpShopForJxcList(t);
	}

}
