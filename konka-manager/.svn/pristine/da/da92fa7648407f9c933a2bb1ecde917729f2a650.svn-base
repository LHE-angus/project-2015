<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" >
    <html-el:form action="admin/KonkaRealStock.do">
      <html-el:hidden property="method" styleId="method" value="list"/>
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td nowrap="nowrap" width="240"><strong class="fb">类别</strong>：
            <html-el:radio property="customer_type" styleId="customer_type_1" value="1">
              <label for="customer_type_1">单客户</label>
            </html-el:radio>
            <html-el:radio property="customer_type" styleId="customer_type_5" value="5">
              <label for="customer_type_5">经办</label>
            </html-el:radio>
            <html-el:radio property="customer_type" styleId="customer_type_3" value="3">
              <label for="customer_type_3">分公司</label>
            </html-el:radio></td>
          <td style="text-align: left;" width="460">
            <div id="handle_select" style="display:none"><strong class="fb">分公司</strong>：
              <html-el:select property="branch_area_name_link" styleId="branch_area_name_link">
                <html-el:option value="">请选择</html-el:option>
                <c:forEach items="${deptList}" var="cur">
                  <html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
              <strong class="fb">经办名称</strong>：
              <html-el:select property="handle_name" styleId="handle_name">
                <html-el:option value="">请选择</html-el:option>
                <c:forEach items="${handleList}" var="cur">
                  <html-el:option value="${cur.handle_name}">${cur.handle_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </div>
            <div id="branch_select" style="display:none"><strong class="fb">分公司</strong>：
              <html-el:select property="branch_name" styleId="branch_name">
                <html-el:option value="">请选择</html-el:option>
                <c:forEach items="${deptList}" var="cur">
                  <html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </div></td>
          <td></td>
        </tr>
        <tr >
          <td colspan="2"><div id="pd_select" style="display:none" ><strong class="fb">型号</strong>：
              <html-el:text property="pd_names" styleId="pd_names" size="90" onblur="getPdIds(this.value);"/>
              <img onclick="getPePdModel();" src="${ctx}/commons/icons/icon010a2.gif" style='cursor: pointer;vertical-align:middle;' alt='选择产品' title="选择产品" /> 
              <html-el:hidden property="pd_ids" styleId="pd_ids" />
            </div>
            <div id="keyword_input" style="display:none"><strong class="fb">R3编码</strong>：
              <html-el:text property="keyword" styleId="keyword" size="10"></html-el:text>
              <strong class="fb">R3网点名称</strong>：
              <html-el:text property="customer_name_like" styleId="customer_name_like"></html-el:text>
              <strong class="fb">R3合并编码</strong>：
              <html-el:text property="customer_code" styleId="customer_code"></html-el:text>
            </div>
            </td>
          <td><input name="button" type="button" class="bgSearch" id="button" value="搜 索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" >
    <form id="listForm" name="listForm" method="post" action="KonkaRealStock.do">
      <c:if test="${not empty r3StList }">
        <input type="submit"value="导出">
        </input>
      </c:if>
      <input type="hidden" name="method" id="method" value="download" />
      <input type="hidden" name="name" id="name" value="${name}" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <span style="color:red">注：考虑页面的布局问题，查询时只能选择最多50个型号的产品！如果按R3网点名称查询，请尽量填写完整的名称，方便查询所需的网点信息。</span>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <c:if test="${not empty pePdModelList}">
          <tr class="title_top">
            <td nowrap="nowrap" width="40">序号</td>
            <td nowrap="nowrap">办事处</td>
            <td nowrap="nowrap">客户名称</td>
            <c:forEach items="${pePdModelList}" var="cur">
              <td nowrap="nowrap">${cur.md_name}</td>
            </c:forEach>
            <td nowrap="nowrap">合计</td>
          </tr>
        </c:if>
        <c:if test="${not empty r3StList}">
        <td align="center" colspan="3">合计</td>
        <c:set var="allTotle" value="0"/>
         <c:forEach items="${totleList}" var="t"  varStatus="vs">
          <td nowrap="nowrap">${t}</td>
         </c:forEach>
         <c:forEach items="${totleList}" var="t"  varStatus="vs">
          <c:set var="allTotle" value="${allTotle+t}"/>
         </c:forEach>
        <td>${allTotle}</td>
        </c:if>
        <c:forEach items="${r3StList}" var="s"  varStatus="vs">
              <c:set var="totle" value="0"/>
          <tr>
            <td nowrap="nowrap" align="center">${vs.count}</td>
            <td nowrap="nowrap">${s.handle_name}</td>
            <td nowrap="nowrap">${s.customer_name}</td>
            <c:forEach items="${s.map.list}" var="l">
              <td>${l.stock_count}</td>
              <c:set var="totle" value="${totle+l.stock_count}"/>
            </c:forEach>
            <td nowrap="nowrap">${totle}</td>
          </tr>
        </c:forEach>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
//	getCountTotal(); //纵向
   $("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
    
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
				url: "${ctx}/manager/admin/KonkaRealStock.do",
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
						$("#handle_name").val("${handle_name}");
					}
				}
			});
	   	}
	});

	<c:if test="${not empty af.map.branch_area_name_link}">
		$("#branch_area_name_link").val("${af.map.branch_area_name_link}");
		$("#branch_area_name_link").change();
		
	</c:if>
	
	<c:if test="${not empty af.map.branch_name}">
		$("#branch_name").val("${af.map.branch_name}");
	</c:if>

	<c:if test="${af.map.customer_type eq 1}">
	$("#customer_type_1").trigger("click");
	$("#keyword_input").show();
	</c:if>
	<c:if test="${af.map.customer_type eq 3}">
	$("#branch_select").show();
	$("#pd_select").show();
	$("#customer_type_3").trigger("click");
	$("#branch_name").attr("dataType", "Require").attr("msg", "请选择");
	$("#pd_ids").attr("dataType", "Require").attr("msg","请选择产品型号");
	</c:if>
	<c:if test="${af.map.customer_type eq 5}">
	$("#handle_select").show();
	$("#pd_select").show();
	$("#customer_type_5").trigger("click");
	$("#pd_ids").attr("dataType", "Require").attr("msg","请选择产品型号");
	$("#handle_name").attr("dataType", "Require").attr("msg", "请选择");
	</c:if>

	 $("#customer_type_1").click(function(){
		 $("#handle_name").attr("dataType", null);
		 $("#branch_name").attr("dataType", null);
		 $("#pd_ids").attr("dataType", null);
		 $("#keyword_input").show();
		 $("#branch_select").hide();
		 $("#handle_select").hide();
		 $("#pd_select").hide();
		 });
	 $("#customer_type_3").click(function(){
		 $("#keyword_input").hide();
		 $("#handle_select").hide();
		 $("#branch_select").show();
		 $("#pd_select").show();
		 $("#handle_name").attr("dataType", null);
		 $("#branch_name").attr("dataType", "Require").attr("msg", "请选择");
		 $("#pd_ids").attr("dataType", "Require").attr("msg","请选择产品型号");
		 $("#handle_name").val("");
		 $("#keyword").val("");
		 $("#customer_name_like").val("");
		 });
	 $("#customer_type_5").click(function(){
		 $("#handle_name").attr("dataType", "Require").attr("msg", "请选择");
		 $("#pd_ids").attr("dataType", "Require").attr("msg","请选择产品型号");
		 $("#branch_name").attr("dataType", null);
		 $("#keyword_input").hide();
		 $("#handle_select").show();
		 $("#pd_select").show();
		 $("#branch_select").hide();
		 $("#keyword").val("");
		 $("#customer_name_like").val("");
		 });

	$("#button").click(function(){
		if(Validator.Validate(this.form, 3)){
			this.form.submit();
		}
	});
});

function getPePdModel() { 
	var returnValue = window.showModalDialog("SelectPePdModel.do?selectype=mutiple&selects=" + $("#pd_ids").val() + "&rowCount=50&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#pd_ids").val(returnValue.ids);
		$("#pd_names").val(returnValue.names);
	}
}

function getPdIds(Pdnames){
	if(Pdnames!=""){
	$.ajax({
		type: "POST",
		cache: false,
		url: "${ctx}/manager/admin/KonkaRealStock.do",
		data: "method=getPdIds&Pd_names=" + Pdnames,
		dataType: "json", 
		error: function(request, settings){
			alert("系统出现错误，请重新操作或联系管理员");
		},
		success: function(data) {
			if(data[0].msg)
			alert(data[0].msg);
			$("#pd_ids").val(data[0].pd_ids);
		}
	});
	}else {
		$("#pd_ids").val("");
	}
}
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>