package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcBuyInfo;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-25
 */
public class PdShowHomePageAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30 || pu.getRole_id().intValue() == 178|| pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
					break;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
					break;
				}
			}
		}
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		// 根据用户类型（工卡、触网） 取不同系统商品
		if (zb) {
			konkaBcompPd.getMap().put("own_sys_in_01", "1");
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			konkaBcompPd.getMap().put("own_sys_in_02", "1");
			if (null != fgs_dept && fgs_dept.getDept_id() != null) {
				konkaBcompPd.getMap().put("dept_id", fgs_dept.getDept_id());
			}else{
				return null;
			}
		} else {
			konkaBcompPd.getMap().put("own_sys_in_01", "1");
		}
		konkaBcompPd.getMap().put("label_of_cate_eq", new Integer[] { 0, 1, 2, 3, 7 });
		konkaBcompPd.getMap().put("today", true);
		konkaBcompPd.getMap().put("is_upSelf", true);
		konkaBcompPd.getRow().setCount(5);// 首页只显示5条
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
		if (null == konkaBcompPdList || 0 == konkaBcompPdList.size())
			return null;
		List<KonkaBcompPd> entityList = new ArrayList<KonkaBcompPd>();
		for (KonkaBcompPd kb : konkaBcompPdList) {
			int r3store_num = 0;
			r3store_num = super.getStockCount(super.getCtxPath(request), kb.getPd_sn()).intValue();
			if (r3store_num < 10) {
				kb.getMap().put("r3_stock", String.valueOf(r3store_num));
				entityList.add(kb);
			}
		}
		request.setAttribute("entityList", entityList);

		// 待审核订单
		PshowOrder pshowOrder = new PshowOrder();
		if (fgs) {
			pshowOrder.setOpr_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
		}
		pshowOrder.setState(10);
		pshowOrder.getMap().put("cust_pay_way", true);

		pshowOrder.getRow().setFirst(0);
		pshowOrder.getRow().setCount(5);
		List<PshowOrder> entityList2 = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		        pshowOrder);
		request.setAttribute("entityList2", entityList2);

		// 待审核会员
		EcUser ecUser = new EcUser();
		ecUser.setIs_act(2);
		ecUser.setIs_del(0);
		ecUser.getRow().setFirst(0);
		ecUser.getRow().setCount(5);
		List<EcUser> entityList3 = super.getFacade().getEcUserService()
		        .getEcUserWithPositionNameAndFullDeptNamePaginatedList(ecUser);
		request.setAttribute("entityList3", entityList3);

		// 未发放佣金
		PshowOrdeDetails pds = new PshowOrdeDetails();
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month_start = String.valueOf(calendar.get(Calendar.MONTH) + 1 - 4);// 取前3个月
		String month_end = String.valueOf(calendar.get(Calendar.MONTH) + 1);// 当前月
		int month_start_num = Integer.valueOf(month_start);
		int month_end_num = Integer.valueOf(month_end);
		int start_num = 0;
		int end_num = 0;
		if (month_start_num < month_end_num) {
			start_num = month_start_num;
			end_num = month_end_num;
		} else {
			start_num = month_end_num;
			end_num = month_end_num;
		}

		String m_s = year + "-";
		String m_e = year + "-";
		if (start_num < 10) {
			m_s = m_s + "0" + start_num;
		} else {
			m_s = m_s + start_num;
		}
		if (end_num < 10) {
			m_e = m_e + "0" + end_num;
		} else {
			m_e = m_e + end_num;
		}
		pds.getMap().put("start_date", m_s);
		pds.getMap().put("end_date", m_e);
		pds.setRebates_status(new Integer(2));// 客户已经提现，等待发放佣金

		pds.getMap().put("state_in", new Integer[] { 60 });
		pds.getMap().put("rebates", "1");
		pds.getRow().setFirst(0);
		pds.getRow().setCount(5);

		List<PshowOrdeDetails> entityList4 = super.getFacade().getPshowOrdeDetailsService()
		        .getPshowOrdeDetailsForFgsPaginatedList(pds);
		request.setAttribute("entityList4", entityList4);

		// 待查看大宗采购
		EcBuyInfo ecInfo = new EcBuyInfo();
		ecInfo.setIs_view(0);// 未查看
		ecInfo.getRow().setFirst(0);
		ecInfo.getRow().setCount(5);
		List<EcBuyInfo> entityList5 = super.getFacade().getEcBuyInfoService().getEcBuyInfoPaginatedList(ecInfo);
		request.setAttribute("entityList5", entityList5);

		// 当月截止现在销售额
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cc = Calendar.getInstance();
		cc.setTime(new Date());
		cc.set(Calendar.DAY_OF_MONTH, 1);
		String add_time_start = df.format(cc.getTime());

		cc.setTime(new Date());
		String add_time_end = df.format(cc.getTime());

		PshowOrder ppo = new PshowOrder();
		if (StringUtils.isNotBlank(add_time_start)) {
			ppo.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			ppo.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		ppo.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });

		// Long recordCount =
		// super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameListCount(ppo);
		// ppo.getRow().setFirst(0);
		// ppo.getRow().setCount(recordCount.intValue());
		// List<PshowOrder> entityList6 =
		// super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
		// ppo);
		// int total_num = 0;
		// BigDecimal total_p = new BigDecimal("0.00");
		// BigDecimal rw_wcl = new BigDecimal(0);
		// if (null != entityList6 && entityList6.size() > 0) {
		// for (PshowOrder pp : entityList6) {
		// int t_num = 0;
		// PshowOrdeDetails psd = new PshowOrdeDetails();
		// psd.setOrder_id(Long.valueOf(pp.getId()));
		// List<PshowOrdeDetails> pshowordedetails =
		// super.getFacade().getPshowOrdeDetailsService()
		// .getPshowOrdeForPDSNDetailsList(psd);
		// pp.setPshowOrdeDetailsList(pshowordedetails);
		// if (null != pshowordedetails && pshowordedetails.size() > 0) {
		// for (PshowOrdeDetails ps : pshowordedetails) {
		// t_num += ps.getNum();
		// }
		// }
		// total_num = total_num + t_num;
		// if (pp.getPay_price() != null) {
		// total_p = total_p.add(pp.getPay_price());
		// }
		// }
		// }

		List<PshowOrder> entityList6 = super.getFacade().getPshowOrderService().getPshowOrderAndDetailsForTj(ppo);
		if (null != entityList6 && entityList6.size() > 0)
			ppo = entityList6.get(0);

		// rw_wcl = total_p.divide(new BigDecimal(20000));// 200万任务额
		request.setAttribute("total_num", ppo.getMap().get("sale_num"));// 总数量
		request.setAttribute("total_p", ppo.getMap().get("sale_price"));// 总金额
		request.setAttribute("rw_wcl", ppo.getMap().get("rw_wcl"));

		// 新增会员数
		EcUser ec = new EcUser();
		ec.setIs_del(0);
		ec.setIs_act(0);
		ec.setUser_type(1);
		ec.getMap().put("add_time_start", add_time_start + " 00:00:00");
		ec.getMap().put("add_time_end", add_time_end + " 23:59:59");
		Long day_user_add_count = super.getFacade().getEcUserService().getEcUserCount(ec);
		request.setAttribute("new_ec_user", day_user_add_count);

		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");
		String time = df2.format(new Date());
		dynaBean.set("day_time_start", df2.parse(time));

		return mapping.findForward("list");
	}

	public ActionForward getDaySellForCharts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");
		// 每日销售额，销售量，订单数，会员增加数 柱状图
		String day_time_start = (String) dynaBean.get("day_time_start");
		String now_day = "";
		if (StringUtils.isNotBlank(day_time_start)) {
			now_day = day_time_start;
			dynaBean.set("day_time_start", now_day);
		} else {
			Calendar c_now = Calendar.getInstance();
			c_now.setTime(new Date());
			now_day = df.format(c_now.getTime());
			dynaBean.set("day_time_start", df2.format(c_now.getTime()));
		}
		String strs[] = now_day.split("-");
		Calendar c1 = Calendar.getInstance();
		int year1 = Integer.parseInt(strs[0]);
		int month1 = Integer.parseInt(strs[1]) - 1;
		//System.out.println("month1---->" + month1);
		c1.set(year1, month1, 1);
		int maxDay = c1.getMaximum(Calendar.DAY_OF_MONTH);
		//System.out.println("maxDay---->" + maxDay);
		List<Object> datas = new ArrayList<Object>();
		for (int j = 1; j <= maxDay; j++) {
			c1.set(year1, month1, j);
			now_day = df.format(c1.getTime());
			if (Integer.parseInt(now_day.substring(5, 7)) == month1 + 1) {
				datas.add(now_day);
			}
		}
		List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
		for (Object object : datas) {
			Map<String, String> map = new HashMap<String, String>();
			//System.out.println("日期--->+" + object);
			PshowOrder pp = new PshowOrder();
			pp.setOrder_from(1);// 工卡
			pp.getMap().put("state_in", new Integer[] { 10, 20, 30, 40, 50, 60 });
			pp.getMap().put("add_time_start", object + " 00:00:00");
			pp.getMap().put("add_time_end", object + " 23:59:59");
			Long day_order_Count = super.getFacade().getPshowOrderService()
			        .getPshowOrdeForDeptNameAndFullNameListCount(pp);
			pp.getRow().setFirst(0);
			pp.getRow().setCount(day_order_Count.intValue());
			List<PshowOrder> dayList = super.getFacade().getPshowOrderService().getPshowOrdeForDeptNameAndFullNameList(
			        pp);
			int day_num = 0;// 销售台数
			BigDecimal day_order_price = new BigDecimal("0.00");
			if (null != dayList && dayList.size() > 0) {
				for (PshowOrder pshowOrder2 : dayList) {
					int t_num = 0;
					PshowOrdeDetails psd = new PshowOrdeDetails();
					psd.setOrder_id(Long.valueOf(pshowOrder2.getId()));
					List<PshowOrdeDetails> pshowordedetails = super.getFacade().getPshowOrdeDetailsService()
					        .getPshowOrdeForPDSNDetailsList(psd);
					pshowOrder2.setPshowOrdeDetailsList(pshowordedetails);
					if (null != pshowordedetails && pshowordedetails.size() > 0) {
						for (PshowOrdeDetails ps : pshowordedetails) {
							t_num += ps.getNum().intValue();
						}
					}
					day_num = day_num + t_num;
					if (pshowOrder2.getPay_price() != null) {
						day_order_price = day_order_price.add(pshowOrder2.getPay_price());
					}
				}
			}

			EcUser ec = new EcUser();
			ec.setIs_del(0);
			ec.setIs_act(0);
			ec.setUser_type(1);
			ec.getMap().put("add_time_start", object + " 00:00:00");
			ec.getMap().put("add_time_end", object + " 23:59:59");
			Long day_user_add_count = super.getFacade().getEcUserService().getEcUserCount(ec);

			map.put("day_time", object.toString());
			map.put("day_num", String.valueOf(day_num * 1000));
			map.put("day_order_Count", String.valueOf(day_order_Count.intValue() * 1000));
			map.put("day_order_price", day_order_price.toString());
			map.put("day_user_add_count", String.valueOf(day_user_add_count.intValue() * 1000));
			jsonList.add(map);

		}

		super.renderJson(response, JSON.toJSONString(jsonList));
		return null;

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(entity);
		if (null == entity) {
			return null;
		}
		super.copyProperties(form, entity);

		String pd_sn = entity.getPd_sn();

		// 工厂(需要和分公司绑定) not null
		String zwerks = "L00E";
		// 库位 not null
		String zlgort = "P046";
		// 仓位
		String zlgpla = "F146ZT";
		// 物料
		List<ZLEBIN> list = new ArrayList<ZLEBIN>();
		ReturnInfo<ZLEBIN> info = new ReturnInfo<ZLEBIN>();
		double dgCount = 0.00;
		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
			// list = getFacade().getR3WebInterfaceService().getZles20(zwerks,
			// zlgort, zlgpla, pd_sn);
			info = getFacade().getR3WebInterfaceService().getZles20(zwerks, zlgort, zlgpla, pd_sn);
			if (info.getSuccess() == true) {
				list = info.getDataResult();
			}
		}
		if (null != list && list.size() > 0) {
			ZLEBIN zlb = list.get(0);
			dgCount = dgCount + zlb.getVERME();
		}

		// 合肥分公司（滁州发货）
		// 工厂(需要和分公司绑定) not null
		String zwerks1 = "L00B";
		// 仓位 仓库地点 not null
		String zlgort1 = "F222";
		// 物料
		List<StockCheckResult> entitylist = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info2 = new ReturnInfo<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks1) && StringUtils.isNotBlank(zlgort1)) {
			info2 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks1, zlgort1, pd_sn);
			if (info2.getSuccess())
				entitylist = info2.getDataResult();
		}
		double hfCount = 0.00;
		if (null != entitylist && entitylist.size() > 0) {
			StockCheckResult scr = entitylist.get(0);
			hfCount = hfCount + scr.getLamount().doubleValue();
		}

		// 武汉分公司（武汉9001仓库发货）
		String zwerks2 = "L00D";// 工厂(需要和分公司绑定) not null
		String zlgort2 = "9001";// 仓位 仓库地点 not null
		double whCount = 0.00;
		List<StockCheckResult> entitylist2 = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info3 = new ReturnInfo<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks2) && StringUtils.isNotBlank(zlgort2)) {
			info3 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks2, zlgort2, pd_sn);
			if (info.getSuccess())
				entitylist2 = info3.getDataResult();
		}
		if (null != entitylist2 && entitylist2.size() > 0) {
			StockCheckResult scr = entitylist2.get(0);
			whCount = whCount + scr.getLamount().doubleValue();
		}

		// 武汉分公司（武汉孝感仓F113发货）
		String zwerks3 = "L00D";// 工厂(需要和分公司绑定) not null
		String zlgort3 = "F113";// 仓位 仓库地点 not null
		double whCount2 = 0.00; // 物料
		List<StockCheckResult> entitylist3 = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info4 = new ReturnInfo<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks3) && StringUtils.isNotBlank(zlgort3)) {
			// entitylist3 =
			// super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks3,
			// zlgort3, pd_sn);
			info4 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks3, zlgort3, pd_sn);
			if (info4.getSuccess() == true) {
				entitylist3 = info4.getDataResult();
			}
		}
		if (null != entitylist3 && entitylist3.size() > 0) {
			StockCheckResult scr = entitylist3.get(0);
			whCount2 = whCount2 + scr.getLamount().doubleValue();
		}

		// 哈尔滨分公司（哈尔滨9034发货）
		String zwerks4 = "L00C";// 工厂(需要和分公司绑定) not null
		String zlgort4 = "9034";// 仓位 仓库地点 not null
		double hebCount = 0.00; // 物料
		List<StockCheckResult> entitylist4 = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info5 = new ReturnInfo<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks4) && StringUtils.isNotBlank(zlgort4)) {
			// entitylist4 =
			// super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks4,
			// zlgort4, pd_sn);
			info5 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks4, zlgort4, pd_sn);
			if (info5.getSuccess() == true) {
				entitylist4 = info5.getDataResult();
			}
		}
		if (null != entitylist4 && entitylist4.size() > 0) {
			StockCheckResult scr = entitylist4.get(0);
			hebCount = hebCount + scr.getLamount().doubleValue();
		}

		dynaBean.set("dgCount", String.valueOf(dgCount));
		dynaBean.set("hfCount", String.valueOf(hfCount));
		dynaBean.set("whCount", String.valueOf(whCount));
		dynaBean.set("whCount2", String.valueOf(whCount2));
		dynaBean.set("hebCount", String.valueOf(hebCount));

		return mapping.findForward("view");
	}

	public ActionForward listAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30 || pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
					break;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
					break;
				}
			}
		}
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		// 根据用户类型（工卡、触网） 取不同系统商品
		if (zb) {
			konkaBcompPd.getMap().put("own_sys_in_01", "1");
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			konkaBcompPd.getMap().put("own_sys_in_02", "1");
			if (fgs_dept != null && fgs_dept.getDept_id() != null) {
				konkaBcompPd.getMap().put("dept_id", fgs_dept.getDept_id());
			}
		} else {
			konkaBcompPd.getMap().put("own_sys_in_01", "1");
		}
		konkaBcompPd.getMap().put("label_of_cate_eq", new Integer[] { 0, 1, 2, 3, 7 });
		konkaBcompPd.getMap().put("today", true);
		konkaBcompPd.getMap().put("is_upSelf", true);
		List<KonkaBcompPd> konkaBcompPdList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(
		        konkaBcompPd);
		if (null == konkaBcompPdList || 0 == konkaBcompPdList.size())
			return null;
		List<KonkaBcompPd> entityList = new ArrayList<KonkaBcompPd>();
		for (KonkaBcompPd kb : konkaBcompPdList) {
			int r3store_num = 0;
			r3store_num = super.getStockCount(super.getCtxPath(request), kb.getPd_sn()).intValue();
			if (r3store_num < 10) {
				kb.getMap().put("r3_stock", String.valueOf(r3store_num));
				entityList.add(kb);
			}
		}
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/admin/PdShowHomePage/listAll.jsp");
	}

}