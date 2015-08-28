<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaMobileVisitPlan"  method="post">
      <html-el:hidden property="plan_id" styleId="plan_id" value="${af.map.plan_id}" />
      <html-el:hidden property="id" styleId="id" value="${af.map.id}" />
      <html-el:hidden property="R3customerStr" styleId="R3customerStr" value="${af.map.R3customerStr}" />
      <html-el:hidden property="shopStr" styleId="shopStr" value="${af.map.shopStr}" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>创建日期：</td>
          <td width="88%" align="left">
		      ${today}
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划月份：</td>
          <td width="88%" align="left">
            ${plan_begin_date}                    
          </td>
        </tr>
         <tr>
    		<td align="right" nowrap="nowrap" class="title_item">拜访客户：</td>
    		<td align="left" nowrap="nowrap">
    		  <html-el:select property="custList" multiple="true" style="width:260px;height:100px;" styleId="custList"> 
                      <c:if test="${not empty custList}">
                        <html-el:optionsCollection label="name" value="id" name="custList" />
                      </c:if>
              </html-el:select>
	    	</td>
  	   </tr>
  	   <tr>
    		<td align="right" nowrap="nowrap" class="title_item">拜访终端：</td>
    		<td align="left" nowrap="nowrap">
    		  <html-el:select property="storeList" multiple="true" style="width:260px;height:100px;" styleId="storeList"> 
                      <c:if test="${not empty storeList}">
                     <html-el:optionsCollection label="name" value="id" name="storeList" />
                      </c:if>
              </html-el:select>
	    	</td>
  	   </tr>
  	    <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划拜访客户数：</td>
          <td width="88%" align="left">
		     <label id="plan_of_access_cust" style="color: red;">${af.map.plan_of_access_cust}</label>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划拜访终端数：</td>
          <td width="88%" align="left">
		      <label id="plan_of_access_shop" style="color: red">${af.map.plan_of_access_cust}</label>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>计划拜访次数：</td>
          <td width="88%" align="left">
           <label id="plan_of_access_shop">${af.map.plan_of_access}</label>
          </td>
        </tr>
           <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">计划开拓数：</td>
          <td width="88%" align="left">
		     <label id="plan_of_dev_cust">${af.map.plan_of_dev_cust}</label>
          </td>
        </tr>
            <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">计划说明:</td>
          <td width="88%" align="left">
             <html-el:textarea property="plan_desc" readonly="true" styleId="plan_desc" styleClass="webinput" style="width:70%;height:60px;" />
          </td>
        </tr>
          <tr>
          <td align="center">
          <input type="button" class="but5" value="返 回" onclick="history.back();return false;" />
            </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>

<script type="text/javascript">
$(document).ready(function(){
	
		$("#plan_desc").textbox({
			maxLength: 500,
			onInput: function(event, status) {
				var img = '<img src="${ctx}/styles/images/tishi.gif" style="vertical-align:middle;" />';
				$("#plan_desc_note").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			}
		}).blur(function() {
			$("#plan_desc_note").slideUp("normal");
		});

		 function multi(id){
		    	$("#"+id).multiselect({
		    		noneSelectedText: '==请选择==',
		    		selectedList: 1,
		    		multiple: false,
		    		minWidth:150,
		    		click: function(event, ui){
		    	       if(ui.value != ""){
		    	    	   $("#"+id).val(ui.value);
		    	       }
		    		}
		    	}).multiselectfilter();
		    }
		    multi("year");
		    multi("month");
			});
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
