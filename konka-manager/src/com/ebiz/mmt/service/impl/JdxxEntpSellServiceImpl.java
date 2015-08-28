package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BuyerInfoDao;
import com.ebiz.mmt.dao.JdxxEntpSellDao;
import com.ebiz.mmt.dao.JxcCustomerDao;
import com.ebiz.mmt.dao.JxcPdDao;
import com.ebiz.mmt.dao.JxcSellBillDao;
import com.ebiz.mmt.dao.JxcSellBillDetailsDao;
import com.ebiz.mmt.dao.MmtShopCustomerDao;
import com.ebiz.mmt.dao.PdModelDao;
import com.ebiz.mmt.dao.ShopPdDao;
import com.ebiz.mmt.domain.BuyerInfo;
import com.ebiz.mmt.domain.JdxxEntpSell;
import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.MmtShopCustomer;
import com.ebiz.mmt.domain.PdModel;
import com.ebiz.mmt.domain.ShopPd;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.service.JdxxEntpSellService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-05-28 16:48:39
 */
@Service
public class JdxxEntpSellServiceImpl implements JdxxEntpSellService {

	@Resource
	private JdxxEntpSellDao jdxxEntpSellDao;

	@Resource
	private JxcPdDao jxcPdDao;

	@Resource
	private JxcCustomerDao jxcCustomerDao;

	@Resource
	private JxcSellBillDetailsDao jxcSellBillDetailsDao;

	@Resource
	private JxcSellBillDao jxcSellBillDao;

	@Resource
	private BuyerInfoDao buyerInfoDao;

	@Resource
	private ShopPdDao shopPdDao;

	@Resource
	private PdModelDao pdModelDao;

	@Resource
	private MmtShopCustomerDao mmtShopCustomerDao;

	public Long createJdxxEntpSell(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.insertEntity(t);
	}

	public Long createJdxxEntpSellForMmstJxc(JdxxEntpSell t) {
		StdEntpMain stdEntpMain = t.getStdEntpMain();
		/* 更新产品信息-绑定MMT的商品 */
		if (null != stdEntpMain.getShop_id()) {
			PdModel pdModel = new PdModel();
			pdModel.setDel_mark(0);
			pdModel.setPd_id(t.getPd_id());
			pdModel = this.pdModelDao.selectEntity(pdModel);

			if (null != pdModel && null != stdEntpMain.getShop_id()) {// PdModel中有记录产品型号，再查看ShopPd
				ShopPd sp = new ShopPd();
				sp.setPd_id(t.getPd_id());
				sp.setShop_id(stdEntpMain.getShop_id());
				sp = this.shopPdDao.selectEntity(sp);
				if (null == sp) {// ShopPd中没有记录
					// 将pdModel保存至ShopPd
					ShopPd shopPd = new ShopPd();
					this.copyPropertiesFromPdModelToShopPd(shopPd, pdModel);
					shopPd.setShop_id(stdEntpMain.getShop_id());
					shopPd.setPrice_audit_state(1);
					shopPd.setIs_country(1);
					shopPd.setPrice(t.getPd_price());
					shopPd.setMmt_price(pdModel.getPrice_limit());
					shopPd.setAccess_num(20000000000L);
					this.shopPdDao.insertEntity(shopPd);
				} else {
					this.copyPropertiesFromPdModelToShopPd(sp, pdModel);
					sp.setShop_id(stdEntpMain.getShop_id());
					sp.setPrice_audit_state(1);
					sp.setIs_country(1);
					sp.setMmt_price(pdModel.getPrice_limit());
					sp.setPrice(t.getPd_price());
					sp.setAccess_num(20100000000L);
					this.shopPdDao.updateEntity(sp);
				}
			}

		}
		/* 更新产品信息-绑定MMT的商品 */

		/* 更新消费者信息-绑定MMT的用户 */
		BuyerInfo bi = new BuyerInfo();
		bi.setBuyer_id_type(1);
		bi.setBuyer_id(t.getBuyer_id().toUpperCase());
		bi = this.buyerInfoDao.selectEntity(bi);

		MmtShopCustomer msc = new MmtShopCustomer();

		if (null != bi) {// 存在信息，进行更新
			Long bi_id = bi.getId();
			this.copyPropertiesFromJdxxEntpSellToBuyerInfo(bi, t);
			bi.setId(bi_id);
			bi.setBuyer_id_type(1);
			bi.setBuyer_id(t.getBuyer_id().toUpperCase());
			this.buyerInfoDao.updateEntity(bi);

			if (null != stdEntpMain.getShop_id()) {
				msc.setCustomer(bi.getId());
				msc.setMmt_shop(stdEntpMain.getShop_id());
				MmtShopCustomer mmTsc = this.mmtShopCustomerDao.selectEntity(msc);
				if (null == mmTsc) {
					this.mmtShopCustomerDao.insertEntity(msc);
				}
			}
		} else {// 不存在，插入
			BuyerInfo buyerInfo = new BuyerInfo();
			this.copyPropertiesFromJdxxEntpSellToBuyerInfo(buyerInfo, t);
			// buyerInfo.setBuyer_id_type(1);
			// buyerInfo.setBuyer_id(t.getBuyer_id().toUpperCase());
			Long buyer_id = this.buyerInfoDao.insertEntity(buyerInfo);

			if (buyer_id != 0) {
				if (null != stdEntpMain.getShop_id()) {
					msc.setMmt_shop(stdEntpMain.getShop_id());
					msc.setCustomer(buyer_id);
					this.mmtShopCustomerDao.insertEntity(msc);
				}
			}
		}
		/* 更新消费者信息-绑定MMT的用户 */

		if (null != t.getMap().get("JXC_ENABLED") && null != stdEntpMain) {
			// 更新进销存库存（减库存） start...
			JxcPd pd = new JxcPd();
			pd.setOut_sys_id(t.getPd_id());
			pd.setOwn_sys(1);
			pd.setIs_del(0);
			pd.setShop_id(stdEntpMain.getShop_id());
			pd.getMap().put("count_reduce_num", 1);
			this.jxcPdDao.updateJxcPdCount(pd);
			// 更新进销存库存 end!

			// 插入进销存销售单信息start...
			JxcCustomer jxcCustomer = (JxcCustomer) t.getMap().get("jxcCustomer");// 进销存客户信息
			Long cus_id = new Long(0);
			if (null != jxcCustomer && null == jxcCustomer.getId()) {
				cus_id = this.jxcCustomerDao.insertEntity(jxcCustomer);
			} else if (null != jxcCustomer && null != jxcCustomer.getId()) {
				cus_id = jxcCustomer.getId();
				this.jxcCustomerDao.updateEntity(jxcCustomer);
			}
			JxcSellBill jxcSellBill = (JxcSellBill) t.getMap().get("jxcSellBill");// 进销存销售单
			Long bill_id = new Long(0);
			if (null != jxcSellBill && !cus_id.equals(new Long(0))) {
				jxcSellBill.setCustomer_id(cus_id);
				bill_id = this.jxcSellBillDao.insertEntity(jxcSellBill);
			}
			JxcSellBillDetails jsbd = (JxcSellBillDetails) t.getMap().get("jsbd");// 进销存门店销售商品明细
			if (null != jsbd && !bill_id.equals(new Long(0))) {
				jsbd.setSell_bill_id(bill_id);
				this.jxcSellBillDetailsDao.insertEntity(jsbd);
			}
			// 插入进销存销售单信息end...
		}

		return this.jdxxEntpSellDao.insertEntity(t);
	}

	public JdxxEntpSell getJdxxEntpSell(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectEntity(t);
	}

	public Long getJdxxEntpSellCount(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectEntityCount(t);
	}

	public List<JdxxEntpSell> getJdxxEntpSellList(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectEntityList(t);
	}

	/**
	 * @desc 此方法包含修改家电下乡信息时，JXC客户单和销售单的修改，其中，退货流程为：添加一张数量为负的销售单，并修改销售单金额.具体为：
	 * @case 1、修改客户信息时，若修改了身份证信息，则认为是修改前的客户先退货，新客户再购买的流程;否则只更新客户信息。
	 * @case 2、修改价格信息时，也认为是先退货，再购买的流程
	 * @edit by Chen,LiLin 2011.3.14
	 */
	public int modifyJdxxEntpSellForMmstJxc(JdxxEntpSell t) {
		StdEntpMain stdEntpMain = t.getStdEntpMain();
		/* 更新产品信息-绑定MMT的商品 */
		if (null != stdEntpMain.getShop_id()) {
			PdModel pdModel = new PdModel();
			pdModel.setDel_mark(0);
			pdModel.setPd_id(t.getPd_id());
			pdModel = this.pdModelDao.selectEntity(pdModel);

			if (null != pdModel && null != stdEntpMain.getShop_id()) {// PdModel中有记录产品型号，再查看ShopPd
				ShopPd sp = new ShopPd();
				sp.setPd_id(t.getPd_id());
				sp.setShop_id(stdEntpMain.getShop_id());
				sp = this.shopPdDao.selectEntity(sp);
				if (null == sp) {// ShopPd中没有记录
					// 将pdModel保存至ShopPd
					ShopPd shopPd = new ShopPd();
					this.copyPropertiesFromPdModelToShopPd(shopPd, pdModel);
					shopPd.setShop_id(stdEntpMain.getShop_id());
					shopPd.setPrice_audit_state(1);
					shopPd.setIs_country(1);
					shopPd.setPrice(t.getPd_price());
					shopPd.setMmt_price(pdModel.getPrice_limit());
					shopPd.setAccess_num(20000000000L);
					this.shopPdDao.insertEntity(shopPd);
				} else {
					this.copyPropertiesFromPdModelToShopPd(sp, pdModel);
					sp.setShop_id(stdEntpMain.getShop_id());
					sp.setPrice_audit_state(1);
					sp.setIs_country(1);
					sp.setMmt_price(pdModel.getPrice_limit());
					sp.setPrice(t.getPd_price());
					sp.setAccess_num(20100000000L);
					this.shopPdDao.updateEntity(sp);
				}
			}

		}
		/* 更新产品信息-绑定MMT的商品 */

		/* 更新消费者信息-绑定MMT的用户 */
		BuyerInfo bi = new BuyerInfo();
		bi.setBuyer_id_type(1);
		bi.setBuyer_id(t.getBuyer_id().toUpperCase());
		bi = this.buyerInfoDao.selectEntity(bi);

		MmtShopCustomer msc = new MmtShopCustomer();

		if (null != bi) {// 存在信息，进行更新
			Long bi_id = bi.getId();
			this.copyPropertiesFromJdxxEntpSellToBuyerInfo(bi, t);
			bi.setId(bi_id);
			bi.setBuyer_id_type(1);
			bi.setBuyer_id(t.getBuyer_id().toUpperCase());
			this.buyerInfoDao.updateEntity(bi);

			if (null != stdEntpMain.getShop_id()) {
				msc.setCustomer(bi.getId());
				msc.setMmt_shop(stdEntpMain.getShop_id());
				MmtShopCustomer mmTsc = this.mmtShopCustomerDao.selectEntity(msc);
				if (null == mmTsc) {
					this.mmtShopCustomerDao.insertEntity(msc);
				}
			}
		} else {// 不存在，插入
			BuyerInfo buyerInfo = new BuyerInfo();
			this.copyPropertiesFromJdxxEntpSellToBuyerInfo(buyerInfo, t);
			// buyerInfo.setBuyer_id_type(1);
			// buyerInfo.setBuyer_id(t.getBuyer_id().toUpperCase());
			Long buyer_id = this.buyerInfoDao.insertEntity(buyerInfo);

			if (buyer_id != 0) {
				if (null != stdEntpMain.getShop_id()) {
					msc.setMmt_shop(stdEntpMain.getShop_id());
					msc.setCustomer(buyer_id);
					this.mmtShopCustomerDao.insertEntity(msc);
				}
			}
		}
		/* 更新消费者信息-绑定MMT的用户 */

		String old_card = (String) t.getMap().get("old_card");
		String old_rpr = (String) t.getMap().get("old_rpr");
		String old_price = (String) t.getMap().get("old_price");
		JxcCustomer jxcCustomer = (JxcCustomer) t.getMap().get("jxcCustomer");// 进销存客户信息
		JxcSellBill jxcSellBill = (JxcSellBill) t.getMap().get("jxcSellBill");// 进销存销售单
		JxcSellBillDetails jsbd = (JxcSellBillDetails) t.getMap().get("jsbd");// 进销存门店销售商品明细

		if (null != t.getMap().get("JXC_ENABLED") && null != stdEntpMain) {
			if (old_card.equals(t.getBuyer_id()) && old_rpr.equals(t.getRpr_number())) {// 没有修改购买客户身份信息
				if (jxcCustomer.getId() != null) { // 此处如果jxcCustomer ==
					// null，则说明是编辑发布进销存前录入的销售信息，不走进销存逻辑
					this.jxcCustomerDao.updateEntity(jxcCustomer);// 首先更新客户信息，如地址，姓名等.
					if (!new BigDecimal(old_price).equals(t.getPd_price())) {// 修改了价格，按照case2的流程
						// 先退货
						Long bill_id = new Long(0);// 插入一张数量为-1的销售单,销售额为原始单价
						if (null != jxcSellBill) {
							jxcSellBill.setCustomer_id(jxcCustomer.getId());
							jxcSellBill.setMoney(new BigDecimal("-1").multiply(new BigDecimal(old_price)));
							jxcSellBill.setPay_money(new BigDecimal("-1").multiply(new BigDecimal(old_price)));
							bill_id = this.jxcSellBillDao.insertEntity(jxcSellBill);
						}
						if (null != jsbd && !bill_id.equals(new Long(0))) {
							jsbd.setSell_bill_id(bill_id);
							jsbd.setCount(new Long(-1));
							// jsbd.setCost_price(new
							// BigDecimal(old_price));不修改成本价格
							jsbd.setPrice(new BigDecimal(old_price));
							this.jxcSellBillDetailsDao.insertEntity(jsbd);
						}
						// 再购买
						Long bill_id_2 = new Long(0);// 插入一张数量为+1的销售单,销售额为新单价
						if (null != jxcSellBill) {
							jxcSellBill.setCustomer_id(jxcCustomer.getId());
							jxcSellBill.setMoney(t.getPd_price());
							jxcSellBill.setPay_money(t.getPd_price());
							bill_id_2 = this.jxcSellBillDao.insertEntity(jxcSellBill);
						}
						if (null != jsbd && !bill_id_2.equals(new Long(0))) {
							jsbd.setSell_bill_id(bill_id_2);
							jsbd.setCount(new Long(1));
							// jsbd.setCost_price(t.getPd_price());
							jsbd.setPrice(t.getPd_price());
							this.jxcSellBillDetailsDao.insertEntity(jsbd);
						}
					}
				}
			} else {// 修改了客户身份信息，也就更改了客户
				// 首先执行原来的客户的退货流程
				JxcCustomer old_cus = new JxcCustomer();// 查询老客户是否存在
				old_cus.setCus_idcard(t.getBuyer_id());
				old_cus.setShop_id(stdEntpMain.getShop_id());
				old_cus.setCus_rpr(t.getRpr_number());
				old_cus.setIs_del(0);
				old_cus = this.jxcCustomerDao.selectEntity(old_cus);
				if (null != old_cus) {// 老客户不存在时，说明是老的标识卡，无需验证进销存信息
					Long bill_id = new Long(0);// 插入一张数量为-1的销售单,销售额为原始单价
					if (null != jxcSellBill) {
						jxcSellBill.setCustomer_id(old_cus.getId());
						jxcSellBill.setMoney(new BigDecimal("-1").multiply(new BigDecimal(old_price)));
						jxcSellBill.setPay_money(new BigDecimal("-1").multiply(new BigDecimal(old_price)));
						bill_id = this.jxcSellBillDao.insertEntity(jxcSellBill);
					}
					if (null != jsbd && !bill_id.equals(new Long(0))) {
						jsbd.setSell_bill_id(bill_id);
						jsbd.setCount(new Long(-1));
						jsbd.setCost_price(new BigDecimal(old_price));
						jsbd.setPrice(new BigDecimal(old_price));
						this.jxcSellBillDetailsDao.insertEntity(jsbd);
					}
					// 新客户添加销售单信息
					Long cus_id = new Long(0);
					if (null != jxcCustomer && null == jxcCustomer.getId()) {
						cus_id = this.jxcCustomerDao.insertEntity(jxcCustomer);
					} else if (null != jxcCustomer && null != jxcCustomer.getId()) {
						cus_id = jxcCustomer.getId();
						this.jxcCustomerDao.updateEntity(jxcCustomer);
					}
					Long bill_id_2 = new Long(0);
					if (null != jxcSellBill && !cus_id.equals(new Long(0))) {
						jxcSellBill.setCustomer_id(cus_id);
						jxcSellBill.setMoney(t.getPd_price());
						jxcSellBill.setPay_money(t.getPd_price());
						bill_id_2 = this.jxcSellBillDao.insertEntity(jxcSellBill);
					}
					if (null != jsbd && !bill_id_2.equals(new Long(0))) {
						jsbd.setSell_bill_id(bill_id_2);
						jsbd.setCount(new Long(1));
						// jsbd.setCost_price(t.getPd_price());
						jsbd.setPrice(t.getPd_price());
						this.jxcSellBillDetailsDao.insertEntity(jsbd);
					}
				}
			}
		}

		return this.jdxxEntpSellDao.updateEntity(t);
	}

	public int modifyJdxxEntpSell(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.updateEntity(t);
	}

	public int removeJdxxEntpSell(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.deleteEntity(t);
	}

	public List<JdxxEntpSell> getJdxxEntpSellPaginatedList(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @vesion 2010-05-31
	 */
	public List<JdxxEntpSell> getJdxxEntpSellIncludeMdNameAndBrandNameList(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectJdxxEntpSellIncludeMdNameAndBrandNameList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @vesion 2010-12-02
	 */
	public List<JdxxEntpSell> getJdxxEntpSellIncludeMdNameAndBrandNameForMmmtList(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectJdxxEntpSellIncludeMdNameAndBrandNameForMmmtList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2010-07-07
	 */
	public Long getJdxxEntpSellCountForStatistics(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectJdxxEntpSellCountForStatistics(t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2010-11-17
	 */
	public List<JdxxEntpSell> getJdxxEntpSellCountGroupByPdType(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectJdxxEntpSellCountGroupByPdType(t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2011-6-22
	 */
	public List<JdxxEntpSell> getTopPdModelList(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectTopPdModelList(t);
	}

	/**
	 * @author Xing,XiuDong
	 * @version 2010-11-17
	 */
	public List<JdxxEntpSell> getJdxxEntpSellCountGroupByPdTypeAndWeek(JdxxEntpSell t) {
		return this.jdxxEntpSellDao.selectJdxxEntpSellCountGroupByPdTypeAndWeek(t);
	}

	public List<Long> getJdxxEntpSellIdStrings(JdxxEntpSell t) {

		return this.jdxxEntpSellDao.selectJdxxEntpSellIdStrings(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-12-07
	 */
	public List<JdxxEntpSell> getJdxxEntpSellForBrandXsSalesList(JdxxEntpSell t) throws DataAccessException {
		return this.jdxxEntpSellDao.selectJdxxEntpSellForBrandXsSalesList(t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-3-30
	 */
	public List<JdxxEntpSell> getJdxxEntpSellWithMdNamePaginatedList(JdxxEntpSell t) throws DataAccessException {
		return this.jdxxEntpSellDao.selectJdxxEntpSellWithMdNamePaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-3-30
	 */
	public List<JdxxEntpSell> getJdxxEntpSellWithMdNameList(JdxxEntpSell t) throws DataAccessException {
		return this.jdxxEntpSellDao.selectJdxxEntpSellWithMdNameList(t);
	}

	private void copyPropertiesFromPdModelToShopPd(ShopPd s, PdModel p) {
		s.setPd_id(p.getPd_id());
		s.setPd_type(p.getPd_type());
		s.setBrand_id(p.getBrand_id());
		s.setSeries_id(p.getSeries_id());
		s.setSeries_name(p.getSeries_name());
		p.setIs_country(1);
		s.setEntp_id(p.getEntp_id());
		s.setMd_name(p.getMd_name());
		s.setPrice_limit(p.getPrice_limit());
		s.setOrder_sort(p.getOrder_sort());
		s.setMain_pic(p.getMain_pic());
		s.setState(p.getDel_mark());
	}

	private void copyPropertiesFromJdxxEntpSellToBuyerInfo(BuyerInfo bi, JdxxEntpSell sell) {
		bi.setBuyer_id(sell.getBuyer_id().toUpperCase());
		bi.setBuyer_id_type(1);
		bi.setBuyer_link(sell.getBuyer_link());
		bi.setBuyer_mobile(sell.getBuyer_mobile());
		bi.setBuyer_name(sell.getBuyer_name());
		bi.setBuyer_tel(sell.getBuyer_link());
		bi.setBuyer_addr(sell.getBuyer_addr());
		bi.setBuyer_bank_name(sell.getBuyer_bank_name());
		bi.setBuyer_bank_account(sell.getBuyer_bank_account());
		bi.setBuyer_bank_user(sell.getBuyer_bank_user());
		bi.setRpr_number(sell.getRpr_number());
		bi.setVillage_p_index(sell.getVillage_p_index());
		bi.setHousemaster(sell.getHousemaster());
		bi.setKin(sell.getKin());
	}
}
