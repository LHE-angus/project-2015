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

import com.ebiz.mmt.domain.BaseProvinceListFour;
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
 * @version 2013-09-22
 */
public class KonkaSailDataSearchForPIndexAction extends BaseAction {

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
		String label_db = (String) dynaBean.get("label_db");// 是否大板
		String label_int = (String) dynaBean.get("label_int");// 是否智能
		String size_sec = (String) dynaBean.get("size_sec");// 规格尺寸
		String time_type = (String) dynaBean.get("time_type");// 时间维度
		String label_3d = (String) dynaBean.get("label_3d");// 3D电视
		String label_www = (String) dynaBean.get("label_www");// 网络电视
		String label_4k = (String) dynaBean.get("label_4k");// 4K
		String date_begin = (String) dynaBean.get("date_begin");// 开始时间
		String date_end = (String) dynaBean.get("date_end");// 结束时间
		String model_name = (String) dynaBean.get("model_name");// 型号
		String model_name_like = (String) dynaBean.get("model_name_like");// 自定义型号

		String dept_id = (String) dynaBean.get("dept_id");// 分公司
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处

		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户名称
		String customer_r3_code_like = (String) dynaBean.get("customer_r3_code_like");// R3编码
		String store_name_like = (String) dynaBean.get("store_name_like");// 门店名称
		String md_serise_like = (String) dynaBean.get("md_serise_like");// 产品系列
		String md_serise = (String) dynaBean.get("md_serise");// 产品系列

		String province = (String) dynaBean.get("province");// 省
		String city = (String) dynaBean.get("city");// 市
		String country = (String) dynaBean.get("country");// 县
		String town = (String) dynaBean.get("town");// 乡镇

		String group_time = (String) dynaBean.get("group_time");// 按时间分组
		String group_p_index = (String) dynaBean.get("group_p_index");// 按省、市、县、乡镇分组

		String sale_money_begin = (String) dynaBean.get("sale_money_begin");// 零售额区间-开始
		String sale_money_end = (String) dynaBean.get("sale_money_end");// 零售额区间-结束

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
		if (StringUtils.isNotBlank(time_type)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			if ("1".equals(time_type)) {
				request.setAttribute("time_type_name", "昨日");
				calendar.add(Calendar.DATE, -1);
				String day = df.format(calendar.getTime());
				// entity.setSale_date(df.parse(day));
				entity.getMap().put("this_date", day);
			} else if ("2".equals(time_type)) {
				request.setAttribute("time_type_name", "当日");
				// entity.setSale_date(new Date());
				String day = df.format(calendar.getTime());
				entity.getMap().put("this_date", day);
			} else if ("3".equals(time_type)) {
				request.setAttribute("time_type_name", "当周");
				entity.setWw(String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
				entity.setY(String.valueOf(calendar.get(Calendar.YEAR)));
			} else if ("4".equals(time_type)) {
				request.setAttribute("time_type_name", "当月");
				entity.setM(String.valueOf(calendar.get(Calendar.MONTH) + 1));
				entity.setY(String.valueOf(calendar.get(Calendar.YEAR)));
			} else if ("5".equals(time_type)) {
				request.setAttribute("time_type_name", "当季度");
				int month = calendar.get(Calendar.MONTH) + 1;
				String q = "";
				switch (month) {
				case 1:
					q = "1";
					break;
				case 2:
					q = "1";
					break;
				case 3:
					q = "1";
					break;
				case 4:
					q = "2";
					break;
				case 5:
					q = "2";
					break;
				case 6:
					q = "2";
					break;
				case 7:
					q = "3";
					break;
				case 8:
					q = "3";
					break;
				case 9:
					q = "3";
					break;
				case 10:
					q = "4";
					break;
				case 11:
					q = "4";
					break;
				case 12:
					q = "4";
					break;
				default:
					break;
				}
				entity.setQ(q);
				entity.setY(String.valueOf(calendar.get(Calendar.YEAR)));
			} else if ("6".equals(time_type)) {
				request.setAttribute("time_type_name", "当年");
				entity.setY(String.valueOf(calendar.get(Calendar.YEAR)));
			}
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
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(customer_r3_code_like)) {
			entity.getMap().put("customer_r3_code_like", customer_r3_code_like);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(md_serise_like)) {
			entity.getMap().put("md_serise_like", md_serise_like);
		}
		if (StringUtils.isNotBlank(md_serise)) {
			entity.setMd_serise(md_serise);
		}
		if (StringUtils.isNotBlank(label_4k)) {
			if ("1".equals(label_4k)) {
				request.setAttribute("label_4k_name", "是");
			} else if ("0".equals(label_4k)) {
				request.setAttribute("label_4k_name", "否");
			}

			entity.getMap().put("md_serise_in", "'9500','9600'");
		}
		if (StringUtils.isNotBlank(town)) {
			BaseProvinceListFour b = new BaseProvinceListFour();
			b.setP_index(new Long(town));
			b.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourList(b);
			if (baseProvinceFourList != null && baseProvinceFourList.size() > 0) {
				request.setAttribute("full_p_name", baseProvinceFourList.get(0).getFull_name());
			}

			entity.getMap().put("town", town);
		} else if (StringUtils.isNotBlank(country)) {
			BaseProvinceListFour b = new BaseProvinceListFour();
			b.setP_index(new Long(country));
			b.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourList(b);
			if (baseProvinceFourList != null && baseProvinceFourList.size() > 0) {
				request.setAttribute("full_p_name", baseProvinceFourList.get(0).getFull_name());
			}

			entity.getMap().put("country", country);
		} else if (StringUtils.isNotBlank(city)) {
			BaseProvinceListFour b = new BaseProvinceListFour();
			b.setP_index(new Long(city));
			b.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourList(b);
			if (baseProvinceFourList != null && baseProvinceFourList.size() > 0) {
				request.setAttribute("full_p_name", baseProvinceFourList.get(0).getFull_name());
			}

			entity.getMap().put("city", city);
		} else if (StringUtils.isNotBlank(province)) {
			BaseProvinceListFour b = new BaseProvinceListFour();
			b.setP_index(new Long(province));
			b.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourList(b);
			if (baseProvinceFourList != null && baseProvinceFourList.size() > 0) {
				request.setAttribute("full_p_name", baseProvinceFourList.get(0).getFull_name());
			}

			entity.getMap().put("province", province);
		}
		if (StringUtils.isNotBlank(group_time)) {
			entity.getMap().put("group_time", group_time);
		}
		if (StringUtils.isNotBlank(group_p_index)) {
			entity.getMap().put("group_p_index", group_p_index);
		}
		if (StringUtils.isNotBlank(sale_money_begin)) {
			entity.getMap().put("sale_money_begin", sale_money_begin);
		}
		if (StringUtils.isNotBlank(sale_money_end)) {
			entity.getMap().put("sale_money_end", sale_money_end);
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

		// 产品系列
		// PePdModel t = new PePdModel();
		// List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelListForMdSerise(t);
		// request.setAttribute("pePdModelList", pePdModelList);

		md_serise_list = properties.getProperty(key);
		log.info("****************md_serise_list:" + md_serise_list);
		String[] array = md_serise_list.split(",");
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			arrayList.add(array[i]);
		}
		request.setAttribute("mdSeriseList", arrayList);

		entity.setIs_del(0);
		if (StringUtils.isNotBlank(search_first_flag) && "1".equals(search_first_flag)) {

			List<?> entityList = getKonkaMobileSailDataForSum(entity);
			request.setAttribute("entityList", entityList);

		}
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return mapping.findForward("list");
	}

	public List<?> getKonkaMobileSailDataForSum(VADetailsOfSalesData entity) {
		List<String> array = new ArrayList<String>();
		String sql = "";
		if ((entity.getMap().get("sale_money_begin") != null && StringUtils.isNotBlank(entity.getMap()
				.get("sale_money_begin").toString()))
				|| (entity.getMap().get("sale_money_end") != null && StringUtils.isNotBlank(entity.getMap()
						.get("sale_money_end").toString()))) {
			sql += " select t2.* from ( ";
		}
		sql += " select ";
		String sql_p_name = "";
		String gdp_pindex="";
		String gdp_pindexfield="";
		String gdp_pindexfieldlist = " ,sum(t3.gdp),sum(rk),sum(t3.mianji),sum(t3.jdkhsl) ";
		if (entity.getMap().get("group_p_index") != null
				&& StringUtils.isNotBlank(entity.getMap().get("group_p_index").toString())) {
			if ("1".equals(entity.getMap().get("group_p_index").toString())) {
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,2)||'0000'),'','','' ";
				gdp_pindex = " substr(p_index,1,2)||'0000' ";
				gdp_pindexfield = " substr(p_index,1,2)||'0000' as p_index";
			} else if ("2".equals(entity.getMap().get("group_p_index").toString())) {
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,2)||'0000') ";
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,4)||'00'),'','' ";
				gdp_pindex = " substr(p_index,1,4)||'0000' ";
				gdp_pindexfield = " substr(p_index,1,4)||'0000' as p_index";
			} else if ("3".equals(entity.getMap().get("group_p_index").toString())) {
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,2)||'0000') ";
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,4)||'00') ";
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,6)),'' ";
				gdp_pindex = " substr(p_index,1,6) ";
				gdp_pindexfield = " substr(p_index,1,6) as p_index";
			} else if ("4".equals(entity.getMap().get("group_p_index").toString())) {
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,2)||'0000') ";
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,4)||'00') ";
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = substr(t_prov.p_index,1,6)) ";
				gdp_pindex = " p_index ";
				gdp_pindexfield = " p_index as p_index";
				sql_p_name += " ,(select p.p_name from KONKA_BASE_PROVINCE_LIST_FOUR p where p.p_index = decode(length(trim(t_prov.p_index)),6,t_prov.p_index||'00',t_prov.p_index)) ";
			} else {
				// 行政区域分组未选
				return null;
			}
		} else {
			// 行政区域分组未选
			return null;
		}
		String pindexgroup = "";
		String pindexfield = "";
		String pindexlevel = "";
		if (entity.getMap().get("group_p_index") != null
				&& StringUtils.isNotBlank(entity.getMap().get("group_p_index").toString())) {
			if ("1".equals(entity.getMap().get("group_p_index").toString())) {
				pindexgroup = ",substr(entp_p_index,1,2)";
				pindexfield = ",substr(entp_p_index,1,2)||'0000' as entp_p_index";
				 pindexlevel = " and t_prov.p_level=1 ";
			} else if ("2".equals(entity.getMap().get("group_p_index").toString())) {
				pindexgroup = ",substr(entp_p_index,1,4)";
				pindexfield = ",substr(entp_p_index,1,4)||'00' as entp_p_index";
				 pindexlevel = " and t_prov.p_level<=2 ";
			} else if ("3".equals(entity.getMap().get("group_p_index").toString())) {
				pindexgroup = ",substr(entp_p_index,1,6)";
				pindexfield = ",substr(entp_p_index,1,6) as entp_p_index";
				pindexlevel = " and t_prov.p_level<=3 ";
			} else if ("4".equals(entity.getMap().get("group_p_index").toString())) {
				pindexgroup = ",entp_p_index";
				pindexfield = ",entp_p_index";
				pindexlevel = " and t_prov.p_level<=4 ";
			} else {
				// 行政区域分组未选
				logger.info("aaaa=============={}","sssssw");
				return null;
			}
		}

		sql += " '' as y,'' as sale_date,t.dept_name,t.dept_id,sum(t1.all_num) as all_num,sum(t1.all_money) as all_money ";
		sql += ",sum(r3_count) as r3_count,sum(wy_count) as wy_count,sum(store_count) as store_count,sum( cxy_count) as cxy_count ";
		//省市县名称
		sql += sql_p_name;
		sql += gdp_pindexfieldlist;
		sql += " from konka_base_province_list_four t_prov ";
		
		String KONKA_PINDEX_AREA_GDP="(select "+gdp_pindexfield+",sum(gdp) gdp,sum(P_AREA) rk,sum(column_1) as mianji,sum(column_2) as jdkhsl from KONKA_PINDEX_AREA_GDP group by " + gdp_pindex + ")";//区域统计信息
		sql += "  left join " + KONKA_PINDEX_AREA_GDP + " t3 on t_prov.p_index=t3.p_index ";
		sql += " left join (select dept_NAME,dept_id" + pindexfield + " ,count(distinct r3_code) as r3_count,count(distinct ywy_user_name) as wy_count,count(distinct store_name) as store_count,count(distinct cxy_user_name) as cxy_count  from " ;
		sql +=  " MV_ORG_OF_CXY t where 1=1 " ;
		if (entity.getDept_id() != null) {
			sql += " and t.DEPT_ID = ? ";
			array.add(String.valueOf(entity.getDept_id()));
		}
		
		if (StringUtils.isNotBlank(entity.getPar_customer_type())) {
			sql += " and t.PAR_CUSTOMER_TYPE = ? ";
			array.add(entity.getPar_customer_type());
		}
		if (StringUtils.isNotBlank(entity.getCustomer_type())) {
			sql += " and t.CUSTOMER_TYPE = ? ";
			array.add(entity.getCustomer_type());
		}
		if (entity.getMap().get("town") != null && StringUtils.isNotBlank(entity.getMap().get("town").toString())) {
			sql += " and t.ENTP_P_INDEX = ? ";
			array.add(entity.getMap().get("town").toString());
		} else if (entity.getMap().get("country") != null
				&& StringUtils.isNotBlank(entity.getMap().get("country").toString())) {
			sql += " and t.ENTP_P_INDEX like substr(?,1,6) || '%' ";
			array.add(entity.getMap().get("country").toString());
		} else if (entity.getMap().get("city") != null
				&& StringUtils.isNotBlank(entity.getMap().get("city").toString())) {
			sql += " and t.ENTP_P_INDEX like substr(?,1,4) || '%' ";
			array.add(entity.getMap().get("city").toString());
		} else if (entity.getMap().get("province") != null
				&& StringUtils.isNotBlank(entity.getMap().get("province").toString())) {
			sql += " and t.ENTP_P_INDEX like substr(?,1,2) || '%' ";
			array.add(entity.getMap().get("province").toString());
		}

		sql += " group by dept_name,dept_id" + pindexgroup + ") t on t.entp_p_index=t_prov.p_index left join (select  ";
		sql += " dept_id,sum(value(t.NUM,0)) as all_num,sum(value(t.ALL_PRICE,0)) as all_money " + pindexfield + " from V_A_DETAILS_OF_SALES_DATA_C t ";
		sql += " where 1 = 1 ";
		if (entity.getIs_del() != null) {
			sql += " and t.IS_DEL = ? ";
			array.add(String.valueOf(entity.getIs_del()));
		}
		if (StringUtils.isNotBlank(entity.getDept_id_in())) {
			//sql += " and t.cur_DEPT_ID in (" + entity.getDept_id_in() + ") ";
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
			sql += " and UPPER(t.MODEL_NAME) like '%' ||UPPER(?)|| '%' ";
			array.add(entity.getMap().get("model_name_like").toString());
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
		if (entity.getMap().get("store_name_like") != null
				&& StringUtils.isNotBlank(entity.getMap().get("store_name_like").toString())) {
			sql += " and t.STORE_NAME like '%' ||?|| '%' ";
			array.add(entity.getMap().get("store_name_like").toString());
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
		if (entity.getMap().get("town") != null && StringUtils.isNotBlank(entity.getMap().get("town").toString())) {
			sql += " and t.ENTP_P_INDEX = ? ";
			array.add(entity.getMap().get("town").toString());
		} else if (entity.getMap().get("country") != null
				&& StringUtils.isNotBlank(entity.getMap().get("country").toString())) {
			sql += " and t.ENTP_P_INDEX like substr(?,1,6) || '%' ";
			array.add(entity.getMap().get("country").toString());
		} else if (entity.getMap().get("city") != null
				&& StringUtils.isNotBlank(entity.getMap().get("city").toString())) {
			sql += " and t.ENTP_P_INDEX like substr(?,1,4) || '%' ";
			array.add(entity.getMap().get("city").toString());
		} else if (entity.getMap().get("province") != null
				&& StringUtils.isNotBlank(entity.getMap().get("province").toString())) {
			sql += " and t.ENTP_P_INDEX like substr(?,1,2) || '%' ";
			array.add(entity.getMap().get("province").toString());
		}

		
		sql += " group by dept_id"+pindexgroup + ")  t1 on t1.dept_id=t.dept_id and t1.entp_p_index=t.entp_p_index where 1 = 1 and t_prov.del_mark=0 ";
		
		sql += pindexlevel;
		if (entity.getDept_id() != null) {
			//如果查询具体的分公司，按照分公司所在区域来控制显示的行政区划列表
			sql += " and t_prov.root_code in (select substr(P_INDEX,1,2)||'0000' p_index from konka_base_province_list where locate (p_index,(select M_AREAS_IDS from KONKA_DEPT where dept_id=?))>0 ) ";		
			array.add(String.valueOf(entity.getDept_id()));
		}
		
		
		
		String group_by = "";
		String order_by = "";
		if (entity.getMap().get("group_p_index") != null
				&& StringUtils.isNotBlank(entity.getMap().get("group_p_index").toString())) {
			if ("1".equals(entity.getMap().get("group_p_index").toString())) {
				group_by += " t.dept_name,t.dept_id,t_prov.p_index ";
				order_by += " t.dept_id,t_prov.p_index asc ";
			} else if ("2".equals(entity.getMap().get("group_p_index").toString())) {
				group_by += " t.dept_name,t.dept_id,t_prov.p_index ";
				order_by += " t.dept_name,t_prov.p_index,t.dept_id asc ";
			} else if ("3".equals(entity.getMap().get("group_p_index").toString())) {
				group_by += " t.dept_name,t.dept_id,t_prov.p_index ";
				order_by += " t.dept_name,t_prov.p_index,t.dept_id asc ";
			} else if ("4".equals(entity.getMap().get("group_p_index").toString())) {
				group_by += " t.dept_name,t.dept_id,t_prov.p_index ";
				order_by += "t.dept_name,t_prov.p_index,t.dept_id asc ";
			} else if ("5".equals(entity.getMap().get("group_p_index").toString())) {
				group_by += " 1.sale_date,t.dept_name,t.dept_id,t_prov.p_index ";
				order_by += " t.dept_name,t_prov.p_index,t1.sale_date desc,t.dept_id asc ";
			} else {
				// 时间分组未选
				return null;
			}
		} else {
			// 时间分组未选
			return null;
		}
		

		sql += " group by " + group_by;
		sql += " order by " + order_by;

		if ((entity.getMap().get("sale_money_begin") != null && StringUtils.isNotBlank(entity.getMap()
				.get("sale_money_begin").toString()))
				|| (entity.getMap().get("sale_money_end") != null && StringUtils.isNotBlank(entity.getMap()
						.get("sale_money_end").toString()))) {
			sql += " ) t2 where 1 = 1 ";
			if ((entity.getMap().get("sale_money_begin") != null && StringUtils.isNotBlank(entity.getMap()
					.get("sale_money_begin").toString()))) {
				sql += " and t2.all_money >= ? ";
				array.add(entity.getMap().get("sale_money_begin").toString());
			}
			if ((entity.getMap().get("sale_money_end") != null && StringUtils.isNotBlank(entity.getMap()
					.get("sale_money_end").toString()))) {
				sql += " and t2.all_money <= ? ";
				array.add(entity.getMap().get("sale_money_end").toString());
			}
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
}