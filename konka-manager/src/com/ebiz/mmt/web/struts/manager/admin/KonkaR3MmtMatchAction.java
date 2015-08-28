package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaAuditListInfo;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobilePdBrand;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopBrand;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.domain.KonkaSalesDept;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.mmt.r3.KNA1;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 2014/01/07
 * 
 * KonkaR3MmtMatch 的出现是第一版进销存的需要.现在已经改版<br>
 * 
 * 现在的逻辑 konka_r3_shop数据从sap进来,直接进行业务员分配branch_assign<br>
 * 
 * konka_r3_shop再与客户门店绑定,门店与促销员绑定
 * 
 * 
 * 虽是KonkaR3MmtMatchAction,但实际操作是konkar3shop的数据
 * 
 * @author zhou
 * 
 */
public class KonkaR3MmtMatchAction extends BaseAction {

	// private static HashMap<String, String> properties = new HashMap<String,
	// String>();
	// static {
	// InputStream inputStream =
	// InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(
	// "webservice-url.properties");
	// Properties p = new Properties();
	// try {
	// p.load(inputStream);
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// for (Object key : p.keySet()) {
	// properties.put((String) key, (String) p.get(key));
	// }
	// }

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	/**
	 * @author pan,gang
	 * @date 2013-7-5
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//判断session是否超时
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		//用户角色信息
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		
		request.setAttribute("user_role", "1");
		for(PeRoleUser t : roleUserList){
			if(t.getRole_id() == 30){//分公司管理员
				request.setAttribute("user_role", "0");
				break;
			}
		}
		
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);

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
		String is_sure = (String) dynaBean.get("is_sure");
		
		//当从R3客户合并页面跳转时，包含合并编码,是否停用，是否送达方
		String customer_code = (String) dynaBean.get("customer_code");

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

		// 添加权限
		// PeProdUser userInfo = (PeProdUser)
		// request.getSession().getAttribute(Constants.USER_INFO);

		// 获取登陆用户角色
		// PeRoleUser _peRoleUser = new PeRoleUser();
		// _peRoleUser.setUser_id(userInfo.getId());
		// List<PeRoleUser> peRoleUserList =
		// this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		// boolean role_id_le_29 = false; // 是否是管理人员
		// boolean role_id_ge_30_le_59 = false; // 是否是分公司人员
		// boolean role_id_30_or_34 = false; // 分公司管理员或者分公司总经理
		// boolean role_id_ge_60 = false; // 是否是业务员
		//
		// for (PeRoleUser peRoleUser : peRoleUserList) {
		// if (peRoleUser.getRole_id() <= 29)
		// role_id_le_29 = true;
		// if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 59)
		// role_id_ge_30_le_59 = true;
		// if (peRoleUser.getRole_id() == 60)
		// role_id_ge_60 = true;
		// if (peRoleUser.getRole_id() == 30 || peRoleUser.getRole_id() == 34)
		// role_id_30_or_34 = true;
		// }

		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		
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
        if(StringUtils.isNotBlank(is_sure)){
        	entity.getMap().put("is_sure", is_sure);
        	if("1".equals(is_sure)){
        		entity.getMap().put("is_sure1", is_sure);
        	}
        	if("2".equals(is_sure)){
        		entity.getMap().put("is_sure2", is_sure);
        	}
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

		// if (role_id_le_29) {// 若登陆用户属于管理员
		// if (StringUtils.isNotEmpty(dept_id)) {
		// KonkaDept _dept = super.getSuperiorForDeptType(Long.valueOf(dept_id),
		// 3);
		// if (null != _dept) {
		// entity.setBranch_area_name_2(_dept.getDept_sn());
		// }
		// }
		// } else if (role_id_ge_30_le_59) {// 若登陆用户属于分公司
		// if (role_id_30_or_34) {
		// KonkaDept _dept =
		// super.getSuperiorForDeptType(Long.valueOf(ui.getDept_id()), 3);
		// if (null != _dept)
		// entity.setBranch_area_name_2(_dept.getDept_sn());
		// } else {
		// entity.getMap().put("is_fgs_user_id", ui.getId());
		// if (ui.getDept_id() != null)
		// entity.getMap().put("is_fgs_dept_id", ui.getDept_id());
		// }
		//
		// } else if (role_id_ge_60) {// 若登陆用户属于业务员
		// entity.getMap().put("ywy_user_id", userInfo.getId());
		// } else {
		// super.renderHtml(response, "无权访问，无系统权限，请联系管理员授予系统角色。");
		// return null;
		// }

//		else {
//			entity.setIs_sdf(0);
//			dynaBean.set("is_sdf", 0);
//		}
		if (StringUtils.isNotBlank(customer_code)) {
			request.setAttribute("from_merge", "true");
		}else{
			request.setAttribute("from_merge", "none");
			if (StringUtils.isNotBlank(is_del)) {
				entity.setIs_del(Long.valueOf(is_del));
			}else{
				if(null==is_del){
					entity.setIs_del(0L);
					dynaBean.set("is_del", 0);
				}
			}
			if(StringUtils.isNotBlank(is_sdf)){
				entity.setIs_sdf(Integer.valueOf(is_sdf));
			}else{
				if(null==is_sdf){
					entity.setIs_sdf(0);
					dynaBean.set("is_sdf", 0);
				}
			}
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

		// 网点类型
//		KonkaCategory kc = new KonkaCategory();
//		kc.setC_type(10);
//		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		if(StringUtils.isNotBlank(is_kf)){
			return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/list_kf.jsp"));
		}
		return mapping.findForward("list");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		
		//从R3客户合并跳转标识
		String merge_flag = (String) dynaBean.get("merge_flag");
		
		// String key = (String) dynaBean.get("key");
		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		if (StringUtils.isNotBlank(merge_flag)) {
			request.setAttribute("is_merge_back", "");
			request.setAttribute("not_merge_back", "none");
		}else{
			request.setAttribute("is_merge_back", "none");
			request.setAttribute("not_merge_back", "");
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		// entity.getMap().put("is_assign", 1);
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);

		// 当月结算,当月回款
		KonkaR3Shop temp = new KonkaR3Shop();
		temp.setR3_code(entity.getR3_code());
		temp = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopSimpleStat(temp);
		entity.getMap().put("stat", temp);

		super.copyProperties(form, entity);

		//联系人信息
		KonkaR3ShopLink kr = new KonkaR3ShopLink();
		kr.setR3_shop_id(Long.valueOf(id));
		kr.setIs_del(0);
		request.setAttribute("konkaR3ShopLinkList", super.getFacade().getKonkaR3ShopLinkService()
		        .getKonkaR3ShopLinkList(kr));
		
		//品牌销售信息
		KonkaR3ShopBrand krsb = new KonkaR3ShopBrand();
		krsb.setR3_shop_id(Long.valueOf(id));
		krsb.setIs_del(0);
		request.setAttribute("konkaR3ShopBrandList", super.getFacade().getKonkaR3ShopBrandService()
				.getKonkaR3ShopBrandList(krsb));

		if (StringUtils.isNotBlank(entity.getCustomer_type())) {
			// 客户类型
			KonkaCategory kc = new KonkaCategory();
			kc.setC_type(10);
			kc.setIs_del(0);
			kc.setC_index(new Long(entity.getCustomer_type()));
			List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
			        .getKonkaCategoryList(kc);
			if (null != konkaCategoryList && konkaCategoryList.size() > 0) {
				kc = konkaCategoryList.get(0);
				request.setAttribute("customer_type_name", "[" + kc.getC_comm() + "]" + kc.getC_name());
			}

		}

		if (entity.getEntp_type() != null) {
			// 企业类型
			KonkaCategory kc = new KonkaCategory();
			kc = new KonkaCategory();
			kc.setC_type(12);
			kc.setIs_del(0);
			kc.setC_index(entity.getEntp_type());
			List<KonkaCategory> entpTypeList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
			if (null != entpTypeList && entpTypeList.size() > 0) {
				kc = entpTypeList.get(0);
				request.setAttribute("entp_type_name", kc.getC_name());
			}
		}

		if (StringUtils.isNotBlank(entity.getEntp_scale())) {
			// 客户规模（年销售额）
			KonkaCategory kc = new KonkaCategory();
			kc.setC_type(13);
			kc.setIs_del(0);
			kc.setC_index(new Long(entity.getEntp_scale()));
			List<KonkaCategory> entpScaleList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
			if (null != entpScaleList && entpScaleList.size() > 0) {
				kc = entpScaleList.get(0);
				request.setAttribute("entp_scale_name", kc.getC_name());
			}
		}

		if (entity.getEntp_p_index() != null && String.valueOf(entity.getEntp_p_index()).length() >= 6) {
			// 省/直辖市/自治区
			BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
			baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index()).substring(0, 2) + "0000"));
			baseProvinceFour.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
			        .getBaseProvinceListFourList(baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				request.setAttribute("province", b.getP_name());
			}
			// 市
			baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index()).substring(0, 4) + "00"));
			baseProvinceFourList = null;
			baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(
			        baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				request.setAttribute("city", b.getP_name());
			}
			// 县
			baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index()).substring(0, 6)));
			baseProvinceFourList = null;
			baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(
			        baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				request.setAttribute("country", b.getP_name());
			}
			// 乡镇
			if(String.valueOf(entity.getEntp_p_index()).length()>6){
				baseProvinceFour.setP_index(new Long(String.valueOf(entity.getEntp_p_index())));
				baseProvinceFourList = null;
				baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(
				        baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					request.setAttribute("town", b.getP_name());
				}
			}else{
				request.setAttribute("town", "");
			}
			
		}

		// 客户分配信息
		if (entity.getIs_match() == 1) {
			BranchAssign ba = new BranchAssign();
			ba.setKonka_r3_id(Long.valueOf(id));
			List<BranchAssign> baList = super.getFacade().getBranchAssignService().getBranchAssignList(ba);
			if (baList.size() > 0) {
				KonkaDept dept = new KonkaDept();
				if (baList.get(0).getJyb_id() != null) {
					dept.setDept_id(baList.get(0).getJyb_id());
					dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
					if (dept != null) {
						request.setAttribute("jyb_name", dept.getDept_name());
					}
				}

				if (baList.get(0).getBsc_id() != null) {
					dept.setDept_id(baList.get(0).getBsc_id());
					dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
					if (dept != null) {
						request.setAttribute("bsc_name", dept.getDept_name());
					}
				}
 
				if (baList.get(0).getUser_id() != null) {
					PeProdUser ppu = new PeProdUser();
					ppu.setId(baList.get(0).getUser_id());
					ppu = super.getFacade().getPeProdUserService().getPeProdUser(ppu);
					if (ppu != null) {
						request.setAttribute("ywy_name", ppu.getReal_name());
					}
				}

			}
		} else {
			request.setAttribute("is_not_match", true);
		}

		KonkaAuditListInfo kali = new KonkaAuditListInfo();
		kali.setLink_id(Long.parseLong(id));
		kali.setLink_type("r3_shop");
		
		List<KonkaAuditListInfo> entityList = null;
		entityList = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfoList(kali);
		request.setAttribute("history", entityList);

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("KONKA_R3_SHOP");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/detail.jsp"));
	}

	public ActionForward toMatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "str_pks", "method"));
		super.copyProperties(form, entity);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/toMatch.jsp"));
	}

	public ActionForward Match(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String r3_shop_id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String mmt_shop_id = (String) dynaBean.get("mmt_shop_id");
		String mmt_shop_name = (String) dynaBean.get("mmt_shop_name");
		super.getFacade().getKonkaR3MmtMatchService().match(r3_shop_id, mmt_shop_id, mmt_shop_name);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward addBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/addBatch.jsp"));
	}

	public ActionForward saveBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		int x = 0, y = 0, z = 0;
		String str = new String();
		PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		List<UploadFile> uploadFiles = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		if (uploadFiles.size() == 1) {

			List<KonkaR3Shop> r3ShopList = new ArrayList<KonkaR3Shop>();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
			String fileSavePath = uploadFiles.get(0).getFileSavePath();

			// 附件保存路径更改遗留问题
			if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "";
				fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
			}

			int size = 0;// Excel表格执行验证的记录条数
			try {
				Workbook workbook = Workbook.getWorkbook(new File(ctxDir + fileSavePath));
				Sheet sheet = workbook.getSheet(0);
				String msg = "";// 上传的表格不符合规范时的返回信息
				for (int i = 1; i < sheet.getRows(); i++) {
					if (StringUtils.isNotBlank(sheet.getCell(0, i).getContents())) {// 判断数据库中定义的必填项是否为空
						KonkaR3Shop r3Shop = new KonkaR3Shop();
						r3Shop.setArea_name(sheet.getCell(0, i).getContents());
						r3Shop.setBranch_area_name(sheet.getCell(1, i).getContents());
						String _type = sheet.getCell(2, i).getContents().trim();
						// r3Shop.setCustomer_type(type);//
						// 客户群类型：1.乡镇客户；2.县级客户；3.核心100；4.白金100；5.代理客户
						String status = sheet.getCell(4, i).getContents().trim();// 交易状态：1.有交易；2.无交易
						String r3_code = sheet.getCell(3, i).getContents().trim();// R3编码
						String customer_name = sheet.getCell(8, i).getContents().trim();// 客户名称

						/*
						 * 判断R3编码是否为空
						 */
						if (!StringUtils.isNotBlank(r3_code)) {
							msg += "请输入第" + (i + 1) + "行的R3编码！";
							super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
							return null;
						} else {
							r3Shop.setR3_code(sheet.getCell(3, i).getContents());// R3编码
						}

						/*
						 * 判断是不是规定的5种客户类型中的一种
						 */
						if (StringUtils.isNotBlank(_type)) {
							if (!_type.equals("乡镇客户") && !_type.equals("县级客户") && !_type.equals("核心500")
							        && !_type.equals("白金100") && !_type.equals("代理客户")) {
								msg += "请输入正确的第" + (i + 1) + "行的客户群类型：乡镇客户、县级客户、核心500、白金100或代理客户！";
								super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
								return null;
							} else {
								r3Shop.setCustomer_type(_type);
							}
						}

						/*
						 * 判断是不是规定的交易状态中2种状态客的一种
						 */
						if (StringUtils.isNotBlank(status)) {
							if (status.equals("有交易")) {
								r3Shop.setStatus(1);
							} else if (status.equals("无交易")) {
								r3Shop.setStatus(2);
							} else {
								msg += "请输入正确的第" + (i + 1) + "行的交易与否：有交易或无交易！";
								super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
								return null;
							}
						}

						/*
						 * 判断客户名称是否为空
						 */
						if (!StringUtils.isNotBlank(customer_name)) {
							msg += "请填写第" + (i + 1) + "行的客户名称！";
							super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
							return null;
						} else {
							r3Shop.setCustomer_name(sheet.getCell(8, i).getContents());// 客户名称
						}

						r3Shop.setHandle_name(sheet.getCell(5, i).getContents());
						r3Shop.setBranch_area_name_2(sheet.getCell(6, i).getContents());
						r3Shop.setCustomer_code(sheet.getCell(7, i).getContents().trim());
						r3Shop.setR3_desc(sheet.getCell(9, i).getContents());
						r3Shop.setMerge_code_2010(sheet.getCell(10, i).getContents().trim());

						r3ShopList.add(r3Shop);
						size = size + 1;
					} else {
						String sum_contents = "";
						StringBuffer sb = new StringBuffer();
						for (int j = 1; j < 11; j++) {
							if (null != sheet.getCell(j, i)) {
								sb.append(sheet.getCell(j, i).getContents().trim());
							}
						}
						sum_contents = sb.toString();
						if (StringUtils.isNotBlank(sum_contents)) {
							size = size + 1;
						}
						break;
					}
				}
				workbook.close();
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (r3ShopList != null && r3ShopList.size() > 0 && r3ShopList.size() == size) {
				z = r3ShopList.size();
				for (int i = 0; i < r3ShopList.size(); i++) {
					KonkaR3Shop entity = r3ShopList.get(i);
					entity.setImport_user_id(ui.getId());
					entity.setImport_date(new Date());

					KonkaR3Shop r3Shop = new KonkaR3Shop();
					r3Shop.setR3_code(r3ShopList.get(i).getR3_code());
					try {
						r3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);

					} catch (Exception e) {
						super.renderJavaScript(response, "alert('R3编码为" + r3ShopList.get(i).getR3_code()
						        + "的数据在数据库中可能存在重复，请联系管理员');history.go(-1);");
						return null;
					}
					if (r3Shop != null) {
						entity.setIs_del(r3Shop.getIs_del());
						entity.setIs_match(r3Shop.getIs_match());
						entity.setId(r3Shop.getId());
						try {
							// super.getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);
							str += r3Shop.getR3_code() + ",";
							x = x + 1;
						} catch (Exception ex) {
							log.info(ex.getMessage() + "\n" + (i + 1) + "");
						}
					} else {
						try {
							entity.setIs_del(new Long(0));
							entity.setIs_match(new Long(0));
							super.getFacade().getKonkaR3ShopService().createKonkaR3Shop(entity);
							y = y + 1;
						} catch (Exception ex) {
							log.info(ex.getMessage() + "\n" + (i + 1) + "");
						}
					}
				}
			} else if (r3ShopList != null && r3ShopList.size() > 0 && r3ShopList.size() < size) {
				super.renderJavaScript(response, "alert('Excel表格内容错误:第" + (size + 1)
				        + "行的第一列：区域不能为空！');history.go(-1);");
				return null;
			} else {
				super.renderJavaScript(response, "alert('Excel表格内容错误:第一行第一列的区域不能为空！');history.go(-1);");
				return null;
			}
		} else {
			super.renderJavaScript(response, "alert('" + "Excel为空" + "');history.go(-1);");
			return null;
		}
		String msg = null;
		if (x == 0) {
			msg = "总数为：" + z + "条信息，新增了" + y + "条R3网点信息。";
		} else if (x > 0) {
			msg = "总数为：" + z + "条信息，新增了" + y + "条R3网点信息，有" + x + "条信息与数据库中的数据R3编码重复，重复的编码为："
			        + str.substring(0, str.length() - 1);
		}
		super.renderJavaScript(response, "alert('" + msg + "');location.href='KonkaR3MmtMatch.do?method=list&mod_id="
		        + mod_id + "';");
		return null;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		entity.getMap().put("is_assign", 1);
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		entity.setQueryString(super.serialize(request, "str_pks", "method"));
		super.copyProperties(form, entity);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/toEdit.jsp"));
	}

	public ActionForward toModify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			this.saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("list");
		}
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		// 当月结算,当月回款
		KonkaR3Shop temp = new KonkaR3Shop();
		temp.setR3_code(entity.getR3_code());
		temp = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopSimpleStat(temp);
		entity.getMap().put("stat", temp);

		entity.setQueryString(super.serialize(request, "id", "method"));
		super.copyProperties(form, entity);

		Boolean role_id_ge_30 = false;
		Boolean role_id_lt_10_gt_30 = false;
		PeRoleUser role_user = new PeRoleUser();
		role_user.setUser_id(super.getSessionUserInfo(request).getId());
		// List<PeRoleUser> roleList =
		// super.getFacade().getPeRoleUserService().getPeRoleUserList(role_user);
		// if (roleList.size() > 0) {
		// for (PeRoleUser role_id : roleList) {
		// if (role_id.getRole_id() >= 30) {
		// role_id_ge_30 = true;
		// }
		// }
		// }

		// update by zhou 2013/10/25
		// 规则判定:当一个人的角色已经是大权限的时候,就以最大的权限给他
		List<PeRoleUser> roleList = super.getFacade().getPeRoleUserService().getPeRoleUserList(role_user);
		if (roleList.size() > 0) {
			for (PeRoleUser _roleUser : roleList) {
				if (_roleUser.getRole_id() >= 30l) {
					role_id_ge_30 = true;// 如果已经确认是分公司角色,直接跳出
					break;
				} else {
					role_id_ge_30 = false;// 如果已经确认是总部角色,直接跳出
					break;
				}
			}
			for (PeRoleUser _roleUser : roleList) {
				if (_roleUser.getRole_id() >= 10L && _roleUser.getRole_id() <= 30L) {
					role_id_lt_10_gt_30 = true;
				}
				//查询是否有允许修改盘点选项的权限
				if(8010L==_roleUser.getRole_id()){
					request.setAttribute("allow_pd", 0);
				}
				//是否为总部管理员，即系统管理员角色
				if(10L==_roleUser.getRole_id()){
					request.setAttribute("sys_admin", 0);
				}
			}
		}

		logger.info("role_id_lt_10_gt_30" + role_id_lt_10_gt_30);
		if (role_id_lt_10_gt_30) {
			request.setAttribute("role_id_lt_10_gt_30", "1");
		}
		// KonkaR3Shop r3 = new KonkaR3Shop();
		// r3.setIs_del(0L);
		// // if (role.getRole_id() >= 30) {
		// if (role_id_ge_30) {
		// KonkaDept dept = new KonkaDept();
		// dept.setDept_id(super.getSessionUserInfo(request).getDept_id());
		// dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		// if (dept.getDept_type() == 3) {// 分公司
		// r3.setBranch_area_name(dept.getDept_name());
		// dynaBean.set("branch_area_name", dept.getDept_name());
		// } else if (dept.getDept_type() == 4 || dept.getDept_type() == 5) {//
		// 经营部
		// r3.setBranch_area_name(super.getSuperiorForDeptType(dept.getDept_id(),
		// 3).getDept_name());
		// dynaBean.set("branch_area_name",
		// super.getSuperiorForDeptType(dept.getDept_id(), 3).getDept_name());
		// }
		// }
		// List<KonkaR3Shop> BranchList =
		// getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByBranchAreaName(r3);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		if (role_id_ge_30) {
			KonkaDept a1 = getKonkaDeptForFgs(super.getSessionUserInfo(request).getDept_id());
			if (a1 != null) {
				konkaDept.setDept_id(a1.getDept_id());
			}

		}
		konkaDept.getMap().put("order_by_dept_name", true);
		List<KonkaDept> kList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		request.setAttribute("BranchList", kList);

		// 客户类型
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(10);
		kc.setIs_del(0);
		request.setAttribute("konkaCategoryList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		// 企业类型
		kc = new KonkaCategory();
		kc.setC_type(12);
		kc.setIs_del(0);
		request.setAttribute("entpTypeList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));

		// 客户规模（年销售额）
		kc = new KonkaCategory();
		kc.setC_type(13);
		kc.setIs_del(0);
		request.setAttribute("entpScaleList", super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc));
		
		// 品牌名称  2014－09-23
		KonkaMobilePdBrand pdBrand = new KonkaMobilePdBrand();
		pdBrand.setIs_del(0);
		List<KonkaMobilePdBrand> pdBrandList = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandList(
				pdBrand);
		request.setAttribute("BrandList", pdBrandList);
		
		// 前10年份列表 2014－09-23
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		ArrayList<String> yearList = new ArrayList<String>();
		for (int i = year; i >= year-10; i--) {
			yearList.add(i+ "");
		}
		request.setAttribute("yearList", yearList);
		
		//排名选择列表
		ArrayList<HashMap> rankList = new ArrayList<HashMap>();
		for(int i=1; i<12; i++){
			HashMap map = new HashMap();
			if(i<11){
				map.put("val", i);
				map.put("name", "第"+i+"名");
			}else{
				map.put("val", i);
				map.put("name", "10名以上");
			}
			rankList.add(map);
		}
		request.setAttribute("rankList", rankList);

		//联系人列表
		KonkaR3ShopLink kr = new KonkaR3ShopLink();
		kr.setR3_shop_id(Long.valueOf(id));
		kr.setIs_del(0);
		request.setAttribute("konkaR3ShopLinkList", super.getFacade().getKonkaR3ShopLinkService()
		        .getKonkaR3ShopLinkList(kr));
		
		//品牌销售列表
		KonkaR3ShopBrand krsb = new KonkaR3ShopBrand();
		krsb.setR3_shop_id(Long.valueOf(id));
		krsb.setIs_del(0);
		request.setAttribute("konkaR3ShopBrandList", super.getFacade().getKonkaR3ShopBrandService()
				.getKonkaR3ShopBrandList(krsb));

		if (null != entity.getEntp_p_index() && String.valueOf(entity.getEntp_p_index()).length() >= 6) {
			request.setAttribute("province", String.valueOf(entity.getEntp_p_index()).substring(0, 2) + "0000");
			request.setAttribute("city", String.valueOf(entity.getEntp_p_index()).substring(0, 4) + "00");
			request.setAttribute("country", String.valueOf(entity.getEntp_p_index()).substring(0, 6));
			request.setAttribute("town", String.valueOf(entity.getEntp_p_index()));
		}

		// PeProdUser user = new PeProdUser();
		// user.setCust_id(Long.valueOf(id));
		// user = getFacade().getPeProdUserService().getPeProdUser(user);
		// if (null != user) {
		// dynaBean.set("user_id", user.getId());
		// dynaBean.set("user_name", user.getUser_name());
		// dynaBean.set("user_type", user.getUser_type());
		// DESPlus des = new DESPlus();
		// dynaBean.set("pass_word", des.decrypt(user.getPass_word()));
		// dynaBean.set("real_name", user.getReal_name());
		// dynaBean.set("link_phone", user.getLink_phone());
		// dynaBean.set("link_addr", user.getLink_addr());
		// }

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("KONKA_R3_SHOP");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		return mapping.findForward("input");
	}

	/**
	 * 修改R3 客户信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String request_from = (String) dynaBean.get("request_from");

		String[] r3_link_position = (String[]) dynaBean.get("r3_link_position");
		String[] r3_link_real_name = (String[]) dynaBean.get("r3_link_real_name");
		String[] r3_link_sex = (String[]) dynaBean.get("r3_link_sex");
		String[] r3_link_birthday = (String[]) dynaBean.get("r3_link_birthday");
		String[] r3_link_tel = (String[]) dynaBean.get("r3_link_tel");
		String[] r3_link_email = (String[]) dynaBean.get("r3_link_email");
		
		//根据IHS需求新增联系人字段  2014-09-22 LiangHouEn
		//岗位
		String[] r3_link_job = request.getParameterValues("r3_link_job");
		//固定电话
		String[] r3_link_phone = request.getParameterValues("r3_link_phone");
		//传真
		String[] r3_link_fax = request.getParameterValues("r3_link_fax");
		//微信号
		String[] r3_link_weixin = request.getParameterValues("r3_link_weixin");
		//QQ号
		String[] r3_link_qq = request.getParameterValues("r3_link_qq");
		//是否默认
		String[] r3_is_default = request.getParameterValues("r3_is_default");
		//是否有效
		String[] r3_is_valid = request.getParameterValues("r3_is_valid");
		//品牌ID
		String[] brand_id = request.getParameterValues("brand_id");
		//销售年份
		String[] sale_year = request.getParameterValues("sale_year");
		//年销售额
		String[] annual_salse = request.getParameterValues("annual_salse");
		//销售排名
		String[] sale_rank = request.getParameterValues("sale_rank");
		
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");

		if (StringUtils.isNotBlank(request_from)) {
			if ("zmd".equalsIgnoreCase(request_from)) {
				dynaBean.set("request_from", request_from);
			}
		}

		String branch_area_name_2 = (String) dynaBean.get("branch_area_name_2");
		String branch_area_name_2_old = (String) dynaBean.get("branch_area_name_2_old");

		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		
		if ((null == entity.getCustomer_code() || "".equals(entity.getCustomer_code()))
				&& null != entity.getR3_code()) {
			//当合并编码为空的时候将r3编码作为其编码
			entity.setCustomer_code(entity.getR3_code());
		}
		
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		entity.getMap().put("add_user_id", ui.getId());
		
		//维护人信息
		entity.setAdd_name(ui.getUser_name());
		entity.setModify_user_id(ui.getId());
		
		//判断是否修改某些字段值，若修改则保存记录
		StringBuffer sb = new StringBuffer("");
		String customer_name_old = (String)dynaBean.get("customer_name_old");
		if(!customer_name_old.equals(entity.getCustomer_name())){
			sb.append("客户名称："+customer_name_old+">>"+entity.getCustomer_name()+"	");
		}
		String area_name_old = (String)dynaBean.get("area_name_old");
		if(!area_name_old.equals(entity.getArea_name())){
			sb.append("所在区域名称："+area_name_old+">>"+entity.getArea_name()+"	");
		}
		String customer_code_old = (String)dynaBean.get("customer_code_old");
		if(null==customer_code_old){
			if(null!=entity.getCustomer_code()){
				sb.append("合并客户编码：空>>"+entity.getCustomer_code()+"	");
			}
		}else{
			if(!customer_code_old.equals(entity.getCustomer_code())){
				sb.append("合并客户编码："+customer_code_old+">>"+entity.getCustomer_code()+"	");
			}
		}
		String shop_status_old = (String)dynaBean.get("shop_status_old");
		if(!shop_status_old.equals(entity.getShop_status())){
			if(null!=shop_status_old&&!"".equals(shop_status_old)){
				int old_v = Integer.parseInt(shop_status_old);
				switch (old_v) {
				case 1:
					shop_status_old = "新客户";
					break;
				case 2:
					shop_status_old = "正式客户";
					break;
				case 3:
					shop_status_old = "静止客户";
					break;
				case 4:
					shop_status_old = "无效客户";
					break;
				default:
					break;
				}
			}
			String shop_status_new = "";
			if(null!=entity.getShop_status()&&!"".equals(entity.getShop_status())){
				int new_v = Integer.parseInt(entity.getShop_status());
				switch (new_v) {
				case 1:
					shop_status_new = "新客户";
					break;
				case 2:
					shop_status_new = "正式客户";
					break;
				case 3:
					shop_status_new = "静止客户";
					break;
				case 4:
					shop_status_new = "无效客户";
					break;
				default:
					break;
				}
			}
			sb.append("客户状态："+shop_status_old+">>"+shop_status_new+"	");
		}
		String is_minus_sales_old = (String)dynaBean.get("is_minus_sales_old");
		if(is_minus_sales_old==null){
			if(entity.getIs_minus_sales()!=null&&entity.getIs_minus_sales()==1){
				sb.append("允许负卖：空>>不允许  ");
			}else if(entity.getIs_minus_sales()!=null&&entity.getIs_minus_sales()==0){
				sb.append("允许负卖：空>>允许	");
			}
		}else{
			if(entity.getIs_minus_sales()!=null&&entity.getIs_minus_sales()-Integer.valueOf(is_minus_sales_old)!=0){
				String temp_str = "允许";
				if("0".equals(is_minus_sales_old)){
					sb.append("允许负卖："+temp_str+">>不"+temp_str+"	");
				}else{
					sb.append("允许负卖：不"+temp_str+">>"+temp_str+"	");
				}
			}
		}
		String web_type_old = (String)dynaBean.get("web_type_old");
		if(null==web_type_old&&entity.getWeb_type()!=null){
			if(entity.getWeb_type()==1){
				sb.append("触网类型：空>>触网1	");
			}else if(entity.getWeb_type()==2){
				sb.append("触网类型：空>>触网2	");
			}
		}else if(null!=web_type_old&&entity.getWeb_type()==null){
			if("1".equals(web_type_old)){
				sb.append("触网类型：触网1>>空	");
			}else if("2".equals(web_type_old)){
				sb.append("触网类型：触网2>>空	");
			}
		}else{
			if (web_type_old!=null&&entity.getWeb_type()!=null&&!web_type_old.equals(String.valueOf(entity.getWeb_type()))) {
				if("1".equals(web_type_old)){
					sb.append("触网类型：触网1>>触网2	");
				}else if("2".equals(web_type_old)){
					sb.append("触网类型：触网2>>触网1	");
				}
			}
			
		}
		String is_inventory_old = (String)dynaBean.get("is_inventory_old");
		if(null==is_inventory_old){
			if(null!=entity.getIs_inventory()){
				if(entity.getIs_inventory()==1){
					sb.append("允许盘存：空>>不允许	 ");
				}else{
					sb.append("允许盘存：空>>允许	");
				}
			}
		}else{
			if(entity.getIs_inventory()-Integer.valueOf(is_inventory_old)!=0){
				String temp_str = "允许";
				if("0".equals(is_inventory_old)){
					sb.append("允许盘存："+temp_str+">>不"+temp_str+"	");
				}else{
					sb.append("允许盘存：不"+temp_str+">>"+temp_str+"	");
				}
			}
		}
		if(!"".equals(sb.toString())){
			KonkaAuditListInfo kali = new KonkaAuditListInfo();
			kali.setLink_id(entity.getId());
			kali.setLink_type("r3_shop");
			kali.setOper_type("modify");
			kali.setOper_reason(sb.toString());
			kali.setCreate_date(new Date());
			kali.setCreate_user_id(ui.getId());
			kali.setStatus("2");
			kali.setIs_del(0);
			Long dept_id = super.getFacade().getKonkaR3ShopService().getDeptIdOfKonkaR3Code(entity.getR3_code());
			kali.setDept_id(dept_id);
			super.getFacade().getKonkaAuditListInfoService().createKonkaAuditListInfo(kali);
		}
		
		// 附件处理
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			filesAttachment = new Attachment();
			filesAttachment.setFile_name(uploadFile.getFileName());
			filesAttachment.setFile_type(uploadFile.getContentType());
			filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
			filesAttachment.setSave_path(uploadFile.getFileSavePath());
			filesAttachment.setSave_name(uploadFile.getFileSaveName());
			filesAttachment.setDel_mark(new Short("0"));
			filesAttachmentList.add(filesAttachment);
		}
		entity.setAttachmentList(filesAttachmentList);

		if (StringUtils.isNotBlank(branch_area_name_2) && StringUtils.isNotBlank(branch_area_name_2_old)
		        && !StringUtils.equalsIgnoreCase(branch_area_name_2, branch_area_name_2_old)) {
			entity.getMap().put("remove_branch_assign", true);
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(branch_area_name_2);
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (null != dept) {
				entity.setBranch_area_name(dept.getDept_name());
			}
		}
		
		//品牌销售信息
		entity.setBrand_id(brand_id);
		entity.setSale_year(sale_year);
		entity.setAnnual_salse(annual_salse);
		entity.setSale_rank(sale_rank);

		// 客户联系人信息
		entity.setR3_link_position(r3_link_position);
		entity.setR3_link_real_name(r3_link_real_name);
		entity.setR3_link_sex(r3_link_sex);
		entity.setR3_link_birthday(r3_link_birthday);
		entity.setR3_link_tel(r3_link_tel);
		entity.setR3_link_email(r3_link_email);
		entity.setR3_link_job(r3_link_job);
		entity.setR3_link_telephone(r3_link_phone);
		entity.setR3_link_fax(r3_link_fax);
		entity.setR3_link_weixin(r3_link_weixin);
		entity.setR3_link_qq(r3_link_qq);
		entity.setR3_link_default(r3_is_default);
		entity.setR3_link_valid(r3_is_valid);

		// 维护时间
		entity.setCreate_date(new Date());

		if (StringUtils.isNotBlank(town)) {
			entity.setEntp_p_index(new Long(town));
		} else if (StringUtils.isNotBlank(country)) {
			entity.setEntp_p_index(new Long(country));
		} else if (StringUtils.isNotBlank(city)) {
			entity.setEntp_p_index(new Long(city));
		} else if (StringUtils.isNotBlank(province)) {
			entity.setEntp_p_index(new Long(province));
		}

		super.getFacade().getKonkaR3ShopService().modifyKonkaR3ShopAndLink(entity);
		// super.getFacade().getKonkaR3ShopService().modifyKonkaR3ShopWithPeProdUser(entity);

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		logger.info(entity.getQueryString());
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);
		String r3_shop_id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String mmt_shop_id = (String) dynaBean.get("mmt_shop_id");
		String mmt_shop_name = (String) dynaBean.get("mmt_shop_name");
		if (GenericValidator.isLong(mmt_shop_id)) {
			KonkaR3MmtMatch r3MmtMatch = new KonkaR3MmtMatch();
			r3MmtMatch.setR3_shop_id(new Long(r3_shop_id));
			super.getFacade().getKonkaR3MmtMatchService().removeKonkaR3MmtMatch(r3MmtMatch);
			r3MmtMatch.setMmt_shop_id(new Long(mmt_shop_id));
			r3MmtMatch.setMmt_shop_name(mmt_shop_name);
			super.getFacade().getKonkaR3MmtMatchService().createKonkaR3MmtMatch(r3MmtMatch);
			KonkaR3Shop r3Shop = new KonkaR3Shop();
			r3Shop.setId(Long.valueOf(r3_shop_id));
			r3Shop.setIs_match(new Long(1));
			super.getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(r3Shop);
		} else {
			KonkaR3Shop r3Shop = new KonkaR3Shop();
			r3Shop.setId(Long.valueOf(r3_shop_id));
			r3Shop.setIs_match(new Long(0));
			super.getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(r3Shop);
			KonkaR3MmtMatch r3MmtMatch = new KonkaR3MmtMatch();
			r3MmtMatch.setR3_shop_id(new Long(r3_shop_id));
			super.getFacade().getKonkaR3MmtMatchService().removeKonkaR3MmtMatch(r3MmtMatch);
		}

		String request_from = (String) dynaBean.get("request_from");
		if ("zmd".equalsIgnoreCase(request_from)) {
			super.saveMessage(request, "konka.xx.zmd.message.zmdadmin.update.success");
			String ctx = super.getCtxPath(request);
			super.renderJavaScript(response, "location.href='" + ctx
			        + "/manager/zmd/KonkaXxZmdAuditUserInfo.do?method=list&mod_id=810300'");
			return null;
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/KonkaR3MmtMatch.do?method=list&is_match=1");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward getR3_code(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		StringBuffer sb = new StringBuffer("[");

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);

		sb.append("{\"r3_code\":\"" + String.valueOf(entity.getR3_code()) + "\"},");
		sb.append("{\"end\":\"\"}]");

		super.renderJson(response, sb.toString());
		return null;
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,// 解除匹配
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		super.getModPopeDom(form, request);
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
		if (!GenericValidator.isLong(r3_shop_id)) {
			this.saveError(request, "errors.long", new String[] { r3_shop_id });
			return mapping.findForward("list");
		}
		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop.setIs_match(new Long(0));
		super.getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(r3Shop);
		KonkaR3MmtMatch r3MmtMatch = new KonkaR3MmtMatch();
		r3MmtMatch.setR3_shop_id(new Long(r3_shop_id));
		super.getFacade().getKonkaR3MmtMatchService().removeKonkaR3MmtMatch(r3MmtMatch);
		return this.list(mapping, form, request, response);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {

			PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

			// 插入停用记录表
			KonkaR3Shop kShop = new KonkaR3Shop();
			kShop.setId(new Long(id));
			kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

			if (null != kShop) {
				KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
				k.setAdd_date(new Date());
				k.setAdd_user_id(ui.getId());
				k.setAdd_user_job_id(ui.getJob_id());
				k.setAdd_user_name(ui.getUser_name());
				k.setC_type(40);
				k.setChange_info("该客户被停用!");
				k.setSs_id(kShop.getId());
				k.setSs_name(kShop.getCustomer_name());
				super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
			}

			KonkaR3Shop entity = new KonkaR3Shop();
			entity.setId(new Long(id));
			entity.setIs_del(1l);
			entity.setStop_date(new Date());
			entity.setAdd_name(ui.getReal_name());
			getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);

			// 停止用户信息
			String c_ids = id;
			JBasePartner jbp = new JBasePartner();
			jbp.setC_id(Long.valueOf(id));
			List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
			if (jbpList.size() > 0) {
				for (JBasePartner jj : jbpList) {
					c_ids = c_ids + "," + jj.getPartner_c_id();
				}
			}

			PeProdUser pUser = new PeProdUser();
			// pUser.setCust_id(Long.valueOf(id));
			pUser.getMap().put("c_ids", c_ids);
			pUser.setIs_del(1);
			super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

			saveMessage(request, "konka.close.success");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaR3Shop entity = new KonkaR3Shop();
			entity.getMap().put("pks", pks);
			entity.setIs_del(1l);
			entity.setStop_date(new Date());
			getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);
			saveMessage(request, "konka.close.success");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward reStart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {

			PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

			// 插入停用记录表
			KonkaR3Shop kShop = new KonkaR3Shop();
			kShop.setId(new Long(id));
			kShop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kShop);

			if (null != kShop) {
				KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
				k.setAdd_date(new Date());
				k.setAdd_user_id(ui.getId());
				k.setAdd_user_job_id(ui.getJob_id());
				k.setAdd_user_name(ui.getUser_name());
				k.setC_type(40);
				k.setChange_info("该客户被启用!");
				k.setSs_id(kShop.getId());
				k.setSs_name(kShop.getCustomer_name());
				super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
			}

			KonkaR3Shop entity = new KonkaR3Shop();
			entity.setId(new Long(id));
			entity.setIs_del(0L);
			getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);

			// 停止用户信息
			String c_ids = id;
			JBasePartner jbp = new JBasePartner();
			jbp.setC_id(Long.valueOf(id));
			List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
			if (jbpList.size() > 0) {
				for (JBasePartner jj : jbpList) {
					c_ids = c_ids + "," + jj.getPartner_c_id();
				}
			}

			PeProdUser pUser = new PeProdUser();
			// pUser.setCust_id(Long.valueOf(id));
			pUser.getMap().put("c_ids", c_ids);
			pUser.setIs_del(0);
			super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
			saveMessage(request, "konka.restart.success");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaR3Shop entity = new KonkaR3Shop();
			entity.getMap().put("pks", pks);
			entity.setIs_del(0L);
			getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);
			saveMessage(request, "konka.restart.success");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		saveToken(request);
		setNaviStringToRequestScope(form, request);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/user.jsp"));
	}

	public ActionForward validateName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String user_name = (String) dynaBean.get("user_name");
		UserInfo entity = new UserInfo();
		entity.setUser_name(user_name);
		entity.setUser_state(0);
		Long count = super.getFacade().getUserInfoService().getUserInfoCount(entity);
		if (0L == count) {
			super.renderText(response, "false");
		} else {
			super.renderText(response, "true");
		}
		return null;
	}

	public ActionForward toBind(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/toBind.jsp"));
	}

	// 同步R3客户
	public ActionForward tbBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		Long count = super.getFacade().getKonkaR3ShopService().createKonkaR3ShopForTb();
		saveMessage(request, "prodadmin.md.tb.success", new String[] { count.toString() });

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward locatInMap(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);
		if (null != entity) {
			request.setAttribute("shop", entity);
		}
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/locatInMap.jsp"));
	}

	// 跳转到指定同步参数的页面
	public ActionForward showR3ShopDataPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// List<KonkaDept> kdList = new LinkedList<KonkaDept>();
		// if (request.getSession().getAttribute("kdList") == null) {
		// // 分公司
		// KonkaDept kd = new KonkaDept();
		// kd.setDept_type(3);
		// kd.getMap().put("order_by_dept_name", true);
		// kdList =
		// super.getFacade().getKonkaDeptService().getKonkaDeptList(kd);
		// // request.getSession().setAttribute("kdList", kdList);
		// }
		// PeProdUser ui = (PeProdUser) super.getSessionAttribute(request,
		// Constants.USER_INFO);
		// int max_dlevel = super.getMaxDLevel(ui.getId());
		// // 分公司
		// KonkaDept kd = new KonkaDept();
		// if (max_dlevel == 9) {
		// kd.setDept_id(0L);
		// } else {
		// kd.setDept_id(ui.getDept_id());
		// }
		// kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
	
		request.setAttribute("kdList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		String mod_id = (String) dynaBean.get("mod_id");
		dynaBean.set("mod_id", mod_id);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/getR3ShopData.jsp"));
	}

	public ActionForward getR3ShopData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// get data from r3Interface
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		dynaBean.set("mod_id", mod_id);
		// 注意:分公司,不是销售组织
		String bukrs = (String) dynaBean.get("sales_dept");
		// 单个r3编码
		String kunnr = (String) dynaBean.get("r3_code");
		String in_date = (String) dynaBean.get("in_date");
		List<KNA1> entityList = new ArrayList<KNA1>();
		ReturnInfo<KNA1> info = new ReturnInfo<KNA1>();
		// bug,如果填写了错误的分公司,即使有了Kunnr也无法得到正确的数据. 问题在于分公司不是销售组织
		// 当有客户编码与分公司,必须客户编码与分公司有从属关系才有值返回
		// TODO need to fixed
		if (kunnr != null && kunnr.length() > 0) {
			// entityList =
			// super.getFacade().getR3WebInterfaceService().getCustomerList(in_date,
			// bukrs,
			// new String[] { kunnr });
			info = super.getFacade().getR3WebInterfaceService().getCustomerList(in_date, bukrs,
			        new String[] { kunnr });
			if (info.getSuccess()) {
				entityList = info.getDataResult();
			}

		} else {
			// 根据分公司的编码查询该分公司及下属经办的销售组织
			KonkaSalesDept ksd = new KonkaSalesDept();
			ksd.setP_sales_org_code(bukrs);
			List<KonkaSalesDept> ksdList = super.getFacade().getKonkaSalesDeptService().getKonkaSalesDeptList(ksd);
			for (KonkaSalesDept t : ksdList) {
				// 如果没有指定r3编码,那么取指定的销售组织的数据
				// List<KNA1> list =
				// super.getFacade().getR3WebInterfaceService().getCustomerList(in_date,
				// t.getSales_org_code(), null);
				info = null;
				info = super.getFacade().getR3WebInterfaceService()
						.getCustomerList(in_date, t.getSales_org_code(), null);
				List<KNA1> list = info.getDataResult();
				if (list.size() > 0) {
					for (KNA1 temp : list) {
						entityList.add(temp);
					}
				}
			}
		}
		request.setAttribute("entityList", entityList);
		request.setAttribute("kdList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/getR3ShopData.jsp"));
	}

	/**
	 * 把选中的客户,进行数据同步回本地数据库
	 */
	public ActionForward syncFromR3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// get data from r3Interface
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		dynaBean.set("mod_id", mod_id);
		String bukrs = (String) dynaBean.get("sales_dept");
		String kunnrs = (String) dynaBean.get("kunnrs");
		String in_date = (String) dynaBean.get("in_date");
		String[] kunnr = null;
		StringBuffer sb = new StringBuffer(kunnrs);
		sb.deleteCharAt(sb.length() - 1);
		kunnr = sb.toString().split("-");
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		//添加记录日志
		OperLog operLog = new OperLog();
		operLog.setLink_tab("KONKA_R3_SHOP");
		operLog.setOper_time(new Date());
		operLog.setOper_uname(ui.getUser_name());
		operLog.setPpdm_name("开始执行手工同步R3客户");
		operLog.setOper_type("同步R3客户 ");
		operLog.setLink_id(Long.parseLong("888881"));
		operLog.setOper_ip(ui.getLast_login_ip());
		operLog.setOper_uid(ui.getId());
		super.getFacade().getOperLogService().createOperLog(operLog);
		
		// 确保每次调用的时候,只同步一个客户
		// 批量同步客户数据
		for (String k : kunnr) {
			if (!"".equals(k)) {
				super.getFacade().getKonkaR3ShopService().createKonkaR3ShopForTb2(bukrs, in_date, new String[] { k });
			}
		}
		
		operLog.setLink_tab("KONKA_R3_SHOP");
		operLog.setOper_time(new Date());
		operLog.setOper_uname(ui.getUser_name());
		operLog.setPpdm_name("结束执行手工同步R3客户");
		operLog.setOper_type("同步R3客户 ");
		operLog.setLink_id(Long.parseLong("888881"));
		operLog.setOper_ip(ui.getLast_login_ip());
		operLog.setOper_uid(ui.getId());
		super.getFacade().getOperLogService().createOperLog(operLog);
		
		saveMessage(request, "prodadmin.md.tb.success", String.valueOf(kunnr.length));
		request.setAttribute("kdList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/getR3ShopData.jsp"));
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			Attachment entity = new Attachment();
			entity.setId(new Long(id));
			super.getFacade().getAttachmentService().removeAttachment(entity);
		}

		super.renderText(response, "success");
		return null;
	}

	public ActionForward plSdfEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		setNaviStringToRequestScope(form, request);
		String[] pks = (String[]) dynaBean.get("pks");

		super.encodeCharacterForGetMethod(dynaBean, request);
		dynaBean.set("category_id", -1);
		dynaBean.set("queryString", super.serialize(request, "id", "method"));
		KonkaR3Shop entity = new KonkaR3Shop();
		entity.getMap().put("selects", StringUtils.join(pks, ","));

		List<KonkaR3Shop> konkaR3ShopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(entity);

		dynaBean.set("pks", pks);
		request.setAttribute("konkaR3ShopList", konkaR3ShopList);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/sdf_form.jsp"));
	}

	public ActionForward sdfSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		setNaviStringToRequestScope(form, request);
		String str_pks = (String) dynaBean.get("str_pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String shop_type = (String) dynaBean.get("shop_type");

		super.encodeCharacterForGetMethod(dynaBean, request);

		KonkaR3Shop entity = new KonkaR3Shop();
		String[] pks = str_pks.split(",");
		for (int i = 0; i < pks.length; i++) {
			entity.setId(Long.valueOf(pks[i]));
			entity.setIs_sdf(Integer.valueOf(shop_type));
			super.getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(entity);
		}

		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	/**
	 * 订货会添加参会客户时使用
	 * 
	 * @author Zhang,Chao
	 * @date 2014-1-24
	 */
	public ActionForward orderMeetingList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String code_like = (String) dynaBean.get("code_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String c_index = (String) dynaBean.get("c_index");

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		entity.setIs_del(0L);

		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
			entity.getMap().put("user_id", ui.getId());
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

		entity.getMap().put("is_assign", "true");
		entity.getMap().put("leftYWY", "true");
		entity.getMap().put("YWY", "true");
		entity.getMap().put("dept_id_start", __dept_id);
		// entity.setIs_match(Long.valueOf(is_match));
		// 数据级别判断结束

		dynaBean.set("is_sdf", 0);
		dynaBean.set("is_del", 0);

		entity.setCustomer_type(c_index);
		entity.getMap().put("c_index", c_index);
		entity.getMap().put("code_like", code_like);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerCount(entity);
		pager.init(recordCount, 100, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerList(
		        entity);

		request.setAttribute("entityList", entityList);

		return new ActionForward(response.encodeRedirectURL("/admin/KonkaR3MmtMatch/orderMeetingList.jsp"));
	}

	public ActionForward getQueryNames(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String key_word = (String) dynaBean.get("key_word");
		String is_sdf = (String) request.getSession().getAttribute("selects");
		String dept_id = (String) dynaBean.get("dept_id");
		String c_index = (String) dynaBean.get("c_index");

		KonkaR3Shop entity = new KonkaR3Shop();
		super.copyProperties(entity, form);
		entity.setIs_del(0L);

		if (StringUtils.isNotBlank(key_word)) {
			entity.getMap().put("code_like", key_word);
		}

		if (GenericValidator.isInt(is_sdf)) {
			entity.setIs_sdf(Integer.valueOf(is_sdf));
		} else {
			dynaBean.set("is_sdf", "0");
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

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
		entity.getMap().put("is_assign", "true");
		entity.getMap().put("leftYWY", "true");
		entity.getMap().put("YWY", "true");
		entity.getMap().put("dept_id_start", __dept_id);
		// entity.setIs_match(Long.valueOf(is_match));
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		dynaBean.set("is_sdf", "0");

		entity.setCustomer_type(c_index);
		entity.getMap().put("c_index", c_index);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerCount(entity);
		pager.init(recordCount, 10000, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopResultListByViewCustomerList(
		        entity);

		StringBuffer sb = new StringBuffer("[");
		for (KonkaR3Shop t : entityList) {
			String ids = t.getMap().get("l4_dept_id") + "_" + t.getMap().get("l4_dept_name") + "_";
			ids += t.getMap().get("ywy_user_name") + "_" + t.getMap().get("customer_name");
			ids += t.getMap().get("customer_type") + "_" + t.getMap().get("customer_type_name") + "_"
			        + t.getMap().get("job_id");

			sb.append("{\"id\":\"" + ids + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getR3_code()) + "\"},");
		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}
	
	
	/**
	 * 弹框填写停、启用申请
	 * @author Liang,HouEn
	 * 2014-12-30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pageForAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String r3_code = (String) dynaBean.get("r3_code"); 
		String flag = (String) dynaBean.get("flag"); 
		
		request.setAttribute("id", id);
		request.setAttribute("r3_code", r3_code);
		request.setAttribute("flag", flag);

		return new ActionForward("/admin/KonkaR3MmtMatch/audit_Form.jsp");
	}
}