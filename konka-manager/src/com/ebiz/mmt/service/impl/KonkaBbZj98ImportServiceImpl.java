package com.ebiz.mmt.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaBbItr2ImportDao;
import com.ebiz.mmt.dao.KonkaBbZj98ImportDao;
import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.mmt.domain.KonkaBbZj98Import;
import com.ebiz.mmt.service.KonkaBbZj98ImportService;


@Service
public class KonkaBbZj98ImportServiceImpl implements KonkaBbZj98ImportService {

	@Resource
	private KonkaBbZj98ImportDao konkaBbZj98ImportDao;
	@Resource
	private KonkaBbItr2ImportDao konkaBbItr2ImportDao;

	@Override
	public Long createKonkaBbZj98Import(KonkaBbZj98Import t) {
		return this.konkaBbZj98ImportDao.insertEntity(t);
	}

	@Override
	public KonkaBbZj98Import getKonkaBbZj98Import(KonkaBbZj98Import t) {
		return this.konkaBbZj98ImportDao.selectEntity(t);
	}

	@Override
	public Long getKonkaBbZj98ImportCount(KonkaBbZj98Import t) {
		return this.konkaBbZj98ImportDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaBbZj98Import> getKonkaBbZj98ImportList(KonkaBbZj98Import t) {
		return this.konkaBbZj98ImportDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaBbZj98Import(KonkaBbZj98Import t) {
		return this.konkaBbZj98ImportDao.updateEntity(t);
	}

	@Override
	public int removeKonkaBbZj98Import(KonkaBbZj98Import t) {
		return this.konkaBbZj98ImportDao.deleteEntity(t);
	}

	@Override
	public List<KonkaBbZj98Import> getKonkaBbZj98ImportPaginatedList(KonkaBbZj98Import t) {
		return this.konkaBbZj98ImportDao.selectEntityPaginatedList(t);
	}

	@Override
	public int createZJ98AndItr2Sync(List<KonkaBbZj98Import> list1, List<KonkaBbItr2Import> list2) throws SQLException {

		// 规则假设今天是20140108
		// 那么查看报表的时候,看到的数据,应该是20140108凌晨某个时间点同步回来的数据
		// KonkaBbZj98Import报表才能算出20140107的计算 ,(里面20140107的数据那个day保存的是7)

		// KonkaBbZj98Import : 今天看此表,数据库存 7号,6号,上个月数据

		// MENGE0 = 截至前天 = 1月1日~~1月6日
		// MENGE = 截至昨天 = 1月1日~~1月7日
		// MENGE4 = 昨天 = MENGE-MENGE0

		// Date date = new Date();
		// Calendar c = Calendar.getInstance();
		// c.setTime(date);
		// c.add(Calendar.DATE, -2);
		//
		// // 先删除<=当月前天的数据
		// KonkaBbZj98Import ki = new KonkaBbZj98Import();
		// ki.setYear(Long.valueOf(c.get(Calendar.YEAR)));
		// ki.setMonth(Long.valueOf(c.get(Calendar.MONTH) + 1));
		// ki.getMap().put("day2", Long.valueOf(c.get(Calendar.DAY_OF_MONTH)));
		// //ki.setDay(Long.valueOf(c.get(Calendar.DATE)));
		// konkaBbZj98ImportDao.deleteEntity(ki);
		// // 如果同步的时间点为月初,刚删除上上一个月的机型存销数据
		// // 由于现在每天都进行同步一上个月的机型存销数据,如果不处理的话,会堆积数据
		// c.setTime(date);
		// c.add(Calendar.MONTH, -1);
		// // if (c.get(Calendar.DAY_OF_MONTH) == 1) {
		// ki.setYear(Long.valueOf(c.get(Calendar.YEAR)));
		// ki.setMonth(Long.valueOf(c.get(Calendar.MONTH) + 1));
		// ki.setDay(null);
		// konkaBbZj98ImportDao.deleteEntity(ki);
		// // }
		//
		// 由于每天都会同步所有数据...所以,直接删除了省事
		KonkaBbZj98Import ki = new KonkaBbZj98Import();
		konkaBbZj98ImportDao.deleteEntity(ki);
		// 由于此表只有一份数据,所以每次先清空,然后再进行新增
		konkaBbItr2ImportDao.removeAllKonkaBbItr2Import();

		for (KonkaBbZj98Import ki0 : list1) {
			konkaBbZj98ImportDao.insertEntity(ki0);
		}

		for (KonkaBbItr2Import ki1 : list2) {
			konkaBbItr2ImportDao.insertEntity(ki1);
		}

		return 0;
	}

}
