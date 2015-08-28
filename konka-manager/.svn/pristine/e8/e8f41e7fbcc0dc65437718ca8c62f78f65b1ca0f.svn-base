<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加调拨信息</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
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
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JxcOutInDetail">
      <html-el:hidden property="method" value="saveConfirm"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="type" value="${af.map.trans_type }" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      	<tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">调拨信息确认</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调拨编码：</td>
          <td align="left">${af.map.trans_index }
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调出客户：</td>
          <td align="left">${af.map.map.out_customer_name }
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调出客户R3编码：</td>
          <td align="left">${af.map.out_r3_code }
          </td>
        </tr>
        <c:if test="${af.map.trans_type eq 0 }">
         	<tr>
	          <td nowrap="nowrap" class="title_item" align="right">调出仓库：</td>
	          <td align="left">
	          	<html-el:select property="out_store_id" styleId="out_store_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	          </td>
	        </tr>
        </c:if>
        <c:if test="${af.map.trans_type eq 1 }">
	        <tr>
	          <td nowrap="nowrap" class="title_item" align="right">调出仓库：</td>
	          <td align="left">${af.map.map.out_store_name }
	          </td>
	        </tr>
        </c:if>
        <tr>
          <td width="17%" nowrap="nowrap" class="title_item" align="right">型号：</td>
          <td width="83%" align="left">${af.map.goods_name }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调入客户：</td>
          <td align="left">${af.map.map.in_customer_name }
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调入客户R3编码：</td>
          <td align="left">${af.map.in_r3_code }
          </td>
        </tr>
        <c:if test="${af.map.trans_type eq 0 }">
        	 <tr>
	          <td nowrap="nowrap" class="title_item" align="right">调入仓库：</td>
	          <td align="left">${af.map.map.in_store_name }
	          </td>
	        </tr>
        </c:if>
        <c:if test="${af.map.trans_type eq 1 }">
	        <tr>
	          <td nowrap="nowrap" class="title_item" align="right">调入仓库：</td>
	          <td align="left">
	          	<html-el:select property="in_store_id" styleId="in_store_id">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
	          </td>
	        </tr>
        </c:if>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调拨数量（单位：台）：</td>
          <td align="left">${af.map.in_num}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调拨时间：</td>
          <td align="left"><fmt:formatDate value="${af.map.out_date }" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left">${af.map.memo}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">确认状态：</td>
          <td align="left">
          		<html-el:select property="state" styleId="state">
	        		<html-el:option value="">请选择</html-el:option>
	          			<html-el:option value="1">确认</html-el:option>
	          			<html-el:option value="2">拒收</html-el:option>
<%-- 	          			<html-el:option value="3">其他</html-el:option> --%>
	            </html-el:select>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#state").attr("datatype", "Require").attr("msg", "请选择！");
	<c:if test="${af.map.trans_type eq 1}">
		$("#in_store_id").multiselect({
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160
		}).multiselectfilter({label:"<span>搜索：</span>"});
	</c:if>
	<c:if test="${af.map.trans_type eq 0}">
		$("#out_store_id").multiselect({
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160
		}).multiselectfilter({label:"<span>搜索：</span>"});
	</c:if>	
	$("#state").multiselect({
		noneSelectedText: '<span >=请选择=</span>',
		selectedList: 1,
		multiple: false,
		minWidth:160,
		click:function(event, ui){
			if(ui.value == 1){
				<c:if test="${af.map.trans_type eq 1}">
					$("#in_store_id").attr("datatype", "Require").attr("msg", "请选择！");
				</c:if>
				<c:if test="${af.map.trans_type eq 0}">
					$("#out_store_id").attr("datatype", "Require").attr("msg", "请选择！");
				</c:if>
			} else {
				<c:if test="${af.map.trans_type eq 1}">
					$("#in_store_id").removeAttr("datatype");
				</c:if>
				<c:if test="${af.map.trans_type eq 0}">
					$("#out_store_id").removeAttr("datatype");
				</c:if>
			}
		}
	}).multiselectfilter({label:"<span>搜索：</span>"});
	
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
			this.form.submit();
		}
	});
});
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
