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
<div class="oarcont" >
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
    <div class="rtabcont1">
      <html-el:form action="/admin/TaskPara">
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
    <div class="rtabcont2" style="overflow-x:auto;">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td nowrap="nowrap" align="center">序号</td>
          <td nowrap="nowrap">名称</td>	
          <td align="center" nowrap="nowrap" width="5%">年度</td>	
          <td align="center" nowrap="nowrap" width="3%">1</td>	
          <td align="center" nowrap="nowrap" width="3%">2</td>	
          <td align="center" nowrap="nowrap" width="3%">3</td>	
          <td align="center" nowrap="nowrap" width="3%">4</td>	
          <td align="center" nowrap="nowrap" width="3%">5</td>	
          <td align="center" nowrap="nowrap" width="3%">6</td>	
          <td align="center" nowrap="nowrap" width="3%">7</td>	
          <td align="center" nowrap="nowrap" width="3%">8</td>	
          <td align="center" nowrap="nowrap" width="3%">9</td>	
          <td align="center" nowrap="nowrap" width="3%">10</td>	
          <td align="center" nowrap="nowrap" width="3%">11</td>	
          <td align="center" nowrap="nowrap" width="3%">12</td>
          <td align="center" nowrap="nowrap" width="8%">年度任务</td>
          <td align="center" nowrap="nowrap" width="8%">年度系数</td>	
          <td align="center" nowrap="nowrap" width="10%">操作</td>
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<c:forEach var="_cur" items="${cur.taskParaList}" varStatus="_vs">
		        <tr>
		     	<c:if test="${_vs.first eq true}">
		            <td rowspan="2" align="center">${vs.count}</td>
		            <td rowspan="2" nowrap="nowrap">${_cur.task_name}</td>
		        </c:if>
		            <td align="center" nowrap="nowrap" height="28">${_cur.year}</td> 
		            <td align="right" nowrap="nowrap">${_cur.column_1}</td>            	    	
		            <td align="right" nowrap="nowrap">${_cur.column_2}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_3}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_4}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_5}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_6}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_7}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_8}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_9}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_10}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_11}</td>
		            <td align="right" nowrap="nowrap">${_cur.column_12}</td> 
		            <td align="right" nowrap="nowrap">${_cur.year_task}</td>
		            <td align="right" nowrap="nowrap">${_cur.year_para}</td>
		            <td align="center" nowrap="nowrap">    
		               <span style="cursor:pointer; margin:0 7px 0 7px;" onclick="confirmUpdate(null, 'TaskPara.do', 'id=${_cur.id}&task_id=${cur.map.id}&year=${_cur.year}&customer_type=${af.map.customer_type}&' + $('#bottomPageForm').serialize())">修改</span>
		            </td>        		
		        </tr>
			</c:forEach>
		</c:forEach>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="TaskPara.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}")
			pager.addHiddenInputs("type", "${fn:escapeXml(af.map.type)}");	
			pager.addHiddenInputs("keyWord", "${fn:escapeXml(af.map.keyWord)}");	
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
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
					}
				}
			});
	   	}
	});

	<c:if test="${not empty af.map.branch_area_name_link}">
		$("#branch_area_name_link").val("${af.map.branch_area_name_link}");
		$("#branch_area_name_link").change();
		$("#handle_name").val("${af.map.handle_name}");
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