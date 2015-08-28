<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
  	<html-el:form action="/admin/CrmPriceHeader" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save_excel" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="id" value="${af.map.id}" styleId="id" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      	<tr>
      		<td colspan="2"><input type="file" name="excel" id="excel" contenteditable="false"  /></td>
      	</tr>
      	<tr>
      		<td align="left" colspan="1">
      			<input class="but_excel" type="button" name="btn_submit" id="btn_submit" value="导入" />
      		</td>
      		<td align="left" colspan="1">
      			<input class="but5" type="button" value="返回" onclick="window.history.go(-1)" id="btn_back" />
      		</td>
      	</tr>
      	<tr>
      		<td colspan="2"><img src="${ctx }/manager/admin/CrmPriceHeader/TPL.jpg" alt="sry,load fail" border="1"></img></td>
      	</tr>
      	<tr>
      		<td>为了提高工作效率,请阅读操作提示:</td>
      		<td colspan="1">
      			<ul>
      				<li><font color="red" style="font-weight: bold;">1.下载Excel2003模板,导入内容放在第一个工作表中!</font>&nbsp;<a style="cursor:pointer;color:blue;" href="${ctx}/files/upload/crm_price_tpl.xls">Excel2003模板下载</a></i><br/>
      				<li>2.参照上图样例,字段顺序不能调整,红色区内字段为必填项</i><br/>
      				<li>3.机型编码在同一个Excel表内不能重复,如有重复,后面数据覆盖前面数据</i><br/>
      				<li>4.Excel表内的机型与已经保存的机型出现重复,Excel表内的数据会覆盖已经保存的机型</i><br/>
      				<li>5.供价/市场价/返利/最低限价/提成 均必须为有效数字</i><br/>
      				<li>6.暂时只支持.xls后缀的Excel文件</i><br/>
      				<li>7.以上,谢谢.</i><br/>
      			</ul>
      		</td>
      	</tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	String.prototype.isPath=function() { return /[^\n\r\t]*\.[x|X][l|L][s|S]/i.test(this); };
	$("#btn_submit").click(function(){
		var id = $("#id").val();
		if (!id) {
	        alert("价格组参数丢失,请再次尝试!");
	        return false;
	    }
		var path = $("#excel").val();
	    if (path == "" || !path.isPath()) {
	        alert("请选择一个有效Excel文件!");
	        return false;
	    }
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
		
	});	

})
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>