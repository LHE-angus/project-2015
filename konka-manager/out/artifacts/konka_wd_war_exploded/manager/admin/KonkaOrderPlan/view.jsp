<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont2">
	  	<html-el:form action="/admin/KonkaOrderPlan" enctype="multipart/form-data">
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
				<tr>
				<td class="title_item" align="right" nowrap="nowrap">客户编码：</td>
				<td><html-el:text property="r3_code" styleId="r3_code"></html-el:text></td>
				<td class="title_item" align="right" nowrap="nowrap">客户名称：</td>
				<td><html-el:text property="customer_name" styleId="customer_name"></html-el:text></td>
				</tr>
				
				<tr> 
			    <td class="title_item" align="right" nowrap="nowrap">进货年月：</td>
				
				<td colspan="3">
				<html-el:select property="plan_year" styleId="plan_year">
				<html-el:option value="">-请选择-</html-el:option>
				<c:forEach var="year" begin="2014" end="2024" step="1"> 
				       <html-el:option value="${year}">${year}</html-el:option>
				</c:forEach>
				</html-el:select>		
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html-el:select property="plan_month" styleId="plan_month">
				<html-el:option value="">-请选择-</html-el:option>
				<c:forEach var="month" begin="01" end="12" step="1"> 
				       <html-el:option value="${month}">${month}</html-el:option>
				</c:forEach>
				</html-el:select>		
				</td>
				</tr>
				
				<tr>
				<td class="title_item" align="right" nowrap="nowrap">计划机型： </td>
                <td >
              <html-el:text property="pd_name" styleId="pd_name"/>
          </td>
          <td class="title_item" align="right" nowrap="nowrap">上月末库存：</td>
				<td><html-el:text property="last_stock_num" styleId="last_stock_num" onfocus="javascript:setOnlyInt(this)"></html-el:text></td>
          </tr>
				
			<tr>	
				<td class="title_item" align="right" nowrap="nowrap">计划进货数量：</td>
				<td><html-el:text property="plan_stock_num" styleId="plan_stock_num" onfocus="javascript:setOnlyInt(this)"></html-el:text></td>
		       
		        <td class="title_item" align="right" nowrap="nowrap">计划销售数量：</td>
				<td ><html-el:text property="plan_sale_num" styleId="plan_sale_num" onfocus="javascript:setOnlyInt(this)"></html-el:text></td>
		        </tr>
		        <tr>	
				<td class="title_item" align="right" nowrap="nowrap">添加人：</td>
				<td><html-el:text property="add_user_name" styleId="add_user_name" ></html-el:text></td>
		       
		        <td class="title_item" align="right" nowrap="nowrap">添加时间：</td>
				<td ><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
		        </tr>
		        <tr>	
				<td class="title_item" align="right" nowrap="nowrap">修改人：</td>
				<td><html-el:text property="modify_user_name" styleId="modify_user_name" ></html-el:text></td>
		       
		        <td class="title_item" align="right" nowrap="nowrap">修改时间：</td>
				<td ><fmt:formatDate value="${af.map.modify_date}" pattern="yyyy-MM-dd"/></td>
		        </tr>
		        <tr>
					<td class="title_item" align="right" nowrap="nowrap">备注：</td>
					<td align="left" colspan="3">
					<html-el:textarea property="memo" styleId="memo" cols="5" style="width:600px;height:100px;"></html-el:textarea>
					</td>
				</tr>
			
		    
		   
		    <tr>
	          <td colspan="4" align="center">
	            <input class="bgButtonBack" type="button" name="return" value="返回" id="btn_reset" onclick="history.back();"/>
	            <br/>
		        <div style="height: 50px">&nbsp;</div>
	          </td>
	        </tr>
	    	</table>
		</html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript">//<![CDATA[

var f = document.forms[0];
$(document).ready(function(){
	$("#r3_code").attr("readOnly","readOnly").attr("dataType", "Require").attr("msg", "请选择客户");
	$("#customer_name").attr("readOnly","readOnly").attr("dataType", "Require").attr("msg", "请选择客户");
	$("#pd_id").attr("dataType", "Require").attr("msg", "请选择机型");
	$("#pd_name").attr("dataType", "Require").attr("msg", "请选择机型");
	$("#plan_year").attr("dataType", "Require").attr("msg", "请选择计划年份");
	$("#plan_month").attr("dataType", "Require").attr("msg", "请选择计划月份");
	$("#memo").attr("datatype", "Limit"  ).attr("max", "200").attr("min","0").attr("msg", "备注必须在200个字之内");


	
	

 
 //calcPdNumAndPrice("tbodyOrder");
 resizeFrameHeight();
});//ready end
	/*处理返回值数据 ===start===*/
	
function resizeFrameHeight(offset, min_height) {
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}


//正则表达式：只能输入数字
function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//类别-连动-型号
function category_id_chg(){
	$("#model_id").empty();
	$("<option value=''>-产品型号-</option>").appendTo($("#model_id"));
	var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getModel&size_id="+$('#measure_id').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#model_id"));
			});
			if('${af.map.model_id }' != null || '${af.map.model_id }' != '' ){
				$("#model_id").val('${af.map.model_id }');
			}
		}
	});
}
function customer_code_chg(){
	$("#r3_code").val($("#customer_code_select").val());
	$("#customer_name").val($("#customer_code_select").find("option:selected").text())
}
function model_id_chg(){
	$("#pd_id").val($("#model_id").val());
	$("#pd_name").val($("#model_id").find("option:selected").text())
}

function DBC2SBC(str)
{
 var result = '';
 for (i=0 ; i<str.length; i++)
 {
  code = str.charCodeAt(i);//获取当前字符的unicode编码
  if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已及各种字符
  {
   result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  }else if (code == 12288)//空格
  {
   result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  }else
  {
   result += str.charAt(i);
  }
 }
 return result;
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>