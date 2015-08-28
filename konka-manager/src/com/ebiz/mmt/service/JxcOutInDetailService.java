package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcOutInDetail;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-02 09:52:43
 */
public interface JxcOutInDetailService {

	Long createJxcOutInDetail(JxcOutInDetail t);

	int modifyJxcOutInDetail(JxcOutInDetail t);

	int removeJxcOutInDetail(JxcOutInDetail t);

	JxcOutInDetail getJxcOutInDetail(JxcOutInDetail t);

	List<JxcOutInDetail> getJxcOutInDetailList(JxcOutInDetail t);

	Long getJxcOutInDetailCount(JxcOutInDetail t);

	List<JxcOutInDetail> getJxcOutInDetailPaginatedList(JxcOutInDetail t);

	Long createJxcOutInDetailList(List<JxcOutInDetail> list);

}