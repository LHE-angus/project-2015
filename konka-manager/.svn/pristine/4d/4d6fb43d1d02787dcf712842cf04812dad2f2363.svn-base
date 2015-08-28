package com.ebiz.mmt.web.struts.customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;

public class EShopAction extends BaseClientJxcAction {

	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.index(mapping, form, request, response);
	}

	public ActionForward decorate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return new ActionForward("/../customer/EShop/decorate.jsp");
	}

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		// DynaBean dynaBean = (DynaBean) form;

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
		// request.setAttribute("shop", shop);
		session.setAttribute("shop", shop);

		if (null != shop && shop.getBranch_area_name_2() != null) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(shop.getBranch_area_name_2());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (null != dept) {
				// request.setAttribute("fgsName", dept.getDept_name());
				session.setAttribute("fgsName", dept.getDept_name());

				KonkaBcompPd bcompPd = new KonkaBcompPd();
				bcompPd.setDept_sn(String.valueOf(dept.getDept_id()));// 分公司商品库
				bcompPd.getMap().put("today", df.format(new Date()));
				bcompPd.setState(1);
				bcompPd.getRow().setCount(6);

				// 新品
				bcompPd.setLabel_of_cate(0);
				List<KonkaBcompPd> cate0List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				request.setAttribute("cate0List", cate0List);
				// 热卖
				bcompPd.setLabel_of_cate(1);
				List<KonkaBcompPd> cate1List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				request.setAttribute("cate1List", cate1List);
				// 直降
				bcompPd.setLabel_of_cate(2);
				List<KonkaBcompPd> cate2List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				request.setAttribute("cate2List", cate2List);
				// 特惠
				bcompPd.setLabel_of_cate(3);
				List<KonkaBcompPd> cate3List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				request.setAttribute("cate3List", cate3List);
				// 推荐
				bcompPd.setLabel_of_cate(4);
				List<KonkaBcompPd> cate4List = getFacade().getKonkaBcompPdService().getKonkaBcompPdWidhDeptAndMdList(
				        bcompPd);
				request.setAttribute("cate4List", cate4List);
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
		// request.setAttribute("shop", shop);
		session.setAttribute("shop", shop);
		if (null != shop && shop.getBranch_area_name_2() != null) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(shop.getBranch_area_name_2());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (null != dept) {
				session.setAttribute("fgsName", dept.getDept_name());

				KonkaBcompPd bcompPd = new KonkaBcompPd();
				if (StringUtils.isNotBlank(label_3d)) {
					bcompPd.setLabel_3d(Integer.valueOf(label_3d));
				} else if (StringUtils.isNotBlank(label_int)) {
					bcompPd.setLabel_int(Integer.valueOf(label_int));
				} else if (StringUtils.isNotBlank(label_www)) {
					bcompPd.setLabel_www(Integer.valueOf(label_www));
				}
				bcompPd.setDept_sn(String.valueOf(dept.getDept_id()));// 分公司商品库
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
				// List<KonkaBcompPd> entityList =
				// super.getFacade().getKonkaBcompPdService().getKonkaBcompPdPaginatedList(entity);
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
		super.saveToken(request);

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
				return mapping.findForward("input");
			}
		} else {
			super.renderJavaScript(response, "alert('参数丢失！');history.back();");
			return null;
		}
	}

}
