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
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaBaseTypeData" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>类别ID：</td>
			<td width="88%" align="left"><html-el:text property="type_id" disabled="true"
				styleId="type_id"/></td>
		</tr>

		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>类别名称：</td>
			<td width="88%" align="left"><html-el:text property="type_name"
				styleId="type_name" /></td>
		</tr>
        <tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">属性：</td>
			<td width="88%" align="left"><html-el:text property="field1" style="width:50%;height:60px;resize:none;"
				styleId="field1"/></td>
		</tr>
		<tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>父类别ID：</td>
             <td width="88%" align="left"><html-el:text property="par_type_id" disabled="true"
				styleId="par_type_id" /></td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>备注：</td>
          <td width="88%" align="left"><html-el:text property="memo" style="width:50%;height:60px;resize:none;"
				styleId="memo" /></td>
        </tr>
         <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>是否删除：</td>
            <td width="88%" align="left">
                 <html-el:select property="is_del" styleId="is_del" style="width:120px;">
			       <html-el:option value="">请选择</html-el:option>
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">已删除</html-el:option>
        	   </html-el:select>
            </td>
          </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>是否锁定：</td>
          <td width="88%" align="left">
           <html-el:select property="is_lock" styleId="is_lock" style="width:120px;">
			       <html-el:option value="">请选择</html-el:option>
	      		   <html-el:option value="0">正常</html-el:option>
	      		   <html-el:option value="1">已锁定</html-el:option>
        	   </html-el:select> 
          </td>
          </tr>
		 <tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>级别：</td>
			<td width="88%" align="left"><html-el:text property="field2" disabled="false"
				styleId="field2"/></td>
		</tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>排序值：</td>
            <td width="88%" align="left"><html-el:text property="order_sort" styleId="order_sort" size="12" maxlength="12"/>
            </td>
          </tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">附件：</td>
            <td width="88%" align="left">
                 <div id="fj"></div>
            </td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.imgAddShow.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("input[type!='button']").each(function(){ this.disabled=true; }); 
	 //附件
	 $("#fj").imgshow({
		ctx:"${ctx}",
		isAdd:false,
		delUrl:"${ctx}/manager/admin/KonkaSpActivityManager.do?method=deleteFile1",
		data:jQuery.parseJSON('${fj_paths}')
    });
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
