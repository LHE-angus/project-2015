package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcSellBillDetailsDao;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.service.JxcSellBillDetailsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcSellBillDetailsServiceImpl implements JxcSellBillDetailsService {

	@Resource
	private JxcSellBillDetailsDao jxcSellBillDetailsDao;

	public Long createJxcSellBillDetails(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.insertEntity(t);
	}

	public JxcSellBillDetails getJxcSellBillDetails(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.selectEntity(t);
	}

	public Long getJxcSellBillDetailsCount(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.selectEntityCount(t);
	}

	public List<JxcSellBillDetails> getJxcSellBillDetailsList(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.selectEntityList(t);
	}

	public int modifyJxcSellBillDetails(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.updateEntity(t);
	}

	public int removeJxcSellBillDetails(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.deleteEntity(t);
	}

	public List<JxcSellBillDetails> getJxcSellBillDetailsPaginatedList(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.selectEntityPaginatedList(t);
	}

	@Override
	public JxcSellBillDetails getJxcSellBillDetailsStatForReal(Map<String, Object> map) {
		return this.jxcSellBillDetailsDao.selectJxcSellBillDetailsStatForReal(map);
	}

	@Override
	public List<JxcSellBillDetails> getJxcSellBillDetailsStatForPdReal(Map<String, Object> map) {
		return this.jxcSellBillDetailsDao.selectJxcSellBillDetailsStatForPdReal(map);
	}

	@Override
	public List<JxcSellBillDetails> getJxcPdModelAnalysis(Map<String, Object> map) {
		return this.jxcSellBillDetailsDao.selectJxcPdModelAnalysis(map);
	}
	
	/**
	 * @author Ren Zhong
	 */
	public List<JxcSellBillDetails> getJxcPdTypeAnalysisResultForList(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.selectJxcPdTypeAnalysisResultForList(t);
	}

	public List<JxcSellBillDetails> getJxcCusAnalysisResultForList(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.selectJxcCusAnalysisResultForList(t);
	}

	public List<JxcSellBillDetails> getJxcDataAnalysisResultForList(JxcSellBillDetails t) {
		return this.jxcSellBillDetailsDao.selectJxcDataAnalysisResultForList(t);
	}

	@Override
	public List<JxcSellBillDetails> getJxcStoresAnalysis(Map<String, Object> map) {
		return this.jxcSellBillDetailsDao.selectJxcStoresAnalysis(map);
	}

	@Override
	public List<JxcSellBillDetails> getJxcStoresAnalysisAnother(Map<String, Object> map) {
		return this.jxcSellBillDetailsDao.selectJxcStoresAnalysisAnother(map);
	}

}
