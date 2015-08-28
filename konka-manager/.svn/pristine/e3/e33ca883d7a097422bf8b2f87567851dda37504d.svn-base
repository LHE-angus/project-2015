package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.databinding.types.soapencoding.Array;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaMobileTestData;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaYjglPlan;
import com.ebiz.mmt.domain.KonkaYjglPlanFp;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaYjglPlanFpForSyAction extends MobileBaseAction {

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

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String dept_id = (String) dynaBean.get("dept_id");
		String plan_year = (String) dynaBean.get("plan_year");
		String is_confirm = (String) dynaBean.get("is_confirm");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String sy_state = (String) dynaBean.get("sy_state");

		KonkaYjglPlanFp entity = new KonkaYjglPlanFp();
		super.copyProperties(entity, form);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_gt_30_lt_60 = false;

		List<Long> roles = new ArrayList<Long>();
		for (PeRoleUser peRoleUser : peRoleUserList) {
			roles.add(peRoleUser.getRole_id());
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			// 分公司管理员
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_gt_30_lt_60 = true;
			}
		}
		if (role_id_eq_30) {

		} else if (role_id_gt_30_lt_60) {
			entity.setDept_id(super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
		}

		List<Long> s_ids = new ArrayList<Long>();
		if (roles.contains(60L) && !roles.contains(188L)) {// 业务员
			s_ids = getR3StoreIds(peProdUser.getId(), 60L);
		} else if (roles.contains(188L) && !roles.contains(60L)) {// 促销员
			s_ids = getR3StoreIds(peProdUser.getId(), 188L);
		} else if (roles.contains(188L) && roles.contains(60L)) {// 既是业务员又是促销员
			s_ids = getR3StoreIds(peProdUser.getId(), 0L);
		}
		if (s_ids.size() > 0) {
			entity.getMap().put("store_ids", s_ids);
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		if (StringUtils.isNotBlank(plan_year)) {
			entity.setPlan_year(Integer.valueOf(plan_year));
		}
		if (StringUtils.isNotBlank(is_confirm)) {
			entity.setIs_confirm(Integer.valueOf(is_confirm));
		}

		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}

		if (StringUtils.isNotBlank(sy_state)) {
			if (sy_state.equals("0")) {
				entity.getMap().put("no_confirm", true);
			} else if (sy_state.equals("1")) {
				entity.getMap().put("wait_sy", true);
			} else if (sy_state.equals("2")) {
				entity.getMap().put("have_sy", true);
			} else if (sy_state.equals("3")) {
				entity.getMap().put("sy_ing", true);
			} else if (sy_state.equals("4")) {
				entity.getMap().put("is_down", true);
			}
		}

		Long recordCount = super.getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFpAndDeptNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaYjglPlanFp> entityList = super.getFacade().getKonkaYjglPlanFpService()
				.getKonkaYjglPlanFpAndDeptNamePaginatedList(entity);

		/*
		 * List<KonkaYjglPlanFp> entityList = new ArrayList<KonkaYjglPlanFp>(); for (KonkaYjglPlanFp kp : entityList1) {
		 * if (StringUtils.isBlank(sy_state)) { entityList.add(kp); } else { if (sy_state.equals("0")) {// 未确认 if
		 * (kp.getIs_confirm().intValue() == 0) { entityList.add(kp); } } else if (sy_state.equals("1")) {// 待上样 5 0 5
		 * Integer xy_num = (Integer) kp.getMap().get("xy_num"); Integer sy_num = (Integer) kp.getMap().get("sy_num");
		 * if (kp.getIs_confirm().intValue() == 1 && Integer.valueOf(sy_num).intValue() == 0 && kp.getNum().intValue() >
		 * Integer.valueOf(xy_num).intValue() + Integer.valueOf(sy_num).intValue()) { entityList.add(kp); } } else if
		 * (sy_state.equals("2")) {// 已上样 Integer sy_num = (Integer) kp.getMap().get("sy_num"); if
		 * (kp.getIs_confirm().intValue() == 1 && kp.getNum().intValue() > Integer.valueOf(sy_num).intValue() &&
		 * Integer.valueOf(sy_num).intValue() > 0) { entityList.add(kp); } } else if (sy_state.equals("3")) {// 上样中
		 * Integer sy_num = (Integer) kp.getMap().get("sy_num"); if (kp.getIs_confirm().intValue() == 1 &&
		 * kp.getNum().intValue() == Integer.valueOf(sy_num).intValue()) { entityList.add(kp); } } else if
		 * (sy_state.equals("4")) {// 已下架 Integer sy_num = (Integer) kp.getMap().get("sy_num"); Integer xy_num =
		 * (Integer) kp.getMap().get("xy_num"); if (kp.getIs_confirm().intValue() == 1 &&
		 * Integer.valueOf(sy_num).intValue() == 0 && kp.getNum().intValue() == Integer.valueOf(xy_num).intValue()) {
		 * entityList.add(kp); } } } } pager.init(Long.valueOf(entityList.size()), pager.getPageSize(),
		 * pager.getRequestPage()); int start_num = (Integer.valueOf(pager.getRequestPage()) - 1) * 10; int end_num =
		 * (Integer.valueOf(pager.getRequestPage())) * 10 - 1; int pageCount = (int) Math.ceil((entityList.size() + 10 -
		 * 1) / 10); if (Integer.valueOf(pager.getRequestPage()) > pageCount) { start_num = (pageCount - 1) * 10;
		 * end_num = (pageCount) * 10 - 1; } else if (Integer.valueOf(pager.getRequestPage()) < 1) { start_num = (1 - 1)
		 * * 10; end_num = (1) * 10 - 1; } List<KonkaYjglPlanFp> entityList2 = new ArrayList<KonkaYjglPlanFp>(); for
		 * (int i = 0; i < entityList.size(); i++) { if (i >= start_num && i <= end_num) {
		 * entityList2.add(entityList.get(i)); } }
		 */

		request.setAttribute("entityList", entityList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		PeProdUser peProdUser = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		KonkaYjglPlan kp = new KonkaYjglPlan();
		kp.setDept_id(super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
		List<KonkaYjglPlan> kpList = super.getFacade().getKonkaYjglPlanService().getKonkaYjglPlanList(kp);
		if (kpList != null && kpList.size() > 0) {
			for (KonkaYjglPlan konkaYjglPlan : kpList) {
				KonkaBaseTypeData kd = new KonkaBaseTypeData();
				kd.setType_id(konkaYjglPlan.getType_id());
				kd = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(kd);
				if (kd != null) {
					konkaYjglPlan.getMap().put("type_name", kd.getType_name());
				}
			}
		}
		request.setAttribute("kpList", kpList);

		Long currentUserId = peProdUser.getId();

		// 初始化门店基础数据
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(currentUserId);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_30 = false; // 分公司管理员
		boolean role_id_eq_188 = false; // 促销员
		boolean role_id_eq_60 = false; // 业务员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
			if (peRoleUser.getRole_id() == 30L) {
				role_id_eq_30 = true;
			}

		}

		Long[] role_id_60_array = null;
		if (role_id_eq_60) {
			// 业务员
			logger.info("*************** 业务员:{}", peProdUser.getUser_name());
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", currentUserId);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);

			if (null != storeList) {

				role_id_60_array = new Long[storeList.size()];
				int i = 0;

				for (KonkaR3Store t : storeList) {
					role_id_60_array[i++] = t.getStore_id();

					MobileSelectItem _t = new MobileSelectItem();
					_t.setId(t.getStore_id().toString());
					if (t.getStore_name() != null) {
						String customer_name = (String) t.getMap().get("customer_name");
						if (customer_name != null && customer_name.equals(t.getStore_name())) {
							_t.setName(t.getStore_name());
						} else {
							_t.setName(t.getStore_name() + "[" + customer_name + "]");
						}
					} else {
						_t.setName("名称维护有误的门店");
					}
				}
				request.setAttribute("storeList", storeList);

			}
		}

		if (role_id_eq_188) {
			// 促销员
			logger.info("*************** 促销员:{}", peProdUser.getUser_name());
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(currentUserId); // 登录用户ID作为促销员的ID
			List<KonkaR3Store> storeList1 = new ArrayList<KonkaR3Store>();

			storeList = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);

			for (KonkaMobileSpRelation t : storeList) {
				if (null != role_id_60_array && ArrayUtils.contains(role_id_60_array, t.getShop_id())) {
					continue;
				}
				KonkaR3Store store = new KonkaR3Store();
				if (null != t && null != t.getShop_id()) {
					store.setStore_id(t.getShop_id());
					store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
					if (null != store) {
						storeList1.add(store);
					}
				}
				MobileSelectItem _t = new MobileSelectItem();
				_t.setId(t.getShop_id().toString());
				if (t.getMap().get("store_name") != null)
					_t.setName(t.getMap().get("store_name").toString());
				else {
					_t.setName("名称维护有误的门店");
				}
				request.setAttribute("storeList", storeList1);
			}
		}

		if (role_id_eq_30) {
			KonkaR3Store store = new KonkaR3Store();
			store.setDept_id(super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreList(store);
			request.setAttribute("storeList", storeList);
		}

		/* *
		 * PePdModel pm = new PePdModel(); pm.setIs_del(0); pm.setBrand_id(Constants.KONKA_BRAND_ID); List<PePdModel>
		 * pePdList = super.getFacade().getPePdModelService().getPePdModelList(pm); request.setAttribute("pePdList",
		 * pePdList);
		 */

		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("OrderByMd", true);
		pePdModel.setIs_del(0);
		pePdModel.setAudit_state(1);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);
		List<MobileSelectItem> entityList = new ArrayList<MobileSelectItem>();
		for (PePdModel _t : pePdModelList) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getPd_id().toString());
			entity.setName(_t.getMd_name());
			entityList.add(entity);
		}

		request.setAttribute("pePdModelList", pePdModelList);
		request.setAttribute("pePdModelJson", JSON.toJSONString(entityList));

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		KonkaDept kd = new KonkaDept();
		kd.setDept_id(super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id());
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		if (kd != null) {
			dynaBean.set("dept_name", kd.getDept_name());
		}

		return mapping.findForward("input");
	}

	public ActionForward upself(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaYjglPlanFp entity = new KonkaYjglPlanFp();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFp(entity);
		if (null == entity) {
			return null;
		}
		super.copyProperties(form, entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(entity.getDept_id()));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (null == konkaDept) {
			return null;
		}

		KonkaR3Store ks = new KonkaR3Store();
		ks.setStore_id(entity.getShop_id());
		ks = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(ks);

		dynaBean.set("dept_name", konkaDept.getDept_name());
		dynaBean.set("store_name", ks.getStore_name());

		int num = entity.getNum().intValue();

		// Long now = new Date().getTime();

		int sy_num = 0;
		int xy_num = 0;

		KonkaMobileTestData kd = new KonkaMobileTestData();
		kd.setPlan_fp_id(entity.getId());
		kd.setStatus(0);
		List<KonkaMobileTestData> kdList = super.getFacade().getKonkaMobileTestDataService()
				.getKonkaMobileTestDataList(kd);
		if (kdList == null || kdList.size() == 0) {

			sy_num = 0;
			xy_num = 0;
		} else {
			for (KonkaMobileTestData kd1 : kdList) {
				if (kd1.getUp_date() != null) {
					if (kd1.getUp_date().getTime() < new Date().getTime()
							&& (kd1.getDown_date() == null || new Date().getTime() < kd1.getDown_date().getTime())) {
						sy_num++;
					} else if (kd1.getUp_date().getTime() > new Date().getTime()
							|| kd1.getDown_date().getTime() < new Date().getTime()) {
						xy_num++;
					}
				}
			}
		}
		int total_num = sy_num + xy_num;

		List<KonkaYjglPlanFp> kfList = new ArrayList<KonkaYjglPlanFp>();
		for (int i = 0; i < num - total_num; i++) {
			KonkaYjglPlanFp fp = new KonkaYjglPlanFp();
			fp.setId(Long.valueOf(id));
			fp.setPd_id(entity.getPd_id());
			fp.setPd_name(entity.getPd_name());
			fp.setNum(1);
			fp.setShop_id(entity.getShop_id());
			kfList.add(fp);
		}
		request.setAttribute("kfList", kfList);

		return mapping.findForward("view");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		KonkaYjglPlanFp entity = new KonkaYjglPlanFp();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFp(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		KonkaDept kd = new KonkaDept();
		kd.setDept_id(entity.getDept_id());
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		if (kd != null) {
			dynaBean.set("dept_name", kd.getDept_name());
		}

		KonkaR3Store ks = new KonkaR3Store();
		ks.setStore_id(entity.getShop_id());
		ks = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(ks);
		dynaBean.set("store_name", ks.getStore_name());

		KonkaBaseTypeData type = new KonkaBaseTypeData();
		type.setType_id(entity.getType_id());
		type = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(type);
		dynaBean.set("type_name", type.getType_name());

		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("OrderByMd", true);
		pePdModel.setIs_del(0);
		pePdModel.setAudit_state(1);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);

		request.setAttribute("pePdModelList", pePdModelList);

		return new ActionForward("/admin/KonkaYjglPlanFpForSy/edit.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String queryString = (String) dynaBean.get("queryString");

		String id = (String) dynaBean.get("id");
		/*
		 * if (StringUtils.isBlank(id)) { if (isCancelled(request)) { return list(mapping, form, request, response); }
		 * if (!isTokenValid(request)) { saveError(request, "errors.token"); return list(mapping, form, request,
		 * response); } resetToken(request); }
		 */
		if (StringUtils.isBlank(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			if (null == user) {
				return null;
			}

			String plan_id = (String) dynaBean.get("plan_id");
			if (!GenericValidator.isLong(plan_id)) {
				return null;
			}
			KonkaYjglPlan kp = new KonkaYjglPlan();
			kp.setId(Long.valueOf(plan_id));
			kp = super.getFacade().getKonkaYjglPlanService().getKonkaYjglPlan(kp);
			if (kp == null) {
				return null;
			}

			KonkaYjglPlanFp entity = new KonkaYjglPlanFp();
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(user.getId());
			entity.setDept_id(kp.getDept_id());
			entity.setIs_del(0);
			entity.setPlan_id(Long.valueOf(plan_id));
			entity.setPlan_year(kp.getPlan_year());
			entity.setType_id(kp.getType_id());
			entity.setIs_confirm(0);

			String[] store_ids = request.getParameterValues("store_id"); // 产品ID
			String[] nums = request.getParameterValues("num"); // 数量
			String[] pd_ids = request.getParameterValues("goods_id"); // 商品id
			String[] remarks = request.getParameterValues("remark"); // 备注
			String[] sy_dates = request.getParameterValues("sy_date"); // 备注
			// super.copyProperties(entity, form);// 接受QueryString等字段

			List<KonkaYjglPlanFp> konkaYjglPlanFpList = new ArrayList<KonkaYjglPlanFp>();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			for (int i = 0; i < store_ids.length; i++) {// 0.1.2
				KonkaYjglPlanFp cur = new KonkaYjglPlanFp();
				super.copyProperties(cur, entity);
				String store_id = store_ids[i];
				String num = nums[i];
				String pd_id = pd_ids[i];
				String remark = remarks[i];
				String sy_date = sy_dates[i];
				if (store_id.equals("") || store_id == null) {// 如果为空字符串，跳出循环
					continue;
				}
				cur.setShop_id(Long.valueOf(store_id));
				cur.setNum(Integer.valueOf(num));
				cur.setPd_id(Long.valueOf(pd_id));
				cur.setRemark(remark);
				cur.setSy_date(sf.parse(sy_date));
				if (StringUtils.isNotBlank(pd_id)) {
					pd_id = pd_id.trim();
					PePdModel pePdModel = new PePdModel();
					pePdModel.setPd_id(Long.valueOf(pd_id));
					pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
					if (pePdModel != null) {
						cur.setPd_name(pePdModel.getMd_name());
					} else {
						super.renderJavaScript(response,
								"window.onload=function(){alert('系统中不存在您选择的型号，请重新选择型号并填报！');history.back();}");
						return null;
					}
				} else {
					super.renderJavaScript(response,
							"window.onload=function(){alert('未选择型号，请选择型号并填报！');history.back();}");
					return null;
				}
				konkaYjglPlanFpList.add(cur);
			}

			super.getFacade().getKonkaYjglPlanFpService().createKonkaYjglPlanFpList(konkaYjglPlanFpList);
			super.saveMessage(request, "entity.inserted");
		} else {
			KonkaYjglPlanFp entity = new KonkaYjglPlanFp();
			super.copyProperties(entity, form);

			PePdModel pd = new PePdModel();
			pd.setPd_id(entity.getPd_id());
			pd = super.getFacade().getPePdModelService().getPePdModel(pd);
			if (pd != null) {
				entity.setPd_name(pd.getMd_name());
			}

			entity.setId(Long.valueOf(id));
			super.getFacade().getKonkaYjglPlanFpService().modifyKonkaYjglPlanFp(entity);
			super.saveMessage(request, "entity.updated");

		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward confirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			return null;
		}

		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaYjglPlanFp entity = new KonkaYjglPlanFp();
		entity.setId(new Long(id));
		entity.setConfirm_date(new Date());
		entity.setConfirm_user_id(peProdUser.getId());
		entity.setIs_confirm(1);// 确认
		super.getFacade().getKonkaYjglPlanFpService().modifyKonkaYjglPlanFp(entity);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward ajaxSetPlanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String plan_id = (String) dynaBean.get("plan_id");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");
		if (StringUtils.isBlank(plan_id)) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaYjglPlan kp = new KonkaYjglPlan();
		kp.setId(Long.valueOf(plan_id));
		kp = super.getFacade().getKonkaYjglPlanService().getKonkaYjglPlan(kp);

		KonkaYjglPlanFp kf = new KonkaYjglPlanFp();
		kf.setPlan_id(Long.valueOf(plan_id));
		kf.setIs_confirm(1);
		List<KonkaYjglPlanFp> kfList = super.getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFpList(kf);
		BigDecimal used_ed = new BigDecimal("0.00");

		if (kfList != null && kfList.size() > 0) {
			for (KonkaYjglPlanFp konkaYjglPlanFp : kfList) {
				// konkaYjglPlanFp.getPd_id();根据商品id 去查询它的限款价
				used_ed = used_ed.add(new BigDecimal("50"));
			}
		}

		if (kp == null) {
			sb = sb.append("0");
		} else {
			sb = sb.append("1").append(",");
			sb = sb.append("\"plan_year\":\"").append(kp.getPlan_year().toString()).append("\"");
			sb = sb.append(",\"plan_ed\":\"").append(kp.getPlan_ed().toString()).append("\"");
			sb = sb.append(",\"used_ed\":\"").append(used_ed.toString()).append("\"");
		}
		sb = sb.append("}");
		logger.info("sb {}", sb);
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * 上样
	 */
	public ActionForward upGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
		PeProdUser user = (PeProdUser) getSessionAttribute(request, Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		KonkaYjglPlanFp entity = new KonkaYjglPlanFp();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFp(entity);

		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// 查门店名称
		if (null != entity && null != entity.getShop_id()) {
			KonkaR3Store store = new KonkaR3Store();
			store.setStore_id(entity.getShop_id());
			store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
			dynaBean.set("store_name", store.getStore_name());
		}

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("date", sf.format(date));

		return new ActionForward("/admin/KonkaYjglPlanFpForSy/upGoods.jsp");
	}

	public ActionForward saveUpGoods(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// 修改的逻辑
		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id)) {
			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}

			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		KonkaMobileTestData entity = new KonkaMobileTestData();
		// 备注
		String memo = (String) dynaBean.get("memo");
		// 销量
		String num = (String) dynaBean.get("num");
		// 价格
		String price = (String) dynaBean.get("price");
		String add_date = (String) dynaBean.get("sy_date");
		String down_date = (String) dynaBean.get("down_date");
		String code = (String) dynaBean.get("code");
		// 型号
		String pd_id = (String) dynaBean.get("pd_id");

		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		String hms = sf.format(new Date());

		KonkaYjglPlanFp fp = new KonkaYjglPlanFp();
		fp.setId(Long.valueOf(id));
		fp = super.getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFp(fp);

		if (fp == null) {
			return null;
		}

		int total_num = fp.getNum() == null ? 0 : fp.getNum().intValue();
		int sy_num = 0;
		int xy_num = 0;

		KonkaMobileTestData mt = new KonkaMobileTestData();
		mt.setPlan_fp_id(Long.valueOf(id));
		mt.setStatus(0);
		List<KonkaMobileTestData> mtList = super.getFacade().getKonkaMobileTestDataService()
				.getKonkaMobileTestDataList(mt);
		if (null == mtList || mtList.size() == 0) {
			sy_num = 0;
			xy_num = 0;
		} else {
			for (KonkaMobileTestData kd : mtList) {
				if (kd.getUp_date() != null) {
					if (kd.getUp_date().getTime() < new Date().getTime()
							&& (kd.getDown_date() == null || new Date().getTime() < kd.getDown_date().getTime())) {
						sy_num++;
					} else if (kd.getUp_date().getTime() > new Date().getTime()
							|| kd.getDown_date().getTime() < new Date().getTime()) {
						xy_num++;
					}
				}
			}
		}

		if ((total_num - sy_num - xy_num) <= 0) {
			super.renderJavaScript(response,
					"window.onload=function(){alert('已经没有样机可上样了，请不要重复操作！');window.history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(add_date)) {
			entity.setUp_date(df.parse(add_date + " " + hms));
		}
		if (StringUtils.isNotBlank(down_date)) {
			entity.setDown_date(df.parse(down_date + " " + hms));
		}

		// 提交的时候验证串码是否重复
		KonkaMobileTestData kd = new KonkaMobileTestData();
		kd.setStatus(0);// 有效的
		if(StringUtils.isNotBlank(code)){
			String[] code_arry =code.split(",");
			kd.setStatus(0);// 有效的
			kd.getMap().put("code_arry",code_arry);
			//验正是否重复
			List<KonkaMobileTestData> KonkaMobileTestDataList = super.getFacade().getKonkaMobileTestDataService()
	        .getselectKonkaMobileTestDataAndCode(kd);

			if (null!=KonkaMobileTestDataList &&KonkaMobileTestDataList.size()> 0 ) {
				String msg = "串码";
				int i=0;
				for (KonkaMobileTestData konkaMobileTestData : KonkaMobileTestDataList) {
					if (Integer.parseInt(konkaMobileTestData.getMap().get("countCode").toString())>0) {
						msg+=konkaMobileTestData.getMap().get("code")+",";
						i=2;
					}
				}
				if(i==2)
				{
					msg+="已经重复， 请重新提交。";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg
				        + "');window.history.back();}");
					return null;
				}
			}
		}
		if (StringUtils.isNotBlank(code)) {
			entity.setCode(code);
		}

		/*
		 * if (StringUtils.isNotBlank(down_cause)&&StringUtils.isNumeric(down_cause )) {
		 * entity.setDown_cause(Integer.valueOf(down_cause)); }
		 */
		if (StringUtils.isNotBlank(down_date)) {
			if (StringUtils.isNotBlank(add_date)) {
				String upStr = df.format(entity.getUp_date());
				Long upLong = Long.valueOf(upStr.replace(":", "").replace("-", "").replaceAll(" ", ""));

				String downStr = df.format(entity.getDown_date());
				Long downLong = Long.valueOf(downStr.replace(":", "").replace("-", "").replaceAll(" ", ""));

				if (upLong > downLong) {
					super.renderJavaScript(response, "alert('下架时间不得小于上架时间！');history.back();");
					return null;
				}
			}
		}
		entity.setMemo(memo);
		entity.setReport_date(new Date());

		// 分公司
		KonkaDept fgs = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		if (null != fgs) {
			entity.setSubcomp_id(fgs.getDept_id());
			entity.setSubcomp_name(fgs.getDept_name());
		} else {
			entity.setSubcomp_id(peProdUser.getDept_id());
			entity.setSubcomp_name(peProdUser.getDepartment());
		}

		// 销量
		if (StringUtils.isNotEmpty(num)) {
			if (NumberUtils.isNumber(num)) {
				entity.setNum(Long.parseLong(num));
			} else {
				super.renderJavaScript(response, "alert('销量必须为数字！');history.back();");
				return null;
			}
		} else {
			super.renderJavaScript(response, "alert('请填写数量！');history.back();");
			return null;
		}

		// 型号名称
		if (!"0".equalsIgnoreCase(num)) {
			if (StringUtils.isNotEmpty(pd_id)) {
				pd_id = pd_id.trim();
				entity.setModel_id(new Long(pd_id));
				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(Long.valueOf(pd_id));
				pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (pePdModel != null) {
					entity.setModel_name(pePdModel.getMd_name());
					entity.setCategory_id(pePdModel.getCls_id());
					entity.setPct_code(pePdModel.getMat_num());
					if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size()))
						entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
					entity.setMeasure_name(pePdModel.getMd_size());
				} else {
					super.renderJavaScript(response, "alert('型号选择出错，请重新选择型号并填报！');history.back();");
					return null;
				}
			} else {
				super.renderJavaScript(response, "alert('请选择型号！');history.back();");
				return null;
			}
		}

		// 门店/客户
		String store_id = (String) dynaBean.get("shop_id");
		if (StringUtils.isBlank(store_id)) {
			super.renderJavaScript(response, "alert('请选择门店！');history.back();");
			return null;
		}

		store_id = store_id.trim();
		entity.setDept_id(new Long(store_id));
		KonkaR3Store r3s = new KonkaR3Store();
		r3s.setStore_id(Long.parseLong(store_id));
		r3s = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(r3s);
		if (r3s != null) {
			entity.setDept_name(r3s.getStore_name());

			// 经办
			// entity.setOffice_id(r3s.getJb_job_id());
			entity.setOffice_name(r3s.getJb_name());

			// 查询客户信息
			if (null != r3s.getR3_code()) {
				KonkaR3Shop s = new KonkaR3Shop();
				s.setR3_code(StringUtils.upperCase(r3s.getR3_code()));
				s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

				if (null != s) {
					entity.setChannel_a_id(s.getId());
					entity.setChannel_a_name(s.getCustomer_name());
					entity.setChannel_b_name(s.getR3_code());

					if (GenericValidator.isLong(s.getCustomer_type())) {
						entity.setChannel_b_id(new Long(s.getCustomer_type()));

						KonkaCategory kc = new KonkaCategory();
						kc.setC_index(entity.getChannel_b_id());
						kc.setC_type(10);
						kc = super.getFacade().getKonkaCategoryService().getKonkaCategory(kc);

						if (null != kc) {
							entity.setOrg_name("[" + kc.getC_comm() + "]" + kc.getC_name());
						}
					}
				}
			}
		}

		// 单价
		if (StringUtils.isNotBlank(price)) {
			entity.setPrice(new BigDecimal(price));
		}

		entity.setStatus(0);
		entity.setReport_id(peProdUser.getId());
		entity.setReport_name(peProdUser.getReal_name());

		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			entity.setPlan_fp_id(Long.valueOf(id));
		}

		Long newId = super.getFacade().getKonkaMobileTestDataService().createKonkaMobileTestData(entity);
		// generateHis(entity, null, null, null, null, null, null);

		// 拿到上传的图片附件
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		Attachment attachment = null;
		UploadFile uploadFile = null;
		for (int i = 0; i < uploadFileList.size(); i++) {
			uploadFile = uploadFileList.get(i);
			attachment = new Attachment();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Integer(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setLink_tab("KONKA_MOBILE_TEST_DATA");
			if ("front".equals(uploadFile.getFormName())) {
				attachment.setFile_desc("正面");
			} else if ("back".equals(uploadFile.getFormName())) {
				attachment.setFile_desc("背面");
			} else if ("side".equals(uploadFile.getFormName())) {
				attachment.setFile_desc("侧面");
			} else {
				attachment.setFile_desc(uploadFile.getFormName());
			}
			Short isdel = new Short("0");
			attachment.setDel_mark(isdel);
			if (null != newId) {
				attachment.setLink_id(newId);
				super.getFacade().getAttachmentService().createAttachment(attachment);
			}
		}
		super.saveMessage(request, "entity.submit.success");
		return new ActionForward("/../manager/admin/KonkaYjglPlanFpForSy.do?method=upself&id=" + id + "&mod_id"
				+ mod_id);
	}

	public List<Long> getR3StoreIds(Long user_id, Long role_id) {

		List<Long> store_ids = new ArrayList<Long>();
		if (role_id.intValue() == 60) {// 业务员
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", user_id);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaR3Store konkaR3Store : storeList) {
					store_ids.add(konkaR3Store.getStore_id());
				}
			}
		} else if (role_id.intValue() == 188) {// 促销员
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(user_id); // 登录用户ID作为促销员的ID
			storeList = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaMobileSpRelation t : storeList) {
					KonkaR3Store store = new KonkaR3Store();
					if (null != t && null != t.getShop_id()) {
						store.setStore_id(t.getShop_id());
						store.setIs_del(0);
						store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
						if (null != store) {
							store_ids.add(store.getStore_id());
						}
					}

				}
			}
		} else {
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", user_id);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaR3Store konkaR3Store : storeList) {
					store_ids.add(konkaR3Store.getStore_id());
				}
			}

			List<KonkaMobileSpRelation> storeList1 = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(user_id); // 登录用户ID作为促销员的ID
			storeList1 = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);
			if (storeList1 != null && storeList1.size() > 0) {
				for (KonkaMobileSpRelation t : storeList1) {
					KonkaR3Store store1 = new KonkaR3Store();
					if (null != t && null != t.getShop_id()) {
						store1.setStore_id(t.getShop_id());
						store1.setIs_del(0);
						store1 = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store1);
						if (null != store1) {
							store_ids.add(store1.getStore_id());
						}
					}

				}
			}

		}
		//System.out.println("store_ids" + store_ids);

		return store_ids;
	}

}