<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/base.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
</head>
<body>
<div align="left" class="nav">${naviString}</div>
<table width="92%" border="0" align="center" cellpadding="0" cellspacing="0" style="border:1px #a7c63c solid; padding-bottom:10px;"">
  <tr>
    <td><ul class="nav" id="navInfo">
        <li id="pd"><a href="#"> 品牌经营情况</a></li>
        <li id="more"><a href="#">更多资料</a></li>
      </ul></td>
  </tr>
  <tr id="pdInfo" class="infoClass">
    <td height="502" align="center" valign="top"><html-el:form action="/admin/BrandGridsAnalysis">
        <html-el:hidden property="method" value="view" />
        <html-el:hidden property="mdas_mod_id" value="${af.map.mdas_mod_id}" />
		<html-el:hidden property="shop_id" value="${af.map.shop_id}" />
        <table width="90%" border="0" cellspacing="0" cellpadding="0" style="margin-top:6px;">
          <tr>
            <td width="110" height="42" align="center"><select name="year" id="year" style="width:100px;">
                <option value="">-请选择年-</option>
              </select></td>
            <td width="370" align="left"><select name="month" id="month" style="width:100px;">
                <option value="">-请选择月-</option>
              </select></td>
              <td width="100">&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
      </html-el:form>
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="30" align="left" style="padding-left:10px;"><c:choose>
              <c:when test="${not empty af.map.month}"> <font class="orange">${shop_name}</font> ${af.map.year}年${af.map.month}月份 </c:when>
              <c:otherwise>
                <c:if test="${not empty af.map.year}"> <font class="orange">${shop_name}</font> ${af.map.year}年 </c:if>
                <c:if test="${empty af.map.year}"> <font class="orange">${shop_name}</font> </c:if>
              </c:otherwise>
            </c:choose>
            ${pd_name}销售总量 <font class="hei">${sumForCount}</font> 台</td>
        </tr>
      </table>
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
      <!--
        <tr>
          <td height="26" colspan="2" align="left"><img src="${ctx}/images/big.gif" id ="showBig" width="119" height="26" style="cursor:pointer; solid #ccc;"/></td>
        </tr>
          -->
        <tr>
          <td width="420" height="174" align="left"><div id="chartdiv"></div>
            <script type="text/javascript">
								var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Pie3D.swf", "ChartId","420", "200", "0", "0");
								chart.setDataUrlParams("shop_id", "${af.map.shop_id}");
								chart.setDataUrlParams("year", "${af.map.year}");
								chart.setDataUrlParams("month", "${af.map.month}");
								chart.setDataUrlParams("mdas_mod_id", "${af.map.mdas_mod_id}");
								chart.setDataURL("BrandGridsAnalysis.do?method=Pie3DForBrandCount");
								//chart.setDataURL("${ctx}/scripts/Charts/test1.xml");
								chart.render("chartdiv");
								</script>
            <div id="chartdivforBig" style="display:none;"></div>
            <script type="text/javascript">
								var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Pie3D.swf", "ChartId","640", "400", "0", "0");
								chart.setDataUrlParams("shop_id", "${af.map.shop_id}");
								chart.setDataUrlParams("year", "${af.map.year}");
								chart.setDataUrlParams("month", "${af.map.month}");
								chart.setDataUrlParams("mdas_mod_id", "${af.map.mdas_mod_id}");
								chart.setDataURL("BrandGridsAnalysis.do?method=Pie3DForBrandCount");
								//chart.setDataURL("${ctx}/scripts/Charts/test1.xml");
								chart.render("chartdivforBig");
								</script></td>
          <td width="152"><ul class="all">
              <c:forEach var="cur" items="${modelCount.baseChartList}">
                <li><font class="orange">${cur.label}:</font> 占总销量<font class="orange">
                  <fmt:formatNumber value="${cur.value/sumForCount}" type="percent"pattern="0.00%" />
                  </font></li>
              </c:forEach>
            </ul></td>
        </tr>
      </table>
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="30" align="left" style="padding-left:10px;"><c:choose>
              <c:when test="${not empty af.map.month}"> <font class="orange">${shop_name}</font> ${af.map.year}年${af.map.month}月份 </c:when>
              <c:otherwise>
                <c:if test="${not empty af.map.year}"> <font class="orange">${shop_name}</font> ${af.map.year}年 </c:if>
                <c:if test="${empty af.map.year}"> <font class="orange">${shop_name}</font> </c:if>
              </c:otherwise>
            </c:choose>
            <fmt:formatNumber value="${sumForAmount}" type="currency" var="sumMoney"></fmt:formatNumber>
            ${pd_name}销售总额 <font class="hei">
            <c:out value="${sumMoney}"/>
            </font> 元 </td>
        </tr>
      </table>
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
      <!--
        <tr>
          <td height="26" colspan="2" align="left"><img src="${ctx}/images/big.gif" id ="showBigforBrand" width="119" height="26" style="cursor:pointer; solid #ccc;"/></td>
        </tr>
          -->
        <tr>
          <td width="420" height="174"><div id="chartdivforbrand"></div>
            <script type="text/javascript">
								var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Pie3D.swf", "ChartId","420", "200", "0", "0");
								chart.setDataUrlParams("shop_id", "${af.map.shop_id}");
								chart.setDataUrlParams("year", "${af.map.year}");
								chart.setDataUrlParams("month", "${af.map.month}");
								chart.setDataUrlParams("mdas_mod_id", "${af.map.mdas_mod_id}");
								chart.setDataURL("BrandGridsAnalysis.do?method=Pie3DForBrand");
								//chart.setDataURL("${ctx}/scripts/Charts/test1.xml");
								chart.render("chartdivforbrand");
							   </script>
            <div id="chartdivforbrandforBig" style="display:none;"></div>
            <script type="text/javascript">
								var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Pie3D.swf", "ChartId","640", "400", "0", "0");
								chart.setDataUrlParams("shop_id", "${af.map.shop_id}");
								chart.setDataUrlParams("year", "${af.map.year}");
								chart.setDataUrlParams("month", "${af.map.month}");
								chart.setDataUrlParams("mdas_mod_id", "${af.map.mdas_mod_id}");
								chart.setDataURL("BrandGridsAnalysis.do?method=Pie3DForBrand");
								//chart.setDataURL("${ctx}/scripts/Charts/test1.xml");
								chart.render("chartdivforbrandforBig");
							   </script></td>
          <td width="152"><ul class="all">
              <c:forEach var="cur" items="${modelAmount.baseChartList}">
                <li><font class="orange">${cur.label}</font> 占总销额<font class="orange">
                  <fmt:formatNumber value="${cur.value/sumForAmount}" type="percent" pattern="0.00%" />
                  </font></li>
              </c:forEach>
            </ul></td>
        </tr>
      </table></td>
  </tr>
  <tr id="moreInfo" style="display:none;" class="infoClass">
    <td height="397" align="center" valign="top">
       <table width="90%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
          <td height="24" align="center" style="border-bottom:1px #a7c63c solid;">
            <ul class="allone" id="moreUlSon">
              <li id="base" class="alloneSpan"><a href="#">基础信息</a></li>
              <li id="scope"><a href="#">经营范围</a></li>
            </ul>
          </td>
        </tr>
      </table>
      <!--  基础信息  -->
      <table width="90%" id="baseInfoSon" class="infoSonClass" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
        <tr>
          <td width="76" height="26" align="right"><font class="gran">企业名称：</font></td>
          <td width="369" height="26" align="left">${entity.entp_name }</td>
        </tr>
        <tr>
          <td width="120" height="26" align="right"><font class="gran">所属区域：</font></td>
          <td height="26" align="left">${p_name }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">所属行业：</font></td>
          <td height="26" align="left">${entity.c_index }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">主营产品：</font></td>
          <td height="26" align="left">${entity.main_pd }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">企业性质：</font></td>
          <td height="26" align="left"><c:choose>
              <c:when test="${entity.entp_kind eq 0}">默认</c:when>
              <c:when test="${entity.entp_kind eq 1}">国企</c:when>
              <c:when test="${entity.entp_kind eq 2}">私企</c:when>
              <c:when test="${entity.entp_kind eq 3}">个体</c:when>
              <c:when test="${entity.entp_kind eq 4}">外企</c:when>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">经营规模：</font></td>
          <td height="26" align="left">${entity.entp_size }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">营业执照：</font></td>
          <td height="26" align="left">${entity.entp_licence }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">联 系 人：</font></td>
          <td height="26" align="left">${entity.linkman }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">联系电话：</font></td>
          <td height="26" align="left">${entity.tel }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">联系地址：</font></td>
          <td height="26" align="left">${entity.addr }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</font></td>
          <td height="26" align="left">${entity.postcode }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">邮&nbsp;&nbsp;&nbsp;&nbsp;箱：</font></td>
          <td height="26" align="left">${entity.email }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">网&nbsp;&nbsp;&nbsp;&nbsp;址：</font></td>
          <td height="26" align="left">${entity.www }</td>
        </tr>
        <tr>
          <td height="26" align="right"><font class="gran">备&nbsp;&nbsp;&nbsp;&nbsp;注：</font></td>
          <td height="26" align="left"><c:choose>
              <c:when test="${entity.is_record eq 0}">未备案</c:when>
              <c:when test="${entity.is_record eq 1}">备案通过</c:when>
              <c:when test="${entity.is_record eq 2}">备案未通过</c:when>
            </c:choose>
          </td>
        </tr>
      </table>
      <!-- 经营范围 -->
      <div id="scopeInfoSon" class="infoSonClass" style="display: none;">
        <html-el:form action="/admin/BrandGridsAnalysis">
          <html-el:hidden property="method" value="view" />
          <html-el:hidden property="mdas_mod_id" value="${af.map.mdas_mod_id}" />
		  <html-el:hidden property="shop_id" value="${af.map.shop_id}" />
          <table width="90%"  border="0" cellspacing="0" cellpadding="0" style="margin-top:6px;">
            <tr>
              <td width="110" height="42" align="center"><select name="year_son" id="year_son" style="width:100px;">
                  <option value="">-请选择年-</option>
                </select></td>
              <td width="370" align="left"><select name="month_son" id="month_son" style="width:100px;">
                  <option value="">-请选择月-</option>
                </select>
                &nbsp;&nbsp;
                <html-el:submit value="快速搜索" /></td>
            </tr>
          </table>
        </html-el:form>
        <table width="90%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="30" align="center" style="padding-left:10px;"><c:choose>
                <c:when test="${not empty af.map.month_son}"> <font class="orange">${shop_name}</font> ${af.map.year_son}年${af.map.month_son}月份 </c:when>
                <c:otherwise>
                  <c:if test="${not empty af.map.year_son}"> <font class="orange">${shop_name}</font> ${af.map.year_son}年 </c:if>
                  <c:if test="${empty af.map.year_son}"> <font class="orange">${shop_name}</font> </c:if>
                </c:otherwise>
              </c:choose>
              <fmt:formatNumber value="${sumForAmountWithPd}" type="currency" var="sumMoney"></fmt:formatNumber>
              销售总额 <font class="hei">
              <c:out value="${sumMoney}"/>
              </font> 元 </td>
          </tr>
        </table>
        <table width="90%" border="0" cellspacing="0" cellpadding="0">
        <!--
          <tr>
            <td height="26" colspan="2" align="left"><img src="${ctx}/images/big.gif" id ="chartdivForMoreBigBtn" width="119" height="26" style="cursor:pointer; solid #ccc;"/></td>
          </tr>
            -->
          <tr>
            <td width="420" height="174"><div id="chartdivForMore"></div>
              <script type="text/javascript">
								var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Pie3D.swf", "ChartId","420", "200", "0", "0");
								chart.setDataUrlParams("shop_id", "${af.map.shop_id}");
								chart.setDataUrlParams("year_son", "${af.map.year_son}");
								chart.setDataUrlParams("month_son", "${af.map.month_son}");
								chart.setDataUrlParams("mdas_mod_id", "${af.map.mdas_mod_id}");
								chart.setDataURL("BrandGridsAnalysis.do?method=Pie3D");
								chart.render("chartdivForMore");
							   </script>
              <div id="chartdivForMoreBig" style="display:none;"></div>
              <script type="text/javascript">
								var chart = new FusionCharts("${ctx}/manager/scripts/Charts/Pie3D.swf", "ChartId","640", "400", "0", "0");
								chart.setDataUrlParams("shop_id", "${af.map.shop_id}");
								chart.setDataUrlParams("year_son", "${af.map.year_son}");
								chart.setDataUrlParams("month_son", "${af.map.month_son}");
								chart.setDataUrlParams("mdas_mod_id", "${af.map.mdas_mod_id}");
								chart.setDataURL("BrandGridsAnalysis.do?method=Pie3D");
								chart.render("chartdivForMoreBig");
							   </script></td>
            <td width="152"><ul class="all">
                <c:forEach var="cur" items="${model.baseChartList}">
                  <li><font class="orange">${cur.label}</font> 占总销额<font class="orange">
                    <fmt:formatNumber value="${cur.value/sumForAmountWithPd}" type="percent" pattern="0.00%" />
                    </font></li>
                </c:forEach>
              </ul></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function() {
$("#year").attr({"subElement": "month", "defaultText": "-请选择年-", "defaultValue": "", "selectedValue": "${af.map.year}", "datatype": "Require", "msg": "请选择年"});
$("#month" ).attr({"defaultText": "-请选择月-", "defaultValue": "", "selectedValue": "${af.map.month}"});
$("#year").cs("${ctx}/manager/admin/BrandGridsAnalysis.do?method=getDateForSelectList&mdas_mod_id=${af.map.mdas_mod_id}", "year", "0", false);
$("#year").change();

$("#year_son").attr({"subElement": "month_son", "defaultText": "-请选择年-", "defaultValue": "", "selectedValue": "${af.map.year_son}", "datatype": "Require", "msg": "请选择年"});
$("#month_son" ).attr({"defaultText": "-请选择月-", "defaultValue": "", "selectedValue": "${af.map.month_son}"});
$("#year_son").cs("${ctx}/manager/admin/BrandGridsAnalysis.do?method=getDateForSelectList&mdas_mod_id=${af.map.mdas_mod_id}", "year_son", "0", false);
$("#year_son").change();

/*
var dialogWidth = 665;

$("#showBig").click(function(){
	$("#chartdiv").hide();
	$("#chartdivforbrand").hide();
	$("#year").hide();
	$("#month").hide();
	//alert($("#chartdivforbrandBig").dialog({width: dialogWidth}).html());
	$("#chartdivforBig").dialog( {
		width: dialogWidth,
		title: '品牌经营情况对比图',
		close: function() {$("#chartdiv").show();$("#chartdivforbrand").show();$("#year").show();$("#month").show();},
		buttons : {"关闭" : function() {$(this).dialog("close");$("#chartdiv").show();$("#chartdivforbrand").show();$("#year").show();$("#month").show();}},
		modal: true
	}).dialog("open");
});

$("#showBigforBrand").click(function(){
	$("#chartdiv").hide();
	$("#chartdivforbrand").hide();
	$("#year").hide();
	$("#month").hide();
	//alert($("#chartdivforbrandBig").dialog({width: dialogWidth}).html());
	$("#chartdivforbrandforBig").dialog( {
		width: dialogWidth,
		title: '品牌经营情况对比图',
		close: function() {$("#chartdiv").show();$("#chartdivforbrand").show();$("#year").show();$("#month").show();},
		buttons : {"关闭" : function() {$(this).dialog("close");$("#chartdiv").show();$("#chartdivforbrand").show();$("#year").show();$("#month").show();}},
		modal: true
	}).dialog("open");
});

$("#chartdivForMoreBigBtn").click(function(){
	$("#chartdivForMore").hide();
	$("#year_son").hide();
	$("#month_son").hide();
	$("#chartdivForMoreBig").dialog( {
		width: dialogWidth,
		title: '大类经营情况对比图',
		close: function() {$("#chartdivForMore").show();$("#year_son").show();$("#month_son").show();},
		buttons : {"关闭" : function() {$(this).dialog("close");$("#chartdivForMore").show();$("#year_son").show();$("#month_son").show();}},
		modal: true
	}).dialog("open");
});
*/

$("li", "#navInfo").each(function(){
	$(this).click(function(){
		var id = $(this).attr("id");
		if("" != id) {
			$(".infoClass").hide();
			$("#" + id + "Info").show();
		}
	});
});

if ("" != "${af.map.year_son}") {
	$(".infoClass").hide();
	$("#moreInfo").show();
	$(".infoSonClass").hide();
	$("#scopeInfoSon").show();
}

$("li", "#moreUlSon").each(function(){
	$(this).click(function(){
		var id = $(this).attr("id");
		if("" != id) {
			$(".infoSonClass").hide();
			$("#" + id + "InfoSon").show();
		}
		$(".alloneSpan").removeClass("alloneSpan");
		$(this).addClass("alloneSpan");
	}).mouseover(function(){
		$(this).addClass("alloneSpan");
	}).mouseout(function(){$(this).removeClass("alloneSpan");});
	//onmouseover="" onmouseout=""
});

});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
