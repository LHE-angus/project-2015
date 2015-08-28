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
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
.rtable1 td {
	padding:0px 2px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/admin/TaskContent">
  <div class="rtabcont1">
      <html-el:hidden property="method" value="listCustReport" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="report_type" styleId="report_type" value="${af.map.report_type}" />
      <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
          <tr>
          <td class="title_item" align="right"  width="10%">分公司：</td>
          <td class="title_item" width="30%">
          <html-el:select property="dept_id" styleId="dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select> 
			<html-el:select property="l4_dept_id" styleId="l4_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>
          </td>
          
       		<td align="right"  width="10%"><strong class="fb">客户编码：</strong></td>
       		<td align="left"  width="20%">
       			<html-el:text property="r3_code_like" styleId="r3_code_like" maxlength="40"/>
        	</td>
            <td align="right"  width="10%"><strong class="fb">客户名称：</strong></td>
       		<td  width="20%">
       			<html-el:text property="customer_name_like" styleId="customer_name_like" maxlength="40"/>
       		</td>
       	</tr>
       	<tr style="margin-top: 10px;margin-bottom: 10px;" >
       	 <td align="right"  width="10%"><strong class="fb">客户类型：</strong></td>
       		<td align="left"  width="20%">
       			<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	     <html-el:option value="">-请选择-</html-el:option>
	            </html-el:select> 
	            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
	                <html-el:option value="">-请选择-</html-el:option>
	            </html-el:select>
        	</td>
       	  <td class="title_item" align="right"  width="10%">年度：</td>
          <td width="20%">
<%--           	<html-el:text property="task_year" styleId="task_year" onkeyup="checkNum(this)"></html-el:text> --%>
          	<html-el:select property="task_year"  styleId="task_year" style="width:150px">
	          	<html-el:option value="">-请选择-</html-el:option>
	          	<html-el:option value="2010">2010</html-el:option>
	          	<html-el:option value="2011">2011</html-el:option>
	          	<html-el:option value="2012">2012</html-el:option>
	          	<html-el:option value="2013">2013</html-el:option>
	          	<html-el:option value="2014">2014</html-el:option>
	          	<html-el:option value="2015">2015</html-el:option>
	          	<html-el:option value="2016">2016</html-el:option>
	          	<html-el:option value="2017">2017</html-el:option>
	          	<html-el:option value="2018">2018</html-el:option>
	          	<html-el:option value="2019">2019</html-el:option>
	          	<html-el:option value="2020">2020</html-el:option>
          	</html-el:select>
           </td>
            <td class="title_item" align="right"  width="10%">月份：</td>
          <td width="20%">
          	<html-el:select property="task_month"  styleId="task_month" style="width:150px">
          	<html-el:option value="">-请选择-</html-el:option>
          	<html-el:option value="01">1</html-el:option>
          	<html-el:option value="02">2</html-el:option>
          	<html-el:option value="03">3</html-el:option>
          	<html-el:option value="04">4</html-el:option>
          	<html-el:option value="05">5</html-el:option>
          	<html-el:option value="06">6</html-el:option>
          	<html-el:option value="07">7</html-el:option>
          	<html-el:option value="08">8</html-el:option>
          	<html-el:option value="09">9</html-el:option>
          	<html-el:option value="10">10</html-el:option>
          	<html-el:option value="11">11</html-el:option>
          	<html-el:option value="12">12</html-el:option>
          	</html-el:select>
          </td>
        	<td align="center"  width="30%">
        		<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
        	</td>
       	</tr>
       
      </table>
  </div>
  <div style="text-align: left;padding-left: 10px">
	<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
  </div>
  </html-el:form>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td align="center" nowrap="nowrap" width="5%">序号</td>
          <td align="center" nowrap="nowrap" >分公司</td>	
          <td align="center" nowrap="nowrap" >部门</td>
          <td align="center" nowrap="nowrap" >客户编码</td>	
          <td align="center" nowrap="nowrap" >客户名称</td>
           <td align="center" nowrap="nowrap" >客户类型</td>	
          <td align="center" nowrap="nowrap" >客户细分类型</td>
          <td align="center" nowrap="nowrap" >年度</td>	
          <td align="center" nowrap="nowrap" >月份</td>	
          <td align="center" nowrap="nowrap" >任务系数</td>	
          <td align="center" nowrap="nowrap" >任务额度</td>
		  <td align="center" nowrap="nowrap" >结算额</td>
		  <td align="center" nowrap="nowrap" >回款额</td>	
		  <td align="center" nowrap="nowrap" >结算完成率</td>	
          
        </tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
				<tr>
		        	<td height="28"  align="center">${vs.count}</td>
		            <td align="center" nowrap="nowrap">${cur.DEPT_NAME }</td> <!--<c:if test="${!cur.map.dept_name }">(${cur.map.dept_name })</c:if>-->
		            <td align="center" nowrap="nowrap">${cur.CUR_DEPT_NAME }</td>
		            <td align="center" nowrap="nowrap">${cur.R3_CODE }</td>
		            <td align="center" nowrap="nowrap">${cur.CUSTOMER_NAME }</td>
		            <td align="center" nowrap="nowrap">${cur.PAR_CUSTOMER_TYPE_NAME}</td>
		            <td align="center" nowrap="nowrap">${cur.TASK_P_TYPE}</td>
		            <td align="center" nowrap="nowrap">${cur.TASK_YEAR}</td>
		            <td align="center" nowrap="nowrap">${cur.TASK_MONTH}</td>
		            <td align="center" nowrap="nowrap">${cur.TASK_XS}</td>
		            <td align="center" nowrap="nowrap">${cur.TASK_ED}</td>
		            <td align="center" nowrap="nowrap">${cur.CUR_CLS_MONEY}</td>
		            <td align="center" nowrap="nowrap">${cur.CUR_BACK_MONEY}</td>
		            <td align="center" nowrap="nowrap">${cur.JS_BFB}%</td>
		        </tr>
		</c:forEach>
      </table>
    <c:if test="${not vs.last}">
      <div style="height:10px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="TaskContent.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="20"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "listCustReport");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
								pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");	
						        pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
						        pager.addHiddenInputs("task_year", "${af.map.task_year}");	
						        pager.addHiddenInputs("task_month", "${af.map.task_month}");		
						        pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
					            pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
					            pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//分公司初始化
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId&isNotEpp=isNotEpp", "par_id", "0", false);
	$("#dept_id").change();

	
	$("#button").click(function(){
		if (Validator.Validate(this.form, 1)){
			this.form.submit();
			}
	});

	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
	});
	//导出excel
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
