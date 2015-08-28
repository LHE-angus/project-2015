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
<style type="text/css">
/**ul {width:100%;}
ul li {width:200px; float:left; overflow:hidden;}*/
#fact_str {width:100%;}
#fact_str li {width:200px;float:left;overflow:hidden;}
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/zmd/KonkaXxZmdClerk"  styleClass="form_cust">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="zmd_ids" styleId="zmd_ids" value="" />
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td colspan="4" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">分配${role_id_1 eq 390 ?'业务员':'专卖店管理员'}</td>
        </tr>
        <tr>
          <td class="title_item" align="right" width="10%">业务员：</td>
          <td colspan="3"><html-el:hidden property="user_id" value="${af.map.user_id}" />
            ${af.map.real_name } </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">选择专卖店：</td>
          <td colspan="3"><ul id="fact_str">
              <c:forEach var="cur" items="${kxzList}" varStatus="vs">
                <li title="${cur.zmd_sn}">
                  <html-el:checkbox property="zmd_id_${cur.zmd_id}" styleId="zmd_id_${cur.zmd_id}" styleClass="zmd_values" />
                  &nbsp; <c:out value="${fnx:abbreviate(cur.zmd_sn, 2 * 5, '...')}" /></li>
              </c:forEach>
            </ul>
            <div style="clear:both;"></div>
            <br />
            <html-el:checkbox property="check_all" styleId="check_all" />
            全选/反选 </td>
        </tr>
        <tr>
          <td colspan="4" height="40"  align="center"><input class="but4" type="submit" name="submit" value="保存 " id="submit" />
            <input class="but3" type="reset"  value="重填 " id="reset" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#check_all").click(function(){
		if (this.checked) {
			$(".zmd_values").attr("checked", "checked");
		} else {
			$(".zmd_values").removeAttr("checked");
		}
	});

	$(".form_cust").submit(function(){
		var isSubmit = Validator.Validate(this, 3);
		if (isSubmit) {
			var zmd_ids = [];
			$(".zmd_values:checked").each(function (){
				zmd_ids[zmd_ids.length] = this.id.replace("zmd_id_", "");
			});

			if(zmd_ids.length == 0){
				alert("请选择专卖店！");
				return false;
			}
			
			$("#zmd_ids").val(zmd_ids.join(","));
			
			$(":submit").attr("disabled", "true");
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
		}
		return isSubmit;
	});
	
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
