package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxLogistics;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 *@version 2012-04-05
 */
public class KonkaXxLogisticsAction extends BaseZmdAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String md_name = (String) dynaBean.get("md_name");

		KonkaXxLogistics entity = new KonkaXxLogistics();

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (role_id_btw_300_400) {
			entity.setDept_id(getKonkaDeptForFgs(user_id.getDept_id()).getDept_id());
		}

		// 收货地区设定
		if (StringUtils.isNotBlank(province) && StringUtils.isBlank(city)) {
			entity.getMap().put("par_p_index", province);
		}
		if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city) && StringUtils.isBlank(country)) {
			entity.getMap().put("par_p_index", city);
		}
		if (StringUtils.isNotBlank(province) && StringUtils.isNotBlank(city) && StringUtils.isNotBlank(country)) {
			entity.getMap().put("par_p_index", country);
		}

		if (StringUtils.isNotBlank(md_name)) {
			entity.setMd_name(md_name);
		}

		entity.setIs_del(0);

		Long recordCount = super.getFacade().getKonkaXxLogisticsService().getKonkaXxLogisticsWithPNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxLogistics> entityList = super.getFacade().getKonkaXxLogisticsService()
				.getKonkaXxLogisticsWithPNamePaginatedList(entity);

		if (null != entityList) {
			for (KonkaXxLogistics temp : entityList) {
				Long p_index_e = temp.getP_index_e();
				Long p_index_s = temp.getP_index_s();

				if (null != p_index_e) {
					temp.getMap().put("p_index_name_e", super.getPIndexName(p_index_e.toString(), ""));
				}
				if (null != p_index_s) {
					temp.getMap().put("p_index_name_s", super.getPIndexName(p_index_s.toString(), ""));
				}
			}
		}

		request.setAttribute("entityList", entityList);

		// 获取产品型号
		// KonkaXxStdPd konkaXxStdPd = new KonkaXxStdPd();
		// konkaXxStdPd.setMd_type(0);
		// List<KonkaXxStdPd> konkaXxStdPdList =
		// super.getFacade().getKonkaXxStdPdService().getKonkaXxStdPdList(
		// konkaXxStdPd);
		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pdModel);

		request.setAttribute("konkaXxStdPdList", pePdModelList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		// DynaBean dynaBean = (DynaBean) form;

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_btw_300_400) {
			request.setAttribute("dept_id", getKonkaDeptForFgs(user_id.getDept_id()).getDept_id());
			request.setAttribute("dept_name", getKonkaDeptForFgs(user_id.getDept_id()).getDept_name());
		}

		// 获取产品型号
		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pdModel);
		request.setAttribute("konkaXxStdPdList", pePdModelList);

		// 获取分公司所在地
		// String dept_p_index =
		// getKonkaDeptById(user_id.getDept_id()).getP_index().toString();
		// if (StringUtils.isNotEmpty(dept_p_index)) {
		// if (StringUtils.lastIndexOf(dept_p_index, "0000") > 0) {
		// dynaBean.set("province_s", dept_p_index);
		// } else if (StringUtils.lastIndexOf(dept_p_index, "00") > 0) {
		// dynaBean.set("province_s", dept_p_index.substring(0, 2) + "0000");
		// dynaBean.set("city_s", dept_p_index);
		// } else {
		// dynaBean.set("province_s", dept_p_index.substring(0, 2) + "0000");
		// dynaBean.set("city_s", dept_p_index.substring(0, 4) + "00");
		// dynaBean.set("country_s", dept_p_index);
		// }
		// }

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String logistics_id = (String) dynaBean.get("logistics_id");
		String province_s = (String) dynaBean.get("province_s");
		String city_s = (String) dynaBean.get("city_s");
		String country_s = (String) dynaBean.get("country_s");
		String province_e = (String) dynaBean.get("province_e");
		String city_e = (String) dynaBean.get("city_e");
		String country_e = (String) dynaBean.get("country_e");

		PeProdUser userInfo = super.getSessionUserInfo(request);

		KonkaXxLogistics entity = new KonkaXxLogistics();
		super.copyProperties(entity, form);
		// 发货地区设定
		if (StringUtils.isNotBlank(province_s) && StringUtils.isBlank(city_s) && StringUtils.isBlank(country_s)) {
			entity.setP_index_s(Long.valueOf(province_s));
		}
		if (StringUtils.isNotBlank(province_s) && StringUtils.isNotBlank(city_s) && StringUtils.isBlank(country_s)) {
			entity.setP_index_s(Long.valueOf(city_s));
		}
		if (StringUtils.isNotBlank(province_s) && StringUtils.isNotBlank(city_s) && StringUtils.isNotBlank(country_s)) {
			entity.setP_index_s(Long.valueOf(country_s));
		}
		// 收货地区设定
		if (StringUtils.isNotBlank(province_e) && StringUtils.isBlank(city_e) && StringUtils.isBlank(country_e)) {
			entity.setP_index_e(Long.valueOf(province_e));
		}
		if (StringUtils.isNotBlank(province_e) && StringUtils.isNotBlank(city_e) && StringUtils.isBlank(country_e)) {
			entity.setP_index_e(Long.valueOf(city_e));
		}
		if (StringUtils.isNotBlank(province_e) && StringUtils.isNotBlank(city_e) && StringUtils.isNotBlank(country_e)) {
			entity.setP_index_e(Long.valueOf(country_e));
		}

		entity.setDept_id(getKonkaDeptForFgs(userInfo.getDept_id()).getDept_id());
		entity.setIs_del(0);

		if (!GenericValidator.isLong(logistics_id)) {
			// 判断该区域物流设置是否已经存在
			KonkaXxLogistics temp = new KonkaXxLogistics();
			temp.setDept_id(entity.getDept_id());
			temp.setMd_name(entity.getMd_name());
			temp.setP_index_e(entity.getP_index_e());
			temp.setP_index_s(entity.getP_index_s());

			Long recordCount = super.getFacade().getKonkaXxLogisticsService().getKonkaXxLogisticsCount(temp);

			if (recordCount > 0L) {
				String msg = super.getMessage(request, "konka.xx.logistics.exist");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaXxLogisticsService().createKonkaXxLogistics(entity);
			saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaXxLogisticsService().modifyKonkaXxLogistics(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String logistics_id = (String) dynaBean.get("logistics_id");

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;
		Boolean role_id_btw_300_400 = false;

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_btw_300_400) {
			request.setAttribute("dept_name",getKonkaDeptForFgs(user_id.getDept_id()).getDept_name());
		}

//		KonkaXxStdPd konkaXxStdPd = new KonkaXxStdPd();
//		konkaXxStdPd.setMd_type(0);
//		List<KonkaXxStdPd> konkaXxStdPdList = super.getFacade().getKonkaXxStdPdService().getKonkaXxStdPdList(
//				konkaXxStdPd);
		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pdModel);
		
		request.setAttribute("konkaXxStdPdList", pePdModelList);

		if (GenericValidator.isLong(logistics_id)) {
			KonkaXxLogistics entity = new KonkaXxLogistics();
			entity.setLogistics_id(Long.valueOf(logistics_id));
			entity.setIs_del(0);
			entity = getFacade().getKonkaXxLogisticsService().getKonkaXxLogistics(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {
				entity.setQueryString(super.serialize(request, "id", "method"));
				super.copyProperties(form, entity);

				if (null != entity.getP_index_e()) {
					if (StringUtils.lastIndexOf(entity.getP_index_e().toString(), "0000") > 0) {
						dynaBean.set("province_e", entity.getP_index_e().toString());
					} else if (StringUtils.lastIndexOf(entity.getP_index_e().toString(), "00") > 0) {
						dynaBean.set("province_es", entity.getP_index_e().toString().substring(0, 2) + "0000");
						dynaBean.set("city_e", entity.getP_index_e().toString());
					} else {
						dynaBean.set("province_e", entity.getP_index_e().toString().substring(0, 2) + "0000");
						dynaBean.set("city_e", entity.getP_index_e().toString().substring(0, 4) + "00");
						dynaBean.set("country_e", entity.getP_index_e().toString());
					}
				}

				if (null != entity.getP_index_s()) {
					if (StringUtils.lastIndexOf(entity.getP_index_s().toString(), "0000") > 0) {
						dynaBean.set("province_s", entity.getP_index_s().toString());
					} else if (StringUtils.lastIndexOf(entity.getP_index_s().toString(), "00") > 0) {
						dynaBean.set("province_s", entity.getP_index_s().toString().substring(0, 2) + "0000");
						dynaBean.set("city_s", entity.getP_index_s().toString());
					} else {
						dynaBean.set("province_s", entity.getP_index_s().toString().substring(0, 2) + "0000");
						dynaBean.set("city_s", entity.getP_index_s().toString().substring(0, 4) + "00");
						dynaBean.set("country_s", entity.getP_index_s().toString());
					}
				}

				return mapping.findForward("input");
			}
		} else {
			saveError(request, "errors.long", logistics_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String logistics_id = (String) dynaBean.get("logistics_id");

		if (!GenericValidator.isLong(logistics_id)) {
			this.saveError(request, "errors.long", new String[] { logistics_id });
			return mapping.findForward("list");
		}

		KonkaXxLogistics entity = new KonkaXxLogistics();
		entity.setLogistics_id(Long.valueOf(logistics_id));
		entity.setIs_del(0);
		entity = super.getFacade().getKonkaXxLogisticsService().getKonkaXxLogistics(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		List<KonkaDept> konkaDeptsList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (null != konkaDeptsList && konkaDeptsList.size() > 0) {
			request.setAttribute("dept_name", konkaDeptsList.get(0).getDept_name());
		}
		if (null != entity.getP_index_e()) {
			request.setAttribute("p_name_e", super.getPIndexName(entity.getP_index_e().toString(), ""));
		}
		if (null != entity.getP_index_s()) {
			request.setAttribute("p_name_s", super.getPIndexName(entity.getP_index_s().toString(), ""));
		}

		super.copyProperties(form, entity);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String logistics_id = (String) dynaBean.get("logistics_id");

		if (GenericValidator.isLong(logistics_id)) {
			KonkaXxLogistics entity = new KonkaXxLogistics();
			entity.setLogistics_id(Long.valueOf(logistics_id));
			entity.setIs_del(1);
			getFacade().getKonkaXxLogisticsService().modifyKonkaXxLogistics(entity);
		}
		saveMessage(request, "entity.deleted");
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
