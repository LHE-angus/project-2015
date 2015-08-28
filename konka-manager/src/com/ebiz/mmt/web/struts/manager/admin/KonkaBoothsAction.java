package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaBooths;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaBoothsAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		return mapping.findForward("input");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;

		super.encodeCharacterForGetMethod(dynaBean, request);

		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String branch_area_name_select = (String) dynaBean
				.get("branch_area_name_select");

		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);

		KonkaBooths entity = new KonkaBooths();
		entity.getMap().put("date_start", date_start);
		entity.getMap().put("date_end", date_end);
		entity.getMap().put("r3_code_like", r3_code_like);
		entity.getMap().put("branch_area_name_select", branch_area_name_select);

		Long recordCount = getFacade().getKonkaBoothsService()
				.getKonkaBoothsCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBooths> entityList = getFacade().getKonkaBoothsService()
				.getKonkaBoothsPaginatedList(entity);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(super.getSessionUserInfo(request).getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30 = false;
		for (PeRoleUser peRoleUser: peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
		}
		
		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setIs_del(0L);
		if (role_id_ge_30) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (dept.getDept_type() == 3) {// 分公司
				r3.setBranch_area_name(dept.getDept_name());
				dynaBean.set("branch_area_name", dept.getDept_name());
			} else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {// 经营部
				r3.setBranch_area_name(super.getSuperiorForDeptType(
						dept.getDept_id(), 3).getDept_name());
				dynaBean.set("branch_area_name", super.getSuperiorForDeptType(
						dept.getDept_id(), 3).getDept_name());
			}
		}
		List<KonkaR3Shop> BranchList = getFacade().getKonkaR3ShopService()
				.getKonkaR3ShopGroupByBranchAreaName(r3);

		super.copyProperties(entity, form);
		request.setAttribute("BranchList", BranchList);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	@Override
	public ActionForward toExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		DynaBean dynaBean = (DynaBean) form;
		String date_start = (String) dynaBean.get("date_start");
		String date_end = (String) dynaBean.get("date_end");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		KonkaBooths entity = new KonkaBooths();
		entity.getMap().put("date_start", date_start);
		entity.getMap().put("date_end", date_end);
		entity.getMap().put("r3_code_like", r3_code_like);
		List<KonkaBooths> entityList = getFacade().getKonkaBoothsService()
				.getKonkaBoothsList(entity);
		String html = "<table border='1'>";
		html += "<tr>";
		html += "<td>序号</td>";
		html += "<td>门店名称</td>";
		html += "<td>年累计销售额</td>";
		html += "<td>展台延米数</td>";
		html += "<td>有无直销员</td>";
		html += "<td>直销员姓名</td>";
		html += "<td>直销员电话</td>";
		html += "<td>最近改造时间</td>";
		html += "<td>形象照片</td>";
		html += "<td>门店地址</td>";
		html += "</tr>";
		int i = 1;
		for (KonkaBooths _t : entityList) {
			html += "<tr>";
			html += "<td border='1'>" + i + "</td>";
			html += "<td border='1'>" + _t.getMap().get("r3_name") + "</td>";
			html += "<td border='1'>" + _t.getBooths_sale() + "</td>";
			html += "<td border='1'>" + _t.getBooths_num() + "</td>";
			html += "<td border='1'>" + (_t.getIf_man() == 0 ? "无" : "有")
					+ "</td>";
			html += "<td border='1'>" + _t.getMan_name() + "</td>";
			html += "<td border='1'>" + _t.getMan_phone() + "</td>";
			html += "<td border='1'>" + _t.getMap().get("last_rebuild")
					+ "</td>";
			html += "<td border='1'>"
					+ (_t.getPhotos() != null ? "<a href=\"http://konka.mymyty.com/"
							+ _t.getPhotos() + "\">图片</a>"
							: "") + "</td>";
			html += "<td border='1'>" + _t.getMap().get("r3_addr") + "</td>";
			html += "</tr>";
			i++;
		}
		html += "</table>";

		html = html.replace("null", "");
		response.setCharacterEncoding("utf-8");

		if (entityList.size() > 0) {

			try {
				response.setContentType("application/vnd.ms-excel");

				response
						.addHeader("Content-Disposition",
								"attachment; filename=\""
										+ URLEncoder.encode("展台展柜", "UTF-8")
										+ ".xls\"");

				PrintWriter out = response.getWriter();

				out
						.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
				out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
				out.println("<head>");
				out
						.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
				out
						.println("<meta http-equiv=\"MSThemeCompatible\" content=\"no\" />");
				out
						.println("<meta name=\"MSSmartTagsPreventParsing\" content=\"true\" />");
				out.println("<title>展台展柜</title>");
				out.println("</head>");

				// html = html.replace("<A
				// href=\"[^>]*?\">([^<]*?)<\/A>", "$1");

				out.println(html);

				out.println("</body>");
				out.println("</html>");
				out.println("<body>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
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
		// List<UploadFile> uploadFiles = super.uploadFile(form, false);
		List<UploadFile> uploadFiles = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaBooths entity = new KonkaBooths();
		super.copyProperties(entity, form);
		if (0 != uploadFiles.size()) {
			entity.setPhotos(uploadFiles.get(0).getFileSavePath());
		}
		entity.setAdd_man(userInfo.getId());
		entity.setAdd_time(new java.util.Date());

		if (null == entity.getBooths_id()) {
			getFacade().getKonkaBoothsService().createKonkaBooths(entity);
			saveMessage(request, "entity.inserted");
		} else {
			getFacade().getKonkaBoothsService().modifyKonkaBooths(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaBooths entity = new KonkaBooths();
			entity.setBooths_id(new Long(id));
			entity = getFacade().getKonkaBoothsService().getKonkaBooths(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaBooths entity = new KonkaBooths();
			entity.setBooths_id(new Long(id));
			getFacade().getKonkaBoothsService().removeKonkaBooths(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaBooths entity = new KonkaBooths();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaBoothsService().modifyKonkaBooths(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaBooths entity = new KonkaBooths();
			entity.setBooths_id(new Long(id));
			entity = getFacade().getKonkaBoothsService().getKonkaBooths(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}
}