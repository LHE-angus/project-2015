package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JfRule;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsDetails;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Wu,ShangLong
 * @version 2013-06-25
 */
public class JfWebInterfaceAction extends BaseAction {

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param opr_date
	 *            ： 格式化（“yyyy-MM-dd”），dept_id：分公司ID，md_name：产品型号
	 * @return status:-2-未设置规则，-1-参数丢失，0-成功 jf_type: 1-按数量固定返积分,2-按金额比例返积分
	 *         jf_value:数值
	 * @desc 获取积分规则
	 */
	public ActionForward getRule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String md_name = (String) dynaBean.get("md_name");
		String opr_date = (String) dynaBean.get("opr_date");

		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isEmpty(dept_id) || StringUtils.isEmpty(md_name) || StringUtils.isEmpty(opr_date)) {
			sb = sb.append("\"status\":\"-1\"}");
			super.renderJson(response, sb.toString());
			return null;
		}

		JfRule entity = new JfRule();
		entity.setDept_id(Long.valueOf(dept_id));
		entity.setPd_id(md_name);
		entity.setJf_avl_type(2);
		entity.getMap().put("jf_avl_start_lt", opr_date);
		entity.getMap().put("jf_avl_end_gt", opr_date);

		List<JfRule> entityList = super.getFacade().getJfRuleService().getJfRuleList(entity);
		if (null != entityList && entityList.size() > 0) {
			sb = sb.append("\"status\":\"0\",");
			sb = sb.append("\"jf_type\":\"").append(entityList.get(0).getJf_type()).append("\",");
			sb = sb.append("\"jf_value\":\"").append(entityList.get(0).getJf_value()).append("\"");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		entity.setJf_avl_type(1);
		entity.getMap().put("jf_avl_start_lt", null);
		entity.getMap().put("jf_avl_end_gt", null);

		entityList = super.getFacade().getJfRuleService().getJfRuleList(entity);
		if (null != entityList && entityList.size() > 0) {
			sb = sb.append("\"status\":\"0\",");
			sb = sb.append("\"jf_type\":\"").append(entityList.get(0).getJf_type()).append("\",");
			sb = sb.append("\"jf_value\":\"").append(entityList.get(0).getJf_value()).append("\"");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		sb = sb.append("\"status\":\"-2\"");
		sb = sb.append("}");
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param user_sn
	 *            :会员号，dept_id：分公司ID，md_name：产品型号，score：积分值，out_sys_name：外部系统关联名，
	 *            out_sys_id：外部系统关联ID
	 * @return status:-1-参数丢失，0-成功
	 * @desc 新增积分条目
	 */
	public ActionForward createScores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String user_sn = (String) dynaBean.get("user_sn");
		String dept_id = (String) dynaBean.get("dept_id");
		String md_name = (String) dynaBean.get("md_name");
		String score = (String) dynaBean.get("score");
		String out_sys_name = (String) dynaBean.get("out_sys_name");
		String out_sys_id = (String) dynaBean.get("out_sys_id");

		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isEmpty(user_sn) || StringUtils.isEmpty(dept_id) || StringUtils.isEmpty(md_name)
				|| StringUtils.isEmpty(score) || StringUtils.isEmpty(out_sys_name) || StringUtils.isEmpty(out_sys_id)) {
			sb = sb.append("\"status\":\"-1\"}");
			super.renderJson(response, sb.toString());
			return null;
		}

		JfScortsDetails entity = new JfScortsDetails();
		entity.setDept_id(Long.valueOf(dept_id));
		entity.setUser_sn(user_sn);
		entity.setPd_id(md_name);
		entity.setScorts(new BigDecimal(score));
		entity.setJf_cate(1);
		entity.setStatus(0);
		entity.setOut_sys_name(out_sys_name);
		entity.setOut_sys_id(out_sys_id);

		super.getFacade().getJfScortsDetailsService().createJfScortsDetails(entity);

		sb = sb.append("\"status\":\"0\"}");
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param user_sn
	 *            :会员号，dept_id：分公司ID，md_name：产品型号，score：积分值，out_sys_name：外部系统关联名，
	 *            out_sys_id：外部系统关联ID
	 * @return status:（-1：参数丢失，0：成功，-2：关联审核会员商品型号不存在，-3：关联多个会员商品型号信息）
	 * @desc 更新积分条目状态
	 */
	public ActionForward modifyScores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String user_sn = (String) dynaBean.get("user_sn");
		String dept_id = (String) dynaBean.get("dept_id");
		String md_name = (String) dynaBean.get("md_name");
		String score = (String) dynaBean.get("score");
		String out_sys_name = (String) dynaBean.get("out_sys_name");
		String out_sys_id = (String) dynaBean.get("out_sys_id");

		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isEmpty(user_sn) || StringUtils.isEmpty(dept_id) || StringUtils.isEmpty(md_name)
				|| StringUtils.isEmpty(score) || StringUtils.isEmpty(out_sys_name) || StringUtils.isEmpty(out_sys_id)) {
			sb = sb.append("\"status\":\"-1\"}");
			super.renderJson(response, sb.toString());
			return null;
		}

		JfScortsDetails entity = new JfScortsDetails();
		entity.setDept_id(Long.valueOf(dept_id));
		entity.setUser_sn(user_sn);
		entity.setPd_id(md_name);
		// entity.setScorts(new BigDecimal(score));
		entity.setJf_cate(1);
		entity.setStatus(0);
		entity.setOut_sys_name(out_sys_name);
		entity.setOut_sys_id(out_sys_id);

		List<JfScortsDetails> entityList = super.getFacade().getJfScortsDetailsService().getJfScortsDetailsList(entity);

		if (null == entityList || entityList.size() == 0) {
			sb = sb.append("\"status\":\"-2\"}");
			super.renderJson(response, sb.toString());
			return null;
		} else if (entityList.size() > 1) {
			sb = sb.append("\"status\":\"-3\"}");
			super.renderJson(response, sb.toString());
			return null;
		} else {
			JfScortsDetails temp = entityList.get(0);
			temp.setStatus(1);
			temp.setScorts(new BigDecimal(score));

			super.getFacade().getJfScortsDetailsService().modifyJfScortsDetailsAndTotalScores(temp); // 审核通过，修改总积分
		}

		sb = sb.append("\"status\":\"0\"}");
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param user_sn
	 *            ：会员号
	 * @return status:-2:该会员没有积分，-1-参数丢失，0-成功 ;scores：会员积分
	 * @desc 获取积分
	 */
	public ActionForward getScores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String user_sn = (String) dynaBean.get("user_sn");

		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isEmpty(user_sn)) {
			sb = sb.append("\"status\":\"-1\"}");
			super.renderJson(response, sb.toString());
			return null;
		}

		JfScorts entity = new JfScorts();
		entity.setUser_sn(user_sn);

		List<JfScorts> entityList = super.getFacade().getJfScortsService().getJfScortsList(entity);
		if (null != entityList && entityList.size() > 0) {
			sb = sb.append("\"status\":\"0\",");
			sb = sb.append("\"scores\":\"").append(entityList.get(0).getTotal_scorts()).append("\"");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		sb = sb.append("\"status\":\"-2\"}");
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param user_sn
	 *            :会员号，dept_id：分公司ID，md_name：产品型号，num：产品数量，price:
	 *            产品单价，status：积分状态（0-需要审核，1-不需要审核），opr_date ： 购买时间
	 *            格式化（“yyyy-MM-dd”，out_sys_name：外部系统关联名， out_sys_id：外部系统关联ID
	 * @return status:-1-参数丢失，0-成功,-2:规则不存在；
	 * @desc 新增积分条目并且获取积分，不用关心积分规则，直接通过产品型号和购买时间，分公司获取
	 */
	public ActionForward createJfScores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String user_sn = (String) dynaBean.get("user_sn");
		String dept_id = (String) dynaBean.get("dept_id");
		String md_name = (String) dynaBean.get("md_name");
		String num = (String) dynaBean.get("num");
		String price = (String) dynaBean.get("price");
		String status = (String) dynaBean.get("status");
		String opr_date = (String) dynaBean.get("opr_date");
		String out_sys_name = (String) dynaBean.get("out_sys_name");
		String out_sys_id = (String) dynaBean.get("out_sys_id");

		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isEmpty(user_sn) || StringUtils.isEmpty(dept_id) || StringUtils.isEmpty(md_name)
				|| StringUtils.isEmpty(num) || StringUtils.isEmpty(price) || StringUtils.isEmpty(opr_date)
				|| StringUtils.isEmpty(out_sys_name) || StringUtils.isEmpty(out_sys_id)) {
			sb = sb.append("\"status\":\"-1\"}");
			super.renderJson(response, sb.toString());
			return null;
		}

		JfRule entity = new JfRule();
		entity.setDept_id(Long.valueOf(dept_id));
		entity.setPd_id(md_name);
		entity.setJf_avl_type(2);
		entity.getMap().put("jf_avl_start_lt", opr_date);
		entity.getMap().put("jf_avl_end_gt", opr_date);

		List<JfRule> entityList = super.getFacade().getJfRuleService().getJfRuleList(entity);
		String jf_type = "";
		BigDecimal score = new BigDecimal("0");
		if (null == entityList || entityList.size() == 0) {
			entity.setJf_avl_type(1);
			entity.getMap().put("jf_avl_start_lt", null);
			entity.getMap().put("jf_avl_end_gt", null);

			entityList = super.getFacade().getJfRuleService().getJfRuleList(entity);
			if (null == entityList || entityList.size() == 0) {
				sb = sb.append("\"status\":\"-2\",");
				sb = sb.append("}");
				super.renderJson(response, sb.toString());
				return null;
			} else {
				jf_type = entityList.get(0).getJf_type().toString();
				if ("1".equals(jf_type)) {
					score = entityList.get(0).getJf_value().multiply(new BigDecimal(num));
				}
				if ("2".equals(jf_type)) {
					score = entityList.get(0).getJf_value().multiply(new BigDecimal(num)).multiply(
							new BigDecimal(price)).divide(new BigDecimal("100"));
				}
			}
		} else {
			jf_type = entityList.get(0).getJf_type().toString();
			if ("1".equals(jf_type)) {
				score = entityList.get(0).getJf_value().multiply(new BigDecimal(num));
			}
			if ("2".equals(jf_type)) {
				score = entityList.get(0).getJf_value().multiply(new BigDecimal(num)).multiply(new BigDecimal(price))
						.divide(new BigDecimal("100"));
			}
		}

		JfScortsDetails details = new JfScortsDetails();
		details.setDept_id(Long.valueOf(dept_id));
		details.setUser_sn(user_sn);
		details.setPd_id(md_name);
		details.setScorts(score);
		details.setJf_cate(1);
		details.setStatus(Integer.valueOf(status));
		details.setOut_sys_name(out_sys_name);
		details.setOut_sys_id(out_sys_id);

		super.getFacade().getJfScortsDetailsService().createJfScortsDetails(details);

		sb = sb.append("\"status\":\"0\"}");
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param user_sn
	 *            :会员号，dept_id：分公司ID，md_name：产品型号，out_sys_name：外部系统关联名，
	 *            out_sys_id：外部系统关联ID
	 * @return status:（-1：参数丢失，0：成功，-2：关联审核会员商品型号不存在，-3：关联多个会员商品型号信息）
	 * @desc 更新积分条目状态
	 */
	public ActionForward removeScores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String user_sn = (String) dynaBean.get("user_sn");
		String dept_id = (String) dynaBean.get("dept_id");
		String md_name = (String) dynaBean.get("md_name");
		String out_sys_name = (String) dynaBean.get("out_sys_name");
		String out_sys_id = (String) dynaBean.get("out_sys_id");

		StringBuffer sb = new StringBuffer("{");
		if (StringUtils.isEmpty(user_sn) || StringUtils.isEmpty(dept_id) || StringUtils.isEmpty(md_name)
				|| StringUtils.isEmpty(out_sys_name) || StringUtils.isEmpty(out_sys_id)) {
			sb = sb.append("\"status\":\"-1\"}");
			super.renderJson(response, sb.toString());
			return null;
		}

		JfScortsDetails entity = new JfScortsDetails();
		entity.setDept_id(Long.valueOf(dept_id));
		entity.setUser_sn(user_sn);
		entity.setPd_id(md_name);
		entity.setJf_cate(1);
		entity.setOut_sys_name(out_sys_name);
		entity.setOut_sys_id(out_sys_id);

		List<JfScortsDetails> entityList = super.getFacade().getJfScortsDetailsService().getJfScortsDetailsList(entity);

		if (null == entityList || entityList.size() == 0) {
			sb = sb.append("\"status\":\"-2\"}");
			super.renderJson(response, sb.toString());
			return null;
		} else if (entityList.size() > 1) {
			sb = sb.append("\"status\":\"-3\"}");
			super.renderJson(response, sb.toString());
			return null;
		} else {
			JfScortsDetails temp = entityList.get(0);
			super.getFacade().getJfScortsDetailsService().removeJfScortsDetailsAndTotalScores(temp);
		}

		sb = sb.append("\"status\":\"0\"}");
		super.renderJson(response, sb.toString());
		return null;
	}
}
