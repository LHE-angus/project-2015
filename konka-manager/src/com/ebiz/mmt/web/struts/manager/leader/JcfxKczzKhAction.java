package com.ebiz.mmt.web.struts.manager.leader;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.JcfxKczzKh;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.mmt.domain.JcfxKczzKhfzLink;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ibm.db2.jcc.b.re;
/**
 * 决策分析库存周转客户表
 * @author Administrator
 *
 */
public class JcfxKczzKhAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return jcfxCwbkczzlList(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String _customer_name_like = (String) dynaBean.get("_customer_name_like");
		String _r3_code_like = (String) dynaBean.get("_r3_code_like");
		String _add_user_name = (String) dynaBean.get("_add_user_name");
		String excel_all = (String) dynaBean.get("excel_all");
		String group_by_fied = (String) dynaBean.get("group_by_fied");
		String c_index = (String) dynaBean.get("c_index");
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		JcfxKczzKh entity = new JcfxKczzKh();
		
		if (StringUtils.isNotBlank(_r3_code_like)) {
			entity.getMap().put("_r3_code_like", _r3_code_like);
		}
		if (StringUtils.isNotBlank(_customer_name_like)) {
			entity.getMap().put("_customer_name_like", _customer_name_like);
		}
		if(StringUtils.isNotBlank(_add_user_name)){
			entity.getMap().put("_add_user_name", _add_user_name);
		}
		request.setAttribute("level", "1");
		if(StringUtils.isNotBlank(c_index)){
			entity.getMap().put("c_index", c_index);
			request.setAttribute("level", "2");
		}
		
		if(StringUtils.isNotBlank(group_by_fied)){		
			entity.getMap().put("group_field_name", group_by_fied);
		}
		
		if (flag) {// 如果是业务员，只能看到自己的拜访记录
			//entity.setAdd_user_id(user.getId());
		} else {

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				break;
			case 8:
				// 分公司及以下部门可见

				KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			case 7:
				// 我所在的部门及以下部门可见
				_dept_id = user.getDept_id(); // 默认为当前用户所在部门
				entity.getMap().put("dept_id_start", _dept_id);
				break;
			case 0:
				_dept_id = user.getDept_id(); // 默认为当前用户所在部门
				// entity.getMap().put("dept_id_start", __dept_id);
				entity.getMap().put("filter_by_ywy_id_eq", user.getId());
				break;
			default:
				// 出错
			}
			//entity.getMap().put("session_user_id", user.getId());

		}

		List<JcfxKczzKh> entityList = null;
			Long recordCount = super.getFacade().getJcfxKczzKhService()
			        .getJcfxKczzKhCount(entity);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());

			String filename = "待选R3用户";
			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
				if (recordCount.intValue() > 20000) {
					renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
					        + "！');history.back();");
					return null;
				}
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
				        + EncryptUtils.encodingFileName(filename) + ".xls");
				entity.getRow().setFirst(0);
				entity.getRow().setCount(recordCount.intValue());

				List<JcfxKczzKh> entityList1 = super.getFacade().getJcfxKczzKhService()
				        .getJcfxKczzKhPaginatedList(entity);
				request.setAttribute("allList", entityList1);
				Integer count = entityList1.size();
				if (count != null && count > 0) {
					return new ActionForward("/leader/JcfxKczzKh/listReport.jsp");
				} else {
					return null;
				}
			}
			entityList = super.getFacade().getJcfxKczzKhService()
			        .getJcfxKczzKhPaginatedList(entity);
			request.setAttribute("mod_id", mod_id);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		//setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		//super.getModPopeDom(form, request);

		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String keyword = (String) dynaBean.get("keyword");
		String code_like = (String) dynaBean.get("code_like");
		String is_match = (String) dynaBean.get("is_match");
		String dept_id = (String) dynaBean.get("dept_id");
		//String c_index = (String) dynaBean.get("c_index");
		String user_name_like = (String) dynaBean.get("user_name_like");
		String is_del = (String) dynaBean.get("is_del");
		String export = (String) dynaBean.get("export");
		String is_sdf = (String) dynaBean.get("is_sdf");
		
		String is_kf = (String) dynaBean.get("is_kf");
		
		//当从R3客户合并页面跳转时，包含合并编码,是否停用，是否送达方
		String customer_code = (String) dynaBean.get("customer_code");
		String merge_sdf = (String) dynaBean.get("merge_sdf");
		String merge_stop = (String) dynaBean.get("merge_stop");
		

		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		
		//新增查询条件 2014-09-25  LiangHouEn
		String cust_stat = (String) dynaBean.get("cust_stat");
		String merge_code = (String) dynaBean.get("merge_code");
		String branch_name = (String) dynaBean.get("branch_name");
		
		//一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");
		
		//二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");

		dynaBean.set("match", is_match);

		

		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		entity.setIs_del(0L);
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Long.valueOf(is_del));
		}
		
		//新增查询条件
		if (StringUtils.isNotBlank(cust_stat)) {
			entity.setShop_status(cust_stat);
		}
		if (StringUtils.isNotBlank(merge_code)) {
			entity.setCustomer_code(merge_code);
		}
		if (StringUtils.isNotBlank(branch_name)) {
			entity.setBranch_name(branch_name);
		}

		if (!GenericValidator.isLong(is_match)) {
			entity.getMap().put("is_assign", "true");
			entity.getMap().put("leftYWY", "true");
			entity.getMap().put("YWY", "true");
		} else {
			entity.getMap().put("is_assign", "true");
			entity.getMap().put("leftYWY", "true");
			entity.getMap().put("YWY", "true");
			entity.setIs_match(Long.valueOf(is_match));
		}

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("user_id", ui.getId());
		}
        if(StringUtils.isNotBlank(is_kf)){
        	entity.getMap().put("is_kf", is_kf);
        }
        if(StringUtils.isNotBlank(customer_code)){
        	entity.setCustomer_code(customer_code);
        	dynaBean.set("code_like", customer_code);
        }
        
        //添加客户类型筛选条件
        if(StringUtils.isNotBlank(customer_type2)){
        	entity.getMap().put("cus_type2", customer_type2);
        }else{
        	if(StringUtils.isNotBlank(customer_type1)){
        		entity.getMap().put("cus_type1", customer_type1);
        	}
        }
      		
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

		

		if (GenericValidator.isInt(is_sdf)) {
			entity.setIs_sdf(Integer.valueOf(is_sdf));
		} else {
			entity.setIs_sdf(0);
			dynaBean.set("is_sdf", 0);
		}
		if (StringUtils.isNotBlank(merge_sdf)) {
			entity.setIs_sdf(Integer.valueOf(merge_sdf));
			dynaBean.set("is_sdf", Integer.valueOf(merge_sdf));
		}
		if (StringUtils.isNotBlank(merge_stop)) {
			entity.setIs_del(Long.valueOf(merge_stop));
			dynaBean.set("is_del", Long.valueOf(merge_stop));
			request.setAttribute("from_merge", "true");
		}else{
			dynaBean.set("is_del", is_del);
			request.setAttribute("from_merge", "none");
		}
		

		//entity.setCustomer_type(c_index);
		//entity.getMap().put("c_index", c_index);
		entity.getMap().put("user_name_like", user_name_like); // 业务员模糊查询字段
		entity.getMap().put("keyword", keyword);
		entity.getMap().put("code_like", code_like);
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
		}

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopForYwyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopForYwyPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(export)) {
			//添加查询客户端帐户的子查询语句
			entity.getMap().put("account", true);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("R3客户")
			        + ".xls");
			if(StringUtils.isNotBlank(is_kf)){
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("非R3客户")
			        + ".xls");
			}
			if (recordCount > 5000) {
				super.renderJavaScript(response, "window.onload=function(){alert('只能导出5000条以下数据！');history.back();}");
			}
			entity.getRow().setCount(recordCount.intValue());
			dynaBean.set("export", export);
			List<KonkaR3Shop> entityList1 = getFacade().getKonkaR3ShopService().exportData(entity);
			
			//取出最大联系人数并传递到前台
			int max = (Integer)entityList1.get(entityList1.size()-1).getMap().get("MaxNum");
			request.setAttribute("MaxNum", max);
			entityList1.remove(entityList1.size()-1);
			
			request.setAttribute("entityList1", entityList1);
			return mapping.findForward("view");
		}

		// 分公司
		KonkaDept kd = new KonkaDept();
		KonkaDept kf = new KonkaDept();
		KonkaDept sf = new KonkaDept();
		KonkaDept dfd = new KonkaDept();
		kf.setDept_sn("KF001");
		kf.setDept_type(3);
		kf = super.getFacade().getKonkaDeptService().getKonkaDept(kf);
		sf.setDept_sn("KFSF001");
		sf.setDept_type(3);
		sf = super.getFacade().getKonkaDeptService().getKonkaDept(sf);
		dfd.setDept_sn("KFDFD001");
		dfd.setDept_type(3);
		dfd = super.getFacade().getKonkaDeptService().getKonkaDept(dfd);
		List<KonkaDept> sybDeptInfoList=new ArrayList<KonkaDept>();
		if (max_dlevel == 9) {
			kd.setDept_id(0L);
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			entity.getMap().put("dept_name", kd.getDept_name());
		    if(StringUtils.isNotBlank(is_kf)){
		    	sybDeptInfoList.add(kf);
		    	sybDeptInfoList.add(sf);
		    	sybDeptInfoList.add(dfd);
		    }else{
		    	sybDeptInfoList= super.getDeptInfoListWithOutLimit(mapping, form, request, response);	
		    	List<KonkaDept> removelist=new ArrayList<KonkaDept>();
		    	if(null!=sybDeptInfoList &&sybDeptInfoList.size()>0){
		    		for(KonkaDept dp:sybDeptInfoList){
		    			if("KF001".equals(dp.getDept_sn())||"KFSF001".equals(dp.getDept_sn())||"KFDFD001".equals(dp.getDept_sn())){
		    				removelist.add(dp);
		    			}
		    		}
		    	}
		    	sybDeptInfoList.removeAll(removelist);
		    }	
		} else {
			kd.setDept_id(ui.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			entity.getMap().put("dept_name", kd.getDept_name());
			sybDeptInfoList= super.getDeptInfoListWithOutLimit(mapping, form, request, response);	
		}
		
		
		
		request.setAttribute("sybDeptInfoList",sybDeptInfoList);



        return new ActionForward("/../manager/leader/JcfxKczzKh/from.jsp");
	}
	
    /**
     * 异步保存单用户
     */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		/*if (!super.isTokenValid(request, true)) {
			super.saveMessage(request, "errors.token");
			return mapping.findForward("list");
		}
		resetToken(request);*/
		DynaBean dynaBean = (DynaBean) form;
		//setNaviStringToRequestScope(form, request);
		super.saveToken(request);
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}
		String cust_id = (String) dynaBean.get("cust_id");// 拜访类型
		String r3_code = (String) dynaBean.get("r3_code");// 拜访类型
		String coustmer_name = (String) dynaBean.get("coustmer_name");// 拜访类型
		JcfxKczzKh entity = new JcfxKczzKh();
		if (StringUtils.isNotBlank(cust_id)) {
			entity.setCust_id(Long.valueOf(cust_id));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(coustmer_name)) {
			entity.setCoustmer_name(coustmer_name);
		}
		entity.setAdd_date(new Date());
		if (null!=userInfo.getId()) {
			entity.setAdd_user_id(userInfo.getId());
		}
		if (null!=userInfo.getUser_name()) {
			entity.setAdd_user_name(userInfo.getUser_name());
		}
		entity.setIs_del(0);
		
		JcfxKczzKh jcfxKczzKh=new JcfxKczzKh();
		if (StringUtils.isNotBlank(cust_id)) {
			jcfxKczzKh.setCust_id(Long.valueOf(cust_id));
		}
		if (StringUtils.isNotBlank(r3_code)) {
			jcfxKczzKh.setR3_code(r3_code);
		}
		
		if (StringUtils.isNotBlank(cust_id)&&StringUtils.isNotBlank(r3_code)) {
			jcfxKczzKh=super.getFacade().getJcfxKczzKhService().getJcfxKczzKh(jcfxKczzKh);
		}
		if (null!=jcfxKczzKh) {
		      allmap.put("res",coustmer_name+"客户已在添加列表");	
		}else{
			Long id =super.getFacade().getJcfxKczzKhService().createJcfxKczzKh(entity);
			if (StringUtils.isNotBlank(""+id)) {
				allmap.put("id", id);
				allmap.put("res", coustmer_name+"客户添加成功！");
			}else{
				allmap.put("res", coustmer_name+"客户添加失败！");
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "visit_id");
			return this.list(mapping, form, request, response);
		}
		
		//查找分组客户里面对应的客户信息
		JcfxKczzKhfzLink linkEntity=new JcfxKczzKhfzLink();
		linkEntity.setKh_id(Long.valueOf(id));
		Long  linkIds[]={};
		List<JcfxKczzKhfzLink> linkList= super.getFacade().getJcfxKczzKhfzLinkService().getJcfxKczzKhfzLinkList(linkEntity);
		for (int j = 0; j < linkList.size(); j++) {
			linkIds[j]=linkList.get(j).getId();
		}
		
		// 删除分组客户里面对应的客户信息
		linkEntity.getMap().put("pks", linkIds);
		   super.getFacade().getJcfxKczzKhfzLinkService().removeJcfxKczzKhfzLink(linkEntity);
			// 删除主表信息
			JcfxKczzKh jcfxKczzKh = new JcfxKczzKh();
			jcfxKczzKh.setId(Long.valueOf(id));
			Integer number = super.getFacade().getJcfxKczzKhService()
					.removeJcfxKczzKh(jcfxKczzKh);
			if (number == 1) {
				allmap.put("res", "删除成功！");
			} else {
				allmap.put("res", "删除失败！");
			}
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	
	/**
	 * 财务部库存周转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcfxCwbkczzlList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fz_id = (String) dynaBean.get("fz_id");//分组客户
		String excel_all = (String) dynaBean.get("excel_all");
		SimpleDateFormat formaty1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		String group_by_field = (String) dynaBean.get("group_by_field1");
		String select_by_field = (String) dynaBean.get("select_by_field1");
		//重点客户使用查询条件
		String c_index = (String) dynaBean.get("c_index");
		//分公司重点客户查询条件
		String dept_sn = (String) dynaBean.get("dept_sn");
		String is_cur_month="yes";//不为空表示是单月   // 为空表示不是当月
		if(group_by_field!=null && group_by_field.equals("groupbycustomer")){
			group_by_field = "data.R3_CODE,data.CUSTOMER_NAME";
			dynaBean.set("groupbyfield", "groupbycustomer");
		}
		if(select_by_field!=null && select_by_field.equals("groupbycustomer")){
			select_by_field = "data.R3_CODE AS C_INDEX, data.CUSTOMER_NAME AS C_NAME";
			dynaBean.set("selectbyfield", "groupbycustomer");
		}	
			
			
		Date date= new Date(); //当月使用
		
		Calendar uptime = Calendar.getInstance();//上月使用
		uptime.add(Calendar.MONTH, -1);
		
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		

		
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		JcfxKczzKh entity = new JcfxKczzKh();
		
		request.setAttribute("level", "1");
		if(StringUtils.isNotBlank(c_index)){
			entity.getMap().put("c_index", c_index);
			request.setAttribute("c_index", ""+c_index);
			request.setAttribute("level", "2");
		}

		
		if(StringUtils.isNotBlank(dept_sn)){
			entity.getMap().put("dept_sn", dept_sn);
			request.setAttribute("dept_sn", ""+dept_sn);
			request.setAttribute("level", "2");
		}
		
		if(StringUtils.isNotBlank(select_by_field)){		
			entity.getMap().put("select_by_field", select_by_field);
		}
		
		if(StringUtils.isNotBlank(group_by_field)){		
			entity.getMap().put("group_field_name", group_by_field);
		}
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
			
			if ((year+"-"+month).equals(formaty2.format(date))) {
				is_cur_month="yes";
				entity.getMap().put("is_cur_month", is_cur_month);
				dynaBean.set("type", "0");
			}else {
				is_cur_month=null;
				dynaBean.set("type", "1");
			}
			//计算周转率参数
			Integer curmaxdaytmp=day(Integer.valueOf(year),Integer.valueOf(month));
			String curmaxday=curmaxdaytmp>9?""+curmaxdaytmp:"0"+curmaxdaytmp;
			entity.getMap().put("curday", year+"-"+month+"-"+curmaxday);
			entity.getMap().put("firestday", year+"-"+month+"-"+"01");
			
			request.setAttribute("curday", year+"-"+month+"-"+curmaxday);
			request.setAttribute("firestday", year+"-"+month+"-"+"01");
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
			
			is_cur_month="yes";
			entity.getMap().put("is_cur_month", is_cur_month);
			
			 //计算周转率参数
			entity.getMap().put("curday", formaty1.format(date));
			entity.getMap().put("firestday", formaty2.format(date)+"-"+"01");
			
			request.setAttribute("curday", formaty1.format(date));
			request.setAttribute("firestday", formaty2.format(date)+"-"+"01");
			dynaBean.set("type", "0");
		}
		//上月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			String upyear_month[]=formaty2.format(uptime.getTime()).split("-");
			entity.getMap().put("up_month",getLastDate(upyear_month[0]+upyear_month[1]));
			
			 //计算周转率参数
			String upYearMonth=getLastDate(upyear_month[0]+upyear_month[1]);
			String upYear=upYearMonth.substring(0, 4);
			String upMonth=upYearMonth.substring(4, 6);
			Integer curmaxdaytmp=day(Integer.valueOf(upYear),Integer.valueOf(upMonth));
			String curmaxday=curmaxdaytmp>9?""+curmaxdaytmp:"0"+curmaxdaytmp;
			entity.getMap().put("upday", upYear+"-"+upMonth+"-"+curmaxday);
			entity.getMap().put("upfirestday",  upYear+"-"+upMonth+"-"+"01");
		}else {
			String up_year_month[]=formaty2.format(uptime.getTime()).split("-");
			entity.getMap().put("up_month",getLastDate(up_year_month[0]+up_year_month[1]));
			
			 //计算周转率参数
			entity.getMap().put("upday", formaty1.format(uptime.getTime()));
			entity.getMap().put("upfirestday", formaty2.format(uptime.getTime())+"-"+"01");
		}
		
		if(StringUtils.isNotBlank(fz_id)){
			entity.getMap().put("fz_id",fz_id);
			dynaBean.set("fz_id", fz_id);
		}
       //计算周转率参数

		
		
		

		List<Map<String, String>> entityList = null;

		entity.getRow().setFirst(0);
		entity.getRow().setCount(Integer.MAX_VALUE);
			String filename = year+"年"+month+"月份分渠道汇总连锁渠道周转情况";
			// 导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
				        + EncryptUtils.encodingFileName(filename) + ".xls");
				entity.getRow().setFirst(0);
				entity.getRow().setCount(Integer.MAX_VALUE);

				List<Map<String, String>> entityList1 = super.getFacade().getJcfxKczzKhService()
				        .getJcfxCwbkczzlPaginatedList(entity);
				request.setAttribute("allList", entityList1);
				Integer count = entityList1.size();
				if (count != null && count > 0) {
					return new ActionForward("/leader/JcfxKczzKh/jcfxCwbkczzlList.jsp");
				} else {
					return null;
				}
			}
			entityList = super.getFacade().getJcfxKczzKhService().getJcfxCwbkczzlPaginatedList(entity);
			request.setAttribute("mod_id", mod_id);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		
		
		String is_bi = (String) dynaBean.get("is_bi");
		if(StringUtils.isNotBlank(is_bi)){
			request.setAttribute("is_bi",is_bi);
		}
		/**
		 * 遍历年份，取前10年
		 */
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);
		/**
		 * 初始化分组查询条件
		 */
		JcfxKczzKhfz fz=new JcfxKczzKhfz();
		List<JcfxKczzKhfz>fzlist= super.getFacade().getJcfxKczzKhfzService().getJcfxKczzKhfzList(fz);
		request.setAttribute("fzlist", fzlist);
		return new ActionForward("/leader/JcfxKczzKh/JcfxCwbkczzlList.jsp");
	}
	
	/**
	 *  全国连锁渠道周转情况分公司排名汇总表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcfxQglsqdzzqkfgspmList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String fz_id = (String) dynaBean.get("fz_id");//分组客户
		String excel_all = (String) dynaBean.get("excel_all");
		SimpleDateFormat formaty1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		String is_cur_month="yes";//不为空表示是单月   // 为空表示不是当月
		Date date= new Date();
		Calendar uptime = Calendar.getInstance();//上月使用
		uptime.add(Calendar.MONTH, -1);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		boolean flag = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
		}
		JcfxKczzKh entity = new JcfxKczzKh();
		
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
			
			//计算周转率参数
			Integer curmaxdaytmp=day(Integer.valueOf(year),Integer.valueOf(month));
			String curmaxday=curmaxdaytmp>9?""+curmaxdaytmp:"0"+curmaxdaytmp;
			entity.getMap().put("curday", year+"-"+month+"-"+curmaxday);
			entity.getMap().put("firestday", year+"-"+month+"-"+"01");
			
			
			request.setAttribute("curday", year+"-"+month+"-"+curmaxday);
			request.setAttribute("firestday", year+"-"+month+"-"+"01");
			
			if ((year+"-"+month).equals(formaty2.format(date))) {
				is_cur_month="yes";
				entity.getMap().put("is_cur_month", is_cur_month);
				dynaBean.set("type", "0");
			}else {
				is_cur_month=null;
				dynaBean.set("type", "1");
			}
			
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
			
			
			 //计算周转率参数
			entity.getMap().put("curday", formaty1.format(date));
			entity.getMap().put("firestday", formaty2.format(date)+"-"+"01");
			
			request.setAttribute("curday", formaty1.format(date));
			request.setAttribute("firestday", formaty2.format(date)+"-"+"01");
			
			is_cur_month="yes";
			entity.getMap().put("is_cur_month", is_cur_month);
			dynaBean.set("type", "0");
		}
		//分组id
		if(StringUtils.isNotBlank(fz_id)){
			entity.getMap().put("fz_id",fz_id);
			dynaBean.set("fz_id", fz_id);
		}


		List<Map<String, String>> entityList = null;

		entity.getRow().setFirst(0);
		entity.getRow().setCount(Integer.MAX_VALUE);
		String filename = year+"年"+month+"月份分渠道汇总连锁渠道周转情况";
		// 导出
		if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {
			/*if (recordCount.intValue() > 20000) {
				renderJavaScript(response, "alert('" + super.getMessage(request, "export.too.many")
				        + "！');history.back();");
				return null;
			}*/
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
			        + EncryptUtils.encodingFileName(filename) + ".xls");
			entity.getRow().setFirst(0);
			entity.getRow().setCount(Integer.MAX_VALUE);

			List<Map<String, String>> entityList1 = super.getFacade().getJcfxKczzKhService()
			        .getJcfxQglsqdzzqkfgspmPaginatedList(entity);
			request.setAttribute("allList", entityList1);
			Integer count = entityList1.size();
			if (count != null && count > 0) {
				return new ActionForward("/leader/JcfxKczzKh/qglsqdzzqkfgspmList.jsp");
			} else {
				return null;
			}
		}
		entityList = super.getFacade().getJcfxKczzKhService().getJcfxQglsqdzzqkfgspmPaginatedList(entity);
			request.setAttribute("mod_id", mod_id);
		request.setAttribute("mod_id", mod_id);
		request.setAttribute("entityList", entityList);
		
		String is_bi = (String) dynaBean.get("is_bi");
		if(StringUtils.isNotBlank(is_bi)){
			request.setAttribute("is_bi",is_bi);
		}
		
		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();
		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);
		/**
		 * 初始化分组查询条件
		 */
		JcfxKczzKhfz fz=new JcfxKczzKhfz();
		List<JcfxKczzKhfz>fzlist= super.getFacade().getJcfxKczzKhfzService().getJcfxKczzKhfzList(fz);
		request.setAttribute("fzlist", fzlist);
		return new ActionForward("/leader/JcfxKczzKh/qglsqdzzqkfgspmList.jsp");
	}
	
    /**
     * 客户库存详情报表 金额由现款价计算出来
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward jcfxzdkhList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String handle_name_like = (String) dynaBean.get("handle_name_like");// 经办名称
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String goods_name_like = (String) dynaBean.get("goods_name_like");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String excel_to_all = (String) dynaBean.get("excel_to_all");
		String type = (String) dynaBean.get("type");// 0:客户库存汇总 1:月度库存
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String cur_min_num = (String) dynaBean.get("cur_min_num");
		String cur_max_num = (String) dynaBean.get("cur_max_num");
		// 一级客户类型
		String customer_type1 = (String) dynaBean.get("v_customer_type1");

		String is_cur_month="yes";//不为空表示是单月   // 为空表示不是当月
		String is_allow_back = (String) dynaBean.get("is_allow_back");
		
		request.setAttribute("is_allow_back", is_allow_back);
		
		// 二级客户类型
		String customer_type2 = (String) dynaBean.get("v_customer_type2");

		JStocksSummary entity = new JStocksSummary();

		if (StringUtils.isNotBlank(type)) {
			dynaBean.set("type", type);
		} 

		if (StringUtils.isNotBlank(customer_name_like))
			entity.getMap().put("customer_name_like", customer_name_like.trim());

		if (StringUtils.isNotBlank(r3_code_like))
			entity.setR3_code(r3_code_like);
		if (StringUtils.isNotBlank(cur_min_num)) {
			entity.getMap().put("cur_min_num", Integer.valueOf(cur_min_num));
			dynaBean.set("cur_min_num", cur_min_num);
		}
		if (StringUtils.isNotBlank(cur_max_num)) {
			entity.getMap().put("cur_max_num", Integer.valueOf(cur_max_num));
			dynaBean.set("cur_max_num", cur_max_num);
		}
		// 添加客户类型筛选条件
		if (StringUtils.isNotBlank(customer_type2)) {
			entity.getMap().put("cus_type2", customer_type2);
			dynaBean.set("customer_type2", customer_type2);
		} else {
			if (StringUtils.isNotBlank(customer_type1)) {
				entity.getMap().put("cus_type1", customer_type1);
				dynaBean.set("customer_type1", customer_type1);
			}
		}
		if (StringUtils.isNotBlank(goods_name_like))
			entity.getMap().put("goods_name_like", goods_name_like.trim());

		if (StringUtils.isNotBlank(dept_sn))
			entity.getMap().put("dept_sn", dept_sn);

		if (StringUtils.isNotBlank(handle_name_like)) {
			entity.getMap().put("handle_name_like_1", handle_name_like.trim());
			dynaBean.set("handle_name_like_1", handle_name_like.trim());
		}

		SimpleDateFormat formaty = new SimpleDateFormat("yyyy");
		SimpleDateFormat formaty2 = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		//当月
		if (StringUtils.isNotBlank(year)&&StringUtils.isNotBlank(month)) {
			entity.getMap().put("cur_month", year+month);
			dynaBean.set("year", year);
			dynaBean.set("month", month);
			
			if ((year+"-"+month).equals(formaty2.format(date))) {
				is_cur_month="yes";
				entity.getMap().put("is_cur_month", is_cur_month);
			}else {
				is_cur_month=null;
			}
		}else {
			String year_month[]=formaty2.format(date).split("-");
			entity.getMap().put("cur_month", year_month[0]+year_month[1]);
			dynaBean.set("year", year_month[0]);
			dynaBean.set("month", year_month[1]);
			
			is_cur_month="yes";
			entity.getMap().put("is_cur_month", is_cur_month);
		}

		entity.getRow().setFirst(0);
		entity.getRow().setCount(Integer.MAX_VALUE);

		if (StringUtils.isNotBlank(excel_to_all)) {
			
			//为“总部财务部”角色开放导出记录数限制
			PeRoleUser roleUser = new PeRoleUser();
			roleUser.setUser_id(ui.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
			
			boolean is_exit = true;
			for(PeRoleUser t : roleUserList){
				if(t.getRole_id()==29){
					is_exit = false;
					break;
				}
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("客户库存数据")
					+ ".xls");

			List<JStocksSummary> entityList1 = getFacade().getJStocksSummaryService().getjcfxzdkhListList(
					entity);
			request.setAttribute("allList", entityList1);
			return mapping.findForward("view");
		}
		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		List<JStocksSummary> entityList = super.getFacade().getJStocksSummaryService()
				.getjcfxzdkhListList(entity);
		
		Object totlePageNum = super.getFacade().getJStocksSummaryService().getJStocksSummaryForR3ShopSumCount(entity);

		if (null != totlePageNum) {
			dynaBean.set("totlePageNum", totlePageNum);
		} else {
			dynaBean.set("totlePageNum", "0");
		}
		if (entityList != null && entityList.size() > 0) {
			JStocksSummary firestEntity= entityList.get(0);
			if (firestEntity != null ) {
				if (null!=firestEntity.getAdd_date()) {
					request.setAttribute("add_date", firestEntity.getAdd_date());
				}
				StringBuffer custmsg= new StringBuffer("<table border='0' width='100%' style='color:red'>");
				if (null!=firestEntity.getMap().get("dept_name")) {
					custmsg.append("<tr><td align='right'>分公司名称：</td><td>"+firestEntity.getMap().get("dept_name")+"</td>");
				}else{
					custmsg.append("<tr><td align='right'>分公司名称：</td><td>暂无</td>");
				}
				if (null!=firestEntity.getR3_code()) {
					custmsg.append("<td align='right'>客户编码：</td><td>"+firestEntity.getR3_code()+"</td>");
				}else{
					custmsg.append("<td align='right'>客户编码：</td><td>暂无</td>");
				}
				if (null!=firestEntity.getCustomer_name()) {
					custmsg.append("<td align='right'>客户名称：</td><td>"+firestEntity.getCustomer_name()+"</td></tr>");
				}else{
					custmsg.append("<td align='right'>客户名称：</td><td>暂无</td></tr>");
				}
				if (null!=firestEntity.getMap().get("par_cust_type_name")) {
					custmsg.append("<tr><td align='right'>客户类型：</td><td>"+firestEntity.getMap().get("par_cust_type_name")+"</td>");
				}else{
					custmsg.append("<tr><td align='right'>客户类型：</td><td>暂无</td>");
				}
				if (null!=firestEntity.getMap().get("cust_type_name")) {
					custmsg.append("<td align='right'>细分类型：</td><td>"+firestEntity.getMap().get("cust_type_name")+"</td>");
				}else{
					custmsg.append("<td align='right'>细分类型：</td><td>暂无</td>");
				}
				if (null!=firestEntity.getMap().get("handle_name")) {
					custmsg.append("<td align='right'>经办名称：</td><td>"+firestEntity.getMap().get("handle_name")+"</td></tr></table>");
				}else{
					custmsg.append("<td align='right'>经办名称：</td><td>"+firestEntity.getMap().get("handle_name")+"</td></tr></table>");
				}
				request.setAttribute("custmsg", custmsg);
			}
		}
		request.setAttribute("entityList", entityList);
		// 遍历年份，取前10年
		ArrayList<String> yearList = new ArrayList<String>();

		for (int y = 0; y < 10; y++) {
			yearList.add((Integer.valueOf(formaty.format(date)) - y) + "");
		}
		request.setAttribute("yearList", yearList);
		
		
		List<KonkaDept> konkaDeptList = super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("konkaDeptList", konkaDeptList);
		return new ActionForward("/leader/JcfxKczzKh/jcfxzdkhList.jsp");
	}
	/**
	 * 取的年月的最大天数
	 * @param year
	 * @param month
	 * @return
	 */
	private static int day(int year, int month) {
		int maxDay = 0;
		int day = 1;
		/**
		 * 与其他语言环境敏感类一样，Calendar 提供了一个类方法 getInstance， 以获得此类型的一个通用的对象。Calendar 的
		 * getInstance 方法返回一 个 Calendar 对象，其日历字段已由当前日期和时间初始化：
		 */
		Calendar calendar = Calendar.getInstance();
		/**
		 * 实例化日历各个字段,这里的day为实例化使用
		 */
		calendar.set(year, month - 1, day);
		/**
		 * Calendar.Date:表示一个月中的某天 calendar.getActualMaximum(int
		 * field):返回指定日历字段可能拥有的最大值
		 */
		maxDay = calendar.getActualMaximum(Calendar.DATE);
		return maxDay;
	}
	/**
	 * yyyy-MM 当月
	 * @param datestr
	 * @return 上月
	 */
	private static String getLastDate(String datestr){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date date;
		Calendar cal=Calendar.getInstance();
		try {
			date = df.parse(datestr);
			cal.setTime(date);
			cal.add(Calendar.MONTH,0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(cal.getTime());
	}
	@SuppressWarnings("unused")
	private List<KonkaR3Shop> getcust(PeProdUser userInfo, Integer report_type) {
		KonkaR3Shop entity = new KonkaR3Shop();
		if (null != report_type) {
			if (report_type == 1) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type == 2) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		}
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else

		{

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				// break;
				return null;
			case 8:
				// 分公司及以下部门可见
				if (!fgsgly) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				} else {
					return null;
				}
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
		}
		entity.setIs_del(0L);// 未被停用的
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);

		return entityList;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Store> getShop(PeProdUser userInfo, Integer report_type) {
		KonkaR3Store entity = new KonkaR3Store();
		if (null != report_type) {
			if (report_type == 1) {

				entity.getMap().put("cust_type", new String[] { "1", "2" });
			}
			if (report_type == 2) {
				entity.getMap().put("cust_type", new String[] { "3", "4" });
			}
		}
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else
		{

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				// break;
				return null;
			case 8:
				// 分公司及以下部门可见
				if (!fgsgly) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				} else {
					return null;
				}
				// return null;
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
		}
		entity.setIs_del(0);// 未被停用的
		List<KonkaR3Store> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreForCustVisit(entity);

		return entityList;
	}
}
