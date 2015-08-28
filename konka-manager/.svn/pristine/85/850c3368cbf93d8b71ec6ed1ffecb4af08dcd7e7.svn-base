<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
<style type="text/css">
.areause1 {
}
.areause1 td {
	border-bottom: 0px solid #E3E3E3;
	border-right: 0px solid #E3E3E3;
	padding: 0px 0px 0px;
}
#select_models {
}
#select_models td {
	border-bottom: 2px dotted #FFDCB9;
	padding-left:5px;
}
.btn_del_s {
	background: url("${ctx}/images/del_btn.png") no-repeat scroll 0 0 transparent;
	border: 0 none;
	color: #333333;
	cursor: pointer;
	font: 12px/20px "宋体";
	padding-left: 4px;
	text-align: left;
	width: 24px;
}
.td_bg_img {
	background: url("${ctx}/images/td_bg.gif") no-repeat scroll 0 0 transparent;
	border: 0 none;
	color: #333333;
	cursor: pointer;
	font: 12px/20px "宋体";
	padding-left: 4px;
	text-align: left;
	height:24px;
}
.select_model {
	background: url("${ctx}/images/manager/sel_but.png") no-repeat scroll 0 0 transparent;
	border: 0 none;
	color: #333333;
	cursor: pointer;
	font: 12px/20px "宋体";
	padding-left: 25px;
	text-align: left;
	height:24px;
}
</style>
<base target="_self">
</base>
</head>
<body>
<div class="oarcont" align="left">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1" id="model_div" style="display:none;">
    <html-el:form action="/admin/ChannelBestSellingModelsAnalysis">
      <html-el:hidden property="method" value="listStat" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="model_list" styleId="model_list" value=""/>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
        <tr>
          <td width="15px"></td>
          <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable1">
              <tr class="tabtt1">
                <td>&nbsp;</td>
                <td><strong class="fb">型号：</strong>
                  <html-el:text property="column_11" styleId="column_11" style="width:200px;" /></td>
                <td width="100px"><input class="but1" type="button" name="Submit" value="搜索" onclick="getModelList();" /></td>
              </tr>
            </table>
            <table id="model_List" width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
              <tr class="tabtt1" style="height:18px;">
                <td align="center" nowrap="nowrap" width="30" style="height:18px;">序号</td>
                <td nowrap="nowrap">机型</td>
                <td align="center" nowrap="nowrap" width="10%">选择机型</td>
              </tr>
              <tr>
                <td align="center" id="m_0_0" ></td>
                <td id="m_0_1"></td>
                <td align="center" id="m_0_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_1_0" ></td>
                <td id="m_1_1"></td>
                <td align="center" id="m_1_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_2_0" ></td>
                <td id="m_2_1"></td>
                <td align="center" id="m_2_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_3_0" ></td>
                <td id="m_3_1"></td>
                <td align="center" id="m_3_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_4_0" ></td>
                <td id="m_4_1"></td>
                <td align="center" id="m_4_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_5_0" ></td>
                <td id="m_5_1"></td>
                <td align="center" id="m_5_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_6_0" ></td>
                <td id="m_6_1"></td>
                <td align="center" id="m_6_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_7_0" ></td>
                <td id="m_7_1"></td>
                <td align="center" id="m_7_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_8_0" ></td>
                <td id="m_8_1"></td>
                <td align="center" id="m_8_2"></td>
              </tr>
              <tr>
                <td align="center" id="m_9_0" ></td>
                <td id="m_9_1"></td>
                <td align="center" id="m_9_2"></td>
              </tr>
            </table>
            <div id="pager_control" style="margin-top:5px;text-align:center;"> </div></td>
          <td width="15px"></td>
          <td width="322px" valign="top"><table width="322px" border="0" cellspacing="0" cellpadding="0" class="rtable1">
              <tr class="tabtt1">
                <td width="5px"></td>
                <td><strong class="fb">选择的统计机型</strong></td>
              </tr>
            </table>
            <div style="width:320px;height:322px;overflow-y:auto;border:1px solid #E3E3E3;">
              <table width="100%" class="areause1">
                <tbody id="select_models">
                </tbody>
              </table>
            </div>
            <div style="width:320px;margin-top:5px;text-align:right;">
              <html-el:button property="" value="清空" styleClass="but3" styleId="btn_clear" onclick="clearModels()"/>
            </div></td>
          <td width="15px"></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" id="stat_div" >
    <div class="oartop" style="border:1px solid #E3E3E3;margin:0 0;padding: 0px 0 0 0px;text-align:center;"> 统计结果 </div>
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td width="15"></td>
        <td><strong class="fb">类别：</strong>
          <input type="radio" name="type" id="type_1" value="1"   />
          <label for="type_1">单客户</label>
          <input type="radio" name="type" id="type_5" value="5" />
          <label for="type_5">经办</label>
          <input type="radio" name="type" id="type_3" value="3" />
          <label for="type_3">分公司</label>
          &nbsp;<span id="keyword_input" style="display:none"><strong class="fb">R3编码</strong>：
          <input name="keyWord" size="30" maxlength="255" id="keyWord" />
          </span> &nbsp;<span id="keyword_select_handle" style="display:none"> <strong class="fb">分公司名称</strong>：
          <select name="branch_area_name_link" id="branch_area_name_link">
            <option value="">-请选择-</option>
            <c:forEach items="${BranchList}" var="cur">
              <option value="${cur.branch_area_name}">${cur.branch_area_name}</option>
            </c:forEach>
          </select>
          <strong class="fb">经办名称</strong>：
          <select name="handle_name" id="handle_name">
            <option value="">请选择</option>
            <c:forEach items="${handleList}" var="cur">
              <option value="${cur.handle_name}">${cur.handle_name}</option>
            </c:forEach>
          </select>
          </span> &nbsp;<span id="keyword_select_branch" style="display:none"> <strong class="fb">分公司名称</strong>：
          <select name="branch_area_name_select" id="branch_area_name_select">
            <option value="${af.map.branch_area_name }">请选择</option>
            <c:forEach items="${BranchList}" var="cur">
              <option value="${cur.branch_area_name}">${cur.branch_area_name}</option>
            </c:forEach>
          </select>
          </span></td>
      </tr>
      <tr>
        <td width="15"></td>
        <td><strong class="fb">时间：</strong>
          <input  type="text" name="date_start"  id="date_start"  size="10" maxlength="10" readonly="readonly" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          至
          <input type="text" name="date_end" id="date_end"  size="10" maxlength="10" readonly="readonly" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          &nbsp;
          <input class="but1" type="button" name="Submit"  id="btn_stat"  value="统计" onclick="toStat(this);" />
          &nbsp;
          <input  class="but2" type="button" name="Submit" value="选机型" onclick="showModeldiv(this,'model_div');"  />
          &nbsp;
          <input class="but_excel" type="submit"  value="导出" name="toExcelButton1" onclick="toExcel('divExcel', '?method=toExcel');" /></td>
      </tr>
    </table>
    <div id="divExcel" title="畅销机型统计.xls" style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tbody id="stat_result">
        </tbody>
      </table>
      <div id="stat_pager_control" style="margin-top:5px;text-align:center;"> </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager2.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
var first_show = true;
var show_flg = true;
function showModeldiv(obj,model_div){
	if(first_show){
		  getModelList();
		  first_show = false;
	}
	if(show_flg){
		$("#"+model_div).show();
		show_flg = false;
	}else{
		$("#"+model_div).hide();
		show_flg = true;
	}	
}

$(document).ready(function(){
	  
	$("#branch_area_name_link").change( function() {
		var branch_area_name = $("#branch_area_name_link").val();
		$("#handle_name").empty();

		if(""==branch_area_name){
	   		var opt = new Option( "请选择...",""); 
			$("#handle_name").get(0).options.add(opt);
		   	}
	   	if(branch_area_name!=""){
		   	$.ajax({
				type: "POST",
				cache: false,
				url: "${ctx}/manager/admin/ChannelBestSellingModelsAnalysis.do",
				data: "method=getHandleList&branch_area_name=" + $("#branch_area_name_link").val(),
				dataType: "json", 
				error: function(request, settings){
					alert("系统出现错误，请重新操作或联系管理员");
					var opt = new Option( "请选择...",""); 
				    $("#handle_name").get(0).options.add(opt);},
				success: function(data) {
					if (data.length >= 1) {
						var opt = new Option( "请选择...",""); 
						$("#handle_name").get(0).options.add(opt);
						for(var i = 0; i < data.length - 1; i++) {
							var opt = new Option( data[i].name,data[i].id); 
							$("#handle_name").get(0).options.add(opt);
						}
					}
				}
			});
	   	}
	});
	
<c:if test="${not empty af.map.branch_area_name_link}">
	$("#branch_area_name_link").val("${af.map.branch_area_name_link}");
	$("#branch_area_name_link").change();
	$("#handle_name").val("${af.map.handle_name}");
	</c:if>

<c:if test="${not empty af.map.branch_area_name_select}">
	$("#branch_area_name_select").val("${af.map.branch_area_name_select}");
</c:if>
   
	<c:if test="${af.map.type eq 1}">
	  	$("#keyword").attr("dataType", "Require").attr("msg", "请填写");
	  	$("#branch_area_name_select").attr("dataType", null);
	  	$("#handle_name").attr("dataType", null);
	  	$("#type_1").trigger("click");
	  	$("#keyword_input").show();
	  	$("#keyword_select_handle").hide();
	  	$("#keyword_select_branch").hide();
  	</c:if>
 
	 $("#type_1").click(function(){
		 $("#keyword").attr("dataType", "Require").attr("msg", "请填写");
		 $("#branch_area_name_select").attr("dataType", null);
		 $("#handle_name").attr("dataType", null);
		 $("#keyword_input").show();	
		 $("#keyword_select_handle").hide();
		 $("#keyword_select_branch").hide();
		 });
	 $("#type_3").click(function(){
		 $("#branch_area_name_select").attr("dataType", "Require").attr("msg", "请填写");
		 $("#keyword").attr("dataType", null);
		 $("#handle_name").attr("dataType", null);
		 $("#keyword_input").hide();
		 $("#keyword_select_handle").hide();
		 $("#keyword_select_branch").show();
		 });
	 $("#type_5").click(function(){
		 $("#handle_name").attr("dataType", "Require").attr("msg", "请填写");
		 $("#keyword").attr("dataType", null);
		 $("#branch_area_name_select").attr("dataType", null);
		 $("#keyword_input").hide();
		 $("#keyword_select_handle").show();
		 $("#keyword_select_branch").hide();
		 });
	 
	 var b_d = $("#date_start");
	 var e_d = $("#date_end");
	 
	 $("form").submit(function(){
		   var isSubmit = Validator.Validate(this, 1);	
		   if (isSubmit) {
			   if(e_d.val() < b_d.val()) {
					alert("开始日期必须小于等于结束日期!");
					return false;
			   }
			   $("#submit_loading").show();
		   }
		   return isSubmit;
		});
});

var mode_arr = [];
function choose(obj,md_name){
  if(typeof mode_arr[md_name] =='undefined' || mode_arr[md_name] ==''){
	  var tb = document.getElementById("select_models");
	  mode_arr[md_name] = md_name;
	  
	  var tr = document.createElement("tr");
	  tr.setAttribute("id", "tr_"+md_name);
	  tr.style.backgroundColor = "#FFF3EE";
	  var td1 =  document.createElement("td");
	  td1.innerHTML = md_name;
	  td1.setAttribute("id",md_name);
	  
	  var td2 =  document.createElement("td");
	  td2.setAttribute("width","30px");
	  var tdstr= "<input type=\"button\" value=\"\" class=\"btn_del_s\" onclick=\"deleteModel('"+md_name+"')\"/>";
	  td2.innerHTML = tdstr;
	  tr.appendChild(td1);
	  tr.appendChild(td2);
	  tb.appendChild(tr);
	  obj.setAttribute("disabled",true);
	  obj.value="已 选";
	  obj.parentNode.parentNode.style.backgroundColor = "#FFF3EE";
  } 
}

function deleteModel(md_name){
	var tb = document.getElementById("select_models");
	var tr = document.getElementById("tr_"+md_name);
	tb.removeChild(tr);
	var obj = document.getElementById("btn_"+md_name);
	if(obj){
		obj.removeAttribute("disabled");
		obj.value="选 择";
		obj.parentNode.parentNode.style.backgroundColor = "";
	}
	mode_arr[md_name] = "";
}

function clearModels(){
  var tb = document.getElementById("select_models");
  for (var i = tb.childNodes.length-1; i >= 0; i--) {
    	var tr = tb.childNodes[i];
    	if(tr.id){
	    	var id =  tr.getAttribute("id");
	    	mode_arr[id.substr(3)] = "";
	    	tb.removeChild(tr);
	    	var obj = document.getElementById("btn_"+id.substr(3));
	    	if(obj){
	    		obj.removeAttribute("disabled");
	    		obj.value="选 择";
	    		obj.parentNode.parentNode.style.backgroundColor = "";
	    	}
    	}
  }
}
		
function getModelList() {
    var column_value = $("#column_11").val();
   	var requestPageSize = $("#pageSize").val();
	requestPageSize = /^\d+$/g.test(requestPageSize) ? requestPageSize : 10;
	$("#pager_control").pager("${ctx}/manager/admin/ChannelBestSellingModelsAnalysis.do", 
				$.extend({}, {method: 'getModelList'}, {column_11:column_value}),
				$.extend({}, {showPagesCount : 0,pageSize : requestPageSize, use_defined_flg :true,
				use_defined_type : 3}), function(ret,cRow) {
			    var count = 0;
			    if(ret){
			    	count = ret.list.length;
				    for (var i = 0; i < ret.list.length; i++) {
						 var cur =ret.list[i];
					     var td1 = document.getElementById("m_"+i+"_0");
					     td1.innerHTML = cRow + i;
					     var td2 = document.getElementById("m_"+i+"_1");
					     td2.innerHTML = cur.model_name;
					     var td3 = document.getElementById("m_"+i+"_2");
					     var tdstr = "";
					     if(typeof mode_arr[cur.model_name] =='undefined' || mode_arr[cur.model_name] ==''){
					    	 tdstr = "<input type=\"button\" id=\"btn_"+cur.model_name+"\" value=\"选 择\" class=\"but2\" onclick=\"choose(this,'"+cur.model_name+"')\"/>";
					    	 td3.parentNode.style.backgroundColor = "";
						 }else{
					    	 tdstr = "<input type=\"button\" id=\"btn_"+cur.model_name+"\" value=\"已 选\" class=\"but2\" onclick=\"choose(this,'"+cur.model_name+"')\" disabled=\"true\" />";
					    	 td3.parentNode.style.backgroundColor = "#FFF3EE";
					     }
					     td3.innerHTML = tdstr; 
				    }
			    }
			    for (var i = count; i < 10; i++) {
			    	 for(var j = 0; j < 3; j++) {
			    		 var td = document.getElementById("m_"+i+"_"+j);
			    		 td.innerHTML = "<input type=\"button\" value=\"\" class=\"td_bg_img\"/>";
			    		 td.parentNode.style.backgroundColor = "";
					 }
			    }	
	});
}

function number_deal(num,len){
	    var per = num;
  	    if(num == 0){
  	    	per = "--";
  	    }else if(num == 100){
  	    	per = "100%";
  	    }else{
  	    	per = parseFloat(num).toFixed(len)+"%";
  	    }
  	    return per;
}

function toStat(obj){
	  var tb = document.getElementById("select_models");
	  var ids = [];
	  for (var i =0; i < tb.childNodes.length; i++) {
	    	var tr = tb.childNodes[i];
	    	if(tr.id){
		    	var id = tr.getAttribute("id");
		    	ids[ids.length] = id.substr(3);
	    	}
	  }
	  if(ids.length == 0){
		  alert("请选择机型！");
		  return;
	  }

	  var option ={};
	  option.model_list = ids.join(",");
      var m = document.getElementsByName("type");
      var type = "";
      for(var i=0;i<m.length;i++) {
        if(m[i].checked) {type = m[i].value;break;}
      }
      var keyWord = $("#keyWord").val();
      var n = document.getElementsByName("date");
      var date = "";
      for(var i=0;i<n.length;i++) {
        if(n[i].checked) {date = n[i].value;break;}
      }
      option.type = encodeURI(type);
      var date_start = $("#date_start").val();
      var date_end = $("#date_end").val();
      var handle_name =$("#handle_name").val();
      var branch_area_name_link =$("#branch_area_name_link").val();
      var fgs =$("#branch_area_name_select").val();
      if(type==5 && branch_area_name_link != ""){
    	  if(handle_name ==""){
    		  alert("请选择经办");
    		  return  false;
    	  }
      }
      if(keyWord != '') option.keyWord = encodeURI(keyWord);
      option.date = encodeURI(date);
      
      if(handle_name != '') option.handle_name = encodeURI(handle_name);
      if(branch_area_name_link != '') option.branch_area_name_link = encodeURI(branch_area_name_link);
      if(fgs != '') option.fgs = encodeURI(fgs);
     
      if(date_start != '' && date_end !=''){
      if(date_end < date_start){
    	  alert("开始日期必须小于等于结束日期!");
			return false;
      }else{
          if(date_start != '') option.date_start = encodeURI(date_start);
          if(date_end != '') option.date_end = encodeURI(date_end);
      }
      }
       // $("#btn_stat").attr("disabled",true);
      obj.setAttribute("disabled",true);
      obj.value = "统计中";
	  var requestPageSize = $("#pageSize2").val();
	  requestPageSize = /^\d+$/g.test(requestPageSize) ? requestPageSize : 10;
	  $("#stat_pager_control").pager2("${ctx}/manager/admin/ChannelBestSellingModelsAnalysis.do", 
		$.extend({}, {method: 'listStat'}, option),
		$.extend({}, {showPagesCount : 0,pageSize : requestPageSize, use_defined_flg :true,
			use_defined_type : 3}), function(ret,cRow) {
           var count = 0;
           var cols_count = 5 + ids.length;
    	var stat_result = document.getElementById("stat_result");
	    if(ret){
	    	$("#stat_result").empty();
	    	var tr = document.createElement("tr");
	    	tr.className = "tabtt1";
	    	tr.style.height = "18px";
	    	tbHeader_a(tr);
	  	    for(var i= 0;i< ids.length; i++){
	  	    	var td = createTD("td",ids[i],false,{name:"nowrap",value:"nowrap"});
	  	    	tr.appendChild(td);
	  	    }
	    	tbHeader_b(tr);
	    	stat_result.appendChild(tr);
		    count = ret.list.length;
			for (var i = 0; i < ret.list.length; i++) {
			    tr = document.createElement("tr");
				var cur = ret.list[i];
				var td1 = addTD("td",i + cRow,{name:"align",value:"center"});
				var td2 = addTD("td",cur.task_name,{name:"nowrap",value:"nowrap"});
				tr.appendChild(td1);
				tr.appendChild(td2);
				var sum_list = cur.sumList;
		  	    for(var j= 0;j< sum_list.length; j++){
		  	    	var td = createTD("td",sum_list[j]);
		  	    	tr.appendChild(td);
		  	    }
		  	    var tdx = addTD("td",cur.t_sum);
				var tdy = addTD("td",number_deal(cur.t_per0,5));
				var tdz = addTD("td",number_deal(cur.t_per1,5));
				tr.appendChild(tdx);
				tr.appendChild(tdy);
				tr.appendChild(tdz);
				stat_result.appendChild(tr);
			}
		}
	    for (var i = count; i < 10; i++) {
	    	 var tr = document.createElement("tr");
	    	 for(var j = 0; j < cols_count; j++) {
	    		 var td = document.createElement("td");
	    		 td.innerHTML = "<input type=\"button\" value=\"\" class=\"td_bg_img\"/>";
	    		 tr.appendChild(td);
			 }
	    	 stat_result.appendChild(tr);
	    }
	    $("#stat_div").show();
	    if(show_flg){
		    $("#model_div").hide();
	    }		
	    obj.removeAttribute("disabled"); 
	    obj.value = "统计";
	  });
}
var type_names= ["","单客户","","分公司","","经办"];
var th_names = ["序号","名称","所选机型累计","所选机型在该###的占比","所选机型在所有###的占比"];

function createTD(tag,value,tp,att){
	var m = document.getElementsByName("type");
	var type = "";
	for(var i=0;i<m.length;i++) {
	  if(m[i].checked) {type = m[i].value;break;}
	}
	  var td =  document.createElement(tag);
	  if(att)td.setAttribute(att.name,att.value);
	  if(tp)value = value.replace(tp,type_names[parseInt(type)]);
	  td.innerHTML = value;
	  return td;
}
function addTD(tag,value,att){
	  var td =  document.createElement(tag);
	  if(att)td.setAttribute(att.name,att.value);
	  td.innerHTML = value;
	  return td;
}
function tbHeader_a(tr){
	  var td1 = createTD("td",th_names[0]);
	  td1.setAttribute("nowrap","nowrap");
	  td1.setAttribute("width","30px");
	  var td2 = createTD("td",th_names[1]);
	  td2.setAttribute("nowrap","nowrap");
	  tr.appendChild(td1);
	  tr.appendChild(td2);
}
function tbHeader_b(tr){
	  var td1 =  createTD("td",th_names[2],"###");
	  td1.setAttribute("nowrap","nowrap");
	  td1.setAttribute("width","85px");
	  var td2 =  createTD("td",th_names[3],"###");
	  td2.setAttribute("nowrap","nowrap");
	  td2.setAttribute("width","162px");	  
	  var td3 =  createTD("td",th_names[4],"###");
	  td3.setAttribute("nowrap","nowrap");
	  td3.setAttribute("width","172px");
	  tr.appendChild(td1);
	  tr.appendChild(td2);
	  tr.appendChild(td3);
}


//以下为自定义的jQuery插件
$.fn.extend({
   	textInputWithVal : function(val){
   		var $this = this;
   		return $this.attr("emptyValue", val).blur(function() {
   			if ($.trim($this.val()).length == 0) { $this.val(val); }
   		}).focus(function() {
   			if ($.trim($this.val()) == val) { $this.val(""); }
   		}).val(val);
   	},
   	json : function(){
   		var obj = {};
   		var ss = $(this).serialize().split("&");
   		for (var i = 0; i < ss.length; i++) {
   			obj[ss[i].split("=")[0]] = ss[i].split("=")[1];
   		}
   		return obj;
   	}
});
$.extend({
   	isBlank : function(s) { return (s === undefined) || (s === null) || /^\s*$/g.test(s.replace("null", "")); },
   	isNotBlank : function(s) { return !$.isBlank(s); }
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
