package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.FighterInfo;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaProSq;
import com.ebiz.mmt.domain.KonkaXxAuditNote;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaProSqAction extends BaseAction {
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
		Pager pager = (Pager) dynaBean.get("pager");
		String file_no_like = (String) dynaBean.get("file_no_like");
		String pro_name_like = (String) dynaBean.get("pro_name_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String excel_all = (String) dynaBean.get("excel_all");
		String pro_state = (String) dynaBean.get("pro_state");
		String area_id = (String) dynaBean.get("area_id");
		String dept_id = (String) dynaBean.get("dept_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaProSq entity = new KonkaProSq();
		if (StringUtils.isNotBlank(file_no_like)) {
			entity.getMap().put("file_no_like", file_no_like);
		}
		if (StringUtils.isNotBlank(pro_name_like)) {
			entity.getMap().put("pro_name_like", pro_name_like);
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(pro_state)) {
			entity.setPro_state(Integer.valueOf(pro_state));
		}
		if (StringUtils.isNotBlank(area_id)) {
			entity.setArea_id(Integer.valueOf(area_id));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		entity.setAdd_user_id(userInfo.getId());// 只能看到本人添加的
		entity.setIs_del(0);

		Long recordCount = getFacade().getKonkaProSqService().getKonkaProSqCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaProSq> entityList = getFacade().getKonkaProSqService().getKonkaProSqPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			if (recordCount.intValue() > 5000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaProSq> entityList1 = getFacade().getKonkaProSqService().getKonkaProSqPaginatedList(entity);
			dynaBean.set("excel_all", excel_all);
			request.setAttribute("allList", entityList1);
		}

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		// 分公司取得
		if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
			dynaBean.set("fgs_dept_name", fgs_dept.getDept_name());
		}
		// 当前年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String ym = sdf.format(date);
		dynaBean.set("yymm", ym.substring(2, ym.length()));

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		KonkaXxBaseType kbt = new KonkaXxBaseType();
		kbt.setPar_id(140000L);
		List<KonkaXxBaseType> konkaXxBaseTypeList = super.getFacade().getKonkaXxBaseTypeService()
		        .getKonkaXxBaseTypeList(kbt);
		request.setAttribute("konkaXxBaseTypeList", konkaXxBaseTypeList);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
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
		String id = (String) dynaBean.get("id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// 修改的时候 竞争对手信息
		String[] brand_name = request.getParameterValues("brand_name");
		String[] md_name = request.getParameterValues("md_name");
		String[] param = request.getParameterValues("param");
		String[] sail_money = request.getParameterValues("sail_money");
		String[] bd_tb_price = request.getParameterValues("bd_tb_price");
		String[] f_remark = request.getParameterValues("f_remark");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaProSq entity = new KonkaProSq();
		super.copyProperties(entity, form);

		if (StringUtils.isBlank(id)) {
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(ui.getId());
			entity.setAdd_user_name(ui.getUser_name());
			String file_no_lm = dynaBean.get("file_no_left") + "" + dynaBean.get("file_no_middle");
			entity.setFile_no(getFilesMaxNo(file_no_lm));
			entity.setFile_state(1);// 1-提交
			entity.setIs_del(0);
			entity.setPro_state(0);
			
			
			// 初始化资质申请历史记录
			List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList = new ArrayList<KonkaXxZmdAuditUserHis>();
			KonkaXxAuditNote konkaXxAuditNote = new KonkaXxAuditNote();
			konkaXxAuditNote.setAudit_type(30L);// 备案审核
			konkaXxAuditNote.setAudit_node_id(0L);
			List<KonkaXxAuditNote> konkaXxAuditNoteList = super.getFacade().getKonkaXxAuditNoteService()
					.getKonkaXxAuditNoteList(konkaXxAuditNote);
			if (konkaXxAuditNoteList.size() > 0) {
				KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis = new KonkaXxZmdAuditUserHis();
				konkaXxZmdAuditUserHis.setIs_end(0);
				konkaXxZmdAuditUserHis.setAudit_node_name("申请人");
				konkaXxZmdAuditUserHis.setAudit_node_id(0L);
				konkaXxZmdAuditUserHis.setAudit_next_node_id(8001L);
				konkaXxZmdAuditUserHis.setAudit_next_node_name("工程部经理");
				konkaXxZmdAuditUserHis.setAudit_status(24100L);
				konkaXxZmdAuditUserHis.setAudit_desc("备案申请");
				konkaXxZmdAuditUserHis.setAudit_type(24000L);
				konkaXxZmdAuditUserHis.setAudit_status_desc("提交备案申请");
				konkaXxZmdAuditUserHis.setAudit_user_id(peProdUser.getId());
				konkaXxZmdAuditUserHis.setAudit_user_name("--");
				konkaXxZmdAuditUserHis.setAudit_date(new Date());
				konkaXxZmdAuditUserHisList.add(konkaXxZmdAuditUserHis);
			}
			entity.setKonkaXxZmdAuditUserHisList(konkaXxZmdAuditUserHisList);
			
			super.getFacade().getKonkaProSqService().createKonkaProSq(entity);
			saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(id));
			//驳回重新提交
			if(entity.getFile_state()==5){
				
			// 初始化资质申请历史记录
			List<KonkaXxZmdAuditUserHis> konkaXxZmdAuditUserHisList = new ArrayList<KonkaXxZmdAuditUserHis>();
			KonkaXxAuditNote konkaXxAuditNote = new KonkaXxAuditNote();
			konkaXxAuditNote.setAudit_type(30L);// 备案审核
			konkaXxAuditNote.setAudit_node_id(0L);
			List<KonkaXxAuditNote> konkaXxAuditNoteList = super.getFacade().getKonkaXxAuditNoteService()
					.getKonkaXxAuditNoteList(konkaXxAuditNote);
			if (konkaXxAuditNoteList.size() > 0) {
				KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis = new KonkaXxZmdAuditUserHis();
				konkaXxZmdAuditUserHis.setIs_end(0);
				konkaXxZmdAuditUserHis.setAudit_node_name("申请人");
				konkaXxZmdAuditUserHis.setAudit_node_id(0L);
				konkaXxZmdAuditUserHis.setAudit_next_node_id(8001L);
				konkaXxZmdAuditUserHis.setAudit_next_node_name("工程部经理");
				konkaXxZmdAuditUserHis.setAudit_status(24100L);
				konkaXxZmdAuditUserHis.setAudit_desc("备案申请");
				konkaXxZmdAuditUserHis.setAudit_type(24000L);
				konkaXxZmdAuditUserHis.setAudit_status_desc("提交备案申请");
				konkaXxZmdAuditUserHis.setAudit_user_id(peProdUser.getId());
				konkaXxZmdAuditUserHis.setAudit_user_name("--");
				konkaXxZmdAuditUserHis.setAudit_date(new Date());
				konkaXxZmdAuditUserHisList.add(konkaXxZmdAuditUserHis);
			}
			entity.setKonkaXxZmdAuditUserHisList(konkaXxZmdAuditUserHisList);
			}
			
			
			List<FighterInfo> fighterInfoList = new ArrayList<FighterInfo>();
			if (null != brand_name) {
				for (int i = 0; i < brand_name.length; i++) {
					FighterInfo fighterInfo = new FighterInfo();
					if (StringUtils.isNotBlank(brand_name[i])) {
						fighterInfo.setBrand_name(brand_name[i]);
					} else {
						break;
					}
					if (StringUtils.isNotBlank(md_name[i])) {
						fighterInfo.setMd_name(md_name[i]);
					}
					if (StringUtils.isNotBlank(param[i])) {
						fighterInfo.setParam(param[i]);
					}
					if (null != sail_money[i] && StringUtils.isNotBlank(sail_money[i])
					        && GenericValidator.isDouble(sail_money[i])) {
						fighterInfo.setSail_money(new BigDecimal(sail_money[i]));
					}
					if (null != bd_tb_price[i] && StringUtils.isNotBlank(bd_tb_price[i])
					        && GenericValidator.isDouble(bd_tb_price[i])) {
						fighterInfo.setBd_tb_price(new BigDecimal(bd_tb_price[i]));
					}
					if (StringUtils.isNotBlank(f_remark[i])) {
						fighterInfo.setF_remark(f_remark[i]);
					}
					fighterInfoList.add(fighterInfo);
				}
				entity.setFighterInfoList(fighterInfoList);
			}
			
			getFacade().getKonkaProSqService().modifyKonkaProSq(entity);
			saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaProSq entity = new KonkaProSq();
			entity.setId(new Long(id));
			entity.setIs_del(1);
			getFacade().getKonkaProSqService().modifyKonkaProSq(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaProSq entity = new KonkaProSq();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(new Long(xx));
				entity.setIs_del(1);
				getFacade().getKonkaProSqService().modifyKonkaProSq(entity);
			}
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		saveToken(request);
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaProSq entity = new KonkaProSq();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaProSqService().getKonkaProSq(entity);
		if (null != entity.getArea_id()) {
			if (entity.getArea_id().intValue() == 10) {
				dynaBean.set("area_name", "华东");
			} else if (entity.getArea_id().intValue() == 20) {
				dynaBean.set("area_name", "山东");
			} else if (entity.getArea_id().intValue() == 30) {
				dynaBean.set("area_name", "东北");
			} else if (entity.getArea_id().intValue() == 40) {
				dynaBean.set("area_name", "华北");
			}else if (entity.getArea_id().intValue() == 50) {
				dynaBean.set("area_name", "华南");
			} else if (entity.getArea_id().intValue() == 60) {
				dynaBean.set("area_name", "西南");
			} else if (entity.getArea_id().intValue() == 70) {
				dynaBean.set("area_name", "华中");
			} else if (entity.getArea_id().intValue() == 80) {
				dynaBean.set("area_name", "西北");
			}
		}
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		FighterInfo ft = new FighterInfo();
		ft.setPro_id(entity.getId());
		List<FighterInfo> fighterInfoList = super.getFacade().getFighterInfoService().getFighterInfoList(ft);

		request.setAttribute("fighterInfoList", fighterInfoList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		KonkaXxBaseType kbt = new KonkaXxBaseType();
		kbt.setPar_id(140000L);
		List<KonkaXxBaseType> konkaXxBaseTypeList = super.getFacade().getKonkaXxBaseTypeService()
		        .getKonkaXxBaseTypeList(kbt);
		request.setAttribute("konkaXxBaseTypeList", konkaXxBaseTypeList);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaProSq entity = new KonkaProSq();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaProSqService().getKonkaProSq(entity);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		request.setAttribute("dept_name", konkaDept.getDept_name());

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		FighterInfo ft = new FighterInfo();
		ft.setPro_id(entity.getId());
		List<FighterInfo> fighterInfoList = super.getFacade().getFighterInfoService().getFighterInfoList(ft);

		request.setAttribute("fighterInfoList", fighterInfoList);

		return mapping.findForward("view");

	}

	/**
	 * @param request
	 *            自动生成登录用户所在部门的提交文件编号
	 * @author Cheng,Bing
	 */
	public String getFilesMaxNo(String file_no_lm) {

		Long max_fileno = null;

		KonkaProSq kd = new KonkaProSq();
		kd.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_1 = super.getFacade().getKonkaProSqService().getKonkaProSqNoMax(kd);
		max_fileno_1 = max_fileno_1 == null ? 0 : max_fileno_1;

		KonkaProSq kf = new KonkaProSq();
		kf.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_2 = super.getFacade().getKonkaProSqService().getKonkaProSqNoMax(kf);
		max_fileno_2 = max_fileno_2 == null ? 0 : max_fileno_2;

		max_fileno = max_fileno_1 >= max_fileno_2 ? max_fileno_1 : max_fileno_2;
		max_fileno = max_fileno + 1;
		String file_no_r = "";
		if (max_fileno < 1000) {
			file_no_r = "0000".substring(0, 4 - ("" + max_fileno).length()) + max_fileno;
		} else {
			file_no_r = "" + max_fileno;
		}

		return file_no_lm + file_no_r;
	}

	public ActionForward chooseArea(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String area_id = "";
		if (StringUtils.isNotBlank(dept_id)) {
			KonkaDept entity = new KonkaDept();
			entity.setDept_id(Long.valueOf(dept_id));
			entity = super.getFacade().getKonkaDeptService().getKonkaDept(entity);
			if (null != entity.getP_area()) {
				area_id = entity.getP_area().toString();// 表示该分公司有大区
			} else {
				area_id = "";// 表示该分公司没有大区，可以选择大区
			}
		}

		super.renderJson(response, area_id);
		return null;
	}

}
