package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileCategoryDao;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.service.KonkaMobileCategoryService;

/**
 * @author Xu,ZhengYang
 * @date 2010-11-14 10:33:45
 */

@Service
public class KonkaMobileCategoryServiceImpl implements
		KonkaMobileCategoryService {

	@Resource
	private KonkaMobileCategoryDao konkaMobileCategoryDao;

	public String getKonkaMobileDept(KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.selectKonkaMobileDept(t);
	}

	public Long createKonkaMobileCategory(KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.insertEntity(t);
	}

	public int modifyKonkaMobileCategory(KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.updateEntity(t);
	}

	public int removeKonkaMobileCategory(KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.deleteEntity(t);
	}

	public KonkaMobileCategory getKonkaMobileCategory(KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.selectEntity(t);
	}

	public Long getKonkaMobileCategoryCount(KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.selectEntityCount(t);
	}

	public List<KonkaMobileCategory> getKonkaMobileCategoryList(
			KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.selectEntityList(t);
	}

	public List<KonkaMobileCategory> getKonkaMobileCategoryPaginatedList(
			KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao.selectEntityPaginatedList(t);
	}

	public List<KonkaMobileCategory> getKonkaMobileCategoryListForFiles(
			KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao
				.selectKonkaMobileCategoryListForFiles(t);
	}

	public List<KonkaMobileCategory> getKonkaMobileCategoryListForSelect(
			KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao
				.selectKonkaMobileCategoryListForSelect(t);
	}

	public Long createKonkaMobileCategoryForAddType(KonkaMobileCategory t) {
		return this.konkaMobileCategoryDao
				.insertKonkaMobileCategoryForAddType(t);
	}

	/**
	 * 查找表KONKA_MOBILE_CATEGORY取得当前操作表中使用的类别枚举信息
	 * 
	 * 传值：type为当前操作表中的枚举类型字段的数值
	 */
	public KonkaMobileCategory getCategory(KonkaMobileCategory t, Integer type) {
		t.setIs_type(1);
		t.setIs_del(0);
		t.setC_index(new Long(type));
		return this.konkaMobileCategoryDao.selectEntity(t);
	}

	/**
	 * 查找表KONKA_MOBILE_CATEGORY的所有类别
	 * 
	 * 
	 */
	public List<KonkaMobileCategory> getCategoryForList() {
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setIs_type(0);
		t.setIs_del(0);
		return this.konkaMobileCategoryDao.selectEntityList(t);
	}

	/**
	 * 查找表KONKA_MOBILE_CATEGORY的样机枚举
	 * 
	 * 
	 */
	public List<KonkaMobileCategory> getCategoryList() {
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setIs_type(1);
		t.setIs_del(0);
		t.setC_type(11);
		return this.konkaMobileCategoryDao.selectEntityList(t);
	}

	/**
	 * 查找表KONKA_MOBILE_CATEGORY的终端物料枚举
	 * 
	 * 
	 */
	public List<KonkaMobileCategory> getWuliaoCategoryList() {
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setIs_type(1);
		t.setIs_del(0);
		t.setC_type(4);
		return this.konkaMobileCategoryDao.selectEntityList(t);
	}

	/**
	 * 查找表KONKA_MOBILE_CATEGORY的竞品品牌枚举
	 * 
	 * 
	 */
	public List<KonkaMobileCategory> getJingpinCategoryList() {
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setIs_type(1);
		t.setIs_del(0);
		t.setC_type(14);
		return this.konkaMobileCategoryDao.selectEntityList(t);

	}
}
