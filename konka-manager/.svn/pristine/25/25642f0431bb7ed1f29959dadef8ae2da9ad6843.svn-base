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
    <html-el:form action="/admin/JStocksSummary">
      <html-el:hidden property="method" value="custRepertoryReportList" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="type" value="${af.map.type}" />
      <html-el:hidden property="weekDayTime" value="${af.map.weekDayTime}" />
      <html-el:hidden property="weekNum" value="${af.map.weekNum}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
		<tr>
			<td align="right">分公司：</td>
			<td align="left">
			<html-el:select property="dept_id" styleId="dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select> 
			<html-el:select property="l4_dept_id" styleId="l4_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>
			<html-el:select property="l5_dept_id" styleId="l5_dept_id">
				<html-el:option value="">-请选择-</html-el:option>
			</html-el:select>
			</td>
			<td align="right">r3编码：</td>
			<td align="left"><html-el:text property="r3_code_like" size="15" maxlength="20" styleId="r3_code_like" /></td>
			<td align="right">客户名称：</td>
			<td align="left"><html-el:text property="customer_name_like" size="15" maxlength="20" styleId="customer_name_like" /></td>
			<td align="right">客户类型：</td>
			<td align="left">
			<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select> 
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
			</td>
		</tr>
		<tr>
			<td align="right">业务员：</td>
			<td align="left"><html-el:text property="ywy_user_name_like" size="15" maxlength="20" styleId="ywy_user_name_like" /></td>
			<td align="right">型号分类</td>
			<td align="left">
			 <html-el:select property="goods_name_type" styleId="goods_name_type">
			          <html-el:option value="">--请选择--</html-el:option>
		              <html-el:option value="1">新品</html-el:option>
		              <html-el:option value="2">主销</html-el:option>
		              <html-el:option value="3">退市</html-el:option>
		              <html-el:option value="4">停产</html-el:option>
		     </html-el:select>
			</td>
			<td align="right">型号：</td>
			<td align="left"><html-el:text property="goods_name_like" size="15" maxlength="20" styleId="goods_name_like" /></td>
			<td align="right">预警状态：</td>
			<td align="left">
			    <html-el:select property="yujing" styleId="yujing">
		              <html-el:option value="0">全部</html-el:option>
		              <html-el:option value="1">超出</html-el:option>
		              <html-el:option value="2">短缺</html-el:option>
		            </html-el:select>
		            &nbsp&nbsp&nbsp
		             <html-el:submit styleClass="but1" value="搜索" />
			</td>
		</tr>
      </table>
    </html-el:form>
  </div>
  <div>
  </div>
  <div class="rtabcont1" style="overflow-x: auto;">
   <html-el:form action="/admin/JStocksSummary">
   <html-el:hidden property="method" value="pushMessages"/>
      <html-el:hidden property="pager.requestPage" value="${af.map.pager.currentPage}"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr>
      <td colspan="18" align="right">
          <input type="button" value="导出" id="export_22" class="but_excel" style="margin-right: 10px;"/>
          <input type="submit" value="推送" id="bgButtonAdd" class="bgButtonAdd" style="margin-right: 10px;" />
     </td>
     </tr>
        <tr class="tabtt1">
          <td width="3%" align="center" nowrap="nowrap"> <input name="chkAll" type="checkbox" id="chkAll" value="-1" onClick="checkAll(this);" /></td>
          <td width="5%" align="center">序号</td>
          <td nowrap="nowrap" align="left" width="8%">分公司</td>
          <td nowrap="nowrap" align="left" width="8%">部门</td>
          <td nowrap="nowrap" align="left">经办</td>
          <td nowrap="nowrap" align="left" width="8%">R3编码</td>
          <td nowrap="nowrap" align="left">客户名称</td>
          <td nowrap="nowrap" align="left">客户类型</td>
          <td nowrap="nowrap" align="left">细分类型</td>
          <td nowrap="nowrap" align="left">业务员</td>
          <td nowrap="nowrap" align="left" width="6%">型号分类</td>
          <td nowrap="nowrap" align="left" width="8%">型号</td>
          <td nowrap="nowrap" align="right" width="8%">前四周销量</td>
          <td nowrap="nowrap" align="right" width="6%">最高存量</td>
          <td nowrap="nowrap" align="right" width="8%">最低存量</td>
          <td nowrap="nowrap" align="right" width="6%">当前库存</td>
          <td nowrap="nowrap" align="left" width="8%">预警状态</td>
          <td nowrap="nowrap" align="center" width="8%">操作</td>
        </tr>
        
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
           <td align="center" nowrap="nowrap">
           <c:choose>  
			    <c:when test="${(cur.TOTLE_CUR_NUM gt cur.TOTAL_NUM) or (cur.TOTLE_CUR_NUM lt cur.AVG_NUM)}">  
			        <input name="pks" type="checkbox" id="pks" value="${cur.R3_CODE}#${cur.GOODS_NAME}" />
			    </c:when>  
			   <c:otherwise>  
			       <input name="pks" type="checkbox" id="pks" disabled="disabled" value="${cur.R3_CODE}#${cur.GOODS_NAME}" />
			    </c:otherwise>  
			</c:choose> 
            </td>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.DEPT_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.L4_DEPT_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.CUR_DEPT_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.R3_CODE)}</td>
            <td align="left" nowrap="nowrap">${cur.CUSTOMER_NAME}</td>
            <td align="left" nowrap="nowrap">${cur.PAR_CUSTOMER_TYPE_NAME}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.CUSTOMER_TYPE_NAME)}</td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.YWY_USER_NAME)}</td>
            <td align="left" nowrap="nowrap">
               <c:if test="${fn:escapeXml(cur.GOODS_NAME_TYPE) eq 1}">新品</c:if>
               <c:if test="${fn:escapeXml(cur.GOODS_NAME_TYPE) eq 2}">主销</c:if>
               <c:if test="${fn:escapeXml(cur.GOODS_NAME_TYPE) eq 3}">退市</c:if>
               <c:if test="${fn:escapeXml(cur.GOODS_NAME_TYPE) eq 4}">停产</c:if>
            </td>
            <td align="left" nowrap="nowrap">${fn:escapeXml(cur.GOODS_NAME)}</td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.TOTAL_NUM)}</td>
             <td align="right" nowrap="nowrap">${fn:escapeXml(cur.TOTAL_NUM)}</td>
            <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.AVG_NUM}" pattern="0.0" /></td>
            <td align="right" nowrap="nowrap">${fn:escapeXml(cur.TOTLE_CUR_NUM)}</td>
            <td align="left" nowrap="nowrap">
	            <c:if test="${cur.TOTLE_CUR_NUM lt cur.AVG_NUM}"><label style="color: red;">赶紧补货</label></c:if>
	            <c:if test="${cur.TOTLE_CUR_NUM gt cur.TOTAL_NUM}"><label style="color: green;">库存超了</label></c:if>
            </td>
            <td align="center" nowrap="nowrap">
             <c:if test="${(cur.TOTLE_CUR_NUM gt cur.TOTAL_NUM) or (cur.TOTLE_CUR_NUM lt cur.AVG_NUM)}">
                <a style="color: blue;" href="javascript:void();" onclick="pushMessageOne('${cur.GOODS_NAME}','${cur.R3_CODE}')">推送</a>
             </c:if>
             </td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}">
        <tr>
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
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
      </table>
    </html-el:form>
    <br />
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JStocksSummary.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "custRepertoryReportList");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
            pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");	
            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");	
            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");	
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");	
            pager.addHiddenInputs("ywy_user_name_like", "${af.map.ywy_user_name_like}");		
            pager.addHiddenInputs("goods_name_like", "${af.map.goods_name_like}");	
            pager.addHiddenInputs("goods_name_type", "${af.map.goods_name_type}");	
            pager.addHiddenInputs("yujing", "${af.map.yujing}");
            pager.addHiddenInputs("weekDayTime", "${af.map.weekDayTime}");	
            pager.addHiddenInputs("weekNum", "${af.map.weekNum}");
            document.write(pager.toString());
            </script>
            </td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){   

	//分公司初始化
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	
	$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId&isNotEpp=isNotEpp", "par_id", "0", false);
	$("#dept_id").change();

	
	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
	
});

//批量推送
function pushMessageMary(){
	var all_goods_name=$(":checkbox").attr();
	for(var i=0;i<all_goods_name.length;i++){
          alert(all_goods_name[i].val());
	}
}

//单个推送信息
function pushMessageOne(goods_name,r3_code){
	
     var param='method=pushMessage';
    	if (goods_name != null && goods_name != '') {
    	    param += '&goods_name='+goods_name;
    	}
    	if (r3_code != null && r3_code != '') {
    	    param += '&r3_code='+r3_code;
    	}
	 $.ajax({
		   type: "POST",
		   url: "${ctx}/manager/admin/JStocksSummary.do",
		   data: param,
		   success: function(data){
		     alert(data.res );
		   }
	 });
}

//导出
$("#export_22").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#bottomPageForm").append("<input type='hidden' name='excel_to_all' value='1' />");
		$("#bottomPageForm").submit();	
	}
});                                     
function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+]?\d+(?:\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^$/))this.value=0;this.o_value=this.value};
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>