<#import "spring.ftl" as spring />
<#--
  * 格式化金钱
  -->
<#function formatMoney numVal format=",##0.00" defaultVal="--">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#return (numVal/100)?string(format)>
		<#else>
			<#return defaultVal>
		</#if>	
	<#else>
		<#return defaultVal>
	</#if>	
</#function>
<#--
  * 格式化金钱,不除以100
  -->
<#function formatMoneyNo100 numVal format=",##0.00" defaultVal="--">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#return numVal?string(format)>
		<#else>
			<#return defaultVal>
		</#if>	
	<#else>
		<#return defaultVal>
	</#if>	
</#function>
<#--格式化金钱，不采用逗号隔开-->
<#function formatMoneyNoComma numVal format="##0.00" defaultVal="--">
	<#if numVal?has_content>
		<#if numVal?is_number>
			<#return (numVal/100)?string(format)>
		<#else>
			<#return defaultVal>
		</#if>	
	<#else>
		<#return defaultVal>
	</#if>	
</#function>

<#--
  * 格式化日期
  -->
<#function formatDate dateVal format="yyyy-MM-dd" defaultVal="--">
	<#if dateVal?has_content>
	    <#return dateVal!?string(format)>
	<#else>
		<#return defaultVal>
	</#if>	
</#function>

<#--
  * 格式化日期
  -->
<#function formatDateToNum dateVal format="yyyyMMdd" defaultVal="--">
	<#if dateVal?has_content>
	    <#return dateVal!?string(format)>
	<#else>
		<#return defaultVal>
	</#if>	
</#function>

<#--
  * 格式化日期时间
  -->
<#function formatDateTime dateVal format="yyyy-MM-dd HH:mm" defaultVal="--">
	<#if dateVal?has_content>
	    <#return dateVal!?string(format)>
	<#else>
		<#return defaultVal>
	</#if>	
</#function>

<#function formatDateTimeSS dateVal format="yyyy-MM-dd HH:mm:ss" defaultVal="--">
	<#if dateVal?has_content>
	    <#return dateVal!?string(format)>
	<#else>
		<#return defaultVal>
	</#if>	
</#function>

<#macro checkSelected key value>
    <#if key?is_number && key == value?number>selected="selected"</#if>
    <#if key?is_string && key == value>selected="selected"</#if>
</#macro>

<#--下拉框-->
<#macro singleSelectWithBlank name options  value selectTop=""  attributes="">
   
    <select id="${name}" name="${name}" ${attributes}>
        <#if selectTop!=""><option value="-1">${selectTop}</option></#if>
        <#list options?keys as key>
        <option value="${key?html}"<@checkSelected key value/>>${options[key]?html}</option>
        </#list>
    </select>
</#macro>

<#--下拉框2-->
<#macro singleSelectWithBlank2 name options  value selectTop="" attributes="">
   
    <select id="${name}" name="${name}" ${attributes}>
        <option value="-1">${selectTop}</option>
        <#list options?keys as key>
        <#if key?is_number && key<=-1 || key?number<=-1>
       	 	<optgroup label="${options[key]?html}" style="color:orange" ></optgroup>
        <#else>
       		<option value="${options[key]?html}"<@checkSelected key value/> >${options[key]?html}</option>
        </#if>
        </#list>
    </select>
</#macro>

<!-- 格式化日期-->
<#macro fromatDate date>
    <#assign years = date?substring(0,5)>
     <#assign month = date?substring(5,7)>
     <#assign day = date?substring(7,date?length)>
     ${years+"-"+month+"-"+day}
</#macro>


<#--
  * 隐藏银行帐号
  -->
<#macro encBankAcct value=''>
	<#--变量长度 -->
	<#assign vlen = value?length>
	<#compress>
	<#if vlen lt 9>
		${value}
	  <#else>
	  	${value?substring(0, 4)}
	  	<#list 0..vlen - 8 -1 as i>
	  	 <#t>*
	  	</#list>
	  	${value?substring(vlen -4, vlen)}
	</#if>
	</#compress>
</#macro>

<#--
  * 期号
  -->
<#macro issueChoose issue>
	<tr>
		<td class="td2" valign="top" align="right">起始</td>
		<td valign="top"><select name="startIssue" id="startIssue" onChange="checkingBeginIssue()" class="select2">
		<#if issue?? && issue?size gt 0>
        <#list issue as r>
        <option value="${r.issue}">${r.issue}</option>
        </#list>
        </#if>
		</select></td>
		<td valign="top" align="right">截止</td>
		<td valign="top"><select name="endIssue" id="endIssue" onChange="checkingEndIssue()" class="select2">
		<#if issue?? && issue?size gt 0>
        <#list issue as r>
        <option value="${r.issue}">${r.issue}</option>
        </#list>
        </#if>
		</select></td>
	</tr>
</#macro>

<#macro selectOption options value="" >
        <#list options?keys as key>
        <option value="${key?html}"<@checkSelected key value/>>${options[key]?html}</option>
        </#list>
</#macro>
<#macro pageUrl url targetpage formID="null" targetID="dataDiv2" asyn = true>
    <#assign splitChar = "?">
     <#if url?index_of('?') gt 0>
       <#assign splitChar = "&">
    </#if>
    <#if asyn == true>
    	<#assign seq = "javascript:pagination({url:'"+ url + splitChar +"targetpage="+targetpage+"',form:'"+formID+"',id:'"+targetID+"'}) ">
     <#else>
    	<#assign seq = url + splitChar +"targetpage="+targetpage>
    </#if>
     ${seq}
</#macro>
<#--
	分页
	-->
<#macro page asyn = false>
<table class="main_page foot_set">
	<tr>
		<td>&nbsp;
			<#if pageview.page.recordCount gt 0>
			搜索到 <b>${pageview.page.recordCount}</b> 条记录 共 <b>${pageview.page.totalPage}</b> 页 第 <b>${pageview.page.targetPage}</b> 页 每页 <b>${pageview.page.pageSize}</b>	条记录
			<span class="page_num">
				<#if pageview.page.targetPage gt 1>
				<a href="<@pageUrl pageview.page.fullUrl pageview.page.targetPage-1 pageview.page.formID pageview.page.targetID true/>">上一页</a>&nbsp;
				</#if>
				 <#list pageview.serialPageList as rs>
	               <#if rs?is_number>
	                    <#if rs == pageview.page.targetPage>
		               		<#if pageview.page.totalPage gt 1>
			                    &nbsp;<a href="javascript:void(0)" style="color:red">${rs}</a>
		               		</#if>
	                    <#else>&nbsp;<a href="<@pageUrl pageview.page.fullUrl rs pageview.page.formID pageview.page.targetID true/>">${rs}</a>
	                    </#if>
	              	<#else>
	                &nbsp;...
	                </#if>
	             </#list>
				<#if pageview.page.targetPage lt pageview.page.totalPage>
				<a href="<@pageUrl pageview.page.fullUrl pageview.page.targetPage+1 pageview.page.formID pageview.page.targetID true/>">下一页</a>&nbsp;
				</#if>
			</#if>
			</span>
		</td>
	</tr>
</table>
</#macro>

<#--定义权限树的宏buildNode-->
<#macro buildNode parent>  
	
	<#if parent.privilege.operations?? && parent.privilege.operations?size gt 0>
		<li>
			<ul>
				<#list parent.op as op>
					<li >
						<#if parent.opSelected[op_index]>
							<input name="node" type="checkbox" checked="checked" value="${op.name!}" onclick="onSelected(${parent.privilege.privilegeCode!},this.checked,${op_index})"/>${op.des!}
						<#else>
							<input name="node" type="checkbox" value="${op.name!}" onclick="onSelected(${parent.privilege.privilegeCode!},this.checked,${op_index})"/>${op.des!}
						</#if>
					</li>   
				</#list>
			</ul>
		</li>
	</#if>
	
    <#if parent.children?? && parent.children?size gt 0> 
    	<li>  
	        <ul>  
	            <#list parent.children as t>
	            	<#if t.privilege.privilegeCode?length == 5>
	            		<li class="con_title">
	            	<#elseif t.hasOperation!>	
	            		<li class="con_title2">
	            	<#else>
	            		<li>
	            	</#if>
						<#if t.selected>
							<input name="node" type="checkbox" checked="checked" value="${t.privilege.privilegeCode!}" onclick="onSelected(${t.privilege.privilegeCode!},this.checked,'')"/>${t.privilege.privilegeDes!}
						<#else>
							<input name="node" type="checkbox" value="${t.privilege.privilegeCode!}" onclick="onSelected(${t.privilege.privilegeCode!},this.checked,'')"/>${t.privilege.privilegeDes!}
						</#if>
					</li>
	                <#-- 递归调用宏buildNode -->  
	                <@util.buildNode parent=t/>  
	            </#list>   
	        </ul>  
        </li>
    </#if>  
</#macro>

<#macro type typeId>
	<#if typeId==15>单选题<#elseif typeId==18>多选题<#elseif typeId==25>病例题<#elseif typeId==26>配伍题<#elseif typeId==27>是非题<#elseif typeId==28>填空题<#elseif typeId==29>名词解释<#elseif typeId==30>简答题<#elseif typeId==31>论述题<#elseif typeId==33>A2单选题<#else>案例分析题</#if>
</#macro>  




