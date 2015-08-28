<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/global1.css" /> 
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/ping.css" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script> 
</head>
<body> 
<div class="Wrap_ProComment" style="display: block; ">
  <div class="Votes">
    <ol>
      <li><font><fmt:formatNumber value="${score45}" pattern="0.0" />%</font>好评度</li>
    </ol>
    <ul>
      <li><s>5分</s><font><span class="star5"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score5}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score5}" pattern="0.0" />%</li>
      <li><s>4分</s><font><span class="star4"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score4}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score4}" pattern="0.0" />%</li>
      <li><s>3分</s><font><span class="star3"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score3}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score3}" pattern="0.0" />%</li>
      <li><s>2分</s><font><span class="star2"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score2}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score2}" pattern="0.0" />%</li>
      <li><s>1分</s><font><span class="star1"></span></font><span class="widthbg"><em style="width: <fmt:formatNumber value="${score1}" pattern="0.0" />%"></em></span><fmt:formatNumber value="${score1}" pattern="0.0" />%</li>
    </ul>
    <dl>
      <b>用户平均打分<fmt:formatNumber value="${scorea}" pattern="0.0" /></b>(目前已有<font>${scoreCount}</font>篇用户评价)
    </dl>
  </div>
  <div id="ProComment_Panel_Null">
    <div class="Nodata">
      <div class="tabbtn">
        <ul>
          <li class="${empty af.map.eavl? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }">全部评论(${scoreCount})</a></li>
          <li class="${af.map.eavl eq '1' ? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eavl=1">好评(${eval_score_a})</a></li>
          <li class="${af.map.eavl eq '2' ? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eavl=2">中评(${eval_score_b})</a></li>
          <li class="${af.map.eavl eq '3' ? 'current':'' }"><a href="EcPdEavl.do?method=list&goods_id=${af.map.goods_id }&eavl=3">差评(${eval_score_c})</a></li>
        </ul>
      </div>
      <div class="tabbtn-b">
         <c:forEach var="cur" items="${entityList}" varStatus="vs">
        <div class="comment-eval_con_sumary">
          <div class="comment-eval_con_sumary-top">
           评价用户： <c:if test="${cur.is_anon eq 0 }">
           <font>匿名用户</font></c:if>
           <c:if test="${cur.is_anon eq 1 }">
           <font><c:out value="${cur.map.real_name }" /></font></c:if>
          </div>
          <div class="comment-eval_con_sumary-b">
            <dl>
              <dt>标题： <c:out value="${cur.eval_title}"/> <c:if test="${cur.audit_state ne 1}">&nbsp;&nbsp;<i>(未审核)</i></c:if></dt>
            </dl>
            <dl>
              <dt>优点：  <c:out value="${cur.eval_con_merit}"/></dt>
            </dl>
            <dl>
              <dt>缺点：  <c:out value="${cur.eval_con_defect}"/></dt>
            </dl>
            <dl>
              <dt>总结：  <c:out value="${cur.eval_con_sumary}"/></dt>
            </dl><c:if test="${not empty cur.re_content}">
            <dl>
              <dt><span style="font-size:15px;">客服回复</span>： <font color="red"><c:out value="${cur.re_content}"/></font></dt> 
            </dl></c:if>
          </div>
        </div>
        </c:forEach>
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
  <div class="CommentForm" style="margin-top:10px;">
    <h5>发表评论并打分：</h5>
    <form action="${ctx }/member/EcPdEavl.do?method=save" method="post">
    <input type="hidden"  name="goods_id" value="${af.map.goods_id }"/>
    <table cellpadding="0" cellspacing="0" style="margin-top:5px;">
      <tbody>
        <tr>
          <td valign="bottom" align="center" width="12%">评&nbsp;&nbsp;分：</td>
          <td><table id="ProComment_rdo_point" border="0">
              <tbody>
                <tr>
                  <td><input id="ProComment_rdo_point_0" type="radio" name="eval_score" value="5" checked="checked">
                    <label for="ProComment_rdo_point_0"><span class="star5"></span></label></td>
                  <td><input id="ProComment_rdo_point_1" type="radio" name="eval_score" value="4">
                    <label for="ProComment_rdo_point_1"><span class="star4"></span></label></td>
                  <td><input id="ProComment_rdo_point_2" type="radio" name="eval_score" value="3">
                    <label for="ProComment_rdo_point_2"><span class="star3"></span></label></td>
                  <td><input id="ProComment_rdo_point_3" type="radio" name="eval_score" value="2">
                    <label for="ProComment_rdo_point_3"><span class="star2"></span></label></td>
                  <td><input id="ProComment_rdo_point_4" type="radio" name="eval_score" value="1">
                    <label for="ProComment_rdo_point_4"><span class="star1"></span></label></td>
                </tr>
              </tbody>
            </table></td>
          <td>  &nbsp;&nbsp;请针对产品质量与服务进行点评</td>
        </tr>
      </tbody>
    </table>
    <p style="height: 32px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标&nbsp;&nbsp;题：
    <input type="text" name="eval_title" class="comm_input" maxlength="100" id="eval_title"/>
    </p>
    <p style="height: 32px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;优&nbsp;&nbsp;点：
    <input type="text" name="eval_con_merit" class="comm_input" maxlength="100" id="eval_con_merit"/>
    </p>
     <p style="height: 32px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缺&nbsp;&nbsp;点：
    <input type="text" name="eval_con_defect" class="comm_input" maxlength="100" id="eval_con_defect"/>
    </p>
    
    <dl>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总&nbsp;&nbsp;结：
     	<textarea name="eval_con_sumary" rows="8" cols="20" id="eval_con_sumary" style="width:60%;"></textarea>
	    <label id="info1" style="font-size: 12px;"> 还能输入</label>
	    <span id="info" style=" font-size: 14px; ">250</span>
	    <label id="info2" style=" font-size: 12px;"> 个字</label>   
    </dl> 
    <div id="ProComment_PanLogin">
      <c:if test="${not empty ecUser }">
       <c:if test="${c lt d}">
    	&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
     	 <input type="button" class="btn_comment" id="btn_submit" />
		&nbsp; &nbsp;&nbsp;
                  当前用户:${ecUser.user_name }  &nbsp; &nbsp;<input type="checkbox" name="is_anon" value="1" > 匿名评论</input>
       </c:if> <c:if test="${c ne 0 and c ge d }">
     <br/>&nbsp; &nbsp;&nbsp;  您已经评论过了
       </c:if>          
  	 </c:if>
  	 <c:if test="${d eq 0 }">
     <br/>&nbsp; &nbsp;&nbsp; 请购买产品后再评论
     </c:if>   
  	 <c:if test="${empty ecUser }">
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

	$("#eval_con_sumary").keyup(check);  
    //$("#eval_con_sumary").onpaste(check);
	$("#eval_title"	).attr("dataType", "Require").attr("msg", "请填写标题！"); 
	//$("#eval_con_merit"	).attr("dataType", "Require").attr("msg", "请填写优点！"); 
	//$("#eval_con_defect").attr("dataType", "Require").attr("msg", "请填写缺点！"); 
	//$("#eval_con_sumary").attr("dataType", "Require").attr("msg", "请填写总结！"); 
	
	$("#btn_submit").click(function(){
		var isSubmit = Validator.Validate(this.form,3);
		if (isSubmit) {
			if(c==0){
				//$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
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
</html>
