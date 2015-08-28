<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/ping.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
</head>
<body> 
<div class="Wrap_ProComment" style="display: block; ">
  <div class="Votes">
    <ol>
      <li><font><fmt:formatNumber value="${score45}" pattern="0.0" />%</font>好评度</li>
    </ol>
    <ul>
      <li><s>好评</s><span class="widthbg"><em style="width: <fmt:formatNumber value="${score5+score4}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score5+score4}" pattern="0.0" />%</li>
      <li><s>中评</s><span class="widthbg"><em style="width: <fmt:formatNumber value="${score3}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score3}" pattern="0.0" />%</li>
      <li><s>差评</s><span class="widthbg"><em style="width: <fmt:formatNumber value="${score2+score1}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score2+score2+score1}" pattern="0.0" />%</li>
    </ul>
    <dl>
      <b>用户平均打分<fmt:formatNumber value="${scorea}" pattern="0.0" /></b>(目前已有<font>${scoreCount}</font>篇用户评价)
    </dl>
  </div>
  <div id="ProComment_Panel_Null">
    <div class="Nodata">
      <div class="tabbtn">
        <ul>
          <li class="${empty af.map.eval? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }">全部评论(${scoreCount})</a></li>
          <li class="${af.map.eval eq '1' ? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eval=1">好评(${eval_score_a})</a></li>
          <li class="${af.map.eval eq '2' ? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eval=2">中评(${eval_score_b})</a></li>
          <li class="${af.map.eval eq '3' ? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eval=3">差评(${eval_score_c})</a></li>
        </ul>
      </div>
      <div class="tabbtn-b">
      	<table width="100%">
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
      	</table> 
       <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
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
  <div class="CommentForm" style="margin-top:10px; display:none;">
    <h5>发表评论并打分：</h5>
    <form action="${ctx }/zxmall/EcPdEavl.do?method=save" method="post" enctype="multipart/form-data">
    <input type="hidden"  name="goods_id" value="${af.map.goods_id }"/>
    <input type="hidden"  name="detail_id" value="${af.map.detail_id }" id="detail_id"/> 
    <table cellpadding="0" cellspacing="0" style="margin-top:5px;">
      <tbody>
        <tr>
          <td valign="bottom" align="center" width="12%">评&nbsp;&nbsp;分：</td>
          <td><table id="ProComment_rdo_point" border="0">
              <tbody>
                <tr>
                  <td><input id="ProComment_rdo_point_0" type="radio" name="eval_score" value="5" checked="checked" />
                    <label for="ProComment_rdo_point_0"><span class="star5"></span></label></td>
                  <td><input id="ProComment_rdo_point_1" type="radio" name="eval_score" value="4" />
                    <label for="ProComment_rdo_point_1"><span class="star4"></span></label></td>
                  <td><input id="ProComment_rdo_point_2" type="radio" name="eval_score" value="3" />
                    <label for="ProComment_rdo_point_2"><span class="star3"></span></label></td>
                  <td><input id="ProComment_rdo_point_3" type="radio" name="eval_score" value="2" />
                    <label for="ProComment_rdo_point_3"><span class="star2"></span></label></td>
                  <td><input id="ProComment_rdo_point_4" type="radio" name="eval_score" value="1" />
                    <label for="ProComment_rdo_point_4"><span class="star1"></span></label></td>
                </tr>
              </tbody>
            </table></td>
          <td> </td>
        </tr>
      </tbody>
    </table>
    <p style="height: 32px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关键字：
    <input type="text" name="eval_title" class="comm_input" maxlength="100" id="eval_title"/>
    </p> 
    <dl>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心&nbsp;&nbsp;得：
     	<textarea name="eval_con_sumary" rows="8" cols="20" id="eval_con_sumary" style="width:60%;"></textarea>
	    <label id="info1" style="font-size: 12px;"> 还能输入</label>
	    <span id="info" style=" font-size: 14px; ">250</span>
	    <label id="info2" style=" font-size: 12px;"> 个字</label>   
    </dl> 
    <dl>
    <table>
    <tr>
     <td width="62" align="right">晒&nbsp;&nbsp;单： </td>
      <td><span id="img_div"></span></td>
     <td>
    	<span style="display:none"><input id="sendImage1" type="file" name="sendImage1" value="" accept=".jpg,jpeg,.JPG,.JPEG" /><input id="sendImage2" type="file" name="sendImage2" value="" accept=".jpg,jpeg,.JPG,.JPEG"/><input id="sendImage3" type="file" name="sendImage3" value="" accept=".jpg,jpeg,.JPG,.JPEG"/></span>
        <label for="sendImage1" title="上传图片" id="label_1" style=""><font size="20">+</font> </label>
        <label for="sendImage2" title="上传图片" id="label_2" style="display:none;"><font size="20">+</font> </label>
        <label for="sendImage3" title="上传图片" id="label_3" style="display:none;"><font size="20">+</font> </label>
        	图片上传：只支持 *.jpg格式文件
       </td>
    </tr>
    </table> 
    	
    </dl> 
    <div id="ProComment_PanLogin">
      <c:if test="${not empty zxmall }">
       <c:if test="${c lt d}">
    	&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
     	 <input type="button" class="btn_comment" id="btn_submit" />
		&nbsp; &nbsp;&nbsp;
                  当前用户:${zxmall.user_name }  &nbsp; &nbsp;<input type="checkbox" name="is_anon" value="1" > 匿名评论</input>
       </c:if> <c:if test="${c ne 0 and c ge d }">
     <br/>&nbsp; &nbsp;&nbsp;  您已经评论过了
       </c:if>          
  	 </c:if>
  	 <c:if test="${d eq 0 }">
     <br/>&nbsp; &nbsp;&nbsp; 请购买产品后再评论
     </c:if>   
  	 <c:if test="${empty zxmall }">
  	<br/>&nbsp; &nbsp;&nbsp; 请登录平台后发表评论
   	</c:if>
    </div>
    </form>
  </div>
</div>
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
var c=0;                                         
$(document).ready(function(){ 
	 document.getElementById('sendImage1').addEventListener('change', function() {
         if (this.files.length != 0) {
             var file = this.files[0], reader = new FileReader() ;
             reader.onload = function(e) {
               showLabel();
               displayImage(e.target.result,1);
             };
             reader.readAsDataURL(file);
         };
     }, false); 
	 document.getElementById('sendImage2').addEventListener('change', function() {
         if (this.files.length != 0) {
             var file = this.files[0], reader = new FileReader() ;
             reader.onload = function(e) {

                 showLabel();
               displayImage(e.target.result,2);
             };
             reader.readAsDataURL(file);
         };
     }, false); 
	 document.getElementById('sendImage3').addEventListener('change', function() {
         if (this.files.length != 0) {
             var file = this.files[0], reader = new FileReader() ;
             reader.onload = function(e) { 
               showLabel();
               displayImage(e.target.result,3);
             };
             reader.readAsDataURL(file);
         };
     }, false); 

	$("#eval_con_sumary").keyup(check);  
    //$("#eval_con_sumary").onpaste(check);
	$("#eval_title"	).attr("dataType", "Require").attr("msg", "请填写关键字！"); 
	//$("#eval_con_merit"	).attr("dataType", "Require").attr("msg", "请填写优点！"); 
	//$("#eval_con_defect").attr("dataType", "Require").attr("msg", "请填写缺点！"); 
	$("#eval_con_sumary").attr("dataType", "Require").attr("msg", "请填写心得！"); 
	
	$("#btn_submit").click(function(){
		if($("#detail_id").val()==""){
			alert('请先购买产品再评论');
			return false;
		}
		var isSubmit = Validator.Validate(this.form,3);
		if (isSubmit) {
			if(c==0){
				c=1;
				//$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			    var str = $("#eval_con_sumary").val(); 
			    str = trim(str);  
			    $("#eval_con_sumary").val(str);
				 this.form.submit();
			}else{
				alert("心得字数超出");
			}
		}
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
	 <c:if test="${empty zxmall }">
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
		window.parent.window.dyniframesizeForPdEval("pc_pd_eavl_iframe");
	}catch(e){ }
}

function dian_zan(id){	
	<c:if test="${empty zxmall }">
	 alert("请登陆后再点赞");
	 return false;
	 </c:if>
	$.ajax({
			type: "POST",
			url: "<c:url value='/zxmall/EcPdEavl.do' />",
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

function displayImage(imgData,index) {
   var html ='<span id="img_'+index+'"><a href="' + imgData + '" target="_blank"><img src="' + imgData + '" width="60"/></a> <a href="javascript:void(0);" title="删除" onclick="remove_img(\''+index+'\');"><font color="red" size="9">×</font></a></span>'; 
   document.getElementById("img_div").innerHTML=document.getElementById("img_div").innerHTML+html;
}

function remove_img(index){
	var span_img = document.getElementById("img_"+index);
	span_img.parentNode.removeChild(span_img); 
	document.getElementById("sendImage"+index).value="";
	showLabel();
}

function showLabel() {
	var flg=true;
	for(var i=1;i<4;i++){ 
		document.getElementById("label_"+i).style.display="none"; 
	}
	for(var i=1;i<4;i++){ 
		document.getElementById("label_"+i).style.display="none";
		if(flg&&document.getElementById("sendImage"+i).value==""){ 
			flg=false; 
			document.getElementById("label_"+i).style.display="block";
		} 
	}
}

//]]>
</script> 
</html>
