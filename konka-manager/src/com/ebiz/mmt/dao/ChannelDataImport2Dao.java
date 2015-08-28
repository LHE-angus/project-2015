package com.ebiz.mmt.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.ChannelDataImport2;
import com.ebiz.ssi.dao.EntityDao;


public interface ChannelDataImport2Dao extends EntityDao<ChannelDataImport2> {


	public HashMap<String, BigDecimal> selectChannelDataImport2AllCountAndAllMoney(ChannelDataImport2 t);

	public void insertChannelDataImport2Batch(List<ChannelDataImport2> list) throws SQLException;

	/**
	 * @author Hu,Hao
	 * @version 2013-11-18
	 */
	List<ChannelDataImport2> selectChannelDataImport2ForFgsTop(ChannelDataImport2 t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-23
	 */
	List<ChannelDataImport2> selectChannelDataImport2ForFgsTop2(ChannelDataImport2 t);

	/**
	 * @author ZHOU
	 * @version 2014-02-21
	 * @param t
	 * @return
	 */
	List<ChannelDataImport2> selectChannelDataImport2ForFgsTop3(ChannelDataImport2 t);

}