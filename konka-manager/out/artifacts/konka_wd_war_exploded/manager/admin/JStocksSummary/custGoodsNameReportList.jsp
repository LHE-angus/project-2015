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
      <html-el:hidden property="method" value="custGoodsNameReportList" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="type" value="${af.map.type}" />
      <html-el:hidden property="isfirst" value="${af.map.isfirst}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
		<tr>
			<td align="right">分公司：</td>
			<td align="left">
			<html-el:select property="dept_id" styleId="dept_id" >
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${deptList}" var="cur">
	    		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	    		</c:forEach>
	    	</html-el:select>
			</td>
			<td align="right">客户类型：</td>
			<td align="left">
			<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select> 
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
			</td>
			<td align="right">r3编码：</td>
			<td align="left"><html-el:text property="r3_code_like" size="15" maxlength="20" styleId="r3_code_like" /></td>
			<td align="right">客户名称：</td>
			<td align="left"><html-el:text property="customer_name_like" size="15" maxlength="20" styleId="customer_name_like" /></td>
		</tr>
		<tr>
		    <td align="right">型号：</td>
			<td align="left"><html-el:text property="goods_name_like" size="15" maxlength="20" styleId="goods_name_like" /></td>
			<td align="right">业务员：</td>
			<td align="left"><html-el:text property="user_name_like" size="15" maxlength="20" styleId="user_name_like" /></td>
			<td align="right">时间段</td>
			<td align="left">
				  <html-el:select property="begin_year" styleId="begin_year" style="width:65px">
                   <html-el:option value="">-年份-</html-el:option>
                   <c:forEach begin="2014" end='2020' varStatus="vs" step="1">
		      		   <html-el:option value="${vs.current }">${vs.current }年</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
	    	  <html-el:select property="begin_month" styleId="begin_month" style="width:60px">
	    	       <html-el:option value="">-月份-</html-el:option>
	    	       <c:forEach begin="1" end='12' varStatus="vs" step="1">
		       	       <html-el:option value="${vs.current }">${vs.current }月</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
	    	  &nbsp;至&nbsp;
	    	  <html-el:select property="end_year" styleId="end_year" style="width:65px">
                   <html-el:option value="">-年份-</html-el:option>
                   <c:forEach begin="2014" end='2020' varStatus="vs" step="1">
		      		   <html-el:option value="${vs.current }">${vs.current }年</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
              <html-el:select property="end_month" styleId="end_month" style="width:60px">
	    	       <html-el:option value="">-月份-</html-el:option>
	    	       <c:forEach begin="1" end='12' varStatus="vs" step="1">
		       	       <html-el:option value="${vs.current }">${vs.current }月</html-el:option>
                   </c:forEach>
	    	  </html-el:select>
			</td>
			
			<td align="center" colspan="2">
			     <input type="button" value="导出" id="export_22" class="but_excel" style="margin-right: 10px;"/>
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
   <html-el:form action="/admin/KonkaPartership">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
		<tr class="tabtt1">
			<td nowrap="nowrap" align="center" rowspan="2">序号</td>
			<td nowrap="nowrap" align="left" rowspan="2">分公司</td>
			<td nowrap="nowrap" align="left" rowspan="2">客户大类</td>
		    <td nowrap="nowrap" align="left" rowspan="2">客户小类</td>
		    <td nowrap="nowrap" align="left" rowspan="2">客户编码</td>
			<td nowrap="nowrap" align="left" rowspan="2">客户</td>
			<td nowrap="nowrap" align="left" rowspan="2">机型</td>
			<td nowrap="nowrap" align="left" rowspan="2">业务员</td>

			<td nowrap="nowrap" align="center" colspan="2">期初库存</td>
			<td nowrap="nowrap" align="center" colspan="2">本期进货</td>
			<td nowrap="nowrap" align="center" colspan="2">本期零售</td>
			<td nowrap="nowrap" align="center" colspan="2">期末库存</td>
			<td nowrap="nowrap" align="center" colspan="2">周转天数</td>
			
		</tr>
		<tr class="tabtt1">
			<td nowrap="nowrap" align="right">库存量</td>
			<td nowrap="nowrap" align="right">库存额</td>
			<td nowrap="nowrap" align="right">进货量</td>
			<td nowrap="nowrap" align="right">进货额</td>
			<td nowrap="nowrap" align="right">零售量</td>
			<td nowrap="nowrap" align="right">零售额</td>
			<td nowrap="nowrap" align="right">数量</td>
			<td nowrap="nowrap" align="right">金额</td>
			<td nowrap="nowrap" align="right">按量</td>
			<td nowrap="nowrap" align="right">按额</td>
		</tr>
		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			<tr>
				<td align="center" nowrap="nowrap">${(af.map.pager.currentPage- 1) * af.map.pager.pageSize + vs.count}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.DEPT_NAME)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.PAR_CUSTOMER_TYPE_NAME)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.CUSTOMER_TYPE_NAME)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.R3_CODE)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.CUSTOMER_NAME)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.GOODS_NAME)}</td>
				<td align="left" nowrap="nowrap">${fn:escapeXml(cur.YWY_USER_NAME)}</td>

				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.INIT_NUM)}</td>
				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.INIT_MONEY)}</td>
				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.COME_NUM)}</td>
				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.COME_MONEY)}</td>
				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.OUT_NUM)}</td>
				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.OUT_MONEY)}</td>
				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.LAST_NUM)}</td>
				<td nowrap="nowrap" align="right">${fn:escapeXml(cur.LAST_MONEY)}</td>
				<td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.DAY_NUM}" pattern="0.00" /></td>
				<td nowrap="nowrap" align="right"><fmt:formatNumber value="${cur.DAY_MONEY}" pattern="0.00" /></td>
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
            pager.addHiddenInputs("method", "custGoodsNameReportList");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");	
            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");	
            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");	
            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");	
            pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");		
            pager.addHiddenInputs("goods_name_like", "${af.map.goods_name_like}");	
            pager.addHiddenInputs("goods_name_type", "${af.map.goods_name_type}");
            	
            pager.addHiddenInputs("begin_year", "${af.map.begin_year}");	
            pager.addHiddenInputs("begin_month", "${af.map.begin_month}");	
            pager.addHiddenInputs("end_year", "${af.map.end_year}");
            pager.addHiddenInputs("end_month", "${af.map.end_month}");
            
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

	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});
	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
	
});

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
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>