<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td nowrap="nowrap" width="10"></td>
          <td nowrap="nowrap" width="110" align="right">网点转化方向：</td>
          <td><html-el:select property="convertShopDirection"
			styleId="convertShopDirection" onchange="changeDirection(this)">
              <html-el:option value="0">经销商</html-el:option>
              <html-el:option value="1">R3用户</html-el:option>
            </html-el:select></td>
        </tr>
        <tr id="dls_tr">
          <td nowrap="nowrap" width="10"></td>
          <td nowrap="nowrap" width="110" align="right">请选择代理商：</td>
		  <td>
		    <html-el:text property="konka_r3_name" styleId="konka_r3_name" readonly="true" /> 
		    <html-el:hidden property="konka_r3_id" styleId="konka_r3_id" />
            <input type='button' value='选择' onclick="getAgentsList();" /></td>
        </tr>
        <tr id="r3_tr" style="display: none;">
          <td nowrap="nowrap" width="10"></td>
          <td nowrap="nowrap" width="110" align="right">
             <table width="100%" style="height:350px;padding-left:15px;border-left:1px solid #CCCCCC;border-top:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC">      
               <tbody>
                <tr><td align="right"> 已创建R3用户信息：</td>
                </tr>
              </tbody>
             </table>
          </td>
          <td><table style="padding-left:15px;border:1px solid #CCCCCC"">
			<tbody>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right" width="20%">R3网点名称（客户名称）：</td>
                    <td><c:out value="${af.map.customer_name}"/></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">所在区域名称：</td>
					<td><c:out value="${af.map.area_name}" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">分公司所在地名称：</td>
					<td><c:out value="${af.map.branch_area_name}" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">客户群类型：</td>
					<td>${af.map.customer_type}</td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">交易状态：</td>
					<td><c:if test="${af.map.status eq 1}">
						<c:out value="有交易" />
					</c:if> <c:if test="${af.map.status eq 2}">
						<c:out value="无交易" />
					</c:if></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">R3编码：</td>
					<td><c:out value="${af.map.r3_code}" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">经办名称：</td>
					<td><c:out value="${af.map.handle_name}" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">合并客户编码：</td>
					<td><c:out value="${af.map.customer_code}" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">备注：</td>
					<td><c:out value="${af.map.r3_desc}" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">2010合并编码：</td>
					<td><c:out value="${af.map.merge_code_2010}" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">导入日期：</td>
					<td><fmt:formatDate value="${af.map.import_date}"
						pattern="yyyy-mm-dd" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap" class="title_item" align="right">导入人姓名：</td>
					<td><c:out value="${af.map.map.import_user_name}" /></td>
				</tr>
			</tbody>
		  </table></td>
        </tr>
      </table>