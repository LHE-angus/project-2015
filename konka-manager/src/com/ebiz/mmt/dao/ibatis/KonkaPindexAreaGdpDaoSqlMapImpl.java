package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPindexAreaGdpDao;
import com.ebiz.mmt.domain.KonkaPindexAreaGdp;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-03 14:59:02
 */
@Service
public class KonkaPindexAreaGdpDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaPindexAreaGdp> implements
        KonkaPindexAreaGdpDao {

	public String insertEcBaseCardNo(List<KonkaPindexAreaGdp> list) {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			KonkaPindexAreaGdp t = list.get(i);
			KonkaPindexAreaGdp cardno = new KonkaPindexAreaGdp();
			cardno.setP_index(t.getP_index());
			Long c = this.selectEntityCount(cardno);
			if (c > 0) {
				// msg += (i + 1) + "," + cardno.getP_index() + " 系统已经存在;\\n";
				this.getSqlMapClientTemplate().update("updateKonkaPindexAreaGdp", t);
			} else {
				this.getSqlMapClientTemplate().insert("insertKonkaPindexAreaGdp", t);
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

}
