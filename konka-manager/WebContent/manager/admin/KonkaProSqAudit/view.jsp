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
<c:if test="${!empty is_add}">
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
</c:if>
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
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr>
          <td class="title_item" align="right">文件编号：</td>
          <td >
               ${af.map.file_no}
          </td>
          <td class="title_item" align="right">投标人名称：</td>
           <td>
           		${fn:escapeXml(af.map.tbr_name)}
           </td>
        </tr>
        <tr>
          <td class="title_item" align="right">分公司：</td>
          <td>
             ${dept_name}
          </td>
           <td class="title_item" align="right">区域：</td>
          <td>
              <c:if test="${af.map.area_id eq 10}">华东</c:if>
          	  <c:if test="${af.map.area_id eq 20}">山东</c:if>
          	  <c:if test="${af.map.area_id eq 30}">东北</c:if>
          	  <c:if test="${af.map.area_id eq 40}">华北</c:if>
              <c:if test="${af.map.area_id eq 50}">华南</c:if>
          	  <c:if test="${af.map.area_id eq 60}">西南</c:if>
          	  <c:if test="${af.map.area_id eq 70}">华中</c:if>
          	  <c:if test="${af.map.area_id eq 80}">西北</c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">负责人：</td>
          <td width="50%" >
             ${af.map.fzr_name}
            </td>
            <td class="title_item" nowrap="nowrap" align="right">负责人电话：</td>
          <td width="50%" >
             ${af.map.fzr_tel}
            </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">分公司联系人：</td>
          <td width="50%" >
           ${af.map.fgs_name}
            </td>
            <td class="title_item" nowrap="nowrap" align="right">分公司联系人电话：</td>
          <td width="50%" >
            ${af.map.fgs_tel}
            </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">项目名称：</td>
          <td width="50%" >
           ${af.map.pro_name}
            </td>
            <td class="title_item" nowrap="nowrap" align="right">招标时间：</td>
          <td width="50%" >
            <fmt:formatDate value="${af.map.zb_date}" pattern="yyyy-MM-dd HH:mm" />
            </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">报备人：</td>
          <td width="50%" >
           ${af.map.add_user_name}
            </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">项目进展情况备注：</td>
          <td width="88%" colspan="3">
          	${af.map.remark}
          </td>
        </tr>
        <c:if test="${af.map.is_support eq 0}">
        <tr>
        <td class="title_item" nowrap="nowrap" align="right">是否需要总部支持：</td>
         <td align="left" colspan="3">是</td> 
        <tr>
        <tr>
        <td class="title_item" nowrap="nowrap" align="right">支持内容：</td>
          <td width="88%" colspan="3">
          	${af.map.support_content}
          </td>
        </tr>
        </c:if>
        <c:if test="${af.map.pro_state ne 0}">
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">所需设备及其他要求：</td>
          <td width="88%" colspan="3">
          	${af.map.sb_remark}
          </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">支持及其他说明：</td>
          <td width="88%" colspan="3">
          	${af.map.zc_remark}
          </td>
        </tr>
        <tr> <td class="title_item" nowrap="nowrap" align="center" colspan="4">竞品对手信息：</td></tr>
        <tr>
        	<td colspan="4" width="100%">
	        	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
	              	<tr class="tabtt1" style="height: 28px;">
	              	  <td width="15%" align="center" nowrap="nowrap">品牌</td>
	              	  <td width="10%" align="center" nowrap="nowrap">型号</td>
	              	  <td width="10%" align="center" nowrap="nowrap">参数</td>
	              	  <td width="10%" align="center" nowrap="nowrap">市场价</td>
	              	  <td width="10%" align="center" nowrap="nowrap">本地投标价</td>
	              	  <td width="10%" align="center" nowrap="nowrap">备注</td>
	              	</tr>
	              	<c:forEach items="${fighterInfoList}" var="cur">
	              	<tr >
	              	  	<td><html-el:text property="brand_name" value="${cur.brand_name}" style="width:200px;" styleId="brand_name_${_cur.id}" maxlength="30" readonly="true" /></td>
	              	  	<td><html-el:text property="md_name" value="${cur.md_name}" style="width:100px;" styleId="md_name_${_cur.id}" maxlength="30" readonly="true" /></td>
	              	  	<td><html-el:text property="param" value="${cur.param}" style="width:100px;" maxlength="20" styleId="param_${_cur.id}"   readonly="true" /></td>
	              	  	<td><html-el:text property="sail_money" value="${cur.sail_money}" style="width:80px;" styleId="sail_money_${_cur.id}" readonly="true" maxlength="10" onchange="javascript:setOnlyDouble(this);" /></td>
	              	  	<td><html-el:text property="bd_tb_price" value="${cur.bd_tb_price}" style="width:80px;" styleId="bd_tb_price_${_cur.id}" readonly="true" maxlength="10" onchange="javascript:setOnlyDouble(this);" /></td>
	              	  	<td><html-el:text property="f_remark" value="${cur.f_remark}" style="width:200px;" maxlength="20" styleId="f_remark_${_cur.id}" readonly="true" /></td>
	              	</tr>
	              	</c:forEach>
	              	</table>
	         </td>
        </tr>
        </c:if>
        
        <tr>
      	<td colspan="4">审核信息</td>
      </tr>
      <tr>
        <td colspan="4">
        　　　　<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
              	<tr>
              	  <td width="10%" align="center" nowrap="nowrap">序号</td>
              	  <td width="10%" align="center" nowrap="nowrap">审核人</td>
              	  <td width="8%" align="center" nowrap="nowrap">审核状态</td>
              	  <td width="10%" align="center" nowrap="nowrap">审核时间</td>
              	  <td align="center">审核意见</td>
              	  <td width="10%" align="center" nowrap="nowrap">下一个审核角色</td>
              	</tr>
              	<c:forEach items="${kHis}" var="cur" varStatus="vs">
	                 <tr>
	              	  	<td align="center" nowrap="nowrap">${vs.count}</td>
	              	  	<td align="left" nowrap="nowrap"><c:if test="${!empty cur.audit_user_name and cur.audit_user_name ne '--'}">${cur.audit_user_name}</c:if>
	              	  	<c:if test="${empty cur.audit_user_name or cur.audit_user_name eq '--'}">${af.map.add_user_name}（报备人）</c:if>
	              	  	</td>
	              	  	<td align="left" nowrap="nowrap">${cur.audit_status_desc}</td>
	              	  	<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd" /></td>
	              	  	<td align="left" nowrap="nowrap">${cur.audit_option}</td>
	              	  	<td align="left" nowrap="nowrap">${cur.audit_next_node_name}<c:if test="${empty cur.audit_next_node_name}">无</c:if></td>
	              	  </tr>
              	</c:forEach>
            </table>	
        </td>
      </tr>
      
     
        <tr>
          <td align="center" colspan="4" ><label>
            <input class="but5" type="button"  id="btn_back" value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
