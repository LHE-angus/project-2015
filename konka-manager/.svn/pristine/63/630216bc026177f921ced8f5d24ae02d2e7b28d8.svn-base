package com.ebiz.mmt.web.struts.wap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcBaseExpressReachDay;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept; 

/**
 * @author TUDP
 * @version 2013-12-29
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
		if (ecUser==null) {
			String msg = "对不起，您还未登录，请先登录系统";
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');}");
			return null;
		}
		// 判断产品ID是否丢失
		if (!GenericValidator.isLong(goods_id)) {
			String msg = super.getMessage(request, "param.isEmpty", new String[] { "Product Code." });
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 取当前请求数据的用户所在地
		String p_index = super.getPIndexByRequest(request);
		request.setAttribute("p_index", p_index); 

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
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{	
			konkaBcompPd =getKonkaBcompPdAllDataWithPindexAndGoodsId(super.getCtxPath(request),ecUser.getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array,ecUser.getDept_id(),ecUser.getCust_id());
		}else{
			konkaBcompPd =getKonkaBcompPdAllDataWithPindexAndGoodsIdNew(super.getCtxPath(request),ecUser.getUser_type(), Long.valueOf(goods_id), Long.valueOf(p_index), dept_sn_array,ecUser.getDept_id(),ecUser.getCust_id(),ecUser.getPlat_sys());
		}
		
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
		
		//商品评论
		EcPdEavl entity = new EcPdEavl();
		entity.setOwn_sys(ecUser.getUser_type());
		entity.setIs_del(0);
		entity.setGoods_id(new Long(goods_id));
		if(ecUser != null) {
			entity.getMap().put("user_id", ecUser.getId());// 取已审核的和当前用户的评论
		}else{
			entity.setAudit_state(new Integer("1"));// 取已审核的评论
		}
		Long scoreSum = super.getFacade().getEcPdEavlService().getEcPdEavlSumEvalScore(entity);//总分数
		Long scoreCount = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);//评论数
		entity.setEval_score(new Integer(4));
		Long scoreCount4 = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
		entity.setEval_score(new Integer(5));
		Long scoreCount5 = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);
		if (scoreCount.intValue() > 0){
			request.setAttribute("score45", "" + ((scoreCount5.intValue() + scoreCount4.intValue()) * 100.0f / scoreCount.intValue()));
		}
		request.setAttribute("scoreCount", scoreCount);//总评论数
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
		List<EcGoodsPrice> ecpList = null;
		if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{		
			ecpList = getEcGoodsPriceListWithPindexAndGoodsId(ecUser.getUser_type(), Long.valueOf(goods_id), p_index_array, ecUser.getDept_id(),ecUser.getCust_id());
		}else{
			ecpList = getEcGoodsPriceListWithPindexAndGoodsId(ecUser.getUser_type(), Long.valueOf(goods_id), p_index_array, ecUser.getDept_id(),ecUser.getCust_id());
		}
		// 查询库存
		List<EcStocks> esList = null;
		if(ecUser.getUser_type().intValue()==1&ecUser.getIs_epp_fgs()!=null&&ecUser.getIs_epp_fgs().intValue()==0)	{
			esList =getEcStocksListWithPindexAndGoodsId(ecUser.getUser_type(), Long.valueOf(goods_id), p_index_array, dept_sn_array);
		}else{
			esList = getEcStocksListWithPindexAndGoodsIdNew(ecUser.getUser_type(), Long.valueOf(goods_id), p_index_array, ecUser.getDept_id(),ecUser.getPlat_sys());
		}
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
				r3store_num=super.getStockCount(pd.getPd_sn()).intValue();
			}
		}catch(Exception e){};
		}
		if (null != esList && 0 != esList.size()) {
			r3store_num=esList.get(0).getStocks().intValue()+r3store_num; 
			map.put("stocks", r3store_num);
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
	
	public ActionForward getUrl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url = request.getParameter("url");
		String body = "";
		HttpClient httpclient = new DefaultHttpClient();
		String msg="";
		try {
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			body = httpclient.execute(httpget, responseHandler);
			logger.info("-------------->>邮件内容\n" + url + body);
		} catch (ClientProtocolException e) {
			msg = "读取内容出错";
			logger.error(e.getMessage());
		} catch (IOException e) {
			msg = "读取内容出错";
			logger.error(e.getMessage());
		}
		super.renderHtml(response, body+msg);
        return null;
	}

}
