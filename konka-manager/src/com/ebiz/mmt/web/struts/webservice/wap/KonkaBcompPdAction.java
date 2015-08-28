package com.ebiz.mmt.web.struts.webservice.wap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBcompType;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.web.struts.wap.BaseMemberAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-12
 */
public class KonkaBcompPdAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");
		String p_index=null;
		if(ecUser==null){
			 ecUser = new EcUser();
			 ecUser.setUser_name("游客");
			 ecUser.setUser_type(new Integer(2));
			 ecUser.setId(new Long(1));
			 ecUser.setDept_id(0L);
			 ecUser.setPlat_sys(new Integer(0));
			 ecUser.setCust_id(0L);
			 ecUser.setPlat_sys(0);
		}
		if(p_index==null&&ecUser!=null&&ecUser.getUser_type().intValue()==2&&ecUser.getP_index()!=null){
			p_index=ecUser.getP_index().toString(); 
		}
		if(p_index ==null || "".equals(p_index)){
			try{
				p_index = super.getPIndexByRequest(request);   
			}catch(Exception e){
				p_index = "440300";//默认等于深圳
			}
		}
		
		Integer own_sys =ecUser.getUser_type();
		if(ecUser.getUser_type().intValue()==1&&ecUser.getDept_id()!=null&&ecUser.getDept_id().intValue()>0){
			own_sys= new Integer(5);
		}

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_type = (String) dynaBean.get("pd_type");
		String pd_size_type = (String) dynaBean.get("pd_size_type");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String pd_price = (String) dynaBean.get("pd_price");
		String pd_sn_or_pd_name_like = (String) dynaBean.get("pd_sn_or_pd_name_like");
		String is_tj = (String) dynaBean.get("is_tj");
		String order_by_sale_num_desc = (String) dynaBean.get("order_by_sale_num_desc");
		String order_by_price_desc = (String) dynaBean.get("order_by_price_desc");
		String order_by_price_asc = (String) dynaBean.get("order_by_price_asc");
		String brand_name = (String) dynaBean.get("brand_name");
		String prod_type = (String) dynaBean.get("prod_type");
		String prod_name = (String) dynaBean.get("prod_name");
		logger.info("----p_index--->{}", p_index);

		// 获取请求地区的分公司 dept_id以及获取总部的产品
		Long[] p_index_array = getPindexArrayByPindex(Long.valueOf(p_index));

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_type(3);
		konkaDept.getRow().setCount(2);
		konkaDept.getMap().put("m_areas_ids_like", p_index_array[1]);
		List<KonkaDept> konkaDeptList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		String[] dept_sn_array = new String[] { "0" };
		if (1 == konkaDeptList.size()) {
			dept_sn_array = new String[] { "0", konkaDeptList.get(0).getDept_id().toString() };
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		entity.getMap().put("today", true); 
		if (own_sys.intValue() == 1) {// 根据用户类型（工卡、触网） 取不同系统商品
			entity.getMap().put("own_sys_in_01", "1");
		} else if (own_sys.intValue() == 2) {// 触网用户 根据客户ID(c_id)
			entity.getMap().put("own_sys_new_in_02", "1");
			// ,分公司ID（dept_id)屏蔽商品
			entity.getMap().put("own_sys_in_02_new", "1");
			entity.getMap().put("dept_id", ecUser.getDept_id());
			entity.getMap().put("c_id", ecUser.getCust_id()); 
				// ,分公司ID（dept_id)屏蔽商品
				if (ecUser.getPlat_sys() != null) {
					if (ecUser.getPlat_sys().intValue() == 1) {
						entity.getMap().put("plat_sys_eq_1", "1");
						entity.getMap().put("own_sys_new_in_02", "1");
						entity.getMap().put("own_sys_in_02_new", "1");
						entity.getMap().put("dept_id", ecUser.getDept_id());
						EcGroup eg = new EcGroup();
						eg.setId(ecUser.getDept_id());
						eg = super.getFacade().getEcGroupService().getEcGroup(eg);
						if (eg != null && eg.getLink_dept_id() != null) {
							entity.getMap().put("dept_id_new", eg.getLink_dept_id());
						}
						entity.getMap().put("c_id", ecUser.getCust_id());
					} else if (ecUser.getPlat_sys().intValue() == 0) {
						entity.getMap().put("plat_sys_eq_0", "1");
						entity.getMap().put("own_sys_new_in_02_for_zb", "1");
						entity.getMap().put("own_sys_in_02_new", "1");
						entity.getMap().put("dept_id", ecUser.getDept_id());
						EcGroup eg = new EcGroup();
						eg.setId(ecUser.getDept_id());
						eg = super.getFacade().getEcGroupService().getEcGroup(eg);
						if (eg != null && eg.getLink_dept_id() != null) {
							entity.getMap().put("dept_id_new", eg.getLink_dept_id());
						}
						entity.getMap().put("c_id", ecUser.getCust_id());
					} 

			}
		} else if (own_sys.intValue() == 5) {
			entity.getMap().put("dept_id", ecUser.getDept_id());
			entity.getMap().put("own_sys_in_05", "1");
			entity.setOwn_sys(5);
		}
		entity.setState(1); // 状态：0-已停用 1-正常 -1-已删除
		// 非电视产品不过滤prod_type
		if (prod_type != null && !"".equals(prod_type) && !"0".equals(prod_type) && !"7".equals(prod_type)) {
		//	entity.getMap().put("label_of_cate_eq", new Integer[] { 0, 1, 2, 3, 7 }); // label_of_cate
			// 0-新品，1-主销，2-热销，3-特惠，4-工程机，5-限时抢购，6-团购,7-精品
		}
		entity.getMap().put("is_upself", "true"); // 只查询上架积分商品
		if (StringUtils.isNotBlank(p_index)) {
			entity.getMap().put("pindex_id_eq", p_index_array);
		} else {
			entity.getMap().put("pindex_id_eq", new Long[] { -1L });
		}

		if (StringUtils.isNotBlank(pd_sn_or_pd_name_like)) {
			entity.getMap().put("pd_sn_or_pd_name_like", pd_sn_or_pd_name_like);
			request.setAttribute("search_pd_name", pd_sn_or_pd_name_like);
		}

		if (StringUtils.isNotBlank(pd_type) && "1".equals(pd_type)) {
			entity.setLabel_3d(1);
			request.setAttribute("pd_type_name", "3D\u7535\u89c6");// 3D电视
		} else if (StringUtils.isNotBlank(pd_type) && "2".equals(pd_type)) {
			entity.setLabel_int(1);
			request.setAttribute("pd_type_name", "\u667a\u80fd\u7535\u89c6");// 智能电视
		} else if (StringUtils.isNotBlank(pd_type) && "3".equals(pd_type)) {
			entity.setLabel_www(1);
			request.setAttribute("pd_type_name", "\u7f51\u7edc\u7535\u89c6");// 网络电视
		} else if (StringUtils.isNotBlank(pd_type) && "4".equals(pd_type)) {
			entity.setLabel_4k(1);// 4K
			request.setAttribute("pd_type_name", "4K\u7535\u89c6");// 4K电视
		} else if (StringUtils.isNotBlank(pd_type) && "9".equals(pd_type)) {
			entity.getMap().put("no_lable", "1");
			request.setAttribute("pd_type_name", "\u5176\u4ED6");// 其他
		}
		if (StringUtils.isNotBlank(pd_size_type) && "31".equals(pd_size_type)) {
			entity.getMap().put("pd_size_lt", "32");
			request.setAttribute("pd_size_name", "32\u82f1\u5bf8\u4ee5\u4e0b");// 32英寸以下
		} else if (StringUtils.isNotBlank(pd_size_type) && "59".equals(pd_size_type)) {
			entity.getMap().put("pd_size_gt", "57");
			request.setAttribute("pd_size_name", "58\u82F1\u5BF8\u53CA\u4EE5\u4E0A");// 58英寸及以上
		} else if (StringUtils.isNotBlank(pd_size_type) && "39".equals(pd_size_type)) {// 39/40
			entity.getMap().put("pd_size_lt", "41");
			entity.getMap().put("pd_size_gt", "38");
			request.setAttribute("pd_size_name", "39/40\u82f1\u5bf8");// 39/40英寸
		} else if (StringUtils.isNotBlank(pd_size_type) && "49".equals(pd_size_type)) {// 39/40英寸
			entity.getMap().put("pd_size_lt", "51");
			entity.getMap().put("pd_size_gt", "48");
			request.setAttribute("pd_size_name", "49/50\u82f1\u5bf8");// 39/40英寸
		} else if (StringUtils.isNotBlank(pd_size_type)) {
			entity.setPd_size(new Integer(pd_size_type));
			request.setAttribute("pd_size_name", pd_size_type + "\u82f1\u5bf8");// 英寸
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(new Integer(label_of_cate));
			EcBcompType e = new EcBcompType();
			e.setId(new Long(label_of_cate));
			e = super.getFacade().getEcBcompTypeService().getEcBcompType(e);
			if (null != e) {
				request.setAttribute("label_of_cate_name", e.getName());
			}
		}
		if (StringUtils.isNotBlank(pd_price) && "1".equals(pd_price)) {
			entity.getMap().put("price_ge", "0");
			entity.getMap().put("price_lt", "1000");
			request.setAttribute("pd_price_name", "0 - 1000");
		} else if (StringUtils.isNotBlank(pd_price) && "2".equals(pd_price)) {
			entity.getMap().put("price_ge", "1000");
			entity.getMap().put("price_lt", "2000");
			request.setAttribute("pd_price_name", "1000 - 2000");
		} else if (StringUtils.isNotBlank(pd_price) && "3".equals(pd_price)) {
			entity.getMap().put("price_ge", "2000");
			entity.getMap().put("price_lt", "3000");
			request.setAttribute("pd_price_name", "2000 - 3000");
		} else if (StringUtils.isNotBlank(pd_price) && "4".equals(pd_price)) {
			entity.getMap().put("price_ge", "3000");
			entity.getMap().put("price_lt", "5000");
			request.setAttribute("pd_price_name", "3000 - 5000");
		} else if (StringUtils.isNotBlank(pd_price) && "5".equals(pd_price)) {
			entity.getMap().put("price_ge", "5000");
			entity.getMap().put("price_lt", "10000");
			request.setAttribute("pd_price_name", "5000 - 10000");
		} else if (StringUtils.isNotBlank(pd_price) && "6".equals(pd_price)) {
			entity.getMap().put("price_ge", "10000");
			entity.getMap().put("price_lt", "200000");
			request.setAttribute("pd_price_name", "10000以上");
		}
		if (StringUtils.isNotBlank(is_tj)) {
			entity.setIs_tj(new Integer(is_tj));
		}
		entity.getMap().put("is_upSelf", true);
		// 排序
		if (StringUtils.isNotBlank(order_by_sale_num_desc)) {
			entity.getMap().put("order_by_sale_num_desc", order_by_sale_num_desc);
		}
		if (StringUtils.isNotBlank(order_by_price_desc)) {
			entity.getMap().put("order_by_price_desc", order_by_price_desc);
		}
		if (StringUtils.isNotBlank(order_by_price_asc)) {
			entity.getMap().put("order_by_price_asc", order_by_price_asc);
		}
		if (StringUtils.isNotBlank(brand_name)) {
			if ("KONKA".equals(brand_name)) {
				brand_name = "康佳";
			}
			entity.getMap().put("pd_name_like", brand_name);
		}
		if (StringUtils.isNotBlank(prod_type)) {
			if ("1".equals(prod_type)) {
				entity.getMap().put("prod_type_in", new Integer[] { 4, 5, 6 });
			} else {
				entity.setProd_type(new Integer(prod_type));
			}
		}
		if (StringUtils.isNotBlank(prod_name)) {
			if ("31".equals(prod_name)) {
				entity.getMap().put("pd_name_like", "电饭煲");
			}
			if ("32".equals(prod_name)) {
				entity.getMap().put("pd_name_like", "电磁炉");
			}
			if ("33".equals(prod_name)) {
				entity.getMap().put("pd_name_like", "热水壶");
			}
		}

		entity.getMap().put("labe_of_cat_not_eq_5", true);

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForEcPriceAndSaleNewCount(entity);
		pager.setPageSize(9);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
		        .getKonkaBcompPdForEcPriceAndSaleNewPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		List<KonkaBcompPd> bcomp_pd_list_top_5 = super.getKonkaBcompTopNewList(own_sys, p_index_array, 1, 5, ecUser
		        .getDept_id(), ecUser.getCust_id(), null,ecUser.getPlat_sys());
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);

		return mapping.findForward("list");
	}
}