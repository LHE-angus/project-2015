package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPdModelTaskDao;
import com.ebiz.mmt.domain.KonkaPdModelTask;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class KonkaPdModelTaskDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaPdModelTask> implements KonkaPdModelTaskDao {

	@Override
	public String insertKonkaPdModelTask(List<KonkaPdModelTask> list) {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			KonkaPdModelTask t = list.get(i);
			KonkaPdModelTask cardno = new KonkaPdModelTask();
			cardno.setYear(t.getYear());
			cardno.setMonth(t.getMonth());
			cardno.setMd_name(t.getMd_name());
			Long c = this.selectEntityCount(cardno);
			if (c > 0) {
				msg += (i + 1) + ",型号" + cardno.getMd_name() + "," + cardno.getYear() + "年" + cardno.getMonth() + "月"
						+ " 在系统中已经存在;\\n";
			} else {
				this.getSqlMapClientTemplate().insert("insertKonkaPdModelTask", t);
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

	@Override
	public List<HashMap<String, String>> selectKonkaPdModelXCLY(KonkaPdModelTask t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaPdModelXCLY", t);
	}

	@Override
	public List<HashMap<String, Object>> selectKonkaPdModelXCLYHZ(KonkaPdModelTask t) {

		return super.getSqlMapClientTemplate().queryForList("selectKonkaPdModelXCLYHZ", t);
	}
}
