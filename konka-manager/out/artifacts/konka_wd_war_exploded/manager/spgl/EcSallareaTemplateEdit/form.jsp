<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.treeview, .treeview ul { 
	padding: 0;
	margin: 0;
	list-style: none;
}

.treeview ul {
	background-color: white;
	margin-top: 4px;
}

.treeview .hitarea {
	height: 16px;
	width: 16px;

	cursor: pointer;
	position:absolute;
	left:0px;
	top:3px;
	
	
}
/* fix for IE6 */
* html .hitarea {
	display: inline;
	float:none;
}

.treeview li { 
	margin: 0;
	padding: 3px 0pt 3px 16px;
}

.treeview a.selected {
	background-color: #eee;
}

#treecontrol { margin: 1em 0; display: none; }

.treeview .hover { color: red; cursor: pointer; }

.treeview li.collapsable, .treeview li.expandable { background-position: 0 -176px; }

.treeview .expandable-hitarea { background-position: -80px -3px; }

.treeview li.last { background-position: 0 -1766px }
.treeview li.lastCollapsable { background-position: 0 -111px }
.treeview li.lastExpandable { background-position: -32px -67px }

.treeview div.lastCollapsable-hitarea, .treeview div.lastExpandable-hitarea { background-position: 0; }

.treeview .placeholder {
	height: 16px;
	width: 16px;
	display: block;
}

.filetree li { padding: 3px 0 2px 16px; }  
.filetree span.folder, .filetree span.file { padding: 1px 0 1px 16px; display: block; }


</style>
</head> 
<body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<div class="oarcont" style="margin-bottom: 100px;">
  <div style="overflow:hidden;border:1px solid #dbdbdb; margin-left:10px;float:left;width:100%;">  
   <div class="gd_ad1" style="background:#f7f7f7;position:relative">
			<div class="nav_path" style="height:31px;line-height:31px; text-indent:10px;font-size:14px">销售区域管理</div>
  </div>    
    <div class="houtai_ml_l" style="height:700px;width:25%;overflow-y:scroll;overflow-x:hidden;margin-top:0px;float:left;font-size:12px;color:#666;position:relative">
				<div class="houtai_ml_t" style="padding:10px">区域所在的目录位置如下：<span id="pp" style="display: none;"></span><span id="countryString" style="display: none;"></span></div> 
				<div class="houtai_ml_t" style="padding:10px"><a href="javascript:checkAll()" class="fc-link" style="color:#005EA7;">全选/取消</a></div> 
				 <div id="main">   
						<ul id="browser" class="filetree treeview">
							<!-- 省级 start -->
								<li class="closed collapsable" style="position:relative;float:left;width:100%"><div class="hitarea closed-hitarea collapsable-hitarea"></div>
									<c:forEach items="${bfList}" var="cur" > 
									<span class="folder" style="margin-left:20px;"> 
									 <span id="span_open_${cur.p_index}" onclick="shouTree('${cur.p_index}');">+</span><span id="span_close_${cur.p_index}" style="display: none" onclick="hideTree('${cur.p_index}');">-</span>
										<input style="left:15px;top:1px;" type="checkbox"  id="province_${cur.p_index}" class="province" name="rights_province" value="${cur.p_index}"/> ${cur.p_name}
									</span>      
									<!-- 市级start -->
									<c:forEach items="${cur.baseProvinceListFourList}" var="cur2">
									<ul style="float: left; width: 100%; display: none;" id="div_${cur.p_index}" class="div_${cur.p_index}">   
												<li class="closed last" style="position:relative;" >  
													<span class="folder" style="margin-left:25px;">    
														<input style="left:15px;top:1px;" type="checkbox" name="rights_city" class="city_${cur.p_index}"  value="${cur2.p_index}"/><a href="javascript:choisePc('${cur2.p_index}')" class="">${cur2.p_name}[选择]</a>
													</span>
												</li> 
									</ul>
									</c:forEach>
									<!-- 市级end -->
									</c:forEach>
									 
								</li>
						</ul> 
				</div>
	</div>			 
		<div class="houtai_ml_r">       
			<iframe name="pcLeftFrame" id="pcLeftFrame" height="700px" width="74%" src="${ctx}/manager/spgl/EcSallareaTemplateEdit.do?method=add&id=${af.map.id}&mod_id=${af.map.mod_id}" border="1" frameborder="0" scrolling="no" style="float:left;"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe>
		</div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var proStr = '${af.map.proStr}';
	if(proStr!=""){
		var proList=proStr.split(",");
		for(var i=0;i<proList.length;i++){
			var pro=proList[i];
			var selectedprovince= document.getElementsByName("rights_province");
			for(var j=0;j<selectedprovince.length;j++){
				if(selectedprovince[j].value==pro){
					selectedprovince[j].checked=true;
					
					 document.getElementById("span_open_"+pro).style.display='none';
					 var citysDivs=$(".div_"+pro);
					 for(var m=0;m<citysDivs.length;m++){
						 citysDivs[m].style.display='block';  
					 }
					 document.getElementById("span_close_"+pro).style.display='inline';	

					var citys=$(".city_"+pro);
					for(var k =0;k<citys.length;k++){
						citys[k].checked = true;
					}
				}
			}
		}
	}
	var cityStr = '${af.map.cityStr}';
	if(cityStr!=""){
		var cityList=cityStr.split(",");
		for(var i=0;i<cityList.length;i++){
			var city=cityList[i];
			var selectedcity= document.getElementsByName("rights_city");
			for(var j=0;j<selectedcity.length;j++){
				if(selectedcity[j].value==city){ 
					selectedcity[j].checked=true;
				    var p_index= selectedcity[j].className.split("_")[1];  
				    document.getElementById("span_open_"+p_index).style.display='none';
					 var citysDivs=$(".div_"+p_index);
					 for(var m=0;m<citysDivs.length;m++){
						 citysDivs[m].style.display='block';  
					 }
					 document.getElementById("span_close_"+p_index).style.display='inline';	
				}

			}
		}	
	}

	
	
	$(".province").click(function(){
		var p_index=$(this).attr("id").split("_")[1];  
		if($(this).attr("checked")=="checked"){
			var citys=$(".city_"+p_index);
			for(var i =0;i<citys.length;i++){
				citys[i].checked = true;
			}	  
		}else{   
			var citys=$(".city_"+p_index);
			for(var i =0;i<citys.length;i++){
				citys[i].checked = false;  
			}
		}
	});


	var cys = '${af.map.cys}';
	if(cys!=null&&cys!=""){
		$('#countryString').html(cys);   
	}

	

});

var id="${af.map.id}";	  

function checkAll(){
	var falg1=false;
	var selectedprovince= document.getElementsByName("rights_province");
	for(var i=0;i<selectedprovince.length;i++){
		if(selectedprovince[i].checked!=true){
			falg1=true;
			break;
		}
	}
	var selectedcitys= document.getElementsByName("rights_city");
	for(var i=0;i<selectedcitys.length;i++){
		if(selectedcitys[i].checked!=true){
			falg1=true;
			break;
		}
	}
	for(var i=0;i<selectedprovince.length;i++){
		selectedprovince[i].checked=falg1;
	} 
	for(var i=0;i<selectedcitys.length;i++){
		selectedcitys[i].checked=falg1;
	}  
}	

function shouTree(p_index){
	 document.getElementById("span_open_"+p_index).style.display='none';
	 var citys=$(".div_"+p_index);
	 for(var i=0;i<citys.length;i++){
		 citys[i].style.display='block';  
	 }
	 document.getElementById("span_close_"+p_index).style.display='inline';	
}

function hideTree(p_index){  
	 document.getElementById("span_open_"+p_index).style.display='inline';  
	 var citys=$(".div_"+p_index);
	 for(var i=0;i<citys.length;i++){
		 citys[i].style.display='none';
	 }	
	 document.getElementById("span_close_"+p_index).style.display='none';	
}

function choisePc(par_index){

	var cs =$('#countryString').html(); 
	var id=window.frames["pcLeftFrame"].document.getElementById("t_id").value;   
	$("#pp").html(par_index);
	if(id=="" || id=="undefined"){
		$("#pcLeftFrame").attr("src","${ctx}/manager/spgl/EcSallareaTemplateEdit.do?method=add&par_index="+par_index+"&id="+id+"&mod_id=${af.map.mod_id}");
	}else{
		$.ajax({
			type: "POST",url: "<c:url value='/manager/spgl/EcSallareaTemplateEdit.do' />",
			data: {"method":"ajaxcountry", "id":id, "par_index":par_index ,"timestamp":new Date().getTime() },
			dataType: "json",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {},
			success: function(result){
				if(result.status=='1'){
					if(result.cc!=""){
						if(cs==""){ 
							$('#countryString').html(result.cc+"-");
						}else{
							 var cslist=result.cc.split("-");	
							 for(var i=0;i<cslist.length;i++){
								if(cs.indexOf(cslist[i])==-1){
									cs=cs+cslist[i]+"-";
								}else{
									continue;
								}
							 }
							 $('#countryString').html(cs);    
						} 
					}
					$("#pcLeftFrame").attr("src","${ctx}/manager/spgl/EcSallareaTemplateEdit.do?method=add&par_index="+par_index+"&id="+id+"&mod_id=${af.map.mod_id}");
				}
			}
		}); 
	}
	
}


function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	})
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	})
	//this.text.selected;
}


//]]></script>

<jsp:include page="/__analytics.jsp" />
</body>
</html>