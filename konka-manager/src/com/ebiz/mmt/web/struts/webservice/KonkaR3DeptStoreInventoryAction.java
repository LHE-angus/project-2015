package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

public class KonkaR3DeptStoreInventoryAction  extends BaseAction {
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

		String dept_name = (String) dynaBean.get("dept_name");

		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		
		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser userInfo = checkUserid(user_id, userpass,android_version,ios_version);
		//PeProdUser userInfo = this.checkUser("0", "p@ssword9");
          if (null==userInfo) {
        	  super.renderJson(response, "用户登录失败!");
  			return null;
		}
		// 取用户角色并判断是不是分公司级别的角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		Boolean role_id_gt_30_btw_200_300 = false;
		for (PeRoleUser temp : peRoleUserList) {
			if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
				role_id_gt_30_btw_200_300 = true;
		}

		KonkaR3DeptStore kDeptStore = new KonkaR3DeptStore();
		if (!role_id_gt_30_btw_200_300) {
			KonkaDept kdp = new KonkaDept();
			kdp = getKonkaDeptForFgs(userInfo.getDept_id());
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
		List<KonkaR3DeptStore> kDeptStoreList = super.getFacade().getKonkaR3DeptStoreService()
				.getKonkaR3DeptStoreForFgsNameList(kDeptStore);
		request.setAttribute("kDeptStoreList", kDeptStoreList);

		dynaBean.set("dept_name", dept_name);


		List<StockCheckResult> entitylist = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info = new ReturnInfo<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
			if (zmatnr != null && !"".equals(zmatnr)) {
				info = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks, zlgort, zmatnr);
				if (info.getSuccess())
					entitylist = info.getDataResult();

			} else {
				info = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks, zlgort, null);
				if (info.getSuccess())
					entitylist = info.getDataResult();
			}
		}
		request.setAttribute("entitylist", entitylist);

		request.setAttribute("user_id", user_id);
		request.setAttribute("userpass", userpass);
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
