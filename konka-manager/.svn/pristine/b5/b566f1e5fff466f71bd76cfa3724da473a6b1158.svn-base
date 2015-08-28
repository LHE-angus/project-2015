<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/css/common.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link
	href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>


</head>
<body>
<div class="oarcont">
<div class="rtabcont2"><html-el:form enctype="multipart/form-data"
	action="/admin/KonkaMobileTestDataQuery.do" method="post">
	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<html-el:hidden property="queryString" styleId="queryString" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="rtable3">
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">创建日期：</td>
			<td><fmt:formatDate value="${af.map.report_date}"
				pattern="yyyy/MM/dd" /></td>
				
			<td align="center" nowrap="nowrap" class="title_item">分公司：</td>
			<td><html-el:text property="subcomp_name" styleId="subcomp_name"></html-el:text>
			</td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">经办：</td>
			<td><html-el:text property="office_name" styleId="office_name"></html-el:text></td>
			
			<td align="center" nowrap="nowrap" class="title_item">客户名称：</td>
			<td><html-el:text property="channel_a_name" styleId="channel_a_name"></html-el:text>
			</td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">客户R3编码：</td>
			<td><html-el:text property="channel_b_name" styleId="channel_b_name"></html-el:text>
			</td>
			
			<td align="center" nowrap="nowrap" class="title_item">门店R3编码 ：</td>
			<td><html-el:text property="map.store_r3_sn" styleId="store_r3_sn"></html-el:text></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">客户类型：</td>
			<td><html-el:text property="map.c_comm" styleId="c_comm"></html-el:text></td>
			
			<td align="center" nowrap="nowrap" class="title_item">细分类型：</td>
			<td align="left" nowrap="nowrap"><html-el:text property="map.c_name" styleId="map.c_name"></html-el:text></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">上报人：</td>
			<td><html-el:text property="report_name" styleId="report_name"></html-el:text></td>
			
			<td align="center" nowrap="nowrap" class="title_item">上报门店：</td>
			<td><html-el:text property="dept_name" styleId="dept_name"></html-el:text></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">尺寸：</td>
			<td><html-el:text property="measure_name" styleId="measure_name"></html-el:text></td>
			
			<td align="center" nowrap="nowrap" class="title_item">产品型号：</td>
			<td align="left" nowrap="nowrap"><html-el:text property="model_name"
				styleId="model_name"></html-el:text></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">数量：</td>
			<td align="left" nowrap="nowrap"><html-el:text property="num"
				styleId="num"></html-el:text></td>
				
				<td align="center" nowrap="nowrap" class="title_item">挂牌价：</td>
			<td align="left" nowrap="nowrap"><span class="kz-price-12">
            <fmt:formatNumber value="${af.map.price}" type="currency" />
            </span></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">上样时间：</td>
			<td align="left" nowrap="nowrap"><fmt:formatDate
				value="${af.map.up_date}" pattern="yyyy-MM-dd" /></td>	
				
				<td align="center" nowrap="nowrap" class="title_item">下架时间：</td>
			<td align="left" nowrap="nowrap"><fmt:formatDate
				value="${af.map.down_date}" pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td align="center" nowrap="nowrap" class="title_item">状态：</td>
		    <td align="left" nowrap="nowrap">
		      <c:if test="${not empty downDate}">
		          <c:if test="${now lt downDate}"><span style="color:#009900;">上架中</span></c:if>
		          <c:if test="${now ge downDate}"><span style="color:#CD0000;">已下架</span></c:if>
              </c:if>
              <c:if test="${empty downDate}"> 
          	 <span style="color:#009900;">上架中</span>
          </c:if>
          </td>
			
			<td class="title_item" align="center" nowrap="nowrap">总销售量：</td>
			<td align="left" nowrap="nowrap"><html-el:text property="map.all_num"
				styleId="all_num"></html-el:text></td>
		</tr>
		<tr>
			<td class="title_item" align="center" nowrap="nowrap">总销售额：</td>
			<td align="left" nowrap="nowrap">
			  <span class="kz-price-12"><fmt:formatNumber value="${af.map.map.all_price_1}" type="currency" /> </span>
			</td>
		</tr>
		<tr>
			<td class="title_item" align="center" nowrap="nowrap">备注：</td>
			<td><html-el:textarea
				property="memo" styleId="memo" styleClass="webinput"
				style="width:70%;height:60px;" /></td>
		</tr>
		<tr>
			<td class="title_item" align="center" nowrap="nowrap">附件：</td>
			<td>
			<!--<table>
				<tr>
					<td>正面</td>
					<td><a href="${ctx}/${af.map.front.save_path}">${af.map.front.file_name}</a></td>
				</tr>
				<tr>
					<td>背面</td>
					<td><a href="${ctx}/${af.map.back.save_path}">${af.map.back.file_name}</a></td>
				</tr>
				<tr>
					<td>侧面</td>
					<td><a href="${ctx}/${af.map.side.save_path}">${af.map.side.file_name}</a></td>
				</tr>
			</table>
			--><div id="fj"></div>
			</td>
		</tr>
		<tr>
			<td width="20%"></td>
			<td><input class="but5" type="button" name="Submit5" value="返回"
				onclick="history.back();return false;"/>
            </td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.imgAddShow.js"></script>

<script type="text/javascript">
$(document).ready(function(){

	  //附件
	 $("#fj").imgshow({
		ctx:"${ctx}",
		data:jQuery.parseJSON('${fj_paths}')
       });
});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
