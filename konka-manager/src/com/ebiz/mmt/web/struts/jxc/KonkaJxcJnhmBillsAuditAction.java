package com.ebiz.mmt.web.struts.jxc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.domain.JxcJnhmSellBillAudit;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillAttachments;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Hu,Hao
 * @version 2012-08-16
 */
public class KonkaJxcJnhmBillsAuditAction extends BaseJxcAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		// DynaBean dynaBean = (DynaBean) form;
		PeProdUser user = super.getSessionUserInfo(request);
		if (null == user) {
			return null;
		}
		// R3网点搜索条件
		List<KonkaR3Shop> konkaR3ShopForSearchList = super.getShopListByDeptId(user.getDept_id());
		request.setAttribute("konkaR3ShopForSearchList", konkaR3ShopForSearchList);

		return mapping.findForward("list");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
		String state = (String) dynaBean.get("states");

		PeProdUser user = super.getSessionUserInfo(request);

		if (null == user) {
			return null;
		}
		// R3网点搜索条件
		List<KonkaR3Shop> konkaR3ShopForSearchList = super.getShopListByDeptId(user.getDept_id());
		request.setAttribute("konkaR3ShopForSearchList", konkaR3ShopForSearchList);

		if (!GenericValidator.isLong(r3_shop_id)) {
			String msg = "对不起，请选择网点!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setR3_shop_id(Long.valueOf(r3_shop_id));
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
		if (null == konkaR3MmtMatch) {
			String msg = "对不起，该R3网点未匹配买卖提网点!";
			renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		Long mmt_shop_id = konkaR3MmtMatch.getMmt_shop_id();
		JxcSellBill entity = new JxcSellBill();
		super.copyProperties(entity, form);

		entity.setShop_id(mmt_shop_id);

		if ("1".equals(state)) {
			entity.getMap().put("state_1", true);
		} else {
			entity.getMap().put("state_0", true);
		}

		String customer_name = "";
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(Long.valueOf(r3_shop_id));
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
		if (null != konkaR3Shop) {
			customer_name = konkaR3Shop.getCustomer_name();
		}
		request.setAttribute("customer_name", customer_name);
		request.setAttribute("shop_id", mmt_shop_id);
		request.setAttribute("r3_shop_id", r3_shop_id);

		Long recordCount = super.getFacade().getJxcSellBillService().getJxcSellBillForJnhmAuditCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<JxcSellBill> entityList = super.getFacade().getJxcSellBillService().getJxcSellBillForJnhmAuditPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		JxcSellBillDetails jxcSellBillDetails = new JxcSellBillDetails();
		jxcSellBillDetails.setSell_bill_id(Long.valueOf(id));
		jxcSellBillDetails = super.getFacade().getJxcSellBillDetailsService().getJxcSellBillDetails(jxcSellBillDetails);
		request.setAttribute("pd_name", jxcSellBillDetails.getPd_name());
		request.setAttribute("pd_unique_code", jxcSellBillDetails.getPd_unique_code());
		request.setAttribute("allowance", jxcSellBillDetails.getAllowance());
		request.setAttribute("allowance_money", jxcSellBillDetails.getAllowance_money());
		request.setAttribute("count", jxcSellBillDetails.getCount());
		request.setAttribute("price", jxcSellBillDetails.getPrice());

		JxcSellBill entity = new JxcSellBill();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getJxcSellBillService().getJxcSellBill(entity);
		super.copyProperties(form, entity);

		JxcCustomer jxcCustomer = new JxcCustomer();
		jxcCustomer.setId(Long.valueOf(entity.getCustomer_id()));
		jxcCustomer = super.getFacade().getJxcCustomerService().getJxcCustomer(jxcCustomer);
		request.setAttribute("cus_name", jxcCustomer.getName());
		request.setAttribute("cus_idcard", jxcCustomer.getCus_idcard());

		// 附件
		JxcSellBillAttachments attachment = new JxcSellBillAttachments();
		attachment.setSell_bill_id(Long.valueOf(id));
		attachment.setIs_del(0);
		request.setAttribute("attachmentList", super.getFacade().getJxcSellBillAttachmentsService().getJxcSellBillAttachmentsList(
				attachment));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isSessionLoginForKonkaJxc(request)) {
			return forwardNoSessionForKonkaJxc(request, response);
		}
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String id = (String) dynaBean.get("id");
		String state = (String) dynaBean.get("state");
		String audit_remarks = (String) dynaBean.get("audit_remarks");
		String audit_user_name = (String) dynaBean.get("audit_user_name");
		String shop_id = (String) dynaBean.get("shop_id");

		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setMmt_shop_id(Long.valueOf(shop_id));
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

		JxcJnhmSellBillAudit jxcJnhmSellBillAudit = new JxcJnhmSellBillAudit();
		jxcJnhmSellBillAudit.setSell_bill_id(Long.valueOf(id));

		JxcJnhmSellBillAudit entity = new JxcJnhmSellBillAudit();
		entity.setAudit_date(new Date());
		entity.setSell_bill_id(Long.valueOf(id));
		entity.setStates(Integer.valueOf(state));
		entity.setAudit_user_name(audit_user_name);

		jxcJnhmSellBillAudit = super.getFacade().getJxcJnhmSellBillAuditService().getJxcJnhmSellBillAudit(jxcJnhmSellBillAudit);
		if (jxcJnhmSellBillAudit == null) {
			entity.setAudit_remarks(audit_remarks);
			super.getFacade().getJxcJnhmSellBillAuditService().createJxcJnhmSellBillAudit(entity);
		} else if (jxcJnhmSellBillAudit != null && state.equals("0")) {
			entity.setId(jxcJnhmSellBillAudit.getId());
			entity.setAudit_remarks(audit_remarks);
			super.getFacade().getJxcJnhmSellBillAuditService().modifyJxcJnhmSellBillAudit(entity);
		} else if (jxcJnhmSellBillAudit != null && state.equals("1")) {
			entity.setId(jxcJnhmSellBillAudit.getId());
			entity.setAudit_remarks("");
			super.getFacade().getJxcJnhmSellBillAuditService().modifyJxcJnhmSellBillAudit(entity);
		}

		saveMessage(request, "konka.zmd.audit.sales.order.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&r3_shop_id=" + konkaR3MmtMatch.getR3_shop_id());
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward audit_all(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String shop_id = (String) dynaBean.get("shop_id");

		JxcSellBill entity = new JxcSellBill();
		entity.setShop_id(Long.valueOf(shop_id));

		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setMmt_shop_id(Long.valueOf(shop_id));
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

		super.getFacade().getJxcSellBillService().jxcSellBillAudit(entity);

		saveMessage(request, "konka.zmd.audit.sales.order.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&r3_shop_id=" + konkaR3MmtMatch.getR3_shop_id());
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward plAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String shop_id = (String) dynaBean.get("shop_id");
		String[] pkss = request.getParameterValues("pkss");
		String audit_memo = (String) dynaBean.get("audit_memo");
		String audit_state = (String) dynaBean.get("audit_state");
		String audit_user_name = (String) dynaBean.get("audit_user_name");

		JxcSellBill entity = new JxcSellBill();
		entity.getMap().put("pkss", pkss);
		entity.getMap().put("audit_memo", audit_memo);
		entity.getMap().put("audit_state", audit_state);
		entity.getMap().put("audit_user_name", audit_user_name);

		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setMmt_shop_id(Long.valueOf(shop_id));
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);

		super.getFacade().getJxcSellBillService().jxcSellBillSelectAudit(entity);

		saveMessage(request, "konka.zmd.audit.sales.order.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&r3_shop_id=" + konkaR3MmtMatch.getR3_shop_id());
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
}
