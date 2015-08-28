<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=0.67, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" /> 
<title>开心猫</title> 
<link rel="stylesheet" type="text/css" href="${ctx}/styles/wap/css/global.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/wap/css/ping.css" />  
<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>  
</head>
<body> 
   <div class="Votes">
      	<ol><li><font><fmt:formatNumber value="${score45}" pattern="0.0" />%</font>好评度</li> <li>共${scoreCount}个用户</li></ol>
    	<ul>
	      <li><s>5分</s><font><span class="star5"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score5}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score5}" pattern="0.0" />%</li>
	      <li><s>4分</s><font><span class="star4"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score4}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score4}" pattern="0.0" />%</li>
	      <li><s>3分</s><font><span class="star3"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score3}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score3}" pattern="0.0" />%</li>
	      <li><s>2分</s><font><span class="star2"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score2}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score2}" pattern="0.0" />%</li>
	      <li><s>1分</s><font><span class="star1"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score1}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score1}" pattern="0.0" />%</li>
	    </ul>
  </div>
  <div id="ProComment_Panel_Null">
    <div class="Nodata">
      <div class="tabbtn">
        <ul>
          <li class="${empty af.map.eavl? 'current':'' }" style="width:80px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }" style="width:60px;">全部(${scoreCount})</a></li>
          <li class="${af.map.eavl eq '1' ? 'current':'' }" style="width:70px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eavl=1" >好评(${eavl_score_a})</a></li>
          <li class="${af.map.eavl eq '2' ? 'current':'' }" style="width:70px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eavl=2" style="width:60px;">中评(${eavl_score_b})</a></li>
          <li class="${af.map.eavl eq '3' ? 'current':'' }" style="width:70px;"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eavl=3" style="width:60px;">差评(${eavl_score_c})</a></li>
        </ul>
      </div>
      <div class="tabbtn-b"><c:forEach var="cur" items="${entityList}" varStatus="vs">
       <div class="comment-eval_con_sumary">
          <div class="comment-eval_con_sumary-top">评论用户:<c:if test="${cur.is_anon eq 0 }"><font>匿名用户</font></c:if><c:if test="${cur.is_anon eq 1 }"><font><c:out value="${cur.map.real_name }" /></font></c:if></div>
          <div class="comment-eval_con_sumary-b">
            <dl><dt>标题： <c:out value="${cur.eval_title}"/> <c:if test="${cur.audit_state ne 1}">&nbsp;&nbsp;<i>(未审核)</i></c:if></dt></dl>
            <dl><dt>优点：  <c:out value="${cur.eval_con_merit}"/></dt></dl><dl><dt>缺点：  <c:out value="${cur.eval_con_defect}"/></dt></dl> <dl><dt>总结： <c:out value="${cur.eval_con_sumary}"/></dt></dl>          
          	<c:if test="${not empty cur.re_content}"><dl><dt><span style="font-size:15px;">客服回复</span>： <font color="red"><c:out value="${cur.re_content}"/></font></dt></dl></c:if>
          </div>
        </div></c:forEach><c:if test="${af.map.pager.pageCount>1}">
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
  <div class="CommentForm" style="margin-top:10px;">
    <h5>发表评论并打分：</h5><form action="${ctx }/wap/EcPdEavl.do?method=save" method="post"> <input type="hidden"  name="goods_id" value="${af.map.goods_id }"/>
        <p style="margin-top:5px;"><span style="margin-left:20px;">评&nbsp;&nbsp;分：</span>
        <label for="ProComment_rdo_point_0"> <input id="ProComment_rdo_point_0" type="radio" name="eval_score" value="5" checked="checked" /> <span class="STYLE1"> 5分</span></label>
        <label for="ProComment_rdo_point_1"> <input id="ProComment_rdo_point_1" type="radio" name="eval_score" value="4" /> <span class="STYLE1"> 4分</span> </label>
        <label for="ProComment_rdo_point_2"> <input id="ProComment_rdo_point_2" type="radio" name="eval_score" value="3" /> <span class="STYLE1"> 3分</span> </label>
        <label for="ProComment_rdo_point_3"> <input id="ProComment_rdo_point_3" type="radio" name="eval_score" value="2" /> <span class="STYLE2"> 2分 </span> </label>
        <label for="ProComment_rdo_point_4"> <input id="ProComment_rdo_point_4" type="radio" name="eval_score" value="1" /> <span class="STYLE2"> 1分</span> </label>
        </p>
	    <p style="height: 32px;"><span style="margin-left:20px;">标&nbsp;&nbsp;题：</span> <input type="text" name="eval_title" class="comm_input" maxlength="100" id="eval_title"/> </p>
	    <p style="height: 32px;"><span style="margin-left:20px;">优&nbsp;&nbsp;点：</span>  <input type="text" name="eval_con_merit" class="comm_input" maxlength="100" id="eval_con_merit"/>  </p>
	    <p style="height: 32px;"><span style="margin-left:20px;">缺&nbsp;&nbsp;点：</span>  <input type="text" name="eval_con_defect" class="comm_input" maxlength="100" id="eval_con_defect"/>  </p>
        <p><span style="margin-left:20px;">总&nbsp;&nbsp;结：</span> <textarea name="eval_con_sumary" rows="6" cols="16" id="eval_con_sumary" style="width:60%;" ></textarea><br/> 
        <span id="info1" style="margin-left:70px;"> 还能输入</span><span id="info" style=" font-size: 14px; ">250</span><label id="info2" style="font-size: 12px;"> 个字</label></p>
      	<div id="ProComment_PanLogin"><c:if test="${not empty ecUser }">
        <c:if test="${c lt d}"> 
    	 <div style="padding-bottom:5px;margin-left:70px;">当前用户:${ecUser.user_name }&nbsp; &nbsp;<input type="checkbox" name="is_anon" value="1" id="is_anon"/><label for="is_anon"> 匿名评论</label></div>
	     <input  class="but_ping" value="评 价" type="button"  id="btn_submit" /> 
	    </c:if>
	    <c:if test="${c ne 0 and c ge d }"><p style="margin-left:70px;"> 您已经评论过了</p> </c:if></c:if> <c:if test="${d eq 0 }"><p style="margin-left:70px;">请购买产品后再评论</p> </c:if> <c:if test="${empty ecUser }"><p style="margin-left:70px;"> 请登录平台后发表评论 </p>	</c:if> 
	   	</div>
    </form>
  </div> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
var c=0;                                         
$(document).ready(function(){

	$("#eval_con_sumary").keyup(check);   
	$("#eval_title"	).attr("dataType", "Require").attr("msg", "请填写标题！"); 
	$("#eval_con_merit"	).attr("dataType", "Require").attr("msg", "请填写优点！"); 
	$("#eval_con_defect").attr("dataType", "Require").attr("msg", "请填写缺点！"); 
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

//]]>
</script> 
</body>
</html>
