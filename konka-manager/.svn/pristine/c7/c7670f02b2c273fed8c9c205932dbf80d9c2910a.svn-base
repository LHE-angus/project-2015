package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ChannelDataImport2Dao;
import com.ebiz.mmt.dao.KonkaDeptJsMoneyDao;
import com.ebiz.mmt.domain.ChannelDataImport2;
import com.ebiz.mmt.domain.KonkaDeptJsMoney;
import com.ebiz.mmt.r3.SOXX;
import com.ebiz.mmt.r3.helper.R3Dao;
import com.ebiz.mmt.service.ChannelDataImport2Service;
import com.ebiz.mmt.web.util.ArithUtil;

@Service
public class ChannelDataImport2ServiceImpl implements ChannelDataImport2Service {

	@Resource
	private ChannelDataImport2Dao ChannelDataImport2Dao;
	
	@Resource
	private KonkaDeptJsMoneyDao konkaDeptJsMoneyDao;

	@Resource
	private R3Dao r3Dao;

	@Override
	public Long createChannelDataImport2(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.insertEntity(t);
	}

	@Override
	public ChannelDataImport2 getChannelDataImport2(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectEntity(t);
	}

	@Override
	public Long getChannelDataImport2Count(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectEntityCount(t);
	}

	@Override
	public List<ChannelDataImport2> getChannelDataImport2List(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectEntityList(t);
	}

	@Override
	public int modifyChannelDataImport2(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.updateEntity(t);
	}

	@Override
	public int removeChannelDataImport2(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.deleteEntity(t);
	}

	@Override
	public List<ChannelDataImport2> getChannelDataImport2PaginatedList(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectEntityPaginatedList(t);
	}

	@Override
	public HashMap<String, BigDecimal> getChannelDataImport2AllCountAndAllMoney(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectChannelDataImport2AllCountAndAllMoney(t);
	}

	@Override
	public void createChannelDataImport2List(List<ChannelDataImport2> t) {
		if (0 != t.size()) {
			for (ChannelDataImport2 i : t) {
				this.ChannelDataImport2Dao.insertEntity(i);
			}
		}
	}

	@Override
	public void modifyChannelDataImport2List(List<ChannelDataImport2> t) {
		if (0 != t.size()) {
			for (ChannelDataImport2 i : t) {
				this.ChannelDataImport2Dao.updateEntity(i);
			}
		}
	}

	// 准备弃用
	@Override
	public HashMap<String, Long> createOrModifySyncChannelDataForzbMx(Set<String> syncVkorgList, String v_vtweg,
			String v_spart, String v_audat_begin, String v_audat_end, Set<String> ctmList) throws SQLException {
		Map<String, Long> map = new HashMap<String, Long>();
		Long isize = 0l;
		Long msize = 0l;

		// 一般syncVkorgList.size()=1 ,ctmList =300 这种大客户和一般的客户在数量上有大差别
		for (String korg : syncVkorgList) {
			for (String ctm : ctmList) {
				List<SOXX> DataList = new ArrayList<SOXX>();
				List<ChannelDataImport2> insertList = new ArrayList<ChannelDataImport2>();

				// 1.从接口取数据回来
				DataList = r3Dao.getSoxxMx(korg, v_vtweg, v_spart, v_audat_begin, v_audat_end, ctm);

				String startDate = null;
				String endDate = null;
				// 2.删除相关数据
				if (DataList.size() > 0) {
					startDate = v_audat_begin + " 00:00:00";
					endDate = v_audat_end + " 23:59:59";
					ChannelDataImport2 cdi = new ChannelDataImport2();
					// cdi.getMap().put("count1", true);
					cdi.setColumn_25(korg);
					cdi.setColumn_1(ctm);
					// yyyy-MM-dd hh24:mi:ss R3订单时间
					cdi.getMap().put("startDate", startDate);
					// yyyy-MM-dd hh24:mi:ss R3订单时间
					cdi.getMap().put("endDate", endDate);
					// 不作更新,会把相应的数据先删除再作插入
					ChannelDataImport2Dao.deleteEntity(cdi);
				}

				Date import_date = new Date();
				// 3.封装插入数据列表
				for (SOXX s : DataList) {
					ChannelDataImport2 im = new ChannelDataImport2();
					im.setImport_date(import_date);
					im.setImport_uid(1l); // 系统管理员的id
					im.setColumn_1(s.getKUNNR());// 售达方
					// im.setColumn_4();// 名称(售)
					// im.setColumn_2(column_1);// 分
					// im.setColumn_3(column_1);// 地域类别
					im.setColumn_5(s.getWEKUNNR());// 送达方
					// im.setColumn_6();// column_6送达方 名
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if (!(s.getERDAT().equals(""))) {
							im.setColumn_7(sdf2.parse(s.getERDAT()));
						} else {
							im.setColumn_7(null);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					} // 创建日期 订单在r/3创建的时间

					try {
						if (!(s.getAUDAT().equals(""))) {
							im.setColumn_26(sdf2.parse(s.getAUDAT()));
						} else {
							im.setColumn_26(null);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					} // 订单r/3凭证日期

					try {
						if (!(s.getBSTDK().equals(""))) {
							im.setColumn_31(sdf2.parse(s.getBSTDK()));
						} else {
							im.setColumn_31(null);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					} // 订单r/3采购日期

					im.setColumn_8(s.getVBELN());// 销售单号
					im.setColumn_11(s.getMATNR());// 机型
					im.setColumn_12(s.getKWMENG());// 每一订单行机型数量
					// 含税单价
					im.setColumn_13(new BigDecimal(Double.valueOf(s.getCMPRE0())));
					// 总金额（含税）
					im.setColumn_14(new BigDecimal(Double.valueOf(s.getKZWI6())));

					im.setColumn_9(s.getAUART());// 订单类型 zfor zfgc zfre..
					im.setColumn_10(s.getPOSNR());// column_10 项目号
					// K007（含税）
					im.setColumn_15(new BigDecimal(ArithUtil.sub(Double.valueOf(s.getKZWI1()),
							Double.valueOf(s.getKZWI6()))));

					// im.setColumn_16("");//RB00(含税)

					im.setColumn_17(new BigDecimal(s.getKZWI1()));// 总净值金额(含税)

					// 交货日期
					try {
						if (!(s.getWADAT_IST().equals(""))) {
							im.setColumn_18(sdf2.parse(s.getWADAT_IST()));
						} else {
							im.setColumn_18(null);
						}

					} catch (ParseException e) {
						e.printStackTrace();
					}
					im.setColumn_19(s.getVBELN_L());// KF交货单
					im.setColumn_20(s.getVBELN_LES());// 物流交货单
					im.setColumn_21(s.getLGORT_L());// KF发货仓位
					im.setColumn_22(s.getBSTNK());// 采购订单编号


					im.setColumn_23(s.getMATKL());// 物料组

					im.setColumn_24(s.getVKBUR());// 办事处
					im.setColumn_25(s.getVKORG());// 销售组织

					// 交货单数量
					im.setColumn_27(new BigDecimal(Double.valueOf(s.getJWMENG())));
					// 已发货数量
					im.setColumn_28(new BigDecimal(Double.valueOf(s.getMWMENG())));
					// 已开发票数量
					im.setColumn_29(new BigDecimal(Double.valueOf(s.getRWMENG())));

					// 实际交货数量
					im.setColumn_30(new BigDecimal(Double.valueOf(s.getLFIMG_L())));
					insertList.add(im);

				}
				// 4.批量提交待插入数据
				// throws exception
				if (insertList.size() > 0) {
					// this.ChannelDataImport2Dao.insertChannelDataImport2Batch(insertList);
				}

				// 累加
				isize += DataList.size();
				// msize += msize;
				insertList = null;
				DataList = null;
			}
		}

		map.put("isize", isize);
		map.put("msize", msize);
		return (HashMap<String, Long>) map;
	}

	// 准备弃用
	@Override
	public HashMap<String, Long> createOrModifySyncChannelDataForfgsMx(Set<String> syncVkorgList, String v_vtweg,
			String v_spart, String v_audat_begin, String v_audat_end, String v_kunnr) throws SQLException {
		Map<String, Long> map = new HashMap<String, Long>();
		Long isize = 0l;
		Long msize = 0l;
		v_kunnr = null;
		// 一个销售组织提交一次
		for (String korg : syncVkorgList) {
			List<SOXX> DataList = new ArrayList<SOXX>();
			List<ChannelDataImport2> insertList = new ArrayList<ChannelDataImport2>();

			// 1.从接口取数据回来
			DataList = r3Dao.getSoxxMx(korg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);

			String startDate = null;
			String endDate = null;
			// 2.删除相关数据
			if (DataList.size() > 0) {
				startDate = v_audat_begin + " 00:00:00";
				endDate = v_audat_end + " 23:59:59";
				ChannelDataImport2 cdi = new ChannelDataImport2();
				cdi.setColumn_25(korg);
				cdi.setColumn_1(v_kunnr);
				// yyyy-MM-dd hh24:mi:ss R3订单时间
				cdi.getMap().put("startDate", startDate);
				// yyyy-MM-dd hh24:mi:ss R3订单时间
				cdi.getMap().put("endDate", endDate);
				// 不作更新,会把相应的数据先删除再作插入
				ChannelDataImport2Dao.deleteEntity(cdi);
			}
			Date import_date = new Date();
			// 3.封装插入数据列表
			for (SOXX s : DataList) {
				ChannelDataImport2 im = new ChannelDataImport2();
				im.setImport_date(import_date);
				im.setImport_uid(1l); // 系统管理员的id
				im.setColumn_1(s.getKUNNR());// 售达方
				// im.setColumn_4();// 名称(售)
				// im.setColumn_2(column_1);// 分
				// im.setColumn_3(column_1);// 地域类别
				im.setColumn_5(s.getWEKUNNR());// 送达方
				// im.setColumn_6();// column_6送达方 名
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				try {
					if (!(s.getERDAT().equals(""))) {
						im.setColumn_7(sdf2.parse(s.getERDAT()));
					} else {
						im.setColumn_7(null);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} // 创建日期 订单在r/3创建的时间

				try {
					if (!(s.getAUDAT().equals(""))) {
						im.setColumn_26(sdf2.parse(s.getAUDAT()));
					} else {
						im.setColumn_26(null);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} // 订单r/3凭证日期

				im.setColumn_8(s.getVBELN());// 销售单号
				im.setColumn_11(s.getMATNR());// 机型
				im.setColumn_12(s.getKWMENG());// 每一订单行机型数量
				// 含税单价
				im.setColumn_13(new BigDecimal(Double.valueOf(s.getCMPRE0())));
				// 总金额（含税）
				im.setColumn_14(new BigDecimal(Double.valueOf(s.getKZWI6())));

				im.setColumn_9(s.getAUART());// 订单类型 zfor zfgc zfre..
				im.setColumn_10(s.getPOSNR());// column_10 项目号
				// K007（含税）
				im.setColumn_15(new BigDecimal(
						ArithUtil.sub(Double.valueOf(s.getKZWI1()), Double.valueOf(s.getKZWI6()))));

				// im.setColumn_16(column_1);//RB00(含税)

				im.setColumn_17(new BigDecimal(s.getKZWI1()));// 总净值金额(含税)

				// 交货日期
				try {
					if (!(s.getWADAT_IST().equals(""))) {
						im.setColumn_18(sdf2.parse(s.getWADAT_IST()));
					} else {
						im.setColumn_18(null);
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}
				im.setColumn_19(s.getVBELN_L());// KF交货单
				im.setColumn_20(s.getVBELN_LES());// 物流交货单
				im.setColumn_21(s.getLGORT_L());// KF发货仓位
				im.setColumn_22(s.getBSTNK());// 采购订单编号
				// 客户采购订单日期 暂时不用

				im.setColumn_23(s.getKDMAT());// 客户物料号
				im.setColumn_24(s.getVKBUR());// 办事处
				im.setColumn_25(s.getVKORG());// 销售组织

				// 交货单数量
				im.setColumn_27(new BigDecimal(Double.valueOf(s.getJWMENG())));
				// 已发货数量
				im.setColumn_28(new BigDecimal(Double.valueOf(s.getMWMENG())));
				// 已开发票数量
				im.setColumn_29(new BigDecimal(Double.valueOf(s.getRWMENG())));

				// 实际交货数量
				im.setColumn_30(new BigDecimal(Double.valueOf(s.getLFIMG_L())));
				insertList.add(im);

			}
			// 4.批量提交待插入数据
			// throws exception
			if (insertList.size() > 0) {
				// this.ChannelDataImport2Dao.insertChannelDataImport2Batch(insertList);
			}

			// 累加
			isize += DataList.size();
			// msize += msize;
			insertList = null;
			DataList = null;
		}
		map.put("isize", isize);
		map.put("msize", msize);
		return (HashMap<String, Long>) map;
	}
	

	@Override
	public HashMap<String, Long> createOrModifySyncChannelDataForzbTj(Set<String> syncVkorgList, String v_vtweg,
			String v_spart, String v_audat_begin, String v_audat_end, Set<String> ctmList) throws SQLException{
		
		//TODO  使用第一个接口CALL_Get_SOXX 同步总部与分公司,大客户的订单 数据(不包括KF交货单,物流单的数据)
		
		Map<String, Long> map = new HashMap<String, Long>();
		Long isize = 0l;
		Long msize = 0l;

		// 一般syncVkorgList.size()=1 ,ctmList =300 这种大客户和一般的客户在数量上有大差别
		for (String korg : syncVkorgList) {
			for (String ctm : ctmList) {
				List<SOXX> DataList = new ArrayList<SOXX>();
				List<ChannelDataImport2> insertList = new ArrayList<ChannelDataImport2>();

				// 1.从接口取数据回来
				DataList = r3Dao.getSoxxTj(korg, v_vtweg, v_spart, v_audat_begin, v_audat_end, ctm);

				String startDate = null;
				String endDate = null;
				// 2.删除相关数据(删除数据也要按)
				if (DataList.size() > 0) {
					startDate = v_audat_begin + " 00:00:00";
					endDate = v_audat_end + " 23:59:59";
					ChannelDataImport2 cdi = new ChannelDataImport2();
					// cdi.getMap().put("count1", true);
					cdi.setColumn_25(korg);
					cdi.setColumn_1(ctm);
					// yyyy-MM-dd hh24:mi:ss R3订单时间
					cdi.getMap().put("startDate", startDate);
					// yyyy-MM-dd hh24:mi:ss R3订单时间
					cdi.getMap().put("endDate", endDate);
					// 不作更新,会把相应的数据先删除再作插入
					ChannelDataImport2Dao.deleteEntity(cdi);
				}
				Date import_date = new Date();
				// 3.封装插入数据列表
				for (SOXX s : DataList) {
					ChannelDataImport2 im = new ChannelDataImport2();
					im.setImport_date(import_date);
					im.setImport_uid(1l); // 系统管理员的id
					im.setColumn_1(s.getKUNNR());// 售达方
					im.setColumn_4("");// 名称(售)
					// im.setColumn_2(column_1);// 分
					// im.setColumn_3(column_1);// 地域类别
					im.setColumn_5(s.getWEKUNNR());// 送达方
					im.setColumn_6("");// column_6送达方 名
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if (!(s.getERDAT().equals(""))) {
							im.setColumn_7(sdf2.parse(s.getERDAT()));
						} else {
							im.setColumn_7(null);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					} // 创建日期 订单在r/3创建的时间

					try {
						if (!(s.getAUDAT().equals(""))) {
							im.setColumn_26(sdf2.parse(s.getAUDAT()));
						} else {
							im.setColumn_26(null);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					} // 订单r/3凭证日期

					try {
						if (!(s.getBSTDK().equals(""))) {
							im.setColumn_31(sdf2.parse(s.getBSTDK()));
						} else {
							im.setColumn_31(null);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					} // 采购订单日期

					im.setColumn_8(s.getVBELN());// 销售单号
					im.setColumn_11(s.getMATNR());// 机型
					im.setColumn_12(s.getKWMENG());// 每一订单行机型数量
					// 含税单价
					im.setColumn_13(new BigDecimal(Double.valueOf(s.getCMPRE0())));
					// 总金额（含税）
					im.setColumn_14(new BigDecimal(Double.valueOf(s.getKZWI6())));

					im.setColumn_9(s.getAUART());// 订单类型 zfor zfgc zfre..
					im.setColumn_10(s.getPOSNR());// column_10 项目号
					// K007（含税）
					im.setColumn_15(new BigDecimal(ArithUtil.sub(Double.valueOf(s.getKZWI1()),
							Double.valueOf(s.getKZWI6()))));

					// im.setColumn_16("");//RB00(含税)

					im.setColumn_17(new BigDecimal(s.getKZWI1()));// 总净值金额(含税)

					// 交货日期
					// try {
					// if (!(s.getWADAT_IST().equals(""))) {
					// im.setColumn_18(sdf2.parse(s.getWADAT_IST()));
					// } else {
					// im.setColumn_18(null);
					// }
					//
					// } catch (ParseException e) {
					// e.printStackTrace();
					// }
					// im.setColumn_19(s.getVBELN_L());// KF交货单
					// im.setColumn_20(s.getVBELN_LES());// 物流交货单
					// im.setColumn_21(s.getLGORT_L());// KF发货仓位

					im.setColumn_22(s.getBSTNK());// 采购订单编号

					im.setColumn_23(s.getMATKL());// 物料组

					im.setColumn_24(s.getVKBUR());// 办事处
					im.setColumn_25(s.getVKORG());// 销售组织

					// 交货单数量
					im.setColumn_27(new BigDecimal(Double.valueOf(s.getJWMENG())));
					// 已发货数量
					im.setColumn_28(new BigDecimal(Double.valueOf(s.getMWMENG())));
					// 已开发票数量
					im.setColumn_29(new BigDecimal(Double.valueOf(s.getRWMENG())));

					insertList.add(im);

				}
				// 4.批量提交待插入数据
				// throws exception
				if (insertList.size() > 0) {
					this.ChannelDataImport2Dao.insertChannelDataImport2Batch(insertList);
					
					//生成总部订单之前 ，统计截止到当日结算数据合计 start
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
					SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
					Date day = new Date();

					KonkaDeptJsMoney kdjMoney = new KonkaDeptJsMoney();
					kdjMoney.getMap().put("this_day_s", format1.format(day) + "-01 00:00:00");
					kdjMoney.getMap().put("this_day_e", format2.format(day) + " 23:59:59");

					
					//删除当日已有数据
					KonkaDeptJsMoney km = new KonkaDeptJsMoney();
					km.getMap().put("d_day_s", format2.format(day) + " 00:00:00");
					km.getMap().put("d_day_e", format2.format(day) + " 23:59:59");
					this.konkaDeptJsMoneyDao.deleteEntity(km);
					
					List<KonkaDeptJsMoney> kdjList = this.konkaDeptJsMoneyDao.selectKonkaDeptJsMoneyToR3List(kdjMoney);

					if (kdjList.size() > 0) {
						KonkaDeptJsMoney k =  new KonkaDeptJsMoney();
						k.getMap().put("d_day_s", format2.format(day) + " 00:00:00");
						k.getMap().put("d_day_e", format2.format(day) + " 23:59:59");
						
						for (KonkaDeptJsMoney temp : kdjList) {
							if (null != temp.getMap().get("dept_name_"))
								temp.setDept_name(temp.getMap().get("dept_name_").toString());
							if (null != temp.getMap().get("dept_id_"))
								temp.setDept_id(Long.valueOf(temp.getMap().get("dept_id_").toString()));
							if (null != temp.getMap().get("dept_sn_"))
								temp.setDept_sn(temp.getMap().get("dept_sn_").toString());
							if (null != temp.getMap().get("js_money_"))
								temp.setJs_money(new BigDecimal(temp.getMap().get("js_money_").toString()));
							temp.setType(20);
							temp.setJs_date(day);
							temp.setAdd_date(day);
						this.konkaDeptJsMoneyDao.insertEntity(temp);
						}
					}
					//生成总部订单之前 ，统计截止到当日结算数据合计
				}

				// 累加
				isize += DataList.size();
				// msize += msize;
				insertList = null;
				DataList = null;
			}
		}

		map.put("isize", isize);
		map.put("msize", msize);
		return (HashMap<String, Long>) map;
	};
	

	/**
	 * @author Hu,Hao
	 * @version 2013-11-18
	 */
	@Override
	public List<ChannelDataImport2> getChannelDataImport2ForFgsTop(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectChannelDataImport2ForFgsTop(t);
	}
	
	/**
	 * @author Hu,Hao
	 * @version 2013-11-18
	 */
	@Override
	public List<ChannelDataImport2> getChannelDataImport2ForFgsTop2(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectChannelDataImport2ForFgsTop2(t);
	}

	@Override
	public List<ChannelDataImport2> getChannelDataImport2ForFgsTop3(ChannelDataImport2 t) {
		return this.ChannelDataImport2Dao.selectChannelDataImport2ForFgsTop3(t);
	}
}