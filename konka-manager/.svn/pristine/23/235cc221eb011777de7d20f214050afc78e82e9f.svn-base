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

  <div class="rtabcont2">
    <html-el:form action="/admin/PeProdUser.do" enctype="multipart/form-data">
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="method" value="save1" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="returnUrl" />
      <c:if test="${pe_prod_user_session.user_type eq 0}"><c:set value="true" var="is_admin" /></c:if>
     <div align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
          <tr class="oartop">
              <td colspan="4" style="padding-bottom:10px;fontweight:bold;">
                                              岗位  <span style="font-size:14px;font-weight:bolder;">${empty af.map.job_id ? '未指定岗位ID，请尽快完善' : af.map.job_id}</span> 的详细信息
              </td>
          </tr>
                  <tr class="oartop">
                  	<td colspan="4">组织信息</td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">用户类型：</td>
                    <td colspan="3"><html-el:hidden property="user_type" value="1"></html-el:hidden>企业用户</td>
                  </tr>
                  <tr>
                    <td height="28" width="15%" nowrap="nowrap" class="title_item">部门：</td>
                    <td colspan="3"><c:out value="${fn:substring(konkaDept.map.full_name,2,-1)}" />&nbsp;<c:if test="${not empty leader.user_name}"><span class="note">[${peDept.dept_name}负责人：<a style="text-decoration:none;" href="PeProdUser.do?method=view&user_id=${leader.id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}">${leader.user_name}]</a></span></c:if>
                    </td>
                  </tr>
                  <tr>
                    <td height="28" width="15%" nowrap="nowrap" class="title_item">职务：</td>
                    <td colspan="3"><c:out value="${role_name}" />
                      </td>
                  </tr>
                  <tr class="oartop">
                  	<td height="28" colspan="4">登录信息</td>
                  </tr>                 
                  <tr>
                    <td nowrap="nowrap">登录用户名：</td>
                    <td colspan="3"><c:out value="${af.map.user_name}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">登录密码：</td>
                    <td colspan="3">****************</td>
                  </tr>
                  <tr class="oartop">
                  	<td colspan="4">在岗人员信息</td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">姓名：</td>
                    <td colspan="3"><c:out value="${af.map.real_name}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">性别：</td>
                    <td><c:out value="${af.map.sex eq 0 ? '男' : (af.map.sex eq 1 ? '女' : '保密')}" /></td>
                    <td nowrap="nowrap">出生日期：</td>
                  	<td><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">居住地：</td>
                    <td colspan="3"><c:out value="${p_index_name}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">联系地址：</td>
                    <td colspan="3"><c:out value="${af.map.link_addr}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">手机：</td>
                    <td width="35%"><c:out value="${af.map.link_phone}" /></td>
                    <td width="15%">电话：</td>
                    <td width="35%" colspan="3"><c:out value="${af.map.link_tel}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">邮编：</td>
                    <td><c:out value="${af.map.link_post}" /></td>
                    <td nowrap="nowrap">邮箱：</td>
                    <td><c:out value="${af.map.email}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">QQ：</td>
                    <td><c:out value="${af.map.link_qq}" /></td>
                    <td nowrap="nowrap">MSN：</td>
                    <td><c:out value="${af.map.link_msn}" /></td>
                  </tr>
                  <tr class="oartop">
                  	<td height="28" colspan="4">其他信息</td>
                  </tr>  
                  <tr>
                    <td nowrap="nowrap">排序值：</td>
                    <td colspan="3"><c:out value="${af.map.order_value}" /></td>
                  </tr>
                   <tr >
			            <td nowrap="nowrap"  height="28" class="title_item">停用原因：</td>
			            <td colspan="3"><html-el:select  property="chan_status"  styleId="chan_status">
			            				<html-el:option value="">--请选择--</html-el:option>
			            				<html-el:option value="0">转岗</html-el:option>
			            				<html-el:option value="1">离职</html-el:option>
			            				<html-el:option value="2">其他</html-el:option>
			            </html-el:select><span style="color:red">*</span> 
           			 	</td>
          			</tr>
          		<tr>
          <td colspan="6" height="40"  align="center">
         	 <input class="but4" type="button" name="Submit4" value=" 保存 " id="btn_submit" />
                <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </td>
        </tr>
      </table>
    </div>
    </html-el:form>
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

		$("#chan_status").attr("datatype","Require").attr("msg","请选择停用原因！");

		
		$("#btn_submit").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(".but4").attr("disabled", "true");
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
<jsp:include page="/__analytics.jsp" />
</body>
</html>