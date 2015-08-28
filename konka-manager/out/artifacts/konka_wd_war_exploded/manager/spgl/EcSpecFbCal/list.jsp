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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcSpecFbCal">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          	&nbsp;&nbsp;主队球队名  ：
          	 <html-el:select property="a_team_id" styleId="a_team_id" style="width:120px;">
      		        <html-el:option value="">请选择</html-el:option>
      				<c:forEach items="${teamList}" var="cur">
      					<html-el:option value="${cur.id}">${cur.team_name}(${cur.group_name})</html-el:option>
      				</c:forEach>
        	 </html-el:select>
          	&nbsp;&nbsp;客队球队名  ：
          	 <html-el:select property="b_team_id" styleId="b_team_id" style="width:120px;">
       				<html-el:option value="">请选择</html-el:option>
       				<c:forEach items="${teamList}" var="cur">
       					<html-el:option value="${cur.id}">${cur.team_name}(${cur.group_name})</html-el:option>
       				</c:forEach>
	         </html-el:select>	 
	         &nbsp;&nbsp;开赛时间：
          	<html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					&nbsp;&nbsp;<input type="button" class="but2" value="新 增" onclick="location.href='EcSpecFbCal.do?method=add&mod_id=${af.map.mod_id}';" />
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="15%" nowrap="nowrap" align="center">主队球队</td>
        <td width="15%" nowrap="nowrap" align="center">客队球队</td>
         <td width="15%" nowrap="nowrap" align="center">比赛时间</td>
        <td width="20%" nowrap="nowrap" align="center">比赛输赢</td>
         <td width="6%" nowrap="nowrap" align="center">主队得分</td>
         <td width="6%" nowrap="nowrap" align="center">客队得分</td>
         <td width="6%" nowrap="nowrap" align="center">创建时间</td>
         <td width="6%" nowrap="nowrap" align="center">创建人</td>
         <td width="6%" nowrap="nowrap" align="center">排序号</td>
          <td width="6%" nowrap="nowrap" align="center">状态</td>
          <td width="6%" nowrap="nowrap" align="center">得分维护时间</td>
          <td width="6%" nowrap="nowrap" align="center">得分维护人</td>
          <td width="6%" nowrap="nowrap" align="center">场次说明</td>
          <td width="6%" nowrap="nowrap" align="center">主队积分</td>
          <td width="6%" nowrap="nowrap" align="center">客户积分</td>
          <td width="6%" nowrap="nowrap" align="center">比赛场地名称</td>
          <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.a_team}</td>
          <td align="left" nowrap="nowrap">${cur.map.b_team}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.play_time}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">
            <c:if test="${cur.play_status eq 0}">赢</c:if>
		     <c:if test="${cur.play_status eq 1}">平</c:if>
		     <c:if test="${cur.play_status eq 2}">输</c:if>
         </td>
          <td align="right" nowrap="nowrap">${cur.a_team_goal}</td>
          <td align="right" nowrap="nowrap">${cur.b_team_goal}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">${cur.map.add_user}</td>
          <td align="right" nowrap="nowrap">${cur.order_sort_num}</td>
          <td align="left" nowrap="nowrap"> 
            <c:if test="${cur.is_del eq 0}">
		      <label>启用</label>
		    </c:if>
		    <c:if test="${cur.is_del eq 1}">
		      <label>停用</label>
		    </c:if>
		  </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.gift_add_date}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">${cur.map.gift_user}</td>
          <td align="left" nowrap="nowrap">${cur.match_desc}</td>
          <td align="right" nowrap="nowrap">${cur.a_team_result}</td>
          <td align="right" nowrap="nowrap">${cur.b_team_result}</td>
          <td align="left" nowrap="nowrap">${cur.match_addr}</td>
          <td align="center" nowrap="nowrap"> 
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcSpecFbCal.do','edit' ,'match_id=${cur.match_id}&' + $('#bottomPageForm').serialize())">修改</span> 
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'EcSpecFbCal.do','delete' ,'match_id=${cur.match_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">删除</span>
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcSpecFbCal.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("a_team_id", "${af.map.a_team_id}");
								pager.addHiddenInputs("b_team_id", "${af.map.b_team_id}");
								pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
								pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
