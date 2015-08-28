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
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaYjglPlanFp;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaYjglPlanFpTjAction extends MobileBaseAction {

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
		String is_tq = (String) dynaBean.get("is_tq");
		String is_cy = (String) dynaBean.get("is_cy");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String cxy_name_like = (String) dynaBean.get("cxy_name_like");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

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
			entity.getMap().put("dept_id", (super.getSuperiorForDeptType(peProdUser.getDept_id(), 3).getDept_id()));
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
			// entity.getMap().put("store_ids", s_ids);
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(cxy_name_like)) {
			entity.getMap().put("cxy_name_like", cxy_name_like);
		}
		if (StringUtils.isNotBlank(is_tq)) {
			if (is_tq.equals("是") || is_tq.equals("否")) {
				entity.getMap().put("is_tq", is_tq);
			}
		}
		if (StringUtils.isNotBlank(is_cy)) {
			if (is_cy.equals("0")) {
				entity.getMap().put("is_cy", true);
			} else if (is_cy.equals("1")) {
				entity.getMap().put("is_not_cy", true);
			}
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}

		Long recordCount = super.getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFpAndTjCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaYjglPlanFp> entityList = super.getFacade().getKonkaYjglPlanFpService()
		        .getKonkaYjglPlanFpAndTjPaginatedList(entity);
		if (entityList != null && entityList.size() > 0) {
			for (KonkaYjglPlanFp konkaYjglPlanFp : entityList) {
				String save_path = (String) konkaYjglPlanFp.getMap().get("save_path");
				if (save_path != null && !save_path.equals("")) {
					String[] ss = save_path.split(",");
					if (ss.length > 0) {
						for (String xx : ss) {// 22#背面,33#正面
							//System.out.println("xx-->" + xx);
							if (xx.contains("正面")) {
								konkaYjglPlanFp.getMap().put("zm", xx.split("&")[0]);
							} else if (xx.contains("背面")) {
								konkaYjglPlanFp.getMap().put("bm", xx.split("&")[0]);
							} else if (xx.contains("侧面")) {
								konkaYjglPlanFp.getMap().put("cm", xx.split("&")[0]);
							}
						}
					}
				}
			}
		}

		request.setAttribute("entityList", entityList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("list");
	}

	public List<Long> getR3StoreIds(Long user_id, Long role_id) {

		List<Long> store_ids = new ArrayList<Long>();
		if (role_id.intValue() == 60) {// 业务员
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", user_id);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
			        store);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaR3Store konkaR3Store : storeList) {
					store_ids.add(konkaR3Store.getStore_id());
				}
			}
		} else if (role_id.intValue() == 188) {// 促销员
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(user_id); // 登录用户ID作为促销员的ID
			storeList = super.getFacade().getKonkaMobileSpRelationService().getKonkaMobileSpRelationInShopNameList(
			        entity);
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
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
			        store);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaR3Store konkaR3Store : storeList) {
					store_ids.add(konkaR3Store.getStore_id());
				}
			}

			List<KonkaMobileSpRelation> storeList1 = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(user_id); // 登录用户ID作为促销员的ID
			storeList1 = super.getFacade().getKonkaMobileSpRelationService().getKonkaMobileSpRelationInShopNameList(
			        entity);
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