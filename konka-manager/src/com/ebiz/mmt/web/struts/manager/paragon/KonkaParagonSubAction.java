package com.ebiz.mmt.web.struts.manager.paragon;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaParagonEquipmentC;
import com.ebiz.mmt.domain.KonkaParagonEquipmentJ;
import com.ebiz.mmt.domain.KonkaParagonEtcash;
import com.ebiz.mmt.domain.KonkaParagonManinfo;
import com.ebiz.mmt.domain.KonkaParagonSales;
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.mmt.domain.KonkaParagonShowt;
import com.ebiz.mmt.domain.KonkaParagonShowversion;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.PinyinTools;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaParagonSubAction extends BaseAction {

	@SuppressWarnings("unused")
	private KonkaParagonSales sales = new KonkaParagonSales();

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.setPageSize(15);
		String shop_order_like = (String) dynaBean.get("shop_order_like");
		String shop_name_like = (String) dynaBean.get("shop_name_like");
		String channel_name_like = (String) dynaBean.get("channel_name_like");

		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		if (StringUtils.isNotBlank(shop_order_like)) {
			entity.setShow_shop_code(shop_order_like);
		}
		if (StringUtils.isNotBlank(shop_name_like)) {
			entity.getMap().put("shop_name_like", shop_name_like);
		}
		if (StringUtils.isNotBlank(channel_name_like)) {
			entity.getMap().put("channel_name_like", channel_name_like);
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);
		entity.getMap().put("user_id", ui.getId());
		Long recordCount = getFacade().getKonkaParagonShowinfoService()
				.selectKonkaParagonShowinfoCountForsub(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowinfo> entityList = getFacade()
				.getKonkaParagonShowinfoService()
				.selectKonkaParagonShowinfoPaginatedListForSub(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
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
		String scode = (String) dynaBean.get("scode");
		String fixdate = (String) dynaBean.get("fixdate");
		String oldate = (String) dynaBean.get("oldate");
		Date now = new Date();

		SimpleDateFormat _ft = new SimpleDateFormat("yyyyMM");
		if (StringUtils.isEmpty(fixdate)) {
			fixdate = _ft.format(now);
		}

		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);

		dynaBean.set("queryString", super.serialize(request, "id", "method"));

		if (StringUtils.isEmpty(scode)) {
			renderJavaScript(response,
					"window.onload=function(){alert('你没有可以填写数据的门店，请先关联');history.back();}");
			return null;
		}
		// 取已关联门店
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		entity.setShow_shop_code(scode);
		entity = super.getFacade().getKonkaParagonShowinfoService()
				.getKonkaParagonShowinfoForView(entity);
		if (entity == null) {
			renderJavaScript(response,
					"window.onload=function(){alert('门店信息不存在，请先维护');history.back();}");
			return null;
		}
		// 取此门店促销员
		KonkaParagonManinfo t = new KonkaParagonManinfo();
		t.setShow_shop_code(scode);
		t.setFixdate(fixdate);
		List<KonkaParagonManinfo> sellerList = super.getFacade()
				.getKonkaParagonManinfoService().getKonkaParagonManinfoList(t);
		// 取此门店展台展柜信息
		KonkaParagonShowt tShow = new KonkaParagonShowt();
		tShow.setShow_shop_code(scode);
		tShow.setFixdate(fixdate);
		List<KonkaParagonShowt> tShowList = super.getFacade()
				.getKonkaParagonShowtService().getKonkaParagonShowtList(tShow);
		// 取此形象版本
		KonkaParagonShowversion version = new KonkaParagonShowversion();
		List<KonkaParagonShowversion> versionList = super.getFacade()
				.getKonkaParagonShowversionService()
				.getKonkaParagonShowversionList(version);
		request.setAttribute("versionList", versionList);

		// 取本年销售额、进场费
		KonkaParagonEtcash etcash = new KonkaParagonEtcash();
		etcash.setShow_shop_code(scode);
		etcash.setEt_year(fixdate);
		etcash = super.getFacade().getKonkaParagonEtcashService()
				.getKonkaParagonEtcash(etcash);
		if (etcash != null)
			entity.setEtcash(etcash);

		KonkaParagonSales sales = new KonkaParagonSales();
		sales.setShow_shop_code(scode);
		sales.setSale_year(fixdate);
		sales = super.getFacade().getKonkaParagonSalesService()
				.getKonkaParagonSales(sales);
		if (sales != null)
			entity.setSales(sales);

		super.copyProperties(form, entity);

		KonkaParagonEquipmentC tc = new KonkaParagonEquipmentC();
		tc.setStatus(1);
		tc.getMap().put("shop_code", scode);
		tc.getMap().put("fixdate", fixdate);
		tc.setEtype(1);// 演示设备
		List<KonkaParagonEquipmentC> setList = super.getFacade()
				.getKonkaParagonEquipmentCService()
				.getKonkaParagonEquipmentList(tc);
		tc.setEtype(2);// 样机
		List<KonkaParagonEquipmentC> machineList = super.getFacade()
				.getKonkaParagonEquipmentCService()
				.getKonkaParagonEquipmentList(tc);
		request.setAttribute("showtList", tShowList);
		request.setAttribute("sellerList", sellerList);
		request.setAttribute("setList", setList);
		request.setAttribute("machineList", machineList);
		request.setAttribute("makeDate", fixdate);
		request.setAttribute("oldate", oldate);
		request.setAttribute("scode", scode);
		return mapping.findForward("input");
	}

	public ActionForward load(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String scode = (String) dynaBean.get("scode");
		String oldate = (String) dynaBean.get("oldate");
		String fixdate = (String) dynaBean.get("fixdate");
		Date now = new Date();

		SimpleDateFormat _ft = new SimpleDateFormat("yyyyMM");
		if (StringUtils.isEmpty(fixdate)) {
			fixdate = _ft.format(now);
		}

		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);

		dynaBean.set("queryString", super.serialize(request, "id", "method"));

		if (StringUtils.isEmpty(scode)) {
			renderJavaScript(response,
					"window.onload=function(){alert('你没有可以填写数据的门店，请先关联');history.back();}");
			return null;
		}
		// 取已关联门店
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		entity.setShow_shop_code(scode);
		entity = super.getFacade().getKonkaParagonShowinfoService()
				.getKonkaParagonShowinfoForView(entity);
		if (entity == null) {
			renderJavaScript(response,
					"window.onload=function(){alert('门店信息不存在，请先维护');history.back();}");
			return null;
		}
		// 取此门店促销员
		KonkaParagonManinfo t = new KonkaParagonManinfo();
		t.setShow_shop_code(scode);
		t.setFixdate(oldate);
		List<KonkaParagonManinfo> sellerList = super.getFacade()
				.getKonkaParagonManinfoService().getKonkaParagonManinfoList(t);
		// 取此门店展台展柜信息
		KonkaParagonShowt tShow = new KonkaParagonShowt();
		tShow.setShow_shop_code(scode);
		tShow.setFixdate(oldate);
		List<KonkaParagonShowt> tShowList = super.getFacade()
				.getKonkaParagonShowtService().getKonkaParagonShowtList(tShow);
		// 取此形象版本
		KonkaParagonShowversion version = new KonkaParagonShowversion();
		List<KonkaParagonShowversion> versionList = super.getFacade()
				.getKonkaParagonShowversionService()
				.getKonkaParagonShowversionList(version);
		request.setAttribute("versionList", versionList);

		// 取本年销售额、进场费
		KonkaParagonEtcash etcash = new KonkaParagonEtcash();
		etcash.setShow_shop_code(scode);
		etcash.setEt_year(oldate);
		etcash = super.getFacade().getKonkaParagonEtcashService()
				.getKonkaParagonEtcash(etcash);
		if (etcash != null)
			entity.setEtcash(etcash);

		KonkaParagonSales sales = new KonkaParagonSales();
		sales.setShow_shop_code(scode);
		sales.setSale_year(oldate);
		sales = super.getFacade().getKonkaParagonSalesService()
				.getKonkaParagonSales(sales);
		if (sales != null)
			entity.setSales(sales);

		super.copyProperties(form, entity);

		KonkaParagonEquipmentC tc = new KonkaParagonEquipmentC();
		tc.setStatus(1);
		tc.getMap().put("shop_code", scode);
		tc.getMap().put("fixdate", oldate);
		tc.setEtype(1);// 演示设备
		List<KonkaParagonEquipmentC> setList = super.getFacade()
				.getKonkaParagonEquipmentCService()
				.getKonkaParagonEquipmentList(tc);
		tc.setEtype(2);// 样机
		List<KonkaParagonEquipmentC> machineList = super.getFacade()
				.getKonkaParagonEquipmentCService()
				.getKonkaParagonEquipmentList(tc);
		request.setAttribute("showtList", tShowList);
		request.setAttribute("sellerList", sellerList);
		request.setAttribute("setList", setList);
		request.setAttribute("machineList", machineList);
		request.setAttribute("makeDate", fixdate);
		request.setAttribute("oldate", oldate);
		request.setAttribute("scode", scode);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		resetToken(request);

		DynaBean dynaBean = (DynaBean) form;

		String mod_id = (String) dynaBean.get("mod_id");
		String[] seller_ids = (String[]) request
				.getParameterValues("a_seller_id");
		String[] seller_names = (String[]) request
				.getParameterValues("a_seller_name");
		String[] seller_phones = (String[]) request
				.getParameterValues("a_seller_phone");
		String[] setids = (String[]) request.getParameterValues("set_1");
		String[] macids = (String[]) request.getParameterValues("set_2");
		String[] start_1_times = (String[]) request
				.getParameterValues("set_1_start");
		String[] end_1_times = (String[]) request
				.getParameterValues("set_1_end");
		String[] setnum = (String[]) request.getParameterValues("set_1_num");
		String[] macnum = (String[]) request.getParameterValues("set_2_num");
		String[] start_2_times = (String[]) request
				.getParameterValues("set_2_start");
		String[] end_2_times = (String[]) request
				.getParameterValues("set_2_end");
		String make_date = (String) dynaBean.get("make_date");
		String sales_val = (String) dynaBean.get("sales_val");
		String etcash_val = (String) dynaBean.get("etcash_val");

		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		super.copyProperties(entity, form);
		entity.setShow_shop_code(entity.getShow_shop_code().toUpperCase());
		entity.getMap().put("fixdate", make_date);

		// 处理直销员
		List<KonkaParagonManinfo> manList = new ArrayList<KonkaParagonManinfo>();
		if (seller_ids != null)
			for (int i = 0; i < seller_ids.length; i++) {
				KonkaParagonManinfo _tman = new KonkaParagonManinfo();
				_tman.setAddman(userInfo.getId());
				_tman.setAddtime(new Date());
				_tman.setSeller_name(seller_names[i]);
				_tman.setSeller_phone(seller_phones[i]);
				_tman.setShow_shop_code(entity.getShow_shop_code());
				_tman.setFixdate(make_date);
				manList.add(_tman);
			}
		entity.setManList(manList);

		// 处理演示设备
		List<KonkaParagonEquipmentJ> set1List = new ArrayList<KonkaParagonEquipmentJ>();

		if (setids != null)
			for (int i = 0; i < setids.length; i++) {
				KonkaParagonEquipmentJ set1 = new KonkaParagonEquipmentJ();
				set1.setAddman(userInfo.getId());
				set1.setAddtime(new Date());
				set1.setEquipment_id(Long.parseLong(setids[i]));
				if (StringUtils.isNotBlank(start_1_times[i])) {
					set1.setStartime(java.sql.Date.valueOf(start_1_times[i]));
				}
				if (StringUtils.isNotBlank(end_1_times[i])) {
					set1.setEndtime(java.sql.Date.valueOf(end_1_times[i]));
				}
				set1.setShow_shop_code(entity.getShow_shop_code());

				if (StringUtils.isNotBlank(setnum[i])) {
					set1.setEquipment_num(Long.parseLong(setnum[i]));
					set1.setFixdate(make_date);
					set1List.add(set1);
				}
			}
		entity.setSet1List(set1List);

		// 处理样机
		List<KonkaParagonEquipmentJ> set2List = new ArrayList<KonkaParagonEquipmentJ>();
		if (macids != null)
			for (int i = 0; i < macids.length; i++) {
				KonkaParagonEquipmentJ set2 = new KonkaParagonEquipmentJ();
				set2.setAddman(userInfo.getId());
				set2.setAddtime(new Date());
				set2.setEquipment_id(Long.parseLong(macids[i]));
				set2.setShow_shop_code(entity.getShow_shop_code());
				if (StringUtils.isNotBlank(start_2_times[i])) {
					set2.setStartime(java.sql.Date.valueOf(start_2_times[i]));
				}
				if (StringUtils.isNotBlank(end_2_times[i])) {
					set2.setEndtime(java.sql.Date.valueOf(end_2_times[i]));
				}
				if (StringUtils.isNotBlank(macnum[i])) {
					set2.setEquipment_num(Long.parseLong(macnum[i]));
					set2.setFixdate(make_date);
					set2List.add(set2);
				}
			}
		entity.setSet2List(set2List);

		// 处理展台展柜
		String[] showt_ids = (String[]) request
				.getParameterValues("a_showt_id");
		String[] showt_areas = (String[]) request
				.getParameterValues("a_showt_area");
		String[] showt_types = (String[]) request
				.getParameterValues("a_showt_type");
		String[] showt_miles = (String[]) request
				.getParameterValues("a_showt_mile");
		String[] showt_cashs = (String[]) request
				.getParameterValues("a_showt_cash");
		String[] showt_times = (String[]) request
				.getParameterValues("a_showt_time");
		String[] version_ids = (String[]) request
				.getParameterValues("a_version_id");

		List<KonkaParagonShowt> tShowList = new ArrayList<KonkaParagonShowt>();

		if (showt_ids != null)
			for (int i = 0; i < showt_ids.length; i++) {
				KonkaParagonShowt _tShow = new KonkaParagonShowt();
				_tShow.setAddman(userInfo.getId());
				_tShow.setAddtime(new Date());
				if (StringUtils.isNotBlank(showt_areas[i])) {
					_tShow.setShowt_area(BigDecimal.valueOf(Double
							.parseDouble(showt_areas[i])));
				}
				if (StringUtils.isNotBlank(showt_types[i])) {
					_tShow.setShowt_type(showt_types[i]);
				}
				if (StringUtils.isNotBlank(showt_miles[i])) {
					_tShow.setShowt_mile(BigDecimal.valueOf(Double
							.parseDouble(showt_miles[i])));
				}
				if (StringUtils.isNotBlank(showt_cashs[i])) {
					_tShow.setShowt_cash(BigDecimal.valueOf(Double
							.parseDouble(showt_cashs[i])));
				}
				if (StringUtils.isNotBlank(showt_times[i])) {
					_tShow.setShowt_time(java.sql.Date.valueOf(showt_times[i]));
				}
				if (StringUtils.isNotBlank(version_ids[i])) {
					_tShow.setVersion_id(new Long(version_ids[i]));
				}
				_tShow.setFixdate(make_date);
				_tShow.setShow_shop_code(entity.getShow_shop_code());
				tShowList.add(_tShow);
			}

		entity.setShowtList(tShowList);

		if (StringUtils.isNotBlank(make_date)) {
			if (StringUtils.isNotBlank(sales_val)) {
				// 处理销售额
				KonkaParagonSales sales = new KonkaParagonSales();
				sales.setAddman(userInfo.getId());
				sales.setAddtime(new Date());
				sales.setShow_shop_code(entity.getShow_shop_code());
				sales.setSale_year(make_date.replace("-", ""));
				sales.setSales(BigDecimal
						.valueOf(Double.parseDouble(sales_val)));
				entity.setSales(sales);
			}

			if (StringUtils.isNotBlank(etcash_val)) {
				// 处理进场费
				KonkaParagonEtcash etcash = new KonkaParagonEtcash();
				etcash.setAddman(userInfo.getId());
				etcash.setAddtime(new Date());
				etcash.setShow_shop_code(entity.getShow_shop_code());
				etcash.setEt_year(make_date.replace("-", ""));
				etcash.setEnter_ticket(BigDecimal.valueOf(Double
						.parseDouble(etcash_val)));
				entity.setEtcash(etcash);
			}
		}

		entity.setAddman(userInfo.getId());
		entity.setAddtime(new Date());
		getFacade().getKonkaParagonShowinfoService().saveKonkaParagonShowinfo(
				entity);
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity
				.getQueryString()));
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
			KonkaParagonSales entity = new KonkaParagonSales();
			entity.setSales_id(new Long(id));
			entity = getFacade().getKonkaParagonSalesService()
					.getKonkaParagonSales(entity);
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

	public ActionForward getval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String comid = (String) dynaBean.get("comid");
		if (StringUtils.isNotBlank(comid)) {
			// 查询康佳部门信息
			KonkaDept kd = new KonkaDept();
			kd.setDept_id(Long.valueOf(comid));
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			
			// 查询部门地区信息
			BaseProvinceList bpl = new BaseProvinceList();
			bpl.setP_index(kd.getP_index());
			bpl = super.getFacade().getBaseProvinceListService().getBaseProvinceList(bpl);
			
			String py1 = StringUtils.substring(PinyinTools.cn2FirstSpell(bpl.getP_name()), 0, 2).toUpperCase();
			String py2 = StringUtils.substring(PinyinTools.cn2FirstSpell(kd.getDept_name()), 0, 2).toUpperCase();
			
			KonkaParagonShowinfo kps = new KonkaParagonShowinfo();
			kps.getMap().put("show_shop_code_like", py1 + py2);
			Long count = super.getFacade().getKonkaParagonShowinfoService().getKonkaParagonShowinfoCount(kps) + 1;
			String resutl = "";
			if (count < 10L){
				resutl = py1 + py2 + "00" + count;
			} else if (count < 100L){
				resutl = py1 + py2 + "0" + count;
			} else {
				resutl = py1 + py2 + count;
			}
			if (StringUtils.isNotBlank(resutl))
				super.renderText(response, resutl);
			else {
				super.renderText(response, "");
			}
		}
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String scode = (String) dynaBean.get("scode");
		String fixdate = (String) dynaBean.get("fixdate");

		Date now = new Date();

		SimpleDateFormat _ft = new SimpleDateFormat("yyyyMM");
		if (StringUtils.isEmpty(fixdate)) {
			fixdate = _ft.format(now);
		}

		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);

		dynaBean.set("queryString", super.serialize(request, "id", "method"));

		// 取已关联门店
		KonkaParagonShowinfo entity = new KonkaParagonShowinfo();
		entity.setShow_shop_code(scode);
		entity = super.getFacade().getKonkaParagonShowinfoService()
				.getKonkaParagonShowinfoForView(entity);
		if (entity == null) {
			renderJavaScript(response,
					"window.onload=function(){alert('门店信息不存在，请先维护');history.back();}");
			return null;
		}
		// 取此门店促销员
		KonkaParagonManinfo t = new KonkaParagonManinfo();
		t.setShow_shop_code(scode);
		t.setFixdate(fixdate);
		List<KonkaParagonManinfo> sellerList = super.getFacade()
				.getKonkaParagonManinfoService().getKonkaParagonManinfoList(t);

		// 取此门店展台展柜信息
		KonkaParagonShowt tShow = new KonkaParagonShowt();
		tShow.setShow_shop_code(scode);
		tShow.setFixdate(fixdate);
		List<KonkaParagonShowt> tShowList = super.getFacade()
				.getKonkaParagonShowtService().getKonkaParagonShowtList(tShow);

		// 取此形象版本
		KonkaParagonShowversion version = new KonkaParagonShowversion();
		List<KonkaParagonShowversion> versionList = super.getFacade()
				.getKonkaParagonShowversionService()
				.getKonkaParagonShowversionList(version);
		request.setAttribute("versionList", versionList);

		// 取本年销售额、进场费
		KonkaParagonEtcash etcash = new KonkaParagonEtcash();
		etcash.setShow_shop_code(scode);
		etcash.setEt_year(fixdate);
		List<HashMap<String, String>> cashList = new ArrayList<HashMap<String, String>>();
		cashList = super.getFacade().getKonkaParagonEtcashService()
				.selectKonkaParagonEtcashListByOne(etcash);

		super.copyProperties(form, entity);

		KonkaParagonEquipmentC tc = new KonkaParagonEquipmentC();
		tc.setStatus(1);
		tc.getMap().put("fixdate", fixdate);
		tc.getMap().put("shop_code", scode);
		tc.setEtype(1);// 演示设备
		List<KonkaParagonEquipmentC> setList = super.getFacade()
				.getKonkaParagonEquipmentCService()
				.getKonkaParagonEquipmentList(tc);
		tc.setEtype(2);// 样机
		List<KonkaParagonEquipmentC> machineList = super.getFacade()
				.getKonkaParagonEquipmentCService()
				.getKonkaParagonEquipmentList(tc);
		request.setAttribute("showtList", tShowList);
		request.setAttribute("sellerList", sellerList);
		request.setAttribute("setList", setList);
		request.setAttribute("machineList", machineList);
		request.setAttribute("cashList", cashList);
		request.setAttribute("scode", scode);
		request.setAttribute("makeDate", fixdate);
		return mapping.findForward("view");
	}
}