package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.Konkamobilesaledatas;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaMobileSaleDatasAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("search_first_flag", "0");
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

		Konkamobilesaledatas entity = new Konkamobilesaledatas();
		super.copyProperties(entity, form);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String search_first_flag = (String) dynaBean.get("search_first_flag");// 查询标识

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		//String office_id = (String) dynaBean.get("office_id");// 办事处
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");//二级部门
		String l5_dept_id= (String) dynaBean.get("l5_dept_id");//三级部门
		
		
		
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户
		String report_name_like = (String) dynaBean.get("report_name_like");// 上报人
		String dept_name_like = (String) dynaBean.get("dept_name_like");// 上报门店
		String measure_id = (String) dynaBean.get("measure_id");// 尺寸
		String model_id = (String) dynaBean.get("model_id");// 型号
		String date_begin = (String) dynaBean.get("date_begin");// 起始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
		String dept = "";
		if (peProdUser != null) {
			KonkaMobileCategory _t = new KonkaMobileCategory();
			_t.setC_index(peProdUser.getDept_id());
			dept = super.getFacade().getKonkaMobileCategoryService()
					.getKonkaMobileDept(_t);
		}
		if (null != dept){ // 分公司用户
			entity.setDept_id(Long.parseLong(dept));
		}else{
			// 总部用户
			request.setAttribute("isFgsUser", "true");
		}
		
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			sizeList.add(t);
		}
		request.setAttribute("sizeList", sizeList);

		// 分公司,下拉选项
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		if (null != dept) {// 分公司用户
			konkaDept.setDept_id(peProdUser.getDept_id());// ++2013-04-18
		}
		List<KonkaDept> baseDeptList = super.getFacade()
				.getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("baseDeptList", baseDeptList);

		// 所属客户
		KonkaCategory konkaCategory = new KonkaCategory();
		List<KonkaCategory> konkaCategoryList = super.getFacade()
				.getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);
		
		if(StringUtils.isNotBlank(search_first_flag) && "1".equals(search_first_flag)){
			// 查询条件
			if (StringUtils.isNotBlank(dept_id)) {
				entity.setDept_id(Long.valueOf(dept_id));
				dynaBean.set("dept_id", dept_id);
			}
			if (StringUtils.isNotBlank(l4_dept_id)) {
				entity.setL4_dept_id(Long.valueOf(l4_dept_id));
				dynaBean.set("l4_dept_id", l4_dept_id);
			}
			if (StringUtils.isNotBlank(l5_dept_id)) {
				entity.setL5_dept_id(Long.valueOf(l5_dept_id));
				dynaBean.set("l5_dept_id", l5_dept_id);
			}
			if (StringUtils.isNotBlank(customer_name_like)) {
				entity.getMap().put("customer_name_like", customer_name_like);
			}
			if (StringUtils.isNotBlank(report_name_like)) {
				entity.getMap().put("report_name_like", report_name_like);
			}
			if (StringUtils.isNotBlank(dept_name_like)) {
				entity.getMap().put("dept_name_like", dept_name_like);
			}
			if (StringUtils.isNotBlank(measure_id)) {
				entity.getMap().put("measure_id", measure_id);
			}
			if (StringUtils.isNotBlank(model_id)) {
				entity.setModel_id(Long.valueOf(model_id));
			}
			if (StringUtils.isNotBlank(date_begin)) {
				entity.getMap().put("date_begin", date_begin);
			}
			if (StringUtils.isNotBlank(date_end)) {
				entity.getMap().put("date_end", date_end);
			}

			// 处理时间条件
			String timetype = (String) dynaBean.get("timetype");
			if (StringUtils.isEmpty(timetype)) {
				timetype = "1";
				dynaBean.set("timetype", "1");
			}

			// 处理聚类选项
			String selected01 = (String) dynaBean.get("selected01");
			if (StringUtils.isNotEmpty(selected01))
				entity.getMap().put("selected01", selected01);
			String selected02 = (String) dynaBean.get("selected02");
			if (StringUtils.isNotEmpty(selected02))
				entity.getMap().put("selected02", selected02);
			String selected03 = (String) dynaBean.get("selected03");
			if (StringUtils.isNotEmpty(selected03))
				entity.getMap().put("selected03", selected03);
			String selected04 = (String) dynaBean.get("selected04");
			if (StringUtils.isNotEmpty(selected04))
				entity.getMap().put("selected04", selected04);
			String selected05 = (String) dynaBean.get("selected05");
			if (StringUtils.isNotEmpty(selected05))
				entity.getMap().put("selected05", selected05);
			String selected06 = (String) dynaBean.get("selected06");
			if (StringUtils.isNotEmpty(selected06))
				entity.getMap().put("selected06", selected06);
			String selected07 = (String) dynaBean.get("selected07");
			if (StringUtils.isNotEmpty(selected07))
				entity.getMap().put("selected07", selected07);
			
			if (StringUtils.isNotBlank(timetype)) {
				entity.getMap().put("timetype", timetype);

				Long recordCount = 0l;
				if (StringUtils.isNotBlank(timetype)) {
					switch (Integer.parseInt(timetype)) {
					case 1:
						recordCount = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasCountMonth(entity);
						break;
					case 2:
						recordCount = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasCountDay(entity);
						break;
					case 3:
						recordCount = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasCountSeason(entity);
						break;
					case 4:
						recordCount = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasCountYear(entity);
						break;
					default:
						recordCount = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasCountMonth(entity);
						break;
					}
				}

				pager.init(recordCount, 100, pager.getRequestPage());
				entity.getRow().setFirst(pager.getFirstRow());
				entity.getRow().setCount(pager.getRowCount());

				List<HashMap<String, String>> entityList = new ArrayList<HashMap<String, String>>();
				if (StringUtils.isNotBlank(timetype)) {
					switch (Integer.parseInt(timetype)) {
					case 1:
						entityList = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasPaginatedListMonth(entity);
						break;
					case 2:
						entityList = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasPaginatedListDay(entity);
						break;
					case 3:
						entityList = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasPaginatedListSeason(entity);
						break;
					case 4:
						entityList = getFacade().getKonkamobilesaledatasService()
								.gettKonkamobilesaledatasPaginatedListYear(entity);
						break;
					default:
						entityList = getFacade().getKonkamobilesaledatasService()
								.getKonkamobilesaledatasPaginatedListMonth(entity);
						break;
					}
				}

				request.setAttribute("entityList", entityList);
			}
		}else{
			pager.init(0L, 100, pager.getRequestPage());
		}
		
		super.copyProperties(form, entity);
		return mapping.findForward("list");
	}
}
