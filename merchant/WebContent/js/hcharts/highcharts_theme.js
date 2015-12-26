Highcharts.theme = {
	colors : [ '#058DC7',  '#ED561B','#50B432', '#DDDF00', '#24CBE5',
			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	chart : {
		backgroundColor : {
			linearGradient : {
				x1 : 0,
				y1 : 0,
				x2 : 1,
				y2 : 1
			},
			stops : [ [ 0, 'rgb(255, 255, 255)' ], [ 1, 'rgb(240, 240, 255)' ] ]
		},
		borderWidth : 2,
		plotBackgroundColor : 'rgba(255, 255, 255, .9)',
		plotShadow : true,
		plotBorderWidth : 1
	},
	title : {
		style : {
			color : '#000',
			font : 'bold 16px "Trebuchet MS", Verdana, sans-serif'
		}
	},
	subtitle : {
		style : {
			color : '#666666',
			font : 'bold 12px "Trebuchet MS", Verdana, sans-serif'
		}
	},
	xAxis : {
		gridLineWidth : 1,
		lineColor : '#000',
		tickColor : '#000',
		labels : {			
			style : {
				color : '#000',
				font : '11px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title : {
			style : {
				color : '#333',
				fontWeight : 'bold',
				fontSize : '12px',
				fontFamily : 'Trebuchet MS, Verdana, sans-serif'
			}
		}
	},
	yAxis : {
		minorTickInterval : 'auto',
		lineColor : '#000',
		lineWidth : 1,
		tickWidth : 1,
		tickColor : '#000',
		labels : {
			style : {
				color : '#000',
				font : '11px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title : {
			style : {
				color : '#333',
				fontWeight : 'bold',
				fontSize : '12px',
				fontFamily : 'Trebuchet MS, Verdana, sans-serif'
			}
		}
	},
	legend : {
		itemStyle : {
			font : '9pt Trebuchet MS, Verdana, sans-serif',
			color : 'black'
		},
		itemHoverStyle : {
			color : '#039'
		},
		itemHiddenStyle : {
			color : 'gray'
		}
	},
	plotOptions : {
		area : {
			fillColor : {
				linearGradient : {
					x1 : 0,
					y1 : 0,
					x2 : 0,
					y2 : 1
				},
				stops : [
						[ 0, Highcharts.getOptions().colors[0] ],
						[
								1,
								Highcharts.Color(
										Highcharts.getOptions().colors[0])
										.setOpacity(0).get('rgba') ] ]
			},
			marker : {
				radius : 2
			},
			lineWidth : 1,
			states : {
				hover : {
					lineWidth : 1
				}
			},
			threshold : null
		}
	},
	labels : {
		style : {
			color : '#99b'
		}
	},
	navigation : {
		buttonOptions : {
			theme : {
				stroke : '#CCCCCC'
			}
		}
	}
};
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);