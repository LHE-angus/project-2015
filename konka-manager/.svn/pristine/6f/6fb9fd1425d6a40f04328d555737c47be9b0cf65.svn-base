package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBillDao;
import com.ebiz.mmt.dao.JStocksVerifyDao;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.service.JStocksStackService;
import com.ebiz.mmt.service.KonkaCustomerPublicService;

@Service
public class KonkaCustomerPublicServiceImpl implements KonkaCustomerPublicService {

	@Resource
	private JStocksVerifyDao jStocksVerifyDao;

	@Resource
	private JStocksStackService jStocksStackService;

	public Long createStockVerify(Date oprDate, String storeId, String goodsId, String stocks, String verStocks,
			Long custId, String memo, String[] pyBuyPrices, String[] stackIds) {

		JStocksVerify jTemp = new JStocksVerify();
		jTemp.setStore_id(Long.valueOf(storeId));
		jTemp.setGoods_id(Long.valueOf(goodsId));
		jTemp.getRow().setCount(1);
		jTemp = this.jStocksVerifyDao.selectJStocksVerifyForLastedVerify(jTemp);
		if (null != jTemp) {
			Date lastOprDate = jTemp.getOpr_date();
			// //System.out.println("______________________________上一次盘点日期 = " +
			// lastOprDate);
			// //System.out.println("______________________________本次盘点日期 = " +
			// oprDate);
			if (lastOprDate.getTime() > oprDate.getTime()) {
				// super.renderJavaScript(response,
				// "alert('抱歉，您选择的商品盘点日期小于最近一次盘点日期！');history.back();");
				return -1L;
			}
		}

		Integer trade_type = 0;
		String type = "";
		String bill_sn = "";
		// 生成单据编号 采购:CG, 采购退货:CGTH, 销售:XS, 销售退货:XSTH, 盘亏:PK, 盘盈:PY, 库实相符:KSXF
		if (Integer.valueOf(verStocks) > Integer.valueOf(stocks)) { // 盘盈：入栈
			trade_type = 31;
			type = "PY";
		} else if (Integer.valueOf(verStocks) < Integer.valueOf(stocks)) { // 盘亏
			trade_type = 30;
			type = "PK";
		} else {
			trade_type = 0;
			type = "KSXF";
		}
		if (StringUtils.isNotBlank(type)) {
			bill_sn = getJBillSn(type);
		}
		JStocksVerify entity = new JStocksVerify();
		entity.setBill_sn(bill_sn);
		entity.setTrade_type(trade_type);
		entity.setGoods_id(Long.valueOf(goodsId));
		entity.setStore_id(Long.valueOf(storeId));
		entity.setStocks(Long.valueOf(stocks));
		entity.setVer_stocks(Long.valueOf(verStocks));
		entity.setMemo(memo);
		entity.setOpr_date(oprDate);
		entity.setAdd_date(new Date());
		entity.setC_id(Long.valueOf(custId));
		this.jStocksVerifyDao.insertEntity(entity);

		if (trade_type == 31) { // 盘盈
			for (int i = 0; i < pyBuyPrices.length; i++) {
				jStocksStackService.push(Long.valueOf(custId), Long.valueOf(storeId), Long.valueOf(goodsId),
						new BigDecimal(pyBuyPrices[i]), bill_sn);
				// CG入栈
			}
		} else if (trade_type == 30) {// 盘亏
			for (int i = 0; i < stackIds.length; i++) {
				jStocksStackService.pop(Long.valueOf(custId), Long.valueOf(storeId), Long.valueOf(goodsId), bill_sn);// 出栈
			}

		}

		return 0L;
	}

	/**
	 * 添加了期初金额字段和当前期初金额字段、盘点类型字段 2014-12-01<br/>
	 * 盘点类型 verify_type 1:单个盘点 2：月初结转 3:月中盘点 4：月末全部盘点
	 * 
	 * @param opr_date
	 * @param store_id
	 * @param goods_id
	 * @param stocks
	 * @param ver_stocks
	 * @param custId
	 * @param memo
	 * @param pyBuyPrices
	 * @param stackIds
	 * @return
	 */
	public Long createStockVerify(Date oprDate, String storeId, String goodsId, String stocks, String money,
			String verStocks, String ver_money, Long custId, String memo, int verify_type, String[] pyBuyPrices,
			String[] stackIds) {

		JStocksVerify jTemp = new JStocksVerify();
		jTemp.setStore_id(Long.valueOf(storeId));
		jTemp.setGoods_id(Long.valueOf(goodsId));
		jTemp.getRow().setCount(1);
		jTemp = this.jStocksVerifyDao.selectJStocksVerifyForLastedVerify(jTemp);
		if (null != jTemp) {
			Date lastOprDate = jTemp.getOpr_date();
			// //System.out.println("______________________________上一次盘点日期 = " +
			// lastOprDate);
			// //System.out.println("______________________________本次盘点日期 = " +
			// oprDate);
			if (lastOprDate.getTime() > oprDate.getTime()) {
				// super.renderJavaScript(response,
				// "alert('抱歉，您选择的商品盘点日期小于最近一次盘点日期！');history.back();");
				return -1L;
			}
		}

		Integer trade_type = 0;
		String type = "";
		String bill_sn = "";
		// 生成单据编号 采购:CG, 采购退货:CGTH, 销售:XS, 销售退货:XSTH, 盘亏:PK, 盘盈:PY, 库实相符:KSXF
		if (Integer.valueOf(verStocks) > Integer.valueOf(stocks)) { // 盘盈：入栈
			trade_type = 31;
			type = "PY";
		} else if (Integer.valueOf(verStocks) < Integer.valueOf(stocks)) { // 盘亏
			trade_type = 30;
			type = "PK";
		} else {
			trade_type = 0;
			type = "KSXF";
		}
		if (StringUtils.isNotBlank(type)) {
			bill_sn = getJBillSn(type);
		}
		JStocksVerify entity = new JStocksVerify();
		entity.setBill_sn(bill_sn);
		entity.setTrade_type(trade_type);
		entity.setGoods_id(Long.valueOf(goodsId));
		entity.setStore_id(Long.valueOf(storeId));
		entity.setStocks(Long.valueOf(stocks));
		entity.setMoney(new BigDecimal(money));
		entity.setVer_stocks(Long.valueOf(verStocks));
		entity.setVer_money(new BigDecimal(ver_money));
		entity.setMemo(memo);
		entity.setType(verify_type);
		entity.setOpr_date(oprDate);
		entity.setAdd_date(new Date());
		entity.setC_id(Long.valueOf(custId));
		this.jStocksVerifyDao.insertEntity(entity);

		if (trade_type == 31) { // 盘盈
			for (int i = 0; i < pyBuyPrices.length; i++) {
				jStocksStackService.push(Long.valueOf(custId), Long.valueOf(storeId), Long.valueOf(goodsId),
						new BigDecimal(pyBuyPrices[i]), bill_sn);
				// CG入栈
			}
		} else if (trade_type == 30) {// 盘亏
			for (int i = 0; i < stackIds.length; i++) {
				jStocksStackService.pop(Long.valueOf(custId), Long.valueOf(storeId), Long.valueOf(goodsId), bill_sn);// 出栈
			}

		}

		return 0L;
	}

	@Resource
	private JBillDao jBillDao;

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-09
	 * @desc 生成单据编号 采购:CG, 采购退货:CGTH, 销售:XS, 销售退货:XSTH, 盘亏:PK, 盘盈:PY, 库实相符:KSXF
	 */
	protected String getJBillSn(String type) {
		Date today = new Date();
		String bill_sn = type + "-" + DateFormatUtils.format(today, "yyyyMMdd") + "-";
		// Long seq_jbill_id =
		// super.getFacade().getJBillService().getSeqJBillId();
		Long seq_jbill_id = this.jBillDao.selectSeqJBillId();
		bill_sn = bill_sn + StringUtils.leftPad(seq_jbill_id.toString(), 8, "0");

		return bill_sn;
	}

}
