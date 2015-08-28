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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaOrderInfoTransDetails">
      <html-el:hidden property="method" value="listTZD" />
      <html-el:hidden property="queryString" styleId="queryString"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">
          &nbsp; &nbsp; <strong class="fb">分公司：</strong>
              <html-el:select property="dept_id" styleId="dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l4_dept_id" styleId="l4_dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>
              <html-el:select property="l5_dept_id" styleId="l5_dept_id">
                <html-el:option value="">-请选择-</html-el:option>
              </html-el:select>   
<!--           &nbsp;&nbsp;<strong class="fb">发货状态：</strong> -->
<%--             	<html-el:select property="trans_status" styleId="trans_status"> --%>
<%--                 	<html-el:option value="">-请选择-</html-el:option> --%>
<%--                 	<html-el:option value="0">未发货</html-el:option> --%>
<%--                 	<html-el:option value="1">发货中</html-el:option> --%>
<%--                 	<html-el:option value="2">已发货</html-el:option> --%>
<%--                 	<html-el:option value="3">已结案</html-el:option> --%>
<%--               	</html-el:select> --%>
          &nbsp;&nbsp;<strong class="fb">发货时间：</strong>
	          <input name="trans_date_s" id="trans_date_s" size="12" value="${af.map.trans_date_s}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'trans_date_e\')}'})" />
	            	    至
	          <input name="trans_date_e" id="trans_date_e" size="12" value="${af.map.trans_date_e}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'trans_date_s\')||\'2013-11-01\'}'})" />
	          
            </td>
       </tr>
       <tr>
          <td nowrap="nowrap">
            &nbsp;&nbsp;<strong class="fb">R3物流单号：</strong>
             <html-el:text property="r3_vbedl_like" size="20" maxlength="20" styleId="r3_vbedl_like" styleClass="webinput" />
            &nbsp;&nbsp;<strong class="fb">R3订单号：</strong>
             <html-el:text property="vbeln_like" size="20" maxlength="20" styleId="vbeln_like" />
            &nbsp;&nbsp;<strong class="fb">交易流水号：</strong>
             <html-el:text property="trade_index_like" maxlength="30" styleId="trade_index_like" style="width:170px"/>
<!--             &nbsp;&nbsp;<strong class="fb">发货单号：</strong>   -->
<%--              <html-el:text property="trans_index_like" size="20"  maxlength="20" styleId="trans_index_like" styleClass="webinput" /> --%>
         </td>
        </tr>
        <tr>
          <td nowrap="nowrap">
            &nbsp;&nbsp;<strong class="fb">客户名称：</strong>
             <html-el:text property="customer_name_like" size="20" maxlength="60" styleId="customer_name_like" styleClass="webinput" />
            &nbsp;&nbsp;<strong class="fb">R3客户编码：</strong>
             <html-el:text property="r3_code_like" size="20" maxlength="20" styleId="r3_code_like" styleClass="webinput" />
            &nbsp;&nbsp;<strong class="fb">送达方：</strong>
             <html-el:text property="we_like" size="20" maxlength="20" styleId="we_like" styleClass="webinput" />
            &nbsp;&nbsp;<strong class="fb">配送方式：</strong>  
             	<html-el:select property="send_type" styleId="send_type">
                	<html-el:option value="">-请选择-</html-el:option>
                	<html-el:option value="1">自提</html-el:option>
                	<html-el:option value="2">配送</html-el:option>
              	</html-el:select>
         </td>
        </tr>
        <tr>
          <td nowrap="nowrap">
            &nbsp;&nbsp;<strong class="fb">收货人姓名：</strong>
             <html-el:text property="user_name_like" size="20" maxlength="60" styleId="trans_recl_user_like" styleClass="webinput" />
            &nbsp;&nbsp;<strong class="fb">收货人电话：</strong>
             <html-el:text property="user_phone_like" size="20" maxlength="20" styleId="trans_recl_user_phone_like" styleClass="webinput" />
	        &nbsp;&nbsp;<strong class="fb">收货人地址：</strong>
             <html-el:text property="user_addr_like" size="20" maxlength="60" styleId="trans_recl_addr_like" styleClass="webinput" />
            &nbsp;&nbsp;<html-el:submit styleClass="but1" value="搜索" />  
         </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp" %>

    <div class="rtabcont1" style="overflow-x:auto;">
      <form id="listForm" name="listForm" method="post" action="KonkaOrderInfoTrans.do?method=add">
<!--         <input type="hidden" name="method" id="method" value="listTZD" /> -->
        <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="dept_sn" value="${af.map.dept_sn}" />
        
        <input class="but8" style="width:60px;" id="syncBtnAll" value="同步物流"></input>
        <input class="but_excel" type="button" name="button3" id="button3" value="发货" onClick="confirmOperateAll('确认发货吗?', 'add',this.form);" />
        <div style="overflow-x:auto;">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tr class="tabtt1">
            <td width="3%" align="center" nowrap="nowrap">
            <input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" />
            </td>
            <td nowrap="nowrap" align="center" width="8%">操作</td>
            <td nowrap="nowrap" align="center" width="8%">序号</td>
            <td nowrap="nowrap" align="center" width="8%">R3物流单号</td>
            <td nowrap="nowrap" align="center" width="8%">R3订单号</td>
            <td nowrap="nowrap" align="center" width="8%">交易流水号</td>
            <td nowrap="nowrap" align="center" width="8%">客户名称</td>
            <td nowrap="nowrap" align="center" width="8%">R3客户编码</td>
            <td nowrap="nowrap" align="center" width="8%">送达方编码</td>
            <td nowrap="nowrap" align="center" width="8%">订单数量</td>
            <td nowrap="nowrap" align="center" width="8%">订单金额</td>
            <td nowrap="nowrap" align="center" width="8%">R3发货数量</td>
<!--             <td nowrap="nowrap" align="center" width="8%">发货状态</td> -->
            <td nowrap="nowrap" align="center" width="8%">发货时间</td>
            <td nowrap="nowrap" align="center" width="8%">发货单数量</td>
<!--             <td nowrap="nowrap" align="center" width="8%">发货单号</td> -->
            <td nowrap="nowrap" align="center" width="8%">配送方式</td>
            <td nowrap="nowrap" align="center" width="8%">收货人姓名</td>
            <td nowrap="nowrap" align="center" width="8%">收货人手机</td>
            <td nowrap="nowrap" align="center" width="8%">收货人地址</td>
                            
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
           <tr>
	           	<td align="center" nowrap="nowrap">
	           	<c:if test="${cur.map.good_count > cur.map.trans_num}">
	            	<input name="pks" type="checkbox" id="pks" value="${cur.map.trade_index}" />
	            	</c:if>
	            	<c:if test="${cur.map.good_count <= cur.map.trans_num}">
               		<input name="pks" type="checkbox" id="pks" value="${cur.map.trade_index}" disabled="disabled"/>
               </c:if>
	            </td>
	           <td nowrap="nowrap" align="center">
<%-- 	            <c:if test="${cur.map.order_num > cur.map.num_count}"> --%>
<%-- 	                        <a style="font-color:green;" onclick="doNeedMethod(null,'../admin/KonkaOrderInfoTrans.do','add','id=${cur.map.zsof_id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize());"> --%>
<!-- 	                                              发货 -->
<!-- 	                        </a> -->
<%-- 	             </c:if> --%>
	           <c:if test="${cur.map.good_count > cur.map.trans_num}">
	           		<span style="cursor:pointer;font-color:green;" class="fblue" onClick="doNeedMethod(null, '${ctx}/manager/admin/KonkaOrderInfoTrans.do','add','trade_index=${cur.map.trade_index}&mod_id=${af.map.mod_id}')">发货 </span>
               </c:if>
               <c:if test="${cur.map.good_count <= cur.map.trans_num}">发货</c:if>
	           </td>
	           <td nowrap="nowrap" align="center">${vs.count}</td>
	           <td nowrap="nowrap" align="center">${cur.map.vbedl}</td>
	           <td nowrap="nowrap" align="center">${cur.map.vbeln}</td>
	           <td nowrap="nowrap" align="center">${cur.map.trade_index}</td>
	           <td nowrap="nowrap" align="left">${cur.map.name1}</td>
	           <td nowrap="nowrap" align="center">${cur.map.kunnr}</td>
	           <td nowrap="nowrap" align="center">${cur.map.we}</td>
	           <td nowrap="nowrap" align="center">${cur.map.good_count}</td>
	           <td nowrap="nowrap" align="center">${cur.map.good_sum_price}</td>
	           <td nowrap="nowrap" align="center">${cur.map.trans_num}</td>
<!-- 	           <td nowrap="nowrap" align="center">发货状态</td> -->
	           <td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.map.trans_date}" pattern="yyyy-MM-dd"/> </td>
	           <td nowrap="nowrap" align="center">${cur.map.trans_sum_nums}</td>
<!-- 	           <td nowrap="nowrap" align="center">发货单号</td> -->
	           <td nowrap="nowrap" align="center">
	           		<c:if test="${cur.map.send_type eq 1}">自提</c:if>
	           		<c:if test="${cur.map.send_type eq 2}">配送</c:if>
	           </td>
	           <td nowrap="nowrap" align="center">${cur.map.user_name}</td>
	           <td nowrap="nowrap" align="center">${cur.map.user_phone}</td>
	           <td nowrap="nowrap" align="center">
	          <c:set var="user_addr" value="${cur.map.user_addr}" />
	           <c:choose>  
                <c:when test="${fn:length(user_addr) > 10}"><c:out value="${fn:substring(user_addr, 0, 10)}.." /></c:when>  
			   <c:otherwise><c:out value="${user_addr}" /></c:otherwise>  
			 </c:choose>  
	           </td>
           </tr>
          </c:forEach>
        </table>
        </div>
      </form>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderInfoTransDetails.do">
     <html-el:hidden property="method" value="listTZD" />
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
            pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
            pager.addHiddenInputs("trans_status", "${af.map.trans_status}");
            pager.addHiddenInputs("trans_date_s", "${af.map.trans_date_s}");
            pager.addHiddenInputs("trans_date_e", "${af.map.trans_date_e}");
            pager.addHiddenInputs("r3_vbedl_like", "${af.map.r3_vbedl_like}");
            pager.addHiddenInputs("vbeln_like", "${af.map.vbeln_like}");
            pager.addHiddenInputs("trade_index_like", "${af.map.trade_index_like}");
//             pager.addHiddenInputs("trans_index_like", "${af.map.trans_index_like}");
            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
            pager.addHiddenInputs("we_like", "${af.map.we_like}");
            pager.addHiddenInputs("send_type", "${af.map.send_type}");
            pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
            pager.addHiddenInputs("user_phone_like", "${af.map.user_phone_like}");
            pager.addHiddenInputs("user_addr_like", "${af.map.user_addr_like}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    </div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>   
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
});


$("#syncBtnAll").click(function(){
	var isExecute = doSyncMethod("此操作用于同步R3物流数据,确认操作?", 'KonkaOrderSearch.do','ZbSync','&mod_id=${af.map.mod_id}' ,$('#bottomPageForm').serialize());
	if( isExecute == true){
		$("#syncBtnAll").attr("disabled","disabled");
		$("font").text("数据同步中...").css("color","red");
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
}

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>