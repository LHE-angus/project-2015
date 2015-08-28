<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<c:if test="${report_type eq 1}">
	<c:set var="naviString" value="正常客户拜访" />
</c:if>
<c:if test="${report_type eq 2}">
	<c:set var="naviString" value="老客户重拾拜访" />
</c:if>
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link
	href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>


</head>
<body>
<div class="oarcont">
<div class="rtabcont2"><html-el:form enctype="multipart/form-data"
	action="/admin/KonkaMobileCustVisit.do" method="post">
	<html-el:hidden property="method" value="save" />
	<html-el:hidden property="visit_id" value="${af.map.visit_id}" />
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<html-el:hidden property="is_del" styleId="is_del" />
	<html-el:hidden property="report_type" value="${report_type}" />
	<html-el:hidden property="queryString" styleId="queryString" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="rtable3">
		<tr>
			<td colspan="2" align="right"><span id="sj_his"
				style="cursor: pointer;"
				onclick="doNeedMethod(null, 'KonkaMobileCustVisit.do','list' ,'report_type=${report_type}&is_del=0&mod_id=${af.map.mod_id}')">历史记录</span></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">创建日期：</td>
			<td><c:if test="${not empty today}">
      ${today}
       </c:if> <c:if test="${empty today}">
				<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss"
					var="add_date" />
       ${add_date}
        </c:if></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">开始日期：</td>
			<td><fmt:formatDate value="${af.map.begin_date}"
				pattern="yyyy-MM-dd" var="begin_date" /> <html-el:text
				property="begin_date" styleClass="webinput" styleId="begin_date"
				onclick="new Calendar(2005, 2030, 0).show(this);"
				style="cursor:pointer;text-align:center;width:100px;"
				value="${begin_date}" readonly="readonly" /></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">客户名称：</td>
			<td align="left" nowrap="nowrap"><html-el:text
				property="konkaMobileCustVisitDetail.customer_name"
				styleId="shop_name"></html-el:text> <!--    		<html-el:select property="r3_code" styleId="r3_code" value="${r3_code}">-->
			<!--	    	 <html-el:option value="">-请选择-</html-el:option>--> <!--	    		<c:forEach items="${custList}" var="cur">-->
			<!--	    		<html-el:option value="${cur.map.customer_code}">${cur.map.customer_name}</html-el:option>-->
			<!--	    		</c:forEach>--> <!--	    	</html-el:select>--></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">客户编码：</td>
			<td align="left" nowrap="nowrap" id="r3_code_show"><html-el:text
				property="konkaMobileCustVisitDetail.r3_code" styleId="r3_code"></html-el:text>
			</td>
		</tr>

		<tr>
			<td align="center" nowrap="nowrap" class="title_item">终端名称：</td>
			<td align="left" nowrap="nowrap"><html-el:text
				property="konkaMobileCustVisitDetail.shop_name" styleId="shop_name"></html-el:text>
			<!--    		<html-el:select property="shop_id" styleId="shop_id"  value="${shop_id}" >-->
			<!--	    	 <html-el:option value="">-请选择-</html-el:option>--> <!--	    		<c:forEach items="${storeList}" var="cur">-->
			<!--	    		  <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>-->
			<!--	    		</c:forEach>--> <!--	    	</html-el:select>--></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">拜访类型：</td>
			<td align="left" nowrap="nowrap"><c:forEach
				items="${visitTypeList}" var="visttype" varStatus="status">
				<label for="pay_type_${status.count}"> <input
					type="checkbox" name="visit_type_id"
					<c:if  test="${not empty visttype.map.checked}">${visttype.map.checked}</c:if>
					id="visit_type_${visttype.visit_type_id}"
					value="${visttype.visit_type_id}" />
				&nbsp;&nbsp;${visttype.visit_type_name}</label>&nbsp;&nbsp;
   <c:if test="${status.count % 3 eq 0}">
					<br />
				</c:if>
			</c:forEach></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">反馈问题：</td>
			<td align="left" nowrap="nowrap"><html-el:textarea
				property="feed_list" styleId="feed_list"
				style="width:50%;height:60px;" /></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">拜访说明：</td>
			<td align="left" nowrap="nowrap"><html-el:textarea
				property="visit_desc" styleId="visit_desc"
				style="width:60%;height:60px;" /></td>

		</tr>
		
		<c:if test="${report_type eq 1}">
			<tr>
				<td align="center" nowrap="nowrap" class="title_item">国产排名：</td>
				<td align="left" nowrap="nowrap"><html-el:text
					property="domestic_ranking" styleId="domestic_ranking"></html-el:text>
				</td>
			</tr>
			<tr>
				<td align="center" nowrap="nowrap" class="title_item">零售量：</td>
				<td align="left" nowrap="nowrap"><html-el:text
					property="consumer_sales" styleId="consumer_sales"></html-el:text>
				</td>
			</tr>
			<tr>
				<td align="center" nowrap="nowrap" class="title_item">零售额：</td>
				<td align="left" nowrap="nowrap"><html-el:text
					property="retail_sales" styleId="retail_sales"></html-el:text></td>
			</tr>
		</c:if>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">被访人：</td>
			<td align="left" nowrap="nowrap"><html-el:text
				property="consumer_name" styleId="consumer_name"></html-el:text></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">被访人电话：</td>
			<td align="left" nowrap="nowrap"><html-el:text
				property="consumer_phone" styleId="consumer_phone"></html-el:text></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">拜访状态：</td>
			<td align="left" nowrap="nowrap"><c:if
				test="${af.map.state eq 0}">需跟踪</c:if> <c:if
				test="${af.map.state eq 1}">已关闭</c:if></td>
		</tr>
		<tr>
			<td class="title_item" align="center" nowrap="nowrap">附件：</td>
			<td><c:forEach items="${af.map.attachmentsList}" var="cur">
			 <li><a href="${ctx}/${cur.save_path}" target="_blank">${cur.file_name}</a>
			 &nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}"></a></li>
			</c:forEach></td>
		</tr>
		<tr>
			<td width="20%"></td>
			<td> <input class="but5" type="button"
				name="Submit5" value="返回" onclick="history.back();return false;" /></td>
		</tr>
	</table>
</html-el:form>
<div style="height: 50px;"></div>
</div>

<div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>

<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("input[type!='button']").each(function(){ this.disabled=true; }); 
	
	<c:forEach items="${konkaMobileCustVisitTypeList}" var="cur">
	$("#visit_type_${cur.visit_type_id}").attr("checked",true);
</c:forEach>
});
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
