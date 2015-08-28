package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCustVisitGps;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @date 2014-11-21
 * @author Wangk,KunLin
 * 
 */
public class KonkaR3StoreAction extends BaseAction {
	private static final double PI = 3.14159265;
	private static final double EARTH_RADIUS = 6378137;
	private static final double RAD = Math.PI / 180.0;

	/***
	 * 手机终端首页列表 wang,KunLin 2014-11-21
	 */
	public ActionForward listMainPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
 HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String terminal_name_like = (String) dynaBean.get("terminal_name_like");
		String first_row = (String) dynaBean.get("first_row");
		String last_row = (String) dynaBean.get("last_row");
		String max_terminal_id = (String) dynaBean.get("max_terminal_id");
		String min_terminal_id = (String) dynaBean.get("min_terminal_id");
		String position_x = (String) dynaBean.get("position_x");
		String position_y = (String) dynaBean.get("position_y");
		String city = (String) dynaBean.get("city");
		String type = (String) dynaBean.get("type");//0 销售网点 1 服务网点  2 附近的店
		String order_by_index=(String) dynaBean.get("order_by");//1 是销量排序  2是距离

		String terminal_id = (String) dynaBean.get("terminal_id");

		KonkaR3Store entity = new KonkaR3Store();
		
		
		if(StringUtils.isNotBlank(terminal_name_like)){
			entity.getMap().put("terminal_name_like", terminal_name_like);
		}
		

		if (StringUtils.isNotBlank(terminal_id)
				&& GenericValidator.isLong(terminal_id)) {
			entity.getMap().put("terminal_id", Long.valueOf(terminal_id));
			entity.getMap().put("first_row", 1);
			entity.getMap().put("last_row", 1);
		} else {
			if (StringUtils.isBlank(first_row)
					|| StringUtils.isBlank(first_row)) {
				entity.getMap().put("first_row", 1);
				entity.getMap().put("last_row", 20);
			} else {
				if (GenericValidator.isInt(first_row)) {
					entity.getMap().put("first_row",
							Integer.parseInt(first_row));
				}
				if (GenericValidator.isInt(last_row)) {
					entity.getMap().put("last_row", Integer.parseInt(last_row));
				}
			}
		}
		if (StringUtils.isNotBlank(max_terminal_id)) {
			entity.getMap().put("max_termianl_id", max_terminal_id);
		}
		if (StringUtils.isNotBlank(min_terminal_id)) {
			entity.getMap().put("min_termianl_id", min_terminal_id);
		}

		if (StringUtils.isNotBlank(type)) {
			if ("2".equals(type)) {
				entity.getMap().put("order_by_distinct", "yes");
				if (StringUtils.isNotBlank(position_y)
						&& StringUtils.isNotBlank(position_x)) {
					// 找附件的店
					double[] ground = getAround(new Double(position_y),
							new Double(position_x), 5000);
					entity.getMap().put("min_position_y", ground[0]);
					entity.getMap().put("min_position_x", ground[1]);
					entity.getMap().put("max_position_y", ground[2]);
					entity.getMap().put("max_position_x", ground[3]);
					entity.getMap().put("terminal_position_x", position_x);
					entity.getMap().put("terminal_position_y", position_y);
				} else {
					if (StringUtils.isBlank(position_x)) {
						position_x = "113.9";
					}
					if (StringUtils.isBlank(position_y)) {
						position_y = "22.540665";
					}
					entity.getMap().put("terminal_position_x", position_x);
					entity.getMap().put("terminal_position_y", position_y);
				}
				
			}else if("0".equals(type) &&StringUtils.isNotBlank(order_by_index) ){
				if("1".equals(order_by_index) ){
					entity.getMap().put("order_by_salenum", "yes");
				}	else if( "2".equals(order_by_index)){
					entity.getMap().put("order_by_distinct", "yes");
					if (StringUtils.isBlank(position_x)) {
						position_x = "113.9";
					}
					if (StringUtils.isBlank(position_y)) {
						position_y = "22.540665";
					}
					entity.getMap().put("terminal_position_x", position_x);
					entity.getMap().put("terminal_position_y", position_y);
				}
			}else if ("1".equals(type)) {
				return null;
			}
		}
		
			
		if (StringUtils.isBlank(city)) {
			city = "深圳市";
		}
		BaseProvinceListFour provinceListFour = new BaseProvinceListFour();
		provinceListFour.setP_name(city.trim());
		provinceListFour.setP_level(2);
		provinceListFour = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFour(provinceListFour);
		if (null != provinceListFour && null != provinceListFour.getP_index()) {
			entity.getMap().put("p_index_code",
					(provinceListFour.getP_index() + "").substring(0, 4));
		}
		
		
		List<HashMap> entityList = getFacade().getKonkaR3StoreService()
				.getKonkaR3StoreAndJbasePartnerForMainPage(entity);
		if (null == entityList || entityList.size() == 0) {
			return null;
		} else {
			for (HashMap map : entityList) {
				if (null != map.get("TERMINAL_POSITION_X")
						&& null != map.get("TERMINAL_POSITION_Y")
						&& StringUtils.isNotBlank(position_y)
						&& StringUtils.isNotBlank(position_x)) {
					double distanct = getDistance(new Double(""
							+ map.get("TERMINAL_POSITION_X")), new Double(""
							+ map.get("TERMINAL_POSITION_Y")), new Double(
							position_x), new Double(position_y));
					map.put("DISTANCT", distanct);
				} else {
					map.put("DISTANCT", 0);
				}
			}
		}

		logger.info(entityList.toString());
		JSONArray jsonArray = JSONArray.fromObject(entityList);
		// int start = jsonArray.toString().indexOf("[");
		// int end = jsonArray.toString().lastIndexOf("}");
		// String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;

	}

	/***
	 * 手机终端修改功能的列表 wang,KunLin 2014-11-21
	 */
	public ActionForward listManagerPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");

		String first_row = (String) dynaBean.get("first_row");
		String last_row = (String) dynaBean.get("last_row");
		String terminal_id = (String) dynaBean.get("terminal_id");

		String max_terminal_id = (String) dynaBean.get("max_terminal_id");
		String min_terminal_id = (String) dynaBean.get("min_terminal_id");
		
		String terminal_name_like = (String) dynaBean.get("terminal_name_like");
		
	
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在
		PeProdUser user = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaR3Store entity = new KonkaR3Store();

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
			entity.getMap().put("filter_by_ywy_id_eq", user.getId());
			break;
		default:
			// 出错
		}
		// 数据级别判断结束
		entity.getMap().put("session_user_id", user.getId());

		if (StringUtils.isNotBlank(terminal_id) && GenericValidator.isLong(terminal_id)) {
			entity.getMap().put("terminal_id", Long.valueOf(terminal_id));
			entity.getMap().put("first_row", 1);
			entity.getMap().put("last_row", 1);
		} else {
			if (StringUtils.isBlank(first_row) || StringUtils.isBlank(first_row)) {
				entity.getMap().put("first_row", 1);
				entity.getMap().put("last_row", 20);
			} else {
				if (GenericValidator.isInt(first_row)) {
					entity.getMap().put("first_row", Integer.parseInt(first_row));
				}
				if (GenericValidator.isInt(last_row)) {
					entity.getMap().put("last_row", Integer.parseInt(last_row));
				}
			}
		}
		if (StringUtils.isNotBlank(max_terminal_id)) {
			entity.getMap().put("max_termianl_id", Long.valueOf(max_terminal_id));
		}
		if (StringUtils.isNotBlank(min_terminal_id)) {
			entity.getMap().put("min_termianl_id", Long.valueOf(min_terminal_id));
		}
		if (StringUtils.isNotBlank(terminal_name_like)) {
			entity.getMap().put("terminal_name_like", terminal_name_like);
		}
		List<HashMap> entityList = getFacade().getKonkaR3StoreService()
		        .getKonkaR3StoreAndJbasePartnerForManager(entity);
		logger.info(entityList.toString());
		JSONArray jsonArray = JSONArray.fromObject(entityList);
		// int start = jsonArray.toString().indexOf("[");
		// int end = jsonArray.toString().lastIndexOf("}");
		// String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTerminalModify(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 终端ID
		String terminal_id = (String) dynaBean.get("terminal_id");
		// 终端name
		String terminal_name = (String) dynaBean.get("terminal_name");
		// 终端简介
		String terminal_memo = (String) dynaBean.get("terminal_memo");
		// 终端类型 1 门店 2 网店
		String terminal_type = (String) dynaBean.get("terminal_type");
		// 上报人经度
		String terminal_position_x = (String) dynaBean.get("terminal_position_x");
		// 上报人纬度
		String terminal_position_y = (String) dynaBean.get("terminal_position_y");
		// 上报人地址
		String terminal_addr = (String) dynaBean.get("terminal_addr");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser user = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (StringUtils.isBlank(terminal_type) || StringUtils.isBlank(terminal_id)) {
			super.renderJavaScript(response, "window.onload=function(){alert('操作所需参数信息不完整');history.back();}");
			return null;
		}

		if ("1".equals(terminal_type)) {
			KonkaR3Store entity = new KonkaR3Store();
			entity.setStore_id(Long.valueOf(terminal_id));
			if (StringUtils.isNotBlank(terminal_name)) {
				entity.setStore_name(terminal_name);
			}
			if (StringUtils.isNotBlank(terminal_memo)) {
				entity.setMemo(terminal_memo);
			}

			super.getFacade().getKonkaR3StoreService().modifyKonkaR3Store(entity);

			// gps信息修改开始
			KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
			gps.setLink_id(Long.valueOf(terminal_id));
			gps.setLink_tab("KONKA_R3_STORE");
			boolean is_update = false;
			List<KonkaMobileCustVisitGps> gpsList = super.getFacade().getKonkaMobileCustVisitGpsService()
			        .getKonkaMobileCustVisitGpsList(gps);
			if (null != gpsList && gpsList.size() > 0) {
				gps = gpsList.get(0);
				is_update = true;
			}

			if (StringUtils.isNotBlank(terminal_position_x)) {
				gps.setPosition_x(new BigDecimal(terminal_position_x));
			}
			if (StringUtils.isNotBlank(terminal_position_y)) {
				gps.setPosition_y(new BigDecimal(terminal_position_y));
			}
			if (null != user && null != user.getId()) {
				gps.setOpr_user_id(user.getId());
			}
			if (StringUtils.isNotBlank(terminal_addr)) {
				gps.setAddr(terminal_addr);
			}

			if (is_update) {
				super.getFacade().getKonkaMobileCustVisitGpsService().modifyKonkaMobileCustVisitGps(gps);

			} else {
				super.getFacade().getKonkaMobileCustVisitGpsService().createKonkaMobileCustVisitGps(gps);
			}

		}
		if ("2".equals(terminal_type)) {
			JBasePartner entity = new JBasePartner();
			entity.setPartner_id(Long.valueOf(terminal_id));
			if (StringUtils.isNotBlank(terminal_name)) {
				entity.setPartner_name(terminal_name);
			}
			if (StringUtils.isNotBlank(terminal_memo)) {
				entity.setMemo(terminal_memo);
			}
			super.getFacade().getJBasePartnerService().modifyJBasePartner(entity);
			// gps信息修改开始
			boolean is_update = false;
			KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
			gps.setLink_id(Long.valueOf(terminal_id));
			gps.setLink_tab("J_BASE_PARTNER");
			List<KonkaMobileCustVisitGps> gpsList = super.getFacade().getKonkaMobileCustVisitGpsService()
			        .getKonkaMobileCustVisitGpsList(gps);
			if (null != gpsList && gpsList.size() > 0) {
				gps = gpsList.get(0);
				is_update = true;
			}

			if (StringUtils.isNotBlank(terminal_position_x)) {
				gps.setPosition_x(new BigDecimal(terminal_position_x));
			}
			if (StringUtils.isNotBlank(terminal_position_y)) {
				gps.setPosition_y(new BigDecimal(terminal_position_y));
			}
			if (null != user && null != user.getId()) {
				gps.setOpr_user_id(user.getId());
			}
			if (StringUtils.isNotBlank(terminal_addr)) {
				gps.setAddr(terminal_addr);
			}

			if (is_update) {
				super.getFacade().getKonkaMobileCustVisitGpsService().modifyKonkaMobileCustVisitGps(gps);

			} else {
				super.getFacade().getKonkaMobileCustVisitGpsService().createKonkaMobileCustVisitGps(gps);
			}

			// gps信息修改结束

		}
		super.renderTextOrJsonp(request, response, "success:" + terminal_id);
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showTerminal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		// 终端ID
		String terminal_id = (String) dynaBean.get("terminal_id");
		// 终端name
		String terminal_name = (String) dynaBean.get("terminal_name");
		// 终端简介
		String terminal_memo = (String) dynaBean.get("terminal_memo");
		// 终端类型 1 门店 2 网店
		String terminal_type = (String) dynaBean.get("terminal_type");
		// 上报人经度
		String terminal_position_x = (String) dynaBean.get("terminal_position_x");
		// 上报人纬度
		String terminal_position_y = (String) dynaBean.get("terminal_position_y");
		// 上报人地址
		String terminal_addr = (String) dynaBean.get("terminal_addr");

		// // 用户名或者密码不存在
		// PeProdUser user = checkUser(inuser_id, inuserpass);
		// if (null == user) {
		// String msg = super.getMessage(request, "user.session.isEmpty");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }
		if (StringUtils.isBlank(terminal_type) || StringUtils.isBlank(terminal_id)) {
			super.renderJavaScript(response, "window.onload=function(){alert('操作所需参数信息不完整');history.back();}");
			return null;
		}

		if ("1".equals(terminal_type)) {
			KonkaR3Store entity = new KonkaR3Store();
			entity.setStore_id(Long.valueOf(terminal_id));

			entity = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(entity);

			// 门店总销售量（1年内）
			KonkaMobileSailData ks = new KonkaMobileSailData();
			ks.setDept_id(Long.valueOf(terminal_id));
			List<KonkaMobileSailData> ksList = super.getFacade().getKonkaMobileSailDataService()
			        .getKonkaMobileSailDataForSaleNumList(ks);
			String s_num = "";
			if (ksList != null && ksList.size() > 0) {
				ks = ksList.get(0);
				Long sale_num = (Long) ks.getMap().get("sale_num");
				s_num = sale_num.toString();
			}
			entity.getMap().put("s_num", s_num);

			request.setAttribute("entity", entity);

			// gps信息开始
			KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
			gps.setLink_id(Long.valueOf(terminal_id));
			gps.setLink_tab("KONKA_R3_STORE");
			List<KonkaMobileCustVisitGps> gpsList = super.getFacade().getKonkaMobileCustVisitGpsService()
			        .getKonkaMobileCustVisitGpsList(gps);
			if (null != gpsList && gpsList.size() > 0) {
				gps = gpsList.get(0);
				if (StringUtils.isNotBlank(terminal_position_x) && StringUtils.isNotBlank(terminal_position_y)
				        && null != gps.getPosition_y() && null != gps.getPosition_x()) {
					request.setAttribute("distanct", getDistance(gps.getPosition_x().doubleValue(), gps.getPosition_y()
					        .doubleValue(), new Double(terminal_position_x), new Double(terminal_position_y)));

				}
			}
			request.setAttribute("gps", gps);

			// 门店商品销售排行前十（1年内）
			KonkaMobileSailData kd = new KonkaMobileSailData();
			kd.setDept_id(Long.valueOf(terminal_id));
			List<KonkaMobileSailData> salesTop10List = super.getFacade().getKonkaMobileSailDataService()
			        .getKonkaMobileSailDataForTop10List(kd);
			request.setAttribute("salesTop10List", salesTop10List);
			logger.info("salesTop10List----->" + salesTop10List.size());

			Attachment attachment = new Attachment();

			attachment.setLink_id(Long.valueOf(terminal_id));
			attachment.setLink_tab("KONKA_R3_STORE");

			List<Attachment> attachmentlist = super.getFacade().getAttachmentService().getAttachmentList(attachment);
			request.setAttribute("attachmentList", attachmentlist);

		}
		if ("2".equals(terminal_type)) {
			JBasePartner entity = new JBasePartner();
			entity.setPartner_id(Long.valueOf(terminal_id));
			List<JBasePartner> entityList = super.getFacade().getJBasePartnerService().getJBasePartnerList(entity);
			if (null != entityList && entityList.size() > 0) {
				entity = entityList.get(0);
			}

			request.setAttribute("entity", entity);

			KonkaMobileCustVisitGps gps = new KonkaMobileCustVisitGps();
			gps.setLink_id(Long.valueOf(terminal_id));
			gps.setLink_tab("J_BASE_PARTNER");
			List<KonkaMobileCustVisitGps> gpsList = super.getFacade().getKonkaMobileCustVisitGpsService()
			        .getKonkaMobileCustVisitGpsList(gps);
			if (null != gpsList && gpsList.size() > 0) {
				gps = gpsList.get(0);
			}

			request.setAttribute("gps", gps);

			Attachment attachment = new Attachment();

			attachment.setLink_id(Long.valueOf(terminal_id));
			attachment.setLink_tab("J_BASE_PARTNER");

			List<Attachment> attachmentlist = super.getFacade().getAttachmentService().getAttachmentList(attachment);
			request.setAttribute("attachmentList", attachmentlist);

		}

		request.setAttribute("terminal_type", terminal_type);
		return new ActionForward("/KonkaR3Store/viewTerminal.jsp");
	}

	/***
	 * 门店终端的图片保存方法
	 * 
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws Exception
	 * @author WangKunLin 2014-11-21
	 */
	public ActionForward SaveAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String lid = (String) dynaBean.get("terminal_id");
		String user_id = (String) dynaBean.get("user_id");
		String userpass = (String) dynaBean.get("userpass");
		String terminal_type = (String) dynaBean.get("terminal_type");
		String terminal_photo_type = (String) dynaBean.get("terminal_photo_type");

		if (!GenericValidator.isLong(lid)) {
			this.saveError(request, "errors.long", new String[] { lid });
			return null;
		}
		if (!GenericValidator.isInt(terminal_photo_type)) {
			this.saveError(request, "errors.long", new String[] { terminal_type });
			return null;
		}
		if (!GenericValidator.isInt(terminal_type)) {
			this.saveError(request, "errors.long", new String[] { terminal_photo_type });
			return null;
		}

		logger.info("--------------------lid==" + lid);
		logger.info("--------------------username==" + user_id);
		logger.info("--------------------userpass==" + userpass);

		String link_tab = "";

		if ("1".equals(terminal_type)) {
			link_tab = "KONKA_R3_STORE";
		} else {
			link_tab = "J_BASE_PARTNER";
		}
		String file_desc = "";
		switch (Integer.parseInt(terminal_photo_type)) {
		case 1:
			file_desc = "康佳全景";
			break;
		case 2:
			file_desc = "康佳主形象";
			break;
		case 3:
			file_desc = "康佳左面";
			break;
		case 4:
			file_desc = "康佳右面";
			break;
		case 5:
			file_desc = "三星全景";
			break;
		case 6:
			file_desc = "海信全景";
			break;
		case 7:
			file_desc = "创维全景";
			break;
		default:
			file_desc = "其他";
		}
		KonkaR3Store kbs = new KonkaR3Store();

		super.copyProperties(kbs, form);
		// 附件处理
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH, true, 0, new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				filesAttachment = new Attachment();
				filesAttachment.setFile_name(uploadFile.getFileName());
				filesAttachment.setFile_type(uploadFile.getContentType());
				filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
				filesAttachment.setSave_path(uploadFile.getFileSavePath());
				filesAttachment.setSave_name(uploadFile.getFileSaveName());
				filesAttachment.setDel_mark(new Short("0"));
				filesAttachment.setLink_tab(link_tab);
				filesAttachment.setFile_desc(file_desc);
				filesAttachmentList.add(filesAttachment);
			}
		}
		kbs.setAttachmentList(filesAttachmentList);
		super.getFacade().getKonkaR3StoreService().createKonkaR3StoreAttachment(kbs, Long.valueOf(lid));

		return null;

	}

	/**
	 * 根据给定的经纬及给出的距离（米）算出经纬度的范围
	 * 
	 * @param lat
	 * @param lon
	 * @param raidus
	 * @return
	 */
	protected double[] getAround(double lat, double lon, int raidus) {
		Double latitude = lat; // 纬度
		Double longitude = lon;// 经度
		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;
		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		// //System.out.println(&quot;[&quot;+minLat+&quot;,&quot;+minLng+&quot;,&quot;+maxLat+&quot;,&quot;+maxLng+&quot;]&quot;);
		return new double[] { minLat, minLng, maxLat, maxLng };
	}

	/**
	 * 根据给出的两组经纬度算出距离，返回单位是--千米
	 * 
	 * @param longitude1
	 * @param latitude1
	 * @param longitude2
	 * @param latitude2
	 * @return
	 */
	protected double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
		double Lat1 = rad(latitude1);
		double Lat2 = rad(latitude2);
		double a = Lat1 - Lat2;
		double b = rad(longitude1) - rad(longitude2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2)
		        * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000 / 1000;
		return s;
	}

	private double rad(double d) {
		return d * Math.PI / 180.0;
	}

}