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
<script type="text/javascript" src="${ctx}/commons/scripts/convertor.js"></script>
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
        <td><strong class="fb">业务员姓名：</strong>
            <input type="text" name="ywy_name_like" id="ywy_name_like" style="width:200px;" maxlength="30"/>
             &nbsp;&nbsp;&nbsp;&nbsp;
            <input class="but1" type="button" name="btn_search" id="btn_search" value="搜索" onclick="getUserList(this);"/>
        </td>
      </tr>
    </table>
    <div id="divExcel" style="overflow-x:auto;">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          <tbody id="gps_result">
                <tr class="tabtt1" style="height:18px;">
                  <td align="center" nowrap="nowrap" width="40" style="height:18px;">序号</td>
                  <td nowrap="nowrap">业务员姓名</td>
                  <td align="center" nowrap="nowrap" width="12%">手机号</td>
				  <td align="center" nowrap="nowrap" width="140">更新时间</td>
				  <td align="center" nowrap="nowrap" width="130">经度</td>
				  <td align="center" nowrap="nowrap" width="130">纬度</td>
				 <!--  <th align="center" nowrap="nowrap" width="15%">GPS速度，单位米/秒</td>
				  <td align="center" nowrap="nowrap" width="18%">信号强度，单位db，取值0-100</td>--> 
				  <td align="center" nowrap="nowrap" width="25%">地址</td>
                </tr>
                <tr id="tr_0" onclick="chooseTr(0);" style="cursor:pointer;"><td height="20" align="center" id="td_0_0" ></td><td id="td_0_1"></td><td align="left" id="td_0_2"></td><td align="center" id="td_0_3"></td><td align="center" id="td_0_4"></td><td align="center" id="td_0_5"></td><td id="td_0_6"></td><!--<td id="td_0_7"></td><td id="td_0_8"></td>--></tr>
                <tr id="tr_1" onclick="chooseTr(1);" style="cursor:pointer;"><td height="20" align="center" id="td_1_0" ></td><td id="td_1_1"></td><td align="left" id="td_1_2"></td><td align="center" id="td_1_3"></td><td align="center" id="td_1_4"></td><td align="center" id="td_1_5"></td><td id="td_1_6"></td><!--<td id="td_1_7"></td><td id="td_1_8"></td>--></tr>
                <tr id="tr_2" onclick="chooseTr(2);" style="cursor:pointer;"><td height="20" align="center" id="td_2_0" ></td><td id="td_2_1"></td><td align="left" id="td_2_2"></td><td align="center" id="td_2_3"></td><td align="center" id="td_2_4"></td><td align="center" id="td_2_5"></td><td id="td_2_6"></td><!--<td id="td_2_7"></td><td id="td_2_8"></td>--></tr>
                <tr id="tr_3" onclick="chooseTr(3);" style="cursor:pointer;"><td height="20" align="center" id="td_3_0" ></td><td id="td_3_1"></td><td align="left" id="td_3_2"></td><td align="center" id="td_3_3"></td><td align="center" id="td_3_4"></td><td align="center" id="td_3_5"></td><td id="td_3_6"></td><!--<td id="td_3_7"></td><td id="td_3_8"></td>--></tr>
                <tr id="tr_4" onclick="chooseTr(4);" style="cursor:pointer;"><td height="20" align="center" id="td_4_0" ></td><td id="td_4_1"></td><td align="left" id="td_4_2"></td><td align="center" id="td_4_3"></td><td align="center" id="td_4_4"></td><td align="center" id="td_4_5"></td><td id="td_4_6"></td><!--<td id="td_4_7"></td><td id="td_4_8"></td>--></tr>
                <tr id="tr_5" onclick="chooseTr(5);" style="cursor:pointer;"><td height="20" align="center" id="td_5_0" ></td><td id="td_5_1"></td><td align="left" id="td_5_2"></td><td align="center" id="td_5_3"></td><td align="center" id="td_5_4"></td><td align="center" id="td_5_5"></td><td id="td_5_6"></td><!--<td id="td_5_7"></td><td id="td_5_8"></td>--></tr>
                <tr id="tr_6" onclick="chooseTr(6);" style="cursor:pointer;"><td height="20" align="center" id="td_6_0" ></td><td id="td_6_1"></td><td align="left" id="td_6_2"></td><td align="center" id="td_6_3"></td><td align="center" id="td_6_4"></td><td align="center" id="td_6_5"></td><td id="td_6_6"></td><!--<td id="td_6_7"></td><td id="td_6_8"></td>--></tr>
                <tr id="tr_7" onclick="chooseTr(7);" style="cursor:pointer;"><td height="20" align="center" id="td_7_0" ></td><td id="td_7_1"></td><td align="left" id="td_7_2"></td><td align="center" id="td_7_3"></td><td align="center" id="td_7_4"></td><td align="center" id="td_7_5"></td><td id="td_7_6"></td><!--<td id="td_7_7"></td><td id="td_7_8"></td>--></tr>
                <tr id="tr_8" onclick="chooseTr(8);" style="cursor:pointer;"><td height="20" align="center" id="td_8_0" ></td><td id="td_8_1"></td><td align="left" id="td_8_2"></td><td align="center" id="td_8_3"></td><td align="center" id="td_8_4"></td><td align="center" id="td_8_5"></td><td id="td_8_6"></td><!--<td id="td_8_7"></td><td id="td_8_8"></td>--></tr>
                <tr id="tr_9" onclick="chooseTr(9);" style="cursor:pointer;"><td height="20" align="center" id="td_9_0" ></td><td id="td_9_1"></td><td align="left" id="td_9_2"></td><td align="center" id="td_9_3"></td><td align="center" id="td_9_4"></td><td align="center" id="td_9_5"></td><td id="td_9_6"></td><!--<td id="td_9_7"></td><td id="td_9_8"></td>--></tr>
          </tbody>
        </table>
      <div id="user_pager_control" style="margin-top:5px;text-align:center;"> </div>
    </div> 
  </div>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&services=true"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.pager.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script> 
<script type="text/javascript">
var COLS_COUNT = 7;
var data_row = 0;
var map = new BMap.Map("container");               // 创建Map实例
var point = new BMap.Point(114.061686, 22.539857); // 创建点坐标
var ps = [];
map.centerAndZoom(point,12);                       // 初始化地图，设置中心点坐标和地图级别。
map.addControl(new BMap.NavigationControl());      // 添加控件
map.enableScrollWheelZoom();
map.enableContinuousZoom();

var persons = [];
var opts = {width : 0, height: 0};
function addMarker(_marker, rownum, name, mobile_no, phone, dept, duty, date, addr){
	var infoBox = personInfoBox(name, phone, dept, duty, date, addr);
	persons[mobile_no] = {marker:_marker, infoBox:infoBox, rownum:rownum};
	_marker.addEventListener("click", function(e){
		showInfoWindow(mobile_no);
	});
	map.addOverlay(_marker);
}

function personInfoBox(name, phone, dept, duty, date, addr){
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
	info[info.length] = date;
	info[info.length] = '<br /><b>位　　置：</b>';
	info[info.length] = addr;
	return info;
}
var selectTR = null;

function showInfoWindow(mobile_no) {
	var person = persons[mobile_no];
	if(typeof person != "object") return;
	var info = persons[mobile_no].infoBox;
	var _marker = persons[mobile_no].marker;
	var infoWindow = new BMap.InfoWindow(info.join(""), opts);
	_marker.openInfoWindow(infoWindow, _marker.getPosition());
	var row = persons[mobile_no].rownum;
    if(selectTR != null){
	     selectTR.style.backgroundColor = "";
    }
	var tr = document.getElementById("tr_"+row);
	tr.style.backgroundColor = "#FFD0FF";
	selectTR = tr;
}

function findMobileByRow(row){
  	var td = document.getElementById("td_"+row+"_2");
    if(typeof td =="object")
       return td.innerHTML;
    return "";	
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

var keys = ["","ywy_name","mobile_no","update_time","longitude","latitude","address"];

function updateRowTD(cur,cRow,rownum){
	 for (var i = 0; i < keys.length; i++) {
		 var td = document.getElementById("td_"+rownum+"_"+i);
		 if(i == 0)td.innerHTML = cRow + rownum;
		 else td.innerHTML = eval("cur."+keys[i]);
	 }
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
			     var td = document.getElementById("td_"+cur_arr[mobile_no].rownum+"_6");
			     td.innerHTML = addr;
           }
    });
};

var cur_arr = [];
function updateGPSMarkersAndGetAddress(cur, cRow, rownum){
       if(cur.gps_id == '--'){
          return;
       }
       cur_arr[cur.mobile_no] = {cur:cur,cRow:cRow,rownum:rownum,point:null};

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
		   url: "KonkaYwyGPSRT.do",
		   data: "method=updateUserGPSInfo&" + urlData,
		   dataType: "json",
		   cache: false,
		   error: function(request, settings) {alert("数据加载请求失败！"); },
		   success: function(ret) {
			   if (ret.status == "1") {
				  if(cur.gps_type == "0"){
				     var td = document.getElementById("td_"+rownum+"_6");
				     td.innerHTML = addr;
				     addMarker(new BMap.Marker(bpoint), rownum, cur.ywy_name, cur.mobile_no, cur.phone, cur.dept_name, cur.duty, cur.update_time, addr);
				  }	
			  }
		  }
	  });
}

function clearRowTD(rownum){
	 for (var i = 0; i < keys.length; i++) {
		 var td = document.getElementById("td_"+rownum+"_"+i);
		 td.innerHTML = "";
	 }
}

var show_map_type = '${af.map.sm}';
if(show_map_type == ""){
	show_map_type = '${af.map.show_map_type}';
}
function getUserList(obj){
	  persons = [];
      if(map.getInfoWindow() != null){
	     map.closeInfoWindow();
      }
	  var option ={};
	  option.sm = show_map_type;
	  var ywy_name_like = $("#ywy_name_like").val();
      if(ywy_name_like != ""){
    	  option.ywy_name_like = encodeURI(ywy_name_like);
      }
	  if(obj){
         obj.setAttribute("disabled",true);
	  }
	  var requestPageSize = $("#pageSize").val();
	  requestPageSize = /^\d+$/g.test(requestPageSize) ? requestPageSize : 10;
	  $("#user_pager_control").pager("${ctx}/manager/ywygps/KonkaYwyGPSRT.do", 
					$.extend({}, {method: 'ajaxGetUserList'}, option),
					$.extend({}, {showPagesCount : 0, pageSize : requestPageSize, use_defined_flg :true,
					use_defined_type : 3}), function(ret,cRow) {
		            var count = 0;
				    if(ret){
					    map.clearOverlays();
					    if(selectTR != null){
					 	     selectTR.style.backgroundColor = "";
					 		 selectTR = null;
					    }
				    	cur_arr = [];
					    count = ret.list.length;
					    data_row = count;
					    for (var i = 0; i < ret.list.length; i++) {
							 var cur =ret.list[i];
							 updateRowTD(cur,cRow,i);			
					    }
					}
				    for (var i = count; i < 10; i++) {
				    	clearRowTD(i);
				    }
			        if(count > 0){
					    for (var i = 0; i < ret.list.length; i++) {
							 var cur =ret.list[i];
							 updateGPSMarkersAndGetAddress(cur, cRow, i);			
					    }
					    if(ret.first_gps_person != "-1"){
					    	 selectTR = document.getElementById("tr_"+ret.first_gps_person);
					    	 chooseTr(ret.first_gps_person);
					    }
			        }
	  });
	  if(obj){
		 obj.removeAttribute("disabled");
	  }
}

getUserList(false);
$(document).everyTime(30*1000, function(){getUserList(false);}); // 间隔60秒刷新

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>