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
  <div >
	  <html-el:form action="/spgl/PdShowNew" enctype="multipart/form-data" styleClass="f1" method="post">
	    <html-el:hidden property="method" value="saveShow" /> 
	    <html-el:hidden property="id" value="${af.map.id}"/>
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="queryString" styleId="queryString" />
	    
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      			<tr>
          		<td class="title_item" nowrap="nowrap">商品编码：</td>
          		<td>${af.map.pd_sn}</td>
          	</tr>
          	<tr>
          		<td class="title_item" nowrap="nowrap">商品名称：</td>
          		<td>${af.map.pd_name}</td>
          	</tr>
          	<tr>
            <td class="title_item" nowrap="nowrap">促销规则：</td>
            <td nowrap="nowrap" >
            <table class="areause1" >  
              <tbody>
                <tr>
                  <td><span id="area_name_0">供选择促销规则</span><br />
                    <html-el:select property="select1" multiple="true" style="width:260px;height:200px;" styleId="select1" onchange="moveSelected(this.form.select1, this.form.select2);"> 
                      <c:if test="${not empty list1}">
                     <html-el:optionsCollection label="rule_title" value="id" name="list1" />
                      </c:if>
                    </html-el:select>
                    </td>
                  <td><span id="area_name_1">已选择促销规则</span><br />
                    <html-el:select property="select2" multiple="true" style="width:260px;height:200px;" styleId="select2" onchange="moveSelected(this.form.select2, this.form.select1);">
                      <c:if test="${not empty list2}">
                        <html-el:optionsCollection label="rule_title" value="id" name="list2" />
                      </c:if>
                    </html-el:select></td>
                </tr>
              </tbody>
            </table></td>
          </tr>
          	<tr>
          		<td align="center" colspan="2"> 
          			<c:if test="${is_admin eq 1 or (is_fgs eq 1 and af.map.is_lock eq 0 and (fn:contains(af.map.group_id,cur.dept_sn) and cur.dept_sn ne 0 ))}">	
					<input class="but_ec" type="button" name="Submit4" value="保存" id="btn_submit" /> 
          			</c:if>
          		    <input class="but_ec" type="button" name="Submit4" value="关闭" id="btn_close"  onclick="closeW();"/>
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
	
	$("#btn_submit").click(function(){
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

function closeW(){ 
	parent.$.fancybox.close(); 
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




//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
  