package com.ebiz.mmt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Wu,Yang
 * @version 2011-10-12 16:24
 */
@SuppressWarnings("rawtypes")
public interface KonkaJxcFhBillDetailsDao extends EntityDao<KonkaJxcFhBillDetails> {

	List<KonkaJxcFhBillDetails> selectKonkaJxcFhBillDetailsWithNamesList(KonkaJxcFhBillDetails konkaJxcFhBillDetails) throws DataAccessException;
	
	List<Map> selectKonkaJxcFhBillDetailsSumPdCountList(KonkaJxcFhBillDetails t);
	
	List<Map> selectKonkaJxcFhBillDetailsSumPdCountListWithSrc(KonkaJxcFhBillDetails t);
}
