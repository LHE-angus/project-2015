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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>  
<style type="text/css">
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 2px;/*padding:2px 5px;*/}
input,textarea,select,file,button{font-family:Microsoft Yahei;font-size:12px;}
.webinput {background:#f5f4f4;	padding-left: 5px;height: 19px;line-height: 19px;	/*font-family: Arial, Helvetica, sans-serif;*/	border: #ccc solid 1px;}
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

input[type='submit'],input[type='button'] { border-radius:3px; border:1px solid #CCC; background-color:#eee; color:#555; cursor: pointer; margin-right: 1px;}
</style> 
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
    <html-el:form action="/spgl/EcVoteMain" method="post" enctype="multipart/form-data"> 
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readOnly"  value="${empty af.map.id?false:true}" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td nowrap="nowrap" class="title_item" ><font color="red">* </font>标题：</td>
          <td ><html-el:text property="title" styleId="title" size="30" maxlength="30"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">活动简介：</td>
          <td><html-el:text property="brief" styleId="brief" size="30" maxlength="200"/> 
          </td>
        </tr> 
        <tr>
          <td nowrap="nowrap" class="title_item">活动内容：</td>
          <td><FCK:editor instanceName="content">
              <jsp:attribute name="value">${af.map.content}</jsp:attribute>
            </FCK:editor>
            <div class="note">1、此处上传的图片不会自动缩放，请用相关做图软件缩放成您想要的大小；</div>
            <div class="note">2、点击最后一排倒数第三个按钮可实现全屏编辑。</div></td>
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
          <td nowrap="nowrap" class="title_item">是否删除：</td>
          <td><label for="is_del1" style="width:80px;">
              <html-el:radio property="is_del" styleId="is_del1" value="0">未删除</html-el:radio>
            </label>
            <label for="is_del2" style="width:80px;">
              <html-el:radio property="is_del" styleId="is_del2" value="1">已删除</html-el:radio>
            </label></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" ><font color="red">* </font>开始时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.vote_start_time}" pattern="yyyy-MM-dd HH:mm:ss" var="_vote_start_time" />
             <html-el:text styleId="vote_start_time" property="vote_start_time" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_vote_start_time}" />
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" ><font color="red">* </font>结束时间：</td>
          <td width="88%" align="left"><fmt:formatDate value="${af.map.vote_end_time}" pattern="yyyy-MM-dd HH:mm:ss" var="_vote_end_time" />
          	<html-el:text styleId="vote_end_time" property="vote_end_time" size="20" maxlength="15" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="cursor:pointer;text-align:center;" value="${_vote_end_time}" />
          </td>
        </tr>
         <tr>
          <td nowrap="nowrap" class="title_item" ><font color="red">* </font>用户可投票数：</td>
          <td ><html-el:text property="vote_all_num" styleId="vote_all_num" size="6" maxlength="6"/></td>
        </tr> 
        <tr>
          <td nowrap="nowrap" class="title_item">是否发布：</td>
          <td><label for="is_pub1" style="width:80px;">
              <html-el:radio property="is_pub" styleId="is_pub1" value="0">未发布</html-el:radio>
            </label>
            <label for="is_pub2" style="width:80px;">
              <html-el:radio property="is_pub" styleId="is_pub2" value="1">已发布</html-el:radio>
            </label></td>
        </tr>
         <tr>
          <td  nowrap="nowrap" class="title_item" ><font color="red">* </font>所属系统：</td>
          <td  >
          	<html-el:select property="own_sys" styleId="own_sys" disabled="${readOnly}">
          				<html-el:option value="">-请选择-</html-el:option>
          				<html-el:option value="1">工卡</html-el:option>
          				<html-el:option value="2">触网</html-el:option>
          				<html-el:option value="3">会员</html-el:option>
          				<html-el:option value="4">顺丰</html-el:option>
          	</html-el:select>
          </td>
        </tr>  
         <tr>
          <td nowrap="nowrap" class="title_item">活动状态：</td>
          <td><label for="vote_state1" style="width:80px;">
              <html-el:radio property="vote_state" styleId="vote_state1" value="0">未开始</html-el:radio>
            </label>
            <label for="vote_state2" style="width:80px;">
              <html-el:radio property="vote_state" styleId="vote_state2" value="1">进行中</html-el:radio>
            </label>
            <label for="vote_state3" style="width:80px;">
              <html-el:radio property="vote_state" styleId="vote_state3" value="2">已结束</html-el:radio>
            </label>
            </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item">排序值：</td> 
          <td><html-el:text property="order_value" styleId="order_value" style="width:40px;" maxlength="4" size="4" styleClass="webinput" />
            取值范围：0 - 9999， 值越大，显示越靠前。&nbsp;&nbsp;</td>
        </tr> 
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" />
           </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#title").attr("datatype", "Require").attr("msg", "请填写活动标题");
	$("#vote_start_time").attr("datatype", "Require").attr("msg", "请选择活动开始时间");
	$("#vote_end_time").attr("datatype", "Require").attr("msg", "请选择活动结束时间"); 
	$("#vote_all_num").attr("datatype", "Require").attr("msg", "请填写每个用户投票数");
	$("#own_sys").attr("datatype", "Require").attr("msg", "请选择所属系统");
	$("#vote_all_num" ).focus(function(){setOnlyInt(this);}); 
	$("#order_value" ).focus(function(){setOnlyInt(this);});
	
	if ("" == "${af.map.id}") {
		$("#main_pic_file").attr("dataType", "Require").attr("msg", "请上传主图！"); 
	}
	if ("" != "${af.map.id}") {
		$("#main_pic_a").fancybox({
		    'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic',
			'titlePosition' : 'inside',
			'centerOnScroll' : true
		});
		$("a[rel=group]").fancybox({
			'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic',
			'titlePosition' : 'over',
			'cyclic'        : true,
			'titleFormat'	: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
		});
	}

	window.task_index = 0;
	window.index = 0;
	//添加
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
		$("input[type='file']", lastTR).attr("dataType", "Require").attr("msg", "请上传图片！");

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


	$("a[id^='att_del_']").click(function() {
	  	  var a = this;
	  	   if(!confirm('确实要删除？')) return;
	  	   // $.post("EcVoteMain.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	  	   if (true){alert("恭喜您，删除成功!");$(a).parent().parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
	  	   //});
	   }); 
		

	$("#btn_submit").click(function(){ 
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
	
}); 

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

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//正数
function setOnlyPosNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
