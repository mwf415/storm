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

	<#include "headcycle.ftl" />
    <!--left-->
  <div class="main_left" id="left">
        <#if (loginSession.userRole?? && loginSession.userRole =='h') && (loginSession.priMap["100"])!'false' == "true" >
             <div class="left_title" id="div_6"><span>医院教学管理</span></div> 
             <div class="left_em">
                <ul>          
          		    <li id="li62"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/plotController/plot_index.htm?li=li62&div=div_6&menuOrder=1&type=h"/>"">教学计划</a></li><!--文档格式-->
          		    <li id="li64"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/activesCont/index.htm?li=li64&div=div_6&menuOrder=1&type=h"/>">入科教育</a></li>   
              		<li id="li66"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/activesCont/index.htm?li=li66&div=div_6&menuOrder=1&type=h"/>">教学查房安排</a></li>
              		<li id="li67"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/activesCont/index.htm?li=li67&div=div_6&menuOrder=1&type=h"/>">病例讨论安排</a></li>
              		<li id="li68"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/activesCont/index.htm?li=li68&div=div_6&menuOrder=1&type=h"/>">教学讲座安排</a></li>
              		<li id="li69"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/activesCont/index.htm?li=li69&div=div_6&menuOrder=1&type=h"/>">操作训练计划</a></li>
           			<li id="li65"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li65&div=div_6&menuOrder=1&type=h"/>">出科考试</a></li>  
              		<li id="li63"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/activesCont/index.htm?li=li63&div=div_6&menuOrder=1&type=h"/>">其他活动计划</a></li>   
           			<li id="li61"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/outRoomCont/outroomindex.htm?li=li61&div=div_6&menuOrder=1&type=h"/>">学生出科成绩</a></li>
           			<li id="li610"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/judgeController/index.htm?li=li610&div=div_6&menuOrder=1&type=h"/>">评价与回顾</a></li>
                </ul>
            </div>
         </#if>
         
         <#if  ( loginSession.userRole?? && loginSession.userRole =='b' ) && (loginSession.priMap["400"])!'false' == "true">
            <div class="left_title" id="div_2"><span>专业基地管理</span></div>
            <div class="left_em">
                <ul>
              		<li id="li21"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/plotController/plot_index.htm?li=li21&div=div_2&menuOrder=2"/>">教学计划</a></li><!--是文档格式-->              		
					<li id="li24"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/activesCont/index.htm?li=li24&div=div_2&menuOrder=2&type=b"/>">入科教育</a></li>                 	
              		<li id="li26"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/activesCont/index.htm?li=li26&div=div_2&menuOrder=2&type=b"/>">教学查房</a></li>              		
              		<li id="li27"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/activesCont/index.htm?li=li27&div=div_2&menuOrder=2&type=b"/>">病例讨论</a></li>              		
              		<li id="li28"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/activesCont/index.htm?li=li28&div=div_2&menuOrder=2&type=b"/>">教学讲座</a></li>              		
              		<li id="li29"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/activesCont/index.htm?li=li29&div=div_2&menuOrder=2&type=b"/>">操作训练</a></li>              		
           			<li id="li25"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li25&div=div_2&menuOrder=2&type=b"/>">出科考试</a></li>  
              		<li id="li23"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/activesCont/index.htm?li=li23&div=div_6&menuOrder=1&type=b"/>">其他活动</a></li>
           			<li id="li22"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/outRoomCont/outroomindex.htm?li=li22&div=div_2&menuOrder=2&type=b"/>">学生出科成绩</a></li>
           			<li id="li210"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/judgeController/index.htm?li=li210&div=div_2&menuOrder=2&type=b"/>">评价与回顾</a></li>
              		<li id="li20"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/singleArrTurnCont/index.htm?li=li20&div=div_2&menuOrder=2&type=b"/>">轮转信息查询</a></li>
                </ul>
            </div>
          </#if> 
   
   
          <#if  (loginSession.userRole?? && loginSession.userRole =='r') && (loginSession.priMap["500"])!'false' == "true" > 
            <div class="left_title" id="div_3"><span>科室教学管理</span></div>
            <div class="left_em">
                <ul>
                	<li id="li311"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/singleArrTurnCont/index.htm?li=li311&div=div_3&menuOrder=3&type=r"/>">轮转详情</a></li> 
                	<li id="li31"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/plotController/plot_index.htm?li=li31&div=div_3&menuOrder=3"/>">教学计划</a></li>
                  	<li id="li312"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/arrTeacherCont/arrTeacherindex.htm?li=li312&div=div_3&menuOrder=3&type=r"/>">轮转和教师安排</a></li>
                  	<li id="li34"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/activesCont/index.htm?li=li34&div=div_3&menuOrder=3&type=r"/>">入科教育</a></li>
               		<li id="li36"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li36&div=div_3&menuOrder=3&type=r"/>">教学查房</a></li>
              		<li id="li37"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/activesCont/index.htm?li=li37&div=div_3&menuOrder=3&type=r"/>">病例讨论</a></li>
              		<li id="li38"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/activesCont/index.htm?li=li38&div=div_3&menuOrder=3&type=r"/>">教学讲座</a></li>
              		<li id="li39"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/activesCont/index.htm?li=li39&div=div_3&menuOrder=3&type=r"/>">操作训练</a></li>
              		<li id="li35"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li35&div=div_3&menuOrder=3&type=r"/>">出科考试</a></li>
              		<li id="li33"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/activesCont/index.htm?li=li33&div=div_3&menuOrder=3&type=r"/>">其他活动</a></li>  
              		<li id="li32"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/outRoomCont/outroomindex.htm?li=li32&div=div_3&menuOrder=3&type=r"/>">学生出科成绩</a></li>
              		<li id="li310"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/judgeController/index.htm?li=li310&div=div_3&menuOrder=3&type=r"/>">评价与回顾</a></li>
              		<li id="li511"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/cyleNoteController/index.htm?li=li511&div=div_5&menuOrder=3&type=r"/>">轮转学习记录</a></li>
              		
                </ul>
            </div>
          </#if> 
          
          <#if (loginSession.userRole?? && loginSession.userRole =='t') && (loginSession.priMap["600"])!'false' == "true" >
            <div class="left_title" id="div_4"><span>带教老师</span></div>
            <div class="left_em">
                <ul>
          	    	<li id="li41"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/teacheringController/index.htm?li=li41&div=div_4&menuOrder=4"/>">所带学生</a></li>              		
              		<li id="li40"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/plotController/plot_index.htm?li=li40&div=div_4&menuOrder=4"/>">教学计划</a></li>              		
              		<li id="li44"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/activesCont/index.htm?li=li44&div=div_4&menuOrder=4&type=t"/>">入科教育</a></li>                  	
                  	<li id="li46"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/activesCont/index.htm?li=li46&div=div_4&menuOrder=4&type=t"/>">教学查房</a></li>                  	
                  	<li id="li47"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/activesCont/index.htm?li=li47&div=div_4&menuOrder=4&type=t"/>">病例讨论</a></li>              		
              		<li id="li48"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/activesCont/index.htm?li=li48&div=div_4&menuOrder=4&type=t"/>">教学讲座</a></li>              		
              		<li id="li49"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li49&div=div_4&menuOrder=4&type=t&proj=0"/>">操作训练</a></li>              		
              		<li id="li45"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li45&div=div_4&menuOrder=4&type=t"/>">出科考试</a></li>
              		  
              		<li id="li43"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/activesCont/index.htm?li=li43&div=div_4&menuOrder=4&type=t"/>">其他活动</a></li>              		
              		<li id="li410"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/outRoomCont/outroomindex.htm?li=li410&div=div_4&menuOrder=4&type=t"/>">学生出科成绩</a></li>              		
               		<li id="li42"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/judgeController/index.htm?li=li42&div=div_4&menuOrder=4&type=t"/>">评价与回顾</a></li>
              		
                </ul>
            </div>
          </#if>
		  
          <#if (loginSession.priMap["700"])!'false' == "true" || loginSession.userType == "2">
             <div class="left_title" id="div_5"><span>住院医师</span></div>
             <div class="left_em">
                <ul>          
              		<li id="li50"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/singleArrTurnCont/index.htm?li=li50&div=div_5&menuOrder=5&type=s"/>">轮转信息查询</a></li>
              		<li id="li51"><img src="${uri.imgsite}/left_icon06.gif"><a href="<@spring.url "/plotController/plot_index.htm?li=li51&div=div_5&menuOrder=5"/>">教学计划</a></li>
              		<li id="li54"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/activesCont/index.htm?li=li54&div=div_5&menuOrder=5&type=s"/>">入科教育</a></li>
               		<li id="li56"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li56&div=div_5&menuOrder=5&type=s"/>">教学查房记录</a></li>
              		<li id="li57"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/activesCont/index.htm?li=li57&div=div_5&menuOrder=5&type=s"/>">病例讨论记录</a></li>
              		<li id="li58"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/activesCont/index.htm?li=li58&div=div_5&menuOrder=5&type=s"/>">教学讲座记录</a></li>
              		<li id="li59"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/activesCont/index.htm?li=li59&div=div_5&menuOrder=5&type=s"/>">操作训练记录</a></li>
              		<li id="li55"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/activesCont/index.htm?li=li55&div=div_5&menuOrder=5&type=s"/>">出科考试</a></li>
              		  
              		<li id="li53"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/activesCont/index.htm?li=li53&div=div_5&menuOrder=1&type=s"/>">其他活动</a></li>
              		<li id="li510"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/outRoomCont/outroomindex.htm?li=li510&div=div_5&menuOrder=5&type=s"/>">出科成绩</a></li>
                	<li id="li52"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/judgeController/index.htm?li=li52&div=div_5&menuOrder=5&type=s"/>">评价与回顾</a></li>
                	<li id="li511"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/cyleNoteController/index.htm?li=li511&div=div_5&menuOrder=5&type=s"/>">轮转学习记录</a></li>
                	<li id="li512"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/studentSelfController/index.htm?li=li512&div=div_5&menuOrder=5&type=s"/>">学习统计</a></li>
                </ul>
            </div>
          </#if>
          
         <#if ( loginSession.userRole?? && loginSession.userRole =='h' ) && (loginSession.priMap["1600"])!'false' == "true"  >
            <div class="left_title" id="div_16" ><span>轮转安排</span></div>
            <div class="left_em">
                <ul>               
                  	<li id="li160"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/arrTurnController/arrturnRule_Index.htm?li=li160&div=div_16&menuOrder=1&type=3"/>">轮转规则(3年)</a></li>
                  	<li id="li165"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/arrTurnController/arrturn_index.htm?li=li165&div=div_16&menuOrder=1&type=3"/>">轮转安排(3年)</a></li>                 	
           			<li id="li162"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/arrTurnCheckController/arrTurnIndex.htm?li=li162&div=div_16&menuOrder=1&type=3"/>">轮转审核(3年)</a></li>
                  	<#--
                  	<li id="li164"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/arrTurnOneController/arrturnindex.htm?li=li164&div=div_16&menuOrder=1&type=3"/>">单独安排(3年)</a></li>
                  	<li id="li161"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/arrTurnController/arrturnRule_Index.htm?li=li161&div=div_16&menuOrder=1&type=2"/>">轮转规则(2年)</a></li> 
                  	<li id="li167"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/arrTurnController/arrturn_index.htm?li=li167&div=div_16&menuOrder=1&type=2"/>">轮转安排(2年)</a></li>                 	
           			<li id="li1612"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/arrTurnCheckController/arrTurnIndex.htm?li=li1612&div=div_16&menuOrder=1&type=2"/>">轮转审核(2年)</a></li>
                  	<li id="li1613"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/arrTurnOneController/arrturnindex.htm?li=li1613&div=div_16&menuOrder=1&type=2"/>">单独安排(2年)</a></li>
                  	-->
                  	<li id="li1611"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/singleArrTurnCont/index.htm?li=li1611&div=div_16&menuOrder=1&type=h"/>">轮转详情</a></li> 
                  	<li id="li1614"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/arrTurnwithteach/index.htm?li=li1614&div=div_16&menuOrder=1&type=h"/>">老师带教</a></li> 
                </ul>
            </div> 
        </#if>
          
          <#if ( loginSession.userRole?? && loginSession.userRole =='h') && (loginSession.priMap["1100"])!'false' == "true" >
          <div class="left_title" id="div_10"><span>轮转信息</span></div> 
            <div class="left_em">
                <ul> 
           			<li id="li104"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/changeArrTurn/index.htm?li=li104&div=div_10&menuOrder=1&type=y"/>">换科查询</a></li>
           			<li id="li101"><img src="${uri.imgsite}/left_icon04.gif"><a href="<@spring.url "/changeArrTurn/index.htm?li=li101&div=div_10&menuOrder=1&type=n"/>">不换科查询</a></li>
             	</ul>
            </div>
          </#if>
          
          <#if ( loginSession.userRole?? && loginSession.userRole =='h')>
            <div class="left_title" id="div_9"><span>评分系统</span></div> 
            <div class="left_em">
                <ul> 
           			<li id="li912"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/gradeController/list.htm?li=li912&div=div_9&menuOrder=1&type=h"/>">Mini-CEX考评</a></li>
                	<li id="li913"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/soapController/list.htm?li=li913&div=div_9&menuOrder=1&type=h"/>">SOAP考评</a></li>
                	<li id="li914"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/dopsController/list.htm?li=li914&div=div_9&menuOrder=1&type=h"/>">DOPS考评</a></li>
             	</ul>
            </div>
          </#if>
          <#if ( loginSession.userRole?? && loginSession.userRole =='b')>
            <div class="left_title" id="div_9"><span>评分系统</span></div> 
            <div class="left_em">
                <ul> 
           			<li id="li912"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/gradeController/list.htm?li=li912&div=div_9&menuOrder=2&type=b"/>">Mini-CEX考评</a></li>
                	<li id="li913"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/soapController/list.htm?li=li913&div=div_9&menuOrder=2&type=b"/>">SOAP考评</a></li>
                	<li id="li914"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/dopsController/list.htm?li=li914&div=div_9&menuOrder=2&type=b"/>">DOPS考评</a></li>
             	</ul>
            </div>
          </#if>
          <#if ( loginSession.userRole?? && loginSession.userRole =='r')>
            <div class="left_title" id="div_9"><span>评分系统</span></div> 
            <div class="left_em">
                <ul> 
           			<li id="li912"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/gradeController/list.htm?li=li912&div=div_9&menuOrder=3&type=r"/>">Mini-CEX考评</a></li>
                	<li id="li913"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/soapController/list.htm?li=li913&div=div_9&menuOrder=3&type=r"/>">SOAP考评</a></li>
                	<li id="li914"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/dopsController/list.htm?li=li914&div=div_9&menuOrder=3&type=r"/>">DOPS考评</a></li>
             	</ul>
            </div>
          </#if>
          <#if ( loginSession.userRole?? && loginSession.userRole =='t')>
            <div class="left_title" id="div_9"><span>评分系统</span></div> 
            <div class="left_em">
                <ul> 
           			<li id="li912"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/gradeController/list.htm?li=li912&div=div_9&menuOrder=4&type=t"/>">Mini-CEX考评</a></li>
                	<li id="li913"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/soapController/list.htm?li=li913&div=div_9&menuOrder=4&type=t"/>">SOAP考评</a></li>
                	<li id="li914"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/dopsController/list.htm?li=li914&div=div_9&menuOrder=4&type=t"/>">DOPS考评</a></li>
             	</ul>
            </div>
          </#if>
          <#if ( loginSession.userRole?? && loginSession.userRole =='s')>
            <div class="left_title" id="div_9"><span>评分系统</span></div> 
            <div class="left_em">
                <ul> 
           			<li id="li912"><img src="${uri.imgsite}/left_icon01.gif"><a href="<@spring.url "/gradeController/list.htm?type=s&li=li912&div=div_9&menuOrder=5"/>">Mini-CEX考评</a></li>
                	<li id="li913"><img src="${uri.imgsite}/left_icon08.gif"><a href="<@spring.url "/soapController/list.htm?type=s&li=li913&div=div_9&menuOrder=5"/>">SOAP考评</a></li>
                	<li id="li914"><img src="${uri.imgsite}/left_icon09.gif"><a href="<@spring.url "/dopsController/list.htm?type=s&li=li914&div=div_9&menuOrder=5"/>">DOPS考评</a></li>
             	</ul>
            </div>
          </#if>
            <#--
            <div class="left_title" id="div_7"><span>留言板块</span></div> 
            <div class="left_em">
                <ul> 
                   	<li id="li71"><img src="${uri.imgsite}/left_icon05.gif"><a href="<@spring.url "/messageController/message_index.htm?li=li71&div=div_7&menuOrder=0"/>">新建留言</a></li>
               		<li id="li72"><img src="${uri.imgsite}/left_icon03.gif"><a href="<@spring.url "/messageController/messagelist_index.htm?li=li72&div=div_7&menuOrder=0"/>">查询已发送留言</a></li>
               		<li id="li73"><img src="${uri.imgsite}/left_icon02.gif"><a href="<@spring.url "/messageController/messageReceivelist_index.htm?li=li73&div=div_7&menuOrder=0"/>">查询已接收留言</a></li>
                </ul>
            </div>
            -->
	</div>
    <div class="left_io" id="switchPoint" onclick="switchSysBar()">3</div>
