package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3ShopCredit;
import com.ebiz.mmt.domain.KonkaSalesDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2013-07-04
 */
public class KonkaR3ShopCreditAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String kunnr_like = (String) dynaBean.get("kunnr_like");
		String vkorg = (String) dynaBean.get("vkorg");
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		
		//一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");
		
		//二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");
		
		//客户名称
		String kh_name_like = (String) dynaBean.get("kh_name_like");

		// PeRoleUser pUser = new PeRoleUser();
		// pUser.setUser_id(ui.getId());
		// List<PeRoleUser> pList =
		// super.getFacade().getPeRoleUserService().getPeRoleUserList(pUser);
		//
		// Boolean role_id_gt_30 = false;
		// Boolean role_id_btw_30_60_and_60_100 = false;
		// Boolean role_id_eq_60 = false;
		// for (PeRoleUser temp : pList) {
		// if (temp.getRole_id() < 30)
		// role_id_gt_30 = true;
		// if (temp.getRole_id() >= 30 && temp.getRole_id() < 100 &&
		// temp.getRole_id() != 60)
		// role_id_btw_30_60_and_60_100 = true;
		// if (temp.getRole_id() == 60)
		// role_id_eq_60 = true;
		// }

		KonkaR3ShopCredit entity = new KonkaR3ShopCredit();

		// if (role_id_gt_30) {
		// } else if (role_id_btw_30_60_and_60_100) {
		// entity.getMap().put("is_not_admin", true);
		// entity.getMap().put("fgs_user_id", ui.getId());
		// entity.getMap().put("cur_dept_id", ui.getDept_id());
		// } else if (role_id_eq_60) {
		// entity.getMap().put("is_not_admin", true);
		// entity.getMap().put("ywy_user_id", ui.getId());
		// } else {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }

		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		if (StringUtils.isNotBlank(kunnr_like)) {
			entity.getMap().put("kunnr_like", kunnr_like);
		}

		if (StringUtils.isNotBlank(vkorg)) {
			entity.setVkorg(vkorg);
		}
		
		//添加客户类型筛选条件
		if(customer_type1!=null&&!"".equals(customer_type1)){
			entity.getMap().put("customer_type1", customer_type1);
		}
		if(customer_type2!=null&&!"".equals(customer_type2)){
			entity.getMap().put("customer_type2", customer_type2);
		}
		
		if (StringUtils.isNotBlank(kh_name_like)) {
			entity.getMap().put("kh_name_like", kh_name_like);
		}

		Long recordCount = getFacade().getKonkaR3ShopCreditService().getKonkaR3ShopCreditForRoleDataCount(entity);
		pager.init(recordCount, 10, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3ShopCredit> entityList = getFacade().getKonkaR3ShopCreditService()
		        .getKonkaR3ShopCreditForRoleDataPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		
		if (StringUtils.isNotBlank(excel_to_all)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户账期")
			        + ".xls");
			if (recordCount > 5000) {
				super.renderJavaScript(response, "window.onload=function(){alert('只能导出5000条以下数据！');history.back();}");
			}
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaR3ShopCredit> entityList1 = getFacade().getKonkaR3ShopCreditService()
			        .getKonkaR3ShopCreditForRoleDataPaginatedList(entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}

		return mapping.findForward("list");
	}

	public ActionForward tbData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String v_kkber = "KF01";
		String v_spart = "10";

		// 销售组织
		KonkaSalesDept ksd = new KonkaSalesDept();
		ksd.setIs_del(0);
		List<KonkaSalesDept> ksdList = super.getFacade().getKonkaSalesDeptService().getKonkaSalesDeptList(ksd);

		List<KonkaR3ShopCredit> entityList = new ArrayList<KonkaR3ShopCredit>();

		long count = 0;

		if (ksdList.size() > 0) {

			ReturnInfo<KHXD> info = new ReturnInfo<KHXD>();

			for (KonkaSalesDept temp : ksdList) {
				List<KHXD> khxdList = new ArrayList<KHXD>();
				info = getFacade().getR3WebInterfaceService().getKhxd(v_kkber,
				        temp.getSales_org_code(), v_spart, null);
				if (info.getSuccess() == true)
					khxdList = info.getDataResult();
				if (khxdList.size() > 0) {
					for (KHXD temp1 : khxdList) {
						KonkaR3ShopCredit konkaR3ShopCredit = new KonkaR3ShopCredit();
						konkaR3ShopCredit.setKunnr(temp1.getKUNNR());
						konkaR3ShopCredit.setCtlpc(temp1.getCTLPC());
						konkaR3ShopCredit.setDbekr(temp1.getDBEKR());
						konkaR3ShopCredit.setKlimk(temp1.getKLIMK());
						konkaR3ShopCredit.setKlprz(temp1.getKLPRZ());
						konkaR3ShopCredit.setOblig(temp1.getOBLIG());
						konkaR3ShopCredit.setSauft(temp1.getSAUFT());
						konkaR3ShopCredit.setSkfor(temp1.getSKFOR());
						konkaR3ShopCredit.setVkorg(temp1.getVKORG());
						konkaR3ShopCredit.setZlimt(temp1.getZLIMT());
						konkaR3ShopCredit.setZsyed(temp1.getZSYED());
						konkaR3ShopCredit.setUmsav(temp1.getUMSAV());
						konkaR3ShopCredit.setKlime(temp1.getKLIME());
						konkaR3ShopCredit.setKlimg(temp1.getKLIMG());
						konkaR3ShopCredit.setSyed(temp1.getSYED());
						entityList.add(konkaR3ShopCredit);
						count++;
					}
				}
			}
		}

		super.getFacade().getKonkaR3ShopCreditService().modifyKonkaR3ShopCreditForTb(entityList);

		saveMessage(request, "prodadmin.md.tb.success", new String[] { count + "" });

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}
}
