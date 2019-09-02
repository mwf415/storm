var timerID = null;
var dayAndWeekId = null;
var timerRunning = false;
function showtime() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
		month = month + 1;
	var day = now.getDate();
	var hours = now.getHours();
	var minutes = now.getMinutes();
//	if (hours > 12) {
//		hours = hours - 12;
//	}
	var timeValue = year + "-" + ((month < 10)?"0"+month:month) + "-" 
			+ ((day < 10)?"0"+day:day) +" " 
			+ ((hours < 10) ? "0" + hours : hours)
		+ ((minutes < 10) ? ":0" : ":") + minutes ;
	
	$('#time').html(timeValue);
	timerID = setTimeout("showtime()", 1000);
	timerRunning = true;
}

function showDayAndWeek() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var date = now.getDate();
	var week = now.getDay();

	switch (week) {
	case 1:
		week = "星期一"
		break;
	case 2:
		week = "星期二"
		break;
	case 3:
		week = "星期三"
		break;
	case 4:
		week = "星期四"
		break;
	case 5:
		week = "星期五"
		break;
	case 6:
		week = "星期六"
		break;
	default:
		week = "星期日"
		break;
	}

	if (month < 10) {
		month = "0" + month;
	}
	if (date < 10) {
		date = "0" + date;
	}

	var dayAndWeekValue = year + "-" + month + "-" + date + " " + week;

	$("#dayAndWeek").html(dayAndWeekValue);
	dayAndWeekId = setTimeout("showDayAndWeek()", 60000);
}

function stopclock() {
	if (timerRunning) {
		clearTimeout(timerID);
		clearTimeout(dayAndWeekId);
	}
	timerRunning = false;
}
function startclock() {
	stopclock();
	showtime();
	showDayAndWeek()
}
