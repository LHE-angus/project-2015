package com.ebiz.mmt.web.struts.jxcnokey;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.domain.JxcSellBill;
import com.ebiz.mmt.domain.JxcSellBillAttachments;
import com.ebiz.mmt.domain.JxcSellBillDetails;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Wu,ShangLong
 * @version 2012-08-17
 */
public class JxcJnhmSellBillAction extends BaseJxcAction {
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String customer_name = (String) dynaBean.get("customer_name");// 消费者名
		String search_flag = (String) dynaBean.get("search_flag");// 是否点查询

		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);

		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		Long shop_id = user.getEntp_shop().getShop_id();
		// request.setAttribute("own_sys", user.getOwn_sys());

		Pager pager = (Pager) dynaBean.get("pager");
		// String customer_id = (String) dynaBean.get("customer_id");
		String sell_date_ge = (String) dynaBean.get("sell_date_ge");
		String sell_date_le = (String) dynaBean.get("sell_date_le");

		Date now = new Date();
		if (StringUtils.isBlank(sell_date_ge)) {
			sell_date_ge = DateFormatUtils.format(now, "yyyy-MM-") + "01";
		}
		if (StringUtils.isBlank(sell_date_le)) {
			sell_date_le = DateFormatUtils.format(now, "yyyy-MM-dd");
		}
		JxcSellBill sellBill = new JxcSellBill();

		if (StringUtils.isNotBlank(search_flag)) {

			// if (GenericValidator.isLong(customer_id)) {
			// sellBill.setCustomer_id(Long.valueOf(customer_id));
			// }
			if (StringUtils.isNotBlank(customer_name.trim())) {// 根据消费者查询列表条件
				sellBill.getMap().put("customer_name", customer_name.trim());
				if (StringUtils.isNotBlank(shop_id.toString())) {// 根据消费者shop_id查询列表条件
					sellBill.getMap().put("customer_shop_id", shop_id);
				}
			}
			sellBill.getMap().put("sell_date_ge", sell_date_ge);
			sellBill.getMap().put("sell_date_le", sell_date_le);
			sellBill.getMap().put("is_del_j", 0);
			sellBill.getMap().put("is_del_c", 0);
			sellBill.getMap().put("shop_id_j", shop_id);
			sellBill.getMap().put("allowance_money_gt", 0); // 具有节能惠民补贴的销售单

			Long recCount = super.getFacade().getJxcSellBillService().getJxcSellBillCountForXS(sellBill);

			pager.init(recCount, pager.getPageSize(), pager.getRequestPage());
			sellBill.getRow().setFirst(pager.getFirstRow());
			sellBill.getRow().setCount(pager.getRowCount());
			List<JxcSellBill> sellBillList = super.getFacade().getJxcSellBillService()
					.getJxcSellBillPaginatedListForXS(sellBill);
			request.setAttribute("sellBillList", sellBillList);

		}

		// 客户列表 业务更改不需取此列表 暂留备用
		// List<JxcCustomer> customerList = super.getCustomerList(shop_id);
		// request.setAttribute("customerList", customerList);

		dynaBean.set("sell_date_ge", sell_date_ge);
		dynaBean.set("sell_date_le", sell_date_le);

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");

		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);
		Long shop_id = user.getEntp_shop().getShop_id();

		// 销售单明细
		JxcSellBillDetails details = new JxcSellBillDetails();
		details.setSell_bill_id(Long.valueOf(sell_bill_id));

		List<JxcSellBillDetails> detailList = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsList(details);

		request.setAttribute("detailList", detailList);

		// 销售单
		JxcSellBill sellBill = new JxcSellBill();
		sellBill.setId(Long.valueOf(sell_bill_id));
		sellBill.setIs_del(0);
		sellBill = super.getFacade().getJxcSellBillService().getJxcSellBill(sellBill);
		request.setAttribute("sellBill", sellBill);

		// 客户
		JxcCustomer cust = new JxcCustomer();
		cust.setShop_id(shop_id);
		cust.setIs_del(0);
		cust.setId(sellBill.getCustomer_id());
		cust = super.getFacade().getJxcCustomerService().getJxcCustomer(cust);
		request.setAttribute("cust", cust);

		// 附件
		JxcSellBillAttachments attachment = new JxcSellBillAttachments();
		attachment.setSell_bill_id(Long.valueOf(sell_bill_id));
		attachment.setIs_del(0);
		request.setAttribute("attachmentList", super.getFacade().getJxcSellBillAttachmentsService()
				.getJxcSellBillAttachmentsList(attachment));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String sell_bill_id = (String) dynaBean.get("sell_bill_id");
		String allowance = (String) dynaBean.get("allowance");// 单台补贴金额

		request.setAttribute("not_validate_record", "true");
		// StdEntpUser user = super.getStdEntpUserFromRequest(request, response,
		// keySeq);
		UserInfo user = super.getUserInfoFromWebService(request, response);
		// logger.info("============keySeq:{}", keySeq);
		if (null == user) {
			return null;
		}
		dynaBean.set("keySeq", keySeq);

		// 销售单
		JxcSellBill sellBill = new JxcSellBill();
		sellBill.setId(Long.valueOf(sell_bill_id));
		sellBill.setIs_del(0);
		sellBill = super.getFacade().getJxcSellBillService().getJxcSellBill(sellBill);

		JxcSellBillDetails details = new JxcSellBillDetails();
		details.setSell_bill_id(Long.valueOf(sell_bill_id));
		List<JxcSellBillDetails> detailsList = super.getFacade().getJxcSellBillDetailsService()
				.getJxcSellBillDetailsList(details);

		details.setId(detailsList.get(0).getId());
		details.setAllowance(Long.valueOf(allowance));
		details.setAllowance_money(Long.valueOf(allowance) * detailsList.get(0).getCount());

		super.getFacade().getJxcSellBillDetailsService().modifyJxcSellBillDetails(details);

		sellBill.setBill_allowance_money(Long.valueOf(allowance) * detailsList.get(0).getCount());
		super.getFacade().getJxcSellBillService().modifyJxcSellBill(sellBill);
		// 附件
		// List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new
		// int[] { 240 });
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OA_PATH, true, 0, new int[] { 240 });
		JxcSellBillAttachments attachment = null;

		for (UploadFile uploadFile : uploadFileList) {

			attachment = new JxcSellBillAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSource(2); // 1-买卖商通进销存，2-康佳进销存;
			attachment.setIs_del(0);
			attachment.setSell_bill_id(Long.valueOf(sell_bill_id));
			attachment.setAdd_user_name(user.getUser_name());

			getFacade().getJxcSellBillAttachmentsService().createJxcSellBillAttachments(attachment);
		}

		saveMessage(request, "entity.updated");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&keySeq=").append(keySeq);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String file_path = (String) dynaBean.get("file_path");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(file_path)) {
			super.renderText(response, "fail");
			return null;
		}
		logger.info("id:{}", id);
		logger.info("file_path:{}", file_path);

		JxcSellBillAttachments entity = new JxcSellBillAttachments();
		entity.setId(Long.valueOf(id));
		getFacade().getJxcSellBillAttachmentsService().removeJxcSellBillAttachments(entity);

		super.renderText(response, "success");
		return null;
	}
}
