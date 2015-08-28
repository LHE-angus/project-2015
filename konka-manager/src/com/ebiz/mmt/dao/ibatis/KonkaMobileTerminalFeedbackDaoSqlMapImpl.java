package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;
import com.ebiz.mmt.dao.KonkaMobileTerminalFeedbackDao;
import com.ebiz.mmt.domain.KonkaMobileTerminalFeedback;

@Repository
public class KonkaMobileTerminalFeedbackDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaMobileTerminalFeedback> implements KonkaMobileTerminalFeedbackDao {
	/**
	 * @author Wang,Yang
	 * @version 2011-11-18
	 */
	public Long selectKonkaMobileTerminalFeedbackWithFbBackCount(KonkaMobileTerminalFeedback t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaMobileTerminalFeedbackWithFbBackCount",
				t);
	}
	
	@SuppressWarnings("unchecked")
	public List<KonkaMobileTerminalFeedback> selectKonkaMobileTerminalFeedbackListWithFbBack(KonkaMobileTerminalFeedback t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaMobileTerminalFeedbackListWithFbBack",
				t);
	}
}

