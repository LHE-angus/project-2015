package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3StoreTaskDao;
import com.ebiz.mmt.domain.KonkaR3StoreTask;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-06 14:47:57
 */
@Service
public class KonkaR3StoreTaskDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3StoreTask> implements KonkaR3StoreTaskDao {

	public String insertKonkaR3StoreTaskList(List<KonkaR3StoreTask> list) {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			KonkaR3StoreTask t = list.get(i);
			KonkaR3StoreTask ks = new KonkaR3StoreTask();
			ks.setYear(t.getYear());
			ks.setMonth(t.getMonth());
			ks.setStore_name(t.getStore_name());
			List<KonkaR3StoreTask> entityList = this.selectEntityList(ks);
			if (null != entityList && entityList.size() > 0) {
				for (KonkaR3StoreTask konkaR3StoreTask : entityList) {
					this.getSqlMapClientTemplate().delete("deleteKonkaR3StoreTask", konkaR3StoreTask);
				}
				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreTask", t);
			} else {
				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreTask", t);
			}
		}

		return msg;
	}

}
