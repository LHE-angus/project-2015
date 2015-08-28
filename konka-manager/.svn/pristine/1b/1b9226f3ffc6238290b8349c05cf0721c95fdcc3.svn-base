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
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.Call_Get_KCXX;
import com.ebiz.mmt.r3.KCXX;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Hu,Hao
 * @version 2013-10-09
 */
public class KonkaR3YjKcSearhAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		// 工厂(需要和分公司绑定) not null
		String zwerks = (String) dynaBean.get("fac_sn");
		// 仓位 仓库地点 not null
		String zlgort = (String) dynaBean.get("store_sn");
		// 物料
		String zmatnr = (String) dynaBean.get("zmatnr");

		// 物料
		String r3_code = (String) dynaBean.get("r3_code");

		String dept_name = (String) dynaBean.get("dept_name");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);

		// 取用户角色并判断是不是分公司级别的角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		Boolean role_id_gt_30_btw_200_300 = false;
		// Boolean role_id_eq_60 = false;
		// Boolean role_id_lt_30_60_lt_300 = false;
		for (PeRoleUser temp : peRoleUserList) {
			if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
				role_id_gt_30_btw_200_300 = true;
			// if (temp.getRole_id() == 60)
			// role_id_eq_60 = true;
			// if ((temp.getRole_id() >= 30 && temp.getRole_id() < 60) ||
			// temp.getRole_id() >= 400)
			// role_id_lt_30_60_lt_300 = true;
		}

		KonkaR3DeptStore kDeptStore = new KonkaR3DeptStore();
		if (!role_id_gt_30_btw_200_300) {
			KonkaDept kdp = new KonkaDept();
			kdp = getKonkaDeptForFgs(ui.getDept_id());
			if (null != kdp && null != kdp.getDept_name()) {
				kDeptStore.setDept_name(kdp.getDept_name());
				dept_name = kdp.getDept_name();
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		kDeptStore.setIs_del(0);
		
		List<KCXX> entitylist = new ArrayList<KCXX>();
		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort) && StringUtils.isNotBlank(r3_code)) {
			entitylist = new Call_Get_KCXX().doCall(zwerks, zlgort, r3_code, zmatnr);
			request.setAttribute("count", entitylist.size());
		}

		List<KonkaR3DeptStore> kDeptStoreList = super.getFacade().getKonkaR3DeptStoreService()
				.getKonkaR3DeptStoreForFgsNameList(kDeptStore);
		request.setAttribute("kDeptStoreList", kDeptStoreList);

		// PePdModel pdModel = new PePdModel();
		// pdModel.setIs_del(0);
		// List<PePdModel> pdModelList =
		// super.getFacade().getPePdModelService().getPePdModelList(pdModel);
		// request.setAttribute("pdModelList", pdModelList);

		request.setAttribute("entitylist", entitylist);

		dynaBean.set("dept_name", dept_name);

		// 根据用户角色处理R3客户
		// KonkaR3Shop kR3Shop = new KonkaR3Shop();
		// if (role_id_gt_30_btw_200_300) {
		// } else if (!role_id_gt_30_btw_200_300 && role_id_lt_30_60_lt_300) {
		// kR3Shop.getMap().put("fgs_user_id", ui.getId());
		// kR3Shop.getMap().put("cur_dept_id", ui.getDept_id());
		// } else if (!role_id_gt_30_btw_200_300 && !role_id_lt_30_60_lt_300 &&
		// role_id_eq_60) {
		// kR3Shop.getMap().put("ywy_id", ui.getId());
		// } else {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }
		// List<KonkaR3Shop> kR3ShopsList =
		// super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForUserIdList(kR3Shop);
		// request.setAttribute("kR3ShopsList", kR3ShopsList);

		return mapping.findForward("list");
	}

	/*
	 * 工厂
	 */
	public ActionForward getFacSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// subcomp_id
		DynaBean dynaBean = (DynaBean) form;
		String dept_name = (String) dynaBean.get("dept_name");
		if (StringUtils.isNotEmpty(dept_name)) {
			List<String> dataList = new ArrayList<String>();
			KonkaR3DeptStore t = new KonkaR3DeptStore();
			t.setDept_name(dept_name);
			t.setIs_del(0);
			List<KonkaR3DeptStore> tList = super.getFacade().getKonkaR3DeptStoreService()
					.getKonkaR3DeptStoreForFacSnList(t);
			for (KonkaR3DeptStore _t : tList) {
				if (null != _t.getMap().get("t_fac_sn"))
					dataList.add(StringUtils
							.join(new String[] { "[\"", _t.getMap().get("t_fac_sn").toString(), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}

	/*
	 * 仓库
	 */
	public ActionForward getStore_sn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String fac_sn = (String) dynaBean.get("fac_sn");
		String dept_name = (String) dynaBean.get("dept_name");
		if (StringUtils.isNotEmpty(fac_sn) && StringUtils.isNotEmpty(dept_name)) {
			List<String> dataList = new ArrayList<String>();
			KonkaR3DeptStore t = new KonkaR3DeptStore();
			t.setFac_sn(fac_sn);
			t.setDept_name(dept_name);
			t.setIs_del(0);
			List<KonkaR3DeptStore> tList = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(t);
			for (KonkaR3DeptStore _t : tList) {
				dataList.add(StringUtils.join(new String[] { "[\"", _t.getStore_sn(), "\"]" }));
			}
			super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		}
		return null;
	}
}
