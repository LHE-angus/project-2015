<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
        <td width="20"><img src="${ctx}/images/manager/arrow3.gif" style="vertical-align:middle;" /></td>
        <td>当前位置：当前位置：&nbsp;&gt;&nbsp;信息接收&nbsp;&gt;&nbsp;站内信息</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/manager/admin/PeShopMsg/shop_msg_top.jsp" %>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/PeShopMsg.do" enctype="multipart/form-data" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="state" styleId="state" />
      <html-el:hidden property="areas_ids" styleId="areas_ids" />
      <html-el:hidden property="areas_names" styleId="areas_names" />
      <html-el:hidden property="user_type" styleId="user_type" />
      <html-el:hidden property="receive_user_type" styleId="receive_user_type" />
      <html-el:hidden property="id" styleId="id" value="${entity.id}"/>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td nowrap="nowrap" class="title_item">标&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
          <td><html-el:text property="title" styleId="title" style="width:300px" maxlength="60" size="40" value="${entity.title}" onkeyup="limitTitleLength()"/>
            <span style="color:red">*</span> <br />
            <font style="font-size: 12px;color: gray" id="title_msg">*不超过30个汉字</font></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
          <td><html-el:textarea property="content" styleId="content"  style="height:200px;width:600px;" value="${entity.content }" onkeyup="limitContentLength()"/>
            <span style="color:red">*</span> <br />
            <font style="font-size: 12px;color: gray" id="content_msg">*不超过500个汉字</font></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="left">发件方式：</td>
          <td><c:if test="${af.map.user_type gt 0}">
              <c:set var="disabled" value="true"></c:set>
            </c:if>
            <c:if test="${af.map.user_type eq 0}">
              <c:set var="disabled" value="false"></c:set>
            </c:if>
            <html-el:select property="public_type" styleId="public_type" disabled="${disabled}" >
              <html-el:option value="1">选择对象发布</html-el:option>
              <html-el:option value="0">对所有网点</html-el:option>
            </html-el:select>
            <span style="color:red">*</span></td>
        </tr>
        <tr id="select_1" 
        <c:if test="${af.map.user_type gt 0 or af.map.public_type eq 0}">style="display: none;"</c:if>
        >
        <td nowrap="nowrap" class="title_item" align="left">选择收件地区：</td>
          <td align="left"><html-el:select property="province" styleId="province">
              <html-el:option value="">-请选择省/直辖市/自治区-</html-el:option>
              <html-el:optionsCollection label="p_name" value="p_index"  name="baseProvince1List" />
            </html-el:select>
            <br/>
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
                  <td colspan="3"><font color="red">注:列表项可以通过双击在两个区域间移动.</font></td>
                </tr>
              </tbody>
            </table>
            <br/></td>
        </tr>
        <tr id="select_2" 
        <c:if test="${af.map.public_type eq 0}">style="display: none;"</c:if>
        >
        <td nowrap="nowrap" class="title_item" align="left">选择收件网点：</td>
          <td><html-el:text property="shop_name" styleId="shop_name" style="width:250px;"  maxlength="100" readonly="true" />
            &nbsp;
            <input id="gsBTN" type='button' value='选择' onclick="getShopInfo();"/>
            <html-el:hidden property="shop_id" styleId="shop_id" />
            <c:if test="${af.map.user_type eq 0}"> &nbsp;注:请先选择区域(省或地区),再选择网点，如不选网点则默认为所选区域下所有网点. </c:if></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">收件网点类别：</td>
          <td><html-el:text property="peShopCategoryName" styleId="peShopCategoryName" style="width:250px;"  maxlength="100" readonly="true" />
            &nbsp;
            <input type='button' value='选择' onclick="getPeShopCategoryInfo();"/>
            <html-el:hidden property="peShopCategoryId" styleId="peShopCategoryId" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input class="but4" type="button" name="Submit5" value="发送" onclick="msg_save(1)" />
            <input class="but2" type="button" name="Submit5" value="暂存" onclick="msg_save(0)"  />
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
	$("#title").attr("dataType", "Require").attr("msg", "请输入标题！");
	$("#content").attr("dataType", "Require").attr("msg", "请填写短信息内容！");
    $("#shop_name").attr("dataType", "Require").attr("msg", "请选择收件网点");
    $("#province").change(function(){
		var province = $("#province").val();
		$.ajax({
				type: "POST",
				url: "CsAjax.do",
				data: "method=" + "getBaseProvinceList" + "&p_index=" + $("#province").val()+"&many_p_index=${af.map.many_p_index}",
				dataType: "json",
				error: function(request, settings) {},
				success: function(Datas) {
					createSelectareas("selectareas",Datas,"0");
				}
		});
	});
	$("#province").change();
	
	$("#public_type").change(function(){if(this.value==0){$("#select_1, #select_2").hide();}else{$("#select_1, #select_2").show();}})
});

function selectReciver(){
    var returnValue = window.showModalDialog("PeShopMsg.do?method=listShopMain&province="+ document.getElementById("province").value + "&city="+ document.getElementById("city").value + "&country="+ document.getElementById("country").value + "&time="+new Date().getTime(), window, "dialogWidth:500px;status:no;dialogHeight:350px"); 
    if(returnValue != null) {
    	var shop_info = returnValue.split(",");
    	var shop_name = shop_info[0];
    	var user_name = shop_info[1];
    	var user_id = shop_info[2];
		$("#receive_user_name").val(shop_name);
		$("#receive_user_id").val(user_id);
    } 
    if(returnValue != null) {
		var value = returnValue.split("##");
		var userIds = "";
		var shopNames = "";
		var len = value.length;
		for(var i = 0;i<len; i++){
			userIds+=","+value[i].split(",")[2];
			shopNames += ","+value[i].split(",")[0];
		}
		document.getElementById("receive_user_name_dis").title = shopNames.substr(1,shopNames.length);
		document.getElementById("receive_user_name_dis").value = shopNames.substr(1,shopNames.length);
		document.getElementById("receive_user_id").value = userIds.substr(1,userIds.length);
		document.getElementById("receive_user_name").value = shopNames.substr(1,shopNames.length);
	 } else{
		return false;
	}
}
var f = document.forms[0];
function msg_save(type){
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
<c:if test="${af.map.user_type eq 0}">
	$("#shop_name").removeAttr("dataType");
	if(ids.length == 0 && $("#public_type").val() == 1){
		alert('请选择区域');
		return false;
	}

	if($("#public_type").val()=='1' &&$('#province').val()=='' &&  $('#areas_ids').val()=='' && $('#shop_id').val()==''){ //非所有网点情况下，省、地区、网点 必选其一
		alert('请选择发布区域(省、地区或网点).');
		return false;
	}
</c:if>
	   
	$("#state").val(type);
	
	limitTitleLength();
	limitContentLength();
	
	if( Validator.Validate(f, 2)){
		f.submit();
	}
}

function limitTitleLength(){
	var len = strlen($("#title").val());
	if(len>60){
		document.getElementById("title_msg").style.color="red";
		checkbutton();
		return;
	}else{
		document.getElementById("title_msg").style.color="gray";
		checkbutton();
		return;
	}
	
}
function limitContentLength(){
	var len = strlen($("#content").val());
	if(len>1000){
		document.getElementById("content_msg").style.color="red";
		checkbutton();
		return;
	}else{
		document.getElementById("content_msg").style.color="gray";
		checkbutton();
		return;
	}
	
}
function checkbutton(){
	if(strlen($("#content").val())>1000||strlen($("#title").val())>60){
		$(":button").attr("disabled","true");
	}else{
		$(":button").removeAttr("disabled");
	}
}
function strlen(str) {   
    var len = 0;   
    for (var i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 255 || str.charCodeAt(i)<0) {
        	len += 2; 
        }
        else len ++;   
    }   
    return len;   
};  

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

function getPeShopCategoryInfo(){
	var returnValue = window.showModalDialog("PeShopCategory.do?method=listForLevel&selects=" + $("#peShopCategoryId").val() +  "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");
	if (returnValue != null) {
		$("#peShopCategoryId").val(returnValue.ids);
		$("#peShopCategoryName").val(returnValue.names);
	};	
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
	
	<c:if test="${af.map.user_type eq 0}">
	if(areas_ids == ''){
		alert('请先选择区域');
		return false;
	}
	</c:if>

	var returnValue = window.showModalDialog("SelectEntyShopByArea.do?method=toWindowFramePage&receive_user_type=" + $("#receive_user_type").val() + "&selectype=mutil&selects=" + $("#shop_id").val() + "&province=" + $("#province").val()+ "&areas_ids=" + areas_ids + "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:500px");

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
