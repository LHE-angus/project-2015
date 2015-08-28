<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#dayList {width:350px;}
#dayList td {width:50px;height:40px; border: 0px solid #E3E3E3;}
.sunday_td {color:red; }

#noList {width:500px;}
#noList td {width:100px;height:30px; border: 0px solid #E3E3E3;}

#timetb {width:360px;border: 0px solid #CCCCCC;}
#timetb td {border: 0px solid #E3E3E3;}

#timeList {width:300px;}
#timeList td {border: 0px solid #E3E3E3;}
#timeList input {width:102px;height:21px; border: 1px solid #84C1FF;}
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
      <html-el:form action="/ywygps/KonkaYwyMobileSetPlan">
        <html-el:hidden property="method" value="view" />
        <html-el:hidden property="mod_id" />
        <html-el:hidden property="id" value="${af.map.id}"/>
        <html-el:hidden property="p_id" value="${af.map.p_id}"/>
        <html-el:hidden property="s_id" value="${af.map.s_id}"/>
        <html-el:hidden property="month_days" styleId="month_days"/>
        <html-el:hidden property="queryString" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
           <tr class="tabtt1">
               <td colspan="2" align="center">业务员手机设置&nbsp;&nbsp;月度方案</td>
           </tr>     
          <tr>
            <td width="18%" class="title_item" nowrap="nowrap" align="right"><strong>公司名称：</strong></td>
            <td align="left" >${af.map.entp_name}</td>
          </tr>
          <c:if test="${not empty af.map.p_id}">
          <tr>
            <td width="18%" class="title_item" nowrap="nowrap" align="right"><strong>上一月度方案：</strong></td>
            <td align="left" >${af.map.before_setplan_name}</td>
          </tr>
          </c:if>
          <tr>
            <td width="18%" class="title_item" nowrap="nowrap" align="right"><strong>月度方案名：</strong></td>
            <td align="left" >
              <c:if test="${not empty af.map.id}">
                 ${af.map.setplan_name}
              </c:if>
              <c:if test="${empty af.map.id}">
                 <html-el:text property="setplan_name" styleId="setplan_name" maxlength="100" size="80" style="width:240px;"/>
              </c:if>
            </td>
          </tr>
          <tr>
            <td width="18%" align="right"><strong>年份：</strong></td>
            <td>
              <html-el:select property="year" styleId="year" style="width:200px;">
              <c:if test="${empty af.map.id}">
                <html-el:option value="${af.map.year}">${af.map.year}年</html-el:option>
                <html-el:option value="${af.map.year + 1}">${af.map.year + 1}年</html-el:option>
              </c:if>
              <c:if test="${not empty af.map.id}">
                <html-el:option value="${af.map.year}">${af.map.year}年</html-el:option>
              </c:if>
              </html-el:select>
            </td>
          </tr>
          <tr>
            <td width="18%" align="right"><strong>月份：</strong></td>
            <td>
              <html-el:select property="month" styleId="month" style="width:200px;">             
                <c:if test="${empty af.map.id}">
                  <c:forEach begin="1" end="12" varStatus="vs">
                    <html-el:option value="${vs.count}">${vs.count}月</html-el:option>
                  </c:forEach>
                </c:if>
                <c:if test="${not empty af.map.id}">
                  <html-el:option value="${af.map.month}">${af.map.month}月</html-el:option>
                </c:if>
              </html-el:select>
            </td>
          </tr>
          <tr>
            <td width="18%" align="right"><strong>发送日期：</strong></td>
            <td>
                 <table id="dayList" border="0">                 
                 </table>
            </td>
          </tr>
          <tr>
            <td width="18%" align="right"><strong>发送时间段：</strong></td>
            <td>
                 <table id="timetb" border="0">
                   <tr>
                     <td>
                        <table border="0">
                          <tbody id="timeList">
                           <tr>
                             <td align="center" nowrap="nowrap" width="100" style="border-bottom:1px solid #E3E3E3;">开始时间</td>
                             <td align="center" nowrap="nowrap" width="100" style="border-bottom:1px solid #E3E3E3;">结束时间</td>
                             <td align="center" nowrap="nowrap" width="100" style="border-bottom:1px solid #E3E3E3;">操作</td>
                           </tr> 
                           <tr>
                             <td><html-el:text property="start_time" styleId="start_time_0" onclick="WdatePicker({dateFmt:'HH:mm:ss'})" style="cursor:pointer;" readonly="true" /></td>
                             <td><html-el:text property="end_time" styleId="end_time_0" onclick="WdatePicker({dateFmt:'HH:mm:ss'})" style="cursor:pointer;" readonly="true" /></td>
                             <td>&nbsp;</td>
                           </tr>
                           </tbody>   
                        </table>
                     </td>
                     <td valign="top" width="50">
                        &nbsp;
                     </td>
                   </tr>
                 </table>
                 <br/> 
            </td>
          </tr>
          <tr>
              <td width="18%" height="30" align="right">发送间隔：</td>
              <td>默认:
                <html-el:select property="default_interval" styleId="default_interval">
                  <html-el:option value="600">10分钟</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;&nbsp;<html-el:checkbox property="user_defined" styleId="user_defined" onclick="userDefinedInterval(this,'user_defined_interval','default_interval')" >
                <label for="user_defined">&nbsp;启用自定义：</label>
                </html-el:checkbox>
                <html-el:select property="user_defined_interval" styleId="user_defined_interval" disabled="true" style="width:80px;">
                       <html-el:option value="60">1分钟</html-el:option>
                 <c:forEach begin="1" end="60" varStatus="vs">
                     <c:if test="${vs.count % 5 eq 0}">
                        <html-el:option value="${vs.count * 60}">${vs.count}分钟</html-el:option>
                     </c:if>
                 </c:forEach>
                </html-el:select></td>
          </tr>
           <tr>
            <td width="18%" align="right"><strong>不允许发送的手机号：</strong></td>
            <td>
                 <c:if test="${not empty af.map.id}">
                   <table id="noList" border="0">                 
                   </table>
                 </c:if>
                 <br/><br/>
            </td>
          </tr>
       
          <tr>
            <td colspan="2" align="center">
              &nbsp;&nbsp;<html-el:button property="back" value=" 返回 " onclick="history.back();" styleId="btn_back" styleClass="but5"/></td>
          </tr>
        </table>
      </html-el:form>
    </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    $("#month").val('${af.map.month}');
    $("#month").change();
    
	$("#btn_submit").click(function(){	
		if(Validator.Validate(this.form, 3)){
			$("#btn_submit"   ).attr("disabled",true);
			$("#btn_back"     ).attr("disabled",true);			
			this.form.submit();
		}
	});

});

function userDefinedInterval(obj,id,oid){
    var select = document.getElementById(id);
    var other = document.getElementById(oid);
	if(obj.checked){
		select.disabled = false;
		other.disabled = true;
	}else{
		select.disabled = true;
		other.disabled = false;
	}
}

function initDefinedInterval(selectId,value){
	var select = document.getElementById(selectId);
    for(var i=0;i<select.childNodes.length;i++){
        if(select.childNodes[i].value == value){
        	select.selectedIndex = i; 
        }
    }	
}

<c:if test="${not empty af.map.id}">
// 修改操作，初始化自定义下拉框
var value = '${af.map.time_interval}';
if(value != '600'){
   var user_defined = document.getElementById("user_defined");
   user_defined.checked = true;
   userDefinedInterval(user_defined,"user_defined_interval","default_interval");
   initDefinedInterval("user_defined_interval",value);
}
</c:if>

function checkAll(e,id) {
    var tb = document.getElementById(id);
    var inputs = tb.getElementsByTagName("input");
    for (var i =0; i < inputs.length; i++) {
	    inputs[i].checked = e.checked;
    }
}

function setOnlyNum(obj) {
	var v = obj.value.replace(/[^\d-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}
function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	if(v==0){
		obj.value='';
	}else{
		obj.value=v;
	}
}


/******************************日期*****************************************/
var solarMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var weeks = new Array('一','二','三','四','五','六','日');

//返回公历 y年某m+1月的天数
function solarDays(y,m) {
     if(m==1)
       return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28);
     else
       return(solarMonth[m]);
}


// 参数(y年,m+1月)
function calendar(y,m,tb_id,days_arr) {
    var sDObj = new Date(y,m,1,0,0,0,0);//当月一日日期
    this.length    = solarDays(y,m);    //当月天数
    this.firstWeek = sDObj.getDay();    //当月1日星期几

    $("#month_days").val(this.length);
    $("#"+tb_id).empty();
    var td = null,len = 0;
    var myCalender = document.getElementById(tb_id);
    var tr = document.createElement("tr");
    for(var i = 0; i < 7; i++) {
        td = document.createElement("td");
        td.innerHTML = weeks[i];
        tr.appendChild(td);
    }
    myCalender.appendChild(tr);
    tr = document.createElement("tr");

    for(var i = 1; i < this.length +1; i++) {
        if(i == 1){
            var sp = this.firstWeek == 0? 7:this.firstWeek;
            for(var j = 1; j < sp; j++) {
               td = document.createElement("td");
               td.innerHTML = "&nbsp;";
               tr.appendChild(td);
               len++;
           }
        }
        td = document.createElement("td");
        var ischecked = " ";
		if(days_arr && days_arr[i-1] == "1") ischecked = "checked=\"checked\"";

        var str = "<input type=\"checkbox\" value=\""+i+"\" name=\"week_days\" id=\"week_days_"+i+"\" "+ischecked+"><label for=\"week_days_"+i+"\">&nbsp;"+i+"</label>";
        td.innerHTML = str;
        if( (len+1) % 7 == 0 || (len+2) % 7 == 0)td.setAttribute("class","sunday_td");
        if(len%7 == 0 ){
             if(len != 0) myCalender.appendChild(tr);
             tr = document.createElement("tr");
        }
        tr.appendChild(td);
        len++;
    }
    
    while(len % 7 !=0){
        td = document.createElement("td");
        td.innerHTML = "&nbsp;";
        tr.appendChild(td);   
        len++;
    }
    myCalender.appendChild(tr);
}

$("#month").change(function(){
	  var days_str = '${af.map.date_str}';
	  var days_arr = null;
	  if(days_str != ''){
		  days_arr = days_str.split(",");
	  }
      var ty = $("#year").val();
      var tm = $("#month").val();
      new calendar(parseInt(ty),parseInt(tm) - 1,"dayList",days_arr);     
});

<c:if test="${not empty af.map.id}">
  var mobile_nos = "${af.map.blockmobile_str}";
  function blockMobiles(nos_str,tb_id) {
	  var nos = nos_str.split(",");
	  $("#"+tb_id).empty();
	  var tb = document.getElementById(tb_id);
	  var td = null,len = 0;
	  for(var i = 0; i < nos.length; i++) {
	        td = document.createElement("td");
	        var ischecked = "checked=\"checked\"";
	        var str = "<input type=\"checkbox\" value=\""+nos[i]+"\" name=\"blockmobile\" id=\"blockmobile_"+nos[i]+"\" "+ischecked+"><label for=\"blockmobile_"+nos[i]+"\">&nbsp;"+nos[i]+"</label>";
	        td.innerHTML = str;
	        if(len % 5 == 0 ){
	             if(len != 0) tb.appendChild(tr);
	             tr = document.createElement("tr");
	        }
	        tr.appendChild(td);
	        len++;
	    }
	    
	    while(len % 5 !=0){
	        td = document.createElement("td");
	        td.innerHTML = "&nbsp;";
	        tr.appendChild(td);   
	        len++;
	    }
	    tb.appendChild(tr);
  }
  blockMobiles(mobile_nos,"noList");
</c:if>

var timeListCount = 1;
function addTimeElement(tb_id,times_str){
	var timeList = document.getElementById(tb_id);
	var tr = document.createElement("tr");
	tr.setAttribute("id","tr_"+timeListCount);

    var times_arr = ["",""];
    if(times_str != "")times_arr = times_str.split("--");
    
	
    var td1 = document.createElement("td");
    td1.innerHTML = "<input type=\"text\" name=\"start_time\" value=\""+times_arr[0]+"\" styleId=\"start_time_"+timeListCount+"\" onclick=\"WdatePicker({dateFmt:'HH:mm:ss'});\" style=\"cursor:pointer;\" readonly=\"true\">";

    var td2 = document.createElement("td");
    td2.innerHTML = "<input type=\"text\" name=\"end_time\" value=\""+times_arr[1]+"\" styleId=\"end_time_"+timeListCount+"\" onclick=\"WdatePicker({dateFmt:'HH:mm:ss'});\" style=\"cursor:pointer;\" readonly=\"true\">";

    var td3 = document.createElement("td");
    td3.innerHTML = "<a href=\"javascript:void(0);\" onclick=\"deleteTimeElement('"+tb_id+"',"+timeListCount+");\" >删除</a>";

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    timeList.appendChild(tr);
    timeListCount++;
}
function deleteTimeElement(tb_id,count){
	var timeList = document.getElementById(tb_id);
	var tr = document.getElementById("tr_"+count);
	timeList.removeChild(tr);
}

function modifyTimeElement(times_str){
    var times_arr = ["",""];
    if(times_str != "")times_arr = times_str.split("--");	
	var start_time = document.getElementById("start_time_0");
	start_time.value = times_arr[0];
	var end_time = document.getElementById("end_time_0");
	end_time.value = times_arr[1];
}
<c:if test="${not empty af.map.id}">
   var time_str = "${af.map.time_str}";
   var times_arr = time_str.split(",");
   modifyTimeElement(times_arr[0]);
   for(var i = 1; i < times_arr.length; i++) {
	   addTimeElement("timeList",times_arr[i]);
   }
</c:if>


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
