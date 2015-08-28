package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptR3PdDao;
import com.ebiz.mmt.domain.KonkaDeptR3Pd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-01-18 15:28:01
 */
@Service
public class KonkaDeptR3PdDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaDeptR3Pd> implements KonkaDeptR3PdDao {

	@Override
	public String insertKonkaDeptR3Pd(List<KonkaDeptR3Pd> list) {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			KonkaDeptR3Pd t = list.get(i);
			KonkaDeptR3Pd cardno = new KonkaDeptR3Pd();
			cardno.setIs_del(0);
			cardno.setYear(t.getYear());
			cardno.setMonth(t.getMonth());
			cardno.setR3_code(t.getR3_code());
			cardno.setPd_sn(t.getPd_sn());
			Long c = this.selectEntityCount(cardno);
			if (c > 0) {
				t.getMap().put("updataBatch", true);
				msg = "更新数据成功！";
				this.getSqlMapClientTemplate().update("updateKonkaDeptR3Pd", t);
			} else {
				this.getSqlMapClientTemplate().insert("insertKonkaDeptR3Pd", t);
				msg = "插入数据成功！";
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
