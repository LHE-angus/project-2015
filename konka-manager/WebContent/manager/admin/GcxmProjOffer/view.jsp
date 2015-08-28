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
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" /> 
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
       <div style="float:right;">
       	<label >
           <input  type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
        </label>
  	      </div>
	         <ul id="tabs">
			    <li><a href="#" name="tab1">项目上报</a></li>
			    <li><a href="#" name="tab2">项目报价</a></li>
			    <li><a href="#" name="tab3">项目结果</a></li>
			</ul>
      <div id="content"> 
			    <div id="tab1"> 
   
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
      		<td class="title_item" align="right" width="15%">项目类型：</td>
      		<td width="35%">
      		<c:if test="${af.map.proj_type eq 1}">政府采购</c:if>
      		<c:if test="${af.map.proj_type eq 2}">酒店采购</c:if>
      		<c:if test="${af.map.proj_type eq 3}">企业采购</c:if>
      		<c:if test="${af.map.proj_type eq 4}">其他</c:if>
      		</td>
             <td class="title_item" align="right" width="15%">项目编号：</td>
             <td width="35%">
             ${af.map.proj_code}
             </td>
      	</tr> 
      	<tr>
      		<td class="title_item" align="right">项目名称：</td>
      		<td colspan="3">
      		<c:choose>
        		<c:when test="${fn:length(af.map.proj_name)>30}">
        		<c:out value="${fn:substring(af.map.proj_name,0,30)}..."></c:out>
        		</c:when>
        		<c:otherwise>
        		<c:out value="${af.map.proj_name}"></c:out>
        		</c:otherwise>
        	</c:choose>
      		</td>
      	</tr>
        <tr>
          <td class="title_item" align="right">&nbsp;分公司&nbsp;：</td>
          <td>
          ${af.map.fgs_dept_name}
          </td>
           <td class="title_item" align="right">审核状态：</td>
          <td width="35%">
          <c:if test="${empty af.map.id}">未提交</c:if>
          <c:if test="${not empty af.map.id}">
              <c:if test="${af.map.info_state eq -1}">未提交</c:if>
              <c:if test="${af.map.info_state eq 0}">审核中</c:if>
              <c:if test="${af.map.info_state eq 1}">审核结束</c:if>
          </c:if>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;<span id="s_cg">采购人</span>&nbsp;：</td>
          <td width="50%" >
          ${af.map.buyer}
          </td>
          <td class="title_item" nowrap="nowrap" align="right"><span id="s_dh">采购人电话：</span></td>
          <td width="50%" >
           ${af.map.buyer_tel}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span id="s_rq">报价日期：</span></td>
          <td width="50%" >
          <fmt:formatDate  value="${af.map.offer_date}" pattern="yyyy-MM-dd" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">交货日期：</td>
          <td width="50%" >
          <fmt:formatDate  value="${af.map.delivery_date}" pattern="yyyy-MM-dd" />
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">采购尺寸：</td>
          <td width="50%" >
          ${af.map.size}
          </td>
          <td class="title_item" nowrap="nowrap" align="right">采购数量：</td>
          <td width="50%" >
          ${af.map.buyer_num}（台）
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">采购预算：</td>
          <td width="50%" >
          ${af.map.budget}（元）
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">参数要求：</td>
          <td width="88%" colspan="3">
          ${af.map.memo}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;备 注：</td>
          <td width="88%" colspan="3">
          <c:choose>
        			<c:when test="${fn:length(af.map.remark)>30}">
        			<c:out value="${fn:substring(af.map.remark,0,20)}..."></c:out>
        			</c:when>
        			<c:otherwise>
        			<c:out value="${af.map.remark}"></c:out>
        			</c:otherwise>
        		</c:choose>
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;创建人：</td>
          <td width="50%" >
          ${af.map.user_name}
          </td>
          <td class="title_item" nowrap="nowrap" align="right">创建日期：</td>
          <td width="50%" >
          <fmt:formatDate value="${af.map.create_date}" pattern="yyyy-MM-dd" /> 
          </td>
        </tr>
        <c:if test="${not empty attachmentList}">
          <tr>
            <td height="28" class="title_item" align="right">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;</li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <tr><td colspan="4">
        <table width="100%">
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型1：</td>
          <td width="50%" ><input type="text" name="model_1" maxlength="125" value="${af.map.model_1}"  readonly="readonly" id="model_1" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件1：</td>
         
            <td width="30%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_1}" target="_blank" >${af.map.fj_name_1}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型2：</td>
          <td width="50%" ><input type="text" name="model_2" maxlength="125" value="${af.map.model_2}"  readonly="readonly" id="model_2" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td >
          <td class="title_item" nowrap="nowrap" align="right">参数附件2：</td>
         
           <td width="30%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_2}" target="_blank" >${af.map.fj_name_2}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型3：</td>
          <td width="50%" ><input type="text" name="model_3" maxlength="125" value="${af.map.model_3}"  readonly="readonly" id="model_3" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件3：</td>
         
           <td width="30%"><c:if test="${not empty af.map.id }">
          <a href="${ctx}/${af.map.fj_3}" target="_blank" >${af.map.fj_name_3}</a>
           </c:if></td>
        </tr>
        </table>
        </td>
        </tr>
        <tr><td colspan="4" class="title_item" align="left">审核记录</td></tr>
        <tr>
        <td colspan="4">
        <table width="100%">
        <tr bgcolor="pink"><td align="center" width="10%">审核人</td><td align="center" width="10%">审核时间</td><td align="center" width="10%" nowrap="nowrap">能否满足参数</td><td align="center" width="10%">审核结果</td><td align="center">审批意见</td></tr>
        		<c:forEach items="${auditList}" var="cur">
        		<tr>
        			<td nowrap="nowrap" align="center">${cur.map.audit_user_name}</td>
        		<td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.audit_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        		<td nowrap="nowrap" align="center">
        		<c:if test="${cur.is_meet eq 0}">满足</c:if>
        		<c:if test="${cur.is_meet eq 1}">不满足</c:if>
        		</td>
        		<td nowrap="nowrap" align="center">
        		<c:if test="${cur.audit_result eq 1}">审核通过</c:if>
        		<c:if test="${cur.audit_result eq -1}">驳回</c:if>
        		<c:if test="${cur.audit_result eq -2}">撤回</c:if>
        		<c:if test="${cur.audit_result eq -3}">驳回至制单人</c:if>
        		</td>
        		<td title="${cur.audit_idea}">
        		<c:choose>
        			<c:when test="${fn:length(cur.audit_idea)>80}">
        			<c:out value="${fn:substring(cur.audit_idea,0,80)}..."></c:out>
        			</c:when>
        			<c:otherwise>
        			<c:out value="${cur.audit_idea}"></c:out>
        			</c:otherwise>
        		</c:choose></td>
        		</tr>
        		</c:forEach>
        </table>
        </td>
        </tr>
      </table>
      </div>
      
      <div id=tab2>
      	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      	<tr>
      		<td class="title_item" align="right" width="15%">项目编号：</td>
      		<td width="35%">${af.map.proj_code}</td>
             <td class="title_item" align="right" width="15%">项目类型：</td>
             <td width="35%">
            <c:if test="${af.map.proj_type eq 1}">政府采购</c:if>
      		<c:if test="${af.map.proj_type eq 2}">酒店采购</c:if>
      		<c:if test="${af.map.proj_type eq 3}">企业采购</c:if>
      		<c:if test="${af.map.proj_type eq 4}">其他</c:if>
             </td>
      	</tr> 
      	<tr>
      		<td class="title_item" align="right">项目名称：</td>
      		<td colspan="3">
      		<c:choose>
        		<c:when test="${fn:length(af.map.proj_name)>30}">
        		<c:out value="${fn:substring(af.map.proj_name,0,30)}..."></c:out>
        		</c:when>
        		<c:otherwise>
        		<c:out value="${af.map.proj_name}"></c:out>
        		</c:otherwise>
        	</c:choose></td>
      	</tr>
        <tr>
          <td class="title_item" align="right">&nbsp;分公司&nbsp;：</td>
          <td>
            ${af.map.fgs_dept_name}
          </td>
           <td class="title_item" align="right">审核状态：</td>
          <td width="35%">
              <c:if test="${af.map.gcxmProjOffer.info_state eq -1}">未提交</c:if>
              <c:if test="${af.map.gcxmProjOffer.info_state eq 0}">审核中</c:if>
              <c:if test="${af.map.gcxmProjOffer.info_state eq 1}">已完结</c:if>
          </td>
        </tr>
        <tr><td colspan="4">
        <table width="100%">
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型1：</td>
          <td width="50%" ><input type="text" name="model_1" maxlength="125" value="${af.map.model_1}"  readonly="readonly" id="model_1" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件1：</td>
          
            <td width="30%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_1}" target="_blank" >${af.map.fj_name_1}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型2：</td>
          <td width="50%" ><input type="text" name="model_2" maxlength="125" value="${af.map.model_2}"  readonly="readonly" id="model_2" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td >
          <td class="title_item" nowrap="nowrap" align="right">参数附件2：</td>
           <td width="30%"> <c:if test="${not empty af.map.id }"><a href="${ctx}/${af.map.fj_2}" target="_blank" >${af.map.fj_name_2}</a></c:if></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">推荐机型3：</td>
          <td width="50%" ><input type="text" name="model_3" maxlength="125" value="${af.map.model_3}"  readonly="readonly" id="model_3" style="width: 280px; color: rgb(153, 153, 153);" class="webinput" />
          </td>
          <td class="title_item" nowrap="nowrap" align="right">参数附件3：</td>
           <td width="30%"><c:if test="${not empty af.map.id }">
          <a href="${ctx}/${af.map.fj_3}" target="_blank" >${af.map.fj_name_3}</a>
           </c:if></td>
        </tr>
        </table>
        </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">报价型号：</td>
          <td width="50%" >
          ${af.map.gcxmProjOffer.offer_model}</td>
          <td class="title_item" nowrap="nowrap" align="right">分公司报价：</td>
          <td width="50%" >
         ${af.map.gcxmProjOffer.offer_price}
          </td>
        </tr>
        <tr>
        <td class="title_item" nowrap="nowrap" align="right">交货日期：</td>    
          <td width="50%" >
          <fmt:formatDate  value="${af.map.gcxmProjOffer.delivery_date}" pattern="yyyy-MM-dd" /> 
          </td>     
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">参数要求：</td>
          <td width="88%" colspan="3">
          ${af.map.gcxmProjOffer.offer_memo}
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;创建人：</td>
          <td width="50%" >
          ${af.map.gcxmProjOffer.create_name}
          </td>
          <td class="title_item" nowrap="nowrap" align="right">创建日期：</td>      
          <td width="50%" >
          <fmt:formatDate  value="${af.map.gcxmProjOffer.create_date}" pattern="yyyy-MM-dd" />  
          </td>
        </tr>
         <c:if test="${not empty attachmentList2}">
          <tr>
            <td height="28" class="title_item" align="right">已上传的附件：</td>
            <td><ol>
                <c:forEach var="cur" items="${attachmentList2}" varStatus="vs">
                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;</li>
                </c:forEach>
              </ol></td>
          </tr>
        </c:if>
        <tr><td colspan="4" class="title_item" align="left">审核记录</td></tr>
        <tr>
        <td colspan="4">
        <table width="100%">
        		<tr bgcolor="pink"><td align="center" width="10%">审核人</td><td align="center" width="10%">审核时间</td><td align="center" width="10%" nowrap="nowrap">能否满足参数</td><td align="center" width="10%">审核结果</td><td align="center">审批意见</td></tr>
        		<c:forEach items="${auditList2}" var="cur">
        		<tr>
        			<td nowrap="nowrap" align="center">${cur.map.audit_user_name}</td>
        		<td nowrap="nowrap" align="center"><fmt:formatDate value="${cur.audit_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
        		<td nowrap="nowrap" align="center">
        		<c:if test="${cur.is_meet eq 0}">满足</c:if>
        		<c:if test="${cur.is_meet eq 1}">不满足</c:if>
        		</td>
        		<td nowrap="nowrap" align="center">
        		<c:if test="${cur.audit_result eq 1}">审核通过</c:if>
        		<c:if test="${cur.audit_result eq -1}">驳回</c:if>
        		<c:if test="${cur.audit_result eq -2}">撤回</c:if>
        		<c:if test="${cur.audit_result eq -3}">驳回至制单人</c:if>
        		</td>
        		<td title="${cur.audit_idea}">
        		<c:choose>
        			<c:when test="${fn:length(cur.audit_idea)>80}">
        			<c:out value="${fn:substring(cur.audit_idea,0,80)}..."></c:out>
        			</c:when>
        			<c:otherwise>
        			<c:out value="${cur.audit_idea}"></c:out>
        			</c:otherwise>
        		</c:choose></td>
        		</tr>
        		</c:forEach>
        </table>
        </td>
        </tr>  
      </table>
      </div>
      
      <div id=tab3>
      	<table  width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3" >
      	<tr>
      		<td class="title_item" align="right" width="15%">项目编号：</td>
      		<td width="35%">${af.map.proj_code}</td>
             <td class="title_item" align="right" width="15%">项目类型：</td>
             <td width="35%">
            <c:if test="${af.map.proj_type eq 1}">政府采购</c:if>
      		<c:if test="${af.map.proj_type eq 2}">酒店采购</c:if>
      		<c:if test="${af.map.proj_type eq 3}">企业采购</c:if>
      		<c:if test="${af.map.proj_type eq 4}">其他</c:if>
             </td>
      	</tr> 
      	<tr>
      		<td class="title_item" align="right">项目名称：</td>
      		<td colspan="3">${af.map.proj_name}</td>
      	</tr>
        <tr>
          <td class="title_item" align="right">&nbsp;分公司&nbsp;：</td>
          <td>
            ${af.map.fgs_dept_name}
          </td>
           <td class="title_item" align="right">项目状态：</td>
          <td width="35%">
              <c:if test="${af.map.gcxmProjSupply.info_state eq -1 or empty af.map.gcxmProjSupply.info_state}">未提交</c:if>
              <c:if test="${af.map.gcxmProjSupply.info_state eq 1}">已结束</c:if> 
          </td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">报价型号：</td>
          <td width="50%" >
          ${af.map.gcxmProjOffer.offer_model}</td>
          <td class="title_item" nowrap="nowrap" align="right">分公司报价：</td>
          <td width="50%" >
           ${af.map.gcxmProjOffer.offer_price}元/台
          </td>
        </tr>
        <tr>
        <td class="title_item" nowrap="nowrap" align="right">交货日期：</td>    
          <td width="50%" >
          <fmt:formatDate value="${af.map.delivery_date}" pattern="yyyy-MM-dd" />  
          </td>     
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">&nbsp;创建人：</td> 
          <td width="50%" >
          ${af.map.gcxmProjSupply.create_name}
          </td>
          <td class="title_item" nowrap="nowrap" align="right">创建日期：</td>      
          <td width="50%" >
          <fmt:formatDate  value="${af.map.gcxmProjSupply.create_date}" pattern="yyyy-MM-dd" />  
          </td>
        </tr>
        <tr>
        <td colspan="4" align="left" class="title_item">竞品价格</td>
        </tr>
         <tr>
         <td colspan="4"><table width="80%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="5%" align="center" nowrap="nowrap">序号</td>
          			<td align="center" width="10%" nowrap="nowrap">品牌</td>
          			<td width="15%" align="center" nowrap="nowrap">型号</td>
          			<td width="15%" align="center" nowrap="nowrap">报价</td>
          			<td width="15%" align="center" nowrap="nowrap">说明</td>
          		</tr>
          		<c:forEach var="cur1" items="${gcList}" varStatus="vs">
          			<tr class="tr_pd_1">
		          	    <td align="center" nowrap="nowrap"><span id="audit_level_show_2" class="jxcTip2">${vs.count}</span><input type="hidden" name="c_ids" id="c_ids" value="${cur1.id}" /></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="brand_name" size="40" maxlength="20" styleClass="brand_name" value="${cur1.brand_name}" readonly="true"/></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="compet_model" styleClass="compet_model" size="40" maxlength="20" value="${cur1.compet_model}" readonly="true"/></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="compet_price" size="8" maxlength="8" styleClass="compet_price" value="${cur1.compet_price}"  readonly="true"/></td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="compet_memo" size="40" maxlength="20" styleClass="compet_memo" value="${cur1.compet_memo}" readonly="true"/></td>
	          		</tr>
	          		</c:forEach>
          		<tbody id="showAddTrsTbody"></tbody>
          </table>
          </td>
         </tr> 
         <tr>
         <td colspan="4" class="title_item" align="left">
         <c:if test="${af.map.is_win eq 0}">中标</c:if>
         <c:if test="${af.map.is_win eq 1 or empty af.map.is_win}">未中标</c:if>
         </td>
         </tr>
          <tr>
          <td class="title_item" nowrap="nowrap" align="right">供货机型：</td>
          <td width="50%" >${af.map.gcxmProjSupply.supply_model}</td>
          <td class="title_item" nowrap="nowrap" align="right">供货数量：</td>
          <td width="50%" >
          ${af.map.gcxmProjSupply.supply_num}
          </td>
        </tr>
         <tr>
          <td class="title_item" nowrap="nowrap" align="right">描述：</td>
          <td width="88%" colspan="3"> ${af.map.gcxmProjSupply.supply_memo}</td>
        </tr>
      </table>
      </div>
      </div>

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
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script> 
<script type="text/javascript">//<![CDATA[
                                        
$(document).ready(function(){ 
	
	//tab切换
	$("#content div[id^=tab]").hide(); // Initially hide all content
    $("#tabs li:first").attr("id","current"); // Activate first tab
    $("#content div[id^=tab]:first").fadeIn(); // Show first tab content
    $('#tabs a').click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         	return   ;    
        } else{             
	        $("#content div[id^=tab]").hide(); //Hide all content
	        $("#tabs li").attr("id",""); //Reset id's
	        $(this).parent().attr("id","current"); // Activate this
	        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
        }
        window.parent.resizeFrameHeight('mainFrame', 3);
    }); 

});




//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
