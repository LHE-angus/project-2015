<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进货管理 &gt; 进货登记</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
-->
</style>
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;"/> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcStockBill">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <c:if test="${add}"><html-el:hidden property="saveType" value="add" /></c:if>
      <c:if test="${update}"><html-el:hidden property="saveType" value="update" />
      	<html-el:hidden property="id" value="${af.map.id}"/>
      </c:if>
      <html-el:hidden property="queryString" />
      
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr>
          <th colspan="2">进货登记</th>
        </tr>
        <tr>
          <td width="18%" class="title_item"><font color="red">*</font>进货日期：</td>
          <td>
          	<c:if test="${add}"><html-el:text property="add_date" styleId="add_date" size="9" maxlength="9" readonly="true" styleClass="webinput" value="${today}" onclick="new Calendar(2011, 2031, 0).show(this);" />
          	</c:if>
          	<c:if test="${update}">
	          	<fmt:formatDate value="${af.map.add_date }" pattern="yyyy-MM-dd"></fmt:formatDate>
	        </c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>大类：</td>
          <td>
	          <c:if test="${update}">
	          	${fn:escapeXml(af.map.konkaJxcStockBillDetailsList[0].pd_type_name)}
	          </c:if>
	          <c:if test="${add}"><html-el:select property="pd_type_id" styleId="pd_type_id" value="${af.map.konkaJxcStockBillDetailsList[0].pd_type_id}" disabled="${update}">
	              <html-el:option value="">请选择...</html-el:option>
	              <c:forEach var="cur" items="${basePdClassList}">
	                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
	              </c:forEach>
	            </html-el:select></c:if></td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>型号：</td>
          <td>
          <c:if test="${update}">
	          	${fn:escapeXml(af.map.konkaJxcStockBillDetailsList[0].pd_name)}
	      </c:if>
          <c:if test="${add}"><html-el:select property="pd_id" styleId="pd_id" style="width:155px;" value="${af.map.konkaJxcStockBillDetailsList[0].pd_id}" disabled="${update}">
          	<html-el:option value="">请选择...</html-el:option>
          </html-el:select></c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item"><font color="red">*</font>入库仓库：</td>
          <td>
          <c:if test="${update}">
	          	${fn:escapeXml(af.map.konkaJxcStockBillDetailsList[0].map.store_name)}
	      </c:if>
          <c:if test="${add}"><html-el:select property="store_id" styleId="store_id">
              <html-el:option value="">请选择...</html-el:option>
              <c:forEach var="cur" items="${storeList}">
                <html-el:option value="${cur.id}">${fn:escapeXml(cur.store_name)}</html-el:option>
              </c:forEach>
            </html-el:select></c:if></td>
        </tr>
        <c:if test="${roleId eq 30}">
        	<td class="title_item">上级部门：</td>
        	<td>
        	<c:if test="${add}">${fn:escapeXml(peDept.dept_name)}<html-el:hidden property="part_dept_id" styleId="part_dept_id" value="${peDept.dept_id}"/>
        	</c:if>
        	<c:if test="${update}">
	          	${fn:escapeXml(af.map.map.dept_name)}
	      </c:if></td>
        </c:if>
        <tr>
          <td class="title_item"><font color="red">*</font>进货数量：</td>
          <td><html-el:text property="count" styleClass="webinput" styleId="count" value="${fn:escapeXml(af.map.konkaJxcStockBillDetailsList[0].count)}" maxlength="8"/></td>
        </tr>
        <tr>
          <td class="title_item">备注：</td>
          <td>
	          <c:if test="${add}"><html-el:textarea property="remark" styleClass="webtextarea" styleId="remark"></html-el:textarea>
	          </c:if>
          	<c:if test="${update}">
	          	${fn:escapeXml(af.map.remark)}
	      	</c:if>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input class="bgButtonSave" type="button" name="save" class="bgButtonSave" value=" 保 存 " id="btn_submit"/>
            <input class="bgButtonBack" type="reset" name="reset" value="重填" id="btn_reset"/>
			<html-el:button property="back" styleId="back" value=" 返回 " onclick="history.back();" styleClass="bgButtonBack"/></td>
        </tr>
      </table>
    </html-el:form>
  </div> 
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
var f = document.forms[0];                                          
                                        
$(document).ready(function(){
	$("#add_date").attr("datatype", "Require").attr("msg", "请选择进货日期！");
	$("#pd_type_id").attr("datatype", "Require").attr("msg", "请选择产品类别！");
	$("#pd_id").attr("datatype", "Require").attr("msg", "请选择型号！");
	$("#count").attr("dataType","Integer").attr("msg","请填写进货数量，且必须为整数！");
	$("#store_id").attr("datatype", "Require").attr("msg", "请选择入库仓库！");

	<c:if test="${roleId ne 20}">
		doSelectAjax("CsAjax.do", $("#pd_type_id"), "${af.map.pd_type_id}", $("#pd_id"), "${af.map.pd_id}", "getOwnPdModleList");
	</c:if>
	<c:if test="${roleId eq 20}">
		doSelectAjax("CsAjax.do", $("#pd_type_id"), "${af.map.pd_type_id}", $("#pd_id"), "${af.map.pd_id}", "getOwnPdModleListToSyb");
	</c:if>
	
	
	$("#count").keyup(function(){//进货数量
		var count = $(this).val();//进货数量
		$("#thInfo").remove();
		if(count < 0){
			$(this).after("<span id='thInfo' title='退货：当输入的数量为负数时。'><img src='${ctx}/styles/jxc/images/th.gif' style='vertical-align: text-bottom;padding-left:5px;'/></span>");
		}
	});
	
	// 提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		$("#tip").remove();
		if (Validator.Validate(this.form, 3)){
			var remark = $("#remark").val();
			if(remark.length > 300){
				alert("备注超过300字符长度限制！");
				$("#remark").focus();
				return false;
			}
			
			$("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
	        $("#back").attr("disabled", "true");
			f.submit();
		}
	});
	 
});
//]]>--></script> 
<jsp:include page="../public_page.jsp" flush="true"/>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
