
 <script>
     $(document).ready(function(){
            if("${li!}" != ''){
                $("#"+"${li!}").addClass("on");
                $("#"+"${li!}").parent().parent().attr("style","display:block");
                $("#"+"${div!}").addClass("left_title on");
            }
            var menuOrder = "${menuOrder!0}";
            var currentMenu = $(".b_dh_info ul").find("li[id='menuhead"+menuOrder+"']");
            currentMenu.addClass("on");
            currentMenu.removeAttr("onmouseover");
            currentMenu.removeAttr("onmouseout");
            documentReady();
            startclock();
            filterAjaxRequest("<@spring.url '/index.htm'/>");
         });
 </script>
<div class="main_head">
	<div class="header" id="topheader">
</div>
	
	

<div class="b_dh" id="header">
	<div class="user_info">
   		<div class="user_info_con">
        	<ul>
           		<li><img src="${uri.imgsite}/top_img.gif"/></li>
                <li class="text"><font color="#424242">欢迎：</font>
                	<span>${loginSession.obj.realName!}</span>
                	
                	&nbsp;&nbsp;&nbsp;<span><a href="<@spring.url '/loginout.htm'/>">退出</a></span><br/><span id = "time1" class="text">${loginSession.obj.baseName!}</span></li>
            </ul>
     	 </div>
    </div>

        		<#--进入之后，显示与自己最相关的任务-->
    <div class="b_dh_con" style="width:80%;">
        <div class="b_dh_info">
        	<ul>
        		<#if (loginSession.priMap["200"])!'false' == "true">
        			<li id="menuhead1" onmouseover="this.className='on'" onmouseout="this.className=''"><a class="li_t" href="<@spring.url '/menuController/index.htm?userRole=h&menuOrder=1'/>">管理医院</a></li>
        		</#if> 
        		<#if (loginSession.priMap["400"])!'false' == "true"> 
                	<li id="menuhead2" onmouseover="this.className='on'" onmouseout="this.className=''"><a class="li_t" href="<@spring.url '/menuController/index.htm?userRole=b&menuOrder=2'/>">基地管理</a></li>
                </#if> 
        		<#if (loginSession.priMap["500"])!'false' == "true"> 
                	<li id="menuhead3" onmouseover="this.className='on'" onmouseout="this.className=''"><a class="li_t" href="<@spring.url '/menuController/index.htm?userRole=r&menuOrder=3'/>">科室管理</a></li>
                </#if> 
        		<#if (loginSession.priMap["600"])!'false' == "true"> 
                	<li id="menuhead4" onmouseover="this.className='on'" onmouseout="this.className=''"><a class="li_t" href="<@spring.url '/menuController/index.htm?userRole=t&menuOrder=4'/>">带教老师</a></li>
                </#if> 
        		<#if (loginSession.priMap["700"])!'false' == "true"> 
                	<li id="menuhead5" onmouseover="this.className='on'" onmouseout="this.className=''"><a class="li_t" href="<@spring.url '/menuController/index.htm&menuOrder=5'/>">住院医师</a></li>
            	</#if> 
                
            </ul>
        </div>
        <div class="mbx" id>
        	<div id="mapPosition"></div>
        </div>
    </div>
</div>
</div>


