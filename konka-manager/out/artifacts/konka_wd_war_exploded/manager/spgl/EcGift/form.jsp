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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 2px;/*padding:2px 5px;*/}
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
.ck-li{position:relative;height:22px;padding:1px auto;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}


.navClass {background-color:#CCC;border-collapse:collapse;}
.navClass th {background:#F6F6F6;color:#C4C4C4;font-size:12px;font-weight:bold;height:20px;line-height:20px;text-align:center;padding:2px;border:1px solid #CCC;}
.navClass td {padding:3px;height:18px;background-color:#fff;border:1px solid #CCC;}

.xubox_close{position:absolute;}
.xubox_close1_0{ right:-25px; top:-16px; width:33px; height:31px; background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -182px no-repeat; cursor:pointer; overflow:hidden;}
.xubox_close1_0:hover{background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -215px no-repeat;}
.xubox_border{border-radius:5px;position:absolute;}

.main_box{position:relative;width:260px;z-index:825;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.pic_box{text-align:center;;z-index:827;background:#FFFFFF;margin:5px;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
	  <html-el:form action="/spgl/EcGift" enctype="multipart/form-data" method="post">
	    <html-el:hidden property="method" value="save" />
	    <html-el:hidden property="id" />
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="dept_sn" value="${af.map.dept_sn}" />
	    <html-el:hidden property="own_sys" value="${af.map.own_sys}" />
	    <html-el:hidden property="queryString" />
	    <html-el:hidden property="ecGiftComm_id" value="${af.map.ecGiftComm.id}"/>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
	    	<tr>
          		<td width="12%"  class="title_item" nowrap="nowrap">商品编码：</td>
          		<td><html-el:text property="pd_sn" styleId="pd_sn" styleClass="webinput" maxlength="20"  /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品名称：</td>
          		<td><html-el:text property="pd_name" styleId="pd_name" styleClass="webinput" size="40" maxlength="100" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">市场价：</td>
          		<td><html-el:text property="original_price" styleId="original_price" styleClass="webinput" maxlength="8" onfocus="javascript:setOnlyNum(this);" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">所需积分：</td>
          		<td><html-el:text property="need_integral" styleId="need_integral" styleClass="webinput" maxlength="8" onfocus="javascript:setOnlyInt(this);" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">关联商品：</td>
          		<td>
          		商品价格：<html-el:text property="ecGiftComm_price" styleId="ecGiftComm_price" styleClass="webinput" maxlength="8" value="${af.map.ecGiftComm.price}" /><br/>
          		商品地址：<html-el:text property="ecGiftComm_url" styleId="ecGiftComm_url" styleClass="webinput" size="100" maxlength="200" value="${af.map.ecGiftComm.goods_url}" />
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item">主图：</td>
          		<td>
          			<c:if test="${empty af.map.id}"><html-el:file property="main_pic_file" styleId="main_pic_file" styleClass="webinput" style="width:" /></c:if>
          			<c:if test="${not empty af.map.id}">
          				<div id="main_pic_div" class="main_box">
	          				<div class="pic_box">
	          					<a id="main_pic_a" href="${ctx}/${af.map.main_pic_file}" title="用创新赢得尊严！">
		        					<img src="${ctx}/${fn:substringBefore(af.map.main_pic_file, '.')}_240.jpg" />
		        				</a>
		        				<html-el:hidden property="main_pic_hidden" styleId="main_pic_hidden" value="${af.map.main_pic_file}" />
		        				<a class="xubox_close xubox_close1_0" href="javascript:delPic('main_pic_div');"></a>
		        			</div>
          				</div>
          				<div style="margin-top:5px;">
		        			<input type="checkbox" name="chkReUploadMainPicFileImage" id="chkReUploadMainPicFileImage" value="1" onclick="$('#main_pic_file').toggle();" style="vertical-align:middle;cursor:pointer;" />
			            	<label for="chkReUploadMainPicFileImage" style="vertical-align:middle;cursor:pointer;">重新上传</label>
          				</div>
		            	<html-el:file property="main_pic_file" styleId="main_pic_file" styleClass="webinput" style="display:none;width:" />
          			</c:if>
          			<div>注：此处上传图片尺寸为1:1时，效果最佳。</div>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item">图片：</td>
          		<td align="left">
          			<table width="100%" border="0" cellpadding="0" cellspacing="0">
	       				<c:if test="${not empty picList}">
	       					<!-- 已上传图片显示区 -->
	       					<tr>
	       						<td>
			       					<ul style="display:block;list-style-type:none;">
				       					<c:forEach items="${picList}" var="cur" varStatus="vs">
				       						<li id="pic_${vs.count}_li" class="main_box" style="display:inline-table;margin:5px 0px 5px 20px">
			       								<div class="pic_box">
					       							<a rel="group" href="${ctx}/${cur}" title="康佳彩电">
					       								<img src="${ctx}/${fn:substringBefore(cur, '.')}_240.jpg" />
					       							</a>
					       							<html-el:hidden property="pic_hidden" styleId="pic_hidden" value="${cur}" />
					       							<a class="xubox_close xubox_close1_0" href="javascript:delPic('pic_${vs.count}_li');"></a>
				       							</div>
				       						</li>
				       					</c:forEach>
			       					</ul>
	       						</td>
	       					</tr>
	    				</c:if>
	    				<!-- 新增附图 -->
	    				<tr>
	    					<td>
			       				<table width="300" border="1" cellpadding="0" cellspacing="0" class="navClass">
			       					<tr>
			       						<th width="12%" nowrap="nowrap">序号</th>
			       						<th width="78%" nowrap="nowrap">图片</th>
			       						<th width="10%" align="center" nowrap="nowrap" style="cursor:pointer;" id="addPicTd"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></th>
			       					</tr>
			       					<tbody id="picTbody"></tbody>
			       				</table>
	    					</td>
	    				</tr>
          			</table>
          			<div>注：此处上传图片尺寸为1:1时，效果最佳。</div>
          		</td>
          	</tr>
          	<tr>
          		<td nowrap="nowrap" class="title_item">产品描述:</td>
          		<td><FCK:editor instanceName="content1">
              			<jsp:attribute name="value">${content1}</jsp:attribute>
            		</FCK:editor>
            		<div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            		<div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div></td>
         	</tr>
          	<tr>
          		<td nowrap="nowrap" class="title_item">产品规格:</td>
          		<td><FCK:editor instanceName="content2">
              			<jsp:attribute name="value">${content2}</jsp:attribute>
            		</FCK:editor>
            		<div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            		<div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div></td>
         	</tr>
          	<tr>
          		<td nowrap="nowrap" class="title_item">售后服务:</td>
          		<td><FCK:editor instanceName="content3">
              			<jsp:attribute name="value">${content3}</jsp:attribute>
            		</FCK:editor>
            		<div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            		<div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div></td>
         	</tr>
         	<tr>
          		<td class="title_item" nowrap="nowrap">排序值：</td>
          		<td><html-el:text property="order_value" styleId="order_value" styleClass="webinput" value="0" size="4" maxlength="4" onfocus="javascript:setOnlyInt(this);" /></td>
          	</tr>
          	<tr>
          		<td align="center" colspan="2">
          			<input class="but4" type="button" name="Submit4" value="保存" id="btn_submit" />
					<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
          		</td>
          	</tr>
	    </table>
	  </html-el:form>
  </div>
  
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#pd_sn").attr("dataType", "LimitB").attr("min","1").attr("max","20").attr("msg", "请填写商品编码，并且不能超过10个汉字或20个英文字符！");
	$("#pd_name").attr("dataType", "Require").attr("msg", "请填写商品名称！");
	$("#original_price").attr("dataType", "Require").attr("msg", "请填写市场价！");
	$("#need_integral").attr("dataType", "Require").attr("msg", "请填写所需积分！");
	
	if (${empty af.map.id}) {
		$("#main_pic_file").attr("dataType", "Filter").attr("Require","true").attr("accept","gif,bmp,png,jpeg,jpg").attr("msg", "请上传主图，并且格式为：gif,bmp,png,jpeg,jpg！");
	}
	
	
	//添加
	window.task_index = 0;
	window.index = 0;
	$("#addPicTd").click(function(){
		index++; //解决file控件
		//$("#picModelTr").clone().appendTo($("#picTbody")).show();
		$("#picTbody").append('<tr id="picModelTr_' + index + '">' +
								'<td align="center">1</td>' +
								'<td align="left" nowrap="nowrap"><input type="file" name="pic_file_' + index + '" id="pic_file_' + index + '" class="webinput" style="width:150px;" /></td>' +
								'<td align="center" nowrap="nowrap" style="cursor:pointer;"><img src="${ctx}/styles/jxc/images/x_.gif" style="vertical-align:middle;" /></td>' +
							  '</tr>');
		task_index++;
		var lastTR = $("tr:last", "#picTbody");
		lastTR.children().eq(0).text(task_index);
		lastTR.children().eq(2).empty().append('<img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:middle;" />');
		$("input[type='file']", lastTR).attr("dataType", "Filter").attr("Require","true").attr("accept","gif,bmp,png,jpeg,jpg").attr("msg", "请上传图片，并且格式为：gif,bmp,png,jpeg,jpg！");

		$("td:last", lastTR).click(function (){
			task_index--;
			$(this).parent().remove();
			var i = 1;
			$("tr", "#picTbody").each(function(){
				if (i <= task_index) {
					$(this).find("td").eq(0).text(i);
					i++;
				}
			});
			window.parent.resizeFrameHeight('mainFrame', 3);
		});
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	$("#ecGiftComm_price").keypress(function(){//单价
		var ecGiftComm_price = $("#ecGiftComm_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(ecGiftComm_price)) {
			$("#ecGiftComm_price").val(0);
		}
	}).keyup(function(){
		var ecGiftComm_price = $("#ecGiftComm_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(ecGiftComm_price)) {
			$("#ecGiftComm_price").val(0);
		}
	}).blur(function(){
		var ecGiftComm_price = $("#ecGiftComm_price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(ecGiftComm_price)) {
			$("#ecGiftComm_price").val(0);
		}
	});	
	
	
	$("#btn_submit").click(function(){
		if (${not empty af.map.id}) {
			if (0 == $("input[type='hidden'][name='main_pic_hidden']").length) {
				$("#main_pic_file").attr("dataType", "Filter").attr("Require","true").attr("accept","gif,bmp,png,jpeg,jpg").attr("msg", "请上传主图，并且格式为：gif,bmp,png,jpeg,jpg！");
			}
		}
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});
});

//正则表达式：只能输入整数
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

//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		//if(obj.value.length == 0) obj.value = "0";
	});
}

function delPic(name){
	if (confirm("确定删除该图片吗？")) {
		setTimeout("hide('" + name + "')",0);
		setTimeout("del('" + name + "')",1500);
	}
}
function hide(name){
	$("#" + name).fadeOut(1000);
}
function del(name){
	$("#" + name).remove();
}

function closeLoading(){
	$.jNotify._close();
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>