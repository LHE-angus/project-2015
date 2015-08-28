<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
    <html-el:form action="/admin/RetailSalesQuery">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="mod_code" value="${af.map.mod_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><ul>
              <li> <strong class="fb">选择渠道</strong>
              <html-el:select property="fgs_dept_id" styleId="fgs_dept_id" style="width:130px;" value="10">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="10">城市渠道</html-el:option>
                  <html-el:option value="">城市客户</html-el:option>
                  <html-el:option value="">工程师</html-el:option>
                  <html-el:option value="">专业连锁</html-el:option>
                  <html-el:option value="">县乡客户</html-el:option>
                  <html-el:option value="">直销户</html-el:option>
                  <html-el:option value="">其它</html-el:option>
                </html-el:select>
              <html-el:select property="fgs_dept_id" styleId="fgs_dept_id" style="width:130px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="">大润发</html-el:option>
                  <html-el:option value="">华联</html-el:option>
                  <html-el:option value="">好又多</html-el:option>
                  <html-el:option value="">华润万家</html-el:option>
                  <html-el:option value="">家乐福</html-el:option>
                  <html-el:option value="">乐购</html-el:option>
                  <html-el:option value="">农工商</html-el:option>
                  <html-el:option value="">麦德龙</html-el:option>
                  <html-el:option value="">人人乐</html-el:option>
                  <html-el:option value="">苏果</html-el:option>
                  <html-el:option value="">沃尔玛</html-el:option>
                  <html-el:option value="">物美</html-el:option>
                  <html-el:option value="">易初莲花</html-el:option>
                  <html-el:option value="">易买得</html-el:option>
                  <html-el:option value="">其它</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb">选择日期</strong>
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  />
                至
                <html-el:text property="key_word" size="10" maxlength="20" styleId="key_word"  /></li>
              <li style="padding-top:3px;"> <strong class="fb">选择机构</strong>
              <html-el:select property="fgs_dept_id" styleId="fgs_dept_id" style="width:260px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="10">城市渠道</html-el:option>
                  <html-el:option value="">城市客户</html-el:option>
                  <html-el:option value="">工程师</html-el:option>
                  <html-el:option value="">专业连锁</html-el:option>
                  <html-el:option value="">县乡客户</html-el:option>
                  <html-el:option value="">直销户</html-el:option>
                  <html-el:option value="">其它</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong class="fb">选择门店</strong>
              <html-el:select property="fgs_dept_id" styleId="fgs_dept_id" style="width:250px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="">大润发</html-el:option>
                  <html-el:option value="">华联</html-el:option>
                  <html-el:option value="">好又多</html-el:option>
                  <html-el:option value="">华润万家</html-el:option>
                  <html-el:option value="">家乐福</html-el:option>
                  <html-el:option value="">乐购</html-el:option>
                  <html-el:option value="">农工商</html-el:option>
                  <html-el:option value="">麦德龙</html-el:option>
                  <html-el:option value="">人人乐</html-el:option>
                  <html-el:option value="">苏果</html-el:option>
                  <html-el:option value="">沃尔玛</html-el:option>
                  <html-el:option value="">物美</html-el:option>
                  <html-el:option value="">易初莲花</html-el:option>
                  <html-el:option value="">易买得</html-el:option>
                  <html-el:option value="">其它</html-el:option>
                </html-el:select></li>
              <li style="padding-top:3px;"> <strong class="fb">产品类别</strong>
              <html-el:select property="fgs_dept_id" styleId="fgs_dept_id" style="width:120px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="10">城市渠道</html-el:option>
                  <html-el:option value="">城市客户</html-el:option>
                  <html-el:option value="">工程师</html-el:option>
                  <html-el:option value="">专业连锁</html-el:option>
                  <html-el:option value="">县乡客户</html-el:option>
                  <html-el:option value="">直销户</html-el:option>
                  <html-el:option value="">其它</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;&nbsp;<strong class="fb">产品型号</strong>
              <html-el:select property="fgs_dept_id" styleId="fgs_dept_id" style="width:120px;">
                  <html-el:option value="">请选择...</html-el:option>
                  <html-el:option value="">大润发</html-el:option>
                  <html-el:option value="">华联</html-el:option>
                  <html-el:option value="">好又多</html-el:option>
                  <html-el:option value="">华润万家</html-el:option>
                  <html-el:option value="">家乐福</html-el:option>
                  <html-el:option value="">乐购</html-el:option>
                  <html-el:option value="">农工商</html-el:option>
                  <html-el:option value="">麦德龙</html-el:option>
                  <html-el:option value="">人人乐</html-el:option>
                  <html-el:option value="">苏果</html-el:option>
                  <html-el:option value="">沃尔玛</html-el:option>
                  <html-el:option value="">物美</html-el:option>
                  <html-el:option value="">易初莲花</html-el:option>
                  <html-el:option value="">易买得</html-el:option>
                  <html-el:option value="">其它</html-el:option>
                </html-el:select>
                &nbsp;&nbsp;&nbsp;<strong class="fb">业务负责人</strong>
              <html-el:text property="customer_name_like" maxlength="20" styleId="customer_name_like" style="width:120px;"  />
                &nbsp;&nbsp;&nbsp;
                <html-el:submit styleClass="but1" value="搜索" />
                &nbsp;<html-el:submit styleClass="but1" value="导出" />
              </li>
            </ul></td>
        </tr>
      </table>
      </html-el:form>