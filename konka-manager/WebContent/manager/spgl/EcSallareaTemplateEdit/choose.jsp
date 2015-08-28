<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style>
		*{margin:0;padding:0;}  
		ul,li{ list-style:none;}
		.main-table{ width:780px; overflow:hidden; border:1px #dbdbdb solid}
		.table_left{ width:200px; background:#e6d7c1;float:left; margin-top:15px; height:500px; border:1px solid #dedddd; margin-left:10px;overflow:auto;}
		.table_right{ width:98%; background:#e6d7c1;float:left;background:#fff; overflow:auto;margin-left:10px; height:579px}
		.table_right table{ border-collapse:collapse;  }
		.table_right table th{border:1px #d8d8d8 solid; height:35px; font-size:12px; color:#666; line-height:35px; background:#f2f2f2}
		.table_right table td{border:1px #d8d8d8 solid; height:35px; text-align:center; font-size:12px; color:#666}
		.table_left li{height:32px; width:200px; line-height:32px;;font-size:12px; text-align:center;border-bottom:1px solid #c8b08c;}
		.table_text{ width:38px; height:20px; background:#fff; border:1px solid #c8b08c}
		.store-foot{border:none}
		.store-path{ padding:10px 0 10px 10px;overflow:hidden; line-height:25px;font-size:12px;position: relative}
		.btn25x{ height: 22px;display: inline-block;padding-left: 10px;margin: 0px 1px;cursor: pointer;border:none;text-decoration: none}
		.btn26x{ height: 22px;display: inline-block;padding-left:11px;*padding-left:5px;margin: 0px 1px;cursor: pointer;border:none;padding-right:11px;text-align:center;}
		.btn25x span{display: inline-block;	
			height: 22px;
			line-height: 24px;
			padding-right: 8px;
			overflow: hidden;
			color: #333;
			cursor: pointer;font-size:12px} 
	</style>
</head>
<body> 
 <form name="storeSalesAreaForm" method="post" action="${ctx}/manager/spgl/EcSallareaTemplateEdit.do"> 
 <input type="hidden" name="method" value="save" />
 <input type="hidden" name="rightstr" />
 <input type="hidden" name="citystr" />
 <input type="hidden" name="coutry" />
 <input type="hidden" name="mod_id" value="${af.map.mod_id}" /> 
 <input type="hidden" name="par_index" id="par_index"/>
 <input type="hidden" name="id" value="${af.map.id}" id="t_id"/>
<div class="store-right" style="min-height:0;overflow:hidden;">
          <div class="store-path"><%@ include file="/commons/pages/messages.jsp" %> </div>
		<div class="store-path">销售区域管理 &gt; 销售区域编辑&nbsp;&nbsp;<c:if test="${not empty af.map.city}">当前选择：${af.map.city} </c:if>      
 				<div style="position: absolute;right:10px;top:12px">
						<a href="javascript:submitForm();" class="btn25x" target="_self"><span style="color: blue;">保存模板</span></a>  
				</div>	 
 			</div>
		  <div class="table_right" style="height:75px">	
		  	<table width="99%" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
						    <td colspan="3" align="center">模板名称：<input type="text" name="title" value="${af.map.title}" class="input-text" id="title"/> 
						    </td>
						    <td colspan="3" align="center">模板状态：<select name="state" id="state">  
						          <option value="0" <c:if test="${af.map.state eq 0}">selected</c:if>>发布</option>
						          <option value="1" <c:if test="${af.map.state eq 1}">selected</c:if>>不发布</option> 
						        </select>
							</td>
							<td colspan="3" align="center">所属组织：<select name="dept_id" id="dept_id">
						          <option value="" >请选择--</option> 
						          <c:forEach items="${groupList}" var="cur">
						          <option value="${cur.id}" <c:if test="${cur.id eq af.map.dept_id}">selected</c:if> >${cur.group_name}</option>  
						          </c:forEach>
						        </select>
							</td>
						</tr>
					</tbody>
			</table>
			</div>	
			<div class="table_right"> 
	 				<table width="100%" cellspacing="0" cellpadding="0" style="float:right">
	 				 	<tbody><tr>
				   			<th width="15%" style="text-align:center;">序号</th>
				   			<th width="30%" style="text-align:center;"><a href="javascript:checkAllMd()" class="list"><span style="color: blue;">全选/取消</span></a></th>
				   			<th style="text-align:center;">县级名称</th> 
				  			</tr>
				  		</tbody> 
				  		<c:forEach items="${coutryList}" var="cur" varStatus="vs">
				  		<tr>
					  	  <td>${vs.count}</td> 
					  	  <td>
					  	  	<input type="checkbox" name="selected" value="${cur.p_index}" class="country"/>      
					  	  </td>
				    	<td> ${cur.p_name}</td> 
					 	</tr>
					 	</c:forEach>
				  		</table>
			</div>
</div>
</form>
<jsp:include page="/__analytics.jsp" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	var par_index = window.parent.document.getElementById("pp");
	$("#par_index").val(par_index.innerHTML); 

	 var cs=window.parent.document.getElementById("countryString").innerHTML;  	

		// 动态监测 checkbox 是否选择 
		$(document).delegate(".country", "click", function(){
			var checkbox = $(".country");
			for(var i = 0; i < checkbox.length; i++){
				if("checked" == $(checkbox[i]).attr("checked")){
					var value = $(checkbox[i]).val()+"-";
					if(cs.indexOf(value)==-1){
						cs=cs+value;
					}else{
						continue;
					}
					
				}else if("checked"!=$(checkbox[i]).attr("checked")){ 
					var ch=$(checkbox[i]).val()+"-";
					cs=cs.replace(ch,"");
					if(cs=="-"){
						cs="";  
					}
				}
			}
			window.parent.document.getElementById("countryString").innerHTML=cs;    
		});

	var province_choose="${af.map.province_choose}";
	var city_choose="${af.map.city_choose}";
	var country_choose="${af.map.country_choose}";

	if(province_choose!=""){
		var selectedmd= document.getElementsByName("selected");
		for(var i=0;i<selectedmd.length;i++){
			selectedmd[i].checked=true;
		}
	}

	if(city_choose!=""){
		var selectedmd= document.getElementsByName("selected");
		for(var i=0;i<selectedmd.length;i++){
			selectedmd[i].checked=true;
		}
	}

	if(country_choose!=""){
		var countryList = country_choose.split(',');
		var selectedmd= document.getElementsByName("selected");
		for(var i=0;i<selectedmd.length;i++){
			for(var j=0;j<countryList.length;j++){
				if(selectedmd[i].value==countryList[j]){  
					selectedmd[i].checked=true;
					 break;
				}
			}
		}
	}

	
});

function checkAllMd(){
	var falg1=false;
	var selectedmd= document.getElementsByName("selected");
	for(var i=0;i<selectedmd.length;i++){
		if(selectedmd[i].checked!=true){
			falg1=true;
			break;
		}
	}
	for(var i=0;i<selectedmd.length;i++){
		selectedmd[i].checked=falg1;
	}

	if(falg1){
	    var cs=window.parent.document.getElementById("countryString").innerHTML; 
		if(cs==""){    
			var cc="";
			for(var i=0;i<selectedmd.length;i++){
				cc=cc+selectedmd[i].value+"-";
			}
			window.parent.document.getElementById("countryString").innerHTML=cc; 
		}else{   
			for(var i=0;i<selectedmd.length;i++){
				if(cs.indexOf(selectedmd[i].value)==-1){
					cs=cs+selectedmd[i].value+"-";
				}
			}  
			window.parent.document.getElementById("countryString").innerHTML=cs;

		}
	}else{
		var cs=window.parent.document.getElementById("countryString").innerHTML;
		for(var i=0;i<selectedmd.length;i++){
				var ch=selectedmd[i].value+"-";  
				cs=cs.replace(ch,"");
		}
		if(cs=="-"){
			cs="";  
		}
		window.parent.document.getElementById("countryString").innerHTML=cs;  
	}
}



function submitForm(){  
    var strvar ="";
    var cityvar="";
	var righhtList = parent.document.getElementsByName("rights_province");    
	var righhtSubList = parent.document.getElementsByName("rights_city"); 

	if(righhtList!=null&&righhtList.length>0){
		for(var i =0;i<righhtList.length;i++){ 
			var flagright = righhtList[i].checked;
			if(flagright){  
				strvar = strvar+righhtList[i].value+"-";
			}
		}
	} 
	if(righhtSubList!=null && righhtSubList.length>0){
		for(var i = 0;i<righhtSubList.length;i++){
			var flagright = righhtSubList[i].checked;
			if(flagright){  
				cityvar = cityvar+righhtSubList[i].value+"-";
			}
		}
	}  
   document.getElementsByName("rightstr")[0].value =strvar; 
   document.getElementsByName("citystr")[0].value =cityvar; 
	 
	 var title = document.getElementsByName("title")[0].value; 
	 title = title.replace(/[ ]/g,"");  
	 if(title ==""){
	 	alert("请输入模板名称!");
	 	return;
	 }


	 var d_id= document.getElementById("dept_id"); 
	 if(d_id.value ==""){
	 	alert("请选择所属组织!");  
	 	return;
	 }  
	

	var coutry="";
	var selecteds= document.getElementsByName("selected"); 
	if(selecteds!=null && selecteds.length>0){
		for(var i = 0;i<selecteds.length;i++){
			var flagright = selecteds[i].checked;
			if(flagright){  
				coutry = coutry+selecteds[i].value+"-";  
			}
		}
	}
	document.getElementsByName("coutry")[0].value =coutry; 


	var id = '${af.map.id}';  
	if(id!=""&&id!=null){
		document.getElementsByName("coutry")[0].value=window.parent.document.getElementById("countryString").innerHTML;

	}


	if(strvar==""&&coutry==""&&cityvar==""){  
		alert("请选择销售区域！");
	 	return;
	}

	if(confirm("确定操作吗?") ){
	}else{
		 return;
	} 

	
 	document.storeSalesAreaForm.submit();	 
}


</script>
</body>
</html>
