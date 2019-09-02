// JavaScript Document
var isIe=(document.all)?true:false;
var imgs = '/cycle/monitor/imgs';
//系统提示
function show_sec(ev,str1,str2)
{
	var objPo = mousePosition(ev);
	messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>"+str1+"</td></tr><tr><td><a href='"+str2+"'><img src='"+imgs+"/icon_yes.jpg'/></a></td></tr></table></div>";
	showMessageBox('信息提示',messContent,objPo,550);
}

function twobut2_pop(ev,word1,word2,url,tem,clickFunc)
{
var objPo = null;
if(ev != null)
	objPo = mousePosition(ev);
var word = "twobut_reason(event,'"+word2+"','"+url+"','"+tem+"')";
if(clickFunc != null)
	word = clickFunc;
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>"+word1+"</td></tr><tr><td><a href='#' onclick="+word+"><img src='"+imgs+"/icon_yes.jpg'/></a> &nbsp; <a href='#' onclick='closeWindow();'><img src='"+imgs+"/icon_cancel.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}

function twobut_reason(ev,w1,url,tem)
{
var objPo = null;
if(ev != null)
	objPo = mousePosition(ev);
messContent="<div class='pop_box'><form action='"+url+"' method='post'><table class='pop_tab2' style='text-align:center;'><tr><td>"+w1+"</td><td><textarea name='reason' cols='60' rows='6'></textarea></td></tr><tr><td colspan='2'><input type='submit' value='' style='width:65px;height:22px;border:0px;background-image:url("+imgs+"/icon_yes.jpg)' /></td></tr></table></form></div>";
if(tem == '1'){
	messContent="<div class='pop_box'><form action='"+url+"' method='post' onsubmit='return checks(this);'><table class='pop_tab2'><tr><td height='30px' style='text-align:right;'>缓冲期&nbsp;</td><td><input name='cache' id='cache' type='text' value='30' maxlength='4' /> 天</td></tr><tr><td>"+w1+"</td><td><textarea name='reason' id='reason' cols='60' rows='6'></textarea></td></tr><tr><td colspan='2' style='text-align:center;'><input type='submit' value='' style='width:65px;height:22px;border:0px;background-image:url("+imgs+"/icon_yes.jpg)' /></td></tr></table></form></div>";
}else if(tem == '0'){
	messContent="<div class='pop_box'><form action='"+url+"' method='post' onsubmit='return checks(this);'><table class='pop_tab2'><tr><td>"+w1+"</td><td><textarea name='reason' id='reason' cols='60' rows='6'></textarea></td></tr><tr><td colspan='2' style='text-align:center;'><input type='submit' value='' style='width:65px;height:22px;border:0px;background-image:url("+imgs+"/icon_yes.jpg)' /></td></tr></table></form></div>";
}

showMessageBox('信息提示',messContent,objPo,550);
}

function checks(form){
	var cache = form.cache;
	var reason = form.reason;
	if(cache != null){
		if(cache.value.length == 0||isNaN(cache.value)||cache.value<1||cache.value>30||cache.value.indexOf('.')>0){  //修改缓冲期为小数或者不在1-30区间的输入控制
			alert("缓冲期天数必须填入1-30整数型数字！");
			cache.focus();
			return false;
		}
	}
	if(reason != null){
		if(reason.value.length == 0){
			alert("原因不能为空！");
			reason.focus();
			return false;
		}
		if(reason.value.length > 60){
			alert("原因不能大于120个字符！");
			reason.focus();
			return false;
		}
	}
	return true;
	}
//查看渠道方面是否有工作流定义开关
function checkHavewfDefinetion(ev,word1,word2,url,tem,workflowNum,refreshUrl,context){
	var param = null;
	var postmethod = "get";
	var check_url =context + "/lcpQueryController/checkhavewfdefinetion.htm?workflowNum="+workflowNum;
	$.ajax({
		   type: postmethod,
		   cache: false,
		   url: check_url,
		   data : param,
		   success: function(data){
			if(data == 'true'){
				twobut2_pop(ev,word1,word2,url,tem,null);
			}else{
				twobut2_pop(ev,word1,word2,url,tem,"urlEvent('"+url+"','"+refreshUrl+"')");
			}
		   },
		   error:function(xhr,err){ 
		}
	});
}

function urlEvent(url,refreshUrl){
	$U(url);
//	var param = null;
//	var postmethod = "get";
//	$.ajax({
//		   type: postmethod,
//		   cache: false,
//		   url: url,
//		   data : param,
//		   success: function(data){
//			if(data == 'true'){
//				showMessageDialog(null,'操作成功',refreshUrl,'closeWindow();')
//			}else{
//				showMessageDialog(event,'操作失败','','');
//			}
//		   },
//		   error:function(xhr,err){ 
//			   
//		   }
//	});
}

//设置select的可见状态
function setSelectState(state)
{
var objl=document.getElementsByTagName('select');
for(var i=0;i<objl.length;i++)
{
objl[i].style.visibility=state;
}
}
function mousePosition(ev)
{
if(ev.pageX || ev.pageY)
{
return {x:ev.pageX, y:ev.pageY};
}
return {
x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,y:ev.clientY + document.body.scrollTop - document.body.clientTop
};
}
//弹出方法
function showMessageBox(wTitle,content,pos,wWidth)
{
closeWindow();
var bWidth=parseInt(document.documentElement.scrollWidth);
var bHeight=parseInt(document.documentElement.scrollHeight);
if(isIe){
setSelectState('hidden');}
var back=document.createElement("div");
back.id="back";
var styleStr="top:0px;left:0px;position:absolute;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";
styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;";
back.style.cssText=styleStr;
document.body.appendChild(back);
showBackground(back,50);
var mesW=document.createElement("div");
mesW.id="mesWindow";
mesW.className="mesWindow";
mesW.innerHTML="<div class='mesWindowTop' id='mesWindowTop'><table width='100%' height='100%' ><tr><td>&nbsp;"+wTitle+"</td><td width='40px'><a href='#' onclick='closeWindow();' >关闭</a></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>";
var v_top=(document.body.clientHeight-mesW.clientHeight)/2;
v_top+=document.documentElement.scrollTop;
styleStr="top:"+(v_top-200)+"px;left:"+(document.body.clientWidth/2-mesW.clientWidth/2)+"px;position:absolute;width:"+wWidth+"px;margin-left:5%;left:auto;z-index:9999;";
mesW.style.cssText=styleStr;
document.body.appendChild(mesW);
}
function showMessageBox_in(wTitle,content,pos,wWidth,id)
{
closeWindow();
var bWidth=parseInt(document.documentElement.scrollWidth);
var bHeight=parseInt(document.documentElement.scrollHeight);
var mesW=document.createElement("div");
mesW.id=id;
mesW.className="mesWindow";
mesW.innerHTML="<div class='mesWindowTop' id='mesWindowTop'><table width='100%' height='100%' ><tr><td>&nbsp;"+wTitle+"</td><td width='40px'></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>";
var v_top=(document.body.clientHeight-mesW.clientHeight)/2;
v_top+=document.documentElement.scrollTop;
styleStr="top:"+(v_top-200)+"px;left:"+(document.body.clientWidth/2-mesW.clientWidth/2)+"px;position:absolute;width:"+wWidth+"px;margin-left:-350px;left:60%;z-index:9999;display:block";
mesW.style.cssText=styleStr;
document.body.appendChild(mesW);
}




function showMessageBox1(wTitle,content,pos,wWidth)
{
closeWindow();
var bWidth=parseInt(document.documentElement.scrollWidth);
var bHeight=parseInt(document.documentElement.scrollHeight);
if(isIe){
setSelectState('hidden');}
var back=document.createElement("div");
back.id="back";

var styleStr="top:0px;left:0px;position:absolute;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";
styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;";
back.style.cssText=styleStr;
document.body.appendChild(back);
showBackground(back,50);
var mesW=document.createElement("div");
mesW.id="mesWindowUsr";
mesW.className="mesWindow";
mesW.innerHTML="<div class='mesWindowTop'><table width='100%' height='100%' ><tr><td>&nbsp;"+wTitle+"</td><td width='40px'>"+'<a href="#" onclick="closeWindow(\'mesWindowUsr\');" >关闭</a></td></tr></table></div><div class="mesWindowContent" id="mesWindowContentUsr" style="max-height:600px;overflow:auto;">'+content+'</div><div class="mesWindowBottom"></div>';
var v_top=(document.body.clientHeight-mesW.clientHeight)/2;
v_top+=document.documentElement.scrollTop;
styleStr="top:40px;left:"+(document.body.clientWidth/2-mesW.clientWidth/2)+"px;position:absolute;width:90%;margin-left:5%;left:auto;z-index:9999;";
mesW.style.cssText=styleStr;
document.body.appendChild(mesW);
}

function showTipMessageBox(wTitle,content,pos,wWidth)
{
closeWindow();
var bWidth=parseInt(document.documentElement.scrollWidth);
var bHeight=parseInt(document.documentElement.scrollHeight);
if(isIe){
setSelectState('hidden');}
var back=document.createElement("div");
back.id="back";
var styleStr="top:0px;left:0px;position:absolute;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";
styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;";
back.style.cssText=styleStr;
document.body.appendChild(back);
showBackground(back,50);
var mesW=document.createElement("div");
mesW.id="mesWindow";
mesW.className="mesWindow";
mesW.innerHTML="<div class='mesWindowTop'><table width='100%' height='100%' ><tr><td>&nbsp;"+wTitle+"</td><td width='40px'></td></tr></table></div><div class='mesWindowContent' id='mesWindowContent'>"+content+"</div><div class='mesWindowBottom'></div>";
var v_top=(document.body.clientHeight-mesW.clientHeight)/2;
v_top+=document.documentElement.scrollTop;
styleStr="top:"+(v_top-200)+"px;left:"+(document.body.clientWidth/2-mesW.clientWidth/2)+"px;position:absolute;width:"+wWidth+"px;margin-left:-350px;left:60%;z-index:9999;";
mesW.style.cssText=styleStr;
document.body.appendChild(mesW);
}

//让背景渐渐变暗
function showBackground(obj,endInt)
{
if(isIe)
{
obj.filters.alpha.opacity+=5;
if(obj.filters.alpha.opacity<endInt)
{
setTimeout(function(){showBackground(obj,endInt)},5);
}
}else{
var al=parseFloat(obj.style.opacity);al+=0.05;
obj.style.opacity=al;
if(al<(endInt/100))
{setTimeout(function(){showBackground(obj,endInt)},5);}
}
}
//关闭窗口
function closeWindow(id)
{
	//alert(ev);
if(id != null ){
	document.getElementById(id).parentNode.removeChild(document.getElementById(id));
}
if(document.getElementById('back')!=null)
{
document.getElementById('back').parentNode.removeChild(document.getElementById('back'));
}
if(document.getElementById('mesWindow')!=null)
{
document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
}

if(isIe){
setSelectState('');}
}
//测试弹出


function testMessageBox100(ev)
{
var objPo = mousePosition(ev);
messContent="<div class='pop_box_03'><div class='scrol_div'><table class='pop_table'><tr><td class='pop_tr_r'>登录名：</td><td><span>01152505</span></td><td class='pop_tr_r'>密码：</td><td><span>test222</span></td></tr><tr><td  class='pop_tr_r'>管辖范围：</td><td><span>省级</span></td><td class='pop_tr_r'>真实姓名：</td><td><span>周立凡</span></td></tr><tr><td class='pop_tr_r'>电话：</td><td><span>021-215485214</span></td><td class='pop_tr_r'>邮箱：</td><td><span>mpf@123.com</span></td></tr></table></div><table class='pop_table'><tr><td align='center'><img src='imgs/pop_but.jpg'  onclick='closeWindow();'/></td></tr></table></div>"
showMessageBox('基本信息',messContent,objPo,700);
}

function testMessageBox101(ev)
{
var objPo = mousePosition(ev);
messContent="<div class='pop_box_03'><div class='scrol_div'><table class='pop_table'><tr><td class='pop_tr_r'>玩法序号：</td><td><span>1</span></td><td class='pop_tr_r'>销售期号：</td><td><span>2012002</span></td></tr><tr><td  class='pop_tr_r'>销售开始时间：</td><td><span>2011-02-05 10:02:10</span></td><td class='pop_tr_r'>销售期结时间：</td><td><span>2011-02-05 10:02:10</span></td></tr><tr><td class='pop_tr_r'>开奖时间：</td><td><span>2011-02-05 10:02:10</span></td><td class='pop_tr_r'>&nbsp;</td><td>&nbsp;</td></tr></table></div><table class='pop_table'><tr><td align='center'><img src='imgs/pop_but.jpg'  onclick='closeWindow();'/></td></tr></table></div>"
showMessageBox('基本信息',messContent,objPo,700);
}

function testMessageBox2(ev)
{
var objPo = mousePosition(ev);
messContent="<div class='pop_box_02'><table class='pop_tab_02' style='text-align:left;'><tr class='title_pop'><td>角色名称</td><td>描述信息</td><td>全选&nbsp;<input type='checkbox' name='checkbox' id='checkbox' /></td></tr><tr><td>日常操作人员</td><td>日常操作人员</td><td><input type='checkbox' name='checkbox' id='checkbox' /></td></tr><tr><td>日常操作人员</td><td>日常操作人员</td><td><input type='checkbox' name='checkbox' id='checkbox' /></td></tr><tr><td>日常操作人员</td><td>日常操作人员</td><td><input type='checkbox' name='checkbox' id='checkbox' /></td></tr><tr><td>日常操作人员</td><td>日常操作人员</td><td><input type='checkbox' name='checkbox' id='checkbox' /></td></tr><tr><tr><td>日常操作人员</td><td>日常操作人员</td><td><input type='checkbox' name='checkbox' id='checkbox' /></td></tr><tr><tr><td>日常操作人员</td><td>日常操作人员</td><td><input type='checkbox' name='checkbox' id='checkbox' /></td></tr><tr><tr><td>日常操作人员</td><td>日常操作人员</td><td><input type='checkbox' name='checkbox' id='checkbox' /></td></tr></table><center><a href='#'><img src='imgs/icon_jiaose.jpg' width='71' height='27'/></a></center></div>";
showMessageBox('分配角色',messContent,objPo,700);
}

function testMessageBox(ev)
{
var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab' style='text-align:left;'><tr><td>02 06 07 08 20 31 | 02</td></tr><tr><td>03 07 10 16 22 27 | 14</td></tr><tr><td>07 08 10 16 18 19 | 15</td></tr><tr><td>03 04 14 15 19 29 | 12</td></tr><tr><td>07 13 20 28 29 30 | 07</td></tr></table></div>";
showMessageBox('方案内容',messContent,objPo,550);
}


function testMessageBox10(ev)
{
var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>您已发起彩民权限修改工作流</td></tr><tr><td><a href='cm_guanli.html'><img src='imgs/icon_yes.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}



function testMessageBox14(ev)
{
var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>是否取消所有追号？</td></tr><tr><td><a href='#' onclick='testMessageBox24(event);'><img src='imgs/icon_yes.jpg'/></a> &nbsp; <a href='#' onclick='closeWindow();'><img src='imgs/icon_cancel.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}


function testMessageBox15(ev)
{
var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>是否取消该期追号？</td></tr><tr><td><a href='#' onclick='testMessageBox25(event);'><img src='imgs/icon_yes.jpg'/></a> &nbsp; <a href='#' onclick='closeWindow();'><img src='imgs/icon_cancel.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}




function testMessageBox22(ev)
{

var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>已提交修改流程，请返回！</td></tr><tr><td><a href='cm_guanli.html'><img src='imgs/icon_yes.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}



function testMessageBox23(ev)
{

var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>已取消绑定，请返回！</td></tr><tr><td><a href='cm_guanli.html'><img src='imgs/icon_yes.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}


function testMessageBox24(ev)
{

var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>已取消追号，请返回！</td></tr><tr><td><a href='cm_zhuihao.html'><img src='imgs/icon_yes.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}


function testMessageBox25(ev)
{

var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>已取消追号，请返回！</td></tr><tr><td><a href='#' onclick='closeWindow();'><img src='imgs/icon_yes.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}




function testMessageBox20(ev)
{

var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>系统将以邮件和短信的方式通知相关用户</td></tr><tr><td><a href='czy_manage.html'><img src='imgs/icon_yes.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}


function testMessageBox30(ev)
{

var objPo = mousePosition(ev);
messContent="<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>密码修改成功！</td></tr><tr><td><a href='czy_manage.html'><img src='imgs/icon_yes.jpg'/></a></td></tr></table></div>";
showMessageBox('信息提示',messContent,objPo,550);
}

//弹出框

function show_confirm() {
        var r = confirm('确认是否重置密码信息!');
        if (r == true) {
testMessageBox20(event);
        }
    }


function show_confirm02() {
        var r = confirm('确认是否修改彩民权限！');
        if (r == true) {
        window.location.href="workf_com_cm.html";
        }
    }

function show_confirm03() {
        var r = confirm('确认是否取消主卡绑定！');
        if (r == true) {
			testMessageBox23(event);
        }
    }




function workf_show() {
        var r = confirm('是否中止当前工作流？');
        if (r == true) {
			alert("该工作流状态已变更！");
        }
    }



/**提示框模板**/

function viewDialog(title,content){
	showMessageBox(title,content,null,700);
}

function showAssignRoleDialog(ev,content){
	showMessageBox('角色分配',content,null,550);
}

function showMessageInfo(content){
	showMessageBox('基本信息',content,null,700);
}

function showReasonDialog(ev,message,href,onclick){
	if(href == '' && onclick == ''){
		href = "#";
		onclick = "closeWindow();";
	}
	if(contains(href,"|",true))
		href = replaceAll(href,"|","'");
	if(contains(onclick,"|",true))
		onclick = replaceAll(onclick,"|","'");
	var objPo = null;
	if(ev != null && ev != '')
		objPo = mousePosition(ev);
	var tempContent="<form id='reasonform'><div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>"+message+"</td><td><textarea cols='60' rows='6' id='reason' name='reason'></textarea></td></tr><tr><td colspan='2'><a href='"+href+"' onclick="+onclick+"><img src='"+imgs+"/icon_yes.jpg'/></a></td></tr></table></div></form>";
	showMessageBox('信息提示',tempContent,null,550);
}

function checkReasonText(){
	var reason = $("#reason").val();
	if(reason == ''){
		alert("原因不能为空");
		return null;
	}else if(getStrLen(reason) > 200){
		alert("不能超过100个汉字");
		return null;
	}
	closeWindow();
	return reason;
}

//获取包括中文的字符长度，
function getStrLen(str) {
	var totallength=0;
	for (var i=0;i<str.length;i++){
		var intCode=str.charCodeAt(i);
		if (intCode>=0&&intCode<=128){
			totallength=totallength+1;
		}else {
			totallength=totallength+2;
		}
	}
	return totallength;
}


function showMessageDialog(ev,message,href,onclick){
	if(href == '' && onclick == ''){
		href = "#";
		onclick = "closeWindow();";
	}
	if(contains(href,"|",true))
		href = replaceAll(href,"|","'");
	if(contains(onclick,"|",true))
		onclick = replaceAll(onclick,"|","'");
	var tempContent = "<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>"+message+"</td></tr><tr><td><a href='"+href+"' onclick="+onclick+"><img src='"+imgs+"/icon_yes.jpg'/></a></td></tr></table></div>";
	var objPo = null;
	if(ev != null)
		objPo = mousePosition(ev);
	showTipMessageBox('信息提示',tempContent,objPo,550);
}

function showConfirmDialog(ev,message,href,onclick){
	if(href == '' && onclick == '')
		onclick = "closeWindow();";
	if(contains(href,"|",true))
		href = replaceAll(href,"|","'");
	if(contains(onclick,"|",true))
		onclick = replaceAll(onclick,"|","'");
	var objPo = null;
	if(ev != null)
		objPo = mousePosition(ev);
	var tempContent = "<div class='pop_box'><table class='pop_tab2' style='text-align:center;'><tr><td>"+message+"</td></tr><tr><td><a href='"+href+"' onclick="+onclick+"><img src='"+imgs+"/icon_yes.jpg'/></a> &nbsp;<a href='#' onclick='closeWindow();'><img src='"+imgs+"/icon_cancel.jpg'/></a></td></tr></table></div>";
	showMessageBox('信息提示',tempContent,objPo,550);
}

function contains(string,substr,isIgnoreCase){
	if(isIgnoreCase){
		string=string.toLowerCase();
		substr=substr.toLowerCase();
	}
	var startChar=substr.substring(0,1);
	var strLen=substr.length;
	for(var j=0;j<string.length-strLen+1;j++){
		if(string.charAt(j)==startChar){
			if(string.substring(j,j+strLen)==substr){
				return true;
			}
		}
	}
	return false;
}

/**
 * @name replaceAll JS用于替换一个字符串中对应的所有的字符串
 * @param String  initStr 原字符串。
 * @param String  rstr  要转化的字符串。
 * @param String  rs  转换成的字符串
 * @return String str;
 */

function replaceAll(initStr,rstr,rs){
	str='';
	while(initStr.indexOf(rstr)!=-1){
	   k=initStr.indexOf(rstr);
	   initStr=initStr.replace(rstr,rs);
	   str+=initStr.substr(0,k+rs.length);
	   initStr=initStr.substr(k+rs.length);
	 }
	 str+=initStr;
	 return str;
}
function pop_info(ev,url)
{


//var param = $("#hiddenForm").serialize();
var postmethod = "get";
$.ajax({
    type: postmethod,
    cache: false,
    url: url,
    data : null,
    success: function(data){
	var objPo = mousePosition(ev);
        showMessageBox('详情',data,objPo,700);
    },
    error:function(xhr,err){
    }
});
}

function showInfo(ev,url,title,size)
{
var postmethod = "get";
$.ajax({
    type: postmethod,
    cache: false,
    url: url,
    data : null,
    success: function(data){
	var objPo = mousePosition(ev);
        showMessageBox(title,data,objPo,size);
    },
    error:function(xhr,err){
    }
});
}
function showInfo_in(ev,url,title,size,id)
{
var postmethod = "get";
$.ajax({
    type: postmethod,
    cache: false,
    url: url,
    data : null,
    success: function(data){
	var objPo = mousePosition(ev);
        showMessageBox_in(title,data,objPo,size,id);
    },
    error:function(xhr,err){
    }
});
}
function pop_userinfo(ev,url)
{
//var param = $("#hiddenForm").serialize();
var postmethod = "get";
$.ajax({
    type: postmethod,
    cache: false,
    url: url,
    data : null,
    success: function(data){
	var objPo = mousePosition(ev);
	   showMessageBox1('详情',data,objPo,1100);
       $("#con_0").click();
       $("#tbUserUp").hide();
    },
    error:function(xhr,err){
    }
});
}
function toUserInfo(gName,divId,url)
{
	$("#"+divId+"").siblings().css("display","none");
	$("#"+divId+"").css("display","block");
	$("#"+gName+"").addClass("on");
	$("#"+gName+"").siblings().removeClass("on");
	$("#"+divId).siblings().each(function(){
		$(this).html("");
	   })
	//url="<@spring.url '"+url+"&targetID=innerData&formID=innerSearchForm'/>";
    var param = $("#hiddenForm").serialize();
    var postmethod = "get";
    $.ajax({
        type: postmethod,
        cache: false,
        url: url,
        data : param,
        success: function(data){
    	 if(data.indexOf("lsmp-cas/login")>-1){
             window.location.href = "/lsmp-manage-web/index.jsp";
    	 }else{
    		 $("#"+divId+"").html(data);
    	 }
            
        },
        error:function(xhr,err){
        }
    });
}

function awardDetail(id){
	
	var obj = $("#"+id);
 	var dis =obj.css("display");
 	if(dis == "none"){
 		obj.show();
	}else{
		obj.hide();
	}
}

Date.prototype.Format = function(fmt) { 
	var o = {
		"M+" : this.getMonth() + 1, //月份 
		"d+" : this.getDate(), //日 
		"h+" : this.getHours(), //小时 
		"m+" : this.getMinutes(), //分 
		"s+" : this.getSeconds(), //秒 
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
		"S" : this.getMilliseconds()
	//毫秒 
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}