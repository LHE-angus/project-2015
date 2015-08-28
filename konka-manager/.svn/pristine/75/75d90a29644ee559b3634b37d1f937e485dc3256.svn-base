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
        <html-el:form action="/admin/JStocksVerify">
            <html-el:hidden property="method" value="list"/>
            <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
            <table width="100%" border="0" cellspacing="5" cellpadding="0"
                   class="rtable1">
                <tr>
                    <td width="5%" class="fb" align="right">部门：</td>
                    <td ><html-el:select property="dept_id" styleId="dept_id"
                                        style="width:90px">
                        <html-el:option value="">-请选择-</html-el:option>
                    </html-el:select>
                        <html-el:select property="l4_dept_id" styleId="l4_dept_id"
                                                      style="width:110px;display:none;" >
                        <html-el:option value="">-请选择-</html-el:option>
                    </html-el:select> <html-el:select property="l5_dept_id" styleId="l5_dept_id"
                                                      style="width:110px;display:none;">
                        <html-el:option value="">-请选择-</html-el:option>
                    </html-el:select>
                    </td>
                    <td width="7%" class="fb" align="right">客户名称：</td>
                    <td><html-el:text property="customer_name_like" size="15" maxlength="20"
                                      styleId="customer_name_like"/></td>
                    <td width="7%" class="fb" align="right">R3编码：</td>
                    <td><html-el:text property="r3_code_like" size="15" maxlength="20"
                                      styleId="r3_code_like"/></td>
                    <td width="7%" class="fb" align="right">型号：</td>
                    <td><html-el:text property="goods_name_like" size="15" maxlength="20"
                                      styleId="goods_name_like"/></td>
                    <td width="7%" class="fb" align="right">盘点类型：</td>
                    <td><html-el:select property="hander_type"
                                        styleId="hander_type">
                        <html-el:option value="">请选择</html-el:option>
                        <html-el:option value="0">人工盘点</html-el:option>
                        <html-el:option value="1">系统盘点</html-el:option>
                    </html-el:select>
                    </td>
                </tr>
                <tr>
                    <td width="7%" class="fb" align="right">客户类型：</td>
                    <td>
                        <html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
                            <html-el:option value="">-请选择-</html-el:option>
                        </html-el:select>
                        <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                            <html-el:option value="">-请选择-</html-el:option>
                        </html-el:select>
                    </td>

                    <td width="5%" class="fb" align="right">仓库：</td>
                    <td><html-el:text property="store_name_like" size="15"
                                      maxlength="20" styleId="store_name_like"/></td>

                    <td width="7%" class="fb" align="right">区分仓库</td>
                    <td>
                        <html-el:select property="store_flag" styleId="store_flag">
                            <html-el:option value="1">分仓</html-el:option>
                            <html-el:option value="0">不分仓</html-el:option>
                        </html-el:select>
                    </td>
                    <td align="right" width="7%">
                        <strong class="fb">时间范围：</strong>
                    </td>
                    <td>
                        <html-el:text property="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
                        -
                        <html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
                    </td>
                    <td align="center" colspan="2">
                        <html-el:submit styleClass="but1" value="搜索"/>
                        <logic-el:match name="popedom" value="+128+">
                            <input type="button" value="导出" id="export_23" class="but_excel" style="margin-left: 10px;"
                                   title="导出"/>
                        </logic-el:match>
                    </td> </tr>
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
                <td width="5%" align="center" nowrap="nowrap" >序号</td>

                <td align="center" nowrap="nowrap" >分公司</td>
                <td align="center" nowrap="nowrap" >经办</td>
                <td align="center" nowrap="nowrap" >客户名称</td>
                <td align="center" nowrap="nowrap" >R3编码</td>
                <td align="center" nowrap="nowrap" >渠道类型</td>
                <td align="center" nowrap="nowrap" >拆分类型</td>
                <c:if test="${af.map.store_flag eq 1}">

                <td align="center" nowrap="nowrap" >仓库</td>
                </c:if>
                <td align="center" nowrap="nowrap" >型号</td>
                <td align="center" nowrap="nowrap" >最近30天</td>
                <td align="center" nowrap="nowrap" >最近7天</td>
                <td align="center" nowrap="nowrap" >盘点后数量</td>
                <td align="center" nowrap="nowrap" >盘点后金额</td>
                <td align="center" nowrap="nowrap" >最后盘点时间</td>
                <td align="center" nowrap="nowrap" >盘点类型</td>
                <td align="center" nowrap="nowrap" >盘点明细</td>
  </tr>



            <c:forEach var="cur" items="${entityList}" varStatus="vs">


                <tr>
                    <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
                    <td align="center" nowrap="nowrap">${cur.map.fgs_name}</td>
                    <td align="center" nowrap="nowrap">${cur.map.jb_name}</td>

                    <td align="center" nowrap="nowrap" >${cur.map.customer_name}</td>
                    <td align="center" nowrap="nowrap" >${cur.map.r3_code}</td>
                    <td align="center" nowrap="nowrap" >${cur.map.par_customer_type_name}</td>
                    <td align="center" nowrap="nowrap" >${cur.map.customer_type_name}</td>
                    <c:if test="${af.map.store_flag eq 1}">
                    <td align="center" nowrap="nowrap" >${cur.map.store_name}</td>
                    </c:if>
                    <td align="center" nowrap="nowrap" >${cur.map.goods_name}</td>
                    <td align="center" nowrap="nowrap" >0</td>
                    <td align="center" nowrap="nowrap" >0</td>
                    <td align="right" nowrap="nowrap" >${cur.ver_stocks}</td>
                    <td align="right" nowrap="nowrap" >
                        <font color="red">
                    <fmt:formatNumber type="currency" value="${cur.ver_money}" />
                        </font></td>
                    <td align="center" nowrap="nowrap" >
                    <fmt:formatDate value="${cur.opr_date}" pattern="yyyy-MM-dd hh:mm:ss"/>
                    </td>
                    <td align="center" nowrap="nowrap" >
                        <c:if test="${cur.map.opr_type eq 1}" ><font color="green" >人工盘点</font></c:if>
                        <c:if test="${cur.map.opr_type ne 1}" ><font color="orange" >系统盘点</font></c:if>
                    </td>
                    <td align="center" nowrap="nowrap" ><a  style="cursor: pointer;" onclick="showModels('${cur.c_id}','${cur.store_id}','${cur.goods_id}');">查看明细</a></td>

            </tr>
            </c:forEach>
        </table>
    </div>
    <br/>

    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JStocksVerify.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="40" align="right">
                    <script type="text/javascript"
                            src="${ctx}/commons/scripts/pager.js">;</script>
                    <script
                            type="text/javascript">
                        var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
                        pager.addHiddenInputs("method", "list");
                        pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
                        pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");
                        pager.addHiddenInputs("dept_id", "${fn:escapeXml(af.map.dept_id)}");
                        pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");
                        pager.addHiddenInputs("v_customer_type1", "${fn:escapeXml(af.map.v_customer_type1)}");
                        pager.addHiddenInputs("v_customer_type2", "${fn:escapeXml(af.map.v_customer_type2)}");
                        pager.addHiddenInputs("store_name_like", "${fn:escapeXml(af.map.store_name_like)}");
                        pager.addHiddenInputs("goods_name_like", "${fn:escapeXml(af.map.goods_name_like)}");
                        pager.addHiddenInputs("hander_type", "${fn:escapeXml(af.map.hander_type)}");
                        pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");
                        pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");
                        pager.addHiddenInputs("store_flag", "${fn:escapeXml(af.map.store_flag)}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
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


    $("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
    $("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
    $("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
    $("#v_customer_type1").change();

    $(".iframea").colorbox({iframe: true, width: "60%", height: "480px"});
});


//返回
$("#btn_back").click(function () {
    history.back(-1);
});

//导出
$("#export_23").click(function () {
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
//]]>


//弹出客户型号数据列表
function showModels(c_id,store_id,goods_id){
    $.dialog({
        title:  "盘点详细记录",
        width:  1000,
        height: 500,
        lock:true ,
        zIndex:1979,
        content:"url:JStocksVerify.do?method=showDetails&c_id="+c_id+"&store_id="+store_id+"&goods_id="+goods_id+
        "&hander_type=${af.map.hander_type}&date_begin=${af.map.date_begin}&date_end=${af.map.date_end}"
    });
}


</script>
<jsp:include page="/__analytics.jsp"/>
</body>
</html>