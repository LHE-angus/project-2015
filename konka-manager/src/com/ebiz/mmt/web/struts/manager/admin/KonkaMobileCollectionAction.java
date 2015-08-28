package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
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

import com.ebiz.mmt.domain.KonkaMobileCollection;
import com.ebiz.mmt.domain.KonkaMobileStocksHis;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @desc 仓库存品查询
 * @author Dou，QingRen
 * @datetime 2011-11-29 15:05:13
 */
public class KonkaMobileCollectionAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		List<PePdModel> pePdModelList = super.getDeptLinkProduct(request,
				response, null, null, null,null);
		request.setAttribute("pePdModelList", pePdModelList);

		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String storage_id = (String) dynaBean.get("storage_id");
		if (StringUtils.isBlank(storage_id)) {
			List<PePdModel> pePdModelList = super.getDeptLinkProduct(request,
					response, null, null, null,null);
			request.setAttribute("pePdModelList", pePdModelList);

			return new ActionForward("/admin/KonkaMobileCollection/list.jsp");
		}

		KonkaMobileCollection entity = new KonkaMobileCollection();

		super.copyProperties(entity, form);

		entity.setIs_del(0); // 已同意的记录

		String coll_name_like = (String) dynaBean.get("coll_name_like");
		entity.getMap().put("coll_name_like", coll_name_like);

		Long recordCount = getFacade().getKonkaMobileCollectionService()
				.getKonkaMobileCollectionCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileCollection> entityList = getFacade()
				.getKonkaMobileCollectionService()
				.getKonkaMobileCollectionPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		List<PePdModel> pePdModelList = super.getDeptLinkProduct(request,
				response, null, null, null,null);
		request.setAttribute("pePdModelList", pePdModelList);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String coll_id = (String) dynaBean.get("coll_id");

		if (!GenericValidator.isLong(coll_id)) {
			this.saveError(request, "errors.long", new String[] { coll_id });
			return mapping.findForward("list");
		}

		KonkaMobileCollection entity = new KonkaMobileCollection();
		entity.setColl_id(Long.valueOf(coll_id));
		entity = super.getFacade().getKonkaMobileCollectionService()
				.getKonkaMobileCollection(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		List<PePdModel> pePdModelList = super.getDeptLinkProduct(request,
				response, null, null, null,null);
		request.setAttribute("pePdModelList", pePdModelList);

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "coll_id", "mod_id"));
		// end

		super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String base_num_old = (String) dynaBean.get("base_num_old");
		String busi_num_old = (String) dynaBean.get("busi_num_old");

		KonkaMobileCollection entity = new KonkaMobileCollection();
		super.copyProperties(entity, form);

		if (null == entity.getColl_id()) {// insert
			entity.setIs_del(0);
			entity.setStatus(0);
			entity.setBusi_num(entity.getBase_num()); // 新增的时候业务数等于初始库存
			super.getFacade().getKonkaMobileCollectionService()
					.createKonkaMobileCollection(entity);
			saveMessage(request, "entity.inserted");
		} else if (null != entity) {// update
			
			getFacade().getKonkaMobileCollectionService()
					.modifyKonkaMobileCollection(entity);
			if(!base_num_old.equals(entity.getBase_num().toString())){ //修改了原始库存数，需要调用历史数据接口，做历史记录
				KonkaMobileStocksHis his = new KonkaMobileStocksHis();
				his.setColl_id(entity.getColl_id());
				his.setOp_num(entity.getBase_num());
				his.setOp_type(4L);
				his.setStorage_id(entity.getStorage_id());
				
				BigDecimal busi_num ;
				if(StringUtils.isBlank(busi_num_old)){
					busi_num  = new BigDecimal(0);
				}else{
					busi_num = new BigDecimal(busi_num_old);
				}
				
				super.getFacade().getKonkaMobileStocksHisService().resetKonkaMobileStocksHis(his, new BigDecimal(base_num_old), busi_num);
			}
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity
				.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String coll_id = (String) dynaBean.get("coll_id");

		if (!GenericValidator.isLong(coll_id)) {
			saveError(request, "errors.long", new String[] { coll_id });
			return mapping.findForward("edit");
		}

		KonkaMobileCollection entity = new KonkaMobileCollection();
		entity.setColl_id(Long.valueOf(coll_id));
		entity = super.getFacade().getKonkaMobileCollectionService()
				.getKonkaMobileCollection(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { coll_id });
			return mapping.findForward("edit");
		}
		entity.setQueryString(super.serialize(request, "coll_id", "mod_id"));
		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String coll_id = (String) dynaBean.get("coll_id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(coll_id) && GenericValidator.isLong(coll_id)) {
			KonkaMobileCollection entity = new KonkaMobileCollection();
			entity.setColl_id(new Long(coll_id));
			super.getFacade().getKonkaMobileCollectionService()
					.removeKonkaMobileCollection(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaMobileCollection entity = new KonkaMobileCollection();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaMobileCollectionService()
					.removeKonkaMobileCollection(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "coll_id", "mod_id")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward doLock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String coll_id = (String) dynaBean.get("coll_id");
		String opty = (String) dynaBean.get("opty");
		String newStatus = (String) dynaBean.get("newStatus");

		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);

		if (!StringUtils.isBlank(coll_id) && GenericValidator.isLong(coll_id)) {
			KonkaMobileCollection entity = new KonkaMobileCollection();
			entity.setColl_id(new Long(coll_id));
			entity.setStatus(Integer.valueOf(newStatus));
			entity.getMap().put("opty", Long.valueOf(opty));
			entity.getMap().put("op_man", userInfo.getId());
			super.getFacade().getKonkaMobileCollectionService().doLock(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "coll_id", "mod_id")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward sendRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String opty = (String) dynaBean.get("opty");
		String coll_id = (String) dynaBean.get("coll_id");
		KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
		entity.setColl_id(new Long(coll_id));
		entity.setOp_type(new Long(opty));

		KonkaMobileCollection _t = new KonkaMobileCollection();
		_t.setColl_id(entity.getColl_id());
		_t = super.getFacade().getKonkaMobileCollectionService()
				.getKonkaMobileCollection(_t);
		entity.getMap().put("coll_name", _t.getColl_name());
		entity.setStorage_id(_t.getStorage_id());

		super.copyProperties(form, entity);
		return new ActionForward("/admin/KonkaMobileCollection/sendRec.jsp");
	}

	public ActionForward sendRecSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaMobileStocksHis entity = new KonkaMobileStocksHis();
		super.copyProperties(entity, form);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		entity.setOp_man(peProdUser.getId());
		entity.setOp_time(new Date());
		if (null == entity.getOp_id()) {
			getFacade().getKonkaMobileStocksHisService()
					.sendRecKonkaMobileStocksHis(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaMobileStocksHisService()
					.modifyKonkaMobileStocksHis(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "coll_id", "mod_id")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}
}