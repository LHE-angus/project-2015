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
    <html-el:form >
      <html-el:hidden property="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">专卖店编号：</td>
          <td><strong class="fb"><span style="color: blue;">${fn:escapeXml(zmd_sn)}</span></strong></td>
        </tr>
        <c:forEach var="cur_08" items="${baseTypesList80000}" varStatus="vs">
          <tr>
            <td width="15%" nowrap="nowrap" class="title_item" align="right">${cur_08.type_name}：</td>
            <td width="85%"> 返佣比例：
              <c:if test="${list[vs.index] ne 1}">
                <html-el:text property="reward_ratio_${cur_08.type_id}" styleId="reward_ratio_${cur_08.type_id}" size="10"/>
                &nbsp;
                <html-el:checkbox property="is_enabled_${cur_08.type_id}" styleId="is_enabled_${cur_08.type_id}" />
                启用
                &nbsp;
                <html-el:checkbox property="is_locked_${cur_08.type_id}"  styleId="is_locked_${cur_08.type_id}" />
                锁定
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="but4" type="button" name="set_${cur_08.type_id}" value="保存" id="send_${cur_08.type_id}" />
              </c:if>
              <c:if test="${list[vs.index] eq 1}">
                <html-el:text property="reward_ratio_${cur_08.type_id}" styleId="reward_ratio_${cur_08.type_id}" readonly="true" size="10"/>
                &nbsp;
                <html-el:checkbox property="is_enabled_${cur_08.type_id}" styleId="is_enabled_${cur_08.type_id}" disabled="true"/>
                启用
                &nbsp;
                <html-el:checkbox property="is_locked_${cur_08.type_id}"  styleId="is_locked_${cur_08.type_id}" disabled="true"/>
                锁定
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="but5" type="button" name="locak_${cur_08.type_id}" value="解锁" id="locak_${cur_08.type_id}" />
              </c:if></td>
          </tr>
        </c:forEach>
      <tr>
        <td class="title_item" width="99%" colspan="2" align="right"><p style="color: red;">注：返佣比例为百分比；承包专卖店盈利设计：常规零售返佣（8%）+高买部分返佣（高卖部分83%） +工程机返佣（建议按照1%—3%）+规模激励（年零售总额的1%）。</p></td>
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
	
	$("input[id^=send_]").click(function(){
	 	var	but= confirm("是否保存？");
	 	if(but){ 	
		$("#tip_msg").remove();
		var $this = $(this);
		var type_id = this.id.replace("send_", "");
		var reward_ratio = $("#reward_ratio_" + type_id).val();

		if ($.trim(reward_ratio).length == 0) {
			$(this).after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">返佣比例不能为空！</span>");
			return false;
		}

		if($.trim(reward_ratio)>100){
			$(this).after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">返佣比例不能超过100%！</span>");
			return false;
		}
		var param = {
			type_id : type_id,
			reward_ratio : reward_ratio,
			is_enabled : $("#is_enabled_" + type_id)[0].checked ? 1 : 0,
			is_locked : $("#is_locked_" + type_id)[0].checked ? 1 : 0,
			zmd_id : "${af.map.zmd_id}"
		};
		
		$.post("${ctx}/manager/zmd/KonkaXxZmdRewardSet.do?method=ajaxSaveRewardType", param, function(ret_flag) {
			$("#tip_msg").remove();
			if ("1" == ret_flag) {
				$this.after("<span id=\"tip_msg\" style=\"color:green;margin-left:5px;\">恭喜您，保存成功！</span>");
				setTimeout("reload()",500);
			} else {
				$this.after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">很抱歉，服务器内部错误，请联系管理员！</span>");
			}
		});
	 	}
	 	else{
		 	return false;
		 	}
	});

	$("input[id^=locak_]").click(function(){
	 	var	but= confirm("是否解锁？");
	 	if(but){
		$("#tip_msg").remove();
		var $this = $(this);
		var type_id = this.id.replace("locak_", "");
		var param = {
			type_id : type_id,
			zmd_id : "${af.map.zmd_id}"
		};
		$.post("${ctx}/manager/zmd/KonkaXxZmdRewardSet.do?method=ajaxOpenRewardType", param, function(ret_flag) {
			$("#tip_msg").remove();
			if ("1" == ret_flag) {
				$this.after("<span id=\"tip_msg\" style=\"color:green;margin-left:5px;\">恭喜您，解锁成功！</span>");
				setTimeout("reload()",500);
			} else {
				$this.after("<span id=\"tip_msg\" style=\"color:#F00;margin-left:5px;\">很抱歉，服务器内部错误，请联系管理员！</span>");
			}
		});
	 	}
	 	else{
	 		return false;
		 	}
	});

});

function reload(){
	window.location.href="${ctx}/manager/zmd/KonkaXxZmdRewardSet.do?method=edit&zmd_sn=${af.map.zmd_sn}&zmd_id=${af.map.zmd_id}&time="+ new Date();
}
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
