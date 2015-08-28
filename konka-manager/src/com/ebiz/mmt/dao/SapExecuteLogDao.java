package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.SapExecuteLog;
import com.ebiz.ssi.dao.EntityDao;


public interface SapExecuteLogDao extends EntityDao<SapExecuteLog> {

	List<HashMap> selectSapExecuteLogReport();
}
