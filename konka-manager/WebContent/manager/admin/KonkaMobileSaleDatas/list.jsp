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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
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
    <html-el:form  action="/admin/KonkaMobileSaleDatas">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id"/>
      <html-el:hidden property="search_first_flag" styleId="search_first_flag" />
       <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td width="150"><strong class="fb">分公司/办事处：</strong></td>
          <td colspan="2">
            <html-el:select property="dept_id" styleId="dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select> 
			<html-el:select property="l4_dept_id" styleId="l4_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>
			<html-el:select property="l5_dept_id" styleId="l5_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>          
          <!--
          <html-el:select property="subcomp_id" styleId="subcomp_id" onchange="subcomp_id_chg();">
              <html-el:option value="">-分公司-</html-el:option>
              <c:forEach var="cur" items="${baseDeptList}">
              	 <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>&nbsp;
            <select name="office_id" id="office_id">
              <option value="">-办事处-</option>
            </select>
            --></td>
          <td width="100"><strong class="fb">客户名称：</strong></td>
          <td><html-el:text property="customer_name_like" size="30" style="width:170px;" maxlength="40" styleId="customer_name_like" styleClass="webinput" /></td>
          <td></td>
        </tr>
        <tr>
          <td width="15"></td>
          <td width="70"><strong class="fb">上报人 ：</strong></td>
          <td colspan="2"><html-el:text property="report_name_like" size="30" style="width:170px;" maxlength="40" styleId="report_name_like" styleClass="webinput" /></td>
          <td width="70"><strong class="fb">上报门店: </strong></td>
          <td colspan="2"><html-el:text property="dept_name_like" size="30" maxlength="40" styleId="dept_name_like"  /></td>
          <td></td>
        </tr>
        <tr>
          <td width="15"></td>
          <td width="150"><strong class="fb">尺寸/型号：</strong></td>
          <td colspan="2"><html-el:select property="measure_id" styleId="measure_id" onchange="category_id_chg();">
              <html-el:option value="">-选择尺寸-</html-el:option>
             <html-el:optionsCollection label="name" name="sizeList" value="name" />
            </html-el:select>
            &nbsp;
            <select name="model_id" id="model_id">
              <option value="">-产品型号-</option>
            </select></td>
          <td width="70"><strong class="fb">时间范围：</strong></td>
          <td><html-el:text property="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
          <td></td>
        </tr>
       <tr>
       <td colspan="2">
	       <html-el:radio  value="1" styleId="timetype" property="timetype"  />月度
	       <html-el:radio  value="2" styleId="timetype" property="timetype"  />天
	       <html-el:radio  value="3" styleId="timetype" property="timetype"  />季度
	       <html-el:radio  value="4" styleId="timetype" property="timetype"  />年度
       </td>
       <!-- 
       <td colspan="4">
		<input id="date_begin1" name="date_begin1" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
		<input id="date_begin2" name="date_begin2" class="Wdate" type="text" onFocus="var d52222=$dp.$('date_end2');WdatePicker({onpicked:function(){d52222.focus();},dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'date_end2\')}'})"/>
		<span id="tospan">至</span>
		<input id="date_end2" name="date_end2" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'date_begin2\')}'})"/>
		<input id="date_begin3" name="date_begin3" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy'})"/>
		<select id="date_end3" name="date_end3">
		<option value="1">第一季</option>
		<option value="2">第二季</option>
		<option value="3">第三季</option>
		<option value="4">第四季</option>
		</select>
	   </td>
       </tr>
        <tr>
         <td width="15"></td>
   		  <td width="150"><strong class="fb"></strong></td> -->
   		  <td>&nbsp;</td>
   		  <td colspan="3">
   		  <c:if test="${isFgsUser}">
	   	  <input type="checkbox" value="selected01" id="selected01" name="selected01" <c:if test="${not empty af.map.selected01 }">checked</c:if>/>分公司
          </c:if>
<!--          <input type="checkbox" value="selected02" id="selected02" name="selected02" <c:if test="${not empty af.map.selected02 }">checked</c:if>/>办事处-->
          <input type="checkbox" value="selected06" id="selected06" name="selected06" <c:if test="${not empty af.map.selected06 }">checked</c:if>/>部门
   		  <input type="checkbox" value="selected07" id="selected07" name="selected07" <c:if test="${not empty af.map.selected07 }">checked</c:if>/>经办
   		  
   		  <input type="checkbox" value="selected03" id="selected03" name="selected03" <c:if test="${not empty af.map.selected03 }">checked</c:if>/>产品品类
   		  <input type="checkbox" value="selected04" id="selected04" name="selected04" <c:if test="${not empty af.map.selected04 }">checked</c:if>/>尺寸
   		  <input type="checkbox" value="selected05" id="selected05" name="selected05" <c:if test="${not empty af.map.selected05 }">checked</c:if>/>型号
	      </td>
   		  <td>
   		  	<!--<html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit>-->
   		  	<html-el:button styleId="btn_submit_search" property="btn_submit_search" styleClass="but1" value="搜索" />
   		  </td>
         </tr>
      </table>
    </html-el:form>
    </div>
    <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
  <div  style="overflow-y:auto;height:400px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
	          <td width="40" align="center" nowrap="nowrap">序号</td>
	          
	          	 <td align="center" nowrap="nowrap">
			          <c:if test="${empty af.map.timetype || '1' eq af.map.timetype }">月度</c:if>
			          <c:if test="${'2' eq af.map.timetype }">天</c:if>
			          <c:if test="${'3' eq af.map.timetype }">季度</c:if>
			          <c:if test="${'4' eq af.map.timetype }">年度</c:if>
	          	 </td>
	          
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="center" nowrap="nowrap">分公司</td>
		          </c:if>
		      <!--
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center" nowrap="nowrap">办事处</td>
		          </c:if>
	          -->
	           <c:if test="${not empty af.map.selected06 }">
		          	 <td align="center" nowrap="nowrap">部门</td>
	           </c:if>
	           <c:if test="${not empty af.map.selected07 }">
	          	 <td align="center" nowrap="nowrap">经办</td>
	          </c:if>
	          
	          
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center" nowrap="nowrap">产品品类</td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center" nowrap="nowrap">尺寸</td>
		          </c:if>
		          
	          <c:if test="${not empty af.map.selected05 }">
		          	 <td align="center" nowrap="nowrap">型号</td>
		          </c:if>
	          <td width="120" nowrap="nowrap" align="center" >数量</td>
	          <td width="120" nowrap="nowrap" align="center" >单价</td>
	          <td width="120" nowrap="nowrap" align="center" >总价</td>
	          
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
	            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
	            
	            <td align="center">
		            <c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'sale_date') }">
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
	            </td>
	          <c:if test="${not empty af.map.selected01 }">
		          	 <td align="left">
			          	 <!--<c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'dept_name')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
		          	-->
		          	${cur.DEPT_NAME}
		          	</td>
		          </c:if>
		      <!--
	          <c:if test="${not empty af.map.selected02 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'handle_name')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
	          -->
	           <c:if test="${not empty af.map.selected06 }">
		          	 <td align="left">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'l4_dept_name')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
	            </c:if>
	            <c:if test="${not empty af.map.selected07 }">
	          	 <td align="center">
	          	  <c:forEach var="c_map" items="${cur}">
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'l5_dept_name')}">
		           	 			${c_map.value}
		           	 	</c:if>
		            </c:forEach>
		           </td>
	            </c:if>
	          
	          <c:if test="${not empty af.map.selected03 }">
		          	 <td align="center">
							<c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'CLS_NAME')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>		          	 
			           </td>
		          </c:if>
	          
	          <c:if test="${not empty af.map.selected04 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'CC_ID')}">
			           	 			${c_map.value}
			           	 	</c:if>
			            </c:forEach>
			           </td>
		          </c:if>
		          
	          <c:if test="${not empty af.map.selected05 }">
		          	 <td align="center">
		          	  <c:forEach var="c_map" items="${cur}">
			           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'MD_NAME')}">
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
		           	 	<c:if test="${fn:containsIgnoreCase(c_map.key,'all_price') }">
			           	 	<c:if test="${num ne 0 }">
			           	 			<fmt:formatNumber type="currency" value="${c_map.value / num }" ></fmt:formatNumber> 
			           	 	</c:if>
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
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileSaleDatas.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("subcomp_id", "${af.map.subcomp_id}");							
			pager.addHiddenInputs("office_id", "${af.map.office_id}");							
			pager.addHiddenInputs("category_id", "${af.map.category_id}");							
			pager.addHiddenInputs("measure_id", "${af.map.measure_id}");
			pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
			pager.addHiddenInputs("model_id", "${af.map.model_id}");							
			pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
			pager.addHiddenInputs("timetype", "${af.map.timetype}");
			pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
			pager.addHiddenInputs("selected01", "${af.map.selected01}");
			pager.addHiddenInputs("selected02", "${af.map.selected02}");
			pager.addHiddenInputs("selected03", "${af.map.selected03}");
			pager.addHiddenInputs("selected04", "${af.map.selected04}");
			pager.addHiddenInputs("selected05", "${af.map.selected05}");
			pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");							
			pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");		
			pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	        pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
	        pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");	
	    	pager.addHiddenInputs("selected06", "${af.map.selected06}");
			pager.addHiddenInputs("selected07", "${af.map.selected07}");	
			pager.addHiddenInputs("search_first_flag", "1");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//分公司初始化
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	
	$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId&isNotEpp=isNotEpp", "par_id", "0", false);
	$("#dept_id").change();
	
	//subcomp_id_chg();
	category_id_chg();
	
	var queryForm = document.forms[0];
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
	
	function submitForm(){
		var f = document.forms[0];
		$("#search_first_flag").val("1");
		f.submit();
	}
	
	$("#btn_submit_search").click(function(){
		loading();
		submitForm();
	});
});

function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}

//分公司- 连动-办事处
function subcomp_id_chg(){
		$("#office_id").empty();
		$("<option value=''>-所属办事处-</option>").appendTo($("#office_id"));
		var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getDept&subcomp_id="+$('#subcomp_id').val();
		$.getJSON(url, function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					$("<option></option>").val(item[0]).text(item[0]).appendTo($("#office_id"));
				});
			}
			if('${af.map.office_id }' != null || '${af.map.office_id }' != '' ){
				$("#office_id").val('${af.map.office_id }');
			}
		});
	}

	//类别-连动-型号
	function category_id_chg(){
		$("#model_id").empty();
		$("<option value=''>-产品型号-</option>").appendTo($("#model_id"));
		var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getModel&size_id="+$('#measure_id').val();
		$.getJSON(url, function(data) {
			if(data != null){
				$.each(data, function(i, item) {
					var option = $("<option></option>").val(item[1]).text(item[0]);
					option.appendTo($("#model_id"));
				});
				if('${af.map.model_id }' != null || '${af.map.model_id }' != '' ){
					$("#model_id").val('${af.map.model_id }');
				}
			}
		});
	}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
