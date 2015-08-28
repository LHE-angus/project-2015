package com.ebiz.mmt.web.struts.manager.leader;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.JcfxReportXsqs;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaPindexAreaGdp;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.CacheObjUtil;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wangkl
 * @version 2014-12-08
 * @desc 销售趋势报表
 */
public class JcfxReportTerminalMapAction extends BaseAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.map9(mapping, form, request, response);
	}

	public ActionForward map9(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PeProdUser user = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_INFO);

		KonkaR3Store entity = new KonkaR3Store();
		DynaBean dynaBean = (DynaBean) form;

		String group_by = (String) dynaBean.get("group_by");

		String _year = (String) dynaBean.get("_year");
		if (_year == null) {
			_year = new SimpleDateFormat("yyyy").format(Calendar.getInstance()
					.getTime());
		}

		String begin_date = _year + "-1-1 00:00:00";

		String end_date = _year + "-12-31 23:59:59";

		entity.getMap().put("begin_date", begin_date);

		entity.getMap().put("end_date", end_date);
		request.setAttribute("_year", new SimpleDateFormat("yyyy")
				.format(Calendar.getInstance().getTime()));
		// 数据级别判断开始
		Long __dept_id = user.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(user.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			KonkaDept dept_fgs = super.getKonkaDeptForFgs(user.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", __dept_id);
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			__dept_id = user.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);

			break;
		case 0:
			__dept_id = user.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_job_id_eq", user.getJob_id());

			break;
		default:
			// 出错
		}
		// 数据级别判断结束
		entity.getMap().put("session_user_id", user.getId());
		entity.getMap().put("filter_by_job_id_eq", user.getJob_id());

		int objTime = 3600 * 24;

		List<HashMap> entityList;
		String objName = "JcfxReportTerminalMap.list";
		String objKey = "groupbyWhat" + group_by;
		// 初始化对象
		CacheObjUtil cacheObjUtil = new CacheObjUtil(super.getFacade());

		entityList = getFacade().getKonkaR3StoreService()
				.getKonkaR3StoreAndJbasePartnerForManager(entity);

		StringBuffer province_list = new StringBuffer();
		StringBuffer datestr = new StringBuffer();
		StringBuffer geoCoordstr = new StringBuffer();
		BigDecimal min = new BigDecimal(0), max = new BigDecimal(0);
		if (null != entityList && entityList.size() > 0) {
			int i = 0;
			for (HashMap map : entityList) {
				if (null != map.get("TERMINAL_POSITION_X")
						&& null != map.get("TERMINAL_POSITION_Y")) {
					datestr.append("	                    {name: \""
							+ map.get("TERMINAL_NAME") + "\", value: "
							+ "parseInt" + "(\"" + map.get("TERMINAL_SALEMONEY")
							+ "\"), value2: " + map.get("TERMINAL_ID") + ", year: " + _year + "},	\n");

					
					if (min.compareTo(new BigDecimal(0)) == 0
							|| (new BigDecimal(map.get("TERMINAL_SALEMONEY")+"")).compareTo(min) == -1) {
						min = (new BigDecimal(map.get("TERMINAL_SALEMONEY")+""));
					}
					if ((new BigDecimal(map.get("TERMINAL_SALEMONEY")+"")).compareTo(max) == 1) {
						max = (new BigDecimal(map.get("TERMINAL_SALEMONEY")+""));
						logger.info(map.get("TERMINAL_NAME")+"");
						logger.info(map.get("TERMINAL_SALEMONEY")+"");
					}
					
					geoCoordstr.append("	                \""
							+ map.get("TERMINAL_NAME") + "\":["
							+ map.get("TERMINAL_POSITION_X") + ","
							+ map.get("TERMINAL_POSITION_Y") + "],	\n");

				}
			}
			datestr.append("	                    {name: \"门店\", value: 0}	\n");
			geoCoordstr.append("	                \"门店\":[0,0]	\n");
		}
		KonkaPindexAreaGdp konkaPindexAreaGdp = new KonkaPindexAreaGdp();
		konkaPindexAreaGdp.getMap().put("level", 1);
		List<KonkaPindexAreaGdp> list = this.getFacade()
				.getKonkaPindexAreaGdpService()
				.getKonkaPindexAreaGdpList(konkaPindexAreaGdp);
		
		for (int i = 0; i < list.size(); i++) {
			KonkaPindexAreaGdp tmp = list.get(i);
			String pname = tmp.getP_name();
			if (pname != null) {
				pname = pname.replace("市", "");
				pname = pname.replace("省", "");
			}
			if (group_by == null) {
				if (min.compareTo(new BigDecimal(0)) == 0
						|| tmp.getGdp().compareTo(min) == -1) {
					min = tmp.getGdp();
				}
				if (tmp.getGdp().compareTo(max) == 1) {
					max = tmp.getGdp();
				}
				province_list.append("	                    {name: \"" + pname
						+ "\", value: " + tmp.getGdp() + "},	\n");
			}

			if (group_by != null && group_by.equals("gdp")) {
				if (min.compareTo(new BigDecimal(0)) == 0
						|| tmp.getGdp().compareTo(min) == -1) {
					min = tmp.getGdp();
				}
				if (tmp.getGdp().compareTo(max) == 1) {
					max = tmp.getGdp();
				}
				province_list.append("	                    {name: \"" + pname
						+ "\", value: " + tmp.getGdp() + "},	\n");
			}

			if (group_by != null && group_by.equals("area")) {
				if (min.compareTo(new BigDecimal(0)) == 0
						|| tmp.getGdp().compareTo(min) == -1) {
					min = tmp.getColumn_1();
				}
				if (tmp.getGdp().compareTo(max) == 1) {
					max = tmp.getColumn_1();
				}
				province_list.append("	                    {name: \"" + pname
						+ "\", value: " + tmp.getColumn_1() + "},	\n");
			}

		}

		// for(int i=0; i<=2000;i++){
		// datestr.append("		                {name:'西藏', value:605.83},\n").
		// append("	                    {name: \"平顶山\", value: 119},	\n");
		// geoCoordstr.append("	                \"西藏\":[122.207216,29.985295],	\n")
		// .append("	                \"平顶山\":[113.29,33.75],	\n");
		// }
		// datestr.append("	                    {name: \"门店\", value: 0}	\n");
		// geoCoordstr.append("	                \"门店\":[0,0]	\n");
		// }

		StringBuffer sb = new StringBuffer();
		sb.append("{	\n");
		sb.append("	    title : {	\n");
		sb.append("	        text: '全国门店网点终端',	\n");
		sb.append("	        subtext: 'data from qdgl',	\n");
		sb.append("	        	\n");
		sb.append("	        x:'center'	\n");
		sb.append("	    },	\n");
		sb.append("	    tooltip : {	\n");
		sb.append("	        trigger: 'item'	\n");
		sb.append("	    },	\n");
		sb.append("	    legend: {	\n");
		sb.append("	        orient: 'vertical',	\n");
		sb.append("	        x:'right',	\n");
		sb.append("	        data:['门店编号']	\n");
		sb.append("	    },	\n");
		sb.append("	    dataRange: {	\n");
		sb.append("	        min : 0,	\n");
		sb.append("	        max : " + max + ",	\n");
		sb.append("	        calculable : true,	\n");
		sb.append("	        color: ['maroon','purple','red','orange','yellow','lightgreen']	\n");
		sb.append("	    },	\n");
		sb.append("	    toolbox: {	\n");
		sb.append("	        show : true,	\n");
		sb.append("	        orient : 'vertical',	\n");
		sb.append("	        x: 'right',	\n");
		sb.append("	        y: 'center',	\n");
		sb.append("	        feature : {	\n");
		sb.append("	            mark : {show: true},	\n");
		sb.append("	            dataView : {show: true, readOnly: false},	\n");
		sb.append("	            restore : {show: true},	\n");
		sb.append("	            saveAsImage : {show: true}	\n");
		sb.append("	        }	\n");
		sb.append("	    },	\n");

		sb.append("	    series : [	\n");
		sb.append("	        {	\n");
		sb.append("	            name: '',	\n");
		sb.append("	            type: 'map',	\n");
		sb.append("	            mapType: 'china',");
		sb.append("	            hoverable: false,	\n");
		sb.append("	            roam:true,	\n");
		sb.append("	            data : [" + province_list + "],	\n");
		sb.append("	            itemStyle:{");
		sb.append("	                normal:{label:{show:true}},");
		sb.append("	               emphasis:{label:{show:true}}");
		sb.append("	            },");
		sb.append("	            markPoint : {	\n");
		sb.append("	                symbolSize: 2,      // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2	\n");
		sb.append("	                itemStyle: {	\n");
		sb.append("	                    normal: {	\n");
		sb.append("	                        borderColor: '#87cefa',	\n");
		sb.append("	                        borderWidth: 1,            // 标注边线线宽，单位px，默认为1	\n");
		sb.append("	                        label: {	\n");
		sb.append("	                            show: false	\n");
		sb.append("	                        }	\n");
		sb.append("	                    },	\n");
		sb.append("	                    emphasis: {	\n");
		sb.append("	                        borderColor: '#1e90ff',	\n");
		sb.append("	                        borderWidth: 5,	\n");
		sb.append("	                        label: {	\n");
		sb.append("	                            show: false	\n");
		sb.append("	                        }	\n");
		sb.append("	                    }	\n");
		sb.append("	                },	\n");
		sb.append("	                data : [	\n");
		sb.append(datestr);
		sb.append("	                ]	\n");
		sb.append("	            },	\n");
		sb.append("	            geoCoord: {	\n");
		sb.append(geoCoordstr);
		sb.append("	            }	\n");
		sb.append("	        },	\n");
		sb.append("	        {	\n");
		// sb.append("	            name: 'Top5',	\n");
		// sb.append("	            type: 'map',	\n");
		// sb.append("	            mapType: 'china',	\n");
		// sb.append("	            data:[],	\n");
		// sb.append("	            markPoint : {	\n");
		// sb.append("	                symbol:'emptyCircle',	\n");
		// sb.append("	                symbolSize : function (v){	\n");
		// sb.append("	                    return 10 + v/100	\n");
		// sb.append("	                },	\n");
		// sb.append("	                effect : {	\n");
		// sb.append("	                    show: true,	\n");
		// sb.append("	                    shadowBlur : 0	\n");
		// sb.append("	                },	\n");
		// sb.append("	                itemStyle:{	\n");
		// sb.append("	                    normal:{	\n");
		// sb.append("	                        label:{show:false}	\n");
		// sb.append("	                    }	\n");
		// sb.append("	                },	\n");
		// sb.append("	                data : [	\n");
		// sb.append("	                    {name: \"廊坊\", value: 193},	\n");
		// sb.append("	                    {name: \"菏泽\", value: 194},	\n");
		// sb.append("	                    {name: \"合肥\", value: 229},	\n");
		// sb.append("	                    {name: \"武汉\", value: 273},	\n");
		// sb.append("	                    {name: \"大庆\", value: 279}	\n");
		// sb.append("	                ]	\n");
		// sb.append("	            }	\n");
		sb.append("	        }	\n");
		sb.append("	    ]	\n");
		sb.append("	};	\n");

		String optionStr = sb.toString();// GsonUtil.format(option);
		request.setAttribute("option", optionStr);

		request.setAttribute("title", "map9");
		
		String is_bi = (String) dynaBean.get("is_bi");
		if(StringUtils.isNotBlank(is_bi)){
			request.setAttribute("is_bi",is_bi);
		}
		// request.setAttribute("url",
		// "http://echarts.baidu.com/doc/example/map9.html");
		return mapping.findForward("view");
	}

	public ActionForward ajaxGetDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynabean = (DynaBean) form;
		String terminal_id = (String) dynabean.get("terminal_id");

		
		if (StringUtils.isBlank(terminal_id)) {
			return null;
		}
		String _year = (String) dynabean.get("_year");
		if (_year == null) {
			_year = new SimpleDateFormat("yyyy").format(Calendar.getInstance()
					.getTime());
		}
		KonkaMobileSailData entity = new KonkaMobileSailData();
		entity.setDept_id(Long.valueOf(terminal_id));
		entity.setIs_del(0);
		entity.getMap().put(
				"report_date_begin",
				_year+ "-01-01 00:00:00");
		entity.getMap().put(
				"report_date_end",
				_year+ "-12-31 23:59:59");
		List<HashMap<String, Object>> entityList = super.getFacade()
				.getKonkaMobileSailDataService()
				.getKonkaMobileSailDataForTerminalMap(entity);
		if (null != entityList && entityList.size() > 0) {
			logger.info(entityList.toString());
			JSONArray jsonArray = JSONArray.fromObject(entityList);
			// int start = jsonArray.toString().indexOf("[");
			// int end = jsonArray.toString().lastIndexOf("}");
			// String jsonStr = jsonArray.toString().substring(start + 1, end +
			// 1);
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(jsonArray.toString());
			out.flush();
			out.close();
		}
		return null;
	}

}
