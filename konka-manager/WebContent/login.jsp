<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Description" content="对终端零售数据、渠道分销数据进行采集、汇总,并借助数据挖掘工具提供决策报表,通过PC与移动端为分公司提供信息化工具,为客户提供进销存管理系统,并为业务、促销人员提供移动办公平台" />
<meta name="Copyright" content="康佳集团,渠道管理部" />
<meta name="Author" content="北京买卖提" /> 
<meta name="Authorsite" content="qdgl.konka.com" />
<title>康佳_渠道管理系统</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/loginv2/css/css.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/loginv2/css/global.css" />
<style type="text/css">
.smallslider {position:relative;padding:0;margin:0;overflow:hidden;}
.smallslider ul {list-style-type:none;padding:0;margin:0;position: absolute;width:auto;	height:auto;}
.smallslider li {margin:0;padding:0;}
.smallslider li a {margin:0;padding:0;}
.smallslider li a img {border:0;padding:0;margin:0;vertical-align:top;}
.smallslider h3 {position:absolute;font-weight:bold;font-size:12px;margin:0;padding:0;text-indent:2%;line-height:26px;z-index:102;width:98%;color:#CCC;}
.smallslider h3 a {padding:0;margin:0;text-indent:0;}
.smallslider h3 a:link, .smallslider h3 a:visited {text-decoration:none;color:#FFFFFF;}
.smallslider h3 a:hover {text-decoration:underline;color:#FF6600;}
.smallslider li.current-li {}
.smallslider-btns {position:absolute;z-index:103;}
.smallslider-btns span {background-color:#FFFFFF;border:1px solid #DCDCDC;color:#9F9F9F;	cursor:pointer;	float:left;	font-size:12px;	height:16px;line-height:16px;text-align:center;width:16px;}
.smallslider-btns span.current-btn {background-color:#C00100;border:1px solid #A00100;color:white;font-size:13px;	font-weight:bold;}
.smallslider-lay {position:absolute;background:black;height:26px;width:100%;z-index:101;}
</style>
</head>
<body class="bg_main" id="body">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" id="table">
  <tr>
    <td align="center" valign="middle"><table width="980" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="40" align="left"><span class="right_contcontleft"><img src="${ctx}/styles/loginv2/images/logo1.png" width="500" height="45" /></span></td>
        </tr>
        <tr>
          <td height="10" align="center" bgcolor="#FFFFFF"></td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF"><table width="960" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="692"><table width="682" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="222" height="270" rowspan="3">
                      <div id="imgplayer1" class="sub_r smallslider" style="width:222px;height:270px;">
					      <ul>
					        <c:forEach var="cur" items="${imgList}">
					          <li><a href="${cur.image_url}" target="_blank"><img src="${ctx}/${cur.image_path}" title="" alt="${cur.title}" width="222" height="270" /></a></li>
					        </c:forEach>
					        <c:if test="${empty imgList}">
					          <li><a href="#"><img src="${ctx}/styles/loginv2/images/8h.jpg" title="" alt="" width="222" height="270" style="border:0px;" /></a></li>
					          <li><a href="#"><img src="${ctx}/styles/loginv2/images/8h.jpg" title="" alt="" width="222" height="270" style="border:0px;" /></a></li>
					          <li><a href="#"><img src="${ctx}/styles/loginv2/images/8h.jpg" title="" alt="" width="222" height="270" style="border:0px;" /></a></li>
					        </c:if>
					      </ul>
					    </div></td>
                      <td width="7" rowspan="3">&nbsp;</td>
                      <td height="100" bgcolor="#4d00ca"><table width="222" height="100" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td style="text-align:center;"><div class="sub_1_l1">12:00</div>
                              <div class="sub_1_l2">2013年07月26日</div>
                              <div class="sub_1_l3">星期五</div></td>
                            <td width="76"><img src="${ctx}/styles/loginv2/images/shijian.png" width="76" height="74" /></td>
                          </tr>
                        </table></td>
                      <td>&nbsp;</td>
                      <td style="background-color:#b10002;">
                      	<div style="position:relative;z-index:99999px;"><iframe src="http://qdgl.konka.com/w2.html?t=12333ddd33" frameborder="0" width="100%" style="margin:-20px 0px 0px 0px; padding: 0px; height: 109px; filter:alpha(opacity=100);opacity:1;" allowTransparency="true"></iframe></div>
                      </td>
                    </tr>
                    <tr>
                      <td height="7" colspan="3"></td>
                    </tr>
                    <tr>
                      <td height="163" colspan="3" bgcolor="#7B68EE"><table width="453" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td id="news_td">
                            	<div class="newsBox chenghao" id="news_div">
                            	<ul class="news_list" id="news_list">
					               <c:forEach var="cur" items="${articleList}">
					            	  <li>
					            	 
					            	  	<a href="${ctx}/webservice/KonkaPeArticleInfoInterface.do?method=view&id=${cur.id}" title='${cur.title}（<fmt:formatDate value="${cur.pub_date}" pattern="yyyy-MM-dd" />）' style="color:#fff;" target="_blank">
					            			<span style="font-size:14px">${fnx:abbreviate(cur.title, 10 * 2 ,'...')}</span>
					            	   	</a>
					            	   
					            	<span style="font-size:14px;text-align:right;">【<fmt:formatDate value="${cur.pub_date}" pattern="MM-dd" />】</span>
					            	  </li>
					                </c:forEach>
					            </ul>
					            </div>	
                            </td>
                            <td width="170" style="border:1px solid rgb(0, 57, 241);"><img src="${ctx}/styles/loginv2/images/8he.png" width="160" height="120" /></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td height="7" colspan="5"></td>
                    </tr>
                    <tr>
                      <td><a href="http://qdgl.konka.com/files/konka/" target="_blank"><img src="${ctx}/styles/loginv2/images/and.jpg" width="222" height="100" /></a></td>
                      <td>&nbsp;</td>
                      <td><a href="http://qdgl.konka.com/files/konka/" target="_blank"><img src="${ctx}/styles/loginv2/images/iosw.jpg" width="222" height="100" /></a></td>
                      <td>&nbsp;</td>
                      <td><a href="http://vip.konka.com"><img src="${ctx}/styles/loginv2/images/khdl.jpg" width="222" height="100" /></a></td>
                    </tr>
                  </table></td>
                <td valign="top"><html-el:form action="/login" method="post" styleClass="form_class">
                    <html-el:hidden property="method" value="login" />
                    <html-el:hidden property="url" value="${url}" />
                    <table width="260" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td height="50" valign="top"><span class="gldl">管理端用户登录</span></td>
                      </tr>
                      <tr>
                        <td><table width="250" border="0" cellspacing="0" cellpadding="0" class="tdTab" id="login_table">
                            <tr>
                              <td width="80" align="center">用户名:</td>
                              <td colspan="2"><html-el:text property="user_name" styleId="user_name" value="${user_name}" maxlength="32" tabindex="1" size="40" styleClass="webinput" /></td>
                            </tr>
                            <tr>
                              <td width="80" align="center">密　码:</td>
                              <td colspan="2"><html-el:password property="password" value="${password}" styleId="password" styleClass="webinput2" size="30" maxlength="32" tabindex="2" /></td>
                            </tr>
                            <c:if test="${isEnabledCode eq '1'}">
                            <tr>
                              <td width="80" align="center" style="padding-top:5px">验证码:</td>
                              <td valign="middle"><input type="text" name="verificationCode" id="verificationCode" tabindex="3" class="webinput1" size="30" maxlength="4" style="color:#000;" /></td>
                              <td valign="top"><img src="${ctx}/images/VerificationCode.jpg" width="76" height="24" style="border:1px solid #A1BCA3;cursor:pointer;" alt="验证码，看不清楚请用鼠标点击此处！" onclick="$(this).hide().attr('src', this.src + '?' + new Date().getTime()).fadeIn();" /></td>
                            </tr>
                            </c:if>
                            <tr>
                              <td width="80" align="left" valign="bottom">&nbsp;</td>
                              <td align="left">
                              	<input type="checkbox" name="is_remember" value="1" id="is_remember" style="vertical-align:middle;padding:0;margin:0 3px 3px 0 " />
              					<font style="font-size:12px; color: #666;">记住密码</font></td>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td colspan="3" align="right" valign="bottom"><img src="${ctx}/styles/loginv2/images/denglu_but.jpg" width="244" height="35" id="btn_sub" style="cursor:pointer;" /></td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
                  </html-el:form></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="10" bgcolor="#FFFFFF"></td>
        </tr>
        <tr>
          <td height="50" align="center"><div class="foot">康佳集团 版权所有 KONKA 2014.All Rights Reserved   | 版本号 ：${VersionOfManage}<br>
    网站管理：多媒体渠道管理部 | 运营电话：0755-26608866-6303 | <a href="http://icp.aizhan.com/" target="_blank" style="color: white;"><script type="text/javascript" src="http://icp.aizhan.com/geticp/?host=qdgl.konka.com&style=1" charset="utf-8"></script></a><br>
   <a href="http://www.kuaidi100.com" target="_blank"><font color="white">快递查询</font></a> 
    </div>
    
    
    
    </td>
        </tr>
      </table></td>
  </tr>
</table>



<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.smallslider.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#is_remember").attr("checked",true);
	
	$("#user_name").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	$("#password").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	$("#verificationCode").keyup(function(){
		var job_id = $(this).val();
		$(this).val(SBC2DBC(job_id));
	});
	
	$("#user_name" ).attr("dataType", "Require").attr("msg", "请填写您的用户名！");
	<c:if test="${not isDebugMode}">
		$("#password"  ).attr("dataType", "Require").attr("msg", "请填写您的密码！");
		if ("${isEnabledCode}" == "1") {
			$("#verificationCode" ).attr("dataType" ,"Custom" ).attr("msg", "验证码必须是一个4位的数字").attr("regexp", "\\d{4}");
		}
	</c:if>
	
	// 点击登录
	$(document).delegate("#btn_sub", "click", function(){
		if(Validator.Validate($(".form_class")[0], 1)){
			$(".form_class").submit();
		}
	});
	
	// 记住密码回显
	if ("true" == "${not empty is_remember}") {
		$("#is_remember")[0].checked = true;
	}
	
	//设置时间
	var server_time = parseInt("${now}");
    var client_time = new Date().getTime();  
    var diff = server_time - client_time;
	timeChange(diff); 
	
	// 资讯滚动
	loadNewsPage();
	
	// 读取浏览器
	browserRedirect();

	// 浏览器改变大小重置
	$(window).resize(resizeBody);
	
	// 如果是IE处理高度问题，否则不能居中
	if($.browser.msie && ($.browser.version != "10.0")){
		$("#table").css({"height" : $(document).height() - 10 + "px"});
	}
	
	// 强制右边出现  START...
	var table_widht = 985;
	var body_width = $("#body").width();
	if(body_width < 985){
		$("#table").css({"position" : "absolute", "left" : body_width-table_widht + "px"});
	}
	// 强制右边出现 END.
	
	// 处理新闻的位置
	var offset = $("#news_td").offset();
	$("#news_div").offset({ top: offset.top, left: offset.left });
			
	// 回车提交表单
	function keyEnter(){ if(event.keyCode == 13) $("#btn_sub").click(); } 
	document.onkeydown = keyEnter; 
	
	$('#imgplayer1').smallslider({
        onImageStop:true,
        switchEffect:'ease',
        switchEase: 'easeOutQuart',
        switchPath: 'left',
        switchMode: 'hover',
        switchTime : 1000,
        time : 5000,
        showText:false,
        onImageStop : true,
        textSwitch:2,
        showButtons:false
    });
});

// 浏览器改变大小重置
function resizeBody() {
	// 如果是IE处理高度问题，否则不能居中
	if($.browser.msie && ($.browser.version != "10.0")){
		$("#table").css({"height" : $(document).height() - 10 + "px"});
	}
	
	// 判断改变右边距离
	var table_widht = 985;
	var body_width = $("#body").width();
	if(body_width < 985){
		$("#table").css({"position" : "absolute", "left" : body_width-table_widht + "px"});
	}
	
	// 处理新闻的位置
	var offset = $("#news_td").offset();
	$("#news_div").offset({ top: offset.top, left: offset.left });
}

// 时钟
function timeChange(diff) {  
    var myDate = new Date();
    myDate.setTime(myDate.getTime() + diff);
    var weeks = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六']; 
    var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
    var month = myDate.getMonth() + 1;       //获取当前月份(0-11,0代表1月)
    var day = myDate.getDate();        //获取当前日(1-31)
    var week = weeks[myDate.getDay()];  //获取当前星期X(0-6,0代表星期天)
    var hour = myDate.getHours();       //获取当前小时数(0-23)
    var min = myDate.getMinutes();     //获取当前分钟数(0-59)
    $(".sub_1_l1").html(hour + ":" + (min < 10 ? ("0" + min) : min));
    $(".sub_1_l2").html(year + "年" + month + "月" + day + "日");
    $(".sub_1_l3").html(week);  
    setTimeout("timeChange(" + diff + ");", 1000);
}

// 读浏览器
function browserRedirect() {  
    var sUserAgent = navigator.userAgent.toLowerCase();  
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";  
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";  
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";  
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";  
    var bIsAndroid = sUserAgent.match(/android/i) == "android";  
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";  
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (bIsAndroid){
        //window.location.href="${ctx}/mobile";
    } 
}

// 文字滚动
function loadNewsPage() {
	var isRoll = true;
	$("#news_list").mouseover(function() {
		isRoll = false
	}).mouseout(function() {
		isRoll = true
	});
	var $list = $("#news_list");
	var step = $list.find("li").css("line-height").replace("px", "");
	var line = 1;
	var count = Math.ceil(($list.find("li").length + 1 / 2));
	var fn = function() {
		$(this).addClass("currentLI").siblings("li").removeClass();
	};
	$list.append($list.html());
	$list.find("li:eq(0)").addClass("currentLI");
	$list.find("li").mouseover(fn);
	window.setInterval(function() {
		if (isRoll) {
			$list.animate( {
				top : '-=' + step
			}, 1000, function() {
				if (line >= count) {
					$list.css("top", 0);
					line = 1;
					$list.find("li:eq(0)").addClass("currentLI").siblings("li").removeClass();
				}
			});
			$("#news_list li:eq(" + (line-1) + ")").siblings("li").show();
			$("#news_list li:eq(" + (line-1) + ")").next("li").addClass("currentLI").siblings("li").removeClass();
			line++;
		}
	}, 3000)
}

//全角转换成半角
function SBC2DBC(str){
	var   i;  
	var   result= '';  
	for(i=0;i <str.length;i++)   {
		var  code=str.charCodeAt(i)
		//“65281”是“！”，“65373”是“｝”
		if(code >= 65281&&code < 65373){
			//     “65248”是转换码距
			result+=String.fromCharCode(str.charCodeAt(i)-65248);
		} else {
			result+=str.charAt(i);
		}
	}  
	return result;  
}
//]]></script>
<jsp:include page="/__ie6_check.jsp" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>