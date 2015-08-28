package com.ebiz.mmt.web.struts.customer;

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
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-26
 */
public class EcGoodsStockModifyForTouchNetAction extends BaseAction {
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

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

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
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}

		EcStocks entity = new EcStocks();

		PeProdUser p_user = new PeProdUser();
		p_user.getMap().put("user_id", user.getId());
		p_user = super.getFacade().getPeProdUserService().getPeProdUserForFgs(p_user);

		KonkaBcompPd pd = new KonkaBcompPd();
		pd.setId(Long.valueOf(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		if (null != pd) {
			// 如果是分公司产品
			if (!pd.getDept_sn().equals("0")) {
				entity.getMap().put("is_fgs", p_user.getMap().get("fgs_id").toString());
			} else {
				// 如果是总部产品
				entity.getMap().put("is_zb", true);
			}
		} else {
			entity.getMap().put("is_fgs", p_user.getMap().get("fgs_id").toString());
		}

		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		Long recordCount = super.getFacade().getEcStocksService().getEcStocksForStoreTypeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcStocks> entityList = super.getFacade().getEcStocksService().getEcStocksForStoreTypeList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../customer/EcGoodsStockModifyForTouchNet/list2.jsp");
	}

	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

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
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
		}
		if (fgs) {
			request.setAttribute("is_fgs", "1");
		}

		// 取仓库
		EcBaseStore ebs = new EcBaseStore();
		ebs.setStore_type(0);
		List<EcBaseStore> entityList1 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

		ebs.setStore_type(1);
		List<EcBaseStore> entityList2 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

		ebs.setStore_type(2);
		List<EcBaseStore> entityList3 = super.getFacade().getEcBaseStoreService().getEcBaseStoreList(ebs);

		request.setAttribute("entityList1", entityList1);
		request.setAttribute("entityList2", entityList2);
		request.setAttribute("entityList3", entityList3);

		dynaBean.set("store_type", "0");

		return new ActionForward("/../manager/spgl/EcGoodsStockModifyForTouchNet/add2.jsp");
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
			}
			if (StringUtils.isNotBlank(store_id_2)) {
				ec.setStore_id(Long.valueOf(store_id_2));
			}
			if (StringUtils.isNotBlank(store_id_3)) {
				ec.setStore_id(Long.valueOf(store_id_3));
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
		return new ActionForward("/../manager/spgl/EcGoodsStockModifyForTouchNet.do?method=list2&id=" + goods_id);

	}

	public ActionForward delete2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
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

		return new ActionForward("/../manager/spgl/EcGoodsStockModifyForTouchNet.do?method=list2&id=" + goods_id);
	}

	public ActionForward edit2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		return new ActionForward("/../manager/spgl/EcGoodsStockModifyForTouchNet/add2.jsp");
	}

}
