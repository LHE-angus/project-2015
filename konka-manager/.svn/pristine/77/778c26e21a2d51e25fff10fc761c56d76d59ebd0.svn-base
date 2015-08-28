package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.chart.BaseChart;

/**
 * @author Jiang,JiaYong
 * @version 2012-04-05
 */
public class KonkaXxPdStatisticsAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.view(mapping, form, request, response);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		
		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		// 取用户角色并判断是不是分公司级别的角色
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		//request.setAttribute("peRoleUser", peRoleUser);
		
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_eq_400 = false;
		Boolean role_id_btw_200_300 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if(temp.getRole_id()==400L){
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() >= 200 && temp.getRole_id() < 300) {
					role_id_btw_200_300 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}
		request.setAttribute("role_id_btw_200_300", role_id_btw_200_300);
		//request.setAttribute("role_id_btw_300_400", role_id_btw_300_400);
		//request.setAttribute("role_id_eq_400", role_id_eq_400);
		
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		// 总部角色登陆查询分公司
		if (role_id_btw_200_300) {
			// 查询分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(0L);
			konkaDept.setDept_type(3);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			request.setAttribute("konkaDeptList", konkaDeptList);
			
			// 选择了查询条件进行查询
			if(GenericValidator.isLong(dept_id) && StringUtils.isNotBlank(date_start) && StringUtils.isNotBlank(date_end)){
				KonkaXxSellBill ksb = new KonkaXxSellBill();
				ksb.setDept_id(Long.valueOf(dept_id));
				if(GenericValidator.isLong(zmd_id))
					ksb.setZmd_id(Long.valueOf(zmd_id));
				ksb.getMap().put("add_date_ge", date_start);
				ksb.getMap().put("add_date_le", date_end);
				List<KonkaXxSellBill> entityListForMoney = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillDeptPdSellMoneySumList(ksb);
				List<KonkaXxSellBill> entityListForNum = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillDeptPdSellNumSumList(ksb);
				request.setAttribute("entityListForMoney", entityListForMoney);
				request.setAttribute("entityListForNum", entityListForNum);
				request.setAttribute("dept_id", dept_id);
				request.setAttribute("zmd_id", zmd_id);
			} 
		}

		// 分公司角色登陆查询分公司
		if (role_id_btw_300_400) {
			// 查询专卖店
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setDept_id(kDept.getDept_id());
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

			request.setAttribute("zmdList", zmdList);

			// 选择了查询条件进行查询
			if(StringUtils.isNotBlank(date_start) && StringUtils.isNotBlank(date_end)){
				KonkaXxSellBill ksb = new KonkaXxSellBill();
				ksb.setDept_id(kDept.getDept_id());
				if(GenericValidator.isLong(zmd_id))
					ksb.setZmd_id(Long.valueOf(zmd_id));
				ksb.getMap().put("add_date_ge", date_start);
				ksb.getMap().put("add_date_le", date_end);
				List<KonkaXxSellBill> entityListForMoney = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillDeptPdSellMoneySumList(ksb);
				List<KonkaXxSellBill> entityListForNum = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillDeptPdSellNumSumList(ksb);
				request.setAttribute("entityListForMoney", entityListForMoney);
				request.setAttribute("entityListForNum", entityListForNum);
				request.setAttribute("dept_id", kDept.getDept_id());
				request.setAttribute("zmd_id", zmd_id);
			} 
		}

		// 专卖店用户
		if (role_id_eq_400) {
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(ui.getId());
			konkaXxZmdUsers.getRow().setCount(2);
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
				String m = getMessage(request, "konka.zmd.userinfo.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}
			if (konkaXxZmdUsersList.size() == 2) {
				String m = getMessage(request, "konka.zmd.userinfo.too.many");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}
			// 查询专卖店信息
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.getRow().setCount(2);
			konkaXxZmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
			if (1 != konkaXxZmdList.size()) {
				String msg = super.getMessage(request, "konka.zmd.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			
			// 选择了查询条件进行查询
			if(StringUtils.isNotBlank(date_start) && StringUtils.isNotBlank(date_end)){
				KonkaXxSellBill ksb = new KonkaXxSellBill();
				ksb.setDept_id(konkaXxZmdList.get(0).getDept_id());
				ksb.setZmd_id(konkaXxZmdList.get(0).getZmd_id());
				ksb.getMap().put("add_date_ge", date_start);
				ksb.getMap().put("add_date_le", date_end);
				List<KonkaXxSellBill> entityListForMoney = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillDeptPdSellMoneySumList(ksb);
				List<KonkaXxSellBill> entityListForNum = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillDeptPdSellNumSumList(ksb);
				request.setAttribute("entityListForMoney", entityListForMoney);
				request.setAttribute("entityListForNum", entityListForNum);
				request.setAttribute("dept_id", konkaXxZmdList.get(0).getDept_id());
				request.setAttribute("zmd_id", konkaXxZmdList.get(0).getZmd_id());
			} 
		}	
				
		return mapping.findForward("view");
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);
		
		KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
		// 取用户角色并判断是不是分公司级别的角色
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		//request.setAttribute("peRoleUser", peRoleUser);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_eq_400 = false;
		Boolean role_id_btw_200_300 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if(temp.getRole_id()==400L){
					role_id_eq_400 = true;
				}
				if (temp.getRole_id() >= 200 && temp.getRole_id() < 300) {
					role_id_btw_200_300 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}
		
		request.setAttribute("role_id_btw_200_300", role_id_btw_200_300);
		request.setAttribute("role_id_btw_300_400", role_id_btw_300_400);
		request.setAttribute("role_id_eq_400", role_id_eq_400);
		
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 总部角色登陆查询分公司
		if (role_id_btw_200_300) {
			// 查询分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setPar_id(0L);
			konkaDept.setDept_type(3);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);

			request.setAttribute("konkaDeptList", konkaDeptList);
		}

		// 分公司角色登陆查询分公司
		if (role_id_btw_300_400) {
			// 查询专卖店
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setDept_id(kDept.getDept_id());
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);

			request.setAttribute("zmdList", zmdList);

			// 查询分公司产品信息
			KonkaXxPd konkaXxPd = new KonkaXxPd();
			konkaXxPd.setDept_id(kDept.getDept_id());
			List<KonkaXxPd> konkaXxPdList = getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);

			request.setAttribute("konkaXxPdList", konkaXxPdList);
		}

		// 专卖店用户
		if (role_id_eq_400) {
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(ui.getId());
			konkaXxZmdUsers.getRow().setCount(2);
			List<KonkaXxZmdUsers> konkaXxZmdUsersList = getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
				String m = getMessage(request, "konka.zmd.userinfo.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}
			if (konkaXxZmdUsersList.size() == 2) {
				String m = getMessage(request, "konka.zmd.userinfo.too.many");
				super.renderJavaScript(response, "window.onload=function(){alert('" + m + "');history.back();}");
				return null;
			}
			// 查询专卖店信息
			KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
			konkaXxZmd.getRow().setCount(2);
			konkaXxZmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			List<KonkaXxZmd> konkaXxZmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(konkaXxZmd);
			if (1 != konkaXxZmdList.size()) {
				String msg = super.getMessage(request, "konka.zmd.missing");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 查询专卖店所属分公司产品信息
			KonkaXxPd konkaXxPd = new KonkaXxPd();
			konkaXxPd.setDept_id(konkaXxZmdList.get(0).getDept_id());
			List<KonkaXxPd> konkaXxPdList = getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);

			request.setAttribute("konkaXxPdList", konkaXxPdList);
			request.setAttribute("zmd_id", konkaXxZmdList.get(0).getZmd_id());
		}
		
		return mapping.findForward("list");
	}

	public ActionForward ajaxGetZmdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		JSONObject result = new JSONObject();
		result.put("result", "false");
		if (StringUtils.isNotBlank(dept_id)) {
			// 查询分公司下的专卖店
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setDept_id(Long.valueOf(dept_id));
			List<KonkaXxZmd> zmdList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdList(zmd);
			StringBuffer sb = new StringBuffer();
			for (KonkaXxZmd konkaXxZmd : zmdList) {
				sb.append(konkaXxZmd.getZmd_id()).append(",").append(konkaXxZmd.getZmd_sn()).append("#");
			}
			if (sb.length() > 2) {
				sb.delete(sb.length() - 1, sb.length());
			}
			result.put("zmd_list", sb.toString());

			// 查询分公司的产品
			KonkaXxPd konkaXxPd = new KonkaXxPd();
			konkaXxPd.setDept_id(Long.valueOf(dept_id));
			List<KonkaXxPd> konkaXxPdList = getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
			StringBuffer sb_pd = new StringBuffer();
			for (KonkaXxPd konkaXxPd2 : konkaXxPdList) {
				sb_pd.append(konkaXxPd2.getMd_name()).append(",").append(konkaXxPd2.getMd_name()).append("#");
			}
			if (sb_pd.length() > 2) {
				sb_pd.delete(sb_pd.length() - 1, sb_pd.length());
			}
			result.put("konka_xx_pd_list", sb_pd.toString());

			result.put("result", "true");
		}

		logger.info("json is {}", result.toString());
		super.render(response, result.toString(), "text/html;charset=UTF-8");
		return null;
	}
	
	public ActionForward pie3DForNum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String md_name = (String) dynaBean.get("md_name");
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		
		KonkaXxSellBill ksb = new KonkaXxSellBill();
		ksb.setSell_state(70L);
		if (GenericValidator.isLong(dept_id))
			ksb.setDept_id(Long.valueOf(dept_id));
		if (GenericValidator.isLong(zmd_id))
			ksb.setZmd_id(Long.valueOf(zmd_id));
		if (StringUtils.isNotBlank(date_start))
			ksb.getMap().put("add_date_ge", date_start);
		if (StringUtils.isNotBlank(date_end))
			ksb.getMap().put("add_date_le", date_end);

		// 查询总的销售数
		Long all_num_sum = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillNumSum(ksb);
		if(null == all_num_sum)
			all_num_sum = 0L;
		//设置产品型号查询条件
		ksb.getMap().put("md_name", md_name);
		// 查询具体型号的销售数
		Long num_sum = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillNumSum(ksb);
		if(null == num_sum)
			num_sum = 0L;
		
		// 开始封装饼状图
		BaseChart baseChart_num = new BaseChart();
		baseChart_num.setLabel(md_name + "销售数");
		baseChart_num.setValue(num_sum.toString());
		baseChart_num.setIs_sliced(0);
		baseChartList.add(baseChart_num);
		
		BaseChart baseChart_all_num = new BaseChart();
		baseChart_all_num.setLabel("其它销售数");
		baseChart_all_num.setValue(String.valueOf(all_num_sum - num_sum));
		baseChart_all_num.setIs_sliced(0);
		baseChartList.add(baseChart_all_num);
		// 封装饼状图结束
		
		// 主标题
		StringBuffer caption = new StringBuffer();
		caption.append(md_name).append("，").append("销售数（台）");
		// 副标题
		StringBuffer unit = new StringBuffer();
		unit.append(date_start).append("至").append(date_end).append("{br}");
		unit.append(md_name).append("销售数量：").append(baseChart_num.getValue()).append("，");
		unit.append("全部产品销售数：").append(all_num_sum.toString());
		unit.append("{br}");
		unit.append("销售数量占比:").append(new BigDecimal("100").multiply((new BigDecimal(baseChart_num.getValue()).divide(new BigDecimal(all_num_sum.toString()),4,BigDecimal.ROUND_HALF_UP))).doubleValue()).append("%");
		model.put("baseChartList", baseChartList);
		model.put("exportFileName", md_name + "_sales_accounted");
		model.put("caption", caption.toString());
		model.put("unit", unit.toString());
		model.put("numberPrefix", ""); // 格式化数字精度
		model.put("ctx", super.getCtxPath(request));

		String xmlData = getFacade().getTemplateService().getContent("fusioncharts/Pie3D.ftl", model);
		renderXmlWithEncoding(response, xmlData, "GBK");
		return null;
	}
	
	public ActionForward pie3DForMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String zmd_id = (String) dynaBean.get("zmd_id");
		String md_name = (String) dynaBean.get("md_name");
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		
		KonkaXxSellBill ksb = new KonkaXxSellBill();
		ksb.setSell_state(70L);
		if (GenericValidator.isLong(dept_id))
			ksb.setDept_id(Long.valueOf(dept_id));
		if (GenericValidator.isLong(zmd_id))
			ksb.setZmd_id(Long.valueOf(zmd_id));
		if (StringUtils.isNotBlank(date_start))
			ksb.getMap().put("add_date_ge", date_start);
		if (StringUtils.isNotBlank(date_end))
			ksb.getMap().put("add_date_le", date_end);

		// 查询总的销售额
		KonkaXxSellBill ksb1 = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillMoneySum(ksb);
		BigDecimal all_money_sum = (BigDecimal) ksb1.getMap().get("sell_money_sum");
		if(null == all_money_sum)
			all_money_sum = new BigDecimal("0");
		
		//设置产品型号查询条件
		ksb.getMap().put("md_name", md_name);
		// 查询具体型号的销售额
		KonkaXxSellBill ksb2 = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBillMoneySum(ksb);
		BigDecimal money_sum = (BigDecimal) ksb2.getMap().get("sell_money_sum");
		if (null == money_sum)
			money_sum = new BigDecimal("0");
		
		// 开始封装饼状图
		BaseChart baseChart_num = new BaseChart();
		baseChart_num.setLabel(md_name + "销售额");
		baseChart_num.setValue(money_sum.toString());
		baseChart_num.setIs_sliced(0);
		baseChartList.add(baseChart_num);
		
		BaseChart baseChart_all_num = new BaseChart();
		baseChart_all_num.setLabel("其它销售额");
		baseChart_all_num.setValue(all_money_sum.subtract(money_sum).toString());
		baseChart_all_num.setIs_sliced(0);
		baseChartList.add(baseChart_all_num);
		// 封装饼状图结束
		
		// 主标题
		StringBuffer caption = new StringBuffer();
		caption.append(md_name).append("，").append("销售额（元）");
		// 副标题
		StringBuffer unit = new StringBuffer();
		unit.append(date_start).append("至").append(date_end).append("{br}");
		unit.append(md_name).append("销售数额：").append(baseChart_num.getValue()).append("，");
		unit.append("全部产品销售额：").append(all_money_sum.toString());
		unit.append("{br}");
		if ("0".equalsIgnoreCase(all_money_sum.toString())) {
			all_money_sum  = new BigDecimal("1");
		} 
		unit.append("销售数额占比:").append(new BigDecimal("100").multiply((new BigDecimal(baseChart_num.getValue()).divide(new BigDecimal(all_money_sum.toString()),4,BigDecimal.ROUND_HALF_UP))).doubleValue()).append("%");
		
		model.put("baseChartList", baseChartList);
		model.put("exportFileName", md_name + "_sales_accounted");
		model.put("caption", caption.toString());
		model.put("formatNumberScale", "2"); // 格式化数字精度
		model.put("unit", unit.toString());
		model.put("ctx", super.getCtxPath(request));

		String xmlData = getFacade().getTemplateService().getContent("fusioncharts/Pie3D.ftl", model);
		renderXmlWithEncoding(response, xmlData, "GBK");
		return null;
	}
}
