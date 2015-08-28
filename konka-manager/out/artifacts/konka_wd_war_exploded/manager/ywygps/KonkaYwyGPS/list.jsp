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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/convertor.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script> 
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
	<div style="width:100%;height:500px;border:1px solid gray" id="container"> </div>
	<table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
        <td width="10"></td>
        <td> <html-el:form action="/ywygps/KonkaYwyGPS">
        	<strong class="fb">业务员姓名：</strong>
            <input type="text" name="ywy_name_like" id="ywy_name_like" style="width:150px;" maxlength="30"/>
             &nbsp;&nbsp;<strong class="fb">更新时间：</strong>
             <html-el:text styleClass="webinput" property="update_time_st" styleId="update_time_st" value="${af.map.update_time_en}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
	          &nbsp;至
	          <html-el:text styleClass="webinput" property="update_time_en" styleId="update_time_en" value="${af.map.update_time_en}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:left;width:80px;" />
	          &nbsp;
	          <br/>
	          <br/>
	          <jsp:include page="../../admin/_inc/search_fgs_jyb_bsc_ywy.jsp?fgs_dept_id=${af.map.fgs_dept_id}&jyb_dept_id=${af.map.jyb_dept_id}&bsc_dept_id=${af.map.bsc_dept_id}&ywy_user_id=${af.map.ywy_user_id}" />
             &nbsp;&nbsp;&nbsp;&nbsp;
           	 <input class="but1" type="button" name="btn_search" id="btn_search" style="cursor:pointer;" value="搜索" onclick="getUserList(this);"/>
            </html-el:form>
        </td>
      </tr>
    </table>
    <div id="divExcel" style="overflow-x:auto;">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
                <tr class="tabtt1" style="height:18px;">
                  <td align="center" nowrap="nowrap" width="40" style="height:18px;">序号</td>
                  <td width="10%"align="center" nowrap="nowrap">业务员姓名</td>
                  <!-- td align="center" nowrap="nowrap" width="12%">手机号</td> -->
				  <td align="center" nowrap="nowrap" width="140">更新时间</td>
				  <td align="center" nowrap="nowrap" width="130">经度</td>
				  <td align="center" nowrap="nowrap" width="130">纬度</td>
				 <!--  <th align="center" nowrap="nowrap" width="15%">GPS速度，单位米/秒</td>
				  <td align="center" nowrap="nowrap" width="18%">信号强度，单位db，取值0-100</td>--> 
				  <td align="center" nowrap="nowrap">地址</td>
                </tr>
                <tbody id="gps_user_list"></tbody>
        </table>
      <div id="user_pager_control" style="margin-top:5px;text-align:center;"> </div>
    </div> 
  </div>
</div>
<!-- 辅助POST提交 -->
<form name="form1" action="KonkaYwyGPSOR.do" style="display:none" method="post">  
	<input type="hidden" name="method" value="list"/>  
	<input type="hidden" name="mod_id" value="105054"/>
	<input type="hidden" name="ywy_name" id="ywy_name" value=""/>
	<input type="hidden" name="sub_date" id="sub_date" value=""/>
</form>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script> 
<script type="text/javascript">
var COLS_COUNT = 7;
var data_row = 0;
var map = new BMap.Map("container");               // 创建Map实例
var point = new BMap.Point(110.123588, 35.060338); // 创建点坐标
var ps = [];
map.centerAndZoom(point,5);                       // 初始化地图，设置中心点坐标和地图级别。
map.addControl(new BMap.NavigationControl());      // 添加控件
map.enableScrollWheelZoom();
map.enableContinuousZoom();

var persons = [];
var opts = {width : 0, height: 0};
function addMarker(_marker, rownum,user_name, name, duty, phone, dept_name, date, addr){
	var infoBox = personInfoBox(name, phone, dept_name, duty, date, addr);
	persons[user_name] = {marker:_marker, infoBox:infoBox, rownum:rownum};
	_marker.addEventListener("click", function(e){
		showInfoWindow(user_name);
	});
	map.addOverlay(_marker);
}

function personInfoBox(name, phone,dept, duty, date, addr){

	$("#ywy_name").val(name);
	$("#sub_date").val(date);
	var info = [];
	info[info.length] = '<span style="font-weight:bold;font-size:14px;">';
	if($.trim(phone).length > 0){
		info[info.length] = name + "（" + phone + "）";
	}else{
		info[info.length] = name;
	}
	info[info.length] = '</span><br /><b>分公司/经办：</b>';
	info[info.length] = dept;
	info[info.length] = '<br /><b>职　　位：</b>';
	info[info.length] = duty;
	info[info.length] = '<br /><b>时　　间：</b>';
	info[info.length] = '<a href=\'javascript:document.form1.submit();\'>' + date + '</a>';
	info[info.length] = '<br /><b>位　　置：</b>';
	info[info.length] = addr;
	return info;
}
var selectTR = null;

function showInfoWindow(user_name) {
	var person = persons[user_name];
	if(typeof person != "object") return;
	var info = persons[user_name].infoBox;
	var _marker = persons[user_name].marker;
	var infoWindow = new BMap.InfoWindow(info.join(""), opts);
	_marker.openInfoWindow(infoWindow, _marker.getPosition());
	//var row = persons[user_name].rownum;
    //if(selectTR != null){
	//     selectTR.style.backgroundColor = "";
    //}
	//var tr = document.getElementById("tr_"+row);
	//tr.style.backgroundColor = "#FFD0FF";
	//selectTR = tr;
}

var translateOptions = function (sr_point,point,mobile_no){
    cur_arr[mobile_no].point = point;
    // 异步获取位置
    var myGeo = new BMap.Geocoder();      	
    myGeo.getLocation(point, function(result){
            var addr = "--";
            if (result){
                 addr = result.address;
      	         var urlData = "gps_id=" + cur_arr[mobile_no].cur.gps_id+"&gps_type="+cur_arr[mobile_no].cur.gps_type+"&latitude="+cur_arr[mobile_no].point.lat+"&longitude="+cur_arr[mobile_no].point.lng+"&address="+encodeURI(addr);
                 ajaxUpdateUserGPSInfo(point,urlData,cur_arr[mobile_no].cur,cur_arr[mobile_no].rownum,addr);
            }else{
                 addr = "经纬度解析失败，无法获取业务员的位置！";
			     var td = document.getElementById("td_"+cur_arr[mobile_no].rownum + "_5");
			     td.innerHTML = addr;
           }
    });
};

var cur_arr = [];
function updateGPSMarkersAndGetAddress(cur,rownum){
       if(cur.gps_id == '--'){
          return;
       }
       cur_arr[cur.mobile_no] = {cur:cur,rownum:rownum,point:null};

       var bpoint = new BMap.Point(cur.longitude, cur.latitude);
       if(cur.back == '0'){
           addMarker(new BMap.Marker(bpoint),rownum,cur.ywy_name, cur.mobile_no, cur.phone, cur.dept_name, cur.duty, cur.update_time, cur.address);
           return;
       }
        // 手机GPS转成百度经纬度
        BMap.Convertor.translate(bpoint,0,cur.mobile_no,translateOptions);
}

function ajaxUpdateUserGPSInfo(bpoint,urlData,cur,rownum,addr){
	$.ajax({
		   type: "POST",
		   url: "KonkaYwyGPS.do",
		   data: "method=updateUserGPSInfo&" + urlData,
		   dataType: "json",
		   cache: false,
		   error: function(request, settings) {alert("数据加载请求失败！"); },
		   success: function(ret) {
			   if (ret.status == "1") {
				  if(cur.gps_type == "0"){
				     var td = document.getElementById("td_"+rownum+"_5");
				     td.innerHTML = addr;
				     addMarker(new BMap.Marker(bpoint), rownum, cur.ywy_name, cur.mobile_no, cur.phone, cur.dept_name, cur.duty, cur.update_time, addr);
				  }	
			  }
		  }
	});
}

function chooseTr(row){
    if(row < data_row){
	   var mobile_no = findMobileByRow(row);
	   if(mobile_no != "" && mobile_no != "--")
		  showInfoWindow(mobile_no);
	   else{
          alert("业务员没有GPS手机，无法定位！");
	   }
    }
}

var show_map_type = '${af.map.sm}';
if(show_map_type == ""){
	show_map_type = '${af.map.show_map_type}';
}
function getUserList(obj){

	  var fgs_dept_id = $("#fgs_dept_id").val();
	  if($.trim(fgs_dept_id).length == 0){
		 alert("请选择分公司！");
		 $("#fgs_dept_id").focus();
		 return false;
	  }
	  persons = [];
      if(map.getInfoWindow() != null){
	     map.closeInfoWindow();
      }
	  var ywy_name_like = $("#ywy_name_like").val();
      var update_time_st = $("#update_time_st").val();
      var update_time_en = $("#update_time_en").val();
      var fgs_dept_id = $("#fgs_dept_id").val();
      var jyb_dept_id = $("#jyb_dept_id").val();
      var bsc_dept_id= $("#bsc_dept_id").val();
      var ywy_user_id = $("#ywy_user_id").val();
      
	  if(obj){
         obj.setAttribute("disabled",true);
	  }
	  $.ajax({
		   type: "POST",
		   url: "KonkaYwyGPS.do?method=ajaxGetUserList",
		   data: {"ywy_name_like":ywy_name_like,"update_time_st":update_time_st,"update_time_en":update_time_en,"fgs_dept_id":fgs_dept_id,"jyb_dept_id":jyb_dept_id,"bsc_dept_id":bsc_dept_id,"ywy_user_id":ywy_user_id},
		   dataType: "json",
		   cache: false,
		   error: function(request, settings) {alert("数据加载请求失败！"); },
		   success: function(ret) {
			   if (ret) {
				   map.clearOverlays();
				  var userList = ret.list;
				  $("#gps_user_list").html("");
				  var html = "";
				  for(var i = 0; i < userList.length; i++){
						html = html + "<tr>";
						html = html + "<td align=\"center\" id='td_" + i + "_0'>" + (i+1) + "</td>";
						html = html + "<td id='td_" + i + "_1'>" + (userList[i].ywy_name) + "</td>";
						html = html + "<td id='td_" + i + "_2'>" + (userList[i].update_time) + "</td>";
						html = html + "<td id='td_" + i + "_3'>" + (userList[i].longitude) + "</td>";
						html = html + "<td id='td_" + i + "_4'>" + (userList[i].latitude) + "</td>";
						html = html + "<td id='td_" + i + "_5'>" + (userList[i].address) + "</td>";
						html = html + "</tr>";
						var bpoint = new BMap.Point(userList[i].longitude, userList[i].latitude);
						addMarker(new BMap.Marker(bpoint),i,userList[i].user_name,userList[i].ywy_name,userList[i].duty,userList[i].mobile_no, userList[i].full_name, userList[i].update_time, userList[i].address);		
					}
					if(userList.length == 1){
						showInfoWindow(userList[0].user_name);
					}
				  $("#gps_user_list").html(html);
				  
				  if(userList.length > 0){
					    for (var i = 0; i < userList.length; i++) {
							 var cur =userList[i];
							 updateGPSMarkersAndGetAddress(cur, i);			
					    }
					    if(ret.first_gps_person != "-1"){
					   	 	chooseTr(userList.first_gps_person);
					    }
			        }
			  }
		  }
	  });
	  if(obj){
		 obj.removeAttribute("disabled");
	  }
}

//getUserList(false);
//$(document).everyTime(30*1000, function(){getUserList(false);}); // 间隔60秒刷新
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>