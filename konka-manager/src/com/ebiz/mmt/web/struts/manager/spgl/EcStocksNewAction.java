package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseStore;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-15
 */
public class EcStocksNewAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String id = (String) dynaBean.get("id");

		// HttpSession session = request.getSession();
		// PeProdUser user = (PeProdUser)
		// session.getAttribute(Constants.USER_INFO);
		// if (null == user) {
		// return null;
		// }

		KonkaBcompPd entity = new KonkaBcompPd();
		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}
		entity.setState(1);// 1表示正常
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		Pager pager = (Pager) dynaBean.get("pager");
		String store_type = (String) dynaBean.get("store_type");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcStocks es = new EcStocks();
		es.setGoods_id(Long.valueOf(id));
		es.getMap().put("store_type", store_type);

		Long recordCount = super.getFacade().getEcStocksService().getEcStocksForStoreTypeCount(es);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		es.getRow().setFirst(pager.getFirstRow());
		es.getRow().setCount(pager.getRowCount());
		List<EcStocks> entityList = super.getFacade().getEcStocksService().getEcStocksForStoreTypeList(es);

		dynaBean.set("goods_id", id);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("view");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_type = (String) dynaBean.get("store_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		// 取仓库
		EcBaseStore ebs = new EcBaseStore();
		ebs.setStore_type(Integer.valueOf(store_type));
		List<EcBaseStore> entityList = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

		request.setAttribute("entityList", entityList);
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String store_type = (String) dynaBean.get("store_type");
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		// 取仓库
		EcBaseStore ebs = new EcBaseStore();
		ebs.setStore_type(Integer.valueOf(store_type));
		List<EcBaseStore> entityList = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

		request.setAttribute("entityList", entityList);

		if (GenericValidator.isLong(store_id)) {
			EcStocks ec = new EcStocks();
			ec.setGoods_id(Long.valueOf(goods_id));
			ec.setStore_id(Long.valueOf(store_id));
			ec = super.getFacade().getEcStocksService().getEcStocks(ec);
			super.copyProperties(form, ec);
			dynaBean.set("store_id_1", String.valueOf(ec.getStore_id()));
		} else {
			this.saveError(request, "errors.long", new String[] { store_id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String store_id_1 = (String) dynaBean.get("store_id_1");
		String store_type = (String) dynaBean.get("store_type");
		String goods_id = (String) dynaBean.get("goods_id");

		EcStocks ec = new EcStocks();
		super.copyProperties(ec, form);

		if (StringUtils.isEmpty(store_id)) {
			// 添加 唯一性验证
			EcStocks entity = new EcStocks();
			entity.setGoods_id(Long.valueOf(goods_id));
			entity.setStore_id(Long.valueOf(store_id_1));
			// entity.getMap().put("store_type", store_type);
			Long count = super.getFacade().getEcStocksService().getEcStocksCount(entity);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.pd.store.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			ec.setStore_id(Long.valueOf(store_id_1));
			ec.setGoods_id(Long.valueOf(goods_id));
			super.getFacade().getEcStocksService().createEcStocks(ec);
			super.saveMessage(request, "entity.inserted");
		} else {
			ec.setStore_id(Long.valueOf(store_id));
			ec.setGoods_id(Long.valueOf(goods_id));
			super.getFacade().getEcStocksService().modifyEcStocks(ec);
			super.saveMessage(request, "entity.updated");
		}

		// end
		return new ActionForward("/../manager/spgl/EcStocks.do?method=view&id=" + goods_id + "&store_type="
		        + store_type);

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String store_type = (String) dynaBean.get("store_type");
		String goods_id = (String) dynaBean.get("goods_id");

		if (!GenericValidator.isLong(store_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcStocks es = new EcStocks();
		es.setStore_id(Long.valueOf(store_id));
		es.setGoods_id(Long.valueOf(goods_id));
		super.getFacade().getEcStocksService().removeEcStocks(es);

		saveMessage(request, "entity.deleted");

		return new ActionForward("/../manager/spgl/EcStocks.do?method=view&id=" + goods_id + "&store_type="
		        + store_type);
	}

	public ActionForward list2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		KonkaBcompPd kpd = new KonkaBcompPd();
		if (StringUtils.isNotBlank(goods_id)) {
			kpd.setId(Long.valueOf(goods_id));
			kpd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kpd);
			if (null != kpd) {
				dynaBean.set("pd_dept", kpd.getDept_sn());
				dynaBean.set("lock_state", kpd.getLock_state());
				dynaBean.set("pd_sn", kpd.getPd_sn());
			} else {
				dynaBean.set("pd_dept", user.getDept_id());
			}
		}

		EcStocks entity = new EcStocks();
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
			entity.getMap().put("is_zb", true);
			entity.getMap().put("own_sys", "2");
		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());
			request.setAttribute("is_fgs", "1");

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}

			entity.getMap().put("own_sys", "2");

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看库存！');history.back();}");
			return null;
		}

		Long recordCount = super.getFacade().getEcStocksService().getEcStocksForStoreTypeNewCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcStocks> entityList = super.getFacade().getEcStocksService().getEcStocksForStoreTypeNewList(entity);

		request.setAttribute("entityList", entityList);
		dynaBean.set("goods_id", goods_id);

		return new ActionForward("/../manager/spgl/EcStocksNew/list2.jsp");
	}

	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb) {
			request.setAttribute("is_admin", "1");
			dynaBean.set("store_type", "0");
		} else if (fgs) {
			request.setAttribute("is_fgs", "1");
			// dynaBean.set("store_type", "2");
			dynaBean.set("store_type", "0");
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看库存！');history.back();}");
			return null;
		}

		// 总部取所有分公司仓库
		EcBaseStore ebs = new EcBaseStore();
		if (zb) {
			// 取仓库
			ebs.setStore_type(0);
			ebs.setOwn_sys(2);
			List<EcBaseStore> entityList1 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

			ebs.setStore_type(1);
			ebs.setOwn_sys(2);
			List<EcBaseStore> entityList2 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

			ebs.setStore_type(2);
			ebs.setOwn_sys(2);
			List<EcBaseStore> entityList3 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);
			request.setAttribute("entityList1", entityList1);
			request.setAttribute("entityList2", entityList2);
			request.setAttribute("entityList3", entityList3);
		} else if (fgs) {
			// 分公司去本分公司仓库
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			List<String> ids = new ArrayList<String>();
			if (deptList != null && deptList.size() > 0) {
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				ebs.getMap().put("group_id_in", ids);
			}

			ebs.setStore_type(0);
			ebs.setPlat_sys(1);
			ebs.setOwn_sys(2);
			List<EcBaseStore> entityList1 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

			ebs.setStore_type(1);
			ebs.setPlat_sys(1);
			ebs.setOwn_sys(2);
			List<EcBaseStore> entityList2 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

			ebs.setStore_type(2);
			ebs.setPlat_sys(1);
			ebs.setOwn_sys(2);
			ebs.getMap().put("group_id_in", ids);
			List<EcBaseStore> entityList3 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

			request.setAttribute("entityList1", entityList1);
			request.setAttribute("entityList2", entityList2);
			request.setAttribute("entityList3", entityList3);
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		dynaBean.set("goods_id", goods_id);

		return new ActionForward("/../manager/spgl/EcStocksNew/add2.jsp");
	}

	public ActionForward save2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String store_id_1 = (String) dynaBean.get("store_id_1");
		String store_id_2 = (String) dynaBean.get("store_id_2");
		String store_id_3 = (String) dynaBean.get("store_id_3");
		String goods_id = (String) dynaBean.get("goods_id");

		EcStocks ec = new EcStocks();
		super.copyProperties(ec, form);

		if (StringUtils.isEmpty(store_id)) {
			// 添加 唯一性验证
			EcStocks entity = new EcStocks();
			entity.setGoods_id(Long.valueOf(goods_id));
			if (StringUtils.isNotBlank(store_id_1)) {
				entity.setStore_id(Long.valueOf(store_id_1));
			}
			if (StringUtils.isNotBlank(store_id_2)) {
				entity.setStore_id(Long.valueOf(store_id_2));
			}
			if (StringUtils.isNotBlank(store_id_3)) {
				entity.setStore_id(Long.valueOf(store_id_3));
			}
			Long count = super.getFacade().getEcStocksService().getEcStocksCount(entity);
			if (count > 0) {
				String msg = super.getMessage(request, "konka.pd.store.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			if (StringUtils.isNotBlank(store_id_1)) {
				ec.setStore_id(Long.valueOf(store_id_1));
				EcBaseStore es = new EcBaseStore();
				es.setStore_id(Long.valueOf(store_id_1));
				ec.setPlat_sys(super.getFacade().getEcBaseStoreService().getEcBaseStore(es).getPlat_sys());
			}
			if (StringUtils.isNotBlank(store_id_2)) {
				ec.setStore_id(Long.valueOf(store_id_2));
				EcBaseStore es = new EcBaseStore();
				es.setStore_id(Long.valueOf(store_id_2));
				ec.setPlat_sys(super.getFacade().getEcBaseStoreService().getEcBaseStore(es).getPlat_sys());
			}
			if (StringUtils.isNotBlank(store_id_3)) {
				ec.setStore_id(Long.valueOf(store_id_3));
				EcBaseStore es = new EcBaseStore();
				es.setStore_id(Long.valueOf(store_id_3));
				ec.setPlat_sys(super.getFacade().getEcBaseStoreService().getEcBaseStore(es).getPlat_sys());
			}
			ec.setGoods_id(Long.valueOf(goods_id));
			super.getFacade().getEcStocksService().createEcStocks(ec);
			super.saveMessage(request, "entity.inserted");
		} else {
			ec.setStore_id(Long.valueOf(store_id));
			ec.setGoods_id(Long.valueOf(goods_id));
			super.getFacade().getEcStocksService().modifyEcStocks(ec);
			super.saveMessage(request, "entity.updated");
		}

		// end
		return new ActionForward("/../manager/spgl/EcStocksNew.do?method=list2&id=" + goods_id);

	}

	public ActionForward delete2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String goods_id = (String) dynaBean.get("goods_id");

		logger.info("++++goods_id" + goods_id);
		logger.info("++++store_id" + store_id);
		if (!GenericValidator.isLong(store_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcStocks es = new EcStocks();
		es.setStore_id(Long.valueOf(store_id));
		es.setGoods_id(Long.valueOf(goods_id));
		super.getFacade().getEcStocksService().removeEcStocks(es);

		saveMessage(request, "entity.deleted");

		return new ActionForward("/../manager/spgl/EcStocksNew.do?method=list2&id=" + goods_id);
	}

	public ActionForward edit2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String store_type = (String) dynaBean.get("store_type");
		String goods_id = (String) dynaBean.get("goods_id");
		logger.info("store_type++++=" + store_type);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		if (GenericValidator.isLong(store_id)) {
			EcStocks ec = new EcStocks();
			ec.setGoods_id(Long.valueOf(goods_id));
			ec.setStore_id(Long.valueOf(store_id));
			ec = super.getFacade().getEcStocksService().getEcStocks(ec);
			super.copyProperties(form, ec);
			dynaBean.set("store_type", store_type);
		} else {
			this.saveError(request, "errors.long", new String[] { store_id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		return new ActionForward("/../manager/spgl/EcStocksNew/add2.jsp");
	}

}
