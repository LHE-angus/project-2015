package com.ebiz.mmt.web.struts.jxc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcStockBill;
import com.ebiz.mmt.domain.KonkaJxcStockBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Ka
 * @version 2010-10-17
 */
public class KonkaJxcStockBillAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// String pd_type_id = (String) dynaBean.get("pd_type_id");
		String sn_like = (String) dynaBean.get("sn_like");

		PeProdUser user = super.getSessionUserInfo(request);
		Pager pager = (Pager) dynaBean.get("pager");
		if (null == user) {
			return null;
		}

		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());
		// if(role.getRole_id().intValue() == Constants.ROLE_ID_SYB){// 事业部
		// List<BasePdClazz> basePdClassList = super.getBaseAllPdClazzList();
		// request.setAttribute("basePdClassList", basePdClassList);
		// } else {
		// // 产品类型（选择条件中授权的大类）
		// BasePdClazz basePdClass = new BasePdClazz();
		// basePdClass.setIs_del(0);
		// basePdClass.getMap().put("getOwnDeptCls", "ture");
		// basePdClass.getMap().put("dept_id", user.getDept_id());
		// List<BasePdClazz> basePdClassList =
		// super.getFacade().getBasePdClazzService().getBasePdClazzList(basePdClass);
		// request.setAttribute("basePdClassList", basePdClassList);
		// }

		// 初始化列表页面的起始时间
		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd").format(f.getTime());
		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		String add_date_gt = (String) dynaBean.get("add_date_gt");
		String add_date_lt = (String) dynaBean.get("add_date_lt");
		if (add_date_gt == null) {
			add_date_gt = firstday;
		}
		if (add_date_lt == null) {
			add_date_lt = today;
		}
		request.setAttribute("add_date_lt", add_date_lt);
		request.setAttribute("add_date_gt", add_date_gt);
		// request.setAttribute("brand_id", Constants.KONKA_BRAND_ID);康佳品牌

		KonkaJxcStockBill konkaJxcStockBill = new KonkaJxcStockBill();
		konkaJxcStockBill.setAdd_dept_id(user.getDept_id());// 添加人部门，限制给角色只能看自己部门的记录
		konkaJxcStockBill.getMap().put("par_dept_id", user.getPar_dept_id());
		// if (StringUtils.isNotBlank(pd_type_id)) {
		// konkaJxcStockBill.getMap().put("pd_type_id", pd_type_id);// 产品大类ID
		// }
		konkaJxcStockBill.getMap().put("brand_id", (Constants.KONKA_BRAND_ID));// 康佳品牌ID
		// if (StringUtils.isNotBlank(pd_name_like)) {
		// konkaJxcStockBill.getMap().put("pd_name_like", pd_name_like);// 产品型号名称
		// }
		konkaJxcStockBill.getMap().put("add_date_gt", add_date_gt);// 开始日期
		konkaJxcStockBill.getMap().put("add_date_lt", add_date_lt);// 结束日期
		konkaJxcStockBill.getMap().put("sn_like", sn_like);

		Long count = getFacade().getKonkaJxcStockBillService().getKonkaJxcStockBillCount(konkaJxcStockBill);
		pager.init(count, 10, pager.getRequestPage());
		konkaJxcStockBill.getRow().setFirst(pager.getFirstRow());
		konkaJxcStockBill.getRow().setCount(pager.getRowCount());
		List<KonkaJxcStockBill> konkaJxcStockBilllist = getFacade().getKonkaJxcStockBillService()
				.getKonkaJxcStockBillWithDetailsPaginatedList(konkaJxcStockBill);

		request.setAttribute("roleId", role.getRole_id());// 20:事业部 30:分公司（事业部用户，隐藏列表的上级部门）
		request.setAttribute("konkaJxcStockBilllist", konkaJxcStockBilllist);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		

		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());
		if (role.getRole_id().intValue() >= Constants.ROLE_ID_SYB
				&& role.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// 事业部
			List<BasePdClazz> basePdClassList = super.getBaseAllPdClazzList();
			request.setAttribute("basePdClassList", basePdClassList);
		} else {
			// 产品类型（选择条件中授权的大类）
			BasePdClazz basePdClass = new BasePdClazz();
			basePdClass.setIs_del(0);
			basePdClass.getMap().put("getOwnDeptCls", "ture");
			basePdClass.getMap().put("dept_id", user.getDept_id());
			List<BasePdClazz> basePdClassList = super.getFacade().getBasePdClazzService().getBasePdClazzList(
					basePdClass);
			request.setAttribute("basePdClassList", basePdClassList);
		}

		// 进入仓库
		KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
		store.setAdd_dept_id(user.getDept_id());
		store.setIs_del(0);
		List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(store);
		request.setAttribute("storeList", storeList);

		request.setAttribute("roleId", role.getRole_id());// 20:事业部 30:分公司
		if (role.getRole_id().intValue() >= Constants.ROLE_ID_FGS
				&& role.getRole_id().intValue() < Constants.ROLE_ID_JYB) {// 分公司
			// 上级部门
			// PeDept peDept = new PeDept();
			KonkaDept peDept = new KonkaDept();
			peDept.setDept_id(user.getPar_dept_id());
			peDept = getFacade().getKonkaDeptService().getKonkaDept(peDept);// getFacade().getPeDeptService().getPeDept(peDept);
			if (null != peDept) {
				request.setAttribute("peDept", peDept);
			}
		}

		dynaBean.set("sn", "JH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 生成固定格式的进货单编号

		Date today = new Date();
		// request.setAttribute("today", DateFormatUtils.format(today, "yyyy-MM-dd"));
		dynaBean.set("today", DateFormatUtils.format(today, "yyyy-MM-dd"));
		request.setAttribute("add", true);
		return mapping.findForward("input");
	}

	/** 产品型号的大类ID一律从产品型号表查，不从页面取得 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		Long jh_sum_count = 0L;// 进货总数

		// 获取产品明细
		String[] store_ids = request.getParameterValues("store_id");// 进货仓库
		// String[] cls_ids = request.getParameterValues("cls_id");// 产品类型id
		String[] pd_ids = request.getParameterValues("pd_id");// 产品型号id
		String[] counts = request.getParameterValues("count");// 进货数量
		String[] prices = request.getParameterValues("price");// 进货单价
		String[] remarks = request.getParameterValues("remark");// 备注
		String money_must = (String) dynaBean.get("money_must");// 应付金额
		String money_result = (String) dynaBean.get("money_result");// 实付金额

		KonkaJxcStockBill konkaJxcStockBill = new KonkaJxcStockBill();
		// String pd_type_id = (String) dynaBean.get("pd_type_id");
		// String pd_id = (String) dynaBean.get("pd_id");
		// String store_id = (String) dynaBean.get("store_id");
		// String remark = (String) dynaBean.get("remark");

		// PePdModel pePdModel = new PePdModel();
		// pePdModel.setPd_id(Long.valueOf(pd_id));
		// pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);//型号的信息
		//
		// // 如果数量是负的，验证输入的退货数量 库存是否充足
		// KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
		// if (StringUtils.isNotBlank(store_id)) {
		// if (Long.valueOf(count).intValue() < 0) {// 数量为负时，验证退货库存是否充足
		// konkaJxcStoreState.setStore_id(Long.valueOf(store_id));
		// konkaJxcStoreState.setDept_id(user.getDept_id());
		// //konkaJxcStoreState.setPd_type_id(Long.valueOf(pd_type_id));
		// //konkaJxcStoreState.setBrand_id(Long.valueOf(Constants.KONKA_BRAND_ID));// 康佳品牌ID
		// konkaJxcStoreState.setPd_id(Long.valueOf(pd_id));
		// konkaJxcStoreState = getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(
		// konkaJxcStoreState);
		// if (null != konkaJxcStoreState) {
		// if (konkaJxcStoreState.getPd_num().intValue() + Long.valueOf(count).intValue() < 0) {
		// String msg = "库存不足：该型号产品，在您选择的仓库库存中实时库存数量【";
		// super.renderJavaScript(response, "alert('" + msg + konkaJxcStoreState.getPd_num()
		// + "】，请重新输入！');history.back();");
		// return null;
		// }
		// } else {
		// String msg = "库存不足：该型号产品，在您选择的仓库库存中无记录，请重新输入！";
		// super.renderJavaScript(response, "alert('" + msg + "');history.back();");
		// return null;
		// }
		// }
		// } else {
		// String msg = "请选择一个仓库!";
		// super.renderJavaScript(response, "alert('" + msg + "');history.back();");
		// return null;
		// }

		super.copyProperties(konkaJxcStockBill, dynaBean);

		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());

		konkaJxcStockBill.setAdd_user_name(user.getUser_name());
		konkaJxcStockBill.setAdd_user_id(user.getId());
		konkaJxcStockBill.setAdd_user_type(role.getRole_id());
		konkaJxcStockBill.setAdd_dept_id(user.getDept_id());
		konkaJxcStockBill.setAdd_dept_name(super.getKonkaDeptById(user.getDept_id()).getDept_name());
		konkaJxcStockBill.setMoney_must(new BigDecimal(money_must));// 应付金额
		konkaJxcStockBill.setMoney_result(new BigDecimal(money_result));// 实付金额
		konkaJxcStockBill.setStock_src(1);// 进货来源：进货

		List<KonkaJxcStockBillDetails> konkaJxcStockBillDetailsList = new ArrayList<KonkaJxcStockBillDetails>();
		if (null != store_ids && store_ids.length > 0) {
			for (int i = 1; i < store_ids.length; i++) {
				KonkaJxcStockBillDetails konkaJxcStockBillDetails = new KonkaJxcStockBillDetails();
				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(Long.valueOf(pd_ids[i]));
				pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);// 型号的信息

				// 如果数量是负的，验证输入的退货数量 库存是否充足
				KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
				if (Long.valueOf(counts[i]).intValue() < 0) {// 数量为负时，验证退货库存是否充足
					konkaJxcStoreState.setStore_id(Long.valueOf(store_ids[i]));
					konkaJxcStoreState.setDept_id(user.getDept_id());
					konkaJxcStoreState.setPd_id(Long.valueOf(pd_ids[i]));
					konkaJxcStoreState = getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(
							konkaJxcStoreState);
					if (null != konkaJxcStoreState) {
						if (konkaJxcStoreState.getPd_num().intValue() + Long.valueOf(counts[i]).intValue() < 0) {
							String msg = "库存不足：该型号产品，在您选择的仓库库存中实时库存数量【";
							super.renderJavaScript(response, "alert('" + msg + konkaJxcStoreState.getPd_num()
									+ "】，请重新输入！');history.back();");
							return null;
						}
					}
				}

				konkaJxcStockBillDetails.setPd_type_id(pePdModel.getCls_id());// 根据型号反查大类
				konkaJxcStockBillDetails.setPd_type_name(getBasePdClazz(pePdModel.getCls_id()).getCls_name());
				konkaJxcStockBillDetails.setBrand_id((Constants.KONKA_BRAND_ID));// 康佳品牌ID
				konkaJxcStockBillDetails.setBrand_name(getBaseBrandInfo((Constants.KONKA_BRAND_ID)).getBrand_name());
				konkaJxcStockBillDetails.setPd_id(Long.valueOf(pd_ids[i]));
				konkaJxcStockBillDetails.setPd_name(getPePdModel(Long.valueOf(pd_ids[i])).getMd_name());
				konkaJxcStockBillDetails.setCount(Long.valueOf(counts[i]));// 进货数量
				konkaJxcStockBillDetails.setPrice(new BigDecimal(prices[i]));// 进货单价
				konkaJxcStockBillDetails.setStore_id(Long.valueOf(store_ids[i]));// 仓库ID
				konkaJxcStockBillDetails.getMap().put("storeName",
						super.getKonkaStockById(Long.valueOf(store_ids[i])).getStore_name());
				konkaJxcStockBillDetails.setAdd_user_id(user.getId());
				konkaJxcStockBillDetails.setAdd_dept_id(user.getDept_id());
				konkaJxcStockBillDetails.setRemark(remarks[i]);
				jh_sum_count += Long.valueOf(counts[i]);// 累加取总数
				konkaJxcStockBillDetailsList.add(konkaJxcStockBillDetails);
			}
		}

		konkaJxcStockBill.setKonkaJxcStockBillDetailsList(konkaJxcStockBillDetailsList);
		konkaJxcStockBill.setJh_sum_count(jh_sum_count);// 进货总数

		getFacade().getKonkaJxcStockBillService().createKonkaJxcStockBill(konkaJxcStockBill);
		saveMessage(request, "entity.inserted");
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(null == konkaJxcStockBill.getQueryString() ? ""
				: konkaJxcStockBill.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());// 用户角色
			request.setAttribute("roleId", role.getRole_id());// 20:事业部 30:分公司

			KonkaJxcStockBill konkaJxcStockBill = new KonkaJxcStockBill();
			konkaJxcStockBill.setId(Long.valueOf(id));
			konkaJxcStockBill = getFacade().getKonkaJxcStockBillService().getKonkaJxcStockBill(konkaJxcStockBill);
			if (null == konkaJxcStockBill) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			KonkaJxcStockBillDetails konkaJxcStockBillDetails = new KonkaJxcStockBillDetails();
			konkaJxcStockBillDetails.setStock_bill_id(konkaJxcStockBill.getId());
			List<KonkaJxcStockBillDetails> konkaJxcStockBillDetailsList = getFacade()
					.getKonkaJxcStockBillDetailsService().getKonkaJxcStockBillDetailsWithStoreInfoList(
							konkaJxcStockBillDetails);
			request.setAttribute("konkaJxcStockBillDetailsList", konkaJxcStockBillDetailsList);

			konkaJxcStockBill.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, konkaJxcStockBill);
			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			// 产品类型（选择条件中授权的大类）
			BasePdClazz basePdClass = new BasePdClazz();
			basePdClass.setIs_del(0);
			basePdClass.getMap().put("getOwnDeptCls", "ture");
			basePdClass.getMap().put("dept_id", user.getDept_id());
			List<BasePdClazz> basePdClassList = super.getFacade().getBasePdClazzService().getBasePdClazzList(
					basePdClass);
			request.setAttribute("basePdClassList", basePdClassList);

			// 进入仓库
			KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
			store.setAdd_dept_id(user.getDept_id());
			store.setIs_del(0);
			List<KonkaJxcStoreInfo> storeList = super.getFacade().getKonkaJxcStoreInfoService()
					.getKonkaJxcStoreInfoList(store);
			request.setAttribute("storeList", storeList);

			KonkaJxcStockBill konkaJxcStockBill = new KonkaJxcStockBill();
			konkaJxcStockBill.setId(Long.valueOf(id));
			konkaJxcStockBill = getFacade().getKonkaJxcStockBillService().getKonkaJxcStockBill(konkaJxcStockBill);
			if (null == konkaJxcStockBill) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());// 用户角色
			request.setAttribute("roleId", role.getRole_id());// 20:事业部 30:分公司
			Date today = new Date();
			request.setAttribute("today", DateFormatUtils.format(today, "yyyy-MM-dd"));
			konkaJxcStockBill.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, konkaJxcStockBill);
			request.setAttribute("update", true);
			return mapping.findForward("input");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
	}

}
