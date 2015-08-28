<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=0.67, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>触网</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/epp/mobile/css/epp_index.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/wap/css/global.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/wap/css/ping.css" />  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>  
</head>
<body><div class="top_class"><span class="lspan"><a href="javascript:void(0);history.back();"><img src="${ctx}/styles/epp/mobile/images/return.gif" /></a></span><h3>评论信息</h3><a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"><span>触网</span></a>
	<div class="new-jd-tab" style="top:45px;display:none;" id="jdkey">
	<div class="topsearch">
		<form method="post" action="<c:url value='/webservice/wap/KonkaBcompPd.do' />" id="search_form_top">
		    <table style="width:100%">
			    <tr>
			    <td><div class="searcharea"><input name="pd_sn_or_pd_name_like" id="pd_sn_or_pd_name_like" class="input_search" type="text" maxlength="20" value="搜索商品" onfocus="if(value=='搜索商品') {value='';}" onblur="if (value=='') {value='搜索商品';}"/></div></td>
			    <td class="padl5" width="60">
			    <a href="javascript:void(0);" id="search_btn_sub_top" class="new-tbl-cell"><span class="icon2">搜索</span></a>
			    <!-- a href="javascript:void(0);showNav();" id="btnJdkey" class="new-a-jd"  style="margin-top:0;" ><span>触网</span></a -->
			    </td>
			    </tr>
		    </table>
	    </form> 
    </div>	
	<div class="new-tbl-type">
		<a href="<c:url value='/webservice/wap/Index.do' />" class="new-tbl-cell"><span class="icon">首页</span><p style="color:#6e6e6e;">首页</p>	</a>
		<a href="<c:url value='/webservice/wap/ProdType.do' />" class="new-tbl-cell"><span class="icon2">产品分类</span><p style="color:#6e6e6e;">产品分类</p></a>
		<a href="<c:url value='/webservice/wap/ShoppingCar.do' />" id="html5_cart" class="new-tbl-cell"><span class="icon3 on">购物车</span><p style="color:#6e6e6e;" class="on">购物车</p></a>
		<a href="<c:url value='/webservice/wap/center/Index.do' />" class="new-tbl-cell"><span class="icon4">会员中心</span> <p style="color:#6e6e6e;">会员中心</p></a>
	</div>
	</div>
</div>
<div id="content"> 
<div class="mainbox">
<div class="maincont">  
   <div class="Votes">
      	<ol><li><font><fmt:formatNumber value="${score45}" pattern="0.0" />%</font>好评度</li> <li>共${scoreCount}个用户</li></ol>
    	<ul> 
	      <li><s>好评</s><span class="widthbg"><em style="width: <fmt:formatNumber value="${score5+score4}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score5+score4}" pattern="0.0" />%</li>
	      <li><s>中评</s><span class="widthbg"><em style="width: <fmt:formatNumber value="${score3}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score3}" pattern="0.0" />%</li>
	      <li><s>差评</s><span class="widthbg"><em style="width: <fmt:formatNumber value="${score2+score1}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score2+score2+score1}" pattern="0.0" />%</li>
	    </ul>	  
  </div>
  <div id="ProComment_Panel_Null">
    <div class="Nodata">
      <div class="tabbtn">
        <ul>
          <li class="${empty af.map.eval? 'current':'' }" style="width:80px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }" style="width:60px;">全部(${scoreCount})</a></li>
          <li class="${af.map.eval eq '1' ? 'current':'' }" style="width:70px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eval=1" >好评(${eval_score_a})</a></li>
          <li class="${af.map.eval eq '2' ? 'current':'' }" style="width:70px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eval=2" style="width:60px;">中评(${eval_score_b})</a></li>
          <li class="${af.map.eval eq '3' ? 'current':'' }" style="width:70px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eval=3" style="width:60px;">差评(${eval_score_c})</a></li>
        </ul>
      </div>
      <div class="tabbtn-b"><table width="100%">
      	 <tr style="background-color:#ccc;color:#000;height:32px;">
      	  	<th>评价心得</th>
      	    <th width="20%">顾客满意度</th>
      	    <th width="20%">评论者</th>
      	  </tr>
      	  <c:forEach var="cur" items="${entityList}" varStatus="vs">
      	  <c:set var="real_name" value=""/>
      	  <tr >
      	  	<td><div style="margin-top:4px;"><c:out value="${cur.eval_title}"/>&nbsp;&nbsp;&nbsp;&nbsp; <fmt:formatDate value="${cur.eval_date}" pattern="yyyy-MM-dd HH:mm:ss" /><br/><c:out value="${cur.eval_con_sumary}"/>
	      	  	<c:if test="${not empty cur.konkaPeAttachmentsList}"><div>			    
			      <c:forEach var="imgs" items="${cur.konkaPeAttachmentsList}" varStatus="vsi"> 
			       <a href="${ctx}/${imgs.save_path}" target="_blank"><img src="${ctx}/${imgs.save_path}" width="60"></img></a> 
			      </c:forEach> </div>
			  </c:if>		
      	  	</div></td>
      	    <td><span class="star${cur.eval_score }"></span></td>
      	    <td width="15%">
	      	   <c:if test="${cur.is_anon eq 0 }"><font>匿名用户</font> <c:set var="real_name" value="匿名用户"/></c:if>
	           <c:if test="${cur.is_anon eq 1 }"><font><c:out value="${cur.map.real_name }" /> <c:set var="real_name" value="${cur.map.real_name }"/></font></c:if>
           </td> 
      	  </tr>
      	  <tr>
	      	  <td colspan="3">
		      	  <c:if test="${not empty cur.re_content}">
		      	  <p><span style="font-size:12px;line-height:40px;">客服回复</span>： <font color="red"><c:out value="${cur.re_content}"/></font></p>
		      	  </c:if>		      	
		      	<p><a href="javascript:void(0);" onclick="show_re_div('re_div_${cur.id }');">回复（${fn:length(cur.ecPdEavlList)}）</a> <a href="javascript:void(0);" onclick="dian_zan('${cur.id }');" >赞（<font id="zan_${cur.id }">${cur.zan_num}</font>）</a></p>
		      	<div style="display:none" id="re_div_${cur.id }"> <!-- 回复  start -->
			      	<div style="background-color:#F1EEEE;height:75px;" >
			      		<form action="${ctx }/zxmall/EcPdEavl.do?method=saveRe" method="post" id="form_${cur.id }">
				      	<input type="hidden"  name="goods_id" value="${af.map.goods_id }"/>
				      	<input type="hidden"  name="par_id" value="${cur.id }"/>
			      	    <input type="text" name="eval_con_sumary" class="comm_input" maxlength="120" style="margin-left:15px;margin-top:15px;width:92%" id="eval_con_sumary" placeholder="回复  ${real_name }"/><br/> 
			      		<span style="padding:2px;margin-top:5px;background-color:#ffffff;float:right;margin-right:30px; border: 1px solid #7C6F6F;" onclick="re_sbt(document.getElementById('form_${cur.id }'));">提交回复</span>
			      		</form>
			      	</div> 
			      	<c:forEach var="cur2" items="${cur.ecPdEavlList}" varStatus="vs2"><c:set var="real_name" value="${cur.map.real_name }"/>				      	   
			      	<div style="margin-top:10px;border-top: 1px dotted #ccc;">
				      	<p style="line-height:30px;">${real_name }:<font color="#005aa0"><c:out value="${cur2.eval_con_sumary}"/></font>&nbsp;&nbsp;	<fmt:formatDate value="${cur2.eval_date}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
				      	<p><a href="javascript:void(0);" onclick="show_re_div('re_div_${cur2.id }');">回复</a></p>
				      	<div style="display:none" id="re_div_${cur2.id }">
				      	<div style="background-color:#F1EEEE;height:75px;" ><form action="${ctx }/zxmall/EcPdEavl.do?method=saveRe" method="post" id="form_${cur2.id }">
					      	<input type="hidden"  name="goods_id" value="${af.map.goods_id }"/>
					      	<input type="hidden"  name="par_id" value="${cur.id }"/>
				      	    <input type="text" name="eval_con_sumary" class="comm_input" maxlength="120" style="margin-left:15px;margin-top:15px;width:92%" id="eval_con_sumary" placeholder="回复  ${real_name }"/><br/> 
				      		<span style="padding:2px;margin-top:5px;background-color:#ffffff;float:right;margin-right:30px; border: 1px solid #7C6F6F;" onclick="re_sbt(document.getElementById('form_${cur2.id }'));">提交回复</span>
				      		</form>
				      	</div>
				      	</div>
			      	 </div></c:forEach><!-- 回复  end-->
		      	</div>
	      	  </td>
      	   </tr>
      	   <tr><td  colspan="3"><hr/></td></tr>
      	  </c:forEach>
      	</table> <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table style="width:100%;">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("start_date", "<c:out value='${af.map.start_date}'/>"); 	
			                     pager.addHiddenInputs("end_date", "<c:out value='${af.map.end_date}'/>"); 	
			                     document.write(pager.toString());
			      </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>  
       </div>
    </div>
  </div> 
</div>
</div>
</div>  
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[ 
                                          
var c=0;                                         
$(document).ready(function(){

	$("#eval_con_sumary").keyup(check);    
	$("#eval_con_sumary").attr("dataType", "Require").attr("msg", "请填写总结！"); 
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form,3);
		if (isSubmit) {
			if(c==0){ 
			    var str = $("#eval_con_sumary").val(); 
			    str = trim(str);  
			    $("#eval_con_sumary").val(str);
				 this.form.submit();
			}else{
				alert("总结字数超出");
			}
		}
	});
	
	$(document).delegate("#search_btn_sub_top", "click", function(){
		if($("#pd_sn_or_pd_name_like").val()=='搜索商品'){
			$("#pd_sn_or_pd_name_like").val('');
		}
		$("#search_form_top").submit();
	});
     
});

function check() {
    var str = $("#eval_con_sumary").val();  
    var len = strlen(str);  
    var info = 250 - len;  
    info = info + "";  
    if(info.indexOf('.') > 0){
        info = info.substring(0, info.indexOf('.'));
    } 
    if(len <= 250) {
    	c=0;
        $("#info1").html("还能输入");
        $("#info").css('color', 'gray');
        $("#info").html(info);
    }else {
    	c=1;
        info = info.substr(1)
        $("#info1").html("超出");
        $("#info").css('color', 'red');
        $("#info").html(info);
     }
}  

function trim(str) {
    return (str + '').replace(/(\s+)$/g, '').replace(/^\s+/g, '');
}
function strlen(str) {
    str = trim(str);  
    var len = 0;  
    for (var i = 0; i < str.length; i++) {  
        len += str.charCodeAt(i) > 0 && str.charCodeAt(i) < 255 ? 0.5 : 1;  
    }
    return len;
}  

function re_sbt(form){
	 <c:if test="${empty ecUser }">
	 alert("请登陆后再发表评论");
	 return false;
	 </c:if>
	if(form.eval_con_sumary.value==""){
		alert("回复内容不能为空");
		form.eval_con_sumary.focus();
		return false;
	}
	if(c==0){
		c=1;
		form.submit();
	}
}

function show_re_div(id){
	if($("#"+id).is(':hidden')){
		 $("#"+id).show();
	}else{
		 $("#"+id).hide();
	}
	try{
		window.parent.window.dyniframesizeForPdEval("pc_pd_eval_iframe");
	}catch(e){ }
}

function dian_zan(id){	
	<c:if test="${empty ecUser }">
	 alert("请登陆后再点赞");
	 return false;
	 </c:if>
	$.ajax({
			type: "POST",
			url: "<c:url value='/webservice/wap/EcPdEavl.do' />",
			data: { "method":"ajaxZan", "goods_id":${af.map.goods_id }, "eavl_id":id, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true, // jsonp不支持同步
			error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result) {
				if(result.status=="1"){
					$("#zan_"+id).html(result.num);
				} 
				alert(result.msg);
			}
		}); 
}


function showNav(){
	if(document.getElementById("jdkey").style.display=='none'){
		document.getElementById("jdkey").style.display='block';
	}else{
		document.getElementById("jdkey").style.display='none';
	} 
}
//]]>
</script> 
</body>
</html>
