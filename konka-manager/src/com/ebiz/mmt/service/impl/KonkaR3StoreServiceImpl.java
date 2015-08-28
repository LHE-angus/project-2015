package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.KonkaDeptDao;
import com.ebiz.mmt.dao.KonkaMobileCustVisitGpsDao;
import com.ebiz.mmt.dao.KonkaMobileSpRelationDao;
import com.ebiz.mmt.dao.KonkaR3StoreDao;
import com.ebiz.mmt.dao.KonkaSalesmanInfoDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCustVisitGps;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaSalesmanInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaR3StoreService;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-26 10:40:24
 */
@Service
public class KonkaR3StoreServiceImpl implements KonkaR3StoreService {

	@Resource
	private KonkaR3StoreDao konkaR3StoreDao;

	@Resource
	private PeProdUserDao prodUserDao;

	@Resource
	private KonkaDeptDao konkaDeptDao;

	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	private KonkaMobileSpRelationDao konkaMobileSpRelationDao;

	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	@Resource
	private KonkaSalesmanInfoDao konkaSalesmanInfoDao;

	@Resource
	private KonkaMobileCustVisitGpsDao gpsDao;

	public Long createKonkaR3Store(KonkaR3Store t) {
		if (StringUtils.isNotBlank(t.getYwy_job_id())) {
			PeProdUser p1 = new PeProdUser();
			p1.setJob_id(t.getYwy_job_id());
			p1 = this.prodUserDao.selectEntity(p1);
			if (null != p1 && null != p1.getReal_name()) {
				t.setYwy_name(p1.getReal_name());
			}
		}
		if (StringUtils.isNotBlank(t.getYwzg_job_id())) {
			PeProdUser p2 = new PeProdUser();
			p2.setJob_id(t.getYwzg_job_id());
			p2 = this.prodUserDao.selectEntity(p2);
			if (null != p2 && null != p2.getReal_name()) {
				t.setYwzg_name(p2.getReal_name());
			}
			if (null != p2 && null != p2.getLink_tel()) {
				t.setYwzg_tel(p2.getLink_tel());
			}
		}
		if (StringUtils.isNotBlank(t.getJbjl_job_id())) {
			PeProdUser p3 = new PeProdUser();
			p3.setJob_id(t.getJbjl_job_id());
			p3 = this.prodUserDao.selectEntity(p3);
			if (null != p3 && null != p3.getReal_name()) {
				t.setJbjl_name(p3.getReal_name());
			}
		}
		if (StringUtils.isNotBlank(t.getJb_job_id())) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(Long.valueOf(t.getJb_job_id()));
			konkaDept = this.konkaDeptDao.selectEntity(konkaDept);
			if (null != konkaDept && null != konkaDept.getDept_name()) {
				t.setJb_name(konkaDept.getDept_name());
			}
		}

		Long id = this.konkaR3StoreDao.insertEntity(t);

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_R3_STORE");
				a.setLink_id(id);
				this.attachmentDao.insertEntity(a);
			}
		}

		if (null != t.getGps()) {
			KonkaMobileCustVisitGps gps = t.getGps();
			gps.setLink_id(id);
			this.gpsDao.insertEntity(gps);
		}

		return id;
	}

	public KonkaR3Store getKonkaR3Store(KonkaR3Store t) {
		KonkaR3Store result = this.konkaR3StoreDao.selectEntity(t);
		// 处理所在城市
		if (result.getP_index() != null && String.valueOf(result.getP_index()).length() >= 6) {
			// 省/直辖市/自治区
			BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
			baseProvinceFour.setP_index(new Long(String.valueOf(result.getP_index()).substring(0, 2) + "0000"));
			baseProvinceFour.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao
					.selectEntityList(baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				result.getMap().put("PROVINCE", b.getP_name());
			}
			if (!(String.valueOf(result.getP_index()).substring(0, 2) + "0000").equals(String.valueOf(
					result.getP_index()).substring(0, 4)
					+ "00")) {
				// 市
				baseProvinceFour.setP_index(new Long(String.valueOf(result.getP_index()).substring(0, 4) + "00"));
				baseProvinceFourList = null;
				baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					result.getMap().put("CITY", b.getP_name());
				}
			}
			if (!String.valueOf(result.getP_index()).substring(0, 6).equals(
					String.valueOf(result.getP_index()).substring(0, 4) + "00")) {
				// 县
				baseProvinceFour.setP_index(new Long(String.valueOf(result.getP_index()).substring(0, 6)));
				baseProvinceFourList = null;
				baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					result.getMap().put("COUNTRY", b.getP_name());
				}
			}
			if (!String.valueOf(result.getP_index()).substring(0, 6).equals(String.valueOf(result.getP_index()))) {
				// 乡镇
				baseProvinceFour.setP_index(new Long(String.valueOf(result.getP_index())));
				baseProvinceFourList = null;
				baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					result.getMap().put("TOWN", b.getP_name());
				}
			}
		}
		return result;
	}

	public Long getKonkaR3StoreCount(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectEntityCount(t);
	}

	public List<KonkaR3Store> getKonkaR3StoreList(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectEntityList(t);
	}

	@SuppressWarnings("unchecked")
	public int modifyKonkaR3Store(KonkaR3Store t) {
		if (StringUtils.isNotBlank(t.getYwy_job_id())) {
			PeProdUser p1 = new PeProdUser();
			p1.setJob_id(t.getYwy_job_id());
			p1 = this.prodUserDao.selectEntity(p1);
			t.setYwy_name(p1.getReal_name());
		}
		if (StringUtils.isNotBlank(t.getYwzg_job_id())) {
			PeProdUser p2 = new PeProdUser();
			p2.setJob_id(t.getYwzg_job_id());
			p2 = this.prodUserDao.selectEntity(p2);
			t.setYwzg_name(p2.getReal_name());
			t.setYwzg_tel(p2.getLink_tel());
		}
		if (StringUtils.isNotBlank(t.getJbjl_job_id())) {
			PeProdUser p3 = new PeProdUser();
			p3.setJob_id(t.getJbjl_job_id());
			p3 = this.prodUserDao.selectEntity(p3);
			t.setJbjl_name(p3.getReal_name());
		}
		if (StringUtils.isNotBlank(t.getJb_job_id())) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(Long.valueOf(t.getJb_job_id()));
			konkaDept = this.konkaDeptDao.selectEntity(konkaDept);
			t.setJb_name(konkaDept.getDept_name());
		}

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_R3_STORE");
				a.setLink_id(t.getStore_id());
				this.attachmentDao.insertEntity(a);
			}
		}

		// 先删后增
		if (null != t.getMap().get("old_ids") && !"".equals(t.getMap().get("old_ids"))) {
			List<String> ids = (List<String>) t.getMap().get("old_ids");
			for (String ss : ids) {
				KonkaMobileSpRelation ks = new KonkaMobileSpRelation();
				ks.setSeller_id(Long.valueOf(ss));
				ks.setShop_id(t.getStore_id());
				this.konkaMobileSpRelationDao.deleteEntity(ks);
			}
		}

		if (null != t.getMap().get("ids") && !"".equals(t.getMap().get("ids"))) {
			String ids = (String) t.getMap().get("ids");
			for (String ss : ids.split(",")) {
				KonkaMobileSpRelation ks = new KonkaMobileSpRelation();
				ks.setSeller_id(Long.valueOf(ss));
				ks.setShop_id(t.getStore_id());
				this.konkaMobileSpRelationDao.insertEntity(ks);
			}
		}

		if (null != t.getGps()) {
			KonkaMobileCustVisitGps gps = t.getGps();
			if (null != null && null != t.getStore_id()) {
				gps.setLink_id(t.getStore_id());
				this.gpsDao.updateEntity(gps);
			}
		}
		return this.konkaR3StoreDao.updateEntity(t);
	}

	public int removeKonkaR3Store(KonkaR3Store t) {
		return this.konkaR3StoreDao.deleteEntity(t);
	}

	public List<KonkaR3Store> getKonkaR3StorePaginatedList(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Xing,XiuDong 根据业务员用户ID查询门店
	 */
	public List<KonkaR3Store> getKonkaR3StoreListWithYwyUserId(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreListWithYwyUserId(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-25
	 * @return 根据p_index查询所能获取到的门店数量
	 */
	@Override
	public List<KonkaR3Store> getKonkaR3StoreCountByPIndex(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreCountByPIndex(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-25
	 * @return 根据p_index查询所能获取到的门店零售额
	 */
	@Override
	public List<KonkaR3Store> getKonkaR3StoreListByPIndex(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreListByPIndex(t);
	}

	public Long getKonkaR3StoreForRoleDataCount(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreForRoleDataCount(t);
	}

	public List<KonkaR3Store> getKonkaR3StoreForRoleDataPaginatedList(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreForRoleDataPaginatedList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-26
	 */
	public List<KonkaR3Store> getKonkaR3StoreForStoresList(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreForStoresList(t);
	}

	public Long getKonkaR3StoreAndYwyNameCount(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreAndYwyNameCount(t);
	}
	
	public Long getStoreDataCountForVIP(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectStoreDataCountForVIP(t);
	}

	public List<HashMap> getKonkaR3StoreAndYwyNamePaginatedList(KonkaR3Store t) {

		List<HashMap> result = this.konkaR3StoreDao.selectKonkaR3StoreAndYwyNamePaginatedNew(t);

		if(null==t.getMap().get("is_pc")){
			if (null != result && result.size() > 0) {
				for (HashMap c : result) {
					// 处理所在城市
					if (c.get("P_INDEX") != null && String.valueOf(c.get("P_INDEX")).length() >= 6) {
						// 取的gps信息
						if (null != c.get("STORE_ID")) {
							KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
							gps.setLink_id(Long.valueOf("" + c.get("STORE_ID")));
							gps.setLink_tab("KONKA_R3_STORE");
							gps = this.gpsDao.selectEntity(gps);
							c.put("GPS", gps);
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public List<HashMap> getStoreDataListForVIP(KonkaR3Store t) {
		List<HashMap> result = this.konkaR3StoreDao.selectStoreDataListForVIP(t);
		
		if (null != result && result.size() > 0) {
			for (HashMap c : result) {
				// 处理所在城市
				if (c.get("P_INDEX") != null && String.valueOf(c.get("P_INDEX")).length() >= 6) {
					// 省/直辖市/自治区
					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
					baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX")).substring(0, 2) + "0000"));
					baseProvinceFour.setDel_mark(0);
					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao
							.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						c.put("PROVINCE", b.getP_name());
					}
					if (!(String.valueOf(c.get("P_INDEX")).substring(0, 2) + "0000").equals(String.valueOf(
							c.get("P_INDEX")).substring(0, 4)
							+ "00")) {
						// 市
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX")).substring(0, 4) + "00"));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("CITY", b.getP_name());
						}
					}
					if (!String.valueOf(c.get("P_INDEX")).substring(0, 6).equals(
							String.valueOf(c.get("P_INDEX")).substring(0, 4) + "00")) {
						// 县
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX")).substring(0, 6)));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("COUNTRY", b.getP_name());
						}
					}
					if (!String.valueOf(c.get("P_INDEX")).substring(0, 6).equals(String.valueOf(c.get("P_INDEX")))) {
						// 乡镇
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX"))));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("TOWN", b.getP_name());
						}
					}
				}
				
				// 添加促销员类型列
				String typelist = "";
				if (null != c.get("CXY_ID_ALL")) {
					String[] job_id_arr = c.get("CXY_ID_ALL").toString().split(",");
					for (String type : job_id_arr) {
						KonkaSalesmanInfo ksinfo = new KonkaSalesmanInfo();
						ksinfo.setUser_job_id(type);
						ksinfo = this.konkaSalesmanInfoDao.selectEntity(ksinfo);
						if (null != ksinfo) {
							if (null != ksinfo.getSales_type()) {
								if ("1".equals(ksinfo.getSales_type().toString())) {
									typelist += "兼职,";
								}
								if ("2".equals(ksinfo.getSales_type().toString())) {
									typelist += "全职,";
								}
							} else {
								typelist += ",";
							}
						} else {
							typelist += ",";
						}
					}
				}
				if (typelist.length() > 0) {
					typelist = typelist.substring(0, typelist.length() - 1);
				}
				c.put("CXY_TYPE_NAME", typelist);
			}
		}
		
		return result;
	}

	@Override
	public List<KonkaR3Store> getKonkaR3StoreForCustVisit(KonkaR3Store entity) {
		
		return this.konkaR3StoreDao.selectKonkaR3StoreForCustVisit(entity);
	}

	/**
	 * 查询返回map
	 */
	@Override
	public List<HashMap> getKonkaR3StoreAndYwyNamePaginatedNew(KonkaR3Store t) {
		
		List<HashMap> result = this.konkaR3StoreDao.selectKonkaR3StoreAndYwyNamePaginatedNew(t);

		if (null != result && result.size() > 0) {
			for (HashMap c : result) {
				// 处理所在城市
				if (c.get("P_INDEX") != null && String.valueOf(c.get("P_INDEX")).length() >= 6) {
					// 省/直辖市/自治区
					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
					baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX")).substring(0, 2) + "0000"));
					baseProvinceFour.setDel_mark(0);
					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao
							.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						c.put("PROVINCE", b.getP_name());
					}
					if (!(String.valueOf(c.get("P_INDEX")).substring(0, 2) + "0000").equals(String.valueOf(
							c.get("P_INDEX")).substring(0, 4)
							+ "00")) {
						// 市
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX")).substring(0, 4) + "00"));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("CITY", b.getP_name());
						}
					}
					if (!String.valueOf(c.get("P_INDEX")).substring(0, 6).equals(
							String.valueOf(c.get("P_INDEX")).substring(0, 4) + "00")) {
						// 县
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX")).substring(0, 6)));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("COUNTRY", b.getP_name());
						}
					}
					if (!String.valueOf(c.get("P_INDEX")).substring(0, 6).equals(String.valueOf(c.get("P_INDEX")))) {
						// 乡镇
						baseProvinceFour.setP_index(new Long(String.valueOf(c.get("P_INDEX"))));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.put("TOWN", b.getP_name());
						}
					}
					if (null != c.get("STORE_ID")) {
						// gps定位信息
						KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
						gps.setLink_id(Long.valueOf("" + c.get("STORE_ID")));
						gps.setLink_tab("KONKA_R3_STORE");
						gps = this.gpsDao.selectEntity(gps);

					}

				}
			}
		}
		return result;
	}

	@Override
	public Long createKonkaR3StoreAttachment(KonkaR3Store krs, Long lid) {

		if (krs.getAttachmentList() != null && krs.getAttachmentList().size() > 0) {
			for (Attachment a : krs.getAttachmentList()) {
				a.setLink_tab("KONKA_R3_STORE");
				a.setLink_id(lid);
				this.attachmentDao.insertEntity(a);
			}
		}
		return 0L;
	}

	@Override
	public Long getKonkaR3StoreOnlyByCxyCount(KonkaR3Store entity) {
		
		return this.konkaR3StoreDao.selectKonkaR3StoreOnlyByCxyCount(entity);
	}

	@Override
	public List<HashMap> getKonkaR3StoreOnlyByCxyPaginatedList(KonkaR3Store entity) {
		
		return this.konkaR3StoreDao.selectKonkaR3StoreOnlyByCxyPaginatedList(entity);
	}

	@Override
	public List<KonkaR3Store> getKonkaR3StoreForYwyAndCxyList(KonkaR3Store t) {
		return this.konkaR3StoreDao.selectKonkaR3StoreForYwyAndCxyList(t);
	}

	// 这是给手机端的那个终端的首页的列表
	@Override
	public List<HashMap> getKonkaR3StoreAndJbasePartnerForMainPage(KonkaR3Store entity) {
		
		return this.konkaR3StoreDao.selectKonkaR3StoreAndJbasePartnerForMainPage(entity);
	}

	// 这是给手机端的那个终端的修改管理功能的
	@Override
	public List<HashMap> getKonkaR3StoreAndJbasePartnerForManager(KonkaR3Store entity) {
		
		return this.konkaR3StoreDao.selectKonkaR3StoreAndJbasePartnerForManager(entity);
	}

	@Override
	public Long getKonkaR3StoreSaleCount(KonkaR3Store entity) {
		
		return this.konkaR3StoreDao.selectKonkaR3StoreSaleCount(entity);
	}

	@Override
	public List<KonkaR3Store> getKonkaR3StoreSalePaginatedList(KonkaR3Store entity) {
		
		return this.konkaR3StoreDao.selectKonkaR3StoreSalePaginatedList(entity);
	}
    /**
     * 拍照上传取的当前人可以取到的门店信息（不包含门店）
     */
	@Override
	public List<KonkaR3Store> getKonkaR3StoreForCustVisit1(KonkaR3Store entity) {
		return this.konkaR3StoreDao.selectKonkaR3StoreForCustVisit1(entity);
	}

	/**
	 * 根据客户id查询门店
	 * @author Liang Houen
	 * @since 2015-06-23
	 * @param c_id
	 * @return
	 */
	@Override
	public List<HashMap> getStoreListByCID(Long c_id) {
		return this.konkaR3StoreDao.selectStoreListByCID(c_id);
	}

}
