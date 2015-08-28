package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author OuYang,BaiYang
 * @version 2014-1-23
 */

public class KonkaR3ShopLinkAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");// R3编码
		String real_name_like = (String) dynaBean.get("real_name_like");// 姓名
		String sex = (String) dynaBean.get("sex");// 性别
		String birthday_s = (String) dynaBean.get("birthday_s");// 生日
		String birthday_e = (String) dynaBean.get("birthday_e");// 生日
		String tel_like = (String) dynaBean.get("tel_like");// 固定电话
		String mobile_like = (String) dynaBean.get("mobile_like");// 移动电话
		String telephone_like =(String) dynaBean.get("telephone_like");//移动电话
		String job_like = (String) dynaBean.get("job_like");// 岗位
		String position = (String) dynaBean.get("position");// 职务
		String fax_like = (String) dynaBean.get("fax_like");// 传真
		String email_like = (String) dynaBean.get("email_like");// 电子邮箱
		String weixin_like = (String) dynaBean.get("weixin_like");// 微信号
		String qq_like = (String) dynaBean.get("qq_like");// QQ号
		String is_default = (String) dynaBean.get("is_default");// 是否默认
		String is_valid = (String) dynaBean.get("is_valid");// 是否有效
		String is_kf = (String) dynaBean.get("is_kf");
		
		
		//判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		//分公司、客户名称、R3编码、姓名、性别、生日、固定电话、移动电话、岗位、职务、传真人、电子邮箱、微信号、QQ号、是否默认、是否有效、
		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		String r3_code = (String) dynaBean.get("r3_code");
		entity.getMap().put("r3_code", r3_code);
		String dept_name = (String) dynaBean.get("dept_name");
		entity.getMap().put("dept_name", dept_name);
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
        if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
        if (StringUtils.isNotBlank(r3_code_like)) {
			entity.getMap().put("r3_code_like",r3_code_like);
		}
        if(StringUtils.isNotBlank(real_name_like)){
        	entity.getMap().put("real_name_like", real_name_like);
        }
        if (StringUtils.isNotBlank(sex) && GenericValidator.isInt(sex)) {
			entity.setSex(Integer.valueOf(sex));
		}
        if (StringUtils.isNotBlank(birthday_s)) {
			entity.getMap().put("birthday_s", birthday_s+" 00:00:00");
		}
        if (StringUtils.isNotBlank(birthday_e)) {
			entity.getMap().put("birthday_e", birthday_e+" 23:59:59");
		}
        if (StringUtils.isNotBlank(tel_like)) {
			entity.getMap().put("tel_like", tel_like);
		}
        if (StringUtils.isNotBlank(telephone_like)) {
			entity.getMap().put("telephone_like", telephone_like);
		}
        if (StringUtils.isNotBlank(job_like)) {
			entity.getMap().put("job_like", job_like);
		}
        if (StringUtils.isNotBlank(position)) {
			entity.setPosition(position);
		}
        if (StringUtils.isNotBlank(fax_like)) {
			entity.getMap().put("fax_like", fax_like);
		}
        if (StringUtils.isNotBlank(email_like)) {
			entity.getMap().put("email_like", email_like);
		}
        if (StringUtils.isNotBlank(weixin_like)) {
			entity.getMap().put("weixin_like", weixin_like);
		}
        if (StringUtils.isNotBlank(qq_like)) {
			entity.getMap().put("qq_like", qq_like);
		}
        //是否默认
        if (StringUtils.isNotBlank(is_default)) {
			entity.setIs_default(is_default);
		}
        //是否有效
        if (StringUtils.isNotBlank(is_valid)) {
			entity.setIs_valid(is_valid);
		}
        
        //检查权限
        // 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
        
		
		//分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(ui.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		entity.getMap().put("dept_name", kd.getDept_name());
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		
        //分页
        Long recordCount = super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomerCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
        
		//查询
		List<KonkaR3ShopLink> entityList = super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkPaginatedForCustomerList(entity);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");
        KonkaR3ShopLink entity = new KonkaR3ShopLink();
        entity.setLink_id(Long.valueOf(link_id));
        entity =  super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomer(entity);
        super.copyProperties(form, entity);
		return mapping.findForward("view");
	}
	
	//修改
	public ActionForward edit(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//导航信息
		setNaviStringToRequestScope(form, request);
		//防止重复提交
		super.saveToken(request);
		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");
		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		entity.setLink_id(Long.valueOf(link_id));
		entity = super.getFacade().getKonkaR3ShopLinkService().getKonkaR3ShopLinkForCustomer(entity);
		entity.setQueryString(super.serialize(request, "link_id","method"));
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}
	
	//保存
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String link_id = (String) dynaBean.get("link_id");
		String mod_id=(String)dynaBean.get("mod_id");
		String customer_name = (String)dynaBean.get("customer_name");
		String r3_code = (String)dynaBean.get("r3_code");

		
//		//获取客户姓名，客户r3编码的id
//		KonkaR3Shop ks = new KonkaR3Shop();
//		ks.setCustomer_name(customer_name);
//		ks.setCustomer_code(r3_code);
//		ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
//		Long id = ks.getId(); 
//		
		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		super.copyProperties(entity,form);
		
		if (StringUtils.isEmpty(link_id)) {
			//获取客户姓名，客户r3编码的id
			KonkaR3Shop ks = new KonkaR3Shop();
		
			ks.setR3_code(r3_code);
			List<KonkaR3Shop> shopList=super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(ks);
			if(null!=shopList && shopList.size()> 0){
				ks =shopList.get(0);
				if(null!=ks){
					Long id = ks.getId(); 	
					entity.setR3_shop_id(id);
					entity.setAdd_date(new Date());
					super.getFacade().getKonkaR3ShopLinkService()
							.createKonkaR3ShopLink(entity);
					super.saveMessage(request, "entity.inserted");
				}else{
					super.renderJavaScript(response, "The customer linked is empty!");
					return null;
				}
			}else{
				super.renderJavaScript(response, "The customer linked is empty!");
				return null;
			}
		} else {

			entity.setAdd_date(new Date());
			super.getFacade().getKonkaR3ShopLinkService()
					.modifyKonkaR3ShopLink(entity);
			super.saveMessage(request, "entity.updated");
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/KonkaR3ShopLink.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);

		// end
		return forward;
	}
	
	//添加
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		
		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		// 判断用户是否是空
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		KonkaR3ShopLink entity = new KonkaR3ShopLink();
		//检查权限
        // 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id", null);
				entity.getMap().put("fgs_dept_value", dept_id);
			}

			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("fgs_dept_value", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", userInfo.getId());
		// 数据级别判断结束
        
		
		//分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
		} else {
			kd.setDept_id(userInfo.getDept_id());
		}
		kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		entity.getMap().put("dept_name", kd.getDept_name());
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		// 拿到客户的列表
		
		List<KonkaR3Shop> customerList = getCustomer(userInfo);
		request.setAttribute("customerList", customerList);
		
		return mapping.findForward("input");
	}

	/**
	 * 根据用户按照部门级别获取客户
	 * 
	 * @param userInfo
	 * @return
	 */
	protected List<KonkaR3Shop> getCustomer(PeProdUser userInfo) {
		KonkaR3Shop entity = new KonkaR3Shop();

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());

		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		// return null;
		case 8:
			// 分公司及以下部门可见

			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", _dept_id);
				// entity.getMap().put("fgs_dept_value", _dept_id);
			}
			break;

		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", userInfo.getId());

		// entity.setIs_del(0L);// 未被停用的
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);
		logger.info("++++++++++++++++++++++++++++++++");
		return entityList;
	}
}