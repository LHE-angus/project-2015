<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString }</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JBasePartner">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="partner_type" styleId="partner_type" value="0" />
      <html-el:hidden property="partner_id" />
      <html-el:hidden property="returnUrl" />
      <html-el:hidden property="partner_c_id" styleId="partner_c_id" />
      <html-el:hidden property="type1" styleId="type1" value="0"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;">往来单位信息</td>
        </tr>
        <tr>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">类型：</td>
          <td width="35%" nowrap="nowrap" class="title_item" align="left">供应商</td>
          <td width="15%" nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>性质：</td>
          <td width="35%" align="left">
          	<c:choose>
            	<c:when test="${af.map.partner_obj eq 0}">个人</c:when>
                <c:when test="${af.map.partner_obj eq 1}">组织/单位</c:when>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>名称：</td>
          <td align="left">${af.map.partner_name }</td>
          <td nowrap="nowrap" class="title_item" align="right">编号：</td>
          <td align="left">${af.map.partner_sn}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">传真：</td>
          <td align="left" colspan="3">${af.map.partner_fax}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">所在城市：</td>
          <td align="left" colspan="3">
          	  ${af.map.map.PROVINCE } ${af.map.map.CITY } ${af.map.map.COUNTRY } ${af.map.map.TOWN }
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">公司地址：</td>
          <td align="left" colspan="3">${af.map.partner_addr }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left" colspan="3"><textarea name="memo" rows="3" cols="50" id="memo" disabled="disabled">${af.map.memo}</textarea></td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;">联系人信息</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">姓名：</td>
          <td align="left">${af.map.link_name }</td>
          <td nowrap="nowrap" class="title_item" align="right">移动电话：</td>
          <td align="left">${af.map.link_mobile }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">固定电话：</td>
          <td align="left">${af.map.link_tel }</td>
          <td nowrap="nowrap" class="title_item" align="right">QQ/MSN：</td>
          <td align="left">${af.map.link_qq_msn }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">证件类型：</td>
          <td align="left">
          	<c:choose>
            	<c:when test="${af.map.link_id_type eq 0}">身份证</c:when>
                <c:when test="${af.map.link_id_type eq 1}">护照</c:when>
                <c:when test="${af.map.link_id_type eq 2}">港澳通行证</c:when>
                <c:when test="${af.map.link_id_type eq 3}">台湾通行证</c:when>
            </c:choose>
          </td>
          <td nowrap="nowrap" class="title_item" align="right">证件号码：</td>
          <td align="left">${af.map.link_id }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">联系人地址：</td>
          <td align="left" colspan="3">${af.map.link_addr }</td>
        </tr>
        <tr>
          <td colspan="4" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;">收货人设置</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">姓名：</td>
          <td align="left">${af.map.consignee_name }</td>
          <td nowrap="nowrap" class="title_item" align="right">电话：</td>
          <td align="left">${af.map.consignee_tel }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">地址：</td>
          <td align="left" colspan="3">
          	${af.map.map._PROVINCE }${af.map.map._CITY }${af.map.map._COUNTRY }${af.map.map._TOWN }${af.map.consignee_street }
      	  </td>
        </tr>
        <tr style="display: none;">
          <td colspan="4"><table style="display:${(empty partner_user_info and not (af.map.partner_obj eq 1 and fn:indexOf(af.map.partner_type, '1') gt -1)) ? 'none' : ''};" id="userinfo_id" width="100%">
              <tr>
                <td colspan="4" align="right" bgcolor="#CCCCCC" style="font-weight:bold;padding-right:30px;"><c:if test="${empty partner_user_info}">
                    <label style="cursor:pointer;">
                      <input id="input_user" type="checkbox" name="input_user" />
                      &nbsp;创建客户登录帐号？</label>
                  </c:if>
                  <c:if test="${not empty partner_user_info}">客户登录帐号</c:if></td>
              </tr>
              <tbody id="user-info-form" style="display:${empty partner_user_info ? 'none' : ''};">
                <tr>
                  <td nowrap="nowrap" height="28" class="title_item"  align="right" width="15%"><span style="color:red">*</span>&nbsp;用户名：</td>
                  <td><c:if test="${empty partner_user_info}">
                      <html-el:text property="user_name" styleId="user_name" size="40" maxlength="20" />
                      &nbsp; <span style="color:#f00;display:none;" id="user_name_exist_error">该登录名已被使用，请重新输入！</span><span style="color:#f00;display:none;" id="user_name_erro">登录名不能含空白字符！</span> </c:if>
                    <c:if test="${not empty partner_user_info}">${partner_user_info.user_name}
                      <html-el:hidden property="partner_user_id" value="${partner_user_info.id}" />
                    </c:if></td>
                </tr>
                <tr>
                  <td nowrap="nowrap" height="28" class="title_item"  align="right"><span style="color:red">*</span> 账户${empty partner_user_info ? '' : '新'}密码：</td>
                  <td><html-el:password property="pass_word" styleId="pass_word" size="12" maxlength="16" />
                    &nbsp;
                    <input type="button" name="initDefaultPassword" onClick="this.form.pass_word.value='888888';this.form.repeat.value='888888';" value=" 使用默认密码：888888 " /></td>
                </tr>
                <tr>
                  <td nowrap="nowrap" class="title_item"  align="right">重复密码：</td>
                  <td><html-el:password property="repeat" styleId="repeat" maxlength="16" size="12" /></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
        <tr style="display:none;">
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>是否删除：</td>
          <td align="left" colspan="3"><html-el:select property="is_del" styleId="is_del">
              <html-el:option value="0" >否</html-el:option>
              <html-el:option value="1">是</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td colspan="4" align="center">
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
