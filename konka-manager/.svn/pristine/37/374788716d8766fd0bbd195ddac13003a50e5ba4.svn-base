package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBaseCardNoDao;
import com.ebiz.mmt.domain.EcBaseCardNo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-11-15 10:43:30
 */
@Service
public class EcBaseCardNoDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcBaseCardNo> implements EcBaseCardNoDao {

	/**
	 * @author tudp
	 * @version 2013-10-15
	 */
	public String insertEcBaseCardNo(List<EcBaseCardNo> list) throws DataAccessException {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			EcBaseCardNo t = list.get(i);
			EcBaseCardNo cardno = new EcBaseCardNo();
			cardno.setCard_no(t.getCard_no());
			Long c = this.selectEntityCount(cardno);
			if (c > 0) {
				msg += (i + 1) + "," + cardno.getCard_no() + " 系统已经存在;\\n";
			} else {
				this.getSqlMapClientTemplate().insert("insertEcBaseCardNo", t);
			}
			if (!"".equals(msg)) {
				try {
					throw new Exception(msg);
				} catch (Exception e) {
				}
			}
		}
		return msg;
	}

	public String insertEcBaseCardNo(EcBaseCardNo en) {
		this.getSqlMapClientTemplate().insert("insertEcBaseCardNo", en);
		return null;
	}

	/**
	 * @author tudp
	 * @version 2014-01-16
	 */
	public int updateEcBaseCardNoBYCardNo(EcBaseCardNo entity) throws DataAccessException {
		return this.getSqlMapClientTemplate().update("updateEcBaseCardNoBYCardNo", entity);
	}
}
