//顶端tab切换,跳转
$.ajaxSetup({  
     contentType:"application/x-www-form-urlencoded;charset=utf-8",  
     complete:function(XMLHttpRequest,textStatus){   
         var result = XMLHttpRequest.responseText; //通过XMLHttpRequest取得响应头，sessionstatus，  
         if(result.indexOf('lsmp-cas/login') > -1 || result.indexOf('400错误') > -1){   
             //如果超时就处理 ，指定要跳转的页面  
             window.location.href = "/lsmp-report-web/index.jsp";   
         }   
     }   
});
$(document).ready(function() {
	$(".userlis").children().click(function() {
		// 请求地址
		var url = $(this).attr("href");
		if ($(this).attr("class") == "on") {
			$(this).siblings().attr("class", "");
		} else {
			$(this).attr("class", "on");
			$(this).siblings().attr("class", "");
		}
		showDataInDiv("dataDiv", url);
	});
});

function showDataInDiv(div, url, data) {
	$.ajax({
		url:url,
		type:'get',
		data:data,
		cache:false,
		success:function(msg){
			$('#' + div).html(msg);
		}
	});
}
function getValueById(id) {
	return document.getElementById(id).value;
}

function getRadioValue(name) {
	var radios = document.getElementsByName(name);
	var value;
	for ( var i = 0; i < radios.length; i++) {
		if (radios.item(i).checked) {
			value = radios.item(i).value;
			break;
		}
	}
	return value;
}
/**
 * 分页
 */
function pagination(obj){
	showDataInDiv(obj.id, obj.url);
}
/**
 * 
 * @param json
 * @param key
 * @returns {Array}
 */
function getJsonValueByKey(json, key) {
	var size = json.length;
	var data = [];
	for ( var i = 0; i < size; i++) {
		data.push(json[i][key]);
	}
	return data;
}
/**
 * 以柱状图的形式展示数据
 * 
 * @param divid:展示图标的div
 * @param seriesname:图例名
 * @param xdata:x轴数据
 * @param ydata:y轴数据
 */
function drawColunmn(divid, seriesname, xdata, ydata, title) {
	var chartObj = {
		chart : {
			type : 'column'
		},
		title : {
			text : title
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		subtitle : {
			text : null
		},
		xAxis : {
			categories : xdata,
			labels:{ 
				formatter: function() {
					//获取到刻度值
					var labelVal = this.value;
					if (!/[a-zA-Z]/.exec(labelVal)) {						
						//实际返回的刻度值
						var reallyVal = '';
						//判断刻度值的长度
						var len = labelVal.length;
						for(var i = 0; i < len; i++){
							reallyVal += labelVal.substr(i,1) + "<br/>";
						}
						return reallyVal;
					}
					return labelVal;
				}
			}
		},
		yAxis : {
			min : 0,
			allowDecimals: false,
			title : {
				text : null
			}
		},
		tooltip : {
			headerFormat : '<span style="font-size:12px">{point.key}</span><table>',
			pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
					+ '<td style="padding:0"><b>{point.y} </b></td></tr>',
			footerFormat : '</table>',
			shared : true,
			useHTML : true
		},
		plotOptions : {
			column : {
				pointPadding : 0.2,
				borderWidth : 0
			}
		},
		legend : {
			enabled : false
		},
		series : [ {
			name : seriesname,
			data : ydata
		} ]
	};
	$(divid).highcharts(chartObj);
}
function getPieJsonValueByKey(json, key1, key2) {
	var size = json.length;
	var data = [];
	for ( var i = 0; i < size; i++) {
		data.push([ json[i][key1], json[i][key2] ]);
	}
	return data;
}
/**
 * 
 * @param divid
 * @param seriesname
 * @param xdata
 * @param ydata
 * @param title
 */
function drawPie(divid, seriesname, data, title) {
	var chartObj = {
		chart : {
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false
		},
		title : {
			text : title
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.2f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : true,
					color : '#000000',
					connectorColor : '#000000',
					format : '<b>{point.name}</b>: {point.percentage:.2f} %'
				},
				showInLegend: true
			}
		},
		series : [ {
			type : 'pie',
			name : seriesname,
			data : data
		} ]
	};
	$(divid).highcharts(chartObj);
}

/**
 * 饼图显示数量
 * @param divid
 * @param seriesname
 * @param data
 * @param title
 */
function drawPieByQuan(divid, seriesname, data, title) {
	var chartObj = {
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : title,
				style: {
					color: '#3E576F',
					fontWeight: 'bold',
					fontSize: '16px'
				}
			},
			// 去掉 highcharts.com 链接
			credits : {
				enabled : false,
				text : ''
			},
			tooltip : {
				 formatter: function () {
				       return '' + this.series.name + ': <b>' + Highcharts.numberFormat(this.y, 2, '.' ) + '</b>';
				   } 
				//pointFormat : '{series.name}: <b>Hightcharts.numberFormat(point.y,2,'.')</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						formatter: function (){
							return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.y, 2, '.');
						}
						//format : '<b>{point.name}</b>: {point.y}'
					},
					showInLegend: true
				}
			},
			series : [ {
				type : 'pie',
				name : seriesname,
				data : data
			} ]
	};
	$(divid).highcharts(chartObj);
}
/**
 * 多Y轴
 * 
 * @param divid
 * @param seriesname
 * @param data
 * @param title
 */
function drawMutilYxis(divid, xCate, yAxis, series, title) {
	var chartObj = {
		chart : {
			zoomType: 'x',
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false
		},
		title : {
			text : title
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		tooltip: {
            shared: true,
            xDateFormat: '%Y-%m-%d %H:%M'
        },
		plotOptions : {

		},
		xAxis : {
			categories : xCate
		},
		yAxis : yAxis,
		series : series
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}
/**
 * 时间轴折线图
 * @param divid
 * @param seriesname
 * @param data
 * @param title
 */
function drawLineWithTime2(divid, seriesname, data, title) {

	var chartObj = {
		chart : {
			zoomType : 'x',
			type : 'line',
			events : {
				// 点击图表后在指定区域 zoomUpDiv 放大显示
				click : null
			}
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		plotOptions : {
			series : {
				// 去掉点的marker, 使图形更美观
				marker : {
					enabled : false,
					states : {
						hover : {
							enabled : true
						}
					}
				}
			}
		},
		series : [ {
			name : seriesname,
			data : data
		} ],
		xAxis : {
			labels:{
				enabled:false
			},
			type : 'datetime',
			tickInterval : 3600 * 1000 * 24,
			dateTimeLabelFormats : {
				hour : '%H:%M',
				day : '%Y-%m-%d',
				week : '%e. %b',
				month : '%b %y',
				year : '%Y'
			}
		},
		yAxis : {
			title : {
				text : ''
			}
		},
		tooltip : {
			crosshairs : true,
			shared : true,
			formatter : function() { // 当鼠标悬停图表上时, 格式化提示信息
				var tipText = '<b>'
						+ Highcharts.dateFormat('%Y-%m-%d', this.x)
						+ '</b>';
				$.each(this.points, function(i, point) {
					tipText += '<br/>' + point.series.name + ': ' + point.y;
				});
				return tipText;
			}
		},
		title : {
			// 不显示图表标题
			text : null
		}
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}
/**
 * 
 * @param divid
 * @param seriesname
 * @param data
 * @param title
 */
function drawLineWithTime(divid, seriesname, data, title,withhms) {
	var df;
    if(withhms==true){
    	df='%Y-%m-%d %H:%M';
    }else{
    	df='%Y-%m-%d';
    }
	var chartObj = {
		chart : {
			zoomType : 'x',
			type : 'spline',
			events : {
				// 点击图表后在指定区域 zoomUpDiv 放大显示
				click : null
			}
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		plotOptions : {
			series : {
				// 去掉点的marker, 使图形更美观
				marker : {
					enabled : false,
					states : {
						hover : {
							enabled : true
						}
					}
				}
			}
		},
		series : [ {
			name : seriesname,
			data : data
		} ],
		xAxis : {
			type : 'datetime',
			tickInterval : 3600 * 1000 * 24,
			dateTimeLabelFormats : {
				hour : '%H:%M',
				day : '%Y-%m-%d',
				week : '%e. %b',
				month : '%b %y',
				year : '%Y'
			}
		},
		yAxis : {
			title : {
				text : ''
			}
		},
		tooltip : {
			crosshairs : true,
			shared : true,
			formatter : function() { // 当鼠标悬停图表上时, 格式化提示信息
				var tipText = '<b>'
						+ Highcharts.dateFormat(df, this.x)
						+ '</b>';
				$.each(this.points, function(i, point) {
					tipText += '<br/>' + point.series.name + ': ' + point.y;
				});
				return tipText;
			}
		},
		title : {
			// 不显示图表标题
			text : null
		}
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}
/**
 * 画两条线
 * 
 * @param divid
 * @param seriesname1
 * @param seriesname2
 * @param data1
 * @param data2
 * @param title
 */
function drawTwoLineWithTime(divid, seriesname1, seriesname2, data1, data2,
		title) {

	var chartObj = {
		chart : {
			zoomType : 'x',
			type : 'spline',
			events : {
				// 点击图表后在指定区域 zoomUpDiv 放大显示
				click : null
			}
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		plotOptions : {
			series : {
				// 去掉点的marker, 使图形更美观
				marker : {
					enabled : false,
					states : {
						hover : {
							enabled : true
						}
					}
				}
			}
		},
		series : [ {
			type : 'line',
			name : seriesname1,
			color : 'BLUE',
			data : data1
		}, {
			type : 'line',
			yAxis : 1,
			name : seriesname2,
			color : 'RED',
			data : data2
		} ],
		xAxis : {
			type : 'datetime',
			tickInterval : 3600 * 1000 * 24,
			dateTimeLabelFormats : {
				hour : '%H:%M',
				day : '%e/%b',
				week : '%e. %b',
				month : '%b %y',
				year : '%Y'
			}
		},
		yAxis : [ {
			title : {
				text : '数量',
				style : {
					color : 'BLUE'
				}
			},
			lables : {
				style : {
					color : 'BLUE'
				}
			}
		}, {
			title : {
				text : '占比',
				style : {
					color : 'RED'
				}
			},
			labels : {
				format : '{value}%',
				style : {
					color : 'RED'
				}
			},
			opposite : true
		} ],
		tooltip : {
			crosshairs : true,
			shared : true,
			formatter : function() { // 当鼠标悬停图表上时, 格式化提示信息
				var tipText = '<b>'
						+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x)
						+ '</b>';
				$.each(this.points, function(i, point) {
					tipText += '<br/>' + point.series.name + ': ';
					if ((point.y + '').indexOf('.') != -1) {
						tipText += Highcharts.numberFormat(point.y, 2);
					} else {
						tipText += point.y;
					}
				});
				return tipText;
			}
		},
		title : {
			// 不显示图表标题
			text : null
		}
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}

function drawTwoLineWithOneYForTime(divid, seriesname1, seriesname2, data1,
		data2, title) {

	var chartObj = {
		chart : {
			zoomType : 'x',
			type : 'line',
			events : {
				// 点击图表后在指定区域 zoomUpDiv 放大显示
				click : null
			}
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		plotOptions : {
			series : {
				// 去掉点的marker, 使图形更美观
				marker : {
					enabled : false,
					states : {
						hover : {
							enabled : true
						}
					}
				}
			}
		},
		series : [ {
			name : seriesname1,
			data : data1
		}, {
			name : seriesname2,
			data : data2
		} ],
		xAxis : {
			labels:{
				enabled:false
			},
			type : 'datetime',
			tickInterval : 3600 * 1000 * 24,
			dateTimeLabelFormats : {
				hour : '%H:%M',
				day : '%Y-%m-%d',
				week : '%e. %b',
				month : '%b %y',
				year : '%Y'
			}
		},
		yAxis : {
			title : {
				text : ''
			}
		},
		tooltip : {
			crosshairs : true,
			shared : true,
			formatter : function() { // 当鼠标悬停图表上时, 格式化提示信息
				var tipText = '<b>'
						+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x)
						+ '</b>';
				$.each(this.points, function(i, point) {
					tipText += '<br/>' + point.series.name + ': ';
					if ((point.y + '').indexOf('.') != -1) {
						tipText += Highcharts.numberFormat(point.y, 2);
					} else {
						tipText += point.y;
					}
				});
				return tipText;
			}
		},
		title : {
			// 不显示图表标题
			text : null
		}
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}
/**
 * 单Y轴双线非时间轴
 * @param divid
 * @param seriesname1
 * @param seriesname2
 * @param data1
 * @param data2
 * @param title
 */
function drawTwoLineWithOneY(divid, xCate, seriesname1, seriesname2, data1,
		data2, title) {
	
	var chartObj = {
			chart : {
				zoomType : 'x',
				type : 'line',
				events : {
					// 点击图表后在指定区域 zoomUpDiv 放大显示
					click : null
				}
			},
			// 去掉 highcharts.com 链接
			credits : {
				enabled : false,
				text : ''
			},
			plotOptions : {
				series : {
					// 去掉点的marker, 使图形更美观
					marker : {
						enabled : false,
						states : {
							hover : {
								enabled : true
							}
						}
					}
				}
			},
			series : [ {
				name : seriesname1,
				data : data1
			}, {
				name : seriesname2,
				data : data2
			} ],
			xAxis : {
				categories : xCate
			},
			yAxis : {
				title : {
					text : ''
				}
			},
			tooltip : {
				crosshairs : true,
				shared : true,
				formatter : function() { // 当鼠标悬停图表上时, 格式化提示信息
					var tipText = '<b>' + this.x + '</b>';
					$.each(this.points, function(i, point) {
						tipText += '<br/>' + point.series.name + ': ';
						if ((point.y + '').indexOf('.') != -1) {
							tipText += Highcharts.numberFormat(point.y, 2);
						} else {
							tipText += point.y;
						}
					});
					return tipText;
				}
			},
			title : {
				// 不显示图表标题
				text : null
			}
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}
/**
 * 横轴非时间
 * @param divid
 * @param seriesname
 * @param data
 * @param title
 */
function drawSimpleLine(divid, seriesname, data, title,dateflag) {
	var chartObj = {
		chart : {
			zoomType : 'x',
			type : 'spline',
			events : {
				// 点击图表后在指定区域 zoomUpDiv 放大显示
				click : null
			}
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		plotOptions : {
			series : {
				// 去掉点的marker, 使图形更美观
				marker : {
					enabled : false,
					states : {
						hover : {
							enabled : true
						}
					}
				}
			}
		},
		series : [ {
			name : seriesname,
			data : data
		} ],
		xAxis :{
			allowDecimals: false,
			labels:{
				formatter:function(){
					if(dateflag=='month'){
						return this.value+'月';
					}else if(dateflag=='week'){
						return '第'+this.value+'周';
					}
				}
			}
		},
		yAxis : {
			title : {
				text : ''
			}
		},
		tooltip : {
			crosshairs : true,
			shared : true,
			formatter : function() { // 当鼠标悬停图表上时, 格式化提示信息
				var tipText ='';
				if(dateflag=='month'){
					tipText+= '<b>'+ this.x+ '月</b>';
				}else if(dateflag=='week'){
					tipText+= '<b>第'+ this.x+ '周</b>';
				}
				
				$.each(this.points, function(i, point) {
					tipText += '<br/>' + point.series.name + ': ' + point.y;
				});
				return tipText;
			}
		},
		title : {
			// 不显示图表标题
			text : null
		}
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}
/**
 * 
 * @param divid
 * @param seriesname
 * @param data
 * @param title
 */
function drawSimpleLine2(divid, xCate, seriesname, data, title) {
	var chartObj = {
		chart : {
			zoomType : 'x',
			type : 'line',
			events : {
				// 点击图表后在指定区域 zoomUpDiv 放大显示
				click : null
			}
		},
		// 去掉 highcharts.com 链接
		credits : {
			enabled : false,
			text : ''
		},
		plotOptions : {
			series : {
				// 去掉点的marker, 使图形更美观
				marker : {
					enabled : false,
					states : {
						hover : {
							enabled : true
						}
					}
				}
			}
		},
		series : [ {
			name : seriesname,
			data : data
		} ],
		xAxis :{
			categories : xCate
		},
		yAxis : {
			title : {
				text : ''
			}
		},
		tooltip : {
			crosshairs : true,
			shared : true,
			formatter : function() { // 当鼠标悬停图表上时, 格式化提示信息
				var tipText = '<b>'	+ this.x + '</b>';				
				$.each(this.points, function(i, point) {
					tipText += '<br/>' + point.series.name + ': ' + point.y;
				});
				return tipText;
			}
		},
		title : {
			// 不显示图表标题
			text : null
		}
	};
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});
	$(divid).highcharts(chartObj);
}
