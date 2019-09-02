
function goUrl(url)
 {
  window.location=url;
  }

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
function changeUserMsg(){
    if(document.getElementById("UserType").value==0){
     document.getElementById('msg0').style.display='none';
	 document.getElementById('msg1').style.display='none';	
	  document.getElementById('msg2').style.display='none';	
	 //document.all.btn.disabled=false; 
    }
    else if(document.getElementById("UserType").value==1){
     document.getElementById('msg0').style.display='none';
	 document.getElementById('msg1').style.display='';
	  document.getElementById('msg2').style.display='none';	
	 //document.all.btn.disabled=false;
    }
	 else if(document.getElementById("UserType").value==2){
     document.getElementById('msg0').style.display='none';
	 document.getElementById('msg1').style.display='none';
	  document.getElementById('msg2').style.display='';	
	 //document.all.btn.disabled=false;
    }
	
}
function changeUserMsgw(){
    if(document.getElementById("UserTypew").value==0){
     document.getElementById('msg0w').style.display='none';
	 document.getElementById('msg1w').style.display='none';	
	  document.getElementById('msg2w').style.display='none';	
	 //document.all.btn.disabled=false; 
    }
    else if(document.getElementById("UserTypew").value==1){
     document.getElementById('msg0w').style.display='none';
	 document.getElementById('msg1w').style.display='';
	  document.getElementById('msg2w').style.display='none';	
	 //document.all.btn.disabled=false;
    }
	 else if(document.getElementById("UserTypew").value==2){
     document.getElementById('msg0w').style.display='none';
	 document.getElementById('msg1w').style.display='none';
	  document.getElementById('msg2w').style.display='';	
	 //document.all.btn.disabled=false;
    }
	
}

function changeUserMsgv(){
    if(document.getElementById("UserTypev").value==0){
     document.getElementById('msg0v').style.display='none';
	 document.getElementById('msg1v').style.display='none';	
	  document.getElementById('msg2v').style.display='none';	
	 //document.all.btn.disabled=false; 
    }
    else if(document.getElementById("UserTypev").value==1){
     document.getElementById('msg0v').style.display='none';
	 document.getElementById('msg1v').style.display='';
	  document.getElementById('msg2v').style.display='none';	
	 //document.all.btn.disabled=false;
    }
	 else if(document.getElementById("UserTypev").value==2){
     document.getElementById('msg0v').style.display='none';
	 document.getElementById('msg1v').style.display='none';
	  document.getElementById('msg2v').style.display='';	
	 //document.all.btn.disabled=false;
    }
	
}


function changeUserMsgY(){
    if(document.getElementById("UserTypeY").value==0){
     document.getElementById('msg0Y').style.display='';
	 document.getElementById('msg1Y').style.display='none';	
	 
	 //document.all.btn.disabled=false; 
    }
    else if(document.getElementById("UserTypeY").value==1){
     document.getElementById('msg0Y').style.display='none';
	 document.getElementById('msg1Y').style.display='';
	 	
	 //document.all.btn.disabled=false;
    }
		
}


function sendMsg_one(){
	if (document.getElementById("xd_one").checked == false) {
	document.getElementById("msg1_4").style.display = "none";

	} else {
	document.getElementById("msg1_4").style.display = ""; 
	}
	}

//2012-11-15 

function changeUserMsg4s(){
    if(document.getElementById("useType").value==1){
    
 document.getElementById('msg1_1').style.display=''; 
 document.getElementById('msg1_2').style.display=''; 
 document.getElementById('msg1_3').style.display=''; 
  document.getElementById('msg2_1').style.display='none';
  document.getElementById('msg2_2').style.display='none';
  document.getElementById('msg2_3').style.display='none';
  document.getElementById('msg2_4').style.display='none';
  document.getElementById('msg2_5').style.display='none';
  document.getElementById('msg2_6').style.display='none';
  document.getElementById('msg2_7').style.display='none';
  
    }
    else if(document.getElementById("useType").value==2){
   
 document.getElementById('msg1_1').style.display='none'; 
 document.getElementById('msg1_2').style.display='none'; 
 document.getElementById('msg1_3').style.display='none';
 document.getElementById('msg1_4').style.display='none';
 document.getElementById('msg1_5').style.display='none';
 document.getElementById('msg1_6').style.display='none';
  document.getElementById('msg2_1').style.display='';
  document.getElementById('msg2_2').style.display='';
  document.getElementById('msg2_3').style.display='';
  document.getElementById('msg2_7').style.display='';
    } 

}

    function sendMsg_one(){
	if (document.getElementById("continueOrder").checked == false) {
	document.getElementById("msg1_4").style.display = "none";
	document.getElementById("msg1_5").style.display = "none";
	document.getElementById("msg1_6").style.display = "none";
	document.getElementById("sendMsgcheckbox").checked=false;
	document.getElementById("chargeMsg").value = "";
	document.getElementById("forwordDays").value = "";
	} else {
	document.getElementById("msg1_4").style.display = ""; 
	}
	}

	function sendMsg(){
	if (document.getElementById("sendMsgcheckbox").checked == false) {
	document.getElementById("msg1_5").style.display = "none";
	document.getElementById("msg1_6").style.display = "none";
	document.getElementById("chargeMsg").value = "";
	document.getElementById("forwordDays").value = "";
	} else {
	document.getElementById("msg1_5").style.display = "";
	document.getElementById("msg1_6").style.display = "";

	}
	}


	function sendMsg03(){
	if (document.getElementById("continueOrder2").checked == false) {
	document.getElementById("msg2_4").style.display = "none";
	document.getElementById("msg2_5").style.display = "none";
	document.getElementById("msg2_6").style.display = "none";
	document.getElementById("sendMsgcheckbox2").checked=false;
	document.getElementById("chargeMsg2").value = "";
	document.getElementById("forwordDays2").value = "";
	} else {
	document.getElementById("msg2_4").style.display = "";

	}
	}

	function sendMsg04(){
	if (document.getElementById("sendMsgcheckbox2").checked == false) {
	document.getElementById("msg2_5").style.display = "none";
	document.getElementById("msg2_6").style.display = "none";
	document.getElementById("chargeMsg2").value = "";
	document.getElementById("forwordDays2").value = "";
	} else {
	document.getElementById("msg2_5").style.display = ""; 
	document.getElementById("msg2_6").style.display = "";

	}
	}
	//end 
	//彩民注销 
	function box_change(){
		if (document.getElementById("onOrOff").checked == false) {
	document.getElementById("zx_con1").style.display = "none";
	document.getElementById("zx_con2").style.display = "none";

	} else {
	document.getElementById("zx_con1").style.display = "";
	document.getElementById("zx_con2").style.display = "";

	}
	}
	//end