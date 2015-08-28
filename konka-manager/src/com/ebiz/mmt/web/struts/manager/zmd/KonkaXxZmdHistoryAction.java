package com.ebiz.mmt.web.struts.manager.zmd;

import java.util.ArrayList;
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
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdRes;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.mmt.domain.KonkaXxZmdStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.ZmdRole;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Zheng,Kun
 * @version 2012-1-10
 */
public class KonkaXxZmdHistoryAction extends BaseZmdAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		String busi_type = (String) dynaBean.get("busi_type");
		String arc_state = (String) dynaBean.get("arc_state");

		KonkaXxZmd entity = new KonkaXxZmd();
		if (StringUtils.isNotBlank(zmd_sn_like)) {
			entity.getMap().put("zmd_sn_like", zmd_sn_like);
		}
		if (StringUtils.isNotBlank(busi_type)) {
			entity.setBusi_type(Long.valueOf(busi_type));
		}
		if (StringUtils.isNotBlank(arc_state)) {
			entity.setArc_state(Integer.valueOf(arc_state));
		}

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(peProdUser.getId());

		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() >= 300 && temp.getRole_id() < 400) {
					role_id_btw_300_400 = true;
				}
			}
		}
		// 当角色是分公司管理员，取本部门的专卖店，角色是总公司管理员时，取全部专卖店
		if (role_id_btw_300_400) {
			KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
			if (kDept != null)
				entity.setDept_id(kDept.getDept_id());
		}
		entity.setIs_del(0);
		entity.getMap().put("apply_date_desc", true);

		Long recordCount = getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdPaginatedList(entity);

		setBaseTypeListByParIdToRequest(10000L, request);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");

		if (!GenericValidator.isLong(zmd_id)) {
			this.saveError(request, "errors.long", new String[] { zmd_id });
			return mapping.findForward("list");
		}

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		String t1 = super.getPIndexName(entity.getP_index().toString(), "");
		String t2 = entity.getAddr();
		String zmd_addr_1 = t1 + t2;
		entity.getMap().put("zmd_addr_1", zmd_addr_1);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		entity.getMap().put("dept_name", konkaDept.getDept_name());

		// 取专卖点返佣设置信息
		KonkaXxZmdRewardSet konkaXxZmdRewardSet = new KonkaXxZmdRewardSet();
		konkaXxZmdRewardSet.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = super.getFacade().getKonkaXxZmdRewardSetService()
				.getKonkaXxZmdRewardSetList(konkaXxZmdRewardSet);
		request.setAttribute("konkaXxZmdRewardSetList", konkaXxZmdRewardSetList);

		// 取专卖店仓库信息
		KonkaXxZmdStore konkaXxZmdStore = new KonkaXxZmdStore();
		konkaXxZmdStore.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdStore> konkaXxZmdStoreList = super.getFacade().getKonkaXxZmdStoreService()
				.getKonkaXxZmdStoreList(konkaXxZmdStore);
		request.setAttribute("konkaXxZmdStoreList", konkaXxZmdStoreList);

		super.copyProperties(form, entity);

		// 取基础类型表里相对应的数据
		setBaseTypeListByParIdToRequest(10000L, request);
		setBaseTypeListByParIdToRequest(80000L, request);
		setBaseTypeListByParIdToRequest(30000L, request);
		setBaseTypeListByParIdToRequest(100000L, request);
		setBaseTypeListByParIdToRequest(110000L, request);

		// 专卖店资源
		KonkaXxZmdRes konkaXxZmdRes = new KonkaXxZmdRes();
		konkaXxZmdRes.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdRes> konkaXxZmdResList = super.getFacade().getKonkaXxZmdResService().getKonkaXxZmdResList(
				konkaXxZmdRes);
		if (konkaXxZmdResList.size() > 0) {
			request.setAttribute("konkaXxZmdResList", konkaXxZmdResList);
		} else {
			List<KonkaXxZmdRes> arrlist = new ArrayList<KonkaXxZmdRes>();
			for (int i = 0; i < ZmdRole.res_name.length; i++) {
				KonkaXxZmdRes res = new KonkaXxZmdRes();
				res.setRes_name(ZmdRole.res_name[i]);
				arrlist.add(res);
			}
			request.setAttribute("konkaXxZmdResList", arrlist);
		}

		return mapping.findForward("view");
	}
}
