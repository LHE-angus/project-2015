package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcStockBillDetailsDao;
import com.ebiz.mmt.domain.JxcStockBillDetails;
import com.ebiz.mmt.service.JxcStockBillDetailsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcStockBillDetailsServiceImpl implements JxcStockBillDetailsService {

	@Resource
	private JxcStockBillDetailsDao jxcStockBillDetailsDao;

	public Long createJxcStockBillDetails(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.insertEntity(t);
	}

	public JxcStockBillDetails getJxcStockBillDetails(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.selectEntity(t);
	}

	public Long getJxcStockBillDetailsCount(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.selectEntityCount(t);
	}

	public List<JxcStockBillDetails> getJxcStockBillDetailsList(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.selectEntityList(t);
	}

	public int modifyJxcStockBillDetails(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.updateEntity(t);
	}

	public int removeJxcStockBillDetails(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.deleteEntity(t);
	}

	public List<JxcStockBillDetails> getJxcStockBillDetailsPaginatedList(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.selectEntityPaginatedList(t);
	}

	@Override
	public JxcStockBillDetails getJxcStockBillDetailsStatForReal(Map<String, Object> map) {
		return this.jxcStockBillDetailsDao.selectJxcStockBillDetailStatForReal(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> getJxcstockBillDetailsSumPdCountList(JxcStockBillDetails t) {
		return this.jxcStockBillDetailsDao.selectJxcstockBillDetailsSumPdCountList(t);
	}


	@Override
	public Long getJxcstockBillDetailsSumPdCountNotSrc(JxcStockBillDetails t) {
		
		return this.jxcStockBillDetailsDao.selectJxcstockBillDetailsSumPdCountNotSrc(t);
	}

}
