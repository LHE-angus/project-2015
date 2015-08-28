package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
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

import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileMdPercent;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-05-09
 */
public class KonkaMobileMdPercentAction extends MobileBaseAction {
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
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_id = (String) dynaBean.get("pd_id");
		String dept_id = (String) dynaBean.get("dept_id");

		KonkaMobileMdPercent entity = new KonkaMobileMdPercent();
		entity.setStatus(0); // 默认查询正常数据
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String login_dept_id = (String) dynaBean.get("dept_id");
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			login_dept_id = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != login_dept_id) {// 分公司用户
			entity.setDept_id(Long.valueOf(login_dept_id));
			request.setAttribute("isHeadquarters", "false");
		} else { // 总部
			if (GenericValidator.isLong(dept_id)) // 总部且选择了分公司
				entity.setDept_id(Long.valueOf(dept_id));
			request.setAttribute("isHeadquarters", "true");
		}

		// 型号
		if (GenericValidator.isLong(pd_id))
			entity.setPd_id(Long.valueOf(pd_id));

		Long recordCount = getFacade().getKonkaMobileMdPercentService().getKonkaMobileMdPercentCount(entity);
		pager.init(recordCount, 15, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobileMdPercent> entityList = getFacade().getKonkaMobileMdPercentService()
		        .getKonkaMobileMdPercentPaginatedList(entity);
		for (KonkaMobileMdPercent konkaMobileMdPercent : entityList) {
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(konkaMobileMdPercent.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			if (null != kd)
				konkaMobileMdPercent.getMap().put("dept_name", kd.getDept_name());

			PePdModel pm = new PePdModel();
			pm.setPd_id(konkaMobileMdPercent.getPd_id());
			pm = super.getFacade().getPePdModelService().getPePdModel(pm);
			if (null != pm)
				konkaMobileMdPercent.getMap().put("md_name", pm.getMd_name());
		}
		request.setAttribute("entityList", entityList);

		// 分公司,下拉选项
		KonkaDept kd = new KonkaDept();
		kd.setDept_type(3);
		kd.setPar_id(0L);
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
		request.setAttribute("konkaDeptList", konkaDeptList);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
		        .getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String dept_id = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept_id = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileDept(_t);
		}
		if (null != dept_id) {// 分公司用户
			request.setAttribute("isHeadquarters", "false");
		} else {// 总部用户
			KonkaDept kd = new KonkaDept();
			kd.setDept_type(3);
			kd.setPar_id(0L);
			kd.getMap().put("order_by_dept_name", true);
			List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
			request.setAttribute("konkaDeptList", konkaDeptList);
			request.setAttribute("isHeadquarters", "true");
		}

		dynaBean.set("dept_id", dept_id);

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
		        .getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaMobileMdPercent entity = new KonkaMobileMdPercent();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaMobileMdPercentService().getKonkaMobileMdPercent(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "id", "mod_id", "pd_id"));
		// end

		super.copyProperties(form, entity);

		// 查型号
		PePdModel pePdModel = new PePdModel();
		pePdModel.setPd_id(entity.getPd_id());
		pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
		// BasePdClazz basePdClazz = new BasePdClazz();
		// logger.info("-----pePdModel.getCls_id()----->{}",
		// pePdModel.getCls_id());
		// basePdClazz.setCls_id(pePdModel.getCls_id());
		// basePdClazz =
		// super.getFacade().getBasePdClazzService().getBasePdClazz(basePdClazz);
		dynaBean.set("category_id", pePdModel.getCls_id());
		dynaBean.set("md_name", pePdModel.getMd_name());
		logger.info("-----pePdModel.getPd_id()-->{}", pePdModel.getPd_id());
		dynaBean.set("pd_id", pePdModel.getPd_id().toString());

		// 类别 下拉选项
		List<BasePdClazz> basePdClazzList = super.getFacade().getRetailMsBaseService()
		        .getKonkaBasePdClazzListByClsIds();
		request.setAttribute("basePdClazzList", basePdClazzList);

		// 尺寸sizeList
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String pd_id = (String) dynaBean.get("pd_id");
		String percent = (String) dynaBean.get("percent");
		String type = (String) dynaBean.get("type");

		KonkaMobileMdPercent entity = new KonkaMobileMdPercent();
		entity.setDept_id(Long.valueOf(dept_id));
		if (StringUtils.isNotBlank(pd_id)) {
			entity.setPd_id(Long.valueOf(pd_id));
		}
		entity.setPercent(new BigDecimal(percent));
		entity.setType(Integer.valueOf(type));

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		entity.setStatus(0);
		if (StringUtils.isBlank(id)) {// insert

			KonkaMobileMdPercent pm = new KonkaMobileMdPercent();
			if (StringUtils.isNotBlank(pd_id)) {
				pm.setPd_id(Long.valueOf(pd_id));
				pm.setDept_id(Long.valueOf(dept_id));
			}
			Long count = super.getFacade().getKonkaMobileMdPercentService().getKonkaMobileMdPercentCount(pm);
			if (count > 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('该型号已经存在，请重新选择！');history.back();}");
				return null;
			}

			entity.setDept_id(Long.valueOf(dept_id));
			super.getFacade().getKonkaMobileMdPercentService().createKonkaMobileMdPercent(entity);
			saveMessage(request, "entity.inserted");
		} else {// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}

			KonkaMobileMdPercent md = new KonkaMobileMdPercent();
			md.setId(Long.valueOf(id));
			md = super.getFacade().getKonkaMobileMdPercentService().getKonkaMobileMdPercent(md);

			if (StringUtils.isNotBlank(pd_id) && !pd_id.equals(md.getPd_id().toString())) {
				KonkaMobileMdPercent mp = new KonkaMobileMdPercent();
				mp.setPd_id(Long.valueOf(pd_id));
				mp.setDept_id(md.getDept_id());
				Long count = super.getFacade().getKonkaMobileMdPercentService().getKonkaMobileMdPercentCount(mp);
				if (count > 0) {
					super.renderJavaScript(response,
					        "window.onload=function(){alert('该型号已经存在，请重新选择！');history.back();}");
					return null;
				}
			}

			entity.setModify_man(peProdUser.getId());
			entity.setModify_time(new Date());
			entity.setId(Long.valueOf(id));
			super.getFacade().getKonkaMobileMdPercentService().modifyKonkaMobileMdPercent(entity);
			saveMessage(request, "entity.updated");
		}

		if (StringUtils.isNotBlank(returnUrl)) {
			response.sendRedirect(URLDecoder.decode(returnUrl, Constants.SYS_ENCODING));
			return null;
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=").append(tree_param);
		// pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}