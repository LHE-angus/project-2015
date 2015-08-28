package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptJbTaskDao;
import com.ebiz.mmt.domain.KonkaDeptJbTask;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-17 11:44:15
 */
@Service
public class KonkaDeptJbTaskDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaDeptJbTask> implements KonkaDeptJbTaskDao {

	public String insertKonkaDeptJbTask(List<KonkaDeptJbTask> list) {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			KonkaDeptJbTask t = list.get(i);
			KonkaDeptJbTask cardno = new KonkaDeptJbTask();
			cardno.setYear(t.getYear());
			cardno.setMonth(t.getMonth());
			cardno.setR3_code(t.getR3_code());
			Long c = this.selectEntityCount(cardno);
			if (c > 0) {
				t.getMap().put("updataBatch", true);
				msg = "更新数据成功！";
				this.getSqlMapClientTemplate().update("updateKonkaDeptJbTask", t);
			} else {
				this.getSqlMapClientTemplate().insert("insertKonkaDeptJbTask", t);
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

	@SuppressWarnings("unchecked")
	public List<KonkaDeptJbTask> selectKonkaDeptJbTaskForBackMoneyList(KonkaDeptJbTask t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptJbTaskForBackMoneyList", t);
	}

}
