<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<c:if test="${false}">
<!-- 消息提醒窗Begin -->
<style type="text/css">
#winpop { width:276px; height:0px; position:absolute; right:0; bottom:0;  margin:0; padding:1px; overflow:hidden; display:none;}
.ms-lt {
	width: 13px;
	height: 33px;
	background-image: url('${ctx}/styles/message/images/dialog_lt.png') !important;
	background-image: none;filter: progid : DXImageTransform.Microsoft.AlphaImageLoader(src = '${ctx}/styles/message/images/dialog_lt.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_lt.png')
}

.ms-ct {
	height: 33px;
	background-image: url('${ctx}/styles/message/images/dialog_ct.png') !important;
	background-image: none; filter: progid : DXImageTransform.Microsoft.AlphaImageLoader(src = '${ctx}/styles/message/images/dialog_ct.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_ct.png')
}

.ms-title {
	float: left;
	font-weight: bold;
	color: #FFFFFF;
	padding: 9px 0 0 4px;
}

.ms-close {
	position: relative;
	cursor: pointer;
	float: right;
	margin: 5px 0 0;
	_margin: 4px 0 0;
	height: 17px;
	width: 28px;
	background-image: url('${ctx}/styles/message/images/dialog_closebtn.gif')
}

.ms-rt {
	background-image: url('${ctx}/styles/message/images/dialog_rt.png') !important;
	background-image: none; filter: progid : DXImageTransform.Microsoft.AlphaImageLoader (src = '${ctx}/styles/message/images/dialog_rt.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_rt.png')
}

.ms-mlm {
	background-image: url('${ctx}/styles/message/images/dialog_mlm.png') !important;
	background-image: none; filter: progid : DXImageTransform.Microsoft.AlphaImageLoader (src = '${ctx}/styles/message/images/dialog_mlm.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_mlm.png')
}

.ms-mrm {
	background-image: url('${ctx}/styles/message/images/dialog_mrm.png') !important;
	background-image: none; filter: progid : DXImageTransform.Microsoft.AlphaImageLoader (src = '${ctx}/styles/message/images/dialog_mrm.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_mrm.png')
}

.ms-lb {
	background-image: url('${ctx}/styles/message/images/dialog_lb.png') !important;
	background-image: none; filter: progid : DXImageTransform.Microsoft.AlphaImageLoader (src = '${ctx}/styles/message/images/dialog_lb.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_lb.png')
}

.ms-cb {
	background-image: url('${ctx}/styles/message/images/dialog_cb.png') !important;
	background-image: none; filter: progid : DXImageTransform.Microsoft.AlphaImageLoader (src = '${ctx}/styles/message/images/dialog_cb.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_cb.png')
}

.ms-rb {
	background-image: url('${ctx}/styles/message/images/dialog_rb.png') !important;
	background-image: none; filter: progid : DXImageTransform.Microsoft.AlphaImageLoader (src = '${ctx}/styles/message/images/dialog_rb.png', sizingMethod = 'crop' );
	_background-image: url('${ctx}/styles/message/images/dialog_rb.png')
}
</style>
<link href="${ctx}/styles/message-bar/bar.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/message-scroll/css/scroll.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script>
<script type="text/javascript" src="${ctx}/styles/message-scroll/js/message.scroll.js"></script>
<script type="text/javascript">//<![CDATA[
$('body').everyTime('10s','C',function(){
    getMessages();
    //document.getElementById('winpop').style.height = '0px';
	//tips_pop();
},0,true);

function getMessages(){
	$.ajax({
    	type: "POST" , 
        url: "${ctx}/manager/zmd/KonkaXxMessage.do" , 
        data: "method=getMessages&user_id=${sessionScope.userInfo.id}&n=" + Math.random(), 
        dataType: "json" , 
        timeout: 3000,//超时时间3秒
        async: true, 
        error: function (request, settings) {}, 
        success: function (result) {
			$("#notRead").text(result.notReadCount);//未读消息条数
			if (result.notReadCount != "0") {
				$("#notReadDiv").empty();
				for ( var k = 0; k < result.notReadList.length; k++) {
					$("#notReadDiv").append('<li><a href="#" title="' + result.notReadList[k].msg_title + '" onclick="openMessageBox(' + result.notReadList[k].id + ')">' + (k + 1) + '.' + result.notReadList[k].msg_title + '</a></li>');
					//if (result.notReadList.length - 1 == k) {
					//	$("#notReadDiv").append('<li><a href="#">．．．．．．</a></li>');
					//}
				}
			}
            if (result.num != "0") {
            	document.getElementById("winpop").style.height = "0px";
            	//$("#contentDiv").empty().append('您好！您有未查看的消息' + result.isNotReadCount + '条！');
            	$("#newCount").text(result.list.length.toString());
            	$("#testimonial_slideshow").empty();
				for ( var i = 0; i < result.list.length; i++) {
					var msg_type = "";
					if ("0" == result.list[i].msg_type) {
						msg_type = "系统消息";
					} else if ("1" == result.list[i].msg_type) {
						msg_type = "通知公告";
					}
					$("#testimonial_slideshow").append('<div class="testiomonial_item" style="text-align:left;">' +
															'<h5 style="font-size:14px;">' + parseInt((i + 1), 10) + '、' + msg_type + '</h5>' +
															'<p class="p"><a href="javascript:void(0);" onclick="openMessageBox(' + result.list[i].id + ')">' + result.list[i].msg_title + '</a></p>' +
															'<p>来自：<a href="#">' + result.list[i].sender_user + '</a></p>' +
														'</div>');
				}
            	tips_pop();
			}
        }
	});
}

function tips_pop() {
	var MsgPop = document.getElementById("winpop");
	var popH = parseInt(MsgPop.style.height);//将对象的高度转化为数字
	if (popH == 0) {
		MsgPop.style.display = "block";//显示隐藏的窗口
		show = setInterval("changeH('up')", 2);
	} else {
		hide = setInterval("changeH('down')", 2);
	}

	if ($('#testimonial_slideshow').length > 0) {
		$('#testimonial_slideshow').cycle({ 
			fx: 'scrollHorz',
			speed: 600,
			randomizeEffects: true, 
			timeout: 6000, 
			cleartype:  true,
            cleartypeNoBg:  true,
            next:'#slideNext', 
			prev:'#slidePrev'
		});
	}
}

function changeH(str) {
	var MsgPop = document.getElementById("winpop");
	var popH = parseInt(MsgPop.style.height);
	if (str == "up") {
		if (popH <= 185) {
			MsgPop.style.height = (popH + 2).toString() + "px";
		} else {
			clearInterval(show);
		}
	}
	if (str == "down") {
		if (popH >= 2) {
			MsgPop.style.height = (popH - 2).toString() + "px";
		} else {
			clearInterval(hide);
			MsgPop.style.display = "none"; //隐藏DIV
		}
	}
}

window.onload = function() {//加载
	getMessages();
	//document.getElementById('winpop').style.height = '0px';
	//setTimeout("tips_pop()", 800);//3秒后调用tips_pop()这个函数
}
//]]></script>

<div id="winpop" style="position:fixed;">
	<table border="0" cellpadding="0" cellspacing="0" width="276px;">
		<tr style="cursor:move" id="">
			<td width="13" height="33" class="ms-lt">
				<div style="width:13px;"></div>
			</td>
			<td height="33" class="ms-ct">
				<div class="ms-title">
					<img src="${ctx}/styles/message/images/icon_dialog.gif" align="middle">&nbsp;您有新短消息<span style="font-weight:bold;" id="newCount"></span>条
				</div>
				<div class="ms-close" onmouseover="this.style.backgroundImage='url(\'${ctx}/styles/message/images/dialog_closebtn_over.gif\')'" onmouseout="this.style.backgroundImage='url(\'${ctx}/styles/message/images/dialog_closebtn.gif\')'" onclick="tips_pop()"></div>
			</td>
			<td width="13" height="33" class="ms-rt">
				<div style="width:13px;"></div>
			</td>
		</tr>
		<tr>
			<td width="13" class="ms-mlm"></td>
	    	<td align="center" valign="top"><a href="#;" id=""></a>
	    		<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
	    			<!-- 弹出窗口示例 说明Begin -->
	    			<tr id="" style="display:none;">
	          			<td height="50" valign="top">
	          				<table id="" width="100%" border="0" cellspacing="0" cellpadding="8" style="background:#EAECE9 url('${ctx}/styles/message/images/dialog_bg.jpg') no-repeat right top;">
	                			<tr>
	                				<td width="25" height="50" align="right">
	                					<img id="" src="${ctx}/styles/message/images/window.gif" width="32" height="32">
	                				</td>
	                				<td align="left" style="line-height:16px;">
	                					<h5 class="fb" id="">&nbsp;</h5>
	                					<div id="">&nbsp;</div>
	                				</td>
	              				</tr>
	              			</table>
	              		</td>
	              	</tr>
	              	<!-- 弹出窗口示例 说明End -->
	              	
	        		<tr>
	        			<td align="center" valign="top">
	        				<div style="position:relative;width:250px;height:100px">
<!--	         					<div id="" style="position:absolute; height:100%; width:100%;display:none;">&nbsp;</div>-->
	         					<div id="contentDiv" style="">
	         						<div style="float:left;padding-left:5px;"><img src="${ctx}/styles/message/images/4.png" /></div>
	         						<!-- 消息提醒Begin -->
									<div class="left_column" style="float:right;width:170px;padding-right:5px;">
										<div class="item">
									<!-- <H3><a href="#">牛图库--www.niutuku.com</a></H3>-->
											<div id="testimonial_slideshow">
												<div class="testiomonial_item">
													<h5 style="font-size:14px;">jQuery实现背景切换动画设计</h5>
													<p class="p"><a href="#">公司为中大眼科制作的一个首页</a></p>
													<p>BY：<a href="#">林炜贤</a></p>
												</div>
												<div class="testiomonial_item">
													<h5 style="font-size:14px;">苹果mac栏目动画设计制作</h5>
													<p class="p"><a href="#">公司为中大眼科制作的一个首页</a></p>
													<p>BY：<a href="#">林炜贤</a></p>
												</div>
												<div class="testiomonial_item">
													<h5 style="font-size:14px;">网易汽车树形菜单设计制作</h5>
													<p class="p"><a href="#">公司为中大眼科制作的一个首页</a></p>
													<p>BY：<a href="#">林炜贤</a></p>
												</div>
											</div>
										</div>
									</div>
									<!-- 消息提醒End -->
								</div>
	         				</div>
	         			</td>
	         		</tr>
	         		<tr>
	         			<td height="36">
	            			<div id="" style="height:21px;text-align:left;border-top:#dadee5 1px solid; padding:8px 20px; background-color:#f6f6f6;">
<!--	           					<input id="" type="button" onclick="openMessageBox()" value="查看历史消息">-->
								<div style="float:left;"><a href="#" style="line-height:21px;" onclick="openMessageBox()">查看历史消息</a></div>
<!--	           					<input id="" type="button" onclick="tips_pop()" value="关闭">-->
		            			<div class="text_right" style="float:right;">
									<a href="#"><img id="slidePrev" height="20" alt="back" src="${ctx}/styles/message-scroll/images/testimonial-back-btn.gif" /></a>
									<a href="#"><img id="slideNext" height="20" alt="next" src="${ctx}/styles/message-scroll/images/testimonial-next-btn.gif" /></a>
								</div>
	            			</div>
	            		</td>
	            	</tr>
	      		</table>
	      	</td>
	    	<td width="13" class="ms-mrm"></td>
	    </tr>
	  	<tr>
	  		<td width="13" height="13" class="ms-lb"></td>
	    	<td class="ms-cb"></td>
	    	<td width="13" height="13" class="ms-rb"></td>
	  	</tr>
	 </table>
</div>
<!-- 消息提醒窗End -->

<!-- 消息悬停栏Begin -->
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$.fn.adjustPanel = function(){ 
		$(this).find("ul, .subpanel").css({ 'height' : 'auto'}); //Reset subpanel and ul height
		
		var windowHeight = $(window).height(); //Get the height of the browser viewport
		var panelsub = $(this).find(".subpanel").height(); //Get the height of subpanel	
		var panelAdjust = windowHeight - 100; //Viewport height - 100px (Sets max height of subpanel)
		var ulAdjust =  panelAdjust - 25; //Calculate ul size after adjusting sub-panel (27px is the height of the base panel)
		
		if ( panelsub >= panelAdjust ) {	 //If subpanel is taller than max height...
			$(this).find(".subpanel").css({ 'height' : panelAdjust }); //Adjust subpanel to max height
			$(this).find("ul").css({ 'height' : ulAdjust}); //Adjust subpanel ul to new size
		}
		else if ( panelsub < panelAdjust ) { //If subpanel is smaller than max height...
			$(this).find("ul").css({ 'height' : 'auto'}); //Set subpanel ul to auto (default size)
		}
	};
	
	//Execute function on load
	$("#chatpanel").adjustPanel(); //Run the adjustPanel function on #chatpanel
	$("#alertpanel").adjustPanel(); //Run the adjustPanel function on #alertpanel
	
	//Each time the viewport is adjusted/resized, execute the function
	$(window).resize(function () { 
		$("#chatpanel").adjustPanel();
		$("#alertpanel").adjustPanel();
	});
	
	//Click event on Chat Panel + Alert Panel	
	$("#chatpanel a:first, #alertpanel a:first").click(function() { //If clicked on the first link of #chatpanel and #alertpanel...
		if($(this).next(".subpanel").is(':visible')){ //If subpanel is already active...
			$(this).next(".subpanel").hide(); //Hide active subpanel
			$("#footpanel li a").removeClass('active'); //Remove active class on the subpanel trigger
		}
		else { //if subpanel is not active...
			$(".subpanel").hide(); //Hide all subpanels
			$(this).next(".subpanel").toggle(); //Toggle the subpanel to make active
			$("#footpanel li a").removeClass('active'); //Remove active class on all subpanel trigger
			$(this).toggleClass('active'); //Toggle the active class on the subpanel trigger
		}
		return false; //Prevent browser jump to link anchor
	});
	
	//Click event outside of subpanel
	$(document).click(function() { //Click anywhere and...
		$(".subpanel").hide(); //hide subpanel
		$("#footpanel li a").removeClass('active'); //remove active class on subpanel trigger
	});
	$('.subpanel ul').click(function(e) { 
		e.stopPropagation(); //Prevents the subpanel ul from closing on click
	});
	
	//Delete icons on Alert Panel
	$("#alertpanel li").hover(function() {
		$(this).find("a.delete").css({'visibility': 'visible'}); //Show delete icon on hover
	},function() {
		$(this).find("a.delete").css({'visibility': 'hidden'}); //Hide delete icon on hover out
	});

});
//]]></script>
<div id="footpanel">
	<ul id="mainpanel">    	
        <li></li>
<!--        <li><a href="#" onclick="location.href='KonkaXxCustomerInfo.do?method=list&mod_id=807001'" class="profile"><small>客户管理</small></a></li>-->
<!--        <li><a href="#" onclick="location.href='KonkaXxPassWordUpdate.do?method=list&mod_id=810002'" class="editprofile"><small>修改密码</small></a></li>-->
<!--        <li><a href="#" onclick="location.href='KonkaXxUserInfoUpdate.do?method=view&mod_id=810001'" class="contacts"><small>修改资料</small></a></li>-->
        <li><a href="#" onclick="openMessageBox()" class="messages"><small>消息盒子</small></a></li>
<!--        <li><a href="#" class="playlist"><small>Play List</small></a></li>-->
<!--        <li><a href="#" class="videos"><small>Videos</small></a></li>-->
<!--        <li id="alertpanel">-->
<!--        	<a href="#" class="alerts" style="border-left: 1px solid #bbb;border-right: 1px solid #bbb;"> Alerts</a>-->
<!--            <div class="subpanel">-->
<!--            <h3><span> &ndash; </span>未读消息</h3>-->
<!--            <ul>-->
<!--            	<li class="view"><a href="#" onclick="openMessageBox()">查看全部</a></li>-->
<!--            	<li><a href="#" class="delete">X</a><p><a href="#">Antehabeo</a> abico quod duis odio tation luctus eu ad <a href="#">lobortis facilisis</a>.</p></li>-->
<!--                <li><a href="#" class="delete">X</a><p><a href="#">Et voco </a> Duis vel quis at metuo obruo, turpis quadrum nostrud <a href="#">lobortis facilisis</a>.</p></li>-->
<!--                <li><a href="#" class="delete">X</a><p><a href="#">Tego</a> nulla eum probo metuo nullus indoles os consequat commoveo os<a href="#">lobortis facilisis</a>.</p></li>-->
<!--            </ul>-->
<!--            </div>-->
<!--        </li>-->
        <li id="chatpanel">
        	<a href="#" class="chat" style="width:80px;">未读消息(<span id="notRead"></span>) </a>
            <div class="subpanel">
	            <h3><span> &ndash; </span>未读消息</h3>
	            <ul>
	<!--            	<li><a href="#"></a></li>-->
	<!--                <li><span>Other Friends</span></li>-->
	<!--                <li><a href="http://www.16sucai.com/daima/"><img src="images/chat-thumb.gif" alt="" /> Your Friend</a></li>-->
					<li>
						<div id="notReadDiv" style="padding-left:10px;"></div>
					</li>
	            	<li><span style="float:right;width:60px;"><a href="#" onclick="openMessageBox()">查看全部</a></span></li>
	            </ul>
            </div>
        </li>       
	</ul>
</div>
<!-- 消息悬停栏End -->


<!-- 消息盒子Begin -->
<link href="${ctx}/styles/layer/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/styles/layer/js/jquery-layer.js"></script>
<script type="text/javascript">
//openMessageBox();
function openMessageBox(msg_id) {
	//alert(msg_id); 点击查看消息：id
	$.layerSetup({
		 id:"sentence",
		 title:'消息盒子',
		 content:'<iframe id="frm_sentence" src="${ctx}/manager/zmd/KonkaXxMessageFrame.do?msg_id=' + msg_id + '" frameborder="0" height="380" width="645" ></iframe>',
			 	 //'<frameset cols="400,*" framespacing="0" frameborder="no" border="0" id="setyou">' +
		  		//	'<frame name="leftFrame" marginwidth="0" marginheight="0" id="leftFrame" title="leftFrame" noresize="noresize" scrolling="auto"/>' +
		  			//'<frame name="middleFrame" marginwidth="0" marginheight="0" id="middleFrame" title="middleFrame" noresize="noresize" scrolling="no"/>' +
		  		// 	'<frame name="mainFrame" marginwidth="0" marginheight="0" scrolling="yes" id="mainFrame" title="mainFrame"  noresize="noresize" />' +
				 //'</frameset>',
		 isbg:true,
		 templete:'<div class="showwint_mini_title8">' +
		 			//'<iframe id="frm_sentence" src="about:blank" frameborder="0" height="300" width="700" >' +
			 	  	'<table border="0" cellpadding="0" cellspacing="0" width="700px;">' +
			 	  		'<tr style="cursor:move" id="">' +
							'<td width="13" height="33" class="ms-lt">' +
								'<div style="width:13px;"></div>' +
							'</td>' +
							'<td height="33" class="ms-ct">' +
								'<span class="showwint_mini_close_btn8"><a href="javascript:void(null);" class="layerclose"></a></span>' +
					 	  		'<span class="showwint_mini_title_content8" id="@moveid@">' +
					 				'<span id="@titleid@"></span>' +
					 			'</span>' +
							'</td>' +
							'<td width="13" height="33" class="ms-rt">' +
								'<div style="width:13px;"></div>' +
							'</td>' +
						'</tr>' +
			 	  	'</table>' +
			 	  	//'</iframe>' +
			 	  '</div>' +
			 	  '<div>' +
			 	  	//'<iframe id="frm_sentence" src="about:blank" frameborder="0" height="300" width="700" >' +
			 	 	'<table border="0" cellpadding="0" cellspacing="0" width="700px;">' +
			 	 		'<tr>' +
							'<td width="13" class="ms-mlm"></td>' +
							'<td align="center" valign="top">' +
								'<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">' +
									'<tr id="" style="display:none;">' +
      									'<td height="50" valign="top">' +
      										'<table id="" width="100%" border="0" cellspacing="0" cellpadding="8" style="background:#EAECE9 url(\'${ctx}/styles/message/images/dialog_bg.jpg\') no-repeat right top;">' +
            									'<tr>' +
            										'<td width="25" height="50" align="right">' +
            											'<img id="" src="${ctx}/styles/message/images/window.gif" width="32" height="32" style="margin-left:10px;">' +
            										'</td>' +
            										'<td align="left" style="line-height:16px;">' +
            											'<h5 class="fb" id="">&nbsp;</h5>' +
            											'<div id="">&nbsp;</div>' +
            										'</td>' +
          										'</tr>' +
          									'</table>' +
          								'</td>' +
          							'</tr>' +
									'<tr>' +
										'<td align="center" valign="top">' +
											'<div style="position:relative;width:650px;height:400px">' +
												'<div id="" style="position:absolute; height:100%; width:100%;display:none;">&nbsp;</div>' +
												'<div id="@contentid@" style="text-align:left;">' +
													
												'</div>' +
											'</div>' +
										'</td>' +
									'</tr>' +
//									'<tr>' +
//										'<td height="36">' +
//											'<div id="" style="height:21px;text-align:right;border-top:#dadee5 1px solid; padding:8px 20px; background-color:#f6f6f6;">' +
	           									'<!-- <input id="" type="button" onclick="openMessageBox()" value="查看历史消息">-->' +
												'<!--<div style="float:left;"><a href="#" style="line-height:21px;" onclick="openMessageBox()">查看历史消息</a></div>-->' +
												//'<input id="" type="button" onclick="" value="关闭" class="layerclose">' +
//												'<a href="#" style="line-height:21px;" class="layerclose" onclick=""> 关 闭 </a>' +
												//'<div class="text_right" style="float:right;">' +
												//	'<a href="#"><img id="slidePrev" height="20" alt="back" src="${ctx}/styles/message-scroll/images/testimonial-back-btn.gif" /></a>' +
												//	'<a href="#"><img id="slideNext" height="20" alt="next" src="${ctx}/styles/message-scroll/images/testimonial-next-btn.gif" /></a>' +
												//'</div>' +
//											'</div>' +
//										'</td>' +
//									'</tr>' +
								'</table>' +
							'</td>' +
							'<td width="13" class="ms-mrm"></td>' +
						'</tr>' +
						'<tr>' +
							'<td width="13" height="13" class="ms-lb"></td>' +
							'<td class="ms-cb"></td>' +
							'<td width="13" height="13" class="ms-rb"></td>' +
						'</tr>' +
					'</table>' +
					//'</iframe>' +
				'</div>'
    });
	$.layershow();
}

</script>
<!-- 消息盒子End -->
</c:if>