package com.ebiz.mmt.web.struts.webservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaMobileSailDataJson;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.webservice.dto.CXYSalesInfo;
import com.ebiz.mmt.web.struts.webservice.dto.FGSSalesInfo;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * 
 * 为OA系统提供查询零售通的零售数据的服务
 * 
 * @version 2015/01/19
 * 
 */
public class KonkaMobileSailDataJsonAction extends BaseAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;

        // 密码
        String userpass = (String) dynaBean.get("userpass");

        String user_id = (String) dynaBean.get("user_id");

        String startime = (String) dynaBean.get("startime");

        String endtime = (String) dynaBean.get("endtime");

        String type_value = (String) dynaBean.get("type_value");

        String store_id = (String) dynaBean.get("store_id");

        String model_id = (String) dynaBean.get("model_id");

        String store_name_like = (String) dynaBean.get("store_name_like");

        Pager pager = (Pager) dynaBean.get("pager");

        if (!GenericValidator.isLong(user_id) || StringUtils.isBlank(userpass)) {
            super.renderText(response, "参数错误，请联系管理员！");
            return null;
        }
    	String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
        PeProdUser ui = checkUserid(user_id, userpass,android_version,ios_version);
        if (null == ui) {
            super.renderText(response, "用户信息出错，请联系管理员！");
            return null;
        }

        KonkaMobileSailData entity = new KonkaMobileSailData();

        if (StringUtils.isBlank(type_value)) {
            type_value = "1";
        }

        if (StringUtils.isNotBlank(startime)) {
            entity.getMap().put("report_date_begin", startime);
        }

        if (StringUtils.isNotBlank(endtime)) {
            entity.getMap().put("report_date_end", endtime);
        }

        if (StringUtils.isNotBlank(store_id)) {
            entity.setDept_id(Long.valueOf(store_id));
        }

        if (StringUtils.isNotBlank(model_id)) {
            entity.setModel_id(Long.valueOf(model_id));
        }
        // 添加一个门店名称的模糊查询
        if (StringUtils.isNotBlank(store_name_like)) {

            // System.out.println(changeToUTF8(store_name_like) + "+++++++++++++++++++++++++");
            entity.getMap().put("store_name_like", store_name_like);
        }

        entity.getMap().put("num_price_not_0", true);
        entity.getMap().put("r3_code_is_not_null", true);

        // 数据级别判断开始
        Long __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
        int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
        logger.info("Max level : {}", max_dlevel);
        switch (max_dlevel) {
            case 9:
                // 集团及以下部门可见
                __dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
                break;
            case 8:
                // 分公司及以下部门可见
                KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
                if (null != dept_fgs && null != dept_fgs.getDept_id()) {
                    __dept_id = dept_fgs.getDept_id(); // 分公司部门ID
                    entity.getMap().put("dept_id_start_json", __dept_id);
                    entity.getMap().put("filter_by_job_id_eq", ui.getJob_id());
                }
                break;
            case 7:
                // 我所在的部门及以下部门可见
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("dept_id_start_json", __dept_id);
                entity.getMap().put("filter_by_job_id_eq", ui.getJob_id());
                break;
            case 0:
                __dept_id = ui.getDept_id(); // 默认为当前用户所在部门
                entity.getMap().put("filter_by_ywy_id_eq_json", ui.getJob_id());
                entity.getMap().put("filter_by_job_id_eq", ui.getJob_id());
                break;
            default:
                // 出错
        }

        entity.getMap().put("session_user_id", ui.getId());
        // 数据级别判断结束

        entity.setIs_del(0);
        List<KonkaMobileSailDataJson> jsonList = new ArrayList<KonkaMobileSailDataJson>();

        if ("1".equals(type_value)) {
            entity.getMap().put("order_by_date_store_price_desc", true);

            Long recordCount = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataCount(entity);
            pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
            entity.getRow().setFirst(pager.getFirstRow());
            entity.getRow().setCount(pager.getRowCount());

            List<KonkaMobileSailData> entityList = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataPaginatedList(entity);

            if (entityList.size() > 0) {

                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (KonkaMobileSailData temp : entityList) {

                    KonkaMobileSailDataJson kmsd = new KonkaMobileSailDataJson();// 返回JSON对象
                    if (null != temp.getDept_id()) {
                        kmsd.setStore_id(temp.getDept_id().toString());
                    } else {
                        kmsd.setStore_id("");
                    }

                    if (temp.getDept_name() != null) {
                        kmsd.setStore_name(temp.getDept_name());
                    } else {
                        kmsd.setStore_name("");
                    }

                    if (null != temp.getModel_name()) {
                        kmsd.setMd_name(temp.getModel_name());
                    } else {
                        kmsd.setMd_name("");
                    }

                    if (null != temp.getNum()) {
                        kmsd.setSail_num(temp.getNum());
                    } else {
                        kmsd.setSail_num(0L);
                    }

                    if (null != temp.getAll_price()) {
                        kmsd.setSail_price(temp.getAll_price());
                    } else {
                        kmsd.setSail_price(new BigDecimal(0));
                    }

                    if (null != temp.getReport_name()) {
                        kmsd.setReprot_man(temp.getReport_name());
                    } else {
                        kmsd.setReprot_man("");
                    }

                    if (null != temp.getReport_date()) {
                        kmsd.setReport_date(df1.format(temp.getReport_date()));
                    } else {
                        kmsd.setReport_date("");
                    }

                    jsonList.add(kmsd);
                }
            }
        } else if ("2".equals(type_value)) {
            Long recordCount = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataToJsonCount(entity);
            pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
            entity.getRow().setFirst(pager.getFirstRow());
            entity.getRow().setCount(pager.getRowCount());

            List<KonkaMobileSailData> entityList = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataToJsonPaginatedList(entity);

            if (entityList.size() > 0) {
                for (KonkaMobileSailData temp : entityList) {
                    KonkaMobileSailDataJson kmsd = new KonkaMobileSailDataJson();// 返回JSON对象

                    if (null != temp.getMap().get("store_id")) {
                        kmsd.setStore_id(temp.getMap().get("store_id").toString());
                    } else {
                        kmsd.setStore_id("");
                    }

                    if (temp.getMap().get("sail_num") != null) {
                        kmsd.setSail_num(Long.valueOf(temp.getMap().get("sail_num").toString()));
                    } else {
                        kmsd.setSail_num(0L);
                    }

                    if (temp.getMap().get("sail_price") != null) {
                        kmsd.setSail_price(new BigDecimal(temp.getMap().get("sail_price").toString()));
                    }

                    if (temp.getMap().get("md_name") != null) {
                        kmsd.setMd_name(temp.getMap().get("md_name").toString());
                    } else {
                        kmsd.setMd_name("");
                    }

                    if (temp.getMap().get("store_name") != null) {
                        kmsd.setStore_name(temp.getMap().get("store_name").toString());
                    } else {
                        kmsd.setStore_name("");
                    }
                    jsonList.add(kmsd);
                }
            }
        } else {
            Long recordCount = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataToJson3Count(entity);
            pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
            entity.getRow().setFirst(pager.getFirstRow());
            entity.getRow().setCount(pager.getRowCount());

            List<KonkaMobileSailData> entityList = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataToJson3PaginatedList(entity);

            if (entityList.size() > 0) {
                for (KonkaMobileSailData temp : entityList) {
                    KonkaMobileSailDataJson kmsd = new KonkaMobileSailDataJson();// 返回JSON对象

                    if (null != temp.getMap().get("store_id")) {
                        kmsd.setStore_id(temp.getMap().get("store_id").toString());
                    } else {
                        kmsd.setStore_id("");
                    }

                    if (temp.getMap().get("sail_num") != null) {
                        kmsd.setSail_num(Long.valueOf(temp.getMap().get("sail_num").toString()));
                    } else {
                        kmsd.setSail_num(0L);
                    }

                    if (temp.getMap().get("sail_price") != null) {
                        kmsd.setSail_price(new BigDecimal(temp.getMap().get("sail_price").toString()));
                    }

                    if (temp.getMap().get("store_name") != null) {
                        kmsd.setStore_name(temp.getMap().get("store_name").toString());
                    } else {
                        kmsd.setStore_name("");
                    }
                    jsonList.add(kmsd);
                }
            }
        }

        super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(jsonList));
        return null;
    }

    protected PeProdUser checkUser(String user_id, String userpass) throws Exception {
        PeProdUser ui = new PeProdUser();
        if (StringUtils.isEmpty(user_id)) return null;
        if (StringUtils.isEmpty(userpass)) return null;

        PeProdUser entity = new PeProdUser();
        entity.setId(Long.valueOf(user_id));
        entity.setIs_del(0);
        DESPlus des = new DESPlus();
        entity.setPass_word(des.encrypt(userpass));
        ui = getFacade().getPeProdUserService().getPeProdUser(entity);
        return ui;
    }

    public ActionForward listCXYSaleSInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String r3_job_ids = (String) dynaBean.get("r3_job_id");// 可多个id转过来
        String user_name = (String) dynaBean.get("user_name");// 促销员名字
        String date_begin = (String) dynaBean.get("date_begin");
        String date_end = (String) dynaBean.get("date_end");

        List<CXYSalesInfo> returnInfoList = new ArrayList<CXYSalesInfo>();


        CXYSalesInfo cobj = new CXYSalesInfo();
        if (StringUtils.isBlank(r3_job_ids)) {
            cobj.setReturn_state(1);
            cobj.setReturn_error("请求参数 r3_job_id 不能为空!");
            cobj.setSalesInfoList(null);
            returnInfoList.add(cobj);
            super.renderJson(response, JSON.toJSONString(returnInfoList));
            return null;
        }

        // if (StringUtils.isBlank(user_name)) {
        // cobj.setReturn_state(1);
        // cobj.setReturn_error("parameters user_name can not be null ");
        // cobj.setSalesInfoList(null);
        // super.renderJson(response, JSON.toJSONString(cobj));
        // return null;
        // }

        if (date_begin == null || date_end == null || "".equals(date_begin) || "".equals(date_end)) {
            cobj.setReturn_state(1);
            cobj.setReturn_error("请求参数 date_begin,date_end 不能为空!");
            cobj.setSalesInfoList(null);
            returnInfoList.add(cobj);
            super.renderJson(response, JSON.toJSONString(returnInfoList));
            return null;
        }

        String[] the_job_ids = r3_job_ids.split(";");
        if (the_job_ids != null && the_job_ids.length >= 400) {
            cobj.setReturn_state(1);
            cobj.setReturn_error("请求参数 the_job_ids个数不能超过400/次!");
            cobj.setSalesInfoList(null);
            returnInfoList.add(cobj);
            super.renderJson(response, JSON.toJSONString(returnInfoList));
            return null;
        }

        boolean usr_ok_flag = true;

        String no_exits_job_ids = "";
        String rebind_job_ids = "";

        // 检验job_Id
        for (String jobid : the_job_ids) {
            PeProdUser user = new PeProdUser();
            user.setR3_job_id(jobid);
            List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
            if (null == userList || userList.size() == 0) {
                usr_ok_flag = false;
                no_exits_job_ids += jobid + ";";
                continue;
            }

            if (userList != null && userList.size() > 1) {
                usr_ok_flag = false;
                rebind_job_ids += jobid + ";";
                continue;
            }

        }

        if (usr_ok_flag == false) {
            if (no_exits_job_ids.length() > 0) {
                cobj.setIs_valid("-1");
                cobj.setReturn_state(1);
                cobj.setReturn_error("请求参数 r3_job_id:" + no_exits_job_ids + "在渠道系统不存在!");
                cobj.setSalesInfoList(null);
                returnInfoList.add(cobj);
                super.renderJson(response, JSON.toJSONString(returnInfoList));
                return null;
            }
            if (rebind_job_ids.length() > 0) {
                cobj.setIs_valid("-1");
                cobj.setReturn_state(1);
                cobj.setReturn_error("请求参数r3_job_id:" + rebind_job_ids + "在渠道系统重复绑定!");
                cobj.setSalesInfoList(null);
                returnInfoList.add(cobj);
                super.renderJson(response, JSON.toJSONString(returnInfoList));
                return null;
            }
            cobj.setIs_valid("-1");
            cobj.setReturn_state(1);
            cobj.setReturn_error("未知错误!");
            cobj.setSalesInfoList(null);
            returnInfoList.add(cobj);
            super.renderJson(response, JSON.toJSONString(returnInfoList));
            super.renderJson(response, JSON.toJSONString(cobj));
            return null;

        }
        // 检验job_Id end

        // 入参
        for (String jobid : the_job_ids) {
            CXYSalesInfo robj = new CXYSalesInfo();
            // jobid -->usrid = 1:1
            PeProdUser user = new PeProdUser();
            user.setR3_job_id(jobid);
            // user.setIs_del(0); 虽然停用,也要算工资
            List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);

            KonkaMobileSailData sailDate = new KonkaMobileSailData();
            sailDate.setReport_id(userList.get(0).getId());
            if (StringUtils.isNotBlank(date_begin)) {
                sailDate.getMap().put("date_begin", date_begin + " 00:00:00");
            }
            if (StringUtils.isNotBlank(date_end)) {
                sailDate.getMap().put("date_end", date_end + " 23:59:59");
            }

            // {"ALL_MONEY":240434,"ALL_NUM":100,"STORE_ID":173,"STORE_NAME":"大润发大朗店"}
            List<HashMap<String, Object>> entityList = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataListByR3JobId(sailDate);

            double all_money = 0d;
            long all_num = 0L;
            String store_id = "";
            String store_name = "";
            String report_name = "";// 上报记录的人
            if (entityList != null && entityList.size() > 0) {
                for (HashMap<String, Object> map : entityList) {
                    all_money += getBigDecimal(map.get("ALL_MONEY")).doubleValue();
                    all_num += Long.valueOf(map.get("ALL_NUM") + "");
                    store_id += String.valueOf(map.get("STORE_ID") == null ? "" : map.get("STORE_ID")) + ";";
                    store_name += String.valueOf(map.get("STORE_NAME") == null ? "" : map.get("STORE_NAME")) + ";";
                    report_name = String.valueOf(map.get("REPORT_NAME") == null ? "" : map.get("REPORT_NAME")) + "";
                }
            }

            // obj.setSalesInfoList(entityList);
            robj.setReport_name(report_name);
            robj.setR3_job_id(jobid);
            robj.setIs_valid("0");
            robj.setReturn_state(0);
            robj.setReturn_error("");
            DecimalFormat df = new DecimalFormat("#.##");
            robj.setReturn_error("");
            robj.setAll_money(df.format(all_money));
            robj.setAll_num(df.format(all_num));
            if (store_id.length() > 0) {
                robj.setStore_id(store_id.substring(0, store_id.length() - 1));
            } else {
                robj.setStore_id("");
            }
            if (store_name.length() > 0) {
                robj.setStore_name(store_name.substring(0, store_name.length() - 1));
            } else {
                robj.setStore_name(store_name);
            }

            returnInfoList.add(robj);
        }

        super.renderJson(response, JSON.toJSONString(returnInfoList));

        return null;
    }

    public ActionForward listFGSSaleSInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        String dept_sn = (String) dynaBean.get("fgs_code");
        String date_begin = (String) dynaBean.get("date_begin");
        String date_end = (String) dynaBean.get("date_end");

        FGSSalesInfo obj = new FGSSalesInfo();

        if (StringUtils.isBlank(dept_sn)) {
            obj.setReturn_state(1);
            obj.setReturn_error("请求参数 fgs_code 不能为空!");
            obj.setAll_money("0");
            obj.setAll_num("0");
            super.renderJson(response, JSON.toJSONString(obj));
            return null;
        }

        if (date_begin == null || date_end == null || "".equals(date_begin) || "".equals(date_end)) {
            obj.setReturn_state(1);
            obj.setReturn_error("请求参数 date_begin,date_end 不能为空!");
            obj.setAll_money("0");
            obj.setAll_num("0");
            super.renderJson(response, JSON.toJSONString(obj));
            return null;
        }

        // 入参

        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("date_begin", date_begin + " 00:00:00");
        paramMap.put("date_end", date_end + " 23:59:59");
        paramMap.put("fgs_code", dept_sn);

        List<HashMap<String, Object>> entityList = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataListByFgsCode(paramMap);

        double all_money = 0d;
        long all_num = 0L;
        String fgs_code = "";
        if (entityList != null && entityList.size() > 0) {
            for (HashMap<String, Object> map : entityList) {
                all_money += getBigDecimal(map.get("ALL_MONEY")).doubleValue();
                all_num += Long.valueOf(map.get("ALL_NUM") + "");
                fgs_code += (String) map.get("DEPT_SN") + ";";
            }
        }

        if (fgs_code.length() > 0) {
            obj.setFgs_code((fgs_code.substring(0, fgs_code.length() - 1)));
        } else {
            obj.setFgs_code("");
        }

        DecimalFormat df = new DecimalFormat("#.##");
        obj.setReturn_error("");
        obj.setAll_money(df.format(all_money));
        obj.setAll_num(df.format(all_num));

        super.renderJson(response, JSON.toJSONString(obj));

        return null;
    }

    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }
}
