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
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,ShangLong
 *@version 2012-04-06
 */
public class KonkaXxLogisticsSearchAction extends BaseZmdAction {

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
		String dept_id = (String) dynaBean.get("dept_id");

		KonkaXxLogistics entity = new KonkaXxLogistics();

		PeProdUser user_info = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		//PeRoleUser role_info = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_info.getId());

		Boolean role_id_gt_400 = false;//专卖店人员
		Boolean role_id_eq_400 = false;//专卖店店长
		Boolean role_id_btw_300_400 = false;//专卖店分公司人员
		Boolean role_id_btw_200_300 = false;//专卖店总部人员

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
				if ((temp.getRole_id() >= 200 && temp.getRole_id() < 300) || temp.getRole_id() < 30) {
					role_id_btw_200_300 = true;
				}
				if(temp.getRole_id() == 400){
					role_id_eq_400 = true;
				}
			}
		}
		
		// 取用户角色
		//PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 页面角色控制 start
		//long role_id = role_info.getRole_id();
		if (role_id_eq_400) { // 专卖店人员
			KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
			konkaXxZmdUsers.setUser_id(user_info.getId());

			List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
					.getKonkaXxZmdUsersList(konkaXxZmdUsers);
			if (null == konkaXxZmdUsersList || konkaXxZmdUsersList.size() == 0) {
				String msg = getMessage(request, "konka.xx.zmd.user.not.bind.zmd");
				saveDirectlyError(request, msg);
				return mapping.findForward("list");
			}
			KonkaXxZmd zmd = new KonkaXxZmd();
			zmd.setZmd_id(konkaXxZmdUsersList.get(0).getZmd_id());
			zmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(zmd);

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(zmd.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);

			request.setAttribute("dept_name", kd.getDept_name());

			entity.setDept_id(kd.getDept_id());

		} else if (role_id_btw_300_400) { // 分公司人员
			KonkaDept kd = new KonkaDept();
			kd = getKonkaDeptForFgs(user_info.getDept_id());

			request.setAttribute("dept_name", kd.getDept_name());

			entity.setDept_id(kd.getDept_id());

		} else if(role_id_btw_200_300) { // 总部
			request.setAttribute("konkaDepts", super.getKonkaDepts());

			if (GenericValidator.isLong(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
			}
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

		request.setAttribute("entityList", entityList);

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
}
