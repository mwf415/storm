<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="org.slf4j.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>error</title>
<style type="text/css">
body{ margin:0; padding:0; background:#efefef; font-family:Georgia, Times, Verdana, Geneva, Arial, Helvetica, sans-serif; }
			div#mother{ margin:0 auto; width:943px; height:572px; position:relative; }
			div#errorBox{ background: url(/cycle/monitor/imgs/404_bg.png) no-repeat top left; width:943px; height:572px; margin:auto; }
			div#errorText{ color:#39351e; padding:146px 0 0 446px }
			div#errorText p{ width:303px; font-size:14px; line-height:26px; }
			div.link{ /*background:#f90;*/ height:50px; width:145px; float:left; }
			div#home{ margin:20px 0 0 444px;}
			div#contact{ margin:20px 0 0 25px;}
			h1{ font-size:40px; margin-bottom:35px; }
</style>
</head>

<body>
<div id="mother">
	<div id="errorBox">
		<div id="errorText">
	    	<h1>Sorry..页面没有找到！</h1>
	    	<ul class="er_text1">
		        <li>
		        	<p><a href="javascript:history.go(-1)">返回上一页</a></p>
		         </li>
		         <li>
		        	 您浏览的网页暂时不能显示，原因如下：
		         	<ul class="er_text2">
						<li>您正在浏览的网页可能已被删除</li>
		                <li>网页被重命名或暂时不可用，您可以稍后刷新或尝试其他链接</li>
		            </ul>
		        </li>
   		 </ul>
		</div>
	</div>
</div>
<%!private final Logger logger = LoggerFactory.getLogger(this.getClass());%>
<%logger.error("Exception:",exception);%>
<div class="er_fot">
</div>
</body>
</html>
