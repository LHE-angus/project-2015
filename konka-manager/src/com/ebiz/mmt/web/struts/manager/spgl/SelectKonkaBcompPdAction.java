package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class SelectKonkaBcompPdAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if ( pu.getRole_id() < 30L ||pu.getRole_id().intValue()==140317 ||pu.getRole_id().intValue()==1001 ) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb && !fgs) {// session用户属于总部，不属于分公司
			entity.setDept_sn("0");
		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			entity.setDept_sn(fgs_dept.getDept_sn());
		}
		if (!zb && !fgs) {
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		// 所属系统：1-工卡，2-触网，3-会员
		// entity.setOwn_sys(1);
		// 状态：0-已停用 1-正常 -1-已删除
		entity.setState(1);

		// 去除已设置返利的商品
		EcBcompPdRebates e = new EcBcompPdRebates();
		e.setIs_del(0);
		List<EcBcompPdRebates> ecBcompPdRebatesList = super.getFacade().getEcBcompPdRebatesService()
		        .getEcBcompPdRebatesList(e);
		String goods_id_not_in = "";
		if (null != ecBcompPdRebatesList && ecBcompPdRebatesList.size() > 0) {
			for (int i = 0; i < ecBcompPdRebatesList.size() - 1; i++) {
				EcBcompPdRebates t = ecBcompPdRebatesList.get(i);
				goods_id_not_in += String.valueOf(t.getGoods_id()) + ",";
			}
			EcBcompPdRebates t = ecBcompPdRebatesList.get(ecBcompPdRebatesList.size() - 1);
			goods_id_not_in += String.valueOf(t.getGoods_id());
		}
		entity.getMap().put("goods_id_not_in", goods_id_not_in);
		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdWithDeptAndMdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
		        .getKonkaBcompPdWithDeptAndMdPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

}