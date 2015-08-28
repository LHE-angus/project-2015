<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<style type="text/css">
  select{font-family:Microsoft YAHEI;font-size:12px;}
  input[type="button"]{
    border: 0 none;
    color: #333;
    cursor: pointer;
    font: 12px/20px "宋体";
    height: 24px;
    padding-left: 27px;
    text-align: left;
    width: 67px;
  
  }
  .imgfloat{
  float:left;
  margin-left:30px;
  }
  
</style>
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
 
	<div class="rtabcont2" style="padding: 5px 5px">
	 	<div style="float:right;">
       	<label >
           <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
        </label>
  	      </div>
	         <ul id="tabs">
			    <li><a href="#" name="tab1">门店信息</a></li>
			    <li><a href="#" name="tab2">展品柜台</a></li>
			    <li><a href="#" name="tab3">门店图片</a></li>
			</ul>
			<div id="content"> 
			    <div id="tab1">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableclass">
        <tr>
          <td height="28" colspan="4" align="left" style="padding:10px">门店资料</td>
        </tr>
        <c:if test="${not empty af.map.add_date}">
			<tr>
				<td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">创建时间：</td>
				<td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">维护时间：</td>
				<td><fmt:formatDate value="${af.map.modify_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:if>
		<c:if test="${not empty af.map.create_user_id or not empty af.map.modify_user_id}">
			<tr>
				<td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">创建人：</td>
				<td>${create_man}</td>
				<td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">维护人：</td>
				<td>${modify_man}</td>
			</tr>
		</c:if>
        <c:if test="${not empty af.map.store_id}">
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">门店编码：</td>
          <td colspan="3">${af.map.store_id}</td>
        </tr>
        </c:if>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">门店名称：</td>
          <td>${af.map.store_name}</td>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">门店R3编码：</td>
          <td>${af.map.r3_sdf_sn}</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">所在地区：</td>
          <td colspan="3">
          	${af.map.map.PROVINCE}&nbsp;${af.map.map.CITY }&nbsp;${af.map.map.COUNTRY }&nbsp;${af.map.map.TOWN }
          </td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">街道地址：</td>
          <td colspan="3">${af.map.addr}</td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">联系人：</td>
          <td>${af.map.link_man}</td>
          <td height="28" nowrap="nowrap" class="title_item" align="right">联系电话：</td>
          <td>${af.map.linka_tel}</td>
        </tr>
        <tr>
          <td height="28" colspan="4" align="left">分公司资料</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">所属分公司/经办：</td>
          <td width="35%">
          	${af.map.dept_name}  ${af.map.jb_name}
              </td>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">经办部门电话：</td>
          <td width="35%">
          	${af.map.jb_tel}
          </td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">经办经理：</td>
          <td>
           	${af.map.jbjl_name}
          </td>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">业务主管：</td>
          <td>
         	${af.map.ywzg_name}
          </td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">业务员：</td>
          <td>
          	${af.map.ywy_name}
          </td>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">是否有促销员：</td>
          <td>
          	<c:if test="${af.map.havaman eq 0}">
          		无
          	</c:if>
          	<c:if test="${af.map.havaman eq 1}">
          		有
          	</c:if>
          </td>
        </tr>
        <tr>
        	<td height="28" nowrap="nowrap" class="title_item" align="right">已有促销员：</td>
        	<td colspan="3">${exCXY}</td>
        </tr>
        <tr>
          <td height="28" colspan="4" align="left">客户资料</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">销售组织：</td>
          <td colspan="3">${af.map.xszz}</td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">客户R3编码：</td>
          <td>${af.map.r3_code}</td>
          <td nowrap="nowrap" class="title_item" align="right">客户名称：</td>
          <td>${af.map.kh_name}</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">R3售达方编码：</td>
          <td>${af.map.r3_shsdf_sn}</td>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">出货仓送达方编码：</td>
          <td >${af.map.mf_sn}</td>
		</tr>
		<tr>
				<td height="28" width="15%" nowrap="nowrap" align="right"
					class="title_item">出货仓名称：</td>
				<td colspan="3">${af.map.ck_name}</td>
		</tr>
		<tr>
				<td height="28" colspan="4" align="left">附件资料信息</td>
		</tr>
		<tr>
        	<td height="28" width="15%" nowrap="nowrap" class="title_item" align="right" >已上传的附件：</td>
          	<td colspan="3">
          		<ol>
              		<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
                		<li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;</li>
              		</c:forEach>
            	</ol>
            </td>
      	</tr>
      	<tr>
          <td height="28" colspan="4" align="left">详细信息</td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">门店级别：</td>
          <td width="35%">
          <c:if test="${af.map.store_level eq 0}">
          		A类
          	</c:if>
          	<c:if test="${af.map.store_level eq 1}">
          		B类
          	</c:if>
          	<c:if test="${af.map.store_level eq 2}">
          		C类
          	</c:if>
              </td>
          <td width="15%" nowrap="nowrap" class="title_item" align="right">年销数：</td>
          <td width="35%">
          	${af.map.year_sale_count}万元
          </td>
        </tr>
        <tr>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">总营业面积：</td>
          <td>
           	${af.map.business_area}
          </td>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">彩电营业面积：</td>
          <td>
         	${af.map.tv_business_area}
          </td>
        </tr>
        <tr>
          <td height="28" nowrap="nowrap" class="title_item" align="right">我品位置：</td>
          <td>
          	第${af.map.konka_site}位
          </td>
          <td height="28" width="15%" nowrap="nowrap" class="title_item" align="right">是否重点：</td>
          <td>
          <c:if test="${af.map.is_important eq 0}">
          		是
          	</c:if>
          	<c:if test="${af.map.is_important eq 1}">
          		否
          	</c:if>
          
          </td>
        </tr>
        <tr>
        	<td height="28" nowrap="nowrap" class="title_item" align="right">竞品品牌：</td>
        	<td colspan="3">
        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="competition_0" value="0" >TCL</input>
        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="competition_1" value="1">长虹</input>
        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="competition_2" value="2">海信</input>
        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="competition_3" value="3">创维</input>
        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="competition_4" value="4">乐视</input>
        	&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="competition_5" value="5">小米</input>
        	</td>
        </tr>
        <tr>
            <td height="28" nowrap="nowrap" class="title_item" align="right">门店简介：</td>
            <td>${af.map.memo}</td>
        </tr>
      	</table>
      	</div>
      
	    <div id="tab2">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableclass">
        <tr>
        	 <td colspan="4" style="font-weight:900;" align="left"><strong class="fb">展台展柜:</strong></td>
            </tr>
            <tr>
        	<td colspan="4" width="100%">
	        	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
	              	<tr class="tabtt1" style="height: 28px;">
	              	<td width="15%" align="center" nowrap="nowrap">门店</td>
	              	  <td width="15%" align="center" nowrap="nowrap">品类名称</td>
	              	  <td width="10%" align="center" nowrap="nowrap">年月</td>
	              	  <td width="10%" align="center" nowrap="nowrap">数量</td>
	              	  <td width="10%" align="center" nowrap="nowrap">金额单位（元）</td>
	              	  <td width="10%" align="center" nowrap="nowrap">尺寸/规格</td>
	              	  <td width="10%" nowrap="nowrap" align="center">添加人</td>
						                    <td width="10%" nowrap="nowrap" align="center">添加时间</td>
	              	  <td width="10%" align="center" nowrap="nowrap">备注</td>
	              	</tr>
	              	<tbody id="tbodyContent" class="rtable2">
	              	<c:forEach items="${ksList}" var="cur">
	              	<tr >
	              	 <td nowrap="nowrap" align="center"><span style="cursor:pointer;color: blue;" class="fblue" 
	              	 onclick="location.href='KonkaR3Store.do?method=view2&mod_id=${af.map.mod_id}&id=${cur.id}'">${cur.store_name}</span></td>
	              	<td nowrap="nowrap" align="center">${cur.category_name}</td>
	              	<td nowrap="nowrap" align="center">${cur.year}年${cur.month}月</td>
	              	 <td nowrap="nowrap" align="center">${cur.num}</td>
	              	 <td nowrap="nowrap" align="center">${cur.task_money}</td>
	              	 <td nowrap="nowrap" align="center">${cur.size}</td>
	              	 <td align="right" nowrap="nowrap">${cur.map.user_name}</td>
                     <td align="center" nowrap="nowrap">
                        <fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
                     </td>
	              	 <td nowrap="nowrap" align="center">${cur.remark}</td>
	              	</tr>
	              	</c:forEach>
	              	
		            </tbody>
	            </table>
        	</td>
           </tr>
           </table>
           </div>
      <div id=tab3>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="tableclass">
	<tr>
		<td colspan="4" style="font-weight: 900;" align="left"><strong
			class="fb">门店图片:</strong></td>
	</tr>
	<tr>
		<c:if test="${not empty attachmentList}">
			<tr>
				<td height="28" colspan="4" align="left">已上传的附件：</td>
			</tr>
			<tr>
				<td colspan="4" margin-left="20px" align="left">
				<ul>

					<c:forEach var="cur" items="${attachmentList}" varStatus="cou">

						<li class="imgfloat"><a
							href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}"
							target="_blank"> <img src="${ctx}/${cur.save_path}"
							style="vertical-align: middle; cursor: pointer; width: 300px; height: 250px;" />
						<br />
						<span>${cur.file_desc}</span></a></li>
						<c:if test="${cou.count % 3 eq 0}">

						</c:if>
					</c:forEach>
				</ul>
				</td>
			</tr>
		</c:if>
	</tr>
</table>
</div>
      </div>

    
  </div>
<div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var competition='${af.map.competitions}';
	 if (null != competition){
		 var competitions = competition.split(",");
		 if(competitions.length>0){
			 for(var k in competitions){
				 $("#competition_"+competitions[k]).attr("checked","checked");
			 }
		 }
		 
	 } 
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
function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
