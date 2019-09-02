<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>error</title>
<style type="text/css">

*{margin:0px;padding:0px;}
body {
	margin: 0px;
	font-family:Verdana, Geneva, sans-serif;
	font-size:14px;
	background:#f7f7f7;
	color:#3d3d3d;
}
img{border:0px;}
ul,li{list-style-type:none;float:left;width:500px;}
a{color:#3da7d7;}
.er_top{background:#c92f2f;}
.er_top_con{background:no-repeat right;height:27px;}
.er_main{width:1003px;margin:0px auto;}
.er_03{background:no-repeat;height:369px;position:relative;}
span{color:#c92f2f;}
p{margin-top:10px;}
.er_text1{line-height:26px;position:absolute;left:466px;top:19px;word-spacing:1em;}

.er_text2 li{background:no-repeat 0px 11px;padding-left:15px;}
.er_fot{margin-top:75px;width:auto;color:#868686;border-top:1px solid #c7c7c7;padding:20px;font-size:12px;line-height:24px;text-align:center;}

</style></head>

<body>
<div class="er_top"><div class="er_main er_top_con"></div></div>
<div class="er_main"><img src="/cycle/monitor/imgs/404_bg.png"/></div>
<div class="er_main er_03">
	<ul class="er_text1">
    	<li></li>
        <li>
        	<p><a href="javascript:history.go(-1)">返回上一页</a></p>
 			<br/>
			您浏览的网页暂时不能显示，原因如下：
         </li>
         <li>
         	<ul class="er_text2">
				<li>您正在浏览的网页可能已被删除</li>
                <li>网页被重命名或暂时不可用，您可以稍后刷新或尝试其他链接</li>
            </ul>
        </li>
    </ul>
</div>

<div class="er_fot">
</div>
</body>
</html>
