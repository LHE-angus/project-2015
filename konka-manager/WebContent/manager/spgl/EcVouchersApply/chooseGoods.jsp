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
 <div class="rtabcont1">
    <html-el:form action="/spgl/EcVouchersApply.do">
      <html-el:hidden property="method" value="chooseGoods" /> 
      <html-el:hidden property="goods_types_hid" value="${af.map.goods_types_hid}" />
      <html-el:hidden property="prod_types" value="${af.map.prod_types}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td>
          	&nbsp;商品编码：
            <html-el:text property="pd_sn_like" styleId="pd_sn_like" maxlength="30" styleClass="webinput" />
            &nbsp;商品名称：
            <html-el:text property="pd_name_like" styleId="pd_name_like"  maxlength="30" styleClass="webinput" />
          </td>
        </tr>
         <tr>
         <td>
         &nbsp;&nbsp;分类标签 <html-el:select property="label_of_cate" styleId="label_of_cate">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="0">新品</html-el:option>
            	<html-el:option value="2">热销</html-el:option>
            	<html-el:option value="3">特惠</html-el:option>
            	<html-el:option value="4">工程机</html-el:option>
            	<html-el:option value="5">限时抢购</html-el:option>
            	<html-el:option value="6">团购</html-el:option>
            	<html-el:option value="7">精品</html-el:option>
            	<html-el:option value="8">每周一劲爆</html-el:option>
            	<html-el:option value="9">样机专区</html-el:option>
            </html-el:select>  &nbsp;&nbsp; 
                       所属组织：<html-el:select property="is_zb" styleId="is_zb">
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="1">总部</html-el:option>
            	<html-el:option value="2">分公司</html-el:option>
            </html-el:select>  &nbsp;&nbsp;产品分类：<html-el:select property="prod_type" styleId="prod_type"  >  
            	<html-el:option value="">请选择--</html-el:option>
            	<html-el:option value="0">彩电</html-el:option>
            	<html-el:option value="3">小家电</html-el:option>
            	<html-el:option value="5">洗衣机</html-el:option>
            </html-el:select>&nbsp;&nbsp;&nbsp;	
            <html-el:submit styleClass="but1" value="搜索" />
          </td>  
         </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center"></td> 
        <td width="5%" nowrap="nowrap" align="center">序号</td>  
        <td nowrap="nowrap" align="center" >产品分类</td>
        <td nowrap="nowrap" align="center" >商品编码</td>
        <td nowrap="nowrap" align="center">分类标签</td>
        <td nowrap="nowrap" align="center">所属组织</td>
        <td nowrap="nowrap" align="center" >商品名称</td>
        <td nowrap="nowrap" align="center" >商品图片</td>
      </tr>
       <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr style="height:60px;">
            <td align="center" nowrap="nowrap"><input name="pks" type="checkbox" class="pks"  value="${cur.id}" /></td>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.prod_type eq 0}">彩电</c:if>
            <c:if test="${cur.prod_type eq 3}">小家电</c:if>
            <c:if test="${cur.prod_type eq 5}">洗衣机</c:if>
            </td>
            <td align="left" nowrap="nowrap" id="s_${cur.id}">${cur.pd_sn}</td>
            <td align="center">
            <c:if test="${cur.label_of_cate eq 0}">新品</c:if>
            <c:if test="${cur.label_of_cate eq 2}">热销</c:if>
            <c:if test="${cur.label_of_cate eq 3}">特惠</c:if>
            <c:if test="${cur.label_of_cate eq 4}">工程机</c:if>
            <c:if test="${cur.label_of_cate eq 5}">限时抢购</c:if>
            <c:if test="${cur.label_of_cate eq 6}">团购</c:if>
            <c:if test="${cur.label_of_cate eq 7}">精品</c:if>
            <c:if test="${cur.label_of_cate eq 8}">每周一劲爆</c:if> 
            <c:if test="${cur.label_of_cate eq 9}">样机专区</c:if>
            </td>
            <td align="left" nowrap="nowrap"><c:if test="${cur.dept_sn eq 0}">总部</c:if><c:if test="${cur.dept_sn ne 0}">分公司</c:if></td>
            <td align="left" nowrap="nowrap">${cur.pd_name}</td>
            <td align="center" nowrap="nowrap" ><c:set value="${fn:split(cur.main_pic, ',')[0]}" var="main_pic_path" />
              <img  width="60px" height="40px" src="${ctx}/${fn:substringBefore(main_pic_path, '.')}_120.jpg" /></td>
          </tr> 
        </c:forEach>
        <tr>
          <td align="center" nowrap="nowrap" colspan="8"><input type="button" value="确认" onclick="clos();" style="height:30px;width:80px;"/></td>
        </tr>
    </table>
  </div>
  <div class="clear"></div>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcVouchersApply.do?method=chooseGoods">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("pd_name_like", "${fn:escapeXml(af.map.pd_name_like)}");
	            pager.addHiddenInputs("goods_types_hid", "${fn:escapeXml(af.map.goods_types_hid)}");
	            pager.addHiddenInputs("prod_types", "${fn:escapeXml(af.map.prod_types)}");
	            pager.addHiddenInputs("label_of_cate", "${af.map.label_of_cate}");
	            pager.addHiddenInputs("pd_sn_like", "${fn:escapeXml(af.map.pd_sn_like)}");
	            pager.addHiddenInputs("is_zb", "${fn:escapeXml(af.map.is_zb)}");
	            pager.addHiddenInputs("prod_type", "${af.map.prod_type}");
		        document.write(pager.toString()); 
	      </script></td>
        </tr>
      </table>
    </form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
    var api = frameElement.api, W = api.opener;
    
	var cs= W.document.getElementById("goods_hid").value;    
	var prod_names= W.document.getElementById("goods_name_hid").value;  
	

	if(cs!=""){
		var cs1=cs.substring(0,cs.length-1);
		var countryList = cs1.split(',');
		var selectedmd= document.getElementsByName("pks");
		for(var i=0;i<selectedmd.length;i++){
			for(var j=0;j<countryList.length;j++){
				if(selectedmd[i].value==countryList[j]){ 
					selectedmd[i].checked=true;
					 break;
				}
			}
		}

	}
	
	
	//  动态监测 checkbox 是否选择 
	//var flag_names="";   
	$(document).delegate(".pks", "click", function(){
		var checkbox = $(".pks");   
		for(var i = 0; i < checkbox.length; i++){  
			if("checked" == $(checkbox[i]).attr("checked")){
				var value = $(checkbox[i]).val()+",";
				if(cs.indexOf(value)==-1){
					cs=cs+value;
				}else{
					continue;   
				}
				//var id = $(checkbox[i]).val();
				//var p_name = $("#s_"+id).html()+",";
				//flag_names=flag_names+prod_names+p_name;      
				
			}else if("checked"!=$(checkbox[i]).attr("checked")){ 
				cs=cs.substring(0,cs.length-1);
				var ss=cs.split(",");
				var cc="";
				for(var j=0;j<ss.length;j++){
					if(ss[j]!=$(checkbox[i]).val()){
						cc=cc+ss[j]+","; 
					}
				}
				cs=cc;
				if(cs==","){    
					cs="";
				} 
				
			}
		}
		//var p_name_show="";
		//if(flag_names!=""){
			//if(flag_names.substring(flag_names.length-1,flag_names.length)==","){
				//p_name_show=flag_names.substring(0,flag_names.length-1); 
			//}	
		//}else{
			//p_name_show=flag_names; 
		//}
		
		W.document.getElementById("goods_hid").value=cs;   
		
		$.ajax({
			type: "POST",  
			url: "${ctx}/manager/spgl/EcVouchersApply.do",
			data: { "method":"ajaxShow", "cs":cs, "timestamp":new Date().getTime() },
			dataType: "json",
			sync: true,
			error: function (xhr, ajaxOptions, thrownError) {  },
			success: function(result) {
				if(result.status == '0'){
					W.document.getElementById("goods_name_hid").value="";
					W.document.getElementById("goods").value="";     
				} else {
					W.document.getElementById("goods_name_hid").value=result.goods_name_hid;
					W.document.getElementById("goods").value=result.goods;
				}
			}
		});
		 
	});    
	
});

function clos(){
	 var api = frameElement.api, W = api.opener;
	 api.close();
}


//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
