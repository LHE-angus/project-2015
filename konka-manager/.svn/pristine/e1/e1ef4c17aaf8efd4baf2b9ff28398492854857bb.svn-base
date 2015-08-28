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
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/top.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/search.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/nav.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/footer.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />

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


input[type='submit'],input[type='button'] { border-radius:3px; border:1px solid #CCC; padding: 0px 5px 6px 5px;background-color:#eee; color:#555; cursor: pointer; margin-right: 1px;}
</style>
<link href="${ctx}/styles/spgl/qudao.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/spgl/aaa.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/spgl/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/spgl/global.css" rel="stylesheet" type="text/css" />
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
  
  <div class="viewtit2">
      <ul>
        <li class="cur pd_content" id="pdc1">商品信息</li>
        <li class="pd_content" id="pdc2">价格维护</li>
        <li class="pd_content" id="pdc3">库存维护</li>
        <li class="pd_content" id="pdc4">商品返利</li>
        <li class="pd_content" id="pdc5">商品积分</li>
      </ul>
  </div>
  <html-el:form action="/spgl/PdShow" enctype="multipart/form-data" styleClass="f1" method="post">
	    <html-el:hidden property="method" value="save" />
	    <html-el:hidden property="id" value="${af.map.id}"/>
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="queryString" styleId="queryString" />
	    <html-el:hidden property="prod_type" value="${af.map.prod_type}" />
  <div id="pdc1_pdc1" style="display:'';overflow-x:hidden;" class="viewbox4">
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      		<tr>
          		<td width="12%" nowrap="nowrap" class="title_item">分公司：</td>
          		<td>
          			<c:if test="${empty af.map.fgs_id}">
	          			<html-el:select property="dept_sn" styleId="dept_sn" style="width:120px;">
	          				<html-el:option value="">请选择</html-el:option>
	          				<html-el:option value="0">多媒体事业本部</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select>
          			</c:if>
          			<c:if test="${not empty af.map.fgs_id}">
          				<c:if test="${af.map.dept_sn eq 0}">多媒体事业本部
          					<html-el:hidden property="dept_sn" styleId="dept_sn" value="0" />
          				</c:if>
          				<c:if test="${af.map.dept_sn eq af.map.fgs_id}">${af.map.fgs_name}
          					<html-el:hidden property="dept_sn" styleId="dept_sn" value="${af.map.fgs_id}" />
          				</c:if>
          			</c:if>
          		</td>
          	</tr>
          	<c:if test="${af.map.prod_type eq 0}">
          	<tr>
          		<td class="title_item" nowrap="nowrap">规格型号：</td>
          		<td>
          			<select name="pd_spec" id="pdIds" multiple="multiple">
		          	</select>
          		</td>
          	</tr>
          	</c:if>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品编码：</td>
          		<td><html-el:text property="pd_sn" styleId="pd_sn" styleClass="webinput" onkeyup="upperCase(this.id)" maxlength="18" /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品名称：</td>
          		<td><html-el:text property="pd_name" styleId="pd_name" styleClass="webinput" size="60" maxlength="100"  /></td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">服务套餐：</td>
          		<td nowrap="nowrap">
          		<c:if test="${not empty af.map.id}">
          		<table width="400" >
          				<c:forEach items="${hadEntityList}" var="cur" varStatus="vs" >
          					<tr><td>${vs.count}</td><td>${cur.goods_name}</td><td>${cur.price}</td></tr>
          				</c:forEach>
          		</table>
          		</c:if>
          		<input type="button" class="but_ec" value="编 辑" onclick="getEcBinding('${af.map.id}', '${af.map.own_sys}');" style="cursor:pointer;" />
          		<table width="400" id="fuwu_table">
          		</table>
          		<html-el:hidden property="e_id" styleId="e_id"  value="${af.map.e_ids}"/>
          		</td>
          	</tr>
          	<tr>
            <td class="title_item" nowrap="nowrap">商品规则：</td>
            <td nowrap="nowrap" >
            <table class="areause1" >
              <tbody>
                <tr>
                  <td><span id="area_name_0">供选择区域列表</span><br />
                    <html-el:select property="select1" multiple="true" style="width:260px;height:200px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);"> 
                      <c:if test="${not empty list1}">
                     <html-el:optionsCollection label="rule_title" value="id" name="list1" />
                      </c:if>
                    </html-el:select>
                    </td>
                  <td><span id="area_name_1">已选择区域列表</span><br />
                    <html-el:select property="select2" multiple="true" style="width:260px;height:200px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty list2}">
                        <html-el:optionsCollection label="rule_title" value="id" name="list2" />
                      </c:if>
                    </html-el:select></td>
                </tr>
              </tbody>
            </table></td>
          </tr>
          	<!-- <tr>
          		<td class="title_item" nowrap="nowrap">商品套餐：</td>
          		<td nowrap="nowrap">
          		<c:if test="${not empty af.map.id}">
          		<table width="400" >
          				<c:forEach items="${hadEntityList1}" var="cur" varStatus="vs" >
          					<tr><td>${vs.count}</td><td>${cur.goods_name}</td><td>${cur.price}</td></tr>
          				</c:forEach>
          		</table>
          		</c:if>
          		<input type="button" class="but_ec" value="编 辑" onclick="getEcBindingForPd('${af.map.id}', '${af.map.own_sys}');" style="cursor:pointer;" />
          		<table width="400" id="fuwu_table2">
          		</table>
          		<html-el:hidden property="e_id2" styleId="e_id2"/>	
          		</td>
          	</tr> -->
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
          	<c:if test="${af.map.prod_type eq 0}">
          	<tr >
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
			        		<!-- li id="li_3" class="ck-li">
			        			<html-el:checkbox property="label_www" styleId="construct_3" value="1" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
			        				<label style="cursor:pointer;vertical-align:middle;" for="construct_3" onclick="choose('label_www', this.previousSibling)">
			        					<span class="ck-default">网络电视</span>
			        				</label>
			        			</html-el:checkbox>
			        		</li -->
			        		<li id="li_4" class="ck-li">
			        			<html-el:checkbox property="label_4k" styleId="construct_4" value="1" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
			        				<label style="cursor:pointer;vertical-align:middle;" for="construct_4" onclick="choose('label_4k', this.previousSibling)">
			        					<span class="ck-default">4K电视</span>
			        				</label>
			        			</html-el:checkbox>
			        		</li>
			      		</ul>
          			</div>
          		</td>
          	</tr>
          	</c:if>
          	<tr style="display: none;">
          		<td class="title_item" nowrap="nowrap">产品类型：</td>
          		<td>
          			<ul class="ckUl">
		      			<li id="li_1" class="ck-li">
		      				<html-el:checkbox property="prod_type" styleId="prod_type_0" value="0" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		      					<label style="cursor:pointer;vertical-align:middle;" for="prod_type_0" onclick="choose('prod_type', this.previousSibling)">
		      						<span class="ck-default">彩电</span>
      							</label>
		      				</html-el:checkbox>
		      			</li>
		        		<li id="li_2" class="ck-li">
		        			<html-el:checkbox property="prod_type" styleId="prod_type_1" value="1" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_1" onclick="choose('prod_type', this.previousSibling)">
		        					<span class="ck-default">冰箱</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_3" class="ck-li">
		        			<html-el:checkbox property="prod_type" styleId="prod_type_2" value="2" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="prod_type_2" onclick="choose('prod_type', this.previousSibling)">
		        					<span class="ck-default">空调</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_4" class="ck-li">
		        			<html-el:checkbox property="prod_type" styleId="prod_type_3" value="3" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="prod_type_3" onclick="choose('prod_type', this.previousSibling)">
		        					<span class="ck-default">小家电</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_5" class="ck-li">
		        			<html-el:checkbox property="prod_type" styleId="prod_type_10" value="10" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="prod_type_10" onclick="choose('prod_type', this.previousSibling)">
		        					<span class="ck-default">配件</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		      		</ul>
          		</td>
          	</tr>
          	<c:if test="${af.map.prod_type eq 0}">
          	<tr>
          		<td class="title_item" nowrap="nowrap">尺寸：</td>
          		<td><html-el:text property="pd_size" styleId="pd_size" styleClass="webinput" size="4" maxlength="4"/>&nbsp;&nbsp;英寸</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">分辨率：</td>
          		<td align="left">
          			<html-el:text property="pd_res_x" styleId="pd_res_x" value="${af.map.pd_res_x}" styleClass="webinput" size="5" maxlength="5" />&nbsp;×&nbsp;<html-el:text property="pd_res_y" styleId="pd_res_y" value="${af.map.pd_res_y}" styleClass="webinput" size="5" maxlength="5" />
          		</td>
          	</tr>
          	</c:if>
          	<tr>
          		<td class="title_item" nowrap="nowrap">实际重量：</td>
          		<td><html-el:text property="sj_weight" styleId="sj_weight" styleClass="webinput" size="10" maxlength="10"/>&nbsp;&nbsp;KG</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">计费重量：</td>
          		<td align="left">
          		<html-el:text property="p_weight" styleId="p_weight" styleClass="webinput" size="10" maxlength="10"/>&nbsp;&nbsp;KG
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
		        		<li id="li_3" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_2" value="2" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_2" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">热销</span>
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
		        					<span class="ck-default">工程机</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_6" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_5" value="5" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_5" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">限时抢购</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_7" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_6" value="6" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_6" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">团购</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_8" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_7" value="7" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_7" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">精品</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_9" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_8" value="8" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_8" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">每周一劲爆</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		        		<li id="li_10" class="ck-li">
		        			<html-el:checkbox property="label_of_cate" styleId="label_of_cate_9" value="9" styleClass="hidden-accessible" style="cursor:pointer;vertical-align:middle;">
		        				<label style="cursor:pointer;vertical-align:middle;" for="label_of_cate_9" onclick="choose('label_of_cate', this.previousSibling)">
		        					<span class="ck-default">样机特区</span>
		        				</label>
		        			</html-el:checkbox>
		        		</li>
		      		</ul>
          		</td>
          	</tr>
          	 <tr>
          <td colspan="2"><table width="80%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td align="center" width="10%" nowrap="nowrap"><span style="color:red;">* </span>属性名称&nbsp;&nbsp;</td>
          			<td width="20%" align="center" nowrap="nowrap"><span style="color:red;">* </span>属性值</td>
          			<td width="5%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
          		</tr>
          			<c:forEach var="cur1" items="${ecList}" varStatus="vs">
          			<tr class="tr_pd_1">
	          			<td align="center" class="td_pd" nowrap="nowrap">
	          			<input type="hidden" name="p_ids" id="p_ids" value="${cur1.id}" />
	          			<input name="prop_name_2" id="prop_name" size="25"  value="${cur1.prop_name}" maxlength="20" class="prop_name_2" /></td>
	          			<td align="center" nowrap="nowrap"><input name="prop_value_2" id="prop_value_${vs.count}" size="40"  value="${cur1.prop_value}" maxlength="20" class="prop_value_2" />
	          			<c:forEach var="cur2" items="${cur1.ecExtendList}"  varStatus="vs2">
	          				<a class="a1" id="prop_value_${vs.count}" >${cur2.prop_value}</a>
	          			</c:forEach>
	          			</td>
	          			<td align="center" style="cursor:pointer;" class="td_del"><img src="${ctx}/images/x_gray.gif" style="vertical-align:middle;top:0px;" /></td>
	          		</tr>
	          		</c:forEach>
          		<tr id="tr_model" style="display:none;">
          			<td align="center" nowrap="nowrap"><html-el:text property="prop_name" styleClass="prop_name" size="40" maxlength="20" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="prop_value" size="40" maxlength="20" styleClass="prop_value" /></td>
          			<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		<tbody id="showAddTrsTbody"></tbody>
          </table>
          </td>
        </tr>
          		<c:if test="${empty af.map.fgs_id}">
		          	<tr>
		          		<td class="title_item" nowrap="nowrap">是否锁定：</td>
		          		<td>
		          		<html-el:radio property="lock_state" styleClass="lock_state"  value="0"  >不锁定</html-el:radio>&nbsp;
		          		<html-el:radio property="lock_state" styleClass="lock_state"  value="1"  >锁定</html-el:radio>
		          		</td>
		          	</tr>
          		</c:if>
          		<c:if test="${not empty af.map.fgs_id && af.map.dept_sn ne 0}">
		          	<tr>
		          		<td class="title_item" nowrap="nowrap">是否锁定：</td>
		          		<td>
		          		<html-el:radio property="lock_state" styleClass="lock_state"  value="0"  >不锁定</html-el:radio>&nbsp;
		          		<html-el:radio property="lock_state" styleClass="lock_state"  value="1"  >锁定</html-el:radio>
		          		</td>
		          	</tr>
          		</c:if>
          	<tr>
          		<td class="title_item" nowrap="nowrap">是否推荐：</td>
          		<td>
          		<html-el:radio property="is_tj" styleClass="is_tj"  value="0"  >不推荐</html-el:radio>
          		<html-el:radio property="is_tj" styleClass="is_tj"  value="1"  >推荐</html-el:radio>
          		</td>
          	</tr>
          		<tr>
          		<td class="title_item" nowrap="nowrap">团购模式：</td>
          		<td>
          		<html-el:radio property="is_djq" styleClass="is_djq"  value="0"  >橙子</html-el:radio>
          		<html-el:radio property="is_djq" styleClass="is_djq"  value="1"  >代金券</html-el:radio>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品类型：</td>
          		<td>
          		<html-el:radio property="own_sys" styleClass="own_sys"  value="1"  >总部工卡</html-el:radio>
          		<html-el:radio property="own_sys" styleClass="own_sys"  value="2"  >触网</html-el:radio>
          		<html-el:radio property="own_sys" styleClass="own_sys"  value="4"  >顺丰</html-el:radio>
          		<html-el:radio property="own_sys" styleClass="own_sys"  value="5"  >分公司工卡</html-el:radio>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">销量：</td>
          		<td><html-el:text property="sale_num" styleId="sale_num" styleClass="webinput" size="6" maxlength="6" /></td>
          	</tr>	
          	<tr>
          		<td class="title_item" nowrap="nowrap">排序值：</td>
          		<td><html-el:text property="order_value" styleId="order_value" styleClass="webinput" size="4" maxlength="4" /></td>
          	</tr>
          	<tr>
          		<td align="center" colspan="2">
					<c:if test="${empty af.map.fgs_id}">
          			<input class="but_ec" type="button" name="Submit4" value="保存" id="btn_submit" />
          			</c:if>
					<input class="but_ec" type="button" name="Submit5" value="返回" id="btn_back" onclick="location.href = '${ctx}/manager/spgl/PdShow.do?method=list&prod_type=${af.map.prod_type}&mod_id=${af.map.mod_id}' " /> 
          		</td>
          	</tr>
      	</table>
      
  </div>
  <div id="pdc5_pdc5" style="display:none;overflow-x:hidden;" class="viewbox4">
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
          		<td class="title_item" nowrap="nowrap">积分类型：</td>
          		<td>
          		<html-el:radio property="integral_type" styleClass="integral_type"  value="0"  >按比例</html-el:radio>&nbsp;
          		<html-el:radio property="integral_type" styleClass="integral_type"  value="1"  >按固定积分</html-el:radio>
          		</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">积分值：</td>
          		<td><html-el:text property="integral" styleId="integral" styleClass="webinput" size="10" maxlength="10" />&nbsp;&nbsp;数值/比例（%）</td>
          	</tr>
      	</table>
  </div>
  </html-el:form>
  <div id="pdc2_pdc2" style="display:none;overflow-x:hidden;">
    	<img src="${ctx}/images/loading45.gif" id="pc_pd_eavl_loading_gif" style="display:none;" />
    	<iframe 
			id="pc_pd_eavl_iframe"
			name="pc_pd_eavl_iframe"
			src="javascript:void(0);"
			height="50"
			width="100%" 
			frameborder="no" 
			border="0" 
			marginwidth="0" 
			marginheight="0" 
			scrolling="no" 
			allowtransparency="yes"
			onload="javascript:dyniframesizeForPdEval('pc_pd_eavl_iframe');"
	   ></iframe>
    </div>
    
    <div id="pdc3_pdc3" style="display:none;overflow-x:hidden;">
    	<img src="${ctx}/images/loading45.gif" id="pc_pd_eavl_loading_gif_3" style="display:none;" />
    	<iframe 
			id="pc_pd_eavl_iframe_3"
			name="pc_pd_eavl_iframe_3"
			src="javascript:void(0);"
			height="50"
			width="100%" 
			frameborder="no" 
			border="0" 
			marginwidth="0" 
			marginheight="0" 
			scrolling="no" 
			allowtransparency="yes"
			onload="javascript:dyniframesizeForPdEval3('pc_pd_eavl_iframe_3');"
	   ></iframe>
    </div>
    
    <div id="pdc4_pdc4" style="display:none;overflow-x:hidden;">
    	<img src="${ctx}/images/loading45.gif" id="pc_pd_eavl_loading_gif_4" style="display:none;" />
    	<iframe 
			id="pc_pd_eavl_iframe_4"
			name="pc_pd_eavl_iframe_4"
			src="javascript:void(0);"
			height="50"
			width="100%" 
			frameborder="no" 
			border="0" 
			marginwidth="0" 
			marginheight="0" 
			scrolling="no" 
			allowtransparency="yes"
			onload="javascript:dyniframesizeForPdEval4('pc_pd_eavl_iframe_4');"
	   ></iframe>
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
	<c:if test="${af.map.prod_type eq 0}">
	$("#pdIds").attr("dataType", "Require").attr("msg", "请选择规格型号！");
	$("#pd_res_x").attr("dataType", "Integer").attr("msg", "分辨率格式非法！");
	$("#pd_res_y").attr("dataType", "Integer").attr("msg", "分辨率格式非法！");
	</c:if>
	$("#pd_sn").attr("dataType", "Require").attr("msg", "请填写商品编码！");
	$("#pd_name").attr("dataType", "Require").attr("msg", "请填写商品名称！");
	$("#pd_size").attr("dataType", "Integer").attr("msg", "尺寸格式非法！").attr("Require", "false");
	$("#integral").focus(function(){setOnlyInt(this);});
	$("#sj_weight" ).attr("focus",setOnlyPosNum); 
	$("#p_weight" ).attr("focus",setOnlyPosNum);  

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
		//showCkboxEcho("label_www", "${af.map.label_www}");
		//4K电视
		showCkboxEcho("label_4k", "${af.map.label_4k}");
		/****************分类标签***************/
		showCkboxEcho("label_of_cate", "${af.map.label_of_cate}");
		/***************产品类别*****************/ 
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
		click: function(event, obj){
			var id = obj.value;
			$.ajax({
				type: "POST" , 
				url: "${ctx}/manager/admin/PeCdModel.do" , 
				data:"method=ajAxGetMdName&pd_id=" + id,
				dataType: "json" , 
		        async: true, 
		        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
		        success: function (result) {
		        	$("#pd_sn").val(result.md_name);
		        }
			});
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

	$(document).delegate(".a1", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标指向的那个图片
		var tt = $(this).attr("id");
		var ta = $(this).text();
		$("input[id='"+tt+"']").val(ta);

	});	

	$(document).delegate(".pd_content", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标指向的那个图片

		$(".pd_content").each(function(){
			var id = $(this).attr("id");
		    if($(this).attr("hover") == "hover"){
				 $(this).removeClass("cur pd_content").addClass("cur pd_content");
				 $("#" + id + "_" + id).show();
				 
				 // 如果是价格维护
				 if("pdc2" == id){
					var url = "${ctx}/manager/spgl/EcGoodsPrice.do?method=list2&mod_id=${af.map.mod_id}&goods_id=${af.map.id}";
					$("#pc_pd_eavl_iframe").hide();
					$("#pc_pd_eavl_loading_gif").show();
					$("#pc_pd_eavl_iframe").attr("src", url);
				 }else if("pdc3" == id){
				//如果是库存维护
					var url = "${ctx}/manager/spgl/EcStocks.do?method=list2&mod_id=${af.map.mod_id}&goods_id=${af.map.id}";
					$("#pc_pd_eavl_iframe_3").hide();
					$("#pc_pd_eavl_loading_gif_3").show();
					$("#pc_pd_eavl_iframe_3").attr("src", url);
				 }else if("pdc4" == id){
				//如果是返利设置
					var url = "${ctx}/manager/spgl/EcBcompPdRebates.do?method=list2&mod_id=${af.map.mod_id}&goods_id=${af.map.id}";
					$("#pc_pd_eavl_iframe_4").hide();
					$("#pc_pd_eavl_loading_gif_4").show();
					$("#pc_pd_eavl_iframe_4").attr("src", url);
				 }
			} else {
				$(this).removeClass("cur pd_content").addClass("pd_content");
				 $("#" + id + "_" + id).hide();
			}
		});

		$(this).removeAttr("hover");
	});


	// 计数，处理点击添加以后输入行的ID问题
	var num_count = 1;
	$("#addPdTD").click(function(){
		var tr_pd = $("#tr_model").clone(true).attr("class","tr_pd");
		tr_pd.find("input").eq(0).attr("id", "goods_id" + num_count);
		tr_pd.find("input").eq(1).attr("id", "goods_id_" + num_count);
		num_count++;
		tr_pd.appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
			if (window.parent.resizeFrameHeight) window.parent.resizeFrameHeight('mainFrame', 3);
	    }).css("cursor", "pointer");
		   
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});

	$("#del").click(function(){
		$(this).parent().remove();
		if (window.parent.resizeFrameHeight) window.parent.resizeFrameHeight('mainFrame', 3);
	}).css("cursor", "pointer");

	
	
	$("#btn_submit").click(function(){
		if ("" != "${af.map.id}") {
			if (0 == $("input[type='hidden'][name='main_pic_hidden']").length) {
				$("#main_pic_file").attr("dataType", "Require").attr("msg", "请上传主图！");
			}
		}
        <c:if test="${not empty ecList}">
		$(".tr_pd_1 .prop_name_2").attr("dataType", "Require").attr("msg", "请填写属性名称！");
		$(".tr_pd_1 .prop_value_2").attr("dataType", "Require").attr("msg", "请填写属性值！");
		</c:if>
		$(".tr_pd .prop_name").attr("dataType", "Require").attr("msg", "请填写属性名称！");
		$(".tr_pd .prop_value").attr("dataType", "Require").attr("msg", "请填写属性值！");
		if(Validator.Validate(this.form, 3)){
			if(confirm("确定提交表单？")){

				var opts=document.getElementById("select2");
				for(var i=0;i<opts.length;i++){
					opts[i].selected=true;
				}

				
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});

});

function isSubmit1(id,type,own_sys){
	if(confirm("确定提交吗"))
	{
		if(type == 1){
			if(own_sys==1){
				location.href = "${ctx}/manager/spgl/EcGoodsPrice.do?method=list2&mod_id=905103&goods_id="+id;
			}else{
				location.href = "${ctx}/manager/spgl/EcGoodsPriceModifyForTouchNet.do?method=list2&mod_id=905103&goods_id="+id;
			}
		}else{
			if(own_sys==1){
				location.href = "${ctx}/manager/spgl/EcStocks.do?method=list2&mod_id=905201&goods_id="+id;
			}else{
				location.href = "${ctx}/manager/spgl/EcGoodsPriceModifyForTouchNet.do?method=list2&mod_id=905201&goods_id="+id;
			}
		 }
	}	
	else
	{
	       return false;
	}
	
}


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

function getEcBinding(id, own_sys){
	var returnValue = window.showModalDialog("EcBindingPd.do?method=selectEcBinding&goods_id="+id+"&mod_id=905105&azaz=" + Math.random(),window,"dialogWidth:700px;status:no;dialogHeight:300px");
	var e_ids = "";
	if(returnValue != null){
		$(".d1").remove();
		$("#d2").remove();
		$("#e_id").val("");
		$("<tr id='d2'><td width=\"280\">套餐名称</td><td>套餐价格</td></tr>").appendTo($("#fuwu_table"));
		for(var i = 0; i < returnValue.split("##").length; i++){
			var temp = 	returnValue.split("##")[i];
			if(temp.length > 0){
			var pds = temp.split("@");
			e_ids += pds[3]+"@";
			var tr = "<tr class='d1'><td>" + pds[1] + "</td><td>" + pds[2] + "</td></tr>";
			$(tr).appendTo($("#fuwu_table"));
			}
		}
	}
	$("#e_id").val(e_ids);
	window.parent.resizeFrameHeight('mainFrame', 3);
}

function getEcBindingForPd(id, own_sys){
	var returnValue = window.showModalDialog("EcBindingPd.do?method=selectEcBindingForPd&goods_id="+id+"&mod_id=905105&azaz=" + Math.random(),window,"dialogWidth:700px;status:no;dialogHeight:550px");
	var e_ids2 = "";
	if(returnValue != null){
		$(".d3").remove();
		$("#d4").remove();
		$("#e_id2").val("");
		$("<tr id='d4'><td>套餐类型</td><td>套餐名称</td><td>套餐价格</td><td>套餐ID</td></tr>").appendTo($("#fuwu_table2"));
		for(var i = 0; i < returnValue.split("##").length; i++){
			var temp = 	returnValue.split("##")[i];
			if(temp.length > 0){
			var pds = temp.split("@");
			e_ids2 += pds[3]+"@";
			var tr = "<tr class='d3'><td>"+pds[0]+"</td><td>" + pds[1] + "</td><td>" + pds[2] + "</td><td>"+pds[3]+"</td></tr>";
			$(tr).appendTo($("#fuwu_table2"));
			}
		}
	}
	$("#e_id2").val(e_ids2);
	window.parent.resizeFrameHeight('mainFrame', 3);
}

function dyniframesizeForPdEval(iframeid) {
	$("#pc_pd_eavl_loading_gif").hide();
	$("#" + iframeid).show();	
	var ifm= document.getElementById(iframeid); 
	var subWeb = document.frames ? document.frames[iframeid].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		ifm.height = subWeb.body.scrollHeight; 
	} 
}

function dyniframesizeForPdEval3(iframeid) {
	$("#pc_pd_eavl_loading_gif_3").hide();
	$("#" + iframeid).show();	
	var ifm= document.getElementById(iframeid); 
	var subWeb = document.frames ? document.frames[iframeid].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		ifm.height = subWeb.body.scrollHeight; 
	} 
}

function dyniframesizeForPdEval4(iframeid) {
	$("#pc_pd_eavl_loading_gif_4").hide();
	$("#" + iframeid).show();	
	var ifm= document.getElementById(iframeid); 
	var subWeb = document.frames ? document.frames[iframeid].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		ifm.height = subWeb.body.scrollHeight; 
	} 
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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "";
	});
}

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
		if(isNaN(obj.value)) obj.value = "";
	});
}

function moveSelected(sourceSelect, targetSelect, isDelete){
	var cachOptionsArray = new Array();
	var index = 0;
	for (var i = sourceSelect.options.length - 1; i >= 0; i--){
		if (sourceSelect.options[i].selected){
			cachOptionsArray[index] = new Option(sourceSelect.options[i].text, sourceSelect.options[i].value);
			if(isDelete==undefined || isDelete==true){
				sourceSelect.options[i] = null;
			}
			index++;
		}
	}
	var exist = false;
	for (var i = cachOptionsArray.length - 1; i >= 0; i--){
		exist = false;
		for (var j = 0; j < targetSelect.options.length; j++){
			if (targetSelect.options[j].value.toString() == cachOptionsArray[i].value.toString()){
				exist = true; 
				break;
			}
		}
		if (!exist){
			targetSelect.options[targetSelect.options.length] = cachOptionsArray[i];
		}
	}
}

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
function upperCase(x)
{
var y=document.getElementById(x).value
document.getElementById(x).value=y.toUpperCase()
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
  