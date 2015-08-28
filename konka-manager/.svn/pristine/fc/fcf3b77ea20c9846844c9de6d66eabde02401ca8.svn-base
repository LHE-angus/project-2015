package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.member.BaseMemberAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author TUDP
 * @version 2013-12-17
 */
public class EcPdListForMobileAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request); 
		HttpSession session = request.getSession(); 
		DynaBean dynaBean = (DynaBean) form; 
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_type = (String) dynaBean.get("pd_type");
		String pd_size_type = (String) dynaBean.get("pd_size_type");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String pd_price = (String) dynaBean.get("pd_price");
		String pd_sn_or_pd_name_like = (String) dynaBean.get("pd_sn_or_pd_name_like");
		String pageSize = (String) dynaBean.get("pageSize");
		String requestPage = (String) dynaBean.get("requestPage");
		String is_tj = (String) dynaBean.get("is_tj");
		String p_index =  (String) dynaBean.get("p_index");
		String order_by_sale_num_desc =(String)dynaBean.get("order_by_sale_num_desc");
		String order_by_price_desc =(String)dynaBean.get("order_by_price_desc");
		String order_by_price_asc =(String)dynaBean.get("order_by_price_asc");
		String order_by_view_counts_desc =(String)dynaBean.get("order_by_view_counts_desc");
		
		String prod_type = (String) dynaBean.get("prod_type");
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		EcUser ecUser = (EcUser) session.getAttribute(Constants.EPP_USER);
		if(ecUser==null){
			ecUser = super.checkUser(username, userpass);
			session.setAttribute(Constants.EPP_USER,ecUser);
			
			if(new DESPlus().encrypt(userpass).equals(ecUser.getPass_word())){
				session.setAttribute("touch", null);
			}else{
				if(ecUser.getUser_type().intValue()==1){
				session.setAttribute("touch", 1);
				}
			}
		}else{
			 ecUser = new EcUser();
			 ecUser.setUser_name("游客");
			 ecUser.setUser_type(new Integer(2));
			 ecUser.setId(new Long(1));
		}
		if(p_index==null&&ecUser!=null&&ecUser.getUser_type().intValue()==2){
			p_index=ecUser.getP_index().toString(); 
		}
		if(p_index ==null || "".equals(p_index)){
			try{
				p_index = super.getPIndexByRequest(request);   
			}catch(Exception e){
				p_index = "440300";//默认等于深圳
			}
		}
		
		KonkaBcompPd entity = new KonkaBcompPd();
		entity.getMap().put("today", true); 
		if(ecUser.getUser_type().intValue()==1){//根据用户类型（工卡、触网）  取不同系统商品
			entity.getMap().put("own_sys_in_01", "1");	
		}else if(ecUser.getUser_type().intValue()==2){//触网用户 根据客户ID(c_id) ,分公司ID（dept_id)屏蔽商品
			entity.getMap().put("own_sys_in_02", "1");	
			entity.getMap().put("dept_id", ecUser.getDept_id());
			entity.getMap().put("c_id", ecUser.getCust_id());
		}
		entity.setState(1); // 状态：0-已停用 1-正常 -1-已删除
		if(label_of_cate==null||"".equals(label_of_cate)){
		entity.getMap().put("label_of_cate_eq", new Integer[] { 0, 1, 2, 3 ,7,8,9}); // label_of_cate 0-新品，1-主销，2-热销，3-特惠，4-工程机，5-限时抢购，6-团购,7-精品,8-每周一劲爆,9,样机
		}
		entity.getMap().put("is_upSelf", true); // 只查询上架商品
		//根据地区取产品
		if (StringUtils.isNotBlank(p_index)) {
			entity.getMap().put("pindex_id_eq", new Long[] { new Long(p_index) });
		} else {
			entity.getMap().put("pindex_id_eq", new Long[] { -1L });
		}
		//按名称搜索产品
		if (StringUtils.isNotBlank(pd_sn_or_pd_name_like)) {
			entity.getMap().put("pd_sn_or_pd_name_like", pd_sn_or_pd_name_like);
			request.setAttribute("search_pd_name", pd_sn_or_pd_name_like);
		}
		//智能 4k 3d电视
		if (StringUtils.isNotBlank(pd_type) && "1".equals(pd_type)) {
			entity.setLabel_3d(1); 
		} else if (StringUtils.isNotBlank(pd_type) && "2".equals(pd_type)) {
			entity.setLabel_int(1); 
		} else if (StringUtils.isNotBlank(pd_type) && "3".equals(pd_type)) {
			entity.setLabel_www(1); 
		} else if (StringUtils.isNotBlank(pd_type) && "4".equals(pd_type)) {
			entity.setLabel_4k(1);//4K 
		} else if (StringUtils.isNotBlank(pd_type) && "9".equals(pd_type)) {
			entity.getMap().put("no_lable", "1");
			request.setAttribute("pd_type_name", "\u5176\u4ED6");//其他
		}
		//产品型号大小
		if (StringUtils.isNotBlank(pd_size_type) && "31".equals(pd_size_type)) {
			entity.getMap().put("pd_size_lt", "32"); 
		} else if (StringUtils.isNotBlank(pd_size_type) && "56".equals(pd_size_type)) {
			entity.getMap().put("pd_size_gt", "55"); 
		} else if (StringUtils.isNotBlank(pd_size_type)) {
			entity.setPd_size(new Integer(pd_size_type)); 
		}
		//分类 热销 优惠 推荐 
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(new Integer(label_of_cate)); 
		}
		//产品价格区间
		if (StringUtils.isNotBlank(pd_price) && "1".equals(pd_price)) {
			entity.getMap().put("price_ge", "0");
			entity.getMap().put("price_lt", "1000"); 
		} else if (StringUtils.isNotBlank(pd_price) && "2".equals(pd_price)) {
			entity.getMap().put("price_ge", "1000");
			entity.getMap().put("price_lt", "2000"); 
		} else if (StringUtils.isNotBlank(pd_price) && "3".equals(pd_price)) {
			entity.getMap().put("price_ge", "2000");
			entity.getMap().put("price_lt", "3000"); 
		}  else if (StringUtils.isNotBlank(pd_price) && "4".equals(pd_price)) {
			entity.getMap().put("price_ge", "3000");
			entity.getMap().put("price_lt", "5000"); 
		}else if (StringUtils.isNotBlank(pd_price) && "5".equals(pd_price)) {
			entity.getMap().put("price_ge", "5000");
			entity.getMap().put("price_lt", "10000"); 
		}else if (StringUtils.isNotBlank(pd_price) && "6".equals(pd_price)) {
			entity.getMap().put("price_ge", "10000");
			entity.getMap().put("price_lt", "200000"); 
		}
		if (GenericValidator.isLong(is_tj)) {
			entity.setIs_tj(new Integer(is_tj));
		}
		//产品类别
		if (StringUtils.isNotBlank(prod_type)) {
			if("1".equals(prod_type)){ 
			entity.getMap().put("prod_type_in", new Integer[]{4,5,6}); 
			}else{
			entity.setProd_type(new Integer(prod_type));
			}
		} 
		//排序
		if (StringUtils.isNotBlank(order_by_sale_num_desc)) {
			entity.getMap().put("order_by_sale_num_desc", order_by_sale_num_desc); 
		}
		if (StringUtils.isNotBlank(order_by_price_desc)) {
			order_by_price_asc=null;
			entity.getMap().put("order_by_price_desc", order_by_price_desc); 
		}
		if (StringUtils.isNotBlank(order_by_price_asc)) {
			entity.getMap().put("order_by_price_asc", order_by_price_asc); 
		}
		if (StringUtils.isNotBlank(order_by_view_counts_desc)) {
			entity.getMap().put("order_by_view_counts_desc", order_by_view_counts_desc); 
		}
		//分页  默认取九条信息
		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForEcPriceAndSaleCount(entity);
		if (GenericValidator.isLong(pageSize)) {
			pager.setPageSize(new Integer(pageSize));
		}else{
			pager.setPageSize(9);
			pageSize="9";
		}
		if(GenericValidator.isLong(requestPage)){
			pager.setRequestPage(requestPage);	
			if(new Integer(requestPage).intValue()<1){
				requestPage="1";
			}
		}else{
			pager.setRequestPage("1");	
			requestPage="1";
		} 
		
		//pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		
		Integer firstRow =new Integer(pageSize).intValue()*(new Integer(requestPage).intValue()-1);
		Integer	endRow = new Integer(pageSize).intValue();
		
		entity.getRow().setFirst(firstRow);
		entity.getRow().setCount(endRow);
		//查询
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForEcPriceAndSalePaginatedList(entity);
		List<HashMap> list =new ArrayList<HashMap>();
		HashMap map= new HashMap();
 	    for(KonkaBcompPd pd:entityList){
 	    	map= new HashMap();
	    	String goods_id = pd.getId().toString();
	    	String main_pic = pd.getMain_pic();
	    	String pd_sn = pd.getPd_sn();
	    	String pd_name = pd.getPd_name();
	    	String pd_size =  pd.getPd_size()==null?"":pd.getPd_size().toString();
	    	String price = pd.getMap().get("price")==null?"":pd.getMap().get("price").toString();
	    	String original_price = pd.getMap().get("original_price")==null?"":pd.getMap().get("original_price").toString();
	    	String sale_num =  pd.getSale_num()==null?"":pd.getSale_num().toString();
	    	map.put("goods_id",goods_id);
	    	map.put("main_pic",main_pic);
	    	map.put("pd_sn",pd_sn);
	    	map.put("pd_name",pd_name);
	    	map.put("pd_size",pd_size);
	    	map.put("price",price);
	    	map.put("original_price",original_price);
	    	map.put("sale_num",sale_num);
	    	list.add(map);	    	
	    }
 	   super.renderJson(response, JSON.toJSONString(list));
 	   return null;
	}
	
	public ActionForward listForYk(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request); 
		HttpSession session = request.getSession(); 
		DynaBean dynaBean = (DynaBean) form; 
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_type = (String) dynaBean.get("pd_type");
		String pd_size_type = (String) dynaBean.get("pd_size_type");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String pd_price = (String) dynaBean.get("pd_price");
		String pd_sn_or_pd_name_like = (String) dynaBean.get("pd_sn_or_pd_name_like");
		String pageSize = (String) dynaBean.get("pageSize");
		String requestPage = (String) dynaBean.get("requestPage");
		String is_tj = (String) dynaBean.get("is_tj");
		String p_index =  (String) dynaBean.get("p_index");
		String order_by_sale_num_desc =(String)dynaBean.get("order_by_sale_num_desc");
		String order_by_price_desc =(String)dynaBean.get("order_by_price_desc");
		String order_by_price_asc =(String)dynaBean.get("order_by_price_asc");
		String order_by_view_counts_desc =(String)dynaBean.get("order_by_view_counts_desc");
		String username = (String) dynaBean.get("username");
		String prod_type = (String) dynaBean.get("prod_type"); 
		
		//设置默认用户 无用户名取系统第一个触网用户
		EcUser ecUser = new EcUser(); 
		if (StringUtils.isNotBlank(username)) {
			ecUser.setUser_name("user_name");
		}else{
			ecUser.setId(new Long(91284));
		}
		ecUser.setUser_type(new Integer(2));
	    List<EcUser> listUser =super.getFacade().getEcUserService().getEcUserList(ecUser);
	    ecUser=listUser.get(0);
	   
		session.setAttribute(Constants.EPP_USER,ecUser); 
				
		if(p_index==null&&ecUser!=null&&ecUser.getUser_type().intValue()==2){
			p_index=ecUser.getP_index().toString(); 
		}
		if(p_index ==null || "".equals(p_index)){
			try{
				p_index = super.getPIndexByRequest(request);   
			}catch(Exception e){
				p_index = "440300";//默认等于深圳
			}
		}
		
		KonkaBcompPd entity = new KonkaBcompPd();
		entity.getMap().put("today", true); 
		if(ecUser.getUser_type().intValue()==1){//根据用户类型（工卡、触网）  取不同系统商品
			entity.getMap().put("own_sys_in_01", "1");	
		}else if(ecUser.getUser_type().intValue()==2){//触网用户 根据客户ID(c_id) ,分公司ID（dept_id)屏蔽商品
			entity.getMap().put("own_sys_in_02", "1");	
			entity.getMap().put("dept_id", ecUser.getDept_id());
			entity.getMap().put("c_id", ecUser.getCust_id());
		}
		entity.setState(1); // 状态：0-已停用 1-正常 -1-已删除
		if(label_of_cate==null||"".equals(label_of_cate)){
		entity.getMap().put("label_of_cate_eq", new Integer[] { 0, 1, 2, 3 ,7,8,9}); // label_of_cate 0-新品，1-主销，2-热销，3-特惠，4-工程机，5-限时抢购，6-团购,7-精品,8-每周一劲爆,9,样机
		}
		entity.getMap().put("is_upSelf", true); // 只查询上架商品
		//根据地区取产品
		if (StringUtils.isNotBlank(p_index)) {
			entity.getMap().put("pindex_id_eq", new Long[] { new Long(p_index) });
		} else {
			entity.getMap().put("pindex_id_eq", new Long[] { -1L });
		}
		//按名称搜索产品
		if (StringUtils.isNotBlank(pd_sn_or_pd_name_like)) {
			entity.getMap().put("pd_sn_or_pd_name_like", pd_sn_or_pd_name_like);
			request.setAttribute("search_pd_name", pd_sn_or_pd_name_like);
		}
		//智能 4k 3d电视
		if (StringUtils.isNotBlank(pd_type) && "1".equals(pd_type)) {
			entity.setLabel_3d(1); 
		} else if (StringUtils.isNotBlank(pd_type) && "2".equals(pd_type)) {
			entity.setLabel_int(1); 
		} else if (StringUtils.isNotBlank(pd_type) && "3".equals(pd_type)) {
			entity.setLabel_www(1); 
		} else if (StringUtils.isNotBlank(pd_type) && "4".equals(pd_type)) {
			entity.setLabel_4k(1);//4K 
		} else if (StringUtils.isNotBlank(pd_type) && "9".equals(pd_type)) {
			entity.getMap().put("no_lable", "1");
			request.setAttribute("pd_type_name", "\u5176\u4ED6");//其他
		}
		//产品型号大小
		if (StringUtils.isNotBlank(pd_size_type) && "31".equals(pd_size_type)) {
			entity.getMap().put("pd_size_lt", "32"); 
		} else if (StringUtils.isNotBlank(pd_size_type) && "56".equals(pd_size_type)) {
			entity.getMap().put("pd_size_gt", "55"); 
		} else if (StringUtils.isNotBlank(pd_size_type)) {
			entity.setPd_size(new Integer(pd_size_type)); 
		}
		//分类 热销 优惠 推荐 
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(new Integer(label_of_cate)); 
		}
		//产品价格区间
		if (StringUtils.isNotBlank(pd_price) && "1".equals(pd_price)) {
			entity.getMap().put("price_ge", "0");
			entity.getMap().put("price_lt", "1000"); 
		} else if (StringUtils.isNotBlank(pd_price) && "2".equals(pd_price)) {
			entity.getMap().put("price_ge", "1000");
			entity.getMap().put("price_lt", "2000"); 
		} else if (StringUtils.isNotBlank(pd_price) && "3".equals(pd_price)) {
			entity.getMap().put("price_ge", "2000");
			entity.getMap().put("price_lt", "3000"); 
		}  else if (StringUtils.isNotBlank(pd_price) && "4".equals(pd_price)) {
			entity.getMap().put("price_ge", "3000");
			entity.getMap().put("price_lt", "5000"); 
		}else if (StringUtils.isNotBlank(pd_price) && "5".equals(pd_price)) {
			entity.getMap().put("price_ge", "5000");
			entity.getMap().put("price_lt", "10000"); 
		}else if (StringUtils.isNotBlank(pd_price) && "6".equals(pd_price)) {
			entity.getMap().put("price_ge", "10000");
			entity.getMap().put("price_lt", "200000"); 
		}
		if (GenericValidator.isLong(is_tj)) {
			entity.setIs_tj(new Integer(is_tj));
		}
		//产品类别
		if (StringUtils.isNotBlank(prod_type)) {
			if("1".equals(prod_type)){ 
			entity.getMap().put("prod_type_in", new Integer[]{4,5,6}); 
			}else{
			entity.setProd_type(new Integer(prod_type));
			}
		} 
		//排序
		if (StringUtils.isNotBlank(order_by_sale_num_desc)) {
			entity.getMap().put("order_by_sale_num_desc", order_by_sale_num_desc); 
		}
		if (StringUtils.isNotBlank(order_by_price_desc)) {
			order_by_price_asc=null;
			entity.getMap().put("order_by_price_desc", order_by_price_desc); 
		}
		if (StringUtils.isNotBlank(order_by_price_asc)) {
			entity.getMap().put("order_by_price_asc", order_by_price_asc); 
		}
		if (StringUtils.isNotBlank(order_by_view_counts_desc)) {
			entity.getMap().put("order_by_view_counts_desc", order_by_view_counts_desc); 
		}
		//分页  默认取九条信息
		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForEcPriceAndSaleCount(entity);
		if (GenericValidator.isLong(pageSize)) {
			pager.setPageSize(new Integer(pageSize));
		}else{
			pager.setPageSize(9);
			pageSize="9";
		}
		if(GenericValidator.isLong(requestPage)){
			pager.setRequestPage(requestPage);	
			if(new Integer(requestPage).intValue()<1){
				requestPage="1";
			}
		}else{
			pager.setRequestPage("1");	
			requestPage="1";
		} 
		
		//pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		
		Integer firstRow =new Integer(pageSize).intValue()*(new Integer(requestPage).intValue()-1);
		Integer	endRow = new Integer(pageSize).intValue();
		
		entity.getRow().setFirst(firstRow);
		entity.getRow().setCount(endRow);
		//查询
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForEcPriceAndSalePaginatedList(entity);
		List<HashMap> list =new ArrayList<HashMap>();
		HashMap map= new HashMap();
 	    for(KonkaBcompPd pd:entityList){
 	    	map= new HashMap();
	    	String goods_id = pd.getId().toString();
	    	String main_pic = pd.getMain_pic();
	    	String pd_sn = pd.getPd_sn();
	    	String pd_name = pd.getPd_name();
	    	String pd_size =  pd.getPd_size()==null?"":pd.getPd_size().toString();
	    	String price = pd.getMap().get("price")==null?"":pd.getMap().get("price").toString();
	    	String original_price = pd.getMap().get("original_price")==null?"":pd.getMap().get("original_price").toString();
	    	String sale_num =  pd.getSale_num()==null?"":pd.getSale_num().toString();
	    	map.put("goods_id",goods_id);
	    	map.put("main_pic",main_pic);
	    	map.put("pd_sn",pd_sn);
	    	map.put("pd_name",pd_name);
	    	map.put("pd_size",pd_size);
	    	map.put("price",price);
	    	map.put("original_price",original_price);
	    	map.put("sale_num",sale_num);
	    	list.add(map);	    	
	    }
 	   super.renderJson(response, JSON.toJSONString(list));
 	   return null;
	}
	
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String p_index =  (String) dynaBean.get("p_index");
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		EcUser ecUser = super.checkUser(username, userpass);
		if(p_index==null&&ecUser!=null&&ecUser.getUser_type().intValue()==2){
			p_index=ecUser.getP_index().toString(); 
		}
		if(p_index ==null || "".equals(p_index)){
			p_index = super.getPIndexByRequest(request);   
		}
		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index)); 
		// 查询分公司
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getRow().setCount(2);
		konkaDept.getMap().put("m_areas_ids_like", p_index_array[1]);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		String[] dept_sn_array = new String[] { "0" };
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}
	 
		KonkaBcompPd konkaBcompPd = getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request),ecUser.getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array,ecUser.getDept_id(),ecUser.getCust_id());

		// 更新浏览量
		KonkaBcompPd t = new KonkaBcompPd();
			t.setId(konkaBcompPd.getId());
			t.setView_counts(1L);
			getFacade().getKonkaBcompPdService().modifyKonkaBcompPdForViewCountOrSaleNum(t);
				
			// 将数据存入 request 作用域
		request.setAttribute("konkaBcompPd", konkaBcompPd);
		return null;
	}
}