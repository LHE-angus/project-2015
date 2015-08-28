package com.ebiz.mmt.web.struts.manager.leader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.JcfxKczzKh;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.mmt.domain.JcfxKczzKhfzLink;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * 财务部库存周转率
 *
 */
public class JcfxKczzKhfzAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return list(mapping, form, request, response);
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		
		String _title_like = (String) dynaBean.get("_title_like");//  分组名
		String _customer_name_like = (String) dynaBean.get("_customer_name_like");//  分组名
		String _add_user_name_like = (String) dynaBean.get("_add_user_name_like");//  分组名
		String _is_del = (String) dynaBean.get("_is_del");//  分组名
		String _begin_add_date = (String) dynaBean.get("_begin_add_date");//  分组名
		String _end_add_date = (String) dynaBean.get("_end_add_date");//  分组名
		//  
		
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		
		JcfxKczzKhfz entity=new JcfxKczzKhfz();

		if (StringUtils.isNotBlank(_title_like)) {
			entity.getMap().put("_title_like", _title_like);
		}
		if (StringUtils.isNotBlank(_customer_name_like)) {
			entity.getMap().put("_customer_name_like", _customer_name_like);
		}
		if (StringUtils.isNotBlank(_add_user_name_like)) {
			entity.getMap().put("_add_user_name_like", _add_user_name_like);
		}
		if (StringUtils.isNotBlank(_is_del)) {
			entity.getMap().put("_is_del", _is_del);
		}
		if (StringUtils.isNotBlank(_begin_add_date)) {
			entity.getMap().put("_begin_add_date",_begin_add_date + " 00:00:00");
		}
		if (StringUtils.isNotBlank(_end_add_date)) {
			entity.getMap().put("_end_add_date", _end_add_date + " 23:59:59");
		}
		
		List<JcfxKczzKhfz> entityList=new ArrayList<JcfxKczzKhfz>();
		Long recordCount = super.getFacade().getJcfxKczzKhfzService().getJcfxKczzKhfzLBCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		
		entityList=super.getFacade().getJcfxKczzKhfzService().getJcfxKczzKhfzLBPaginatedList(entity);
		
		request.setAttribute("entityList", entityList);
		String is_bi = (String) dynaBean.get("is_bi");
		if(StringUtils.isNotBlank(is_bi)){
			request.setAttribute("is_bi",is_bi);
		}
		 return new ActionForward("/../manager/leader/JcfxKczzKhfz/list.jsp");
	}
	
	/**
	 * 新增分组或修改都调这个方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String _khfz_id = (String) dynaBean.get("_khfz_id");
		String _title = (String) dynaBean.get("_title");
		request.setAttribute("_khfz_id", _khfz_id);
		request.setAttribute("_title", _title);
		
		return new ActionForward("/../manager/leader/JcfxKczzKhfz/from.jsp");
	}
	
	/**
	 * 待选客户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward noChooseMapList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String _khfz_id = (String) dynaBean.get("_khfz_id");
		String _customer_name_like = (String) dynaBean.get("_customer_name_like");
		String _r3_code_like = (String) dynaBean.get("_r3_code_like");
       //待分配用户
		JcfxKczzKh entity=new JcfxKczzKh();
		if(StringUtils.isNotBlank(_khfz_id)){
			entity.getMap().put("_khfz_id", _khfz_id);
			JcfxKczzKhfz fz=new JcfxKczzKhfz();
			if (StringUtils.isNotBlank(_khfz_id)) {
				fz.setId(Long.valueOf(_khfz_id));
				fz = super.getFacade().getJcfxKczzKhfzService()
						.getJcfxKczzKhfz(fz);
				if (null!=fz&&null!=fz.getTitle()) {
					allmap.put("title", fz.getTitle());
				}
			}
		}
		if (StringUtils.isNotBlank(_r3_code_like)) {
			entity.getMap().put("_r3_code_like", _r3_code_like);
		}
		if (StringUtils.isNotBlank(_customer_name_like)) {
			entity.getMap().put("_customer_name_like", _customer_name_like);
		}
        List<JcfxKczzKh> entityList = super.getFacade().getJcfxKczzKhService().getJcfxKczzKhList(entity);
        allmap.put("noChooseMapList", entityList);
        JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 已选客户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chooseMapList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		Map<String, Object> allmap = new HashMap<String, Object>();
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String _khfz_id = (String) dynaBean.get("_khfz_id");
		if (StringUtils.isNotBlank(_khfz_id)&&StringUtils.isNumeric(_khfz_id)) {
			//已分配用户
			JcfxKczzKhfzLink entity=new JcfxKczzKhfzLink();
			if (StringUtils.isNotBlank(_khfz_id)&&StringUtils.isNumeric(_khfz_id)) {
				entity.setKhfz_id(Long.valueOf(_khfz_id));
			}
	        List<JcfxKczzKhfzLink> entityList = super.getFacade().getJcfxKczzKhfzLinkService().getJcfxKczzKhfzLinkList(entity);
	        allmap.put("chooseMapList", entityList);	
		}
        JSONArray jsonArray = JSONArray.fromObject(allmap);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 分组客户保存级联保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		resetToken(request);
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == userInfo) {
			super.renderHtml(response, "登录超时");
			return null;
		}

		String _khfz_id = (String) dynaBean.get("_khfz_id");// 分组id
		String title = (String) dynaBean.get("title");//  分组名
		String[] cust_custid_r3code_name =request.getParameterValues("select2");// 选择的客户
		List<JcfxKczzKhfzLink> linkList=new ArrayList<JcfxKczzKhfzLink>();
		JcfxKczzKhfzLink linkEntity=new JcfxKczzKhfzLink();
		if (null!=cust_custid_r3code_name&&cust_custid_r3code_name.length>0) {
			for (int i = 0; i < cust_custid_r3code_name.length; i++) {
				String custCustidR3codeNametemp[]=cust_custid_r3code_name[i].split("#");
				linkEntity=new JcfxKczzKhfzLink();
				if(custCustidR3codeNametemp.length>0){
					if (null!=custCustidR3codeNametemp[0]&&""!=custCustidR3codeNametemp[0]) {
						linkEntity.setKh_id(Long.valueOf(custCustidR3codeNametemp[0]));
					}
					if (null!=custCustidR3codeNametemp[1]&&""!=custCustidR3codeNametemp[1]) {
						linkEntity.setCust_id(Long.valueOf(custCustidR3codeNametemp[1]));
					}
					if (null!=custCustidR3codeNametemp[2]&&""!=custCustidR3codeNametemp[2]) {
						linkEntity.setR3_code(custCustidR3codeNametemp[2]);
					}
					if (null!=custCustidR3codeNametemp[3]&&""!=custCustidR3codeNametemp[3]) {
						linkEntity.setCoustmer_name(custCustidR3codeNametemp[3]);
					}
					linkEntity.setAdd_date(new Date());
					if (null!=userInfo.getUser_name()) {
						linkEntity.setAdd_user(userInfo.getUser_name());
					}
					if (null!=userInfo.getId()) {
						linkEntity.setAdd_user_id(userInfo.getId());
					}
					linkEntity.setIs_del(0);
					
				}
				linkList.add(linkEntity);
			}
	    }
		JcfxKczzKhfz fzEntity=new JcfxKczzKhfz();
		fzEntity.setTitle(title);
		
		fzEntity.setLinkList(linkList);//分组     客户管理表
		//修改
		if (StringUtils.isNotBlank(_khfz_id)&&StringUtils.isNotBlank(_khfz_id)) {
			fzEntity.setId(Long.valueOf(_khfz_id));
			int old_id=super.getFacade().getJcfxKczzKhfzService().modifyJcfxKczzKhfzLB(fzEntity);
		}else {//新增
			if (null!=userInfo.getId()) {
				fzEntity.setAdd_user_id(userInfo.getId());
			}
			if (null!=userInfo.getUser_name()) {
				fzEntity.setAdd_user_name(userInfo.getUser_name());
			}
			fzEntity.setAdd_date(new Date());
			fzEntity.setIs_del(0);
			
			Long new_Id=super.getFacade().getJcfxKczzKhfzService().createJcfxKczzKhfzLB(fzEntity);
		}

		// end
		return mapping.findForward("list");
	}

}
