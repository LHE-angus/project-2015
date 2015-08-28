package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wang,Yang
 * @version 2011-09-25
 */
public class SellDataStatisticsAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");

		KonkaR3Shop entity = super.getR3ShopByAddDate(mapping, form, request, response);
		entity.getMap().put("s_state", 1);

		Long recordCount = getFacade().getKonkaR3ShopService().getKonkaR3ShopCountByAddDate(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedListByAddDate(entity);

		super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);

		request.setAttribute("entityList", entityList);

		request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
		return mapping.findForward("list");
	}

	public ActionForward toStatistics(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.statistics(mapping, form, request, response);
	}

	@SuppressWarnings("unchecked")
	public ActionForward statistics(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		Integer pageSize = pager.getPageSize();
		String sell_date_start = (String) dynaBean.get("sell_date_start");
		String sell_date_end = (String) dynaBean.get("sell_date_end");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");
		String downloadAll = (String) dynaBean.get("downloadAll");
		String cus_sn_like = (String) dynaBean.get("cus_sn_like");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		// PeProdUser peProdUser = (PeProdUser) session.getAttribute(Constants.PE_PROD_USER_SESSION);

		KonkaR3Shop r3Shop = new KonkaR3Shop();
		r3Shop.setId(Long.valueOf(r3_shop_id));
		r3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(r3Shop);

		KonkaSell entity = new KonkaSell();
		entity.setIs_del(0);
		entity.setState(1);
		entity.setCus_sn(r3Shop.getR3_code());
		entity.getMap().put("cus_sn_like", cus_sn_like);

		entity.getMap().put("state_in", new Integer[] { 1, 2 });

		if (StringUtils.isNotBlank(sell_date_start)) {
			entity.getMap().put("sell_date_start", sell_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(sell_date_end)) {
			entity.getMap().put("sell_date_end", sell_date_end + " 23:59:59");
		}
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
		}

		// 处理不同权限的人登陆系统所需要查询的数据
		// if ("true".equals(peProdUser.getMap().get("is_leader"))) {
		// if (peProdUser.getUser_type() != 0) {
		// entity.getMap().put("is_leader_dept_id", peProdUser.getDept_id());
		// }
		// } else {
		// entity.getMap().put("add_user_id", ui.getId());
		if (0 != ui.getId()) { // 超级管理员可以查看全部
			entity.getMap().put("dept_id", ui.getDept_id());
		}
		// }

		//为下载单个网点一个时间段内的销售数据的提供s_id
		List<KonkaSell> sellList = super.getFacade().getKonkaSellService().getKonkaSellList(entity);
		ArrayList ids = new ArrayList();
		for (KonkaSell sell : sellList) {
			ids.add(sell.getS_id());
		}
		request.setAttribute("ids", ids);
		logger.info("+++++++++++++++++++ids_size:{}",ids.size());
		
		Long recordCount = super.getFacade().getKonkaSellService().getKonkaSellCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSell> konkaSellList = super.getFacade().getKonkaSellService().getKonkaSellPaginatedList(entity);

		// 处理是否全部下载
		if (StringUtils.isNotEmpty(downloadAll) && "all".equals(downloadAll)) {
			pager.init(recordCount, recordCount.intValue(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(pager.getRowCount());

			List<KonkaSell> downloadList = super.getFacade().getKonkaSellService().getKonkaSellPaginatedList(entity);

			request.setAttribute("downloadList", downloadList);
			pager.init(recordCount, pageSize, pager.getRequestPage());
		} else {
			request.setAttribute("downloadList", konkaSellList);
		}
		request.setAttribute("konkaSellList", konkaSellList);

		// request.setAttribute("is_leader", peProdUser.getMap().get("is_leader"));
		return new ActionForward(response.encodeRedirectURL("/admin/SellDataStatistics/statistics.jsp"));
	}

	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String s_id = (String) dynaBean.get("s_id");

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String date = s.format(new Date()).toString();

		if (!GenericValidator.isLong(s_id)) {
			saveError(request, "errors.long", new String[] { s_id });
			return mapping.findForward("list");
		}

		// 取销售记录信息
		KonkaSell entity = new KonkaSell();
		entity.setS_id(Long.valueOf(s_id));
		entity = super.getFacade().getKonkaSellService().getKonkaSell(entity);

		// 取分配给自己所在分公司的产品型号
		List<PePdModel> kpmList = super.getDeptLinkProduct(request, response, null, null, null,null);

		WritableWorkbook wwb = null;
		String excel_name = "HsInFo_Tjd_temp  " + date + ".xls";
		try {
			// 获取系统实际路径
			String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(SystemPath + "files/" + excel_name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);

			ws.addCell(new Label(0, 0, "客户R3编码"));
			ws.addCell(new Label(1, 0, entity.getCus_sn()));

			ws.addCell(new Label(0, 1, "客户名称"));
			ws.addCell(new Label(1, 1, entity.getCus_name()));

			ws.addCell(new Label(0, 2, "销售日期"));
			ws.addCell(new Label(1, 2, DateFormatUtils.format(entity.getSell_date(), "yyyy-MM-dd hh:mm:ss")));
			ws.addCell(new Label(0, 3, "信息填写时间"));
			ws.addCell(new Label(1, 3, DateFormatUtils.format(entity.getAdd_date(), "yyyy-MM-dd hh:mm:ss")));

			ws.addCell(new Label(0, 4, ""));
			ws.addCell(new Label(1, 4, "型号名称"));
			ws.addCell(new Label(2, 4, "销售数量"));
			ws.addCell(new Label(3, 4, "销售单价"));
			ws.addCell(new Label(4, 4, "销售总额"));

			Iterator<PePdModel> it = kpmList.iterator();
			int i = 5;
			Double str_label5 = 0D;
			BigDecimal str_label6 = new BigDecimal(0);

			while (it.hasNext()) {
				PePdModel pePdModel = (PePdModel) it.next();
				Label label0 = new Label(0, i, Integer.toString(i-3));
				Label label1 = new Label(1, i, pePdModel.getMd_name());

				// 取销售记录详细信息
				KonkaSellDetails ksd = new KonkaSellDetails();
				ksd.setS_id(entity.getS_id());
				ksd.setPd_id(pePdModel.getPd_id());
				ksd = super.getFacade().getKonkaSellDetailsService().getKonkaSellDetails(ksd);
				if (null != ksd) {
					String str_label2 = "";
					String str_label3 = "";
					String str_label4 = "";
					if (ksd.getSell_count() != null) {
						str_label2 = ksd.getSell_count().toString();
						str_label5 = str_label5 + Double.parseDouble(str_label2);
					}
					if (ksd.getSell_money() != null) {
						str_label3 = ksd.getSell_money().toString();
					}
					if (ksd.getSell_count() != null && ksd.getSell_money() != null) {
						str_label4 = BigDecimal.valueOf(Double.parseDouble(str_label2))
								.multiply(BigDecimal.valueOf(Double.parseDouble(str_label3))).toString();
					}
					str_label6 = str_label6.add(new BigDecimal(str_label4));
					if (!str_label4.equals("0.00")) {
						Label label2 = new Label(2, i, str_label2);
						Label label3 = new Label(3, i, str_label3);
						Label label4 = new Label(4, i, str_label4);
						try {// 将生成的单元格添加到工作表中
							ws.addCell(label0);
							ws.addCell(label1);
							ws.addCell(label2);
							ws.addCell(label3);
							ws.addCell(label4);
						} catch (RowsExceededException e) {
							e.printStackTrace();
						} catch (WriteException e) {
							e.printStackTrace();
						}
						i++;
					}
				}
			}
			Label label5 = new Label(2, i + 1, str_label5.toString());
			Label label6 = new Label(4, i + 1, str_label6.toString());
			try {// 将生成的单元格添加到工作表中
				ws.addCell(new Label(0, i, "合计"));
				ws.addCell(new Label(2, i, "总销售数量"));
				ws.addCell(new Label(4, i, "销售总价"));
				ws.addCell(label5);
				ws.addCell(label6);
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}

			try {
				wwb.write();// 从内存中写入文件中
				wwb.removeSheet(0);
				wwb.close();// 关闭资源，释放内存
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		String ctx = super.getCtxPath(request);
		super.renderJavaScript(response, "window.location.href='" + ctx + "/files/" + excel_name + "';");
		return null;
	}

	public ActionForward downloadAll(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String ids = (String) dynaBean.get("ids");
		String sell_date_start = (String) dynaBean.get("sell_date_start");// 客户所查销售数据上报搜索条件中的盘存开始时间
		String sell_date_end = (String) dynaBean.get("sell_date_end");// 客户所查销售数据上报搜索条件中的盘存结束时间

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String date = s.format(new Date()).toString();

		if (!StringUtils.isNotBlank(ids)) {
			saveError(request, "errors.long", new String[] { ids });
			return mapping.findForward("list");
		}
		
		String ids_list = ids.substring(1, ids.length() - 1);
		logger.info("----------------------------+++ids:{}",ids);

		// 取分配给自己所在分公司的产品型号
		List<PePdModel> kpmList = super.getDeptLinkProduct(request, response, null, null, null,null);

		WritableWorkbook wwb = null;
		String excel_name = "HsInFo_Tjd_temp  " + date + ".xls";
		try {
			// 获取系统实际路径
			String SystemPath = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(SystemPath + "files/" + excel_name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wwb != null) {
			// 取销售记录信息
			KonkaSell entity = new KonkaSell();
			entity.getMap().put("s_ids", ids_list);
			List<KonkaSell> sellList = super.getFacade().getKonkaSellService().getKonkaSellList(entity);

			if (sellList != null) {
				// 创建一个可写入的工作表
				// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
				WritableSheet ws = wwb.createSheet("sheet1", 0);

				ws.addCell(new Label(0, 0, "客户R3编码"));
				ws.addCell(new Label(1, 0, sellList.get(0).getCus_sn()));

				ws.addCell(new Label(0, 1, "客户名称"));
				ws.addCell(new Label(1, 1, sellList.get(0).getCus_name()));

				if(!StringUtils.isNotBlank(sell_date_start) || !StringUtils.isNotBlank(sell_date_end)){
					ws.addCell(new Label(0, 2, "查询的是当前客户的所有销售数据上报记录"));
				}else{
					ws.addCell(new Label(0, 2, "销售开始时间"));
				    ws.addCell(new Label(1, 2, sell_date_start + " 00:00:00"));
					ws.addCell(new Label(2, 2, "销售结束时间"));
					ws.addCell(new Label(3, 2, sell_date_end + " 23:59:59"));
				}
	
				ws.addCell(new Label(0, 3, ""));
				ws.addCell(new Label(1, 3, "型号名称"));
				ws.addCell(new Label(2, 3, "销售数量"));
				ws.addCell(new Label(3, 3, "销售单价"));
				ws.addCell(new Label(4, 3, "销售总额"));

				int i = 4;
				Double str_label5 = 0D;
				BigDecimal str_label6 = new BigDecimal(0);

				for (KonkaSell sell : sellList) {
					Iterator<PePdModel> it = kpmList.iterator();
					while (it.hasNext()) {
						PePdModel pePdModel = (PePdModel) it.next();
						Label label0 = new Label(0, i, Integer.toString(i-4));
						Label label1 = new Label(1, i, pePdModel.getMd_name());
						// 取销售记录详细信息
						KonkaSellDetails ksd = new KonkaSellDetails();
						ksd.setS_id(sell.getS_id());
						ksd.setPd_id(pePdModel.getPd_id());
						ksd = super.getFacade().getKonkaSellDetailsService().getKonkaSellDetails(ksd);
						if (null != ksd) {
							String str_label2 = "";
							String str_label3 = "";
							String str_label4 = "";
							if (ksd.getSell_count() != null) {
								str_label2 = ksd.getSell_count().toString();
								str_label5 = str_label5 + Double.parseDouble(str_label2);
							}
							if (ksd.getSell_money() != null) {
								str_label3 = ksd.getSell_money().toString();
							}
							if (ksd.getSell_count() != null && ksd.getSell_money() != null) {
								str_label4 = BigDecimal.valueOf(Double.parseDouble(str_label2))
										.multiply(BigDecimal.valueOf(Double.parseDouble(str_label3))).toString();
							}
							str_label6 = str_label6.add(new BigDecimal(str_label4));
							if (!str_label4.equals("0.00")) {
								Label label2 = new Label(2, i, str_label2);
								Label label3 = new Label(3, i, str_label3);
								Label label4 = new Label(4, i, str_label4);
								try {// 将生成的单元格添加到工作表中
									ws.addCell(label0);
									ws.addCell(label1);
									ws.addCell(label2);
									ws.addCell(label3);
									ws.addCell(label4);
								} catch (RowsExceededException e) {
									e.printStackTrace();
								} catch (WriteException e) {
									e.printStackTrace();
								}
								i++;
							}
						}
					}
				}
				Label label5 = new Label(2, i + 1, str_label5.toString());
				Label label6 = new Label(4, i + 1, str_label6.toString());
				try {// 将生成的单元格添加到工作表中
					ws.addCell(new Label(0, i, "合计"));
					ws.addCell(new Label(2, i, "总销售数量"));
					ws.addCell(new Label(4, i, "销售总价"));
					ws.addCell(label5);
					ws.addCell(label6);
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
			try {
				wwb.write();// 从内存中写入文件中
				wwb.removeSheet(0);
				wwb.close();// 关闭资源，释放内存
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		String ctx = super.getCtxPath(request);
		super.renderJavaScript(response, "window.location.href='" + ctx + "/files/" + excel_name + "';");
		return null;
	}
}