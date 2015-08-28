package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.EcGoodsPriceAreaDao;
import com.ebiz.mmt.dao.EcGoodsPriceDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcGoodsPriceArea;
import com.ebiz.mmt.service.EcGoodsPriceService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcGoodsPriceServiceImpl implements EcGoodsPriceService {

	@Resource
	private EcGoodsPriceDao ecGoodsPriceDao;

	@Resource
	private EcGoodsPriceAreaDao ecGoodsPriceAreaDao;

	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	public Long createEcGoodsPrice(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.insertEntity(t);
	}

	public EcGoodsPrice getEcGoodsPrice(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEntity(t);
	}

	public Long getEcGoodsPriceCount(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEntityCount(t);
	}

	public List<EcGoodsPrice> getEcGoodsPriceList(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEntityList(t);
	}

	public int modifyEcGoodsPrice(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.updateEntity(t);
	}

	public int removeEcGoodsPrice(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.deleteEntity(t);
	}

	public List<EcGoodsPrice> getEcGoodsPricePaginatedList(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 */
	public List<EcGoodsPrice> getEcGoodsPriceWithGoodsIdAndPindexList(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceWithGoodsIdAndPindexList(t);
	}

	public Long createEcGoodsPriceWithPindex(EcGoodsPrice t) {
		Long ec_price_id = this.ecGoodsPriceDao.insertEntity(t);
		List<EcGoodsPriceArea> ecGoodsPriceAreaList = t.getEcGoodsPriceAreaList();
		if (null != ecGoodsPriceAreaList && 0 != ecGoodsPriceAreaList.size())
			for (EcGoodsPriceArea ecGoodsPriceArea : ecGoodsPriceAreaList) {
				ecGoodsPriceArea.setPrice_id(ec_price_id);
				this.ecGoodsPriceAreaDao.insertEntity(ecGoodsPriceArea);
			}
		return ec_price_id;
	}

	public Long getEcGoodsPriceForDeptNameAndPdNameCount(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceForDeptNameAndPdNameCount(t);
	}

	public List<EcGoodsPrice> getEcGoodsPriceForDeptNameAndPdNameList(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceForDeptNameAndPdNameList(t);
	}

	public Long getEcGoodsPriceAndAreaCount(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceAndAreaCount(t);
	}

	public Long createEcGoodsPriceAndArea(EcGoodsPrice t, String province, String city, String country) {
		Long ec_id = this.ecGoodsPriceDao.insertEntity(t);
		EcGoodsPriceArea eca = new EcGoodsPriceArea();
		if (StringUtils.isNotBlank(country)) {
			eca.setPindex_id(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country)) {
			eca.setPindex_id(Long.valueOf(city));
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city)) {
			eca.setPindex_id(Long.valueOf(province));
		}
		eca.setPrice_id(ec_id);
		BaseProvinceListFour bp = new BaseProvinceListFour();
		if (StringUtils.isNotBlank(country)) {
			bp.setP_index(Long.valueOf(country));
		} else if (StringUtils.isNotBlank(city) && StringUtils.isBlank(country)) {
			bp.setP_index(Long.valueOf(city));
		} else if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city)) {
			bp.setP_index(Long.valueOf(province));
		}
		bp = this.baseProvinceListFourDao.selectEntity(bp);
		eca.setP_name(bp.getP_name());
		this.ecGoodsPriceAreaDao.insertEntity(eca);

		return ec_id;
	}

	public EcGoodsPrice getEcGoodsPriceForCoustomerPrice(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceForCoustomerPrice(t);
	}

	@Override
	public Long getEcGoodsPriceForDeptNameAndPdNameNewCount(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceForDeptNameAndPdNameNewCount(t);
	}

	@Override
	public List<EcGoodsPrice> getEcGoodsPriceForDeptNameAndPdNameNewList(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceForDeptNameAndPdNameNewList(t);
	}

	@Override
	public List<EcGoodsPrice> getEcGoodsPriceWithGoodsIdAndPindexNewList(EcGoodsPrice t) {
		return this.ecGoodsPriceDao.selectEcGoodsPriceWithGoodsIdAndPindexNewList(t);
	}

}
