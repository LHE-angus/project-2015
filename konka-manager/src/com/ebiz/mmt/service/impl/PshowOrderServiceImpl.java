package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.EcBaseExpressDao;
import com.ebiz.mmt.dao.EcBindingPdOrdeDetailsDao;
import com.ebiz.mmt.dao.EcGroupBuyMainDao;
import com.ebiz.mmt.dao.EcMessageDao;
import com.ebiz.mmt.dao.EcOrderExpressInfoDao;
import com.ebiz.mmt.dao.EcRuleBingdingDetailDao;
import com.ebiz.mmt.dao.EcSelfAreaDao;
import com.ebiz.mmt.dao.EcStocksDao;
import com.ebiz.mmt.dao.EcUserDao;
import com.ebiz.mmt.dao.EcVouchCodeDao;
import com.ebiz.mmt.dao.EcVouchersDao;
import com.ebiz.mmt.dao.JBasePartnerDao;
import com.ebiz.mmt.dao.JBillDao;
import com.ebiz.mmt.dao.JBillDetailsDao;
import com.ebiz.mmt.dao.JStocksStackDao;
import com.ebiz.mmt.dao.KonkaBcompPdDao;
import com.ebiz.mmt.dao.PshowOrdeAuditDao;
import com.ebiz.mmt.dao.PshowOrdeDetailsDao;
import com.ebiz.mmt.dao.PshowOrdeExchangeDao;
import com.ebiz.mmt.dao.PshowOrderDao;
import com.ebiz.mmt.dao.SfhkOutStorageDao;
import com.ebiz.mmt.dao.SfhkRelEppOrderDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcBindingPdOrdeDetails;
import com.ebiz.mmt.domain.EcGroupBuyMain;
import com.ebiz.mmt.domain.EcMessage;
import com.ebiz.mmt.domain.EcOrderExpressInfo;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleBingdingDetail;
import com.ebiz.mmt.domain.EcSelfArea;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.EcVouchCode;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JStocksStack;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeAudit;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrdeExchange;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.service.JStocksStackService;
import com.ebiz.mmt.service.PshowOrderService;
import com.ebiz.mmt.web.struts.BaseAction;
import com.sf.integration.warehouse.Order;
import com.sf.integration.warehouse.SfOrderService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-08-09 18:19:59
 */
@Service
public class PshowOrderServiceImpl extends BaseAction implements PshowOrderService {

	@Resource
	private PshowOrderDao pshowOrderDao;

	@Resource
	private PshowOrdeAuditDao pshowOrdeAuditDao;

	@Resource
	private PshowOrdeDetailsDao pshowOrdeDetailsDao;

	@Resource
	private EcBindingPdOrdeDetailsDao ecBindingPdOrdeDetailsDao;

	@Resource
	private KonkaBcompPdDao konkaBcompPdDao;

	@Resource
	private EcStocksDao ecStocksDao;

	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	@Resource
	private EcBaseExpressDao ecBaseExpressDao;

	@Resource
	private EcOrderExpressInfoDao ecOrderExpressInfoDao;

	@Resource
	private EcVouchersDao ecVouchersDao;

	@Resource
	private EcMessageDao ecMessageDao;

	@Resource
	private EcUserDao ecUserDao;

	@Resource
	private JBillDao jBillDao;

	@Resource
	private JBillDetailsDao jBillDetailsDao;

	@Resource
	private PshowOrdeExchangeDao pshowOrdeExchangeDao;

	@Resource
	private JBasePartnerDao jBasePartnerDao;

	@Resource
	JStocksStackService jStocksStackService;

	@Resource
	JStocksStackDao jStocksStackDao;

	@Resource
	EcSelfAreaDao ecSelfAreaDao;

	@Resource
	EcRuleBingdingDetailDao ecRuleBingdingDetailDao;

	@Resource
	EcVouchCodeDao ecVouchCodeDao;

	@Resource
	private SfhkOutStorageDao sfhkOutStorageDao;

	@Resource
	private EcGroupBuyMainDao ecGroupBuyMainDao;

	@Resource
	private SfhkRelEppOrderDao sfhkRelEppOrderDao;

	public Long createPshowOrder(PshowOrder t) {
		return this.pshowOrderDao.insertEntity(t);
	}

	public Long createPshowOrderWithDetails(PshowOrder t) {
		EcUser ecUser = new EcUser();
		ecUser.setId(t.getOrder_user_id());
		ecUser = this.ecUserDao.selectEntity(ecUser);
		// 计算奖励积分
		BigDecimal x = new BigDecimal("50");
		// 银卡会员获取奖励积分=付款金额*50
		if (ecUser.getEcBaseCardLevel() != null && ecUser.getEcBaseCardLevel().getCard_type_discount() != null) {
			x = ecUser.getEcBaseCardLevel().getCard_type_discount();
		}

		t.setPay_integral(t.getPay_price());
		Long order_id = this.pshowOrderDao.insertEntity(t);
		int state = t.getState().intValue();
		// 订单明细
		List<PshowOrdeDetails> pshowOrdeDetailsList = t.getPshowOrdeDetailsList();
		String pd_name_num = "";// 机型*数量 ;
		BigDecimal all_pay_integral = new BigDecimal("0.0");
		BigDecimal all_integral = new BigDecimal("0.0");
		BigDecimal all_pay_price = t.getPay_price();
		for (PshowOrdeDetails pshowOrdeDetails : pshowOrdeDetailsList) {
			pshowOrdeDetails.setOrder_id(order_id);
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(pshowOrdeDetails.getPd_id());
			konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
			pd_name_num += konkaBcompPd.getPd_sn() + "*" + pshowOrdeDetails.getNum() + " ";
			BigDecimal pay_integral = new BigDecimal("0.0");
			BigDecimal integral = new BigDecimal("0.0");
			if (all_pay_price != null) {
				if (konkaBcompPd.getProd_type() != null && konkaBcompPd.getProd_type().intValue() != 9
						&& konkaBcompPd.getProd_type().intValue() != 22
						&& pshowOrdeDetails.getTotal_price().intValue() > 0 && t.getTotal_price().intValue() > 0) {
					pay_integral = all_pay_price.multiply(pshowOrdeDetails.getTotal_price()).divide(t.getTotal_price(),
							2, BigDecimal.ROUND_HALF_EVEN);
				}
			}
			all_pay_integral = all_pay_integral.add(pay_integral);
			if (pay_integral.intValue() > 0) {
				integral = pay_integral.multiply(x).divide(new BigDecimal("100"));
			}
			if (t.getOrder_type().intValue() == 6) {
				pay_integral = new BigDecimal("0.0");
				integral = new BigDecimal("0.0");
			}
			all_integral = all_integral.add(integral);
			pshowOrdeDetails.setPay_integral(pay_integral);
			pshowOrdeDetails.setIntegral(integral);
			Long details_id = this.pshowOrdeDetailsDao.insertEntity(pshowOrdeDetails);

			// 订单明细中的商品绑定的服务
			List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = pshowOrdeDetails.getEcBindingPdOrdeDetailsList();
			if (null != ecBindingPdOrdeDetailsList && 0 != ecBindingPdOrdeDetailsList.size()) {
				for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ecBindingPdOrdeDetailsList) {
					ecBindingPdOrdeDetails.setDetails_id(details_id);
					this.ecBindingPdOrdeDetailsDao.insertEntity(ecBindingPdOrdeDetails);
				}
			}
			// 费用规则
			List<EcRule> ecRuleList = pshowOrdeDetails.getEcRuleList();
			if (null != ecRuleList && ecRuleList.size() > 0) {
				for (EcRule ecRule : ecRuleList) {
					EcRuleBingdingDetail ecb = new EcRuleBingdingDetail();
					ecb.setDetail_id(pshowOrdeDetails.getBill_item_id());
					ecb.setRule_id(ecRule.getId());
					ecb.setRule_money(ecRule.getRule_price().toString());
					ecb.setRule_type(ecRule.getRule_type().toString());
					ecb.setRule_desc(ecRule.getRule_msg());
					this.ecRuleBingdingDetailDao.insertEntity(ecb);
				}
			}

			if (state == 5 || t.getPay_way().intValue() == 0 || t.getPay_way().intValue() == 1) {
				// 更新销量
				KonkaBcompPd t4 = new KonkaBcompPd();
				t4.setId(pshowOrdeDetails.getPd_id());
				t4.setSale_num(pshowOrdeDetails.getNum());
				this.konkaBcompPdDao.updateKonkaBcompPdForViewCountOrSaleNum(t4);

				// 更新库存
				EcStocks ecStocks = new EcStocks();
				ecStocks.setStocks(pshowOrdeDetails.getNum());
				ecStocks.setGoods_id(pshowOrdeDetails.getPd_id());
				ecStocks.setStore_id(pshowOrdeDetails.getStore_id());
				this.ecStocksDao.updateEcStocksForSale(ecStocks);
			}

		}
		// 购物券更新
		List<EcVouchers> ecVouchersList = t.getEcVouchersList();
		if (ecVouchersList != null && ecVouchersList.size() > 0) {
			for (EcVouchers ecvouchers : ecVouchersList) {
				EcVouchers e = new EcVouchers();
				e.setId(ecvouchers.getId());
				e.setInfo_state(new Integer(1));
				e.setOrder_id(order_id.toString());
				e.getMap().put("info_state", "1");
				ecVouchersDao.updateEntity(e);
			}
		}
		// 更新付款积分、奖励积分
		PshowOrder p = new PshowOrder();
		p.setId(t.getId());
		p.setPay_integral(all_pay_integral);
		p.setIntegral(all_integral);
		this.pshowOrderDao.updateEntity(p);

		// 下单短信发送
		p = new PshowOrder();
		p.setId(t.getId());
		p = this.pshowOrderDao.selectEntity(p);

		// 取订单客户信息、下单人信息
		String kh_name = p.getBuyer_name();
		String trade_index = p.getTrade_index();
		String user_tel = ecUser.getLink_phone();
		String user_real_name = ecUser.getReal_name();
		Integer orderState = p.getState();

		String msg = kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
				+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
		String msg2 = user_real_name + "您好," + kh_name + "的订单" + trade_index + "（" + pd_name_num
				+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
		if (ecUser.getUser_type().intValue() == 2) {
			if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
				msg = "【康佳&顺丰直销商城】" + kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
						+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
				msg2 = "";
			} else {
				msg = "【康佳网上直销商城】" + kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
						+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
				msg2 = "【康佳网上直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（" + pd_name_num
						+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
			}
		}
		try {
			if (!"".equals(msg)) {
				EcMessage ecm = new EcMessage();
				ecm.setAdd_date(new Date());
				ecm.setContent(msg);
				ecm.setMobile(p.getBuyer_mp());
				ecm.setOrder_id(p.getId().toString());
				ecm.setSend_date(new Date());
				ecm.setOrder_state(orderState);
				if (getSendMessageResult(p.getBuyer_mp(), msg)) {
					ecm.setSend_state(0);
				} else {
					ecm.setSend_state(1);
				}
				ecMessageDao.insertEntity(ecm);
			}
			if (!"".equals(msg2)) {
				EcMessage ecm = new EcMessage();
				ecm.setAdd_date(new Date());
				ecm.setContent(msg2);
				ecm.setMobile(user_tel);
				ecm.setOrder_id(p.getId().toString());
				ecm.setSend_date(new Date());
				ecm.setOrder_state(orderState);
				if (getSendMessageResult(user_tel, msg2)) {// 给下单用户发短信
					ecm.setSend_state(0);
				} else {
					ecm.setSend_state(1);
				}
				ecMessageDao.insertEntity(ecm);
			}
		} catch (DataAccessException e) {
		} catch (Exception e) {
		}
		return order_id;
	}

	@Override
	public Long createPshowOrderWithDetailsForZxMall(PshowOrder t) {
		EcUser ecUser = new EcUser();
		ecUser.setId(t.getOrder_user_id());
		ecUser = this.ecUserDao.selectEntity(ecUser);

		t.setPay_integral(t.getPay_price());
		Long order_id = this.pshowOrderDao.insertEntity(t);
		int state = t.getState().intValue();
		// 订单明细
		List<PshowOrdeDetails> pshowOrdeDetailsList = t.getPshowOrdeDetailsList();
		String pd_name_num = "";// 机型*数量 ;
		BigDecimal all_pay_integral = new BigDecimal("0.0");
		BigDecimal all_integral = new BigDecimal("0.0");
		// BigDecimal all_pay_price = t.getPay_price();
		for (PshowOrdeDetails pshowOrdeDetails : pshowOrdeDetailsList) {
			pshowOrdeDetails.setOrder_id(order_id);
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(pshowOrdeDetails.getPd_id());
			konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
			pd_name_num += konkaBcompPd.getPd_sn() + "*" + pshowOrdeDetails.getNum() + " ";

			all_pay_integral = all_pay_integral.add(pshowOrdeDetails.getPay_integral());
			all_integral = all_integral.add(pshowOrdeDetails.getIntegral());
			pshowOrdeDetails.setPay_integral(pshowOrdeDetails.getPay_integral());
			pshowOrdeDetails.setIntegral(pshowOrdeDetails.getIntegral());
			Long details_id = this.pshowOrdeDetailsDao.insertEntity(pshowOrdeDetails);

			// 订单明细中的商品绑定的服务
			List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = pshowOrdeDetails.getEcBindingPdOrdeDetailsList();
			if (null != ecBindingPdOrdeDetailsList && 0 != ecBindingPdOrdeDetailsList.size()) {
				for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ecBindingPdOrdeDetailsList) {
					ecBindingPdOrdeDetails.setDetails_id(details_id);
					this.ecBindingPdOrdeDetailsDao.insertEntity(ecBindingPdOrdeDetails);
				}
			}
			// 费用规则
			List<EcRule> ecRuleList = pshowOrdeDetails.getEcRuleList();
			if (null != ecRuleList && ecRuleList.size() > 0) {
				for (EcRule ecRule : ecRuleList) {
					EcRuleBingdingDetail ecb = new EcRuleBingdingDetail();
					ecb.setDetail_id(pshowOrdeDetails.getBill_item_id());
					ecb.setRule_id(ecRule.getId());
					ecb.setRule_money(ecRule.getRule_price().toString());
					ecb.setRule_type(ecRule.getRule_type().toString());
					ecb.setRule_desc(ecRule.getRule_msg());
					this.ecRuleBingdingDetailDao.insertEntity(ecb);
				}
			}

			if (state == 5 || t.getPay_way().intValue() == 0 || t.getPay_way().intValue() == 1) {
				// 更新销量
				KonkaBcompPd t4 = new KonkaBcompPd();
				t4.setId(pshowOrdeDetails.getPd_id());
				t4.setSale_num(pshowOrdeDetails.getNum());
				this.konkaBcompPdDao.updateKonkaBcompPdForViewCountOrSaleNum(t4);

				// 更新库存
				EcStocks ecStocks = new EcStocks();
				ecStocks.setStocks(pshowOrdeDetails.getNum());
				ecStocks.setGoods_id(pshowOrdeDetails.getPd_id());
				ecStocks.setStore_id(pshowOrdeDetails.getStore_id());
				this.ecStocksDao.updateEcStocksForSale(ecStocks);
			}

		}
		// 购物券更新
		List<EcVouchers> ecVouchersList = t.getEcVouchersList();
		if (ecVouchersList != null && ecVouchersList.size() > 0) {
			for (EcVouchers ecvouchers : ecVouchersList) {
				EcVouchers e = new EcVouchers();
				e.setId(ecvouchers.getId());
				e.setInfo_state(new Integer(1));
				e.setOrder_id(order_id.toString());
				e.getMap().put("info_state", "1");
				ecVouchersDao.updateEntity(e);
			}
		}
		// 更新付款积分、奖励积分
		PshowOrder p = new PshowOrder();
		p.setId(t.getId());
		p.setPay_integral(all_pay_integral);
		p.setIntegral(all_integral);
		this.pshowOrderDao.updateEntity(p);

		// 下单短信发送
		p = new PshowOrder();
		p.setId(t.getId());
		p = this.pshowOrderDao.selectEntity(p);

		// 取订单客户信息、下单人信息
		String kh_name = p.getBuyer_name();
		String trade_index = p.getTrade_index();
		String user_tel = ecUser.getLink_phone();
		String user_real_name = ecUser.getReal_name();
		Integer orderState = p.getState();

		String msg = kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
				+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
		String msg2 = user_real_name + "您好," + kh_name + "的订单" + trade_index + "（" + pd_name_num
				+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
		if (ecUser.getUser_type().intValue() == 2) {
			if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
				msg = "【康佳&顺丰直销商城】" + kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
						+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
				msg2 = "";
			} else {
				msg = "【康佳网上直销商城】" + kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
						+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
				msg2 = "【康佳网上直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（" + pd_name_num
						+ "）平台已经收到,我们将尽快处理![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
			}
		}
		try {
			if (!"".equals(msg)) {
				EcMessage ecm = new EcMessage();
				ecm.setAdd_date(new Date());
				ecm.setContent(msg);
				ecm.setMobile(p.getBuyer_mp());
				ecm.setOrder_id(p.getId().toString());
				ecm.setSend_date(new Date());
				ecm.setOrder_state(orderState);
				if (getSendMessageResult(p.getBuyer_mp(), msg)) {
					ecm.setSend_state(0);
				} else {
					ecm.setSend_state(1);
				}
				ecMessageDao.insertEntity(ecm);
			}
			if (!"".equals(msg2)) {
				EcMessage ecm = new EcMessage();
				ecm.setAdd_date(new Date());
				ecm.setContent(msg2);
				ecm.setMobile(user_tel);
				ecm.setOrder_id(p.getId().toString());
				ecm.setSend_date(new Date());
				ecm.setOrder_state(orderState);
				if (getSendMessageResult(user_tel, msg2)) {// 给下单用户发短信
					ecm.setSend_state(0);
				} else {
					ecm.setSend_state(1);
				}
				ecMessageDao.insertEntity(ecm);
			}
		} catch (DataAccessException e) {
		} catch (Exception e) {
		}
		return order_id;
	}

	public Long createPshowOrderForExchange(PshowOrder t, PshowOrdeAudit poa,
			List<PshowOrdeDetails> pshowOrdeDetailsList, int order_type) {
		this.pshowOrdeAuditDao.insertEntity(poa);
		Long order_id = null;
		Long old_order_id = poa.getOrder_id();
		Long oper_user_id = poa.getOpr_user_id();
		Integer exchange_info = null;// 退机类型
		if (t.getMap().get("exchange_info") != null && "".equals(t.getMap().get("exchange_info"))) {
			exchange_info = new Integer(t.getMap().get("exchange_info").toString());
		}
		if (order_type == 2) {// 换货同时生成俩个订单：换货单和退货单
			String trade_index = t.getTrade_index();
			// 换货新增订单
			Long par_order_id = t.getPar_order_id();
			PshowOrder p = new PshowOrder();
			p.setPar_order_id(par_order_id);
			Long c = this.pshowOrderDao.selectEntityCount(p);
			c = c + 1;
			t.setTrade_index(trade_index + "_" + c);

			// 更原新订单金额
			BigDecimal pay_price = (BigDecimal) t.getMap().get("pay_price");
			BigDecimal integral = (BigDecimal) t.getMap().get("integral");
			if (pay_price != null && pay_price.floatValue() > 0) {
				t.setPay_price(pay_price);
				// PshowOrder old_order = new PshowOrder();
				// old_order.setId(par_order_id);
				// old_order = this.pshowOrderDao.selectEntity(old_order);
				// PshowOrder s_order = new PshowOrder();
				// s_order.setId(par_order_id);
				// s_order.setPay_price(old_order.getPay_price().subtract(pay_price));
				// if (old_order.getIntegral() != null && integral != null) {
				// s_order.setIntegral(old_order.getIntegral().subtract(integral));
				// }
				// this.pshowOrderDao.updateEntity(s_order);
			}

			order_id = this.pshowOrderDao.insertEntity(t);
			for (PshowOrdeDetails pshowOrdeDetails : pshowOrdeDetailsList) {
				// if (pshowOrdeDetails.getIntegral() != null ||
				// pshowOrdeDetails.getRebates() != null) {
				// // 计算原订单明细佣金积分
				// PshowOrdeDetails detail = new PshowOrdeDetails();
				// detail.setBill_item_id(pshowOrdeDetails.getBill_item_id());
				// detail = this.pshowOrdeDetailsDao.selectEntity(detail);
				// if (pshowOrdeDetails.getIntegral() != null) {
				// detail.setIntegral(detail.getIntegral().subtract(pshowOrdeDetails.getIntegral()));
				// }
				// if (pshowOrdeDetails.getRebates() != null) {
				// detail.setRebates(detail.getRebates().subtract(pshowOrdeDetails.getRebates()));
				// }
				// this.pshowOrdeDetailsDao.updateEntity(detail);
				// }
				// 保存新订单明细
				pshowOrdeDetails.setBill_item_id(null);
				pshowOrdeDetails.setOrder_id(order_id);
				Long details_id = this.pshowOrdeDetailsDao.insertEntity(pshowOrdeDetails);
				// 订单明细中的商品绑定的服务
				List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = pshowOrdeDetails
						.getEcBindingPdOrdeDetailsList();
				if (null != ecBindingPdOrdeDetailsList && 0 != ecBindingPdOrdeDetailsList.size()) {
					for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ecBindingPdOrdeDetailsList) {
						ecBindingPdOrdeDetails.setTrade_index(t.getTrade_index());
						ecBindingPdOrdeDetails.setDetails_id(details_id);
						this.ecBindingPdOrdeDetailsDao.insertEntity(ecBindingPdOrdeDetails);
					}
				}
				// 退换货跟宗表
				// PshowOrdeExchange pshowOrdeExchange = new
				// PshowOrdeExchange();
				// pshowOrdeExchange.setOld_order_id(old_order_id);
				// pshowOrdeExchange.setNew_order_id(order_id);
				// pshowOrdeExchange.setOrder_detail_id(details_id);
				// pshowOrdeExchange.setIs_exchange(order_type);
				// pshowOrdeExchange.setOpr_user_id(oper_user_id);
				// this.pshowOrdeExchangeDao.insertEntity(pshowOrdeExchange);
			}

			// 退货订单
			c = c + 1;
			t.setTrade_index(trade_index + "_" + c);
			t.setTotal_price(t.getTotal_price().multiply(new BigDecimal("-1")));
			t.setPay_price(t.getPay_price().multiply(new BigDecimal("-1")));
			if (t.getPay_integral() != null)
				t.setPay_integral(t.getPay_integral().multiply(new BigDecimal("-1")));

			order_id = this.pshowOrderDao.insertEntity(t);
			for (PshowOrdeDetails pshowOrdeDetails : pshowOrdeDetailsList) {
				pshowOrdeDetails.setBill_item_id(null);
				pshowOrdeDetails.setOrder_id(order_id);
				// 数量、 金额、佣金、积分设置为负数
				pshowOrdeDetails.setNum(new Long(pshowOrdeDetails.getNum().intValue() * -1));
				pshowOrdeDetails.setTotal_price(pshowOrdeDetails.getTotal_price().multiply(new BigDecimal("-1")));
				if (pshowOrdeDetails.getRebates() != null)
					pshowOrdeDetails.setRebates(pshowOrdeDetails.getRebates().multiply(new BigDecimal("-1")));
				if (pshowOrdeDetails.getIntegral() != null)
					pshowOrdeDetails.setIntegral(pshowOrdeDetails.getIntegral().multiply(new BigDecimal("-1")));
				if (pshowOrdeDetails.getPay_integral() != null)
					pshowOrdeDetails.setPay_integral(pshowOrdeDetails.getPay_integral().multiply(new BigDecimal("-1")));

				Long details_id = this.pshowOrdeDetailsDao.insertEntity(pshowOrdeDetails);
				// 订单明细中的商品绑定的服务
				List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = pshowOrdeDetails
						.getEcBindingPdOrdeDetailsList();
				if (null != ecBindingPdOrdeDetailsList && 0 != ecBindingPdOrdeDetailsList.size()) {
					for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ecBindingPdOrdeDetailsList) {
						ecBindingPdOrdeDetails.setTrade_index(t.getTrade_index());
						ecBindingPdOrdeDetails.setDetails_id(details_id);
						ecBindingPdOrdeDetails.setNum(new Long(ecBindingPdOrdeDetails.getNum().intValue() * -1));
						this.ecBindingPdOrdeDetailsDao.insertEntity(ecBindingPdOrdeDetails);
					}
				}
				// 退换货跟宗表
				PshowOrdeExchange pshowOrdeExchange = new PshowOrdeExchange();
				pshowOrdeExchange.setOld_order_id(old_order_id);
				pshowOrdeExchange.setNew_order_id(order_id);
				pshowOrdeExchange.setOrder_detail_id(details_id);
				pshowOrdeExchange.setIs_exchange(order_type);
				pshowOrdeExchange.setOpr_user_id(oper_user_id);
				pshowOrdeExchange.setExchange_info(exchange_info);
				this.pshowOrdeExchangeDao.insertEntity(pshowOrdeExchange);
			}
		} else { // 退货订单
			PshowOrder p = new PshowOrder();
			p.setPar_order_id(poa.getOrder_id());
			Long c = this.pshowOrderDao.selectEntityCount(p);
			c = c + 1;
			t.setTrade_index(t.getTrade_index() + "_" + c);
			t.setTotal_price(t.getTotal_price().multiply(new BigDecimal("-1")));
			t.setPay_price(t.getPay_price().multiply(new BigDecimal("-1")));
			if (t.getPay_integral() != null)
				t.setPay_integral(t.getPay_integral().multiply(new BigDecimal("-1")));
			order_id = this.pshowOrderDao.insertEntity(t);
			for (PshowOrdeDetails pshowOrdeDetails : pshowOrdeDetailsList) {
				pshowOrdeDetails.setBill_item_id(null);
				pshowOrdeDetails.setOrder_id(order_id);
				// 数量、 金额、佣金、积分设置为负数
				pshowOrdeDetails.setNum(new Long(pshowOrdeDetails.getNum().intValue() * -1));
				pshowOrdeDetails.setTotal_price(pshowOrdeDetails.getTotal_price().multiply(new BigDecimal("-1")));
				if (pshowOrdeDetails.getRebates() != null)
					pshowOrdeDetails.setRebates(pshowOrdeDetails.getRebates().multiply(new BigDecimal("-1")));
				if (pshowOrdeDetails.getIntegral() != null)
					pshowOrdeDetails.setIntegral(pshowOrdeDetails.getIntegral().multiply(new BigDecimal("-1")));
				if (pshowOrdeDetails.getPay_integral() != null)
					pshowOrdeDetails.setPay_integral(pshowOrdeDetails.getPay_integral().multiply(new BigDecimal("-1")));

				Long details_id = this.pshowOrdeDetailsDao.insertEntity(pshowOrdeDetails);
				// 订单明细中的商品绑定的服务
				List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = pshowOrdeDetails
						.getEcBindingPdOrdeDetailsList();
				if (null != ecBindingPdOrdeDetailsList && 0 != ecBindingPdOrdeDetailsList.size()) {
					for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ecBindingPdOrdeDetailsList) {
						ecBindingPdOrdeDetails.setTrade_index(t.getTrade_index());
						ecBindingPdOrdeDetails.setDetails_id(details_id);
						ecBindingPdOrdeDetails.setNum(new Long(ecBindingPdOrdeDetails.getNum().intValue() * -1));
						this.ecBindingPdOrdeDetailsDao.insertEntity(ecBindingPdOrdeDetails);
					}
				}
				// 退换货跟宗表
				PshowOrdeExchange pshowOrdeExchange = new PshowOrdeExchange();
				pshowOrdeExchange.setOld_order_id(old_order_id);
				pshowOrdeExchange.setNew_order_id(order_id);
				pshowOrdeExchange.setOrder_detail_id(details_id);
				pshowOrdeExchange.setIs_exchange(order_type);
				pshowOrdeExchange.setOpr_user_id(oper_user_id);
				pshowOrdeExchange.setExchange_info(exchange_info);
				this.pshowOrdeExchangeDao.insertEntity(pshowOrdeExchange);
			}
		}
		return order_id;
	}

	public Long createPshowOrderWithDetailsForSplit(List<PshowOrder> pshowList, String id) {

		PshowOrder p2 = new PshowOrder();
		p2.setId(Long.valueOf(id));
		p2.setPar_order_id(-1L);
		this.pshowOrderDao.updateEntity(p2);

		for (PshowOrder t : pshowList) {
			Long order_id = this.pshowOrderDao.insertEntity(t);
			// 订单明细
			List<PshowOrdeDetails> pshowOrdeDetailsList = t.getPshowOrdeDetailsList();

			BigDecimal all_pay_integral = new BigDecimal("0.0");
			BigDecimal all_pay_price = t.getPay_price();

			for (PshowOrdeDetails pshowOrdeDetails : pshowOrdeDetailsList) {
				pshowOrdeDetails.setOrder_id(order_id);

				// 重新订单明细表付款积分，其中电脑品类的付款积分设置0
				KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
				konkaBcompPd.setId(pshowOrdeDetails.getPd_id());
				konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
				BigDecimal pay_integral = new BigDecimal("0.0");
				if (all_pay_price != null) {
					if (konkaBcompPd.getProd_type() != null && konkaBcompPd.getProd_type().intValue() != 9
							&& pshowOrdeDetails.getTotal_price().intValue() > 0 && t.getTotal_price().intValue() > 0) {
						pay_integral = all_pay_price.multiply(pshowOrdeDetails.getTotal_price()).divide(
								t.getTotal_price());
					}
				}
				all_pay_integral = all_pay_integral.add(pay_integral);
				pshowOrdeDetails.setPay_integral(pay_integral);

				Long details_id = this.pshowOrdeDetailsDao.insertEntity(pshowOrdeDetails);
				// 订单明细中的商品绑定的服务
				List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = pshowOrdeDetails
						.getEcBindingPdOrdeDetailsList();
				if (null != ecBindingPdOrdeDetailsList && 0 != ecBindingPdOrdeDetailsList.size()) {
					for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ecBindingPdOrdeDetailsList) {
						ecBindingPdOrdeDetails.setDetails_id(details_id);
						this.ecBindingPdOrdeDetailsDao.insertEntity(ecBindingPdOrdeDetails);
					}
				}
			}

			// 更新付款积分
			PshowOrder p = new PshowOrder();
			p.setId(t.getId());
			// if (all_pay_integral.intValue() < t.getPay_price().intValue()) {
			p.setPay_integral(all_pay_integral);
			this.pshowOrderDao.updateEntity(p);
			// }
			// 购物券更新
			List<EcVouchers> ecVouchersList = t.getEcVouchersList();
			if (ecVouchersList != null && ecVouchersList.size() > 0) {
				for (EcVouchers ecvouchers : ecVouchersList) {
					EcVouchers e = new EcVouchers();
					e.setId(ecvouchers.getId());
					e.setInfo_state(new Integer(1));
					e.setOrder_id(order_id.toString());
					e.getMap().put("info_state", "1");
					ecVouchersDao.updateEntity(e);
				}
			}
		}
		return 0L;
	}

	public PshowOrder getPshowOrder(PshowOrder t) {
		return this.pshowOrderDao.selectEntity(t);
	}

	public Long getPshowOrderCount(PshowOrder t) {
		return this.pshowOrderDao.selectEntityCount(t);
	}

	public List<PshowOrder> getPshowOrderList(PshowOrder t) {
		return this.pshowOrderDao.selectEntityList(t);
	}

	public int modifyPshowOrder(PshowOrder t) {
		return this.pshowOrderDao.updateEntity(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-17
	 */
	public int modifyPshowOrderAndSaleNum(PshowOrder t) {

		// 更新销售数量
		PshowOrder pshowOrder = new PshowOrder();
		pshowOrder.setTrade_index(t.getTrade_index());
		List<PshowOrder> list = this.pshowOrderDao.selectEntityList(pshowOrder);
		for (PshowOrder t1 : list) {
			if (t1.getTrade_no() == null) {
				PshowOrdeDetails t2 = new PshowOrdeDetails();
				t2.setOrder_id(t1.getId());
				List<PshowOrdeDetails> t2List = this.pshowOrdeDetailsDao.selectEntityList(t2);
				for (PshowOrdeDetails t3 : t2List) {
					// 更新销量
					KonkaBcompPd t4 = new KonkaBcompPd();
					t4.setId(t3.getPd_id());
					t4.setSale_num(t3.getNum());
					this.konkaBcompPdDao.updateKonkaBcompPdForViewCountOrSaleNum(t4);

					// 更新库存
					EcStocks ecStocks = new EcStocks();
					ecStocks.setStocks(t3.getNum());
					ecStocks.setGoods_id(t3.getPd_id());
					ecStocks.setStore_id(t3.getStore_id());
					this.ecStocksDao.updateEcStocksForSale(ecStocks);
				}
			}
		}

		if (t.getMap().get("tuan") != null && ((String) t.getMap().get("tuan")).equals("2")
				&& t.getState().intValue() == 5) {
			pshowOrder = list.get(0);
			PshowOrdeDetails pd = new PshowOrdeDetails();
			pd.setOrder_id(pshowOrder.getId());
			List<PshowOrdeDetails> pdList = this.pshowOrdeDetailsDao.selectEntityList(pd);
			String pd_name_num = "";
			int num = 0;
			String djq = "1";
			Long group_id = 0L;
			Long shop_id = 0L;
			Date dd = null;
			for (PshowOrdeDetails ps : pdList) {
				num += ps.getNum().intValue();
				KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
				konkaBcompPd.setId(ps.getPd_id());
				konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
				pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
				if (konkaBcompPd.getIs_djq() != null && konkaBcompPd.getIs_djq().intValue() == 1) {
					djq = "2";
				}
				EcGroupBuyMain eb = new EcGroupBuyMain();
				eb.setGoods_id(konkaBcompPd.getId());
				eb = this.ecGroupBuyMainDao.selectEntity(eb);
				if (eb != null) {
					group_id = eb.getId();
					shop_id = eb.getShop_id();
					if (eb.getExp_date() != null) {
						dd = eb.getExp_date();
					}

				}
			}

			if (djq.equals("1")) {
				EcVouchCode ee = new EcVouchCode();
				ee.setTrade_index(pshowOrder.getTrade_index());
				List<EcVouchCode> eeList = this.ecVouchCodeDao.selectEntityList(ee);
				if (eeList == null || eeList.size() == 0) {
					EcVouchCode vc = new EcVouchCode();
					vc.setAdd_date(new Date());
					vc.setAdd_user_id(pshowOrder.getOrder_user_id());
					vc.setTrade_index(pshowOrder.getTrade_index());
					vc.setIs_userd(0);
					vc.setIs_del(0);
					vc.setShop_id(shop_id);
					vc.setVouch_id(group_id);
					String code = GetRandomNumber();
					vc.setCode(code);

					vc.setPrice(pshowOrder.getPay_price());
					this.ecVouchCodeDao.insertEntity(vc);
					// {工号姓名}您好！您已成功预购{产品名称}*{数量}，订单号为：{订单号}，验证码为：{验证码}，请妥善保管，该验证码是取货唯一凭证。团购成功后，我们将另行通知您取货事宜，敬请期待！[奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]

					String msg = pshowOrder.getBuyer_name() + "您好!您已成功预购" + pd_name_num + ",订单号为:"
							+ pshowOrder.getTrade_index() + ",验证码为:" + code
							+ ",请妥善保管,该验证码是取货唯一凭证。团购成功后，我们将另行通知您取货事宜,敬请期待![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";

					if (!"".equals(msg)) {
						EcMessage ecm = new EcMessage();
						ecm.setAdd_date(new Date());
						ecm.setContent(msg);
						ecm.setMobile(pshowOrder.getBuyer_mp());
						ecm.setOrder_id(pshowOrder.getId().toString());
						ecm.setSend_date(new Date());
						ecm.setOrder_state(5);
						try {
							if (getSendMessageResult(pshowOrder.getBuyer_mp(), msg)) {
								ecm.setSend_state(0);
							} else {
								ecm.setSend_state(1);
							}
						} catch (Exception e) {
						}
						ecMessageDao.insertEntity(ecm);
					}
				}

			} else if (djq.equals("2")) {
				EcVouchCode ee = new EcVouchCode();
				ee.setTrade_index(pshowOrder.getTrade_index());
				List<EcVouchCode> eeList = this.ecVouchCodeDao.selectEntityList(ee);
				if (eeList == null || eeList.size() == 0) {
					String codes = "";
					for (int i = 0; i < num; i++) {
						EcVouchCode vc = new EcVouchCode();
						vc.setAdd_date(new Date());
						vc.setAdd_user_id(pshowOrder.getOrder_user_id());
						vc.setTrade_index(pshowOrder.getTrade_index());
						vc.setIs_userd(0);
						vc.setIs_del(0);
						String code = GetRandomNumber();
						codes = codes + code + ",";
						vc.setCode(code);
						vc.setShop_id(shop_id);
						vc.setVouch_id(group_id);
						if (dd != null) {
							vc.setExp_date(dd);
						}
						vc.setPrice(pshowOrder.getPay_price().divide(new BigDecimal(num)));
						this.ecVouchCodeDao.insertEntity(vc);
					}
					if (codes.endsWith(",")) {
						codes = codes.substring(0, codes.length() - 1);
					}
					String msg = pshowOrder.getBuyer_name() + "您好!您已成功预购" + pd_name_num + ",订单号为:"
							+ pshowOrder.getTrade_index() + ",验证码为:" + codes
							+ ",请妥善保管,该验证码是取货唯一凭证。团购成功后，我们将另行通知您取货事宜,敬请期待![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";

					if (!"".equals(msg)) {
						EcMessage ecm = new EcMessage();
						ecm.setAdd_date(new Date());
						ecm.setContent(msg);
						ecm.setMobile(pshowOrder.getBuyer_mp());
						ecm.setOrder_id(pshowOrder.getId().toString());
						ecm.setSend_date(new Date());
						ecm.setOrder_state(5);
						try {
							if (getSendMessageResult(pshowOrder.getBuyer_mp(), msg)) {
								ecm.setSend_state(0);
							} else {
								ecm.setSend_state(1);
							}
						} catch (Exception e) {
						}
						ecMessageDao.insertEntity(ecm);
					}
				}
			}

		}

		return this.pshowOrderDao.updateEntity(t);
	}

	public int removePshowOrder(PshowOrder t) {
		return this.pshowOrderDao.deleteEntity(t);
	}

	public List<PshowOrder> getPshowOrderPaginatedList(PshowOrder t) {
		return this.pshowOrderDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-15
	 */
	public List<PshowOrder> getPshowOrderIncludeDetailsList(PshowOrder t) {
		return this.pshowOrderDao.selectPshowOrderIncludeDetailsList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-19
	 */
	public List<PshowOrder> getPshowOrderIncludeDetailsPaginatedList(PshowOrder t) {
		return this.pshowOrderDao.selectPshowOrderIncludeDetailsPaginatedList(t);
	}

	/**
	 * @author Pan,Gang
	 * @version 2013-08-16
	 */
	public List<PshowOrder> getPshowOrdeForDeptNameAndFullNameList(PshowOrder t) {
		return this.pshowOrderDao.selectPshowOrdeForDeptNameAndFullNameList(t);
	}

	public List<PshowOrder> getPshowOrdeForPdDetailsList(PshowOrder t) {
		return this.pshowOrderDao.selectPshowOrdeForPdDetailsList(t);
	}

	public Long getPshowOrdeForDeptNameAndFullNameListCount(PshowOrder t) {
		return this.pshowOrderDao.selectPshowOrdeForDeptNameAndFullNameListCount(t);
	}

	public Long getPshowOrdeForPdDetailsListCount(PshowOrder t) {
		return this.pshowOrderDao.selectPshowOrdeForPdDetailsListCount(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-12
	 */
	public BigDecimal getTotalPriceByOrderUserId(PshowOrder t) {
		return this.pshowOrderDao.selectTotalPriceByOrderUserId(t);
	}

	/**
	 * @author tudp
	 * @version 2013-09-14
	 */
	public int modifyPshowOrderForCancel(PshowOrder t) {
		return this.pshowOrderDao.updatePshowOrderForCancel(t);
	}

	/**
	 * @author WANGKUNLIN
	 * @version 2014-04-16
	 */
	public int modifyPshowOrderForSwitchR3(PshowOrder t) {
		return this.pshowOrderDao.updatePshowOrderForSwitchR3(t);
	}

	public Long createPshowOrdeAuditAndModifyPshowOrder(PshowOrder t, PshowOrdeAudit t1) throws Exception {
		this.pshowOrderDao.updateEntity(t);
		this.pshowOrdeAuditDao.insertEntity(t1);

		Long id = 0L;

		PshowOrder p = new PshowOrder();
		p.setId(t.getId());
		p = this.pshowOrderDao.selectEntity(p);

		if (t.getMap().get("tuan") != null && ((String) t.getMap().get("tuan")).equals("2")) {
			EcVouchCode evc = new EcVouchCode();
			evc.setTrade_index(p.getTrade_index());
			List<EcVouchCode> ecvList = this.ecVouchCodeDao.selectEntityList(evc);
			if (ecvList != null && ecvList.size() > 0) {
				for (EcVouchCode ecVouchCode : ecvList) {
					ecVouchCode.setIs_del(1);
					ecVouchCode.setDel_date(new Date());
					this.ecVouchCodeDao.updateEntity(ecVouchCode);
				}
			}
			// 模拟支付宝支付成功
			if (p.getState().intValue() == 5) {
				PshowOrdeDetails pd = new PshowOrdeDetails();
				pd.setOrder_id(p.getId());
				List<PshowOrdeDetails> pdList = this.pshowOrdeDetailsDao.selectEntityList(pd);
				String pd_name_num = "";
				Long group_id = 0L;
				Long shop_id = 0L;
				for (PshowOrdeDetails ps : pdList) {
					KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
					konkaBcompPd.setId(ps.getPd_id());
					konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
					pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
					EcGroupBuyMain eb = new EcGroupBuyMain();
					eb.setGoods_id(konkaBcompPd.getId());
					eb = this.ecGroupBuyMainDao.selectEntity(eb);
					if (eb != null) {
						group_id = eb.getId();
						shop_id = eb.getShop_id();
					}
				}
				if (ecvList == null || ecvList.size() == 0) {
					EcVouchCode vc = new EcVouchCode();
					vc.setAdd_date(new Date());
					vc.setAdd_user_id(p.getOrder_user_id());
					vc.setTrade_index(p.getTrade_index());
					vc.setIs_userd(0);
					String code = GetRandomNumber();
					vc.setCode(code);
					vc.setPrice(p.getPay_price());
					vc.setShop_id(shop_id);
					vc.setReal_name(p.getBuyer_name());
					vc.setVouch_id(group_id);
					this.ecVouchCodeDao.insertEntity(vc);

					String msg = p.getBuyer_name() + "您好!您已成功预购" + pd_name_num + ",订单号为：" + p.getTrade_index()
							+ ",验证码为:" + code
							+ ",请妥善保管,该验证码是取货唯一凭证。团购成功后,我们将另行通知您取货事宜,敬请期待![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";

					if (!"".equals(msg)) {
						EcMessage ecm = new EcMessage();
						ecm.setAdd_date(new Date());
						ecm.setContent(msg);
						ecm.setMobile(p.getBuyer_mp());
						ecm.setOrder_id(p.getId().toString());
						ecm.setSend_date(new Date());
						ecm.setOrder_state(5);
						try {
							if (getSendMessageResult(p.getBuyer_mp(), msg)) {
								ecm.setSend_state(0);
							} else {
								ecm.setSend_state(1);
							}
						} catch (Exception e) {
						}
						ecMessageDao.insertEntity(ecm);
					}
				}
			}

		} else if (t.getMap().get("tuan") != null && ((String) t.getMap().get("tuan")).equals("3")) {
			// 模拟支付宝支付成功
			if (p.getState().intValue() == 5) {
				PshowOrdeDetails pd = new PshowOrdeDetails();
				pd.setOrder_id(p.getId());
				List<PshowOrdeDetails> pdList = this.pshowOrdeDetailsDao.selectEntityList(pd);
				String pd_name_num = "";
				Long group_id = 0L;
				Long shop_id = 0L;
				int num = 0;
				Date dd = null;
				for (PshowOrdeDetails ps : pdList) {
					num += ps.getNum().intValue();
					KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
					konkaBcompPd.setId(ps.getPd_id());
					konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
					pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
					EcGroupBuyMain eb = new EcGroupBuyMain();
					eb.setGoods_id(konkaBcompPd.getId());
					eb = this.ecGroupBuyMainDao.selectEntity(eb);
					if (eb != null) {
						group_id = eb.getId();
						shop_id = eb.getShop_id();
						if (eb.getExp_date() != null) {
							dd = eb.getExp_date();
						}
					}
				}

				EcVouchCode ee = new EcVouchCode();
				ee.setTrade_index(p.getTrade_index());
				List<EcVouchCode> eeList = this.ecVouchCodeDao.selectEntityList(ee);
				if (eeList == null || eeList.size() == 0) {

					String codes = "";
					for (int i = 0; i < num; i++) {
						EcVouchCode vc = new EcVouchCode();
						vc.setAdd_date(new Date());
						vc.setAdd_user_id(p.getOrder_user_id());
						vc.setTrade_index(p.getTrade_index());
						vc.setIs_userd(0);
						vc.setIs_del(0);
						String code = GetRandomNumber();
						codes = codes + code + ",";
						vc.setCode(code);
						vc.setShop_id(shop_id);
						vc.setVouch_id(group_id);
						if (dd != null) {
							vc.setExp_date(dd);
						}
						vc.setReal_name(p.getBuyer_name());
						vc.setPrice(p.getPay_price().divide(new BigDecimal(num)));
						this.ecVouchCodeDao.insertEntity(vc);
					}
					if (codes.endsWith(",")) {
						codes = codes.substring(0, codes.length() - 1);
					}

					String msg = p.getBuyer_name() + "您好!您已成功预购" + pd_name_num + ",订单号为：" + p.getTrade_index()
							+ ",验证码为:" + codes
							+ ",请妥善保管,该验证码是取货唯一凭证。团购成功后,我们将另行通知您取货事宜,敬请期待![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";

					if (!"".equals(msg)) {
						EcMessage ecm = new EcMessage();
						ecm.setAdd_date(new Date());
						ecm.setContent(msg);
						ecm.setMobile(p.getBuyer_mp());
						ecm.setOrder_id(p.getId().toString());
						ecm.setSend_date(new Date());
						ecm.setOrder_state(5);
						try {
							if (getSendMessageResult(p.getBuyer_mp(), msg)) {
								ecm.setSend_state(0);
							} else {
								ecm.setSend_state(1);
							}
						} catch (Exception e) {
						}
						ecMessageDao.insertEntity(ecm);
					}
				}
			}
		}

		PshowOrdeDetails pd = new PshowOrdeDetails();
		pd.setOrder_id(p.getId());
		List<PshowOrdeDetails> pdList = this.pshowOrdeDetailsDao.selectEntityList(pd);

		int t_num = 0;
		String pd_name_num = "";// 机型*数量
		BigDecimal rebate = new BigDecimal("0.0");
		List<String> cargoList = new ArrayList<String>();// 货物名称
		List<String> cargo_count_List = new ArrayList<String>();// 货物数量
		List<String> cargo_unit_List = new ArrayList<String>();// 货物单位
		List<String> cargo_weight_List = new ArrayList<String>();// 货物重量
		List<String> cargo_amount_List = new ArrayList<String>();// 货物单价

		BigDecimal total_weight = new BigDecimal("0.00");

		for (PshowOrdeDetails ps : pdList) {
			t_num += ps.getNum();
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			if(konkaBcompPd.getPd_sn()!=null){
			konkaBcompPd.setId(ps.getPd_id());
			konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
			pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
			}
			if (ps.getRebates() != null) {
				rebate = rebate.add(ps.getRebates());
			}

			BigDecimal weight = new BigDecimal("0.00");
			if (konkaBcompPd.getSj_weight() != null) {
				weight = konkaBcompPd.getSj_weight();
			}

			total_weight = total_weight.add(weight.multiply(new BigDecimal(ps.getNum())));

			cargoList.add(konkaBcompPd.getPd_sn());
			cargo_count_List.add(ps.getNum().toString());
			cargo_unit_List.add("台");
			cargo_weight_List.add(weight.toString());
			cargo_amount_List.add(ps.getPrice().toString());

		}
		// 取订单客户信息、下单人信息
		String kh_name = p.getBuyer_name();
		String trade_index = p.getTrade_index();
		if (p.getIntegral() == null) {
			p.setIntegral(new BigDecimal("0.0"));
		}
		if (p.getPay_integral() == null) {
			p.setPay_integral(new BigDecimal("0.0"));
		}
		int integral = p.getIntegral().intValue();
		int pay_integral = p.getPay_integral().intValue();// p.getPay_price() == null ? p.getTotal_price().intValue() :
															// p.getPay_price().intValue();
		int all_integral = integral + pay_integral;
		String rebates = rebate.setScale(2).toString();

		EcUser ecUser = new EcUser();
		ecUser.setId(p.getOrder_user_id());
		ecUser = this.ecUserDao.selectEntity(ecUser);

		String user_real_name = ecUser.getReal_name();
		String user_tel = ecUser.getLink_phone();

		EcBaseExpress ec = new EcBaseExpress();
		if (null != t.getExpress_id()) {
			ec.setExpress_id(t.getExpress_id());
			ec = this.ecBaseExpressDao.selectEntity(ec);
		}

		PshowOrdeDetails ps = new PshowOrdeDetails();
		ps.setOrder_id(t.getId());
		List<PshowOrdeDetails> psList = this.pshowOrdeDetailsDao.selectEntityList(ps);
		if (null != psList && psList.size() > 0) {
			if (null != ec.getExpress_ui_type() && t.getState() == 40 && ec.getExpress_ui_type() == 1) {
				if (null != p) {
					SfOrderService sf = new SfOrderService();
					String sxddmxOpName = "sfexpressService";
					Order order = new Order();
					order.setOrderid(p.getTrade_index());

					// 判断客户是否选择自提和自提点ID
					String p_index = "";
					if (p.getIs_self() == 1 && null != p.getSelf_id()) {
						EcSelfArea ecs = new EcSelfArea();
						ecs.setId(p.getSelf_id());
						ecs = this.ecSelfAreaDao.selectEntity(ecs);
						p_index = ecs.getP_index().toString();
						order.setD_address(ecs.getSelf_addr());
					} else {
						if (p.getBuyer_p_index() != null && p.getBuyer_p_index() != -1) {
							p_index = p.getBuyer_p_index().toString();
						}
						order.setD_address(p.getBuyer_addr());
					}

					if (StringUtils.isNotBlank(p_index)) {
						if (!p_index.endsWith("00")) {
							if (p_index.length() == 6) {
								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
								baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
								order.setD_province(baseProvince.getP_name());

								BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
								baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
								baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
								order.setD_city(baseProvince1.getP_name());

								BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
								baseProvince2.setP_index(Long.valueOf(p_index));
								baseProvince2 = this.baseProvinceListFourDao.selectEntity(baseProvince2);
								order.setD_county(baseProvince2.getP_name());
							} else if (p_index.length() == 8) {
								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
								baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
								order.setD_province(baseProvince.getP_name());

								BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
								baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
								baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
								order.setD_city(baseProvince1.getP_name());

								BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
								baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
								baseProvince2 = this.baseProvinceListFourDao.selectEntity(baseProvince2);
								order.setD_county(baseProvince2.getP_name());

							}
						} else if (p_index.endsWith("0000")) {
							if (p_index.length() == 6) {
								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(p_index));
								baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
								order.setD_province(baseProvince.getP_name());
								order.setD_city("");
								order.setD_county("");
							} else if (p_index.length() == 8) {
								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(p_index));
								baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
								order.setD_province(baseProvince.getP_name());

								BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
								baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
								baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
								order.setD_city(baseProvince1.getP_name());
								order.setD_county("");
							}
						} else if (p_index.endsWith("00")) {
							if (p_index.length() == 6) {
								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
								baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
								order.setD_province(baseProvince.getP_name());

								BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
								baseProvince1.setP_index(Long.valueOf(p_index));
								baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
								order.setD_city(baseProvince1.getP_name());
								order.setD_county("");

							} else if (p_index.length() == 8) {

								BaseProvinceListFour baseProvince = new BaseProvinceListFour();
								baseProvince.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 2) + "0000"));
								baseProvince = this.baseProvinceListFourDao.selectEntity(baseProvince);
								order.setD_province(baseProvince.getP_name());

								BaseProvinceListFour baseProvince1 = new BaseProvinceListFour();
								baseProvince1.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 4) + "00"));
								baseProvince1 = this.baseProvinceListFourDao.selectEntity(baseProvince1);
								order.setD_city(baseProvince1.getP_name());

								BaseProvinceListFour baseProvince2 = new BaseProvinceListFour();
								baseProvince2.setP_index(Long.valueOf(StringUtils.substring(p_index, 0, 6)));
								baseProvince2 = this.baseProvinceListFourDao.selectEntity(baseProvince2);
								order.setD_county(baseProvince2.getP_name());

							}
						}
					}

					// 根据不同的分公司取不同的联系人，联系地址信息
					String fgs = (String) t.getMap().get("fgs");
					if (StringUtils.isNotBlank(fgs)) {
						if (fgs.equalsIgnoreCase("1")) {// 合肥
							order.setJ_contact("周仁明");
							order.setJ_tel("0550-3089116");
							order.setJ_address("滁州市中都大道999号");
							order.setJ_province("安徽省");
							order.setJ_city("滁州市");
							order.setJ_county("南谯区");
							order.setJ_mobile("13655504217");
							order.setCustid("5500047967");
							order.setValue1("5500047967");

						} else if (fgs.equalsIgnoreCase("2")) {// 东莞
							order.setJ_contact("黄生");
							order.setJ_tel("0769-82552522");
							order.setJ_address("广东东莞凤岗康佳直销中心");
							order.setJ_province("广东省");
							order.setJ_city("东莞市");
							order.setJ_county("凤岗");
							order.setJ_mobile("0769-82552522");
							order.setCustid("7691250396");
							order.setValue1("7691250396");
						} else if (fgs.equalsIgnoreCase("3")) {// 哈尔滨
							order.setJ_contact("张帅");
							order.setJ_tel("0451-55550681");
							order.setJ_address("哈尔滨市道外区大新街75号康佳集团哈尔滨分公司");
							order.setJ_province("黑龙江省");
							order.setJ_city("哈尔滨市");
							order.setJ_county("道外区");
							order.setJ_mobile("18645145490");
							order.setHead("kjjt,IHcoDnPIalG8oYBzxMYomOwMmHzdykSO");// 哈尔滨顾客编码，密码

							// 保价
							order.setS_price("0");
							order.setPrice(p.getPay_price().toString());
							order.setCustid("4512483409");
						}
					}

					order.setD_contact(p.getBuyer_name());
					order.setD_tel(p.getBuyer_tel() == null ? "" : p.getBuyer_tel());
					order.setD_mobile(p.getBuyer_mp());
					// order.setD_address(p.getBuyer_addr());
					// order.setParcel_quantity(String.valueOf(t_num));//
					// 包裹数
					order.setParcel_quantity("1");

					// 货物信息，多个货物用,隔开
					order.setCargo(StringUtils.join(cargoList.toArray(), ","));
					order.setCargo_count(StringUtils.join(cargo_count_List.toArray(), ","));
					order.setCargo_unit(StringUtils.join(cargo_unit_List.toArray(), ","));
					order.setCargo_weight(StringUtils.join(cargo_weight_List.toArray(), ","));
					order.setCargo_amount(StringUtils.join(cargo_amount_List.toArray(), ","));
					order.setCargo_total_weight(total_weight.toString());
					order.setD_company("");
					order.setSendstarttime("");
					// 如果是货到付款
					if (p.getPay_way() == 0) {
						order.setPay_way("0");
						order.setValue(p.getPay_price().toString());
					}
					String xml = order.toXml();
					//System.out.println("xml--->" + xml);
					String returnXml = sf.sfWebService2(xml, sxddmxOpName);
					try {
						Document doc = DocumentHelper.parseText(returnXml);
						Element rootElt = doc.getRootElement();
						if (rootElt.elementTextTrim("Head").equalsIgnoreCase("OK")) {
							Iterator<Element> iter = rootElt.elementIterator("Body");
							while (iter.hasNext()) {
								Element iter2 = iter.next();
								Iterator<Element> iter3 = iter2.elementIterator("OrderResponse");
								while (iter3.hasNext()) {
									Element iter4 = iter3.next();
									if (StringUtils.isNotBlank(iter4.attributeValue("mailno"))) {
										EcOrderExpressInfo ex = new EcOrderExpressInfo();
										ex.setTrade_index(p.getTrade_index());
										ex.setExpress_id(t.getExpress_id());
										ex.setExpress_name(ec.getExpress_name());
										ex.setLogistic_sn(iter4.attributeValue("mailno"));
										ex.setOrder_from(iter4.attributeValue("origincode"));
										ex.setOrder_to(iter4.attributeValue("destcode"));
										ex.setAdd_date(new Date());
										this.ecOrderExpressInfoDao.insertEntity(ex);

										// 订单走顺丰物流发送的短信
										String msg = kh_name
												+ "您好,您的订单"
												+ trade_index
												+ "（"
												+ pd_name_num
												+ "）已经安排发货,顺丰的物流单号"
												+ ex.getLogistic_sn()
												+ ",请注意查收,签收时请一定开箱验货,如有屏碎或外观损坏请拒收!如您需要上门安装调试,请致电4008800016,我们将尽快与您确定上门服务时间![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346,售后问题请致电康佳大拇指服务热线4008800016]";
										String msg2 = user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
												+ pd_name_num + "）已经安排发货,顺丰的物流单号" + ex.getLogistic_sn()
												+ ",请注意查收![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
										if (ecUser.getUser_type().intValue() == 2) {
											if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
												msg = "【康佳&顺丰直销商城】"
														+ kh_name
														+ "您好,您的订单"
														+ trade_index
														+ "（"
														+ pd_name_num
														+ "）已经安排发货,顺丰的物流单号"
														+ ex.getLogistic_sn()
														+ ",请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有外观破损或屏碎的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-26608866-6346，上门安装调试,请致电4008800016]";
												msg2 = "";
											} else {
												msg = "【康佳网上直销商城】"
														+ kh_name
														+ "您好,您的订单"
														+ trade_index
														+ "（"
														+ pd_name_num
														+ "）已经安排发货,顺丰的物流单号"
														+ ex.getLogistic_sn()
														+ ",请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有外观破损或屏碎的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-26608866-6346，上门安装调试,请致电4008800016]";
												msg2 = "【康佳网上直销商城】"
														+ user_real_name
														+ "您好,"
														+ kh_name
														+ "的订单"
														+ trade_index
														+ "（"
														+ pd_name_num
														+ "）已经安排发货,顺丰的物流单号"
														+ ex.getLogistic_sn()
														+ ",请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有因运输产生的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-26608866-6346，上门安装调试,请致电4008800016]";
											}
										}
										if (!"".equals(msg)) {
											EcMessage ecm = new EcMessage();
											ecm.setAdd_date(new Date());
											ecm.setContent(msg);
											ecm.setMobile(p.getBuyer_mp());
											ecm.setOrder_id(p.getId().toString());
											ecm.setSend_date(new Date());
											ecm.setOrder_state(40);
											if (getSendMessageResult(p.getBuyer_mp(), msg)) {
												ecm.setSend_state(0);
											} else {
												ecm.setSend_state(1);
											}
											ecMessageDao.insertEntity(ecm);
										}
										if (!"".equals(msg2)) {
											EcMessage ecm = new EcMessage();
											ecm.setAdd_date(new Date());
											ecm.setContent(msg2);
											ecm.setMobile(user_tel);
											ecm.setOrder_id(p.getId().toString());
											ecm.setSend_date(new Date());
											ecm.setOrder_state(40);
											if (getSendMessageResult(user_tel, msg2)) {// 给下单用户发短信
												ecm.setSend_state(0);
											} else {
												ecm.setSend_state(1);
											}
											ecMessageDao.insertEntity(ecm);
										}
										id = 1L;
									}
								}
							}
						}
					} catch (Exception e) {
						id = 0L;
						//System.out.println("报错啦！！");
					}
				}
			} else if (null != ec.getExpress_ui_type() && t.getState() == 40 && ec.getExpress_ui_type() == 100) {
				// 订单未走顺丰物流发送的短信
				String msg = kh_name
						+ "您好,您的订单"
						+ trade_index
						+ "（"
						+ pd_name_num
						+ "）已经安排发货,请注意查收,签收时请一定开箱验货,如有屏碎或外观损坏请拒收!如您需要上门安装调试,请致电4008800016,我们将尽快与您确定上门服务时间![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346,售后问题请致电康佳大拇指服务热线4008800016]";
				String msg2 = user_real_name + "您好," + kh_name + "的订单" + trade_index + "（" + pd_name_num
						+ "）已经安排发货,请注意查收![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
				if (ecUser.getUser_type().intValue() == 2) {
					if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
						msg = "【康佳&顺丰直销商城】"
								+ kh_name
								+ "您好,您的订单"
								+ trade_index
								+ "（"
								+ pd_name_num
								+ "）已经安排发货,请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有外观破损或屏碎的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-26608866-6346，上门安装调试,请致电4008800016]";
						msg2 = "";
					} else {
						msg = "【康佳网上直销商城】"
								+ kh_name
								+ "您好,您的订单"
								+ trade_index
								+ "（"
								+ pd_name_num
								+ "）已经安排发货,请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有外观破损或屏碎的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-26608866-6346，上门安装调试,请致电4008800016]";
						msg2 = "【康佳网上直销商城】"
								+ user_real_name
								+ "您好,"
								+ kh_name
								+ "的订单"
								+ trade_index
								+ "（"
								+ pd_name_num
								+ "）已经安排发货,请注意查收!为了确保您能收到满意的商品，请您务必在收货签收前开箱验机，如有因运输产生的问题请直接拒收，并告知客服，稍后我们将会为您安排换机；如签收后发现任何问题请务必在12小时内联系康佳厂家售后4008800016上门鉴定，并同时告知客服跟进处理。 [奉献精致产品,引领美妙生活!客服热线0755-26608866-6346，上门安装调试,请致电4008800016]";
					}
				}
				if (!"".equals(msg)) {
					EcMessage ecm = new EcMessage();
					ecm.setAdd_date(new Date());
					ecm.setContent(msg);
					ecm.setMobile(p.getBuyer_mp());
					ecm.setOrder_id(p.getId().toString());
					ecm.setSend_date(new Date());
					ecm.setOrder_state(40);
					if (getSendMessageResult(p.getBuyer_mp(), msg)) {
						ecm.setSend_state(0);
					} else {
						ecm.setSend_state(1);
					}
					ecMessageDao.insertEntity(ecm);
				}
				if (!"".equals(msg2)) {
					EcMessage ecm = new EcMessage();
					ecm.setAdd_date(new Date());
					ecm.setContent(msg2);
					ecm.setMobile(user_tel);
					ecm.setOrder_id(p.getId().toString());
					ecm.setSend_date(new Date());
					ecm.setOrder_state(40);
					if (getSendMessageResult(user_tel, msg2)) {// 给下单用户发短信
						ecm.setSend_state(0);
					} else {
						ecm.setSend_state(1);
					}
					ecMessageDao.insertEntity(ecm);
				}

			} else if (t.getState() == 20) {// 审核通过发短信
				String msg = kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
						+ "）已经确认,我们将尽快安排发货![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
				String msg2 = user_real_name + "您好," + kh_name + "的订单" + trade_index + "（" + pd_name_num
						+ "）已经确认,我们将尽快安排发货![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
				if (ecUser.getUser_type().intValue() == 2) {
					if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
						msg = "【康佳&顺丰直销商城】" + kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
								+ "）已经确认,我们将尽快安排发货![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
						msg2 = "";
					} else {
						msg = "【康佳网上直销商城】" + kh_name + "您好,您的订单" + trade_index + "（" + pd_name_num
								+ "）已经确认,我们将尽快安排发货![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
						msg2 = "【康佳网上直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
								+ pd_name_num + "）已经确认,我们将尽快安排发货![奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
					}
				}
				if ((null != p.getBuyer_mp()) && (!"".equals(p.getBuyer_mp())) && (null != t.getMap().get("content"))) {
					if (!"".equals(msg)) {
						EcMessage ecm = new EcMessage();
						ecm.setAdd_date(new Date());
						ecm.setContent(msg);
						ecm.setMobile(p.getBuyer_mp());
						ecm.setOrder_id(p.getId().toString());
						ecm.setSend_date(new Date());
						ecm.setOrder_state(20);
						if (getSendMessageResult(p.getBuyer_mp(), msg)) {
							ecm.setSend_state(0);
						} else {
							ecm.setSend_state(1);
						}
						ecMessageDao.insertEntity(ecm);
					}
					if (!"".equals(msg2)) {
						EcMessage ecm = new EcMessage();
						ecm.setAdd_date(new Date());
						ecm.setContent(msg2);
						ecm.setMobile(user_tel);
						ecm.setOrder_id(p.getId().toString());
						ecm.setSend_date(new Date());
						ecm.setOrder_state(20);
						if (getSendMessageResult(user_tel, msg2)) {// 给下单用户发短信
							ecm.setSend_state(0);
						} else {
							ecm.setSend_state(1);
						}
						ecMessageDao.insertEntity(ecm);
					}
				}
			} else if (t.getState() == 60) {
				// 订单确认收货，给下单人发送短信
				if (null != ecUser.getLink_phone() && !"".equals(ecUser.getLink_phone())) {
					String msg = user_real_name + "您好," + kh_name + "的订单" + trade_index + "已签收,积分" + all_integral
							+ "其中:付款积分" + pay_integral + ",奖励积分" + integral + "已到账;佣金" + rebates
							+ ",请您及时进入会员中心,点击提现或转积分![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
					if (ecUser.getUser_type().intValue() == 2) {
						if (ecUser.getDept_id() != null && ecUser.getDept_id().intValue() == 2110) {
							msg = "【康佳&顺丰直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
									+ pd_name_num + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
						} else {
							msg = "【康佳网上直销商城】" + user_real_name + "您好," + kh_name + "的订单" + trade_index + "（"
									+ pd_name_num + "）已签收[奉献精致产品,引领美妙生活!客服热线0755-26608866-6346]";
						}
					}
					EcMessage ecm = new EcMessage();
					ecm.setAdd_date(new Date());
					ecm.setContent(msg);
					ecm.setMobile(user_tel);
					ecm.setOrder_id(p.getId().toString());
					ecm.setSend_date(new Date());
					ecm.setOrder_state(60);
					if (getSendMessageResult(user_tel, msg)) {// 给下单用户发短信
						ecm.setSend_state(0);
						ecMessageDao.insertEntity(ecm);
					} else {
						ecm.setSend_state(1);
						ecMessageDao.insertEntity(ecm);
					}
				}
			}
		}

		return id;
	}

	public Long createPshowOrdeAuditAndModifyPshowOrder3(PshowOrder t2, PshowOrdeAudit t1, JBill t) throws Exception {
		this.pshowOrderDao.updateEntity(t2);
		this.pshowOrdeAuditDao.insertEntity(t1);

		JBasePartner jBasePartner = t.getjBasePartner();
		if (null != jBasePartner) {
			if (jBasePartner.getPartner_id() == null) {
				Long p_id = this.jBasePartnerDao.insertEntity(jBasePartner);
				t.setPartner_id(p_id);
			} else {
				this.jBasePartnerDao.updateEntity(jBasePartner);
			}
		}

		Long id = this.jBillDao.insertEntity(t);
		List<JBillDetails> detailsList = t.getjBillDetailsList();
		if (null != detailsList && detailsList.size() > 0) {
			for (JBillDetails details : detailsList) {
				details.setBill_id(id);
				Long details_id = this.jBillDetailsDao.insertEntity(details);

				// 商品入栈
				if (t.getBill_type() == 10) { // 进货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.push(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								details.getPrice(), t.getBill_sn());
					}
				}
				if (t.getBill_type() == 21) { // 销售退货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.rejected(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								t.getR_bill_sn());
					}
				}
				if (t.getBill_type() == 42) { // 分销退货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.rejected(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								t.getR_bill_sn());
					}
				}
				// 商品出栈
				if (t.getBill_type() == 11 || t.getBill_type() == 20 || t.getBill_type() == 40) { // 销售，进货退货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.pop(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								t.getBill_sn());
					}
				}
				if (t.getBill_type() == 20 || t.getBill_type() == 40) { // 销售，分销时，更新成本
					JStocksStack stack = new JStocksStack();
					stack.setGoods_id(details.getGoods_id());
					stack.setStore_id(details.getStore_id());
					stack.setBill_id_pop(t.getBill_sn());
					stack.setC_id(t.getC_id());

					List<JStocksStack> stacks = this.jStocksStackDao.selectEntityList(stack);

					if (null != stacks && stacks.size() > 0) {
						BigDecimal total_cost = new BigDecimal("0");
						for (JStocksStack temp : stacks) {
							total_cost = total_cost.add(temp.getGoods_cost());
						}
						// 更新成本
						JBillDetails dils = new JBillDetails();
						dils.setBill_item_id(details_id);
						dils.setCost(total_cost);

						this.jBillDetailsDao.updateEntity(dils);
					}

				}
			}
		}

		return id;
	}

	public Long createPshowOrdeAuditAndModifyPshowOrder2(PshowOrder t, PshowOrdeAudit t1) {
		this.pshowOrderDao.updateEntity(t);
		this.pshowOrdeAuditDao.insertEntity(t1);
		return 0L;
	}

	public Long createPshowOrdeAuditAndModifyPshowOrderForBatch(PeProdUser ecuser, String[] pks) throws Exception {

		for (String id : pks) {
			//System.out.println("id___________>" + id);
			PshowOrder p1 = new PshowOrder();
			p1.setId(Long.valueOf(id));
			p1 = this.pshowOrderDao.selectEntity(p1);
			if (p1.getIs_hdfk() != 0) {
				return null;
			}

			// 更新订单表
			PshowOrder entity = new PshowOrder();
			entity.setState(p1.getState());
			entity.setIs_hdfk(1);// 表示财务已经收到顺丰货款
			entity.setId(Long.valueOf(id));

			this.pshowOrderDao.updateEntity(entity);

			PshowOrder pp = new PshowOrder();
			pp.setId(Long.valueOf(id));
			pp = this.pshowOrderDao.selectEntity(pp);

			// 审核记录表插入记录
			PshowOrdeAudit poa = new PshowOrdeAudit();
			poa.setOper_date(new Date());
			poa.setOrder_id(Long.valueOf(id));
			poa.setState(70);
			poa.setOpr_user_id(ecuser.getId());
			poa.setOpr_user_real_name(ecuser.getUser_name());
			poa.setTotal_price(pp.getPay_price());
			this.pshowOrdeAuditDao.insertEntity(poa);
		}

		return 1L;
	}

	@Override
	public List<PshowOrder> getPshowOrderSubIncludeDetailsPaginatedList(PshowOrder t) throws Exception {
		return this.pshowOrderDao.selectPshowOrderSubIncludeDetailsPaginatedList(t);
	}

	@Override
	public Long getPshowOrderSubIncludeDetailsPaginatedListCount(PshowOrder t) throws Exception {
		return (Long) this.pshowOrderDao.selectPshowOrderSubIncludeDetailsPaginatedListCount(t);
	}

	@Override
	public List<PshowOrder> getPshowOrderAndDetailsForTj(PshowOrder t) throws DataAccessException {
		return this.pshowOrderDao.selectPshowOrderAndDetailsForTj(t);
	}

	@Override
	public List<PshowOrder> getPshowOrderAndDetailsForTj2(PshowOrder t) throws DataAccessException {
		return this.pshowOrderDao.selectPshowOrderAndDetailsForTj2(t);
	}

	public Long createPshowOrderWithDetailsForTuan(PshowOrder t) {
		EcUser ecUser = new EcUser();
		ecUser.setId(t.getOrder_user_id());
		ecUser = this.ecUserDao.selectEntity(ecUser);
		// 计算奖励积分
		BigDecimal x = new BigDecimal("50");
		// 银卡会员获取奖励积分=付款金额*50
		if (ecUser.getEcBaseCardLevel() != null && ecUser.getEcBaseCardLevel().getCard_type_discount() != null) {
			x = ecUser.getEcBaseCardLevel().getCard_type_discount();
		}

		t.setPay_integral(t.getPay_price());
		Long order_id = this.pshowOrderDao.insertEntity(t);
		int state = t.getState().intValue();
		// 订单明细
		List<PshowOrdeDetails> pshowOrdeDetailsList = t.getPshowOrdeDetailsList();
		String pd_name_num = "";// 机型*数量 ;
		BigDecimal all_pay_integral = new BigDecimal("0.0");
		BigDecimal all_integral = new BigDecimal("0.0");
		BigDecimal all_pay_price = t.getPay_price();
		for (PshowOrdeDetails pshowOrdeDetails : pshowOrdeDetailsList) {
			pshowOrdeDetails.setOrder_id(order_id);
			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(pshowOrdeDetails.getPd_id());
			konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
			pd_name_num += konkaBcompPd.getPd_sn() + "*" + pshowOrdeDetails.getNum() + " ";
			BigDecimal pay_integral = new BigDecimal("0.0");
			BigDecimal integral = new BigDecimal("0.0");
			if (all_pay_price != null) {
				if (konkaBcompPd.getProd_type() != null && konkaBcompPd.getProd_type().intValue() != 9
						&& pshowOrdeDetails.getTotal_price().intValue() > 0 && t.getTotal_price().intValue() > 0) {
					pay_integral = all_pay_price.multiply(pshowOrdeDetails.getTotal_price()).divide(t.getTotal_price(),
							2, BigDecimal.ROUND_HALF_EVEN);
				}
			}
			all_pay_integral = all_pay_integral.add(pay_integral);
			if (pay_integral.intValue() > 0) {
				integral = pay_integral.multiply(x).divide(new BigDecimal("100"));
			}
			all_integral = all_integral.add(integral);
			pshowOrdeDetails.setPay_integral(pay_integral);
			pshowOrdeDetails.setIntegral(integral);
			Long details_id = this.pshowOrdeDetailsDao.insertEntity(pshowOrdeDetails);

			// 订单明细中的商品绑定的服务
			List<EcBindingPdOrdeDetails> ecBindingPdOrdeDetailsList = pshowOrdeDetails.getEcBindingPdOrdeDetailsList();
			if (null != ecBindingPdOrdeDetailsList && 0 != ecBindingPdOrdeDetailsList.size()) {
				for (EcBindingPdOrdeDetails ecBindingPdOrdeDetails : ecBindingPdOrdeDetailsList) {
					ecBindingPdOrdeDetails.setDetails_id(details_id);
					this.ecBindingPdOrdeDetailsDao.insertEntity(ecBindingPdOrdeDetails);
				}
			}
			// 费用规则
			List<EcRule> ecRuleList = pshowOrdeDetails.getEcRuleList();
			if (null != ecRuleList && ecRuleList.size() > 0) {
				for (EcRule ecRule : ecRuleList) {
					EcRuleBingdingDetail ecb = new EcRuleBingdingDetail();
					ecb.setDetail_id(pshowOrdeDetails.getBill_item_id());
					ecb.setRule_id(ecRule.getId());
					ecb.setRule_money(ecRule.getRule_price().toString());
					ecb.setRule_type(ecRule.getRule_type().toString());
					ecb.setRule_desc(ecRule.getRule_msg());
					this.ecRuleBingdingDetailDao.insertEntity(ecb);
				}
			}

			if (state == 5 || t.getPay_way().intValue() == 0 || t.getPay_way().intValue() == 1) {
				// 更新销量
				KonkaBcompPd t4 = new KonkaBcompPd();
				t4.setId(pshowOrdeDetails.getPd_id());
				t4.setSale_num(pshowOrdeDetails.getNum());
				this.konkaBcompPdDao.updateKonkaBcompPdForViewCountOrSaleNum(t4);

				// 更新库存
				EcStocks ecStocks = new EcStocks();
				ecStocks.setStocks(pshowOrdeDetails.getNum());
				ecStocks.setGoods_id(pshowOrdeDetails.getPd_id());
				ecStocks.setStore_id(pshowOrdeDetails.getStore_id());
				this.ecStocksDao.updateEcStocksForSale(ecStocks);
			}

		}
		// 购物券更新
		List<EcVouchers> ecVouchersList = t.getEcVouchersList();
		if (ecVouchersList != null && ecVouchersList.size() > 0) {
			for (EcVouchers ecvouchers : ecVouchersList) {
				EcVouchers e = new EcVouchers();
				e.setId(ecvouchers.getId());
				e.setInfo_state(new Integer(1));
				e.setOrder_id(order_id.toString());
				e.getMap().put("info_state", "1");
				ecVouchersDao.updateEntity(e);
			}
		}
		// 更新付款积分、奖励积分
		PshowOrder p = new PshowOrder();
		p.setId(t.getId());
		p.setPay_integral(all_pay_integral);
		p.setIntegral(all_integral);
		this.pshowOrderDao.updateEntity(p);

		return order_id;
	}

	@Override
	public Long createPshowOrdeAuditAndModifyPshowOrderForTuan(PshowOrder t, PshowOrdeAudit t1) throws Exception {
		this.pshowOrderDao.updateEntity(t);
		this.pshowOrdeAuditDao.insertEntity(t1);

		Long id = 0L;

		PshowOrder p = new PshowOrder();
		p.setId(t.getId());
		p = this.pshowOrderDao.selectEntity(p);

		PshowOrdeDetails pd = new PshowOrdeDetails();
		pd.setOrder_id(p.getId());
		List<PshowOrdeDetails> pdList = this.pshowOrdeDetailsDao.selectEntityList(pd);

		int t_num = 0;
		String pd_name_num = "";// 机型*数量
		BigDecimal rebate = new BigDecimal("0.0");
		List<String> cargoList = new ArrayList<String>();// 货物名称
		List<String> cargo_count_List = new ArrayList<String>();// 货物数量
		List<String> cargo_unit_List = new ArrayList<String>();// 货物单位
		List<String> cargo_weight_List = new ArrayList<String>();// 货物重量
		List<String> cargo_amount_List = new ArrayList<String>();// 货物单价

		BigDecimal total_weight = new BigDecimal("0.00");

		for (PshowOrdeDetails ps : pdList) {
			t_num += ps.getNum();

			KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
			konkaBcompPd.setId(ps.getPd_id());
			konkaBcompPd = this.konkaBcompPdDao.selectEntity(konkaBcompPd);
			pd_name_num += konkaBcompPd.getPd_sn() + "*" + ps.getNum() + " ";
			if (ps.getRebates() != null) {
				rebate = rebate.add(ps.getRebates());
			}

			BigDecimal weight = new BigDecimal("0.00");
			if (konkaBcompPd.getSj_weight() != null) {
				weight = konkaBcompPd.getSj_weight();
			}

			total_weight = total_weight.add(weight.multiply(new BigDecimal(ps.getNum())));

			cargoList.add(konkaBcompPd.getPd_sn());
			cargo_count_List.add(ps.getNum().toString());
			cargo_unit_List.add("台");
			cargo_weight_List.add(weight.toString());
			cargo_amount_List.add(ps.getPrice().toString());

		}
		// 取订单客户信息、下单人信息
		String kh_name = p.getBuyer_name();
		String trade_index = p.getTrade_index();
		if (p.getIntegral() == null) {
			p.setIntegral(new BigDecimal("0.0"));
		}
		int integral = p.getIntegral().intValue();
		int pay_integral = p.getPay_price() == null ? p.getTotal_price().intValue() : p.getPay_price().intValue();
		int all_integral = integral + pay_integral;
		String rebates = rebate.setScale(2).toString();

		EcUser ecUser = new EcUser();
		ecUser.setId(p.getOrder_user_id());
		ecUser = this.ecUserDao.selectEntity(ecUser);

		String user_real_name = ecUser.getReal_name();
		String user_tel = ecUser.getLink_phone();

		EcBaseExpress ec = new EcBaseExpress();
		if (null != t.getExpress_id()) {
			ec.setExpress_id(t.getExpress_id());
			ec = this.ecBaseExpressDao.selectEntity(ec);
		}

		PshowOrdeDetails ps = new PshowOrdeDetails();
		ps.setOrder_id(t.getId());
		List<PshowOrdeDetails> psList = this.pshowOrdeDetailsDao.selectEntityList(ps);
		if (null != psList && psList.size() > 0) {
			if (t.getState() == 60) {

				EcVouchCode ev = new EcVouchCode();
				ev.setId((Long) t.getMap().get("code_id"));
				ev.setIs_userd(1);
				ev.setUsed_date(new Date());
				this.ecVouchCodeDao.updateEntity(ev);

				// 订单确认收货，给购买人发短信
				// {工号姓名}您好！您预购的{产品名称}*{数量}（订单号为：{订单号}）已确认收货，感谢您的支持！[奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]
				if (null != p.getBuyer_mp() && !"".equals(p.getBuyer_mp())) {
					String msg = user_real_name + "您好!您预购的" + pd_name_num + "（订单号为:" + p.getTrade_index()
							+ "）已确认收货,感谢您的支持![奉献精致产品,引领美妙生活!康佳EPP平台,客服热线0755-26608866-6346]";
					EcMessage ecm = new EcMessage();
					ecm.setAdd_date(new Date());
					ecm.setContent(msg);
					ecm.setMobile(p.getBuyer_mp());
					ecm.setOrder_id(p.getId().toString());
					ecm.setSend_date(new Date());
					ecm.setOrder_state(60);
					if (getSendMessageResult(p.getBuyer_mp(), msg)) {// 给购买人发短信
						ecm.setSend_state(0);
						ecMessageDao.insertEntity(ecm);
					} else {
						ecm.setSend_state(1);
						ecMessageDao.insertEntity(ecm);
					}
				}
			}
		}

		return id;
	}

	public String GetRandomNumber() {

		// 使用SET以此保证写入的数据不重复
		List<String> list = new ArrayList<String>();
		String[] arg = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };//
		// 随机数
		Random random = new Random();
		int ss = 0;
		for (int i = 0; i < arg.length + 1; i++) {
			while (list.size() < 12) {
				// nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
				// 和指定值（不包括）之间均匀分布的 int 值。
				ss = random.nextInt(arg.length);
				list.add(arg[ss]);
			}
		}

		String code = "";
		for (String string : list) {
			code = code + string;
		}
		//System.out.println(code);// 0123

		EcVouchCode ee = new EcVouchCode();
		List<EcVouchCode> eeList = this.ecVouchCodeDao.selectEntityList(ee);
		List<String> cc = new ArrayList<String>();
		if (eeList != null && eeList.size() > 0) {
			for (EcVouchCode ecVouchCode : eeList) {
				if (ecVouchCode.getCode() != null) {
					cc.add(ecVouchCode.getCode());
				}
			}
		}

		String codeList = StringUtils.join(cc, ",");
		if (codeList != null && !codeList.equals(""))
			if (codeList.contains(code)) {
				GetRandomNumber();
			}

		return code;
	}

}
