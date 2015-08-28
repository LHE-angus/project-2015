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
<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
      <c:if test="${pe_prod_user_session.user_type eq 0}"><c:set value="true" var="is_admin" /></c:if>
     <div align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable4">
         <tr>
         	<td colspan="4">基础信息</td>
         </tr>
         <tr>
           <td nowrap="nowrap" width="15%" align="right">姓名：</td>
           <td colspan="3"><c:out value="${af.map.real_name}" /><c:if test="${empty af.map.real_name}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">性别：</td>
           <td colspan="3"><c:out value="${af.map.sex eq 0 ? '男' : (af.map.sex eq 1 ? '女' : '保密')}" /></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">出生日期：</td>
         	<td colspan="3"><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /><c:if test="${empty af.map.birthday}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">居住地：</td>
           <td colspan="3"><c:out value="${p_index_name}" /><c:if test="${empty p_index_name}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">联系地址：</td>
           <td colspan="3"><c:out value="${af.map.link_addr}" /><c:if test="${empty af.map.link_addr}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">邮编：</td>
           <td colspan="3"><c:out value="${af.map.link_post}" /><c:if test="${empty af.map.link_post}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">手机：</td>
           <td colspan="3"><c:out value="${af.map.link_phone}" /><c:if test="${empty af.map.link_phone}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">电话：</td>
           <td colspan="3"><c:out value="${af.map.link_tel}" /><c:if test="${empty af.map.link_tel}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">邮箱：</td>
           <td colspan="3"><c:out value="${af.map.email}" /><c:if test="${empty af.map.email}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">QQ：</td>
           <td colspan="3"><c:out value="${af.map.link_qq}" /><c:if test="${empty af.map.link_qq}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td nowrap="nowrap" align="right">MSN：</td>
           <td colspan="3"><c:out value="${af.map.link_msn}" /><c:if test="${empty af.map.link_msn}"><span style="color:#999;">未填写</span></c:if></td>
         </tr>
         <tr>
           <td colspan="6" height="40"  align="center">
             <input class="but4" type="button" name="Submit5" value="修改" onclick="location.href='KonkaXxUserInfoUpdate.do?method=edit&user_id=${af.map.id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
             <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
           </td>
        </tr>
      </table>
    </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		<c:forEach items="${bpList}" var="cur">
		<c:if test="${cur.is_required eq 1}">
			<c:if test="${cur.prop_type eq 0 || cur.prop_type eq 1}">
				$("#prop_values_${cur.prop_id}").attr("datatype","Require").attr("msg","请填写${cur.prop_name}的属性值");
			</c:if>
			<c:if test="${cur.prop_type eq 2}">
				$("#checkbox_${cur.prop_id}").attr("datatype","Group").attr("min","1").attr("msg","请至少选择一个${cur.prop_name}的属性值");
			</c:if>
		</c:if>
		</c:forEach>
		$("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});

		$("#province").attr({"subElement": "city", "defaultText": "请选择省/直辖市/自治区", "defaultValue": "", "selectedValue": "${province}"});
		$("#city"    ).attr({"subElement": "country", "defaultText": "请选择市", "defaultValue": "", "selectedValue": "${city}"});
		$("#country" ).attr({"subElement": "town","defaultText": "请选择县", "defaultValue": "", "selectedValue": "${country}"});
		$("#town"    ).attr({"defaultText": "请选择乡/镇", "defaultValue": "", "selectedValue": "${town}"});
		
		$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
		$("#province").change();
		
	});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>