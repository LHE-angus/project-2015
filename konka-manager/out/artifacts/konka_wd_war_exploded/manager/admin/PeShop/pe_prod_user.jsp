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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
<div class="rtabcont2">
  <html-el:form action="/admin/PeShop" method="post">
    <html-el:hidden property="id" value="${af.map.id}" />
      <html-el:hidden property="method" value="save" />
       <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
        <html-el:hidden property="tree_param" value="${tree_param}" />
        <html-el:hidden property="returnUrl" />
          <html-el:hidden property="queryString" />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr id="showCagegory">
        <td nowrap="nowrap" class="title_item">用户名：</td>
        <td><html-el:text property="user_name" readonly="true" onclick="openChild(1)" styleId="user_name" style="width:250px;" maxlength="20" value="${af.map.user_name}"/>
           <html-el:hidden property="leader" styleId="leader" />
          <a href="#" id="delete_id" style="display:${not empty af.map.user_name ? '' : 'none'}" onclick="deleteCate()">&nbsp;&nbsp;删除&nbsp;&nbsp;</a> </td>
      </tr>
      <tr>
         <td nowrap="nowrap" class="title_item">负责业务类别：</td>
      <td><html-el:select property="cls_id" styleId="cls_id">
                     	<html-el:option value="">请选择...</html-el:option>
                        <c:forEach var="cur" items="${basePdClassList}">
                          <html-el:option value="${cur.cls_id}" styleId="${cur.full_name}_${cur.is_leaf}" >${fn:escapeXml(cur.tree_name)}</html-el:option>
                        </c:forEach>
                  </html-el:select></td>
      </tr>
       <tr>
        <td>&nbsp;</td>
        <td><label>
            <input class="but4" type="button" name="save" id="send" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </label></td>
      </tr>
    </table>
  </html-el:form>
 </div>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	$("#leader" ).attr("dataType" , "Require").attr("msg" , "请选择业务员！");
	$("#cls_id" ).attr("dataType" , "Require").attr("msg" , "请选择业务类别！");
	 $("#send").click(function(){
			var isSubmit = Validator.Validate(this.form, 3);
			if (isSubmit) {
				$(":button").attr("disabled", "true");
				$(":reset").attr("disabled", "true");
				this.form.submit();
			}
		});
});

function openChild(num){
	if(num == 1) {
	    var returnValue = window.showModalDialog("PeShop.do?method=listPeProdUser" + "&time=" + new Date().getTime(), window, "dialogWidth:655px;status:no;dialogHeight:438px");
	    if(returnValue != null) {
			var value = new Array();
			value = returnValue.split(",");
	        document.forms[0].user_name.value = value[0];
	        document.forms[0].leader.value = value[1];
	        
	        var temp =  document.forms[0].category_name.value;
	        if($.trim(temp).length > 0){
	        	$("#delete_id").show();
	        }
	    }
	}
}
function initStyle(){
	$("input[type='text']").css({border: "1px solid #ccc"});
	$("input[type='text'][readonly]").css({color:"#999"});
    $("input[type='text']").not("[readonly]").focus(function(){
    	$(this).addClass("row_focus");
    }).blur(function(){
    	$(this).removeClass("row_focus");
    });
}

function deleteCate(){
	$("#user_name").val('');
	$("#leader").val('');
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
