package com.ebiz.mmt.web.struts.manager.admin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.org.apache.commons.lang3.StringUtils;

public class HandOperateAction extends BaseAction {
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        super.setNaviStringToRequestScope(form, request);
        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == userInfo) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }

        if ("超级管理员".equals(userInfo.getUser_name())) {
            request.setAttribute("is_superUser", true);
        }
        return mapping.findForward("list");

    }

    /**
     * @param 1 同步回款 2同步账期 3 同步集采数据 4 同步客户产品数据
     * @param form
     * @param request
     * @param response
     * @return null
     * @throws Exception
     */
    public ActionForward Operate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String type = (String) dynaBean.get("type");
        String r3_code = (String) dynaBean.get("r3_code");
        PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == userInfo) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }

        if (StringUtils.isNotBlank(type) && GenericValidator.isInt(type)) {
            int typeId = Integer.parseInt(type);
            switch (typeId) {
                case 1:
                    super.getFacade().getAutoRunService().createKonkaR3BackMoney();// 同步回款
                    break;
                case 2:
                    super.getFacade().getAutoRunService().createKonkaR3ShopCredit();// 同步账期
                    break;
                case 3:
                    super.getFacade().getAutoRunService().autoSyncKonkaZles23ResultInfo();// 同步集采数据
                    break;
                case 4:
                    super.getFacade().getAutoRunService().syncR3OrderToCustPd();// 同步客户产品数据
                    break;
                case 5:
                    super.getFacade().getAutoRunService().autoSendMail();// 邮件发送
                    break;
                case 6:
                    super.getFacade().getAutoRunService().syncAutoRunUpdateStatementForDataInsertAndUpdate();// 统计数据-年度
                    break;
                case 7:
                    super.getFacade().getAutoRunService().syncAutoRunUpdateStatementForDataMonthInsertAndUpdate();// 统计数据-月度
                    break;

                case 8:
                    if (StringUtils.isNotEmpty(r3_code)) {
                        HashMap map = new HashMap();
                        map.put("I_R3_CODE", r3_code);
                        super.getFacade().getJBaseStoreService().clearStockUseR3code(map);
                    } else {
                        super.renderJavaScript(response, "alert('请输入需要清空的R3编码')");
                        return null;
                    }
                    break;

                case 9:
                    super.getFacade().getAutoRunService().syncAutoRunUpdateStatementForInsertJStocksVerify();// 自动结转客户库存
                    break;
                case 10:
                    super.getFacade().getAutoRunService().autoSyncStatisticalDimensionRetailData();// 自动初始化统计基础数据-零售
                    break;
                case 11:
                    super.getFacade().getAutoRunService().autoSyncStatisticalDimensionArea();// 执行初始化统计省市县镇的基础数据，初始化统计分析区域数据
                    break;
                case 12:
                    super.getFacade().getAutoRunService().autoSyncStatisticalDimensionKhfgs();// 执行初始化客户分公司的基础数据
                    break;
                case 13:
                    super.getFacade().getAutoRunService().autoSyncStatisticalDimensionStore();// 执行初始化客户门店的基础数据
                    break;
                case 14:
                    super.getFacade().getAutoRunService().autoSyncStatisticalDimensionTimeDay();// 执行初始化统计的时间的基础数据
                    break;
                case 15:
                    super.getFacade().getAutoRunService().autoSyncStatisticalDimensionMd();// 执行初始化统计的商品的基础数据
                    break;
                case 16:
                    super.getFacade().getAutoRunService().autoYsncDRP();
                    break;
                case 17:
                    super.getFacade().getAutoRunService().autoKonkaFifoStockInDc();
                    break;

            }

        }
        saveMessage(request, "entity.inserted");

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append(mapping.findForward("success").getPath());
        // pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        // pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end
        return forward;

    }
    // public ActionForward test(ActionMapping mapping, ActionForm form,
    // HttpServletRequest request, HttpServletResponse response)
    // throws Exception {
    // super.getFacade().getIosPushMessageService().SendMessage("催办订单",
    // "客户待办理的订单催办", 2,
    // "http://qdgl.konka.com/manager/admin/KonkaOrderSearch.do?method=view&order_id=",
    // "客户待办理的订单催办");
    // JPushClient jpush =
    // new JPushClient("884c9820c69c4e1e38c33e29",
    // "e96161383b912c9ac5e61ec1",true,3);
    // Map map = new HashMap();
    // map.put("url", "www.baidu.com");
    // map.put("type", "2");
    // PushPayload puShpayLoad =
    // PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.all())
    // .setMessage(Message.newBuilder().setMsgContent("客户待办理的订单催办").build()).setNotification(
    // Notification.ios("nihao", map)).build();
    // PushPayload puShpayLoad1 =
    // PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.alias("17229"))
    // .setMessage(Message.newBuilder().setMsgContent("客户待办理的订单催办").build()).setNotification(
    // Notification.ios("nihao", map)).build();
    // try {
    // PushResult result=jpush.sendPush(puShpayLoad);
    // PushResult result1=jpush.sendPush(puShpayLoad1);
    //
    //
    // logger.info("Got result - " + result);
    // logger.info("Got result - " + result1);
    // } catch (APIConnectionException e) {
    // logger.error("Connection error. Should retry later. ", e);
    //
    // } catch (APIRequestException e) {
    // logger.error("Error response from JPush server. Should review and fix it. ",
    // e);
    // logger.info("HTTP Status: " + e.getStatus());
    // logger.info("Error Code: " + e.getErrorCode());
    // logger.info("Error Message: " + e.getErrorMessage());
    // logger.info("Msg ID: " + e.getMsgId());
    // }
    //
    //
    //
    //
    // saveMessage(request, "entity.inserted");
    //
    // StringBuffer pathBuffer = new StringBuffer();
    // pathBuffer.append(mapping.findForward("success").getPath());
    // // pathBuffer.append("&mod_id=" + mod_id);
    // pathBuffer.append("&");
    // //
    // pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
    // ActionForward forward = new ActionForward(pathBuffer.toString(), true);
    // // end
    // return forward;
    // }

}
