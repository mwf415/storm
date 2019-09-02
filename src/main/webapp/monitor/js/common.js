var path=null;
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim=function(){
    return this.replace(/(^\s*)/g,"");
}
String.prototype.rtrim=function(){
    return this.replace(/(\s*$)/g,"");
}


/*******************************************************************************
 * cm common******************************
 ******************************************************************************/

function initDate(did,flag){
	var now=new Date();
	if(flag){now.setFullYear(now.getFullYear()+1);}
	var m=parseInt(now.getMonth(),10)+1;
	var d=parseInt(now.getDate(),10);
	if(m<10){m="0"+m;}
	if(d<10){d="0"+d;}
	document.getElementById(did).value=now.getFullYear()+"-"+m+"-"+d;
}

function initYesterday(did){
	var today=new Date();
	var yesterday = new Date(today.getFullYear(),today.getMonth(),today.getDate()-1); 
	var m=parseInt(yesterday.getMonth(),10)+1;
	var d=parseInt(yesterday.getDate(),10);
	if(m<10){m="0"+m;}
	if(d<10){d="0"+d;}
	document.getElementById(did).value=yesterday.getFullYear()+"-"+m+"-"+d;
}

/* 根据元素ID查找元素 */
function $Element() {
    var elements = new Array();
    for (var i = 0; i < arguments.length; i++) {
        var element = arguments[i];
        if (typeof element == 'string')
            element = document.getElementById(element);
        if (arguments.length == 1)
            return element;
        elements.push(element);
    }
    return elements;
}

/* 根据元素ID取得元素 */
function $E(elemid) {
    return document.getElementById(elemid);
}

/* 根据元素ID取得元素的value */
function $Vo(elemid) {
    return document.getElementById(elemid).value;
}

/* 根据元素ID取得元素的innerHTML */
function $H(elemid) {
    return document.getElementById(elemid).innerHTML;
}

/* 根据元素ID隐藏该元素 */
function $Hide(id) {
    document.getElementById(id).style.display = 'none';
}

/* 根据元素ID显示该元素 */
function $Display(id) {
    document.getElementById(id).style.display = 'block';
}

/* 根据元素ID设置该元素为空 */
function $Dlose(id) {
    document.getElementById(id).innerHTML='';
}

/* 网页转向 */
function $U(url){
    window.location.href=url;
}

/* 确认页面转向 */
function $CU(message,url){
    if (window.confirm(message)){
        window.location.href=url;
    }
}

/* 取字符长度，一个中文字符为两个字节 */
function $Len(str){
    return (''+str).replace(/[^\x0000-\xFF00]/gi,'xx').length;
}

/* 自适应大小 */
function DrawImage(ImgD,_width,_height){
    if(!_width) _width=120;
    if(!_height) _height=120;
    var flag=false;
    var image=new Image();
    image.src=ImgD.src;
    if(image.width>0&&image.height>0){
        flag=true;
        if(image.width/image.height>=_width/_height){// 120/120
            if(image.width>_width){
                ImgD.width=_width;
                ImgD.height=(image.height*_width)/image.width;
            }else{
                ImgD.width=image.width;
                ImgD.height=image.height;
            }
            ImgD.alt=image.width+"X"+image.height;
        }
        else{
            if(image.height>_height){
                ImgD.height=_height;
                ImgD.width=(image.width*_height)/image.height;
            }else{
                ImgD.width=image.width;
                ImgD.height=image.height;
            }
            ImgD.alt=image.width+"X"+image.height;
        }
    }
}

/* Cookie */
function setCookie(name,value)
{
    var Days = 365;
    var exp  = new Date();    // new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)();|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

/* 验证数字 */
function isNumber(e){
    var number = "1234567890";
    for(var i=0; i<e.length; i++){
        if (number.indexOf(e.charAt(i))<0) {
            return false;
        }
    }
    return true;
}

/* 验证数字 */
function isAllDigits(argvalue) {
    argvalue = argvalue.toString();
    var validChars = "0123456789";
    var startFrom = 0;
    if (argvalue.substring(0, 2) == "0x") {
        validChars = "0123456789abcdefABCDEF";
        startFrom = 2;
    } else if (argvalue.charAt(0) == "0") {
        validChars = "01234567";
        startFrom = 1;
    } else if (argvalue.charAt(0) == "-") {
        startFrom = 1;
    }

    for (var n = startFrom; n < argvalue.length; n++) {
        if (validChars.indexOf(argvalue.substring(n, n+1)) == -1) return false;
    }
    return true;
}

/* 检查Email是否合法 */
function isEmail(s){
    if (s.length<7||s.length > 50){
        return false;
    }
    var regu = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT)$"
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        return true;
    } else {
        return false;
    }
}

/* 检查字符串是否为Null */
function isNull(s){
    if (s == null || s.length <= 0){
        return true;
    }
    return false;
}

/* 检查字符串是否为空 */
function isEmpty(s){
    if (s == null || s.length <= 0){
        return true;
    }
    return false;
}

/* 检查日期是否合法 */
function isValidDate(day, month, year) {
    if (month < 1 || month > 12) {
        return false;
    }
    if (day < 1 || day > 31) {
        return false;
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) &&
        (day == 31)) {
        return false;
    }
    if (month == 2) {
        var leap = (year % 4 == 0 &&
            (year % 100 != 0 || year % 400 == 0));
        if (day>29 || (day == 29 && !leap)) {
            return false;
        }
    }
    return true;
}

/* 获得Radio的值 */
function getRadioValue(name){
    var radios = document.getElementsByName(name);
    var i;
    if (null == radios.length){
        if(radios.checked) {
            return radios.value;
        }
    }
    for(i = 0; i < radios.length; i++){
        if(radios[i].checked){
            return radios[i].value;
        }
    }
    return 0;
}

/* 设置Radio的值 */
function setRadioValue(name,value){
    var radios = document.getElementsByName(name);
    var i;
    if (null == radios.length){
        if(radios.checked) {
            radios.checked = "checked";
        }
    }
    for(i=0;i<radios.length;i++){
        if(value == radios[i].value){
            radios[i].checked = "checked";
        }
    }
    return 0;
}

/* 获得CheckBox的值,多个为数组 */
function getCheckBoxValues(name){
    var values = new Array();
    var cbs = document.getElementsByName(name);
    var i;
    if (null == cbs) return values;
    if (null == cbs.length){
        if(cbs.checked) {
            values[values.length] = cbs.value;
        }
        return values;
    }
    var count = 0 ;
    for(i = 0; i<cbs.length; i++){
        if(cbs[i].checked){
            values[values.length] = cbs[i].value;
        }
    }
    return values;
}

/* 设置CheckBox的值 */
function setCheckBoxValue(name,value){
    var cbs = document.getElementsByName(name);
    var i;
    if (null == cbs) return 0 ;
    if (null == cbs.length){
        cbs.checked = value;
        return 0;
    }
    for(i=0;i<cbs.length;i++){
        cbs[i].checked = value;
    }
    return 0;
}

/* 设置CheckBox选中状态 */
function setCheckBoxs(name,value){
    var cbs = document.getElementsByName(name);
    var i;
    if (null == cbs) return 0 ;
    if (null == cbs.length){
        cbs.checked = true;
        return 0;
    }
    for(i=0;i<cbs.length;i++){
        if(cbs[i].value == value){
            cbs[i].checked = true;
        }
    }
    return 0;
}

function htmlEncode(text) {
    return text.replace(/&/g, '&amp;').replace(/"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

function ajaxFormRequestNomask(datastr){
    eval("var obj = "+datastr+";");
    var param = null;
    var postmethod = "get";
    if (null != obj.form && 'null' != obj.form){
        param = $("#"+obj.form).serialize();
        postmethod = "POST";
    };
    $.ajax({
        type: postmethod,
        cache: false,
        url: obj.url,
        data : param,
        success: function(data){
            if(data.indexOf("lsmp-cas/login")>-1 || data.indexOf("您无此权限或400错误或500错误")>-1){
                window.location.href = "/lsmp-manage-web/index.jsp";
            }else{
                var p = jQuery("#"+obj.id);
                if (p) {
                    p.html(data);
                }
                if (typeof(obj.callback) == "function") {
                    obj.callback(data);
                }
            }
        },
        error:function(xhr,err){
//        	 try{
//             if (xhr.readyState==0 && xhr.status==0 && err=='error')   {
//                 window.location.href=obj.url;
//             } }catch(error){
//                 window.location.href=obj.url;
//             };		
        	window.location.href=document.location.toString();
        }

    });
}

function ajaxRequestNomask(datastr){
    eval("var obj = "+datastr+";");
    if (obj.id == null)
        obj.id = "paginationResult";
    var param = null;
    var postmethod = "get";
    if (null != obj.form && 'null' != obj.form){
        param = $("#"+obj.form).serialize();
        postmethod = "POST";

    };
    $.ajax({
        type: postmethod,
        cache: false,
        url: obj.url,
        data : param,
        success: function(data){
            if(data.indexOf("lsmp-cas/login")>-1 || data.indexOf("您无此权限或400错误或500错误")>-1){
                window.location.href = "/lsmp-manage-web/index.jsp";
            }else{
                var p = jQuery("#"+obj.id);
                if (p) {
                    p.html(data);
                }
                if (typeof(obj.callback) == "function") {
                    obj.callback(data);
                }
            }
        },
        error:function(xhr,err){
//             try{
//             if (xhr.readyState==0 && xhr.status==0 && err=='error')   {
//                 window.location.href=obj.url;
//             } }catch(error){
//                 window.location.href=obj.url;
//             };	
        	window.location.href=document.location.toString();
        }

    });
}

/* 将显示分页的结果 */
function pagination(obj)
{
    if (obj.id == null||obj.id=="null")
        obj.id = "paginationResult";

    ajaxRequest(obj);
}


/* Ajax请求 */
function ajaxRequest(obj) {
    var param = null;
    var postmethod = "get";
    if (null != obj.form && 'null' != obj.form){
        param = $("#"+obj.form).serialize();
        postmethod = "POST";
        if (obj.url.indexOf("?")>0){
            obj.url=obj.url+"&formID="+obj.form;
        } else{
            obj.url=obj.url+"?formID="+obj.form;
        }
    };
    $.ajax({
        type: postmethod,
        cache: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        url: obj.url,
        beforeSend:beforeSend, //发送请求
        data : param,
        success: function(data){
            if(data.indexOf("lsmp-cas/login")>-1 || data.indexOf("您无此权限或400错误或500错误")>-1){
                window.location.href = "/lsmp-manage-web/index.jsp";
            }else{
                var p = jQuery("#"+obj.id);
                if (p) {
                    p.html(data);
      //              p.unmask();
                }
                if (typeof(obj.callback) == "function") {
                    obj.callback(data);
                }
            }
        },
        error:function(xhr,err){
            var p = jQuery("#"+obj.id);
            if (p)
            	p.unmask();
//            try{
//            if (xhr.readyState==0 && xhr.status==0 && err=='error')   {
//                window.location.href=obj.url;
//            } }catch(error){
//                window.location.href=obj.url;
//            };
            window.location.href=document.location.toString();
            /* alert("status: "+xhr.status); */
            /* alert("responseText: "+xhr.responseText); */
        }

    });
}
function beforeSend(XMLHttpRequest){
	var htm="";
	htm+='<div id="dataLoad">'
	   +'<table width=100% height=100% border=0 align=center valign=middle>'
	   +'<tr height=50%><td align=center>&nbsp;</td></tr>'
	   +'<tr><td align=center></tr>'
	   +'<tr><td align=center>数据载入中，请稍后......</td></tr>'
	   +'<tr height=50%><td align=center>&nbsp;</td></tr>'
	   +'</table>'
	   +'</div>';
	   $("#dataDiv").html(htm);
}

function ajaxHtmlData(contentid,url){
    $.ajax({
        type: "get",
        cache: false,
        url: url,
        data : null,
        success: function(data){
            if(data.indexOf("lsmp-cas/login")>-1 || data.indexOf("您无此权限或400错误或500错误")>-1){
                window.location.href = "/lsmp-manage-web/index.jsp";
            }else{
                var p = jQuery("#"+contentid);
                if (p) {
                    p.html(data);
                }
            }
        },
        error:function(xhr,err){
//             try{
//             if (xhr.readyState==0 && xhr.status==0 && err=='error')   {
//                 window.location.href=obj.url;
//             } }catch(error){
//                 window.location.href=obj.url;
//             };
        	window.location.href=document.location.toString();
        }
    });
}

/* 提交form，把得到的数据放在指定的ID上. */
function ajaxFormRequest(datastr) {
    eval("var obj = "+datastr+";");
    if (obj.id == null)
        obj.id = "paginationResult";
    ajaxRequest(obj);
}


/* 打开一个模式窗口 */
function openWindow(url) {
    if (window.showModalDialog)
        window.showModalDialog(url,'','unadorned:yes;dialogWidth:755px;dialogHeight:550px');
    else
        window.open(url,'','width=755,height=550,toolbar=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes ,modal=yes');
}
/* 获取XML某个节点中的文本 */
/* 因为IE和Gecko对节点文本的实现方法不同，所以用这个函数进行封装 */
/* IE: node.text ; Gecko: node.textContent */
function getXmlNodeText(node) {
    var undefined;
    if (node !== null) {  // 判断节点不为空才获取
        if (undefined !== node.text) {
            return node.text;
        } else if (undefined !== node.textContent) {
            return node.textContent;
        }
    }
    else
        alert('null');
    return undefined;
}
function getXmlData(xml, obj) {

    for (o in obj) {

        if ("object" == typeof(obj[o])) {
            getXmlData(xml.getElementsByTagName(o), obj[o]);
        } else {
            obj[o] = getXmlNodeText(xml.getElementsByTagName(o).item(0));

        }

    }

}
/* 检查一个输入field是否为空 */
function IsEmptyValue(value){
    var num=0;
    var i=0;
    if(value==null)
        return true;
    if(value.length==0){
        return true;
    }

    for(i=0;i<value.length;i++){
        num=value.charCodeAt(i)
        if((num!=32)&&(num!=13)&&(num!=10))
            return false;
    }/* end for */
    return true
}// end IsEmptyValue


/* 验证开始日期跟结束日期是否正确 */
function checkDate(startTimeID,endTimeID){
    var startTime = $E(startTimeID).value;
    var endTime = $E(endTimeID).value;
    if((endTime==null || endTime =="") || (startTime==null || startTime =="")){
        alert("查询日期不能为空！");
        return false;
    }else{
        startTime = startTime.replace("-","").replace("-","");
        endTime = endTime.replace("-","").replace("-","");
        if(startTime>endTime){
            alert("开始日期不能小于结束日期！");
            return false;
        }
    }
    return true;
}

//将中文编码
function getEncodeURL(url){
	//此URL一定要执行两边方可编码
	url=window.encodeURI(url);
	url=window.encodeURI(url);
	return url;
}


/*查看业务对象信息js*/
function viewInfo(contextpath,param,title){
	var postmethod = "get";
	var url = contextpath + "/commonController/viewbusobjinfo.htm";
	$.ajax({
		   type: postmethod,
		   cache: false,
		   url: url,
		   data : param,
		   success: function(data){
			   if(data.indexOf("lsmp-cas/login")>-1 || data.indexOf("您无此权限或400错误或500错误")>-1){
	                window.location.href = "/lsmp-manage-web/index.jsp";
	                return;
	            }
			   viewDialog(title,data);
		   },
		   error:function(xhr,err){
			   window.location.href=document.location.toString();
		}
	});
}

/*弹出框显示请求url返回的页面*/
function viewInfoByUrl(url,param,title){
	var postmethod = "post";
	$.ajax({
		   type: postmethod,
		   contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		   cache: false,
		   url: url,
		   data : param,
		   success: function(data){
			   if(data.indexOf("lsmp-cas/login")>-1 || data.indexOf("您无此权限或400错误或500错误")>-1){
	                window.location.href = "/lsmp-manage-web/index.jsp";
	                return;
	            }
			   viewDialog(title,data);
		   },
		   error:function(xhr,err){
			   window.location.href=document.location.toString();
		}
	});
}

/* 加载省下拉列表 */
function loadProvinces(contextpath,onchange,disabled,provinceNum){
    var url = contextpath + "/commoncontroller/provinces.htm?provinceNum="+provinceNum+"&disabled="+disabled;
    if(onchange != null)
        url = url + "&onchange="+onchange;
    ajaxHtmlData('prov_tag',url);
}

/* 加载市下拉列表 */
function loadcitys(contextpath,disabled,pNum,cityNum){
    var url;
    if(pNum != null){
        url = contextpath + "/commoncontroller/findcitysbyprovince.htm";
        url = url+"?pNum="+pNum+"&cityNum="+cityNum+"&disabled="+disabled;
    }else{
        url = contextpath + "/commoncontroller/citys.htm?cityNum="+cityNum+"&disabled="+disabled;
    }
    ajaxHtmlData('city_tag',url);
}

function loadnotextprovinces(contextpath,disabled,pNum){
	 var url = contextpath + "/commoncontroller/loadnotextprovinces.htm?disabled="+disabled+"&pNum="+pNum;
	 ajaxHtmlData('prov_tag',url);
}

function loadnotextcitys(contextpath,disabled,pNum,cNum){
	var url = contextpath + "/commoncontroller/loadnotextcitysbyprovince.htm";
	url = url+"?pNum="+pNum+"&disabled="+disabled+"&cNum="+cNum;
    if(pNum == "00"){
    	$("#city_tag").html("");
    }else{
    	ajaxHtmlData('city_tag',url);
    }
}

/* 记录当前页面 */
function targetPage(targetPage){
    if($("#targetPage").length > 0){
        if(targetPage == null || targetPage <= 0){
            targetPage = 1;
        }
        $("#targetPage").attr("value",targetPage);
    }
}

/*移除控件弹出的验证消息,也可以是整个表单*/
function removeValite(id){
	jQuery("#"+id).validationEngine("hide");
}

/*添加验证控件信息*/
function showPromptms(divid,errorContent){
	jQuery("#"+divid).validationEngine("showPrompt", errorContent, "ajaxed");
	$("#"+divid).click(function(){
		  jQuery("#"+divid).validationEngine('hide');
	});
}

/*提示验证信息  data 格式（控件id:errorcontent）*/
function validateFieldMs(data){
	  if(data.indexOf(":") > -1){
        var id = data.substr(0,data.indexOf(":"));
      	var errorContent = data.substr(data.indexOf(":")+1);
      	showPromptms(id,errorContent);
      	return false;
      }
	  return true;
}

/* show view */
function showView(id,url){
    var param = null;
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
                $(".contentTr").hide();
                $("#"+id).parent().parent().show();
                $(".showViewStyle").text("");
                $("#"+id ).html(data);
            }

        },
        error:function(xhr,err){
            if (xhr.readyState==0 && xhr.status==0 && xhr.statusText=='error')   {
                window.location.reload();
                //showView(id,url);

            }
        }
    });
}
/* hide view */
function hiddenView(id){
    $("#"+id).parent().parent().hide();
    $("#"+id).html("");
}

/* changeTable */
function changeTable(index,flag){
    var f =  flag=="left"?"right":"left";
    document.getElementById("li_"+flag+"_"+index).className = "on";
    document.getElementById("li_"+f+"_"+index).className = "";
    if(flag=="left"){
        document.getElementById(flag+"_"+ index).style.display = "";
        document.getElementById(f+"_"+ index).style.display = "none";
    }else{
	document.getElementById(flag+"_"+ index).style.display = "";
	document.getElementById(f+"_"+ index).style.display = "none";
    }

}

jQuery.download = function(url, data, method){
    //url and data options required
    if( url && data ){
        //data can be string of parameters or array/object
        data = typeof data == 'string' ? data : jQuery.param(data);
        //split params into form inputs
        var inputs = '';
        jQuery.each(data.split('&'), function(){
            var pair = this.split('=');
            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />';
        });
        //send request
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
            .appendTo('body').submit().remove();
    };
};

function filterAjaxRequest(toUrl){
	 $.ajaxSetup({     
	        complete:function(XMLHttpRequest,textStatus){  
	            // 通过XMLHttpRequest取得响应头，sessionstatus，
	        	if(XMLHttpRequest.status==404){
	        		window.location.href = "/error/404.jsp";
	        	}else if(XMLHttpRequest.status==500){
	        		window.location.href = "/error/500.jsp";
	        	}else if(XMLHttpRequest.status==0&&XMLHttpRequest.responseText==""&&textStatus == "error"){
	        		window.location.href = toUrl;
	        	}
	        }
	    });  
}

/**      
 * 对Date的扩展，将 Date 转化为指定格式的String      
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符      
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)      
 * eg:      
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
 */         
Date.prototype.pattern=function(fmt) {          
    var o = {          
    "M+" : this.getMonth()+1, //月份          
    "d+" : this.getDate(), //日          
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时          
    "H+" : this.getHours(), //小时          
    "m+" : this.getMinutes(), //分          
    "s+" : this.getSeconds(), //秒          
    "q+" : Math.floor((this.getMonth()+3)/3), //季度          
    "S" : this.getMilliseconds() //毫秒          
    };          
    var week = {          
    "0" : "/u65e5",          
    "1" : "/u4e00",          
    "2" : "/u4e8c",          
    "3" : "/u4e09",          
    "4" : "/u56db",          
    "5" : "/u4e94",          
    "6" : "/u516d"         
    };          
    if(/(y+)/.test(fmt)){          
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));          
    }          
    if(/(E+)/.test(fmt)){          
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);          
    }          
    for(var k in o){          
        if(new RegExp("("+ k +")").test(fmt)){          
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));          
        }          
    }          
    return fmt;          
}    
/**
 * @param msec把毫秒数转换为日期 yyyy-MM-dd
 * @returns
 */
function parseLong2DateString(msec){
	return new Date(msec).pattern("yyyy-MM-dd");
}
/**
 * 毫秒数转换为日期，格式位fmt格式
 * @param msec
 * @param fmt
 * @returns
 */
function parseLong2DateFormat(msec,fmt){
	return new Date(msec).pattern(fmt);
}