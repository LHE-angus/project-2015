package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;

/**
 * 分公司库存查询
 * 
 * @author Hu,hao
 * @version 2013-09-24
 */
public class KonkaR3DeptStoreInventoryAction extends BaseAction {
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
		String excel_to_all = (String) dynaBean.get("excel_to_all");

		// 取当前登录用户
		PeProdUser ui = super.getSessionUserInfo(request);

		// 取用户角色并判断是不是分公司级别的角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		Boolean role_id_gt_30_btw_200_300 = false;
		for (PeRoleUser temp : peRoleUserList) {
			if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
				role_id_gt_30_btw_200_300 = true;
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
		
		// 根据机型从小到大排序 LED的机型放在最前面 -Jacky  LED、OLED、LCD、LDZ、LBG
		String[] orderArr = {"LED","OLED","LCD","LDZ","LBG"};
		List<List<StockCheckResult>> orderList = new ArrayList<List<StockCheckResult>>();
		for(int i=0; i<orderArr.length; i++){
			List<StockCheckResult> tempList = new ArrayList<StockCheckResult>();
			orderList.add(tempList);
		}
		List<StockCheckResult> entityOtherlist = new ArrayList<StockCheckResult>();
		orderList.add(entityOtherlist);
		
		for(StockCheckResult tempResult: entitylist){
			// 在数组中
			boolean b = false;
			for(int i=0; i<orderArr.length; i++){
				if(tempResult.getMatnr().startsWith(orderArr[i])){
					orderList.get(i).add(tempResult);
					b = true;
					break;
				}
			}
			// 不在数组中则为其他
			if(!b){
				entityOtherlist.add(tempResult);
			}
		}
		entitylist.clear();
		// 分别排序// 分别载入
		for(int i=0; i<orderList.size(); i++){
			Collections.sort(orderList.get(i),new Comparator<StockCheckResult>(){
	            public int compare(StockCheckResult arg0, StockCheckResult arg1) {
	            	 return arg0.getMatnr().compareTo(arg1.getMatnr());
	            }
	        });
			entitylist.addAll(orderList.get(i));
		}
		
		request.setAttribute("entitylist", entitylist);
		
		if (StringUtils.isNotBlank(excel_to_all)) {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("分公司库存查询")
					+ ".xls");

			request.setAttribute("allList", entitylist);
			return new ActionForward("/admin/KonkaR3DeptStoreInventory/view.jsp");
		}

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
