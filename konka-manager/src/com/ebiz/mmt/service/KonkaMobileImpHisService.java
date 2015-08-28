package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileImpHis;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
public interface KonkaMobileImpHisService {

	Long createKonkaMobileImpHis(KonkaMobileImpHis t);

	int modifyKonkaMobileImpHis(KonkaMobileImpHis t);

	int removeKonkaMobileImpHis(KonkaMobileImpHis t);

	KonkaMobileImpHis getKonkaMobileImpHis(KonkaMobileImpHis t);

	List<KonkaMobileImpHis> getKonkaMobileImpHisList(KonkaMobileImpHis t);

	Long getKonkaMobileImpHisCount(KonkaMobileImpHis t);

	List<KonkaMobileImpHis> getKonkaMobileImpHisPaginatedList(KonkaMobileImpHis t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	Long modifyKonkaMobileImpDAtaPro(KonkaMobileImpHis t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	List<KonkaMobileImpHis> getKonkaMobileImpHisWithUserNameList(KonkaMobileImpHis t);

}