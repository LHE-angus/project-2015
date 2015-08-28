package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcBackPassword;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Pan,Gang
 * @version 2013-08-16
 */
public class IsAct2Action extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String key = (String) dynaBean.get("key");
		// String email = (String) dynaBean.get("email");
		// String s1 = (String) dynaBean.get("s1");
		//System.out.println("key---------->" + key);
		// //System.out.println("email---------->" + email);
		// //System.out.println("s1---------->" + s1);
		if (StringUtils.isBlank(key)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		// DESPlus des = new DESPlus();
		// String id = des.decrypt(s1);
		// //System.out.println("id--------->" + id);
		// String s2 = des.decrypt(email);
		// //System.out.println("s2--------->" + s2);

		EcBackPassword ecb = new EcBackPassword();
		ecb.setYz_key(key);
		ecb = super.getFacade().getEcBackPasswordService().getEcBackPassword(ecb);
		if (null == ecb) {
			super.renderJavaScript(response, "window.onload=function(){alert('对不起！没有找到用户信息');location.href='"
			        + super.getCtxPath(request) + "/touch/login.do';}");
			return null;
		}
		if (ecb.getState() == 1) {
			super.renderJavaScript(response, "window.onload=function(){alert('链接只能修改一次，链接已经失效！');location.href='"
			        + super.getCtxPath(request) + "/touch/login.do';}");
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ecb.getAdd_date());
		calendar.add(Calendar.DATE, 1);
		Date date = new Date();

		if (calendar.getTime().getTime() - date.getTime() < 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('链接已经超时，链接已经失效！');location.href='"
			        + super.getCtxPath(request) + "/touch/login.do';}");
			return null;
		}

		EcUser ec = new EcUser();
		ec.setUser_name(ecb.getCard_no());
		List<EcUser> ecList = super.getFacade().getEcUserService().getEcUserList(ec);
		if (null == ecList || ecList.size() == 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('没有找到用户信息');location.href='"
			        + super.getCtxPath(request) + "/touch/login.do';}");
			return null;
		} else if (ecList.size() > 1) {
			super.renderJavaScript(response, "window.onload=function(){alert('用户名重复，请联系管理员');location.href='"
			        + super.getCtxPath(request) + "/touch/login.do';}");
			return null;
		}
		ec = ecList.get(0);

		dynaBean.set("id", ec.getId());
		dynaBean.set("e_id", ecb.getId());

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String e_id = (String) dynaBean.get("e_id");
		String new_password = (String) dynaBean.get("new_password");
		HttpSession session = request.getSession();
		String verificationCode = (String) dynaBean.get("verificationCode");

		String msg = "";

		if (StringUtils.isBlank(verificationCode)) {
			msg = "验证码不能为空";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}
		if (!verificationCode.equals(session.getAttribute("verificationCode"))) {
			msg = "验证码不正确";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');window.history.back();}");
			return null;
		}

		EcUser entity = new EcUser();
		entity.setId(Long.valueOf(id));
		DESPlus des = new DESPlus();
		entity.setPass_word(des.encrypt(new_password));
		entity.getMap().put("e_id", e_id);

		super.getFacade().getEcUserService().modifyEcUserAndEid(entity);

		saveMessage(request, "password.updated.success");

		String msg2 = "密码修改成功";
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg2 + "');location.href='"
		        + super.getCtxPath(request) + "/touch/login.do';}");
		return null;
	}

	@Override
	public ActionForward ajaxGetStockCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		logger.info("i coming -------------->");
		String pd_sn = (String) dynaBean.get("pd_sn");
		// 东莞分公司
		// 工厂(需要和分公司绑定) not null
		String zwerks = "L00E";
		// 库位 not null
		String zlgort = "P046";
		// 仓位
		String zlgpla = "F146ZT";
		// 物料
		List<ZLEBIN> list = new ArrayList<ZLEBIN>();
		ReturnInfo<ZLEBIN> info = new ReturnInfo<ZLEBIN>();
		double dgCount = 0.00;

		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
			// list = getFacade().getR3WebInterfaceService().getZles20(zwerks,
			// zlgort, zlgpla, pd_sn);
			info = getFacade().getR3WebInterfaceService().getZles20(zwerks, zlgort, zlgpla, pd_sn);
			if (info.getSuccess() == true) {
				list = info.getDataResult();
			}
		}
		if (null != list && list.size() > 0) {
			ZLEBIN zlb = list.get(0);
			dgCount = dgCount + zlb.getVERME();
		}

		// 合肥分公司（滁州发货）
		// 工厂(需要和分公司绑定) not null
		String zwerks1 = "L00B";
		// 仓位 仓库地点 not null
		String zlgort1 = "F222";
		// 物料
		List<StockCheckResult> entitylist = new ArrayList<StockCheckResult>();
		ReturnInfo<StockCheckResult> info2 = new ReturnInfo<StockCheckResult>();

		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
			// entitylist =
			// super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks1,
			// zlgort1, pd_sn);
			info2 = super.getFacade().getR3WebInterfaceService().getFGSKC(zwerks1, zlgort1, pd_sn);
			if (info2.getSuccess() == true) {
				entitylist = info2.getDataResult();
			}
		}
		double hfCount = 0.00;
		if (null != entitylist && entitylist.size() > 0) {
			StockCheckResult scr = entitylist.get(0);
			hfCount = hfCount + scr.getLamount().doubleValue();
		}

		//System.out.println("仓库总和========" + getStockCount(pd_sn));

		// 构建数据结构
		List<HashMap> listJson = new ArrayList<HashMap>();
		HashMap map1 = new HashMap();
		map1.put("stock_name", "东莞分公司");
		map1.put("stock_count", dgCount);
		listJson.add(map1);
		HashMap map2 = new HashMap();
		map2.put("stock_name", "合肥分公司");
		map2.put("stock_count", hfCount);
		listJson.add(map2);

		logger.info("----ajaxGetStockCount---->{}", JSON.toJSONString(listJson));
		super.renderJson(response, JSON.toJSONString(listJson));
		return null;
	}
}
