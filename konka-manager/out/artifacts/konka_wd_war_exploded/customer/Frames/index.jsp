<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<div style="width:1266px;height:697px;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/base.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/styles/customer/css/index.css" />
</head>
<body>
         <div class="content clearfix">  
            <div class="columnA">
                <div class="border-box">
                    <h3 class="column-tit"><span class="fl">精品推荐</span>
                    	<a class="fr"  href="<c:url value='CeppOrder.do?mothed=listPic&mod_id=105020000'/>" target="mainFrame">更多</a>
                    	<a class="fr" style="margin-top:12px;" href="JxcKonkaOrderRegister.do?method=add&mod_id=105020200" target="mainFrame">
                    			<!-- 图标太难看 -->
<!--                     		<img class="vm" src="../../styles/customer/images/buy_now_btn.png" /> -->
                    	</a>
                    </h3>
                      <ul class="columnA-list col-b-n clearfix">
                      <c:set value="0" var="num" />
                      <c:forEach  items="${JPentityList}" var="cur" varStatus="vs" begin="0" end="3">
                      <c:set value="${num+1}" var="num" />    
                       <c:if test="${vs.count eq 3}"> <c:out value="</ul><ul class='columnA-list clearfix'>" escapeXml="false" /></c:if>  
                       <li>
                            <a class="columnA-list-pic" href="##"><img src="${ctx}/${cur.map.save_path}" width="240" height="157" /></a>
                            <h4>${cur.pd_code}</h4>
                            <p>${cur.pd_name}</p>
                            <p class="columnA-sell"><a class="columnA-sell-a" href="<c:url value='CeppOrder.do?method=add&goods_id=${cur.pd_id}' />">立即购买</a></p>
                       </li>
                    </c:forEach>  
                    <c:if test="${num eq 0}">
                    <li>
                           <div style="border: 1px solid #ccc;">  
                           <img src="../../styles/customer/images/no_pic.jpg" /></div>
                    </li>
                    <li>
                          <div style="border: 1px solid #ccc;">  
                           <img src="../../styles/customer/images/no_pic.jpg" /></div>
                    </li>
                    <c:out value="</ul><ul class='columnA-list clearfix'>" escapeXml="false" />
                    <li>
                       <div style="border: 1px solid #ccc;">  
                           <img src="../../styles/customer/images/no_pic.jpg" /></div>   
                    </li>
                    <li>
                        <div style="border: 1px solid #ccc;">  
                           <img src="../../styles/customer/images/no_pic.jpg" /></div> 
                    </li>
                    </c:if>
                    <c:if test="${num eq 1}">
                    <li> <div style="border: 1px solid #ccc;">  
                           <img src="../../styles/customer/images/no_pic.jpg" /></div>
                    </li>
                    <c:out value="</ul><ul class='columnA-list clearfix'>" escapeXml="false" />
                    <li>
                         <div style="border: 1px solid #ccc;">  
                        <img src="../../styles/customer/images/no_pic.jpg" />  </div>  
                    </li>
                    <li>
                         <div style="border: 1px solid #ccc;">  
                        <img src="../../styles/customer/images/no_pic.jpg" />  </div>  
                    </li>
                    </c:if>
                     <c:if test="${num eq 2}">
                    <c:out value="</ul><ul class='columnA-list clearfix'>" escapeXml="false" />
                    <li>
                        <div style="border: 1px solid #ccc;">  
                        <img src="../../styles/customer/images/no_pic.jpg" />    
                        </div>
                    </li>
                    <li>
                         <div style="border: 1px solid #ccc;">  
                        <img src="../../styles/customer/images/no_pic.jpg" /> 
                        </div>   
                    </li>
                    </c:if>
                     <c:if test="${num eq 3}">
                    <li>
                        <div style="border: 1px solid #ccc;">  
                        <img src="../../styles/customer/images/no_pic.jpg" />
                        </div>      
                    </li>
                    </c:if>
                    
                    </ul>
                </div>
            </div>
            <div class="columnB">
                <div class="border-box">
                    <h3 class="column-tit"><span class="fl">资讯</span><a class="fr" href="KonkaGroupPeArticleInfo.do?method=list&mod_id=200000110">更多</a></h3>   
                    <ul class="columnB-list">
                        <c:forEach items="${GroupPeInfoList}" var="cur" >
                         <li><span class="columnB-list-type">${cur.map.type_name}</span><a href="KonkaGroupPeArticleInfo.do?method=view&id=${cur.id}&mod_id=200000110" target="_blank" title=" ${cur.title}">
                         <c:choose> 
                              <c:when test="${fn:length(cur.title) > 20}"> 
                              <c:out value="${fn:substring(cur.title, 0, 20)}...." /> 
                              </c:when> 
                              <c:otherwise> 
                              <c:out value="${cur.title}" /> 
                              </c:otherwise>
                              </c:choose></a> <span class="columnB-list-date" style="width: 85px;"><fmt:formatDate value="${cur.pub_date}" pattern="yyyy-MM-dd" /></span></li> 
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="columnC">
                <div class="serve-tel">
                    <img src="../../styles/customer/images/tel_pic.jpg" />
                </div>
                <div class="columnC-lead">
                    <h4>分公司IHS经理</h4>
                    <c:if test="${empty af.map.ihs_jl_pic}">
	                    <div style="border: 1px solid #ccc;">  
	                    <img src="../../styles/customer/images/ihs_jl.jpg" />
	                    </div>
                    </c:if>
                    <c:if test="${not empty af.map.ihs_jl_pic}"><img src="${ctx}/${af.map.ihs_jl_pic}" width="220"  /> </c:if>
                    <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名： ${af.map.ihs_jl_name}</p>
                    <p>固定电话： ${af.map.ihs_jl_tel}</p>
                    <p>移动电话： ${af.map.ihs_jl_phone}</p>
                    <p>邮&nbsp;&nbsp;&nbsp;&nbsp;箱： ${af.map.ihs_jl_email}</p>
                </div>
                <div class="columnC-lead"> 
                    <h4>分公司IHS专员</h4>
                    <c:if test="${empty af.map.ihs_zy_pic}"> 
                     <div style="border: 1px solid #ccc;">  
                    <img src="../../styles/customer/images/ihs_jl.jpg" /></div></c:if>
                    <c:if test="${not empty af.map.ihs_zy_pic}"><img src="${ctx}/${af.map.ihs_zy_pic}" width="220"  /> </c:if>
                    <p>姓&nbsp;&nbsp;&nbsp;&nbsp;名： ${af.map.ihs_zy_name}</p> 
                    <p>固定电话： ${af.map.ihs_zy_tel}</p>
                    <p>移动电话： ${af.map.ihs_zy_phone}</p>
                    <p>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：  ${af.map.ihs_zy_email}</p>
                </div>
            </div>
        </div>
    
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script>

</script>
</body>
</html>
 </div>