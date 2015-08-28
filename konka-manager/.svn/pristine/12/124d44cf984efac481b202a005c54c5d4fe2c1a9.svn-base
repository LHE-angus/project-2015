package com.ebiz.mmt.web.struts.touch;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;

/**
 * @author Jiang,JiaYong
 * @version 2013-09-13
 */
public class PdShowAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");

		// 判断产品ID是否丢失
		if (!GenericValidator.isLong(goods_id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取当前请求数据的用户所在地
		String p_index = super.getPIndexByRequest(request);
		request.setAttribute("p_index", p_index);
		logger.info("----p_index--->{}", p_index);

		// 得到当前请求的地址
		request.setAttribute("p_full_name", super.getPIndexName(p_index, ""));
		
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
		logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));
		
		// 查询省份信息
		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.setP_level(1);
		bplf.setDel_mark(0);
		List<BaseProvinceListFour> bplfList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(bplf);
		for (BaseProvinceListFour t : bplfList) {
			if(StringUtils.substring(t.getP_index().toString(), 0, 2).equals(StringUtils.substring(p_index, 0, 2))){
				request.setAttribute("province_name", t.getP_name());
				request.setAttribute("province_index", t.getP_index());
			}
		}
		request.setAttribute("baseProvinceListFourList", bplfList);
		 
		// 查询商品详细信息
		KonkaBcompPd konkaBcompPd = getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request),ecUser.getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array,ecUser.getDept_id(),ecUser.getCust_id());
		if (null == konkaBcompPd) {
			String msg = super.getMessage(request, "ec.bcomp.pd.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		// 更新浏览量
		KonkaBcompPd t = new KonkaBcompPd();
		t.setId(konkaBcompPd.getId());
		t.setView_counts(1L);
		getFacade().getKonkaBcompPdService().modifyKonkaBcompPdForViewCountOrSaleNum(t);
		
		// 将数据存入 request 作用域
		request.setAttribute("konkaBcompPd", konkaBcompPd);

		// 销量排行前5
		List<KonkaBcompPd> bcomp_pd_list_top_5 = this.getKonkaBcompTopList(ecUser.getUser_type(), p_index_array, 1, 5, ecUser.getDept_id(),ecUser.getCust_id());
		request.setAttribute("bcomp_pd_list_top_5", bcomp_pd_list_top_5);
		
		// 取4个配件产品
		if(konkaBcompPd.getProd_type()!=null&&konkaBcompPd.getProd_type().intValue()==0){
		Integer[] prod_type=new Integer[]{10};
		List<KonkaBcompPd> bcomp_pd_list_pj = this.getKonkaBcompPdWithPindexAndTypeAndCountList(ecUser.getUser_type(), Long.valueOf(p_index), null,4,
								ecUser.getDept_id(),ecUser.getCust_id(),prod_type);
		request.setAttribute("bcomp_pd_list_pj", bcomp_pd_list_pj);	
		}
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @author Jiang,JiaYong
	 * @version 2013-09-16
	 * @desc 根据商品ID和地区信息得到商品详细信息包含库存，价格等
	 */
	public ActionForward ajaxGetKonkaBcompPdByGoodsIdAndPindex(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String p_index = (String) dynaBean.get("p_index");
		request.getSession().setAttribute("p_index", p_index);

		// 关键参数丢失
		if (!GenericValidator.isLong(goods_id) || !GenericValidator.isLong(p_index)) {
			super.renderJson(response, "");
			return null;
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
		logger.info("-----dept_sn_array-->{}", StringUtils.join(dept_sn_array, ","));
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("ecUser");		
		// 查询价格
		List<EcGoodsPrice> ecpList = getEcGoodsPriceListWithPindexAndGoodsId(ecUser.getUser_type(), Long.valueOf(goods_id), p_index_array, ecUser.getDept_id(),ecUser.getCust_id());

		// 查询库存
		List<EcStocks> esList = getEcStocksListWithPindexAndGoodsId(ecUser.getUser_type(), Long.valueOf(goods_id), p_index_array, dept_sn_array);
		
		// 构建数据结构
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", goods_id);
		map.put("p_index", p_index);		
		map.put("price", "");
		map.put("original_price", "");
		if (null != ecpList && 0 != ecpList.size()) {
			map.put("price", ecpList.get(0).getPrice());
			map.put("original_price", ecpList.get(0).getOriginal_price());
		} 
		map.put("stocks", "");
		KonkaBcompPd pd = new KonkaBcompPd();//获取r3库存
		pd.setId(new Long(goods_id));
		pd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd);
		int r3store_num=0;
		if(pd.getProd_type()!=null&&pd.getProd_type().intValue()==0){
		try{
			if(pd!=null){
				if (null != esList && 0 != esList.size()&&esList.get(0)!=null) {
					EcStocks s=(EcStocks)esList.get(0);
					Integer stock_type = new Integer(s.getMap().get("store_type").toString());
					if(stock_type.intValue()!=2){
						r3store_num=super.getStockCount(super.getCtxPath(request),pd.getPd_sn()).intValue();
						r3store_num=esList.get(0).getStocks().intValue()+r3store_num; 
						map.put("stocks", r3store_num);
					}else if(stock_type.intValue()==2&&s.getStocks().intValue()<1&&esList.size()>1){
						r3store_num=esList.get(1).getStocks().intValue()+r3store_num; 
						map.put("stocks", r3store_num);
					}else{
						r3store_num=esList.get(1).getStocks().intValue(); 
						map.put("stocks", r3store_num);
					}
				}
			}
		}catch(Exception e){};
		} 
		
		// 查询快递到达天数
		if (null != esList && 0 != esList.size()) {
			EcBaseExpressReachDay eberd = new EcBaseExpressReachDay();
			eberd.setIs_del(0);
			eberd.getRow().setCount(10);
			eberd.setStore_id(esList.get(0).getStore_id());
			eberd.getMap().put("order_by_area_code_desc", true);
			eberd.getMap().put("area_code_in", new Long[] { p_index_array[3], p_index_array[2], p_index_array[1] }); // 查询地区、县、市倒序排列
			List<EcBaseExpressReachDay> eberdList = getFacade().getEcBaseExpressReachDayService().getEcBaseExpressReachDayList(eberd);
			if(0 != eberdList.size())
				map.put("max_reach_day", eberdList.get(0).getMax_reach_day());
		}
		
		logger.info("----ajaxGetKonkaBcompPdByGoodsIdAndPindex---->{}", JSON.toJSONString(map));
		super.renderJson(response, JSON.toJSONString(map));
		return null;
	}
}
