package com.ebiz.mmt.web.struts;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.ebiz.mmt.domain.Konkamobilesaledatas;
import com.ebiz.mmt.domain.PeProdUser;

/**
 * @author Derek
 */
public class MobileStatisticAction extends MobileSubmitAction {

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	BigDecimal bd = new BigDecimal("100000.0000");

	private static SerializeConfig mapping = new SerializeConfig();
	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.unknow(mapping, form, request, response);
	}

	protected static String toJSONString(Object object, SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String s, Object value) {
				if (null != value) {
					if (value instanceof java.util.Date) {
						// return String.format("%1$tF %1tT", value);
						return String.format("%1$tF", value);
					}
					return value;
				} else {
					return "";
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}

	public ActionForward unknow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	// 分析汇总
	public ActionForward GetStatistic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);
		

		if (null == peProdUser) {
			super.renderTextJsonOrJsonp(request, response, "用户信息出错，请联系管理员！");
			return null;
		}
		String startime = (String) dynaBean.get("startime");
		String endtime = (String) dynaBean.get("endtime");

		String type = (String) dynaBean.get("type");
		if (StringUtils.isEmpty(type)) {
			super.renderTextJsonOrJsonp(request, response, "未指定结果集类型！");
			return null;
		}

		Konkamobilesaledatas entity = new Konkamobilesaledatas();
		entity.getMap().put("date_begin", startime);
		entity.getMap().put("date_end", endtime);
		entity.setCxuser(peProdUser.getId());

		if ("1".equals(type))
			entity.getMap().put("selected01", "1");
		if ("2".equals(type))
			entity.getMap().put("selected02", "1");
		if ("3".equals(type))
			entity.getMap().put("selected03", "1");
		StringBuffer str = new StringBuffer();
		str.append("[");
		List<HashMap<String, String>> entityList = new ArrayList<HashMap<String, String>>();
		entityList = getFacade().getKonkamobilesaledatasService().getKonkamobilesaledatasMobileFlot(entity);

		int _i = 0;
		if ("1".equals(type))
			for (HashMap<String, String> _h : entityList) {
				if (_i > 0) {
					str.append(",");
				}
				String i = String.valueOf(_h.get("SALECASH"));
				_h.put("SALECASH", i);
				String j = String.valueOf(_h.get("SALENUM"));
				_h.put("SALENUM", j);
				str.append("{\"label\":\"" + _h.get("MD_NAME") + "\",\"data\":[[" + j + "," + i + "]]}");
				_i++;
			}
		_i = 0;
		if ("2".equals(type))
			for (HashMap<String, String> _h : entityList) {
				if (_i > 0) {
					str.append(",");
				}
				String i = String.valueOf(_h.get("SALECASH"));
				_h.put("SALECASH", i);
				String j = String.valueOf(_h.get("SALENUM"));
				_h.put("SALENUM", j);
				str.append("{\"label\":\"" + _h.get("CC_ID") + "\",\"data\":[[" + j + "," + i + "]]}");
				_i++;
			}
		_i = 0;
		if ("3".equals(type))
			for (HashMap<String, String> _h : entityList) {
				if (_i > 0) {
					str.append(",");
				}
				String i = String.valueOf(_h.get("SALECASH"));
				_h.put("SALECASH", i);
				String j = String.valueOf(_h.get("SALENUM"));
				_h.put("SALENUM", j);
				str.append("{\"label\":\"" + _h.get("SALEDATE") + "\",\"data\":[[" + j + "," + i + "]]}");
				_i++;
			}
		str.append("]");
		logger.info("-------str.toString--->{}", str.toString());
		super.renderTextJsonOrJsonp(request, response, str.toString());
		return null;
	}
}
