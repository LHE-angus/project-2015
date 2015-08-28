package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.mmt.domain.SfPindex;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-14 15:05:08
 */
public interface EcBaseCardNoService {

	Long createEcBaseCardNo(EcBaseCardNo t);

	int modifyEcBaseCardNo(EcBaseCardNo t);

	int removeEcBaseCardNo(EcBaseCardNo t);

	EcBaseCardNo getEcBaseCardNo(EcBaseCardNo t);

	List<EcBaseCardNo> getEcBaseCardNoList(EcBaseCardNo t);

	Long getEcBaseCardNoCount(EcBaseCardNo t);

	List<EcBaseCardNo> getEcBaseCardNoPaginatedList(EcBaseCardNo t);

	String createEcBaseCardNo(List<EcBaseCardNo> list);

	int modifyEcBaseCardNoBYCardNo(EcBaseCardNo t);

	String createSfPindex(List<SfPindex> list);

}