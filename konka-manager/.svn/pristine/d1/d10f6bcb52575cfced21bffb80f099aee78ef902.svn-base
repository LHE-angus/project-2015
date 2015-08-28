<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/commons/scripts/themes/icon.css"/>
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
  <div class="rtabcont1" style="margin-bottom: 30px">
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/admin/AreaFight">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="p_index" value="${af.map.p_index }"/>
      <html-el:hidden property="queryStr" value="${queryStr}"/>
      
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
    	<tr>
        	<td colspan="4" align="right">
        		<c:if test="${af.map.op_type eq 'edit' }">
	          		<html-el:button property="" value="提&nbsp;交" styleClass="bgButtonSave" styleId="btn_submit" style="padding-left:25px"/>&nbsp;
					<html-el:button property="" value="重&nbsp;置" styleClass="btn_reset" styleId="btn_reset" onclick="this.form.reset();" style="padding-left:25px;"/>&nbsp;
        		</c:if>
				<html-el:button property="" value="返&nbsp;回" styleClass="bgButtonBack" styleId="btn_back" onclick="goback()" style="padding-left:25px"/>
          	</td>
       	</tr>
        <tr>
          	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" width="20%" align="right">
          		分&nbsp;公&nbsp;司：
          	</td>
          	<td colspan="3">
		        <c:if test="${af.map.op_type eq 'view'}">
	          		<html-el:select property="dept_id" styleId="dept_id" disabled="true">
			         	<html-el:option value="">请选择</html-el:option>
			         	<c:forEach items="${BranchList}" var="cur">
			         		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
			         	</c:forEach>
			        </html-el:select>
		        </c:if>
		        <c:if test="${af.map.op_type eq 'edit'}">
	          		<html-el:select property="dept_id" styleId="dept_id">
			         	<html-el:option value="">请选择</html-el:option>
			         	<c:forEach items="${BranchList}" var="cur">
			         		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
			         	</c:forEach>
			        </html-el:select>
		        </c:if>
          	</td>
        </tr>
        <tr>
          	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" width="20%" align="right">
          		乡&nbsp;镇&nbsp;街：
          	</td>
          	<td width="30%">
          		${af.map.map.PROVINCE }&nbsp;&nbsp;${af.map.map.CITY }&nbsp;&nbsp;${af.map.map.COUNTRY }&nbsp;&nbsp;${af.map.map.TOWN }
          	</td>
          	<td nowrap="nowrap" class="title_item" width="20%" style="padding-left: 9%" align="right">乡镇街编码：</td>
          	<td width="30%">${af.map.p_index }</td>
        </tr>
        <tr>
          	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
          		<span style="color:red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;域：
          	</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="area_name" styleId="area_name" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="华东">华东</html-el:option>
		                <html-el:option value="山东">山东</html-el:option>
						<html-el:option value="东北">东北</html-el:option>
						<html-el:option value="华北">华北</html-el:option>
						<html-el:option value="华南">华南</html-el:option>
						<html-el:option value="西南">西南</html-el:option>
						<html-el:option value="华中">华中</html-el:option>
						<html-el:option value="西北">西北</html-el:option>
	          		</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="area_name" styleId="area_name">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="华东">华东</html-el:option>
		                <html-el:option value="山东">山东</html-el:option>
						<html-el:option value="东北">东北</html-el:option>
						<html-el:option value="华北">华北</html-el:option>
						<html-el:option value="华南">华南</html-el:option>
						<html-el:option value="西南">西南</html-el:option>
						<html-el:option value="华中">华中</html-el:option>
						<html-el:option value="西北">西北</html-el:option>
	          		</html-el:select>
          		</c:if>
         	</td>
         	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
         		<span style="color:red;">*</span>类&nbsp;&nbsp;&nbsp;&nbsp;型：
         	</td>
         	<td>
         		<c:if test="${af.map.op_type eq 'view'}">
         			<html-el:select property="t_type" styleId="t_type" style="width:135px" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="1">乡镇</html-el:option>
						<html-el:option value="2">街道</html-el:option>
						<html-el:option value="3">开发区</html-el:option>
		   			</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="t_type" styleId="t_type" style="width:135px">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="1">乡镇</html-el:option>
						<html-el:option value="2">街道</html-el:option>
						<html-el:option value="3">开发区</html-el:option>
		   			</html-el:select>
          		</c:if>
         		
         	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		所辖村数量：
        	</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<c:if test="${empty af.map.t_num }">
          				暂无
          			</c:if>
          			<c:if test="${not empty af.map.t_num }">
	          			${af.map.t_num }个
          			</c:if>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:text property="t_num" styleId="t_num" />个
          		</c:if>
          	</td>
          	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
          		GDP：
          	</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<c:if test="${empty af.map.gdp }">
          				暂无
          			</c:if>
          			<c:if test="${not empty af.map.gdp }">
	          			${af.map.gdp }万元
          			</c:if>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:text property="gdp" styleId="gdp" />万元
          		</c:if>
          	</td>	
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		人&nbsp;&nbsp;&nbsp;&nbsp;口：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<c:if test="${empty af.map.human_num }">
          				暂无
          			</c:if>
          			<c:if test="${not empty af.map.human_num }">
	          			${af.map.human_num }万人
          			</c:if>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:text property="human_num" styleId="human_num" />万人
          		</c:if>
          	</td>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		面&nbsp;&nbsp;&nbsp;&nbsp;积：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<c:if test="${empty af.map.area_size }">
          				暂无
          			</c:if>
          			<c:if test="${not empty af.map.area_size }">
	          			${af.map.area_size }平方公里
          			</c:if>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:text property="area_size" styleId="area_size" />平方公里
          		</c:if>
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		彩电市场容量(额)：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<c:if test="${empty af.map.market_money }">
          				暂无
          			</c:if>
          			<c:if test="${not empty af.map.market_money }">
	          			${af.map.market_money }万元
          			</c:if>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:text property="market_money" styleId="market_money" />万元
          		</c:if>
          	</td>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%">
        		彩电市场容量(量)：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<c:if test="${empty af.map.market_num }">
          				暂无
          			</c:if>
          			<c:if test="${not empty af.map.market_num }">
	          			${af.map.market_num }台
          			</c:if>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:text property="market_num" styleId="market_num" />台
          		</c:if>
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		<span style="color:red;">*</span>家电入驻：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="jd_in" styleId="jd_in" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="0">是</html-el:option>
						<html-el:option value="1">否</html-el:option>
			   		</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="jd_in" styleId="jd_in">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="0">是</html-el:option>
						<html-el:option value="1">否</html-el:option>
			   		</html-el:select>
          		</c:if>
          	</td>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		<span style="color:red;">*</span>康佳入驻：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="konka_in" styleId="konka_in" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="0">是</html-el:option>
						<html-el:option value="1">否</html-el:option>
			   		</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="konka_in" styleId="konka_in">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="0">是</html-el:option>
						<html-el:option value="1">否</html-el:option>
			   		</html-el:select>
          		</c:if>
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		康佳排名：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="konka_rank" styleId="konka_rank" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="1" >第1名</html-el:option>
						<html-el:option value="2" >第2名</html-el:option>
						<html-el:option value="3" >第3名</html-el:option>
						<html-el:option value="4" >第4名</html-el:option>
						<html-el:option value="5" >第5名</html-el:option>
						<html-el:option value="6" >第6名</html-el:option>
						<html-el:option value="7" >第7名</html-el:option>
						<html-el:option value="8" >第8名</html-el:option>
						<html-el:option value="9" >第9名</html-el:option>
						<html-el:option value="10" >第10名</html-el:option>
					</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="konka_rank" styleId="konka_rank">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="1" >第1名</html-el:option>
						<html-el:option value="2" >第2名</html-el:option>
						<html-el:option value="3" >第3名</html-el:option>
						<html-el:option value="4" >第4名</html-el:option>
						<html-el:option value="5" >第5名</html-el:option>
						<html-el:option value="6" >第6名</html-el:option>
						<html-el:option value="7" >第7名</html-el:option>
						<html-el:option value="8" >第8名</html-el:option>
						<html-el:option value="9" >第9名</html-el:option>
						<html-el:option value="10" >第10名</html-el:option>
					</html-el:select>
          		</c:if>
          	</td>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		市场占有率：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			${af.map.market_rate }
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:text property="market_rate" styleId="market_rate" />
          		</c:if>%
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		第一对手：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="first_comp" styleId="first_comp" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="海信" >海信</html-el:option>
						<html-el:option value="TCL" >TCL</html-el:option>
						<html-el:option value="创维" >创维</html-el:option>
						<html-el:option value="海尔" >海尔</html-el:option>
					</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="first_comp" styleId="first_comp">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="海信" >海信</html-el:option>
						<html-el:option value="TCL" >TCL</html-el:option>
						<html-el:option value="创维" >创维</html-el:option>
						<html-el:option value="海尔" >海尔</html-el:option>
					</html-el:select>
          		</c:if>
          	</td>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		第二对手：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="second_comp" styleId="second_comp" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="海信" >海信</html-el:option>
						<html-el:option value="TCL" >TCL</html-el:option>
						<html-el:option value="创维" >创维</html-el:option>
						<html-el:option value="海尔" >海尔</html-el:option>
					</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="second_comp" styleId="second_comp">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="海信" >海信</html-el:option>
						<html-el:option value="TCL" >TCL</html-el:option>
						<html-el:option value="创维" >创维</html-el:option>
						<html-el:option value="海尔" >海尔</html-el:option>
					</html-el:select>
          		</c:if>
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		第三对手：	
			</td>
          	<td colspan="3">
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="third_comp" styleId="third_comp" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="海信" >海信</html-el:option>
						<html-el:option value="TCL" >TCL</html-el:option>
						<html-el:option value="创维" >创维</html-el:option>
						<html-el:option value="海尔" >海尔</html-el:option>
					</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:select property="third_comp" styleId="third_comp">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="海信" >海信</html-el:option>
						<html-el:option value="TCL" >TCL</html-el:option>
						<html-el:option value="创维" >创维</html-el:option>
						<html-el:option value="海尔" >海尔</html-el:option>
					</html-el:select>
          		</c:if>
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		康佳未进入原因：	
			</td>
          	<td colspan="3">
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:textarea property="not_in_reason" styleId="not_in_reason" cols="90" rows="5" disabled="true"></html-el:textarea>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
          			<html-el:textarea property="not_in_reason" styleId="not_in_reason" cols="90" rows="5"></html-el:textarea>
          		</c:if>
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		预计进入日期：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			${af.map.in_date }
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
	          		<fmt:formatDate value="${af.map.in_date}" var="_add_date" pattern="yyyy-MM-dd" />
					<html-el:text property="in_date" styleId="in_date" size="10" maxlength="10" value="${_add_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          		</c:if>
          	</td>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		乡镇状态：	
			</td>
          	<td>
          		<c:if test="${af.map.op_type eq 'view'}">
          			<html-el:select property="t_status" styleId="t_status" disabled="true">
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="0">正常</html-el:option>
						<html-el:option value="1">撤销</html-el:option>
			   		</html-el:select>
          		</c:if>
          		<c:if test="${af.map.op_type eq 'edit'}">
	          		<html-el:select property="t_status" styleId="t_status" >
						<html-el:option value="">-请选择-</html-el:option>
						<html-el:option value="0">正常</html-el:option>
						<html-el:option value="1">撤销</html-el:option>
			   		</html-el:select>
          		</c:if>
          	</td>
        </tr>
        <tr>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		上次维护人：	
			</td>
          	<td>
          		${af.map.map.user_name }
          	</td>
        	<td nowrap="nowrap" class="title_item" style="padding-left: 9%" align="right">
        		上次维护日期：	
			</td>
          	<td>${af.map.map.modify_date_t}
          	</td>
        </tr>
      </table>
    </html-el:form>
  </div>
  
  <div id='tabdiv' style="margin:10px;height: 400px;width: 800;">
	<table id="table" width="100%" border="0" cellspacing="0" cellpadding="0"></table> 
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="${ctx}/commons/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">

	//返回
	function goback(){
		var qstr = '${queryStr}';
		qstr = encodeURI(encodeURI(qstr));
		location.href='${ctx}/manager/admin/AreaFight.do?method=backToList&mod_id=${mod_id}&'+qstr;
	}
	$(function(){
		//表格初始化
		$("#table").datagrid({
			title: '',
			url:'${ctx}/manager/admin/AreaFight.do?method=queryDetailList',
			method: 'post',
			queryParams:{
				p_index: '${af.map.p_index }'
			},
			fitColumns: false,
		    onLoadError:function(){
		    	$.messager.alert('温馨提示','数据请求失败!','error');  
		    },
		    loadMsg:'正在拼命加载中，请稍后。。。。',
		    remoteSort:false,
		    pagination:true,
		    rownumbers:true,
		    columns:[[
		     {title:'类型',field:'OBJ_TYPE',width:50,editor:'text',sortable:'true',align:'center'},
		     {title:'名称',field:'OBJ_NAME',width:150,editor:'text',sortable:'true',align:'center'},
		     {title:'编码',field:'OBJ_CODE',width:80,editor:'text',sortable:'true',align:'center'},
		     {title:'客户类型',field:'KH_TYPE1',width:80,editor:'text',sortable:'true',align:'center'},
		     {title:'细分类型',field:'KH_TYPE2',width:90,editor:'text',sortable:'true',align:'center'},
		     {title:'业务员',field:'YWY_NAME',width:80,editor:'text',sortable:'true',align:'center'},
		     {title:'结算额',field:'CCOUNT_MONEY',width:110,editor:'text',sortable:'true',align:'right'},
		     {title:'结算量',field:'CCOUNT_NUM',width:110,editor:'text',sortable:'true',align:'right'},
		     {title:'销售额',field:'SALE_MONEY',width:110,editor:'text',sortable:'true',align:'right'},
		     {title:'销售量',field:'SALE_NUM',width:110,editor:'text',sortable:'true',align:'right'},
		     {title:'创建日期',field:'CREATE_DATE',width:80,editor:'text',sortable:'true',align:'center'}
		    ]],
		    onHeaderContextMenu: function(e, field){  
            	e.preventDefault();  
                if (!$('#tmenu').length){  
                	createColumnMenu();  
              	}  
             	$('#tmenu').menu('show', {  
                	left:e.pageX,  
                   	top:e.pageY  
               	});  
           	}
		});
		
		// 得到datagrid的pager对象，初始化分页栏
		var pager = $('#table').datagrid('getPager');  
		pager.pagination({    
	   		showPageList:false,    
	    	pageSize:10,  
		    onBeforeRefresh:function(pageNumber, pageSize){
		    	$(this).pagination({pageNumber:$(".pagination-num").val()});
		     	$(this).pagination('loading');
		    }
		});
		
		$("#area_name").attr("dataType", "Require").attr("msg", "请选择所在区域");
		$("#t_type").attr("dataType", "Require").attr("msg", "请选择类型");
		$("#jd_in").attr("dataType", "Require").attr("msg", "请选择家电是否入驻");
		$("#konka_in").attr("dataType", "Require").attr("msg", "请选择康佳是否入驻");
		
		$("#btn_submit").click(function(){
			if(Validator.Validate(this.form, 3)){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			}
		});
	});
</script>
</body>
</html>
