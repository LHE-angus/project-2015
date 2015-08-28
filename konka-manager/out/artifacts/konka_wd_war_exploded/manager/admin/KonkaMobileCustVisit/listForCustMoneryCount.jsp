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
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaMobileCustVisit">
      <html-el:hidden property="method" value="listMoneryReport" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
       <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
	      <td>
	         <strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
	      </td>
	      <td>
	         <strong class="fb">统计年月： </strong>
         <html-el:select property="year" styleId="year" style="width:90px;">
        	           <html-el:option value="2014">2014</html-el:option>
        	           <html-el:option value="2013">2013</html-el:option>
        	           <html-el:option value="2012">2012</html-el:option>
        	         </html-el:select>年
        	  <html-el:select property="month" styleId="month" style="width:70px;">
        	          <html-el:option value="01">01</html-el:option>
        	          <html-el:option value="02">02</html-el:option>
        	          <html-el:option value="03">03</html-el:option>
        	          <html-el:option value="04">04</html-el:option>
        	          <html-el:option value="05">05</html-el:option>
        	          <html-el:option value="06">06</html-el:option>
        	          <html-el:option value="07">07</html-el:option>
        	          <html-el:option value="08">08</html-el:option>
        	          <html-el:option value="09">09</html-el:option>
        	          <html-el:option value="10">10</html-el:option>
        	          <html-el:option value="11">11</html-el:option>
        	          <html-el:option value="12">12</html-el:option>
        	         </html-el:select>月
	      </td>
	      <td>
	         <strong class="fb">R3编码：</strong>
        	 <html-el:text property="r3_code_like"></html-el:text>
	      </td>
	      <td>
	         <strong class="fb">客户名称：</strong>
        	 <html-el:text property="customer_name_like"></html-el:text>
	      </td>
	      </tr>
	      <tr>
	      <td>
	         <strong class="fb">上报人：</strong>
        	 <html-el:text property="ywy_user_name_like"></html-el:text>
	      </td>
       		<td align="left">
       		<strong class="fb">客户类型： </strong>
       			<html-el:select property="par_customer_type" styleId="par_customer_type" style="width:140px">
              		<html-el:option value="">－请选择－</html-el:option>
              		<c:forEach var="cur" items="${konkaCategoryList}">
                		<html-el:option value="${cur.PAR_INDEX}">${cur.C_COMM}</html-el:option>
              		</c:forEach>
            	</html-el:select>
        	</td>
        	<td align="left">
       		<strong class="fb">客户状态： </strong>
       			<html-el:select property="shop_status" styleId="shop_status" style="width:140px">
              		    <html-el:option value="">－请选择－</html-el:option>
                		<html-el:option value="1">新客户</html-el:option>
                		<html-el:option value="2">正式客户</html-el:option>
                		<html-el:option value="3">静止客户</html-el:option>
                		<html-el:option value="4">无效客户</html-el:option>
                		<html-el:option value="5">终端网点</html-el:option>
            	</html-el:select>
        	</td>
          <td height="15" align="left" style="padding-left:5px;">
        	 <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
        	 <input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />&nbsp;&nbsp;
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
		<%@ include file="/commons/pages/messages.jsp"%>	
 <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
  <tr style="bgcolor:orange;" >
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2" >序号</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">分公司</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">R3编码</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">客户名称</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">客户类型</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">客户状态</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">区域信息</td>
     <td nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">合并编码</td>
     <td  nowrap="nowrap" align="center" style="background-color: #FFD39B;" rowspan="2">上报人</td>
    
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">结算</td>
    <td  nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">回款</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">零售</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" colspan="3">拜访次数</td>
    
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" rowspan="2">当月计划</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" rowspan="2">当月完成率</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" rowspan="2">预警</td>
    <td nowrap="nowrap" align="center"  rowspan="2">日期区间</td>
  </tr>
  <tr >
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">上上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">上上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;">上上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上上月拜访次数</td>
     <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >上月拜访次数</td>
    <td nowrap="nowrap" align="center" style="border-bottom: 1px solid #E3E3E3;background-color: #A4D3EE;" >当月拜访次数</td>
    
  </tr>
   <c:forEach items="${entityList}" var="cur" varStatus="vs">
    <tr>
    <td nowrap="nowrap" align="center">${vs.count}</td>
     <td align="center" nowrap="nowrap">
     	${cur.BRANCH_AREA_NAME}
     </td>
     <td align="left">${fn:escapeXml(cur.R3_CODE)}</td>
     <td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
     <td align="center" nowrap="nowrap">${fn:escapeXml(cur.PAR_CUSTOMER_TYPE_NAME)}
       <c:if test="${empty cur.PAR_CUSTOMER_TYPE_NAME}"><span style="color:#ccc;">未指定</span></c:if>
     </td>
     <td align="left">
     	<c:if test="${cur.SHOP_STATUS eq 1}">
     		新客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 2}">
     		正式客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 3}">
     		静止客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 4}">
     		无效客户
     	</c:if>
     	<c:if test="${cur.SHOP_STATUS eq 5}">
     		终端网点
     	</c:if>
     	<c:if test="${empty cur.SHOP_STATUS}"><span style="color:#ccc;">未指定</span></c:if>
     </td>  
     <td align="left" nowrap="nowrap">
     	${fn:escapeXml(cur.AREA_NAME)}-${fn:escapeXml(cur.BRANCH_AREA_NAME)}</td>
     <td align="left" nowrap="nowrap">${fn:escapeXml(cur.CUSTOMER_CODE)}</td>
     <td align="left" nowrap="nowrap">${fn:escapeXml(cur.YWY_USER_NAME)}</td>
     
     
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_CLS_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.cur.LAST_ONE_CLS_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"> <fmt:formatNumber value="${cur.CUR_CLS_MONEY}" pattern=".00"/> </td>
    
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_BACK_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_ONE_BACK_MONEY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.CUR_BACK_MONEY}" pattern=".00"/>  </td>
   
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_SAIL_MONERY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_ONE_SAIL_MONERY}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.CUR_SAIL_MONERY}" pattern=".00"/>  </td>
    
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_TWO_CUST_VISIT_COUNT}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.LAST_ONE_CUST_VISIT_COUNT}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${cur.CUR_CUST_VISIT_COUNT}" pattern=".00"/></td>
      
    <td nowrap="nowrap" align="center"><fmt:formatNumber value="${map.CUR_CUST_PLAN_VISIT_COUNT}" pattern=".00"/></td>
     <td nowrap="nowrap" align="center"><fmt:formatNumber value="${map.MONTH_CUST_VISIT_BFB}" pattern=".00"/></td>
    <td nowrap="nowrap" align="center"></td>
    <c:if test="${not empty cur.ADD_DATE}">
    <td nowrap="nowrap" align="center">${cur.ADD_DATE}</td>
    </c:if>
    <c:if test="${empty cur.ADD_DATE}">
     <td nowrap="nowrap" align="center">未指定区间</td>
   </c:if>
  </tr>
 </c:forEach>
     </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileCustVisit.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "listMoneryReport");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
								pager.addHiddenInputs("year", "${af.map.year}");
								pager.addHiddenInputs("month", "${af.map.month}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
								pager.addHiddenInputs("ywy_user_name_like", "${af.map.ywy_user_name_like}");
								pager.addHiddenInputs("shop_status", "${af.map.shop_status}");
								pager.addHiddenInputs("par_customer_type", "${af.map.par_customer_type}");
								pager.addHiddenInputs("role_id", "${af.map.role_id}");
								pager.addHiddenInputs("isfirst", "${af.map.isfirst}");
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
	$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
	//	$("#bottomPageForm").append("<input id='export_id'  name='excel_all' value='1' />");
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});

});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
