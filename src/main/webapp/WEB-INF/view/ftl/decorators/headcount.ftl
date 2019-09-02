<#--统计的头-->
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

</div>
</div>


