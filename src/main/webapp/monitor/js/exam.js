	//收藏功能
	function collection(parm) {
		var param = null;
		var postmethod = "get";
		var url = "/cycle/collectionExam/add.htm?parm=" + parm;
		$.ajax({
			type : postmethod,
			cache : false,
			url : url,
			data : param,
			success : function(data) {
				if (data == '1') {
					alert("收藏成功");
				}
				if (data == '0') {
					alert("收藏失败");
				}
			}
		});

	}
	


	