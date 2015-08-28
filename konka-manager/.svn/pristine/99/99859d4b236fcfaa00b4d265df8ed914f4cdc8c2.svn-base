package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseGoodsInitStockDao;
import com.ebiz.mmt.domain.JBaseGoodsInitStock;
import com.ebiz.mmt.service.JBaseGoodsInitStockService;
import com.ebiz.mmt.service.KonkaCustomerPublicService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-20 10:28:34
 */
@Service
public class JBaseGoodsInitStockServiceImpl implements JBaseGoodsInitStockService {

	@Resource
	private JBaseGoodsInitStockDao jBaseGoodsInitStockDao;

	@Resource
	private KonkaCustomerPublicService konkaCustomerPublicService;

	public Long createJBaseGoodsInitStock(JBaseGoodsInitStock t) {
		return this.jBaseGoodsInitStockDao.insertEntity(t);
	}

	public Long createJBaseGoodsInitStockAndStock(JBaseGoodsInitStock t) {
		Long id = this.jBaseGoodsInitStockDao.insertEntity(t);
		// 如果负数，自动转换未正数
		String[] arrys = new String[t.getInit_count().intValue() > 0 ? t.getInit_count().intValue() : -t
				.getInit_count().intValue()];
		for (int i = 0; i < t.getInit_count().intValue(); i++) {
			if (t.getBuy_price() != null)
				arrys[i] = t.getBuy_price().toString();
			else
				arrys[i] = "0";
		}
		Date op_date = t.getInit_date();
		String opr_date = (String) t.getMap().get("opr_date");
		if (null != opr_date) {
			try {
				op_date = DateUtils.parseDate(opr_date, new String[] { "yyyy-MM-dd HH:mm:ss" });
			} catch (ParseException e) {
			}
		}

		BigDecimal ver_money = new BigDecimal("0");
		if (null != t.getInit_money()) {// 如果初始化金额不为空，则存入初始化金额
			ver_money = t.getInit_money();
		} else {// 如果初始化金额为空，则算出金额
			if (null != t.getBuy_price() && null != t.getInit_count()) {
				ver_money = t.getBuy_price().multiply(new BigDecimal(t.getInit_count().toString()));
			}
		}
		if( null!=t.getMap().get("is_first_init")){
			konkaCustomerPublicService.createStockVerify(op_date, t.getStore_id().toString(), t.getGoods_id().toString(),
					"0", "0", t.getInit_count().toString(), ver_money.toString(), t.getC_id(), "", 0, arrys,
					new String[] {});
		}else{
			konkaCustomerPublicService.createStockVerify(op_date, t.getStore_id().toString(), t.getGoods_id().toString(),
					"0", "0", t.getInit_count().toString(), ver_money.toString(), t.getC_id(), "", 1, arrys,
					new String[] {});
		}
		
		return id;
	}

	public JBaseGoodsInitStock getJBaseGoodsInitStock(JBaseGoodsInitStock t) {
		return this.jBaseGoodsInitStockDao.selectEntity(t);
	}

	public Long getJBaseGoodsInitStockCount(JBaseGoodsInitStock t) {
		return this.jBaseGoodsInitStockDao.selectEntityCount(t);
	}

	public List<JBaseGoodsInitStock> getJBaseGoodsInitStockList(JBaseGoodsInitStock t) {
		return this.jBaseGoodsInitStockDao.selectEntityList(t);
	}

	public int modifyJBaseGoodsInitStock(JBaseGoodsInitStock t) {
		return this.jBaseGoodsInitStockDao.updateEntity(t);
	}

	public int removeJBaseGoodsInitStock(JBaseGoodsInitStock t) {
		return this.jBaseGoodsInitStockDao.deleteEntity(t);
	}

	public List<JBaseGoodsInitStock> getJBaseGoodsInitStockPaginatedList(JBaseGoodsInitStock t) {
		return this.jBaseGoodsInitStockDao.selectEntityPaginatedList(t);
	}

}
