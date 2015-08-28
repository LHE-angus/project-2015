<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>
<c:if test="${af.map.trans_type eq 10 }">
移仓信息明细
</c:if>
<c:if test="${af.map.trans_type ne 10 }">
调拨信息明细
</c:if>
</title>
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
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      <c:if test="${af.map.trans_type eq 10 }">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">移仓信息查看</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">移仓编码：</td>
          <td align="left">${af.map.trans_index }
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">移出仓库：</td>
          <td align="left">${af.map.map.out_store_name }
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">移入仓库：</td>
          <td align="left">${af.map.map.in_store_name }
          </td>
        </tr>
        <tr>
          <td width="17%" nowrap="nowrap" class="title_item" align="right">型号：</td>
          <td width="83%" align="left">${af.map.goods_name }</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">移出数量（单位：台）：</td>
          <td align="left">${af.map.in_num}</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">移出时间：</td>
          <td align="left"><fmt:formatDate value="${af.map.out_date }" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left">${af.map.memo}</td>
        </tr>
       </c:if>
      <c:if test="${af.map.trans_type ne 10 }">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">调拨信息查看</td>
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
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调出仓库：</td>
          <td align="left">${af.map.map.out_store_name }
          </td>
        </tr>
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
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">调入仓库：</td>
          <td align="left">${af.map.map.in_store_name }
          </td>
        </tr>
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
          		<c:if test="${af.map.state eq 0}">未确认</c:if>
	        	<c:if test="${af.map.state eq 1}">已确认</c:if>
	        	<c:if test="${af.map.state eq 2}">拒收</c:if>
	        	<c:if test="${af.map.state eq 3}">其他</c:if>
          </td>
        </tr>
       </c:if>
        <tr>
          <td>&nbsp;</td>
          <td>
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
  </div>
</div>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
