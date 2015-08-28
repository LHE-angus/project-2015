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
  <div class="rtabcont2" >
    <html-el:form action="/admin/KonkaR3Backmoney.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="list"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <html-el:hidden property="is_kh" styleId="is_kh" value="${af.map.is_kh}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
        <tr>
        	<td width="10%" valign="middle" nowrap="nowrap" class="title_item" align="right">
        		<strong class="fb">年份：</strong>
        	</td>
        	<td>
	        	<html-el:select property="year" styleId="year" style="width:80px;">
	            	<html-el:option value="">-请选择-</html-el:option>
	                <c:forEach items="${yearList}" var="cur">
	                  	<html-el:option value="${cur}">${cur}</html-el:option>
	                </c:forEach>
	            </html-el:select>
        	</td>
        	<td width="10%" valign="middle" nowrap="nowrap" class="title_item" align="right">
        		<strong class="fb">R3编码：</strong>
        	</td>
        	<td>
	            <html-el:text property="r3_code" styleId="r3_code" maxlength="10" />
        	</td>
        	<td width="10%" valign="middle" nowrap="nowrap" class="title_item" align="right">客户类型：</td>
		    <td >
       		 	<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
		   	</td>
        </tr>
        <tr>
        	<td width="10%" valign="middle" nowrap="nowrap" class="title_item" align="right">分公司：</td>
		   	<td>
		   		<html-el:select property="dept_id" styleId="dept_id" style="width:100px;">
                	<html-el:option value="">-请选择-</html-el:option>
              	</html-el:select>
		   	</td>
        	<td width="10%" valign="middle" nowrap="nowrap" class="title_item" align="right">客户名称：</td>
		   	<td>
		   		<html-el:text property="kh_name_like" styleId="kh_name_like" />
		   	</td>
        	<td>
            	<input class="but1" type="submit" name="Submit" value="搜索" />
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
   <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2" id="r3moneyTb">
   <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" /> 
   <c:if test="${call_r3_interface}"> </c:if> 
  	<logic-el:match name="popedom" value="+1+">
  	<input type="button" style="cursor: pointer;" class="but8" id="syncBtn" value="同步数据"></input>
  	<font color="#696969">我们建议同步数据操作由系统自动完成!</font>
  	 <!-- 
  	 <input style="cursor: pointer;" type="button" class="but9" onclick="doNeedMethod('是否同步', 'KonkaR3Backmoney.do','tbData' ,'mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())" value="数据同步" />
       <input type="button" onclick="doNeedMethod(null, 'KonkaR3Backmoney.do','edit' ,$('#bottomPageForm').serialize())" value="导入数据"></input>
      <input type="button" onclick="doNeedMethod(null, 'KonkaR3Backmoney.do','logList' ,'&mod_id=${af.map.mod_id}&tree_param=${tree_param}' + $('#bottomPageForm').serialize())" value="查看日志"></input>
       -->
  	</logic-el:match>
  	<span style="float:right;color: red;">提示：汇款金额的单位为万元！ </span>
  </div>
  <div class="rtabcont2">
  <div style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td align="center" nowrap="nowrap">序号</td>        
        <td align="center" nowrap="nowrap">分公司</td>
        <td align="center" nowrap="nowrap">经办名称</td>
        <td align="center" nowrap="nowrap">客户类型</td>
        <td align="center" nowrap="nowrap">客户细分类型</td>
        <td align="center" nowrap="nowrap">区域</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">R3编码</td>
        <td align="center" nowrap="nowrap">年份</td>        
        <td align="center" nowrap="nowrap">1月</td>
        <td align="center" nowrap="nowrap">2月</td>
        <td align="center" nowrap="nowrap">3月</td>
        <td align="center" nowrap="nowrap">4月</td>
        <td align="center" nowrap="nowrap">5月</td>
        <td align="center" nowrap="nowrap">6月</td>
        <td align="center" nowrap="nowrap">7月</td>
        <td align="center" nowrap="nowrap">8月</td>
        <td align="center" nowrap="nowrap">9月</td>
        <td align="center" nowrap="nowrap">10月</td>
        <td align="center" nowrap="nowrap">11月</td>
        <td align="center" nowrap="nowrap">12月</td>
        
        <!-- <td align="center" nowrap="nowrap">操作</td> -->
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.branch_area_name}</td>
          <td align="left" nowrap="nowrap">${cur.handle_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.area_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>    
          <td align="center" nowrap="nowrap">${cur.year}</td>

          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_1}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_2}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_3}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_4}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_5}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_6}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_7}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_8}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_9}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_10}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_11}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_12}" pattern="#0.00" /></td>
          <!-- <td align="right" nowrap="nowrap"><logic-el:match name="popedom" value="+4+"><span style="cursor:pointer;" class="fblue" onclick="confirmDelete(null, 'KonkaR3Backmoney.do', 'id=${cur.id}&r3_code=${cur.r3_code}&'+$('#bottomPageForm').serialize())">删除</span></logic-el:match></td> -->
        </tr>
      </c:forEach>
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3Backmoney.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");	
			pager.addHiddenInputs("keyWord", "${fn:escapeXml(af.map.keyWord)}");	
            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
            pager.addHiddenInputs("is_kh", "${af.map.is_kh}");
            pager.addHiddenInputs("year", "${af.map.year}");
            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("kh_name_like", "${af.map.kh_name_like}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
    <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="客户回款">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
     <tr class="tabtt1">
        <td align="center" nowrap="nowrap">序号</td>        
        <td align="center" nowrap="nowrap">分公司</td>
        <td align="center" nowrap="nowrap">经办名称</td>
        <td align="center" nowrap="nowrap">客户类型</td>
        <td align="center" nowrap="nowrap">客户细分类型</td>
        <td align="center" nowrap="nowrap">区域</td>
        <td align="center" nowrap="nowrap">客户名称</td>
        <td align="center" nowrap="nowrap">R3编码</td>
        <td align="center" nowrap="nowrap">年份</td>        
        <td align="center" nowrap="nowrap">1月</td>
        <td align="center" nowrap="nowrap">2月</td>
        <td align="center" nowrap="nowrap">3月</td>
        <td align="center" nowrap="nowrap">4月</td>
        <td align="center" nowrap="nowrap">5月</td>
        <td align="center" nowrap="nowrap">6月</td>
        <td align="center" nowrap="nowrap">7月</td>
        <td align="center" nowrap="nowrap">8月</td>
        <td align="center" nowrap="nowrap">9月</td>
        <td align="center" nowrap="nowrap">10月</td>
        <td align="center" nowrap="nowrap">11月</td>
        <td align="center" nowrap="nowrap">12月</td>
      </tr>
      <c:forEach var="cur" items="${allList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.branch_area_name}</td>
          <td align="left" nowrap="nowrap">${cur.handle_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_comm}</td>
          <td align="left" nowrap="nowrap">${cur.map.c_name}</td>
          <td align="left" nowrap="nowrap">${cur.area_name}</td>
          <td align="left" nowrap="nowrap">${cur.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>    
          <td align="center" nowrap="nowrap">${cur.year}</td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_1}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_2}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_3}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_4}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_5}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_6}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_7}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_8}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_9}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_10}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_11}" pattern="#0.00" /></td>
          <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.column_12}" pattern="#0.00" /></td>
        </tr>
      </c:forEach>
    </table>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){

	var is_kh = '${af.map.is_kh}';
	if(is_kh == 1){
		$("#r3_code").attr("disabled","disabled");
		$("#is_kh").append("<input type='hidden' name='r3_code' value='"+'${af.map.r3_code}'+"' />");
		$("#r3moneyTb").hide();
	}
	
	var f = document.forms["af"];
	String.prototype.isPath=function() { return /[^\n\r\t]*\.[x|X][l|L][s|S]/i.test(this); };
	$("#btn_submit").click(function(){
		//var path = f.excel.value;
		var path = $("#excel").val();
	    if (path == "" || !path.isPath()) {
	        alert("请选择一个有效Excel文件!");
	        return false;
	    }
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "导入中").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});	
	
	$("#syncBtn").click(function(){	
		 var pwd = prompt("请输入密码");
		 if(pwd == "951"){
			var isExecute = doSyncMethod('确认同步?', 'KonkaR3Backmoney.do','tbData' ,'mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());
			if( isExecute == true){
				$("#syncBtn").attr("disabled","true");
				$("font").text("数据同步中,请勿刷新页面!").css("color","red");
			}
		 }else{
			 alert("对不起！你没有权限操作");
			 return false;
		 }
	});
	
		//导出
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
			//CNZZ统计代码
			$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' id='excel_to_all' />");
    		$("#bottomPageForm").submit();
    		$("#excel_to_all").val("");	
		}
	});

	//初始化分公司列表
	$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);

	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
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
}
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
