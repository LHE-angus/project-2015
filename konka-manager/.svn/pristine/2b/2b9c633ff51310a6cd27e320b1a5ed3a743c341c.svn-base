package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.VADetailsOfSalesData;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-07
 */
public class KonkaMobileSailDataSearchAction extends BaseAction {

	private static String file_name = "md_serise.properties";// 属性文件名称

	private static String key = "md_serise_list";// 属性文件中key的名称

	private static String md_serise_list = "";// 属性文件中key对于的value

	private static Properties properties = new Properties();
	static {
		InputStream inputStream = InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(file_name);
		try {
			properties.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		dynaBean.set("search_first_flag", "0");

		// 取当月时间
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(calendar.getTime());
		String day_last = df.format(today);
		dynaBean.set("date_begin", day_first);
		dynaBean.set("date_end", day_last);

		return this.list(mapping, form, request, response);
	}

	public ActionForward updateMdSerise(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String md_serise_properties = (String) dynaBean.get("md_serise_properties");// 产品系列属性文件

		try {
			String path = InteractWebServiceImpl.class.getResource("/").getPath() + file_name;
			File file = new File(path);

			if (!file.exists()) {
				file.createNewFile();
				logger.info("create new file");
			}
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(file);
			properties.setProperty(key, md_serise_properties);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			properties.store(fos, "Update '" + key + "' value");
			fos.close();
		} catch (IOException e) {
			System.err.println("属性文件更新错误");
		}

		return this.unspecified(mapping, form, request, response);
	}

	/**
	 * 进销存首页访问
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		
		String search_first_flag = (String) dynaBean.get("search_first_flag");// 首次查询标识

		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String customer_par_index = (String) dynaBean.get("customer_par_index");// 客户类型
		String c_index = (String) dynaBean.get("c_index");// 客户细分类型
		String label_db = (String) dynaBean.get("label_db");// 是否大板
		String label_int = (String) dynaBean.get("label_int");// 是否智能（安卓）
		String size_sec = (String) dynaBean.get("size_sec");// 规格尺寸
		String time_type = (String) dynaBean.get("time_type");// 时间维度
		String label_3d = (String) dynaBean.get("label_3d");// 3D电视
		String label_www = (String) dynaBean.get("label_www");// 网络电视
		String label_4k = (String) dynaBean.get("label_4k");// 4K
		String label_ytv = (String) dynaBean.get("label_ytv");// 易TV
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		String model_name = (String) dynaBean.get("model_name");// 型号
		String model_name_like = (String) dynaBean.get("model_name_like");// 自定义型号

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处
		String md_serise_like = (String) dynaBean.get("md_serise_like");// 产品系列
		String md_serise = (String) dynaBean.get("md_serise");// 产品系列
		String kh_name_like = (String) dynaBean.get("kh_name_like");// 客户名称
		String r3_code = (String) dynaBean.get("r3_code");// R3编码
		
		String t_dept_id = (String) dynaBean.get("t_dept_id");// 从部门维度传过来的部门id
		String dept_level = (String) dynaBean.get("dept_level");// 传过来的部门级别
		String excel_to_all = (String) dynaBean.get("excel_to_all");// 导出标识

		String dept_id_in = "";

		// 禁止促销员访问
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		request.setAttribute("user_id", peProdUser.getId());
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		request.setAttribute("peRoleUserList", peRoleUserList);

		// boolean role_id_ge_1000_le_1100 = false;
		boolean role_id_eq_188 = false; // 促销员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
		}
		
		if (role_id_eq_188) {
			// super.renderHtml(response, "Bad request. No permissions.");
			return null;
		}
		// 禁止促销员访问 结束
		
		if (peProdUser.getUser_type() == 0) {
			// 康佳总部
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (null != konkaDept.getDept_type() && konkaDept.getDept_type() == 3) {

				dept_id = String.valueOf(peProdUser.getDept_id());
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
			} else {
				// 经营处、办事处
				KonkaDept deptfgs = getKonkaDeptForFgs(peProdUser.getDept_id());
				if (null != deptfgs) {
					dept_id = String.valueOf(deptfgs.getDept_id());
				}
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
				List<KonkaDept> deptList = super.getKonkaDeptListOfUser(peProdUser.getId(), true);
				for (KonkaDept dept : deptList) {
					if (dept_id_in != null && !dept_id_in.equals("")) {
						dept_id_in = dept_id_in + ",";
					}
					dept_id_in = dept_id_in == null ? "" : dept_id_in + dept.getDept_id() + "";
				}
			}
		}

		VADetailsOfSalesData entity = new VADetailsOfSalesData();
		entity.setDept_id_in(dept_id_in);// 设置默认访问部门权限
		if (StringUtils.isNotBlank(fgs_dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(fgs_dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("fgs_dept_id_name", k.getDept_name());

			entity.setDept_id(new Long(fgs_dept_id));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("dept_name", k.getDept_name());

			entity.setDept_id(new Long(dept_id));
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(l4_dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("l4_dept_name", k.getDept_name());

			entity.setL4_dept_id(new Long(l4_dept_id));
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(l5_dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("l5_dept_name", k.getDept_name());

			entity.setL5_dept_id(new Long(l5_dept_id));
		}
		
		if (StringUtils.isNotBlank(t_dept_id)) {
			if("2".equals(dept_level)){
				KonkaDept k = new KonkaDept();
				k.setDept_id(new Long(t_dept_id));
				k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
				request.setAttribute("l4_dept_name", k.getDept_name());

				entity.setL4_dept_id(new Long(t_dept_id));
			}else if("3".equals(dept_level)){
				KonkaDept k = new KonkaDept();
				k.setDept_id(new Long(t_dept_id));
				k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
				request.setAttribute("l5_dept_name", k.getDept_name());

				entity.setL5_dept_id(new Long(t_dept_id));
				
				//取上级部门
				KonkaDept k1 = new KonkaDept();
				k1.setDept_id(new Long(k.getPar_id()));
				k1 = super.getFacade().getKonkaDeptService().getKonkaDept(k1);
				request.setAttribute("l4_dept_name", k1.getDept_name());

				entity.setL4_dept_id(new Long(k.getPar_id()));
			}
		}

		if (StringUtils.isNotBlank(customer_par_index)) {
			KonkaCategory konkaCategory = new KonkaCategory();
			konkaCategory.setPar_index(customer_par_index);
			konkaCategory.setC_type(10);
			List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
					.getKonkaCategoryList(konkaCategory);
			if (null != konkaCategoryList && konkaCategoryList.size() > 0) {
				konkaCategory = konkaCategoryList.get(0);
				request.setAttribute("customer_par_index_name", konkaCategory.getC_comm());
			}

			entity.setPar_customer_type(customer_par_index);
		}
		if (StringUtils.isNotBlank(c_index)) {
			KonkaCategory konkaCategory = new KonkaCategory();
			konkaCategory.setC_index(new Long(c_index));
			konkaCategory = super.getFacade().getKonkaCategoryService().getKonkaCategory(konkaCategory);
			request.setAttribute("c_index_name", konkaCategory.getC_name());

			entity.setCustomer_type(c_index);
		}
		if (StringUtils.isNotBlank(label_db)) {
			if ("1".equals(label_db)) {
				request.setAttribute("label_db_name", "是");
			} else if ("0".equals(label_db)) {
				request.setAttribute("label_db_name", "否");
			}

			entity.setLabel_db(new Integer(label_db));
		}
		if (StringUtils.isNotBlank(label_int)) {
			if ("1".equals(label_int)) {
				request.setAttribute("label_int_name", "是");
			} else if ("0".equals(label_int)) {
				request.setAttribute("label_int_name", "否");
			}

			entity.setLabel_int(new Integer(label_int));
		}
		if (StringUtils.isNotBlank(size_sec)) {
			//设置单个尺寸段分类到request范围
			super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSec(request,"size_sec_name","100023", size_sec);
			
			entity.setSize_sec(new Integer(size_sec));
		}
		if (StringUtils.isNotBlank(label_3d)) {
			if ("1".equals(label_3d)) {
				request.setAttribute("label_3d_name", "是");
			} else if ("0".equals(label_3d)) {
				request.setAttribute("label_3d_name", "否");
			}

			entity.setLabel_3d(new Integer(label_3d));
		}
		if (StringUtils.isNotBlank(label_www)) {
			if ("1".equals(label_www)) {
				request.setAttribute("label_www_name", "是");
			} else if ("0".equals(label_www)) {
				request.setAttribute("label_www_name", "否");
			}

			entity.setLabel_www(new Integer(label_www));
		}
		if (StringUtils.isNotBlank(label_4k)) {
			if ("1".equals(label_4k)) {
				request.setAttribute("label_4k_name", "是");
			} else if ("0".equals(label_4k)) {
				request.setAttribute("label_4k_name", "否");
			}
			entity.getMap().put("is_4k", new Integer(label_4k));
		}
		
		//产品属性是否为易TV
		if (StringUtils.isNotBlank(label_ytv)) {
			if ("1".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "是");
			} else if ("0".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "否");
			}
			entity.getMap().put("is_ytv", new Integer(label_ytv));
		}
				
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(model_name)) {
			entity.setModel_name(model_name);
		}
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if (StringUtils.isNotBlank(md_serise_like)) {
			entity.getMap().put("md_serise_like", md_serise_like);
		}
		if (StringUtils.isNotBlank(md_serise)) {
			entity.setMd_serise(md_serise);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setCustomer_r3_code(r3_code);
		}
		if (StringUtils.isNotBlank(kh_name_like)) {
			entity.getMap().put("kh_name_like", kh_name_like);
		}

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			if (StringUtils.isNotBlank(dept_id)) {
				entity.getMap().put("dept_id_start", dept_id);
			}

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
		
		if (StringUtils.isNotBlank(search_first_flag) && "1".equals(search_first_flag)) {
			List<HashMap> entityList = super.getFacade().getVADetailsOfSalesDataService()
							.getVADetailsOfSalesDataListForMap(entity);
			HashMap map = entityList.get(entityList.size()-1);
			entityList.remove(entityList.size()-1);
			
			if(null!=map){
				request.setAttribute("TOTAL_IN_NUM", map.get("TOTAL_IN_NUM"));
				request.setAttribute("TOTAL_IN_MONEY", map.get("TOTAL_IN_MONEY"));
				request.setAttribute("TOTAL_OUT_NUM", map.get("TOTAL_OUT_NUM"));
				request.setAttribute("TOTAL_OUT_MONEY", map.get("TOTAL_OUT_MONEY"));
				request.setAttribute("TOTAL_STORE_NUM", map.get("TOTAL_STORE_NUM"));
				request.setAttribute("TOTAL_STORE_MON", map.get("TOTAL_STORE_MON"));
			}
			request.setAttribute("entityList", entityList);
			if("1".equals(excel_to_all)){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("门店列表")
						+ ".xls");
				request.setAttribute("entityList", entityList);
				return new ActionForward("/admin/KonkaMobileSailDataSearch/listForReport.jsp");
			}
		}
		
		// 分公司列表
		KonkaDept k = new KonkaDept();
		k.setDept_type(3);
		k.setPar_id(new Long("0"));
		if (StringUtils.isNotBlank(dept_id)) {
			k.setDept_id(new Long(dept_id));
		}
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
		request.setAttribute("konkaDeptList", konkaDeptList);

		// 客户类型
		KonkaCategory konkaCategory = new KonkaCategory();
		List<KonkaCategory> konkaCategoryList = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryGroupByCCommList(konkaCategory);
		request.setAttribute("konkaCategoryList", konkaCategoryList);

		// 客户细分类型
		KonkaCategory kcg = new KonkaCategory();
		if (StringUtils.isNotBlank(customer_par_index)) {
			kcg.setPar_index(customer_par_index);
		} else {
			kcg.setC_type(10);
			kcg.setC_index(new Long(-9999));
		}
		List<KonkaCategory> konkaCategoryListForDetail = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryList(kcg);
		request.setAttribute("konkaCategoryListForDetail", konkaCategoryListForDetail);

		// 产品系列
		md_serise_list = properties.getProperty(key);
		String[] array = md_serise_list.split(",");
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			arrayList.add(array[i]);
		}
		request.setAttribute("mdSeriseList", arrayList);

		entity.setIs_del(0);

		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		return mapping.findForward("list");
	}

	public List<?> getKonkaMobileSailDataForSum(VADetailsOfSalesData entity) {
		List<String> array = new ArrayList<String>();
		String sql = " select value(sum(a1.sale_num),0),value(sum(a1.sale_price),0),value(sum(a2.r3_num),0),value(sum(a2.r3_price),0) from (select t.CUSTOMER_R3_CODE,value(sum(t.NUM),0) as sale_num,value(sum(t.ALL_PRICE),0) as sale_price from V_A_DETAILS_OF_SALES_DATA_SUM_C t ";
		sql += " where 1 = 1 and t.IS_DEL = 0 ";
		if (entity.getIs_del() != null) {
			sql += " and t.IS_DEL = ? ";
			array.add(String.valueOf(entity.getIs_del()));
		}
		if (StringUtils.isNotBlank(entity.getDept_id_in())) {
			sql += " and t.cur_DEPT_ID in (" + entity.getDept_id_in() + ") ";
		}
		if (entity.getDept_id() != null) {
			sql += " and t.DEPT_ID = ? ";
			array.add(String.valueOf(entity.getDept_id()));
		}
		if (entity.getL4_dept_id() != null) {
			sql += " and t.L4_DEPT_ID = ? ";
			array.add(String.valueOf(entity.getL4_dept_id()));
		}
		if (entity.getL5_dept_id() != null) {
			sql += " and t.L5_DEPT_ID = ? ";
			array.add(String.valueOf(entity.getL5_dept_id()));
		}
		if (StringUtils.isNotBlank(entity.getPar_customer_type())) {
			sql += " and t.PAR_CUSTOMER_TYPE = ? ";
			array.add(entity.getPar_customer_type());
		}
		if (StringUtils.isNotBlank(entity.getCustomer_type())) {
			sql += " and t.CUSTOMER_TYPE = ? ";
			array.add(entity.getCustomer_type());
		}
		if (entity.getLabel_db() != null) {
			sql += " and t.LABEL_DB = ? ";
			array.add(String.valueOf(entity.getLabel_db()));
		}
		if (entity.getLabel_int() != null) {
			sql += " and t.LABEL_INT = ? ";
			array.add(String.valueOf(entity.getLabel_int()));
		}
		if (entity.getSize_sec() != null) {
			sql += " and t.SIZE_SEC = ? ";
			array.add(String.valueOf(entity.getSize_sec()));
		}
		if (entity.getMap().get("this_date") != null) {
			sql += " and ? = to_char(t.ALE_DATE, 'yyyy-MM-dd') ";
			array.add(entity.getMap().get("this_date").toString());
		}
		if (StringUtils.isNotBlank(entity.getY())) {
			sql += " and t.y = ? ";
			array.add(entity.getY());
		}
		if (StringUtils.isNotBlank(entity.getM())) {
			sql += " and t.m = ? ";
			array.add(entity.getM());
		}
		if (StringUtils.isNotBlank(entity.getQ())) {
			sql += " and t.q = ? ";
			array.add(entity.getQ());
		}
		if (StringUtils.isNotBlank(entity.getWw())) {
			sql += " and t.ww = ? ";
			array.add(entity.getWw());
		}
		if (entity.getLabel_3d() != null) {
			sql += " and t.LABEL_3D = ? ";
			array.add(String.valueOf(entity.getLabel_3d()));
		}
		if (entity.getLabel_www() != null) {
			sql += " and t.LABEL_WWW = ? ";
			array.add(String.valueOf(entity.getLabel_www()));
		}
		if (entity.getMap().get("date_begin") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_begin").toString())) {
			sql += " and t.SALE_DATE >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_begin").toString() + " 00:00:00");
		}
		if (entity.getMap().get("date_end") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_end").toString())) {
			sql += " and t.SALE_DATE <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_end").toString() + " 23:59:59");
		}
		if (StringUtils.isNotBlank(entity.getModel_name())) {
			sql += " and t.MODEL_NAME = ? ";
			array.add(entity.getModel_name());
		}
		if (entity.getMap().get("model_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("model_name_like").toString())) {
			sql += " and t.MODEL_NAME like '%' ||?|| '%' ";
			array.add(entity.getMap().get("model_name_like").toString());
		}
		if (entity.getMap().get("md_serise_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("md_serise_like").toString())) {
			sql += " and UPPER(t.MD_SERISE) like '%' ||UPPER(?)|| '%' ";
			array.add(entity.getMap().get("md_serise_like").toString());
		}
		if (entity.getMd_serise() != null && StringUtils.isNotBlank(entity.getMd_serise())) {
			sql += " and t.MD_SERISE = ? ";
			array.add(entity.getMd_serise());
		}
		if (entity.getMap().get("md_serise_in") != null
				&& StringUtils.isNotBlank(entity.getMap().get("md_serise_in").toString())) {
			sql += " and t.MD_SERISE in (" + entity.getMap().get("md_serise_in").toString() + ") ";
			// array.add(entity.getMap().get("md_serise_in").toString());
		}
		sql += " group by t.CUSTOMER_R3_CODE) a1 left JOIN ";

		sql += " (select t1.R3_CODE as CUSTOMER_R3_CODE,value(sum(t.column_12),0) as r3_num,value(sum(t.column_14),0) as r3_price FROM MV_ORG_OF_CUSTOMER t1 "
				+ "left join CHANNEL_DATA_IMPORT t on t.column_1 = t1.R3_CODE ,KONKA_PE_PD_MODEL t2 where t.column_11 = t2.MD_NAME ";

		if (StringUtils.isNotBlank(entity.getDept_id_in())) {
			sql += " and t1.cur_DEPT_ID in (?) ";
			array.add(entity.getDept_id_in());
		}
		if (entity.getDept_id() != null) {
			sql += " and t1.DEPT_ID = ? ";
			array.add(String.valueOf(entity.getDept_id()));
		}
		if (entity.getL4_dept_id() != null) {
			sql += " and t1.L4_DEPT_ID = ? ";
			array.add(String.valueOf(entity.getL4_dept_id()));
		}
		if (entity.getL5_dept_id() != null) {
			sql += " and t1.L5_DEPT_ID = ? ";
			array.add(String.valueOf(entity.getL5_dept_id()));
		}
		if (StringUtils.isNotBlank(entity.getPar_customer_type())) {
			sql += " and t1.PAR_CUSTOMER_TYPE = ? ";
			array.add(entity.getPar_customer_type());
		}
		if (StringUtils.isNotBlank(entity.getCustomer_type())) {
			sql += " and t1.CUSTOMER_TYPE = ? ";
			array.add(entity.getCustomer_type());
		}
		if (entity.getLabel_db() != null) {
			sql += " and decode (t2.size_sec, 5, 1, 6, 1,  7, 1, 8, 1, 9, 1, 0)  = ? ";
			array.add(String.valueOf(entity.getLabel_db()));
		}
		if (entity.getLabel_int() != null) {
			sql += " and t2.LABEL_INT = ? ";
			array.add(String.valueOf(entity.getLabel_int()));
		}
		if (entity.getSize_sec() != null) {
			sql += " and t2.SIZE_SEC = ? ";
			array.add(String.valueOf(entity.getSize_sec()));
		}
		if (entity.getMap().get("this_date") != null) {
			sql += " and ? = to_char(t.column_26, 'yyyy-MM-dd') ";
			array.add(entity.getMap().get("this_date").toString());
		}
		if (StringUtils.isNotBlank(entity.getY())) {
			sql += " and to_char(t.column_26, 'yyyy') = ? ";
			array.add(entity.getY());
		}
		if (StringUtils.isNotBlank(entity.getM())) {
			sql += " and to_char(t.column_26, 'mm') = ? ";
			array.add(entity.getM());
		}
		if (StringUtils.isNotBlank(entity.getQ())) {
			sql += " and to_char(t.column_26, 'q') = ? ";
			array.add(entity.getQ());
		}
		if (StringUtils.isNotBlank(entity.getWw())) {
			sql += " and to_char(t.column_26, 'ww') = ? ";
			array.add(entity.getWw());
		}
		if (entity.getLabel_3d() != null) {
			sql += " and t2.LABEL_3D = ? ";
			array.add(String.valueOf(entity.getLabel_3d()));
		}
		if (entity.getLabel_www() != null) {
			sql += " and t2.LABEL_WWW = ? ";
			array.add(String.valueOf(entity.getLabel_www()));
		}
		if (entity.getMap().get("date_begin") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_begin").toString())) {
			sql += " and t.column_26 >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_begin").toString() + " 00:00:00");
		}
		if (entity.getMap().get("date_end") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_end").toString())) {
			sql += " and t.column_26 <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_end").toString() + " 23:59:59");
		}
		if (StringUtils.isNotBlank(entity.getModel_name())) {
			sql += " and t2.MODEL_NAME = ? ";
			array.add(entity.getModel_name());
		}
		if (entity.getMap().get("model_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("model_name_like").toString())) {
			sql += " and UPPER(T2.MD_NAME) like '%' ||UPPER(?)|| '%' ";
			array.add(entity.getMap().get("model_name_like").toString());
		}
		if (entity.getMap().get("md_serise_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("md_serise_like").toString())) {
			sql += " and UPPER(t2..MD_SERISE) like '%' ||UPPER(?)|| '%' ";
			array.add(entity.getMap().get("md_serise_like").toString());
		}
		if (entity.getMd_serise() != null && StringUtils.isNotBlank(entity.getMd_serise())) {
			sql += " and t2.MD_SERISE = ? ";
			array.add(entity.getMd_serise());
		}
		if (entity.getMap().get("md_serise_in") != null
				&& StringUtils.isNotBlank(entity.getMap().get("md_serise_in").toString())) {
			sql += " and t2.MD_SERISE in (" + entity.getMap().get("md_serise_in").toString() + ") ";
			// array.add(entity.getMap().get("md_serise_in").toString());
		}
		sql += " group by t1.R3_CODE ) a2 ON a1.CUSTOMER_R3_CODE = a2.CUSTOMER_R3_CODE";
		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}

	public List<?> getChannelDataImportForSum(VADetailsOfSalesData entity) {
		List<String> array = new ArrayList<String>();
		String sql = " select value(sum(t.column_12),0) as NUM,value(sum(t.column_14),0) as ALL_PRICE FROM MV_ORG_OF_CUSTOMER t1 "
				+ "left join CHANNEL_DATA_IMPORT t on t.column_1 = t1.R3_CODE ,KONKA_PE_PD_MODEL t2 where t.column_11 = t2.MD_NAME ";

		if (StringUtils.isNotBlank(entity.getDept_id_in())) {
			sql += " and t1.cur_DEPT_ID in (?) ";
			array.add(entity.getDept_id_in());
		}
		if (entity.getDept_id() != null) {
			sql += " and t1.DEPT_ID = ? ";
			array.add(String.valueOf(entity.getDept_id()));
		}
		if (entity.getL4_dept_id() != null) {
			sql += " and t1.L4_DEPT_ID = ? ";
			array.add(String.valueOf(entity.getL4_dept_id()));
		}
		if (entity.getL5_dept_id() != null) {
			sql += " and t1.L5_DEPT_ID = ? ";
			array.add(String.valueOf(entity.getL5_dept_id()));
		}
		if (StringUtils.isNotBlank(entity.getPar_customer_type())) {
			sql += " and t1.PAR_CUSTOMER_TYPE = ? ";
			array.add(entity.getPar_customer_type());
		}
		if (StringUtils.isNotBlank(entity.getCustomer_type())) {
			sql += " and t1.CUSTOMER_TYPE = ? ";
			array.add(entity.getCustomer_type());
		}
		if (entity.getLabel_db() != null) {
			sql += " and decode (t2.size_sec, 5, 1, 6, 1,  7, 1, 8, 1, 9, 1, 0)  = ? ";
			array.add(String.valueOf(entity.getLabel_db()));
		}
		if (entity.getLabel_int() != null) {
			sql += " and t2.LABEL_INT = ? ";
			array.add(String.valueOf(entity.getLabel_int()));
		}
		if (entity.getSize_sec() != null) {
			sql += " and t2.SIZE_SEC = ? ";
			array.add(String.valueOf(entity.getSize_sec()));
		}
		if (entity.getMap().get("this_date") != null) {
			sql += " and ? = to_char(t.column_26, 'yyyy-MM-dd') ";
			array.add(entity.getMap().get("this_date").toString());
		}
		if (StringUtils.isNotBlank(entity.getY())) {
			sql += " and to_char(t.column_26, 'yyyy') = ? ";
			array.add(entity.getY());
		}
		if (StringUtils.isNotBlank(entity.getM())) {
			sql += " and to_char(t.column_26, 'mm') = ? ";
			array.add(entity.getM());
		}
		if (StringUtils.isNotBlank(entity.getQ())) {
			sql += " and to_char(t.column_26, 'q') = ? ";
			array.add(entity.getQ());
		}
		if (StringUtils.isNotBlank(entity.getWw())) {
			sql += " and to_char(t.column_26, 'ww') = ? ";
			array.add(entity.getWw());
		}
		if (entity.getLabel_3d() != null) {
			sql += " and t2.LABEL_3D = ? ";
			array.add(String.valueOf(entity.getLabel_3d()));
		}
		if (entity.getLabel_www() != null) {
			sql += " and t2.LABEL_WWW = ? ";
			array.add(String.valueOf(entity.getLabel_www()));
		}
		if (entity.getMap().get("date_begin") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_begin").toString())) {
			sql += " and t.column_26 >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_begin").toString() + " 00:00:00");
		}
		if (entity.getMap().get("date_end") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_end").toString())) {
			sql += " and t.column_26 <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_end").toString() + " 23:59:59");
		}
		if (StringUtils.isNotBlank(entity.getModel_name())) {
			sql += " and t2.MODEL_NAME = ? ";
			array.add(entity.getModel_name());
		}
		if (entity.getMap().get("model_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("model_name_like").toString())) {
			sql += " and UPPER(T2.MD_NAME) like '%' ||UPPER(?)|| '%' ";
			array.add(entity.getMap().get("model_name_like").toString());
		}
		if (entity.getMap().get("md_serise_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("md_serise_like").toString())) {
			sql += " and UPPER(t2..MD_SERISE) like '%' ||UPPER(?)|| '%' ";
			array.add(entity.getMap().get("md_serise_like").toString());
		}
		if (entity.getMd_serise() != null && StringUtils.isNotBlank(entity.getMd_serise())) {
			sql += " and t2.MD_SERISE = ? ";
			array.add(entity.getMd_serise());
		}
		if (entity.getMap().get("md_serise_in") != null
				&& StringUtils.isNotBlank(entity.getMap().get("md_serise_in").toString())) {
			sql += " and t2.MD_SERISE in (" + entity.getMap().get("md_serise_in").toString() + ") ";
			// array.add(entity.getMap().get("md_serise_in").toString());
		}
		log.info("**********sql:" + sql);
		if (null != array && array.size() > 0) {
			String array_string = "";
			for (int i = 0; i < array.size(); i++) {
				array_string += array.get(i) + ",";
			}
			log.info("**********array:" + array.size());
			log.info("**********array_string:" + array_string);
		}

		List<?> list = super.getFacade().getBaseReportService().getBaseReportForBindToArray(sql, array.toArray());

		return list;
	}
	
	/**
	 * 点击客户，查询型号进销存信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForModels(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String label_db = (String) dynaBean.get("label_db");// 是否大板
		String label_int = (String) dynaBean.get("label_int");// 是否智能（安卓）
		String size_sec = (String) dynaBean.get("size_sec");// 规格尺寸
		String label_3d = (String) dynaBean.get("label_3d");// 3D电视
		String label_4k = (String) dynaBean.get("label_4k");// 4K
		String label_ytv = (String) dynaBean.get("label_ytv");// 易TV
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		String model_name = (String) dynaBean.get("model_name");// 型号
		String model_name_like = (String) dynaBean.get("model_name_like");// 自定义型号
		String md_serise_like = (String) dynaBean.get("md_serise_like");// 产品系列
		String md_serise = (String) dynaBean.get("md_serise");// 产品系列
		String r3_code = (String) dynaBean.get("r3_code");// R3编码
		String s_kh_name = java.net.URLDecoder.decode((String) dynaBean.get("s_kh_name"),"UTF-8");// 客户名称
		String s_r3_id = (String) dynaBean.get("s_r3_id");// 客户id
		String s_r3_code = (String) dynaBean.get("s_r3_code");// 客户r3编码
		
		VADetailsOfSalesData entity = new VADetailsOfSalesData();
		if (StringUtils.isNotBlank(s_r3_id)) {
			entity.getMap().put("s_r3_id", s_r3_id);
		}
		if (StringUtils.isNotBlank(s_r3_code)) {
			entity.getMap().put("s_r3_code", s_r3_code);
		}
		if (StringUtils.isNotBlank(label_db)) {
			if ("1".equals(label_db)) {
				request.setAttribute("label_db_name", "是");
			} else if ("0".equals(label_db)) {
				request.setAttribute("label_db_name", "否");
			}
			
			entity.setLabel_db(new Integer(label_db));
		}
		if (StringUtils.isNotBlank(label_int)) {
			if ("1".equals(label_int)) {
				request.setAttribute("label_int_name", "是");
			} else if ("0".equals(label_int)) {
				request.setAttribute("label_int_name", "否");
			}
			entity.setLabel_int(new Integer(label_int));
		}
		if (StringUtils.isNotBlank(size_sec)) {
			entity.setSize_sec(new Integer(size_sec));
		}
		if (StringUtils.isNotBlank(label_3d)) {
			if ("1".equals(label_3d)) {
				request.setAttribute("label_3d_name", "是");
			} else if ("0".equals(label_3d)) {
				request.setAttribute("label_3d_name", "否");
			}
			entity.setLabel_3d(new Integer(label_3d));
		}
		if (StringUtils.isNotBlank(label_4k)) {
			if ("1".equals(label_4k)) {
				request.setAttribute("label_4k_name", "是");
			} else if ("0".equals(label_4k)) {
				request.setAttribute("label_4k_name", "否");
			}
			entity.getMap().put("is_4k", new Integer(label_4k));
		}
		
		//产品属性是否为易TV
		if (StringUtils.isNotBlank(label_ytv)) {
			if ("1".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "是");
			} else if ("0".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "否");
			}
			entity.getMap().put("is_ytv", new Integer(label_ytv));
		}
				
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(model_name)) {
			entity.setModel_name(model_name);
		}
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if (StringUtils.isNotBlank(md_serise_like)) {
			entity.getMap().put("md_serise_like", md_serise_like);
		}
		if (StringUtils.isNotBlank(md_serise)) {
			entity.setMd_serise(md_serise);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setCustomer_r3_code(r3_code);
		}

		List<HashMap> entityList = super.getFacade().getVADetailsOfSalesDataService()
						.getCustomModelList(entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("kh_name", s_kh_name);
		request.setAttribute("r3_code", s_r3_code);
		request.setAttribute("r3_id", s_r3_id);
		request.setAttribute("date_begin", date_begin);
		request.setAttribute("date_end", date_end);

		return new ActionForward("/admin/KonkaMobileSailDataSearch/list_models.jsp");
	}

	/**
	 * 查询型号具体记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForModelDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String model_name = (String) dynaBean.get("model_name");// 型号
		String goods_id = (String) dynaBean.get("goods_id");// goods_id
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		String s_r3_code = (String) dynaBean.get("r3_code");// 客户r3编码
		String r3_id = (String) dynaBean.get("r3_id");// 客户r3_id
		String kh_name = java.net.URLDecoder.decode((String) dynaBean.get("kh_name"),"UTF-8");// 客户名称
		String flag = (String) dynaBean.get("flag");// 进货，销售标示
		
		VADetailsOfSalesData entity = new VADetailsOfSalesData();
		if (StringUtils.isNotBlank(model_name)) {
			entity.setModel_name(model_name);
		}
		if (StringUtils.isNotBlank(s_r3_code)) {
			entity.setCustomer_r3_code(s_r3_code);
		}
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(goods_id)) {
			entity.getMap().put("goods_id", goods_id);
		}
		if (StringUtils.isNotBlank(r3_id)) {
			entity.getMap().put("r3_id", r3_id);
		}

		List<HashMap> entityList = null;
		if("in".equals(flag)){//进货记录
			entityList = super.getFacade().getVADetailsOfSalesDataService()
					.getModelInDetailsList(entity);
		}else{//销售记录
			entityList = super.getFacade().getVADetailsOfSalesDataService()
					.getModelOutDetailsList(entity);
		}
		request.setAttribute("entityList", entityList);
		request.setAttribute("kh_name", kh_name);
		request.setAttribute("r3_code", s_r3_code);
		request.setAttribute("ope_type", flag);

		return new ActionForward("/admin/KonkaMobileSailDataSearch/list_model_details.jsp");
	}
	
	/**
	 * 按部门查询进销存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toSailDataByDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String search_first_flag = (String) dynaBean.get("search_first_flag");// 首次查询标识

		String label_db = (String) dynaBean.get("label_db");// 是否大板
		String label_int = (String) dynaBean.get("label_int");// 是否智能（安卓）
		String size_sec = (String) dynaBean.get("size_sec");// 规格尺寸
		String label_3d = (String) dynaBean.get("label_3d");// 3D电视
		String label_www = (String) dynaBean.get("label_www");// 网络电视
		String label_4k = (String) dynaBean.get("label_4k");// 4K
		String label_ytv = (String) dynaBean.get("label_ytv");// 易TV
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		String model_name_like = (String) dynaBean.get("model_name_like");// 自定义型号

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处
		String md_serise_like = (String) dynaBean.get("md_serise_like");// 产品系列
		String md_serise = (String) dynaBean.get("md_serise");// 产品系列

		String dept_id_in = "";

		// 禁止促销员访问
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		request.setAttribute("user_id", peProdUser.getId());
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		request.setAttribute("peRoleUserList", peRoleUserList);

		// boolean role_id_ge_1000_le_1100 = false;
		boolean role_id_eq_188 = false; // 促销员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
		}
		
		if (role_id_eq_188) {
			// super.renderHtml(response, "Bad request. No permissions.");
			return null;
		}
		// 禁止促销员访问 结束
		
		if (peProdUser.getUser_type() == 0) {
			// 康佳总部
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (null != konkaDept.getDept_type() && konkaDept.getDept_type() == 3) {

				dept_id = String.valueOf(peProdUser.getDept_id());
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
			} else {
				// 经营处、办事处
				KonkaDept deptfgs = getKonkaDeptForFgs(peProdUser.getDept_id());
				if (null != deptfgs) {
					dept_id = String.valueOf(deptfgs.getDept_id());
				}
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
				List<KonkaDept> deptList = super.getKonkaDeptListOfUser(peProdUser.getId(), true);
				for (KonkaDept dept : deptList) {
					if (dept_id_in != null && !dept_id_in.equals("")) {
						dept_id_in = dept_id_in + ",";
					}
					dept_id_in = dept_id_in == null ? "" : dept_id_in + dept.getDept_id() + "";
				}
			}
		}

		VADetailsOfSalesData entity = new VADetailsOfSalesData();
		entity.setDept_id_in(dept_id_in);// 设置默认访问部门权限
		if (StringUtils.isNotBlank(dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("dept_name", k.getDept_name());

			entity.setDept_id(new Long(dept_id));
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(l4_dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("l4_dept_name", k.getDept_name());

			entity.setL4_dept_id(new Long(l4_dept_id));
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(l5_dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("l5_dept_name", k.getDept_name());

			entity.setL5_dept_id(new Long(l5_dept_id));
		}

		if (StringUtils.isNotBlank(label_db)) {
			if ("1".equals(label_db)) {
				request.setAttribute("label_db_name", "是");
			} else if ("0".equals(label_db)) {
				request.setAttribute("label_db_name", "否");
			}

			entity.setLabel_db(new Integer(label_db));
		}
		if (StringUtils.isNotBlank(label_int)) {
			if ("1".equals(label_int)) {
				request.setAttribute("label_int_name", "是");
			} else if ("0".equals(label_int)) {
				request.setAttribute("label_int_name", "否");
			}

			entity.setLabel_int(new Integer(label_int));
		}
		if (StringUtils.isNotBlank(size_sec)) {
			//设置单个尺寸段分类到request范围
			super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSec(request,"size_sec_name","100023", size_sec);
			
			entity.setSize_sec(new Integer(size_sec));
		}
		if (StringUtils.isNotBlank(label_3d)) {
			if ("1".equals(label_3d)) {
				request.setAttribute("label_3d_name", "是");
			} else if ("0".equals(label_3d)) {
				request.setAttribute("label_3d_name", "否");
			}

			entity.setLabel_3d(new Integer(label_3d));
		}
		if (StringUtils.isNotBlank(label_www)) {
			if ("1".equals(label_www)) {
				request.setAttribute("label_www_name", "是");
			} else if ("0".equals(label_www)) {
				request.setAttribute("label_www_name", "否");
			}

			entity.setLabel_www(new Integer(label_www));
		}
		if (StringUtils.isNotBlank(label_4k)) {
			if ("1".equals(label_4k)) {
				request.setAttribute("label_4k_name", "是");
			} else if ("0".equals(label_4k)) {
				request.setAttribute("label_4k_name", "否");
			}
			entity.getMap().put("is_4k", new Integer(label_4k));
		}
		
		//产品属性是否为易TV
		if (StringUtils.isNotBlank(label_ytv)) {
			if ("1".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "是");
			} else if ("0".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "否");
			}
			entity.getMap().put("is_ytv", new Integer(label_ytv));
		}
				
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if (StringUtils.isNotBlank(md_serise_like)) {
			entity.getMap().put("md_serise_like", md_serise_like);
		}
		if (StringUtils.isNotBlank(md_serise)) {
			entity.setMd_serise(md_serise);
		}

		// 分公司列表
		KonkaDept k = new KonkaDept();
		k.setDept_type(3);
		k.setPar_id(new Long("0"));
		if (StringUtils.isNotBlank(dept_id)) {
			k.setDept_id(new Long(dept_id));
		}
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
		request.setAttribute("konkaDeptList", konkaDeptList);

		// 产品系列
		md_serise_list = properties.getProperty(key);
		String[] array = md_serise_list.split(",");
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			arrayList.add(array[i]);
		}
		request.setAttribute("mdSeriseList", arrayList);

		entity.setIs_del(0);
		if (StringUtils.isNotBlank(search_first_flag) && "1".equals(search_first_flag)) {
			List<HashMap> entityList = super.getFacade().getVADetailsOfSalesDataService()
							.getSalesDataOfDeptListForMap(entity);
			HashMap map = entityList.get(entityList.size()-1);
			entityList.remove(entityList.size()-1);
			
			if(null!=map){
				request.setAttribute("TOTAL_IN_NUM", map.get("TOTAL_IN_NUM"));
				request.setAttribute("TOTAL_IN_MONEY", map.get("TOTAL_IN_MONEY"));
				request.setAttribute("TOTAL_OUT_NUM", map.get("TOTAL_OUT_NUM"));
				request.setAttribute("TOTAL_OUT_MONEY", map.get("TOTAL_OUT_MONEY"));
				request.setAttribute("TOTAL_STORE_NUM", map.get("TOTAL_STORE_NUM"));
				request.setAttribute("TOTAL_STORE_MON", map.get("TOTAL_STORE_MON"));
			}
			request.setAttribute("entityList", entityList);
		}

		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		return new ActionForward("/admin/KonkaMobileSailDataSearch/list_for_dept.jsp");
	}
	
	/**
	 * 按渠道分类查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward toSailDataByChannel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String search_first_flag = (String) dynaBean.get("search_first_flag");// 首次查询标识
		
		String label_db = (String) dynaBean.get("label_db");// 是否大板
		String label_int = (String) dynaBean.get("label_int");// 是否智能（安卓）
		String size_sec = (String) dynaBean.get("size_sec");// 规格尺寸
		String label_3d = (String) dynaBean.get("label_3d");// 3D电视
		String label_www = (String) dynaBean.get("label_www");// 网络电视
		String label_4k = (String) dynaBean.get("label_4k");// 4K
		String label_ytv = (String) dynaBean.get("label_ytv");// 易TV
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		String model_name_like = (String) dynaBean.get("model_name_like");// 自定义型号
		
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处
		String md_serise_like = (String) dynaBean.get("md_serise_like");// 产品系列
		String md_serise = (String) dynaBean.get("md_serise");// 产品系列
		
		String dept_id_in = "";
		
		// 禁止促销员访问
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		
		request.setAttribute("user_id", peProdUser.getId());
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		request.setAttribute("peRoleUserList", peRoleUserList);
		
		// boolean role_id_ge_1000_le_1100 = false;
		boolean role_id_eq_188 = false; // 促销员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
		}
		
		if (role_id_eq_188) {
			// super.renderHtml(response, "Bad request. No permissions.");
			return null;
		}
		// 禁止促销员访问 结束
		
		if (peProdUser.getUser_type() == 0) {
			// 康佳总部
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (null != konkaDept.getDept_type() && konkaDept.getDept_type() == 3) {
				
				dept_id = String.valueOf(peProdUser.getDept_id());
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
			} else {
				// 经营处、办事处
				KonkaDept deptfgs = getKonkaDeptForFgs(peProdUser.getDept_id());
				if (null != deptfgs) {
					dept_id = String.valueOf(deptfgs.getDept_id());
				}
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
				List<KonkaDept> deptList = super.getKonkaDeptListOfUser(peProdUser.getId(), true);
				for (KonkaDept dept : deptList) {
					if (dept_id_in != null && !dept_id_in.equals("")) {
						dept_id_in = dept_id_in + ",";
					}
					dept_id_in = dept_id_in == null ? "" : dept_id_in + dept.getDept_id() + "";
				}
			}
		}
		
		VADetailsOfSalesData entity = new VADetailsOfSalesData();
		entity.setDept_id_in(dept_id_in);// 设置默认访问部门权限
		if (StringUtils.isNotBlank(dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("dept_name", k.getDept_name());
			
			entity.setDept_id(new Long(dept_id));
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(l4_dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("l4_dept_name", k.getDept_name());
			
			entity.setL4_dept_id(new Long(l4_dept_id));
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			KonkaDept k = new KonkaDept();
			k.setDept_id(new Long(l5_dept_id));
			k = super.getFacade().getKonkaDeptService().getKonkaDept(k);
			request.setAttribute("l5_dept_name", k.getDept_name());
			
			entity.setL5_dept_id(new Long(l5_dept_id));
		}
		
		if (StringUtils.isNotBlank(label_db)) {
			if ("1".equals(label_db)) {
				request.setAttribute("label_db_name", "是");
			} else if ("0".equals(label_db)) {
				request.setAttribute("label_db_name", "否");
			}
			
			entity.setLabel_db(new Integer(label_db));
		}
		if (StringUtils.isNotBlank(label_int)) {
			if ("1".equals(label_int)) {
				request.setAttribute("label_int_name", "是");
			} else if ("0".equals(label_int)) {
				request.setAttribute("label_int_name", "否");
			}
			
			entity.setLabel_int(new Integer(label_int));
		}
		if (StringUtils.isNotBlank(size_sec)) {
			//设置单个尺寸段分类到request范围
			super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSec(request,"size_sec_name","100023", size_sec);
			
			entity.setSize_sec(new Integer(size_sec));
		}
		if (StringUtils.isNotBlank(label_3d)) {
			if ("1".equals(label_3d)) {
				request.setAttribute("label_3d_name", "是");
			} else if ("0".equals(label_3d)) {
				request.setAttribute("label_3d_name", "否");
			}
			
			entity.setLabel_3d(new Integer(label_3d));
		}
		if (StringUtils.isNotBlank(label_www)) {
			if ("1".equals(label_www)) {
				request.setAttribute("label_www_name", "是");
			} else if ("0".equals(label_www)) {
				request.setAttribute("label_www_name", "否");
			}
			
			entity.setLabel_www(new Integer(label_www));
		}
		if (StringUtils.isNotBlank(label_4k)) {
			if ("1".equals(label_4k)) {
				request.setAttribute("label_4k_name", "是");
			} else if ("0".equals(label_4k)) {
				request.setAttribute("label_4k_name", "否");
			}
			entity.getMap().put("is_4k", new Integer(label_4k));
		}
		
		//产品属性是否为易TV
		if (StringUtils.isNotBlank(label_ytv)) {
			if ("1".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "是");
			} else if ("0".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "否");
			}
			entity.getMap().put("is_ytv", new Integer(label_ytv));
		}
		
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if (StringUtils.isNotBlank(md_serise_like)) {
			entity.getMap().put("md_serise_like", md_serise_like);
		}
		if (StringUtils.isNotBlank(md_serise)) {
			entity.setMd_serise(md_serise);
		}
		
		// 分公司列表
		KonkaDept k = new KonkaDept();
		k.setDept_type(3);
		k.setPar_id(new Long("0"));
		if (StringUtils.isNotBlank(dept_id)) {
			k.setDept_id(new Long(dept_id));
		}
		List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(k);
		request.setAttribute("konkaDeptList", konkaDeptList);
		
		// 产品系列
		md_serise_list = properties.getProperty(key);
		String[] array = md_serise_list.split(",");
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			arrayList.add(array[i]);
		}
		request.setAttribute("mdSeriseList", arrayList);
		
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(search_first_flag) && "1".equals(search_first_flag)) {
			List<HashMap> entityList = super.getFacade().getVADetailsOfSalesDataService()
					.getSalesDataOfChannelListForMap(entity);
			HashMap map = entityList.get(entityList.size()-1);
			entityList.remove(entityList.size()-1);
			
			if(null!=map){
				request.setAttribute("TOTAL_IN_NUM", map.get("TOTAL_IN_NUM"));
				request.setAttribute("TOTAL_IN_MONEY", map.get("TOTAL_IN_MONEY"));
				request.setAttribute("TOTAL_OUT_NUM", map.get("TOTAL_OUT_NUM"));
				request.setAttribute("TOTAL_OUT_MONEY", map.get("TOTAL_OUT_MONEY"));
				request.setAttribute("TOTAL_STORE_NUM", map.get("TOTAL_STORE_NUM"));
				request.setAttribute("TOTAL_STORE_MON", map.get("TOTAL_STORE_MON"));
			}
			request.setAttribute("entityList", entityList);
		}
		
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		
		return new ActionForward("/admin/KonkaMobileSailDataSearch/list_for_channel.jsp");
	}
	
	
	/**
	 * 查询细分类型数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listForChannels(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String label_db = (String) dynaBean.get("label_db");// 是否大板
		String label_int = (String) dynaBean.get("label_int");// 是否智能（安卓）
		String size_sec = (String) dynaBean.get("size_sec");// 规格尺寸
		String label_3d = (String) dynaBean.get("label_3d");// 3D电视
		String label_4k = (String) dynaBean.get("label_4k");// 4K
		String label_ytv = (String) dynaBean.get("label_ytv");// 易TV
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		String model_name = (String) dynaBean.get("model_name");// 型号
		String model_name_like = (String) dynaBean.get("model_name_like");// 自定义型号
		String md_serise_like = (String) dynaBean.get("md_serise_like");// 产品系列
		String md_serise = (String) dynaBean.get("md_serise");// 产品系列
		String par_index = (String) dynaBean.get("par_index");
		String c_comm = java.net.URLDecoder.decode((String) dynaBean.get("c_comm"),"UTF-8");// 大类名称
		
		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处
		
		VADetailsOfSalesData entity = new VADetailsOfSalesData();
		if (StringUtils.isNotBlank(dept_id)) {
			entity.setDept_id(new Long(dept_id));
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			entity.setL4_dept_id(new Long(l4_dept_id));
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			entity.setL5_dept_id(new Long(l5_dept_id));
		}
		if (StringUtils.isNotBlank(par_index)) {
			entity.getMap().put("par_index", par_index);
		}
		if (StringUtils.isNotBlank(label_db)) {
			if ("1".equals(label_db)) {
				request.setAttribute("label_db_name", "是");
			} else if ("0".equals(label_db)) {
				request.setAttribute("label_db_name", "否");
			}
			
			entity.setLabel_db(new Integer(label_db));
		}
		if (StringUtils.isNotBlank(label_int)) {
			if ("1".equals(label_int)) {
				request.setAttribute("label_int_name", "是");
			} else if ("0".equals(label_int)) {
				request.setAttribute("label_int_name", "否");
			}
			entity.setLabel_int(new Integer(label_int));
		}
		if (StringUtils.isNotBlank(size_sec)) {
			entity.setSize_sec(new Integer(size_sec));
		}
		if (StringUtils.isNotBlank(label_3d)) {
			if ("1".equals(label_3d)) {
				request.setAttribute("label_3d_name", "是");
			} else if ("0".equals(label_3d)) {
				request.setAttribute("label_3d_name", "否");
			}
			entity.setLabel_3d(new Integer(label_3d));
		}
		if (StringUtils.isNotBlank(label_4k)) {
			if ("1".equals(label_4k)) {
				request.setAttribute("label_4k_name", "是");
			} else if ("0".equals(label_4k)) {
				request.setAttribute("label_4k_name", "否");
			}
			entity.getMap().put("is_4k", new Integer(label_4k));
		}
		
		//产品属性是否为易TV
		if (StringUtils.isNotBlank(label_ytv)) {
			if ("1".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "是");
			} else if ("0".equals(label_ytv)) {
				request.setAttribute("label_ytv_name", "否");
			}
			entity.getMap().put("is_ytv", new Integer(label_ytv));
		}
				
		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}
		if (StringUtils.isNotBlank(model_name)) {
			entity.setModel_name(model_name);
		}
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if (StringUtils.isNotBlank(md_serise_like)) {
			entity.getMap().put("md_serise_like", md_serise_like);
		}
		if (StringUtils.isNotBlank(md_serise)) {
			entity.setMd_serise(md_serise);
		}

		List<HashMap> entityList = super.getFacade().getVADetailsOfSalesDataService()
						.getChannelDetailsList(entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("c_comm", c_comm);

		return new ActionForward("/admin/KonkaMobileSailDataSearch/list_channels.jsp");
	}
	
	
	/**
	 * 导出
	 */
	public ActionForward toExcelJXC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setCharacterEncoding(Constants.SYS_ENCODING);
		String name = request.getParameter("hiddenName");
		String html = request.getParameter("hiddenHtml");

		// 不判断是否为空，下面的 replaceAll方法报错
		if (StringUtils.isBlank(html)) {
			this.renderHtml(response, "no html content to export.");
			return null;
		}

		html = html.replaceAll("&lt;", "<");
		html = html.replaceAll("&gt;", ">");
		try {
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"" + name + ".xls\"");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<meta http-equiv=\"MSThemeCompatible\" content=\"no\" />");
			out.println("<meta name=\"MSSmartTagsPreventParsing\" content=\"true\" />");
			out.println("<title>" + name + "</title>");
			out.println("</head>");
			html = html.replace("border=0", "border=1");
			html = StringUtils.replace(html, "border=\"0\"", "border=\"1\"");
			// html = html.replace("<A href=\"[^>]*?\">([^<]*?)<\/A>", "$1");

			out.println("<body>");
			out.println(html);
			out.println("</body>");
			out.println("</html>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}