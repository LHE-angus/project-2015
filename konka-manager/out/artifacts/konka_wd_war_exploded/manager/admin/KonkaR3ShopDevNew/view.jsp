<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<c:set var="naviString" value="新客户开拓" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/jquery-ui/ui/ui.core.js"></script>
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<style type="">
.xubox_close{position:absolute;}
.xubox_close1_0{ right:-25px; top:-16px; width:33px; height:31px; background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -182px no-repeat; cursor:pointer; overflow:hidden;}
.xubox_close1_0:hover{background:url("${ctx}/styles/customer/images/xubox_ico0_1.png") -6px -215px no-repeat;}
.xubox_border{border-radius:5px;position:absolute;}

.main_box{position:relative;width:260px;z-index:825;border:1px solid rgba(0,0,0,0);-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 0 5px rgba(0,0,0,0.4);-moz-box-shadow:0 0 3px rgba(0,0,0,0.4);box-shadow:0 0 5px rgba(0,0,0,0.4);border:1px solid #CCC;}
.pic_box{text-align:center;;z-index:827;background:#FFFFFF;margin:5px;}

</style>
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form  enctype="multipart/form-data"  action="/admin/KonkaR3ShopDev.do" method="post" >
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="cust_id" value="${af.map.cust_id}" />
      <html-el:hidden property="mod_id"  value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr>
    <td colspan="4" align="right"  style="cursor:pointer;font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';" ><span id="sj_his" style="cursor:pointer;font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';" onclick="doNeedMethod(null, 'KonkaR3ShopDevNew.do','list' ,'&mod_id=${af.map.mod_id}')" >历史记录</span></td>
   </tr>
  <tr>
	<th colspan="4" height="45" style="border-bottom: 1px dotted #d6d6d6;font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>基本信息</span></th>
	</tr>
       <tr style="display:true">
       <td align="center" nowrap="nowrap" class="title_item">创建日期:</td>
       <td colspan="3">
      <c:if test="${not empty today}">
      ${today}
       </c:if>
        <c:if test="${empty today}">
        <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" var="add_date" />
       ${add_date}
        </c:if>
       </td>
       </tr>
  
        <tr>
        <td align="center" nowrap="nowrap" class="title_item">所属分公司:</td>
    		<td align="left" nowrap="nowrap">
    		
    		
    		
    			<html-el:select property="subcomp_id" styleId="subcomp_id"  value="${af.map.subcomp_id}" >
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${deptList}" var="cur">
	    		<c:if test="${not empty cur.dept_id}">
	    			<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
	    		</c:if>
	    		</c:forEach>
	    	</html-el:select>
	    	</td>
        
        
    		<td align="center" nowrap="nowrap" class="title_item"><font color="red">* </font>客户名称:</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:text property="cust_name" styleId="cust_name" maxlength="50"></html-el:text>
	    	</td>
  	   
  	  
 
   </tr>
  	   
  	 <tr>
   <td align="center" nowrap="nowrap" class="title_item"><font color="red">* </font>联系人姓名1:</td>
   <td align="left" nowrap="nowrap">
  <html-el:text property="link_man_name[0]" styleId="link_man_name1" ></html-el:text>
   </td>
   
   <td align="center" nowrap="nowrap" class="title_item"><font color="red">* </font>联系人电话1:</td>
   <td align="left" nowrap="nowrap" >
  <html-el:text property="link_man_tel[0]" styleId="link_man_tel1"></html-el:text>
   </td>
   </tr>
    <tr>
   <td align="center" nowrap="nowrap" class="title_item">联系人姓名2:</td>
   <td align="left" nowrap="nowrap">
  <html-el:text property="link_man_name[1]" styleId="link_man_name2" ></html-el:text>
   </td>
   
   <td align="center" nowrap="nowrap" class="title_item">联系人电话2:</td>
   <td align="left" nowrap="nowrap" >
  <html-el:text property="link_man_tel[1]" styleId="link_man_tel2" ></html-el:text>
   </td>
   </tr>
   
   
   
    	 <tr>
   <td align="center" nowrap="nowrap" class="title_item">联系人手机:</td>
   <td align="left" nowrap="nowrap" >
  <html-el:text property="link_man_mobile" styleId="link_man_mobile"></html-el:text>
  <span id="span_msg_phone__error" style="display: none;"><font style="color: red">正确格式,如：13966557733</font></span>
   </td>

   <td align="center" nowrap="nowrap" class="title_item">联系人邮编:</td>
   <td align="left" nowrap="nowrap">
  <html-el:text property="link_man_post" styleId="link_man_post"></html-el:text>
   </td>
   </tr> 
     	 <tr>
  
  
   <td align="center" nowrap="nowrap" class="title_item">法人姓名:</td>
   <td align="left" nowrap="nowrap">
  <html-el:text property="host_name" styleId="host_name"></html-el:text>
   </td>
     <td align="center" nowrap="nowrap" class="title_item">法人联系电话:</td>
   <td align="left" nowrap="nowrap">
  <html-el:text property="host_tel" styleId="host_tel"></html-el:text>
   </td>
   </tr> 
 <tr>
  
  <td align="center" nowrap="nowrap" class="title_item">联系人地址 :</td>
   <td align="left" nowrap="nowrap" colspan="3">
  <html-el:text property="link_man_addr" styleId="link_man_addr" style="width:350px;"></html-el:text>
   </td>
  
  </tr>
    		
  	   <tr>
	<th colspan="4" height="45" style="border-bottom: 1px dotted #d6d6d6;font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>客户资料</span></th>
	</tr>
  	   
  	   
  	  <tr>
  	  <td align="center" nowrap="nowrap" class="title_item">企业类型:</td>
    		<td align="left" nowrap="nowrap">
    		
    		
    		
    		<html-el:select property="entp_type" styleId="entp_type" value="${af.map.entp_type}">
	    	 <html-el:option value="">-请选择-</html-el:option>
	    		<c:forEach items="${categoryList}" var="cur">
	    		<c:if test="${not empty cur.c_index}">
	    		<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
	    		</c:if>
	    		</c:forEach>
	    	</html-el:select>
	    	</td>
  	   
   <td align="center" nowrap="nowrap" class="title_item">注册资金（万元）:</td>
   <td align="left" nowrap="nowrap">
  <html-el:text property="entp_reg_money" styleId="entp_reg_money"></html-el:text>
   </td>
  
   
   </tr> 
    <tr>
			   <td align="center" nowrap="nowrap" class="title_item">客户规模（年销售额）:</td>
			   <td align="left" nowrap="nowrap">
			   <html-el:select property="total_sale" styleId="total_sale">
					<html-el:option value="">请选择...</html-el:option>
					<c:forEach items="${entpScaleList}" var="cur">
						<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
					</c:forEach>
				</html-el:select> 万元
			   </td>
			  
			   <td align="center" nowrap="nowrap" class="title_item">员工人数:</td>
			   <td align="left" nowrap="nowrap">
					<html-el:select property="entp_man_count" styleId="entp_man_count">
						<html-el:option value="">请选择...</html-el:option>
						<html-el:option value="1">1000以上</html-el:option>
						<html-el:option value="2">500~1000以下</html-el:option>
						<html-el:option value="3">100~500以下</html-el:option>
						<html-el:option value="4">100以下</html-el:option>
					</html-el:select>
				</td>
			   </tr>
			   <tr> <td align="center" nowrap="nowrap" class="title_item">公司电话:</td>
			   <td align="left" nowrap="nowrap">
			  <html-el:text property="entp_tel" styleId="entp_tel"></html-el:text>
			   </td>
			    <td align="center" nowrap="nowrap" class="title_item">公司传真:</td>
			   <td align="left" nowrap="nowrap">
			  <html-el:text property="entp_fax" styleId="entp_fax"></html-el:text>
			   </td></tr>
			   
			   
   <tr>
							<td nowrap="nowrap" class="title_item" align="center" >所在城市：</td>
							<td align="left" colspan="3">
								<select name="province" id="province" style="width:180px;">
				                  <option value="">-请选择省/直辖市/自治区-</option>
				                </select>
				                &nbsp;
				                <select name="city" id="city" style="width:100px;">
				                  <option value="">-请选择市-</option>
				                </select>
				                &nbsp;
				                <select name="country" id="country" style="width:100px;">
				                  <option value="">-请选择县-</option>
				                </select>
				                &nbsp;
				                <select name="town" id="town" style="width:100px;">
				                  <option value="">-请选择乡镇-</option>
				                </select>
							</td>
						</tr>
			  	
				  
				 
			 
			
			    <tr>
			   <td align="center" nowrap="nowrap" class="title_item">成立时间:</td>
			   <td><fmt:formatDate value="${af.map.entp_birthday}" pattern="yyyy-MM-dd" var="entp_birthday" />
			         <html-el:text property="entp_birthday" styleClass="webinput" styleId="entp_birthday" size="10" maxlength="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor:pointer;text-align:center;width:100px;" value="${entp_birthday}" readonly="readonly" />
					</td>
			   
			  
			   
			   <td align="center" nowrap="nowrap" class="title_item">公司邮编:</td>
			   <td align="left" nowrap="nowrap">
			  <html-el:text property="entp_post" styleId="entp_post"></html-el:text>
			   </td>
</tr>
<tr>
							<td align="center" nowrap="nowrap" class="title_item">城市级别：</td>
							<td align="left" >
								<html-el:select property="entp_p_level" styleId="entp_p_level">
									<html-el:option value="">请选择...</html-el:option>
									<html-el:option value="1">一线城市</html-el:option>
									<html-el:option value="2">二线城市</html-el:option>
									<html-el:option value="3">三线城市</html-el:option>
									<html-el:option value="4">四线城市</html-el:option>
								</html-el:select>
							</td>
			   <td align="center" nowrap="nowrap" class="title_item">门店面积(㎡):</td>
			   <td align="left" nowrap="nowrap">
			  <html-el:text property="total_area" styleId="total_area"></html-el:text>
			   </td>
			  
			  
			   </tr>	  
   <tr>
   <td align="center" nowrap="nowrap" class="title_item">公司地址:</td>
   <td align="left" nowrap="nowrap" colspan="3">
  <html-el:text property="entp_addr" styleId="entp_addr" style="width:350px;"></html-el:text>
   </td>
  </tr>
  <tr>
   <td align="center" nowrap="nowrap" class="title_item">公司网址:</td>
   <td align="left" nowrap="nowrap" colspan="3">
  <html-el:text property="entp_www" styleId="entp_www" style="width:350px;"></html-el:text>
   </td>
   </tr>
  <tr>
   <td align="center" nowrap="nowrap" class="title_item">主营产品:</td>
   <td align="left" nowrap="nowrap" colspan="3">
  <html-el:text property="entp_main_pds" styleId="entp_main_pds" style="width:350px;"></html-el:text>
   </td>
  	   </tr>
  	    
  <tr>
   <td align="center" nowrap="nowrap" class="title_item">销售区域:</td>
   <td align="left" nowrap="nowrap" colspan="3">
  <html-el:text property="entp_sale_area" styleId="entp_sale_area" style="width:350px;"></html-el:text>
   </td>
  	   </tr>
  	    <tr>
    <td align="center" nowrap="nowrap" class="title_item">客户简介:</td>
    <td align="left" nowrap="nowrap" colspan="3">
    	<html-el:textarea property="entp_info" styleId="entp_info" style="width:300px;height:50px;"  value="${af.map.entp_info}" />
    </td>
    </tr> 	   
  <tr>
	<th colspan="4" height="45" style="border-bottom: 1px dotted #d6d6d6;font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>开拓信息</span></th>
	</tr>
    <tr>
    		<td align="center" nowrap="nowrap" class="title_item">开拓状态:</td>
    		
    		
    		<td align="left" nowrap="nowrap">
    		<c:if test="${af.map.dev_state eq 1}">开拓中 </c:if>
    		<c:if test="${af.map.dev_state eq 2}">已关闭 </c:if>
    		<c:if test="${af.map.dev_state eq 3}">开拓成功 </c:if>
	    	<c:if test="${af.map.dev_state eq 3 && not empty af.map.link_r3_code}">
	    	    <lable id="r3c">关联R3编码:${af.map.link_r3_code}</lable>
	    	</c:if>
	    	</td>
	    	<td>
	    	<c:if test="${af.map.dev_state eq 3 && not empty af.map.link_r3_code}">
	    	    <lable id="r3c">关联R3编码:${af.map.link_r3_code}</lable>
	    	</c:if>
	    	</td>
  	   </tr>
  	  
  	   <tr>
    		<td align="center" nowrap="nowrap" class="title_item">客户意向:</td>
    		<td align="left" nowrap="nowrap">
    		<c:if test="${af.map.dev_state eq 1}">意向强</c:if>
    		<c:if test="${af.map.dev_state eq 0}">意向一般 </c:if>
	    	</td>
  	   </tr>
  	   
	 <tr>
    <td align="center" nowrap="nowrap" class="title_item">备注说明:</td>
    <td align="left" nowrap="nowrap" colspan="3">
    	<html-el:textarea property="memo" styleId="memo" style="width:300px;height:50px;"  value="${af.map.memo}" /></td>
    </tr> 
	    	 <tr>
           <td class="title_item" align="center" nowrap="nowrap">附件:</td>
				<td>
                <ol>
            	<c:forEach items="${af.map.konkaPeAttachmentsList}" var="cur">
				<c:if test="${fn:startsWith(cur.file_desc, 'policy_file')}">
				 <li><a href="${ctx}/${cur.save_path}">${cur.file_name}</a></li>
				</c:if>
				</c:forEach>
				</ol>
         			</td>
        </tr>
        <tr>
       
          <td colspan="4" align="center"><label>
          <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" /></label></td>
        </tr>
        <tr style="height: 100px;"></tr>
      </table>
    </html-el:form>
  </div>
   
          <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	$("input[type!='button']").each(function(){ this.disabled=true; }); 
});
	
	 
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
