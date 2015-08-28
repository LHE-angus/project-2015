<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        select {
            font-family: Microsoft YAHEI;
            font-size: 12px;
        }

        input {
            font-family: Microsoft YAHEI;
            font-size: 12px;
        }
    </style>
    <title>库存盘点</title>
    <script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;"/></td>
            <td>当前位置：${naviString}</td>
        </tr>
    </table>
</div>
<html-el:form action="/manager/KonkaStockInventory">
    <html-el:hidden property="method" value="list"/>
    <html-el:hidden property="mod_id" styleId="mod_id"/>
    <html-el:hidden property="cust_id" styleId="cust_id"/>
    <div class="rtabcont1">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
            <tr>
                <td height="36" align="left" style="padding-left:5px;">
                    &nbsp;<strong class="fb">单据编号：</strong>
                    <html-el:text property="bill_sn_like" styleId="bill_sn_like" styleClass="webinput"/>
                    &nbsp;<strong class="fb">业务类型：</strong>
                    <html-el:select property="trade_type" styleId="trade_type" onchange="this.form.submit();">
                        <html-el:option value="">=请选择=</html-el:option>
                        <html-el:option value="30">盘亏</html-el:option>
                        <html-el:option value="31">盘盈</html-el:option>
                        <html-el:option value="0">库实相符</html-el:option>
                    </html-el:select>
                    &nbsp;<strong class="fb">仓库：</strong>
                    <html-el:select property="store_id" styleId="store_id" multiple="multiple">
                        <html-el:option value="">=请选择=</html-el:option>
                        <c:forEach items="${jBaseStoreList}" var="cur">
                            <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                        </c:forEach>
                    </html-el:select>
                    &nbsp;<strong class="fb">商品：</strong>
                    <html-el:select property="goods_id" styleId="goods_id" multiple="multiple">
                        <html-el:option value="">=请选择=</html-el:option>
                        <c:forEach items="${jBaseGoodsList}" var="cur">
                            <html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
                        </c:forEach>
                    </html-el:select>
                    &nbsp;<strong class="fb">盘点日期：</strong>
                    <input type="text" name="opr_date_gt" id="opr_date_gt" class="webinput"
                           value="${af.map.opr_date_gt}" readonly="readonly"
                           onclick="new Calendar(1990, 2020, 0).show(this);"
                           style="cursor:pointer;text-align:center;width:80px;"/>
                    &nbsp;至&nbsp;
                    <input type="text" name="opr_date_lt" id="opr_date_lt" class="webinput"
                           value="${af.map.opr_date_lt}" readonly="readonly"
                           onclick="new Calendar(1990, 2020, 0).show(this);"
                           style="cursor:pointer;text-align:center;width:80px;"/>
                    &nbsp;
                    <input name="button" type="submit" class="bgSearch" id="button" value="搜 索"
                           style="font-family:Microsoft YAHEI;"/>
                </td>
            </tr>
        </table>
    </div>
</html-el:form>
<div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <c:if test="${is_pd eq 0}">
                <td width="50%" align="left" valign="middle"><input name="input" type="button" id="gotoAdd"
                                                                    class="bgButtonAdd" value="盘 点"
                                                                    style="font-family:Microsoft YAHEI;"
                                                                    onclick="location.href='KonkaStockInventory.do?method=add&mod_id=${af.map.mod_id}'"/>
                </td>
            </c:if>
            <td align="right" valign="middle"><a href="JBaseGoodsInitStock.do?method=add&mod_id=${af.map.mod_id}"
                                                 style="color:blue;cursor:pointer;text-decoration:underline;font-weight:800;">库存初始化</a>
            </td>
        </tr>
    </table>
</div>
<div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
            <th width="3%" nowrap="nowrap">序号</th>
            <th width="12%" nowrap="nowrap">单据编号</th>
            <th width="12%" nowrap="nowrap">仓库</th>
            <th width="10%" nowrap="nowrap">商品</th>
            <th width="6%" nowrap="nowrap">盘点前数量</th>
            <th width="8%" nowrap="nowrap">盘点前金额</th>
            <th width="8%" nowrap="nowrap">差异数量</th>
            <th width="8%" nowrap="nowrap">差异金额</th>
            <th width="6%" nowrap="nowrap">盘点后数量</th>
            <th width="8%" nowrap="nowrap">盘点后金额</th>
            <th width="8%" nowrap="nowrap">业务类型</th>
            <th width="12%" nowrap="nowrap">差异原因</th>
            <th width="8%" nowrap="nowrap">盘点时间</th>
            <th width="8%" nowrap="nowrap">操作时间</th>
            <th width="4%" nowrap="nowrap">操作</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
            <tr>
                <td align="center" nowrap="nowrap"
                    bgcolor="#fff2dc">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
                <td align="center" nowrap="nowrap">${cur.bill_sn}</td>
                <td align="left">${cur.map.store_name }</td>
                <td align="left">${cur.map.goods_name }</td>
                <td align="right">${cur.stocks}</td>
                <td align="right"><fmt:formatNumber value="${cur.money}" type="currency"/></td>
                <td align="right">${cur.ver_stocks-cur.stocks}</td>
                <td align="right"><fmt:formatNumber value="${cur.ver_money-cur.money}" type="currency"/></td>
                <td align="right">${cur.ver_stocks}</td>
                <td align="right"><fmt:formatNumber value="${cur.ver_money}" type="currency"/></td>
                <td align="center" nowrap="nowrap">
                    <c:if test="${cur.trade_type eq 30}"><span style="color:#CD0000;">盘亏</span></c:if>
                    <c:if test="${cur.trade_type eq 31}"><span style="color:#009900;">盘盈</span></c:if>
                    <c:if test="${cur.trade_type eq 0}">库实相符</c:if>
                </td>
                <td align="center" nowrap="nowrap">${cur.memo }</td>
                <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.opr_date}"
                                                                   pattern="yyyy/MM/dd HH:mm"/></td>
                <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"
                                                                   pattern="yyyy/MM/dd HH:mm"/></td>
                <td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue"
                                                         onclick="doNeedMethod(null, 'KonkaStockInventory.do', 'view','&mod_id=${af.map.mod_id}&id=${cur.id}&'+$('#bottomPageForm').serialize())">查看明细</span>
                </td>
            </tr>
            <c:if test="${vs.last}">
                <c:forEach begin="1" end="${9 - vs.index}">
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </c:forEach>
            </c:if>
        </c:forEach>
        <c:if test="${empty entityList}">
            <c:forEach begin="0" end="9">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div class="rtabcont3">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaStockInventory.do?method=list">
            <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="40" align="center">
                        <script type="text/javascript">
                            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
                            pager.addHiddenInputs("method", "list");
                            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
                            pager.addHiddenInputs("bill_sn_like", "${af.map.bill_sn_like}");
                            pager.addHiddenInputs("trade_type", "${af.map.trade_type}");
                            pager.addHiddenInputs("store_id", "${af.map.store_id}");
                            pager.addHiddenInputs("goods_id", "${af.map.goods_id}");
                            pager.addHiddenInputs("opr_date_gt", "${af.map.opr_date_gt}");
                            pager.addHiddenInputs("opr_date_lt", "${af.map.opr_date_lt}");
                            document.write(pager.toString());
                        </script>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function () {
// 	<c:forEach items="${entityList}" var="cur" varStatus="vs">
// 		var id = "${cur.id}";
// 		var store_id = "${cur.store_id}";
// 		var goods_id = "${cur.goods_id}";
// 		getStoreAndGoodsInfo(store_id, goods_id, id);
// 		//jError("操作失败：参数丢失，请重试！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
// 	</c:forEach>
    $("#goods_id").multiselect({
        noneSelectedText: '==请选择==',
        selectedList: 1,
        multiple: false,
        minWidth: 150,
        click: function (event, ui) {
            if (ui.value != "") {
                $("#goods_id").val(ui.value);
            }
        }
    }).multiselectfilter();
    $("#store_id").multiselect({
        noneSelectedText: '==请选择==',
        selectedList: 1,
        multiple: false,
        minWidth: 150,
        click: function (event, ui) {
            if (ui.value != "") {
                $("#store_id").val(ui.value);
            }
        }
    }).multiselectfilter();

});

// 
// function getStoreAndGoodsInfo(storeId, goodsId, id){
// 	$.ajax({
// 		type: "POST" , 
// 		url: "KonkaStockInventory.do" , 
// 		data:"method=getStoreAndGoodsInfo&store_id=" + storeId + "&good_id=" + goodsId + "&t=" + new Date(),
// 		dataType: "json" , 
//         async: true,
// //        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
//         success: function (result) {
// 			if (result.state == 1) {
// 				$("#storeTd_" + storeId + "_" + id).empty().text(result.storeName);
// 				$("#goodsTd_" + goodsId + "_" + id).empty().text(result.goodName);
// 			} else {

// 			}
//         }
// 	});
// }
//]]></script>
<jsp:include page="/customer/__analytics.jsp"/>
</body>
</html>