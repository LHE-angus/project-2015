package com.ebiz.mmt.web.struts.manager.chengduoa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,Shunhua
 * @version Build 2010-12-16
 */
public class ExpenseQueryAction extends BaseMmtoaAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}


	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		// 当前用户所属部门下部门人员的查询
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setIs_del(0);
		peProdUser.getMap().put("dept_id",ui.getDept_id());

		List<PeProdUser> peProdUserList = super.getFacade().getPeProdUserService()
				.getKonkaUserListWithDeptNameForResultList(peProdUser);
		request.setAttribute("sumbitUserList", peProdUserList);

		// 查看当前用户所属部门的子部门
		KonkaDept dept = new KonkaDept();
		dept.getMap().put("dept_id", ui.getDept_id());
		List<KonkaDept> konkadeptList = getFacade().getKonkaDeptService().getKonkaDeptListWithTreeNameForProdUser(dept);
		request.setAttribute("konkadeptList", konkadeptList);

		// 获取当前用户所属部门的管辖的网点列表
		KonkaR3Shop shop = getKonkaR3ShopForSelect(mapping, form, request, response, ui.getDept_id());// 获取当前用户相关联的直营网点分页列表
		List<KonkaR3Shop> r3ShopList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(shop);
		//request.setAttribute("r3ShopList", setBranchNameForR3ShopListByKonkaR3ShopList(r3ShopList));
		request.setAttribute("r3ShopList", r3ShopList);

		// 费用申请表的查询
		String map_file_status = (String) dynaBean.get("map_file_status");
		Pager pager = (Pager) dynaBean.get("pager");
		String submit_user_id = (String) dynaBean.get("submit_user_id");
		String submit_dept_id = (String) dynaBean.get("submit_dept_id");
		String r3_shop_id = (String) dynaBean.get("r3_shop_id");

		KonkaoaFiles entity = new KonkaoaFiles();
		super.copyProperties(entity, form);

		if (ui.getDept_id() != 17761) {// 信息中心可以查看所有的文件
			entity.getMap().put("audit_user_id", ui.getId());
			entity.getMap().put("is_xxzx", "true");
		}
		entity.setFile_type(1);// 表示查询的是费用申请

		if (StringUtils.isNotBlank(submit_user_id)) {
			entity.setSubmit_user_id(Long.parseLong(submit_user_id));
		}

		if (StringUtils.isNotBlank(submit_dept_id)) {
			entity.setSubmit_dept_id(Long.parseLong(submit_dept_id));
		}

		entity.getMap().put("map_file_status", map_file_status);
		entity.getMap().put("file_title_like", (String) dynaBean.get("file_title_like"));
		entity.getMap().put("st_submit_datetime", (String) dynaBean.get("st_submit_datetime"));
		entity.getMap().put("en_submit_datetime", (String) dynaBean.get("en_submit_datetime"));

		if (StringUtils.isNotBlank(r3_shop_id)) {
			entity.getMap().put("r3_shop_id", r3_shop_id);
		}

		
		// 使sql变得异常复杂.所以删除了.
        // 这个类好像没有在前台使用
        // 默认是查当前部门所有的费用申请表
        // entity.getMap().put("dept_id", ui.getDept_id());
		entity.setIs_del(0);

		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesListForAuditIngCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaoaFiles> entityList = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaFilesPaginatedListForAuditIng(entity);
		for(KonkaoaFiles t : entityList){
			KonkaExpenseClaims claims = new KonkaExpenseClaims();
			claims.setFile_id(t.getId());
			claims= getFacade().getKonkaExpenseClaimsService().getKonkaExpenseClaims(claims);
			if(null != claims){
				t.getMap().put("column_6", claims.getColumn_6());	
			}
		}
		
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
}