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
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/PePdModel.do">
      <html-el:hidden property="method" value="saveProperty" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="pd_id" />
      <div align="left">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
          <tr class="oartop">
            <td colspan="6">产品属性编辑
            
          </td>
          </tr>
          <tr><td colspan="6" class="note">提示：如果下拉列表框中没有值可以选择，请到基础信息管理&gt;基础数据管理&gt;产品属性信息中相应的属性进行设置</td></tr>
          <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">产品类型</td>
            <td colspan="5" style="padding: 0"><ul style="padding-top:5px;padding-bottom:5px;">
                <li> ${fn:escapeXml(full_cls_name)} </li>
                <li> 品牌:&nbsp;康佳&nbsp;&nbsp; 型号名称:&nbsp;${fn:escapeXml(af.map.md_name)} </li>
              </ul></td>
          </tr>
          <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">产品属性赋值</td>
            <td colspan="5" style="padding: 0"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
                <tr class="oartop">
                  <th width="20%" nowrap="nowrap" align="center">属性名称</th>
                  <th width="80%" nowrap="nowrap" align="center">属性值&nbsp;&nbsp;</th>
                </tr>
                <c:set var="category_name" value="" />
                <c:forEach var="cur" items="${bpList}">
                  <html-el:hidden property="prop_ids" value="${cur.prop_id}" />
                  <c:set var="p_unit" value="${cur.prop_unit}" />
                  <c:if test="${category_name ne cur.map.category_name}">
                  <tr>
                    <td colspan="2" class="item_class" align="right" ><strong class="fb">${fn:escapeXml(cur.map.category_name eq 'default' ? '其他属性' : cur.map.category_name)}</strong></td>
                  </tr>
                  </c:if>
                  <c:set var="category_name" value="${cur.map.category_name}" />
                  <tr>
                    <td align="right"><c:if test="${cur.is_required eq 1}"> <span style="color:red;">[必填]</span></c:if>
                      ${fn:escapeXml(cur.prop_name)}:&nbsp;</td>
                    <td align="left"><c:if test="${cur.prop_type eq 0}">
                        <c:if test="${cur.prop_val_type eq 2}">
                          <html-el:text property="prop_values" styleId="prop_values_${cur.prop_id}" value="${cur.map.prop_value}"  size="10" maxlength="20" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
                        </c:if>
                        <c:if test="${cur.prop_val_type ne 2}">
                            <html-el:text property="prop_values" styleId="prop_values_${cur.prop_id}" value="${cur.map.prop_value}" style="width: 20%" styleClass="P_V" />
                            ${fn:escapeXml(p_unit)}
                        </c:if>
                      </c:if>
                      <c:if test="${cur.prop_type eq 1}">
                        <html-el:select property="prop_values" styleId="prop_values_${cur.prop_id}" value="${cur.map.prop_value}" style="width: 20%" styleClass="P_V">
                          <html-el:option value="">请选择...</html-el:option>
                          <c:forEach var="ppv" items="${cur.basePropValItemList}">
                            <html-el:option value="${ppv.prop_item_name}">${fn:escapeXml(ppv.prop_item_name)}${fn:escapeXml(p_unit)}</html-el:option>
                          </c:forEach>
                        </html-el:select>
                      </c:if>
                      <c:if test="${cur.prop_type eq 2}">
                        <html-el:hidden property="prop_values" value="" />
                        <c:forEach items="${cur.basePropValItemList}" var="ppv" varStatus="vs">
                          <c:set var="checked" value="false" />
                          <c:forEach items="${fn:split(cur.map.prop_value,',')}" var="temp">
                            <c:if test="${temp eq ppv.prop_item_name}">
                              <c:set var="checked" value="true" />
                            </c:if>
                          </c:forEach>
                          <c:if test="${checked}">
                            <input type="checkbox" id="checkbox_${cur.prop_id}" name="check_box_${cur.prop_id}" value="${ppv.prop_item_id}-${ppv.prop_item_name}" checked="checked" />
                          </c:if>
                          <c:if test="${not checked}">
                            <input type="checkbox" id="checkbox_${cur.prop_id}" name="check_box_${cur.prop_id}" value="${ppv.prop_item_id}-${ppv.prop_item_name}" />
                          </c:if>
                          <c:out value="${fn:escapeXml(ppv.prop_item_name)}" />
                          ${fn:escapeXml(p_unit)}
                          &nbsp;
                          <c:if test="${vs.count mod 5 eq 0}"><br />
                          </c:if>
                        </c:forEach>
                      </c:if></td>
                  </tr>
                </c:forEach>
              </table></td>
          </tr>
          <tr>
            <td colspan="6" height="40"  align="center"><input class="but4" type="button" name="Submit4" id="send" value="保存" />
              <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
            </td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		<c:forEach items="${bpList}" var="cur">
		<c:if test="${cur.is_required eq 1}">
			<c:if test="${cur.prop_type eq 0 || cur.prop_type eq 1}">
				$("#prop_values_${cur.prop_id}").attr("datatype","Require").attr("msg","请填写${cur.prop_name}的属性值");
			</c:if>
			<c:if test="${cur.prop_type eq 0 && not empty cur.prop_val_min && not empty cur.prop_val_max }">
			 <c:if test="${cur.prop_val_min ne 0}">
			 $("#prop_values_${cur.prop_id}").attr("datatype","Range").attr("min","${cur.prop_val_min-1}").attr("max","${cur.prop_val_max+1}").attr("msg","${cur.prop_name}的属性值必须在${cur.prop_val_min-1}和${cur.prop_val_max+1}之间");
			 </c:if>
			 <c:if test="${cur.prop_val_min eq 0}">
			 $("#prop_values_${cur.prop_id}").attr("datatype","Range").attr("min","0").attr("max","${cur.prop_val_max+1}").attr("msg","${cur.prop_name}的属性值必须在0和${cur.prop_val_max+1}之间");
			 </c:if>
			 </c:if>
			<c:if test="${cur.prop_type eq 2}">
				$("#checkbox_${cur.prop_id}").attr("datatype","Group").attr("min","1").attr("msg","请至少选择一个${cur.prop_name}的属性值");
			</c:if>
		</c:if>
		</c:forEach>
		
		 $("#send").click(function(){
				var isSubmit = Validator.Validate(this.form, 2);
				if (isSubmit) {
					$(":button").attr("disabled", "true");
					$(":reset").attr("disabled", "true");
					this.form.submit();
				}
			});
	});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
