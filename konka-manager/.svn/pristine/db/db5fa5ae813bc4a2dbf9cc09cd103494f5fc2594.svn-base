<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sfmall/qqonline/css/qqonline_blue.css" /> 
<script type="text/javascript" src="${ctx}/styles/sfmall/qqonline/jquery.Sonline.js"></script>
<style type="text/css">
.float_div{position: absolute;border: solid 1px #d1e3f5;top:100px;text-align: center;background: #f5f4f4;left:80%;width:157px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:1000;}
.float_div div{margin-top: 0px;}
.close{ float: right;bottom: 0px;color:red;} 
.app_div{position: absolute;border: solid 1px #d1e3f5;top:30px;text-align: center;background: #f5f4f4;left:30%;width:157px;padding-bottom: 0px;padding-top: 0px;display: none;z-index:1000;}
.app_div div{margin-top: 0px;} 
</style>
<div class="top-2" > 
	  <div class="p1" style="margin-top:6px;"><img src="${ctx}/styles/sfmall/images/pingtai2.jpg" height="65"  /></div>     
	  <div class="p2">
	   	 <form method="post" action="<c:url value='/sfmall/KonkaBcompPd.do' />" id="search_form_top">
	      <div class="p2-1">
	        <input type="text"  name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" value="${search_pd_name}"  maxlength="50" />
	      </div>
	      <div class="p2-2"><img id="search_btn_sub_top" src="${ctx}/styles/sfmall/images/search.jpg" height="28" width="91" /></div>
	  	 </form>
	  </div>
	  <div class="p3"><img src="${ctx}/styles/sfmall/images/tel.jpg" height="60" /></div>
	  <div class="p4">
	    <table width="100%" cellspacing="0" cellpadding="0" border="0" style="font-size:9px;">
	    	<tr>
	   		<td width="50%" align="center"><a href="http://weibo.com/konka" target="_blank"><img src="${ctx}/styles/sfmall/images/weibo01.png" /></a></td>
	    	<td width="50%" align="center"><img id="weixin" src="${ctx}/styles/sfmall/images/weixin01.png"  /></td>
	    	</tr>
	    	<tr><td align="center"><font color="#cccccc">新浪微博</font></td><td align="center"><font color="#cccccc">微信公众号</font></td></tr> 
	    </table>
	  </div>
</div>
<div class="float_div" id="wxPanel" style="display:none;z-index:10000;position:absolute;">
<img src="${ctx}/styles/sfmall/images/weixin_code.jpg"  /> 
</div>
<div class="app_div" id="appPanel" style="display:none;z-index:10000;position:absolute;">
<img src="${ctx}/styles/sfmall/images/app_code.jpg"  width="147"  /> 
</div> 
<script type="text/javascript">
$(document).ready(function(){
	$(document).delegate("#search_btn_sub_top", "click", function(){
		$("#search_form_top").submit();
	});
	var weixin_flg=0;
	$("#weixin").mouseover(function(){$("#wxPanel").fadeIn(500); weixin_flg=2;});
	$("#weixin").click(function(){$("#wxPanel").fadeIn(500); weixin_flg=1;});
	//$("#btn_close").click(function(){$("#wxPanel").fadeOut(500);weixin_flg=0;});
	
	$("#app").mouseover(function(){$("#appPanel").fadeIn(500); weixin_flg=2;});
	$("#app").click(function(){$("#appPanel").fadeIn(500); weixin_flg=1;});
	$(document).click(function(){
		weixin_flg++;
		if(weixin_flg>2){
			$("#wxPanel").fadeOut(500);
			$("#appPanel").fadeOut(500);
			weixin_flg=0;
		}
	}); 
});
$(function(){$("body").Sonline({
	Position:"right",Top:200,Effect:true,DefaultsOpen:false,
	Qqlist:"3010785153|彩&nbsp;&nbsp;电客服,2291790018|彩&nbsp;&nbsp;电客服,2949037121|彩&nbsp;&nbsp;电客服,2822530662|彩&nbsp;&nbsp;电客服,1020045362|白&nbsp;&nbsp;电客服,272517180|小家电客服"
	});})
</script>