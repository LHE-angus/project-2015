<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>R3订单查看页面</title>
<script type="text/javascript" src="../../commons/scripts/jquery.js"></script>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置>>R3订单查看
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/OrderTest.do">
			<html-el:hidden property="method" value="list" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
				<tr>
				    <td>
				    	<label>订单编号</label>
						<html-el:text property="r3_order_no" />
						<label>客户名称</label>
						<html-el:text property="customer_name" />
						<!-- 使用submit的时候就是提交,不要被下面的jquery请求冲掉参数了 -->
				   		<input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索" />
				    </td>
				    
				</tr>
			</table>
			</html-el:form>
<%-- 			<html-el:link href="${ctx}/webservice/R3InterfaceTest.do?method=list1"><font color="blue" >R3客户接口测试</font></html-el:link> --%>
<%-- 			<html-el:link href="${ctx}/webservice/R3InterfaceTest.do?method=list2"><font color="blue" >R3客户合作伙伴接口测试</font></html-el:link> --%>
		</div>
		<div class="rtabcont1">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="tableClass">
				<tr>
					
					<th width="5%" nowrap="nowrap">序号</th>
					<th width="5%" nowrap="nowrap">订单编号</th>
					<th width="8%" nowrap="nowrap">年份</th>
					<th width="8%" nowrap="nowrap">客户编码</th>
					<th width="8%" nowrap="nowrap">客户名称</th>
					<th width="8%" nowrap="nowrap">紧急程度</th>
					<th width="8%" nowrap="nowrap">收货 人</th>
					<th width="8%" nowrap="nowrap">物料类型</th>
					<th width="8%" nowrap="nowrap">周转物料类型</th>
					<th width="8%" nowrap="nowrap">建议承运商</th>
					<th width="8%" nowrap="nowrap">发货方式</th>
					<th width="8%" nowrap="nowrap">发货时间</th>
					<th width="8%" nowrap="nowrap">总金额</th>
					<th width="8%" nowrap="nowrap">详情</th>
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.r3_order_no}</td>
				      <td align="center" nowrap="nowrap" >${cur.r3_order_year}</td>
				      <td align="center" nowrap="nowrap" >${cur.customer_code}</td>
				      <td align="center" nowrap="nowrap" >${cur.customer_name}</td>
				      <td align="center" nowrap="nowrap" >${cur.emergency_level}</td>
				      <td align="center" nowrap="nowrap" >${cur.recipient}</td>
				      <td align="center" nowrap="nowrap" >${cur.material_type}</td>
				      <td align="center" nowrap="nowrap" >${cur.turnover_material_type}</td>
				      <td align="center" nowrap="nowrap" >${cur.suggest_carrier}</td>
				      <td align="center" nowrap="nowrap" >${cur.delivery_type}</td>
				      <td align="center" nowrap="nowrap" > <fmt:formatDate value="${cur.ship_date }" pattern="yyyy-MM-dd" /></td>
				      <td align="center" nowrap="nowrap" >${cur.total_money}</td>
				      <td align="center" nowrap="nowrap" ><a href="${ctx}/webservice/OrderTest.do?method=edit&r3_order_header_id=${cur.r3_order_header_id}" >查看明细${cur.r3_order_header_id}</a></td>
				    </tr>
				  </c:forEach>
			</table>
		</div>


	<div class="rtabcont3">
	<span></span>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="OrderTest.do">
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script> 
          <script type="text/javascript">
		       var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		       pager.addHiddenInputs("method", "list");
		       pager.addHiddenInputs("r3_order_no", "${af.map.r3_order_no}");
		       pager.addHiddenInputs("customer_name", "${af.map.customer_name}");
		       document.write(pager.toString());
		       </script>
		  </td>
        </tr>
      </table>
    </form>
	</div>
</div>
	
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>

</body>
</html>