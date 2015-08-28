package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcThRecord;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Guo,Jun
 * @version 2011-10-10
 */
public class KonkaJxcThAuditAction extends BaseJxcAction {
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
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		Pager pager = (Pager) dynaBean.get("pager");

		PeProdUser userInfo = super.getSessionUserInfo(request);
		PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(userInfo.getId());

		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		super.copyProperties(entity, form);
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		entity.setBrand_id((Constants.KONKA_BRAND_ID));// 退货只限康佳品牌
		entity.getMap().put("son_dept_id", userInfo.getDept_id());// 根据自己部门查询所有下级部门的退货申请
		if (peRoleInfo.getRole_id().intValue() == 60) {// 业务员,根据user_id查询分配网点
			entity.getMap().put("user_id", userInfo.getId());
		} else {// 查找下级所有部门，分配的所有网点
			entity.getMap().put("dept_id", userInfo.getDept_id());
		}

		// 权限控制,根据PeRoleInfo中的角色
		// 事业部审批分公司退货申请
		// 分公司审批网点退货申请
		// 经营部、办事处使用所属分公司的审批功能审批网点退货申请
		/*
		 * if (null != peRoleInfo && null != peRoleInfo.getRole_id()) { String deptIds = ""; if (peRoleInfo.getRole_id()
		 * == 20) {// ===事业部=== // 选取下属【分公司】List KonkaDept konkaDept = new KonkaDept();
		 * konkaDept.setPar_id(userInfo.getDept_id()); List<KonkaDept> branchDeptList =
		 * getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept); for (KonkaDept temp : branchDeptList) {
		 * deptIds += temp.getDept_id() + ","; } entity.getMap().put("dept_ids", deptIds.substring(0,
		 * deptIds.lastIndexOf(","))); } else if (peRoleInfo.getRole_id() == 30 || peRoleInfo.getRole_id() == 40 ||
		 * peRoleInfo.getRole_id() == 50) {// ===分公司\\经营部、办事处=== // 管辖区域的【网点】List KonkaR3Shop konkaR3Shop = new
		 * KonkaR3Shop(); konkaR3Shop.getMap().put("konka_jxc_dept_id", userInfo.getDept_id()); List<KonkaR3Shop>
		 * konkaR3ShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop); for (KonkaR3Shop temp
		 * : konkaR3ShopList) { deptIds += temp.getId() + ","; } entity.getMap().put("shop_ids", deptIds.substring(0,
		 * deptIds.lastIndexOf(","))); } }
		 */

		Long recordCount = getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaJxcThRecord> entityList = getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);

		// 仓库信息列表
		KonkaJxcStoreInfo si = new KonkaJxcStoreInfo();
		si.setAdd_dept_id(userInfo.getDept_id());
		si.setIs_del(0);
		List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(si);
		request.setAttribute("storeList", storeList);

		return mapping.findForward("list");
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);

		PeProdUser userInfo = super.getSessionUserInfo(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcThRecord entity = new KonkaJxcThRecord();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordWith2Names(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			// 仓库信息列表
			KonkaJxcStoreInfo si = new KonkaJxcStoreInfo();
			si.setAdd_dept_id(userInfo.getDept_id());
			si.setIs_del(0);
			List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(si);
			request.setAttribute("storeList", storeList);
			dynaBean.set("add_date", entity.getAdd_date());

			Long role_id = entity.getAdd_user_type();
			Long dept_id = entity.getAdd_dept_id();
			if (null != role_id) {
				if (role_id == 30 || role_id == 40 || role_id == 50) {// ===分公司\\经营部、办事处===
					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_id(dept_id);
					konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
					if (null != konkaDept) {
						dynaBean.set("dept_name", konkaDept.getDept_name());
					}

					dynaBean.set("user_name", entity.getMap().get("ppu_name"));
				} else if (role_id == 60) {// ===网点===
					KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
					konkaR3Shop.setId(dept_id);
					konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
					if (null != konkaR3Shop) {
						dynaBean.set("dept_name", konkaR3Shop.getArea_name());
					}

					dynaBean.set("user_name", entity.getMap().get("seu_name"));
				}
			}

			// logger.info(entity.getMap().get("user_name") + "##########################");
			dynaBean.set("role_name", entity.getMap().get("role_name"));

			return mapping.findForward("input");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);

		PeProdUser userInfo = super.getSessionUserInfo(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaJxcThRecord entity = new KonkaJxcThRecord();
			entity.setId(Long.valueOf(id));
			entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecordWith2Names(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);

			// 仓库信息列表
			KonkaJxcStoreInfo si = new KonkaJxcStoreInfo();
			si.setAdd_dept_id(userInfo.getDept_id());
			si.setIs_del(0);
			List<KonkaJxcStoreInfo> storeList = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(si);
			request.setAttribute("storeList", storeList);
			dynaBean.set("add_date", entity.getAdd_date());

			Long role_id = entity.getAdd_user_type();
			Long dept_id = entity.getAdd_dept_id();
			if (null != role_id) {
				if (role_id == 30 || role_id == 40 || role_id == 50) {// ===分公司\\经营部、办事处===
					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_id(dept_id);
					konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
					if (null != konkaDept) {
						dynaBean.set("dept_name", konkaDept.getDept_name());
					}

					dynaBean.set("user_name", entity.getMap().get("ppu_name"));
				} else if (role_id == 60) {// ===网点===
					KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
					konkaR3Shop.setId(dept_id);
					konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
					if (null != konkaR3Shop) {
						dynaBean.set("dept_name", konkaR3Shop.getArea_name());
					}

					dynaBean.set("user_name", entity.getMap().get("seu_name"));
				}
			}

			dynaBean.set("role_name", entity.getMap().get("role_name"));

			return mapping.findForward("view");
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}

		setNaviStringToRequestScope(form, request);

		PeProdUser userInfo = super.getSessionUserInfo(request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaJxcThRecord formEntity = new KonkaJxcThRecord();// 页面退货记录增加了审批内容
		KonkaJxcThRecord entity = new KonkaJxcThRecord();
		if (null == userInfo) {
			return mapping.findForward("list");
		}

		super.copyProperties(formEntity, form);// copy取值
		if (null == formEntity.getId()) {
			saveMessage(request, "entity.missing");
			// entity.setQueryString("");
		}

		entity.setId(formEntity.getId());
		entity = super.getFacade().getKonkaJxcThRecordService().getKonkaJxcThRecord(entity);// 查询退货记录
		if (null != entity) {

			entity.setIs_audit(formEntity.getIs_audit());// 审批状态
			entity.setAudit_reason(formEntity.getAudit_reason());// 审批原因
			entity.setApproval_date(new Date());
			entity.setAudit_user_id(userInfo.getId());
			entity.setAudit_dept_id(userInfo.getDept_id());
			entity.setAudit_user_type(getPeRoleInfoByUserId(userInfo.getId()).getRole_id());
			entity.setUpdate_user_id(userInfo.getId());
			entity.setUpdate_date(new Date());
			if (formEntity.getIs_audit() == -1) {// 不同意，直接更新退货记录状态
				entity.getMap().put("audit", true);// 管理端审批
				super.getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);
				entity.setQueryString(formEntity.getQueryString());
				saveMessage(request, "entity.updated");
			} else {// 同意时进行记录、更新库存、插入库存记录

				// 先验证网点端的型号本地有没有，没有不予通过
				if (null != entity.getShop_id()) {
					JxcPd jxcPd = super.getJxcPdById(entity.getPd_id());
					// jxcPd.setId(entity.getPd_id());
					// jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
					// 网点端退货端退货:网点端退货记录存的是jxc_pd表ID，如果是康佳产品要找到表的out_sys_id才能对应到库存状态中存的pe_pd_model表ID
					PePdModel pePdModel = new PePdModel();
					if (null != jxcPd) {
						pePdModel.setPd_id(jxcPd.getOut_sys_id());
						pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
						if (null == pePdModel) {
							String msg = "产品库中无该型号产品，不能审批通过，请检查产品库！";
							super.renderJavaScript(response, "alert('" + msg + "');history.back();");
							return null;
						}
					}

					entity.setPePdModel(pePdModel);
					//logger.info("entity.getPePdModel--" + entity.getPePdModel().getPd_id() + "11111");
				}

				entity.setTh_store_id_par(formEntity.getTh_store_id_par());
				KonkaJxcStoreInfo konkaJxcStoreInfo = super.getKonkaStockById(formEntity.getTh_store_id_par());
				if (null != konkaJxcStoreInfo) {
					entity.getMap().put("storeName", konkaJxcStoreInfo.getStore_name());
				}
				entity.getMap().put("audit", true);// 管理端审批
				entity.getMap().put("par_dept_id", userInfo.getPar_dept_id());// 添加人上级部门Id进货记录使用
				entity.getMap().put("own_user_name", userInfo.getUser_name());// 添加人部门Id进货记录使用
				KonkaDept dept = super.getKonkaDeptById(userInfo.getDept_id());
				if (null != dept) {
					entity.getMap().put("own_dept_name", dept.getDept_name());
				}

				super.getFacade().getKonkaJxcThRecordService().modifyKonkaJxcThRecord(entity);
				saveMessage(request, "entity.updated");
			}
		} else {
			saveMessage(request, "entity.missing");
			entity = new KonkaJxcThRecord();
			// entity.setQueryString("");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(formEntity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}