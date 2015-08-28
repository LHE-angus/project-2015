<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${naviString}</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaR3ShopStock.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="user_id" styleId="user_id" value="${user_id}"/>
      <html-el:hidden property="userpass" styleId="userpass" value="${userpass}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtab2">
        <tr>
          <c:if test="${not empty konkaDeptList}">
          	<td class="fb" width="10%" align="left">分公司：
          		<html-el:select property="branch_area_name" styleId="branch_area_name" >
                  <html-el:option value="">-请选择-</html-el:option>
                  <c:forEach items="${konkaDeptList}" var="cur">
	                  <html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
                <br/><br/>
          </c:if>
           <c:if test="${empty konkaDeptList}">
             <td align="left">
           </c:if>
          
            客户名称：<html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like" /><br/><!--
            
            
          客户编码：<html-el:text property="r3_code" size="20" maxlength="20" styleId="r3_code" /><br/>
         上月是否全部盘点：<html-el:select property="is_pd" styleId="is_pd" ><br/>
                  <html-el:option value="">-请选择-</html-el:option>
	              <html-el:option value="0">否</html-el:option>
	              <html-el:option value="1">是</html-el:option>
                </html-el:select>
                <br/>
                               本月是否结转：<html-el:select property="is_jz" styleId="is_jz" >
                  <html-el:option value="">-请选择-</html-el:option>
	              <html-el:option value="0">否</html-el:option>
	              <html-el:option value="1">是</html-el:option>
                </html-el:select>
                <br/>
           <strong>客户类型：</strong>
	            <html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
	            	<html-el:option value="">-请选择-</html-el:option>
	            </html-el:select> 
	           <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
	                <html-el:option value="">-请选择-</html-el:option>
	            </html-el:select>
	            <br/>
                          经办名称：
           <html-el:text property="handle_name_like" size="20" maxlength="60" styleId="handle_name_like" />
        --><br/>
          <html-el:submit styleClass="but1" value="搜索" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
<!--  <div style="color:red">* 全部盘点：对客户已经初始化过的产品进行全部盘点，日期必须在每月的月末(25-月末)之间；结转：若上月末没有全部盘点，则本月初(月初-5)需结转库存。</div>-->
  <div style="overflow-x: auto">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtab2">
      
<!--      <tr>-->
<!--        <td>客户名称</td>-->
<!--        <td>操作</td>-->
<!--      </tr>-->
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
      <tr>
        <td>${cur.customer_name}</td>
        <td align="right">
        <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopStock.do','view', '&mod_id=${af.map.mod_id}&cust_id=${cur.id}&'+$('#bottomPageForm').serialize())">实时库存</span>
<!--            	<c:if test="${cur.map.is_jz eq 1}"><span style='color:#CCC;'>结转</span></c:if>-->
<!--            	<c:if test="${cur.map.is_jz eq 0}">-->
<!--	            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopStock.do','jiezhuan', '&mod_id=${af.map.mod_id}&cust_id=${cur.id}&'+$('#bottomPageForm').serialize())">结转</span>-->
<!--            	</c:if>-->
        </td>
      </tr>
             </c:forEach>
      
      
      </table>
      </div>
<!--      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopStock.do">-->
<!--      <table width="100%" border="0" cellpadding="0" cellspacing="0">-->
<!--        <tr>-->
<!--          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> -->
<!--            <script type="text/javascript">-->
<!--	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});-->
<!--	            pager.addHiddenInputs("method", "list");-->
<!--	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");-->
<!--	            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");-->
<!--	            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");-->
<!--	            pager.addHiddenInputs("branch_area_name", "${af.map.branch_area_name}");-->
<!--	            pager.addHiddenInputs("is_pd", "${af.map.is_pd}");-->
<!--	            pager.addHiddenInputs("is_jz", "${af.map.is_jz}");-->
<!--	            pager.addHiddenInputs("customer_type1", "${af.map.v_customer_type1}");-->
<!--	            pager.addHiddenInputs("customer_type2", "${af.map.v_customer_type2}");-->
<!--	            pager.addHiddenInputs("handle_name_like", "${af.map.handle_name_like}");-->
<!--	            document.write(pager.toString());-->
<!--	      </script></td>-->
<!--        </tr>-->
<!--      </table>-->
<!--    </form>-->
  </div>
  
<!--  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="客户库存">-->
<!--    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">-->
<!--        <tr class="tabtt1">-->
<!--          <td width="5%" align="center" nowrap="nowrap">序号</td>-->
<!--          <td nowrap="nowrap" align="left" >客户名称</td>-->
<!--           <td nowrap="nowrap" align="center">客户类型</td>-->
<!--          <td nowrap="nowrap" align="center">细分类型</td>-->
<!--          <td width="5%" align="center" nowrap="nowrap">客户群类型</td>-->
<!--          <td nowrap="nowrap" width="10%">R3编码</td>-->
<!--          <td nowrap="nowrap" width="10%">分公司所在地名称</td>-->
<!--          <td nowrap="nowrap" width="10%">合并客户编码</td>-->
<!--          <td nowrap="nowrap" width="10%">上月是否全部盘点</td>-->
<!--          <td nowrap="nowrap" width="10%">是否结转</td>-->
<!--        </tr>-->
<!--        <c:forEach var="cur" items="${allList}" varStatus="vs">-->
<!--          <tr>-->
<!--            <td align="center" nowrap="nowrap">${vs.count }</td>-->
<!--            <td align="left" nowrap="nowrap">${cur.customer_name}</td>-->
<!--             <td align="left" nowrap="nowrap">${cur.map.par_cust_type_name}</td>-->
<!--            <td align="left" nowrap="nowrap">${cur.map.cust_type_name}</td>-->
<!--            <td align="center" nowrap="nowrap">${cur.customer_type}</td>-->
<!--            <td align="left">${fn:escapeXml(cur.r3_code)}</td>-->
<!--            <td align="left" >${fn:escapeXml(cur.branch_area_name)}</td>-->
<!--            <td align="left" >${fn:escapeXml(cur.customer_code)}</td>-->
<!--            <td align="left" >-->
<!--            	<c:if test="${cur.map.is_pd eq 1}">是</c:if>-->
<!--            	<c:if test="${cur.map.is_pd eq 0}">否</c:if>-->
<!--            </td>-->
<!--            <td align="left" >-->
<!--            	<c:if test="${cur.map.is_jz eq 1}">是</c:if>-->
<!--            	<c:if test="${cur.map.is_jz eq 0}">否</c:if>-->
<!--            </td>-->
<!--          </tr>-->
<!--        </c:forEach>-->
<!--      </table>-->
<!--  </div>-->
  
  
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
});
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
		$("#bottomPageForm").submit();
	}
});

var excel_all = "${af.map.excel_all}";
if("1" == excel_all){
	toExcel('divExcel_all', '${ctx}/webservice/KonkaR3ShopStock.do?method=toExcel');
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>