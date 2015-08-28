package com.ebiz.mmt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.domain.KonkaBbItr2Import;
import com.ebiz.mmt.domain.KonkaBbZj98Import;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaR3OrderLines;
import com.ebiz.mmt.domain.KonkaSapInterfaceSetting;
import com.ebiz.mmt.domain.SapExecuteLog;
import com.ebiz.mmt.r3.ExcuteMsg;
import com.ebiz.mmt.r3.ITR2;
import com.ebiz.mmt.r3.KCXX;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.KHYS;
import com.ebiz.mmt.r3.KNA1;
import com.ebiz.mmt.r3.KNVP;
import com.ebiz.mmt.r3.KUKLA;
import com.ebiz.mmt.r3.MARA;
import com.ebiz.mmt.r3.ORDER_HEADER_IN;
import com.ebiz.mmt.r3.ORDER_ITEMS_IN;
import com.ebiz.mmt.r3.OpType;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.SOXX;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZA006;
import com.ebiz.mmt.r3.ZJ98;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.r3.ZLES23;
import com.ebiz.mmt.r3.ZSOF;
import com.ebiz.mmt.r3.ZVBALR;
import com.ebiz.mmt.r3.ZdmtrxCriteria;
import com.ebiz.mmt.r3.Zles29a;
import com.ebiz.mmt.r3.Zles29aCriteria;
import com.ebiz.mmt.r3.helper.R3Dao;
import com.ebiz.mmt.service.KonkaSapInterfaceSettingService;
import com.ebiz.mmt.service.R3WebInterfaceService;
import com.ebiz.mmt.service.SapExecuteLogService;
import com.ebiz.org.apache.commons.lang3.StringUtils;

@Service
public class R3WebInterfaceServiceImpl implements R3WebInterfaceService {

	@Resource
	private R3Dao r3Dao;
	@Resource
	private KonkaSapInterfaceSettingService konkaSapInterfaceSettingService;
	@Resource
	private SapExecuteLogService sapExecuteLogService;

	/**
	 * 客户分类接口
	 */
	private final static String GETKULALIST = "getkuklaList";
	/**
	 * 客户信息接口
	 */
	private final static String GETCUSTOMERLIST = "getCustomerList";
	/**
	 * 客户合作伙伴信息接口
	 */
	private final static String GETKNVPSLIST = "getKnvpsList";
	/**
	 * 机型信息接口
	 */
	private final static String GETMARALIST = "getMaraList";
	/**
	 * 客户信贷信息接口
	 */
	private final static String GETKHXD = "getKhxd";

	/**
	 * 客户信贷信息接口
	 */
	private final static String GETZA006 = "getZa006";

	/**
	 * SO订单信息FIFO
	 */
	private final static String GETZVBALR = "getZvbalr";

	/**
	 * 订单物流信息
	 */
	private final static String GETR3DELIVERY = "getR3Delivery";

	/**
	 * 客户应收信息接口
	 */
	private final static String GETKHYS = "getKhys";
	/**
	 * 客户库存信息接口
	 */
	private final static String GETKCXX = "getKcxx";
	/**
	 * 客户销售订单(用于统计)信息
	 */
	private final static String GETSOXX = "getSoxx";
	/**
	 * 客户销售订单(用于查询明细不保存)信息
	 */
	private final static String GETSOXXMX = "getSoxxMX";
	/**
	 * 分公司机型库存信息
	 */
	private final static String GETFGSKC = "getFGSKC";
	/**
	 * y/p/q/d库存信息
	 */
	private final static String GETZLES20 = "getZles20";
	/**
	 * 集采订单
	 */
	private final static String GETZLES23 = "getZles23";
	/**
	 * 分公司调拨评估信息
	 */
	private final static String GETZLES29A = "getZles29a";
	/**
	 * 机型销存比信息
	 */
	private final static String GETZLESZJ98 = "getZlesZJ98";
	/**
	 * 机型销售利润信息
	 * 
	 */
	private final static String GETITR2 = "getITR2";
	// 订单创建时调用库存校验
	private final static String CHECKSTOCK = "checkStock";
	// 订单审批时调用库存校验
	private final static String CHECKSTOCK2 = "checkStock2";

	//
	private final static String SAVEKONKAORDERINFO = "saveKonkaOrderInfo";
	private final static String MODIFYKONKAORDERINFO = "modifyKonkaOrderInfo";
	private final static String SAVEKONKAORDERINFORETURN = "saveKonkaOrderInfoReturn";
	private final static String SAVEKONKAORDERINFODESTORY = "saveKonkaOrderInfoDestory";

	@Override
	public ReturnInfo<KNA1> getCustomerList(String in_date, String bukrs, String[] kunnr) {
		ReturnInfo<KNA1> info = new ReturnInfo<KNA1>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETCUSTOMERLIST);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETCUSTOMERLIST + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		// 在接口启用的情况下

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());
		// data
		List<KNA1> list = new ArrayList<KNA1>();
		list = r3Dao.getCustomerList(in_date, bukrs, kunnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETCUSTOMERLIST);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;

	}

	@Override
	public ReturnInfo<KNVP> getKnvpsList(String vkorg, String vtweg, String spart, String kunnr) {
		ReturnInfo<KNVP> info = new ReturnInfo<KNVP>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETKNVPSLIST);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETKNVPSLIST + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		// 在接口启用的情况下

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());
		// data
		List<KNVP> list = new ArrayList<KNVP>();
		list = r3Dao.getKnvpsList(vkorg, vtweg, spart, kunnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETKNVPSLIST);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<KUKLA> getkuklaList() {
		ReturnInfo<KUKLA> info = new ReturnInfo<KUKLA>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETKULALIST);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETKULALIST + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		// 在接口启用的情况下

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<KUKLA> list = new ArrayList<KUKLA>();
		list = r3Dao.getkuklaList();
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);

		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETKULALIST);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<MARA> getMaraList(String erdat) {
		ReturnInfo<MARA> info = new ReturnInfo<MARA>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETMARALIST);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETMARALIST + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		// 在接口启用的情况下

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<MARA> list = new ArrayList<MARA>();
		list = r3Dao.getMaraList(erdat);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETMARALIST);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;

	}

	@Override
	public ReturnInfo<StockCheckResult> checkStock(String zbukrs, List<KonkaR3OrderLines> itemList) {
		ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(CHECKSTOCK);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
            msg.setMessage("SAP接口" + CHECKSTOCK + "未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<StockCheckResult> list = new ArrayList<StockCheckResult>();
		list = r3Dao.checkStock(zbukrs, itemList);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(CHECKSTOCK);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;

	}

	@Override
	public ReturnInfo<KHXD> getKhxd(String v_kkber, String v_vkorg, String v_spart, String[] kunnr) {

		ReturnInfo<KHXD> info = new ReturnInfo<KHXD>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETKHXD);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETKHXD + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		boolean xflag = true;
		if (kunnr != null) {
			for (String x : kunnr) {
				if (x == null) {
					xflag = false;
					break;
				}
				if (x.equals("")) {
					xflag = false;
					break;
				}

			}
		}
		// data
		List<KHXD> list = new ArrayList<KHXD>();
		if (xflag) {
			list = r3Dao.getKhxd(v_kkber, v_vkorg, v_spart, kunnr);
		} else {
			list = r3Dao.getKhxd(v_kkber, v_vkorg, v_spart, null);
		}

		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETKHXD);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;

	}

	@Override
	public ReturnInfo<KHYS> getKhys(String v_gjahr, String v_monat, String v_spart, String v_vkorg, String[] kunnr) {

		ReturnInfo<KHYS> info = new ReturnInfo<KHYS>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETKHYS);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETKHYS + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<KHYS> list = new ArrayList<KHYS>();
		list = r3Dao.getKhys(v_gjahr, v_monat, v_spart, v_vkorg, kunnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETKHYS);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<KCXX> getKcxx(String v_werks, String v_lgort, String v_lgpla, String v_matnr) {

		ReturnInfo<KCXX> info = new ReturnInfo<KCXX>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETKCXX);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETKCXX + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<KCXX> list = new ArrayList<KCXX>();
		list = r3Dao.getKcxx(v_werks, v_lgort, v_lgpla, v_matnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETKCXX);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;

	}

	@Override
	public ReturnInfo<SOXX> getSoxx(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr) {
		ReturnInfo<SOXX> info = new ReturnInfo<SOXX>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETSOXX);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETSOXX + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<SOXX> list = new ArrayList<SOXX>();
		list = r3Dao.getSoxxTj(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETSOXX);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;

	}

	@Override
	public ReturnInfo<SOXX> getSoxxMX(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr) {

		ReturnInfo<SOXX> info = new ReturnInfo<SOXX>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETSOXXMX);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETSOXXMX + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<SOXX> list = new ArrayList<SOXX>();
		list = r3Dao.getSoxxMx(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETSOXXMX);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<StockCheckResult> getFGSKC(String zwerks, String zlgort, String zmatnr) {
		ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETFGSKC);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETFGSKC + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<StockCheckResult> list = new ArrayList<StockCheckResult>();
		list = r3Dao.getFGSKC(zwerks, zlgort, zmatnr);
		info.setDataResult(list);
		info.setSuccess(true);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETFGSKC);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;

	}

	@Override
	public ReturnInfo<ZLEBIN> getZles20(String zwerks, String zlgort, String zlgpla, String zmatnr) {
		ReturnInfo<ZLEBIN> info = new ReturnInfo<ZLEBIN>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETZLES20);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETZLES20 + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<ZLEBIN> list = new ArrayList<ZLEBIN>();
		list = r3Dao.getZles20(zwerks, zlgort, zlgpla, zmatnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETZLES20);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<Zles29a> getZles29a(Zles29aCriteria zc) {
		ReturnInfo<Zles29a> info = new ReturnInfo<Zles29a>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETZLES29A);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETZLES29A + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<Zles29a> list = new ArrayList<Zles29a>();
		list = r3Dao.getZles29a(zc);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETZLES29A);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<ZLES23> getZles23(String erdat_b, String erdat_e, String ZEBELN) {
		ReturnInfo<ZLES23> info = new ReturnInfo<ZLES23>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETZLES23);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETZLES23 + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<ZLES23> list = new ArrayList<ZLES23>();
		list = r3Dao.getZles23(erdat_b, erdat_e, ZEBELN);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETZLES23);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<KonkaBbZj98Import> getZlesZJ98(String v_matnr_begin, String v_matnr_end, String v_vtweg,
			String v_spart, String v_bstdk_begin, String v_bstdk_end, String v_cxb_begin, String v_cxb_end) {

		ReturnInfo<KonkaBbZj98Import> info = new ReturnInfo<KonkaBbZj98Import>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();
		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETZLESZJ98);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETZLESZJ98 + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		List<KonkaBbZj98Import> list = new ArrayList<KonkaBbZj98Import>();
		Date date = new Date();
		Date p_date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			p_date = sdf.parse(v_bstdk_end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(p_date);

		// r3接口已经对数字进行处理
		List<ZJ98> zJ98list = r3Dao.getZlesZJ98(v_matnr_begin, v_matnr_end, v_vtweg, v_spart, v_bstdk_begin,
				v_bstdk_end, v_cxb_begin, v_cxb_end);

		for (ZJ98 zj : zJ98list) {
			KonkaBbZj98Import ki = new KonkaBbZj98Import();

			ki.setCoamt(Double.valueOf(zj.getCOAMT()));
			//
			ki.setLabs2(Double.valueOf(zj.getLABS2()));
			ki.setLabs3(Double.valueOf(zj.getLABS3()));
			ki.setLabs4(Double.valueOf(zj.getLABS4()));
			ki.setLabs5(Double.valueOf(zj.getLABS5()));
			ki.setLabs6(Double.valueOf(zj.getLABS6()));
			ki.setLabs7(Double.valueOf(zj.getLABS7()));
			ki.setLabs10(Double.valueOf(zj.getLABS10()));
			ki.setLabs11(Double.valueOf(zj.getLABS11()));
			ki.setLabs12(Double.valueOf(zj.getLABS12()));
			ki.setLabs13(Double.valueOf(zj.getLABS13()));
			ki.setLabs14(Double.valueOf(zj.getLABS14()));
			ki.setLabs15(Double.valueOf(zj.getLABS15()));
			ki.setLabs16(Double.valueOf(zj.getLABS16()));
			ki.setLabs17(Double.valueOf(zj.getLABS17()));
			ki.setLabs18(Double.valueOf(zj.getLABS18()));
			ki.setLabsdf(Double.valueOf(zj.getLABSDF()));
			ki.setLabsf(Double.valueOf(zj.getLABSF()));
			ki.setLabsfk(Double.valueOf(zj.getLABSFK()));
			ki.setLabsz(Double.valueOf(zj.getLABSZ()));
			ki.setLbkum(Double.valueOf(zj.getLBKUM()));

			//
			ki.setMatkl(zj.getMATKL());
			ki.setMatnr(zj.getMATNR());
			ki.setMl(Double.valueOf(zj.getML()));
			ki.setMlv(Double.valueOf(zj.getMLV()));
			ki.setMenge(Double.valueOf(zj.getMENGE()));

			// 表示x年x月x天的数据,此三者应该根据传入的同步的时间值进行计算
			// ki.setYear(Long.valueOf(year + ""));
			// ki.setMonth(Long.valueOf(month + ""));
			// ki.setDay(Long.valueOf(day + ""));

			//
			ki.setGcb(Double.valueOf(zj.getGCB()));
			ki.setGxmlv(Double.valueOf(zj.getGXMLV()));
			ki.setNetpr(Double.valueOf(zj.getNETPR()));
			ki.setSaamt(Double.valueOf(zj.getSAAMT()));
			ki.setSalk3(Double.valueOf(zj.getSALK3()));
			ki.setStock_cxb(Double.valueOf(zj.getSTOCK_CXB()));
			ki.setStock_gcb(Double.valueOf(zj.getSTOCK_GCB()));
			ki.setStock_ml(Double.valueOf(zj.getSTOCK_ML()));
			ki.setStock_mlv(Double.valueOf(zj.getSTOCK_MLV()));
			ki.setSync_time(date);
			ki.setVerpr(Double.valueOf(zj.getVERPR()));
			ki.setVtweg(zj.getVTWEG());
			ki.setUnit_co(Double.valueOf(zj.getUNIT_CO()));
			ki.setUnit_ml(Double.valueOf(zj.getUNIT_ML()));

			list.add(ki);
		}
		// data
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETZLESZJ98);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<KonkaBbItr2Import> getITR2(ZdmtrxCriteria zc) {

		ReturnInfo<KonkaBbItr2Import> info = new ReturnInfo<KonkaBbItr2Import>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETITR2);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETITR2 + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		List<KonkaBbItr2Import> list = new ArrayList<KonkaBbItr2Import>();
		// r3接口已经对数字进行处理
		List<ITR2> iTR2list = r3Dao.getITR2(zc);
		// ===========

		for (ITR2 it : iTR2list) {
			KonkaBbItr2Import ki = new KonkaBbItr2Import();
			ki.setLfimg(Double.valueOf(it.getLFIMG()));
			ki.setMatnr(it.getMATNR());
			//
			ki.setYear(Long.valueOf(year + ""));
			ki.setMonth(Long.valueOf(month + ""));
			ki.setDay(Long.valueOf(day + ""));

			ki.setSync_time(date);

			ki.setZ_cost(Double.valueOf(it.getZCOST()));
			ki.setZnet_sincome(Double.valueOf(it.getZNET_SINCOME()));
			ki.setZprofit(Double.valueOf(it.getZPROFIT()));

			ki.setZprofit_v(Double.valueOf(it.getZPROFIT_V()));

			list.add(ki);

		}

		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETITR2);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ZSOF getR3Delivery(Long v_vbeln) {

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETR3DELIVERY);
		if (ks == null) {
			return null;
		}

		ZSOF os = new ZSOF();
		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());
		// call
		os = r3Dao.getR3Delivery(v_vbeln);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETR3DELIVERY);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return os;
	}

	@Override
	public ReturnInfo<ZA006> getZa006(String v_matnr) {

		ReturnInfo<ZA006> info = new ReturnInfo<ZA006>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETZA006);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETZA006 + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<ZA006> list = new ArrayList<ZA006>();
		list = r3Dao.getZa006(v_matnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETZA006);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo<StockCheckResult> checkStock2(String zwerks, String zlgort, String matnr) {
		ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(CHECKSTOCK2);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(CHECKSTOCK2 + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<StockCheckResult> list = new ArrayList<StockCheckResult>();
		list = r3Dao.getFGSKC(zwerks, zlgort, matnr);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(CHECKSTOCK2);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

	@Override
	public ReturnInfo saveKonkaOrderInfo(KonkaOrderInfo konkaOrderInfo, String opername) {

		ReturnInfo info = new ReturnInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(SAVEKONKAORDERINFO);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(SAVEKONKAORDERINFO + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		if (null == konkaOrderInfo) {
			// Assert.notNull(konkaOrderInfo, "konkaOrderInfo is required");
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage("订单信息不能为空");
			msgList.add(msg);
			info.setMsgList(msgList);
			return info;
		}
		if (konkaOrderInfo.getKonkaOrderInfoDetailsList() == null
				|| konkaOrderInfo.getKonkaOrderInfoDetailsList().size() <= 0) {
			// Assert.notEmpty(konkaOrderInfo.getKonkaOrderInfoDetailsList(),
			// "konkaOrderInfoDetailsList is required");
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage("订单行信息不能为空");
			msgList.add(msg);
			info.setMsgList(msgList);
			return info;
		}

		if (konkaOrderInfo.getKonkaOrderInfoDetailsList().size() > 0) {
			List<ExcuteMsg> emList = new ArrayList<ExcuteMsg>();
			for (KonkaOrderInfoDetails kd : konkaOrderInfo.getKonkaOrderInfoDetailsList()) {
				if (kd.getItemno() == null || kd.getItemno() == 0) {
					ExcuteMsg em = new ExcuteMsg();
					em.setType("E");
					em.setMessage(konkaOrderInfo.getTrade_index() + "存在错误的订单行项目号,正确的行项目号类似:10,20,30...");
					emList.add(em);
					info.setSuccess(false);
					info.setMsgList(emList);
					return info;
				} else {
					// go
				}
			}
		}

		ORDER_HEADER_IN header = new ORDER_HEADER_IN();

		// 单据日期
		if (konkaOrderInfo.getAdd_date() != null) {
			header.setDoc_date(sdf.format(konkaOrderInfo.getAdd_date()));
		} else {
			header.setDoc_date(sdf.format(new Date()));
		}

		// 单据类型
		header.setDoc_type(konkaOrderInfo.getDoc_type());
		// 销售组织
		header.setSales_org(konkaOrderInfo.getSales_org());
		// 分销渠道
		header.setDistr_chan("10");
		// 产品组
		header.setDivision(konkaOrderInfo.getDivision());
		header.setAg(konkaOrderInfo.getAg());
		header.setWe(konkaOrderInfo.getWe());
		header.setRe(konkaOrderInfo.getRe());
		header.setRg(konkaOrderInfo.getRg());
		// 采购日期(手工填)
		header.setPurch_date(konkaOrderInfo.getCg_order_date());
		// 采购编号(手工填)
		header.setPurch_no_c(konkaOrderInfo.getThird_cg_order_num());

		// 销售单号(渠道系统订单的流水号)
		header.setPurch_no_s(konkaOrderInfo.getTrade_index());

		// 当前操作接口的人名称
		header.setOpername(opername);

		header.setCustomerCity(konkaOrderInfo.getSales_org());
		header.setCustomerName(konkaOrderInfo.getUser_shop_name());
		header.setCustomerTxt("");

		// 订单行
		List<ORDER_ITEMS_IN> itemlist = new ArrayList<ORDER_ITEMS_IN>();

		for (KonkaOrderInfoDetails cur : konkaOrderInfo.getKonkaOrderInfoDetailsList()) {
			ORDER_ITEMS_IN line = new ORDER_ITEMS_IN();

			line.setITEMNO(cur.getItemno());
			line.setMATERIAL(cur.getPd_name());// 物料
			line.setSALES_UNIT("SET");
			line.setTARGET_QTY(cur.getGood_count());// 数量
			// 每一行订单行的机型的含税单价
			line.setCOND_VALUE(cur.getGood_price().doubleValue());

			// RB00
			line.setRB00_COND_VALUE(cur.getGood_discount_price().doubleValue());
			// K007
			line.setK007_COND_VALUE(cur.getGood_discount().doubleValue());

			line.setTARGET_QU("SET");

			line.setCUST_MAT35(cur.getPd_remark());
			// 处理异常标识符
			if (StringUtils.containsIgnoreCase(cur.getPd_remark(), "退市样机")) {
				line.setCUST_MAT35("退市样机");
			} else if (StringUtils.containsIgnoreCase(cur.getPd_remark(), "常规处理机")) {
				line.setCUST_MAT35("常规处理机");
			}

			// 不能传物流工厂的
			// line.setPLANT(line.getPLANT());// 工厂
			// line.setSTORE_LOC(line.getSTORE_LOC());// 库位
			// line.setSHIP_POINT(line.getSHIP_POINT());// 装运点

			line.setSALQTYDEN(1);// sku分子
			line.setSALQTYNUM(1);// /sku值

			// ZFOR 常规 ,ZFGC 工程机 ,退货 ZFRE
			// if (header.getDoc_type().equals("ZFOR") ||
			// header.getDoc_type().equals("ZFGC")) {
			// line.setITEM_CATEG("ZFTN"); // 正向销售
			// } else {
			// line.setITEM_CATEG("ZREN"); // 退货
			// }
			itemlist.add(line);
		}

		header.setItemList(itemlist);
		info = this.r3Dao.createSO(header);

		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(SAVEKONKAORDERINFO);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.POST);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);

		return info;
	}

	@Override
	public ReturnInfo modifyKonkaOrderInfo(KonkaOrderInfo konkaOrderInfo, String orderNumber, String opername) {
		ReturnInfo info = new ReturnInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(MODIFYKONKAORDERINFO);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(MODIFYKONKAORDERINFO + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());
		if (null == konkaOrderInfo) {
			// Assert.notNull(konkaOrderInfo, "konkaOrderInfo is required");
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage("订单信息不能为空");
			msgList.add(msg);
			info.setMsgList(msgList);
			return info;
		}
		if (konkaOrderInfo.getKonkaOrderInfoDetailsList().size() <= 0) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage("订单行信息不能为空");
			msgList.add(msg);
			info.setMsgList(msgList);
			return info;
		}

		if (konkaOrderInfo.getKonkaOrderInfoDetailsList().size() > 0) {
			List<ExcuteMsg> emList = new ArrayList<ExcuteMsg>();
			for (KonkaOrderInfoDetails kd : konkaOrderInfo.getKonkaOrderInfoDetailsList()) {
				if (kd.getItemno() == null || kd.getItemno() == 0) {
					ExcuteMsg em = new ExcuteMsg();
					em.setType("E");
					em.setMessage(konkaOrderInfo.getTrade_index() + "存在错误的订单行项目号,正确的行项目号类似:10,20,30...");
					emList.add(em);
					info.setSuccess(false);
					info.setMsgList(emList);
					return info;
				} else {
					// go
				}
			}

		}

		ORDER_HEADER_IN header = new ORDER_HEADER_IN();

		header.setDoc_type(konkaOrderInfo.getDoc_type());
		header.setSales_org(konkaOrderInfo.getSales_org());
		header.setDistr_chan("10");
		header.setDivision(konkaOrderInfo.getDivision());
		header.setAg(konkaOrderInfo.getAg());
		header.setWe(konkaOrderInfo.getWe());
		header.setRe(konkaOrderInfo.getRe());
		header.setRg(konkaOrderInfo.getRg());
		// 采购日期(手工填)
		header.setPurch_date(konkaOrderInfo.getCg_order_date());
		// 采购编号(手工填)
		header.setPurch_no_c(konkaOrderInfo.getThird_cg_order_num());

		// 销售单号(渠道系统订单的流水号)
		header.setPurch_no_s(konkaOrderInfo.getTrade_index());

		// 当前操作接口的人名称
		header.setOpername(opername);

		// 订单行
		List<ORDER_ITEMS_IN> itemlist = new ArrayList<ORDER_ITEMS_IN>();

		for (KonkaOrderInfoDetails cur : konkaOrderInfo.getKonkaOrderInfoDetailsList()) {
			ORDER_ITEMS_IN line = new ORDER_ITEMS_IN();
			// 订单行项目号
			line.setITEMNO(cur.getItemno());
			line.setMATERIAL(cur.getPd_name());// 物料
			line.setSALES_UNIT("SET");
			line.setTARGET_QTY(cur.getGood_count());// 数量
			// 每一行订单行的机型的含税单价
			line.setCOND_VALUE(cur.getGood_price().doubleValue());
			// RB00
			line.setRB00_COND_VALUE(cur.getGood_discount_price().doubleValue());
			// K007
			line.setK007_COND_VALUE(cur.getGood_discount().doubleValue());
			line.setTARGET_QU("SET");
			line.setCUST_MAT35(cur.getPd_remark());// 行备注
			// 处理异常标识符
			if (StringUtils.containsIgnoreCase(cur.getPd_remark(), "退市样机")) {
				line.setCUST_MAT35("退市样机");
			} else if (StringUtils.containsIgnoreCase(cur.getPd_remark(), "常规处理机")) {
				line.setCUST_MAT35("常规处理机");
			}
			line.setSALQTYDEN(1);// sku分子
			line.setSALQTYNUM(1);// /sku值
			itemlist.add(line);
		}
		header.setItemList(itemlist);
		// header 里面有opername信息
		info = this.r3Dao.updateSO(header, orderNumber);

		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(MODIFYKONKAORDERINFO);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.PUT);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);

		return info;
	}

	@Override
	public ReturnInfo saveKonkaOrderInfoReturn(KonkaOrderInfo konkaOrderInfo, String opername) {
		ReturnInfo info = new ReturnInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(SAVEKONKAORDERINFORETURN);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(SAVEKONKAORDERINFORETURN + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		if (null == konkaOrderInfo) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage("订单信息不能为空");
			msgList.add(msg);
			info.setMsgList(msgList);
			return info;
		}
		if (konkaOrderInfo.getKonkaOrderInfoDetailsList().size() <= 0) {
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage("订单行信息不能为空");
			msgList.add(msg);
			info.setSuccess(false);
			info.setMsgList(msgList);
			return info;
		}
		if (konkaOrderInfo.getKonkaOrderInfoDetailsList().size() > 0) {
			List<ExcuteMsg> emList = new ArrayList<ExcuteMsg>();
			for (KonkaOrderInfoDetails kd : konkaOrderInfo.getKonkaOrderInfoDetailsList()) {
				if (kd.getItemno() == null || kd.getItemno() == 0) {
					ExcuteMsg em = new ExcuteMsg();
					em.setType("E");
					em.setMessage(konkaOrderInfo.getTrade_index() + "存在错误的订单行项目号,正确的行项目号类似:10,20,30...");
					emList.add(em);
					info.setSuccess(false);
					info.setMsgList(emList);
					return info;
				} else {
					// go
				}
			}
		}

		ORDER_HEADER_IN header = new ORDER_HEADER_IN();

		// 当前操作接口的人名称
		header.setOpername(opername);

		header.setOrdreason(konkaOrderInfo.getReturn_reason());
		// 单据类型
		header.setDoc_type(konkaOrderInfo.getDoc_type());
		// 销售组织
		header.setSales_org(konkaOrderInfo.getSales_org());
		// 分销渠道
		header.setDistr_chan("10");
		// 产品组
		header.setDivision(konkaOrderInfo.getDivision());
		header.setAg(konkaOrderInfo.getAg());
		header.setWe(konkaOrderInfo.getWe());
		header.setRe(konkaOrderInfo.getRe());
		header.setRg(konkaOrderInfo.getRg());
		// 采购日期(手工填)
		header.setPurch_date(konkaOrderInfo.getCg_order_date());
		// 采购编号(手工填)
		header.setPurch_no_c(konkaOrderInfo.getThird_cg_order_num());
		// 销售单号(渠道系统订单的流水号)
		header.setPurch_no_s(konkaOrderInfo.getTrade_index());

		header.setCustomerCity(konkaOrderInfo.getSales_org());
		header.setCustomerName(konkaOrderInfo.getUser_shop_name());
		header.setCustomerTxt("");

		// 订单行
		List<ORDER_ITEMS_IN> itemlist = new ArrayList<ORDER_ITEMS_IN>();

		for (KonkaOrderInfoDetails cur : konkaOrderInfo.getKonkaOrderInfoDetailsList()) {
			ORDER_ITEMS_IN line = new ORDER_ITEMS_IN();
			line.setITEMNO(cur.getItemno());
			line.setMATERIAL(cur.getPd_name());// 物料
			line.setSALES_UNIT("SET");
			line.setTARGET_QTY(cur.getGood_count());// 数量
			// 每一行订单行的机型的含税单价
			line.setCOND_VALUE(cur.getGood_price().doubleValue());

			// RB00
			line.setRB00_COND_VALUE(cur.getGood_discount_price().doubleValue());
			// K007
			line.setK007_COND_VALUE(cur.getGood_discount().doubleValue());

			line.setTARGET_QU("SET");

			line.setCUST_MAT35(cur.getPd_remark());

			// 处理异常标识符
			if (StringUtils.containsIgnoreCase(cur.getPd_remark(), "退市样机")) {
				line.setCUST_MAT35("退市样机");
			} else if (StringUtils.containsIgnoreCase(cur.getPd_remark(), "常规处理机")) {
				line.setCUST_MAT35("常规处理机");
			}

			// 不能传物流工厂的
			// line.setPLANT(line.getPLANT());// 工厂
			// line.setSTORE_LOC(line.getSTORE_LOC());// 库位
			// line.setSHIP_POINT(line.getSHIP_POINT());// 装运点

			line.setSALQTYDEN(1);// sku分子
			line.setSALQTYNUM(1);// /sku值

			line.setITEM_CATEG("ZREN"); // 退货
			itemlist.add(line);
		}

		header.setItemList(itemlist);
		info = this.r3Dao.createReturnSO(header);

		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(SAVEKONKAORDERINFORETURN);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.POST);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);

		return info;
	}

	@Override
	public ReturnInfo saveKonkaOrderInfoDestory(String orderNumber, String opername) {
		ReturnInfo info = new ReturnInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(SAVEKONKAORDERINFODESTORY);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(SAVEKONKAORDERINFODESTORY + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		info = this.r3Dao.createDestorySO(orderNumber, opername);

		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(SAVEKONKAORDERINFODESTORY);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.DELETE);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);

		return info;
	}

	@Override
	public ReturnInfo<ZVBALR> getZVBALRList(String v_vkorg, String v_vtweg, String v_spart, String v_audat_begin,
			String v_audat_end, String v_kunnr, String v_vbeln) {

		ReturnInfo<ZVBALR> info = new ReturnInfo<ZVBALR>();
		List<ExcuteMsg> msgList = new ArrayList<ExcuteMsg>();

		// sap接口是否开放,由于r3系统不稳定,渠道系统手工设置接口的调用
		KonkaSapInterfaceSetting ks;
		ks = konkaSapInterfaceSettingService.getValidKonkaSapInterface(GETZVBALR);
		if (ks == null) {
			info.setSuccess(false);
			ExcuteMsg msg = new ExcuteMsg();
			msg.setType("E");
			msg.setMessage(GETZVBALR + "接口未启用");
			msgList.add(msg);
			info.setMsgList(msgList);
			info.setDataResult(null);
			return info;
		}

		SapExecuteLog log = new SapExecuteLog();
		log.setBegin_time(new Date());

		// data
		List<ZVBALR> list = new ArrayList<ZVBALR>();
		list = r3Dao.getZVBALRList(v_vkorg, v_vtweg, v_spart, v_audat_begin, v_audat_end, v_kunnr, v_vbeln);
		info.setSuccess(true);
		info.setDataResult(list);
		// execute message
		ExcuteMsg msg = new ExcuteMsg();
		msg.setType("S");
		msg.setMessage(ks.getServiceDesc() + "成功执行!");
		msgList.add(msg);
		info.setMsgList(msgList);
		// log
		log.setEnd_time(new Date());
		log.setCost(log.getEnd_time().getTime() - log.getBegin_time().getTime());
		log.setExecute_year(Calendar.getInstance().get(Calendar.YEAR));
		log.setFunc(GETZVBALR);
		log.setFunc_desc(ks.getServiceDesc());
		log.setOp_type(OpType.GET);
		// log.setOp_user(1);
		sapExecuteLogService.createSapExecuteLog(log);
		return info;
	}

}
