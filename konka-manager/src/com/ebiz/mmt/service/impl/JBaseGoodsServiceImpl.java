package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBaseGoodsDao;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.service.JBaseGoodsService;
import com.ebiz.mmt.service.KonkaCustomerPublicService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBaseGoodsServiceImpl implements JBaseGoodsService {

	@Resource
	private JBaseGoodsDao jBaseGoodsDao;

	@Resource
	private KonkaCustomerPublicService konkaCustomerPublicService;

	public Long createJBaseGoods(JBaseGoods t) {
		// if (null == t.getInit_count()) {
		// t.setInit_count(0L);
		// }
		Long goods_Id = this.jBaseGoodsDao.insertEntity(t);
		// 如果负数，自动转换未正数
		// String[] arrys = new
		// String[t.getInit_count().intValue()>0?t.getInit_count().intValue():-t.getInit_count().intValue()];
		// for (int i = 0; i < t.getInit_count().intValue(); i++) {
		// if(t.getBuy_price() != null)
		// arrys[i] = t.getBuy_price().toString();
		// else
		// arrys[i] ="0";
		// }
		// Date op_date = new Date();
		// String opr_date = (String) t.getMap().get("opr_date");
		// if (null != opr_date) {
		// try {
		// op_date = DateUtils.parseDate(opr_date, new String[] {
		// "yyyy-MM-dd HH:mm:ss" });
		// } catch (ParseException e) {
		// }
		// }
		// konkaCustomerPublicService.createStockVerify(op_date,
		// t.getMap().get("store_id").toString(), goods_Id
		// .toString(), "0", t.getInit_count().toString(), t.getC_id(), "",
		// arrys, new String[] {});
		return goods_Id;
	}

	public JBaseGoods getJBaseGoods(JBaseGoods t) {
		return this.jBaseGoodsDao.selectEntity(t);
	}

	public Long getJBaseGoodsCount(JBaseGoods t) {
		return this.jBaseGoodsDao.selectEntityCount(t);
	}

	public List<JBaseGoods> getJBaseGoodsList(JBaseGoods t) {
		return this.jBaseGoodsDao.selectEntityList(t);
	}

	public int modifyJBaseGoods(JBaseGoods t) {
		return this.jBaseGoodsDao.updateEntity(t);
	}

	public int removeJBaseGoods(JBaseGoods t) {
		return this.jBaseGoodsDao.deleteEntity(t);
	}

	public List<JBaseGoods> getJBaseGoodsPaginatedList(JBaseGoods t) {
		return this.jBaseGoodsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-17
	 */
	public Long getJBaseGoodsWithStockCount(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsWithStockCount(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-17
	 */
	public List<JBaseGoods> getJBaseGoodsWithStockPaginatedList(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsWithStockPaginatedList(t);
	}

	/**
	 * @author Hu,hao
	 * @date 2014-03-05
	 */
	public JBaseGoods getJBaseGoodsForNum(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsForNum(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-04-09
	 */
	public JBaseGoods getJBaseGoodsForComeOutNum(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsForComeOutNum(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-07-09
	 */
	public JBaseGoods getJBaseGoodsForNumNew(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsForNumNew(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-07-09
	 */
	public JBaseGoods getJBaseGoodsForComeOutNumNew(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsForComeOutNumNew(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-07-27
	 */
	public List<JBaseGoods> getJBaseGoodsWithOutInitList(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsWithOutInitList(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-08-05
	 */
	public List<JBaseGoods> getJBaseGoodsForComeOutNumWithMoney(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsForComeOutNumWithMoney(t);
	}
	
	/**
	 * @author Chen,Xiaoqi
	 * @date 2015-04-01
	 */
	public List<JBaseGoods> getJBaseGoodsForComeOutNumWithMoney2(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsForComeOutNumWithMoney2(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @date 2014-08-19
	 */
	public List<JBaseGoods> getJBaseGoodsForComeOutNumWithMoneyDetail(JBaseGoods t) {
		return this.jBaseGoodsDao.selectJBaseGoodsForComeOutNumWithMoneyDetail(t);
	}
}
