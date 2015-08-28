package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcPdDao;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.service.JxcPdService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcPdServiceImpl implements JxcPdService {

	@Resource
	private JxcPdDao jxcPdDao;

	public Long createJxcPd(JxcPd t) {
		Long id = this.jxcPdDao.insertEntity(t);
		JxcPd jxcPd = new JxcPd();
		if (null == t.getOut_sys_id()) {
			jxcPd.setId(id);
			jxcPd.setOut_sys_id(id * -1);
			this.jxcPdDao.updateEntity(jxcPd);
		}
		return id;
	}

	public JxcPd getJxcPd(JxcPd t) {
		return this.jxcPdDao.selectEntity(t);
	}

	public Long getJxcPdCount(JxcPd t) {
		return this.jxcPdDao.selectEntityCount(t);
	}

	public List<JxcPd> getJxcPdList(JxcPd t) {
		return this.jxcPdDao.selectEntityList(t);
	}

	public int modifyJxcPd(JxcPd t) {

		int rows = this.jxcPdDao.updateEntity(t);
		JxcPd jxcPd = new JxcPd();
		Long id = t.getId();
		if (null != id) {
			if (null == t.getOut_sys_id()) {
				jxcPd.setId(id);
				jxcPd.setOut_sys_id(id * -1);
				this.jxcPdDao.updateEntity(jxcPd);
			}
		}

		return rows;
	}

	public int removeJxcPd(JxcPd t) {
		return this.jxcPdDao.deleteEntity(t);
	}

	public List<JxcPd> getJxcPdPaginatedList(JxcPd t) {
		return this.jxcPdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2011-03-03
	 */
	public List<JxcPd> getJxcPdListForJX(JxcPd t) {
		return this.jxcPdDao.selectJxcPdListForJX(t);
	}

	/**
	 * @author Chen,LiLin
	 * @version 2011-03-07
	 */
	public int modifyJxcPdCount(JxcPd t) {
		return this.jxcPdDao.updateJxcPdCount(t);
	}

	/**
	 * @author Ren zhong
	 * @version 2011-03-07
	 */
	public JxcPd getQcjcForStock(JxcPd t) {
		return this.jxcPdDao.selectQcjcForStock(t);
	}

	public JxcPd getRcjhForStock(JxcPd t) {
		return this.jxcPdDao.selectRcjhForStock(t);
	}

	public JxcPd getRcchForStock(JxcPd t) {
		return this.jxcPdDao.selectRcchForStock(t);
	}

	public List<JxcPd> getSskcDetailsForStork(JxcPd t) {
		return this.jxcPdDao.selectSskcDetailsForStork(t);
	}

	public List<JxcPd> getSsxsDetailsForStork(JxcPd t) {
		return this.jxcPdDao.selectSsxsDetailsForStork(t);
	}

	public List<JxcPd> getStockDetailsList(JxcPd t) {
		return this.jxcPdDao.selectStockDetailsList(t);
	}

	public List<JxcPd> getStockRcjhAndRcchList(JxcPd t) {
		return this.jxcPdDao.selectStockRcjhAndRcchList(t);
	}

	public Long getStockRcjhAndRcchCount(JxcPd t) {
		return this.jxcPdDao.selectStockRcjhAndRcchCount(t);
	}

	public List<JxcPd> getJxcShopUsedStatisticsResultForList(JxcPd t) {
		return this.jxcPdDao.selectJxcShopUsedStatisticsResultForList(t);
	}

	/**
	 * @author Ren zhong
	 * @version 2011-08-12
	 */
	public Long getJxcPdDetailsByProvinceCount(JxcPd t) {
		return this.jxcPdDao.selectJxcPdDetailsByProvinceCount(t);
	}

	public List<JxcPd> getJxcPdDetailsByProvincePaginatedList(JxcPd t) {
		return this.jxcPdDao.selectJxcPdDetailsByProvincePaginatedList(t);
	}

	/**
	 * @author Ren zhong
	 * @version 2011-08-29
	 */
	public List<JxcPd> getJxcShopSalesPdTypeStatisticsResultForList(JxcPd t) {
		return this.jxcPdDao.selectJxcShopSalesPdTypeStatisticsResultForList(t);
	}

	/**
	 * @author Ren zhong
	 * @version 2011-09-01
	 */
	public List<JxcPd> getJxcShopSalesNotJDXXDetailsResultForList(JxcPd t) {
		return this.jxcPdDao.selectJxcShopSalesNotJDXXDetailsResultForList(t);
	}

	@Override
	public List<JxcPd> selectStockDetailsListForPc(JxcPd t) {
		return this.jxcPdDao.selectStockDetailsListForPc(t);
	}

	@Override
	public List<JxcPd> getShopSellStatisticsList(JxcPd t) {
		return this.jxcPdDao.selectShopSellStatisticsList(t);
	}

	@Override
	public JxcPd getQcjcForShopSellStatistics(JxcPd t) {
		return this.jxcPdDao.selectQcjcForShopSellStatistics(t);
	}

}
