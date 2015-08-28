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
    <html-el:form action="/admin/StatisticalDimensionArea">
      <html-el:hidden property="method" value="listForCustomer" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
           <strong class="fb">年度：</strong>
           <html-el:select property="year" styleId="year">
           		<c:forEach items="${yearList}" var="cur" varStatus="vs">
           			<html-el:option value="${cur}">${cur}</html-el:option>
           		</c:forEach>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">月度：</strong>
           <html-el:select property="month" styleId="month">
           	  <html-el:option value="1">1</html-el:option>
              <html-el:option value="2">2</html-el:option>
              <html-el:option value="3">3</html-el:option>
              <html-el:option value="4">4</html-el:option>
              <html-el:option value="5">5</html-el:option>
              <html-el:option value="6">6</html-el:option>
              <html-el:option value="7">7</html-el:option>
              <html-el:option value="8">8</html-el:option>
              <html-el:option value="9">9</html-el:option>
              <html-el:option value="10">10</html-el:option>
              <html-el:option value="11">11</html-el:option>
              <html-el:option value="12">12</html-el:option>
           	</html-el:select>
           &nbsp;&nbsp;
           <strong class="fb">分公司：</strong>
           	<html-el:select property="fgs_id" styleId="fgs_id" style="width:100px">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${sybDeptInfoList}">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
         </td>
        </tr>
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
         </td>
         </tr>
        <tr>
       	<td width="15"></td>
       	<td>
           <strong class="fb">我品占比：</strong>
           	<html-el:text property="wpzb_l" styleId="wpzb_l" maxlength="10" size="5" styleClass="webinput" />-
           	<html-el:text property="wpzb_g" styleId="wpzb_g" maxlength="10" size="5" styleClass="webinput" />
           &nbsp;&nbsp;
           <strong class="fb">零售额：</strong>
           	<html-el:text property="month_retail_money_l" styleId="month_retail_money_l" maxlength="10" size="5" styleClass="webinput" />-
           	<html-el:text property="month_retail_money_g" styleId="month_retail_money_g" maxlength="10" size="5" styleClass="webinput" />
           &nbsp;&nbsp;
           <strong class="fb">零售均价：</strong>
           	<html-el:text property="retail_price_l" styleId="retail_price_l" maxlength="10" size="5" styleClass="webinput" />-
           	<html-el:text property="retail_price_g" styleId="retail_price_g" maxlength="10" size="5" styleClass="webinput" />
           &nbsp;&nbsp;
           <input class="but1" type="submit" name="Submit" id="submit" value="搜索" />
            &nbsp;&nbsp;
           <input class="but_excel" type="button" name="export_excel"
						    id="export_excel" value="导出" />
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
        <td nowrap="nowrap" align="center" width="6%">我品占比</td>
        <td nowrap="nowrap" align="center" width="6%">创维</td>
        <td nowrap="nowrap" align="center" width="6%">海信</td>
        <td nowrap="nowrap" align="center" width="6%">客户数</td>
        <td nowrap="nowrap" align="center" width="6%">网点数</td>
        <td nowrap="nowrap" align="center" width="6%">门店数</td>
        <td nowrap="nowrap" align="center" width="6%">业务员人数</td>
        <td nowrap="nowrap" align="center" width="6%">促销员人数</td>
        <td nowrap="nowrap" align="center" width="6%">零售额</td>
        <td nowrap="nowrap" align="center" width="6%">零售量</td>
        <td nowrap="nowrap" align="center" width="6%">零售均价</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${cur.province_name}</td>
            <td align="left">${cur.city_name}</td>
            <td align="left">${cur.county_name}</td>
            <td align="left">${cur.town_name}</td>
            <td align="right">${cur.map.population}</td>
            <td align="right">${cur.map.area}</td>
            <td align="right">${cur.map.gdp}</td>
            <td align="right">${cur.map.market_size}</td>
            <td align="right">${cur.map.wpzb}</td>
            <td align="right">${cur.map.jpzb1}</td>
            <td align="right">${cur.map.jpzb2}</td>
            <td align="right">${cur.map.customer_num}</td>
            <td align="right">${cur.map.agent_num}</td>
            <td align="right">${cur.map.store_num}</td>
            <td align="right">${cur.map.ywy_num}</td>
            <td align="right">${cur.map.promoter_num}</td>
            <td align="right"><fmt:formatNumber value="${cur.map.month_retail_money}"  type="currency"/></td>
            <td align="right">${cur.map.month_retail_num}</td>
            <td align="right"><fmt:formatNumber value="${cur.map.retail_price}"  type="currency"/> </td>
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="StatisticalDimensionArea.do">
    <input id='export_id' style="display: none" name='excel_all'
					value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "listForCustomer");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("year", "${af.map.year}");
				pager.addHiddenInputs("month", "${af.map.month}");
				pager.addHiddenInputs("town", "${af.map.town}");
				pager.addHiddenInputs("country", "${af.map.country}");
				pager.addHiddenInputs("city", "${af.map.city}");
				pager.addHiddenInputs("province", "${af.map.province}");
				pager.addHiddenInputs("fgs_id", "${af.map.fgs_id}");
				pager.addHiddenInputs("wpzb_l", "${af.map.wpzb_l}");
				pager.addHiddenInputs("wpzb_g", "${af.map.wpzb_g}");
				pager.addHiddenInputs("month_retail_money_l", "${af.map.month_retail_money_l}");
				pager.addHiddenInputs("month_retail_money_g", "${af.map.month_retail_money_g}");
				pager.addHiddenInputs("retail_price_l", "${af.map.retail_price_l}");
				pager.addHiddenInputs("retail_price_g", "${af.map.retail_price_g}");
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
<script type="text/javascript"
	src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"
	src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript"
	src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
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

	//导出
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
			//CNZZ统计代码
			//_czc.push(["_trackEvent", "区域客户统计表下载", "导出", "Excel", 0, "export_excel"]);
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});

	//判断月份是否超出当前月份
	$("#submit").click(function(){
		var myDate = new Date();
		var nowyear = myDate.getFullYear();
		var nowmonth = myDate.getMonth() + 1;
		var month = $("#month").val();
		var year = $("#year").val();
		if(year == nowyear){
			if(month > nowmonth){
				alert("选择的月份不能大于当前月份!");
				return null;
				}
		}
	});
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
