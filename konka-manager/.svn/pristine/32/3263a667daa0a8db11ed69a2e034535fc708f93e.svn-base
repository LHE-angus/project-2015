<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base target="_self"></base>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/scripts/jquery-easyui/themes/gray/easyui.css" rel="stylesheet" type="text/css">
<link href="${ctx }/scripts/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" >
</head>
<body>
  
<div class="oarcont" id="body_oarcont">
	<div class="rtabcont1">
		<div align="center"><h2>机型维护</h2></div>
    </div>
  <div class="rtabcont2">
    <form action="${ctx}/manager/admin/CrmPriceHeader.do" method="post" id="dataform">
      <input type="hidden" name="method" value="saveCrmPriceLines"/>
      <input type="hidden" name="mod_id" value="${af.map.mod_id}"/>
      <input type="hidden" name="id" value="${af.map.id}"/>
      <input type="hidden" name="headid" value="${af.map.headid}"/>
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" width="10%">推广政策：</td>
          <td width="40%">
          <input class="easyui-validatebox" name="policy" id="policy" value="${af.map.policy}">
          </td>
<!--           <td class="title_item" width="10%" style="display:none">机型功能：</td> -->
<!--           <td width="40%"  style="display:none"> -->
<%--           <input class="easyui-validatebox" name="func" id="func" value="${af.map.func}" readonly="readonly"> --%>
<!--           </td> -->

		<td></td><td></td>
        </tr>
        <tr>
          <td class="title_item" width="10%">机型编号：</td>
          <td width="40%">
		   	   <select id="modelcode" name="modelcode" style="width:151px" class="easyui-combobox" data-options="mode:'local',valueField:'modelcode',textField:'modelcode',value:'${af.map.modelcode}'">
	          		<c:forEach items="${pmList}" var="cur">
	          			<option value="${fn:escapeXml(cur.md_name)}">${fn:escapeXml(cur.md_name)}</option>
	          		</c:forEach>
	           </select>
	           <span id="tip"><font color="red">*</font></span>
          </td>
<!--           <td class="title_item" width="10%">机型名称：</td> -->
<%--           <td width="40%"><html-el:text property="modelname" styleId="modelname" readonly="true"/></td> --%>

		<td class="title_item" width="10%">返利：</td>
          <td width="40%">
          	<input type="text" name="fl" id="fl"  value="${af.map.fl}" readonly="true" alt="不需要手工填写">不需要手工填写
          </td>
        </tr>
        
        <tr>
          <td class="title_item" width="10%">供价：</td>
          <td width="40%">
          	<input class="easyui-numberbox" name="forprice" id="forprice"  onchange="getFl()"  missingMessage="不能为空"  data-options="min:0,max:9999999,precision:2,required:true" value="${af.map.forprice}">
          </td>
          <td class="title_item" width="10%">市场价：</td>
          <td width="40%">
          <input class="easyui-numberbox" name="marketprice" id="marketprice" onchange="getFl()" missingMessage="不能为空"  data-options="min:0,max:9999999,precision:2,required:true" value="${af.map.marketprice}">
          </td>  
        </tr>
        
        <tr>
          <td class="title_item" width="10%">最低限价：</td>
          <td width="40%">
          	<input class="easyui-numberbox" name="lowestprice" id="lowestprice"  missingMessage="不能为空"  data-options="min:0,max:9999999,precision:2,required:true" value="${af.map.lowestprice}">
          </td>
          <td class="title_item" width="10%">提成：</td>
          <td width="40%">
          	<input class="easyui-numberbox" name="tc" id="tc" missingMessage="不能为空" data-options="min:0,max:999999,precision:2,required:true" value="${af.map.tc}">
          </td>
        </tr>
        <tr>
        	<td class="title_item" width="10%">备注：</td>
          	<td colspan="3">
          		<textarea name="remark" id="remark" >${af.map.remark}</textarea>
          	</td>
        </tr>
        <tr>
          <td>&nbsp;</td> 
          <td align="right">
            <input class="but4" type="button" name="Submit4" id="btn_submit" value="保存" />
            <input class="but5" type="button" name="Submit5" value="关闭" onclick="window.close();"/>
          </td>
          <td>&nbsp;</td> 
          <td>&nbsp;</td>

        </tr>
      </table> 
    </form>
  </div> 
</div>
<script type="text/javascript" src="${ctx}/scripts/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//1.
    $("#btn_submit").click(function(){
    	var modelcode =  $('#modelcode').combobox('getValue') ;
    	if(modelcode== null || modelcode==""){
			$("#tip").text(" * 机型不能为空").css({"color":"red"});
    	}
		if (!$('#dataform').form('validate')){
			$.messager.show({
				title:'提示信息' , 
				msg:'验证没有通过,不能提交表单!'
			});
			return false ;
		}else{
    		this.form.submit();
		}
    });

    var v = "${af.map.modelcode}";
    if (v){
      $('#modelcode').combobox('setValue',v);
	} ;
	//3.	
// 	$('#modelcode').combobox({
// 		filter:function(q,row){ 
// 			var opts=$(this).combobox("options"); 
// 			return row[opts.textField].indexOf(q.toUpperCase())>-1;//将从头位置匹配改为任意匹配 
// 		},
// 		onSelect:function(rec){
// 			var v = $('#modelcode').combobox('getValue');
// 			$.ajax({
// 				type: "post",
// 				url: "${ctx}/manager/admin/CrmPriceHeader.do?method=getFuncsByModel",
// 				data: {"modelcode" : v},
// 				dataType: "json",
// 				success: function(result) {
// 					alert("data");
// 				}
// 			});
// 		}
// 	});
	
}); 

function getFl(){
	var forprice  = $("#forprice").val();
	var marketprice = $("#marketprice").val();
	if(forprice!=null && forprice!="" && marketprice!=null && marketprice!="" ){
    	$("#fl").val(forprice-marketprice);
	}else{
		$("#fl").val(0);
	}
}



//]]></script>
</body>
</html>
