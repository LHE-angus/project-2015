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
<style>
	.bg_g{
		background-color: #97FFFF;
	}
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
  <div class="rtabcont2" >
    <html-el:form action="/admin/KonkaOrderInfoStatistics.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="list"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <html-el:hidden property="is_kh" styleId="is_kh" value="${af.map.is_kh}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" >
        <tr>
        	<td valign="middle" nowrap="nowrap" class="title_item" align="right">
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
        	<td valign="middle" nowrap="nowrap" class="title_item" align="right">
        		<strong class="fb">R3编码：</strong>
        	</td>
        	<td>
	            <html-el:text property="r3_code" styleId="r3_code" maxlength="10" size="20"  />
        	</td>
        	<td valign="middle" nowrap="nowrap" class="title_item" align="right">
        		<strong class="fb">客户名称：</strong>
        	</td>
        	<td>
	            <html-el:text property="customer_name" styleId="customer_name" size="28" />
        	</td>
        	<td width="20%"></td>
		   	</tr>
		   	<tr>
		   	<td valign="middle" nowrap="nowrap" class="title_item" align="right">分公司：</td>
		   	<td>
		   		<html-el:select property="dept_id" styleId="dept_id" style="width:80px;">
                	<html-el:option value="">-请选择-</html-el:option>
              	</html-el:select>
		   	</td>
		   	<td valign="middle" nowrap="nowrap" class="title_item" align="right">
		   		统计维度：
		   	</td>
		   	<td>
		   		<html-el:select property="computer_type" styleId="computer_type" style="width:80px;">
	                <c:forEach items="${typelist}" var="cur">
	                  	<html-el:option value="${cur.val}">${cur.name}</html-el:option>
	                </c:forEach>
	            </html-el:select>
		   	</td>
		   	<td valign="middle" nowrap="nowrap" class="title_item" align="right">客户类型：</td>
		    <td >
				<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
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
   <input type="button" style="cursor: pointer;" class="but8" id="syncBtn" value="同步数据"></input>
   <font color="#696969">我们建议同步数据操作还是由系统自动完成!</font>
  </div>
  <div class="rtabcont2">
  <div style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr>
        <td align="center" nowrap="nowrap" class='bg_g'>序号</td>        
        <td align="center" nowrap="nowrap" class='bg_g'>分公司</td>
        <td align="center" nowrap="nowrap" class='bg_g'>一级部门</td>
        <td align="center" nowrap="nowrap" class='bg_g'>二级部门</td>
        <td align="center" nowrap="nowrap" class='bg_g'>业务员</td>
        <!-- <td align="center" nowrap="nowrap">客户类型</td> -->
        <td align="center" nowrap="nowrap" class='bg_g'>客户细分类型</td>
        <td align="center" nowrap="nowrap" class='bg_g'>客户名称</td>
        <td align="center" nowrap="nowrap" class='bg_g'>R3编码</td>
        <td align="center" nowrap="nowrap" class='bg_g'>年份</td>        
        <td align="center" nowrap="nowrap" class='bg_g'>1月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>2月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>3月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>4月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>5月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>6月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>7月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>8月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>9月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>10月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>11月</td>
        <td align="center" nowrap="nowrap" class='bg_g'>12月</td>
      </tr>
      <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
          <td align="left" nowrap="nowrap">
          	<c:if test="${cur.map.l4_dept_name != cur.map.dept_name}">
          		${cur.map.l4_dept_name}
          	</c:if>
          </td>
          <td align="left" nowrap="nowrap">${cur.map.l5_dept_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.ywy_user_name}</td>
          <!-- <td align="center" nowrap="nowrap">${cur.map.par_customer_type_name}</td> -->
          <td align="left" nowrap="nowrap">${cur.map.customer_type_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.customer_name}</td>
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>    
          <td align="center" nowrap="nowrap">${cur.year}</td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month1_num}"><fmt:formatNumber value="${cur.month1_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month1_val}"><fmt:formatNumber value="${cur.month1_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month2_num}"><fmt:formatNumber value="${cur.month2_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month2_val}"><fmt:formatNumber value="${cur.month2_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month3_num}"><fmt:formatNumber value="${cur.month3_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month3_val}"><fmt:formatNumber value="${cur.month3_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month4_num}"><fmt:formatNumber value="${cur.month4_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month4_val}"><fmt:formatNumber value="${cur.month4_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month5_num}"><fmt:formatNumber value="${cur.month5_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month5_val}"><fmt:formatNumber value="${cur.month5_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month6_num}"><fmt:formatNumber value="${cur.month6_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month6_val}"><fmt:formatNumber value="${cur.month6_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month7_num}"><fmt:formatNumber value="${cur.month7_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month7_val}"><fmt:formatNumber value="${cur.month7_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month8_num}"><fmt:formatNumber value="${cur.month8_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month8_val}"><fmt:formatNumber value="${cur.month8_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month9_num}"><fmt:formatNumber value="${cur.month9_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month9_val}"><fmt:formatNumber value="${cur.month9_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month10_num}"><fmt:formatNumber value="${cur.month10_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month10_val}"><fmt:formatNumber value="${cur.month10_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month11_num}"><fmt:formatNumber value="${cur.month11_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month11_val}"><fmt:formatNumber value="${cur.month11_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
          <td align="right" nowrap="nowrap">
          	<c:if test="${empty c_type or c_type eq 1}">
	          	<c:if test="${0 < cur.month12_num}"><fmt:formatNumber value="${cur.month12_num}" pattern="#0" /></c:if>
          	</c:if>
          	<c:if test="${c_type eq 2}">
	          	<c:if test="${0 != cur.month12_val}"><fmt:formatNumber value="${cur.month12_val}" pattern="#0.00" /></c:if>
          	</c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderInfoStatistics.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="center"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");	
			pager.addHiddenInputs("keyWord", "${fn:escapeXml(af.map.keyWord)}");	
            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
			pager.addHiddenInputs("customer_name", "${af.map.customer_name}");
            pager.addHiddenInputs("is_kh", "${af.map.is_kh}");
            pager.addHiddenInputs("year", "${af.map.year}");
            pager.addHiddenInputs("computer_type", "${af.map.computer_type}");
            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
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
			var isExecute = doSyncMethod('确认同步?', 'KonkaOrderInfoStatistics.do','tbData' ,'mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());
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
