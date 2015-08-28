package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.JxcFifoDataCheck;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-14 18:29:54
 */
public interface JxcFifoDataCheckDao extends EntityDao<JxcFifoDataCheck> {

	List<JxcFifoDataCheck> selectJxcFifoDataCheckForStockList(
			JxcFifoDataCheck jxcFifoDataCheck);

	int AutoRunUpdateFifoCheckByChannelDataImport();

	int AutoRunUpdateFifoCheckByZlesDataImport();

}
