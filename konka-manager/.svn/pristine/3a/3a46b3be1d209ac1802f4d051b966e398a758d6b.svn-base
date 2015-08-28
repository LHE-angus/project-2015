package com.ebiz.mmt.web.struts.jxc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcFhBill;
import com.ebiz.mmt.domain.KonkaJxcFhBillDetails;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Zhang,XuFeng
 * @version 2011-10-10
 */
public class KonkaJxcFhBillRegisterAction extends BaseJxcAction {
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
		

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String is_confirm = (String) dynaBean.get("is_confirm");
		String data_src = (String) dynaBean.get("data_src");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		String sn_like = (String) dynaBean.get("sn_like");

		// 初始化列表页面的起始时间
		Date now = new Date();
		if (StringUtils.isBlank(start_date)) {
			start_date = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(end_date)) {
			end_date = DateFormatUtils.format(now, "yyyy-MM-dd");
		}

		PeProdUser ui = super.getSessionUserInfo(request);
		// PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());
		// if (null != peRoleInfo && null != peRoleInfo.getRole_id()) {
		// if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_FGS
		// && peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_YWY) {// ===分公司\\经营部、办事处===
		// request.setAttribute("is_fenjingban", "true");
		// }
		// }

		KonkaJxcFhBill entity = new KonkaJxcFhBill();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(start_date)) {
			entity.getMap().put("start_date", start_date);
		}
		if (StringUtils.isNotBlank(end_date)) {
			entity.getMap().put("end_date", end_date);
		}
		if (null == is_confirm) {
			entity.setIs_confirm(0);
			dynaBean.set("is_confirm", "0");
		}
		if (StringUtils.isNotBlank(data_src)) {
			dynaBean.set("data_src", data_src);
			entity.setData_src(Integer.parseInt(data_src));
		}
		entity.setAdd_user_id(ui.getId());
		entity.setAdd_dept_id(ui.getDept_id());
		entity.getMap().put("sn_like", sn_like);
		Long recordCount = getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBillCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaJxcFhBill> konkaJxcFhBillList = super.getFacade().getKonkaJxcFhBillService()
				.getKonkaJxcFhBillPaginatedList(entity);
		// // 验证盘存
		// for (KonkaJxcFhBill kjfb : konkaJxcFhBillList) {
		// boolean is_pc = false;
		// KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
		// if (null != kjfb.getId()) {
		// konkaJxcFhBillDetails.setFh_bill_id(kjfb.getId());
		// List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = getFacade().getKonkaJxcFhBillDetailsService()
		// .getKonkaJxcFhBillDetailsList(konkaJxcFhBillDetails);
		// for (KonkaJxcFhBillDetails kjfbd : konkaJxcFhBillDetailsList) {
		// if (kjfbd.getIs_pc() == 1) {
		// is_pc = true;
		// }
		// }
		// }
		// if (is_pc) {
		// kjfb.getMap().put("is_pc", "true");
		// }
		// }
		dynaBean.set("start_date", start_date);
		dynaBean.set("end_date", end_date);
		request.setAttribute("entityList", konkaJxcFhBillList);
		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 发货登记
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
		konkaJxcFhBillDetails.setFh_bill_id(new Long(id));

		// 判断角色，显示分公司还是网点
		PeProdUser ui = super.getSessionUserInfo(request);
		PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());
		if (null != peRoleInfo && null != peRoleInfo.getRole_id()) {
			if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_SYB
					&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// ===事业部===
				konkaJxcFhBillDetails.getMap().put("is_shiyebu", "true");
				request.setAttribute("is_shiyebu", "true");
			} else if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_FGS
					&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_YWY) {// ===分公司\\经营部、办事处===
				konkaJxcFhBillDetails.getMap().put("is_fenjingban", "true");
				request.setAttribute("is_fenjingban", "true");
			} else {
				String msg = "对不起只有事业部、分公司、经营部和办事处有权限添加发货登记信息!";
				renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
		}

		List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = getFacade().getKonkaJxcFhBillDetailsService()
				.getKonkaJxcFhBillDetailsWithNamesList(konkaJxcFhBillDetails);
		// 循环取每个发货登记明细，对应的库存数量===用于页面判断，修改时的数量范围===
		for (KonkaJxcFhBillDetails kjfbd : konkaJxcFhBillDetailsList) {
			// 根据【部门id】、【 仓库id】、【品类】、【 型号id】，动态取得对应的【当前库存】
			KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
			konkaJxcStoreState.setDept_id(ui.getDept_id());// 【部门id】
			konkaJxcStoreState.setStore_id(kjfbd.getStore_id());// 【 仓库id】
			konkaJxcStoreState.setPd_type_id(kjfbd.getPd_type_id());// 【品类】
			konkaJxcStoreState.setPd_id(kjfbd.getPd_id());// 【 型号id】
			konkaJxcStoreState = getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(konkaJxcStoreState);
			kjfbd.getMap().put("current_store_pd_num", konkaJxcStoreState.getPd_num());
		}

		request.setAttribute("konkaJxcFhBillDetailsList", konkaJxcFhBillDetailsList);
		dynaBean.set("fh_bill_id", id);
		dynaBean.set("mod_id", mod_id);
		dynaBean.set("queryString", super.serialize(request, "id", "mod_id"));
		return new ActionForward("/KonkaJxcFhBillRegister/audit.jsp");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = request.getParameter("mod_id");
		PeProdUser ui = super.getSessionUserInfo(request);
		PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());

		String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		dynaBean.set("fh_date", today);

		request.setAttribute("sn", "FH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));// 生成固定格式的发货单号

		// 权限控制,根据PeRoleInfo中的角色
		// 事业部发货给分公司
		// 分公司发货给网点
		// 经营部、办事处使用所属分公司的发货功能发货给网点
		List<KonkaDept> branchDeptList = new ArrayList<KonkaDept>();
		List<KonkaR3Shop> konkaR3ShopList = new ArrayList<KonkaR3Shop>();
		if (null != peRoleInfo && null != peRoleInfo.getRole_id()) {
			if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_SYB
					&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// ===事业部===
				// 选取下属【分公司】List
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setPar_id(ui.getDept_id());
				branchDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
				request.setAttribute("branchDeptList", branchDeptList);
				request.setAttribute("shiyebu", "shiyebu");

			} else if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_FGS
					&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_YWY) {// ===分公司\\经营部、办事处===
				// 管辖区域的【网点】List
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.getMap().put("konka_jxc_dept_id", ui.getDept_id());
				konkaR3ShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopMatchAndAssignList(konkaR3Shop);
				request.setAttribute("konkaR3ShopList", konkaR3ShopList);
				request.setAttribute("fenjingban", "fenjingban");
			} else {
				String msg = "对不起只有事业部、分公司、经营部和办事处有权限添加发货登记信息!";
				renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
		}
		dynaBean.set("mod_id", mod_id);
		dynaBean.set("queryString", super.serialize(request, "mod_id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// String queryString = (String) dynaBean.get("queryString");
		String sn = (String) dynaBean.get("sn");
		// 取用户===
		PeProdUser ui = super.getSessionUserInfo(request);

		String fh_bill_id = request.getParameter("fh_bill_id");// 修改时-- 发货登记 id
		// String[] fh_bill_details_ids = request.getParameterValues("fh_bill_details_id");// 修改时--发货登记明细 id
		//		
		// String[] last_fh_counts = request.getParameterValues("last_fh_count");// 上一次发货的数量--修改时计算库存数量

		String branch_or_wd_id = request.getParameter("branch_or_wd_id");// 分公司或者网点ID
		String[] store_ids = request.getParameterValues("store_id");// 出货仓库
		String[] cls_ids = request.getParameterValues("cls_id");// 产品类型id
		String[] pd_ids = request.getParameterValues("pd_id");// 产品型号id
		String[] counts = request.getParameterValues("count");// 发货数量
		String[] prices = request.getParameterValues("price");// 发货单价===成本价从库存状态中取
		String[] remarks = request.getParameterValues("remark");// 备注

		String money_must = request.getParameter("money_must");// 应收金额
		String money_result = request.getParameter("money_result");// 实收金额

		for (int i = 0; i < pd_ids.length; i++) {
			for (int k = i + 1; k < pd_ids.length; k++) {
				if (StringUtils.equals(pd_ids[i], pd_ids[k]) && StringUtils.equals(store_ids[i], store_ids[k])) {
					String send_error = "同一个出货仓库的型号有重复，请重新选择或者合并！";
					super.renderJavaScript(response, "alert('" + send_error + "');history.back();");
					return null;
				}
			}
		}

		if (null == fh_bill_id) {// insert
			Long fh_sum_count = 0L;// 本次发货总数量=====
			List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = new ArrayList<KonkaJxcFhBillDetails>();
			Set<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsSet = new HashSet<KonkaJxcFhBillDetails>();
			konkaJxcFhBillDetailsSet.size();

			// 循环 add 发货登记明细
			for (int i = 1; i < store_ids.length; i++) {
				fh_sum_count += new Long(counts[i]);// 循环增加发货明细的数量到总数中====
				KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
				konkaJxcFhBillDetails.setStore_id(new Long(store_ids[i]));// 仓库
				konkaJxcFhBillDetails.setPd_type_id(new Long(cls_ids[i]));// 类型
				konkaJxcFhBillDetails.setPd_id(new Long(pd_ids[i]));// 型号
				konkaJxcFhBillDetails.setBrand_id(Constants.KONKA_BRAND_ID);// 品牌id
				konkaJxcFhBillDetails.setBrand_name(Constants.KONKA_BRAND_NAME);// 康佳

				BasePdClazz basePdClazz = new BasePdClazz();
				basePdClazz.setCls_id(new Long(cls_ids[i]));
				basePdClazz = getFacade().getBasePdClazzService().getBasePdClazz(basePdClazz);
				if (null != basePdClazz) {// 大类名称
					konkaJxcFhBillDetails.setPd_type_name(basePdClazz.getCls_name());
				}

				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(new Long(pd_ids[i]));
				pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (null != pePdModel) {// 型号名称
					konkaJxcFhBillDetails.setPd_name(pePdModel.getMd_name());
				}

				konkaJxcFhBillDetails.setCount(new Long(counts[i]));// 数量
				konkaJxcFhBillDetails.setPrice(new BigDecimal(prices[i]));// 单价

				konkaJxcFhBillDetails.setIs_pc(0);
				konkaJxcFhBillDetails.setAdd_date(new Date());
				konkaJxcFhBillDetails.setAdd_user_id(ui.getId());// 添加人id
				konkaJxcFhBillDetails.setAdd_dept_id(ui.getDept_id());// 部门id
				konkaJxcFhBillDetails.setRemark(remarks[i]);

				konkaJxcFhBillDetails.setBranch_or_wd_id(new Long(branch_or_wd_id));// 分公司id或网点id

				// 定义库存状态
				KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
				konkaJxcStoreState.setDept_id(ui.getDept_id());// 该部门id
				konkaJxcStoreState.setStore_id(new Long(store_ids[i]));// 仓库id

				KonkaJxcStoreInfo konkaJxcStoreInfo = new KonkaJxcStoreInfo();
				konkaJxcStoreInfo.setId(new Long(store_ids[i]));
				konkaJxcStoreInfo = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(konkaJxcStoreInfo);

				konkaJxcStoreState.setPd_type_id(new Long(cls_ids[i]));// 品类id
				konkaJxcStoreState.setPd_id(new Long(pd_ids[i]));// 型号id
				konkaJxcStoreState.setBrand_id(Constants.KONKA_BRAND_ID);// 品牌id
				konkaJxcStoreState = getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(
						konkaJxcStoreState);
				if (null == konkaJxcStoreState) {
					String msg = "您选择的产品型号中有库存为0的产品,不能发货!";
					renderJavaScript(response, "alert('" + msg
							+ "');location.href='KonkaJxcFhBillRegister.do?method=add&mod_id=" + mod_id + "';");
					return null;
				} else {
					konkaJxcFhBillDetails.setCost_price(konkaJxcStoreState.getCur_cost_price());// 产品实时成本价

					konkaJxcStoreState.setStore_name(konkaJxcStoreInfo.getStore_name());// 仓库名称
					konkaJxcStoreState.setPd_type_name(basePdClazz.getCls_name());// 品类名称
					konkaJxcStoreState.setPd_name(pePdModel.getMd_name());// 型号名称
					konkaJxcStoreState.setBrand_name(Constants.KONKA_BRAND_NAME);// 品牌名称
					konkaJxcStoreState.setPd_num(konkaJxcStoreState.getPd_num() - new Long(counts[i]));// 数量减少
					konkaJxcStoreState.setUpdate_date(new Date());
					konkaJxcStoreState.setUpdate_user_id(ui.getId());

					konkaJxcFhBillDetails.setKonkaJxcStoreState(konkaJxcStoreState);
				}

				konkaJxcFhBillDetailsList.add(konkaJxcFhBillDetails);
			}

			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}
			resetToken(request);

			// add 发货登记
			KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
			super.copyProperties(konkaJxcFhBill, form);

			// 取用户的角色---发货人（添加人）类型
			PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());
			if (null != peRoleInfo && null != peRoleInfo.getRole_id()) {
				konkaJxcFhBill.setAdd_user_type(peRoleInfo.getRole_id());// 进货登记人类型，和KONKA_PE_ROLE_INFO【角色(职位)表】中的数据对应
			}

			konkaJxcFhBill.setFh_sum_count(fh_sum_count);// =======发货总数量=====
			konkaJxcFhBill.setAdd_date(new Date());
			konkaJxcFhBill.setAdd_user_id(ui.getId());
			konkaJxcFhBill.setAdd_user_name(ui.getUser_name());
			konkaJxcFhBill.setSn(sn);
			konkaJxcFhBill.setAdd_dept_id(ui.getDept_id());
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(ui.getDept_id());
			konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			konkaJxcFhBill.setAdd_dept_name(konkaDept.getDept_name());// 添加人部门名称
			konkaJxcFhBill.setIs_confirm(0);// 是否确认收货
			konkaJxcFhBill.setMoney_must(new BigDecimal(money_must));// 应收金额
			konkaJxcFhBill.setMoney_result(new BigDecimal(money_result));// 实收金额
			konkaJxcFhBill.setKonkaJxcFhBillDetails(konkaJxcFhBillDetailsList);
			konkaJxcFhBill.setData_src(1);//数据来源   1：系统录入 2：R3销售导入
			// service中添加 【发货登记明细】
			super.getFacade().getKonkaJxcFhBillService().createKonkaJxcFhBill(konkaJxcFhBill);
			saveMessage(request, "entity.inserted");

		}
		// else {// update
		// for (int i = 0; i < fh_bill_details_ids.length; i++) {
		// // 更新发货登记明细表
		// KonkaJxcFhBillDetails konkaJxcFhBillDetails = new KonkaJxcFhBillDetails();
		// konkaJxcFhBillDetails.setId(new Long(fh_bill_details_ids[i]));// id
		//
		// konkaJxcFhBillDetails = getFacade().getKonkaJxcFhBillDetailsService().getKonkaJxcFhBillDetails(
		// konkaJxcFhBillDetails);
		//
		// konkaJxcFhBillDetails.getMap().put("last_fh_count", last_fh_counts[i]);// 上次发货数量
		// konkaJxcFhBillDetails.setCount(new Long(counts[i]));// 本次发货数量
		// konkaJxcFhBillDetails.setUpdate_user_id(ui.getId());// 更新用户id
		// konkaJxcFhBillDetails.setUpdate_date(new Date());// 更新时间
		// // 更新发货登记明细表时，取得此时【发货登记】中的发货总数量
		// Long last_fh_sum_count = 0L;
		// if (null != fh_bill_id) {
		// KonkaJxcFhBill konkaJxcFhBill = new KonkaJxcFhBill();
		// konkaJxcFhBill.setId(new Long(fh_bill_id));
		// konkaJxcFhBill = getFacade().getKonkaJxcFhBillService().getKonkaJxcFhBill(konkaJxcFhBill);
		// last_fh_sum_count = konkaJxcFhBill.getFh_sum_count();
		// konkaJxcFhBillDetails.getMap().put("fh_bill_id", fh_bill_id.toString());
		// konkaJxcFhBillDetails.getMap().put("last_fh_sum_count", last_fh_sum_count.toString());
		// }
		// // service层 更新发货登记表
		// super.getFacade().getKonkaJxcFhBillDetailsService().modifyKonkaJxcFhBillDetails(konkaJxcFhBillDetails);
		// saveMessage(request, "entity.updated");
		// }
		// }
		// the line below is added for pagination
		// StringBuffer pathBuffer = new StringBuffer();
		// pathBuffer.append(mapping.findForward("success").getPath());
		// pathBuffer.append("&mod_id=" + mod_id);
		// pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(queryString));
		// ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return new ActionForward("/KonkaJxcFhBillRegister.do?method=list&mod_id=203400", true);
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

			// 判断角色，显示分公司还是网点
			PeProdUser ui = super.getSessionUserInfo(request);
			PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());
			if (null != peRoleInfo && null != peRoleInfo.getRole_id()) {
				if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_SYB
						&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// ===事业部===
					konkaJxcFhBillDetails.getMap().put("is_shiyebu", "true");
					request.setAttribute("is_shiyebu", "true");
				} else if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_FGS
						&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_YWY) {// ===分公司\\经营部、办事处===
					konkaJxcFhBillDetails.getMap().put("is_fenjingban", "true");
					request.setAttribute("is_fenjingban", "true");
				}else if(peRoleInfo.getRole_id().intValue()== 10L){
					konkaJxcFhBillDetails.getMap().put("is_shiyebu", "true");
					request.setAttribute("is_shiyebu", "true");
				}
			}
			List<KonkaJxcFhBillDetails> konkaJxcFhBillDetailsList = super.getFacade().getKonkaJxcFhBillDetailsService()
					.getKonkaJxcFhBillDetailsWithNamesList(konkaJxcFhBillDetails);
			request.setAttribute("konkaJxcFhBillDetailsList", konkaJxcFhBillDetailsList);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser ui = super.getSessionUserInfo(request);
		KonkaJxcFhBill entity = new KonkaJxcFhBill();
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setIs_del(1);
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setDel_user_id(ui.getId());
			getFacade().getKonkaJxcFhBillService().modifyKonkaJxcFhBill(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			entity.setIs_del(1);
			entity.setDel_date(new Date());
			entity.setDel_user_id(ui.getId());
			entity.getMap().put("pks", pks);
			getFacade().getKonkaJxcFhBillService().modifyKonkaJxcFhBill(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "ids", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

}
