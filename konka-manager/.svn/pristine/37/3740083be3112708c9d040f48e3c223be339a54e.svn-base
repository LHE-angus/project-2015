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
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/KonkaOrderInfoTransEnsu">
      <html-el:hidden property="method" styleId="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">
<!--             &nbsp; &nbsp; <strong class="fb">分公司：</strong> -->
<%--               <html-el:select property="dept_id" styleId="dept_id"> --%>
<%--                 <html-el:option value="">-请选择-</html-el:option> --%>
<%--               </html-el:select> --%>
<%--               <html-el:select property="l4_dept_id" styleId="l4_dept_id"> --%>
<%--                 <html-el:option value="">-请选择-</html-el:option> --%>
<%--               </html-el:select> --%>
<%--               <html-el:select property="l5_dept_id" styleId="l5_dept_id"> --%>
<%--                 <html-el:option value="">-请选择-</html-el:option> --%>
<%--               </html-el:select> --%>
          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">签收状态：</strong>
          	<html-el:select property="trans_ensu_status" style="width:90px;">
          		<html-el:option value="">--请选择--</html-el:option>
          		<html-el:option value="0">未签收</html-el:option>
          		<html-el:option value="1">部分签收</html-el:option>
          		<html-el:option value="2">确认收货</html-el:option>
          		<html-el:option value="3">全部拒收</html-el:option>
          	</html-el:select>
          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">签收方式：</strong>
          	<html-el:select property="trans_ensu_type" style="width:90px;">
          		<html-el:option value="">--请选择--</html-el:option>
          		<html-el:option value="0">客户签收</html-el:option>
          		<html-el:option value="1">管理端代签</html-el:option>
          		<html-el:option value="2">二维码签收</html-el:option>
          	</html-el:select>
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">发货单号：</strong>
          	<html-el:text property="trans_index_detail_like" style="width:170px;" maxlength="20" styleId="trans_index_detail_like" styleClass="webinput" />
           &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">R3物流单号：</strong>
            <html-el:text property="r3_vbedl_like" size="20" style="width:90px;" maxlength="20" styleId="r3_vbedl_like" styleClass="webinput" />
          </td>
          </tr>
          <tr>
          <td nowrap="nowrap">
	          &nbsp;&nbsp;<strong class="fb">签收日期：</strong>
	          <input name="trans_ensu_date_s" id="trans_ensu_date_s" size="12" value="${af.map.trans_ensu_date_s}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'trans_ensu_date_e\')}'})" />
	            	    至
	          <input name="trans_ensu_date_e" id="trans_ensu_date_e" size="12"  value="${af.map.trans_ensu_date_e}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'trans_ensu_date_s\')||\'2013-11-01\'}'})" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">客户名称：</strong>
	          <html-el:text property="customer_name_like" style="width:170px;" maxlength="60" styleId="customer_name_like" styleClass="webinput" />
	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">R3客户编码：</strong>
	          <html-el:text property="r3_code_like" size="20" style="width:90px;" maxlength="60" styleId="r3_code_like" styleClass="webinput" />
<!-- 	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">送达方：</strong> -->
<%-- 	          <html-el:text property="r3_code_sdf_like" size="20" style="width:90px;" maxlength="60" styleId="r3_code_sdf_like" styleClass="webinput" /> --%>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="but1" type="submit" name="Submit" value="搜索" />
          </td>
         
          </tr>
<!--           <tr> -->
<!--           <td nowrap="nowrap"> -->
<!-- 	          &nbsp;&nbsp;<strong class="fb">承运单位：</strong> -->
<%-- 	          <html-el:text property="trans_unit_like" size="20" style="width:120px;" maxlength="60" styleId="trans_unit_like" styleClass="webinput" /> --%>
<!-- 	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">司机姓名：</strong> -->
<%-- 	          <html-el:text property="link_name_like" size="20" style="width:90px;" maxlength="60" styleId="link_name_like" styleClass="webinput" /> --%>
<!-- 	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">司机电话：</strong> -->
<%-- 	          <html-el:text property="link_phone_like" size="20" style="width:90px;" maxlength="60" styleId="link_phone_like" styleClass="webinput" /> --%>
<!-- 	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">配送方式：</strong> -->
<%-- 		          <html-el:select property="trans_mode" style="width:90px;"> --%>
<%-- 	          		<html-el:option value="">--请选择--</html-el:option> --%>
<%-- 	          		<html-el:option value="0">自有物流</html-el:option> --%>
<%-- 	          		<html-el:option value="1">第三方物流</html-el:option> --%>
<%-- 	          		<html-el:option value="2">其他</html-el:option> --%>
<%-- 	          	 </html-el:select> --%>
<!-- 	          &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">发货状态：</strong> -->
<%-- 		          <html-el:select property="trans_detail_status" style="width:90px;"> --%>
<%-- 	          		<html-el:option value="">--请选择--</html-el:option> --%>
<%-- 	          		<html-el:option value="0">已发货</html-el:option> --%>
<%-- 	          		<html-el:option value="1">已结案</html-el:option> --%>
<%-- 	          	 </html-el:select> --%>
<!--           </td> -->
<!--           </tr> -->
          <tr>
          </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  	<div style="overflow-x:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="15%" align="center" nowrap="nowrap">确认单号</td>
        <td width="15%" align="center" nowrap="nowrap">订单号</td>
        <td width="10%" align="center" nowrap="nowrap">发货单号(details)</td>
        <td width="10%" align="center" nowrap="nowrap">客户编码</td>
        <td width="10%" align="center" nowrap="nowrap">R3单号</td>
        <td width="10%" align="center" nowrap="nowrap">物流单号</td>
         <td width="10%" align="center" nowrap="nowrap">客户名称</td>
         <td width="10%" align="center" nowrap="nowrap">机型</td>
       <td width="10%" align="center" nowrap="nowrap">确认数量</td>
       <td width="10%" align="center" nowrap="nowrap">确认人</td>
       <td width="10%" align="center" nowrap="nowrap">确认时间</td>
       
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap"> ${vs.count}</td>
             <td align="center" nowrap="nowrap">${cur.id}</td>
             <td align="center" nowrap="nowrap">${cur.map.trade_index}</td>
            <td align="center" nowrap="nowrap">${cur.map.trans_index_detail}</td>
             <td align="center" nowrap="nowrap">${cur.map.kunnr}</td>
             <td align="center" nowrap="nowrap">${cur.map.vbeln}</td>
              <td align="center" nowrap="nowrap">${cur.map.vbedl}</td>
              <td align="center" nowrap="nowrap">${cur.map.customer_name}</td>
              <td align="center" nowrap="nowrap">${cur.map.model_name}</td>
            <td align="center" nowrap="nowrap">${cur.trans_ensu_num}</td>
            <td align="center" nowrap="nowrap">${cur.trans_ensu_user}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date }" pattern="yyyy-MM-dd h:m:s"/></td>
            
<!--             <td align="center" nowrap="nowrap"> -->
<%-- 				<c:if test="${cur.map.trans_detail_status eq 0}">已发货</c:if> --%>
<%-- 	            <c:if test="${cur.map.trans_detail_status eq 1}"><font color="red">已结案</font></c:if> --%>
<!-- 			</td> -->
            
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
          <tr align="center">
          <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderInfoTransEnsu.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align:right; padding-right:5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
							var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
							pager.addHiddenInputs("method", "list");
							pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
							document.write(pager.toString());
						</script> 
            </div></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>