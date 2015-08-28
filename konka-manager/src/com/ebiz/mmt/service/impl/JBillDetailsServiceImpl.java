package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.JBillDetailsDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.JBillDetails;
import com.ebiz.mmt.service.JBillDetailsService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBillDetailsServiceImpl implements JBillDetailsService {

	@Resource
	private JBillDetailsDao jBillDetailsDao;
	
	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	public Long createJBillDetails(JBillDetails t) {
		return this.jBillDetailsDao.insertEntity(t);
	}

	public JBillDetails getJBillDetails(JBillDetails t) {
		return this.jBillDetailsDao.selectEntity(t);
	}

	public Long getJBillDetailsCount(JBillDetails t) {
		return this.jBillDetailsDao.selectEntityCount(t);
	}

	public List<JBillDetails> getJBillDetailsList(JBillDetails t) {
		return this.jBillDetailsDao.selectEntityList(t);
	}

	public int modifyJBillDetails(JBillDetails t) {
		return this.jBillDetailsDao.updateEntity(t);
	}

	public int removeJBillDetails(JBillDetails t) {
		return this.jBillDetailsDao.deleteEntity(t);
	}

	public List<JBillDetails> getJBillDetailsPaginatedList(JBillDetails t) {
		return this.jBillDetailsDao.selectEntityPaginatedList(t);
	}

	/**
	 * 
	 * @author Wu,ShangLong
	 * @version 2013-6-13
	 */
	public List<JBillDetails> getJBillDetailsAndGoodsList(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsAndGoodsList(t);
	}

	/**
	 * 
	 * @author TUDP
	 * @version 2013-11-04
	 */
	public Long getJBillDetailsForCustomerCount(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForCustomerCount(t);
	}

	/**
	 * 
	 * @author TUDP
	 * @version 2013-11-04
	 */
	@SuppressWarnings("rawtypes")
	public List<HashMap> getJBillDetailsForCustomerPaginatedList(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForCustomerPaginatedList(t);
	}

	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-03-08
	 */
	public Long getJBillDetailsForJSubSellRecCount(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForJSubSellRecCount(t);
	}

	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-03-08
	 */
	public List<JBillDetails> getJBillDetailsForJSubSellRecPaginatedList(
			JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForJSubSellRecPaginatedList(t);
	}

	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-03-08
	 */
	public List<JBillDetails> getJBillDetailsForJSubSellRecForExcelList(
			JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForJSubSellRecForExcelList(t);
	}
	
	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-05-12
	 */
	public Long getJBillDetailsForDetailsCount(JBillDetails t){
		return this.jBillDetailsDao.selectJBillDetailsForDetailsCount(t);
	}

	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-05-12
	 */
	public List<JBillDetails> getJBillDetailsForDetailsPaginatedList(JBillDetails t){
		return this.jBillDetailsDao.selectJBillDetailsForDetailsPaginatedList(t);
	}
	
	/**
	 * 
	 * @author XIAOGJ
	 * @version 2014-05-12
	 */
	public List<JBillDetails> getJBillDetailsForDetailsList(JBillDetails t){
		return this.jBillDetailsDao.selectJBillDetailsForDetailsList(t);
	}

	@Override
	public Long getJBillDetailsForConfirmCount(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForConfirmCount(t);
	}

	@Override
	public List<HashMap> getJBillDetailsForConfirmList(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForConfirmList(t);
	}

	@Override
	public List<JBillDetails> getJBillDetailsForClient(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForClient(t);
	}

	@Override
	public Long getJBillDetailsForSalesCount(JBillDetails t) {
		return this.jBillDetailsDao.selectJBillDetailsForSalesCount(t);
	}

	@Override
	public List<HashMap> getJBillDetailsForSalesList(JBillDetails t) {
		List<HashMap> result = this.jBillDetailsDao.selectJBillDetailsForSalesList(t);
		if(null != result){
			for(HashMap temp : result){
				//处理收货人所在城市
				if ( null != temp.get("CONSIGNEE_P_INDEX") && String.valueOf(temp.get("CONSIGNEE_P_INDEX")).length() >= 6) {
					// 省/直辖市/自治区
					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
					baseProvinceFour.setP_index(new Long(String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 2) + "0000"));
					baseProvinceFour.setDel_mark(0);
					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						temp.put("_PROVINCE",b.getP_name());
					}
					if(!(String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 2) + "0000").equals(String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 4) + "00")){
						// 市
						baseProvinceFour.setP_index(new Long(String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 4) + "00"));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							temp.put("_CITY",b.getP_name());
						}
					}
					if(!String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 6).equals(String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 4) + "00")){
						// 县
						baseProvinceFour.setP_index(new Long(String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 6)));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							temp.put("_COUNTRY",b.getP_name());
						}
					}
					if(!String.valueOf(temp.get("CONSIGNEE_P_INDEX")).substring(0, 6).equals(String.valueOf(temp.get("CONSIGNEE_P_INDEX")))){
						// 乡镇
						baseProvinceFour.setP_index(new Long(String.valueOf(temp.get("CONSIGNEE_P_INDEX"))));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							temp.put("_TOWN",b.getP_name());
						}
					}
				}
			}
		}
		
		return result;
	}
}
