 <#--统计的左侧-->
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

	<#include "headcount.ftl" />
    <!--left-->
  <div class="main_left" id="left">
        <#if (loginSession.userRole?? && loginSession.userRole =='h') && (loginSession.priMap["100"])!'false' == "true" >
             <div class="left_title" id="div_1"><span>汇总统计</span></div> 
             <div class="left_em">
                <ul>          
              		<li id="li10"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/countController/index.htm?li=li10&div=div_1&menuOrder=1&type=t"/>">老师月度统计</a></li>
              		<li id="li11"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/countController/index.htm?li=li11&div=div_1&menuOrder=1&type=r"/>">科室月度统计</a></li>
              		<li id="li12"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/countController/index.htm?li=li12&div=div_1&menuOrder=1&type=b"/>">基地月度统计</a></li>
              		<li id="li13"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/countController/tindex.htm?li=li13&div=div_1&menuOrder=1&type=m"/>">科室教学统计</a></li>   
			   </ul>
            </div>
             <div class="left_title" id="div_2"><span>学生统计</span></div> 
             <div class="left_em">
                <ul>
              		<li id="li20"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/countSController/index.htm?li=li20&div=div_2&menuOrder=1&type=t"/>">学生出科汇总</a></li>
              		<li id="li21"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/countSController/index.htm?li=li21&div=div_2&menuOrder=1&type=r"/>">科室月度统计</a></li>
              		<li id="li22"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/countSController/index.htm?li=li22&div=div_2&menuOrder=1&type=b"/>">基地月度统计</a></li>
			   </ul>
            </div>

            <div class="left_title" id="div_3"><span>图形显示</span></div>
            <div class="left_em">
                <ul>
                    <li id="li13"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/chartController/index.htm?li=li12&div=div_1&menuOrder=1&type=b"/>">基地统计</a></li>
                   </ul>
            </div>

         </#if>
        
	</div>
    <div class="left_io" id="switchPoint" onclick="switchSysBar()">3</div>
