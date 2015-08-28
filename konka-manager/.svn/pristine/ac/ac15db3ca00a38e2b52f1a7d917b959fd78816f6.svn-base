package com.ebiz.mmt.web.struts.manager.paragon;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaParagonShowshopDetail;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaParagonShowshopDetailAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String year = (String) dynaBean.get("year"); // 时间
		String custom_name_like = (String) dynaBean.get("custom_name_like"); // 客户名称
		String have_interface = (String) dynaBean.get("have_interface"); // 网线接口（有/无）
		String date_start = (String) dynaBean.get("date_start"); // 制作时间begain
		String date_end = (String) dynaBean.get("date_end"); // 制作时间end
		String shop_name_like = (String) dynaBean.get("shop_name_like"); // 制作时间end

		// 20120502新增检索条件
		String mile_start = (String) dynaBean.get("mile_start");// 展台延米数
		String mile_end = (String) dynaBean.get("mile_end");// 展台延米数
		String spare_start = (String) dynaBean.get("spare_start");// 展台面积
		String spare_end = (String) dynaBean.get("spare_end");// 展台面积
		String cash_start = (String) dynaBean.get("cash_start");// 制作费
		String cash_end = (String) dynaBean.get("cash_end");// 制作费
		String sales_start = (String) dynaBean.get("sales_start");// 销售额
		String sales_end = (String) dynaBean.get("sales_end");// 销售额
		String et_start = (String) dynaBean.get("et_start");// 进场费
		String et_end = (String) dynaBean.get("et_end");// 进场费
		pager.setPageSize(15);

		// 分公司列表
		List<KonkaDept> deptInfoList = null;
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0L);

		// 获取登录用户 企业信息，分公司只能看见自己的记录
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_INFO);
//		PeRoleUser peRoleUser = (PeRoleUser) super.getSessionAttribute(request, Constants.ROLE_INFO);
		

		@SuppressWarnings("unchecked")
		List<PeRoleUser> peRoleUserList = (List<PeRoleUser>) request.getSession()
				.getAttribute(Constants.ROLE_INFO_LIST);
		boolean role_id_ne_1000 = true;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 1000) {
				role_id_ne_1000 = false;
				break;
			}
		}
		
		if (role_id_ne_1000) { // 非 完美终端总部管理员，一律按分公司处理
			// 部门列表
			konka_dept = new KonkaDept();
			konka_dept.getMap().put("dept_id", userInfo.getDept_id());
			konka_dept.setPar_id(0L);
			deptInfoList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptListWithParTreeName(konka_dept);

		} else {
			deptInfoList = super.getFacade().getKonkaDeptService()
					.getKonkaDeptList(konka_dept);
		}
		request.setAttribute("deptInfoList", deptInfoList);
		if (StringUtils.isBlank(year)) {
			return mapping.findForward("list");
		}
		KonkaParagonShowshopDetail entity = new KonkaParagonShowshopDetail();
		super.copyProperties(entity, form);
		entity.getMap().put("fixdate", year);
		entity.getMap().put("custom_name_like", custom_name_like);

		if (entity.getHave_interface() != null) {
			if (entity.getHave_interface() == 0)
				entity.getMap().put("havet_interface", have_interface);
			else
				entity.getMap().put("have_interface", have_interface);
		}
		entity.getMap().put("shop_name_like", shop_name_like);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(date_start)) {
			entity.getMap().put("date_start",
					simpleDateFormat.parse(date_start));
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", simpleDateFormat.parse(date_end));
		}
		entity.getMap().put("mile_start", mile_start);
		entity.getMap().put("mile_end", mile_end);
		entity.getMap().put("spare_start", spare_start);
		entity.getMap().put("spare_end", spare_end);
		entity.getMap().put("cash_start", cash_start);
		entity.getMap().put("cash_end", cash_end);
		entity.getMap().put("sales_start", sales_start);
		entity.getMap().put("sales_end", sales_end);
		entity.getMap().put("et_start", et_start);
		entity.getMap().put("et_end", et_end);

		Long recordCount = getFacade().getKonkaParagonShowshopDetailService()
				.getKonkaParagonShowshopDetailCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaParagonShowshopDetail> entityList = getFacade()
				.getKonkaParagonShowshopDetailService()
				.getKonkaParagonShowshopDetailPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward toExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String year = (String) dynaBean.get("year"); // 时间
		String custom_name_like = (String) dynaBean.get("custom_name_like"); // 客户名称
		String have_interface = (String) dynaBean.get("have_interface"); // 网线接口（有/无）
		String date_start = (String) dynaBean.get("date_start"); // 制作时间begain
		String date_end = (String) dynaBean.get("date_end"); // 制作时间end
		String shop_name_like = (String) dynaBean.get("shop_name_like"); // 制作时间end
		
		// 20120502新增检索条件
		String mile_start = (String) dynaBean.get("mile_start");// 展台延米数
		String mile_end = (String) dynaBean.get("mile_end");// 展台延米数
		String spare_start = (String) dynaBean.get("spare_start");// 展台面积
		String spare_end = (String) dynaBean.get("spare_end");// 展台面积
		String cash_start = (String) dynaBean.get("cash_start");// 制作费
		String cash_end = (String) dynaBean.get("cash_end");// 制作费
		String sales_start = (String) dynaBean.get("sales_start");// 销售额
		String sales_end = (String) dynaBean.get("sales_end");// 销售额
		String et_start = (String) dynaBean.get("et_start");// 进场费
		String et_end = (String) dynaBean.get("et_end");// 进场费

		// 分公司列表
		KonkaDept konka_dept = new KonkaDept();
		konka_dept.setDept_type(3);
		konka_dept.setPar_id(0L);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptList(konka_dept);
		request.setAttribute("deptInfoList", deptInfoList);
		if (StringUtils.isBlank(year)) {
			return mapping.findForward("list");
		}
		KonkaParagonShowshopDetail entity = new KonkaParagonShowshopDetail();
		super.copyProperties(entity, form);
		entity.getMap().put("fixdate", year);
		entity.getMap().put("custom_name_like", custom_name_like);
		entity.getMap().put("have_interface", have_interface);
		entity.getMap().put("shop_name_like", shop_name_like);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(date_start)) {
			entity.getMap().put("date_start",
					simpleDateFormat.parse(date_start));
		}
		if (StringUtils.isNotBlank(date_end)) {
			entity.getMap().put("date_end", simpleDateFormat.parse(date_end));
		}
		entity.getMap().put("mile_start", mile_start);
		entity.getMap().put("mile_end", mile_end);
		entity.getMap().put("spare_start", spare_start);
		entity.getMap().put("spare_end", spare_end);
		entity.getMap().put("cash_start", cash_start);
		entity.getMap().put("cash_end", cash_end);
		entity.getMap().put("sales_start", sales_start);
		entity.getMap().put("sales_end", sales_end);
		entity.getMap().put("et_start", et_start);
		entity.getMap().put("et_end", et_end);

		List<KonkaParagonShowshopDetail> entityList = getFacade()
				.getKonkaParagonShowshopDetailService()
				.getKonkaParagonShowshopDetailList(entity);

		StringBuffer result = new StringBuffer("");
		StringBuffer result_head = new StringBuffer("");
		// 生成文件头
		if (entityList.size() == 0) {
			result_head.append("没有数据。");
		} else {
			result
					.append(
							"序号\t 门店代码\t 区域\t 分公司\t 经办\t 客户类别\t 客户\t 门店名称\t 年零售额(万元)\t 年进场费(万元)\t 直销员人数\t 展位面积(m2)\t 展位类型\t 展位延米(m)\t 制作时间\t 制作费用(元)\t 网线接口\t 46寸及以上样机数 \t")
					.append("\r\n");
		}
		int i = 1;
		for (KonkaParagonShowshopDetail detail : entityList) {
			result.append(i).append("\t");
			result.append(
					detail.getShow_shop_code() == null ? "" : detail
							.getShow_shop_code()).append("\t");

			if (detail.getArea_id() == null) {
				result.append("其他").append("\t");
			} else {
				switch (detail.getArea_id().intValue()) {
				case 10:
					result.append("华东").append("\t");
					break;
				case 20:
					result.append("山东").append("\t");
					break;
				case 30:
					result.append("东北").append("\t");
					break;
				case 40:
					result.append("华北").append("\t");
					break;
				case 50:
					result.append("华南").append("\t");
					break;
				case 60:
					result.append("西南").append("\t");
					break;
				case 70:
					result.append("华中").append("\t");
					break;
				case 80:
					result.append("西北").append("\t");
					break;
				default:
					result.append("其他").append("\t");
				}
			}
			result.append(
					detail.getMap().get("part_company_name") == null ? ""
							: detail.getMap().get("part_company_name")).append(
					"\t");
			result.append(
					detail.getMap().get("channel_name") == null ? "" : detail
							.getMap().get("channel_name")).append("\t");

			if (detail.getCustom_type() == null) {
				result.append("其他").append("\t");
			} else {
				switch (detail.getCustom_type()) {
				case 1:
					result.append("连锁").append("\t");
					break;
				case 2:
					result.append("超市").append("\t");
					break;
				case 3:
					result.append("县乡客户群").append("\t");
					break;
				case 4:
					result.append("城市客户群").append("\t");
					break;
				default:
					result.append("其他").append("\t");
				}
			}
			result.append(
					detail.getCustom_name() == null ? "" : detail
							.getCustom_name()).append("\t");
			result.append(
					detail.getShow_shop_name() == null ? "" : detail
							.getShow_shop_name()).append("\t");
			if (detail.getSales() == null) {
				result.append("").append("\t");
			} else {
				result.append(detail.getSales()).append("\t");
			}
			if (detail.getEtcash() == null) {
				result.append("").append("\t");
			} else {
				result.append(detail.getEtcash()).append("\t");
			}
			result.append(
					detail.getSeller_num() == null ? "" : detail
							.getSeller_num()).append("\t");
			result.append(
					detail.getShowt_area() == null ? "" : detail
							.getShowt_area()).append("\t");

			// switch (Integer.parseInt(detail.getShowt_type())) {
			// case 1:
			// result.append("--").append("\t");
			// break;
			// case 2:
			// result.append("L").append("\t");
			// break;
			// case 3:
			// result.append("U").append("\t");
			// break;
			// case 4:
			// result.append("通道").append("\t");
			// break;
			// default:
			// result.append("其他").append("\t");
			// }
			result.append(
					detail.getShowt_type() == null ? "" : detail
							.getShowt_type()).append("\t");
			result.append(
					detail.getShowt_mile() == null ? "" : detail
							.getShowt_mile()).append("\t");
			if (detail.getShowt_time() == null) {
				result.append(" \t");
			} else {
				result.append(simpleDateFormat.format(detail.getShowt_time()))
						.append("\t");
			}

			if (detail.getShowt_cash() == null) {
				result.append("").append("\t");
			} else {
				result.append(detail.getShowt_cash()).append("\t");
			}
			if (detail.getHave_interface() == null) {
				result.append("无").append("\t");
			} else {
				switch (detail.getHave_interface()) {
				case 0:
					result.append("无").append("\t");
					break;
				case 1:
					result.append("有").append("\t");
					break;
				default:
					result.append("无").append("\t");
				}
			}
			result.append(
					detail.getMap().get("proto_sum") == null ? "" : detail
							.getMap().get("proto_sum")).append("\t");
			result.append("\r\n");
			i++;
		}

		StringBuffer all_buffer = result_head.append("\r\n").append(result);

		InputStream inStream = new ByteArrayInputStream(new String(all_buffer)
				.getBytes());
		byte[] buf = new byte[10240];
		int len = 0;
		// 文件名
		String filename = "zonghechaxun.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ filename.toString());

		OutputStream out = response.getOutputStream();
		while ((len = inStream.read(buf)) > 0)
			out.write(buf, 0, len);
		inStream.close();
		out.close();

		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String show_shop_id = (String) dynaBean.get("show_shop_id");

		if (GenericValidator.isLong(show_shop_id)) {
			KonkaParagonShowshopDetail entity = new KonkaParagonShowshopDetail();
			entity.setShow_shop_id(new Long(show_shop_id));
			entity = getFacade().getKonkaParagonShowshopDetailService()
					.getKonkaParagonShowshopDetail(entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			}

			super.copyProperties(form, entity);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", show_shop_id);
			return mapping.findForward("list");
		}
	}
}