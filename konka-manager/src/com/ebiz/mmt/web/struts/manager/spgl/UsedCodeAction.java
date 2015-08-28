package com.ebiz.mmt.web.struts.manager.spgl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGroupBuyMain;
import com.ebiz.mmt.domain.EcVouchCode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.ExcelUtil;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class UsedCodeAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String real_name_like = (String) dynaBean.get("real_name_like");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String shop_id = (String) dynaBean.get("shop_id");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String used_time_start = (String) dynaBean.get("used_time_start");
		String used_time_end = (String) dynaBean.get("used_time_end");
		String is_userd = (String) dynaBean.get("is_userd");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_10 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue() == 140317
			        || peRoleUser.getRole_id().intValue() == 1001) {
				role_id_eq_10 = true;
			}
		}
		EcVouchCode entity = new EcVouchCode();
		request.setAttribute("role_id_eq_10", role_id_eq_10);
		if (!role_id_eq_10) {
			entity.setShop_id(1001L);// 西北印象
		}

		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(used_time_start)) {
			entity.getMap().put("used_time_start", used_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(used_time_end)) {
			entity.getMap().put("used_time_end", used_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);

		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}

		if (StringUtils.isBlank(is_userd)) {
			dynaBean.set("is_userd", 3);
		} else {
			if (is_userd.equals("0")) {
				entity.setIs_userd(Integer.valueOf(is_userd));
			} else if (is_userd.equals("1")) {
				entity.setIs_userd(Integer.valueOf(is_userd));
			}
		}
		if (StringUtils.isBlank(shop_id)) {
			dynaBean.set("shop_id", 3);
		} else {
			if (!shop_id.equals("3")) {
				entity.setShop_id(Long.valueOf(shop_id));
			}
		}

		Long recordCount = super.getFacade().getEcVouchCodeService().getEcVouchCodeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcVouchCode> entityList = super.getFacade().getEcVouchCodeService().getEcVouchCodePaginatedList(entity);
		for (EcVouchCode ecVouchCode : entityList) {
			if (ecVouchCode.getVouch_id() != null) {
				EcGroupBuyMain em = new EcGroupBuyMain();
				em.setId(ecVouchCode.getVouch_id());
				em = super.getFacade().getEcGroupBuyMainService().getEcGroupBuyMain(em);
				if (em != null && em.getShop_id() != null) {
					ecVouchCode.getMap().put("pd_name", em.getGoods_name());
				}
			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String real_name_like = (String) dynaBean.get("real_name_like");
		String trade_index_like = (String) dynaBean.get("trade_index_like");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String shop_id = (String) dynaBean.get("shop_id");
		String add_time_end = (String) dynaBean.get("add_time_end");
		String used_time_start = (String) dynaBean.get("used_time_start");
		String used_time_end = (String) dynaBean.get("used_time_end");
		String is_userd = (String) dynaBean.get("is_userd");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_10 = false;

		for (PeRoleUser peRoleUser : peRoleUserList) {
			// 总部管理员
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue() == 140317
			        || peRoleUser.getRole_id().intValue() == 1001) {
				role_id_eq_10 = true;
			}
		}
		EcVouchCode entity = new EcVouchCode();
		request.setAttribute("role_id_eq_10", role_id_eq_10);
		if (!role_id_eq_10) {
			entity.setShop_id(1001L);// 西北印象
		}

		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(used_time_start)) {
			entity.getMap().put("used_time_start", used_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(used_time_end)) {
			entity.getMap().put("used_time_end", used_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(real_name_like)) {
			entity.getMap().put("real_name_like", real_name_like);

		}
		if (StringUtils.isNotBlank(trade_index_like)) {
			entity.getMap().put("trade_index_like", trade_index_like);
		}

		if (StringUtils.isBlank(is_userd)) {
			dynaBean.set("is_userd", 3);
		} else {
			if (is_userd.equals("0")) {
				entity.setIs_userd(Integer.valueOf(is_userd));
			} else if (is_userd.equals("1")) {
				entity.setIs_userd(Integer.valueOf(is_userd));
			}
		}
		if (StringUtils.isBlank(shop_id)) {
			dynaBean.set("shop_id", 3);
		} else {
			if (!shop_id.equals("3")) {
				entity.setShop_id(Long.valueOf(shop_id));
			}
		}

		Long recordCount = super.getFacade().getEcVouchCodeService().getEcVouchCodeCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(0);
		entity.getRow().setCount(recordCount.intValue());

		List<EcVouchCode> entityList = super.getFacade().getEcVouchCodeService().getEcVouchCodePaginatedList(entity);

		ExcelUtil e = new ExcelUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		int r = 0;
		e.setWorkbook(workbook);
		e.setSheet(sheet);
		e.createRow(r);
		e.setCell(0, "序号");
		e.setCell(1, "交易流水号");
		e.setCell(2, "所属商家");
		e.setCell(3, "金额");
		e.setCell(4, "数量");
		e.setCell(5, "购买人姓名");
		e.setCell(6, "是否使用");
		e.setCell(7, "下单时间");
		e.setCell(8, "使用时间");
		e.setCell(9, "商品名称");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (EcVouchCode pds : entityList) {
			r++;
			e.createRow(r);
			e.setCell(0, r);
			e.setCell(1, pds.getTrade_index());
			String shop_name = "";
			if (pds.getShop_id().intValue() == 1001) {
				shop_name = "西北印象";
			} else {
				shop_name = "康佳集团";
			}
			e.setCell(2, shop_name);
			e.setCell(3, pds.getPrice().toString());
			e.setCell(4, 1);
			e.setCell(5, pds.getReal_name());
			String is_used = "";
			if (pds.getIs_userd().intValue() == 1) {
				is_used = "已使用";
			} else {
				is_used = "未使用";
			}

			e.setCell(6, is_used);
			String add_date = "";
			if (pds.getAdd_date() != null) {
				add_date = sf.format(pds.getAdd_date());
			}
			e.setCell(7, add_date);
			String used_date = "";
			if (pds.getUsed_date() != null) {
				used_date = sf.format(pds.getUsed_date());
			}
			e.setCell(8, used_date);

			if (pds.getVouch_id() != null) {
				EcGroupBuyMain em = new EcGroupBuyMain();
				em.setId(pds.getVouch_id());
				em = super.getFacade().getEcGroupBuyMainService().getEcGroupBuyMain(em);
				if (em != null) {
					e.setCell(9, em.getGoods_name());
				}
			}

		}

		// 输出
		response.setHeader("Content-disposition", "attachment; filename=noname.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型
		OutputStream out = response.getOutputStream();
		e.exportXLS(out);
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		EcVouchCode entity = new EcVouchCode();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity = super.getFacade().getEcVouchCodeService().getEcVouchCode(entity);
		if (entity == null) {
			return null;
		}

		if (entity.getVouch_id() != null) {
			EcGroupBuyMain em = new EcGroupBuyMain();
			em.setId(entity.getVouch_id());
			em = super.getFacade().getEcGroupBuyMainService().getEcGroupBuyMain(em);
			if (em != null) {
				entity.getMap().put("title", em.getTitle());
				entity.getMap().put("pd_name", em.getGoods_name());
			} else {
				entity.getMap().put("title", "橙子");
			}
		}

		copyProperties(form, entity);

		return mapping.findForward("view");
	}

}
