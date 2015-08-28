package com.ebiz.mmt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ebiz.mmt.domain.KonkaBaseTypeData;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-14 11:28:21
 */
public interface KonkaBaseTypeDataService {

	Long createKonkaBaseTypeData(KonkaBaseTypeData t);

	int modifyKonkaBaseTypeData(KonkaBaseTypeData t);

	int removeKonkaBaseTypeData(KonkaBaseTypeData t);

	KonkaBaseTypeData getKonkaBaseTypeData(KonkaBaseTypeData t);

	List<KonkaBaseTypeData> getKonkaBaseTypeDataList(KonkaBaseTypeData t);

	Long getKonkaBaseTypeDataCount(KonkaBaseTypeData t);

	List<KonkaBaseTypeData> getKonkaBaseTypeDataPaginatedList(KonkaBaseTypeData t);
	
    Long getKonkaMobileCustVisitLBCount(KonkaBaseTypeData entity);
	
    List<KonkaBaseTypeData> getKonkaMobileCustVisitPaginatedLBList(KonkaBaseTypeData entity);
    
    /**
     * 设置尺寸段选择参数
     * @param request request范围
     * @param requestKey 设置到request范围的 key
     * @param par_type_id  需要查询的 父类型type_id
     * @param cur_size_sec_name  当前传入的尺寸段分类
     */
	void setRequestSizeSec(HttpServletRequest request,String requestKey,String par_type_id,String cur_size_sec_name);
	
	/**
     * 返回知道尺寸段表示代表的具体尺寸段范围
     * @param request request范围
     * @param requestKey 设置到request范围的 key
     * @param par_type_id  需要查询的 父类型type_id
     * @param cur_size_sec_name  当前传入的尺寸段分类
     */
	String getRequestSizeSec(HttpServletRequest request,String requestKey,String par_type_id,String cur_size_sec_name);
	
	 /**
     * 设置尺寸段选择参数
     * @param request request范围
     * @param requestKey 设置到request范围的 key
     * @param par_type_id  需要查询的 父类型type_id
     */
	void setRequestSizeSecList(HttpServletRequest request,String requestKey,String par_type_id);
}