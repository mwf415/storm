
<ul class="nav nav-tabs">
	<li class="dropdown active">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			 菜单 <span class="caret"></span>
		</a>		
		<ul class="dropdown-menu">
		<!--	<li>
				<a href="javascript:void(0);" >教学</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="kaoping()">考评</a>
			</li>
		-->
			<li>
				<a href="<@spring.url "/point/index.htm"/>" >积分</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href="javascript:void(0);" onclick="changeTop('test')">考试</a>
			</li>
			<li>
				<a href="<@spring.url '/getBookRoom/index.htm'/>" >教室</a>
			</li>
		</ul>
		
	</li>
	<li><a href="#">教学</a></li>
	
	<li style ="float:right;"><a href="<@spring.url '/wap/loginout.htm'/>">退出</a></li>
	<li style ="float:right;"><a >${realName!}</a></li>
</ul>