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
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class R3UserAssignAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);

		super.encodeCharacterForGetMethod(dynaBean, request);
		String keyword = (String) dynaBean.get("keyword");
		String code_like = (String) dynaBean.get("code_like");
		String unassignywy = (String) dynaBean.get("unassignywy");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String is_match = (String) dynaBean.get("is_match");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		// 添加权限
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long user_dept_id = userInfo.getDept_id();
		if (ui.getDept_id() != null && ui.getDept_id() > user_dept_id) {
			user_dept_id = ui.getDept_id();
		}

		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setUser_id(userInfo.getId());
		// peRoleUser =
		// this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		dynaBean.set("match", is_match);

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaR3Shop entity = new KonkaR3Shop();

		super.copyProperties(entity, form);

		entity.setIs_del(new Long(0));
		if (!GenericValidator.isLong(is_match)) {
			entity.getMap().put("is_assign", "true");
		} else {
			entity.getMap().put("is_assign", "true");
			entity.setIs_match(Long.valueOf(is_match));

		}

		entity.setIs_del(new Long(0));

		if (StringUtils.isNotBlank(unassignywy)) {
			entity.getMap().put("unAssignYWY", "true");
			entity.setBranch_area_name_2(dept_sn);
		}

		// PeRoleUser peProdUser = new PeRoleUser();
		// peProdUser.setUser_id(ui.getId());
		// peProdUser =
		// getFacade().getPeRoleUserService().getPeRoleUser(peProdUser);
		KonkaDept dept = new KonkaDept();

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_le_29 = false;
		boolean role_id_ge_30_le_39 = false;
		boolean role_id_ge_40_lt_60 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 10 && peRoleUser.getRole_id() <= 29) {
				role_id_ge_10_le_29 = true;
			}
			if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 39) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 40 && peRoleUser.getRole_id() < 60) {
				role_id_ge_40_lt_60 = true;
			}
			if (peRoleUser.getRole_id() == 60) {
				role_id_eq_60 = true;
			}
		}

		if (role_id_ge_10_le_29) {// 管理员和事业部
			dynaBean.set("key", "true");
		}
		if (role_id_ge_30_le_39) {// 若登录用户属于分公司
			dept.setDept_id(ui.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap().put("branch_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_name_like", "1");
			}
			dynaBean.set("addBatchPopedom", null);
		} else if (role_id_ge_40_lt_60) {
			entity.getMap().put("konka_dept_id", ui.getDept_id());
			dynaBean.set("addBatchPopedom", null);
		} else if (role_id_eq_60) {
			entity.getMap().put("ywy_user_id", ui.getId());
		} else {
			dynaBean.set("addBatchPopedom", 1);
		}

		entity.getMap().put("keyword", keyword);
		entity.getMap().put("code_like", code_like);
		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);

		super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward shopDispach(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ActionForward("/admin/KonkaBranchAssign/pe_prod_user.jsp");
	}

	public ActionForward listPeProdUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");

		PeProdUser entity = new PeProdUser();
		entity.setIs_del(0);
		entity.setPosition_id(Long.valueOf(0));
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		Long recordCount = super.getFacade().getPeProdUserService().getPeProdUserCount(entity);
		pager.init(recordCount, Integer.valueOf("45"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(Integer.valueOf("45"));
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/PeShop/list_peproduser.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (isCancelled(request)) {
			return list(mapping, form, request, response);
		}
		if (!isTokenValid(request)) {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
		}
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String str_pks = (String) dynaBean.get("str_pks");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String category_id = (String) dynaBean.get("category_id");
		String ywy_user_id_old = (String) dynaBean.get("ywy_user_id_old");
		String peShopCategoryId = (String) dynaBean.get("peShopCategoryId");

		String[] peShopCategoryIds = StringUtils.split(peShopCategoryId, ",");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		if (StringUtils.isNotBlank(str_pks)) {
			BranchAssign entity = new BranchAssign();
			// 用于修改客户地址
			KonkaR3Shop  konkaR3ShopEntity = new KonkaR3Shop();
			super.copyProperties(entity, form);
			String[] pks = str_pks.split(",");
			for (int i = 0; i < pks.length; i++) {

				Long konka_r3_id = Long.valueOf(pks[i].toString());

				entity.setKonka_r3_id(konka_r3_id);
				if (GenericValidator.isLong(ywy_user_id)) {
					entity.setUser_id(new Long(ywy_user_id));
				}
				if (GenericValidator.isLong(fgs_dept_id)) {
					entity.setFgs_id(new Long(fgs_dept_id));
					// entity.setSyb_id(super.getSuperiorForDeptType(new
					// Long(fgs_dept_id), 2).getDept_id());
				}
				if (GenericValidator.isLong(jyb_dept_id)) {
					entity.setJyb_id(new Long(jyb_dept_id));
				}
				if (GenericValidator.isLong(bsc_dept_id)) {
					entity.setBsc_id(new Long(bsc_dept_id));
				}
				entity.setBranch_type(1);
				entity.setAdd_user_id(userInfo.getId());
				entity.setAdd_date(new Date());

				if (GenericValidator.isLong(category_id)) {
					KonkaBranchCategory kbc = new KonkaBranchCategory();
					kbc.setKonka_r3_id(entity.getKonka_r3_id());
					kbc.setJxs_r3_id(entity.getKonka_r3_id());
					getFacade().getKonkaBranchCategoryService().removeKonkaBranchCategory(kbc);

					kbc.setCategory_id(Long.valueOf(category_id));

					KonkaBranchCategory kb = new KonkaBranchCategory();
					if (kbc.getCategory_id() == 10) {
						kb.setKonka_r3_id(entity.getKonka_r3_id());
						kb.setCategory_id(10L);
						kb.setAdd_date(new Date());
						getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(kbc);
					} else if (kbc.getCategory_id() == 20) {
						// CATEGORY_ID = 10 为代理商
						// CATEGORY_ID = 20 为经销商
						if (StringUtils.isNotBlank(r3_shop_id)) {
							kb.setKonka_r3_id(Long.valueOf(r3_shop_id));// 代理商ID
							KonkaR3Shop r3 = new KonkaR3Shop();// r3.getMap().put("is_assign",
							// true);
							r3.setId(entity.getKonka_r3_id());
							r3 = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3);
							if (r3 != null && r3.getMap().get("mmt_shop_id") != null) {
								// kb.setMmt_shop_id(Long.valueOf(r3.getMap().get("mmt_shop_id").toString()));
								kb.setCategory_id(20L);
								kb.setJxs_r3_id(entity.getKonka_r3_id());
								getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(kb);
							}
						}
					}
					if (peShopCategoryIds != null && peShopCategoryIds.length > 0) {
						for (int j = 0; j < peShopCategoryIds.length; j++) {
							kbc.setCategory_id(Long.valueOf(peShopCategoryIds[j]));
							getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(kbc);
						}
					}
				}

				// 新增业务员更改记录 start
				if (StringUtils.isNotBlank(ywy_user_id_old) && StringUtils.isNotBlank(ywy_user_id)
						&& !ywy_user_id.equals(ywy_user_id_old)) {
					KonkaStoreUserCInfo ksuc = new KonkaStoreUserCInfo();
					PeProdUser p1 = new PeProdUser();
					p1.setId(Long.valueOf(ywy_user_id_old));
					p1 = super.getFacade().getPeProdUserService().getPeProdUser(p1);

					PeProdUser p2 = new PeProdUser();
					p2.setId(Long.valueOf(ywy_user_id));
					p2 = super.getFacade().getPeProdUserService().getPeProdUser(p2);

					ksuc.setAdd_user_id(userInfo.getId());
					ksuc.setAdd_user_job_id(userInfo.getJob_id());
					ksuc.setAdd_user_name(userInfo.getUser_name());
					ksuc.setC_type(20);
					String user_old="";
					if(p1 != null && p1.getUser_name() !=null){
						user_old = p1.getUser_name()+"";
					}
					String user_new="";
					if(p2 != null && p2.getUser_name() !=null){
						user_new = p2.getUser_name()+"";
					}
					ksuc.setChange_info("该R3客户的业务员：“" + user_old + "”更改为“" + user_new + "”。");

						KonkaR3Shop krs = new KonkaR3Shop();
						krs.setId(Long.valueOf(konka_r3_id));
						krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
						if (null != krs) {
							ksuc.setSs_id(Long.valueOf(konka_r3_id));
							ksuc.setSs_name(krs.getCustomer_name());
						}
					super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(ksuc);
				} else if (StringUtils.isNotBlank(ywy_user_id_old) && StringUtils.isBlank(ywy_user_id)) {
					KonkaStoreUserCInfo ksuc = new KonkaStoreUserCInfo();
					PeProdUser p1 = new PeProdUser();
					p1.setId(Long.valueOf(ywy_user_id_old));
					p1 = super.getFacade().getPeProdUserService().getPeProdUser(p1);
					
					ksuc.setAdd_user_id(userInfo.getId());
					ksuc.setAdd_user_job_id(userInfo.getJob_id());
					ksuc.setAdd_user_name(userInfo.getUser_name());
					ksuc.setC_type(20);
					ksuc.setChange_info("该R3客户的业务员：“" + p1.getUser_name() + "”已经移除。");
					
					
						KonkaR3Shop krs = new KonkaR3Shop();
						krs.setId(Long.valueOf(konka_r3_id));
						krs = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(krs);
						if (null != krs) {
							ksuc.setSs_id(Long.valueOf(konka_r3_id));
							ksuc.setSs_name(krs.getCustomer_name());
						}
					super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(ksuc);
					
				}

				// end
				getFacade().getBranchAssignService().createBranchAssignAndBranchCategory(entity);
				
				// 修改客户地址
				konkaR3ShopEntity.setId(konka_r3_id);
				
				String country = (String) dynaBean.get("country_"+konka_r3_id);
				String town = (String) dynaBean.get("town_"+konka_r3_id);
				if(StringUtils.isNotEmpty(town)){
					if (GenericValidator.isLong(town)) {
						konkaR3ShopEntity.setEntp_p_index(new Long(town));
					}
				}else{
					if (GenericValidator.isLong(country)) {
						konkaR3ShopEntity.setEntp_p_index(new Long(country));
					}
				}
				getFacade().getKonkaR3ShopService().modifyKonkaR3ShopByID(konkaR3ShopEntity);
			}
			saveMessage(request, "entity.updated");
		}

		BranchAssign entity = new BranchAssign();
		super.copyProperties(entity, form);
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		// pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("/../manager/admin/KonkaR3MmtMatch.do?");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// Object createKonkaBranchAssign(KonkaBranchAssign entity, String
	// fgs_dept_id, String jyb_dept_id,String
	// bsc_dept_id,
	// String ywy_user_id, String category_id, String[] peShopCategoryId) throws
	// Exception {
	// if (GenericValidator.isLong(ywy_user_id)) {
	// entity.setUser_id(new Long(ywy_user_id));
	// getFacade().getKonkaBranchAssignService().createKonkaBranchAssign(entity);
	// }
	// if (GenericValidator.isLong(fgs_dept_id)) {
	// entity.setUser_id(null);
	// entity.setDept_id(new Long(fgs_dept_id));
	// getFacade().getKonkaBranchAssignService().createKonkaBranchAssign(entity);
	// }
	// if (GenericValidator.isLong(jyb_dept_id)) {
	// entity.setUser_id(null);
	// entity.setDept_id(new Long(jyb_dept_id));
	// getFacade().getKonkaBranchAssignService().createKonkaBranchAssign(entity);
	// }
	// if (GenericValidator.isLong(bsc_dept_id)) {
	// entity.setUser_id(null);
	// entity.setDept_id(new Long(bsc_dept_id));
	// getFacade().getKonkaBranchAssignService().createKonkaBranchAssign(entity);
	// }
	// if (GenericValidator.isLong(category_id)) {
	// KonkaBranchCategory kbc = new KonkaBranchCategory();
	// kbc.setKonka_r3_id(entity.getKonka_r3_id());
	// getFacade().getKonkaBranchCategoryService().removeKonkaBranchCategory(kbc);
	// kbc.setCategory_id(Long.valueOf(category_id));
	// if (kbc.getCategory_id() != -1) {
	// getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(kbc);
	// }
	// if (peShopCategoryId != null && peShopCategoryId.length > 0) {
	// for (int i = 0; i < peShopCategoryId.length; i++) {
	// kbc.setCategory_id(Long.valueOf(peShopCategoryId[i]));
	// getFacade().getKonkaBranchCategoryService().createKonkaBranchCategory(kbc);
	// }
	// }
	// }
	// return null;
	// }

	/**
	 * 显示分配页面
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		dynaBean.set("category_id", -1);
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		String[] pks = (String[]) dynaBean.get("pks");

		// 1)、系统管理员进入列表页显示所有R3系统导入的网点(9000)，如果没有分配则“上级部门字段为空”；
		// 2)、事业部登陆，可以强制把网点与别的管辖区域分公司建立联系；
		// 3)、分公司登陆，分配网点给相应的经营部、办事处等；
		// 4)、经营部/办事处登陆，使用此功能节点把网点分配给业务员；
		// 5)、“设置”中不仅可以选择分配上级部门，还以选择网的分类属性，属性可以叠加选择。

		if (!ArrayUtils.isEmpty(pks)) {
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			konkaR3Shop.getMap().put("selects", StringUtils.join(pks, ","));
			// konkaR3Shop.getMap().put("is_assign", true);
			List<KonkaR3Shop> konkaR3ShopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(
					konkaR3Shop);

			// for (KonkaR3Shop shop : konkaR3ShopList) {
			// if (shop.getMap().get("mmt_shop_id") == null) {
			// request.setAttribute("key", 1);
			// }
			// }

			request.setAttribute("konkaR3ShopList", konkaR3ShopList);

			if (konkaR3ShopList.size() == 1) {
				KonkaR3Shop t = konkaR3ShopList.get(0);

				KonkaBranchCategory kbc = new KonkaBranchCategory();
				kbc.setKonka_r3_id(t.getId());
				List<KonkaBranchCategory> kbcList = super.getFacade().getKonkaBranchCategoryService()
						.getKonkaBranchCategoryList(kbc);

				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				String[] dls_r3_ids = new String[pks.length];
				int j = 0;
				for (int i = 0; i < kbcList.size(); i++) {
					KonkaBranchCategory t1 = kbcList.get(i);
					if (t1.getCategory_id() == 10 && t1.getMmt_shop_id() == null && i<dls_r3_ids.length) {
						dls_r3_ids[j] = t1.getKonka_r3_id().toString();
						dynaBean.set("category_id", 10);
						j++;
					} else if (t1.getCategory_id() != 10 && t1.getMmt_shop_id() == null) {
						sb1.append(t1.getCategory_id().toString()).append(",");
						PeShopCategory pca = new PeShopCategory();
						pca.setCategory_id(t1.getCategory_id());
						pca = super.getFacade().getPeShopCategoryService().getPeShopCategory(pca);
						if (pca != null) {
							sb2.append(pca.getCategory_name()).append(",");
						}
					}
				}
				if (kbcList.size() == 0) {
					KonkaBranchCategory kb = new KonkaBranchCategory();
					kb.setJxs_r3_id(t.getId()); // 查询经销商的上级代理商
					List<KonkaBranchCategory> kbList = super.getFacade().getKonkaBranchCategoryService()
							.getKonkaBranchCategoryList(kb);
					for (int i = 0; i < kbList.size(); i++) {
						KonkaBranchCategory t2 = kbList.get(i);
						// if (t2.getCategory_id() == 20 && t2.getMmt_shop_id()
						// != null) {
						if (t2.getCategory_id() == 20) {
							KonkaR3Shop r3 = new KonkaR3Shop();
							r3.setId(t2.getKonka_r3_id());
							r3 = getFacade().getKonkaR3ShopService().getKonkaR3ShopForResult(r3);
							dynaBean.set("category_id", 20);
							dynaBean.set("customer_name", r3.getCustomer_name());
							dynaBean.set("r3_shop_id", r3.getId());
						}
					}
				}
				if (StringUtils.isNotBlank(dls_r3_ids[0])) {
					dynaBean.set("dls_r3_id", StringUtils.join(dls_r3_ids, ","));
					log.info("=============" + StringUtils.join(dls_r3_ids, ","));
				}
				dynaBean.set("peShopCategoryId", StringUtils.substring(sb1.toString(), 0, sb1.toString().length() - 1));
				dynaBean.set("peShopCategoryName", StringUtils
						.substring(sb2.toString(), 0, sb2.toString().length() - 1));
				request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, t
						.getId()));
			} else if (konkaR3ShopList.size() != 1) {
				request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
			}

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long");
			return mapping.findForward("list");
		}
	}

}