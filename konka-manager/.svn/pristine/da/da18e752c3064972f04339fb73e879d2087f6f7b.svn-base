package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptTree;
import com.ebiz.mmt.domain.KonkaPlanMoney;
import com.ebiz.mmt.domain.KonkaPlanRatio;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaPlanRatioAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		dynaBean.set("year", String.valueOf(calendar.get(Calendar.YEAR)));

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("fgs_sn", konkaDept.getDept_sn());
				dynaBean.set("dept_type", "3");
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		dynaBean.set("this_year", String.valueOf(calendar.get(Calendar.YEAR)));
		Pager pager = (Pager) dynaBean.get("pager");
		String year = (String) dynaBean.get("year");
		String fgs_sn_search = (String) dynaBean.get("fgs_sn_search");
		String dept_type = (String) dynaBean.get("dept_type");

		KonkaPlanMoney t = new KonkaPlanMoney();
		if (StringUtils.isNotBlank(year)) {
			t.setY(Integer.valueOf(year));
		}

		List<KonkaPlanMoney> list = super.getFacade().getKonkaPlanMoneyService().getKonkaPlanMoneyListForYear(t);
		request.setAttribute("totalList", list);

		KonkaPlanRatio entity = new KonkaPlanRatio();
		if (StringUtils.isNotBlank(year)) {
			entity.setY(Integer.valueOf(year));
		}
		if (StringUtils.isNotBlank(fgs_sn_search)) {
			entity.setFgs_sn(fgs_sn_search);
		}
		if (StringUtils.isNotBlank(dept_type) && "3".equals(dept_type)) {
			PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			if (null != peProdUser) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(peProdUser.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				if (null != konkaDept) {
					entity.setFgs_sn(konkaDept.getDept_sn());
					dynaBean.set("fgs_sn", konkaDept.getDept_sn());
				}
			}

		}
		if (StringUtils.isNotBlank(dept_type) && "1".equals(dept_type)) {
			if (StringUtils.isBlank(fgs_sn_search)) {
				//entity.getMap().put("fgs_dept", "true");
			}
		}

		Long recordCount = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaPlanRatio> entityList = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioPaginatedList(
				entity);
		request.setAttribute("entityList", entityList);

		KonkaDept d = new KonkaDept();
		d.setDept_type(3);
		d.getMap().put("order_by_dept_name", "not_empty");
		d.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(d);
		request.setAttribute("deptList", deptList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		// KonkaPlanMoney entity = new KonkaPlanMoney();
		// // the line below is added for pagination
		// entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
		// // end
		//
		// super.copyProperties(form, entity);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String y = (String) dynaBean.get("y");

		KonkaPlanMoney t = new KonkaPlanMoney();
		if (StringUtils.isNotBlank(id)) {
			t.setId(new Long(id));
		}
		if (StringUtils.isNotBlank(y)) {
			t.setY(Integer.valueOf(y));
		}
		List<KonkaPlanMoney> list = super.getFacade().getKonkaPlanMoneyService().getKonkaPlanMoneyListForYear(t);
		if (null != list && list.size() > 0) {
			KonkaPlanMoney entity = new KonkaPlanMoney();
			entity = list.get(0);
			// the line below is added for pagination
			entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
			// end

			super.copyProperties(form, entity);
			request.setAttribute("konkaPlanMoney", entity);
		} else {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String y = (String) dynaBean.get("y");
		String dept_type = (String) dynaBean.get("dept_type");

		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(new Date());
		// String this_year = String.valueOf(calendar.get(Calendar.YEAR));
		// if (!this_year.equals(y)) {
		// String msg = "系统当前年度是：" + this_year + "年，不能操作其他年度的任务总额记录";
		// super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
		// return null;
		// }

		if (StringUtils.isNotBlank(dept_type) && "3".equals(dept_type)) {
			super.renderJavaScript(response, "window.onload=function(){alert('分公司不能修改任务总额记录');history.back();}");
			return null;
		}

		// String m = (String) dynaBean.get("m");
		String m1 = (String) dynaBean.get("m1");
		String m2 = (String) dynaBean.get("m2");
		String m3 = (String) dynaBean.get("m3");
		String m4 = (String) dynaBean.get("m4");
		String m5 = (String) dynaBean.get("m5");
		String m6 = (String) dynaBean.get("m6");
		String m7 = (String) dynaBean.get("m7");
		String m8 = (String) dynaBean.get("m8");
		String m9 = (String) dynaBean.get("m9");
		String m10 = (String) dynaBean.get("m10");
		String m11 = (String) dynaBean.get("m11");
		String m12 = (String) dynaBean.get("m12");

		KonkaPlanMoney entity = new KonkaPlanMoney();
		super.copyProperties(entity, form);
		entity.setP(0);
		List<KonkaPlanMoney> list = new ArrayList<KonkaPlanMoney>();

		if (StringUtils.isNotBlank(m1)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m1));
			k.setY(Integer.valueOf(y + "01"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m2)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m2));
			k.setY(Integer.valueOf(y + "02"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m3)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m3));
			k.setY(Integer.valueOf(y + "03"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m4)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m4));
			k.setY(Integer.valueOf(y + "04"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m5)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m5));
			k.setY(Integer.valueOf(y + "05"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m6)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m6));
			k.setY(Integer.valueOf(y + "06"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m7)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m7));
			k.setY(Integer.valueOf(y + "07"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m8)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m8));
			k.setY(Integer.valueOf(y + "08"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m9)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m9));
			k.setY(Integer.valueOf(y + "09"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m10)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m10));
			k.setY(Integer.valueOf(y + "10"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m11)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m11));
			k.setY(Integer.valueOf(y + "11"));
			list.add(k);
		}
		if (StringUtils.isNotBlank(m12)) {
			KonkaPlanMoney k = new KonkaPlanMoney();
			k.setP(1);
			k.setM(new BigDecimal(m12));
			k.setY(Integer.valueOf(y + "12"));
			list.add(k);
		}

		if (StringUtils.isBlank(id)) {
			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}

			KonkaPlanMoney t = new KonkaPlanMoney();
			t.setY(Integer.valueOf(y));
			Long count = super.getFacade().getKonkaPlanMoneyService().getKonkaPlanMoneyCount(t);
			if (count > 0) {
				String msg = y + "年度任务总额记录已存在";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			super.getFacade().getKonkaPlanMoneyService().createKonkaPlanMoneyForTotal(entity, list);
			saveMessage(request, "entity.inserted");
		} else {
			// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPlanMoneyService().modifyKonkaPlanMoneyForTotal(entity, list);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&dept_type=" + dept_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward add_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		KonkaDept d = new KonkaDept();
		d.setDept_type(3);
		d.getMap().put("order_by_dept_name", "not_empty");
		d.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(d);
		request.setAttribute("deptList", deptList);

		// KonkaPlanRatio entity = new KonkaPlanRatio();
		// // the line below is added for pagination
		// entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
		// // end
		//
		// super.copyProperties(form, entity);

		return new ActionForward("/admin/KonkaPlanRatio/form_jb.jsp");
	}

	public ActionForward save_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String y = (String) dynaBean.get("y");
		String fgs_sn = (String) dynaBean.get("fgs_sn");
		String fgs_sn_search = (String) dynaBean.get("fgs_sn_search");
		String dept_sn_1 = (String) dynaBean.get("dept_sn"); // 一级部门
		String dept_sn_2 = (String) dynaBean.get("dept_sn_2"); //二级部门
		String dept_sn_3 = (String) dynaBean.get("dept_sn_3"); //三级部门
		String dept_sn_4 = (String) dynaBean.get("dept_sn_4"); //四级部门
		String dept_type = (String) dynaBean.get("dept_type");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String this_year = String.valueOf(calendar.get(Calendar.YEAR));
		if (!this_year.equals(y)) {
			String msg = "系统当前年度是：" + this_year + "年，不能操作其他年度的任务总额记录！";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 从第四级部门开始判断不为空则选未dept_sn
		String dept_sn = null;
		if(StringUtils.isNotBlank(dept_sn_4)){
			dept_sn = dept_sn_4;
		}else if(StringUtils.isNotBlank(dept_sn_3)){
			dept_sn = dept_sn_3;
		}else if(StringUtils.isNotBlank(dept_sn_2)){
			dept_sn = dept_sn_2;
		}else if(StringUtils.isNotBlank(dept_sn_1)){
			dept_sn = dept_sn_1;
		}
		KonkaPlanRatio entity = new KonkaPlanRatio();
		super.copyProperties(entity, form);
		entity.setDept_sn(dept_sn);

		if (StringUtils.isBlank(id)) {
			if (StringUtils.isBlank(fgs_sn)) {
				String msg = "分公司编码为空，请重新选择！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (StringUtils.isBlank(dept_sn)) {
				// 判断分公司的任务系数是否已存在
				KonkaPlanRatio kpr = new KonkaPlanRatio();
				kpr.getMap().put("fgs_dept", "true");
				kpr.setFgs_sn(fgs_sn);
				kpr.setY(Integer.valueOf(y));
				Long recordCount = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioCount(kpr);
				if (recordCount > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('" + y
							+ "年度，该分公司的任务系数已存在！');history.back();}");
					return null;
				}

				KonkaDept k = new KonkaDept();
				k.setDept_sn(fgs_sn);
				List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
				if (null != list && list.size() > 0) {
					k = list.get(0);
					entity.setDept_name(k.getDept_name());
				}
				entity.setDept_sn(fgs_sn);
			} else {
				// 判断经营部的任务系数是否已存在
				KonkaPlanRatio kpr = new KonkaPlanRatio();
				kpr.setDept_sn(dept_sn);
				kpr.setY(Integer.valueOf(y));
				Long recordCount = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioCount(kpr);
				if (recordCount > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('" + y
							+ "年度，该经办的任务系数已存在！');history.back();}");
					return null;
				}
				
				KonkaDept k = new KonkaDept();
				k.setDept_sn(dept_sn);
				List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
				if (null != list && list.size() > 0) {
					k = list.get(0);
					String dept_name = k.getDept_name();
					entity.setDept_name(dept_name);
				}
			}

			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPlanRatioService().createKonkaPlanRatio(entity);
			saveMessage(request, "entity.inserted");
		} else {
			// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPlanRatioService().modifyKonkaPlanRatio(entity);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/KonkaPlanRatio.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&dept_type=" + dept_type);
		pathBuffer.append("&year=" + y);
		pathBuffer.append("&fgs_sn_search=" + fgs_sn_search);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward list_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		dynaBean.set("this_year", String.valueOf(calendar.get(Calendar.YEAR)));

		String y = (String) dynaBean.get("y");
		String fgs_sn = (String) dynaBean.get("fgs_sn");
		KonkaPlanRatio k = new KonkaPlanRatio();
		if (StringUtils.isNotBlank(y)) {
			k.setY(Integer.valueOf(y));
		}
		if (StringUtils.isNotBlank(fgs_sn)) {
			k.setFgs_sn(fgs_sn);
		}
		List<KonkaPlanRatio> entityList = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioList(k);
		request.setAttribute("entityList", entityList);
		return new ActionForward("/admin/KonkaPlanRatio/list_jb.jsp");
	}

	public ActionForward edit_jb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String y = (String) dynaBean.get("y");

		KonkaPlanRatio t = new KonkaPlanRatio();
		if (StringUtils.isNotBlank(id)) {
			t.setId(new Long(id));
		}
		if (StringUtils.isNotBlank(y)) {
			t.setY(Integer.valueOf(y));
		}
		List<KonkaPlanRatio> list = super.getFacade().getKonkaPlanRatioService().getKonkaPlanRatioList(t);
		if (null != list && list.size() > 0) {
			KonkaPlanRatio entity = new KonkaPlanRatio();
			entity = list.get(0);
			
			// the line below is added for pagination
			entity.setQueryString(super.serialize(request, "mod_id", "year", "fgs_sn"));
			// end

			// 判断dept_sn与fgs_sn是否相等，不等则有下级部门
			if(!entity.getFgs_sn().equals(entity.getDept_sn())){
				int level = 1;
				// 查询树的级别
				KonkaDeptTree deptTree = new KonkaDeptTree();
				deptTree.setDept_sn(entity.getDept_sn());
				deptTree = super.getFacade().getKonkaDeptTreeService().getKonkaDeptTree(deptTree);
				if(deptTree != null){
					level = deptTree.getTree_level();
				}
				//一级部门/二级部门...
				List<String> dept_sn_list = new ArrayList<String>();
				String cur_sn = entity.getDept_sn();
				Long cur_dept_id = null;
				for(int i=0; i<level-1; i++){ //树的级别减1  因为分公司的不需要查
					KonkaDept konkaDept = new KonkaDept();
					konkaDept.setDept_sn(cur_sn);
					konkaDept.setDept_id(cur_dept_id);
					// 通过dept_sn查询到dept_id
					List<KonkaDept> konkaDeptList2 = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
					if(konkaDeptList2!=null && konkaDeptList2.size()>0){
						dept_sn_list.add(konkaDeptList2.get(0).getDept_sn());
						cur_sn = null;
						cur_dept_id = konkaDeptList2.get(0).getPar_id();
					}else{
						break;
					}
				}
				// 一级部门
				if(dept_sn_list.size()>0){
					String dept_sn = dept_sn_list.get(dept_sn_list.size()-1);
					entity.getMap().put("dept_sn", dept_sn);
				}
				String dept_sn_2 = "";
				String dept_sn_3 = "";
				String dept_sn_4 = "";
				// 二级部门
				if(dept_sn_list.size()>=2){
					dept_sn_2 = dept_sn_list.get(dept_sn_list.size()-2);
					entity.getMap().put("dept_sn_2", dept_sn_2);
				}
				// 三级部门
				if(dept_sn_list.size()>=3){
					dept_sn_3 = dept_sn_list.get(dept_sn_list.size()-3);
					entity.getMap().put("dept_sn_3", dept_sn_3);
				}
				// 四级部门
				if(dept_sn_list.size()>=4){
					dept_sn_4 = dept_sn_list.get(dept_sn_list.size()-4);
					entity.getMap().put("dept_sn_4", dept_sn_4);
				}
			}
			super.copyProperties(form, entity);
			request.setAttribute("konkaPlanRatio", entity);
		} else {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}

		KonkaDept d = new KonkaDept();
		d.setDept_type(3);
		d.getMap().put("order_by_dept_name", "not_empty");
		d.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(d);
		request.setAttribute("deptList", deptList);

		return new ActionForward("/admin/KonkaPlanRatio/form_jb.jsp");
	}

	public ActionForward add_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		return new ActionForward("/admin/KonkaPlanRatio/form_excel.jsp");
	}

	public ActionForward save_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_type = (String) dynaBean.get("dept_type");

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		if (uploadFilesList.size() == 1) {
			String file_save_path = uploadFilesList.get(0).getFileSavePath();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

			// 附件保存路径更改遗留问题
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "";
				file_save_path = "/Attachment_new/konka-files/" + file_save_path;
			}

			try {
				Workbook workbook = Workbook.getWorkbook(new File(ctxDir + file_save_path));
				Sheet sheet = workbook.getSheet(0);
				int rows = sheet.getRows();
				if (rows <= 1) {
					super.renderJavaScript(response, "window.onload=function(){alert('Excel文件内容为空！');history.back();}");
					return null;
				}
				int start_row = 1;
				int end_row = rows;
				List<KonkaPlanRatio> entityList = new ArrayList<KonkaPlanRatio>();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				String this_year = String.valueOf(calendar.get(Calendar.YEAR));
				for (int i = start_row; i < end_row; i++) {
					KonkaPlanRatio entity = new KonkaPlanRatio();
					if (StringUtils.isNotBlank(sheet.getCell(0, i).getContents())) {
						// (0 列，i行) --- 年度
						if (!this_year.equals(sheet.getCell(0, i).getContents())) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，年度不是"
									+ this_year + "年！');history.back();}");
							return null;
						}
						entity.setY(Integer.valueOf(sheet.getCell(0, i).getContents()));
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，年度为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(1, i).getContents())) {
						// (1列，i行) --- 分公司编码
						if (sheet.getCell(1, i).getContents().length() > 20) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，分公司编码长度大于20！');history.back();}");
							return null;
						}
						KonkaDept k = new KonkaDept();
						k.setDept_sn(sheet.getCell(1, i).getContents());
						k.setDept_type(3);
						List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
						if (null == list || list.size() == 0) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，分公司编码在系统中不存在！');history.back();}");
							return null;
						}
						if (StringUtils.isNotBlank(dept_type) && "3".equals(dept_type)) {
							PeProdUser peProdUser = (PeProdUser) super
									.getSessionAttribute(request, Constants.USER_INFO);
							if (null != peProdUser) {
								KonkaDept konkaDept = new KonkaDept();
								konkaDept.setDept_id(peProdUser.getDept_id());
								konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
								if (null != konkaDept) {
									if (!konkaDept.getDept_sn().equals(sheet.getCell(1, i).getContents())) {
										super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
												+ "行，本分公司不能添加其他分公司的任务系数！');history.back();}");
										return null;
									}
								}
							}

						}
						entity.setFgs_sn(sheet.getCell(1, i).getContents());
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，分公司编码为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(2, i).getContents())) {
						// (2列，i行) --- 经办编码
						if (sheet.getCell(2, i).getContents().length() > 20) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，经办编码长度大于20！');history.back();}");
							return null;
						}
						KonkaDept k = new KonkaDept();
						k.setDept_sn(sheet.getCell(2, i).getContents());
						List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
						if (null == list || list.size() == 0) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，经办编码在系统中不存在！');history.back();}");
							return null;
						}
						if (StringUtils.isNotBlank(dept_type) && "3".equals(dept_type)) {
							if (entity.getFgs_sn().equals(sheet.getCell(2, i).getContents())) {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，本分公司不能添加自己分公司的任务系数，只能添加下级经营部的任务系数！');history.back();}");
								return null;
							}
						}
						if (!entity.getFgs_sn().equals(sheet.getCell(2, i).getContents())) {
							k = list.get(0);
							KonkaDept t = new KonkaDept();
							t.setDept_id(k.getPar_id());
							t.setDept_sn(entity.getFgs_sn());
							t.setDept_type(3);
							KonkaDept d = super.getFacade().getKonkaDeptService().getKonkaDept(t);
							if (null == d) {
								super
										.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
												+ "行，经办编码在系统中对于的分公司编码不是该行的分公司编码：" + entity.getFgs_sn()
												+ "！');history.back();}");
								return null;
							}
						}
						entity.setDept_sn(sheet.getCell(2, i).getContents());
						// entity.setDept_name(t.getDept_name());
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，经办编码为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(3, i).getContents())) {
						// (3列，i行) --- 经办名称
						if (sheet.getCell(3, i).getContents().length() > 20) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，经办名称长度大于20！');history.back();}");
							return null;
						}
						entity.setDept_name(sheet.getCell(3, i).getContents());
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，经办名称为空！');history.back();}");
						return null;
					}
					if (StringUtils.isNotBlank(sheet.getCell(4, i).getContents())) {
						// (4列，i行) --- 任务系数
						if (sheet.getCell(4, i).getContents().length() > 10) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，任务系数长度大于10！');history.back();}");
							return null;
						}
						// 判断数值类型
						GenericValidator.isDouble("");
						Pattern p = Pattern.compile("\\d+(\\.\\d+)?");
						Matcher m = p.matcher(sheet.getCell(4, i).getContents());
						boolean b = m.matches();
						if (b) {
							NumberCell numberCell = (NumberCell)sheet.getCell(4, i);
							double namberValue = numberCell.getValue();
							entity.setRatio(new BigDecimal(namberValue));
						} else {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，任务系数数字格式不正确！');history.back();}");
							return null;
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，任务系数为空！');history.back();}");
						return null;
					}
					entityList.add(entity);
				}

				// 插入数据库
				if (null != entityList && entityList.size() > 0) {
					for (int i = 0; i < entityList.size(); i++) {
						KonkaPlanRatio k = entityList.get(i);
						log.info("****************第" + (i + 2) + "行数据：年度：" + k.getY() + ",分公司编码：" + k.getFgs_sn()
								+ ",经办编码：" + k.getDept_sn() + ",经办名称：" + k.getDept_name() + ",任务系数：" + k.getRatio());
					}
				}

				super.getFacade().getKonkaPlanRatioService().createKonkaPlanRatioForExcel(entityList);
				saveMessage(request, "entity.inserted");
				// the line below is added for pagination
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&mod_id=" + mod_id);
				pathBuffer.append("&dept_type=" + dept_type);
				pathBuffer.append("&year=" + this_year);
				// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;

			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('Excel文件未选择！');history.back();}");
			return null;
		}
	}

	public ActionForward getKonkaDeptForFgsSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String fgs_sn = (String) dynaBean.get("fgs_sn");
		KonkaDept kd = new KonkaDept();
		kd.setDept_sn(fgs_sn);
		kd.setDept_type(3);
		List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);

		String par_id = "";
		if (null != list && list.size() > 0) {
			par_id = String.valueOf(list.get(0).getDept_id());
		}

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(par_id)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		KonkaDept dept = new KonkaDept();
		if (null != par_id) {
			dept.setPar_id(new Long(par_id));
		}
		dept.getMap().put("order_by_dept_name", "no_empty");
		dept.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);

		if (null != entityList && entityList.size() > 0) {
			for (KonkaDept entity : entityList) {
				sb = sb.append("{");
				sb = sb.append("\"dept_sn\":\"").append(entity.getDept_sn()).append("\",");
				sb = sb.append("\"dept_name\":\"").append(entity.getDept_name()).append("\"");
				sb = sb.append("},");
			}
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}
	/**
	 * 通过上级Sn和Dept_type获取下级部门
	 */
	public ActionForward getKonkaDeptForUpperSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String upper_sn = (String) dynaBean.get("upper_sn");
		List<KonkaDept> list = null;
		if(StringUtils.isNotBlank(upper_sn)){
			KonkaDept kd = new KonkaDept();
			kd.setDept_sn(upper_sn);
			list = super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
		}
		String par_id = "";
		if (null != list && list.size() > 0) {
			par_id = String.valueOf(list.get(0).getDept_id());
		}

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(par_id)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		KonkaDept dept = new KonkaDept();
		if (null != par_id) {
			dept.setPar_id(new Long(par_id));
		}
		dept.getMap().put("order_by_dept_name", "no_empty");
		dept.getMap().put("dept_sn_not_null", "not_empty");
		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);

		if (null != entityList && entityList.size() > 0) {
			for (KonkaDept entity : entityList) {
				sb = sb.append("{");
				sb = sb.append("\"dept_sn\":\"").append(entity.getDept_sn()).append("\",");
				sb = sb.append("\"dept_name\":\"").append(entity.getDept_name()).append("\"");
				sb = sb.append("},");
			}
		}

		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	public ActionForward CheckKonkaDeptForDeptSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_sn = (String) dynaBean.get("dept_sn");
		KonkaDept k = new KonkaDept();
		k.setDept_sn(dept_sn);
		List<KonkaDept> list = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
		if (null != list && list.size() > 0) {
			super.render(response, "1", "text/x-json;charset=UTF-8");
		} else {
			super.render(response, "0", "text/x-json;charset=UTF-8");
		}
		return null;
	}
}