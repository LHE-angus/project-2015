package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class R3UserOperateAnalysisAction extends BaseAction {
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
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");

		Integer pageSize = pager.getPageSize();
		String downloadAll = (String) dynaBean.get("downloadAll");

		KonkaR3Shop entity = this.getKonkaR3ShopForSelect(mapping, form, request, response, null);
		entity.setIs_match(null);
		entity.getMap().put("key", 1);

		if (GenericValidator.isLong(fgs_dept_id)) {
			entity.getMap().put("konka_dept_id", fgs_dept_id);
		}
		if (GenericValidator.isLong(jyb_dept_id)) {
			entity.getMap().put("konka_dept_id", jyb_dept_id);
		}
		if (GenericValidator.isLong(bsc_dept_id)) {
			entity.getMap().put("konka_dept_id", bsc_dept_id);
		}
		if (GenericValidator.isLong(ywy_user_id)) {
			entity.getMap().put("ywy_user_id", ywy_user_id);
		}

		Long recordCount = this.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);

		super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

		// 处理是否全部下载
		if (StringUtils.isNotEmpty(downloadAll) && "all".equals(downloadAll)) {
			pager.init(recordCount, recordCount.intValue(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());
			List<KonkaR3Shop> downloadList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);
			// 查询所在省市区的名称
			// for(KonkaR3Shop t :downloadList){
			// BaseProvinceList baseProvinceList = new BaseProvinceList();
			// if(null!=t.getMap().get("p_index")){
			// baseProvinceList.setP_index(Long.valueOf((t.getMap().get("p_index").toString())));
			// t.getMap().put("list",
			// getFacade().getBaseProvinceListService().getBaseProvinceListParentList(baseProvinceList));
			// }
			// }
			request.setAttribute("downloadList", downloadList);
			pager.init(recordCount, pageSize, pager.getRequestPage());
		} else {
			// 查询所在省市区的名称
			for (KonkaR3Shop t : entityList) {
				BaseProvinceList baseProvinceList = new BaseProvinceList();
				if (null != t.getMap().get("p_index")) {
					baseProvinceList.setP_index(Long.valueOf((t.getMap().get("p_index").toString())));
					t.getMap().put("list",
							getFacade().getBaseProvinceListService().getBaseProvinceListParentList(baseProvinceList));
				}
			}
			request.setAttribute("downloadList", entityList);
		}
		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));

		return mapping.findForward("list");
	}

	/**
	 * @modifier Wang,Yang;
	 * @datetime 2011-10-20 14:50:49
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		saveToken(request);
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		DynaBean dynaBean = (DynaBean) form;
		String shop_id = (String) dynaBean.get("shop_id");

		if (GenericValidator.isLong(shop_id)) {
			EntpShop entity = new EntpShop();

			entity.setShop_id(new Long(shop_id));
			entity = getFacade().getEntpShopService().getEntpShop(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			String shop_latlng = null;
			if (null == entity.getG_lat_t() || null == entity.getG_lng_t()) {
				shop_latlng = entity.getG_lat() + "," + entity.getG_lng();
			} else {
				shop_latlng = entity.getG_lat_t() + "," + entity.getG_lng_t();
			}

			// the line below is added for pagination
			entity.setQueryString(super.serialize(request, "id", "method"));

			// end
			request.setAttribute("shop_latlng", shop_latlng);
			entity.setQueryString(super.serialize(request, "str_pks", "method"));
			super.copyProperties(form, entity);

			return mapping.findForward("input");
		} else {
			saveError(request, "errors.long", shop_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_code = (String) dynaBean.get("mod_code");
		String queryString = (String) dynaBean.get("queryString");
		String shop_id = (String) dynaBean.get("shop_id");
		String keySeq = (String) dynaBean.get("keySeq");
		String own_sys = (String) dynaBean.get("own_sys");

		if (!GenericValidator.isLong(shop_id)) {
			saveError(request, "errors.long", shop_id);
			return mapping.findForward("list");
		}
		MmtEntpShop konkaEntpShop = new MmtEntpShop();
		EntpShop entpShop = new EntpShop();

		entpShop.setShop_id(Long.valueOf(shop_id));
		entpShop = getFacade().getEntpShopService().getEntpShop(entpShop);

		konkaEntpShop.setShop_id(Long.valueOf(shop_id));
		konkaEntpShop = getFacade().getMmtEntpShopService().getMmtEntpShop(konkaEntpShop);

		if (null == entpShop || null == konkaEntpShop) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		super.copyProperties(entpShop, form);
		super.copyProperties(konkaEntpShop, form);

		// 密钥，密钥类型为空检查
		if (StringUtils.isEmpty(keySeq) || StringUtils.isEmpty(own_sys)) {
			String msg = getMessage(request, "client.keyseq.error");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 查询密钥信息
		StdEntpUser seu = new StdEntpUser();
		seu.getRow().setCount(2);
		seu.setKey_seq(keySeq);
		List<StdEntpUser> seuList = super.getFacade().getStdEntpUserService().getStdEntpUserList(seu);

		// 判断密钥是否存在
		if (seuList == null || seuList.size() == 0) {
			String msg = super.getMessage(request, "client.login.keyseq.no.need.active");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 密钥在数据库数据有多条数据
		if (seuList.size() >= 2) {
			logger.error("key info toomany:{}", keySeq);
			String msg = super.getMessage(request, "client.login.stdentpuser.key.many");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 判断密钥是否被注销
		Integer stdEntpUserState = seuList.get(0).getUser_state();
		if ((null == stdEntpUserState) || ("1".equals(own_sys) && 0 != stdEntpUserState)
				|| ("2".equals(own_sys) && 1 != stdEntpUserState)) {
			String msg = super.getMessage(request, "client.login.userstate.error");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取企业信息
		StdEntpMain sem = new StdEntpMain();
		sem.getRow().setCount(2);
		sem.setEntp_id(seuList.get(0).getEntp_id());
		sem.setOwn_sys(seuList.get(0).getOwn_sys());
		List<StdEntpMain> semList = super.getFacade().getStdEntpMainService().getStdEntpMainList(sem);

		// 判断企业信息是否丢失
		if (null == semList || semList.size() == 0) {
			String msg = super.getMessage(request, "client.login.fail.stdentpmain.error");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 判断企业数据是否唯一
		if (semList.size() >= 2) {
			String msg = super.getMessage(request, "client.login.stdentman.tomany");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 判断插入的密钥是否与网点匹配
		Long entpShopId = semList.get(0).getShop_id();
		if (!entpShopId.equals(Long.valueOf(shop_id))) {
			String msg = super.getMessage(request, "client.register.userinfo.error");
			renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (null != entpShop.getG_lat_t() && null != entpShop.getG_lng_t()) {
			getFacade().getEntpShopService().modifyEntpShop(entpShop);
			getFacade().getMmtEntpShopService().modifyMmtEntpShop(konkaEntpShop);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_code=" + mod_code);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(queryString));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
