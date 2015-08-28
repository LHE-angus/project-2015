<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/commons/styles/message/message.css" rel="stylesheet" type="text/css" />
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
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont2">
    <html-el:form action="admin/TaskContent" method="post">
      <html-el:hidden property="method" value="view" styleId="method"  />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="participant_id" styleId="participant_id" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id" />
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <tr >
        	<td class="title_item" width="10%">部门：</td>
        	<td>
	            ${af.map.map.dept_name  }
        	</td>
        </tr>
        <tr>
          <td class="title_item" width="10%">任务类别：</td>
          <td width="40%">
	           <c:if test="${af.map.task_p_type =='ywy' }">业务员</c:if>
	           <c:if test="${af.map.task_p_type =='customer' }">客户</c:if>
	           <c:if test="${empty af.map.task_p_type }">-</c:if>
          </td>
        </tr>
        
        <tr name="ywy_tr">
        	<td class="title_item" width="10%">岗位ID：</td>
        	<td>
        		${ af.map.map.ywy_id  }
        	</td>
        </tr>
		<tr name="ywy_tr">
        	<td class="title_item" width="10%">业务员名称：</td>
        	<td>
        		${af.map.map.ywy_name }
        	</td>
        </tr>
        <tr name="customer_tr">
        	<td class="title_item" width="10%">客户R3编码：</td>
        	<td>
        		<c:if test="${empty af.map.map.customer_code }">-</c:if>
        		${ af.map.map.customer_code }
        	</td>
        </tr>
		<tr name="customer_tr">
        	<td class="title_item" width="10%">客户名称：</td>
        	<td>
        		<c:if test="${empty af.map.map.customer_name }">-</c:if>
        		${af.map.map.customer_name }
        	</td>
        </tr> 
		<tr>
          <td class="title_item" width="10%">任务系数：</td>
          <td width="40%">
          	${af.map.task_xs  }
           </td>
           </tr>
		<tr>
          <td class="title_item" width="10%">任务额度：</td>
          <td width="40%">
          	${af.map.task_ed  }
          </td>
        </tr>
		<tr>
          <td class="title_item" width="10%">年度：</td>
          <td width="40%">
          	${af.map.task_year  }
           </td>
           </tr> 
		<tr>
          <td class="title_item" width="10%">月份：</td>
          <td width="40%">
          	${af.map.task_month  }
          </td>
        </tr>
        <tr> 
        <td class="title_item" width="10%">描述：</td>
        <td>
        	${af.map.task_desc  }
          </td>
        </tr>
        
       	<tr>
       		<td nowrap="nowrap" class="title_item" colspan="2">
	       	<input class="but5" type="button" value="返回" onclick="javascript:history.go(-1)" />
       		</td>
       	</tr>
      </table> 
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>


<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
    $(document).ready(function(){
    	//
	}); 
    
//]]></script>
</body>
</html>
