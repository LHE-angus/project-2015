package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeShop;
import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.mmt.domain.PeShopLeaderRec;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Li,Yuan
 * @version 2011-05-19
 */
public class PeShopAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		Pager pager = (Pager) dynaBean.get("pager");
		PeShop entity = new PeShop();
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeProdUser SxPeProdUser = new PeProdUser();
		SxPeProdUser.setIs_del(0);
		SxPeProdUser.setUser_name(PeProdUser.getUser_name());
		SxPeProdUser = super.getFacade().getPeProdUserService().getPeProdUser(
				SxPeProdUser);
		if (null == SxPeProdUser) {
			String msg = super.getMessage(request,
					"user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		} else {
			if (SxPeProdUser.getUser_type() == 1) {
				entity.setLeader(SxPeProdUser.getId());
			}
		}
		if (StringUtils.isNotBlank(shop_name_like)) {
			entity.getMap().put("shop_name_like", shop_name_like);
		}
		entity.setEntp_id(SxPeProdUser.getProd_entp_id());
		// 2011-8-5修改
		// entity.setState(1);
		Long recordCount = super.getFacade().getPeShopService()
				.getPeShopAndEntpShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PeShop> peShopList = super.getFacade().getPeShopService()
				.getPeShopAndEntpShopPaginatedList(entity);

		request.setAttribute("entityList", peShopList);

		return mapping.findForward("list");
	}

	public ActionForward shopDispach(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.setNaviStringToRequestScope(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String type = (String) dynaBean.get("type");
		String[] pks = (String[]) request.getParameterValues("pks");
		HttpSession session = request.getSession();
		session.setAttribute("pksSession", pks);

		String id = (String) dynaBean.get("id");
		if (StringUtils.isNotBlank(id)) {
			PeShop peShop = new PeShop();
			peShop.setId(Long.valueOf(id));
			peShop = super.getFacade().getPeShopService().getPeShop(peShop);
			super.copyProperties(form, peShop);

			if (null == peShop.getLeader()) {
				dynaBean.set("user_name", null);
			} else {
				PeProdUser ppu = new PeProdUser();
				ppu.setIs_del(0);
				ppu.setId(peShop.getLeader());
				ppu = super.getFacade().getPeProdUserService().getPeProdUser(
						ppu);
				dynaBean.set("user_name", ppu.getUser_name());
			}

			if (null == peShop.getCategory_id()) {
				dynaBean.set("category_name", null);
			} else {
				PeShopCategory entity = new PeShopCategory();
				entity.setIs_del((long) 0);
				entity.setCategory_id(peShop.getCategory_id());
				entity = super.getFacade().getPeShopCategoryService()
						.getPeShopCategory(entity);
				dynaBean.set("category_name", entity.getCategory_name());
			}
		}
		if ("1".equals(type)) {
			return new ActionForward("/admin/PeShop/shop_category.jsp");
		} else {
			request.setAttribute("basePdClassList", getBasePdClazzList());
			return new ActionForward("/admin/PeShop/pe_prod_user.jsp");
		}
	}

	public ActionForward listBasePropCategory(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String category_name_like = (String) dynaBean.get("category_name_like");
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeProdUser SxPeProdUser = new PeProdUser();
		SxPeProdUser.setIs_del(0);
		SxPeProdUser.setUser_name(PeProdUser.getUser_name());
		SxPeProdUser = super.getFacade().getPeProdUserService().getPeProdUser(
				SxPeProdUser);

		PeShopCategory entity = new PeShopCategory();
		entity.setIs_del((long) 0);
		entity.setEntp_id(SxPeProdUser.getProd_entp_id());
		if (StringUtils.isNotBlank(category_name_like)) {
			entity.getMap().put("category_name_like", category_name_like);
		}
		Long recordCount = super.getFacade().getPeShopCategoryService()
				.getPeShopCategoryCount(entity);
		pager.init(recordCount, Integer.valueOf("45"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(Integer.valueOf("45"));
		List<PeShopCategory> entityList = super.getFacade()
				.getPeShopCategoryService().getPeShopCategoryPaginatedList(
						entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/PeShop/list_peshopcategory.jsp");
	}

	public ActionForward listPeProdUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String user_name_like = (String) dynaBean.get("user_name_like");

		PeProdUser entity = new PeProdUser();
		entity.setIs_del(0);
		entity.setPosition_id(Long.valueOf(0));
		if (StringUtils.isNotBlank(user_name_like)) {
			entity.getMap().put("user_name_like", user_name_like);
		}
		Long recordCount = super.getFacade().getPeProdUserService()
				.getPeProdUserCount(entity);
		pager.init(recordCount, Integer.valueOf("45"), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(Integer.valueOf("45"));
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService()
				.getPeProdUserPaginatedList(entity);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/PeShop/list_peproduser.jsp");
	}

	// public ActionForward view(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// saveToken(request);
	// setNaviStringToRequestScope(form, request);
	//
	// DynaBean dynaBean = (DynaBean) form;
	// String id = (String) dynaBean.get("id");
	// String shop_id = (String) dynaBean.get("shop_id");
	//
	// if (!GenericValidator.isLong(shop_id)) {
	// saveError(request, "errors.long", new String[] { shop_id });
	// return mapping.findForward("list");
	// }
	//
	// EntpShop entity = new EntpShop();
	// entity.setShop_id(Long.valueOf(shop_id));
	//
	// entity = super.getFacade().getEntpShopService().getEntpShop(entity);
	//
	// entity.getMap().put("p_index_name",
	// super.getPIndexName(entity.getP_index().toString(), ""));
	//
	// MvShopPdtypeAndBrand mspb_cls = new MvShopPdtypeAndBrand();
	// mspb_cls.setShop_id(Long.valueOf(shop_id));
	//
	// List<MvShopPdtypeAndBrand> mspb_clsList =
	// super.getFacade().getMvShopPdtypeAndBrandService()
	// .getDistinctClsIdByShopIdList(mspb_cls);
	//
	// if (null != mspb_clsList && mspb_clsList.size() > 0) {
	// String mspb_clsList_string = "";
	// for (MvShopPdtypeAndBrand temp : mspb_clsList) {
	// mspb_clsList_string += temp.getMap().get("cls_name") + ",";
	// }
	// entity.getMap().put("cls_names", mspb_clsList_string.substring(0,
	// mspb_clsList_string.length() - 1));
	// }
	//
	// MvShopPdtypeAndBrand mspb_brand = new MvShopPdtypeAndBrand();
	// mspb_brand.setShop_id(Long.valueOf(shop_id));
	//
	// List<MvShopPdtypeAndBrand> mspb_brandList =
	// super.getFacade().getMvShopPdtypeAndBrandService()
	// .getDistinctBrandIdByShopIdList(mspb_brand);
	//
	// if (null != mspb_brandList && mspb_brandList.size() > 0) {
	// String mspb_brandList_string = "";
	// for (MvShopPdtypeAndBrand temp : mspb_brandList) {
	// mspb_brandList_string += temp.getMap().get("brand_name") + ",";
	// }
	// entity.getMap().put("brand_names", mspb_brandList_string.substring(0,
	// mspb_brandList_string.length() - 1));
	// }
	// super.copyProperties(form, entity);
	//
	// PeShopLeaderRec plr = new PeShopLeaderRec();
	// plr.setPe_shop_id(Long.valueOf(id));
	// List<PeShopLeaderRec> plrList =
	// super.getFacade().getPeShopLeaderRecService().getPeShopLeaderRecWithNameList(
	// plr);
	// if (null != plrList) {
	// ArrayList<String> strList = new ArrayList<String>();
	// for (PeShopLeaderRec pslr : plrList) {
	// strList.add((String) pslr.getMap().get("user_name"));
	// }
	// dynaBean.set("user_name_list", StringUtils.join(strList, " &gt; "));
	// }
	// return mapping.findForward("view");
	// }

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String returnUrl = (String) dynaBean.get("returnUrl");
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();
		String[] pks = (String[]) session.getAttribute("pksSession");
		session.removeAttribute("pksSession");
		PeProdUser PeProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		PeProdUser SxPeProdUser = new PeProdUser();
		SxPeProdUser.setIs_del(0);
		SxPeProdUser.setUser_name(PeProdUser.getUser_name());

		PeShop entity = new PeShop();
		super.copyProperties(entity, form);
		if (StringUtils.isBlank(id)) {
			entity.getMap().put("pks", pks);
			if (null != entity.getLeader()) {
				for (int i = 0; i < pks.length; i++) {
					PeShop ps = new PeShop();
					ps.setId(Long.valueOf(pks[i]));
					ps = super.getFacade().getPeShopService().getPeShop(ps);
					PeShopLeaderRec pslr = new PeShopLeaderRec();
					pslr.setPe_shop_id(Long.valueOf(pks[i]));
					pslr.setOld_leader(ps.getLeader());
					pslr.setNew_leader(entity.getLeader());
					pslr.setAdd_e_user_id(SxPeProdUser.getId());
					pslr.setAdd_date(new Date());
					super.getFacade().getPeShopLeaderRecService()
							.createPeShopLeaderRec(pslr);
				}
			}
		} else {
			if (null != entity.getLeader()) {
				PeShop ps = new PeShop();
				ps.setId(Long.valueOf(id));
				ps = super.getFacade().getPeShopService().getPeShop(ps);

				PeShopLeaderRec pslr = new PeShopLeaderRec();
				pslr.setPe_shop_id(Long.valueOf(id));
				pslr.setOld_leader(ps.getLeader());
				pslr.setNew_leader(entity.getLeader());
				pslr.setAdd_e_user_id(SxPeProdUser.getId());
				pslr.setAdd_date(new Date());
				super.getFacade().getPeShopLeaderRecService()
						.createPeShopLeaderRec(pslr);
			}

		}

		super.getFacade().getPeShopService().modifyPeShop(entity);
		if (StringUtils.isNotBlank(returnUrl)) {
			response.sendRedirect(URLDecoder.decode(returnUrl,
					Constants.SYS_ENCODING));
			return null;
		}
		saveMessage(request, "entity.updated");
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&").append(
				super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward saveShopState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");

		PeShop entity = new PeShop();
		super.copyProperties(entity, form);

		super.getFacade().getPeShopService().modifyPeShop(entity);
		saveMessage(request, "prodadmin.peshop.findshop.save.success");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&").append(
				super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// public ActionForward sellDataList(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// DynaBean dynaBean = (DynaBean) form;
	// Pager pager = (Pager) dynaBean.get("pager");
	// String shop_id = (String) dynaBean.get("shop_id");
	//
	// StdEntpMain stdEntpMain = new StdEntpMain();
	// super.copyProperties(stdEntpMain, form);
	// dynaBean.set("shop_id", shop_id);
	//
	// Long recordCount =
	// super.getFacade().getStdEntpMainService().getStdEntpMainCount(stdEntpMain);
	//
	// pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
	// stdEntpMain.getRow().setFirst(pager.getFirstRow());
	// stdEntpMain.getRow().setCount(pager.getRowCount());
	//
	// List<StdEntpMain> stdEntpMainList =
	// super.getFacade().getStdEntpMainService().getStdEntpMainPaginatedList(
	// stdEntpMain);
	// request.setAttribute("stdEntpMainList", stdEntpMainList);
	//
	// return new
	// ActionForward("/../WEB-INF/pages/jsp/prodadmin/PeShop/list_selldata.jsp");
	// }

	// public ActionForward sellDataExportPage(ActionMapping mapping, ActionForm
	// form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// DynaBean dynaBean = (DynaBean) form;
	// String shop_id = (String) dynaBean.get("shop_id");
	//
	// if (StringUtils.isBlank(shop_id)) {
	// String msg = super.getMessage(request, "client.wdqbz.bind.fail");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// StdEntpMain sem = new StdEntpMain();
	// sem.setShop_id(Long.valueOf(shop_id));
	// sem.setOwn_sys(1);
	//
	// List<StdEntpMain> semList =
	// super.getFacade().getStdEntpMainService().getStdEntpMainList(sem);
	//
	// if (null == semList || semList.size() == 0) {
	// String msg = super.getMessage(request, "client.wdqbz.bind.fail");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// dynaBean.set("entp_id", semList.get(0).getEntp_id());
	//
	// return new
	// ActionForward("/../WEB-INF/pages/jsp/prodadmin/PeShop/export_selldata.jsp");
	// }

	// public ActionForward sellDataExport(ActionMapping mapping, ActionForm
	// form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// DynaBean dynaBean = (DynaBean) form;
	// String entp_id = (String) dynaBean.get("entp_id");
	// String s_date = (String) dynaBean.get("s_date");
	// String e_date = (String) dynaBean.get("e_date");
	//
	// if (StringUtils.isBlank(entp_id)) {
	// String msg = super.getMessage(request, "client.wdqbz.bind.fail");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// StdEntpMain sem = new StdEntpMain();
	// sem.setEntp_id(Long.valueOf(entp_id));
	// sem.setOwn_sys(1);
	//
	// sem = super.getFacade().getStdEntpMainService().getStdEntpMain(sem);
	//
	// if (null == sem) {
	// String msg = super.getMessage(request, "client.wdqbz.bind.fail");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// request.setAttribute("stdEntpMain", sem);
	//
	// if (StringUtils.isBlank(s_date) || StringUtils.isBlank(e_date)) {
	// return new
	// ActionForward("/../WEB-INF/pages/jsp/prodadmin/PeShop/export_selldata.jsp");//
	// 第一次加载页面，不允许查询所有记录，需要选择时间段，时间跨度不能超过60天
	// }
	//
	// Date start_date = DateUtils.parseDate(s_date, new String[] { "yyyy-MM-dd"
	// });
	// Date end_date = DateUtils.parseDate(e_date, new String[] { "yyyy-MM-dd"
	// });
	// if ((new Date().getTime() - end_date.getTime()) / (60 * 60 * 24 * 1000) <
	// 1) {
	// String msg = super.getMessage(request,
	// "client.wdqbz.jdxx.sell.excel.endtime.long");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	// if ((end_date.getTime() - start_date.getTime()) / (60 * 60 * 24 * 1000) >
	// 60) {
	// String msg = super.getMessage(request,
	// "client.wdqbz.jdxx.sell.excel.time.long");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// JdxxEntpSell sell = new JdxxEntpSell();
	// sell.setSell_entp_id(Long.valueOf(sem.getEntp_id()));
	// sell.getMap().put("sell_date_ge", s_date);
	// sell.getMap().put("sell_date_le", e_date);
	//
	// List<JdxxEntpSell> sellList =
	// super.getFacade().getJdxxEntpSellService().getJdxxEntpSellWithMdNameList(sell);
	// request.setAttribute("sellList", sellList);
	//
	// return new
	// ActionForward("/../WEB-INF/pages/jsp/prodadmin/PeShop/export_selldata.jsp");
	// }

	// public ActionForward sellDataStatistics(ActionMapping mapping, ActionForm
	// form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// DynaBean dynaBean = (DynaBean) form;
	// String shop_id = (String) dynaBean.get("shop_id");
	//
	// if (StringUtils.isBlank(shop_id)) {
	// String msg = super.getMessage(request, "client.wdqbz.bind.fail");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// StdEntpMain sem = new StdEntpMain();
	// sem.setShop_id(Long.valueOf(shop_id));
	// sem.setOwn_sys(1);
	//
	// List<StdEntpMain> semList =
	// super.getFacade().getStdEntpMainService().getStdEntpMainList(sem);
	//
	// if (null == semList || semList.size() == 0) {
	// String msg = super.getMessage(request, "client.wdqbz.bind.fail");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// StdEntpUser seu = new StdEntpUser();
	// seu.setEntp_id(semList.get(0).getEntp_id());
	// seu.setUser_state(0);
	//
	// List<StdEntpUser> seuList =
	// super.getFacade().getStdEntpUserService().getStdEntpUserList(seu);
	//
	// if (null == seuList || seuList.size() == 0) {
	// String msg = super.getMessage(request, "client.wdqbz.bind.fail");
	// super.renderJavaScript(response, "window.onload=function(){alert('" + msg
	// + "');history.back();}");
	// return null;
	// }
	//
	// dynaBean.set("keySeq", seuList.get(0).getKey_seq());
	//
	// return new
	// ActionForward("/../WEB-INF/pages/jsp/prodadmin/PeShop/statistics_selldata.jsp");
	// }

	public ActionForward toExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding(Constants.SYS_ENCODING);

		String name = request.getParameter("hiddenName");

		String html = request.getParameter("hiddenHtml");

		try {

			response.setContentType("application/vnd.ms-excel");

			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(name, Constants.SYS_ENCODING)
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

			out.println("<title>" + name + "</title>");

			out.println("</head>");

			html = html.replace("border=0", "border=1");

			// html = html.replace("<A href=\"[^>]*?\">([^<]*?)<\/A>", "$1");

			out.println(html);
			out.println("</body>");

			out.println("</html>");

			out.println("<body>");

		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}
}
