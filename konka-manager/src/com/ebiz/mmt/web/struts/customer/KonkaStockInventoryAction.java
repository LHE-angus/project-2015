package com.ebiz.mmt.web.struts.customer;

import ch.qos.logback.core.joran.action.Action;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.ssi.web.struts.bean.Pager;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 库存盘点(可盘康佳和非康佳机型的库存)
 *
 * @author ZHOU
 */
public class KonkaStockInventoryAction extends BaseClientJxcAction {

    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat dfm = new SimpleDateFormat("yyyyMMdd");

    @Override
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
        // 单据编号
        String bill_sn_like = (String) dynaBean.get("bill_sn_like");
        // 时间1
        String opr_date_gt = (String) dynaBean.get("opr_date_gt");
        // 时间2
        String opr_date_lt = (String) dynaBean.get("opr_date_lt");

        HttpSession session = request.getSession();
        // 取人员信息
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            return null;
        } else if (null == user.getCust_id()) {
            return null;
        }

        // 库存盘点实体
        JStocksVerify entity = new JStocksVerify();
        super.copyProperties(entity, form);
        entity.setC_id(user.getCust_id());
        if (StringUtils.isNotBlank(bill_sn_like)) {
            entity.getMap().put("bill_sn_like", bill_sn_like);
        }
        if (StringUtils.isNotBlank(opr_date_gt)) {
            entity.getMap().put("start_date", opr_date_gt + " 00:00:00");
        }
        if (StringUtils.isNotBlank(opr_date_lt)) {
            entity.getMap().put("end_date", opr_date_lt + " 23:59:59");
        }
        // 查条数
        Long recordCount = super.getFacade().getJStocksVerifyService().getJStocksVerifyCount(entity);
        pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
        entity.getRow().setFirst(pager.getFirstRow());
        entity.getRow().setCount(pager.getRowCount());
        // 分页查
        List<JStocksVerify> entityList = getFacade().getJStocksVerifyService()
                .getJStocksVerifyWithStoreAndGoodsNamePaginatedList(entity);
        request.setAttribute("entityList", entityList);

        // 获取登录用户 企业信息
        PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.CUSTOMER_USER_INFO);
        if (null == peProdUser.getCust_id()) {
            return null;
        }
        // 取实时库存 ldy
        KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
        konkaR3Shop.setId(peProdUser.getCust_id());
        konkaR3Shop = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);

        if (null != konkaR3Shop) {
            if (konkaR3Shop.getIs_inventory() == 0) {
                request.setAttribute("is_pd", 0);
            } else if (konkaR3Shop.getIs_inventory() == 1) {
                request.setAttribute("is_pd", 1);
            }
        }

        // 取仓库
        JBaseStore jBaseStore = new JBaseStore();
        jBaseStore.setC_id(user.getCust_id());
        jBaseStore.setIs_del(0);
        List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
        request.setAttribute("jBaseStoreList", jBaseStoreList);

        // 取客户商品库产品
        JBaseGoods jBaseGoods = new JBaseGoods();
        jBaseGoods.setC_id(user.getCust_id());

        List<JBaseGoods> jBaseGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
        request.setAttribute("jBaseGoodsList", jBaseGoodsList);

        return mapping.findForward("list");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) {
        super.saveToken(request);
        setNaviStringToRequestScope(form, request);
        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            return null;
        } else if (user.getCust_id() == null) {
            return null;
        }

        // 取仓库
        JBaseStore jBaseStore = new JBaseStore();
        jBaseStore.setC_id(user.getCust_id());
        jBaseStore.setIs_del(0);
        List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
        if (null != jBaseStoreList && jBaseStoreList.size() > 0) {
            for (JBaseStore jbs : jBaseStoreList) {
                if ("总库".equals(jbs.getStore_name())) {
                    request.setAttribute("main_store_id", jbs.getStore_id());
                    JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
                    initStock.setStore_id(jbs.getStore_id());
                    initStock.setInit_state(0);
                    initStock.getMap().put("order_by", "order by b.GOODS_NAME asc");
                    List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
                            .getJBaseGoodsInitStockList(initStock);
                    request.setAttribute("jBaseGoodsList", initStockList);
                }
            }
        }
        request.setAttribute("jBaseStoreList", jBaseStoreList);

        // JBaseGoods jBaseGoods = new JBaseGoods();
        // jBaseGoods.setC_id(user.getCust_id());
        // jBaseGoods.setGoods_stutes(0);
        // List<JBaseGoods> jBaseGoodsList =
        // getFacade().getJBaseGoodsService().getJBaseGoodsList(jBaseGoods);
        // request.setAttribute("jBaseGoodsList", jBaseGoodsList);

        Date today = new Date();
        request.setAttribute("today", df.format(today));
        request.setAttribute("cust_id", user.getCust_id());

        return mapping.findForward("input");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        resetToken(request);
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String[] store_ids = request.getParameterValues("store_id");
        String[] goods_ids = request.getParameterValues("goods_id");
        String[] stockss = request.getParameterValues("stocks");
        String[] moneys = request.getParameterValues("money");
        String[] ver_stockss = request.getParameterValues("ver_stocks");
        String[] prices = request.getParameterValues("price");
        String[] ver_moneys = request.getParameterValues("ver_money");
        String[] memos = request.getParameterValues("memo");

        String cust_id = (String) dynaBean.get("cust_id");
        String opr_date = (String) dynaBean.get("opr_date");

        String HH = (String) dynaBean.get("HH");
        String mm = (String) dynaBean.get("mm");
        // String[] pyBuyPrices = request.getParameterValues("pyBuyPrice");//
        // 盘盈价格列表
        // String[] stackIds = request.getParameterValues("stack_id");//
        // 盘亏单据ID列表

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
            return null;
        } else if (null == user.getCust_id() || Integer.valueOf(cust_id) != user.getCust_id().intValue()) {
            super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
            return null;
        }

        HH = StringUtils.isBlank(HH) ? "00" : HH;
        mm = StringUtils.isBlank(mm) ? "00" : mm;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (store_ids == null || goods_ids == null || stockss == null || ver_stockss == null) {
            super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
            return null;
        }

        opr_date = opr_date + " " + HH + ":" + mm;
        Date _date = new Date();
        //DateUtils.parseDate(new Date(), new String[] { "yyyy-MM-dd HH:mm" });
        Long result = 0l;

        for (int i = 0; i < store_ids.length; i++) {
            String store_id = store_ids[i];
            String goods_id = goods_ids[i];
            String stocks = stockss[i];// 当前库存
            String money = moneys[i];// 当前库存
            String ver_stocks = ver_stockss[i];// 盘点数
            String ver_money = ver_moneys[i];// 盘点数

            String memo = null;
            if (memos != null) {
                memo = memos[i];
            }
            String price = null;
            if (prices != null) {
                price = prices[i];
            }
            logger.info("store_id=" + store_id + "   goods_id=" + goods_id + "    stocks=" + stocks + "  verstocks="
                    + ver_stocks + "   memo=" + memo);
            if (StringUtils.isNotBlank(opr_date) && StringUtils.isNotBlank(store_id)
                    && StringUtils.isNotBlank(goods_id) && StringUtils.isNotBlank(stocks)
                    && StringUtils.isNotBlank(ver_stocks)) {

                String checkResult = super.checkKhContainsMd(user.getCust_id(), Long.valueOf(goods_id),
                        Long.valueOf(store_id));
                if (StringUtils.isNotBlank(checkResult)) {
                    super.renderJavaScript(response, "alert('" + checkResult + "');history.back();");
                    return null;
                }

                JStocksVerify entity = new JStocksVerify();
                entity.setGoods_id(Long.valueOf(goods_id));
                entity.setStore_id(Long.valueOf(store_id));
                entity.getMap().put("compare_date", format.format(_date));
                entity.setC_id(user.getCust_id());
                Long count = super.getFacade().getJStocksVerifyService().getJStocksVerifyCount(entity);

//				下面的注释是为了去掉盘点的限制，每天随便盘点

//				if (count != null && count > 0) {
//					super.renderJavaScript(response, "alert('同一个仓库的同一个商品一天只能盘存一次！');history.back();");
//					return null;
//				}
                // 判断盘点日期是否在上次盘点日期在现在日期之间 start
                SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//				Date current = new Date();
//				Date d_date = DateUtils.parseDate(dformat.format(current), new String[] { "yyyy-MM-dd HH:mm" });
//				if (_date.compareTo(d_date) > 0) {
//					super.renderJavaScript(response, "alert('盘点日期超出当前时间,请重新选择！');history.back();");
//					return null;
//				}

//				JStocksVerify jTemp = new JStocksVerify();
//				jTemp.setStore_id(Long.valueOf(store_id));
//				jTemp.setGoods_id(Long.valueOf(goods_id));
//				jTemp.setC_id(user.getCust_id());
//				jTemp.getMap().put("max_date_id", true);
//				jTemp.getMap().put("store_id_value", store_id);
//				jTemp.getMap().put("goods_id_value", goods_id);
//				jTemp.getRow().setCount(1);
//				jTemp = super.getFacade().getJStocksVerifyService().getJStocksVerify(jTemp);
//				if (null != jTemp) {
//					Date lastOprDate = jTemp.getOpr_date();
//					logger.info("______________________________上一次盘点日期 = " + dformat.format(lastOprDate));
//					logger.info("______________________________本次盘点日期 = " + dformat.format(_date));
//					logger.info("______________________________当前时间= " + dformat.format(d_date));
//					if (lastOprDate.getTime() > _date.getTime()) {
//						super.renderJavaScript(response, "alert('抱歉，您选择的商品盘点日期小于最近一次盘点日期！');history.back();");
//						return null;
//					}
//				}
                // end

                Long re_value = Long.parseLong(ver_stocks) - Long.parseLong(stocks);
                if (StringUtils.isEmpty(price)) {
                    if (0 != re_value) {// 计算价格
                        Double lprice = (Long.parseLong(ver_money) - Double.parseDouble(money)) / re_value;
                        price = lprice.toString();
                    }
                }
                if (re_value > 0) {// 盘盈,遍历价格
                    if (StringUtils.isNotBlank(price)) {
                        String[] price_array = new String[re_value.intValue()];
                        for (int va = 0; va < re_value.intValue(); va++) {
                            price_array[va] = price;
                        }
                        result = result
                                + super.getFacade()
                                .getKonkaCustomerPublicService()
                                .createStockVerify(_date, store_id, goods_id, stocks, money, ver_stocks,
                                        ver_money, user.getCust_id(), memo, 1, price_array, new String[]{});

                        // 盘盈相当于是进货了，添加进货记录

                            super.getFacade()
                                    .getJxcFifoStocksService()
                                    .stock_in(Long.valueOf(store_id), Long.valueOf(goods_id),
                                            new BigDecimal(price), re_value.intValue(), new Date(), 50);

                        // 盘盈相当于是进货了，添加进货记录
                    } else {
                        super.renderJavaScript(response, "alert('抱歉，盘盈的商品必须填写价格！');history.back();");
                        return null;
                    }
                } else {// 盘亏
                    result = result
                            + super.getFacade()
                            .getKonkaCustomerPublicService()
                            .createStockVerify(_date, store_id, goods_id, stocks, money, ver_stocks, ver_money,
                                    user.getCust_id(), memo, 1, new String[]{},
                                    new String[-re_value.intValue()]);
                    //  盘亏相当于是销货了,相当于销售

                        super.getFacade()
                                .getJxcFifoStocksService()
                                .stock_out(Long.valueOf(store_id), Long.valueOf(goods_id), new BigDecimal(price),
                                        re_value.intValue() * -1, new Date(), 560);

                    // 盘亏相当于是销货了
                }
            }
        }
        if (0 == result.intValue()) { // 成功插入
            saveMessage(request, "konka.jstockverify.inserted.success");
        } else if (0 != result.intValue()) { // 盘点日期选择非法
            saveError(request, "konka.jstockverify.oprdate.error");
        }
        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        // pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
        // "id", "pks", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;
    }

    public ActionForward save_old(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String cust_id = (String) dynaBean.get("cust_id");
        String opr_date = (String) dynaBean.get("opr_date");
        String store_id = (String) dynaBean.get("storesList");
        String goods_id = (String) dynaBean.get("goodsList");
        String stocks = (String) dynaBean.get("stocks");// 当前库存
        String ver_stocks = (String) dynaBean.get("ver_stocks");// 盘点数
        String memo = (String) dynaBean.get("memo");

        String HH = (String) dynaBean.get("HH");
        String mm = (String) dynaBean.get("mm");
        // String[] pyBuyPrices = request.getParameterValues("pyBuyPrice");//
        // 盘盈价格列表
        // String[] stackIds = request.getParameterValues("stack_id");//
        // 盘亏单据ID列表

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
            return null;
        } else if (null == user.getCust_id() || Integer.valueOf(cust_id) != user.getCust_id().intValue()) {
            super.renderJavaScript(response, "alert('出现未知错误，请联系管理员！');history.back();");
            return null;
        }

        HH = StringUtils.isBlank(HH) ? "00" : HH;
        mm = StringUtils.isBlank(mm) ? "00" : mm;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotBlank(opr_date) && StringUtils.isNotBlank(store_id) && StringUtils.isNotBlank(goods_id)
                && StringUtils.isNotBlank(stocks) && StringUtils.isNotBlank(ver_stocks)) {
            opr_date = opr_date + " " + HH + ":" + mm;
            Date _date = DateUtils.parseDate(opr_date, new String[]{"yyyy-MM-dd HH:mm"});

            String checkResult = super.checkKhContainsMd(user.getCust_id(), Long.valueOf(goods_id),
                    Long.valueOf(store_id));
            if (StringUtils.isNotBlank(checkResult)) {
                super.renderJavaScript(response, "alert('" + checkResult + "');history.back();");
                return null;
            }

            JStocksVerify entity = new JStocksVerify();
            entity.setGoods_id(Long.valueOf(goods_id));
            entity.setStore_id(Long.valueOf(store_id));
            entity.getMap().put("compare_date", format.format(_date));
            entity.setC_id(user.getCust_id());
            Long count = super.getFacade().getJStocksVerifyService().getJStocksVerifyCount(entity);
            if (count != null && count > 0) {
                super.renderJavaScript(response, "alert('同一个仓库的同一个商品一天只能盘存一次！');history.back();");
                return null;
            }
            // 判断盘点日期是否在上次盘点日期在现在日期之间 start
            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date current = new Date();
            Date d_date = DateUtils.parseDate(dformat.format(current), new String[]{"yyyy-MM-dd HH:mm"});
            if (_date.compareTo(d_date) > 0) {
                super.renderJavaScript(response, "alert('盘点日期超出当前时间,请重新选择！');history.back();");
                return null;
            }

            JStocksVerify jTemp = new JStocksVerify();
            jTemp.setStore_id(Long.valueOf(store_id));
            jTemp.setGoods_id(Long.valueOf(goods_id));
            jTemp.setC_id(user.getCust_id());
            jTemp.getMap().put("max_date_id", true);
            jTemp.getMap().put("store_id_value", store_id);
            jTemp.getMap().put("goods_id_value", goods_id);
            jTemp.getRow().setCount(1);
            jTemp = super.getFacade().getJStocksVerifyService().getJStocksVerify(jTemp);
            if (null != jTemp) {
                Date lastOprDate = jTemp.getOpr_date();
                logger.info("______________________________上一次盘点日期 = " + dformat.format(lastOprDate));
                logger.info("______________________________本次盘点日期 = " + dformat.format(_date));
                logger.info("______________________________当前时间= " + dformat.format(d_date));
                if (lastOprDate.getTime() > _date.getTime()) {
                    super.renderJavaScript(response, "alert('抱歉，您选择的商品盘点日期小于最近一次盘点日期！');history.back();");
                    return null;
                }
            }
            // end
            Long result = super
                    .getFacade()
                    .getKonkaCustomerPublicService()
                    .createStockVerify(_date, store_id, goods_id, stocks, ver_stocks, user.getCust_id(), memo,
                            new String[]{}, new String[]{});
            if (0 == result.intValue()) { // 成功插入
                saveMessage(request, "konka.jstockverify.inserted.success");
            } else if (-1 == result.intValue()) { // 盘点日期选择非法
                saveError(request, "konka.jstockverify.oprdate.error");
            }
        } else {
            saveError(request, "konka.jstockverify.params.missing");
        }
        // the line below is added for pagination
        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        // pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
        // "id", "pks", "method")));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        return forward;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String id = (String) dynaBean.get("id");
        String bill_sn = (String) dynaBean.get("bill_sn");

        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            return null;
        } else if (user.getCust_id() == null) {
            return null;
        }

        if (StringUtils.isBlank(id) && StringUtils.isBlank(bill_sn)) {
            saveError(request, "errors.failed");
            StringBuffer pathBuffer = new StringBuffer();
            pathBuffer.append(mapping.findForward("success").getPath());
            pathBuffer.append("&mod_id=" + mod_id);
            pathBuffer.append("&");
            // pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
            // "id", "pks", "method")));
            ActionForward forward = new ActionForward(pathBuffer.toString(), true);
            return forward;
        }

        JStocksVerify entity = new JStocksVerify();
        if (StringUtils.isNotBlank(id)) {
            entity.setId(Long.valueOf(id));
        } else {
            entity.setBill_sn(bill_sn);
        }
        entity = getFacade().getJStocksVerifyService().getJStocksVerify(entity);
        if (null != entity) {
            if (null != entity.getStore_id()) {
                JBaseStore jBaseStore = new JBaseStore();
                jBaseStore.setStore_id(entity.getStore_id());
                jBaseStore = getFacade().getJBaseStoreService().getJBaseStore(jBaseStore);
                dynaBean.set("store_name", jBaseStore.getStore_name());
            }

            if (null != entity.getGoods_id()) {
                JBaseGoods jBaseGoods = new JBaseGoods();
                jBaseGoods.setGoods_id(entity.getGoods_id());
                jBaseGoods = getFacade().getJBaseGoodsService().getJBaseGoods(jBaseGoods);
                dynaBean.set("goods_name", jBaseGoods.getGoods_name());
            }
        }
        super.copyProperties(form, entity);

        return mapping.findForward("view");
    }

    public ActionForward getStockNumForSelectPd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) {
        DynaBean dynaBean = (DynaBean) form;
        String store_id = (String) dynaBean.get("store_id");
        String good_id = (String) dynaBean.get("good_id");
        String state = "-1";
        JSONObject obj = new JSONObject();

        if (StringUtils.isNotBlank(store_id) && StringUtils.isNotBlank(good_id)) {
            JStocksVerify jStocksVerify = new JStocksVerify();
            jStocksVerify.setStore_id(Long.valueOf(store_id));
            jStocksVerify.setGoods_id(Long.valueOf(good_id));
            jStocksVerify.getRow().setCount(1);
            jStocksVerify = getFacade().getJStocksVerifyService().getJStocksVerifyForLastedVerify(jStocksVerify);
            if (null != jStocksVerify) {
                obj.put("lastOprDate", df.format(jStocksVerify.getOpr_date()));
            }

            Long stocks = 0L;
            BigDecimal money = new BigDecimal("0");
            HttpSession session = request.getSession();
            PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

            JBaseGoods jBaseGoods = new JBaseGoods();
            jBaseGoods.getMap().put("store_id", store_id);
            jBaseGoods.getMap().put("goods_id", good_id);
            jBaseGoods.getMap().put("r3_id", user.getCust_id());
            List<JBaseGoods> entityList = super.getFacade().getJBaseGoodsService()
                    .getJBaseGoodsForComeOutNumWithMoney(jBaseGoods);
            if (null != entityList && entityList.size() > 0) {
                JBaseGoods goods = entityList.get(0);
                stocks = Long.parseLong(goods.getMap().get("init_num").toString())
                        + Long.parseLong(goods.getMap().get("come_num").toString())
                        - Long.parseLong(goods.getMap().get("out_num").toString())
                        + Long.parseLong(goods.getMap().get("other_num").toString())
                ;
                money = new BigDecimal(goods.getMap().get("init_money").toString()).add(
                        new BigDecimal(goods.getMap().get("come_money").toString())).subtract(
                        new BigDecimal(goods.getMap().get("sale_cost").toString()))
                        .add( new BigDecimal(goods.getMap().get("other_money").toString())
                );
            }

            obj.put("stocks", stocks);
            obj.put("money", money);
        } else {
            obj.put("stocks", "0");
            obj.put("money", "0");
        }
        state = "0";
        // }
        obj.put("state", state);

        super.renderJson(response, obj.toString());
        return null;
    }

    /**
     * 根据ID找仓库名称或机型名称
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getStoreAndGoodsInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) {
        DynaBean dynaBean = (DynaBean) form;
        String store_id = (String) dynaBean.get("store_id");
        String good_id = (String) dynaBean.get("good_id");
        String state = "0";
        JSONObject obj = new JSONObject();

        if (StringUtils.isBlank(store_id) || StringUtils.isBlank(good_id)) { // 参数丢失
            state = "0";
        } else {
            // 取仓库
            JBaseStore jBaseStore = new JBaseStore();
            jBaseStore.setStore_id(Long.valueOf(store_id));
            jBaseStore = getFacade().getJBaseStoreService().getJBaseStore(jBaseStore);

            JBaseGoods jBaseGoods = new JBaseGoods();
            jBaseGoods.setGoods_id(Long.valueOf(good_id));
            jBaseGoods = getFacade().getJBaseGoodsService().getJBaseGoods(jBaseGoods);
            if (null != jBaseStore && null != jBaseGoods) { // 仓库、商品信息
                state = "1";
                obj.put("storeName", jBaseStore.getStore_name());
                obj.put("goodName", jBaseGoods.getGoods_name());

            } else { // 仓库或者商品信息丢失
                state = "-2";
            }
        }
        obj.put("state", state);

        super.renderJson(response, obj.toString());
        return null;
    }

    public Action getPkGoodsAndStoresList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws ParseException {
        DynaBean dynaBean = (DynaBean) form;
        String store_id = (String) dynaBean.get("store_id");
        String good_id = (String) dynaBean.get("good_id");
        String num = (String) dynaBean.get("num");
        String state = "0";

        JSONObject result = new JSONObject();
        JSONArray objList = new JSONArray();
        if (StringUtils.isNotBlank(store_id) && StringUtils.isNotBlank(good_id) && StringUtils.isNotBlank(num)) {
            HttpSession session = request.getSession();
            PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);

            JStocksStack entity = new JStocksStack();
            entity.setStore_id(Long.valueOf(store_id));
            entity.setGoods_id(Long.valueOf(good_id));
            if (null != user && null != user.getCust_id()) {
                entity.setC_id(user.getCust_id());
            }
            entity.setStatus(0);
            entity.getMap().put("order_by_stack_id_asc", "true");
            entity.getRow().setCount(Integer.valueOf(num));
            List<JStocksStack> list = getFacade().getJStocksStackService().getJStocksStackList(entity);
            if (null != list && list.size() > 0) {
                state = "1";
                for (JStocksStack jss : list) {
                    // 单据编号
                    String bill_id_push = jss.getBill_id_push();
                    // 业务类型
                    String type = StringUtils.split(bill_id_push, "-")[0];
                    String business_type = "";
                    if ("CG".equals(type)) {
                        business_type = "采购";
                    } else if ("CGTH".equals(type)) {
                        business_type = "采购退货";
                    } else if ("XS".equals(type)) {
                        business_type = "销售";
                    } else if ("XSTH".equals(type)) {
                        business_type = "销售退货";
                    } else if ("PK".equals(type)) {
                        business_type = "盘亏";
                    } else if ("PY".equals(type)) {
                        business_type = "盘盈";
                    } else if ("KSXF".equals(type)) {
                        business_type = "库实相符";
                    }

                    String time = StringUtils.split(bill_id_push, "-")[1];
                    Date bill_date = dfm.parse(time);

                    JSONObject obj = new JSONObject();
                    obj.put("goods_cost", jss.getGoods_cost());
                    obj.put("bill_id_push", jss.getBill_id_push());
                    obj.put("business_type", business_type);
                    obj.put("bill_date", df.format(bill_date));
                    obj.put("stack_id", jss.getStack_id());
                    objList.add(obj);
                }
                result.put("list", objList);
            } else {
                state = "-1";
            }
        }
        result.put("state", state);
        logger.info("__________________________result = " + result.toString());
        renderJson(response, result.toString());
        return null;
    }

    // 根据store_id获得该仓库中的商品
    public ActionForward getGoodsListForSelectPd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String store_id = (String) dynaBean.get("store_id");
        StringBuffer sb = new StringBuffer("{");
        sb = sb.append("\"list\":[");
        if (StringUtils.isBlank(store_id)) {
            sb = sb.append("]}");
            super.renderJson(response, sb.toString());
            return null;
        }

        // 获取商品名称
        JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
        initStock.setStore_id(Long.parseLong(store_id));
        initStock.setInit_state(0);
        initStock.getMap().put("goods_stutes", 0);
        initStock.getMap().put("order_by", "order by b.GOODS_NAME asc");
        List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
                .getJBaseGoodsInitStockList(initStock);

        if (null != initStockList && initStockList.size() > 0) {
            for (JBaseGoodsInitStock temp : initStockList) {
                sb = sb.append("{");
                sb = sb.append("\"goods_id\":\"").append(temp.getGoods_id()).append("\",");
                sb = sb.append("\"goods_name\":\"").append(temp.getMap().get("goods_name")).append("\"");
                sb = sb.append("},");
            }
        }
        String sb_str = StringUtils.removeEnd(sb.toString(), ",") + "]}";
        logger.info("sb_str {}", sb_str);
        super.renderJson(response, sb_str);
        return null;
    }

    // 到导入页面
    public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        setNaviStringToRequestScope(form, request);
        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");
        String opr_date = (String) dynaBean.get("opr_date");
        String opr_HH = (String) dynaBean.get("opr_HH");
        String opr_mm = (String) dynaBean.get("opr_mm");
        request.setAttribute("mod_id", mod_id);
        request.setAttribute("opr_date", opr_date);
        request.setAttribute("opr_HH", opr_HH);
        request.setAttribute("opr_mm", opr_mm);
        return new ActionForward("/../customer/KonkaStockInventory/excel.jsp");
    }

    public ActionForward showExcelData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        super.saveToken(request);
        setNaviStringToRequestScope(form, request);
        HttpSession session = request.getSession();
        PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
        if (null == user) {
            return null;
        } else if (user.getCust_id() == null) {
            return null;
        }

        // 取仓库
        JBaseStore jBaseStore = new JBaseStore();
        jBaseStore.setC_id(user.getCust_id());
        jBaseStore.setIs_del(0);
        List<JBaseStore> jBaseStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jBaseStore);
        // 商品 根据库存ID取商品List（如果没有则查询put进来）
        Map<Long, List<JBaseGoodsInitStock>> goodsListMap = new HashMap<Long, List<JBaseGoodsInitStock>>();
        // 根据库存ID取所有商品库存（减少搜索次数）提高效率
        Map<Long, List<JBaseGoods>> jBaseGoodsListMap = new HashMap<Long, List<JBaseGoods>>();

        if (null != jBaseStoreList && jBaseStoreList.size() > 0) {
            for (JBaseStore jbs : jBaseStoreList) {
                if ("总库".equals(jbs.getStore_name())) {
                    request.setAttribute("main_store_id", jbs.getStore_id());
                    JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
                    initStock.setStore_id(jbs.getStore_id());
                    initStock.setInit_state(0);
                    initStock.getMap().put("order_by", "order by b.GOODS_NAME asc");
                    List<JBaseGoodsInitStock> initStockList = super.getFacade().getJBaseGoodsInitStockService()
                            .getJBaseGoodsInitStockList(initStock);
                    request.setAttribute("jBaseGoodsList", initStockList);
                    // 总库的商品这里后了 下面就不需要再查了
                    goodsListMap.put(jbs.getStore_id(), initStockList);
                    break;
                }
            }
        }
        request.setAttribute("jBaseStoreList", jBaseStoreList);
        Date today = new Date();
        request.setAttribute("today", df.format(today));
        request.setAttribute("cust_id", user.getCust_id());

        // 导入回显到页面
        DynaBean dynaBean = (DynaBean) form;

        FormFile formFile = (FormFile) dynaBean.get("up_load_file");

        String regEx = "^\\d{0,10}(\\.\\d+)?$";
        Pattern vali_dig = Pattern.compile(regEx);

        int startRow = 2;
        int startCol = 1;
        HSSFWorkbook workbook = new HSSFWorkbook(formFile.getInputStream());
        HSSFSheet sheet = null;

        // 返回页面的回显数据
        List<Map<String, Object>> excelMapList = new ArrayList<Map<String, Object>>();
        // start excel data
        // 只支持单个sheet
        sheet = workbook.getSheetAt(0);
        int rowCounts = sheet.getLastRowNum();// 行数
        for (int j = startRow; j <= rowCounts; j++) {// 获取每行
            HSSFRow row = sheet.getRow(j);
            String c0 = getCellText(row, startCol);
            String c1 = getCellText(row, startCol + 1);
            String c2 = getCellText(row, startCol + 2);
            String c3 = getCellText(row, startCol + 3);
            String c4 = getCellText(row, startCol + 4);
            String c5 = getCellText(row, startCol + 5);

            logger.info("c0=" + c0 + "   c1=" + c1 + "   c2=" + c2 + "   c3=" + c3 + "   c4=" + c4 + "   c5=" + c5);

            Map<String, Object> tempMap = new HashMap<String, Object>();
            if (StringUtils.isBlank(c0) || StringUtils.isBlank(c1) || StringUtils.isBlank(c2)
                    || StringUtils.isBlank(c3)) {
                break;
            }

            // 1. *仓库名称
            if (null != c0 && !"".equals(c0)) {
                boolean storeExistFlag = false;
                if (null != jBaseStoreList && jBaseStoreList.size() > 0) {
                    // 仓库遍历
                    for (int i = 0; i < jBaseStoreList.size(); i++) {
                        if (c0.equals(jBaseStoreList.get(i).getStore_name())) {
                            tempMap.put("store_id", jBaseStoreList.get(i).getStore_id());
                            storeExistFlag = true;
                            break;
                        }
                    }
                }
                // 如果不存在
                if (!storeExistFlag) {
                    super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，仓库名称“" + c0
                            + "”不存在！');history.back();");
                    return null;
                }
            } else {
                super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，仓库名称“" + c0
                        + "”格式不正确，不能为空！');history.back();");
                return null;
            }

            // 2. *商品名称
            if (null != c1 && !"".equals(c1)) {
                List<JBaseGoodsInitStock> goodsList = goodsListMap.get((Long) tempMap.get("store_id"));
                if (goodsList == null) {
                    // 获取商品名称
                    JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
                    initStock.setStore_id((Long) tempMap.get("store_id"));
                    initStock.setC_id(user.getCust_id());
                    initStock.getMap().put("order_by", "order by b.GOODS_NAME asc");
                    goodsList = super.getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStockList(initStock);
                    goodsListMap.put((Long) tempMap.get("store_id"), goodsList);
                }
                tempMap.put("jBaseGoodsList", goodsList);

                // 商品是否存在
                boolean goodsExistFlag = false;
                Long goods_id = 0L;
                if (null != goodsList && goodsList.size() > 0) {
                    for (JBaseGoodsInitStock temp : goodsList) {
                        if (c1.equals((String) temp.getMap().get("goods_name"))) {
                            goodsExistFlag = true;
                            goods_id = temp.getGoods_id();
                            break;
                        }
                    }
                }

                if (goodsExistFlag) {
                    tempMap.put("goods_id", goods_id);
                    // 查询盘点前数量与金额
                    Long store_id = (Long) tempMap.get("store_id");
                    // 从缓存中拿 没有则取查 减少查询次数
                    List<JBaseGoods> jBaseGoodsList = jBaseGoodsListMap.get(store_id);
                    if (jBaseGoodsList == null) {
                        JBaseGoods jBaseGoods = new JBaseGoods();
                        jBaseGoods.getMap().put("store_id", store_id);
                        jBaseGoods.getMap().put("r3_id", user.getCust_id());
                        jBaseGoodsList = super.getFacade().getJBaseGoodsService()
                                .getJBaseGoodsForComeOutNumWithMoney(jBaseGoods);
                        jBaseGoodsListMap.put(store_id, jBaseGoodsList);
                    }
                    Long stocks = 0L;
                    BigDecimal money = new BigDecimal("0");

                    if (null != jBaseGoodsList && jBaseGoodsList.size() > 0) {
                        for (JBaseGoods tempGoods : jBaseGoodsList) {
                            if (tempMap.get("goods_id").toString()
                                    .equals(tempGoods.getMap().get("goods_id").toString())) {
                                stocks = Long.parseLong(tempGoods.getMap().get("init_num").toString())
                                        + Long.parseLong(tempGoods.getMap().get("come_num").toString())
                                        - Long.parseLong(tempGoods.getMap().get("out_num").toString());
                                money = new BigDecimal(tempGoods.getMap().get("init_money").toString()).add(
                                        new BigDecimal(tempGoods.getMap().get("come_money").toString())).subtract(
                                        new BigDecimal(tempGoods.getMap().get("sale_cost").toString()));
                                break;
                            }

                        }
                    }

                    tempMap.put("stocks", stocks);
                    tempMap.put("money", money);
                } else {
                    super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，商品名称“" + c1
                            + "”不存在！请到“账户设置－基础数据－商品数据”中维护！');history.back();");
                    return null;
                }
            } else {
                super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，商品名称“" + c1
                        + "”格式不正确，不能为空！');history.back();");
                return null;
            }

            // 3. *盘点后数量
            String c2v = StringUtils.replace(StringUtils.replace(c2, ",", ""), "，", "");

            if (StringUtils.isNotBlank(c2) && StringUtils.isNotBlank(c2v)) {
                tempMap.put("ver_stocks", Long.valueOf(subZeroAndDot(c2v)));
            } else {
                super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，盘点后数量不合法，必须填写数字！');history.back();");
                return null;
            }

            // 4. *盘点单价
            String c3v = StringUtils.replace(StringUtils.replace(c3, ",", ""), "，", "");
            if (StringUtils.isNotBlank(c3) && StringUtils.isNotBlank(c3v) && vali_dig.matcher(c3v).matches()) {
                tempMap.put("price", new BigDecimal(subZeroAndDot(c3v)));
            } else {
                super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，单价不合法，必须填写数字！');history.back();");
                return null;
            }

            // 5. *盘点后金额
            String c4v = StringUtils.replace(StringUtils.replace(c4, ",", ""), "，", "");
            if (StringUtils.isNotBlank(c4) && StringUtils.isNotBlank(c4v) && vali_dig.matcher(c4v).matches()) {
                tempMap.put("ver_money", new BigDecimal(subZeroAndDot(c4v)));
            } else {
                super.renderJavaScript(response, "alert('EXCEL第" + (j + 1) + "行，盘点后金额不合法，必须填写数字！');history.back();");
                return null;
            }

            // 6. *备注说明
            if (StringUtils.isNotBlank(c5)) {
                if (c5.length() > 200) {
                    super.renderJavaScript(response, "alert('EXCEL第" + (j + 1)
                            + "行，备注说明太长，最多不能超过200字！');history.back();");
                    return null;
                } else {
                    tempMap.put("memo", c5);
                }
            }
            excelMapList.add(tempMap);
        }
        request.setAttribute("excelMapList", excelMapList);
        // 回写日期时分
        String opr_date = (String) dynaBean.get("opr_date");
        String opr_HH = (String) dynaBean.get("opr_HH");
        String opr_mm = (String) dynaBean.get("opr_mm");
        request.setAttribute("today", opr_date);
        request.setAttribute("opr_HH", opr_HH);
        request.setAttribute("opr_mm", opr_mm);

        return mapping.findForward("input");
    }

    private String getCellText(HSSFRow row, int col) {
        if (row.getCell(col) == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#0.000000");
        HSSFCell cell = row.getCell(col);
        String data = "";
        if (null != cell) {
            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                data = df.format(cell.getNumericCellValue());
            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                data = cell.getRichStringCellValue().toString();
            }
        }
        return data.trim();
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (null != s && s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

}
