var gloableHeight=0;

function initLayout() {
    var h = document.documentElement.clientHeight - $("#header").height()-$("#topheader").height()-2;
    gloableHeight= $("#header").height() + $("#topheader");
    $('#right').height(h);
    $('#left').height(h);
}

function documentReady(){
    $(".left_title").unbind("click").click(function(){
        $(this).next("div").slideToggle("slow").siblings(".left_em:visible").slideUp("slow");
        $(this).toggleClass("on");
        $(this).siblings(".on").removeClass("on");
    });


    var position = $(".b_dh_info ul").children("li[class='on']").children("a").text();
    var secondMenu = $("#left .on span").text();
    var thirdMenu = $("#left .left_em:visible ul li[class='on'] a").text();

    if(secondMenu != ''){
        position = position  + " >> " + secondMenu;
    }
    if(thirdMenu != ''){
        position = position  + " >> " + thirdMenu;
    }
    $("#mapPosition").text(position);

    initLayout();

    $(window).resize(function(){
        initLayout();
    });

};

// JavaScript Document

//标签切换
function Change(gName,divId,divName,DivCount)
{
    for(i=0;i<=DivCount;i++)
    {
        document.getElementById(divName+i).style.display="none";
        document.getElementById(gName+i).className="";
    }
    document.getElementById(divName+divId).style.display="";
    document.getElementById(gName+divId).className="on";
}

function checkBox(boxid, box_y, _size) {
    var box1 = document.getElementById(boxid);
    var box2 = document.getElementById(box_y);
    // update 2012/7/11 to wangjie
    for (i = 0; i < _size; i++) {
	document.getElementById(i).style.display = "none";
    }
    if (box1.style.display == "none") {
	box1.style.display = "";
	//box2.style.display = "none";
    } else {
	// update 2012/7/11 to wangjie
	box1.style.display = "none";
    } 
}
//右表格展开
function checkBoxJs(boxid,count){
    var box1=document.getElementById(boxid);
    if (box1.style.display == "none"){
    	for(i=0;i<count;i++){
    		document.getElementById("box"+i).style.display="none";
    	}
        box1.style.display = "";
    }
    else{box1.style.display="none";}
}

function check_up(upid){
    var box1 = document.getElementById(upid);
    if (box1.style.display == ""){
        box1.style.display = "none";
    }
}

//搜索展开
$(document).ready(function() {
    $('#linkhtml').click(function() {
        if ($('#box').css("display") == "none") {
            $('#linkhtml').html("收起高级搜索");
        } else {
            $('#linkhtml').html("展开高级搜索");
        }
        $('#box').slideFadeToggle('narmal');
        return false;
    });
});

$(document).ready(function() {
    $('#info_more').click(function() {
        if ($('#box_m').css("display") == "none") {
            $('#info_more').html("收起更多");
        } else {
            $('#info_more').html("展开更多");
        }
        $('#box_m').slideFadeToggle('narmal');
        return false;
    });
});

jQuery.fn.slideFadeToggle = function(speed, callback) {
    return this.animate({
        height: 'toggle',
        opacity: 'toggle'
    }, speed, callback);
};
//搜索展开end

//左栏隐藏
function switchSysBar(){
    var Ww=document.documentElement.clientWidth-10;
    if (switchPoint.innerText==3){
        switchPoint.innerText=4;
        document.all("left").style.width="0%";
        document.all("right").style.width=Ww;
        document.getElementById('switchPoint').className='left_io2';
    }else{
        switchPoint.innerText=3;
        document.all("left").style.width="220px";
        document.all("right").style.width="";
        document.getElementById('switchPoint').className='left_io';
    }
}
//左栏隐藏 end

$(document).ready(function() {
    var h=document.documentElement.clientHeight - $("#header").height();
    var w=$("#right").width()-38;
    $("#right").height(h);
    $("#left").height(h);
    $("#right-right").height(h);
    $("#notes").width(w);
});
