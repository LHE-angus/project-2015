package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ChannelDataImportDao;
import com.ebiz.mmt.dao.KonkaSellDetailsDao;
import com.ebiz.mmt.dao.KonkaSellReportTmpDao;
import com.ebiz.mmt.dao.KonkaStockDetailsDao;
import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.mmt.domain.KonkaSellReportTmp;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.service.KonkaSellReportTmpService;

@Service
public class KonkaSellReportTmpServiceImpl implements KonkaSellReportTmpService {

	@Resource
	private KonkaSellReportTmpDao konkaSellReportTmpDao;

	@Resource
	private ChannelDataImportDao channelDataImportDao;

	@Resource
	private KonkaSellDetailsDao konkaSellDetailsDao;

	@Resource
	private KonkaStockDetailsDao konkaStockDetailsDao;

	@Override
	public Long createKonkaSellReportTmp(KonkaSellReportTmp t) {
		return this.konkaSellReportTmpDao.insertEntity(t);
	}

	@Override
	public KonkaSellReportTmp getKonkaSellReportTmp(KonkaSellReportTmp t) {
		return this.konkaSellReportTmpDao.selectEntity(t);
	}

	@Override
	public Long getKonkaSellReportTmpCount(KonkaSellReportTmp t) {
		return this.konkaSellReportTmpDao.selectEntityCount(t);
	}

	@Override
	public Long reportTMPCount(KonkaSellReportTmp t) {
		if ("1".equals(t.getMap().get("customer_type")))
			return this.konkaSellReportTmpDao.selectKonkaSellReportTmpForRNCount(t);
		else {
			return this.konkaSellReportTmpDao.selectKonkaSellReportTmpForJBCount(t);
		}
	}

	@Override
	public List<KonkaSellReportTmp> getKonkaSellReportTmpList(KonkaSellReportTmp t) {
		return this.konkaSellReportTmpDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaSellReportTmp(KonkaSellReportTmp t) {
		return this.konkaSellReportTmpDao.updateEntity(t);
	}

	@Override
	public int removeKonkaSellReportTmp(KonkaSellReportTmp t) {
		return this.konkaSellReportTmpDao.deleteEntity(t);
	}

	@Override
	public List<KonkaSellReportTmp> getKonkaSellReportTmpPaginatedList(KonkaSellReportTmp t) {
		return this.konkaSellReportTmpDao.selectEntityPaginatedList(t);
	}

	protected List<KonkaStockDetails> stockList(KonkaStockDetails _t) {
		List<KonkaStockDetails> list = new ArrayList<KonkaStockDetails>();
		list = this.konkaStockDetailsDao.selectEntityList(_t);
		return list;
	}

	protected List<ChannelDataImport> importList(ChannelDataImport _t) {
		List<ChannelDataImport> list = new ArrayList<ChannelDataImport>();
		list = this.channelDataImportDao.selectChannelDataImportListToSum(_t);
		return list;
	}

	protected List<KonkaSellDetails> exportList(KonkaSellDetails _t) {
		List<KonkaSellDetails> list = new ArrayList<KonkaSellDetails>();
		list = this.konkaSellDetailsDao.selectKonkaSellDetailsToSum(_t);
		return list;
	}

	// 初始化判断
	public boolean checkFirst(KonkaStockDetails tmp) {
		KonkaSellReportTmp _tmp = new KonkaSellReportTmp();
		_tmp.setShop_code(tmp.getR3_code());
		_tmp.setPd_id(tmp.getPd_id());
		Long iLong = this.konkaSellReportTmpDao.selectEntityCount(_tmp);
		if (iLong > 0) {
			return false;
		} else {
			return true;
		}
	}

	// 初始化
	public void setFirst(KonkaStockDetails tmp) {
		KonkaSellReportTmp _tmp = new KonkaSellReportTmp();
		_tmp.setShop_code(tmp.getR3_code());
		_tmp.setPd_id(tmp.getPd_id());
		Long iLong = this.konkaSellReportTmpDao.selectEntityCount(_tmp);
		if (iLong > 0) {
			this.konkaSellReportTmpDao.deleteEntity(_tmp);
		}
		_tmp.setPd_type_id(11111l);// 品类ID 待补
		_tmp.setPd_type("品类");// 待补
		_tmp.setPd_name("品名");// 待补
	}

	// 合计
	@Override
	public List<HashMap<String, Object>> reportTMPAll(KonkaSellReportTmp t) {
		List<HashMap<String, Object>> fList = new ArrayList<HashMap<String, Object>>();
		if ("1".equals(t.getMap().get("customer_type")))
			fList = this.konkaSellReportTmpDao.selectKonkaSellReportTmpForRNALL(t);
		else
			fList = this.konkaSellReportTmpDao.selectKonkaSellReportTmpForJBALL(t);
		return fList;
	}

	// 日算法集 DaySchedule
	@Override
	public List<HashMap<String, Object>> reportTMP(KonkaSellReportTmp t) {
		List<HashMap<String, Object>> fList = new ArrayList<HashMap<String, Object>>();
		if ("1".equals(t.getMap().get("customer_type")))
			fList = this.konkaSellReportTmpDao.selectKonkaSellReportTmpForRN(t);
		else
			fList = this.konkaSellReportTmpDao.selectKonkaSellReportTmpForJB(t);
		return fList;
	}

	// 日常算法分布 遍历网点&库存品
	@Override
	public boolean calculateDay(String dayDate) throws ParseException {
		List<KonkaSellReportTmp> tmpList = new ArrayList<KonkaSellReportTmp>();
		SimpleDateFormat _ft = new SimpleDateFormat("yyyyMMdd");
		Date DayDate = _ft.parse(dayDate);
		KonkaSellReportTmp t = new KonkaSellReportTmp();
		t.getMap().put("date_like", dayDate);
		this.konkaSellReportTmpDao.deleteEntity(t);
		// 取仓库存品记录项
		KonkaStockDetails _s = new KonkaStockDetails();
		List<KonkaStockDetails> baseList = this.stockList(_s);
		// 取有效出库记录
		KonkaSellDetails _a = new KonkaSellDetails();
		_a.getMap().put("date_like", dayDate);
		List<KonkaSellDetails> _aList = this.exportList(_a);
		// 取有效入库记录
		ChannelDataImport _b = new ChannelDataImport();
		_b.getMap().put("date_like", dayDate);
		List<ChannelDataImport> _bList = this.importList(_b);

		for (KonkaStockDetails _base : baseList) {
			List<KonkaSellDetails> aList = new ArrayList<KonkaSellDetails>();
			List<ChannelDataImport> bList = new ArrayList<ChannelDataImport>();
			if (_aList.size() > 0)
				for (int ai = 0; ai < _aList.size(); ai++) {
					KonkaSellDetails a = _aList.get(ai);
					if (_base.getPd_id().equals(a.getPd_id()) && (_base.getR3_code()).equals(a.getMap().get("cus_sn")))// 过滤匹配记录
					{
						aList.add(a);
						_aList.remove(ai);
						ai--;
					}
				}
			if (_bList.size() > 0) {
				for (int bi = 0; bi < _bList.size(); bi++) {
					ChannelDataImport b = _bList.get(bi);
					if (b.getMap().get("pd_id") != null)
						if (_base.getPd_id().toString().equals(b.getMap().get("pd_id").toString())
								&& _base.getR3_code().equals(b.getColumn_1()))// 过滤匹配记录
						{
							bList.add(b);
							_bList.remove(bi);
							bi--;
						}
				}
			}

			_bList.remove(bList);

			BigDecimal aCount = BigDecimal.valueOf(0);
			BigDecimal bCount = BigDecimal.valueOf(_base.getStock_count());
			BigDecimal bnCount_before = BigDecimal.valueOf(0);
			BigDecimal bPrice = _base.getStock_cost();
			BigDecimal anCount_before = BigDecimal.valueOf(0);
			BigDecimal anCount_today = BigDecimal.valueOf(0);
			BigDecimal an_All = BigDecimal.valueOf(0);
			Integer casei = 0;

			if (aList.size() > 0)
				for (KonkaSellDetails a : aList) {
					aCount = aCount.add(BigDecimal.valueOf(a.getSell_count()));
					if (dayDate.equals(a.getMap().get("datemark"))) {
						anCount_today = anCount_today.add(BigDecimal.valueOf(a.getSell_count()));
					} else {
						anCount_before = anCount_before.add(BigDecimal.valueOf(a.getSell_count()));
					}
				}

			if (anCount_today.compareTo(BigDecimal.valueOf(0)) == 1) {
				if (bCount.compareTo(anCount_before) == 1
						&& bCount.compareTo((anCount_before.add(anCount_today))) != -1)// 从初始库存出尽
					an_All = anCount_today.multiply(bPrice);
				if (bCount.compareTo(anCount_before) == 1
						&& bCount.compareTo((anCount_before.add(anCount_today))) == -1)// 从初始库存出一部分
				{
					an_All = (bCount.subtract(anCount_before)).multiply(bPrice);
					casei = 1;
				}
				if (bCount.compareTo(anCount_before) == -1)// 全部从后期库存出
				{
					casei = 2;
				}
			}

			if (bList.size() > 0)
				for (ChannelDataImport b : bList) {
					bPrice = b.getColumn_13();
					bnCount_before = bCount;
					bCount = bCount.add(BigDecimal.valueOf(Long.parseLong(b.getColumn_12())));

					if (anCount_today.compareTo(BigDecimal.valueOf(0)) == 1) {
						if (casei == 1) {
							if (bCount.compareTo((anCount_before.add(anCount_today))) != 1) {
								an_All = an_All.add((BigDecimal.valueOf(Long.parseLong(b.getColumn_12())))
										.multiply(bPrice));
							} else {
								an_All = an_All.add((anCount_before.add(anCount_today).subtract(bnCount_before))
										.multiply(bPrice));
								casei = 99;
							}
						}
						if (casei == 2) {
							if (bCount.compareTo(anCount_before) == 1
									&& bCount.compareTo(anCount_before.add(anCount_today)) != 1) {
								an_All = an_All.add((bCount.subtract(anCount_before)).multiply(bPrice));
								casei = 1;
							} else if (bCount.compareTo((anCount_before.add(anCount_today))) == 1) {
								an_All = an_All.add(anCount_today.multiply(bPrice));
								casei = 99;
							}
						}
					}
				}

			if (anCount_today.compareTo(BigDecimal.valueOf(0)) == 1) {
				if (aCount.compareTo(bCount) == 1) {
					an_All = an_All.add((aCount.subtract(bCount)).multiply(bPrice));
				}
				if (anCount_before.compareTo(bCount) == 1) {
					an_All = an_All.add(anCount_today.multiply(bPrice));
				}
			}

			KonkaSellReportTmp _tmp = new KonkaSellReportTmp();
			_tmp.setC_num(anCount_today);
			_tmp.setC_all(an_All);
			_tmp.setA_num(BigDecimal.valueOf(0));
			_tmp.setA_all(BigDecimal.valueOf(0));
			if (aCount.compareTo(bCount) == -1) // 出少入多
			{
				BigDecimal _i = bCount.subtract(aCount);
				bk1: for (int i = bList.size(); i > 0; i--) {
					_tmp.setA_num(BigDecimal.valueOf(0));
					ChannelDataImport bb = bList.get(i - 1);
					BigDecimal c12 = (BigDecimal.valueOf(Long.parseLong(bb.getColumn_12())));
					_i = _i.subtract(c12);
					if (_i.compareTo(BigDecimal.valueOf(0l)) == 1) {
						_tmp.setA_num(_tmp.getA_num().add(c12));
						_tmp.setA_all(_tmp.getA_all().add(bb.getColumn_14()));
					} else {
						BigDecimal _j = c12.add(_i);
						_tmp.setA_num(_tmp.getA_num().add(_j));
						_tmp.setA_all(_tmp.getA_all().add(_j.multiply(bb.getColumn_13())));
						break bk1;
					}

				}
				if (_i.compareTo(BigDecimal.valueOf(0l)) == 1) {
					_tmp.setA_num(_tmp.getA_num().add(_i));
					_tmp.setA_all(_tmp.getA_all().add(_i.multiply(_base.getStock_cost())));
				}
				_tmp.setA_price(_tmp.getA_all().divide(_tmp.getA_num(), 2, BigDecimal.ROUND_HALF_DOWN));
			} else if (aCount.compareTo(bCount) == 1) // 出多入少
			{
				BigDecimal _i = aCount.subtract(bCount);
				bk2: for (int i = aList.size(); i > 0; i--) {
					_tmp.setA_num(BigDecimal.valueOf(0));
					KonkaSellDetails bb = aList.get(i - 1);
					BigDecimal c12 = (BigDecimal.valueOf(bb.getSell_count()));
					_i = _i.subtract(c12);
					if (_i.compareTo(BigDecimal.valueOf(0l)) == 1) {
						_tmp.setA_num(_tmp.getA_num().add(c12));
						_tmp.setA_all(_tmp.getA_all().add(c12.multiply(bb.getSell_money())));
						if (_tmp.getA_num().compareTo(BigDecimal.valueOf(0)) != 0)
							_tmp.setA_price(_tmp.getA_all().divide(_tmp.getA_num(), 2, BigDecimal.ROUND_HALF_DOWN));
					} else {
						_i = c12.add(_i);
						_tmp.setA_num(_tmp.getA_num().add(_i));
						_tmp.setA_all(_tmp.getA_all().add(_i.multiply(bb.getSell_money())));
						if (_tmp.getA_num().compareTo(BigDecimal.valueOf(0)) != 0)
							_tmp.setA_price(_tmp.getA_all().divide(_tmp.getA_num(), 2, BigDecimal.ROUND_HALF_DOWN));
						break bk2;
					}
				}
			} else if (aCount.compareTo(bCount) == 0) // 平帐
			{
				_tmp.setA_num(BigDecimal.valueOf(0));
				_tmp.setA_all(BigDecimal.valueOf(0));
				_tmp.setA_price(BigDecimal.valueOf(0));
			}
			_tmp.setShop_code(_base.getR3_code());
			_tmp.setPd_id(_base.getPd_id());
			_tmp.setPd_name(_base.getMap().get("md_name").toString());
			_tmp.setGen_date(DayDate);
			_tmp.setStock_id(_base.getId());
			tmpList.add(_tmp);
			// //System.out.print(_tmp + "\n");
		}
		for (KonkaSellReportTmp _tmp : tmpList) {
			this.konkaSellReportTmpDao.insertEntity(_tmp);
		}
		return true;
	}
	// 日算法集 DaySchedule
}
