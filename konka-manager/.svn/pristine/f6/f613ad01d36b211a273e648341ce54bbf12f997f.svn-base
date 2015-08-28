package com.ebiz.mmt.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.CrmPriceHeader;
import com.ebiz.ssi.dao.EntityDao;


public interface CrmPriceHeaderDao extends EntityDao<CrmPriceHeader> {
	String getNextSeqId();

    public List<CrmPriceHeader> selectEntityListFiterByTime(CrmPriceHeader arg0)
            throws SQLException;


    public List<CrmPriceHeader> selectListOfDeptIdGroupCodePriceTypeAndTime(
            HashMap<String, Object> paramMap);
            
}
