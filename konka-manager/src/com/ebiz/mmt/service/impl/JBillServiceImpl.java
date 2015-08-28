package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ConsumerInfoDao;
import com.ebiz.mmt.dao.JBasePartnerDao;
import com.ebiz.mmt.dao.JBillDao;
import com.ebiz.mmt.dao.JBillDetailsDao;
import com.ebiz.mmt.dao.JStocksStackDao;
import com.ebiz.mmt.dao.JSubSellRecDao;
import com.ebiz.mmt.domain.ConsumerInfo;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.JBill;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.domain.JStocksStack;
import com.ebiz.mmt.domain.JSubSellRec;
import com.ebiz.mmt.service.JBillService;
import com.ebiz.mmt.service.JStocksStackService;
import com.ebiz.mmt.service.JxcFifoStocksService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBillServiceImpl implements JBillService {

	@Resource
	private JBillDao jBillDao;

	@Resource
	private JBillDetailsDao jBillDetailsDao;

	@Resource
	private JSubSellRecDao jSubSellRecDao;

	@Resource
	private JBasePartnerDao jBasePartnerDao;

	@Resource
	JStocksStackService jStocksStackService;

	@Resource
	JStocksStackDao jStocksStackDao;
	
	@Resource
	ConsumerInfoDao consumerInfoDao;

	@Resource
	JxcFifoStocksService jxcFifoStocksService;

	public Long createJBill(JBill t) {
		return this.jBillDao.insertEntity(t);
	}

	public JBill getJBill(JBill t) {
		return this.jBillDao.selectEntity(t);
	}

	public Long getJBillCount(JBill t) {
		return this.jBillDao.selectEntityCount(t);
	}

	@Override
	public Long getJBillCountForFourWeek(JBill t) {
		return this.jBillDao.selectCountForFourWeek(t);
	}

	public List<JBill> getJBillList(JBill t) {
		return this.jBillDao.selectEntityList(t);
	}

	public int modifyJBill(JBill t) {
		return this.jBillDao.updateEntity(t);
	}

	public int removeJBill(JBill t) {
		return this.jBillDao.deleteEntity(t);
	}

	public List<JBill> getJBillPaginatedList(JBill t) {
		return this.jBillDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	public Long getSeqJBillId() {
		return this.jBillDao.selectSeqJBillId();
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	public Long createJBillAndDeatails(JBill t) {

		if(10!=t.getBill_type()&&11!=t.getBill_type()) {
			//获取网点信息
			JBasePartner jBasePartner = t.getjBasePartner();
			if (null != jBasePartner) {
				//新增网点
				if (null == jBasePartner.getPartner_id()) {
					jBasePartner.setAdd_date(new Date());
					if (null != jBasePartner.getMap().get("add_user_id")) {
						jBasePartner.setCreate_user_id(Long.parseLong(jBasePartner.getMap().get("add_user_id").toString()));
					}
					Long p_id = this.jBasePartnerDao.insertEntity(jBasePartner);

					t.setPartner_id(p_id);
				} else {
					this.jBasePartnerDao.updateEntity(jBasePartner);
				}
			}
		}

		//新增订单
		t.setBill_state(0);
		Long id = this.jBillDao.insertEntity(t);
		
		ConsumerInfo con = new ConsumerInfo();
		Long total_num = 0L;
		BigDecimal total_money = new BigDecimal(0);
		
		List<JBillDetails> detailsList = t.getjBillDetailsList();
		if (null != detailsList && detailsList.size() > 0) {
			for (JBillDetails details : detailsList) {
				details.setBill_id(id);
				Long details_id = this.jBillDetailsDao.insertEntity(details);
				
				if(20==t.getBill_type()){
					total_num += details.getNum();
					total_money = total_money.add(details.getMoney());
				}

				// 商品入栈
				if (t.getBill_type() == 10) { // 进货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.push(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								details.getPrice(), t.getBill_sn());
						// 将采购得数据入栈
						jxcFifoStocksService.stock_in(details.getStore_id(), details.getGoods_id(), details.getPrice(),
								1, new Date(), 40);
					}
				}
				if (t.getBill_type() == 21) { // 销售退货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.rejected(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								t.getR_bill_sn());
						// 销售退货
						jxcFifoStocksService.stock_in(details.getStore_id(), details.getGoods_id(), details.getPrice(),
								1, new Date(), 110);
					}
				}
				if (t.getBill_type() == 42) { // 分销退货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.rejected(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								t.getR_bill_sn());
						// 销售退货
						jxcFifoStocksService.stock_in(details.getStore_id(), details.getGoods_id(), details.getPrice(),
								1, new Date(), 120);
					}
				}
				// 商品出栈
				if (t.getBill_type() == 11 || t.getBill_type() == 20 || t.getBill_type() == 40) { // 销售，进货退货
					for (int i = 0; i < details.getNum(); i++) {
						this.jStocksStackService.pop(t.getC_id(), details.getStore_id(), details.getGoods_id(),
								t.getBill_sn());
						// 这是销售方向得，就是出仓
						switch (t.getBill_type()) {
						// 出仓
						case 11:
							jxcFifoStocksService.stock_out(details.getStore_id(), details.getGoods_id(),
									details.getPrice(), 1, new Date(), 620);
							break;
						case 20:
							jxcFifoStocksService.stock_out(details.getStore_id(), details.getGoods_id(),
									details.getPrice(), 1, new Date(), 610);
							break;
						case 40:
							jxcFifoStocksService.stock_out(details.getStore_id(), details.getGoods_id(),
									details.getPrice(), 1, new Date(), 540);
							break;
						}
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
		
		//保存客户端销售中的消费者信息
		if(null!=t.getBill_type()&&20==t.getBill_type()){
			JBasePartner jBasePartner = t.getjBasePartner();
			con.setConsumer_name(jBasePartner.getLink_name());
			con.setConsumer_phone(jBasePartner.getLink_mobile());
			con.setConsumer_addr(jBasePartner.getConsignee_street());
			con.setConsumer_p_index(jBasePartner.getConsignee_p_index());
			con.setData_source(1);
			if(null!=jBasePartner.getMap().get("add_user_name")){
				con.setReport_name(jBasePartner.getMap().get("add_user_name").toString());
			}
			if(null!=jBasePartner.getMap().get("add_user_id")){
				con.setReport_id(Long.parseLong(jBasePartner.getMap().get("add_user_id").toString()));
			}
			con.setSale_date(t.getOpr_date());
			con.setReport_date(new Date());
			con.setCust_id(t.getC_id());
			con.setIs_del(0);
			con.setLink_id(id);
			con.setNum(total_num);
			con.setPrice(total_money);
			this.consumerInfoDao.insertEntity(con);
		}

		return id;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-9
	 */
	public int modifyJBillAndDeatails(JBill t) {
		if(10!=t.getBill_type()&&11!=t.getBill_type()) {
			JBasePartner jBasePartner = t.getjBasePartner();
			if (null != jBasePartner) {
				if (jBasePartner.getPartner_id() == null) {
					Long p_id = this.jBasePartnerDao.insertEntity(jBasePartner);
					t.setPartner_id(p_id);
				} else {
					this.jBasePartnerDao.updateEntity(jBasePartner);

					//更新客户端销售中的消费者信息
					if (20 == t.getBill_type()) {
						ConsumerInfo con = new ConsumerInfo();
						con.setConsumer_name(jBasePartner.getLink_name());
						con.setConsumer_phone(jBasePartner.getLink_mobile());
						con.setConsumer_addr(jBasePartner.getConsignee_street());
						con.setConsumer_p_index(jBasePartner.getConsignee_p_index());
						con.setLink_id(t.getBill_id());
						con.getMap().put("update", true);
						this.consumerInfoDao.updateEntity(con);
					}
				}
			}
		}

		int count = this.jBillDao.updateEntity(t);
		List<JBillDetails> detailsList = t.getjBillDetailsList();
		if (null != detailsList && detailsList.size() > 0) {
			JBillDetails temp = new JBillDetails();
			temp.setBill_id(t.getBill_id());
			this.jBillDetailsDao.deleteEntity(temp);

			for (JBillDetails details : detailsList) {
				details.setBill_id(t.getBill_id());
				this.jBillDetailsDao.insertEntity(details);
			}
		}

		//分销需要网点确认
		if(40==t.getBill_type()||42==t.getBill_type()) {
			//修改后设置网点未确认
			JBill jb = new JBill();
			jb.setBill_id(t.getBill_id());
			jb = this.jBillDao.selectEntity(jb);
			JSubSellRec jsr = new JSubSellRec();
			jsr.setSell_bill_sn(jb.getBill_sn());
			jsr = this.jSubSellRecDao.selectEntity(jsr);
			if (null != jsr) {
				jsr.setStatus(0);
				this.jSubSellRecDao.updateEntity(jsr);
			}
		}

		return count;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-13
	 */
	public int removeJBillAndDetails(JBill t) {

		JBill bill = this.jBillDao.selectEntity(t);
		this.jStocksStackService.delete(bill.getC_id(), bill.getBill_sn());

		int count = this.jBillDao.deleteEntity(t);

		JBillDetails temp = new JBillDetails();
		temp.setBill_id(t.getBill_id());

		this.jBillDetailsDao.deleteEntity(temp);

		return count;

	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-6-17
	 */
	public Long createJBillSubSell(JBill t) {

		// 创建订单以及明细
		Long id = createJBillAndDeatails(t);

		// 创建分销信息
		JBasePartner partner = new JBasePartner();
		partner.setPartner_id(t.getPartner_id());
		partner.setIs_del(0);

		partner = this.jBasePartnerDao.selectEntity(partner);

		if (null != partner && null != partner.getPartner_c_id()) {
			JSubSellRec subSell = new JSubSellRec();
			subSell.setSell_bill_sn(t.getBill_sn());
			subSell.setSell_partner_id(t.getC_id());
			subSell.setBuy_partner_id(partner.getPartner_c_id());
			subSell.setStatus(0);
			subSell.setAdd_date(new Date());

			this.jSubSellRecDao.insertEntity(subSell);
		}

		return id;
	}

	@Override
	/**
	 * @author Wang,Kunlin
	 */
	public Long getJBillCountNameLike(JBill t) {

		return this.jBillDao.getJBillCountNameLike(t);
	}

	@Override
	public Long getFXJbillCount(JBill t) {
		return this.jBillDao.selectFXJbillCount(t);
	}

	@Override
	public List<JBill> getFXJbillList(JBill t) {
		return this.jBillDao.selectFXJbillList(t);
	}

	@Override
	public List<HashMap> getJBillForTH(JBill t) {
		return this.jBillDao.selectJBillForTH(t);
	}

	@Override
	public Long getJBillForTHCount(JBill t) {
		return this.jBillDao.selectJBillForTHCount(t);
	}

	
	@Override
	public Long getSaleDataForkhCount(JBill t) {
		return this.jBillDao.getSaleDataForkhCount(t);
	}

	@Override
	public List<HashMap> getSaleDataForkhList(JBill t) {
		return this.jBillDao.getSaleDataForkhList(t);
	}

	@Override
	public HashMap getInfoForSales(JBill t) {
		return this.jBillDao.selectInfoForSales(t);
	}

	@Override
	public List<HashMap> getSaleInfoByDate(HashMap m) {
		return this.jBillDao.selectSaleInfoByDate(m);
	}

	@Override
	public List<HashMap> getSaleForMonthList(HashMap m) {
		return this.jBillDao.selectSaleForMonthList(m);
	}

	@Override
	public HashMap getBuyInfo(HashMap m) {
		return this.jBillDao.selectBuyInfo(m);
	}

	@Override
	public String getMonthMoney(HashMap m) {
		return this.jBillDao.selectMonthMoney(m);
	}

	@Override
	public List<HashMap> getMonthInDataList(HashMap m) {
		return this.jBillDao.selectMonthInDataList(m);
	}
	public Long getOtherSaleDataCount(JBill t) {
		return this.jBillDao.selectOtherSaleDataCount(t);
	}

	public List<HashMap> getOtherSaleDataList(JBill m) {
		return this.jBillDao.selectOtherSaleDataList(m);
	}
}
