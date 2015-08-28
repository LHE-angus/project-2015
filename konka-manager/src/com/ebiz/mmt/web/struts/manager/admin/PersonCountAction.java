package com.ebiz.mmt.web.struts.manager.admin;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.chart.BaseChart;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-01
 */
public class PersonCountAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_btw_30_40 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 40) {
				role_id_btw_30_40 = true;
			}
		}

		if (!role_id_eq_30 && !role_id_btw_30_40) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeProdUser entity = new PeProdUser();
		if (role_id_eq_30) {
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id_start", dept_id);
			}
			request
					.setAttribute("sybDeptInfoList", super
							.getDeptInfoListWithOutLimit(mapping, form, request, response));
			request.setAttribute("is_admin", true);
			entity.getMap().put("is_admin", true);
		} else if (!role_id_eq_30 && role_id_btw_30_40) {
			entity.getMap().put("fgs_dept_id", getKonkaDeptForFgs(peProdUser.getDept_id()).getDept_id());
		}

		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserForYwyAndCxyCount(entity);
		request.setAttribute("entityList", entityList);

		// 总和
		Integer ywyCount = 0;
		Integer cxyCount = 0;
		Integer totalCount = 0;
		for (PeProdUser pp : entityList) {
			ywyCount += (Integer) (null == pp.getMap().get("ywycount2") ? 0 : pp.getMap().get("ywycount2"));
			cxyCount += (Integer) (null == pp.getMap().get("cxycount2") ? 0 : pp.getMap().get("cxycount2"));
			totalCount += (Integer) (null == pp.getMap().get("totalcount2") ? 0 : pp.getMap().get("totalcount2"));
		}
		request.setAttribute("ywyCount", ywyCount);
		request.setAttribute("cxyCount", cxyCount);
		request.setAttribute("totalCount", totalCount);

		return new ActionForward("/admin/PersonCount/list.jsp");
	}

	// 分公司
	public ActionForward branch_list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	// 经办
	public ActionForward office_list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	public ActionForward getPersonJsonForBranch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");

		
		PeProdUser ppu = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ppu.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_30 = false;
		boolean role_id_btw_30_40 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_30 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() < 40) {
				role_id_btw_30_40 = true;
			}
		}

		if (!role_id_eq_30 && !role_id_btw_30_40) {
			String msg = super.getMessage(request, "konka.r3.roleError");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeProdUser entity = new PeProdUser();
		if (role_id_eq_30) {
			request
					.setAttribute("sybDeptInfoList", super
							.getDeptInfoListWithOutLimit(mapping, form, request, response));
			request.setAttribute("is_admin", true);
			entity.getMap().put("is_admin", true);
		} else if (!role_id_eq_30 && role_id_btw_30_40) {
			entity.getMap().put("fgs_dept_id", getKonkaDeptForFgs(ppu.getDept_id()).getDept_id());
		}
		
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id_start", dept_id);
		}
		
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserForYwyAndCxyCount(entity);
		
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(dept_id));
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		List<BaseChart> baseChartList = new ArrayList<BaseChart>();
		if (null != entityList && entityList.size() > 0) {
			PeProdUser p = entityList.get(0);
			Integer a = (Integer) (null == p.getMap().get("totalcount2") ? 0 : p.getMap().get("totalcount2"));// 人员总数
			Integer b = (Integer) (null == p.getMap().get("ywycount2") ? 0 : p.getMap().get("ywycount2"));// 业务员
			Integer c = (Integer) (null == p.getMap().get("cxycount2") ? 0 : p.getMap().get("cxycount2"));// 促销员
			Integer d = a - b - c;
			// BaseChart b1 = new BaseChart();
			// b1.setLabel("人员总数");
			// b1.setValue(String.valueOf(a));
			BaseChart b2 = new BaseChart();
			b2.setLabel("业务员");
			b2.setValue(String.valueOf(b));
			BaseChart b3 = new BaseChart();
			b3.setLabel("促销员");
			b3.setValue(String.valueOf(c));
			BaseChart b4 = new BaseChart();
			b4.setLabel("其他");
			b4.setValue(String.valueOf(d));

			// baseChartList.add(b1);
			baseChartList.add(b2);
			baseChartList.add(b3);
			baseChartList.add(b4);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("baseChartList", baseChartList);
		String caption = "";
		if (null != konkaDept) {
			caption = konkaDept.getDept_name();
		}
		model.put("caption", caption);
		model.put("decimals", "2");
		model.put("showBorder", "0");
		model.put("formatNumberScale", "0");
		String xmlStr = getFacade().getTemplateService().getContent("chart/Pie3D.ftl", model);
		OutputStream out = response.getOutputStream();
		out.write(xmlStr.getBytes("UTF-8"));
		out.close();
		log.info(xmlStr);
		// render(response,
		// getFacade().getTemplateService().getContent("chart/Pie3D.ftl",
		// model),
		// "text/xml;charset=GBK");
		return null;
	}

	public List<?> getPersonCountForBranch(String dept_id) {
		List<String> array = new ArrayList<String>();

		String sql = " select a.dept_id,a.dept_name,value(b.ywy_num, 0) + value(c.cxy_num, 0),value(b.ywy_num, 0),value(c.cxy_num, 0) ";
		sql += " from konka_dept a left join (select bb.dept_id, count(bb.user_id) as ywy_num ";
		sql += " from (select distinct t.dept_id, t.user_id from MV_ORG_OF_CUSTOMER t where 1 = 1 and t.user_id is not null ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " ) bb group by bb.dept_id) b on a.dept_id = b.dept_id ";
		sql += " left join (select cc.dept_id, count(cc.cxy_user_id) as cxy_num from (select distinct t.dept_id, t.cxy_user_id ";
		sql += " from MV_ORG_OF_CXY t where 1 = 1 and t.cxy_user_id is not null ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " ) cc group by cc.dept_id) c on a.dept_id = c.dept_id ";
		sql += " where a.dept_type = 3 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " order by a.dept_id asc ";
		logger.info("++++++++++++++" + sql);
		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());
		return list;
	}

	public List<?> getPersonCountForDeptType_4(String dept_id, String l4_dept_id) {
		List<String> array = new ArrayList<String>();

		String sql = " select a.dept_id,a.dept_name,a.l4_dept_id,a.l4_dept_name,a.ywy_num,b.cxy_num ";
		sql += " from (select bb4.dept_id,bb4.dept_name, bb4.l4_dept_id,bb4.l4_dept_name,count(bb4.user_id) as ywy_num ";
		sql += " from (select distinct t.dept_id,t.dept_name,t.l4_dept_id,t.l4_dept_name,t.user_id from MV_ORG_OF_CUSTOMER t where 1 = 1 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		sql += " ) bb4 group by bb4.dept_id,bb4.dept_name,bb4.l4_dept_id,bb4.l4_dept_name) a,";
		sql += " (select cc4.dept_id,cc4.dept_name,cc4.l4_dept_id,cc4.l4_dept_name,count(cc4.cxy_user_id) as cxy_num ";
		sql += " from (select distinct t.dept_id,t.dept_name,t.l4_dept_id,t.l4_dept_name,t.cxy_user_id from MV_ORG_OF_CXY t where 1 = 1";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		sql += " ) cc4 group by cc4.dept_id,cc4.dept_name,cc4.l4_dept_id,cc4.l4_dept_name) b where a.l4_dept_id = b.l4_dept_id ";
		sql += " order by a.dept_name asc,a.dept_id asc,a.l4_dept_id asc,a.l4_dept_name asc";

		logger.info("++++++++++++++" + sql);
		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());
		return list;
	}

	public List<?> getPersonCountForDeptType_5(String dept_id, String l4_dept_id, String l5_dept_id) {
		List<String> array = new ArrayList<String>();

		String sql = " select a.dept_id,a.dept_name,a.l4_dept_id,a.l4_dept_name,a.l5_dept_id,a.l5_dept_name,a.ywy_num,b.cxy_num ";
		sql += " from (select bb4.dept_id,bb4.dept_name,bb4.l4_dept_id,bb4.l4_dept_name,bb4.l5_dept_id,bb4.l5_dept_name,count(bb4.user_id) as ywy_num";
		sql += " from (select distinct t.dept_id,t.dept_name,t.l4_dept_id,t.l4_dept_name,t.l5_dept_id,t.l5_dept_name,t.user_id from MV_ORG_OF_CUSTOMER t where 1 = 1 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		sql += " ) bb4 group by bb4.dept_id,bb4.dept_name,bb4.l4_dept_id,bb4.l4_dept_name,bb4.l5_dept_id,bb4.l5_dept_name) a,";
		sql += " (select cc4.dept_id,cc4.dept_name,cc4.l4_dept_id,cc4.l4_dept_name,cc4.l5_dept_id,cc4.l5_dept_name,count(cc4.cxy_user_id) as cxy_num ";
		sql += " from (select distinct t.dept_id,t.dept_name,t.l4_dept_id,t.l4_dept_name,t.l5_dept_id,t.l5_dept_name,t.cxy_user_id from MV_ORG_OF_CXY t where 1 = 1 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		sql += " ) cc4 group by cc4.dept_id,cc4.dept_name,cc4.l4_dept_id,cc4.l4_dept_name,cc4.l5_dept_id,cc4.l5_dept_name) b where a.l5_dept_id = b.l5_dept_id ";
		sql += " order by a.dept_name asc,a.dept_id asc,a.l4_dept_id asc,a.l4_dept_name asc,a.l5_dept_id asc,a.l5_dept_name asc";

		logger.info("++++++++++++++" + sql);
		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());
		return list;
	}

	public List<?> getPersonCountForOffice(String dept_id, String l4_l5_dept_id) {
		List<String> array = new ArrayList<String>();

		String sql = " select kd.dept_id,kd.dept_name,a.dept_id as l4_l5_dept_id,a.dept_name as l4_l5_dept_name,value(b.ywy_num, 0) + value(c.cxy_num, 0),value(b.ywy_num, 0),value(c.cxy_num, 0) ";
		sql += " from konka_dept a left join konka_dept kd on kd.dept_id = a.par_id ";
		sql += " left join((select bb4.l4_dept_id as l4_l5_dept_id,count(bb4.user_id) as ywy_num from (select distinct t.l4_dept_id, t.user_id ";
		sql += "  from MV_ORG_OF_CUSTOMER t where 1 = 1 and t.user_id is not null and t.l5_dept_id is null ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_l5_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_l5_dept_id);
		}
		sql += " ) bb4 group by bb4.l4_dept_id) union (select bb5.l5_dept_id as l4_l5_dept_id, count(bb5.user_id) as ywy_num ";
		sql += " from (select distinct t.l5_dept_id, t.user_id from MV_ORG_OF_CUSTOMER t where 1 = 1 and t.user_id is not null ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l4_l5_dept_id);
		}
		sql += " ) bb5 group by bb5.l5_dept_id)) b on a.dept_id = b.l4_l5_dept_id ";
		sql += " left join((select cc4.l4_dept_id as l4_l5_dept_id,count(cc4.cxy_user_id) as cxy_num ";
		sql += " from (select distinct t.l4_dept_id, t.cxy_user_id from MV_ORG_OF_CXY t where 1 = 1 and t.cxy_user_id is not null and t.l5_dept_id is null ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_l5_dept_id)) {
			sql += " and t.l4_dept_id = ? ";
			array.add(l4_l5_dept_id);
		}
		sql += " ) cc4 group by cc4.l4_dept_id) union (select cc5.l5_dept_id as l4_l5_dept_id,count(cc5.cxy_user_id) as cxy_num ";
		sql += " from (select distinct t.l5_dept_id, t.cxy_user_id from MV_ORG_OF_CXY t where 1 = 1 and t.cxy_user_id is not null ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_l5_dept_id)) {
			sql += " and t.l5_dept_id = ? ";
			array.add(l4_l5_dept_id);
		}
		sql += " ) cc5 group by cc5.l5_dept_id)) c on a.dept_id = c.l4_l5_dept_id where a.dept_type in (4, 5) and kd.dept_type = 3 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.par_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_l5_dept_id)) {
			sql += " and a.dept_id = ? ";
			array.add(l4_l5_dept_id);
		}
		sql += " order by a.dept_id asc ";

		logger.info("++++++++++++++" + sql);
		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());
		return list;

	}

	public ActionForward ajaxGetKonkaDeptListForJB(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 经办
		DynaBean dynaBean = (DynaBean) form;
		// 分公司dept_id
		String dept_id = (String) dynaBean.get("dept_id");
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (StringUtils.isBlank(dept_id)) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}

		String sql = " select t.dept_id, t.dept_name from konka_dept t where t.dept_type = 4 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.par_id = " + dept_id;
		}
		sql += " union ";
		sql += " select a.dept_id, a.dept_name from konka_dept a where a.dept_type = 5 and a.par_id in ";
		sql += " (select t.dept_id from konka_dept t where t.dept_type = 4 ";
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and t.par_id = " + dept_id;
		}
		sql += " ) ";

		// KonkaDept dept = new KonkaDept();
		// dept.getMap().put("dept_types", "4,5");
		// dept.setPar_id(new Long(dept_id));
		List<?> deptList = super.getFacade().getBaseReportService().getBaseReportForArray(sql);

		if (null != deptList && deptList.size() > 0) {
			for (int i = 0; i < deptList.size(); i++) {
				Object[] obj = (Object[]) deptList.get(i);
				sb = sb.append("{");
				sb = sb.append("\"dept_id\":\"").append(obj[0] == null ? "" : obj[0].toString()).append("\",");
				sb = sb.append("\"dept_name\":\"").append(obj[1] == null ? "" : obj[1].toString()).append("\"");
				sb = sb.append("},");
			}
		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

}