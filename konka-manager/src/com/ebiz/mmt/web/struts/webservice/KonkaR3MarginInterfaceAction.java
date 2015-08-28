package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;

/**
 * @author Liu,ZhiXiang
 * @version 2013-07-11
 */
public class KonkaR3MarginInterfaceAction extends BaseAction {

	public ActionForward getR3MarginListToJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");// 分公司名称
		String l4_dept_id = (String) dynaBean.get("l4_dept_id");// 经营部名称
		String l5_dept_id = (String) dynaBean.get("l5_dept_id");// 办事处名称
		String year = (String) dynaBean.get("year");// 年度
		String month = (String) dynaBean.get("month");// 月份
		String ywy_user_name = (String) dynaBean.get("ywy_user_name");// 业务员
		String customer_name = (String) dynaBean.get("customer_name");// 客户名称
		String last_year = "";

		String user_name = (String) dynaBean.get("user_name");// 用户名
		String password = (String) dynaBean.get("password");// 密码
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if (StringUtils.isBlank(user_name)) {
			// 用户名不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"100\",\"msg\":\"用户名不能为空！\"]}");
			return null;
		}
		if (StringUtils.isBlank(password)) {
			// 密码不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"200\",\"msg\":\"密码不能为空！\"]}");
			return null;
		}
		if (StringUtils.isBlank(year)) {
			// 年度不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"300\",\"msg\":\"年度不能为空！\"]}");
			return null;
		}
		if (StringUtils.isBlank(month)) {
			// 月份不能为空！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"400\",\"msg\":\"月份不能为空！\"]}");
			return null;
		}
		
		user_name = user_name.trim();
		PeProdUser entity = new PeProdUser();
		//判断是否需要解密  根据版本好判断
		if(StringUtils.isNotBlank(android_version) && Long.valueOf(android_version)>37)
		{
			entity.setUser_name(new String(new DESPlus().decrypt(user_name).getBytes("iso-8859-1"),"utf-8"));
			entity.setPass_word(password);
		}else if(StringUtils.isNotBlank(ios_version) && Long.valueOf(ios_version)>317){
			entity.setUser_name(new String(new DESPlus().decrypt(user_name).getBytes("iso-8859-1"),"utf-8"));
			entity.setPass_word(password);
		}else
		{
			entity.setUser_name(user_name);
			entity.setPass_word(new DESPlus().encrypt(password));
		}
		entity.getRow().setCount(2);
		entity.setIs_del(0);
		// entity.setIs_xx_user(0L); //不是新兴渠道用户 -----改在SQL中写死，以免各个调用模块都需要\
		entity.getMap().put("user_type_in", "0,1"); // Ren zhong, Add by2013-06-06

		List<PeProdUser> userInfoList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		if (null == userInfoList || userInfoList.size() == 0) {
			// 用户不存在！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"600\",\"msg\":\"用户不存在！\"]}");
			return null;
		} else if (userInfoList.size() > 1) {
			// 用户名重复！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"700\",\"msg\":\"用户名重复！\"]}");
			return null;
		}
		PeProdUser userInfo = userInfoList.get(0);
		if (!userInfo.getPass_word().equals(entity.getPass_word())) {
			// 密码错误！
			super.renderJson(response, "{\"status\":\"-1\",\"error\":[\"error_code\":\"800\",\"msg\":\"密码错误！\"]}");
			return null;
		}

		if (userInfo.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (userInfo.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(userInfo.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				// 用户所属部门为空！
				super.renderJson(response,
						"{\"status\":\"-1\",\"error\":[\"error_code\":\"900\",\"msg\":\"用户所属部门为空！\"]}");
				return null;
			} else if (null == konkaDept.getDept_type() || konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("dept_type", "3");
				dynaBean.set("dept_id", konkaDept.getDept_id().toString());
				dept_id = konkaDept.getDept_id().toString();
			} else {
				// 用户没有操作权限！
				//super.renderJson(response,
				//		"{\"status\":\"-1\",\"error\":[\"error_code\":\"1000\",\"msg\":\"用户没有操作权限！\"]}");
				//return null;
			}
		}

		if (StringUtils.isNotBlank(year)) {
			last_year = String.valueOf((Integer.valueOf(year) - 1));
		}
		if (StringUtils.isNotBlank(month)) {
			switch (Integer.valueOf(month)) {
			case 1:
				month = "01";
				break;
			case 2:
				month = "02";
				break;
			case 3:
				month = "03";
				break;
			case 4:
				month = "04";
				break;
			case 5:
				month = "05";
				break;
			case 6:
				month = "06";
				break;
			case 7:
				month = "07";
				break;
			case 8:
				month = "08";
				break;
			case 9:
				month = "09";
				break;
			default:
				break;
			}
		}
		List<?> entityList = this.getR3MarginList(year, last_year, month, dept_id, l4_dept_id, l5_dept_id,
				ywy_user_name, customer_name,userInfo);

		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"list\":[");
		if (null == entityList || entityList.size() == 0) {
			sb = sb.append("]}");
			super.renderJson(response, sb.toString());
			return null;
		}
		for (int i = 0; i < entityList.size(); i++) {
			Object[] obj = (Object[]) entityList.get(i);
			sb = sb.append("{");
			sb = sb.append("\"r3_code\":\"").append((obj[0] == null ? "" : obj[0].toString())).append("\",");// R3编码
			sb = sb.append("\"customer_name\":\"").append((obj[1] == null ? "" : obj[1].toString())).append("\",");// 客户名称
			sb = sb.append("\"category_name\":\"").append((obj[2] == null ? "" : obj[2].toString())).append("\",");// 客户类型
			sb = sb.append("\"pd_total_money\":\"").append((obj[3] == null ? "0" : obj[3].toString())).append("\",");// R3销售额（元）
			sb = sb.append("\"pd_count\":\"").append((obj[4] == null ? "0" : obj[4].toString())).append("\",");// R3销售量
			sb = sb.append("\"pj_ml_money\":\"").append((obj[5] == null ? "0" : obj[5].toString())).append("\",");// 平均毛利（元）
			sb = sb.append("\"pj_unitprice\":\"").append((obj[6] == null ? "0" : obj[6].toString())).append("\",");// 平均价格（元）
			sb = sb.append("\"tb_pd_total_money\":\"").append((obj[7] == null ? "-" : obj[7].toString())).append("\",");// R3销售额同比
			sb = sb.append("\"tb_pd_count\":\"").append((obj[8] == null ? "-" : obj[8].toString())).append("\",");// R3销售量同比
			sb = sb.append("\"tb_ml_money\":\"").append((obj[9] == null ? "-" : obj[9].toString())).append("\",");// 平均毛利同比
			sb = sb.append("\"tb_unitprice\":\"").append((obj[10] == null ? "-" : obj[10].toString())).append("\"");// 平均价格同比
			sb = sb.append("},");
		}
		String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "],\"status\":\"0\"}";
		logger.info("sb_str {}", sb_str);
		super.renderJson(response, sb_str);
		return null;
	}

	public List<?> getR3MarginList(String year, String last_year, String month, String dept_id, String l4_dept_id,
			String l5_dept_id, String ywy_user_name, String customer_name,PeProdUser userInfo) {
		List<String> array = new ArrayList<String>();
		// 数据级别判断开始
		Long __dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		String dept_id_in = "";
		
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(Long.valueOf(__dept_id)); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			List<KonkaDept> list = super.getKonkaDeptListOfUser(userInfo.getId(), true);
			for (KonkaDept k : list) {
				if (!dept_id_in.equals("")) {
					dept_id_in += ",";
				}
				dept_id_in += k.getDept_id();

			}
			__dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			
			break;
		case 0:
			break;
		default:
			// 出错
		}
		String sql = " select ff.*,decode(to_char(gg.pd_total_money),null,'-',0,'-',to_char(round(((ff.pd_total_money - gg.pd_total_money) / gg.pd_total_money),4) * 100)) as tb_pd_total_money, ";
		sql += " decode(to_char(gg.pd_count),null,'-',0,'-',to_char(round(((ff.pd_count - gg.pd_count) / gg.pd_count), 4) * 100)) as tb_pd_count,";
		sql += " decode(to_char(gg.pj_ml_money),null,'-',0,'-',to_char(round(((ff.pj_ml_money - gg.pj_ml_money) / gg.pj_ml_money), 4) * 100)) as pj_ml_money,";
		sql += " decode(to_char(gg.pj_unitprice),null,'-',0,'-',to_char(round(((ff.pj_unitprice - gg.pj_unitprice) / gg.pj_unitprice),4) * 100)) as tb_unitprice from ( ";

		sql += " select cc.r3_code,cc.customer_name,cc.category_name,cc.pd_total_money,cc.pd_count,decode(cc.pd_count, 0, 0, round((cc.ml_money / cc.pd_count), 4)) as pj_ml_money, ";
		sql += " decode(cc.pd_count,0,0,round((cc.pd_total_money / cc.pd_count), 4)) as pj_unitprice ";
		sql += " from (select bb.r3_code,bb.customer_name,bb.category_name,sum(bb.pd_total_money) as pd_total_money,sum(bb.pd_count) as pd_count,sum(bb.ml_money) as ml_money";
		sql += " from (select aa.r3_code,aa.customer_name,aa.category_name,aa.pd_name,aa.pd_total_money,aa.pd_count,(aa.pd_total_money - (aa.pd_count * aa.cash_price)) as ml_money";
		sql += " from (select a.r3_code,a.customer_name,a.category_name,a.pd_name,value(a.pd_total_money, 0) as pd_total_money,value(a.pd_count, 0) as pd_count,value(b.cash_price, 0) as cash_price";
		sql += " from V_A_DETAILS_OF_PURCHASE a left join (select k.cash_price,k.pd_name from KONKA_PD_MODEL_PRICES k where 1 = 1 ";

		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			sql += " and k.price_month = ? ";
			array.add(year + month);
		}
		sql += " ) b on a.pd_name = b.pd_name where 1 = 1 ";

		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			//sql += "  	 AND rownum<100 ";
			break;
		case 8:
			// 分公司及以下部门可见
			sql += "  	 AND a.dept_id IN ( " + __dept_id + " )";
			break;
		case 7:
			// 我所在的部门及以下部门可见
			sql += "  	 AND (a.l4_dept_id IN ( " + dept_id_in + " ) or a.l5_dept_id in ( " + dept_id_in + " ))";
			
			break;

		default:
			// 集团及以下部门可见
			//sql += "  	 AND rownum<100 ";
			// 出错
		}	
		if (max_dlevel == 0) {
			//sql += "  	 AND (A.l4_dept_id IN ( " + dept_id_in + " ) or A.l5_dept_id in ( " + dept_id_in + " )";
			sql += "  	 and A.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = " + userInfo.getId() + "  )";
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and a.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and a.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and a.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			sql += " and a.customer_name like '%' ||?|| '%' ";
			array.add(customer_name);
		}
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			sql += " and to_char(a.create_date,'yyyy-MM') = ? ";
			array.add(year + "-" + month);
		}
		sql += " ) aa) bb group by bb.r3_code, bb.customer_name, bb.category_name) cc ";

		sql += " order by cc.r3_code desc, cc.customer_name desc, cc.category_name desc ) ff ";

		sql += " left join (select cc.r3_code,cc.customer_name,cc.category_name,cc.pd_total_money,cc.pd_count, ";
		sql += " decode(cc.pd_count,0,0,round((cc.ml_money / cc.pd_count), 4)) as pj_ml_money, ";
		sql += " decode(cc.pd_count,0,0,round((cc.pd_total_money / cc.pd_count), 4)) as pj_unitprice ";
		sql += " from (select bb.r3_code,bb.customer_name,bb.category_name,sum(bb.pd_total_money) as pd_total_money,sum(bb.pd_count) as pd_count,sum(bb.ml_money) as ml_money";
		sql += " from (select aa.r3_code,aa.customer_name,aa.category_name,aa.pd_name,aa.pd_total_money,aa.pd_count,(aa.pd_total_money - (aa.pd_count * aa.cash_price)) as ml_money ";
		sql += " from (select a.r3_code,a.customer_name,a.category_name,a.pd_name,value(a.pd_total_money, 0) as pd_total_money,value(a.pd_count, 0) as pd_count,value(b.cash_price, 0) as cash_price ";
		sql += " from V_A_DETAILS_OF_PURCHASE a left join (select k.cash_price, k.pd_name from KONKA_PD_MODEL_PRICES k where 1 = 1 ";

		
		
		if (StringUtils.isNotBlank(last_year) && StringUtils.isNotBlank(month)) {
			sql += " and k.price_month = ? ";
			array.add(last_year + month);
		}
		
		sql += " ) b on a.pd_name = b.pd_name where 1 = 1 ";

		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			sql += "  	 AND rownum<100 ";
			break;
		case 8:
			// 分公司及以下部门可见
			sql += "  	 AND a.cur_dept_id IN ( " + __dept_id + " )";
			break;
		case 7:
			// 我所在的部门及以下部门可见
			sql += "  	 AND (a.l4_dept_id IN ( " + __dept_id + " ) or a.l5_dept_id in ( " + __dept_id + " ))";
			
			break;

		default:
			// 集团及以下部门可见
			sql += "  	 AND rownum<100 ";
			// 出错
		}	
		if (max_dlevel == 0) {
			sql += "  	 AND a.konka_r3_id IN ( select konka_r3_id from BRANCH_ASSIGN t where t.USER_ID = " + userInfo.getId() + "  )";
		}
		if (StringUtils.isNotBlank(dept_id)) {
			sql += " and a.dept_id = ? ";
			array.add(dept_id);
		}
		if (StringUtils.isNotBlank(l4_dept_id)) {
			sql += " and a.l4_dept_id = ? ";
			array.add(l4_dept_id);
		}
		if (StringUtils.isNotBlank(l5_dept_id)) {
			sql += " and a.l5_dept_id = ? ";
			array.add(l5_dept_id);
		}
		if (StringUtils.isNotBlank(ywy_user_name)) {
			sql += " and a.ywy_user_name like '%' ||?|| '%' ";
			array.add(ywy_user_name);
		}
		if (StringUtils.isNotBlank(customer_name)) {
			sql += " and a.customer_name like '%' ||?|| '%' ";
			array.add(customer_name);
		}
		if (StringUtils.isNotBlank(last_year) && StringUtils.isNotBlank(month)) {
			sql += " and to_char(a.create_date,'yyyy-MM') = ? ";
			array.add(last_year + "-" + month);
		}
		sql += " ) aa) bb group by bb.r3_code, bb.customer_name, bb.category_name) cc) gg on ff.r3_code = gg.r3_code ";

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