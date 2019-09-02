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
  });
 </script>

	<#include "headindex.ftl" />
    <!--left-->
  <div class="main_left" id="left">
		<div class="left_title" id="div_0">
			<span>用户信息查询</span>
		</div>
		<div class="left_em">
			<ul>
				  <li id="li01"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/user/index.htm?li=li01&div=div_0&type=1"/>">老师查询</a></li>              
                  <li id="li02"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/user/index.htm?li=li02&div=div_0&type=2"/>">学员查询</a></li>
				  <li id="li03"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/userController/self_edit.htm?li=li03&div=div_0"/>">修改信息</a></li>
				
			</ul>
		</div>
		
		 <#if (loginSession.userRole?? && loginSession.userRole =='h') && (loginSession.priMap["100"])!'false' == "true" >
            <div class="left_title" id="div_1" ><span>基本信息维护</span></div>
            <div class="left_em">
                <ul>               
          	      <li id="li14"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/user/cheRegist.htm?li=li14&div=div_1&status=0"/>">用户审核</a></li>              
          	      <li id="li10"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/user/index.htm?li=li10&div=div_1&type=1"/>">老师账号设置</a></li>              
                  <li id="li13"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/user/index.htm?li=li13&div=div_1&type=2"/>">学员信息设置</a></li>
                  <!-- <li id="li11"><img src="${uri.imgsite}/qy_add.gif"><a href="<@spring.url "/permissionController/tolistper.htm?li=li11&div=div_1&menuOrder=1"/>">用户权限管理</a></li> 
          		  -->
          		  <li id="li12"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/userRoleController/tolistrole.htm?li=li12&div=div_1&menuOrder=1"/>">用户角色管理</a></li>
        		  <li id="li16"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/userContrller/insertStu_index.htm?li=li16&div=div_1&menuOrder=1"/>">信息录入</a></li>
          		  <li id="li17"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/commonController/add.htm?li=li17&div=div_1&menuOrder=1"/>">字典添加</a></li>
          		  <li id="li15"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/overallScoreModel/index.htm?li=li18&div=div_1&menuOrder=1"/>">360评分模板</a></li>
                </ul>
            </div> 
         </#if>
	</div>
	<div class="left_io" id="switchPoint" onclick="switchSysBar()">3</div>
