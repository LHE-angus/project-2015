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
  <div class="rtabcont2">
					<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">分公司：</td>
							<td align="left">
							<span>${af.map.dept_name}</span>
							</td>
							<td nowrap="nowrap" class="title_item" align="right">单据编码：</td>
							<td align="left"><span><font color="red">${af.map.bill_sn}</font></span>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">合同名称：</td>
							<td align="left">${af.map.con_name}
							</td>
							<td nowrap="nowrap" class="title_item" align="right">合同编码：</td>
							<td align="left">${fn:escapeXml(af.map.con_sn)}</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">授权编号：</td>
							<td align="left" colspan="3">${af.map.sq_sn}
							</td>
						</tr>
						<tr>
					          <td align="right" nowrap="nowrap" class="title_item">客户R3编码：</td>
					          <td>
					          ${af.map.r3_code}
					          </td>
					          <td nowrap="nowrap" align="right" class="title_item">客户名称：</td>
					          <td>
					          ${af.map.customer_name}
					          </td>
       					 </tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">签订时间：</td>
							<td align="left"><fmt:formatDate value="${af.map.sign_date}" pattern="yyyy-MM-dd" /></td>
							<td nowrap="nowrap" class="title_item" align="right">合同期限：</td>
							<td align="left">
								<fmt:formatDate value="${af.map.s_date}" pattern="yyyy-MM-dd" />
								至
								<fmt:formatDate value="${af.map.e_date}" pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">合同基本目标（万元）：</td>
							<td align="left">${af.map.con_base_money}
							</td>
							<td nowrap="nowrap" class="title_item" align="right">合同挑战目标（万元）：</td>
							<td align="left">${af.map.con_expect_money}</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">业务类型：</td>
							<td align="left" ><c:if test="${af.map.c_type eq 1}">加价</c:if>
            	<c:if test="${af.map.c_type eq 2}">倒扣</c:if>
							</td>
							<td nowrap="nowrap" class="title_item" align="right">规模返利：</td>
							<td align="left"> ${af.map.scale_sb}%</td>	
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">月返</td>
							<td align="left">	${af.map.month_sb}%
							</td>
							<td nowrap="nowrap" class="title_item" align="right">特价机点位（百分比）：</td>
							<td align="left">	${af.map.tjj_money}%</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">上年销售额（万元）：</td>
							<td align="left" colspan="3">	${af.map.last_year_sale}
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">其他返利（万元）：</td>
							<td align="left">${af.map.other_sb}%
							</td>
							<td nowrap="nowrap" class="title_item" align="right">工程机点位（百分比）：</td>
							<td align="left">	${af.map.gcj_money}%</td>
						</tr>
						<tr>
				          <td width="15%" nowrap="nowrap" class="title_item" align="right">其他返利备注：</td>
				          <td width="88%" align="left">
				          	${af.map.o_remark}
				          	</td>
				        </tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">合同条款：</td>
							<td align="left" colspan="3">${af.map.con_content }</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">洽谈甲方：</td>
							<td align="left">${af.map.talks_man_a}
							</td>
							<td nowrap="nowrap" class="title_item" align="right">洽谈乙方：</td>
							<td align="left">${af.map.talks_man_b }</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">经营部：</td>
							<td align="left">
							${af.map.jyb_name}
							</td>
							<td nowrap="nowrap" class="title_item" align="right">业务员：</td>
							<td align="left">
							${af.map.ywy_name}
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">制单人：</td>
							<td align="left">${af.map.add_user_name}
							</td>
							<td nowrap="nowrap" class="title_item" align="right">创建日期：</td>
							<td align="left"><fmt:formatDate  value="${af.map.add_date}" pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
				          <td width="15%" nowrap="nowrap" class="title_item" align="right">备注：</td>
				          <td width="88%" align="left">
				          	${af.map.demo}
				          </td>
				        </tr>
			          <c:if test="${not empty attachmentList}">
			          <tr>
			            <td height="28" class="title_item" align="right">已上传的附件：</td>
			            <td colspan="3"><ol>
			                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
			                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;</li>
			                </c:forEach>
			              </ol></td>
			            </tr>
			           </c:if>
			           <tr>
							<td nowrap="nowrap" class="title_item" align="right">合同期结算额（万元）：</td>
							<td align="left">${af.map.con_date_money}
							</td>
							<td nowrap="nowrap" class="title_item" align="right">基本目标完成率（百分比）：</td>
							<td align="left">${af.map.base_target_wcl}%</td>
						</tr>
						 <tr>
							<td nowrap="nowrap" class="title_item" align="right">合同期回款额（万元）：</td>
							<td align="left" colspan="3">${af.map.con_date_backmoney}
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td colspan="3" align="center">
					            <input class="bgButtonBack" type="button" name="reset" value="返回" id="btn_back" onclick="history.back();return false;"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="height: 150px;">&nbsp;</td>
						</tr>
					</table>
			</div>
		</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
