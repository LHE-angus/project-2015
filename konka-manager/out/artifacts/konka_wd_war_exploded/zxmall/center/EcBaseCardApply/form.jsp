<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>康佳</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/> 

<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 会员申请</div>
    <div class="zxmalltab3">
        <%@ include file="/commons/pages/messages.jsp" %>
          <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 申请记录</p>
         <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr class="tr1">
          <td width="20%" nowrap="nowrap" align="center">申请日期</td>
          <td width="10%" nowrap="nowrap" align="center">申请数量</td>
          <td width="10%" nowrap="nowrap" align="center">状态</td>
          </tr>
           <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="30">
              <td align="center" nowrap="nowrap"><fmt:formatDate  value="${cur.apply_date}" pattern="yyyy-MM-dd"/></td>
              <td align="center" nowrap="nowrap">${cur.apply_num}</td>
              <td align="center" nowrap="nowrap"><c:if test="${cur.info_state eq 0}">等待受理</c:if>
              <c:if test="${cur.info_state eq 1}">受理通过</c:if>
              <c:if test="${cur.info_state eq 2}">受理不通过</c:if>
               </td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList}">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="5"> 暂无申请记录 </td>
            </tr>
          </c:if>
        </tbody>
        </table>
        <html-el:form action="/center/EcBaseCardApply" enctype="multipart/form-data">
        <html-el:hidden property="method" styleId="method" value="save" />
        <html-el:hidden property="id"/>
        <p style="margin-left:20px;margin-top:15px;font-size:16px;"> 申请信息</p>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="zxmall_form_table1">
          <tr>
            <td width="20%" align="right"> 申请人姓名：</td>
            <td width="80%" align="left">${af.map.real_name}
            </td>
          </tr>
          <tr>
            <td width="20%" align="right"> 申请用户：</td>
            <td width="80%" align="left">${af.map.user_name}
            </td>
          </tr>
          <tr>
            <td width="20%" align="right"> 申请人手机：</td>
            <td width="80%" align="left"><html-el:text property="apply_tel" maxlength="11" styleId="apply_tel" styleClass="input_txt" style="width:160px;" ></html-el:text>
            </td>
          </tr>
           <tr>
            <td width="20%" align="right"> 申请会员数量：</td>
            <td width="80%" align="left"><html-el:text property="apply_num" maxlength="3" styleId="apply_num" styleClass="input_txt" style="width:160px;" ></html-el:text>
            </td>
          </tr>
          <tr>
            <td align="right">备注：</td>
            <td align="left"><textarea name="memo" rows="8" cols="20" id="memo" style="width:60%;"></textarea>
            </td>
          </tr>
         </table>         
         <p style="margin-left:200px;margin-top:15px;font-size:16px;">  <input class="inputbtn" type="button" name="Submit4" id="btn_submit" value="申请" /></p>            
      </html-el:form>
    </div>
  </div>
  <div class="clear"></div>
</div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
   
		$("input[name='apply_num']").attr("datatype", "Require").attr("msg", "请输入申请数量！"); 
		$("input[name='apply_tel']").attr("dataType", "Mobile").attr("Require", "true").attr("msg", "手机格式不正确！");  
		$("#memo" ).attr({"dataType":"Limit","max":"400","msg":"备注应少于400个字！"});
		$("input[name='apply_num']" ).focus(function(){setOnlyInt(this);});
		
		$("#btn_submit").click(function(){ 

			var num = $("input[name='apply_num']").val();
			if(parseInt(num) == 0){
				alert("申请数量不能为0，请重新输入");
				return;
			}
			if(parseInt(num) > 100){
				alert("最大申请数量为100，请重新输入");
				return;
			}
			var isSubmit = Validator.Validate(this.form,3);
			if (isSubmit) {
				 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
				 this.form.submit();
			}
		});
		
});

function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//]]></script>
</html>
