package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PdModelDao;
import com.ebiz.mmt.domain.PdModel;
import com.ebiz.mmt.service.PdModelService;

/**
 * @author Liu,Huan
 */
@Service
public class PdModelServiceImpl implements PdModelService {

	@Resource
	private PdModelDao pdModelDao;

	// @Resource
	// private PdPropertyCustomDao pdPropertyCustomDao;
	//
	// @Resource
	// private PdPictureDao pdPictureDao;
	//
	// @Resource
	// private PdModelAttInstrDao pdModelAttInstrDao;
	//
	// @Resource
	// private PdContentDao pdContentDao;
	//
	// @Resource
	// private PdTagDao pdTagDao;
	//
	// @Resource
	// private PdBaseTagDao pdBaseTagDao;
	//
	// @Resource
	// private ShopPdDao shopPdDao;

	public Long createPdModel(PdModel t) {
		return this.pdModelDao.insertEntity(t);
		// String temp_flag = (String) t.getMap().get("isCreate");
		// if (StringUtils.isNotBlank(temp_flag) && temp_flag.equals("create")) {
		// return this.pdModelDao.insertEntity(t);
		// } else {
		// Long pd_id = this.pdModelDao.insertEntity(t);
		// String[] property_ids = (String[]) t.getMap().get("property_ids");
		// String[] property_values = (String[]) t.getMap().get("property_values");
		// String[] selectedPdTags = (String[]) t.getMap().get("selectedPdTags");
		// if (null != property_ids && property_ids.length > 0) {
		// String user_id = (String) t.getMap().get("user_id");
		// for (int i = 0; i < property_ids.length; i++) {
		// if (StringUtils.isNotBlank(property_ids[i]) && StringUtils.isNotBlank(property_values[i])) {
		// PdPropertyCustom ppc = new PdPropertyCustom();
		// ppc.setPd_id(pd_id);
		// ppc.setDel_mark(0);
		// ppc.setOpr_date(new Date());
		// ppc.setOpr_id(Long.valueOf(user_id));
		// ppc.setProperty_id(Long.valueOf(property_ids[i]));
		// ppc.setProperty_value(property_values[i]);
		// this.pdPropertyCustomDao.insertEntity(ppc);
		// }
		// }
		// }
		// List<PdPicture> pdPictureList = t.getPdPictureList();
		// if (null != pdPictureList && pdPictureList.size() > 0) {
		// for (PdPicture temp : pdPictureList) {
		// temp.setPd_id(pd_id);
		// temp.setDel_mark(new Short("0"));
		// this.pdPictureDao.insertEntity(temp);
		// }
		// }
		//
		// List<PdModelAttInstr> pdModelAttInstrList = t.getPdModelAttInstrList();
		// if (null != pdModelAttInstrList && pdModelAttInstrList.size() > 0) {
		// for (PdModelAttInstr temp : pdModelAttInstrList) {
		// temp.setBrand_id(t.getBrand_id());
		// temp.setPd_id(pd_id);
		// temp.setIs_del(0);
		// this.pdModelAttInstrDao.insertEntity(temp);
		// }
		// }
		//
		// if (null != selectedPdTags && selectedPdTags.length > 0) {
		// for (String tag_id : selectedPdTags) {
		// PdBaseTag pbt = new PdBaseTag();
		// pbt.setId(Long.valueOf(tag_id));
		// pbt = this.pdBaseTagDao.selectEntity(pbt);
		// if (null != pbt) {
		// PdTag pt = new PdTag();
		// pt.setId(pbt.getId());
		// pt.setDel_mark(new Short("0"));
		// pt.setPd_id(Long.valueOf(pd_id));
		// pt.setPd_type(t.getPd_type());
		// pt.setTag_name(pbt.getTag_name());
		// pt.setDel_mark(new Short("0"));
		// this.pdTagDao.insertEntity(pt);
		// }
		// }
		// }
		//
		// if (StringUtils.isNotBlank(t.getContent())) {
		// PdContent pc = new PdContent();
		// pc.setId(pd_id);
		// pc.setContent(t.getContent());
		// this.pdContentDao.insertEntity(pc);
		// }
		//
		// return pd_id;
		// }
	}

	public PdModel getPdModel(PdModel t) {
		return this.pdModelDao.selectEntity(t);
	}

	public Long getPdModelCount(PdModel t) {
		return this.pdModelDao.selectEntityCount(t);
	}

	public List<PdModel> getPdModelList(PdModel t) {
		return this.pdModelDao.selectEntityList(t);
	}

	public int modifyPdModel(PdModel t) {
		int return_num = this.pdModelDao.updateEntity(t);
		// String to_change_pd_property = (String) t.getMap().get("to_change_pd_property");
		// if (null != to_change_pd_property && StringUtils.isNotBlank(to_change_pd_property)) {
		// String[] property_ids = (String[]) t.getMap().get("property_ids");
		// String[] property_values = (String[]) t.getMap().get("property_values");
		// if (null != property_ids && property_ids.length > 0) {
		// String user_id = (String) t.getMap().get("user_id");
		// PdPropertyCustom pdPropertyCustom = new PdPropertyCustom();
		// pdPropertyCustom.setPd_id(t.getPd_id());
		// // pdPropertyCustom.setOpr_date(new Date());
		// // pdPropertyCustom.setOpr_id(Long.valueOf(user_id));
		// this.pdPropertyCustomDao.deleteEntity(pdPropertyCustom);
		// for (int i = 0; i < property_ids.length; i++) {
		// if (StringUtils.isNotBlank(property_ids[i]) && StringUtils.isNotBlank(property_values[i])) {
		// PdPropertyCustom ppc = new PdPropertyCustom();
		// ppc.setPd_id(t.getPd_id());
		// ppc.setDel_mark(0);
		// ppc.setOpr_date(new Date());
		// ppc.setOpr_id(Long.valueOf(user_id));
		// ppc.setProperty_id(Long.valueOf(property_ids[i]));
		// ppc.setProperty_value(property_values[i]);
		// this.pdPropertyCustomDao.insertEntity(ppc);
		// }
		// }
		// }
		// } else {
		// String[] property_ids = (String[]) t.getMap().get("property_ids");
		// String[] property_values = (String[]) t.getMap().get("property_values");
		// String[] selectedPdTags = (String[]) t.getMap().get("selectedPdTags");
		// if (null != property_ids && property_ids.length > 0) {
		// String user_id = (String) t.getMap().get("user_id");
		// PdPropertyCustom pdPropertyCustom = new PdPropertyCustom();
		// pdPropertyCustom.setPd_id(t.getPd_id());
		// // pdPropertyCustom.setOpr_date(new Date());
		// // pdPropertyCustom.setOpr_id(Long.valueOf(user_id));
		// this.pdPropertyCustomDao.deleteEntity(pdPropertyCustom);
		// for (int i = 0; i < property_ids.length; i++) {
		// if (StringUtils.isNotBlank(property_ids[i]) && StringUtils.isNotBlank(property_values[i])) {
		// PdPropertyCustom ppc = new PdPropertyCustom();
		// ppc.setPd_id(t.getPd_id());
		// ppc.setDel_mark(0);
		// ppc.setOpr_date(new Date());
		// ppc.setOpr_id(Long.valueOf(user_id));
		// ppc.setProperty_id(Long.valueOf(property_ids[i]));
		// ppc.setProperty_value(property_values[i]);
		// this.pdPropertyCustomDao.insertEntity(ppc);
		// }
		// }
		// }
		// List<PdPicture> pdPictureList = t.getPdPictureList();
		// if (null != pdPictureList && pdPictureList.size() > 0) {
		// for (PdPicture temp : pdPictureList) {
		// temp.setPd_id(t.getPd_id());
		// temp.setDel_mark(new Short("0"));
		// this.pdPictureDao.insertEntity(temp);
		// }
		// }
		//
		// List<PdModelAttInstr> pdModelAttInstrList = t.getPdModelAttInstrList();
		// if (null != pdModelAttInstrList && pdModelAttInstrList.size() > 0) {
		// for (PdModelAttInstr temp : pdModelAttInstrList) {
		// temp.setBrand_id(t.getBrand_id());
		// temp.setPd_id(t.getPd_id());
		// temp.setIs_del(0);
		// this.pdModelAttInstrDao.insertEntity(temp);
		// }
		// }
		//
		// if (StringUtils.isNotBlank(t.getContent())) {
		// PdContent pc = new PdContent();
		// pc.setId(t.getPd_id());
		// pc.setContent(t.getContent());
		// this.pdContentDao.updateEntity(pc);
		// }
		//
		// // pd_tag
		// PdTag pt = new PdTag();
		// pt.setPd_id(t.getPd_id());
		// this.pdTagDao.deleteEntity(pt);
		// if (null != selectedPdTags && selectedPdTags.length > 0) {
		// for (String tag_id : selectedPdTags) {
		// PdBaseTag pbt = new PdBaseTag();
		// pbt.setId(Long.valueOf(tag_id));
		// pbt = this.pdBaseTagDao.selectEntity(pbt);
		// if (null != pbt) {
		// PdTag pdModelTag = new PdTag();
		// pdModelTag.setId(pbt.getId());
		// pdModelTag.setDel_mark(new Short("0"));
		// pdModelTag.setPd_id(t.getPd_id());
		// pdModelTag.setPd_type(t.getPd_type());
		// pdModelTag.setTag_name(pbt.getTag_name());
		// this.pdTagDao.insertEntity(pdModelTag);
		// }
		// }
		// }
		// }
		//
		// if (null != t.getPd_id() && (null != t.getIs_country() || null != t.getIs_low_energy())) {
		// ShopPd shopPd = new ShopPd();
		// shopPd.setIs_country(t.getIs_country());
		// shopPd.setIs_low_energy(t.getIs_low_energy());
		// shopPd.getMap().put("update_by_pd_id", t.getPd_id());
		// shopPdDao.updateEntity(shopPd);
		// }

		return return_num;
	}

	public int removePdModel(PdModel t) {
		// PdContent pc = new PdContent();
		// pc.setId(t.getPd_id());
		// this.pdContentDao.deleteEntity(pc);
		//
		// PdTag pt = new PdTag();
		// pt.setPd_id(t.getPd_id());
		// this.pdTagDao.deleteEntity(pt);

		return this.pdModelDao.deleteEntity(t);
	}

	public List<PdModel> getPdModelPaginatedList(PdModel t) {
		return this.pdModelDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Chen,Lide
	 */
	public Long createPdModelWithoutSequence(PdModel t) {
		return this.pdModelDao.insertPdModelWithoutSequence(t);
	}

	/**
	 * @author Chen,Lilin
	 */
	public List<PdModel> getPdModelListWithShopPd(PdModel t) {
		return this.pdModelDao.selectPdModelListWithShopPd(t);
	}

	public PdModel getPdModelForShopPdContrast(PdModel t) {
		return this.pdModelDao.selectPdModelForShopPdContrast(t);
	}

	public Long getPdModelCountForShopPdContrast(PdModel t) {
		return this.pdModelDao.selectPdModelCountForShopPdContrast(t);
	}

	public List<PdModel> getPdModelPaginatedListForShopPdContrast(PdModel t) {
		return this.pdModelDao.selectPdModelPaginatedListForShopPdContrast(t);
	}

	public List<PdModel> getPdModelWithPdTypeSignList(PdModel t) {
		return this.pdModelDao.selectPdModelWithPdTypeSignList(t);
	}

	public List<PdModel> getPdModelPaginatedListForSearch(PdModel t) {
		return this.pdModelDao.selectPdModelPaginatedListForSearch(t);
	}

	public Long getPdModelCountForSearch(PdModel t) {
		return this.pdModelDao.selectPdModelCountForSearch(t);
	}

	public List<PdModel> getPdModelListForLoadImgFromJdxx(PdModel t) {
		return this.pdModelDao.selectPdModelListForLoadImgFromJdxx(t);
	}

	/**
	 * @author Wu,ShangLong
	 */
	public Long getBrandCountInPdMode(PdModel t) {
		return this.pdModelDao.selectBrandCountInPdMode(t);
	}

	/**
	 * @author Du,HongGang
	 * @version 2010-11-17
	 */
	public List<PdModel> getPdModelListForPop(PdModel t) throws DataAccessException {
		return this.pdModelDao.selectPdModelListForPop(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2010-11-23
	 */
	public List<PdModel> getPdModelListForHot(PdModel t) throws DataAccessException {
		return this.pdModelDao.selectPdModelListForHot(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	public List<PdModel> getPdModelListForProd(PdModel t) {
		return this.pdModelDao.selectPdModelListForProd(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-03-25
	 */
	public Long getPdModelListForProdCount(PdModel t) {
		return this.pdModelDao.selectPdModelListForProdCount(t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-02-25
	 */
	public Long getPdModelListCountProd(PdModel t) {
		return this.pdModelDao.selectPdModelListCountProd(t);
	}

	public List<PdModel> getPdModelListForJxcPd(PdModel t) {
		return this.pdModelDao.selectPdModelListForJxcPd(t);
	}

	public List<PdModel> getPdModelPaginatedListForJxcPd(PdModel t) {
		return this.pdModelDao.selectPdModelPaginatedListForJxcPd(t);
	}

	public Long getPdModelCountForJxcPd(PdModel t) {
		return this.pdModelDao.selectPdModelCountForJxcPd(t);
	}

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-14
	 */
	public List<PdModel> getPdModelPaginatedListForSearchAppend(PdModel t) {

		return this.pdModelDao.selectPdModelPaginatedListForSearchAppend(t);
	}

	/**
	 * @author Kun,Zheng
	 * @version 2011-06-14
	 */
	public Long getPdModelCountForSearchAppend(PdModel t) {

		return this.pdModelDao.selectPdModelCountForSearchAppend(t);
	}
}
