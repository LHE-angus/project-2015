package com.ebiz.mmt.web.struts.inter.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaInterfaceAccessLog;
import com.ebiz.mmt.domain.KonkaInterfaceBindsUser;
import com.ebiz.mmt.web.struts.inter.form.CmsCustomer;
import com.ebiz.mmt.web.struts.inter.form.CmsCustomerForm;
import com.ebiz.mmt.web.struts.inter.form.CmsCustomerVO;
import com.ebiz.mmt.web.struts.inter.form.InterUser;
import com.ebiz.mmt.web.util.IpUtils;

/**
 * 多媒体,县乡客户获取接口
 * 
 * @author zhou
 * 
 */
public class CmsCustomerInterfaceAction extends BaseInterAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return getAllCmsCustomer(mapping, form, request, response);
	}

	public ActionForward getAllCmsCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// 1.权限校验
		CmsCustomerForm obj = new CmsCustomerForm();
		// 取当前登录的人是否有绑定接口调用
		InterUser user = super.getInterUser(form, request);
		KonkaInterfaceBindsUser entity = new KonkaInterfaceBindsUser();
		entity.setUser_id(user.getUser_id());
		entity.setLicenses_sn(user.getLicenses_sn());
		entity.setUser_key(user.getUser_key());
		entity = super.getFacade().getKonkaInterfaceBindsUserService().getKonkaInterfaceBindsUser(entity);
		if (null == entity) {
			// 失败日志
			KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
			log.setAdd_date(new Date());
			log.setError_msg("用户ID:" + user.getUser_id() + ",未找到绑定数据");
			log.setIp(IpUtils.getRemortIP(request));
			log.setLicenses_sn(user.getLicenses_sn());
			log.setReq_state(1);
			log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
			log.setReq_mod_name("多媒体,县乡客户获取接口");
			log.setUser_id(user.getUser_id());
			log.setUser_key(user.getUser_key());
			super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);
			obj.setReturn_state(1);
			obj.setReturn_error("user_id user_key licenses_sn does not match...");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}

		// 2.对请求参数验证
		DynaBean dynaBean = (DynaBean) form;
		// 第几页
		String pageno = (String) dynaBean.get("pageno");
		// 每次取多少
		String pagesize = (String) dynaBean.get("pagesize");

		if (pageno == null || pagesize == null) {
			obj.setReturn_state(1);
			obj.setReturn_error("parameters pageno and pagesize must not be null ");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (!isNumber(pageno) || !isNumber(pagesize)) {
			obj.setReturn_state(1);
			obj.setReturn_error("illegalArguments, pageno and pagesize should be a number ");
			super.renderJson(response, JSON.toJSONString(obj));
			return null;
		}
		if (Integer.valueOf(pageno) < 1) {
			pageno = "1";
		}
		if (Integer.valueOf(pagesize) > 500) {
			pagesize = "500";
		}

		// 3. 数据获取
		List<CmsCustomerVO> cmsvolist = new ArrayList<CmsCustomerVO>();
		List<CmsCustomer> cmslist = new ArrayList<CmsCustomer>();
		CmsCustomerVO t = new CmsCustomerVO();
		int totalCount = super.getFacade().getCmsCustomerService().getCmsCustomerCount(t);
		int first = (Integer.valueOf(pageno) - 1) * Integer.valueOf(pagesize);
		t.getRow().setFirst(first);// 从第几条记录开始取数
		t.getRow().setCount(Integer.valueOf(pagesize));// 每次取多少条记录
		cmsvolist = super.getFacade().getCmsCustomerService().getCmsCustomerList(t);
		int currentCount = cmsvolist == null ? 0 : cmsvolist.size();

		if (cmsvolist != null) {
			for (CmsCustomerVO cvo : cmsvolist) {
				CmsCustomer cc = new CmsCustomer();
				if (cvo.getAddDate() != null && !"".equals(cvo.getAddDate())) {
					// cc.setAddDate(StringUtils.substring(cvo.getAddDate(), 0,
					// 10));
					cc.setAddDate(cvo.getAddDate());
				} else {
					cc.setAddDate("");
				}
				cc.setAreaCode(cvo.getAreaCode());
				cc.setAreaName(cvo.getAreaName() == null ? "" : cvo.getAreaName());
				cc.setBranchAreaCode(cvo.getBranchAreaCode());
				cc.setBranchAreaName(cvo.getBranchAreaName() == null ? "" : cvo.getBranchAreaName());
				cc.setCustomerFgsCode(cvo.getCustomerFgsCode() == null ? "" : cvo.getCustomerFgsCode());// 分公司编码
				cc.setCustomerBigType(4);// 客户大类编号
				cc.setCustomerBigTypeName("县乡渠道");// 客户大类名称
				cc.setBranchName(cvo.getBranchName());
				cc.setCustomerName(cvo.getCustomerName());
				cc.setCustomerType(cvo.getCustomerType() == null ? 0 : cvo.getCustomerType());
				cc.setEntpinro(cvo.getEntpinro() == null ? "" : cvo.getEntpinro());
				cc.setEntpmainpds(cvo.getEntpmainpds() == null ? "" : cvo.getEntpmainpds());
				cc.setEntpplevel(cvo.getEntpplevel() == null ? "" : cvo.getEntpplevel());
				cc.setEntpsalearea(cvo.getEntpsalearea() == null ? "" : cvo.getEntpsalearea());
				cc.setIsCassd(cvo.getIsCassd());
				cc.setIsdel(cvo.getIsdel());
				cc.setIsLoevm(cvo.getIsLoevm());
				cc.setR3Code(cvo.getR3Code());
				cc.setR3SalesOrgCode(cvo.getR3SalesOrgCode());
				cc.setStatus(cvo.getStatus());
				// 下面是一二三级（分公司，办事处等等）
				cc.setOneDeptId(cvo.getOneDeptId());
				cc.setOneDeptName(cvo.getOneDeptName());
				cc.setTwoDeptId(cvo.getTwoDeptId());
				cc.setTwoDeptName(cvo.getTwoDeptName());
				cc.setThreeDeptId(cvo.getThreeDeptId());
				cc.setThreeDeptName(cvo.getThreeDeptName());
				cmslist.add(cc);
			}
		}

		// 4. 返回数据
		obj.setCmsCustomerlist(cmslist);
		obj.setReturn_state(0);
		obj.setReturn_error("");
		obj.setReturn_msg("当前共有" + cmslist.size() + "条数据");
		obj.setTotalCount(totalCount);
		obj.setCurrentCount(currentCount);
		super.renderJson(response, JSON.toJSONString(obj));
		//System.out.println(JSON.toJSONString(obj));

		// 5.成功日志
		KonkaInterfaceAccessLog log = new KonkaInterfaceAccessLog();
		log.setAdd_date(new Date());
		log.setIp(IpUtils.getRemortIP(request));
		log.setLicenses_sn(user.getLicenses_sn());
		log.setReq_state(0);
		log.setReq_url(request.getRequestURL().toString() + "?" + request.getQueryString());
		log.setReq_mod_name("多媒体,县乡客户获取接口");
		log.setUser_id(user.getUser_id());
		log.setUser_key(user.getUser_key());
		log.setReq_content(JSON.toJSONString(obj));
		super.getFacade().getKonkaInterfaceAccessLogService().createKonkaInterfaceAccessLog(log);

		return null;
	}

	private static boolean isNumber(String input) {
		if (input == null || "".equals(input))
			return false;
		return Pattern.matches("[0-9]*", input);
	}

}
