package com.ebiz.mmt.web.struts.manager.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaShopDevelop;
import com.ebiz.mmt.domain.KonkaShopVisit;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Cheng,Bing
 * @version 2011-10-20
 */
public class KonkaShopDevelopAction extends BaseAction {

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
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		// 查询条件
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String develop_status = (String) dynaBean.get("shop_develop_status");
		String visit_status = (String) dynaBean.get("shop_visit_status");
		String is_assigned_ywy = (String) dynaBean.get("is_assigned_ywy");

		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 获取用户角色
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_lt_60 = false;
		boolean role_id_ge_30_le_59 = false;
		boolean role_id_ge_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() < 60L) {
				role_id_ge_10_lt_60 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 59L) {
				role_id_ge_30_le_59 = true;
			}
			if (peRoleUser.getRole_id() >= 60L) {
				role_id_ge_60 = true;
			}
		}
		
		if (role_id_ge_30_le_59) {
			dynaBean.set("dev_able", 1);
		}

		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
		if (role_id_ge_10_lt_60) {
			shopDevelop.getMap().put("is_dept", peProdUser.getDept_id());
		} else if (role_id_ge_60) {
			shopDevelop.setUser_id(peProdUser.getId());
		}

		if (StringUtils.isNotBlank(shop_name_like)) {
			shopDevelop.getMap().put("shop_name_like", shop_name_like);
		}

		if (StringUtils.isNotBlank(develop_status)) {
			shopDevelop.setDevelop_status(Long.valueOf(develop_status));
		}

		if (StringUtils.isNotBlank(visit_status)) {
			if ("0".equals(visit_status)) {
				shopDevelop.getMap().put("is_not_visit", visit_status); // 未拜访条件，拜访记录中无该shop的记录
			} else {
				shopDevelop.getMap().put("visit", true);
				shopDevelop.getMap().put("visit_status", visit_status);
			}
		}
		// 是否分配业务员
		if (StringUtils.isNotBlank(is_assigned_ywy)) {
			if ("0".equals(is_assigned_ywy)) {
				shopDevelop.getMap().put("is_assigned_ywy_no", 1);
			} else {
				shopDevelop.getMap().put("is_assigned_ywy_yes", 1);
			}
		}

		// 逻辑删除的不显示
		shopDevelop.setIs_del(Long.valueOf("0"));

		Long recordCount = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelopCount(shopDevelop);
		pager.init(recordCount, 10, pager.getRequestPage());
		shopDevelop.getRow().setFirst(pager.getFirstRow());
		shopDevelop.getRow().setCount(pager.getRowCount());
		List<KonkaShopDevelop> shop_List = super.getFacade().getKonkaShopDevelopService()
				.getKonkaShopDevelopPaginatedList(shopDevelop);
		request.setAttribute("shop_List", shop_List);

		for (int i = 0; i < shop_List.size(); i++) {
			KonkaShopDevelop dev = shop_List.get(i);

			KonkaShopVisit visit = new KonkaShopVisit();
			// visit.setUser_id(peProdUser.getId());
			visit.setShop_id(dev.getShop_id());
			visit.getMap().put("is_last", true);
			visit = super.getFacade().getKonkaShopVisitService().getKonkaShopVisit(visit);
			if (visit != null) {
				dev.getMap().put("visit_count", visit.getVisit_count());
				dev.getMap().put("visit_status", visit.getVisit_status());
				dev.getMap().put("visit_date", visit.getVisit_date());
			}
		}

		return mapping.findForward("list");
	}

	public ActionForward visit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String shop_id = (String) dynaBean.get("shop_id");
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();
		shopDevelop.setUser_id(peProdUser.getId());
		shopDevelop.setShop_id(Long.valueOf(shop_id));
		shopDevelop = super.getFacade().getKonkaShopDevelopService().getKonkaShopDevelop(shopDevelop);

		KonkaShopVisit visit = new KonkaShopVisit();
		visit.setUser_id(peProdUser.getId());

		if (StringUtils.isNotBlank(shop_id)) {
			visit.setShop_id(Long.valueOf(shop_id));
		}

		Long recordCount = super.getFacade().getKonkaShopVisitService().getKonkaShopVisitCount(visit);
		pager.init(recordCount, 10, pager.getRequestPage());
		visit.getRow().setFirst(pager.getFirstRow());
		visit.getRow().setCount(pager.getRowCount());
		List<KonkaShopVisit> visitList = super.getFacade().getKonkaShopVisitService()
				.getKonkaShopVisitPaginatedList(visit);
		request.setAttribute("visitList", visitList);
		request.setAttribute("visit_count", recordCount);
		request.setAttribute("shop_name", shopDevelop.getShop_name());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String shop_id = (String) dynaBean.get("shop_id");

		String develop_id = (String) dynaBean.get("develop_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String developType = (String) dynaBean.get("developType");
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaShopDevelop shopDevelop = new KonkaShopDevelop();

		if (StringUtils.isNotBlank(develop_id)) {
			// 开拓ID
			shopDevelop.setId(Long.valueOf(develop_id));
			// 开拓网点的分公司，经营部，办事处人员
			shopDevelop.setAdd_user_id(peProdUser.getId());

			if ("0".equals(developType)) {
				shopDevelop.setUser_id(Long.valueOf(ywy_user_id));

				// 取业务员信息
				PeProdUser ywy = new PeProdUser();
				ywy.setId(Long.valueOf(ywy_user_id));
				ywy = super.getFacade().getPeProdUserService().getPeProdUser(ywy);

				shopDevelop.setUser_name(ywy.getUser_name());
				// 所属部门
				shopDevelop.setDept_id(ywy.getDept_id());
			} else {
				String convertShopDirection = (String) dynaBean.get("convertShopDirection");
				if ("0".equals(convertShopDirection)) {
					// 代理商网点ID
					String dls_id = (String) dynaBean.get("konka_r3_id");

					// 创建KonkaBranchCategory信息
					KonkaBranchCategory branchCategory = new KonkaBranchCategory();
					branchCategory.setKonka_r3_id(Long.valueOf(dls_id));
					branchCategory.setMmt_shop_id(Long.valueOf(shop_id));
					branchCategory.setCategory_id(Long.valueOf("20"));
					super.getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(branchCategory);

					// 网点直接转化：经销商
					shopDevelop.setJxs_id(Long.valueOf(dls_id));

				} else {
					// 网点直接转化：R3用户
					String r3_id = (String) dynaBean.get("r3_id");
					shopDevelop.setR3_id(Long.valueOf(r3_id));
				}
				shopDevelop.setDevelop_status(Long.valueOf("2"));
				shopDevelop.setEnd_date(new Date());

				// 逻辑删除该网点
				shopDevelop.setIs_del(Long.valueOf("1"));
			}

			super.getFacade().getKonkaShopDevelopService().modifyKonkaShopDevelop(shopDevelop);
			saveMessage(request, "entity.updated");
		}

		// end
		return list(mapping, form, request, response);
	}

	/*
	 * 业务员开拓
	 */
	public ActionForward shopIndirectDevelop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1)、系统管理员进入列表页显示所有R3系统导入的网点(9000)，如果没有分配则“上级部门字段为空”；
		// 2)、事业部登陆，可以强制把网点与别的管辖区域分公司建立联系；
		// 3)、分公司登陆，分配网点给相应的经营部、办事处等；
		// 4)、经营部/办事处登陆，使用此功能节点把网点分配给业务员；
		// 5)、“设置”中不仅可以选择分配上级部门，还以选择网的分类属性，属性可以叠加选择。
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		// 登录用户信息
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		dynaBean.set("category_id", -1);
		String[] pks = (String[]) dynaBean.get("pks");
		if (!ArrayUtils.isEmpty(pks)) {
			MmtEntpShop konkaEntpShop = new MmtEntpShop();
			konkaEntpShop.getMap().put("selects", StringUtils.join(pks, ","));
			List<MmtEntpShop> konkaEntpShopList = super.getFacade().getMmtEntpShopService()
					.getMmtEntpShopList(konkaEntpShop);

			request.setAttribute("konkaEntpShopList", konkaEntpShopList);

			if (konkaEntpShopList.size() == 1) {
				MmtEntpShop t = konkaEntpShopList.get(0);

				// KonkaBranchAssign kba = new KonkaBranchAssign();
				// kba.setMmt_shop_id(Long.valueOf(t.getShop_id()));
				// List<KonkaBranchAssign> kbaList =
				// super.getFacade().getKonkaBranchAssignService().getKonkaBranchAssignList(kba);

				List<BranchAssign> kbaList = super.getBranchAssignList(2, null, Long.valueOf(t.getShop_id()), null,
						null, null, null);

				if (kbaList.size() > 0) {
					for (BranchAssign branchAssign : kbaList) {
						if (branchAssign.getUser_id() != null) {
							PeProdUser peProdUser1 = new PeProdUser();
							peProdUser1.setId(branchAssign.getUser_id());
							peProdUser1 = super.getFacade().getPeProdUserService().getPeProdUser(peProdUser1);

							request.setAttribute("ywy_user", peProdUser1);
						}
						// KonkaDept konkaDept = new KonkaDept();
						// if (branchAssign.getSyb_id() != null) {
						// konkaDept.setDept_id(branchAssign.getSyb_id());
						// }
						// if (branchAssign.getFgs_id() != null) {
						// konkaDept.setDept_id(branchAssign.getFgs_id());
						// }
						// if (branchAssign.getJyb_id() != null) {
						// konkaDept.setDept_id(branchAssign.getJyb_id());
						// }
						// if (branchAssign.getBsc_id() != null) {
						// konkaDept.setDept_id(branchAssign.getBsc_id());
						// }
					}
				}
			}
		}

		KonkaDept fgs_dept = getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		request.setAttribute("fgs_dept", fgs_dept);

		KonkaDept jyb_dept = new KonkaDept();
		jyb_dept.getMap().put("dept_types_Y", "4,5");
		jyb_dept.getMap().put("dept_id", peProdUser.getDept_id());
		List<KonkaDept> jyb_List = super.getFacade().getKonkaDeptService()
				.getKonkaDeptTreeNameByUserForResultList(jyb_dept);
		request.setAttribute("jyb_List", jyb_List);

		request.setAttribute("peProdUser", peProdUser);

		return new ActionForward("/admin/KonkaShopDevelop/pe_prod_user.jsp");
	}

	public ActionForward ajaxSelectVisitById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String visit_id = (String) dynaBean.get("visit_id");
		KonkaShopVisit visit = new KonkaShopVisit();

		if (StringUtils.isNotBlank(visit_id)) {
			visit.setId(Long.valueOf(visit_id));
		}
		visit = super.getFacade().getKonkaShopVisitService().getKonkaShopVisit(visit);

		StringBuffer jsonBuffer = new StringBuffer();
		if (visit != null) {
			jsonBuffer.append("{\"ajax_status\":\"1\",");
			jsonBuffer.append("\"id\":\"").append(visit.getId()).append("\",");
			jsonBuffer.append("\"user_name\":\"").append(visit.getUser_name()).append("\",");
			jsonBuffer.append("\"shop_name\":\"").append(visit.getShop_name()).append("\",");
			jsonBuffer.append("\"visit_status\":\"").append(visit.getVisit_status()).append("\",");
			jsonBuffer.append("\"visit_count\":\"").append(visit.getVisit_count()).append("\",");
			jsonBuffer.append("\"visit_note\":\"").append(visit.getVisit_note()).append("\"");
			jsonBuffer.append("}");
		} else {
			jsonBuffer.append("{\"ajax_status\":\"0\"}");
		}
		logger.info("JSON Output : [{}]", jsonBuffer.toString());

		super.render(response, jsonBuffer.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

	/*
	 * 直接开拓初始
	 */

	public ActionForward toShopDirectDevelop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String shop_id = (String) dynaBean.get("shop_id");

		if (!GenericValidator.isLong(shop_id)) {
			saveError(request, "errors.long", shop_id);
			return mapping.findForward("list");
		}

		EntpShop entpShop = new EntpShop();
		entpShop.setShop_id(Long.valueOf(shop_id));
		entpShop = getFacade().getEntpShopService().getEntpShop(entpShop);

		if (null == entpShop) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(super.getSessionUserInfo(request).getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}

		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setIs_del(0L);
		if (role_id_ge_30) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (dept.getDept_type() == 3) {// 分公司
				r3.setBranch_area_name(dept.getDept_name());
				dynaBean.set("branch_area_name", dept.getDept_name());
			} else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {// 经营部
				r3.setBranch_area_name(super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
				dynaBean.set("branch_area_name", super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
			}
		}
		List<KonkaR3Shop> BranchList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByBranchAreaName(r3);

		request.setAttribute("BranchList", BranchList);
		dynaBean.set("shop_name", entpShop.getShop_name());

		dynaBean.set("operator_step", "1");

		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	/*
	 * 直接开拓 ---创建R3用户
	 */
	public ActionForward shopDirectDevelop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		resetToken(request);
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		String shop_id = (String) dynaBean.get("shop_id");
		String shop_name = (String) dynaBean.get("shop_name");

		// 获取KonkaEntpShop信息
		MmtEntpShop entpShop = new MmtEntpShop();
		entpShop.setShop_id(Long.valueOf(shop_id));
		entpShop = super.getFacade().getMmtEntpShopService().getMmtEntpShop(entpShop);
		if (null == entpShop) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		// 创建KonkaR3MmtMatch信息
		KonkaR3MmtMatch r3MmtMatch = new KonkaR3MmtMatch();
		r3MmtMatch.setMmt_shop_id(entpShop.getShop_id());
		r3MmtMatch.setMmt_shop_name(entpShop.getShop_name());

		// 创建KonkaR3Shop信息
		KonkaR3Shop entity = new KonkaR3Shop();
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setR3_code((String) dynaBean.get("r3_code"));
		shop.setIs_del(0L);
		List<KonkaR3Shop> shopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
		if (shopList.size() != 0) {
			String msg = "R3编码已存在，请重新输入！";
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;

		}
		super.copyProperties(entity, form);
		entity.setImport_user_id(ui.getId());
		entity.setImport_date(new Date());
		entity.setIs_del(0L);
		entity.setIs_match(1L);

		Long r3_shop_id = super.getFacade().getKonkaR3ShopService().createKonkaR3Shop(entity);
		r3MmtMatch.setR3_shop_id(r3_shop_id);
		super.getFacade().getKonkaR3MmtMatchService().createKonkaR3MmtMatch(r3MmtMatch);

		// 创建KonkaBranchCategory信息
		KonkaBranchCategory branchCategory = new KonkaBranchCategory();
		branchCategory.setKonka_r3_id(r3_shop_id);
		branchCategory.setMmt_shop_id(Long.valueOf(shop_id));
		branchCategory.setCategory_id(Long.valueOf("10"));
		super.getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(branchCategory);

		saveMessage(request, "entity.inserted");

		entity = getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);
		// 页面回传
		super.copyProperties(form, entity);
		dynaBean.set("shop_name", shop_name);
		dynaBean.set("operator_step", "2");
		// end
		return mapping.findForward("input");
	}
}
