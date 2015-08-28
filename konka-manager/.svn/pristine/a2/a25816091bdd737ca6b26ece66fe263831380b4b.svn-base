package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBaseTypeDataDao;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.service.KonkaBaseTypeDataService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-14 11:28:21
 */
@Service
public class KonkaBaseTypeDataServiceImpl implements KonkaBaseTypeDataService {

	@Resource
	private KonkaBaseTypeDataDao konkaBaseTypeDataDao;
	

	public Long createKonkaBaseTypeData(KonkaBaseTypeData t) {
		return this.konkaBaseTypeDataDao.insertEntity(t);
	}

	public KonkaBaseTypeData getKonkaBaseTypeData(KonkaBaseTypeData t) {
		return this.konkaBaseTypeDataDao.selectEntity(t);
	}

	public Long getKonkaBaseTypeDataCount(KonkaBaseTypeData t) {
		return this.konkaBaseTypeDataDao.selectEntityCount(t);
	}

	public List<KonkaBaseTypeData> getKonkaBaseTypeDataList(KonkaBaseTypeData t) {
		return this.konkaBaseTypeDataDao.selectEntityList(t);
	}

	public int modifyKonkaBaseTypeData(KonkaBaseTypeData t) {
		return this.konkaBaseTypeDataDao.updateEntity(t);
	}

	public int removeKonkaBaseTypeData(KonkaBaseTypeData t) {
		return this.konkaBaseTypeDataDao.deleteEntity(t);
	}

	public List<KonkaBaseTypeData> getKonkaBaseTypeDataPaginatedList(KonkaBaseTypeData t) {
		return this.konkaBaseTypeDataDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getKonkaMobileCustVisitLBCount(KonkaBaseTypeData entity) {
		return this.konkaBaseTypeDataDao.selectKonkaMobileCustVisitLBCount(entity);
	}

	@Override
	public List<KonkaBaseTypeData> getKonkaMobileCustVisitPaginatedLBList(
			KonkaBaseTypeData entity) {
		return this.konkaBaseTypeDataDao.selectKonkaMobileCustVisitPaginatedLBList(entity);
	}
	
	
	/**
     * 设置尺寸段选择参数
     * @param request request范围
     * @param requestKey 设置到request范围的 key
     * @param par_type_id  需要查询的 父类型type_id
     * @param cur_size_sec_name  当前传入的尺寸段分类
     */
	@Override
	public void setRequestSizeSec(HttpServletRequest request,
			String requestKey, String parTypeId, String curSizeSecName) {
		KonkaBaseTypeData entity=new KonkaBaseTypeData();
		entity.setPar_type_id(Long.valueOf(parTypeId));
		List<KonkaBaseTypeData> sizeSecList= this.konkaBaseTypeDataDao.selectEntityList(entity);
		for (KonkaBaseTypeData konkaBaseTypeData : sizeSecList) {
			if(curSizeSecName.equals(konkaBaseTypeData.getField1())){
				request.setAttribute(requestKey, konkaBaseTypeData.getType_name());
				break;
			}
		}
	}
	
	/**
     * 返回知道尺寸段表示代表的具体尺寸段范围
     * @param request request范围
     * @param requestKey 设置到request范围的 key
     * @param par_type_id  需要查询的 父类型type_id
     * @param cur_size_sec_name  当前传入的尺寸段分类
     */
	@Override
	public String getRequestSizeSec(HttpServletRequest request,
			String requestKey, String parTypeId, String curSizeSecName) {
		KonkaBaseTypeData entity=new KonkaBaseTypeData();
		entity.setPar_type_id(Long.valueOf(parTypeId));
		String tempString="其他";
		List<KonkaBaseTypeData> sizeSecList= this.konkaBaseTypeDataDao.selectEntityList(entity);
		for (KonkaBaseTypeData konkaBaseTypeData : sizeSecList) {
			if(curSizeSecName.equals(konkaBaseTypeData.getField1())){
				return konkaBaseTypeData.getType_name();
			}
		}
		return tempString;
	}
	
	/**
     * 设置尺寸段集合到request范围
     * @param request request范围
     * @param requestKey 设置到request范围的 key
     * @param par_type_id  需要查询的 父类型type_id
     */
	@Override
	public void setRequestSizeSecList(HttpServletRequest request,
			String requestKey, String parTypeId) {
		KonkaBaseTypeData entity=new KonkaBaseTypeData();
		entity.setPar_type_id(Long.valueOf(parTypeId));
		List<KonkaBaseTypeData> sizeSecList= this.konkaBaseTypeDataDao.selectEntityList(entity);
		request.setAttribute(requestKey, sizeSecList);
	}

	
	
}
