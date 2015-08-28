package com.ebiz.mmt.web.struts.wage;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaPersonPwd;
import com.ebiz.mmt.domain.KonkaPersonWage;

public class SearchAction extends BaseWageAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String id_card_no = (String) dynaBean.get("id_card_no");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		if (StringUtils.isBlank(id) || StringUtils.isBlank(id_card_no)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		KonkaPersonPwd entity = new KonkaPersonPwd();
		entity.setId(Long.valueOf(id));
		entity.setId_card_no(id_card_no);
		entity = getFacade().getKonkaPersonPwdService().getKonkaPersonPwd(entity);
		if (null != entity) {
			dynaBean.set("id_card_no", entity.getId_card_no());
			dynaBean.set("email", entity.getEmail());
			dynaBean.set("real_name", entity.getReal_name());
		}

		String __year;
		String __month;
		Calendar cal = Calendar.getInstance();
		Integer _year = cal.get(Calendar.YEAR);// 得到年
		Integer _month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		request.setAttribute("localyear", _year);
		__year = _year.toString();
		__month = _month.toString();
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			__year = year;
			__month = month;
		}
		dynaBean.set("year", __year);
		dynaBean.set("month", __month);

		// 取上个月，下个月
		Calendar c = Calendar.getInstance();
		c.set(Integer.valueOf(__year), Integer.valueOf(__month) - 1, 1);
		c.add(Calendar.MONTH, -1);
		int _lyear = c.get(Calendar.YEAR);
		int _lmonth = c.get(Calendar.MONTH) + 1;
		logger.info("____________________________________上个月：" + _lyear + _lmonth);
		Calendar cc = Calendar.getInstance();
		cc.set(Integer.valueOf(__year), Integer.valueOf(__month) - 1, 1);
		cc.add(Calendar.MONTH, 1);
		int _nyear = cc.get(Calendar.YEAR);
		int _nmonth = cc.get(Calendar.MONTH) + 1;
		logger.info("____________________________________下个月：" + _nyear + _nmonth);
		dynaBean.set("lyear", _lyear);
		dynaBean.set("lmonth", _lmonth);
		dynaBean.set("nyear", _nyear);
		dynaBean.set("nmonth", _nmonth);

		dynaBean.set("id_card_no", id_card_no);
		dynaBean.set("pwdId", id);
		dynaBean.set("id", null);
		return mapping.findForward("list");
	}

	/**
	 * @desc 查询工资
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pwdId = (String) dynaBean.get("pwdId");
		String id_card_no = (String) dynaBean.get("id_card_no");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");

		if (StringUtils.isBlank(id_card_no) || StringUtils.isBlank(year) || StringUtils.isBlank(month)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		KonkaPersonWage entity = new KonkaPersonWage();
		entity.setId_card_no(id_card_no);
		entity.setY(Integer.valueOf(year));
		entity.setM(Integer.valueOf(month));
		entity.getRow().setCount(2);
		List<KonkaPersonWage> list = getFacade().getKonkaPersonWageService().getKonkaPersonWageList(entity);
		if (null == list || list.size() == 0) { // 未查询到工资记录
			saveError(request, "wage.search.failed.result.isEmpty", new String[] { year, month });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&id=" + pwdId + "&id_card_no=" + id_card_no + "&year=" + year + "&month=" + month);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			return forward;
		}
		// else if (list.size() > 1) { //工资表XX年XX月个人工资记录重复，通知联系管理员
		// saveError(request, "wage.search.failed.result.repeat", new
		// String[]{year, month});
		// StringBuffer pathBuffer = new StringBuffer();
		// pathBuffer.append(mapping.findForward("success").getPath());
		// pathBuffer.append("&id=" + pwdId + "&id_card_no=" + id_card_no +
		// "&year=" + year + "&month=" + month);
		// pathBuffer.append("&");
		// ActionForward forward = new ActionForward(pathBuffer.toString(),
		// true);
		// return forward;
		// }

		KonkaPersonWage wage = list.get(0);
		super.copyProperties(form, wage);
		dynaBean.set("year", year);
		dynaBean.set("month", month);

		// 取上个月，下个月
		Calendar c = Calendar.getInstance();
		Integer _year = c.get(Calendar.YEAR);// 得到当前年
		request.setAttribute("localyear", _year);
		c.set(Integer.valueOf(year), Integer.valueOf(month) - 1, 1);
		c.add(Calendar.MONTH, -1);
		int _lyear = c.get(Calendar.YEAR);
		int _lmonth = c.get(Calendar.MONTH) + 1;
		logger.info("____________________________________上个月：" + _lyear + _lmonth);
		Calendar cc = Calendar.getInstance();
		cc.set(Integer.valueOf(year), Integer.valueOf(month) - 1, 1);
		cc.add(Calendar.MONTH, 1);
		int _nyear = cc.get(Calendar.YEAR);
		int _nmonth = cc.get(Calendar.MONTH) + 1;
		logger.info("____________________________________下个月：" + _nyear + _nmonth);
		dynaBean.set("lyear", _lyear);
		dynaBean.set("lmonth", _lmonth);
		dynaBean.set("nyear", _nyear);
		dynaBean.set("nmonth", _nmonth);

		dynaBean.set("pwdId", pwdId);
		return mapping.findForward("list");
	}

}
