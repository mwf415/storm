function xqShowAll(url){
//		var url= "<@spring.url '/searchQuest/xqShowAll.htm?idx="+idx+"'/>";
		$.ajax({
			type:"POST",
			url:url,
			dataType:'json',
			success:function(data){
				var dd=data;
				if(dd!=null){
						$("#xqShow").addClass("game_ques");
							htm="<table cellpadding='0' cellspacing='0' border='0' width='660'>"
								+"<tr>"+dd.content+"</tr><br/>"
								+"<tr>"+secAnawer(dd.examOptions)+"</tr><br/>"
								+"<tr>正确答案为：&nbsp;"+dd.answer+"</tr>"
								+"</table><div class='game_ques_btn' onclick='closeWin();'><span color='red'>确定</span></div>";
						$("#xqShow").html(htm);
						$("#xqShow").show();
				}
			}
		});
	}
		function closeWin(){
			$("#xqShow").hide();
		}
		function secAnawer(examOptions){
			if(examOptions.indexOf("@@")>-1){
				var obj="";
				var ans=["A","B","C","D","E","F"];
				var arr=examOptions.split("@@");
				for(var i=0;i<arr.length;i++){
					obj+=ans[i]+".&nbsp;"+arr[i]+"&nbsp;&nbsp;&nbsp;&nbsp;";
				}
				return obj;
			}else{
				return examOptions;
			}
		}
	
	
	function targetPage(pageIndex){
		
		var subjectIds=$("#subjectIdsStr").value;
		var typeId=$("#typeId").value;
		window.location = "<@spring.url '/searchQuest/testBox.htm?param="+subjectIds+"&typeId="+typeId+"&pageIndex="+pageIdex+"'/>";
	}
		