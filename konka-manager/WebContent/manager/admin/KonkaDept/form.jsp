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
<link href="${ctx}/styles/konkadept_tab.css" rel="stylesheet" type="text/css" />

<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
</head>

<body>

<div class="oarcont"  id="body_oarcont">
  <div class="rtabcont2">
  
  	<!-- 非编辑状态 -->
    <c:if test="${empty af.map.bj}">
      
      <html-el:form action="/admin/KonkaDept" enctype="multipart/form-data">
        <html-el:hidden property="id" styleId="id" />
        <html-el:hidden property="mod_id" styleId="mod_id" />
        <html-el:hidden property="listFlg" styleId="listFlg" />
        <html-el:hidden property="method" styleId="method" value="edit" />
        <table width="99%" border="0" align="center" cellpadding="2" cellspacing="0">
          <tr>
            <td width="50%"><c:if test="${af.map.operate_type eq 0}">
            <a href="KonkaDept.do?method=edit&mod_id=${af.map.mod_id}&id=${af.map.id}&bj=true&listFlg=${af.map.listFlg}">查看 <span style="color:#FF0000;">〖${af.map.dept_name}〗</span>部门信息</a> </c:if>
              <c:if test="${af.map.operate_type eq 1}"> 
               <logic-el:match name="popedom" value="+1+">
              <a href="KonkaDept.do?method=add&mod_id=${af.map.mod_id}&id=${af.map.id}&bj=true&listFlg=${af.map.listFlg}"> 增加<span style="color:#FF0000;">〖${af.map.dept_name}〗</span>的子部门</a> &nbsp;&nbsp;
             </logic-el:match>
              <logic-el:match name="popedom" value="+2+">
               <a href="KonkaDept.do?method=edit&mod_id=${af.map.mod_id}&id=${af.map.id}&bj=true&listFlg=${af.map.listFlg}">修改 <span style="color:#FF0000;">〖${af.map.dept_name}〗</span>部门信息</a>
               </logic-el:match>
                </c:if>
            </td>
            <td width="50%" align="right">
              <label for="contain_subdepts" style="width:80px;"><html-el:checkbox property="contain_subdepts" styleId="contain_subdepts" onclick="this.form.submit();" /> 查询所有子部门人员</label>
            </td>
          </tr>
        </table>
      </html-el:form>
      <%@ include file="/commons/pages/messages.jsp" %>
      <span style="color: red">注：组织架构修改只能在早上8:00到10:00！</span>
      <c:if test="${not empty add_dept}">
        <script> self.parent.frames["leftFrame"].document.location.reload();
        </script>
      </c:if>
      <div style="padding:5px 5px"></div>
      
   <c:if test="${empty af.map.listFlg || af.map.listFlg eq 0}">
   			<div>
	   			<ul id="tabs">
				    <li><a href="#" name="tab1">部门人员信息</a></li>
				    <li><a href="#" name="tab2">已停用部门</a></li>
				</ul>
			</div>
     <div id="content"> 
      <!-- 部门人员列表 -->
	   <div id="tab1">
        <table id="tblSort"  width="99%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="2%" nowrap="nowrap">序号</td>
            <td width="6%" nowrap="nowrap">岗位ID</td>
            <td width="10%" nowrap="nowrap">用户名</td>
            <td width="10%" nowrap="nowrap">真实姓名</td>
            <td width="15%" nowrap="nowrap">职务</td>
            <td nowrap="nowrap">所在部门</td>
            <td width="10%" nowrap="nowrap" >手机/电话</td>
            <td width="15%" nowrap="nowrap">邮箱</td>
          </tr>
          <c:forEach items="${deptUserlist}" var="cur" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td nowrap="nowrap" align="center">${cur.job_id}<c:if test="${empty cur.job_id}"><span style="color:#ccc;">未填写</span></c:if></td>
              <td>
              	<a href="PeProdUser.do?method=view&user_id=${cur.id}&role_id=${cur.map.role_id}&mod_id=${af.map.mod_id}" style="text-decoration:underline;color:#00F;">${cur.user_name}</a>
              </td>
              <td align="left" >${cur.real_name}</td>
              <td align="left" >${cur.map.role_name}</td>
              <td align="left" >${cur.map.k_dept_name}
               <c:if test="${false}">
	               <span style="color:#0066FF">
		               [
		                <c:if test="${1 eq cur.map.k_dept_type}">总公司</c:if>
		                <c:if test="${2 eq cur.map.k_dept_type}">事业部</c:if>
		                <c:if test="${3 eq cur.map.k_dept_type}">分公司</c:if>
		                <c:if test="${4 eq cur.map.k_dept_type}">经营部</c:if>
		                <c:if test="${5 eq cur.map.k_dept_type}">办事处</c:if>
		                <c:if test="${6 eq cur.map.k_dept_type}">代理商</c:if>
		                <c:if test="${7 eq cur.map.k_dept_type}">终端</c:if>
		                <c:if test="${8 eq cur.map.k_dept_type}">职能部门</c:if>
		                <c:if test="${8 eq cur.map.k_dept_type}">其他</c:if> 
		                ] 
	                </span> 
                </c:if>
                </td>
              <td align="left"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '未填写')}" /></td>
              <td align="left" style="table-layout: fixed;WORD-BREAK: break-all; WORD-WRAP: break-word"><c:out value="${not empty cur.email ? cur.email : '未填写'}" /></td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
        <form id="bottomPageForm1" name="bottomPageForm1" method="post" action="KonkaDept.do" target="_self">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm1, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "edit");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("tree_param", "${tree_param}");
	            pager.addHiddenInputs("id", "${af.map.id}");
	            pager.addHiddenInputs("bj", "${af.map.bj}");
	            pager.addHiddenInputs("listFlg", "${af.map.listFlg}");            
	            document.write(pager.toString());
	          </script></td>
            </tr>
          </table>
        </form>
        </div><!-- 部门人员 -->
        
        <div id="tab2">
        	<table  width="99%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	          <tr class="tabtt1">
	            <td style="display: none"></td>
	            <td>部门编码</td>
	            <td>部门名称</td>
	            <td>当前状态</td>
	            <td>操作</td>
	          </tr>
          <c:forEach items="${deleteDeptList}" var="cur">
            <tr>
              <td style="display: none">${cur.dept_id}</td>
              <td>${cur.dept_sn}</td>
              <td>${cur.dept_name}</td>
              <td>禁用</td>
              <td><a href="${ctx }/manager/admin/KonkaDept.do?method=changeKonkaDeptStatus&is_del=0&dept_id=${cur.dept_id }&par_id=${cur.par_id}&mod_id=${af.map.mod_id}&bj=true&listFlg=${af.map.listFlg}">启用</a></td>
            </tr>
          </c:forEach>
        </table>
        </div>
        
        
	      <c:if test="${af.map.listFlg eq 1}">
	      <!-- 部门客户列表 -->
        	<div id="tab3">
	        <html-el:form action="/admin/KonkaDept" enctype="multipart/form-data">
	          <html-el:hidden property="id" styleId="id"/>
	          <html-el:hidden property="mod_id" styleId="mod_id" />
	          <html-el:hidden property="method" styleId="method" value="view" />
	          <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	            <tr class="tabtt1">
	              <td width="4%" nowrap="nowrap">序号</td>
	              <td nowrap="nowrap" width="60">R3编码</td>
	              <td nowrap="nowrap">客户名称</td>
	              <td>客户分配</td>
	            </tr>
	            <c:forEach var="cur" items="${entityList}" varStatus="vs">
	              <tr>
	                <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
	                <td align="left" nowrap="nowrap">${cur.r3_code}</td>
	                <td align="left" valign="middle" onclick="getKonkaR3ShopInfo(${cur.id});" style="cursor:pointer;">${cur.customer_name}</td>
	                <td nowrap="nowrap"><%@ include file="../_inc/view_fgs_jyb_bsc_ywy_name.jsp" %></td>
	              </tr>
	              <c:if test="${vs.last eq true}">
	                <c:set var="i" value="${vs.count}" />
	              </c:if>
	            </c:forEach>
	            <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
	              <tr align="center">
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	                <td>&nbsp;</td>
	              </tr>
	            </c:forEach>
	          </table>
	        </html-el:form>
	        <form id="bottomPageForm2" name="bottomPageForm2" method="post" action="KonkaDept.do" target="_self">
	          <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	            <tr>
	              <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
	                <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm2, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "edit");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("customer_name_like", "${af.map.customer_name}");	
		            pager.addHiddenInputs("id", "${af.map.id}");
		            pager.addHiddenInputs("bj", "${af.map.bj}");
		            pager.addHiddenInputs("listFlg", "${af.map.listFlg}");
		            document.write(pager.toString());
	            </script></td>
	            </tr>
	          </table>
	        </form>
        	</div>
	      </c:if>
	      
	      
		</div><!-- content end -->        
      </c:if>
      
    </c:if>
    <!-- 非编辑状态 END　-->
    
    <!-- 编辑状态 -->
    <c:if test="${not empty af.map.bj}">
      <html-el:form action="/admin/KonkaDept.do">
        <html-el:hidden property="queryString" />
        <html-el:hidden property="method" value="save" />
        <html-el:hidden property="add" />
        <html-el:hidden property="edit" />
        <html-el:hidden property="dept_id"/>
        <html-el:hidden property="mod_id"/>
        <html-el:hidden property="listFlg" value="${af.map.listFlg}" />
        <br />
        <table width="99%" border="0" align="center" cellpadding="2" cellspacing="0">
          <tr class="oartop">
            <td colspan="2">部门基本信息</td>
          </tr>
          <tr>
            <td nowrap="nowrap" height="28">父部门：</td>
            <td>
              <html-el:select property="par_id" styleId="par_id" style="width:200px;" disabled="disabled">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach var="cur" items="${peDeptList}">
                  <html-el:option value="${cur.dept_id}">${fn:escapeXml(cur.map.tree_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr>
            <td nowrap="nowrap" height="28">部门名称：</td>
            <td><html-el:text property="dept_name" styleId="dept_name" style="width:200px;" maxlength="20" />
              	<span style="" id="s_after"></span> &nbsp; <span id="loading" style="display:none;"> <img src="${ctx}/styles/images/ajax-loader.gif" style="vertical-align:middle; margin: 2px;" />正在处理... </span> 
             </td>
          </tr>
          <tr>
            <td nowrap="nowrap" height="28">部门编码：</td>
            <td data-orig="${af.map.dept_sn}">
            <c:if test="${not empty af.map.dept_sn}">
            <span style="font-weight:700;">${af.map.dept_sn}</span>&nbsp;&nbsp;&nbsp;
            </c:if>
            <c:if test="${empty af.map.dept_sn}">
            <html-el:text property="dept_sn" styleId="dept_sn" style="width:200px" />
            	<span style="" id="s_after1"></span> &nbsp; <span id="loading1" style="display:none;"> <img src="${ctx}/styles/images/ajax-loader.gif" style="vertical-align:middle; margin: 2px;" />正在处理... </span>
            </c:if>
            <span style="color:#F00;">说明：部门编码一经填写将不能修改，如果与R3系统销售组织一致务必填写R3系统的销售组织代码。</span>
            </td>
          </tr>
          <tr>
            <td width="15%" height="28">所在大区：</td>
            <td width="85%"><html-el:select property="p_area" styleId="p_area">
                <html-el:option value="">-请选择-</html-el:option>
				<html-el:option value="10">华东</html-el:option>
                <html-el:option value="20">山东</html-el:option>
				<html-el:option value="30">东北</html-el:option>
				<html-el:option value="40">华北</html-el:option>
				<html-el:option value="50">华南</html-el:option>
				<html-el:option value="60">西南</html-el:option>
				<html-el:option value="70">华中</html-el:option>
				<html-el:option value="80">西北</html-el:option>
              </html-el:select>
              <span onclick="$(this).next().toggle();">点击查询大区按行政区域划分参考标准</span>
              <div style="display:none;">
				华东：上海、杭州、温州、苏州、南京、南通、徐州、合肥、芜湖（9个省、市）。<br/>
               	山东：济南、青岛、济宁、淄博（4个省、市）。<br/>
                                                        东北：哈尔滨、齐齐哈尔、长春、沈阳、锦州（5个省、市）。<br/>
                                                        华北：北京、天津、石家庄（3个省、市）。<br/>
                                                        华南：深圳、东莞、广州、佛山、茂名、厦门、福州、海口（8个省、市）。<br/>
                                                        西南：昆明、贵阳、成都、内江、重庆、南宁（6个省、市）。<br/>
                                                        华中：长沙、武汉、南昌、衡阳、荆州、郑州、南阳（7个省、市）。<br/>
                                                        西北：西安、乌鲁木齐、太原、兰州、呼和浩特（5个省、市）。<br/>
              </div>
            </td>
          </tr>
          <tr>
            <td height="28">所属地区：</td>
            <td><html-el:select property="p_index" styleId="p_index" styleClass="bd">
                <html-el:option value="">请选择省/直辖市/自治区</html-el:option>
                <c:forEach items="${provinceList}" var="cur">
                  <html-el:option value="${cur.p_index}">${cur.p_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr>
            <td height="28">部门性质：</td>
            <td><html-el:select property="dept_type" styleId="dept_type">
                <html-el:option value="">请选择...</html-el:option>
              </html-el:select>
            </td>
          </tr>
          <tr id="jb_tr" style="display:none;">
            <td height="28">经办类型</td>
            <td><html-el:select property="jb_type" styleId="jb_type">
                <html-el:option value="1">A类经办</html-el:option>
                <html-el:option value="2">B类经办</html-el:option>
                <html-el:option value="3">C类经办</html-el:option>
                <html-el:option value="4">其他</html-el:option>
              </html-el:select>
            </td>
          </tr>
          <tr>
            <td height="28">部门领导：</td>
            <td><html-el:select property="leader_user_id" styleId="leader_user_id">
                <html-el:option value="">请选择...</html-el:option>
                <c:forEach items="${leaderList}" var="cur">
                  <html-el:option value="${cur.id}">${cur.map.user_name_dept}</html-el:option>
                </c:forEach>
              </html-el:select>
            </td>
          </tr>
          <tr>
            <td>管辖区域：</td>
            <td height="220"><!--  -->
              <select name="province" id="province" class="bd">
                <option value="">-请选择省/直辖市/自治区-</option>
              </select>
              <select name="city" id="city" class="bd">
                <option value="">-请选择市-</option>
              </select>
              <br/>
                <%@ include file="/commons/pages/areamovediv.jsp"%>
              <br/>
            </td>
          </tr>
          <tr>
            <td height="50">部门状态：</td>
            <td>
            	<html-el:select property="is_del" styleId="is_del">
	                <html-el:option value="0">启用</html-el:option>
	                <html-el:option value="1">禁用</html-el:option>
                </html-el:select>
                 <span style="color:#F00; margin-left: 10px">如果当前部门以及当前部门子部门有正式员工,不允许禁用</span>
            </td>
          </tr>
          <tr>
            <td height="50">部门描述：</td>
            <td><html-el:text property="dept_desc" styleId="dept_desc" size="40"  maxlength="50"/></td>
          </tr>
          <tr>
            <td height="28">排序值：</td>
            <td><html-el:text property="order_value" styleId="order_value" size="4" maxlength="4" /></td>
          </tr>
          <tr>
            <td colspan="2" style="text-align:center" height="28">
              <c:if test="${af.map.operate_type eq 1}">
                <input class="but4" type="button" name="Submit4" value="保存 " id="btn_submit" />
                <input class="btn_reset" type="reset"  value="重填 " />
              </c:if>
              <input class="but5" type="button" name="Submit5" value="返回"  onclick="history.back();" />
            </td>
          </tr>
        </table>
      </html-el:form> 
    </c:if>
    <!-- 编辑状态 -->
    
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<%@ include file="/commons/pages/areamove.jsp"%>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
	
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var ti = '${out_time}'; 
	if(ti=='true'){
		alert("请在早上8:00至10:00时间段内修改组织架构！");
	}
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });	
	
	

	//tabs切换Begin
	$("#content div[id^=tab]").hide(); // Initially hide all content
    $("#tabs li:first").attr("id","current"); // Activate first tab
    $("#content div[id^=tab]:first").fadeIn(); // Show first tab content
    $('#tabs a').click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         	return       
        } else{             
	        $("#content div[id^=tab]").hide(); //Hide all content
	        $("#tabs li").attr("id",""); //Reset id's
	        $(this).parent().attr("id","current"); // Activate this
	        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
        }
        window.parent.resizeFrameHeight('mainFrame', 3);
    });
	//tabs切换End
	
	
	
	$("#order_value").focus(setOnlyNum);
	if ("" != "${af.map.leader_user_id}") {
		$("#leader_user_id option[value='${af.map.leader_user_id}']").attr("selected", "selected");
	}
	
	if ("" != "${af.map.add}") { //添加该部门的子部门
		$("#par_id").val("${af.map.par_dept_id}");
	}
    <c:if test="${not empty af.map.bj}">
	if ("" != "${af.map.bj}") {
		if (0 == "${af.map.id}" || 0 == "${af.map.par_dept_id}") { //添加事业部、修改事业部
			$("#dept_type").empty();
			$("#dept_type").get(0).options.add(new Option("请选择...", ""));
			$("#dept_type").get(0).options.add(new Option("事业部", 2));
			$("#dept_type").get(0).options.add(new Option("分公司", 3));
			$("#dept_type").get(0).options.add(new Option("其他组织", 1));
		} else {
			$("#dept_type").empty();
			$("#dept_type").get(0).options.add(new Option("请选择...", ""));
			$("#dept_type").get(0).options.add(new Option("经营部", 4));
			$("#dept_type").get(0).options.add(new Option("办事处", 5));
			$("#dept_type").get(0).options.add(new Option("代理商", 6));
			$("#dept_type").get(0).options.add(new Option("终端", 7));
			$("#dept_type").get(0).options.add(new Option("职能部门", 8));
			$("#dept_type").get(0).options.add(new Option("其他", 9));
		}

		if ("" != "${af.map.edit}") {
			$("#dept_type").val("${af.map.dept_type}"); //回显

			var dept_type = '${af.map.dept_type}';
			if(dept_type == 4 || dept_type == 5){
				$("#jb_tr").show();
			}
		}
	}
	
	$("#dept_name").attr("dataType", "Require").attr("msg", "请填写部门名称！");
	$("#dept_sn").attr("dataType", "Require").attr("msg", "请填写部门编码！");
	$("#p_index").attr("dataType", "Require").attr("msg", "请选择所属地区！");
	$("#dept_type").attr("dataType", "Require").attr("msg", "请选择部门性质！");
	

	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city"    ).attr({"defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);

	$("#dept_type").change(function(){
		if(this.value == 4 || this.value == 5){
			$("#jb_tr").show();
		} else {
			$("#jb_tr").val("");
			$("#jb_tr").hide();
		}
	});
	
	$("#province").change(function(){
		var province = $("#province").val();
		$.ajax({
				type: "POST",
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getBaseProvinceList&is_managered=0&p_index=" + province,
				dataType: "json",
				success: function(Datas) {
					createSelectareas(Datas,"0");
				}
		});
	});
	
	$("#city").change(function(){
		var city = $("#city").val();
		$.ajax({
				type: "POST",
				url: "${ctx}/manager/admin/CsAjax.do",
				data: "method=getBaseProvinceList&is_managered=0&p_index=" + city,
				dataType: "json",
				success: function(Datas) {
					createSelectareas(Datas,"0");
				}
		});
	});
	
</c:if>
});

$("#dept_name").blur(function() {
	if(this.value.length > 0 && this.value != '${af.map.dept_name}'){
		$("#loading").ajaxStart(function(){$(this).show();});
		$("#loading").ajaxStop (function(){$(this).hide();});
		$("#tip").remove();
		$.ajax({
				type: "POST",
				url: "KonkaDept.do",
				data: "method=validateDeptName&dept_name=" + this.value + "&dept_id=${af.map.dept_id}",
				dataType: "json",
				error: function(request, settings) {alert("数据加载请求失败！"); },
				success: function(isExist) {
					if(isExist == 1) {
						$("#btn_submit").attr("disabled",true);
						$("#s_after").after('<span id="tip" style="color:#FF0000;"><img src="${ctx}/images/reg_error.gif" />&nbsp;对不起，该部门名称已存在！<\/span>');
					} else if(isExist == 0){
						$("#btn_submit").removeAttr("disabled");
						$("#s_after").after('<span id="tip" style="color:#5A8E4A;"><img src="${ctx}/images/reg_success.gif" />&nbsp;恭喜，该部门名称可用。<\/span>');
					} 
				}
		});
	} else {
		$("#tip").remove();
	}
});

$("#dept_sn").blur(function(){
	if(this.value.length > 0 && this.value != $(this).parent().data("orig")){
		$("#loading1").ajaxStart(function(){$(this).show();});
		$("#loading1").ajaxStop (function(){$(this).hide();});
		$("#tip1").remove();
		$.ajax({
			type:"POST",
			url:"KonkaDept.do",
			data: "method=validateDeptSN&dept_sn=" + this.value,
			dataType: "json",
			error: function(request, settings) {alert("数据加载请求失败！"); },
			success: function(isExist) {
				if(isExist == 1) {
					$("#btn_submit").attr("disabled", true);
					$("#s_after1").after('<span id="tip1" style="color:#FF0000;"><img src="${ctx}/images/reg_error.gif" />&nbsp;对不起，该部门编码已存在！<\/span>');
				} else if(isExist == 0){
					$("#btn_submit").removeAttr("disabled");
					$("#s_after1").after('<span id="tip1" style="color:#5A8E4A;"><img src="${ctx}/images/reg_success.gif" />&nbsp;恭喜，该部门编码可用。<\/span>');
				} 
			}
		});
	} else {
		$("#tip1").remove();
	}
});


//提交保存
$("#btn_submit").click(function() {
	if(Validator.Validate(this.form, 3)){
		this.form.submit();
	}
});


function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "").bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

////////////////////////区域间移动-----start/////////////////////
<c:if test="${not empty af.map.bj}">
	var areaMove = new AreaMove("areaMove","areaList0","areaList1");
	areaMove.show_key = ["p_name"];
	areaMove.input_id_key = ["p_index"];
	areaMove.input_name_key = ["id_name","id_name"];
	areaMove.input_value_key = ["p_index","p_name"];
	areaMove.setup();
	// JSON 方式添加
	function createSelectareas(Datas,flg){       	            
	    if (Datas != null && Datas.length > 0) {
	   	    areaMove.removeAreaElements(flg);
	   	    for(var i = 0; i < Datas.length; i++) {
	   			var jsonData = {p_index:Datas[i][1],p_name:Datas[i][0]};
	   			areaMove.createAreaElement(jsonData,0);  
	   	    }
	    }else{
	      alert("当前区域没有可供管辖的子区域！");
	    }
	}
	// 修改操作区域初始化
	var areas_ids = '${af.map.m_areas_ids}';
	var areas_names = '${af.map.m_areas_names}';
	if(areas_ids != "" && areas_names != ""){
		var arr_ids = areas_ids.split(",");
		var arr_names = areas_names.split(",");	
		for(var i = 0;i< arr_ids.length;i++){
			var jsonData = {p_index:arr_ids[i],p_name:arr_names[i]};
			areaMove.createAreaElement(jsonData,1);
		}
	}
</c:if>
////////////////////////区域间移动-----end/////////////////////

//弹出当前网点的网点信息
function getKonkaR3ShopInfo(info_id){
	this.info_id = info_id;
	var returnValue = window.showModalDialog("KonkaDept.do?method=view&id="+info_id+"&mod_id=${af.map.mod_id}&azaz=" + Math.random(),window,"dialogWidth:700px;status:no;dialogHeight:550px");
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
