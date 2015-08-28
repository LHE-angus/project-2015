package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ebiz.mmt.domain.VADetailsOfSalesData;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Liu,ZhiXiang
 * @version 2013-11-22
 */
public class KonkaMobileSailDataSearchForWeekAction extends BaseAction {

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

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String search_first_flag = (String) dynaBean.get("search_first_flag");// 查询标识
		// String search_count = (String) dynaBean.get("search_count");// 查询数量

		// Pager pager = (Pager) dynaBean.get("pager");

		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String customer_par_index = (String) dynaBean.get("customer_par_index");// 客户类型
		String c_index = (String) dynaBean.get("c_index");// 客户细分类型
		// String label_db = (String) dynaBean.get("label_db");// 是否大板
		// String label_int = (String) dynaBean.get("label_int");// 是否智能
		// String size_sec = (String) dynaBean.get("size_sec");// 规格尺寸
		// String time_type = (String) dynaBean.get("time_type");// 时间维度
		// String label_3d = (String) dynaBean.get("label_3d");// 3D电视
		// String label_www = (String) dynaBean.get("label_www");// 网络电视
		// String label_4k = (String) dynaBean.get("label_4k");// 4K
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		// String model_name = (String) dynaBean.get("model_name");// 型号
		// String model_name_like = (String) dynaBean.get("model_name_like");// 自定义型号

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处

		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String customer_r3_code_like = (String) dynaBean.get("customer_r3_code_like");// R3编码
		// String store_name_like = (String) dynaBean.get("store_name_like");// 门店名称
		String ywy_user_name_like = (String) dynaBean.get("ywy_user_name_like");// 业务员
		// String md_serise_like = (String) dynaBean.get("md_serise_like");// 产品系列
		// String md_serise = (String) dynaBean.get("md_serise");// 产品系列

		String dept_id_in = "";

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
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

				// fgs_dept_id = String.valueOf(peProdUser.getDept_id());
				dept_id = String.valueOf(peProdUser.getDept_id());
				// dynaBean.set("fgs_dept_id", fgs_dept_id);
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
			} else {
				// 经营处、办事处
				// String msg = super.getMessage(request, "popedom.check.invalid");
				// super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				// return null;

				dept_id = getKonkaDeptForFgs(peProdUser.getDept_id()).getDept_id() + "";
				dynaBean.set("dept_id", dept_id);
				dynaBean.set("dept_type", "3");
				// dynaBean.set("dept_type", konkaDept.getDept_type());
				/*
				 * if(konkaDept.getDept_type() != null && konkaDept.getDept_type() ==4){
				 * request.setAttribute("l4_dept_name", konkaDept.getDept_name()); dynaBean.set("l4_dept_id",
				 * konkaDept.getDept_id().toString()); dynaBean.set("dept_type", "4"); } if(konkaDept.getDept_type() !=
				 * null && konkaDept.getDept_type() ==5){ dynaBean.set("l4_dept_id", konkaDept.getPar_id().toString());
				 * KonkaDept parDept = new KonkaDept(); parDept.setDept_id(konkaDept.getPar_id()); parDept =
				 * super.getFacade().getKonkaDeptService().getKonkaDept(parDept); dynaBean.set("l4_dept_name",
				 * parDept.getDept_name()); request.setAttribute("l5_dept_name", konkaDept.getDept_name());
				 * dynaBean.set("l5_dept_id", konkaDept.getDept_id().toString()); dynaBean.set("dept_type", "5"); }
				 */
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

		if (StringUtils.isNotBlank(date_begin)) {
			entity.getMap().put("date_begin", date_begin);
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", date_end);
		}

		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(customer_r3_code_like)) {
			entity.getMap().put("customer_r3_code_like", customer_r3_code_like);
		}
		if (StringUtils.isNotBlank(ywy_user_name_like)) {
			entity.getMap().put("ywy_user_name_like", ywy_user_name_like);
		}

		// if (StringUtils.isNotBlank(search_count)) {
		// entity.getRow().setCount(new Integer(search_count));
		// }

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
		// konkaCategory.setC_type(10);
		// konkaCategory.getMap().put("group_by_par_index", true);
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
		// kcg.setIs_del(0);

		List<KonkaCategory> konkaCategoryListForDetail = super.getFacade().getKonkaCategoryService()
				.getKonkaCategoryList(kcg);
		request.setAttribute("konkaCategoryListForDetail", konkaCategoryListForDetail);

		entity.setIs_del(0);
		if (StringUtils.isNotBlank(search_first_flag) && "1".equals(search_first_flag)) {

			List<?> list = getKonkaMobileSailDataList(entity);
			request.setAttribute("entityList", list);

		}

		return mapping.findForward("list");
	}

	public List<?> getKonkaMobileSailDataList(VADetailsOfSalesData entity) {
		List<String> array = new ArrayList<String>();

		String sql = " SELECT a3.* FROM (SELECT decode (a2.sale_ww, NULL, b2.r3_ww, a2.sale_ww) AS ww, ";
		sql += "                         round(value (b2.R3_PRICE, 0)/10000,2) AS R3_PRICE, value (b2.R3_NUM, 0) AS R3_NUM, value (b2.R3_SINGLE_PRICE, '0') AS R3_SINGLE_PRICE, ";
		sql += "                         value (b2.R3_SCALE, '0') AS R3_SCALE, round(value (a2.ALL_PRICE, 0)/10000,2) AS ALL_PRICE, value (a2.ALL_NUM, 0) AS ALL_NUM, ";
		sql += "                         value (a2.SALE_SINGLE_PRICE, '0') AS SALE_SINGLE_PRICE, value (a2.SALE_SCALE, '0') AS SALE_SCALE ";
		sql += "        			     FROM    (SELECT c1.sale_ww, c1.all_price, c1.all_num, ";
		sql += "                                  decode (to_char (c1.all_num), 0, '-', to_char (round (c1.all_price / c1.all_num, 2))) AS SALE_SINGLE_PRICE, ";
		sql += "                                  decode (to_char (d1.all_num), 0, '-', to_char (round (c1.all_num * 100 / d1.all_num, 4))) AS sale_scale ";
		sql += "                                  FROM    (SELECT t.WW AS sale_ww, value (sum (t.ALL_PRICE), 0) AS all_price, value (sum (t.NUM), 0) AS all_num, 1 AS flag ";
		sql += "                                           FROM V_A_DETAILS_OF_SALES_DATA t WHERE 1 = 1 ";

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
		if (entity.getMap().get("customer_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_name_like").toString())) {
			sql += " and t.CUSTOMER_NAME like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_name_like").toString());
		}
		if (entity.getMap().get("customer_r3_code_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_r3_code_like").toString())) {
			sql += " and t.CUSTOMER_R3_CODE like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_r3_code_like").toString());
		}
		if (entity.getMap().get("ywy_user_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("ywy_user_name_like").toString())) {
			sql += " and t.ywy_user_name like '%' ||?|| '%' ";
			array.add(entity.getMap().get("ywy_user_name_like").toString());
		}

		sql += "    GROUP BY t.WW) c1 LEFT JOIN (SELECT 1 AS flag, value (sum (t.ALL_PRICE), 0) AS all_price, value (sum (t.NUM), 0) AS all_num ";
		sql += "                                 FROM V_A_DETAILS_OF_SALES_DATA t WHERE 1 = 1 ";

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
		if (entity.getMap().get("customer_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_name_like").toString())) {
			sql += " and t.CUSTOMER_NAME like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_name_like").toString());
		}
		if (entity.getMap().get("customer_r3_code_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_r3_code_like").toString())) {
			sql += " and t.CUSTOMER_R3_CODE like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_r3_code_like").toString());
		}
		if (entity.getMap().get("ywy_user_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("ywy_user_name_like").toString())) {
			sql += " and t.ywy_user_name like '%' ||?|| '%' ";
			array.add(entity.getMap().get("ywy_user_name_like").toString());
		}

		sql += "	) d1 ON c1.flag = d1.flag) a2 FULL OUTER JOIN ";
		sql += "      (SELECT c1.r3_ww, c1.r3_price, c1.r3_num, decode (to_char (c1.r3_num), 0, '-', to_char (round (c1.r3_price / c1.r3_num, 2))) AS R3_SINGLE_PRICE, ";
		sql += "              decode (to_char (d1.r3_num), 0, '-', to_char (round (c1.r3_num * 100 / d1.r3_num, 4))) AS r3_scale ";
		sql += "       FROM    (SELECT a1.r3_ww, sum (a1.r3_num) AS r3_num, sum (a1.r3_price) AS r3_price, 1 AS flag ";
		sql += "                FROM (SELECT week(t1.column_26) AS r3_ww, value (t1.column_12, 0) AS r3_num, ";
		sql += "                             value (t1.column_14, 0) AS r3_price ";
		sql += "                      FROM CHANNEL_DATA_IMPORT t1 LEFT JOIN MV_ORG_OF_CUSTOMER t ";
		sql += "                      ON t1.column_1 = t.R3_CODE LEFT JOIN KONKA_PE_PD_MODEL t2 ";
		sql += "                      ON t1.column_11 = t2.MD_NAME WHERE 1 = 1  and t1.column_12<>0  ";

		if (StringUtils.isNotBlank(entity.getDept_id_in())) {
			sql += " and t.CUR_DEPT_ID in (" + entity.getDept_id_in() + ") ";
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
		if (entity.getMap().get("date_begin") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_begin").toString())) {
			sql += " and t1.column_26 >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_begin").toString() + " 00:00:00");
		}
		if (entity.getMap().get("date_end") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_end").toString())) {
			sql += " and t1.column_26 <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_end").toString() + " 23:59:59");
		}
		if (entity.getMap().get("customer_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_name_like").toString())) {
			sql += " and t.CUSTOMER_NAME like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_name_like").toString());
		}
		if (entity.getMap().get("customer_r3_code_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_r3_code_like").toString())) {
			sql += " and t.R3_CODE like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_r3_code_like").toString());
		}
		if (entity.getMap().get("ywy_user_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("ywy_user_name_like").toString())) {
			sql += " and t.ywy_user_name like '%' ||?|| '%' ";
			array.add(entity.getMap().get("ywy_user_name_like").toString());
		}

		sql += "    ) a1 GROUP BY a1.r3_ww) c1 LEFT JOIN ";
		sql += "      (SELECT 1 AS flag, value (sum (t1.column_14), 0) AS r3_price, value (sum (t1.column_12), 0) AS r3_num ";
		sql += "       FROM CHANNEL_DATA_IMPORT t1  LEFT JOIN MV_ORG_OF_CUSTOMER t ";
		sql += "       ON t1.column_1 = t.R3_CODE LEFT JOIN KONKA_PE_PD_MODEL t2 ";
		sql += "       ON t1.column_11 = t2.MD_NAME WHERE 1 = 1  and t1.column_12<>0  ";

		if (StringUtils.isNotBlank(entity.getDept_id_in())) {
			sql += " and t.CUR_DEPT_ID in (" + entity.getDept_id_in() + ") ";
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
		if (entity.getMap().get("date_begin") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_begin").toString())) {
			sql += " and t1.column_26 >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_begin").toString() + " 00:00:00");
		}
		if (entity.getMap().get("date_end") != null
				&& StringUtils.isNotBlank(entity.getMap().get("date_end").toString())) {
			sql += " and t1.column_26 <= to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			array.add(entity.getMap().get("date_end").toString() + " 23:59:59");
		}
		if (entity.getMap().get("customer_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_name_like").toString())) {
			sql += " and t.CUSTOMER_NAME like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_name_like").toString());
		}
		if (entity.getMap().get("customer_r3_code_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("customer_r3_code_like").toString())) {
			sql += " and t.R3_CODE like '%' ||?|| '%' ";
			array.add(entity.getMap().get("customer_r3_code_like").toString());
		}
		if (entity.getMap().get("ywy_user_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("ywy_user_name_like").toString())) {
			sql += " and t.ywy_user_name like '%' ||?|| '%' ";
			array.add(entity.getMap().get("ywy_user_name_like").toString());
		}

		sql += "    ) d1 ON c1.flag = d1.flag) b2 ON a2.sale_ww = b2.r3_ww ";
		sql += "         WHERE 1 = 1) a3 ORDER BY a3.ww ASC ";

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
}