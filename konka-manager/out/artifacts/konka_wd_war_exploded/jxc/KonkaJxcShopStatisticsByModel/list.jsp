<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<title>网点经营情况</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
<div class="rtabcont1">
  <html-el:form action="/KonkaJxcShopStatisticsByModel">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
      <tr>
        <td width="4%" nowrap="nowrap"><strong class="fb">型号:</strong></td>
        <td width="8%"><html-el:hidden property="pd_id_temp" styleId="pd_id_temp"/>
          <html-el:select property="pd_id" styleId="pd_id" style="width:150px;">
            <html-el:option value="">请选择...</html-el:option>
            <c:forEach items="${pePdModelList}" var="cur" varStatus="vs">
              <html-el:option value="${cur.pd_id}">${fn:escapeXml(cur.md_name)}</html-el:option>
            </c:forEach>
          </html-el:select></td>
        <td><html-el:submit property="" value="搜 索" styleClass="bgSearch"/></td>
      </tr>
    </table>
  </html-el:form>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<form id="listForm" name="listForm" method="post" action="KonkaJxcShopStatisticsByModel">
  <div class="rtabcont1" id="divExcel" title="康佳进销存表-按型号统计.xls">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
      <tr>
        <th colspan="16" align="center" style="font-size: +5;">康佳进销存表-按型号统计</th>
      </tr>
      <tr class="title_top">
        <td rowspan="2" align="center"  width="5%">规格</td>
        <td rowspan="2" align="center"  width="10%">机型</td>
        <td colspan="3" align="center"  width="17%">期初库存(${add_date_st})</td>
        <td colspan="3" align="center"  width="15%">本月进货</td>
        <td colspan="3" align="center"  width="15%">出货/零售</td>
        <td colspan="3" align="center"  width="17%">期末库存(${add_date_en})</td>
        <td rowspan="2" align="center"  width="10%">本周销售计划</td>
        <td rowspan="2" align="center"  width="10%">本周备货计划</td>
      </tr>
      <tr class="title_top">
        <td align="center"  width="5%">数量</td>
        <td align="center"  width="7%">进货单价</td>
        <td align="center"  width="5%">金额</td>
        <td align="center"  width="5%">数量</td>
        <td align="center"  width="5%">单价</td>
        <td align="center"  width="5%">金额</td>
        <td align="center"  width="5%">数量</td>
        <td align="center"  width="5%">单价</td>
        <td align="center"  width="5%">金额</td>
        <td align="center"  width="5%">数量</td>
        <td align="center"  width="5%">金额</td>
        <td align="center" width="7%">库存提示</td>
      </tr>
      <c:forEach items="${pdModelPropsValList}" var="cur" varStatus="status">
        <tr>
          <td align="center" width="5%">${cur.prop_value}</td>
          <td colspan="15" align="center" width="95%"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              <c:if test="${empty cur.pdModelPropsValList}">
                
                  <td align="center" width="10%">-</td>
                  <td align="center" width="5%">0</td>
                  <td align="center" width="7%">￥ 0</td>
                  <td align="center" width="5%">￥ 0</td>
                  <td align="center" width="5%">0</td>
                  <td align="center" width="5%">￥ 0</td>
                  <td align="center" width="5%">￥ 0</td>
                  <td align="center" width="5%">0</td>
                  <td align="center" width="5%">￥ 0</td>
                  <td align="center" width="5%">￥ 0</td>
                  <td align="center" width="5%">0</td>
                  <td align="center" width="5%">￥ 0</td>
                  <td align="center" width="7%"><font color=red>*库存为空</font></td>
                  <td align="center" width="10%">&nbsp;</td>
                  <td align="center" width="10%">&nbsp;</td>
              </c:if>
              <c:if test="${not empty cur.pdModelPropsValList}">
                <c:forEach items="${cur.pdModelPropsValList}" var="cur1" varStatus="status">
                  <c:if test="${not empty cur1.map.md_name}">
                    <tr>
                      <td align="center" width="10%">${cur1.map.md_name}</td>
                      <td align="center" width="5%">${empty cur1.map.qcjc_count_all?0:cur1.map.qcjc_count_all}</td>
                      <td align="center" width="7%"><fmt:formatNumber value="${cur1.map.qcjc_price}" var="qcjc_price" type="currency" pattern="0.00"/>
                        ${empty cur1.map.qcjc_price?0:cur1.map.qcjc_price}</td>
                      <td align="center" width="5%"><fmt:formatNumber value="${cur1.map.qcjc_money_all}" var="qcjc_money" type="currency" />
                        ${empty cur1.map.qcjc_money_all?'0.00':qcjc_money}</td>
                      <td align="center" width="5%">${empty cur1.map.rcjh_count_all?0:cur1.map.rcjh_count_all}</td>
                      <td align="center" width="5%"><fmt:formatNumber value="${cur2.map.rcjh_price}" var="rcjh_price" type="currency" />
                        ${empty cur1.map.rcjh_price?0:cur1.map.rcjh_price}</td>
                      <td align="center" width="5%"><fmt:formatNumber value="${cur1.map.rcjh_money_all}" var="jh_sum_money" type="currency" />
                        ${empty cur1.map.rcjh_money_all?'0.00':jh_sum_money}</td>
                      <td align="center" width="5%">${empty cur1.map.rcch_count_all?0:cur1.map.rcch_count_all}</td>
                      <td align="center" width="5%"><fmt:formatNumber value="${cur1.map.rcch_price}" var="rcch_price" type="currency" />
                        ${empty cur1.map.rcch_price?0:cur1.map.rcch_price}</td>
                      <td align="center" width="5%"><fmt:formatNumber value="${cur1.map.rcch_money_all}" var="ch_sum_money" type="currency" />
                        ${empty cur1.map.rcch_money_all?'0.00':ch_sum_money}</td>
                      <td align="center" width="5%"><fmt:formatNumber value="${cur1.map.qmjc_count_all}" pattern="0" /></td>
                      <td align="center" width="5%"><fmt:formatNumber value="${cur1.map.qmjc_money_all}" type="currency" /></td>
                      <td align="center" width="7%"><c:if test="${cur1.map.qmjc_count_all eq 0}"><font color=red>*库存为空</font></c:if>
                        <c:if test="${cur1.map.qmjc_count_all gt 0}"><font color=red>余存【
                          <fmt:formatNumber value="${cur1.map.qmjc_count_all}" pattern="0" />
                          】</font></c:if></td>
                      <td align="center" width="10%">&nbsp;</td>
                      <td align="center" width="10%">&nbsp;</td>
                    </tr>
                  </c:if>
                  <c:if test="${empty cur1.map.md_name}">
                    
                      <td align="center" width="10%">-</td>
                      <td align="center" width="7%">0</td>
                      <td align="center" width="7%">￥ 0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="5%">0</td>
                      <td align="center" width="5%">￥ 0</td>
                      <td align="center" width="7%"><font color=red>*库存为空</font></td>
                      <td align="center" width="10%">&nbsp;</td>
                      <td align="center" width="10%">&nbsp;</td>
                  </c:if>
                </c:forEach>
              </c:if>
            </table></td>
        </tr>
      </c:forEach>
      <tr>
        <td colspan="2" align="center"><font class="redbig" style="color: red;">合计</font></td>
        <td align="center"><fmt:formatNumber value="${af.map.qcjc_count_total}" pattern="0" /></td>
        <td align="center">-</td>
        <td align="center"><fmt:formatNumber value="${af.map.qcjc_money_total}" type="currency" /></td>
        <td align="center"><fmt:formatNumber value="${af.map.rcjh_count_total}" pattern="0" /></td>
        <td align="center">-</td>
        <td align="center"><fmt:formatNumber value="${af.map.rcjh_money_total}" type="currency" /></td>
        <td align="center"><fmt:formatNumber value="${af.map.rcch_count_total}" pattern="0" /></td>
        <td align="center">-</td>
        <td align="center"><fmt:formatNumber value="${af.map.rcch_money_total}" type="currency" /></td>
        <td align="center"><fmt:formatNumber value="${af.map.qmjc_count_total}" pattern="0" /></td>
        <td align="center"><fmt:formatNumber value="${af.map.qmjc_money_total}" type="currency" /></td>
        <td align="center">-</td>
        <td align="center"></td>
        <td align="center"></td>
      </tr>
    </table>
  </div>
</form>
<div class="rtabcont3">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
    <tr>
      <td height="26" align="center"><div class="left">
          <input name="button" type="button" class="bgButtonExport" id="toExcel" value="导出" onclick="toExcel('divExcel', '?method=toExcel');"/>
          &nbsp;
          <input name="button" type="button" class="bgButtonPrint" id="button" value="打印" />
          &nbsp;
          <input name="button" type="button" class="bgButtonBack" id="button" value="返回" onclick="history.back();"/>
        </div></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#pd_id").multiselect({
			selectedList: 1,
			multiple: false,
			minWidth:240
			
		}).multiselectfilter({width:150});                                         
		
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
			var id=$("#pd_id").val();
			if(id != ''){
				$("#pd_id_temp").val(id);
			}
			$("#pd_id_temp").val("");//清空隐藏域临时变量
				f.submit();
	    });  
});   
var pd_id = "${pd_id}";
var param = "pd_id=" + pd_id +"&random=" + Math.random();

$(".bgButtonPrint").click(function(){
	window.showModalDialog("?method=print&" + encodeURI(param), window, "dialogWidth:900px;status:no;dialogHeight:580px");
});
//]]></script>
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>