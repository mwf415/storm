<#import "../decorators/spring.ftl" as spring />
<#import "../decorators/uri.ftl" as uri />
<#import "../decorators/util.ftl" as util />
<script src="${uri.jssite}/js01.js" type="text/javascript"  ></script>
<script src="${uri.jssite}/timer.js" type="text/javascript"  ></script>
<link href="${uri.csssite}/styles.css" rel="stylesheet" type="text/css"/>
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
	<div class="header" id="topheader" ></div>
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
	
	    <div class="b_dh_con" style="width:80%;">
	        <div class="b_dh_info">
	        	<ul>
	               	<li id="menuhead1" onmouseover="this.className='on'" onmouseout="this.className=''"><a href="<@spring.url '/index/indexlist.htm'/>">回到首页</a></li>
	
	            </ul>
	        </div>
	        <div class="mbx" id>
	        	<div id="mapPosition"></div>
	        </div>
	    </div>
	</div>
</div>


