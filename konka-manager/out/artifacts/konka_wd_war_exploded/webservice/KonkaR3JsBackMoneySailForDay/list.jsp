<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/wmj/css/db.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/wmj/css/global.css" rel="stylesheet" type="text/css" />
<style>
</style>
</head>
<body>
<form name=form1>
  <input type="hidden" id="user_id" name="user_id" value="${af.map.user_id}" />
  <input type="hidden" id="userpass" name="userpass" value="${af.map.userpass}" />
  <table width="86%" border="0" align="center" cellpadding="0" cellspacing="0">
  	 <tr style="height:35px;">
          <td style="height:35px;" nowrap="nowrap">
          	<select name='YYYY' id="y1" onchange="YYYYMM(this.value)">  
				<option value="">年</option>  
			</select>  
			<select name='MM' id="m1" onchange="MMDD(this.value)">  
				<option value="">月</option>  
			</select>  
			<select name='DD' id="d1">  
				<option value="">日</option>  
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="button" value="查询" id="btn_submit" />
          </td>
     </tr>
  </table>
  <div class="mbox">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>
          <div id="li">
            <div class="mbox3" id="li_1" >上日</div>
            <div class="mbox3" id="li_2" >昨日</div>
            <div class="mbox3" id="li_3" >今日</div>
            <div class="mbox3" id="li_4" >下日</div>
          </div>
          </td>
      </tr>
    </table>
  </div>
</form>
<table width="90%" border="0" align="center" cellpadding="0" align="center" cellspacing="0" bgcolor="#FFFFFF">
	 <tr>
      	<td align="right"><span style="font-size: 12px;color: red;">单位：万元</span></td>
      </tr>
<tr>
  <td><table width="86%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr style="height: 210px;">
        <td width="42%" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="20"><div align="center"> <span class="dtextsmall1">零售额<br />
                  <fmt:formatNumber value="${sail.map.sale_all_price}" pattern="###,##0.00" />
                   </span> </div></td>
            </tr>
            <tr>
              <td height="${sail_length eq 0 ? 0 : sail_length}" bgcolor="#ff6600">&nbsp;</td>
            </tr>
          </table></td>
        <td>&nbsp;</td>
        <td width="42%" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="20"><div align="center"> <span class="dtextsmall1">结算额<br />
                  <fmt:formatNumber value="${js.map.js_money}" pattern="###,##0.00" />
                  </span> </div></td>
            </tr>
            <tr>
              <td height="${js_length eq 0 ? 0 : js_length}" bgcolor="#0099ff">&nbsp;</td>
            </tr>
          </table></td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
</tr>
</table>
<table width="75%" border="0" align="center" cellpadding="0" cellspacing="0" class="dtextsmall">
  <tr>
    <td>零售额 <span style="color: blue;">
      <fmt:formatNumber value="${sail.map.sale_all_price}" pattern="###,##0.00" />
      万元</span>,同比 <span  <c:if test="${sail.map.sail_price_tb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_price_tb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_price_tb}" pattern="###,##0.00" />
      %</span>,环比 <span <c:if test="${sail.map.sail_price_hb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_price_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_price_hb}" pattern="###,##0.00" />
      %</span>. </td>
  </tr>
  <tr>
    <td>零售量 <span style="color: blue">
      <fmt:formatNumber value="${sail.map.sale_num}" pattern="###,##0" />
      台</span>，同比 <span <c:if test="${sail.map.sail_num_tb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_num_tb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_num_tb}" pattern="###,##0.00" />
      %</span>,
      环比 <span <c:if test="${sail.map.sail_num_hb ge 0}">style="color: green;"</c:if> <c:if test="${sail.map.sail_num_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${sail.map.sail_num_hb}" pattern="###,##0.00" />
      %</span>,平均单价 <span style="color: blue">
      <fmt:formatNumber value="${sail.map.sail_pj_price}" pattern="###,##0.00" />
      元/台</span>。<br /></td>
  </tr>
  <tr>
    <td>结算额 <span style="color: blue;">
      <fmt:formatNumber value="${js.map.js_money}" pattern="###,##0.00" />
      万元</span>,同比 <span <c:if test="${js.map.js_money_zb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_money_zb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_money_zb}" pattern="###,##0.00" />
      %</span>,环比 <span <c:if test="${js.map.js_money_hb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_money_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_money_hb}" pattern="###,##0.00" />
      %</span></td>
  </tr>
  <tr>
    <td> 结算量 <span style="color: blue">
      <fmt:formatNumber value="${js.map.js_num}" pattern="###,##0" />
      台</span>，
      同比 <span <c:if test="${js.map.js_num_tb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_num_tb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_num_tb}" pattern="###,##0.00" />
      %</span>,环比 <span <c:if test="${js.map.js_num_hb ge 0}">style="color: green;"</c:if> <c:if test="${js.map.js_num_hb lt 0}">style="color: red;"</c:if>>
      <fmt:formatNumber value="${js.map.js_num_hb}" pattern="###,##0.00" />
      %</span>,平均单价 <span style="color: blue">
      <fmt:formatNumber value="${js.map.js_pj_price}" pattern="###,##0.00" />
      元/台</span>。<br /></td>
  </tr>
</table>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[ 	
 $(document).ready(function(){

	 $("#y1").attr("datatype", "Require").attr("msg", "请选择年份");
	 $("#m1").attr("datatype", "Require").attr("msg", "请选择月份");
	 $("#d1").attr("datatype", "Require").attr("msg", "请选择日");

	 $("#btn_submit").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				this.form.submit();
			}
		});

	 
	var type = '${af.map.type}';
	$("#li_"+type).addClass("mbox4");

	
	//上一日
	$("#li_1").click(function(){
		var dd = new Date();
		var YYYY1 = $("#y1").val();
		var MM1 = $("#m1").val();
		var DD1 = $("#d1").val();
		if(""==YYYY1||""==MM1||""==DD1){
			alert("日期不能为空");
			return;
		}
		var user_id = $("#user_id").val();
		var userpass = $("#userpass").val();
		location.href = "${ctx}/webservice/KonkaR3JsBackMoneySailForDay.do?user_id=" + user_id + "&userpass=" + userpass + "&YYYY1=" + YYYY1 + "&MM1=" + MM1 +"&DD1="+DD1+"&next_day=1"+"&type=1";
	});
	
	
		
	//昨日
	$("#li_2").click(function(){
		var dd = new Date();
		dd.setDate(dd.getDate()-1);
		var YYYY = dd.getFullYear();
		var MM = dd.getMonth()+1;
		var DD = dd.getDate();
		var user_id = $("#user_id").val();
		var userpass = $("#userpass").val();
		location.href = "${ctx}/webservice/KonkaR3JsBackMoneySailForDay.do?user_id=" + user_id + "&userpass=" + userpass + "&YYYY=" + YYYY + "&MM=" + MM +"&DD="+DD+"&type=2";
	});
	
	//今日
	$("#li_3").click(function(){
		var dd = new Date();
		dd.setDate(dd.getDate());
		var YYYY = dd.getFullYear();
		var MM = dd.getMonth()+1;
		var DD = dd.getDate();
		var user_id = $("#user_id").val();
		var userpass = $("#userpass").val();
		location.href = "${ctx}/webservice/KonkaR3JsBackMoneySailForDay.do?user_id=" + user_id + "&userpass=" + userpass + "&YYYY=" + YYYY + "&MM=" + MM +"&DD="+DD+"&type=3";
	});

	//下一日
	$("#li_4").click(function(){
		var dd = new Date();
		var YYYY1 = $("#y1").val();
		var MM1 = $("#m1").val();
		var DD1 = $("#d1").val();
		if(""==YYYY1||""==MM1||""==DD1){
			alert("日期不能为空");
			return;
		}
		var user_id = $("#user_id").val();
		var userpass = $("#userpass").val();
		location.href = "${ctx}/webservice/KonkaR3JsBackMoneySailForDay.do?user_id=" + user_id + "&userpass=" + userpass + "&YYYY1=" + YYYY1 + "&MM1=" + MM1 +"&DD1="+DD1+"&next_day=0"+"&type=4";
	});
     	
 });

 function showMonth(i){
	if( i<=2){
		for(var k = 1;k <= 12;k++){
			if(k <= 3){
				$("#li_"+k).css("display","block");
			}else{
				$("#li_"+k).css("display","none");
			}
		}
	} else if (i <= 12 && i > 10){
		for(var k = 1;k <= 12;k++){
			if( k >= 10 && k<=12){
				$("#li_"+ k).css("display","block");
			} else {
				$("#li_"+(k)).css("display","none");
			}
		}
	} else {
		for(var k = 1;k <= 12;k++){
			if(k <=(12-(12-1-i)) && k >= (i-1)){
				$("#li_"+ k).css("display","block");
			} else {
				$("#li_"+ k).css("display","none");
			}
	}
  }
	  
}                                   
//]]></script>
<script type="text/javascript">//<![CDATA[ 
window.onload = function(){  
	strYYYY = document.form1.YYYY.outerHTML;  
	strMM = document.form1.MM.outerHTML;  
	strDD = document.form1.DD.outerHTML;  
	MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];  
	  
	//先给年下拉框赋内容  
	var y = new Date().getFullYear();  
	var str = strYYYY.substring(0, strYYYY.length - 9);  
	for (var i = (y-2); i < (y+5); i++) //以今年为准，前3年，后5年  
	{  
	str += "<option value='" + i + "'> " + i + "</option>\r\n";  
	}  
	document.form1.YYYY.outerHTML = str +"</select>";  
	  
	//赋月份的下拉框  
	var str = strMM.substring(0, strMM.length - 9);  
	for (var i = 1; i < 13; i++)  
	{  
	str += "<option value='" + i + "'> " + i + "</option>\r\n";  
	}  
	document.form1.MM.outerHTML = str +"</select>";  
	  
	document.form1.YYYY.value = y;  
	document.form1.MM.value = new Date().getMonth() + 1;  
	var n = MonHead[new Date().getMonth()];  
	if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;  
	writeDay(n); //赋日期下拉框  
	document.form1.DD.value = new Date().getDate();  

	 var a = "${af.map.year}";
	 var b = "${af.map.month}";
	 var c = "${af.map.day}";
	 if(null!=a&&""!=a){
		$("#y1").val(a);
		$("#m1").val(b);
		$("#d1").val(c);
	 }

	} ; 


	
	function YYYYMM(str) //年发生变化时日期发生变化(主要是判断闰平年)  
	{  
	var MMvalue = document.form1.MM.options[document.form1.MM.selectedIndex].value;  
	if (MMvalue == ""){
		document.form1.DD.outerHTML = strDD; 
		return;
	}  
	var n = MonHead[MMvalue - 1];  
	if (MMvalue ==2 && IsPinYear(str)) n++;  
	writeDay(n) ; 
	}  
	function MMDD(str) //月发生变化时日期联动  
	{  
	var YYYYvalue = document.form1.YYYY.options[document.form1.YYYY.selectedIndex].value;  
	if (str == ""){
		document.form1.DD.outerHTML = strDD; return;
	}  
	var n = MonHead[str - 1];  
	if (str ==2 && IsPinYear(YYYYvalue)) n++;  
	writeDay(n) ; 
	}  
	function writeDay(n) //据条件写日期的下拉框  
	{  
	var s = strDD.substring(0, strDD.length - 9);  
	for (var i=1; i<(n+1); i++)  
	s += "<option value='" + i + "'> " + i + "</option>\r\n";  
	document.form1.DD.outerHTML = s +"</select>";  
	}  
	function IsPinYear(year)//判断是否闰平年  
	{ 
		return(0 == year%4 && (year%100 !=0 || year%400 == 0));

	} 
//]]></script>
</body>
</html>	