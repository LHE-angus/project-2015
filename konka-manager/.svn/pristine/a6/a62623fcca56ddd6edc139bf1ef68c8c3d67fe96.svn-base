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
<style type="text/css">
	.rtable1 td {
		padding:2px 5px;   
		
	}
	
	.but11{width:130px;height:30px;background-color: #FFCC00;border: 1px;text-align:center;cursor:pointer; -moz-border-radius: 2px; -webkit-border-radius: 2px; }
	
</style>
</head>
<body >
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/ChannelDataImport">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="is_kh" value="${af.map.is_kh}"/>
      <html-el:hidden property="is_first" value="1"/>
      <html-el:hidden property="current_fgs_code" styleId="current_fgs_code" value="${current_fgs_code}" />
      
      <table width="100%" border="0" cellpadding="0" class="rtable1">
       	<tr>
       	  <td width="10%" valign="middle" nowrap="nowrap" class="title_item" align="right">分公司：</td>
	   	  <td>
	   		<html-el:select property="dept_id" styleId="dept_id" style="width:100px;" disabled="${show_fgs}">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
	   	  </td>
          <td align="right">
          	<strong class="fb">创建日期：</strong>
          </td>
          <td>
          	<html-el:text property="s_date" styleId="s_date" size="7" maxlength="10" readonly="true" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);"  ></html-el:text>
                                至
            <html-el:text property="e_date" styleId="e_date" size="7"  maxlength="10" readonly="true" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" ></html-el:text>
          </td>
          <td align="right">
          	<strong class="fb">凭证日期：</strong>
          </td>
          <td>
            <html-el:text property="startDate1" styleId="startDate1" size="7" maxlength="10" readonly="true" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);"  ></html-el:text>
		           至
		    <html-el:text property="endDate1" styleId="endDate1" size="7"  maxlength="10" readonly="true" styleClass="webinput"  onclick="new Calendar(2011, 2031, 0).show(this);" ></html-el:text>
          </td>
        </tr>
        <tr>
        	<td align="right">
          		<strong class="fb">销售组织：</strong>
          	</td>
          	<td>
            	<html-el:text property="salesCode" styleId="salesCode"/>
          	</td>
	        <td align="right">
	        	<strong class="fb">售达方：</strong>
	        </td>
	        <td>
	            <html-el:text property="keyword" styleId="keyword"/>
	        </td>
	        <td align="right">
	        	<strong class="fb">售达方名称：</strong>
	        </td>
	        <td>
	        	<html-el:text property="customer_name_like" styleId="customer_name_like"  />
	        </td>
	   </tr>
	   <tr>
	   		<td align="right">
	        	<strong class="fb">订单号：</strong>
	        </td>
	        <td>
	        	<html-el:text property="column_8_like" styleId="column_8_like" maxlength="20"   />
	        </td>
	        <td align="right">
	        	<strong class="fb">订单类型：</strong>
	        </td>
	        <td>
	        	<html-el:text property="column_9" styleId="column_9" maxlength="5" />
	        </td>
	        <td align="right">
	        	<strong class="fb">型&nbsp;&nbsp;&nbsp;号：</strong>
	        </td>
	        <td>
	        	<html-el:text property="column_11" styleId="column_11" />
	        </td>
        </tr>
	   <tr>
	   		<td align="right">
	        	<strong class="fb">区域：</strong>
	        </td>
	        <td>
	        	<html-el:select property="area_name" styleId="area_name">
	              <html-el:option value="">—请选择—</html-el:option>
	              <html-el:option value="华东">华东</html-el:option>
	              <html-el:option value="山东">山东</html-el:option>
	              <html-el:option value="东北">东北</html-el:option>
	              <html-el:option value="华北">华北</html-el:option>
	              <html-el:option value="华南">华南</html-el:option>
	              <html-el:option value="西南">西南</html-el:option>
	              <html-el:option value="华中">华中</html-el:option>
	              <html-el:option value="西北">西北</html-el:option>
	            </html-el:select>
	        </td>
	        <td colspan="3"></td>
	        <td>
	        	<html-el:submit styleClass="but1" value="搜索" />
	        </td>
        </tr>
      </table>
    </html-el:form>
  </div>
   <div class="rtabcont2" id="tb_div">
    <input type="button" id="export_excel" value="导出数据" style="cursor: pointer;" class="but11"></input>&nbsp;&nbsp;
    <input type="button" style="cursor: pointer;" value="同步当天数据" class="but11" onclick="doSyncOrder(1);"></input>&nbsp;&nbsp;
   	<input type="button" style="cursor: pointer;" value="同步30天数据" class="but11" onclick="doSyncOrder(30);"></input>&nbsp;&nbsp;
   	<input type="button" style="cursor: pointer;" value="同步所有数据" class="but11" onclick="doSyncOrder(300);"></input>&nbsp;&nbsp;
	<font color="#696969">我们建议同步数据操作还是由系统自动完成!</font>
	
	
  <logic-el:match name="popedom" value="+1+">
      <input type="button" style="cursor: pointer;" onclick="doNeedMethod(null, 'ChannelDataImport.do','edit' ,$('#bottomPageForm').serialize())" value="导入数据"></input>
      <input type="button" style="cursor: pointer;" onclick="doNeedMethod(null, 'ChannelDataImport.do','logList' ,'&mod_id=${af.map.mod_id}&tree_param=${tree_param}' + $('#bottomPageForm').serialize())" value="查看日志"></input>
  </logic-el:match>
  <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
    当前页合计：数量：<c:out value="${pagerCount}"/>&nbsp;&nbsp;金额： <c:out value="${pagerMoney}"/>&nbsp;&nbsp;&nbsp;&nbsp;查询结果合计：数量： <c:out value="${allCount}"/>&nbsp;&nbsp;金额：<c:out value="${allMoney}"/>
    <div style="overflow-x:auto;"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="30" align="center" nowrap="nowrap">序号</td>
        <td align="center" nowrap="nowrap">销售组织</td>
        <td align="center" nowrap="nowrap">售达方</td>
        <td align="center" nowrap="nowrap">名称(售)</td>
        <td align="center" nowrap="nowrap">送达方</td>
        <td align="center" nowrap="nowrap">名称（送）</td>
        <td align="center" nowrap="nowrap">创建日期</td>
        <td align="center" nowrap="nowrap">凭证日期</td>
        <td align="center" nowrap="nowrap">订单号</td>
        <td align="center" nowrap="nowrap">订单类型</td>
        <td align="center" nowrap="nowrap">项目</td>
        <td align="center" nowrap="nowrap">机型</td>
        <td align="center" nowrap="nowrap">订单数量</td>
        <td align="center" nowrap="nowrap">单价（含税）</td>
        <td align="center" nowrap="nowrap">总金额（含税）</td>
        <td align="center" nowrap="nowrap">交货单数量</td>
        <td align="center" nowrap="nowrap">已发货数量</td>
        <td align="center" nowrap="nowrap">开发票数量</td>
        <td align="center" nowrap="nowrap">K007（含税）</td>
        <td align="center" nowrap="nowrap">总净值金额(含税)</td>
        <td align="center" nowrap="nowrap">客户物料号</td>
        <td align="center" nowrap="nowrap">办事处</td>
        
        <logic-el:match name="popedom" value="+4+"><td align="center" nowrap="nowrap">操作</td></logic-el:match>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr> 
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.column_25}</td>
          <td align="left" nowrap="nowrap">${cur.column_1}</td>
       	  <td align="left" nowrap="nowrap">${cur.map.column_04}</td>
          <td align="left" nowrap="nowrap">${cur.column_5}</td>
          <td align="left" nowrap="nowrap">${cur.map.column_06}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.column_7}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.column_26}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap">${cur.column_8}</td>
          <td align="center" nowrap="nowrap">${cur.column_9}</td>
          <td align="center" nowrap="nowrap">${cur.column_10}</td>
          <td align="center" nowrap="nowrap">${cur.column_11}</td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_27}" pattern="#,###"/></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_13}" pattern="#,##0.00"/></td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_30}" pattern="#,###"/></td>
		  <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_27}" pattern="#,###"/></td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_28}" pattern="#,###"/></td>
          <td align=right nowrap="nowrap"><fmt:formatNumber value="${cur.column_29}" pattern="#,###"/></td>
          <td align="center" nowrap="nowrap">${cur.column_15}</td>
          <td align="center" nowrap="nowrap">${cur.column_17}</td>
          <td align="center" nowrap="nowrap">${cur.column_23}</td>
          <td align="center" nowrap="nowrap">${cur.column_24}</td>
          <logic-el:match name="popedom" value="+4+"><td align="center" nowrap="nowrap"><span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'ChannelDataImport.do', 'id=${cur.id}&column_8=${cur.column_8}&'+$('#bottomPageForm').serialize())">删除</span></td></logic-el:match>
        </tr>
      </c:forEach>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ChannelDataImport.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="60" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");	
			pager.addHiddenInputs("salesCode", "${fn:escapeXml(af.map.salesCode)}");	
			pager.addHiddenInputs("keyword", "${fn:escapeXml(af.map.keyword)}");	
            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
            pager.addHiddenInputs("column_9", "${af.map.column_9}");
            pager.addHiddenInputs("column_8_like", "${af.map.column_8_like}");
            pager.addHiddenInputs("s_date", "${af.map.s_date}");
            pager.addHiddenInputs("e_date", "${af.map.e_date}");
            pager.addHiddenInputs("startDate1", "${af.map.startDate1}");
            pager.addHiddenInputs("endDate1", "${af.map.endDate1}");
            pager.addHiddenInputs("is_kh", "${af.map.is_kh}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("column_11", "${af.map.column_11}");
            pager.addHiddenInputs("area_name", "${af.map.area_name}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
    </div>
  </div>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="销售明细">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
          <td>序号</td>
          <td>销售组织</td>
		  <td>售达方</td>
		  <td>名称(售)</td>
		  <td>送达方</td>
		  <td>名称（送）</td>
		  <td>创建日期</td>
		  <td>凭证日期</td>
		  <td>订单号</td>
		  <td>订单类型</td>
		  <td>项目</td>
		  <td>机型</td>
		  <td>订单数量</td>
		  <td>单价（含税）</td>
		  <td>总金额（含税）</td>
		  <td>交货单数量</td>
		  <td>已发货数量</td>
		  <td>开发票数量</td>
		  <td>K007（含税）</td>
		  <td>总净值金额(含税)</td>
		  <td>客户物料号</td>
		  <td>办事处</td>
      </tr>
      <c:forEach var="cur" items="${entityList1}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td>${cur.column_25}</td>
          <td>${cur.column_1}</td>
       	  <td>${cur.map.column_04}</td>
          <td>${cur.column_5}</td>
          <td>${cur.map.column_06}</td>
          <td><fmt:formatDate value="${cur.column_7}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td><fmt:formatDate value="${cur.column_26}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td>${cur.column_8}</td>
          <td>${cur.column_9}</td>
          <td>${cur.column_10}</td>
          <td>${cur.column_11}</td>
          <td><fmt:formatNumber value="${cur.column_27}" pattern="#,###"/></td>
          <td><fmt:formatNumber value="${cur.column_13}" pattern="#,##0.00"/></td>
          <td><fmt:formatNumber value="${cur.column_30}" pattern="#,###"/></td>
		  <td><fmt:formatNumber value="${cur.column_27}" pattern="#,###"/></td>
          <td><fmt:formatNumber value="${cur.column_28}" pattern="#,###"/></td>
          <td><fmt:formatNumber value="${cur.column_29}" pattern="#,###"/></td>
          <td>${cur.column_15}</td>
          <td>${cur.column_17}</td>
          <td>${cur.column_23}</td>
          <td>${cur.column_24}</td>
        </tr>
      </c:forEach>
    </table>
  </div>

</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">

var is_kh = '${af.map.is_kh}';
if(is_kh == 1){
	$("#keyword").attr("disabled","disabled");
	$("#tb_div").hide();
}	
  
$(document).ready(function(){
	String.prototype.isPath=function() { return /[^\n\r\t]*\.[x|X][l|L][s|S]/i.test(this); };
	$("#btn_submit").click(function(){
		//var path = f.excel.value;
		var path = $("#excel").val();
	    if (path == "" || !path.isPath()) {
	        alert("请选择一个有效Excel文件!");
	        return false;
	    };
	    
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "导入中").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		};
	});	
	
	
	 // 导出excel
    $("#export_excel").click(function(){
    	//CNZZ统计代码
    	$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' id='excel_all' />");
		$("#bottomPageForm").submit();
		$("#excel_all").val("");
    });
    
    //初始化分公司列表
	$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	
	//总部以外的用户，默认所属分公司且不可更改
	var fgs = $("#current_fgs_code").val();
	if(fgs!=""){
		$("#dept_id").val(fgs);
		$("#dept_id").change();
	}
});


function doSyncMethod(msg, page, method, queryString) {
	if(msg != null) {
		if(!confirm(msg))
			return false;
	}
	page = page || "?";
	page = page.indexOf("?") != -1 ? page : (page + "?");
	location.href = page  + "method=" + method + "&" + encodeURI(queryString);
	return true;
} ;


function doSyncOrder(days){
	var pass = window.prompt("请输入同步密码!","");
	if(pass ==null || pass==""){
		return ;
	}
	if(days==1){
		if(pass !="123"){
			alert("ooops,密码不对,猜到了开头却猜不到结局。");
			return ;
		};
	}else{
		if(pass !="951"){
			alert("ooops,密码不对,猜到了开头却猜不到结局。");
			return ;
		};
	}
	//
	var isExecute = "";
	if(days<=30){
		isExecute = doSyncMethod("我们强烈建议订单明细数据同步交由系统自动完成,你仍然坚持使用手工同步的方式来更新订单明细信息吗?", 'ChannelDataImport.do','ZbSync','&days='+days ,$('#bottomPageForm').serialize());
	}else{
		//后台判断,如果大于30天,将重置所有数据
		isExecute = doSyncMethod("此操作用于重置数据,执行后所有订单数据会被清空,然后同步所有数据,确认操作?", 'ChannelDataImport.do','ZbSync','&days='+days ,$('#bottomPageForm').serialize());
	}
	if( isExecute == true){
		$(".but11").attr("disabled","disabled");
		$("font").text("数据同步中,请勿刷新页面!").css("color","red");
	}
	
};


</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
