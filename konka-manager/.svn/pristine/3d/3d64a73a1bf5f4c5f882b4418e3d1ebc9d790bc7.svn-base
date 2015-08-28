package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.JBasePartnerDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.service.JBasePartnerService;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBasePartnerServiceImpl implements JBasePartnerService {

	@Resource
	private JBasePartnerDao jBasePartnerDao;
	
	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;
	
	@Override
	
	/**
	 * @author Wang,KunLin
	 * @version 2014-03-31
	 */
	public List<JBasePartner> getJBasePartnerListOnlyName(JBasePartner t) {
		return this.jBasePartnerDao.selectEntityListOnlyName(t);
	}

	public Long createJBasePartner(JBasePartner t) {
		return this.jBasePartnerDao.insertEntity(t);
	}

	public JBasePartner getJBasePartner(JBasePartner t) {
		JBasePartner result = this.jBasePartnerDao.selectEntity(t);
		
		if(null!=result){
			//处理所在城市
			if (result.getArea_code() != null && String.valueOf(result.getArea_code()).length() >= 6) {
				// 省/直辖市/自治区
				BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
				baseProvinceFour.setP_index(new Long(String.valueOf(result.getArea_code()).substring(0, 2) + "0000"));
				baseProvinceFour.setDel_mark(0);
				List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					result.getMap().put("PROVINCE",b.getP_name());
				}
				if(!(String.valueOf(result.getArea_code()).substring(0, 2) + "0000").equals(String.valueOf(result.getArea_code()).substring(0, 4) + "00")){
					// 市
					baseProvinceFour.setP_index(new Long(String.valueOf(result.getArea_code()).substring(0, 4) + "00"));
					baseProvinceFourList = null;
					baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						result.getMap().put("CITY",b.getP_name());
					}
				}
				if(!String.valueOf(result.getArea_code()).substring(0, 6).equals(String.valueOf(result.getArea_code()).substring(0, 4) + "00")){
					// 县
					baseProvinceFour.setP_index(new Long(String.valueOf(result.getArea_code()).substring(0, 6)));
					baseProvinceFourList = null;
					baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						result.getMap().put("COUNTRY",b.getP_name());
					}
				}
				if(!String.valueOf(result.getArea_code()).substring(0, 6).equals(String.valueOf(result.getArea_code()))){
					// 乡镇
					baseProvinceFour.setP_index(new Long(String.valueOf(result.getArea_code())));
					baseProvinceFourList = null;
					baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						result.getMap().put("TOWN",b.getP_name());
					}
				}
			}
			
			//处理收货人所在城市
			if (result.getConsignee_p_index() != null && String.valueOf(result.getConsignee_p_index()).length() >= 6) {
				// 省/直辖市/自治区
				BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
				baseProvinceFour.setP_index(new Long(String.valueOf(result.getConsignee_p_index()).substring(0, 2) + "0000"));
				baseProvinceFour.setDel_mark(0);
				List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					result.getMap().put("_PROVINCE",b.getP_name());
				}
				if(!(String.valueOf(result.getConsignee_p_index()).substring(0, 2) + "0000").equals(String.valueOf(result.getConsignee_p_index()).substring(0, 4) + "00")){
					// 市
					baseProvinceFour.setP_index(new Long(String.valueOf(result.getConsignee_p_index()).substring(0, 4) + "00"));
					baseProvinceFourList = null;
					baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						result.getMap().put("_CITY",b.getP_name());
					}
				}
				if(!String.valueOf(result.getConsignee_p_index()).substring(0, 6).equals(String.valueOf(result.getConsignee_p_index()).substring(0, 4) + "00")){
					// 县
					baseProvinceFour.setP_index(new Long(String.valueOf(result.getConsignee_p_index()).substring(0, 6)));
					baseProvinceFourList = null;
					baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						result.getMap().put("_COUNTRY",b.getP_name());
					}
				}
				if(!String.valueOf(result.getConsignee_p_index()).substring(0, 6).equals(String.valueOf(result.getConsignee_p_index()))){
					// 乡镇
					baseProvinceFour.setP_index(new Long(String.valueOf(result.getConsignee_p_index())));
					baseProvinceFourList = null;
					baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						result.getMap().put("_TOWN",b.getP_name());
					}
				}
			}
			
			//2014－12-15 Liang,HouEn 非一级网点时，上级客户为网点名称
			if(null!=result.getP_level() && result.getP_level()>1){
				JBasePartner jbp = new JBasePartner();
				jbp.setPartner_id(result.getPar_c_id());
				jbp = this.jBasePartnerDao.selectEntity(jbp);
				if(null!=jbp){
					result.getMap().put("par_partner_name",jbp.getPartner_name());
				}else{
					result.getMap().put("par_partner_name","<未找到>");
				}
			}
			
			if(null!=result.getMap().get("pass_word")){
				//解析密码
				try {
					DESPlus des = new DESPlus();
					String pass_word = des.decrypt(result.getMap().get("pass_word").toString());
					result.getMap().put("pass_word", pass_word);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public Long getJBasePartnerCount(JBasePartner t) {
		return this.jBasePartnerDao.selectEntityCount(t);
	}

	public List<JBasePartner> getJBasePartnerList(JBasePartner t) {
		return this.jBasePartnerDao.selectEntityList(t);
	}

	public int modifyJBasePartner(JBasePartner t) {
		return this.jBasePartnerDao.updateEntity(t);
	}

	public int removeJBasePartner(JBasePartner t) {
		return this.jBasePartnerDao.deleteEntity(t);
	}

	public List<JBasePartner> getJBasePartnerPaginatedList(JBasePartner t) {
		List<JBasePartner> result = this.jBasePartnerDao.selectEntityPaginatedList(t);
		
		//查询所在地区
		if(result!=null){
			for(JBasePartner entity : result){
				if (entity.getArea_code() != null && String.valueOf(entity.getArea_code()).length() >= 6) {
					// 省/直辖市/自治区
					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
					baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code()).substring(0, 2) + "0000"));
					baseProvinceFour.setDel_mark(0);
					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao
							.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						entity.getMap().put("PROVINCE", b.getP_name());
					}
					if (!(String.valueOf(entity.getArea_code()).substring(0, 2) + "0000").equals(String.valueOf(
							entity.getArea_code()).substring(0, 4)
							+ "00")) {
						// 市
						baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code()).substring(0, 4) + "00"));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							entity.getMap().put("CITY", b.getP_name());
						}
					}
					if (!String.valueOf(entity.getArea_code()).substring(0, 6).equals(
							String.valueOf(entity.getArea_code()).substring(0, 4) + "00")) {
						// 县
						baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code()).substring(0, 6)));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							entity.getMap().put("COUNTRY", b.getP_name());
						}
					}
					if (!String.valueOf(entity.getArea_code()).substring(0, 6).equals(String.valueOf(entity.getArea_code()))) {
						// 乡镇
						baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code())));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							entity.getMap().put("TOWN", b.getP_name());
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * @author Hu,hao
	 * @version 2013-10-14
	 */
	@Override
	public List<JBasePartner> getJBasePartnerForBillList(JBasePartner t) {
		return this.jBasePartnerDao.selectJBasePartnerForBillList(t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	public Long getJBasePartnerForLevelCount(JBasePartner t) {
		return this.jBasePartnerDao.selectJBasePartnerForLevelCount(t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	public List<JBasePartner> getJBasePartnerForLevelPaginatedList(JBasePartner t) {
		List<JBasePartner> result = this.jBasePartnerDao.selectJBasePartnerForLevelPaginatedList(t);
		
		//查询所在地区
		if(result!=null){
			for(JBasePartner entity : result){
				if (entity.getArea_code() != null && String.valueOf(entity.getArea_code()).length() >= 6) {
					// 省/直辖市/自治区
					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
					baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code()).substring(0, 2) + "0000"));
					baseProvinceFour.setDel_mark(0);
					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao
							.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						entity.getMap().put("PROVINCE", b.getP_name());
					}
					if (!(String.valueOf(entity.getArea_code()).substring(0, 2) + "0000").equals(String.valueOf(
							entity.getArea_code()).substring(0, 4)
							+ "00")) {
						// 市
						baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code()).substring(0, 4) + "00"));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							entity.getMap().put("CITY", b.getP_name());
						}
					}
					if (!String.valueOf(entity.getArea_code()).substring(0, 6).equals(
							String.valueOf(entity.getArea_code()).substring(0, 4) + "00")) {
						// 县
						baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code()).substring(0, 6)));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							entity.getMap().put("COUNTRY", b.getP_name());
						}
					}
					if (!String.valueOf(entity.getArea_code()).substring(0, 6).equals(String.valueOf(entity.getArea_code()))) {
						// 乡镇
						baseProvinceFour.setP_index(new Long(String.valueOf(entity.getArea_code())));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							entity.getMap().put("TOWN", b.getP_name());
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	public List<JBasePartner> getJBasePartnerForSonPaginatedList(JBasePartner t) {
		return this.jBasePartnerDao.selectJBasePartnerForSonPaginatedList(t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	public List<JBasePartner> getJBasePartnerForFgsKhPaginatedList(JBasePartner t) {
		return this.jBasePartnerDao.selectJBasePartnerForFgsKhPaginatedList(t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	public Long getJBasePartnerForFgsKhCount(JBasePartner t) {
		return this.jBasePartnerDao.selectJBasePartnerForFgsKhCount(t);
	}

	@Override
	public List<HashMap> getJBasePartnerMapList(JBasePartner t) {
		return this.jBasePartnerDao.selectJBasePartnerMapList(t);
	}

	@Override
	public List<HashMap> getJBasePartnerNewList(JBasePartner t) {
		List<HashMap> result = this.jBasePartnerDao.selectJBasePartnerNewList(t);
		
		// 处理省市县镇
//		if (null != result && result.size() > 0) {
//			for (HashMap c : result) {
//				if (c.get("AREA_CODE") != null && String.valueOf(c.get("AREA_CODE")).length() >= 6) {
//					// 省/直辖市/自治区
//					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
//					baseProvinceFour.setP_index(new Long(String.valueOf(c.get("AREA_CODE")).substring(0, 2) + "0000"));
//					baseProvinceFour.setDel_mark(0);
//					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
//					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
//						BaseProvinceListFour b = baseProvinceFourList.get(0);
//						c.put("PROVINCE", b.getP_name());
//					}
//					if(!(String.valueOf(c.get("AREA_CODE")).substring(0, 2) + "0000").equals(String.valueOf(c.get("AREA_CODE")).substring(0, 4) + "00")){
//						// 市
//						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("AREA_CODE")).substring(0, 4) + "00"));
//						baseProvinceFourList = null;
//						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
//						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
//							BaseProvinceListFour b = baseProvinceFourList.get(0);
//							c.put("CITY", b.getP_name());
//						}
//					}
//					if(!String.valueOf(c.get("AREA_CODE")).substring(0, 6).equals(String.valueOf(c.get("AREA_CODE")).substring(0, 4) + "00")){
//						// 县
//						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("AREA_CODE")).substring(0, 6)));
//						baseProvinceFourList = null;
//						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
//						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
//							BaseProvinceListFour b = baseProvinceFourList.get(0);
//							c.put("COUNTRY", b.getP_name());
//						}
//					}
//					if(!String.valueOf(c.get("AREA_CODE")).substring(0, 6).equals(String.valueOf(c.get("AREA_CODE")))){
//						// 乡镇
//						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("AREA_CODE"))));
//						baseProvinceFourList = null;
//						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
//						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
//							BaseProvinceListFour b = baseProvinceFourList.get(0);
//							c.put("TOWN", b.getP_name());
//						}
//					}
//				}
//			}
//		}
		
		return result;
	}

	@Override
	public Long getJBasePartnerNewListCount(JBasePartner t) {
		return this.jBasePartnerDao.selectJBasePartnerNewListCount(t);
	}

	@Override
	public List<HashMap> getJBasePartnerLevelList() {
		return this.jBasePartnerDao.selectJBasePartnerLevelList();
	}

	@Override
	public HashMap getYWYAndDept(String partner_sn) {
		return this.jBasePartnerDao.getYWYAndDept(partner_sn);
	}

	@Override
	public Long getAgentsByLevel(Long c_id, Long level) {
		JBasePartner jbp = new JBasePartner();
		jbp.setC_id(c_id);
		jbp.setIs_del(0);
		jbp.setP_level(level);
		jbp.setPartner_type(1);
		jbp.setPartner_obj(1);
		return this.jBasePartnerDao.selectEntityCount(jbp);
	}
}
