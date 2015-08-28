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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
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
  	<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">购物券名称：</td>
          <td width="88%" align="left">
          	${af.map.title}
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">申请人：</td>
          <td width="88%" align="left">
          	${af.map.user_name}
          </td>
        </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">是否需要加密：</td>
          <td width="88%" align="left">
          <c:if test="${af.map.has_pwd eq 0}">不需要</c:if>
          <c:if test="${af.map.has_pwd eq 1}">需要</c:if>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">申请数量：</td>
          <td width="88%" align="left">${af.map.apply_num}</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">面额：</td>
          <td width="88%" align="left">${af.map.price}</td>
        </tr>
          <tr>
          <td width="12%" nowrap="nowrap" class="title_item"  align="right">有效开始时间：</td>
          <td width="88%" align="left">
          <fmt:formatDate value="${af.map.start_date}" pattern="yyyy-MM-dd HH:mm:ss" />
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item"  align="right">有效截止时间：</td>
          <td width="88%" align="left">
          <fmt:formatDate value="${af.map.effective_date}" pattern="yyyy-MM-dd HH:mm:ss" />
          </td>
        </tr>
        <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">可使用产品类别：</td>
        <td align="left" width="88%">
        ${af.map.prod_name}
       </td>
      </tr>
       <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">可使用属性类别：</td>  
        <td align="left" width="88%">
        ${af.map.goods_types}
       </td>
      </tr>
       <tr>
        <td width="12%" nowrap="nowrap" class="title_item" align="right">可使用商品：</td>  
        <td align="left" width="88%">
        ${af.map.goods} 
       </td>
      </tr>
       <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">所属系统：</td>
          <td width="88%" align="left">
          <c:if test="${af.map.own_sys eq 2}">触网</c:if>
          </td>
        </tr>
        <tr>  
          <td width="12%" nowrap="nowrap" class="title_item" align="right">所属总部/分公司：</td>
          <td width="88%" align="left">
           <c:if test="${af.map.plat_sys eq 0}">总部</c:if>
           <c:if test="${af.map.plat_sys eq 1}">分公司</c:if>
          </td>
        </tr>  
      <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">满多少元：</td> 
          <td width="88%" align="left">${af.map.m_menoy}</td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">满多少数量：</td> 
          <td width="88%" align="left">${af.map.m_num}</td>
        </tr>
        <tr>
          		<td class="title_item" nowrap="nowrap">是否可以叠加使用：</td>
          		<td>
          		<c:if test="${af.map.is_other eq 0}">可以</c:if> 
          		<c:if test="${af.map.is_other eq 1}">不可以</c:if>
          		</td>
          	</tr> 
       <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left">
		${af.map.memo}
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="center" colspan="2">审核记录</td> 
        </tr>
         <tr>
            <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass"> 
                <tr>
                  <td class="title_item" width="5%" style="text-align:center;" nowrap="nowrap">序号</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">登录名</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">操作时间</td>
                  <td class="title_item" width="18%" style="text-align:center;" nowrap="nowrap">审核结果</td>
                  <td class="title_item" style="text-align:center;" nowrap="nowrap">意见</td>
                </tr>
                <c:forEach items="${eaList}" var="cur" varStatus="st">
                  <tr>
                    <td align="center" >${st.count}</td>
                    <td align="center">${cur.opr_user_real_name } </td>
                    <td align="center"><fmt:formatDate value="${cur.oper_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    <td align="center">     
				       <c:if test="${cur.state eq 0 }">审核通过</c:if>
					   <c:if test="${cur.state eq 1 }">审核不通过</c:if>
				         </td>
                    <td align="center">${cur.remark}</td>
                  </tr>
                </c:forEach>
              </table></td>
         </tr>
		<tr>
	          <td align="center" colspan="2">
				<input class="but5" type="button" name="Submit5" value="返回" id="btn_back" onclick="history.back()" />
	          </td>
	    </tr>		
  	</table>
  </div>
  
</div>
</body>
</html>