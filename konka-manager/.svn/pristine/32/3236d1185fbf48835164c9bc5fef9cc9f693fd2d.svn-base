package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcFifoDataCheckDao;
import com.ebiz.mmt.domain.JxcFifoDataCheck;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-14 18:29:54
 */
@Service
public class JxcFifoDataCheckDaoSqlMapImpl extends EntityDaoSqlMapImpl<JxcFifoDataCheck> implements JxcFifoDataCheckDao {

	@Override
	public List<JxcFifoDataCheck> selectJxcFifoDataCheckForStockList(
			JxcFifoDataCheck jxcFifoDataCheck) {
		// TODO Auto-generated method stub
		if(jxcFifoDataCheck.getLink_type()==1){
			return	super.getSqlMapClientTemplate().queryForList("selectJxcFifoDataCheckForChannelList", jxcFifoDataCheck);
		}else{
			return	super.getSqlMapClientTemplate().queryForList("selectJxcFifoDataCheckForZlesList", jxcFifoDataCheck);
		}
	}

	@Override
	public int AutoRunUpdateFifoCheckByChannelDataImport() {
		// TODO Auto-generated method stub
		try {
			this.getSqlMapClientTemplate().update("AutoRunUpdateFifoCheckByChannelDataImport");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int AutoRunUpdateFifoCheckByZlesDataImport() {
		// TODO Auto-generated method stub
		try {
			this.getSqlMapClientTemplate().update("AutoRunUpdateFifoCheckByZlesDataImport");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

}
