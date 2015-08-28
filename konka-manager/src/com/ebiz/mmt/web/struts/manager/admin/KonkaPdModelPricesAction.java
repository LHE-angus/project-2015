package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPdModelPrices;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author lideyu
 * @version 2013-07-23
 */
public class KonkaPdModelPricesAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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

		KonkaPdModelPrices entity = new KonkaPdModelPrices();
		String pd_name_like = (String) dynaBean.get("category_name_like");
		String cash_price_min = (String) dynaBean.get("cash_price_min");
		String cash_price_max = (String) dynaBean.get("cash_price_max");
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(cash_price_min)) {
			entity.getMap().put("cash_price_min", cash_price_min);
		}
		if (StringUtils.isNotBlank(cash_price_max)) {
			entity.getMap().put("cash_price_max", cash_price_max);
		}
		Long recordCount = super.getFacade().getKonkaPdModelPricesService().getKonkaPdModelPricesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaPdModelPrices> konkaPdModelPricesList = super.getFacade().getKonkaPdModelPricesService()
				.getKonkaPdModelPricesPaginatedList(entity);
		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);

		boolean role_id_ge_20_le_29_or_eq_10 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if ((peRoleUser.getRole_id() >= 20 && peRoleUser.getRole_id() <= 29) || peRoleUser.getRole_id() == 10) {
				role_id_ge_20_le_29_or_eq_10 = true;
				break;
			}
		}
		// 判断当前用户是否是事业部或系统管理员
		String is_division_or_admin = "false";
		if (role_id_ge_20_le_29_or_eq_10) {
			is_division_or_admin = "true";
		}
		request.setAttribute("is_division_or_admin", is_division_or_admin);

		request.setAttribute("entityList", konkaPdModelPricesList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		dynaBean.set("order_value", "0");
		PePdModel pePdModel = new PePdModel();
		pePdModel.setIs_del(0);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);
		request.setAttribute("pePdModelList", pePdModelList);
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		// request.setAttribute("basePdClazzList", getBasePdClazzList());
		KonkaPdModelPrices entity = new KonkaPdModelPrices();
		entity.setId(Long.parseLong(id));
		entity = super.getFacade().getKonkaPdModelPricesService().getKonkaPdModelPrices(entity);
		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		super.copyProperties(form, entity);
		// dynaBean.set("mod_id", mod_id);

		PePdModel pePdModel = new PePdModel();
		pePdModel.setIs_del(0);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);

		request.setAttribute("pePdModelList", pePdModelList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String pd_name = (String) dynaBean.get("pd_name");
		String price_month = (String) dynaBean.get("price_month");

		KonkaPdModelPrices kPrices = new KonkaPdModelPrices();
		kPrices.setPd_name(pd_name);
		kPrices.setPrice_month(Integer.valueOf(price_month));
		Long count1 = super.getFacade().getKonkaPdModelPricesService().getKonkaPdModelPricesCount(kPrices);

		KonkaPdModelPrices entity = new KonkaPdModelPrices();
		super.copyProperties(entity, form);
		if (StringUtils.isBlank(id)) {
			if (count1 > 0) {
				String msg = super.getMessage(request, "md.name.month.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPdModelPricesService().createKonkaPdModelPrices(entity);
			saveMessage(request, "entity.inserted");
		} else {
			if (count1 > 1) {
				String msg = super.getMessage(request, "md.name.month.error");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPdModelPricesService().modifyKonkaPdModelPrices(entity);
			saveMessage(request, "entity.updated");
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] pks = (String[]) dynaBean.get("pks");
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPdModelPrices entity = new KonkaPdModelPrices();
			entity.setId(Long.parseLong(id));
			getFacade().getKonkaPdModelPricesService().removeKonkaPdModelPrices(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaPdModelPrices entity = new KonkaPdModelPrices();
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaPdModelPricesService().removeKonkaPdModelPrices(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		// pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaPdModelPrices entity = new KonkaPdModelPrices();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaPdModelPricesService().getKonkaPdModelPrices(entity);
		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);
		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}

	public ActionForward addBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaPdModelPrices/addBatch.jsp"));
	}

	public ActionForward saveBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		int x = 0, y = 0, z = 0, mon = 0;
		String str = new String();
		List<UploadFile> uploadFiles = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		if (uploadFiles.size() == 1) {
			List<KonkaPdModelPrices> konkaPdModelPricesList = new ArrayList<KonkaPdModelPrices>();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

			String fileSavePath = uploadFiles.get(0).getFileSavePath();
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "";
				fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
			}

			int size = 0;// Excel表格执行验证的记录条数
			try {
				Workbook workbook = Workbook.getWorkbook(new File(ctxDir + fileSavePath));
				Sheet sheet = workbook.getSheet(0);
				String msg = "";// 上传的表格不符合规范时的返回信息
				for (int i = 1; i < sheet.getRows(); i++) {
					if (StringUtils.isNotBlank(sheet.getCell(0, i).getContents())) {// 判断数据库中定义的必填项是否为空
						KonkaPdModelPrices konkaPdModelPrices = new KonkaPdModelPrices();
						String pd_name = sheet.getCell(0, i).getContents().trim();
						String price_month = sheet.getCell(4, i).getContents();
						String cash_price = sheet.getCell(1, i).getContents();
						String sale_price = sheet.getCell(2, i).getContents();
						String discount = sheet.getCell(3, i).getContents();
						konkaPdModelPrices.setPd_name(pd_name);// 型号
						/*
						 * 判断月份是否为空
						 */
						if (!StringUtils.isNotBlank(price_month)) {
							msg += "请输入第" + (i + 1) + "行的月份！";
							super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
							return null;
						} else {
							konkaPdModelPrices.setPrice_month(Integer.parseInt(price_month));// 月份
						}
						if (!StringUtils.isNotBlank(cash_price)) {
							konkaPdModelPrices.setCash_price(new BigDecimal(0));
						} else {
							konkaPdModelPrices.setCash_price(new BigDecimal(cash_price));
						}
						if (!StringUtils.isNotBlank(sale_price)) {
							konkaPdModelPrices.setSale_price(new BigDecimal(0));
						} else {
							konkaPdModelPrices.setSale_price(new BigDecimal(sale_price));
						}
						if (!StringUtils.isNotBlank(discount)) {
							konkaPdModelPrices.setDiscount(new BigDecimal(0));
						} else {
							konkaPdModelPrices.setDiscount(new BigDecimal(discount));
						}
						konkaPdModelPricesList.add(konkaPdModelPrices);
						size = size + 1;
					} else {
						String sum_contents = "";
						StringBuffer sb = new StringBuffer();
						for (int j = 1; j < 5; j++) {
							if (null != sheet.getCell(j, i)) {
								sb.append(sheet.getCell(j, i).getContents().trim());
							}
						}
						sum_contents = sb.toString();
						if (StringUtils.isNotBlank(sum_contents)) {
							size = size + 1;
						}
						break;
					}
				}
				workbook.close();
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 统计插入总数、判定重复数据
			if (konkaPdModelPricesList != null && konkaPdModelPricesList.size() > 0
					&& konkaPdModelPricesList.size() == size) {
				z = konkaPdModelPricesList.size();
				for (int i = 0; i < konkaPdModelPricesList.size(); i++) {
					KonkaPdModelPrices entity = konkaPdModelPricesList.get(i);
					KonkaPdModelPrices konkaPdModelPrices = new KonkaPdModelPrices();
					konkaPdModelPrices.setPd_name(entity.getPd_name());
					konkaPdModelPrices.setPrice_month(entity.getPrice_month());
					try {
						konkaPdModelPrices = super.getFacade().getKonkaPdModelPricesService()
								.getKonkaPdModelPrices(konkaPdModelPrices);
					} catch (Exception e) {
						super.renderJavaScript(response,
								"alert('产品型号为" + entity.getPd_name() + "月份为" + entity.getPrice_month()
										+ "的数据在数据库中可能存在重复，请联系管理员');history.go(-1);");
						return null;
					}
					if (konkaPdModelPrices != null) {

						entity.setId(konkaPdModelPrices.getId());
						try {
							mon = konkaPdModelPrices.getPrice_month();
							str += konkaPdModelPrices.getPd_name();
							str += "," + mon + ";";
							x = x + 1;
						} catch (Exception ex) {
							log.info(ex.getMessage() + "\n" + (i + 1) + "");
						}
					} else {
						try {
							super.getFacade().getKonkaPdModelPricesService().createKonkaPdModelPrices(entity);
							y = y + 1;
						} catch (Exception ex) {
							log.info(ex.getMessage() + "\n" + (i + 1) + "");
						}
					}
				}
			} else if (konkaPdModelPricesList != null && konkaPdModelPricesList.size() > 0
					&& konkaPdModelPricesList.size() < size) {
				super.renderJavaScript(response, "alert('Excel表格内容错误:第" + (size + 1)
						+ "行的第一列：区域不能为空！');history.go(-1);");
				return null;
			} else {
				super.renderJavaScript(response, "alert('Excel表格内容错误:第一行第一列的区域不能为空！');history.go(-1);");
				return null;
			}
		} else {
			super.renderJavaScript(response, "alert('" + "Excel为空" + "');history.go(-1);");
			return null;
		}
		String msg = null;
		if (x == 0) {
			msg = "总数为：" + z + "条信息，新增了" + y + "条记录。";
		} else if (x > 0) {
			msg = "总数为：" + z + "条信息，新增了" + y + "条记录，有" + x + "条信息与数据库中的数据重复，重复的型号、月份分别为：" + str;
		}
		super.renderJavaScript(response, "alert('" + msg
				+ "');location.href='KonkaPdModelPrices.do?method=list&mod_id=" + mod_id + "';");
		return null;
	}

}
