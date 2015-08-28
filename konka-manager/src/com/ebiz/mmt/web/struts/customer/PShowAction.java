package com.ebiz.mmt.web.struts.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.domain.PshowOrder;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Jiang,JiaYong
 * @version 2013-08-07
 */
public class PShowAction extends BaseClientJxcAction {

	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static Integer PD_TYPE = 1; // 1-工卡，2-触网，3-会员

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		if (null == userInfo.getCust_id()) {
			super.renderJavaScript(response, "window.onload=function(){alert('"
			        + this.getMessage(request, "popedom.check.invalid") + "');}");
			return null;
		}

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(userInfo.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		session.setAttribute("shop", shop);

		if (null != shop && shop.getBranch_area_name_2() != null) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(shop.getBranch_area_name_2());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (null != dept) {
				session.setAttribute("fgsName", dept.getDept_name());

				// 查询父部门
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(dept.getDept_id());
				List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(konkaDept);
				StringBuffer sb = new StringBuffer("");
				for (KonkaDept konkaDept2 : konkaDeptList) {
					sb.append("'").append(konkaDept2.getDept_id()).append("',");
				}

				KonkaBcompPd bcompPd = new KonkaBcompPd();
				bcompPd.setOwn_sys(PD_TYPE); // 1-工卡，2-触网，3-会员
				// bcompPd.setDept_id(dept.getDept_id());//分公司商品库
				bcompPd.getMap().put("dept_id_in", StringUtils.removeEnd(sb.toString(), ","));
				bcompPd.getMap().put("today", df.format(new Date()));
				bcompPd.setState(1);
				bcompPd.getRow().setCount(6);

				// 新品
				bcompPd.getRow().setCount(8);
				bcompPd.setLabel_of_cate(0);
				List<KonkaBcompPd> cate0List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				for (KonkaBcompPd konkaBcompPd : cate0List) {
					konkaBcompPd.setMain_pic(StringUtils.split(konkaBcompPd.getMain_pic(), ",")[0]);
				}
				request.setAttribute("cate0List", cate0List);

				// 热卖
				bcompPd.getRow().setCount(6);
				bcompPd.setLabel_of_cate(1);
				List<KonkaBcompPd> cate1List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				for (KonkaBcompPd konkaBcompPd : cate1List) {
					konkaBcompPd.setMain_pic(StringUtils.split(konkaBcompPd.getMain_pic(), ",")[0]);
				}
				request.setAttribute("cate1List", cate1List);

				// 直降
				bcompPd.setLabel_of_cate(2);
				List<KonkaBcompPd> cate2List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				for (KonkaBcompPd konkaBcompPd : cate2List) {
					konkaBcompPd.setMain_pic(StringUtils.split(konkaBcompPd.getMain_pic(), ",")[0]);
				}
				request.setAttribute("cate2List", cate2List);

				// 特惠
				bcompPd.getRow().setCount(6);
				bcompPd.setLabel_of_cate(3);
				List<KonkaBcompPd> cate3List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				for (KonkaBcompPd konkaBcompPd : cate3List) {
					konkaBcompPd.setMain_pic(StringUtils.split(konkaBcompPd.getMain_pic(), ",")[0]);
				}
				request.setAttribute("cate3List", cate3List);

				// 推荐
				bcompPd.getRow().setCount(6);
				bcompPd.setLabel_of_cate(4);
				List<KonkaBcompPd> cate4List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				for (KonkaBcompPd konkaBcompPd : cate4List) {
					konkaBcompPd.setMain_pic(StringUtils.split(konkaBcompPd.getMain_pic(), ",")[0]);
				}
				request.setAttribute("cate4List", cate4List);

				// 取轮播图
				ArticleImg img = new ArticleImg();
				img.setNews_module(1101L); // 在线商城首页轮播图
				img.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
				img.setInfo_state(new Short("1"));
				img.getRow().setCount(5);
				List<ArticleImg> imgList = super.getFacade().getArticleImgService().getArticleImgList(img);
				request.setAttribute("imgList", imgList);
			}
		}

		return mapping.findForward("index");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String label_3d = (String) dynaBean.get("label_3d");
		String label_int = (String) dynaBean.get("label_int");
		String label_www = (String) dynaBean.get("label_www");
		String size_val = (String) dynaBean.get("size_val");
		String price_val = (String) dynaBean.get("price_val");
		String resolution_val = (String) dynaBean.get("resolution_val");
		String cate_val = (String) dynaBean.get("cate_val");
		String isNext = (String) dynaBean.get("isNext");

		logger.info("_____________________________label_3d = " + label_3d);
		logger.info("_____________________________label_int = " + label_int);
		logger.info("_____________________________label_www = " + label_www);
		logger.info("_____________________________size_val = " + size_val);
		logger.info("_____________________________price_val = " + price_val);
		logger.info("_____________________________resolution_val = " + resolution_val);

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		if (null == userInfo.getCust_id()) {
			super.renderJavaScript(response, "window.onload=function(){alert('"
			        + this.getMessage(request, "popedom.check.invalid") + "');}");
			return null;
		}

		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setId(userInfo.getCust_id());
		shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
		session.setAttribute("shop", shop);
		if (null != shop && shop.getBranch_area_name_2() != null) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(shop.getBranch_area_name_2());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (null != dept) {
				session.setAttribute("fgsName", dept.getDept_name());

				// 查询父部门
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(dept.getDept_id());
				List<KonkaDept> konkaDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptByDeptId(konkaDept);
				StringBuffer sb = new StringBuffer("");
				for (KonkaDept konkaDept2 : konkaDeptList) {
					sb.append("'").append(konkaDept2.getDept_id()).append("',");
				}

				KonkaBcompPd bcompPd = new KonkaBcompPd();
				bcompPd.setOwn_sys(PD_TYPE); // 1-工卡，2-触网，3-会员
				if (StringUtils.isNotBlank(label_3d)) {
					bcompPd.setLabel_3d(Integer.valueOf(label_3d));
				} else if (StringUtils.isNotBlank(label_int)) {
					bcompPd.setLabel_int(Integer.valueOf(label_int));
				} else if (StringUtils.isNotBlank(label_www)) {
					bcompPd.setLabel_www(Integer.valueOf(label_www));
				}
				// bcompPd.setDept_id(dept.getDept_id());//分公司商品库
				bcompPd.getMap().put("dept_id_in", StringUtils.removeEnd(sb.toString(), ","));
				bcompPd.getMap().put("today", df.format(new Date()));
				bcompPd.setState(1);
				// 尺寸
				if (StringUtils.isNotBlank(size_val)) {
					if (size_val.indexOf("|") == -1) { // 不包含字符"|"
						bcompPd.setPd_size(Integer.valueOf(size_val));
					} else { // 包含"|": ①32英寸以下 ②55英寸以上
						if ("l".equals(StringUtils.split(size_val, "|")[1])) {
							bcompPd.getMap().put("lt_size", StringUtils.split(size_val, "|")[0]);
						} else if ("g".equals(StringUtils.split(size_val, "|")[1])) {
							bcompPd.getMap().put("gt_size", StringUtils.split(size_val, "|")[0]);
						}
					}
				}
				// 价格
				if (StringUtils.isNotBlank(price_val)) {
					if (price_val.indexOf("|") == -1) {
						String le_price = StringUtils.split(price_val, "-")[0];
						String ge_price = StringUtils.split(price_val, "-")[1];
						bcompPd.getMap().put("le_price", le_price);
						bcompPd.getMap().put("ge_price", ge_price);
					} else {
						if ("g".equals(StringUtils.split(price_val, "|")[1])) {
							bcompPd.getMap().put("gt_price", StringUtils.split(price_val, "|")[0]);
						}
					}
				}
				// 分辨率
				if (StringUtils.isNotBlank(resolution_val)) {
					if (!"9999".equals(resolution_val)) { // 不是"其他"
						bcompPd.setPd_res(resolution_val);
					} else {
						String[] pdResArr = new String[] { "3840,2160", "1920,1080", "1366,768", "1024,768" };
						bcompPd.getMap().put("resNotIn", pdResArr);
					}
				}
				// 分类标签
				if (StringUtils.isNotBlank(cate_val)) {
					bcompPd.setLabel_of_cate(Integer.valueOf(cate_val));
				}

				Long recordCount = super.getFacade().getKonkaBcompPdService()
				        .getKonkaBcompPdWithDeptAndMdCount(bcompPd);
				pager.init(recordCount, 12, pager.getRequestPage());
				bcompPd.getRow().setFirst(pager.getFirstRow());
				bcompPd.getRow().setCount(pager.getRowCount());
				List<KonkaBcompPd> cateList = super.getFacade().getKonkaBcompPdService()
				        .getKonkaBcompPdWithDeptAndMdPaginatedList(bcompPd);
				request.setAttribute("cateList", cateList);

				// 特惠
				KonkaBcompPd bPd = new KonkaBcompPd();
				if (StringUtils.isNotBlank(label_3d)) {
					bPd.setLabel_3d(Integer.valueOf(label_3d));
				} else if (StringUtils.isNotBlank(label_int)) {
					bPd.setLabel_int(Integer.valueOf(label_int));
				} else if (StringUtils.isNotBlank(label_www)) {
					bPd.setLabel_www(Integer.valueOf(label_www));
				}
				bPd.setDept_sn(String.valueOf(dept.getDept_id()));// 分公司商品库
				bPd.getMap().put("today", df.format(new Date()));
				bPd.setState(1);
				bPd.getRow().setCount(3);
				bPd.setLabel_of_cate(3);
				List<KonkaBcompPd> cate3List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bPd);
				request.setAttribute("cate3List", cate3List);

				if ("1".equals(isNext)) {
					dynaBean.set("isNext", isNext);
				}
			}
		}

		return mapping.findForward("list");
	}

	public ActionForward OrderNow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			Long countRecord = getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
			if (countRecord.intValue() <= 0) {
				super.renderJavaScript(response, "alert('参数丢失！');history.back();");
				return null;
			} else {
				entity = getFacade().getKonkaBcompPdService().getKonkaBcompPd(entity);
				if (null != entity) {
					String main_pic = entity.getMain_pic();
					if (StringUtils.isNotBlank(main_pic)) {
						String main_pic_file = StringUtils.split(main_pic, ",")[0];
						dynaBean.set("main_pic_file", main_pic_file); // 主图

						List<String> list = new ArrayList<String>(); // 附图list
						String[] picArr = StringUtils.split(main_pic, ",");
						if (null != picArr && picArr.length > 0) {
							for (int i = 0; i < picArr.length; i++) {
								if (!StringUtils.equals(main_pic_file, picArr[i])) {
									list.add(picArr[i]);
								}
							}
						}
						request.setAttribute("picList", list);

						KonkaDept dept = new KonkaDept();
						dept.setDept_id(Long.valueOf(entity.getDept_sn()));
						dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
						dynaBean.set("dept_name", dept.getDept_name());

						PePdModel pdModel = new PePdModel();
						pdModel.setIs_del(0);
						pdModel.setAudit_state(1);
						pdModel.setPd_id(Long.valueOf(entity.getPd_spec()));
						pdModel = getFacade().getPePdModelService().getPePdModel(pdModel);
						dynaBean.set("md_name", pdModel.getMd_name());
					}
				}
				super.copyProperties(form, entity);

				// 处理内容，规格，售后
				KonkaBcompPdContent konkaBcompPdContent = new KonkaBcompPdContent();
				konkaBcompPdContent.setKbp_id(entity.getId());
				List<KonkaBcompPdContent> list = super.getFacade().getKonkaBcompPdContentService()
				        .getKonkaBcompPdContentList(konkaBcompPdContent);
				if (null != list && 0 != list.size())
					for (KonkaBcompPdContent t : list) {
						// 类型：1-产品描述，2-产品规格，3-产品售后
						request.setAttribute("type_" + t.getType(), t.getContent());
					}

				return mapping.findForward("input");
			}
		} else {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
	}

	public ActionForward addOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		super.saveToken(request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String num = (String) dynaBean.get("num");

		if (!GenericValidator.isLong(id) || !GenericValidator.isLong(num)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		// 查询产品信息
		KonkaBcompPd entity = new KonkaBcompPd();
		entity.setId(Long.valueOf(id));
		entity = getFacade().getKonkaBcompPdService().getKonkaBcompPd(entity);
		if (null == entity) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
		request.setAttribute("entity", entity);

		// 型号名称
		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		pdModel.setAudit_state(1);
		pdModel.setPd_id(Long.valueOf(entity.getPd_spec()));
		pdModel = getFacade().getPePdModelService().getPePdModel(pdModel);
		dynaBean.set("md_name", pdModel.getMd_name());

		// 查询客户地址
		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null != userInfo.getP_index() && 0L != userInfo.getP_index()) {
			request.setAttribute("link_p_index", userInfo.getP_index());
			request.setAttribute("link_p_index_name", super.getPIndexName(userInfo.getP_index(), "&nbsp;"));
		}

		return new ActionForward("/../customer/PShow/addorder.jsp");
	}

	public ActionForward addOrderTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		if (!super.isTokenValid(request, true)) {
			super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.token")
			        + "');history.back();");
			return null;
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String num = (String) dynaBean.get("num");
		String real_name = (String) dynaBean.get("real_name");
		String link_p_index = (String) dynaBean.get("link_p_index");
		String link_addr = (String) dynaBean.get("link_addr");
		String link_phone = (String) dynaBean.get("link_phone");
		String link_tel = (String) dynaBean.get("link_tel");
		String remark = (String) dynaBean.get("remark"); // 留言
		String bill_is_add = (String) dynaBean.get("bill_is_add"); // 是否要发票
		String link_zip = (String) dynaBean.get("link_zip");

		if (!GenericValidator.isLong(id) || !GenericValidator.isLong(num)) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		logger.info("------real_name--->{}", real_name);
		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

		// 查询产品信息
		KonkaBcompPd konkaBcompPd = new KonkaBcompPd();
		konkaBcompPd.setId(Long.valueOf(id));
		konkaBcompPd = getFacade().getKonkaBcompPdService().getKonkaBcompPd(konkaBcompPd);
		if (null == konkaBcompPd) {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}

		// 交易流水号
		Date now = new Date();
		String trade_index = DateFormatUtils.format(now, "yyMMddHHmmss")
		        + StringUtils.substring(String.valueOf(now.getTime()), 7);

		// 订单总金额
		// BigDecimal total_price = konkaBcompPd.getBuy_price().multiply(new
		// BigDecimal(num)).setScale(2,
		// BigDecimal.ROUND_HALF_UP);

		// 初始化数据
		PshowOrder pshowOrder = new PshowOrder();
		pshowOrder.setOrder_from(10); // 10-工卡，20-触网，30-会员
		pshowOrder.setUuid(UUID.randomUUID().toString());
		pshowOrder.setTrade_index(trade_index);
		pshowOrder.setDept_id(Long.valueOf(konkaBcompPd.getDept_sn()));
		pshowOrder.setState(0);
		// pshowOrder.setTotal_price(total_price);
		pshowOrder.setOrder_user_id(userInfo.getId());
		pshowOrder.setOrder_user_name(userInfo.getReal_name());
		pshowOrder.setBuyer_name(real_name);
		pshowOrder.setBuyer_p_index(Long.valueOf(link_p_index));
		pshowOrder.setBuyer_addr(link_addr);
		pshowOrder.setBuyer_mp(link_phone);
		pshowOrder.setBuyer_tel(link_tel);
		pshowOrder.setRemark(remark);
		pshowOrder.setBuyer_zip(link_zip);
		pshowOrder.setAdd_date(now);
		if (GenericValidator.isLong(bill_is_add))
			pshowOrder.setBill_is_add(1);
		else
			pshowOrder.setBill_is_add(0);

		// 明细
		PshowOrdeDetails pshowOrdeDetails = new PshowOrdeDetails();
		pshowOrdeDetails.setNum(Long.valueOf(num));
		pshowOrdeDetails.setPd_id(Long.valueOf(id));
		pshowOrdeDetails.setPd_name(konkaBcompPd.getPd_name() + konkaBcompPd.getPd_sn());
		pshowOrdeDetails.setRemark(remark);
		pshowOrdeDetails.setState(0);
		// pshowOrdeDetails.setPrice(konkaBcompPd.getBuy_price());
		// pshowOrdeDetails.setTotal_price(konkaBcompPd.getBuy_price().multiply(new
		// BigDecimal(num)).setScale(2,
		// BigDecimal.ROUND_HALF_UP));
		List<PshowOrdeDetails> pshowOrdeDetailsList = new ArrayList<PshowOrdeDetails>();
		pshowOrdeDetailsList.add(pshowOrdeDetails);
		pshowOrder.setPshowOrdeDetailsList(pshowOrdeDetailsList);
		super.getFacade().getPshowOrderService().createPshowOrderWithDetails(pshowOrder);

		request.setAttribute("trade_index", trade_index);
		// request.setAttribute("total_price", total_price);
		return new ActionForward("/../customer/PShow/addordertwo.jsp");
	}

	public static void main(String[] args) {
		Date now = new Date();
		//System.out.println(now.getTime());
		//System.out.println(StringUtils.substring(String.valueOf(now.getTime()), 7));
	}
}
