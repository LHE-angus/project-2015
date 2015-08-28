package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
public interface KonkaMobileImpHisDao extends EntityDao<KonkaMobileImpHis> {
	
	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	Long updateKonkaMobileImpDAtaPro(KonkaMobileImpHis t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	List<KonkaMobileImpHis> selectKonkaMobileImpHisWithUserNameList(KonkaMobileImpHis t);
}
