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
	  <html-el:form action="/admin/PeCdModel" enctype="multipart/form-data" method="post">
	    <html-el:hidden property="method" value="save" />
	    <html-el:hidden property="id" />
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="pd_type" value="${af.map.pd_type}" />
	    <html-el:hidden property="queryString" />
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      		<tr>
          		<td width="12%" nowrap="nowrap" class="title_item">分公司：</td>
          		<td>
          			<c:if test="${empty af.map.fgs_id}">
	          			<html-el:select property="dept_id" styleId="dept_id" style="width:120px;">
	          				<html-el:option value="">请选择</html-el:option>
	          				<html-el:option value="0">多媒体事业本部</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select>
          			</c:if>
          			<c:if test="${not empty af.map.fgs_id}">${af.map.fgs_name}
          				<html-el:hidden property="dept_id" styleId="dept_id" value="${af.map.fgs_id}" />
          			</c:if>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">规格型号：</td>
          		<td>
          			<select name="pd_spec" id="pdIds" multiple="multiple">
		          		<!--<c:forEach items="${pdList}" var="cur_p" varStatus="vs">
		          			<option value="${cur_p.pd_id}">${cur_p.md_name}</option>
		          		</c:forEach>-->
		          	</select>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品编码：</td>
          		<td><html-el:text property="pd_sn" styleId="pd_sn" styleClass="webinput" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品名称：</td>
          		<td><html-el:text property="pd_name" styleId="pd_name" styleClass="webinput" size="40" /></td>
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
		        			<!--<div id="xubox_border4" class="xubox_border" style="z-index:-1;border:1px solid #CCC;top:-5px;left:-4px;width:268px;height:500px;border:1px solid rgba(0,0,0,0);-webkit-box-shadow:0 0 3px rgba(0,0,0,0.4);-moz-box-shadow:0 0 1px rgba(0,0,0,0.4);box-shadow:0 0 3px rgba(0,0,0,0.4); "></div>-->
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
          		<td class="title_item" nowrap="nowrap">功能分类：</td>
          		<td>
          			<div style="height:23px;">
	          			<ul class="ckUl">
			      			<li id="li_1" class="ck-li">
			      				<html-el:checkbox property="label_3d" styleId="construct_1" value="1" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
			      					<label style="cursor:pointer;vertical-align:middle;" for="construct_1" onclick="choose('label_3d', this.previousSibling)">
			      						<span class="ck-default">3D电视</span>
			      					</label>
			      				</html-el:checkbox>
			      			</li>
			        		<li id="li_2" class="ck-li">
			        			<html-el:checkbox property="label_int" styleId="construct_2" value="1" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
			        				<label style="cursor:pointer;vertical-align:middle;" for="construct_2" onclick="choose('label_int', this.previousSibling)">
			        					<span class="ck-default">智能电视</span>
			        				</label>
			        			</html-el:checkbox>
			        		</li>
			        		<li id="li_3" class="ck-li">
			        			<html-el:checkbox property="label_www" styleId="construct_3" value="1" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
			        				<label style="cursor:pointer;vertical-align:middle;" for="construct_3" onclick="choose('label_www', this.previousSibling)">
			        					<span class="ck-default">网络电视</span>
			        				</label>
			        			</html-el:checkbox>
			        		</li>
			      		</ul>
          			</div>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">尺寸：</td>
          		<td><html-el:text property="pd_size" styleId="pd_size" styleClass="webinput" maxlength="4"/>英寸</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">分辨率：</td>
          		<td align="left">
          			<html-el:text property="pd_res_x" styleId="pd_res_x" value="${af.map.pd_res_x}" styleClass="webinput" size="5" maxlength="5" />&nbsp;×&nbsp;<html-el:text property="pd_res_y" styleId="pd_res_y" value="${af.map.pd_res_y}" styleClass="webinput" size="5" maxlength="5" />
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品描述：</td>
          		<td>
          			<html-el:textarea property="pd_desc" styleId="pd_desc" styleClass="webinput" style="width:70%;height:60px;" onchange="checkMaxLen(this,50)" />	
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
          		<td class="title_item" nowrap="nowrap">分类标签：</td>
          		<td>
          			<ul class="ckUl">
		      			<li id="li_1" class="ck-li">
		      				<html-el:checkbox property="label_of_cate" styleId="label_of_cate_0" value="0" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		      					<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_0" onclick="choose('label_of_cate', this.previousSibling)">
		      						<span class="ck-default">新品</span>
      							</label>
		      				</html-el:checkbox>
		      			</li>
		        		<li id="li_2" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_1" value="1" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_1" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">热卖</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_3" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_2" value="2" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_2" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">直降</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_4" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_3" value="3" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_3" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">特惠</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_5" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_4" value="4" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_4" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">推荐</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		      		</ul>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item">进货价：</td>
          		<td align="left"><html-el:text property="buy_price" styleId="buy_price" styleClass="webinput" maxlength="12" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">零售指导价：</td>
          		<td><html-el:text property="sale_price" styleId="sale_price" styleClass="webinput" maxlength="12" /></td>
          	</tr>
          	<!-- 阶梯价格只对会员商品维护模块起作用 -->
          	<c:if test="${af.map.pd_type eq 3}">
          		<tr>
          			<td class="title_item" nowrap="nowrap">金卡会员价格：</td>
          			<td><html-el:text property="price_la" styleId="price_la" styleClass="webinput" maxlength="12" /></td>
          		</tr>
          		<tr>
          			<td class="title_item" nowrap="nowrap">银卡会员价格：</td>
          			<td><html-el:text property="price_lb" styleId="price_lb" styleClass="webinput" maxlength="12" /></td>
          		</tr>
          		<tr>
          			<td class="title_item" nowrap="nowrap">普通VIP会员价格：</td>
          			<td><html-el:text property="price_lc" styleId="price_lc" styleClass="webinput" maxlength="12" /></td>
          		</tr>
          	</c:if>
          	<tr>
          		<td class="title_item" nowrap="nowrap">排序值：</td>
          		<td><html-el:text property="order_value" styleId="order_value" styleClass="webinput" size="4" maxlength="4" /></td>
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
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#dept_id").attr("dataType", "Require").attr("msg", "请选择分公司！");
	$("#pdIds").attr("dataType", "Require").attr("msg", "请选择规格型号！");
	$("#pd_sn").attr("dataType", "Require").attr("msg", "请填写商品编码！");
	$("#pd_name").attr("dataType", "Require").attr("msg", "请填写商品名称！");
	$("#pd_res_x").attr("dataType", "Integer").attr("msg", "分辨率格式非法！");//.attr("Require", "false");
	$("#pd_res_y").attr("dataType", "Integer").attr("msg", "分辨率格式非法！");//.attr("Require", "false");
	$("#pd_size").attr("dataType", "Integer").attr("msg", "尺寸格式非法！").attr("Require", "false");

	if ("" == "${af.map.id}") {
		$("#main_pic_file").attr("dataType", "Require").attr("msg", "请上传主图！");
	}
	//$("#pic_file").attr("dataType", "Require").attr("msg", "请上传图片！");
	//$("id^=construct_").attr("dataType", "Require").attr("msg", "请选择功能分类！");
	//$("input[type='checkbox'][name='label_of_cate']").attr("dataType", "Group").eq(1).attr("msg", "请选择分类标签！");
	
	//修改回显
	if ("" != "${af.map.id}") {
		/****************功能分类***************/
		//3D电视
		showCkboxEcho("label_3d", "${af.map.label_3d}");
		//智能电视
		showCkboxEcho("label_int", "${af.map.label_int}");
		//网络电视
		showCkboxEcho("label_www", "${af.map.label_www}");
		/****************分类标签***************/
		showCkboxEcho("label_of_cate", "${af.map.label_of_cate}");

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

		//$(".xubox_border", "#main_pic_div")[0].style.pixelHeight = document.getElementById("main_pic_div").offsetHeight + 10;
	}
	
	$("#pdIds").multiselect({
		noneSelectedText: '<span style="font-family:Microsoft YAHEI;">=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:200,
		click: function(event, ui){
			//var storeId = $("#storesList").val();
			//var goodId = ui.value;
			//if (null != storeId && null != goodId) {
			//	getStockNumForSelectPd(storeId, goodId);
			//}
		}
	}).multiselectfilter({label:"<span style='font-family:Microsoft YAHEI;font-size:12px;'>搜索：</span>", width:100}).attr("datatype", "Require").attr("msg", "请选择商品！");

	jLoading("&nbsp;&nbsp;正在初始化数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"top", MinWidth:150});
	//异步初始化规格型号数据
	getPdSpecListByAsync();

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
	
	$("#btn_submit").click(function(){
		if ("" != "${af.map.id}") {
			if (0 == $("input[type='hidden'][name='main_pic_hidden']").length) {
				$("#main_pic_file").attr("dataType", "Require").attr("msg", "请上传主图！");
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

function getPdSpecListByAsync(){
	$.ajax({
		type: "POST" , 
		url: "${ctx}/manager/admin/PeCdModel.do" , 
		data:"method=getPdSpecList&n=" + Math.random(),
		dataType: "json" , 
        async: true, 
        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
        success: function (result) {
			if (result.state == 1) {
				for ( var x in result.list) {
					$("#pdIds").append('<option value="' + result.list[x].pd_id + '">【家电分类 >> 大家电 >> ' + result.list[x].par_cls_name + ' >> ' + result.list[x].cls_name + '】' + result.list[x].md_name + '</option>');
				}
				if ("" != "${af.map.pd_spec}") {//修改回显
					$("#pdIds").val("${af.map.pd_spec}");
				}
				$("#pdIds").multiselect("refresh");
	        	closeLoading();
			}
        }
	});
}

function check(obj){
	if (obj.checked) {//被选中
		//alert("选中");
		//obj.parentNode.className="on";
		obj.parentNode.style.background = "#E60012";//"#66CD00";
		obj.parentNode.style.color = "#FFFFFF";
		
		//var chils= s.childNodes;  //得到s的全部子节点
		//var par=s.parentNode;   //得到s的父节点
		//var ns=s.nextSbiling;   //获得s的下一个兄弟节点
		//var ps=s.previousSbiling;  //得到s的上一个兄弟节点
		//var fc=s.firstChild;   //获得s的第一个子节点
		//var lc=s.lastChild;   //获得s的最后一个子节点
	} else {
		obj.parentNode.style.background = "";
		obj.parentNode.style.color = "";
		//alert("撤销");
	}
}

function checkMaxLen(obj,maxlength){
	   if(obj.value.length>maxlength){
	     obj.value = obj.value.substr(0,maxlength);
	   }
 }


function choose(name, cb){
	var obj = document.getElementsByName(name);
    for (i=0; i<obj.length; i++){
        if (obj[i] != cb){
            obj[i].checked = false;
            obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-default";
        } else {
            //obj[i].checked = cb.checked;
        	//obj[i].checked = true;
        	if (cb.checked) {
				obj[i].checked = true;
        		obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-default";
        		//document.getElementById("alertTr").style.display = "";
			} else {
				obj[i].checked = false;
				obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-visited";
				//document.getElementById("alertTr").style.display = "none";
			}
        }
    }
}

function multichoose(name, cb){
	var obj = document.getElementsByName(name);
    for (i=0; i<obj.length; i++){
    	if (obj[i] == cb){
			if (cb.checked) {
				obj[i].checked = true;
				obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-default";
			} else {
				obj[i].checked = false;
				obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-visited";
			}
    	}
    }
}

function showCkboxEcho(name, value){
	var obj = document.getElementsByName(name);
	for ( var i = 0; i < obj.length; i++) {
		if (value == obj[i].value) {
			obj[i].checked = true;
			obj[i].nextSibling.getElementsByTagName("span")[0].className = "ck-visited";
		}
	}
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
  