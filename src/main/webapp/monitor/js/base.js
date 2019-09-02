

// 轮转安排js
	function subUser(){
		var users=document.getElementsByName("selectUser");
		var str='';
		for(var i=0;i<users.length;i++){  
            if(users[i].checked){ //取到对象数组后，我们来循环检测它是不是被选中  
            	str+=users[i].value+',';
            	
            }//如果选中，将value添加到变量s中      
       }  
		$("#selectedUser").append(str);
		
	}
	//提交时间
	function subTime(){
		var time = document.getElementById("d12");
		alert(time);
		$("#selectedTime").append(time);
	}
	//根据级别查找用户

	function userByGrade(obj){ 
		alert(obj);
		var postmethod = "POST";
		var url ="<@spring.url '/arrTurnController/getUserByGrade.htm'/>";
		$.ajax({
		   type: postmethod,
		   cache: false,
		   url: url,
		   data :{"grade": obj},
		   dataType: "json",
		   success: function(data){
				alert(data["r"]);	
		   },
		   error:function(xhr,err){ 
			}
		});
		}
		