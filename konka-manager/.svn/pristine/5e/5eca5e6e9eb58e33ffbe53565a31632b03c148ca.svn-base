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
    <html-el:form action="/admin/JoinInfoManager" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="areas_ids" styleId="areas_ids" />
      <html-el:hidden property="areas_names" styleId="areas_names" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" width="147">标题：<span style="color:red">*</span></td>
          <td><html-el:text property="title" styleId="title" style="width:380px;" styleClass="webinput" maxlength="60" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right" >内容：</td>
          <td><FCK:editor instanceName="content">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor>
            <div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            <div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。 </div>
            <br /></td>
        </tr>
        <c:if test="${not empty af.map.disabled && af.map.disabled eq 1}">
          <html-el:hidden property="public_type" styleId="public_type" value="1"/>
        </c:if>
        <c:if test="${empty af.map.disabled || af.map.disabled ne 1}">
          <tr>
            <td nowrap="nowrap" class="title_item" align="right">发布方式：<span style="color:red">*</span></td>
            <td><html-el:select property="public_type" styleId="public_type"  >
                <html-el:option value="1">选择对象发布</html-el:option>
                <html-el:option value="0">对所有网点</html-el:option>
              </html-el:select></td>
          </tr>
        </c:if>
        <tr id="public_type_1" <c:if test="${not empty af.map.public_type && af.map.public_type eq 0}">style="display: none;"</c:if>>
          <td nowrap="nowrap" class="title_item" align="right">选择地区：</td>
          <td align="left"><html-el:select property="province" styleId="province">
              <html-el:option value="">-请选择省/直辖市/自治区-</html-el:option>
              <html-el:optionsCollection label="p_name" value="p_index"  name="baseProvince1List" />
            </html-el:select><br/>
            <table>
              <tbody>
                <tr>
                  <td> 供选择区域列表
                    <div style="width:160px;height:160px;overflow-y:auto;border:1px solid black;">
                      <ul id="selectareas">
                      </ul>
                    </div></td>
                  <td width="30"></td>
                  <td> 已选择区域列表
                    <div style="width:160px;height:160px;overflow-y:auto;border:1px solid black;">
                      <ul id="selectedareas">
                      </ul>
                    </div></td>
                </tr>
                <tr>
                  <td colspan="3"><font color="red">注:列表项可以通过双击在两个区域间移动,若地区未选择，则默认为该省下所有地区.</font></td>
                </tr>
              </tbody>
            </table>
            <br/></td>
        </tr>
        <tr id="public_type_2" <c:if test="${not empty af.map.public_type && af.map.public_type eq 0}">style="display: none;"</c:if>>
          <td nowrap="nowrap" class="title_item" align="right">选择网点：</td>
          <td><html-el:text property="shop_name" styleId="shop_name" style="width:250px;"  maxlength="100" readonly="true" />
            &nbsp;
            <input id="gsBTN" type='button' value='选择' onclick="getShopInfo();"/>
            <html-el:hidden property="shop_id" styleId="shop_id" />
            <br />
            1.先选择区域(省或地区),才能对网点进行选择；<br />
            2.选择网点后,仅发布到所选网点,不选择网点时,发布到所选区域下所有网点. </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" value="提交" id="btn_submit" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[

$(document).ready(function(){
	$("#title").attr("dataType", "Require").attr("msg", "请填写标题");

	$("#province").change(function(){
		selectareasByprovince($("#province").val());
	});
	$("#province").change();

	// 发布方式
	$("#public_type").change(function(){
		if(this.value == '0'){
			$('#public_type_1').hide();
			$('#public_type_2').hide();
		}else if(this.value == '1'){
			$('#public_type_1').show();
			$('#public_type_2').show();
		}
	});


	// 提交
	$("#btn_submit").click(function(){

	   var ul = document.getElementById("selectedareas");
	   var ids_names =  ul.getElementsByTagName("input");
	   var ids = new Array(),names = new Array();
	   for(var i = 0;i< ids_names.length;i++){
	       var arr = ids_names[i].value.split(",");
	       ids[ids.length] = arr[0];
	       names[names.length] =  arr[1];
	    }
	   $('#areas_ids').val(ids.join(","));
	   $('#areas_names').val(names.join(","));
	   if($("#public_type").val()=='1' &&$('#province').val()=='' &&  $('#areas_ids').val()=='' && $('#shop_id').val()==''){ //非所有网点情况下，省、地区、网点 必选其一
		   alert('请选择发布区域(省、地区或网点).');
		   return false;
	   }else{
		   if(Validator.Validate(this.form, 1)){
				$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				$("#btn_back").attr("disabled", "true");
				this.form.submit();
		   }
	   }
	});
	
	<c:if test="${peRoleUser.role_id ge '20'}">
	$("#province").hide();
	selectareasByprovince(-1);
	</c:if>


});
function selectareasByprovince(province){ // province -1为只调用管辖区域
	$.ajax({
		type: "POST",
		url: "CsAjax.do",
		data: "method=" + "getBaseProvinceList" + "&p_index=" + province +"&many_p_index=${af.map.many_p_index}",
		dataType: "json",
		error: function(request, settings) {},
		success: function(Datas) {
			createSelectareas("selectareas",Datas,"0");
		}
	});
}
//创建选择区列表
function createArea(ul,id,name,flg){
	 var li = document.createElement("li");
	 var span = document.createElement("span");
	 span.innerHTML = name;
  	 li.style.cursor = 'pointer';
  	 li.setAttribute("flg",""+flg);
  	 li.ondblclick = function (){
      	if(this.getAttribute("flg") == "0"){
     		 var ul = document.getElementById("selectedareas");
      		 ul.appendChild(this);
      		 this.setAttribute("flg","1");
      		 var id_name = this.childNodes[0].value;
      		 selectedArea_arr[id_name.split(",")[0]] = id_name.split(",")[1];
      	}else{
    		 var ul = document.getElementById("selectareas");
      		 ul.appendChild(this);
      		 this.setAttribute("flg","0");
      		 var id_name = this.childNodes[0].value;
      		 selectedArea_arr[id_name.split(",")[0]] = "";
      	}
  	};
 	
  	 var input = document.createElement("input");
  	 input.type="hidden";
  	 input.setAttribute("name","ids_names");
  	 input.value = id+","+name;

  	 li.appendChild(input);
  	 li.appendChild(span);
  	 ul.appendChild(li);
}

// 已选择的管辖区域
var selectedArea_arr = [];

// JSON 方式添加
function createSelectareas(id,Datas,flg){
   var ul = document.getElementById(id);
   ul.innerHTML = "";        	            
   if (Datas != null && Datas.length > 1) {
	   for(var i = 0; i < Datas.length; i++) {
		   if(selectedArea_arr[Datas[i][1]] == null || selectedArea_arr[Datas[i][1]] == ""){
			   createArea(ul,Datas[i][1],Datas[i][0],flg);
		   }		   
	   }
   }
}

//选网点
function getShopInfo() {
	var ul = document.getElementById("selectedareas");
    var ids_names =  ul.getElementsByTagName("input");
    var ids = new Array();
    for(var i = 0;i< ids_names.length;i++){
       var arr = ids_names[i].value.split(",");
       ids[ids.length] = arr[0];
    }
	var areas_ids = ids.join(",");
	if($("#province").val() == '' && areas_ids == ''){
		alert('请先选择区域,可以单独选择省,也可以选择具体地区.');
		return false;
	}
	var returnValue = window.showModalDialog("SelectEntyShopByArea.do?selectype=mutil&selects=" + $("#shop_id").val() + "&province=" + $("#province").val() + "&areas_ids=" + areas_ids + "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#shop_id").val(returnValue.ids);
		$("#shop_name").val(returnValue.names);
	};
};



// 字符串 方式添加
var areas_ids = '${af.map.areas_ids}';
var areas_names = '${af.map.areas_names}';
if(areas_ids != "" && areas_names != ""){
	var arr_ids = areas_ids.split(",");
	var arr_names = areas_names.split(",");
	var ul = document.getElementById("selectedareas");
	for(var i = 0;i< arr_ids.length;i++){
		 createArea(ul,arr_ids[i],arr_names[i],"1");
		 selectedArea_arr[arr_ids[i]] = arr_names[i];
	}
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
