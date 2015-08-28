package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileImpHisDao;
import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
@Service
public class KonkaMobileImpHisDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileImpHis> implements
		KonkaMobileImpHisDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	public Long updateKonkaMobileImpDAtaPro(KonkaMobileImpHis t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("updateKonkaMobileImpDAtaPro", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-05-23
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaMobileImpHis> selectKonkaMobileImpHisWithUserNameList(KonkaMobileImpHis t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileImpHisWithUserNameList", t);
	}
}
