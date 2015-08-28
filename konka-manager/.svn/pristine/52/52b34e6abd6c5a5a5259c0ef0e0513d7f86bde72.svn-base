<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/StatisticalDimensionSaleArea">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
       	<td width="15"></td>
       	<td>
           <strong class="fb">行政区域：</strong>
           	<select name="province" id="province" style="width:180px;">
                  <option value="">-请选择省/直辖市/自治区-</option>
                </select>
                &nbsp;
                <select name="city" id="city" style="width:100px;">
                  <option value="">-请选择市-</option>
                </select>
                &nbsp;
                <select name="country" id="country" style="width:100px;">
                  <option value="">-请选择县-</option>
                </select>
                &nbsp;
                <select name="town" id="town" style="width:100px;">
                  <option value="">-请选择乡镇-</option>
                </select>
                &nbsp;&nbsp;
           		<input class="but1" type="submit" name="Submit" value="搜索" />
           		<input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='StatisticalDimensionSaleArea.do?method=add_excel&mod_id=${af.map.mod_id}';" />
         </td>
         </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="4%">序号</td>
        <td nowrap="nowrap" align="center" width="5%">省</td>
        <td nowrap="nowrap" align="center" width="5%">市</td>
        <td nowrap="nowrap" align="center" width="5%">县</td>
        <td nowrap="nowrap" align="center" width="5%">镇</td>
        <td nowrap="nowrap" align="center" width="6%">人口</td>
        <td nowrap="nowrap" align="center" width="6%">面积</td>
        <td nowrap="nowrap" align="center" width="6%">GDP</td>
        <td nowrap="nowrap" align="center" width="6%">市场容量</td>
        <td nowrap="nowrap" align="center" width="6%">竞品1销售额</td>
        <td nowrap="nowrap" align="center" width="6%">竞品2销售额</td>
        <td nowrap="nowrap" align="center" width="6%">我品占比</td>
        <td nowrap="nowrap" align="center" width="6%">创维</td>
        <td nowrap="nowrap" align="center" width="6%">海信</td>
        <td nowrap="nowrap" align="center" width="6%">市场均价</td>
        <td nowrap="nowrap" align="center" width="6%">我品均价</td>
        <td nowrap="nowrap" align="center" width="6%">更新时间</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${cur.map.province_name}</td>
            <td align="left">${cur.map.city_name}</td>
            <td align="left">${cur.map.county_name}</td>
            <td align="left">${cur.map.town_name}</td>
            <td align="right">${cur.population}</td>
            <td align="right">${cur.area}</td>
            <td align="right">${cur.gdp}</td>
            <td align="right">${cur.market_size}</td>
            <td align="right">${cur.sale_num1}</td>
            <td align="right">${cur.sale_num2}</td>
            <td align="right">${cur.wpzb}</td>
            <td align="right">${cur.jpzb1}</td>
            <td align="right">${cur.jpzb2}</td>
            <td align="right">${cur.scjj}</td>
            <td align="right">${cur.wpjj}</td>
            <td align="right"><fmt:formatDate value="${cur.update_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="StatisticalDimensionSaleArea.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("town", "${af.map.town}");
				pager.addHiddenInputs("country", "${af.map.country}");
				pager.addHiddenInputs("city", "${af.map.city}");
				pager.addHiddenInputs("province", "${af.map.province}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	//行政区域联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
	//省
	$("#province").change(function(){
		$("#city").val("");
		$("#country").val("");
		$("#town").val("");
	});

	//市
	$("#city").change(function(){
		$("#country").val("");
		$("#town").val("");
	});

	//县
	$("#country").change(function(){
		$("#town").val("");
	});
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
