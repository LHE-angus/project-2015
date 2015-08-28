<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="MSThemeCompatible" content="no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=7"/>
    <title>${naviString}</title>
    <link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css"/>
    <link
            href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css"
            rel="stylesheet" type="text/css"/>
    <link
            href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"
            rel="stylesheet" type="text/css"/>
    <link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet"
          type="text/css"/>
    <link href="${ctx}/styles/customer/fancybox/fancybox.css"
          rel="stylesheet" type="text/css"/>
    <link href="${ctx}/styles/colorbox.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .tabtt1 td {
            background-color: #eff;
        }
    </style>
    <style type="text/css">
        select {
            font-family: Microsoft YAHEI;
            font-size: 12px;
        }

        input {
            font-family: Microsoft YAHEI;
            font-size: 12px;
        }

        label {
            cursor: pointer;
        }
    </style>
    <style type="text/css">
        a:link, a:visited {
            text-decoration: none;
            color: #416CE5;
            border-bottom: 1px solid #416CE5;
        }

        h2 {
            font-size: 13px;
            margin: 15px 0 0 0;
        }
    </style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
    <div class="oartop">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="3%"><img
                        src="${ctx}/styles/admin-index/images/k_tup.jpg"
                        style="vertical-align: middle;"/></td>
                <td>当前位置：${naviString}</td>
                <td width="60"><img src="${ctx}/images/manager/help.gif"
                                    style="vertical-align: middle;"/> <span id="span_help"
                                                                            style="cursor: pointer;">帮助</span></td>
            </tr>
        </table>
    </div>
    <div class="rtabcont1">
        <html-el:form action="/admin/JxcFifoStocks">
            <html-el:hidden property="method" value="listAccount"/>
            <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
            <table width="100%" border="0" cellspacing="5" cellpadding="0"
                   class="rtable1">
                <tr>
                    <td width="10%" class="fb" align="right">部门：</td>
                    <td><html-el:select property="dept_id" styleId="dept_id"
                                        style="width:90px">
                        <html-el:option value="">-请选择-</html-el:option>
                    </html-el:select> <html-el:select property="l4_dept_id" styleId="l4_dept_id"
                                                      style="width:110px">
                        <html-el:option value="">-请选择-</html-el:option>
                    </html-el:select> <html-el:select property="l5_dept_id" styleId="l5_dept_id"
                                                      style="width:110px">
                        <html-el:option value="">-请选择-</html-el:option>
                    </html-el:select> <%-- 	          <html-el:select property="handle_name_like_1" styleId="handle_name_like_1" > --%>
                            <%-- 	            <html-el:option value="">-请选择-</html-el:option> --%>
                            <%-- 	          </html-el:select> --%></td>
                    <td width="10%" class="fb" align="right">客户R3编码：</td>
                    <td><html-el:text property="r3_code" size="15" maxlength="20"
                                      styleId="r3_code"/></td>
                        <%--<td width="10%" class="fb" align="right">客户名称：</td>--%>
                        <%--<td><html-el:text property="customer_name_like" size="15"--%>
                        <%--maxlength="20" styleId="customer_name_like" /></td>--%>
                </tr>
                <tr>
                    <!-- 						<td width="10%" class="fb" align="right">出／入库：</td> -->
                        <%-- 						<td><html-el:select property="stock_state" --%>
                        <%-- 								styleId="stock_state" style="width:80px;"> --%>
                        <%-- 								<html-el:option value="">-请选择-</html-el:option> --%>
                        <%-- 								<html-el:option value="10">入仓</html-el:option> --%>
                        <%-- 								<html-el:option value="20">出仓</html-el:option> --%>
                        <%-- 							</html-el:select></td> --%>
                    <td width="10%" class="fb" align="right">产品型号：</td>
                    <td><html-el:text property="goods_name_like" size="15"
                                      maxlength="20" styleId="goods_name_like"/></td>
                    <td align="center" colspan="2">
                        <html-el:checkbox property="group_by_store" value="true">&nbsp;区分仓库</html-el:checkbox>&nbsp;&nbsp;&nbsp;&nbsp;

                        <html-el:submit
                                styleClass="but1" value="搜索"/></td>

                </tr>
            </table>
        </html-el:form>
    </div>
    <div>
        <!--   <input type="button" value="导出" id="export_22" class="but_excel" style="margin-left: 10px;" /> -->

    </div>
    <div class="rtabcont1" style="overflow-x: auto;">
        <div style="text-align: right;">单位：台、元</div>
        <table width="100%" border="0" cellpadding="0" cellspacing="1"
               class="rtable2">
            <tr class="tabtt1">
                <td width="5%" align="center" nowrap="nowrap" rowspan="2">序号</td>
                <c:if test="${group_by_store =='true'}" >
                    <td align="center" nowrap="nowrap" rowspan="2">仓库名称</td>
                </c:if>
                <td align="center" nowrap="nowrap" rowspan="2">型号名称</td>
                <td align="center" nowrap="nowrap" colspan="3">期初</td>
                <td align="center" nowrap="nowrap" colspan="2">进</td>
                <td align="center" nowrap="nowrap" colspan="3">销</td>
                <td align="center" nowrap="nowrap" colspan="2">其他</td>
                <td width="10%" align="center" nowrap="nowrap" rowspan="2">剩余库存量</td>
                <td width="10%" align="center" nowrap="nowrap" rowspan="2">剩余库存金额</td>
            </tr>
            <tr class="tabtt1">
                <td width="10%" align="center" nowrap="nowrap">期初库存</td>
                <td width="10%" align="center" nowrap="nowrap">期初金额</td>
                <td width="10%" align="center" nowrap="nowrap">盘存时间</td>
                <td width="10%" align="center" nowrap="nowrap">进货数量</td>
                <td width="10%" align="center" nowrap="nowrap">进货金额</td>
                <td width="10%" align="center" nowrap="nowrap">销售数量</td>
                <td width="10%" align="center" nowrap="nowrap">销售成本</td>
                <td width="10%" align="center" nowrap="nowrap">销售金额</td>
                <td width="10%" align="center" nowrap="nowrap">其他数量</td>
                <td width="10%" align="center" nowrap="nowrap">其他金额</td>
            </tr>
            <c:set var="s_init_num" value="0"></c:set>
            <c:set var="s_init_money" value="0"></c:set>
            <c:set var="s_come_num" value="0"></c:set>
            <c:set var="s_come_money" value="0"></c:set>
            <c:set var="s_out_num" value="0"></c:set>
            <c:set var="s_sale_cost" value="0"></c:set>
            <c:set var="s_out_money" value="0"></c:set>
            <c:set var="s_other_num" value="0"></c:set>
            <c:set var="s_other_money" value="0"></c:set>
            <c:set var="s_result_money" value="0"></c:set>
            <c:set var="s_result_num" value="0"></c:set>


            <c:forEach var="cur" items="${entityList}" varStatus="vs">

                <c:set var="s_init_num" value="${s_init_num+ cur.map.stock_init_num}"></c:set>
                <c:set var="s_init_money"
                       value="${s_init_money+ cur.map.stock_init_price}"></c:set>
                <c:set var="s_come_num" value="${s_come_num+ cur.stock_in_num}"></c:set>
                <c:set var="s_come_money"
                       value="${s_come_money+ cur.stock_in_price}"></c:set>
                <c:set var="s_out_num" value="${s_out_num+ cur.map.stock_inasout_num+cur.stock_out_num}"></c:set>
                <c:set var="s_sale_cost"
                       value="${s_sale_cost+ cur.map.stock_inasout_cost+cur.map.stock_out_cost}"></c:set>
                <c:set var="s_out_money" value="${s_out_money+ cur.map.stock_inasout_price+cur.stock_out_price}"></c:set>

                <c:set var="s_other_num" value="${s_other_num+ cur.map.stock_other_in_num -cur.map.stock_other_out_num}"></c:set>
                <c:set var="s_other_money" value="${s_other_money+ cur.map.stock_other_in_price-cur.map.stock_other_out_price}"></c:set>

                <c:set var="s_result_money"
                       value="${s_result_money+cur.map.result_cost+cur.map.result_loss_cost}"></c:set>
                <c:set var="s_result_num"
                       value="${s_result_num+ cur.map.result_num+cur.map.result_loss_num}"></c:set>
                <tr>
                    <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
                    <c:if test="${group_by_store =='true'}" >
                        <td nowrap="nowrap" align="center">${cur.map.store_name}</td>
                    </c:if>
                    <td nowrap="nowrap" align="center">${cur.goods_model}</td>
                    <td nowrap="nowrap" align="right">

                        <c:if test="${group_by_store =='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&store_id=${cur.stock_in_store}&goods_name_like=${cur.goods_model}&direction=10">
                                    ${cur.map.stock_init_num }</a>
                        </c:if>
                        <c:if test="${group_by_store !='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&goods_name_like=${cur.goods_model}&direction=10">
                                    ${cur.map.stock_init_num }</a>
                        </c:if>
                    </td>
                    <td nowrap="nowrap" align="right"><span class="kz-price-12">
							<fmt:formatNumber value="${cur.map.stock_init_price }" type="currency" /></span></td>
                    <td nowrap="nowrap" align="center">
                        <fmt:formatDate value="${cur.stock_in_date}" pattern="yyyy-MM-dd" />

                    </td>
                    <td nowrap="nowrap" align="right">
                        <c:if test="${group_by_store =='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&store_id=${cur.stock_in_store}&goods_name_like=${cur.goods_model}&direction=20">
                                    ${cur.stock_in_num}</a>
                        </c:if>
                        <c:if test="${group_by_store !='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&goods_name_like=${cur.goods_model}&direction=20">
                                    ${cur.stock_in_num}</a>
                        </c:if>
                    </td>
                    <td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber
                            value="${cur.stock_in_price}" type="currency" /></span></td>
                    <td nowrap="nowrap" align="right">
                        <c:if test="${group_by_store =='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&store_id=${cur.stock_out_store}&goods_name_like=${cur.goods_model}&direction=30">
                                    ${cur.stock_out_num+cur.map.stock_inasout_num}</a>
                        </c:if>
                        <c:if test="${group_by_store !='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&goods_name_like=${cur.goods_model}&direction=30">
                                    ${cur.stock_out_num+cur.map.stock_inasout_num}</a>
                        </c:if>
                    </td>
                    <td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber
                            value="${cur.map.stock_out_cost+cur.map.stock_inasout_cost}" type="currency" /></span></td>
                    <td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber
                            value="${cur.stock_out_price+cur.map.stock_inasout_price}" type="currency" /></span></td>

                    <td nowrap="nowrap" align="right">
                        <c:if test="${group_by_store =='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&store_id=${cur.stock_out_store}&goods_name_like=${cur.goods_model}&direction=40">
                                    ${ cur.map.stock_other_in_num - cur.map.stock_other_out_num}</a>
                        </c:if>
                        <c:if test="${group_by_store !='true'}" >
                            <a class='iframea' href="${ctx}/customer/manager/JxcFifoStocks.do?method=viewDetails&mod_id=${af.map.mod_id}&goods_name_like=${cur.goods_model}&direction=40">
                                    ${ cur.map.stock_other_in_num - cur.map.stock_other_out_num}</a>
                        </c:if>


                    </td>
                    <td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber
                            value="${ cur.map.stock_other_in_price-cur.map.stock_other_out_price}" type="currency" /></span></td>


                    <td nowrap="nowrap" align="right">
                        <c:if test="${cur.map.result_loss_cost lt 0 }">
                            <span style="color:green;">${cur.map.result_num+cur.map.result_loss_num}</span>
                        </c:if>
                        <c:if test="${cur.map.result_loss_cost eq 0}">
                            ${cur.map.result_num+cur.map.result_loss_num}
                        </c:if>
                    </td>

                    <td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber
                            value="${cur.map.result_cost+cur.map.result_loss_cost}"
                            type="currency" /></span></td>
                </tr>
            </c:forEach>
            <tr>
                <td align="center" nowrap="nowrap" style="font-weight: 800;">合计</td>
                <c:if test="${group_by_store =='true'}">
                    <td align="left" nowrap="nowrap" style="font-weight: 800;"></td>
                </c:if>
                <td align="left" nowrap="nowrap" style="font-weight: 800;"></td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;">${s_init_num}</td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12"><fmt:formatNumber
                        value="${s_init_money}" type="currency" /></span></td>
                <td align="center" nowrap="nowrap" style="font-weight: 800;"></td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;">${s_come_num}</td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12"><fmt:formatNumber
                        value="${s_come_money}" type="currency" /></span></td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;">${s_out_num}</td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12"><fmt:formatNumber
                        value="${s_sale_cost}" type="currency" /></span></td>

                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12"><fmt:formatNumber
                        value="${s_out_money}" type="currency" /></span></td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12"><fmt:formatNumber
                        value="${s_other_num}" type="currency" /></span></td>

                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12"><fmt:formatNumber
                        value="${s_other_money}" type="currency" /></span></td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12">${s_result_num}</span></td>
                <td align="right" nowrap="nowrap" style="font-weight: 800;"><span
                        class="kz-price-12"><fmt:formatNumber
                        value="${s_result_money}" type="currency" /></span></td>
            </tr>
        </table>
    </div>
    <br/>

    <form id="bottomPageForm" name="bottomPageForm" method="post"
          action="JxcFifoStocks.do">
        <table width="98%" border="0" align="center" cellpadding="0"
               cellspacing="0">
            <tr>
                <td height="40" align="right">
                    <script type="text/javascript"
                            src="${ctx}/commons/scripts/pager.js">;</script>
                    <script
                            type="text/javascript">
                        var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
                        pager.addHiddenInputs("method", "listAccount");
                        pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
                        pager.addHiddenInputs("r3_code", "${fn:escapeXml(af.map.r3_code)}");
                        pager.addHiddenInputs("dept_id", "${fn:escapeXml(af.map.dept_id)}");
                        pager.addHiddenInputs("l4_dept_id", "${fn:escapeXml(af.map.l4_dept_id)}");
                        pager.addHiddenInputs("l5_dept_id", "${fn:escapeXml(af.map.l5_dept_id)}");
                        pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");
                        pager.addHiddenInputs("goods_name_like", "${fn:escapeXml(af.map.goods_name_like)}");
                        pager.addHiddenInputs("group_by_store", "${fn:escapeXml(af.map.group_by_store)}");
                        document.write(pager.toString());
                    </script>
                </td>
            </tr>
        </table>
    </form>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function () {
    //客户类型初始化

    $("#dept_id").attr({
        "subElement": "l4_dept_id",
        "defaultText": "-请选择-",
        "defaultValue": "",
        "selectedValue": "${af.map.dept_id}"
    });
    $("#l4_dept_id").attr({
        "subElement": "l5_dept_id",
        "defaultText": "-请选择-",
        "defaultValue": "",
        "selectedValue": "${af.map.l4_dept_id}"
    });
    $("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

    $("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
    $("#dept_id").change();

    $(".iframea").colorbox({iframe: true, width: "60%", height: "480px"});
});


//返回
$("#btn_back").click(function () {
    history.back(-1);
});

//导出
$("#export_22").click(function () {
    if (confirm("提示，您确认导出数据？")) {
        $("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' />");
        $("#bottomPageForm").submit();
    }
});
function setOnlyNum() {
    $(this).css("ime-mode", "disabled");
    $(this).attr("t_value", "");
    $(this).attr("o_value", "");
    $(this).bind("dragenter", function () {
        return false;
    });
    $(this).keypress(function () {
        if (!this.value.match(/^\d*?\d*?$/))this.value = this.t_value; else this.t_value = this.value;
        if (this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value = this.value;
    }).keyup(function () {
        if (!this.value.match(/^\d*?\d*?$/))this.value = this.t_value; else this.t_value = this.value;
        if (this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value = this.value;
    }).blur(function () {
        if (!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value = this.o_value; else {
            if (this.value.match(/^\.\d+$/))this.value = 0 + this.value;
            if (this.value.match(/^$/))this.value = 0;
            this.o_value = this.value
        }
        ;
        if (this.value.length == 0) this.value = "0";
    });
    //this.text.selected;
}
//]]></script>
<jsp:include page="/__analytics.jsp"/>
</body>
</html>