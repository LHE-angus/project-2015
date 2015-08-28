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
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
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
  <div class="rtabcont1">
    <html-el:form  action="/chengduoa/ExpenseClaims">
      <html-el:hidden property="method" value="countList" />
      <html-el:hidden property="mod_id" styleId="mod_id"/>
       <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       <tr>
          <td width="50%" nowrap="nowrap">
           <strong class="fb">客户名称：</strong>
            <html-el:text property="customer_name" styleId="customer_name" size="20"  maxlength="40"/>
              &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">客户R3编码：</strong>
            <html-el:text property="r3_code" styleId="r3_code" size="20"  maxlength="16"/>
          
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">月份：</strong>
          <html-el:text property="st_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onfocus="selectMonth()"/>
          至
          <html-el:text property="en_submit_datetime" size="10" maxlength="10" readonly="true" style="cursor:pointer;width:80px;" onfocus="selectMonth()"/>
          </td>
          </tr>
         <tr>
         <td>
           <strong class="fb">申&nbsp;请&nbsp;人：</strong>
            <html-el:text property="submit_user_id" styleId="submit_user_id" size="20"  maxlength="40"/>
               &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">文件状态：</strong>
          <html-el:select property="map_file_status">
            <html-el:option value="">请选择...</html-el:option>
            <html-el:option value="0">未提交</html-el:option>
            <html-el:option value="1">审批中</html-el:option>
            <html-el:option value="2">已审批</html-el:option>
          </html-el:select>
          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">费用类别：</strong>
            <html-el:text property="c_index" styleId="c_index" size="20"  maxlength="40"/>
          </td>
          </tr>
          <tr>
           <td colspan="3"><strong class="fb">分公司　：</strong>
            <c:set var="disabled" value="false" />
            <c:if test="${af.map.dept_type eq 3}">
            	<c:set var="disabled" value="true" />
            </c:if>
          	<html-el:select property="dept_id" styleId="dept_id" disabled="${disabled}">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select>
          	&nbsp;
          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select>
          	&nbsp;
          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select>
          </td>
          </tr>
       <tr>
   		  <td colspan="1">
	   	  <input type="checkbox" value="selected01" id="selected01" name="selected01" <c:if test="${not empty af.map.selected01 }">checked</c:if>/>申请人
          <input type="checkbox" value="selected02" id="selected02" name="selected02" <c:if test="${not empty af.map.selected02 }">checked</c:if>/>客户名称
   		  <input type="checkbox" value="selected03" id="selected03" name="selected03" <c:if test="${not empty af.map.selected03 }">checked</c:if>/>月份
   		  <input type="checkbox" value="selected04" id="selected04" name="selected04" <c:if test="${not empty af.map.selected04 }">checked</c:if>/>费用类别
   		  &nbsp;&nbsp;&nbsp;&nbsp;<html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>&nbsp;&nbsp;
   		  <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" /></td>     
   		  </tr>
      </table>
    </html-el:form>
    </div>
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
	          <td width="40" align="center" nowrap="nowrap">序号</td>
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="center" nowrap="nowrap">申请人</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center" nowrap="nowrap">客户名称</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center" nowrap="nowrap">月份</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center" nowrap="nowrap">费用类别</td>
		          </c:if>
	          <td width="120" nowrap="nowrap" align="center" >申请单数量</td>
	          <td width="120" nowrap="nowrap" align="center" >申请数量合计</td>
	          <td width="120" nowrap="nowrap" align="center" >申请费用合计</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
	            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	            
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="center">
			          	 <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'submit_user')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
		          	</td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'r3_shop_id')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center">
							<c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'submit_date')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>		          	 
			           </td>
		          </c:if>
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'c_index')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
	            <td align="right" nowrap="nowrap">
	            	<c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'num') }">
		            	<c:set var="num" value="${c_map.value}"></c:set>
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
	            </td>
	             <td align="right" nowrap="nowrap">
	            	<c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'count') }">
		            	<c:set var="num" value="${c_map.value}"></c:set>
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
	            </td>
	         
	            <td align="right" nowrap="nowrap">
		            <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'all_price') }">
			           	 			<fmt:formatNumber type="currency" value="${c_map.value }"></fmt:formatNumber> 
			           	 	</c:if>
			            </c:forEach>
	             </td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
            <td>&nbsp;</td>
            <c:if test="${not empty af.map.selected01 }">
		          	  <td>&nbsp;</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected02 }">
		          	  <td>&nbsp;</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected03 }">
		          	  <td>&nbsp;</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected04 }">
		          	  <td>&nbsp;</td>
		          </c:if>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </table>
    
      <table width="60%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	      	<td style="color: blue;">合计：</td>
	      	<td align="right" style="color: blue;">申请单数量：${sumNum}</td>
	      	<td align="right" style="color: blue;">申请数量合计：${sumCount}</td>
	      	<td align="right" style="color: blue;">申请费用合计：${sumAllPrice}</td>
	      </tr>
      </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="ExpenseClaims.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
			 var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "countList");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("customer_name", "${af.map.customer_name}");
            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
            pager.addHiddenInputs("st_submit_datetime", "${af.map.st_submit_datetime}");
            pager.addHiddenInputs("en_submit_datetime", "${af.map.en_submit_datetime}");
            pager.addHiddenInputs("submit_user_id", "${af.map.submit_user_id}");
            pager.addHiddenInputs("map_file_status", "${af.map.map_file_status}");
            pager.addHiddenInputs("c_index", "${af.map.c_index}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
            pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
 			pager.addHiddenInputs("num", "${af.map.num}");
			pager.addHiddenInputs("count", "${af.map.count}");
	        pager.addHiddenInputs("all_price", "${af.map.all_price}");		
			pager.addHiddenInputs("selected01", "${af.map.selected01}");
			pager.addHiddenInputs("selected02", "${af.map.selected02}");
			pager.addHiddenInputs("selected03", "${af.map.selected03}");
			pager.addHiddenInputs("selected04", "${af.map.selected04}");
			pager.addHiddenInputs("recordCount", "${af.map.recordCount}");
			document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>


<div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="费用统计">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
         <td width="40" align="center" nowrap="nowrap">序号</td>
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="center" nowrap="nowrap">申请人</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center" nowrap="nowrap">客户名称</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center" nowrap="nowrap">月份</td>
		          </c:if>
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center" nowrap="nowrap">费用类别</td>
		          </c:if>
	          <td width="120" nowrap="nowrap" align="center" >申请单数量</td>
	          <td width="120" nowrap="nowrap" align="center" >申请数量合计</td>
	          <td width="120" nowrap="nowrap" align="center" >申请费用合计</td>
      </tr>
      <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
	            <td align="center" nowrap="nowrap">${vs.count}</td>
	            
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="center">
			          	 <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'submit_user')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
		          	</td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'r3_shop_id')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center">
							<c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'submit_date')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>		          	 
			           </td>
		          </c:if>
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'c_index')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
	            <td align="right" nowrap="nowrap">
	            	<c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'num') }">
		            	<c:set var="num" value="${c_map.value}"></c:set>
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
	            </td>
	             <td align="right" nowrap="nowrap">
	            	<c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'count') }">
		            	<c:set var="num" value="${c_map.value}"></c:set>
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
	            </td>
	         
	            <td align="right" nowrap="nowrap">
		            <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'all_price') }">
			           	 			<fmt:formatNumber type="currency" value="${c_map.value }"></fmt:formatNumber> 
			           	 	</c:if>
			            </c:forEach>
	             </td>
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
    </table>
  </div>
  </div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script>
function selectMonth() {  
    WdatePicker({ dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: true});  
}
</script>
<script type="text/javascript">//<![CDATA[
   
	$(document).ready(function() {
		
		$("#r3_shop_id option[value='${af.map.r3_shop_id}']").attr("selected",true);
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



		$("form").eq(0).submit(function(){

			return Validator.Validate(this, 2);
		});
		
		$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
		$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
		$("#dept_id").change();

	    
   var f = document.getElementById('af');	
	$("#btn_submit").click(function(){
		if (f.st_submit_datetime.value != "" && f.en_submit_datetime.value != "") {
			if (f.en_submit_datetime.value < f.st_submit_datetime.value) {
				alert("申请时间结束日期不得大于开始日期,请重新选择!");
				return false;
			}
		}
		f.submit();
	});

	   // 导出excel
    $("#export_excel").click(function(){
    	$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
		$("#bottomPageForm").submit();
    });
    var excel_all = "${af.map.excel_all}";
	if("1" == excel_all){
		toExcel('divExcel_all', '?method=toExcel');
	}

});



//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>