package com.ebiz.mmt.web.struts.jxc;

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

import com.ebiz.mmt.domain.KonkaDeptPdLink;
import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStockBill;
import com.ebiz.mmt.domain.KonkaJxcStockBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Ka
 * @version 2010-11-8
 */
public class KonkaJxcReceiveStockConfirmAction extends BaseJxcAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser user = super.getSessionUserInfo(request);
		Pager pager = (Pager) dynaBean.get("pager");
		if (null == user) {
			return null;
		}
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
		String is_confirm = (String) dynaBean.get("is_confirm");
		if (add_date_gt == null) {
			add_date_gt = firstday;
		}
		if (add_date_lt == null) {
			add_date_lt = today;
		}
		dynaBean.set("add_date_gt", add_date_gt);
		dynaBean.set("add_date_lt", add_date_lt);

		KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
		super.copyProperties(konkaJxcFhBill, form);
		if (null == is_confirm) {
			konkaJxcFhBill.setIs_confirm(0);
			dynaBean.set("is_confirm", "0");
		}
		konkaJxcFhBill.getMap().put("start_date", add_date_gt);
		konkaJxcFhBill.getMap().put("end_date", add_date_lt);
		konkaJxcFhBill.setIs_del(0);
		konkaJxcFhBill.getMap().put("sh_dept_id", user.getDept_id());// 管理端收货确认：根据部门查询，发货记录内存储的为收货部门的ID
		Long recordCount = getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBillCount(konkaJxcFhBill);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		konkaJxcFhBill.getRow().setFirst(pager.getFirstRow());
		konkaJxcFhBill.getRow().setCount(pager.getRowCount());
		List<KonkaJxcFhBill> konkaJxcFhBillList = super.getFacade().getKonkaJxcFhBillService()
				.getKonkaJxcFhBillPaginatedList(konkaJxcFhBill);

		request.setAttribute("konkaJxcFhBillList", konkaJxcFhBillList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcFhBill entity = new KonkaJxcFhBill();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBill(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			super.copyProperties(form, entity);
			// 取发货登记明细list
			// 回显 发布对象信息
			KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
			konkaJxcFhBillDetails.setFh_bill_id(entity.getId());
			konkaJxcFhBillDetails.getMap().put("is_fenjingban", "true");

			List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = super.getFacade().getKonkaJxcFhBillDetailsService()
					.getKonkaJxcFhBillDetailsWithNamesList(konkaJxcFhBillDetails);
			request.setAttribute("konkaJxcFhBillDetailsList", konkaJxcFhBillDetailsList);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		PeProdUser user = super.getSessionUserInfo(request);

		if (GenericValidator.isLong(id)) {
			KonkaJxcFhBill entity = new KonkaJxcFhBill();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBill(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			// 接收仓库（根据部门ID有权限的仓库）
			KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
			store.setAdd_dept_id(user.getDept_id());
			store.setIs_del(0);
			List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(
					store);
			request.setAttribute("storeList", storeList);

			super.copyProperties(form, entity);
			// 取发货登记明细list
			// 回显 发布对象信息
			KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
			konkaJxcFhBillDetails.setFh_bill_id(entity.getId());
			konkaJxcFhBillDetails.getMap().put("is_fenjingban", "true");

			List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = super.getFacade().getKonkaJxcFhBillDetailsService()
					.getKonkaJxcFhBillDetailsWithNamesList(konkaJxcFhBillDetails);
			request.setAttribute("konkaJxcFhBillDetailsList", konkaJxcFhBillDetailsList);
			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	/** 收货确认，检查部门对应的仓库内产品型号是否存在：存在更新其库存状态，不存在插入库存状态 */
	public ActionForward saveConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String[] fh_bill_detail_ids = request.getParameterValues("fh_bill_detail_id");// 所有发货明细ID
		// String[] prices = request.getParameterValues("price");// 所有明细对应的进货单价
		// String[] counts = request.getParameterValues("fh_bill_detail_count");// 所有明细对应的进货数量
		String[] storeIds = request.getParameterValues("store_id");// 所有明细对应的收货仓库
		PeProdUser user = super.getSessionUserInfo(request);
		PeRoleInfo role = super.getPeRoleInfoByUserId(user.getId());

		if (GenericValidator.isLong(id)) {
			KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
			konkaJxcFhBill.setId(Long.valueOf(id));
			konkaJxcFhBill = getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBill(konkaJxcFhBill);
			konkaJxcFhBill.setIs_confirm(1);// 更新发货单为已确认

			KonkaJxcStockBill konkaJxcStockBill = new KonkaJxcStockBill();
			konkaJxcStockBill.setAdd_date(new Date());
			konkaJxcFhBill.setConfirm_date(konkaJxcStockBill.getAdd_date());// 更新发货单确认时间
			konkaJxcStockBill.setKonkaJxcFhBill(konkaJxcFhBill);// 放置发货单（设置了已确认）
			String sn = "JH" + DateFormatUtils.format(konkaJxcStockBill.getAdd_date(), "yyyyMMddHHmmssSSS");// 生成固定格式的进货单编号
			konkaJxcStockBill.setSn(sn);
			konkaJxcStockBill.setJh_sum_count(konkaJxcFhBill.getFh_sum_count());
			konkaJxcStockBill.setMoney_must(konkaJxcFhBill.getMoney_must());
			konkaJxcStockBill.setMoney_result(konkaJxcFhBill.getMoney_result());
			konkaJxcStockBill.setPart_dept_id(user.getPar_dept_id());
			konkaJxcStockBill.setRemark(konkaJxcFhBill.getRemark());
			konkaJxcStockBill.setIs_del(0);
			konkaJxcStockBill.setAdd_user_type(role.getRole_id());
			konkaJxcStockBill.setAdd_user_id(user.getId());
			konkaJxcStockBill.setAdd_user_name(user.getUser_name());
			konkaJxcStockBill.setAdd_dept_id(user.getDept_id());
			konkaJxcStockBill.setAdd_dept_name(super.getKonkaDeptById(user.getDept_id()).getDept_name());
			konkaJxcStockBill.setStock_src(2);//进货来源：收货确认
			konkaJxcStockBill.setFh_sn(konkaJxcFhBill.getSn());//对应的发货单号

			List<KonkaJxcStockBillDetails> konkaJxcStockBillDetailsList = new ArrayList<KonkaJxcStockBillDetails>();
			if (null != fh_bill_detail_ids && fh_bill_detail_ids.length > 0) {
				for (int i = 0; i < fh_bill_detail_ids.length; i++) {
					KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
					konkaJxcFhBillDetails.setId(Long.valueOf(fh_bill_detail_ids[i]));
					konkaJxcFhBillDetails = super.getFacade().getKonkaJxcFhBillDetailsService()
							.getKonkaJxcFhBillDetails(konkaJxcFhBillDetails);
					if (null != konkaJxcFhBillDetails) {
						// 验证发货单内发来的产品是否已授权给收货部门
						KonkaDeptPdLink konkaDeptPdLink = super.getKonkaDeptPdLinkByDeptIdClsIdAndPdId(user
								.getDept_id(), konkaJxcFhBillDetails.getPd_type_id(), konkaJxcFhBillDetails.getPd_id());
						if (null == konkaDeptPdLink) {
							String msg = "【" + konkaJxcFhBillDetails.getPd_name()
									+ "】该型号产品没有授权给所在的部门，因此无法收货确认，请联系你的上级部门予以授权！";
							super.renderJavaScript(response, "alert('" + msg + "');history.back();");
							return null;
						}
						KonkaJxcStockBillDetails konkaJxcStockBillDetails = new KonkaJxcStockBillDetails();
						konkaJxcStockBillDetails.setPd_type_id(konkaJxcFhBillDetails.getPd_type_id());
						konkaJxcStockBillDetails.setPd_type_name(konkaJxcFhBillDetails.getPd_type_name());
						konkaJxcStockBillDetails.setBrand_id(konkaJxcFhBillDetails.getBrand_id());
						konkaJxcStockBillDetails.setBrand_name(konkaJxcFhBillDetails.getBrand_name());
						konkaJxcStockBillDetails.setPd_id(konkaJxcFhBillDetails.getPd_id());
						konkaJxcStockBillDetails.setPd_name(konkaJxcFhBillDetails.getPd_name());
						konkaJxcStockBillDetails.setCount(konkaJxcFhBillDetails.getCount());
						konkaJxcStockBillDetails.setPrice(konkaJxcFhBillDetails.getPrice());
						konkaJxcStockBillDetails.setStore_id(Long.valueOf(storeIds[i]));
						konkaJxcStockBillDetails.getMap().put("storeName",
								super.getKonkaStockById(Long.valueOf(storeIds[i])).getStore_name());
						konkaJxcStockBillDetails.setAdd_user_id(user.getId());
						konkaJxcStockBillDetails.setAdd_dept_id(user.getDept_id());
						konkaJxcStockBillDetails.setAdd_date(konkaJxcStockBill.getAdd_date());
						konkaJxcStockBillDetails.setIs_pc(0);
						konkaJxcStockBillDetails.setRemark(konkaJxcFhBillDetails.getRemark());
						konkaJxcStockBillDetailsList.add(konkaJxcStockBillDetails);
					}
				}
				konkaJxcStockBill.setKonkaJxcStockBillDetailsList(konkaJxcStockBillDetailsList);
			}
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
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

}
