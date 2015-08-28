package com.ebiz.mmt.web.struts.customer;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaAuditListInfo;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaStoreUserCInfo;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 客户管理待审菜单模块
 * @author Liang,HouEn
 * 2014-12-23
 */
public class CustomerAuditAction extends BaseAction {
	
	/**
	 * 进入申请页面初始化
	 * @author Liang,HouEn
	 * 2014-12-23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAuditInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		//位置信息
		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		map.put("local_str", naviString);
		
		//回执单号
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Date date = new Date();
		map.put("hzd_id", "R3-"+ui.getId()+date.getTime());
		
		//当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(date);
		map.put("today", today);
		
		//填写人
		map.put("write_man", ui.getUser_name());
		
		//所在部门
		map.put("dept_name", ui.getDepartment());
		
		//属性列表
		KonkaBaseTypeData kbtd = new KonkaBaseTypeData();
		kbtd.setPar_type_id(100014L);
		List<KonkaBaseTypeData> proList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(kbtd);
		map.put("proList", proList);
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(map);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 初始化查询页面数据
	 * @author Liang,HouEn
	 * 2014-10-10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HashMap allmap = new HashMap();
        //位置信息
        DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		allmap.put("local_str", naviString);
		
		//当前用户部门信息
		PeProdUser ui = super.getSessionUserInfo(request);
		if(ui!=null){
			int max_dlevel = super.getMaxDLevel(ui.getId());
			if(max_dlevel==9){
				allmap.put("dept_id", "");
			}else{
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id());
				allmap.put("dept_id", dept_fgs.getDept_id());
			}
		}
		
		//用户角色信息
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		
		for(PeRoleUser t : roleUserList){
			if(t.getRole_id() == 30){//分公司管理员
				allmap.put("role_id", "30");
			}
			
			if(t.getRole_id() == 10){//总部管理员
				allmap.put("role_id", "10");
			}
		}
		
		SimpleDateFormat mm_fmt = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
        
		allmap.put("date_begin", mm_fmt.format(new Date()) + "-01");
		allmap.put("date_end", fmt1.format(new Date()));
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 保存客户申请
	 * @author Liang,HouEn
	 * 2014-12-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
		String id = (String) dynaBean.get("id");
		String flag = (String) dynaBean.get("flag");
		String r3_code = (String) dynaBean.get("r3_code");
		String content = java.net.URLDecoder.decode((String) dynaBean.get("content"), Constants.SYS_ENCODING);
		String type = (String) dynaBean.get("type");
		
		//用户角色信息
		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(ui.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
		
		boolean fgs_role = false;
		for(PeRoleUser t : roleUserList){
			if(t.getRole_id() == 30){//分公司管理员
				fgs_role = true;
			}
		}
		
		KonkaAuditListInfo kali = new KonkaAuditListInfo();
		kali.setLink_id(Long.parseLong(id));
		
		if("r3_shop".equals(type)){
			kali.setLink_type("r3_shop");
			Long dept_id = super.getFacade().getKonkaR3ShopService().getDeptIdOfKonkaR3Code(r3_code);
			kali.setDept_id(dept_id);
		}
		if("store".equals(type)){
			kali.setLink_type("store");
			KonkaR3Store store = new KonkaR3Store();
			store.setStore_id(Long.parseLong(id));
			store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
			kali.setDept_id(store.getDept_id());
		}
		if("agent".equals(type)){
			kali.setLink_type("agent");
			JBasePartner partner = new JBasePartner();
			partner.setPartner_id(Long.parseLong(id));
			partner = super.getFacade().getJBasePartnerService().getJBasePartner(partner);
			kali.setDept_id(partner.getDept_id());
		}
		//更新之前的记录不为最新状态
		KonkaAuditListInfo temp = new KonkaAuditListInfo();
		temp.setLink_type(kali.getLink_type());
		temp.setLink_id(kali.getLink_id());
		temp.setBack_id("0");
		super.getFacade().getKonkaAuditListInfoService().modifyKonkaAuditListInfoForNew(temp);
		
		kali.setOper_type(flag);
		kali.setOper_reason(content);
		kali.setCreate_date(new Date());
		kali.setCreate_user_id(ui.getId());
		kali.setIs_del(0);
		kali.setBack_id("1");
		kali.setCur_audit_role_id(30L);
		if(fgs_role){
			kali.setStatus("2");
			kali.setModify_user_id(ui.getId());
			kali.setModify_date(new Date());
		}else{
			kali.setStatus("0");
		}
		Long k_id = super.getFacade().getKonkaAuditListInfoService().createKonkaAuditListInfo(kali);
		
		//若为分公司管理员，则直接生效，无需确认
		if(fgs_role){
			if(k_id>0){
				kali = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfo(kali);
				if("r3_shop".equals(kali.getLink_type())){//客户停/启用
					KonkaR3Shop tshop = new KonkaR3Shop();
					tshop.setId(kali.getLink_id());
					if("stop".equals(kali.getOper_type())){
						// 插入停用记录表
						KonkaR3Shop kShop = new KonkaR3Shop();
						kShop.setId(new Long(kali.getLink_id()));
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

						tshop.setIs_del(1l);
						tshop.setShop_status("4");
						tshop.setCreate_date(new Date());
						tshop.setAdd_name(ui.getUser_name());
						getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(tshop);

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
						pUser.getMap().put("c_ids", c_ids);
						pUser.setIs_del(1);
						super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
						
					}
					if("start".equals(kali.getOper_type())){
						// 插入启用记录表
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

						tshop.setIs_del(0L);
						tshop.setShop_status("3");
						tshop.setCreate_date(new Date());
						tshop.setAdd_name(ui.getUser_name());
						getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(tshop);

						// 启用用户信息
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
						pUser.getMap().put("c_ids", c_ids);
						pUser.setIs_del(0);
						super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
					}
				}
				if("agent".equals(kali.getLink_type())){//网点停/启用
					JBasePartner jbp = new JBasePartner();
					jbp.setPartner_id(kali.getLink_id());
					if("stop".equals(kali.getOper_type())){
						// 停止用户信息
						String c_ids = kali.getLink_id().toString();
						List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
						if (jbpList.size() > 0) {
							for (JBasePartner jj : jbpList) {
								c_ids = c_ids + "," + jj.getPartner_c_id();
							}
						}
						PeProdUser pUser = new PeProdUser();
						pUser.getMap().put("c_ids", c_ids);
						pUser.setIs_del(1);
						super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
						
						jbp.setModify_date(new Date());
						jbp.setModify_user_id(ui.getId());
						jbp.setIs_del(1);
						super.getFacade().getJBasePartnerService().removeJBasePartner(jbp);
					}
					if("start".equals(kali.getOper_type())){
						// 启用用户信息
						String c_ids = kali.getLink_id().toString();
						List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
						if (jbpList.size() > 0) {
							for (JBasePartner jj : jbpList) {
								c_ids = c_ids + "," + jj.getPartner_c_id();
							}
						}
						PeProdUser pUser = new PeProdUser();
						pUser.getMap().put("c_ids", c_ids);
						pUser.setIs_del(0);
						super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

						jbp.setModify_date(new Date());
						jbp.setModify_user_id(ui.getId());
						jbp.setIs_del(1);
						super.getFacade().getJBasePartnerService().modifyJBasePartner(jbp);
					}
				}
				if("store".equals(kali.getLink_type())){  // 门店停/启用
					KonkaR3Store store = new KonkaR3Store();
					store.setStore_id(kali.getLink_id());
					if("stop".equals(kali.getOper_type())){
						store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);

						if (null != store) {
							KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
							k.setAdd_date(new Date());
							k.setAdd_user_id(ui.getId());
							k.setAdd_user_job_id(ui.getJob_id());
							k.setAdd_user_name(ui.getUser_name());
							k.setC_type(30);
							k.setChange_info("该门店被停用!");
							k.setSs_id(store.getStore_id());
							k.setSs_name(store.getStore_name());
							super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
						}

						KonkaR3Store store1 = new KonkaR3Store();
						store1.setStore_id(kali.getLink_id());
						store1.setIs_del(1);
						store1.setStop_date(new Date());
						store1.setModify_user_id(ui.getId());
						store1.setModify_date(new Date());
						super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(store1);
					}
					if("start".equals(kali.getOper_type())){
						store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);

						if (null != store) {
							KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
							k.setAdd_date(new Date());
							k.setAdd_user_id(ui.getId());
							k.setAdd_user_job_id(ui.getJob_id());
							k.setAdd_user_name(ui.getUser_name());
							k.setC_type(30);
							k.setChange_info("该门店被启用!");
							k.setSs_id(store.getStore_id());
							k.setSs_name(store.getStore_name());
							super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
						}

						KonkaR3Store store1 = new KonkaR3Store();
						store1.setStore_id(kali.getLink_id());
						store1.setIs_del(0);
						store1.setStop_date(new Date());
						store1.setModify_user_id(ui.getId());
						store1.setModify_date(new Date());
						super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(store1);
					}
				}
			}
		}
		
		HashMap allmap = new HashMap();
		if(k_id>0){
			allmap.put("res", "success");
			if(fgs_role){
				allmap.put("role", true);
			}
		}else{
			allmap.put("res", "error");
		}
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
		
	}
	
	
	/**
	 * 查询待审请求列表
	 * @author Liang,HouEn
	 * 2014-12-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		//验证权限
//		if (null == super.checkUserModPopeDom(form, request, "0")) {
//			return super.noPersission(request,response);
//		}
		KonkaAuditListInfo entity = getEntiy(mapping, form, request);
		
		Long recordCount = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfoCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<HashMap> entityList = null;
		if(recordCount>0){
			entityList = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfoListForMap(entity);
		}
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 封装数据
	 * @author Liang,HouEn
	 * 2014-12-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public KonkaAuditListInfo  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		String dept_id = request.getParameter("dept_id");
		String name_like = request.getParameter("name_like");
		String audit_type = request.getParameter("audit_type");
		String audit_status = request.getParameter("audit_status");
		String ope_date_begin = request.getParameter("ope_date_begin");
		String ope_date_end = request.getParameter("ope_date_end");
		String oper_type = request.getParameter("oper_type");
		String page = request.getParameter("page");
		
        PeProdUser ui = new PeProdUser();
		ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaAuditListInfo entity = new KonkaAuditListInfo();
		
		entity.setIs_del(0);
		//分公司ID
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(Long.parseLong(dept_id));
		}
		if (StringUtils.isNotBlank(audit_status)) {
			entity.setStatus(audit_status);
		}
		if (StringUtils.isNotBlank(ope_date_begin)) {
			entity.getMap().put("ope_date_begin", ope_date_begin);
		}
		if (StringUtils.isNotBlank(ope_date_end)) {
			entity.getMap().put("ope_date_end", ope_date_end);
		}
		if (StringUtils.isNotBlank(oper_type)) {
			entity.setOper_type(oper_type);
		}
		
		entity.setLink_type(audit_type);
		if("r3_shop".equals(audit_type)){
			entity.getMap().put("r3_shop", true);
			if (StringUtils.isNotBlank(name_like)) {
				entity.getMap().put("name_like", name_like);
				entity.getMap().put("link_shop", true);
			}
		}
		if("store".equals(audit_type)){
			entity.getMap().put("store", true);
			if (StringUtils.isNotBlank(name_like)) {
				entity.getMap().put("name_like", name_like);
				entity.getMap().put("link_store", true);
			}
		}
		if("agent".equals(audit_type)){
			entity.getMap().put("agent", true);
			if (StringUtils.isNotBlank(name_like)) {
				entity.getMap().put("name_like", name_like);
				entity.getMap().put("link_agent", true);
			}
		}
		
		// 数据级别判断开始
		Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			break;
		case 0:
			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束
		entity.getMap().put("page", page);
		return entity;
	}
	
	
	/**
	 * 查看申请内容
	 * @author Liang,HouEn
	 * 2014-12-24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String operation = request.getParameter("operation");
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		KonkaAuditListInfo entity = new KonkaAuditListInfo();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		
		HashMap	map = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfoForMap(entity);
		
		if("audit".equals(operation)){
			//若状态为待审批，审批中，则查询当前用户是否为下一个审批人
			if(!"2".equals(map.get("STATUS"))){
				//用户角色信息
				PeRoleUser roleUser = new PeRoleUser();
				roleUser.setUser_id(ui.getId());
				List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);
				
				Long cur_role = Long.parseLong(map.get("CUR_AUDIT_ROLE_ID").toString());
				for(PeRoleUser t : roleUserList){
					if(cur_role.equals(t.getRole_id())){
						map.put("SHOW_AUDIT",true);
						break;
					}
				}
			}
		}
		
		//审批结果
		KonkaoaFilesAuditNode en = new KonkaoaFilesAuditNode();
		en.setLink_id(Long.valueOf(id));
		en.setNode_type(4);
		List<HashMap> auditList = super.getFacade().getKonkaoaFilesAuditNodeService().getAuditInfoList(en);
		map.put("auditlist", auditList);
		
		//位置信息
        DynaBean dynaBean = (DynaBean) form;
		String mod_id = request.getParameter("mod_id");
		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		map.put("local_str", naviString);
		
		JSONArray jsonArray = JSONArray.fromObject(map);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 客户申请提交审批
	 * @author Liang,HouEn
	 * 2014-12-25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean = (DynaBean) form;
		
		String audit_res = (String) dynaBean.get("audit_res");   //审批结果
		String audit_comment = (String) dynaBean.get("audit_comment");  //审批意见
		String id = (String) dynaBean.get("id");     //申请id
		String mod_id = (String) dynaBean.get("mod_id");
		
		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.setAudit_user(ui.getUser_name());
		entity.setAudit_user_id(ui.getId());
		
		entity.setLink_id(Long.valueOf(id));
		//审批类型
		entity.setNode_type(4);
		entity.setAudit_datetime(new Date());
		
		//记录顺序
		KonkaoaFilesAuditNode temp = new KonkaoaFilesAuditNode();
		temp.setLink_id(Long.valueOf(id));
		temp.setNode_type(4);
		List<KonkaoaFilesAuditNode> templist = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeList(temp);
		int count = templist.size()+1;
		entity.setAudit_level(Long.parseLong(String.valueOf(count)));
		
		//审批结果
		if(StringUtils.isNotBlank(audit_res)){
			entity.setAudit_result(Integer.valueOf(audit_res));
		}
		//审批意见
		if(StringUtils.isNotBlank(audit_comment)){
			entity.setAudit_comment(audit_comment);
		}
		
		Long audit_id = super.getFacade().getKonkaoaFilesAuditNodeService().createKonkaoaFilesAuditNode(entity);
		
		if(audit_id>0){
			//更新申请记录状态
			KonkaAuditListInfo en = new KonkaAuditListInfo();
			en.setId(Long.parseLong(id));
			en = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfo(en);
			
			if("1".equals(audit_res)){//驳回
				en.setStatus("2");
			}else{
				//网点、门店申请仅需分公司管理员审批
				if("AGENT".equals(en.getLink_type())){
					JBasePartner jbp = new JBasePartner();
					jbp.setPartner_id(en.getLink_id());
					if("stop".equals(en.getOper_type())){
						// 停止用户信息
						String c_ids = en.getLink_id().toString();
						List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
						if (jbpList.size() > 0) {
							for (JBasePartner jj : jbpList) {
								c_ids = c_ids + "," + jj.getPartner_c_id();
							}
						}
						PeProdUser pUser = new PeProdUser();
						pUser.getMap().put("c_ids", c_ids);
						pUser.setIs_del(1);
						super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
						
						jbp.setModify_date(new Date());
						jbp.setModify_user_id(ui.getId());
						jbp.setIs_del(1);
						super.getFacade().getJBasePartnerService().removeJBasePartner(jbp);
					}
					if("start".equals(en.getOper_type())){
						// 启用用户信息
						String c_ids = en.getLink_id().toString();
						List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
						if (jbpList.size() > 0) {
							for (JBasePartner jj : jbpList) {
								c_ids = c_ids + "," + jj.getPartner_c_id();
							}
						}
						PeProdUser pUser = new PeProdUser();
						pUser.getMap().put("c_ids", c_ids);
						pUser.setIs_del(0);
						super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

						jbp.setModify_date(new Date());
						jbp.setModify_user_id(ui.getId());
						jbp.setIs_del(1);
						super.getFacade().getJBasePartnerService().modifyJBasePartner(jbp);
					}
				}
				if("STORE".equals(en.getLink_type())){
					KonkaR3Store store = new KonkaR3Store();
					store.setStore_id(en.getLink_id());
					if("stop".equals(en.getOper_type())){
						store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);

						if (null != store) {
							KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
							k.setAdd_date(new Date());
							k.setAdd_user_id(ui.getId());
							k.setAdd_user_job_id(ui.getJob_id());
							k.setAdd_user_name(ui.getUser_name());
							k.setC_type(30);
							k.setChange_info("该门店被停用!");
							k.setSs_id(store.getStore_id());
							k.setSs_name(store.getStore_name());
							super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
						}

						KonkaR3Store store1 = new KonkaR3Store();
						store1.setStore_id(en.getLink_id());
						store1.setIs_del(1);
						store1.setStop_date(new Date());
						store1.setModify_user_id(ui.getId());
						store1.setModify_date(new Date());
						super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(store1);
					}
					if("start".equals(en.getOper_type())){
						store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);

						if (null != store) {
							KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
							k.setAdd_date(new Date());
							k.setAdd_user_id(ui.getId());
							k.setAdd_user_job_id(ui.getJob_id());
							k.setAdd_user_name(ui.getUser_name());
							k.setC_type(30);
							k.setChange_info("该门店被启用!");
							k.setSs_id(store.getStore_id());
							k.setSs_name(store.getStore_name());
							super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
						}

						KonkaR3Store store1 = new KonkaR3Store();
						store1.setStore_id(en.getLink_id());
						store1.setIs_del(0);
						store1.setStop_date(new Date());
						store1.setModify_user_id(ui.getId());
						store1.setModify_date(new Date());
						super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(store1);
					}
				}
				en.setStatus("2");
			}
			super.getFacade().getKonkaAuditListInfoService().modifyKonkaAuditListInfo(en);
			
			super.renderJavaScript(response, "location.href='WaitAuditList/list.jsp?mod_id="+mod_id+"'");
		}else{
			super.renderJavaScript(response, "alert('审批失败！');history.back();");
		}
		return null;
	}
	
	/**
	 * 返回列表页面
	 * @author Liang,HouEn
	 * 2014-12-31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward backToList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");// 节点
		//传递过来的查询条件
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String audit_type = (String) dynaBean.get("audit_type");// 类型
		String audit_status = (String) dynaBean.get("audit_status");// 状态
		
		request.setAttribute("mod_id", mod_id);
		String str = "dept_id="+dept_id+"&audit_type="+audit_type+"&audit_status="+audit_status;
		request.setAttribute("queryStr", str);
		return new ActionForward("/admin/WaitAuditList/list.jsp");
	}
	
	/**
	 * 检查是否已有未完成的停/启用申请   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		HashMap allmap = new HashMap();
		String id = (String) dynaBean.get("id");   //关联ID
		String flag = (String) dynaBean.get("flag");//停启用标识
		String r3_code = (String) dynaBean.get("r3_code");//R3编码
		String type = (String) dynaBean.get("type");//申请类型
		
		KonkaAuditListInfo kali = new KonkaAuditListInfo();
		kali.setLink_id(Long.parseLong(id));
		kali.setLink_type(type);
		kali.setBack_id("1");
		kali.setIs_del(0);
		kali = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfo(kali);
		
		allmap.put("have", "0");
		if(null!=kali){
			if("0".equals(kali.getStatus())){
				allmap.put("have", "1");
			}
		}
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 根据id获取申请内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");   //ID
		
		KonkaAuditListInfo kali = new KonkaAuditListInfo();
		kali.setId(Long.parseLong(id));
		kali = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfo(kali);
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(kali);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 确认申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sureAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		HashMap allmap = new HashMap();
		String id = (String) dynaBean.get("id");   //ID
		String s_type = (String) dynaBean.get("s_type");   //ID
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		
		KonkaAuditListInfo kali = new KonkaAuditListInfo();
		kali.setId(Long.parseLong(id));
		if("0".equals(s_type)){
			kali.setStatus("2");
		}
		if("1".equals(s_type)){
			kali.setStatus("1");
		}
		kali.setModify_user_id(ui.getId());
		kali.setModify_date(new Date());
		int mod = super.getFacade().getKonkaAuditListInfoService().modifyKonkaAuditListInfo(kali);
		
		if(mod>0&&"0".equals(s_type)){
			kali = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfo(kali);
			if("r3_shop".equals(kali.getLink_type())){//客户停/启用
				KonkaR3Shop tshop = new KonkaR3Shop();
				tshop.setId(kali.getLink_id());
				if("stop".equals(kali.getOper_type())){
					// 插入停用记录表
					KonkaR3Shop kShop = new KonkaR3Shop();
					kShop.setId(new Long(kali.getLink_id()));
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

					tshop.setIs_del(1l);
					tshop.setShop_status("4");
					tshop.setCreate_date(new Date());
					tshop.setAdd_name(ui.getUser_name());
					getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(tshop);

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
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(1);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
					
				}
				if("start".equals(kali.getOper_type())){
					// 插入启用记录表
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

					tshop.setIs_del(0L);
					tshop.setShop_status("3");
					tshop.setCreate_date(new Date());
					tshop.setAdd_name(ui.getUser_name());
					getFacade().getKonkaR3ShopService().modifyKonkaR3Shop(tshop);

					// 启用用户信息
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
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(0);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
				}
			}
			if("agent".equals(kali.getLink_type())){//网点停/启用
				JBasePartner jbp = new JBasePartner();
				jbp.setPartner_id(kali.getLink_id());
				if("stop".equals(kali.getOper_type())){
					// 停止用户信息
					String c_ids = kali.getLink_id().toString();
					List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
					if (jbpList.size() > 0) {
						for (JBasePartner jj : jbpList) {
							c_ids = c_ids + "," + jj.getPartner_c_id();
						}
					}
					PeProdUser pUser = new PeProdUser();
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(1);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);
					
					jbp.setModify_date(new Date());
					jbp.setModify_user_id(ui.getId());
					jbp.setIs_del(1);
					super.getFacade().getJBasePartnerService().removeJBasePartner(jbp);
				}
				if("start".equals(kali.getOper_type())){
					// 启用用户信息
					String c_ids = kali.getLink_id().toString();
					List<JBasePartner> jbpList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);
					if (jbpList.size() > 0) {
						for (JBasePartner jj : jbpList) {
							c_ids = c_ids + "," + jj.getPartner_c_id();
						}
					}
					PeProdUser pUser = new PeProdUser();
					pUser.getMap().put("c_ids", c_ids);
					pUser.setIs_del(0);
					super.getFacade().getPeProdUserService().modifyPeProdUser(pUser);

					jbp.setModify_date(new Date());
					jbp.setModify_user_id(ui.getId());
					jbp.setIs_del(1);
					super.getFacade().getJBasePartnerService().modifyJBasePartner(jbp);
				}
			}
			if("store".equals(kali.getLink_type())){  // 门店停/启用
				KonkaR3Store store = new KonkaR3Store();
				store.setStore_id(kali.getLink_id());
				if("stop".equals(kali.getOper_type())){
					store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);

					if (null != store) {
						KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
						k.setAdd_date(new Date());
						k.setAdd_user_id(ui.getId());
						k.setAdd_user_job_id(ui.getJob_id());
						k.setAdd_user_name(ui.getUser_name());
						k.setC_type(30);
						k.setChange_info("该门店被停用!");
						k.setSs_id(store.getStore_id());
						k.setSs_name(store.getStore_name());
						super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
					}

					KonkaR3Store store1 = new KonkaR3Store();
					store1.setStore_id(kali.getLink_id());
					store1.setIs_del(1);
					store1.setStop_date(new Date());
					store1.setModify_user_id(ui.getId());
					store1.setModify_date(new Date());
					super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(store1);
				}
				if("start".equals(kali.getOper_type())){
					store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);

					if (null != store) {
						KonkaStoreUserCInfo k = new KonkaStoreUserCInfo();
						k.setAdd_date(new Date());
						k.setAdd_user_id(ui.getId());
						k.setAdd_user_job_id(ui.getJob_id());
						k.setAdd_user_name(ui.getUser_name());
						k.setC_type(30);
						k.setChange_info("该门店被启用!");
						k.setSs_id(store.getStore_id());
						k.setSs_name(store.getStore_name());
						super.getFacade().getKonkaStoreUserCInfoService().createKonkaStoreUserCInfo(k);
					}

					KonkaR3Store store1 = new KonkaR3Store();
					store1.setStore_id(kali.getLink_id());
					store1.setIs_del(0);
					store1.setStop_date(new Date());
					store1.setModify_user_id(ui.getId());
					store1.setModify_date(new Date());
					super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(store1);
				}
			}
			allmap.put("flag", "suc");
		}else{
			allmap.put("flag", "error");
		}
		
		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(allmap);
		
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 查询维护历史
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward historyList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");   //ID
		String type = (String) dynaBean.get("type");   //ID
		KonkaAuditListInfo entity = new KonkaAuditListInfo();
		entity.setLink_id(Long.parseLong(id));
		entity.setLink_type(type);
		
		Long recordCount = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfoCount(entity);
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, 10, request.getParameter("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		List<HashMap> entityList = null;
		if(recordCount>0){
			entityList = super.getFacade().getKonkaAuditListInfoService().getKonkaAuditListInfoListForMap(entity);
		}
		
		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
}