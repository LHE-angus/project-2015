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
      <html-el:form action="/admin/ChannelCustomerPaymentAnalysis">
        <html-el:hidden property="method" value="list" />
        <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
         <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
         <td nowrap="nowrap" width="220"><strong class="fb">类别</strong>：
         <html-el:radio property="customer_type" styleId="customer_type_1" value="1"><label for="customer_type_1">单客户</label></html-el:radio>
         <html-el:radio property="customer_type" styleId="customer_type_5" value="5"><label for="customer_type_5">经办</label></html-el:radio>
         <html-el:radio property="customer_type" styleId="customer_type_3" value="3"><label for="customer_type_3">分公司</label></html-el:radio>
         <!-- 
         <html-el:radio property="customer_type" styleId="customer_type_2" value="2"><label for="customer_type_2">客户群</label></html-el:radio>
         <html-el:radio property="customer_type" styleId="customer_type_4" value="4"><label for="customer_type_4">分大区</label></html-el:radio> -->
         </td>
         <td id="keyword_input" style="display:none"> 
         <strong class="fb">R3编码　</strong>：<html-el:text property="keyword" styleId="keyword"></html-el:text></td>
         
         <td id="keyword_select_handle" style="display:none"> 
         <strong class="fb">分公司名称</strong>：
         <html-el:select property="branch_area_name_link" styleId="branch_area_name_link">
         	<html-el:option value="">请选择</html-el:option>
         	<c:forEach items="${BranchList}" var="cur">
         		<html-el:option value="${cur.branch_area_name}">${cur.branch_area_name}</html-el:option>
         	</c:forEach>
         </html-el:select>
         <strong class="fb">经办名称</strong>：
         <html-el:select property="handle_name" styleId="handle_name" value="${af.map.handle_name}">
         	<html-el:option value="">请选择</html-el:option>
         	<c:forEach items="${handleList}" var="cur">
         		<html-el:option value="${cur.handle_name}">${cur.handle_name}</html-el:option>
         	</c:forEach>
         </html-el:select>
         </td>
         <td id="keyword_select_branch" style="display:none"> 
       	 <strong class="fb">分公司名称</strong>：
         <html-el:select property="branch_area_name_select" styleId="branch_area_name_select">
         	<html-el:option value="${af.map.branch_area_name }">请选择</html-el:option>
         	<c:forEach items="${BranchList}" var="cur">
         		<html-el:option value="${cur.branch_area_name}">${cur.branch_area_name}</html-el:option>
         	</c:forEach>
         	</html-el:select>
         </td>
 		  <td>
 		  <input class="but1" type="submit" name="Submit" value="搜索" />
           </td>
        </tr>
         </table>
      </html-el:form>
    </div>
    &nbsp;<input class="but1" type="submit"  value="导出" name="toExcelButton1" onclick="toExcel('divExcel', '?method=toExcel');" />
   
    <div  id="divExcel" title="客户回款统计.xls" class="rtabcont1" style="overflow-x:auto;">
      <%@ include file="/commons/pages/messages.jsp"%>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <!--   <tr class="tabtt1">
            <td align="center"  nowrap="nowrap">序号</td>
            <td align="left"  nowrap="nowrap">名称</td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',1);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款任务</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',2);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款额</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',3);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />当月回款完成率</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',4);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款任务</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',5);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款额</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',6);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />累计回款完成率</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',7);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期任务</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',8);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期回款</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',9);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />同比去年同期增长率</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',10);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期任务累计</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',11);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />去年同期累计回款</span></td>
            <td align="center"	nowrap="nowrap"><span style="cursor:pointer;" onclick="openChartDialog('${af.map.customer_type}',12);" title="点击查看图表"><img src="${ctx}/images/chart-bar-up.png" border="0" width="20" />同比去年累计增长率</span></td>
        </tr> -->
         <tr class="tabtt1">
            <td width="30" align="center"  nowrap="nowrap">序号</td>
            <td align="left"  nowrap="nowrap">名称</td>
            <td align="center"	nowrap="nowrap">当月回款任务</td>
            <td align="center"	nowrap="nowrap">当月回款额</td>
            <td align="center"	nowrap="nowrap">当月回款完成率</td>
            <td align="center"	nowrap="nowrap">累计回款任务</td>
            <td align="center"	nowrap="nowrap">累计回款额</td>
            <td align="center"	nowrap="nowrap">累计回款完成率</td>
            <td align="center"	nowrap="nowrap">去年同期任务</td>
            <td align="center"	nowrap="nowrap">去年同期回款</td>
            <td align="center"	nowrap="nowrap">同比去年同期增长率</td>
            <td align="center"	nowrap="nowrap">去年同期任务累计</td>
            <td align="center"	nowrap="nowrap">去年同期累计回款</td>
            <td align="center"	nowrap="nowrap">同比去年累计增长率</td>
        </tr>
		<c:forEach var="cur" items="${valueList}" varStatus="vs">
		      <tr>
		        <td align="center" nowrap="nowrap">${vs.count}</td>
		        <td align="left" nowrap="nowrap">${cur.task_name}</td>
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_0/cur.value_2_0}" pattern="0.00" />${cur.per_0}</td> 
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_1/cur.value_2_1}" pattern="0.00" />${cur.per_1}</td> 
                <td align="center" nowrap="nowrap" >
                <c:if test="${cur.value_1_0/cur.value_2_0 eq 0.0}"><span style="color: red;">100%</span></c:if>
                <c:if test="${cur.value_1_0/cur.value_2_0 ne 0.0}"><fmt:formatNumber value="${cur.value_1_2/cur.value_2_2}" pattern="0.00" />${cur.per_2}</c:if>
                </td> 
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_3/cur.value_2_3}" pattern="0.00" />${cur.per_3}</td> 
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_4/cur.value_2_4}" pattern="0.00" />${cur.per_4}</td> 
                <td align="center" nowrap="nowrap" >
                <c:if test="${cur.value_1_3/cur.value_2_3 eq 0.0}"><span style="color: red;">100%</span></c:if>
                <c:if test="${cur.value_1_3/cur.value_2_3 ne 0.0}"><fmt:formatNumber value="${cur.value_1_5/cur.value_2_5}" pattern="0.00" />${cur.per_5}</c:if>
                </td> 
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_6/cur.value_2_6}" pattern="0.00" />${cur.per_6}</td> 
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_7/cur.value_2_7}" pattern="0.00" />${cur.per_7}</td> 
                <td align="center" nowrap="nowrap" >
                <c:if test="${cur.value_1_7/cur.value_2_7 eq 0.0}"><span style="color: red;">--</span></c:if>
                <c:if test="${cur.value_1_7/cur.value_2_7 ne 0.0}"><fmt:formatNumber value="${cur.value_1_8/cur.value_2_8}" pattern="0.00" />${cur.per_8}</c:if>
                </td> 
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_9/cur.value_2_9}" pattern="0.00" />${cur.per_9}</td> 
                <td align="center" nowrap="nowrap" ><fmt:formatNumber value="${cur.value_1_10/cur.value_2_10}" pattern="0.00" />${cur.per_10}</td> 
                <td align="center" nowrap="nowrap" >
                <c:if test="${cur.value_1_10/cur.value_2_10 eq 0.0}"><span style="color: red;">--</span></c:if>
                <c:if test="${cur.value_1_10/cur.value_2_10 ne 0.0}"><fmt:formatNumber value="${cur.value_1_11/cur.value_2_11}" pattern="0.00" />${cur.per_11}</c:if>
                
                </td> 
		     </tr>
		     <c:if test="${vs.last eq true}">
					<c:set var="i" value="${vs.count}" />
				</c:if>
		</c:forEach>
		 <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
			<tr align="center">
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
      </table>
</div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelCustomerPaymentAnalysis.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
			pager.addHiddenInputs("type", "${fn:escapeXml(af.map.type)}");	
			pager.addHiddenInputs("keyWord", "${fn:escapeXml(af.map.keyWord)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>

</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>  
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	

	$("#branch_area_name_link").change( function() {
		
		var branch_area_name = $("#branch_area_name_link").val();
		$("#handle_name").empty();

		if(""==branch_area_name){
	   		var opt = new Option( "请选择...",""); 
			$("#handle_name").get(0).options.add(opt);
		   	}
	   	if(branch_area_name!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/ChannelCustomerPaymentAnalysis.do",
				data: "method=getHandleList&branch_area_name=" + $("#branch_area_name_link").val(),
				dataType: "json", 
				error: function(request, settings){
					alert("系统出现错误，请重新操作或联系管理员");
					var opt = new Option( "请选择...",""); 
				    $("#handle_name").get(0).options.add(opt);},
				success: function(data) {
					if (data.length >= 1) {
						var opt = new Option( "请选择...",""); 
						$("#handle_name").get(0).options.add(opt);
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#handle_name").get(0).options.add(opt);
						}
						$("#handle_name").val("${af.map.handle_name}");
					}
				}
			});
	   	}
	});

	<c:if test="${not empty af.map.branch_area_name_link}">
		$("#branch_area_name_link").val("${af.map.branch_area_name_link}");
		$("#branch_area_name_link").change();
		
	</c:if>
	
	<c:if test="${not empty af.map.branch_area_name_select}">
		$("#branch_area_name_select").val("${af.map.branch_area_name_select}");
	</c:if>
	
	<c:if test="${af.map.customer_type eq 1}">
		$("#keyword").attr("dataType", "Require").attr("msg", "请填写");
		$("#branch_area_name_select").attr("dataType", null);
		$("#handle_name").attr("dataType", null);
		$("#customer_type_1").trigger("click");
		$("#keyword_input").show();
		$("#keyword_select_handle").hide();
		$("#keyword_select_branch").hide();
	</c:if>
	<c:if test="${af.map.customer_type eq 3}">
		$("#branch_area_name_select").attr("dataType", "Require").attr("msg", "请选择");
		$("#keyword").attr("dataType", null);
		$("#handle_name").attr("dataType", null);
		$("#customer_type_3").trigger("click");
		$("#keyword_input").hide();
		$("#keyword_select_handle").hide();
		$("#keyword_select_branch").show();
	</c:if>
	<c:if test="${af.map.customer_type eq 5}">
		$("#handle_name").attr("dataType", "Require").attr("msg", "请选择");
		$("#keyword").attr("dataType", null);
		$("#branch_area_name_select").attr("dataType", null);
		$("#customer_type_5").trigger("click");
		$("#keyword_input").hide();
		$("#keyword_select_handle").show();
		$("#keyword_select_branch").hide();
	</c:if>

	 $("#customer_type_1").click(function(){
		 $("#keyword").attr("dataType", "Require").attr("msg", "请填写");
		 $("#branch_area_name_select").attr("dataType", null);
		 $("#handle_name").attr("dataType", null);
		 $("#keyword_input").show();	
		 $("#keyword_select_handle").hide();
		 $("#keyword_select_branch").hide();
		 });
	 $("#customer_type_3").click(function(){
		 $("#branch_area_name_select").attr("dataType", "Require").attr("msg", "请选择");
		 $("#keyword").attr("dataType", null);
		 $("#handle_name").attr("dataType", null);
		 $("#keyword_input").hide();
		 $("#keyword_select_handle").hide();
		 $("#keyword_select_branch").show();
		 });
	 $("#customer_type_5").click(function(){
		 $("#handle_name").attr("dataType", "Require").attr("msg", "请选择");
		 $("#keyword").attr("dataType", null);
		 $("#branch_area_name_select").attr("dataType", null);
		 $("#keyword_input").hide();
		 $("#keyword_select_handle").show();
		 $("#keyword_select_branch").hide();
		 });
	
	
	if ("true" == "${empty valueList}") {
		$("#toExcelButton1").attr("disabled","true");
	}
	$("form").submit(function(){
		   var isSubmit = Validator.Validate(this, 1);	
		   if (isSubmit) {
			   if(e_d.val() < b_d.val()) {
					alert("开始日期必须小于等于结束日期!");
					return false;
			   }
			   
			   $("#submit_loading").show();
		   }
		   return isSubmit;
		});
});
function openChartDialog(customer_type,num){
	var paras = "ChannelCustomerPaymentAnalysis.do?customer_type="+customer_type+"&chart_type="+num+"&method=chart&mod_id=${af.map.mod_id}&time=" + new Date().getTime();
    window.showModalDialog(paras, window, "dialogWidth:800px;status:no;dialogHeight:600px");
}


// var arr_types=["单客户","客户群","分公司","分大区"];
// function changeType(value){
// 		var type_search = document.getElementById("type_search");
// 		type_search.innerHTML = arr_types[parseInt(value) -1];
// }
// var type_search = '${af.map.type}';
// if(type_search ==''){
// 	changeType(1);
// }else{
// 	changeType(type_search);
// }

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>