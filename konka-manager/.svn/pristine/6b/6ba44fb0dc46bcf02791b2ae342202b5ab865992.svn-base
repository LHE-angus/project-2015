package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.PePdModel;
/**
 * 
 * @author Dou,QingRen
 *
 */
public interface RetailMsBaseService {
	
	/**
	 * 根据cls_id 获取型号  
	 * 参数：cls_ids 数组形式
	 */
	public List<PePdModel> getKonkaPePdModelListByClsIds(String[] cls_ids);
	
	/**
	 *获取品类 
	 */
	public List<BasePdClazz> getKonkaBasePdClazzListByClsIds();
	
	/**
	 * 根据产品型号，  获取尺寸
	 * 参数pd_id:产品型号
	 */
	public List<BasePropValItem> getBasePropValItemListByPdId(String pd_id);
	
}
