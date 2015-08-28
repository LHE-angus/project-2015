<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center"></td> 
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >产品类别名称</td>
      </tr>
        <tr>
          <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" class="pks"  value="0" /></td>   
          <td align="center" nowrap="nowrap">1</td>
          <td align="left" nowrap="nowrap" id="s_0">彩电</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" class="pks"  value="3" /></td>
          <td align="center" nowrap="nowrap">2</td>
          <td align="left" nowrap="nowrap" id="s_3">小家电</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" class="pks"  value="5" /></td>
          <td align="center" nowrap="nowrap">3</td>
          <td align="left" nowrap="nowrap" id="s_5">洗衣机</td>
        </tr>
        <tr>
          <td align="center" nowrap="nowrap" colspan="3"><input type="button" value="确认" onclick="clos();" /></td>
        </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    var api = frameElement.api, W = api.opener;
    
	var cs= W.document.getElementById("prod_types").value;    
	var prod_names= W.document.getElementById("pd_name_hid").value;  
	

	if(cs!=""){
		var cs1=cs.substring(0,cs.length-1);
		var countryList = cs1.split(',');
		var selectedmd= document.getElementsByName("pks");
		for(var i=0;i<selectedmd.length;i++){
			for(var j=0;j<countryList.length;j++){
				if(selectedmd[i].value==countryList[j]){ 
					selectedmd[i].checked=true;
					 break;
				}
			}
		}

	}
	
	
	//  动态监测 checkbox 是否选择 
	$(document).delegate(".pks", "click", function(){
		var checkbox = $(".pks");
		for(var i = 0; i < checkbox.length; i++){
			if("checked" == $(checkbox[i]).attr("checked")){
				var value = $(checkbox[i]).val()+",";
				if(cs.indexOf(value)==-1){
					cs=cs+value;
				}else{
					continue;
				}

				var id = $(checkbox[i]).val();
				var p_name = $("#s_"+id).html()+",";
				if(prod_names.indexOf(p_name)==-1){
					prod_names=prod_names+p_name;
				}else{
					continue;
				}
				
			}else if("checked"!=$(checkbox[i]).attr("checked")){ 
				cs=cs.substring(0,cs.length-1);
				var ss=cs.split(",");
				var cc="";
				for(var j=0;j<ss.length;j++){
					if(ss[j]!=$(checkbox[i]).val()){
						cc=cc+ss[j]+","; 
					}
				}
				cs=cc;
				if(cs==","){
					cs="";
				}

				prod_names=prod_names.substring(0,prod_names.length-1);
				var prod_names_s=prod_names.split(",");
				var pp="";
				for(var j=0;j<prod_names_s.length;j++){
					var id = $(checkbox[i]).val();
					var p_name = $("#s_"+id).html();    
					if(prod_names_s[j]!=p_name){
						pp=pp+prod_names_s[j]+",";  
					}
				}
				prod_names=pp;
				if(prod_names==","){
					prod_names="";
				}
				
			}
		}

		var p_name_show="";
		if(prod_names!=""){
			if(prod_names.substring(prod_names.length-1,prod_names.length)==","){
				p_name_show=prod_names.substring(0,prod_names.length-1); 
			}	
		}else{
			p_name_show=prod_names; 
		}
		
		W.document.getElementById("prod_types").value=cs;  
		W.document.getElementById("pd_name_hid").value=prod_names;
		W.document.getElementById("prod_name").value=p_name_show;     
	});
	
	
});

function clos(){
	 var api = frameElement.api, W = api.opener;
	 W.document.getElementById("goods_hid").value="";
	 W.document.getElementById("goods_name_hid").value="";
	 W.document.getElementById("goods").value="";
	 api.close();
}


//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
